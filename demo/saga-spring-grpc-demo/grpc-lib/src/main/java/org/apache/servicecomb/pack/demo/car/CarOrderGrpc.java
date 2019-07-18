package org.apache.servicecomb.pack.demo.car;

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
    comments = "Source: car.proto")
public final class CarOrderGrpc {

  private CarOrderGrpc() {}

  public static final String SERVICE_NAME = "CarOrder";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.apache.servicecomb.pack.demo.car.Car.CarRequest,
      org.apache.servicecomb.pack.demo.car.Car.CarReply> getOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "order",
      requestType = org.apache.servicecomb.pack.demo.car.Car.CarRequest.class,
      responseType = org.apache.servicecomb.pack.demo.car.Car.CarReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.servicecomb.pack.demo.car.Car.CarRequest,
      org.apache.servicecomb.pack.demo.car.Car.CarReply> getOrderMethod() {
    io.grpc.MethodDescriptor<org.apache.servicecomb.pack.demo.car.Car.CarRequest, org.apache.servicecomb.pack.demo.car.Car.CarReply> getOrderMethod;
    if ((getOrderMethod = CarOrderGrpc.getOrderMethod) == null) {
      synchronized (CarOrderGrpc.class) {
        if ((getOrderMethod = CarOrderGrpc.getOrderMethod) == null) {
          CarOrderGrpc.getOrderMethod = getOrderMethod = 
              io.grpc.MethodDescriptor.<org.apache.servicecomb.pack.demo.car.Car.CarRequest, org.apache.servicecomb.pack.demo.car.Car.CarReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CarOrder", "order"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.servicecomb.pack.demo.car.Car.CarRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.servicecomb.pack.demo.car.Car.CarReply.getDefaultInstance()))
                  .setSchemaDescriptor(new CarOrderMethodDescriptorSupplier("order"))
                  .build();
          }
        }
     }
     return getOrderMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CarOrderStub newStub(io.grpc.Channel channel) {
    return new CarOrderStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CarOrderBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CarOrderBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CarOrderFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CarOrderFutureStub(channel);
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static abstract class CarOrderImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void order(org.apache.servicecomb.pack.demo.car.Car.CarRequest request,
        io.grpc.stub.StreamObserver<org.apache.servicecomb.pack.demo.car.Car.CarReply> responseObserver) {
      asyncUnimplementedUnaryCall(getOrderMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.servicecomb.pack.demo.car.Car.CarRequest,
                org.apache.servicecomb.pack.demo.car.Car.CarReply>(
                  this, METHODID_ORDER)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class CarOrderStub extends io.grpc.stub.AbstractStub<CarOrderStub> {
    private CarOrderStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CarOrderStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CarOrderStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CarOrderStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void order(org.apache.servicecomb.pack.demo.car.Car.CarRequest request,
        io.grpc.stub.StreamObserver<org.apache.servicecomb.pack.demo.car.Car.CarReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOrderMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class CarOrderBlockingStub extends io.grpc.stub.AbstractStub<CarOrderBlockingStub> {
    private CarOrderBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CarOrderBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CarOrderBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CarOrderBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public org.apache.servicecomb.pack.demo.car.Car.CarReply order(org.apache.servicecomb.pack.demo.car.Car.CarRequest request) {
      return blockingUnaryCall(
          getChannel(), getOrderMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class CarOrderFutureStub extends io.grpc.stub.AbstractStub<CarOrderFutureStub> {
    private CarOrderFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CarOrderFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CarOrderFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CarOrderFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.servicecomb.pack.demo.car.Car.CarReply> order(
        org.apache.servicecomb.pack.demo.car.Car.CarRequest request) {
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
    private final CarOrderImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CarOrderImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ORDER:
          serviceImpl.order((org.apache.servicecomb.pack.demo.car.Car.CarRequest) request,
              (io.grpc.stub.StreamObserver<org.apache.servicecomb.pack.demo.car.Car.CarReply>) responseObserver);
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

  private static abstract class CarOrderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CarOrderBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.apache.servicecomb.pack.demo.car.Car.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CarOrder");
    }
  }

  private static final class CarOrderFileDescriptorSupplier
      extends CarOrderBaseDescriptorSupplier {
    CarOrderFileDescriptorSupplier() {}
  }

  private static final class CarOrderMethodDescriptorSupplier
      extends CarOrderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CarOrderMethodDescriptorSupplier(String methodName) {
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
      synchronized (CarOrderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CarOrderFileDescriptorSupplier())
              .addMethod(getOrderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
