package cn.wegostack.sundial.registry.client.proto;

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
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<SundialRegistryProto.PublishCommand,
      SundialRegistryProto.RegistryReply> getPublishMethod() {
    io.grpc.MethodDescriptor<SundialRegistryProto.PublishCommand, SundialRegistryProto.RegistryReply> getPublishMethod;
    if ((getPublishMethod = SundialRegistryGrpc.getPublishMethod) == null) {
      synchronized (SundialRegistryGrpc.class) {
        if ((getPublishMethod = SundialRegistryGrpc.getPublishMethod) == null) {
          SundialRegistryGrpc.getPublishMethod = getPublishMethod =
              io.grpc.MethodDescriptor.<SundialRegistryProto.PublishCommand, SundialRegistryProto.RegistryReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
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
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<SundialRegistryProto.SubscribeCommand,
      SundialRegistryProto.RegistryReply> getSubscribeMethod() {
    io.grpc.MethodDescriptor<SundialRegistryProto.SubscribeCommand, SundialRegistryProto.RegistryReply> getSubscribeMethod;
    if ((getSubscribeMethod = SundialRegistryGrpc.getSubscribeMethod) == null) {
      synchronized (SundialRegistryGrpc.class) {
        if ((getSubscribeMethod = SundialRegistryGrpc.getSubscribeMethod) == null) {
          SundialRegistryGrpc.getSubscribeMethod = getSubscribeMethod =
              io.grpc.MethodDescriptor.<SundialRegistryProto.SubscribeCommand, SundialRegistryProto.RegistryReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
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
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<SundialRegistryProto.HeartbeatCommand,
      SundialRegistryProto.RegistryReply> getHeartbeatMethod() {
    io.grpc.MethodDescriptor<SundialRegistryProto.HeartbeatCommand, SundialRegistryProto.RegistryReply> getHeartbeatMethod;
    if ((getHeartbeatMethod = SundialRegistryGrpc.getHeartbeatMethod) == null) {
      synchronized (SundialRegistryGrpc.class) {
        if ((getHeartbeatMethod = SundialRegistryGrpc.getHeartbeatMethod) == null) {
          SundialRegistryGrpc.getHeartbeatMethod = getHeartbeatMethod =
              io.grpc.MethodDescriptor.<SundialRegistryProto.HeartbeatCommand, SundialRegistryProto.RegistryReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
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
   */
  public static abstract class SundialRegistryImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<SundialRegistryProto.PublishCommand> publish(
        io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getPublishMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<SundialRegistryProto.SubscribeCommand> subscribe(
        io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getSubscribeMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<SundialRegistryProto.HeartbeatCommand> heartbeat(
        io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getHeartbeatMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPublishMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                SundialRegistryProto.PublishCommand,
                SundialRegistryProto.RegistryReply>(
                  this, METHODID_PUBLISH)))
          .addMethod(
            getSubscribeMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                SundialRegistryProto.SubscribeCommand,
                SundialRegistryProto.RegistryReply>(
                  this, METHODID_SUBSCRIBE)))
          .addMethod(
            getHeartbeatMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                SundialRegistryProto.HeartbeatCommand,
                SundialRegistryProto.RegistryReply>(
                  this, METHODID_HEARTBEAT)))
          .build();
    }
  }

  /**
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
    public io.grpc.stub.StreamObserver<SundialRegistryProto.PublishCommand> publish(
        io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getPublishMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<SundialRegistryProto.SubscribeCommand> subscribe(
        io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<SundialRegistryProto.HeartbeatCommand> heartbeat(
        io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getHeartbeatMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
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
  }

  /**
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
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PUBLISH:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.publish(
              (io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply>) responseObserver);
        case METHODID_SUBSCRIBE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.subscribe(
              (io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply>) responseObserver);
        case METHODID_HEARTBEAT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.heartbeat(
              (io.grpc.stub.StreamObserver<SundialRegistryProto.RegistryReply>) responseObserver);
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
