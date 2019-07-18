package org.apache.servicecomb.pack.demo.hotel;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeter service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.14.0)",
    comments = "Source: hotel.proto")
public final class HotelOrderGrpc {

  private HotelOrderGrpc() {}

  public static final String SERVICE_NAME = "HotelOrder";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest,
      org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply> getOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "order",
      requestType = org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest.class,
      responseType = org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest,
      org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply> getOrderMethod() {
    io.grpc.MethodDescriptor<org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest, org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply> getOrderMethod;
    if ((getOrderMethod = HotelOrderGrpc.getOrderMethod) == null) {
      synchronized (HotelOrderGrpc.class) {
        if ((getOrderMethod = HotelOrderGrpc.getOrderMethod) == null) {
          HotelOrderGrpc.getOrderMethod = getOrderMethod = 
              io.grpc.MethodDescriptor.<org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest, org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "HotelOrder", "order"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply.getDefaultInstance()))
                  .setSchemaDescriptor(new HotelOrderMethodDescriptorSupplier("order"))
                  .build();
          }
        }
     }
     return getOrderMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HotelOrderStub newStub(io.grpc.Channel channel) {
    return new HotelOrderStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HotelOrderBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HotelOrderBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HotelOrderFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HotelOrderFutureStub(channel);
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static abstract class HotelOrderImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void order(org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest request,
        io.grpc.stub.StreamObserver<org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply> responseObserver) {
      asyncUnimplementedUnaryCall(getOrderMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest,
                org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply>(
                  this, METHODID_ORDER)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class HotelOrderStub extends io.grpc.stub.AbstractStub<HotelOrderStub> {
    private HotelOrderStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HotelOrderStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HotelOrderStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HotelOrderStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void order(org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest request,
        io.grpc.stub.StreamObserver<org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOrderMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class HotelOrderBlockingStub extends io.grpc.stub.AbstractStub<HotelOrderBlockingStub> {
    private HotelOrderBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HotelOrderBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HotelOrderBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HotelOrderBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply order(org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest request) {
      return blockingUnaryCall(
          getChannel(), getOrderMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class HotelOrderFutureStub extends io.grpc.stub.AbstractStub<HotelOrderFutureStub> {
    private HotelOrderFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HotelOrderFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HotelOrderFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HotelOrderFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply> order(
        org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getOrderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ORDER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HotelOrderImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HotelOrderImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ORDER:
          serviceImpl.order((org.apache.servicecomb.pack.demo.hotel.Hotel.HotelRequest) request,
              (io.grpc.stub.StreamObserver<org.apache.servicecomb.pack.demo.hotel.Hotel.HotelReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HotelOrderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HotelOrderBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.apache.servicecomb.pack.demo.hotel.Hotel.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HotelOrder");
    }
  }

  private static final class HotelOrderFileDescriptorSupplier
      extends HotelOrderBaseDescriptorSupplier {
    HotelOrderFileDescriptorSupplier() {}
  }

  private static final class HotelOrderMethodDescriptorSupplier
      extends HotelOrderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HotelOrderMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HotelOrderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HotelOrderFileDescriptorSupplier())
              .addMethod(getOrderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
