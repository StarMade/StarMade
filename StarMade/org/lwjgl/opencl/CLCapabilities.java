/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   6:    */public final class CLCapabilities
/*   7:    */{
/*   8:  8 */  static final long clLogMessagesToSystemLogAPPLE = CL.getFunctionAddress("clLogMessagesToSystemLogAPPLE");
/*   9:  9 */  static final long clLogMessagesToStdoutAPPLE = CL.getFunctionAddress("clLogMessagesToStdoutAPPLE");
/*  10: 10 */  static final long clLogMessagesToStderrAPPLE = CL.getFunctionAddress("clLogMessagesToStderrAPPLE");
/*  11:    */  
/*  13: 13 */  static final long clSetMemObjectDestructorAPPLE = CL.getFunctionAddress("clSetMemObjectDestructorAPPLE");
/*  14:    */  
/*  16: 16 */  static final long clGetGLContextInfoAPPLE = CL.getFunctionAddress("clGetGLContextInfoAPPLE");
/*  17:    */  
/*  19: 19 */  static final long clGetPlatformIDs = CL.getFunctionAddress("clGetPlatformIDs");
/*  20: 20 */  static final long clGetPlatformInfo = CL.getFunctionAddress("clGetPlatformInfo");
/*  21: 21 */  static final long clGetDeviceIDs = CL.getFunctionAddress("clGetDeviceIDs");
/*  22: 22 */  static final long clGetDeviceInfo = CL.getFunctionAddress("clGetDeviceInfo");
/*  23: 23 */  static final long clCreateContext = CL.getFunctionAddress("clCreateContext");
/*  24: 24 */  static final long clCreateContextFromType = CL.getFunctionAddress("clCreateContextFromType");
/*  25: 25 */  static final long clRetainContext = CL.getFunctionAddress("clRetainContext");
/*  26: 26 */  static final long clReleaseContext = CL.getFunctionAddress("clReleaseContext");
/*  27: 27 */  static final long clGetContextInfo = CL.getFunctionAddress("clGetContextInfo");
/*  28: 28 */  static final long clCreateCommandQueue = CL.getFunctionAddress("clCreateCommandQueue");
/*  29: 29 */  static final long clRetainCommandQueue = CL.getFunctionAddress("clRetainCommandQueue");
/*  30: 30 */  static final long clReleaseCommandQueue = CL.getFunctionAddress("clReleaseCommandQueue");
/*  31: 31 */  static final long clGetCommandQueueInfo = CL.getFunctionAddress("clGetCommandQueueInfo");
/*  32: 32 */  static final long clCreateBuffer = CL.getFunctionAddress("clCreateBuffer");
/*  33: 33 */  static final long clEnqueueReadBuffer = CL.getFunctionAddress("clEnqueueReadBuffer");
/*  34: 34 */  static final long clEnqueueWriteBuffer = CL.getFunctionAddress("clEnqueueWriteBuffer");
/*  35: 35 */  static final long clEnqueueCopyBuffer = CL.getFunctionAddress("clEnqueueCopyBuffer");
/*  36: 36 */  static final long clEnqueueMapBuffer = CL.getFunctionAddress("clEnqueueMapBuffer");
/*  37: 37 */  static final long clCreateImage2D = CL.getFunctionAddress("clCreateImage2D");
/*  38: 38 */  static final long clCreateImage3D = CL.getFunctionAddress("clCreateImage3D");
/*  39: 39 */  static final long clGetSupportedImageFormats = CL.getFunctionAddress("clGetSupportedImageFormats");
/*  40: 40 */  static final long clEnqueueReadImage = CL.getFunctionAddress("clEnqueueReadImage");
/*  41: 41 */  static final long clEnqueueWriteImage = CL.getFunctionAddress("clEnqueueWriteImage");
/*  42: 42 */  static final long clEnqueueCopyImage = CL.getFunctionAddress("clEnqueueCopyImage");
/*  43: 43 */  static final long clEnqueueCopyImageToBuffer = CL.getFunctionAddress("clEnqueueCopyImageToBuffer");
/*  44: 44 */  static final long clEnqueueCopyBufferToImage = CL.getFunctionAddress("clEnqueueCopyBufferToImage");
/*  45: 45 */  static final long clEnqueueMapImage = CL.getFunctionAddress("clEnqueueMapImage");
/*  46: 46 */  static final long clGetImageInfo = CL.getFunctionAddress("clGetImageInfo");
/*  47: 47 */  static final long clRetainMemObject = CL.getFunctionAddress("clRetainMemObject");
/*  48: 48 */  static final long clReleaseMemObject = CL.getFunctionAddress("clReleaseMemObject");
/*  49: 49 */  static final long clEnqueueUnmapMemObject = CL.getFunctionAddress("clEnqueueUnmapMemObject");
/*  50: 50 */  static final long clGetMemObjectInfo = CL.getFunctionAddress("clGetMemObjectInfo");
/*  51: 51 */  static final long clCreateSampler = CL.getFunctionAddress("clCreateSampler");
/*  52: 52 */  static final long clRetainSampler = CL.getFunctionAddress("clRetainSampler");
/*  53: 53 */  static final long clReleaseSampler = CL.getFunctionAddress("clReleaseSampler");
/*  54: 54 */  static final long clGetSamplerInfo = CL.getFunctionAddress("clGetSamplerInfo");
/*  55: 55 */  static final long clCreateProgramWithSource = CL.getFunctionAddress("clCreateProgramWithSource");
/*  56: 56 */  static final long clCreateProgramWithBinary = CL.getFunctionAddress("clCreateProgramWithBinary");
/*  57: 57 */  static final long clRetainProgram = CL.getFunctionAddress("clRetainProgram");
/*  58: 58 */  static final long clReleaseProgram = CL.getFunctionAddress("clReleaseProgram");
/*  59: 59 */  static final long clBuildProgram = CL.getFunctionAddress("clBuildProgram");
/*  60: 60 */  static final long clUnloadCompiler = CL.getFunctionAddress("clUnloadCompiler");
/*  61: 61 */  static final long clGetProgramInfo = CL.getFunctionAddress("clGetProgramInfo");
/*  62: 62 */  static final long clGetProgramBuildInfo = CL.getFunctionAddress("clGetProgramBuildInfo");
/*  63: 63 */  static final long clCreateKernel = CL.getFunctionAddress("clCreateKernel");
/*  64: 64 */  static final long clCreateKernelsInProgram = CL.getFunctionAddress("clCreateKernelsInProgram");
/*  65: 65 */  static final long clRetainKernel = CL.getFunctionAddress("clRetainKernel");
/*  66: 66 */  static final long clReleaseKernel = CL.getFunctionAddress("clReleaseKernel");
/*  67: 67 */  static final long clSetKernelArg = CL.getFunctionAddress("clSetKernelArg");
/*  68: 68 */  static final long clGetKernelInfo = CL.getFunctionAddress("clGetKernelInfo");
/*  69: 69 */  static final long clGetKernelWorkGroupInfo = CL.getFunctionAddress("clGetKernelWorkGroupInfo");
/*  70: 70 */  static final long clEnqueueNDRangeKernel = CL.getFunctionAddress("clEnqueueNDRangeKernel");
/*  71: 71 */  static final long clEnqueueTask = CL.getFunctionAddress("clEnqueueTask");
/*  72: 72 */  static final long clEnqueueNativeKernel = CL.getFunctionAddress("clEnqueueNativeKernel");
/*  73: 73 */  static final long clWaitForEvents = CL.getFunctionAddress("clWaitForEvents");
/*  74: 74 */  static final long clGetEventInfo = CL.getFunctionAddress("clGetEventInfo");
/*  75: 75 */  static final long clRetainEvent = CL.getFunctionAddress("clRetainEvent");
/*  76: 76 */  static final long clReleaseEvent = CL.getFunctionAddress("clReleaseEvent");
/*  77: 77 */  static final long clEnqueueMarker = CL.getFunctionAddress("clEnqueueMarker");
/*  78: 78 */  static final long clEnqueueBarrier = CL.getFunctionAddress("clEnqueueBarrier");
/*  79: 79 */  static final long clEnqueueWaitForEvents = CL.getFunctionAddress("clEnqueueWaitForEvents");
/*  80: 80 */  static final long clGetEventProfilingInfo = CL.getFunctionAddress("clGetEventProfilingInfo");
/*  81: 81 */  static final long clFlush = CL.getFunctionAddress("clFlush");
/*  82: 82 */  static final long clFinish = CL.getFunctionAddress("clFinish");
/*  83: 83 */  static final long clGetExtensionFunctionAddress = CL.getFunctionAddress("clGetExtensionFunctionAddress");
/*  84:    */  
/*  86: 86 */  static final long clCreateFromGLBuffer = CL.getFunctionAddress("clCreateFromGLBuffer");
/*  87: 87 */  static final long clCreateFromGLTexture2D = CL.getFunctionAddress("clCreateFromGLTexture2D");
/*  88: 88 */  static final long clCreateFromGLTexture3D = CL.getFunctionAddress("clCreateFromGLTexture3D");
/*  89: 89 */  static final long clCreateFromGLRenderbuffer = CL.getFunctionAddress("clCreateFromGLRenderbuffer");
/*  90: 90 */  static final long clGetGLObjectInfo = CL.getFunctionAddress("clGetGLObjectInfo");
/*  91: 91 */  static final long clGetGLTextureInfo = CL.getFunctionAddress("clGetGLTextureInfo");
/*  92: 92 */  static final long clEnqueueAcquireGLObjects = CL.getFunctionAddress("clEnqueueAcquireGLObjects");
/*  93: 93 */  static final long clEnqueueReleaseGLObjects = CL.getFunctionAddress("clEnqueueReleaseGLObjects");
/*  94:    */  
/*  96: 96 */  static final long clCreateSubBuffer = CL.getFunctionAddress("clCreateSubBuffer");
/*  97: 97 */  static final long clSetMemObjectDestructorCallback = CL.getFunctionAddress("clSetMemObjectDestructorCallback");
/*  98: 98 */  static final long clEnqueueReadBufferRect = CL.getFunctionAddress("clEnqueueReadBufferRect");
/*  99: 99 */  static final long clEnqueueWriteBufferRect = CL.getFunctionAddress("clEnqueueWriteBufferRect");
/* 100:100 */  static final long clEnqueueCopyBufferRect = CL.getFunctionAddress("clEnqueueCopyBufferRect");
/* 101:101 */  static final long clCreateUserEvent = CL.getFunctionAddress("clCreateUserEvent");
/* 102:102 */  static final long clSetUserEventStatus = CL.getFunctionAddress("clSetUserEventStatus");
/* 103:103 */  static final long clSetEventCallback = CL.getFunctionAddress("clSetEventCallback");
/* 104:    */  
/* 106:106 */  static final long clRetainDevice = CL.getFunctionAddress("clRetainDevice");
/* 107:107 */  static final long clReleaseDevice = CL.getFunctionAddress("clReleaseDevice");
/* 108:108 */  static final long clCreateSubDevices = CL.getFunctionAddress("clCreateSubDevices");
/* 109:109 */  static final long clCreateImage = CL.getFunctionAddress("clCreateImage");
/* 110:110 */  static final long clCreateProgramWithBuiltInKernels = CL.getFunctionAddress("clCreateProgramWithBuiltInKernels");
/* 111:111 */  static final long clCompileProgram = CL.getFunctionAddress("clCompileProgram");
/* 112:112 */  static final long clLinkProgram = CL.getFunctionAddress("clLinkProgram");
/* 113:113 */  static final long clUnloadPlatformCompiler = CL.getFunctionAddress("clUnloadPlatformCompiler");
/* 114:114 */  static final long clGetKernelArgInfo = CL.getFunctionAddress("clGetKernelArgInfo");
/* 115:115 */  static final long clEnqueueFillBuffer = CL.getFunctionAddress("clEnqueueFillBuffer");
/* 116:116 */  static final long clEnqueueFillImage = CL.getFunctionAddress("clEnqueueFillImage");
/* 117:117 */  static final long clEnqueueMigrateMemObjects = CL.getFunctionAddress("clEnqueueMigrateMemObjects");
/* 118:118 */  static final long clEnqueueMarkerWithWaitList = CL.getFunctionAddress("clEnqueueMarkerWithWaitList");
/* 119:119 */  static final long clEnqueueBarrierWithWaitList = CL.getFunctionAddress("clEnqueueBarrierWithWaitList");
/* 120:120 */  static final long clSetPrintfCallback = CL.getFunctionAddress("clSetPrintfCallback");
/* 121:121 */  static final long clGetExtensionFunctionAddressForPlatform = CL.getFunctionAddress("clGetExtensionFunctionAddressForPlatform");
/* 122:    */  
/* 124:124 */  static final long clCreateFromGLTexture = CL.getFunctionAddress("clCreateFromGLTexture");
/* 125:    */  
/* 127:127 */  static final long clRetainDeviceEXT = CL.getFunctionAddress("clRetainDeviceEXT");
/* 128:128 */  static final long clReleaseDeviceEXT = CL.getFunctionAddress("clReleaseDeviceEXT");
/* 129:129 */  static final long clCreateSubDevicesEXT = CL.getFunctionAddress("clCreateSubDevicesEXT");
/* 130:    */  
/* 132:132 */  static final long clEnqueueMigrateMemObjectEXT = CL.getFunctionAddress("clEnqueueMigrateMemObjectEXT");
/* 133:    */  
/* 135:135 */  static final long clCreateEventFromGLsyncKHR = CL.getFunctionAddress("clCreateEventFromGLsyncKHR");
/* 136:    */  
/* 138:138 */  static final long clGetGLContextInfoKHR = CL.getFunctionAddress("clGetGLContextInfoKHR");
/* 139:    */  
/* 141:141 */  static final long clIcdGetPlatformIDsKHR = CL.getFunctionAddress("clIcdGetPlatformIDsKHR");
/* 142:    */  
/* 144:144 */  static final long clTerminateContextKHR = CL.getFunctionAddress("clTerminateContextKHR");
/* 145:    */  
/* 150:150 */  static final boolean CL_APPLE_ContextLoggingFunctions = isAPPLE_ContextLoggingFunctionsSupported();
/* 151:151 */  static final boolean CL_APPLE_SetMemObjectDestructor = isAPPLE_SetMemObjectDestructorSupported();
/* 152:152 */  static final boolean CL_APPLE_gl_sharing = isAPPLE_gl_sharingSupported();
/* 153:153 */  static final boolean OpenCL10 = isCL10Supported();
/* 154:154 */  static final boolean OpenCL10GL = isCL10GLSupported();
/* 155:155 */  static final boolean OpenCL11 = isCL11Supported();
/* 156:156 */  static final boolean OpenCL12 = isCL12Supported();
/* 157:157 */  static final boolean OpenCL12GL = isCL12GLSupported();
/* 158:158 */  static final boolean CL_EXT_device_fission = isEXT_device_fissionSupported();
/* 159:159 */  static final boolean CL_EXT_migrate_memobject = isEXT_migrate_memobjectSupported();
/* 160:160 */  static final boolean CL_KHR_gl_event = isKHR_gl_eventSupported();
/* 161:161 */  static final boolean CL_KHR_gl_sharing = isKHR_gl_sharingSupported();
/* 162:162 */  static final boolean CL_KHR_icd = isKHR_icdSupported();
/* 163:163 */  static final boolean CL_KHR_terminate_context = isKHR_terminate_contextSupported();
/* 164:    */  
/* 165:    */  public static CLPlatformCapabilities getPlatformCapabilities(CLPlatform platform)
/* 166:    */  {
/* 167:167 */    platform.checkValid();
/* 168:    */    
/* 169:169 */    CLPlatformCapabilities caps = (CLPlatformCapabilities)platform.getCapabilities();
/* 170:170 */    if (caps == null) {
/* 171:171 */      platform.setCapabilities(caps = new CLPlatformCapabilities(platform));
/* 172:    */    }
/* 173:173 */    return caps;
/* 174:    */  }
/* 175:    */  
/* 176:    */  public static CLDeviceCapabilities getDeviceCapabilities(CLDevice device) {
/* 177:177 */    device.checkValid();
/* 178:    */    
/* 179:179 */    CLDeviceCapabilities caps = (CLDeviceCapabilities)device.getCapabilities();
/* 180:180 */    if (caps == null) {
/* 181:181 */      device.setCapabilities(caps = new CLDeviceCapabilities(device));
/* 182:    */    }
/* 183:183 */    return caps;
/* 184:    */  }
/* 185:    */  
/* 186:    */  private static boolean isAPPLE_ContextLoggingFunctionsSupported() {
/* 187:187 */    return (clLogMessagesToSystemLogAPPLE != 0L ? 1 : 0) & (clLogMessagesToStdoutAPPLE != 0L ? 1 : 0) & (clLogMessagesToStderrAPPLE != 0L ? 1 : 0);
/* 188:    */  }
/* 189:    */  
/* 192:    */  private static boolean isAPPLE_SetMemObjectDestructorSupported()
/* 193:    */  {
/* 194:194 */    return clSetMemObjectDestructorAPPLE != 0L;
/* 195:    */  }
/* 196:    */  
/* 197:    */  private static boolean isAPPLE_gl_sharingSupported()
/* 198:    */  {
/* 199:199 */    return clGetGLContextInfoAPPLE != 0L;
/* 200:    */  }
/* 201:    */  
/* 202:    */  private static boolean isCL10Supported()
/* 203:    */  {
/* 204:204 */    return (clGetPlatformIDs != 0L ? 1 : 0) & (clGetPlatformInfo != 0L ? 1 : 0) & (clGetDeviceIDs != 0L ? 1 : 0) & (clGetDeviceInfo != 0L ? 1 : 0) & (clCreateContext != 0L ? 1 : 0) & (clCreateContextFromType != 0L ? 1 : 0) & (clRetainContext != 0L ? 1 : 0) & (clReleaseContext != 0L ? 1 : 0) & (clGetContextInfo != 0L ? 1 : 0) & (clCreateCommandQueue != 0L ? 1 : 0) & (clRetainCommandQueue != 0L ? 1 : 0) & (clReleaseCommandQueue != 0L ? 1 : 0) & (clGetCommandQueueInfo != 0L ? 1 : 0) & (clCreateBuffer != 0L ? 1 : 0) & (clEnqueueReadBuffer != 0L ? 1 : 0) & (clEnqueueWriteBuffer != 0L ? 1 : 0) & (clEnqueueCopyBuffer != 0L ? 1 : 0) & (clEnqueueMapBuffer != 0L ? 1 : 0) & (clCreateImage2D != 0L ? 1 : 0) & (clCreateImage3D != 0L ? 1 : 0) & (clGetSupportedImageFormats != 0L ? 1 : 0) & (clEnqueueReadImage != 0L ? 1 : 0) & (clEnqueueWriteImage != 0L ? 1 : 0) & (clEnqueueCopyImage != 0L ? 1 : 0) & (clEnqueueCopyImageToBuffer != 0L ? 1 : 0) & (clEnqueueCopyBufferToImage != 0L ? 1 : 0) & (clEnqueueMapImage != 0L ? 1 : 0) & (clGetImageInfo != 0L ? 1 : 0) & (clRetainMemObject != 0L ? 1 : 0) & (clReleaseMemObject != 0L ? 1 : 0) & (clEnqueueUnmapMemObject != 0L ? 1 : 0) & (clGetMemObjectInfo != 0L ? 1 : 0) & (clCreateSampler != 0L ? 1 : 0) & (clRetainSampler != 0L ? 1 : 0) & (clReleaseSampler != 0L ? 1 : 0) & (clGetSamplerInfo != 0L ? 1 : 0) & (clCreateProgramWithSource != 0L ? 1 : 0) & (clCreateProgramWithBinary != 0L ? 1 : 0) & (clRetainProgram != 0L ? 1 : 0) & (clReleaseProgram != 0L ? 1 : 0) & (clBuildProgram != 0L ? 1 : 0) & (clUnloadCompiler != 0L ? 1 : 0) & (clGetProgramInfo != 0L ? 1 : 0) & (clGetProgramBuildInfo != 0L ? 1 : 0) & (clCreateKernel != 0L ? 1 : 0) & (clCreateKernelsInProgram != 0L ? 1 : 0) & (clRetainKernel != 0L ? 1 : 0) & (clReleaseKernel != 0L ? 1 : 0) & (clSetKernelArg != 0L ? 1 : 0) & (clGetKernelInfo != 0L ? 1 : 0) & (clGetKernelWorkGroupInfo != 0L ? 1 : 0) & (clEnqueueNDRangeKernel != 0L ? 1 : 0) & (clEnqueueTask != 0L ? 1 : 0) & (clEnqueueNativeKernel != 0L ? 1 : 0) & (clWaitForEvents != 0L ? 1 : 0) & (clGetEventInfo != 0L ? 1 : 0) & (clRetainEvent != 0L ? 1 : 0) & (clReleaseEvent != 0L ? 1 : 0) & (clEnqueueMarker != 0L ? 1 : 0) & (clEnqueueBarrier != 0L ? 1 : 0) & (clEnqueueWaitForEvents != 0L ? 1 : 0) & (clGetEventProfilingInfo != 0L ? 1 : 0) & (clFlush != 0L ? 1 : 0) & (clFinish != 0L ? 1 : 0) & (clGetExtensionFunctionAddress != 0L ? 1 : 0);
/* 205:    */  }
/* 206:    */  
/* 271:    */  private static boolean isCL10GLSupported()
/* 272:    */  {
/* 273:273 */    return (clCreateFromGLBuffer != 0L ? 1 : 0) & (clCreateFromGLTexture2D != 0L ? 1 : 0) & (clCreateFromGLTexture3D != 0L ? 1 : 0) & (clCreateFromGLRenderbuffer != 0L ? 1 : 0) & (clGetGLObjectInfo != 0L ? 1 : 0) & (clGetGLTextureInfo != 0L ? 1 : 0) & (clEnqueueAcquireGLObjects != 0L ? 1 : 0) & (clEnqueueReleaseGLObjects != 0L ? 1 : 0);
/* 274:    */  }
/* 275:    */  
/* 283:    */  private static boolean isCL11Supported()
/* 284:    */  {
/* 285:285 */    return (clCreateSubBuffer != 0L ? 1 : 0) & (clSetMemObjectDestructorCallback != 0L ? 1 : 0) & (clEnqueueReadBufferRect != 0L ? 1 : 0) & (clEnqueueWriteBufferRect != 0L ? 1 : 0) & (clEnqueueCopyBufferRect != 0L ? 1 : 0) & (clCreateUserEvent != 0L ? 1 : 0) & (clSetUserEventStatus != 0L ? 1 : 0) & (clSetEventCallback != 0L ? 1 : 0);
/* 286:    */  }
/* 287:    */  
/* 295:    */  private static boolean isCL12Supported()
/* 296:    */  {
/* 297:297 */    return (clRetainDevice != 0L ? 1 : 0) & (clReleaseDevice != 0L ? 1 : 0) & (clCreateSubDevices != 0L ? 1 : 0) & (clCreateImage != 0L ? 1 : 0) & (clCreateProgramWithBuiltInKernels != 0L ? 1 : 0) & (clCompileProgram != 0L ? 1 : 0) & (clLinkProgram != 0L ? 1 : 0) & (clUnloadPlatformCompiler != 0L ? 1 : 0) & (clGetKernelArgInfo != 0L ? 1 : 0) & (clEnqueueFillBuffer != 0L ? 1 : 0) & (clEnqueueFillImage != 0L ? 1 : 0) & (clEnqueueMigrateMemObjects != 0L ? 1 : 0) & (clEnqueueMarkerWithWaitList != 0L ? 1 : 0) & (clEnqueueBarrierWithWaitList != 0L ? 1 : 0) & 0x1 & ((clSetPrintfCallback != 0L) || (clGetExtensionFunctionAddressForPlatform != 0L) ? 1 : 0);
/* 298:    */  }
/* 299:    */  
/* 315:    */  private static boolean isCL12GLSupported()
/* 316:    */  {
/* 317:317 */    return clCreateFromGLTexture != 0L;
/* 318:    */  }
/* 319:    */  
/* 320:    */  private static boolean isEXT_device_fissionSupported()
/* 321:    */  {
/* 322:322 */    return (clRetainDeviceEXT != 0L ? 1 : 0) & (clReleaseDeviceEXT != 0L ? 1 : 0) & (clCreateSubDevicesEXT != 0L ? 1 : 0);
/* 323:    */  }
/* 324:    */  
/* 327:    */  private static boolean isEXT_migrate_memobjectSupported()
/* 328:    */  {
/* 329:329 */    return clEnqueueMigrateMemObjectEXT != 0L;
/* 330:    */  }
/* 331:    */  
/* 332:    */  private static boolean isKHR_gl_eventSupported()
/* 333:    */  {
/* 334:334 */    return clCreateEventFromGLsyncKHR != 0L;
/* 335:    */  }
/* 336:    */  
/* 337:    */  private static boolean isKHR_gl_sharingSupported()
/* 338:    */  {
/* 339:339 */    return clGetGLContextInfoKHR != 0L;
/* 340:    */  }
/* 341:    */  
/* 342:    */  private static boolean isKHR_icdSupported()
/* 343:    */  {
/* 344:344 */    if (clIcdGetPlatformIDsKHR == 0L) {} return true;
/* 345:    */  }
/* 346:    */  
/* 347:    */  private static boolean isKHR_terminate_contextSupported()
/* 348:    */  {
/* 349:349 */    return clTerminateContextKHR != 0L;
/* 350:    */  }
/* 351:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLCapabilities
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */