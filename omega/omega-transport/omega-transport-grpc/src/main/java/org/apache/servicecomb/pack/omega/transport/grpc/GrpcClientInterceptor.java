/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package org.apache.servicecomb.pack.omega.transport.grpc;

import static org.apache.servicecomb.pack.omega.context.OmegaContext.GLOBAL_TX_ID_KEY;
import static org.apache.servicecomb.pack.omega.context.OmegaContext.LOCAL_TX_ID_KEY;

import java.lang.invoke.MethodHandles;

import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

public class GrpcClientInterceptor implements ClientInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final OmegaContext omegaContext;

	public GrpcClientInterceptor(OmegaContext omegaContext) {
		this.omegaContext = omegaContext;
	}

	@Override
	public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method,
			CallOptions callOptions, Channel next) {
		
		final ClientCall<ReqT, RespT> clientCall = next.newCall(method, callOptions);
		return new ForwardingClientCall<ReqT, RespT>() {
			@Override
			protected ClientCall<ReqT, RespT> delegate() {
				return clientCall;
			}

			@Override
			public void start(Listener<RespT> responseListener, Metadata headers) {
				
				if (omegaContext != null && omegaContext.globalTxId() != null) {
					Metadata.Key<String> globalTx = Metadata.Key.of(GLOBAL_TX_ID_KEY, Metadata.ASCII_STRING_MARSHALLER);
					Metadata.Key<String> localTx = Metadata.Key.of(LOCAL_TX_ID_KEY, Metadata.ASCII_STRING_MARSHALLER);
					headers.put(globalTx, omegaContext.globalTxId());
					headers.put(localTx, omegaContext.localTxId());

					LOG.debug("Added {} {} and {} {} to request header", GLOBAL_TX_ID_KEY, omegaContext.globalTxId(),
							LOCAL_TX_ID_KEY, omegaContext.localTxId());
				} else {
					LOG.debug(
							"Cannot inject transaction ID, as the OmegaContext is null or cannot get the globalTxId.");
				}
				super.start(responseListener, headers);
			}
		};
	}
}
