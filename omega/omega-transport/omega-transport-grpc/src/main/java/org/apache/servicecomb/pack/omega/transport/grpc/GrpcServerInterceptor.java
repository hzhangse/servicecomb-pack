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
import org.springframework.util.StringUtils;

import io.grpc.ForwardingServerCall.SimpleForwardingServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;

public class GrpcServerInterceptor implements ServerInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final OmegaContext omegaContext;

	public GrpcServerInterceptor(OmegaContext omegaContext) {
		this.omegaContext = omegaContext;
	}

	@Override
	public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata requestHeaders,
			ServerCallHandler<ReqT, RespT> next) {
		
		Metadata.Key<String> globalTx = Metadata.Key.of(GLOBAL_TX_ID_KEY, Metadata.ASCII_STRING_MARSHALLER);
		Metadata.Key<String> localTx = Metadata.Key.of(LOCAL_TX_ID_KEY, Metadata.ASCII_STRING_MARSHALLER);
		String globalTxId = requestHeaders.get(globalTx);
		String localTxId = requestHeaders.get(localTx);
		if (StringUtils.isEmpty(globalTxId)||StringUtils.isEmpty(localTxId)) {
			call.close(Status.DATA_LOSS, requestHeaders);
			LOG.debug(
					"Cannot set OmegaContext transaction ID, as the requestHeaders  is null or cannot get the globalTxId.");
		}else {
			omegaContext.setGlobalTxId(globalTxId);
			omegaContext.setLocalTxId(localTxId);
			LOG.debug("set OmegaContext globalIxId {} {} and localTxId {} {} ", GLOBAL_TX_ID_KEY, globalTxId,
					LOCAL_TX_ID_KEY, localTxId);
		}
		return next.startCall(new SimpleForwardingServerCall<ReqT, RespT>(call) {

		}, requestHeaders);
	}

}
