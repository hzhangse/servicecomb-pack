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
		LOG.info("header received from client:" + requestHeaders);
		Metadata.Key<String> globalTx = Metadata.Key.of(GLOBAL_TX_ID_KEY, Metadata.ASCII_STRING_MARSHALLER);
		Metadata.Key<String> localTx = Metadata.Key.of(LOCAL_TX_ID_KEY, Metadata.ASCII_STRING_MARSHALLER);
		String globalTxId = requestHeaders.get(globalTx);
		String localTxId = requestHeaders.get(localTx);
		if (StringUtils.isEmpty(globalTxId)||StringUtils.isEmpty(localTxId)) {
			call.close(Status.DATA_LOSS, requestHeaders);
		}else {
			omegaContext.setGlobalTxId(globalTxId);
			omegaContext.setLocalTxId(localTxId);
		}
		return next.startCall(new SimpleForwardingServerCall<ReqT, RespT>(call) {

		}, requestHeaders);
	}

}
