package org.lwjgl.opencl;

public final class CLCapabilities
{
  static final boolean CL_APPLE_ContextLoggingFunctions = isAPPLE_ContextLoggingFunctionsSupported();
  static final long clLogMessagesToSystemLogAPPLE = class_1435.getFunctionAddress("clLogMessagesToSystemLogAPPLE");
  static final long clLogMessagesToStdoutAPPLE = class_1435.getFunctionAddress("clLogMessagesToStdoutAPPLE");
  static final long clLogMessagesToStderrAPPLE = class_1435.getFunctionAddress("clLogMessagesToStderrAPPLE");
  static final boolean CL_APPLE_SetMemObjectDestructor = isAPPLE_SetMemObjectDestructorSupported();
  static final long clSetMemObjectDestructorAPPLE = class_1435.getFunctionAddress("clSetMemObjectDestructorAPPLE");
  static final boolean CL_APPLE_gl_sharing = isAPPLE_gl_sharingSupported();
  static final long clGetGLContextInfoAPPLE = class_1435.getFunctionAddress("clGetGLContextInfoAPPLE");
  static final boolean OpenCL10 = isCL10Supported();
  static final long clGetPlatformIDs = class_1435.getFunctionAddress("clGetPlatformIDs");
  static final long clGetPlatformInfo = class_1435.getFunctionAddress("clGetPlatformInfo");
  static final long clGetDeviceIDs = class_1435.getFunctionAddress("clGetDeviceIDs");
  static final long clGetDeviceInfo = class_1435.getFunctionAddress("clGetDeviceInfo");
  static final long clCreateContext = class_1435.getFunctionAddress("clCreateContext");
  static final long clCreateContextFromType = class_1435.getFunctionAddress("clCreateContextFromType");
  static final long clRetainContext = class_1435.getFunctionAddress("clRetainContext");
  static final long clReleaseContext = class_1435.getFunctionAddress("clReleaseContext");
  static final long clGetContextInfo = class_1435.getFunctionAddress("clGetContextInfo");
  static final long clCreateCommandQueue = class_1435.getFunctionAddress("clCreateCommandQueue");
  static final long clRetainCommandQueue = class_1435.getFunctionAddress("clRetainCommandQueue");
  static final long clReleaseCommandQueue = class_1435.getFunctionAddress("clReleaseCommandQueue");
  static final long clGetCommandQueueInfo = class_1435.getFunctionAddress("clGetCommandQueueInfo");
  static final long clCreateBuffer = class_1435.getFunctionAddress("clCreateBuffer");
  static final long clEnqueueReadBuffer = class_1435.getFunctionAddress("clEnqueueReadBuffer");
  static final long clEnqueueWriteBuffer = class_1435.getFunctionAddress("clEnqueueWriteBuffer");
  static final long clEnqueueCopyBuffer = class_1435.getFunctionAddress("clEnqueueCopyBuffer");
  static final long clEnqueueMapBuffer = class_1435.getFunctionAddress("clEnqueueMapBuffer");
  static final long clCreateImage2D = class_1435.getFunctionAddress("clCreateImage2D");
  static final long clCreateImage3D = class_1435.getFunctionAddress("clCreateImage3D");
  static final long clGetSupportedImageFormats = class_1435.getFunctionAddress("clGetSupportedImageFormats");
  static final long clEnqueueReadImage = class_1435.getFunctionAddress("clEnqueueReadImage");
  static final long clEnqueueWriteImage = class_1435.getFunctionAddress("clEnqueueWriteImage");
  static final long clEnqueueCopyImage = class_1435.getFunctionAddress("clEnqueueCopyImage");
  static final long clEnqueueCopyImageToBuffer = class_1435.getFunctionAddress("clEnqueueCopyImageToBuffer");
  static final long clEnqueueCopyBufferToImage = class_1435.getFunctionAddress("clEnqueueCopyBufferToImage");
  static final long clEnqueueMapImage = class_1435.getFunctionAddress("clEnqueueMapImage");
  static final long clGetImageInfo = class_1435.getFunctionAddress("clGetImageInfo");
  static final long clRetainMemObject = class_1435.getFunctionAddress("clRetainMemObject");
  static final long clReleaseMemObject = class_1435.getFunctionAddress("clReleaseMemObject");
  static final long clEnqueueUnmapMemObject = class_1435.getFunctionAddress("clEnqueueUnmapMemObject");
  static final long clGetMemObjectInfo = class_1435.getFunctionAddress("clGetMemObjectInfo");
  static final long clCreateSampler = class_1435.getFunctionAddress("clCreateSampler");
  static final long clRetainSampler = class_1435.getFunctionAddress("clRetainSampler");
  static final long clReleaseSampler = class_1435.getFunctionAddress("clReleaseSampler");
  static final long clGetSamplerInfo = class_1435.getFunctionAddress("clGetSamplerInfo");
  static final long clCreateProgramWithSource = class_1435.getFunctionAddress("clCreateProgramWithSource");
  static final long clCreateProgramWithBinary = class_1435.getFunctionAddress("clCreateProgramWithBinary");
  static final long clRetainProgram = class_1435.getFunctionAddress("clRetainProgram");
  static final long clReleaseProgram = class_1435.getFunctionAddress("clReleaseProgram");
  static final long clBuildProgram = class_1435.getFunctionAddress("clBuildProgram");
  static final long clUnloadCompiler = class_1435.getFunctionAddress("clUnloadCompiler");
  static final long clGetProgramInfo = class_1435.getFunctionAddress("clGetProgramInfo");
  static final long clGetProgramBuildInfo = class_1435.getFunctionAddress("clGetProgramBuildInfo");
  static final long clCreateKernel = class_1435.getFunctionAddress("clCreateKernel");
  static final long clCreateKernelsInProgram = class_1435.getFunctionAddress("clCreateKernelsInProgram");
  static final long clRetainKernel = class_1435.getFunctionAddress("clRetainKernel");
  static final long clReleaseKernel = class_1435.getFunctionAddress("clReleaseKernel");
  static final long clSetKernelArg = class_1435.getFunctionAddress("clSetKernelArg");
  static final long clGetKernelInfo = class_1435.getFunctionAddress("clGetKernelInfo");
  static final long clGetKernelWorkGroupInfo = class_1435.getFunctionAddress("clGetKernelWorkGroupInfo");
  static final long clEnqueueNDRangeKernel = class_1435.getFunctionAddress("clEnqueueNDRangeKernel");
  static final long clEnqueueTask = class_1435.getFunctionAddress("clEnqueueTask");
  static final long clEnqueueNativeKernel = class_1435.getFunctionAddress("clEnqueueNativeKernel");
  static final long clWaitForEvents = class_1435.getFunctionAddress("clWaitForEvents");
  static final long clGetEventInfo = class_1435.getFunctionAddress("clGetEventInfo");
  static final long clRetainEvent = class_1435.getFunctionAddress("clRetainEvent");
  static final long clReleaseEvent = class_1435.getFunctionAddress("clReleaseEvent");
  static final long clEnqueueMarker = class_1435.getFunctionAddress("clEnqueueMarker");
  static final long clEnqueueBarrier = class_1435.getFunctionAddress("clEnqueueBarrier");
  static final long clEnqueueWaitForEvents = class_1435.getFunctionAddress("clEnqueueWaitForEvents");
  static final long clGetEventProfilingInfo = class_1435.getFunctionAddress("clGetEventProfilingInfo");
  static final long clFlush = class_1435.getFunctionAddress("clFlush");
  static final long clFinish = class_1435.getFunctionAddress("clFinish");
  static final long clGetExtensionFunctionAddress = class_1435.getFunctionAddress("clGetExtensionFunctionAddress");
  static final boolean OpenCL10GL = isCL10GLSupported();
  static final long clCreateFromGLBuffer = class_1435.getFunctionAddress("clCreateFromGLBuffer");
  static final long clCreateFromGLTexture2D = class_1435.getFunctionAddress("clCreateFromGLTexture2D");
  static final long clCreateFromGLTexture3D = class_1435.getFunctionAddress("clCreateFromGLTexture3D");
  static final long clCreateFromGLRenderbuffer = class_1435.getFunctionAddress("clCreateFromGLRenderbuffer");
  static final long clGetGLObjectInfo = class_1435.getFunctionAddress("clGetGLObjectInfo");
  static final long clGetGLTextureInfo = class_1435.getFunctionAddress("clGetGLTextureInfo");
  static final long clEnqueueAcquireGLObjects = class_1435.getFunctionAddress("clEnqueueAcquireGLObjects");
  static final long clEnqueueReleaseGLObjects = class_1435.getFunctionAddress("clEnqueueReleaseGLObjects");
  static final boolean OpenCL11 = isCL11Supported();
  static final long clCreateSubBuffer = class_1435.getFunctionAddress("clCreateSubBuffer");
  static final long clSetMemObjectDestructorCallback = class_1435.getFunctionAddress("clSetMemObjectDestructorCallback");
  static final long clEnqueueReadBufferRect = class_1435.getFunctionAddress("clEnqueueReadBufferRect");
  static final long clEnqueueWriteBufferRect = class_1435.getFunctionAddress("clEnqueueWriteBufferRect");
  static final long clEnqueueCopyBufferRect = class_1435.getFunctionAddress("clEnqueueCopyBufferRect");
  static final long clCreateUserEvent = class_1435.getFunctionAddress("clCreateUserEvent");
  static final long clSetUserEventStatus = class_1435.getFunctionAddress("clSetUserEventStatus");
  static final long clSetEventCallback = class_1435.getFunctionAddress("clSetEventCallback");
  static final boolean OpenCL12 = isCL12Supported();
  static final long clRetainDevice = class_1435.getFunctionAddress("clRetainDevice");
  static final long clReleaseDevice = class_1435.getFunctionAddress("clReleaseDevice");
  static final long clCreateSubDevices = class_1435.getFunctionAddress("clCreateSubDevices");
  static final long clCreateImage = class_1435.getFunctionAddress("clCreateImage");
  static final long clCreateProgramWithBuiltInKernels = class_1435.getFunctionAddress("clCreateProgramWithBuiltInKernels");
  static final long clCompileProgram = class_1435.getFunctionAddress("clCompileProgram");
  static final long clLinkProgram = class_1435.getFunctionAddress("clLinkProgram");
  static final long clUnloadPlatformCompiler = class_1435.getFunctionAddress("clUnloadPlatformCompiler");
  static final long clGetKernelArgInfo = class_1435.getFunctionAddress("clGetKernelArgInfo");
  static final long clEnqueueFillBuffer = class_1435.getFunctionAddress("clEnqueueFillBuffer");
  static final long clEnqueueFillImage = class_1435.getFunctionAddress("clEnqueueFillImage");
  static final long clEnqueueMigrateMemObjects = class_1435.getFunctionAddress("clEnqueueMigrateMemObjects");
  static final long clEnqueueMarkerWithWaitList = class_1435.getFunctionAddress("clEnqueueMarkerWithWaitList");
  static final long clEnqueueBarrierWithWaitList = class_1435.getFunctionAddress("clEnqueueBarrierWithWaitList");
  static final long clSetPrintfCallback = class_1435.getFunctionAddress("clSetPrintfCallback");
  static final long clGetExtensionFunctionAddressForPlatform = class_1435.getFunctionAddress("clGetExtensionFunctionAddressForPlatform");
  static final boolean OpenCL12GL = isCL12GLSupported();
  static final long clCreateFromGLTexture = class_1435.getFunctionAddress("clCreateFromGLTexture");
  static final boolean CL_EXT_device_fission = isEXT_device_fissionSupported();
  static final long clRetainDeviceEXT = class_1435.getFunctionAddress("clRetainDeviceEXT");
  static final long clReleaseDeviceEXT = class_1435.getFunctionAddress("clReleaseDeviceEXT");
  static final long clCreateSubDevicesEXT = class_1435.getFunctionAddress("clCreateSubDevicesEXT");
  static final boolean CL_EXT_migrate_memobject = isEXT_migrate_memobjectSupported();
  static final long clEnqueueMigrateMemObjectEXT = class_1435.getFunctionAddress("clEnqueueMigrateMemObjectEXT");
  static final boolean CL_KHR_gl_event = isKHR_gl_eventSupported();
  static final long clCreateEventFromGLsyncKHR = class_1435.getFunctionAddress("clCreateEventFromGLsyncKHR");
  static final boolean CL_KHR_gl_sharing = isKHR_gl_sharingSupported();
  static final long clGetGLContextInfoKHR = class_1435.getFunctionAddress("clGetGLContextInfoKHR");
  static final boolean CL_KHR_icd = isKHR_icdSupported();
  static final long clIcdGetPlatformIDsKHR = class_1435.getFunctionAddress("clIcdGetPlatformIDsKHR");
  static final boolean CL_KHR_terminate_context = isKHR_terminate_contextSupported();
  static final long clTerminateContextKHR = class_1435.getFunctionAddress("clTerminateContextKHR");
  
  public static CLPlatformCapabilities getPlatformCapabilities(CLPlatform platform)
  {
    platform.checkValid();
    CLPlatformCapabilities caps = (CLPlatformCapabilities)platform.getCapabilities();
    if (caps == null) {
      platform.setCapabilities(caps = new CLPlatformCapabilities(platform));
    }
    return caps;
  }
  
  public static CLDeviceCapabilities getDeviceCapabilities(CLDevice device)
  {
    device.checkValid();
    CLDeviceCapabilities caps = (CLDeviceCapabilities)device.getCapabilities();
    if (caps == null) {
      device.setCapabilities(caps = new CLDeviceCapabilities(device));
    }
    return caps;
  }
  
  private static boolean isAPPLE_ContextLoggingFunctionsSupported()
  {
    return (clLogMessagesToSystemLogAPPLE != 0L ? 1 : 0) & (clLogMessagesToStdoutAPPLE != 0L ? 1 : 0) & (clLogMessagesToStderrAPPLE != 0L ? 1 : 0);
  }
  
  private static boolean isAPPLE_SetMemObjectDestructorSupported()
  {
    return clSetMemObjectDestructorAPPLE != 0L;
  }
  
  private static boolean isAPPLE_gl_sharingSupported()
  {
    return clGetGLContextInfoAPPLE != 0L;
  }
  
  private static boolean isCL10Supported()
  {
    return (clGetPlatformIDs != 0L ? 1 : 0) & (clGetPlatformInfo != 0L ? 1 : 0) & (clGetDeviceIDs != 0L ? 1 : 0) & (clGetDeviceInfo != 0L ? 1 : 0) & (clCreateContext != 0L ? 1 : 0) & (clCreateContextFromType != 0L ? 1 : 0) & (clRetainContext != 0L ? 1 : 0) & (clReleaseContext != 0L ? 1 : 0) & (clGetContextInfo != 0L ? 1 : 0) & (clCreateCommandQueue != 0L ? 1 : 0) & (clRetainCommandQueue != 0L ? 1 : 0) & (clReleaseCommandQueue != 0L ? 1 : 0) & (clGetCommandQueueInfo != 0L ? 1 : 0) & (clCreateBuffer != 0L ? 1 : 0) & (clEnqueueReadBuffer != 0L ? 1 : 0) & (clEnqueueWriteBuffer != 0L ? 1 : 0) & (clEnqueueCopyBuffer != 0L ? 1 : 0) & (clEnqueueMapBuffer != 0L ? 1 : 0) & (clCreateImage2D != 0L ? 1 : 0) & (clCreateImage3D != 0L ? 1 : 0) & (clGetSupportedImageFormats != 0L ? 1 : 0) & (clEnqueueReadImage != 0L ? 1 : 0) & (clEnqueueWriteImage != 0L ? 1 : 0) & (clEnqueueCopyImage != 0L ? 1 : 0) & (clEnqueueCopyImageToBuffer != 0L ? 1 : 0) & (clEnqueueCopyBufferToImage != 0L ? 1 : 0) & (clEnqueueMapImage != 0L ? 1 : 0) & (clGetImageInfo != 0L ? 1 : 0) & (clRetainMemObject != 0L ? 1 : 0) & (clReleaseMemObject != 0L ? 1 : 0) & (clEnqueueUnmapMemObject != 0L ? 1 : 0) & (clGetMemObjectInfo != 0L ? 1 : 0) & (clCreateSampler != 0L ? 1 : 0) & (clRetainSampler != 0L ? 1 : 0) & (clReleaseSampler != 0L ? 1 : 0) & (clGetSamplerInfo != 0L ? 1 : 0) & (clCreateProgramWithSource != 0L ? 1 : 0) & (clCreateProgramWithBinary != 0L ? 1 : 0) & (clRetainProgram != 0L ? 1 : 0) & (clReleaseProgram != 0L ? 1 : 0) & (clBuildProgram != 0L ? 1 : 0) & (clUnloadCompiler != 0L ? 1 : 0) & (clGetProgramInfo != 0L ? 1 : 0) & (clGetProgramBuildInfo != 0L ? 1 : 0) & (clCreateKernel != 0L ? 1 : 0) & (clCreateKernelsInProgram != 0L ? 1 : 0) & (clRetainKernel != 0L ? 1 : 0) & (clReleaseKernel != 0L ? 1 : 0) & (clSetKernelArg != 0L ? 1 : 0) & (clGetKernelInfo != 0L ? 1 : 0) & (clGetKernelWorkGroupInfo != 0L ? 1 : 0) & (clEnqueueNDRangeKernel != 0L ? 1 : 0) & (clEnqueueTask != 0L ? 1 : 0) & (clEnqueueNativeKernel != 0L ? 1 : 0) & (clWaitForEvents != 0L ? 1 : 0) & (clGetEventInfo != 0L ? 1 : 0) & (clRetainEvent != 0L ? 1 : 0) & (clReleaseEvent != 0L ? 1 : 0) & (clEnqueueMarker != 0L ? 1 : 0) & (clEnqueueBarrier != 0L ? 1 : 0) & (clEnqueueWaitForEvents != 0L ? 1 : 0) & (clGetEventProfilingInfo != 0L ? 1 : 0) & (clFlush != 0L ? 1 : 0) & (clFinish != 0L ? 1 : 0) & (clGetExtensionFunctionAddress != 0L ? 1 : 0);
  }
  
