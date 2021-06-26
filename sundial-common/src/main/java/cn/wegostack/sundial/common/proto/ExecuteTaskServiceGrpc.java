package cn.wegostack.sundial.common.proto;

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
    comments = "Source: ExecuteCommand.proto")
public final class ExecuteTaskServiceGrpc {

  private ExecuteTaskServiceGrpc() {}

  public static final String SERVICE_NAME = "sundial.ExecuteTaskService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ExecuteCommandProto.ExecuteCommand,
      ExecuteCommandProto.CommandResult> getExecuteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "execute",
      requestType = ExecuteCommandProto.ExecuteCommand.class,
      responseType = ExecuteCommandProto.CommandResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ExecuteCommandProto.ExecuteCommand,
      ExecuteCommandProto.CommandResult> getExecuteMethod() {
    io.grpc.MethodDescriptor<ExecuteCommandProto.ExecuteCommand, ExecuteCommandProto.CommandResult> getExecuteMethod;
    if ((getExecuteMethod = ExecuteTaskServiceGrpc.getExecuteMethod) == null) {
      synchronized (ExecuteTaskServiceGrpc.class) {
        if ((getExecuteMethod = ExecuteTaskServiceGrpc.getExecuteMethod) == null) {
          ExecuteTaskServiceGrpc.getExecuteMethod = getExecuteMethod =
              io.grpc.MethodDescriptor.<ExecuteCommandProto.ExecuteCommand, ExecuteCommandProto.CommandResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "execute"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ExecuteCommandProto.ExecuteCommand.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ExecuteCommandProto.CommandResult.getDefaultInstance()))
              .setSchemaDescriptor(new ExecuteTaskServiceMethodDescriptorSupplier("execute"))
              .build();
        }
      }
    }
    return getExecuteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ExecuteTaskServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExecuteTaskServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExecuteTaskServiceStub>() {
        @Override
        public ExecuteTaskServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExecuteTaskServiceStub(channel, callOptions);
        }
      };
    return ExecuteTaskServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ExecuteTaskServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExecuteTaskServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExecuteTaskServiceBlockingStub>() {
        @Override
        public ExecuteTaskServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExecuteTaskServiceBlockingStub(channel, callOptions);
        }
      };
    return ExecuteTaskServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ExecuteTaskServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExecuteTaskServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExecuteTaskServiceFutureStub>() {
        @Override
        public ExecuteTaskServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExecuteTaskServiceFutureStub(channel, callOptions);
        }
      };
    return ExecuteTaskServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ExecuteTaskServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void execute(ExecuteCommandProto.ExecuteCommand request,
                        io.grpc.stub.StreamObserver<ExecuteCommandProto.CommandResult> responseObserver) {
      asyncUnimplementedUnaryCall(getExecuteMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getExecuteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                ExecuteCommandProto.ExecuteCommand,
                ExecuteCommandProto.CommandResult>(
                  this, METHODID_EXECUTE)))
          .build();
    }
  }

  /**
   */
  public static final class ExecuteTaskServiceStub extends io.grpc.stub.AbstractAsyncStub<ExecuteTaskServiceStub> {
    private ExecuteTaskServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ExecuteTaskServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExecuteTaskServiceStub(channel, callOptions);
    }

    /**
     */
    public void execute(ExecuteCommandProto.ExecuteCommand request,
                        io.grpc.stub.StreamObserver<ExecuteCommandProto.CommandResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getExecuteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ExecuteTaskServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ExecuteTaskServiceBlockingStub> {
    private ExecuteTaskServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ExecuteTaskServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExecuteTaskServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ExecuteCommandProto.CommandResult execute(ExecuteCommandProto.ExecuteCommand request) {
      return blockingUnaryCall(
          getChannel(), getExecuteMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ExecuteTaskServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ExecuteTaskServiceFutureStub> {
    private ExecuteTaskServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ExecuteTaskServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExecuteTaskServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ExecuteCommandProto.CommandResult> execute(
        ExecuteCommandProto.ExecuteCommand request) {
      return futureUnaryCall(
          getChannel().newCall(getExecuteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EXECUTE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ExecuteTaskServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ExecuteTaskServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXECUTE:
          serviceImpl.execute((ExecuteCommandProto.ExecuteCommand) request,
              (io.grpc.stub.StreamObserver<ExecuteCommandProto.CommandResult>) responseObserver);
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

  private static abstract class ExecuteTaskServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ExecuteTaskServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ExecuteCommandProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ExecuteTaskService");
    }
  }

  private static final class ExecuteTaskServiceFileDescriptorSupplier
      extends ExecuteTaskServiceBaseDescriptorSupplier {
    ExecuteTaskServiceFileDescriptorSupplier() {}
  }

  private static final class ExecuteTaskServiceMethodDescriptorSupplier
      extends ExecuteTaskServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ExecuteTaskServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ExecuteTaskServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ExecuteTaskServiceFileDescriptorSupplier())
              .addMethod(getExecuteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
