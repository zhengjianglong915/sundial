package cn.wegostack.sundial.discovery.registry.proto;

import io.grpc.stub.ClientCalls;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * server service
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.27.0)",
    comments = "Source: SundialRegistry.proto")
public final class SundialRegistryGrpc {

  private SundialRegistryGrpc() {}

  public static final String SERVICE_NAME = "sundial.SundialRegistry";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<SundialRegistryProto.PublishCommand,
      SundialRegistryProto.RegistryReply> getPublishMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "publish",
      requestType = SundialRegistryProto.PublishCommand.class,
      responseType = SundialRegistryProto.RegistryReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SundialRegistryProto.PublishCommand,
      SundialRegistryProto.RegistryReply> getPublishMethod() {
    io.grpc.MethodDescriptor<SundialRegistryProto.PublishCommand, SundialRegistryProto.RegistryReply> getPublishMethod;
    if ((getPublishMethod = SundialRegistryGrpc.getPublishMethod) == null) {
      synchronized (SundialRegistryGrpc.class) {
        if ((getPublishMethod = SundialRegistryGrpc.getPublishMethod) == null) {
          SundialRegistryGrpc.getPublishMethod = getPublishMethod =
              io.grpc.MethodDescriptor.<SundialRegistryProto.PublishCommand, SundialRegistryProto.RegistryReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "publish"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SundialRegistryProto.PublishCommand.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SundialRegistryProto.RegistryReply.getDefaultInstance()))
              .setSchemaDescriptor(new SundialRegistryMethodDescriptorSupplier("publish"))
              .build();
        }
      }
    }
    return getPublishMethod;
  }

  private static volatile io.grpc.MethodDescriptor<SundialRegistryProto.SubscribeCommand,
      SundialRegistryProto.RegistryReply> getSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subscribe",
      requestType = SundialRegistryProto.SubscribeCommand.class,
      responseType = SundialRegistryProto.RegistryReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SundialRegistryProto.SubscribeCommand,
      SundialRegistryProto.RegistryReply> getSubscribeMethod() {
    io.grpc.MethodDescriptor<SundialRegistryProto.SubscribeCommand, SundialRegistryProto.RegistryReply> getSubscribeMethod;
    if ((getSubscribeMethod = SundialRegistryGrpc.getSubscribeMethod) == null) {
      synchronized (SundialRegistryGrpc.class) {
        if ((getSubscribeMethod = SundialRegistryGrpc.getSubscribeMethod) == null) {
          SundialRegistryGrpc.getSubscribeMethod = getSubscribeMethod =
              io.grpc.MethodDescriptor.<SundialRegistryProto.SubscribeCommand, SundialRegistryProto.RegistryReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SundialRegistryProto.SubscribeCommand.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SundialRegistryProto.RegistryReply.getDefaultInstance()))
              .setSchemaDescriptor(new SundialRegistryMethodDescriptorSupplier("subscribe"))
              .build();
        }
      }
    }
    return getSubscribeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<SundialRegistryProto.HeartbeatCommand,
      SundialRegistryProto.RegistryReply> getHeartbeatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "heartbeat",
      requestType = SundialRegistryProto.HeartbeatCommand.class,
      responseType = SundialRegistryProto.RegistryReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<SundialRegistryProto.HeartbeatCommand,
      SundialRegistryProto.RegistryReply> getHeartbeatMethod() {
    io.grpc.MethodDescriptor<SundialRegistryProto.HeartbeatCommand, SundialRegistryProto.RegistryReply> getHeartbeatMethod;
    if ((getHeartbeatMethod = SundialRegistryGrpc.getHeartbeatMethod) == null) {
      synchronized (SundialRegistryGrpc.class) {
        if ((getHeartbeatMethod = SundialRegistryGrpc.getHeartbeatMethod) == null) {
          SundialRegistryGrpc.getHeartbeatMethod = getHeartbeatMethod =
              io.grpc.MethodDescriptor.<SundialRegistryProto.HeartbeatCommand, SundialRegistryProto.RegistryReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "heartbeat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SundialRegistryProto.HeartbeatCommand.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  SundialRegistryProto.RegistryReply.getDefaultInstance()))
              .setSchemaDescriptor(new SundialRegistryMethodDescriptorSupplier("heartbeat"))
              .build();
        }
      }
    }
    return getHeartbeatMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SundialRegistryStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SundialRegistryStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SundialRegistryStub>() {
        @Override
        public SundialRegistryStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SundialRegistryStub(channel, callOptions);
        }
      };
    return SundialRegistryStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SundialRegistryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SundialRegistryBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SundialRegistryBlockingStub>() {
        @Override
        public SundialRegistryBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SundialRegistryBlockingStub(channel, callOptions);
        }
      };
    return SundialRegistryBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SundialRegistryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SundialRegistryFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SundialRegistryFutureStub>() {
        @Override
        public SundialRegistryFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SundialRegistryFutureStub(channel, callOptions);
        }
      };
    return SundialRegistryFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * server service
   * </pre>
   */
  public static abstract class SundialRegistryImplBase implements io.grpc.BindableService {

    /**
     */
    public void publish(SundialRegistryProto.PublishCommand request,
                        io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      asyncUnimplementedUnaryCall(getPublishMethod(), responseObserver);
    }

    /**
     */
    public void subscribe(SundialRegistryProto.SubscribeCommand request,
                          io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }

    /**
     */
    public void heartbeat(SundialRegistryProto.HeartbeatCommand request,
                          io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      asyncUnimplementedUnaryCall(getHeartbeatMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPublishMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SundialRegistryProto.PublishCommand,
                SundialRegistryProto.RegistryReply>(
                  this, METHODID_PUBLISH)))
          .addMethod(
            getSubscribeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SundialRegistryProto.SubscribeCommand,
                SundialRegistryProto.RegistryReply>(
                  this, METHODID_SUBSCRIBE)))
          .addMethod(
            getHeartbeatMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                SundialRegistryProto.HeartbeatCommand,
                SundialRegistryProto.RegistryReply>(
                  this, METHODID_HEARTBEAT)))
          .build();
    }
  }

  /**
   * <pre>
   * server service
   * </pre>
   */
  public static final class SundialRegistryStub extends io.grpc.stub.AbstractAsyncStub<SundialRegistryStub> {
    private SundialRegistryStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SundialRegistryStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SundialRegistryStub(channel, callOptions);
    }

    /**
     */
    public void publish(SundialRegistryProto.PublishCommand request,
                        io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPublishMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subscribe(SundialRegistryProto.SubscribeCommand request,
                          io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void heartbeat(SundialRegistryProto.HeartbeatCommand request,
                          io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHeartbeatMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * server service
   * </pre>
   */
  public static final class SundialRegistryBlockingStub extends io.grpc.stub.AbstractBlockingStub<SundialRegistryBlockingStub> {
    private SundialRegistryBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SundialRegistryBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SundialRegistryBlockingStub(channel, callOptions);
    }

    /**
     */
    public SundialRegistryProto.RegistryReply publish(SundialRegistryProto.PublishCommand request) {
      return blockingUnaryCall(
          getChannel(), getPublishMethod(), getCallOptions(), request);
    }

    /**
     */
    public SundialRegistryProto.RegistryReply subscribe(SundialRegistryProto.SubscribeCommand request) {
      return blockingUnaryCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }

    /**
     */
    public SundialRegistryProto.RegistryReply heartbeat(SundialRegistryProto.HeartbeatCommand request) {
      return blockingUnaryCall(
          getChannel(), getHeartbeatMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * server service
   * </pre>
   */
  public static final class SundialRegistryFutureStub extends io.grpc.stub.AbstractFutureStub<SundialRegistryFutureStub> {
    private SundialRegistryFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SundialRegistryFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SundialRegistryFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SundialRegistryProto.RegistryReply> publish(
        SundialRegistryProto.PublishCommand request) {
      return futureUnaryCall(
          getChannel().newCall(getPublishMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SundialRegistryProto.RegistryReply> subscribe(
        SundialRegistryProto.SubscribeCommand request) {
      return futureUnaryCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<SundialRegistryProto.RegistryReply> heartbeat(
        SundialRegistryProto.HeartbeatCommand request) {
      return futureUnaryCall(
          getChannel().newCall(getHeartbeatMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PUBLISH = 0;
  private static final int METHODID_SUBSCRIBE = 1;
  private static final int METHODID_HEARTBEAT = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SundialRegistryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SundialRegistryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PUBLISH:
          serviceImpl.publish((SundialRegistryProto.PublishCommand) request,
              (io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply>) responseObserver);
          break;
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((SundialRegistryProto.SubscribeCommand) request,
              (io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply>) responseObserver);
          break;
        case METHODID_HEARTBEAT:
          serviceImpl.heartbeat((SundialRegistryProto.HeartbeatCommand) request,
              (io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SundialRegistryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SundialRegistryBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SundialRegistryProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SundialRegistry");
    }
  }

  private static final class SundialRegistryFileDescriptorSupplier
      extends SundialRegistryBaseDescriptorSupplier {
    SundialRegistryFileDescriptorSupplier() {}
  }

  private static final class SundialRegistryMethodDescriptorSupplier
      extends SundialRegistryBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SundialRegistryMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SundialRegistryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SundialRegistryFileDescriptorSupplier())
              .addMethod(getPublishMethod())
              .addMethod(getSubscribeMethod())
              .addMethod(getHeartbeatMethod())
              .build();
        }
      }
    }
    return result;
  }
}