  private static boolean isCL10GLSupported()
  {
    return (clCreateFromGLBuffer != 0L ? 1 : 0) & (clCreateFromGLTexture2D != 0L ? 1 : 0) & (clCreateFromGLTexture3D != 0L ? 1 : 0) & (clCreateFromGLRenderbuffer != 0L ? 1 : 0) & (clGetGLObjectInfo != 0L ? 1 : 0) & (clGetGLTextureInfo != 0L ? 1 : 0) & (clEnqueueAcquireGLObjects != 0L ? 1 : 0) & (clEnqueueReleaseGLObjects != 0L ? 1 : 0);
  }
  
  private static boolean isCL11Supported()
  {
    return (clCreateSubBuffer != 0L ? 1 : 0) & (clSetMemObjectDestructorCallback != 0L ? 1 : 0) & (clEnqueueReadBufferRect != 0L ? 1 : 0) & (clEnqueueWriteBufferRect != 0L ? 1 : 0) & (clEnqueueCopyBufferRect != 0L ? 1 : 0) & (clCreateUserEvent != 0L ? 1 : 0) & (clSetUserEventStatus != 0L ? 1 : 0) & (clSetEventCallback != 0L ? 1 : 0);
  }
  
  private static boolean isCL12Supported()
  {
    return (clRetainDevice != 0L ? 1 : 0) & (clReleaseDevice != 0L ? 1 : 0) & (clCreateSubDevices != 0L ? 1 : 0) & (clCreateImage != 0L ? 1 : 0) & (clCreateProgramWithBuiltInKernels != 0L ? 1 : 0) & (clCompileProgram != 0L ? 1 : 0) & (clLinkProgram != 0L ? 1 : 0) & (clUnloadPlatformCompiler != 0L ? 1 : 0) & (clGetKernelArgInfo != 0L ? 1 : 0) & (clEnqueueFillBuffer != 0L ? 1 : 0) & (clEnqueueFillImage != 0L ? 1 : 0) & (clEnqueueMigrateMemObjects != 0L ? 1 : 0) & (clEnqueueMarkerWithWaitList != 0L ? 1 : 0) & (clEnqueueBarrierWithWaitList != 0L ? 1 : 0) & 0x1 & ((clSetPrintfCallback != 0L) || (clGetExtensionFunctionAddressForPlatform != 0L) ? 1 : 0);
  }
  
  private static boolean isCL12GLSupported()
  {
    return clCreateFromGLTexture != 0L;
  }
  
  private static boolean isEXT_device_fissionSupported()
  {
    return (clRetainDeviceEXT != 0L ? 1 : 0) & (clReleaseDeviceEXT != 0L ? 1 : 0) & (clCreateSubDevicesEXT != 0L ? 1 : 0);
  }
  
  private static boolean isEXT_migrate_memobjectSupported()
  {
    return clEnqueueMigrateMemObjectEXT != 0L;
  }
  
  private static boolean isKHR_gl_eventSupported()
  {
    return clCreateEventFromGLsyncKHR != 0L;
  }
  
  private static boolean isKHR_gl_sharingSupported()
  {
    return clGetGLContextInfoKHR != 0L;
  }
  
  private static boolean isKHR_icdSupported()
  {
    if (clIcdGetPlatformIDsKHR == 0L) {}
    return true;
  }
  
  private static boolean isKHR_terminate_contextSupported()
  {
    return clTerminateContextKHR != 0L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.CLCapabilities
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */