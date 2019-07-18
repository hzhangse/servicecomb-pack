package org.apache.servicecomb.pack.demo.booking;

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
    comments = "Source: booking.proto")
public final class BookingOrderGrpc {

  private BookingOrderGrpc() {}

  public static final String SERVICE_NAME = "BookingOrder";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest,
      org.apache.servicecomb.pack.demo.booking.Booking.BookingReply> getOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "order",
      requestType = org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest.class,
      responseType = org.apache.servicecomb.pack.demo.booking.Booking.BookingReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest,
      org.apache.servicecomb.pack.demo.booking.Booking.BookingReply> getOrderMethod() {
    io.grpc.MethodDescriptor<org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest, org.apache.servicecomb.pack.demo.booking.Booking.BookingReply> getOrderMethod;
    if ((getOrderMethod = BookingOrderGrpc.getOrderMethod) == null) {
      synchronized (BookingOrderGrpc.class) {
        if ((getOrderMethod = BookingOrderGrpc.getOrderMethod) == null) {
          BookingOrderGrpc.getOrderMethod = getOrderMethod = 
              io.grpc.MethodDescriptor.<org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest, org.apache.servicecomb.pack.demo.booking.Booking.BookingReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BookingOrder", "order"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.servicecomb.pack.demo.booking.Booking.BookingReply.getDefaultInstance()))
                  .setSchemaDescriptor(new BookingOrderMethodDescriptorSupplier("order"))
                  .build();
          }
        }
     }
     return getOrderMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BookingOrderStub newStub(io.grpc.Channel channel) {
    return new BookingOrderStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BookingOrderBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BookingOrderBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BookingOrderFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BookingOrderFutureStub(channel);
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static abstract class BookingOrderImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void order(org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest request,
        io.grpc.stub.StreamObserver<org.apache.servicecomb.pack.demo.booking.Booking.BookingReply> responseObserver) {
      asyncUnimplementedUnaryCall(getOrderMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest,
                org.apache.servicecomb.pack.demo.booking.Booking.BookingReply>(
                  this, METHODID_ORDER)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class BookingOrderStub extends io.grpc.stub.AbstractStub<BookingOrderStub> {
    private BookingOrderStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BookingOrderStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookingOrderStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BookingOrderStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void order(org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest request,
        io.grpc.stub.StreamObserver<org.apache.servicecomb.pack.demo.booking.Booking.BookingReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOrderMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class BookingOrderBlockingStub extends io.grpc.stub.AbstractStub<BookingOrderBlockingStub> {
    private BookingOrderBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BookingOrderBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookingOrderBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BookingOrderBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public org.apache.servicecomb.pack.demo.booking.Booking.BookingReply order(org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest request) {
      return blockingUnaryCall(
          getChannel(), getOrderMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class BookingOrderFutureStub extends io.grpc.stub.AbstractStub<BookingOrderFutureStub> {
    private BookingOrderFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BookingOrderFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookingOrderFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BookingOrderFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.servicecomb.pack.demo.booking.Booking.BookingReply> order(
        org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest request) {
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
    private final BookingOrderImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BookingOrderImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ORDER:
          serviceImpl.order((org.apache.servicecomb.pack.demo.booking.Booking.BookingRequest) request,
              (io.grpc.stub.StreamObserver<org.apache.servicecomb.pack.demo.booking.Booking.BookingReply>) responseObserver);
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

  private static abstract class BookingOrderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BookingOrderBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.apache.servicecomb.pack.demo.booking.Booking.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BookingOrder");
    }
  }

  private static final class BookingOrderFileDescriptorSupplier
      extends BookingOrderBaseDescriptorSupplier {
    BookingOrderFileDescriptorSupplier() {}
  }

  private static final class BookingOrderMethodDescriptorSupplier
      extends BookingOrderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BookingOrderMethodDescriptorSupplier(String methodName) {
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
      synchronized (BookingOrderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BookingOrderFileDescriptorSupplier())
              .addMethod(getOrderMethod())
              .build();
        }
      }
    }
    return result;
  }
}
