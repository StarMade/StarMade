/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.nio.Buffer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ import org.lwjgl.opencl.api.CLBufferRegion;
/*     */ import org.lwjgl.opencl.api.CLImageFormat;
/*     */ import org.lwjgl.opencl.api.Filter;
/*     */ import org.lwjgl.opengl.Drawable;
/*     */ 
/*     */ final class InfoUtilFactory
/*     */ {
/*  61 */   static final InfoUtil<CLCommandQueue> CL_COMMAND_QUEUE_UTIL = new InfoUtilAbstract() {
/*     */     protected int getInfo(CLCommandQueue object, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/*  63 */       return CL10.clGetCommandQueueInfo(object, param_name, param_value, null);
/*     */     }
/*  61 */   };
/*     */ 
/*  67 */   static final CLContext.CLContextUtil CL_CONTEXT_UTIL = new CLContextUtil(null);
/*     */ 
/* 170 */   static final InfoUtil<CLDevice> CL_DEVICE_UTIL = new CLDeviceUtil(null);
/*     */ 
/* 188 */   static final CLEvent.CLEventUtil CL_EVENT_UTIL = new CLEventUtil(null);
/*     */ 
/* 206 */   static final CLKernel.CLKernelUtil CL_KERNEL_UTIL = new CLKernelUtil(null);
/*     */ 
/* 288 */   static final CLMem.CLMemUtil CL_MEM_UTIL = new CLMemUtil(null);
/*     */ 
/* 420 */   static final CLPlatform.CLPlatformUtil CL_PLATFORM_UTIL = new CLPlatformUtil(null);
/*     */ 
/* 473 */   static final CLProgram.CLProgramUtil CL_PROGRAM_UTIL = new CLProgramUtil(null);
/*     */ 
/* 600 */   static final InfoUtil<CLSampler> CL_SAMPLER_UTIL = new InfoUtilAbstract() {
/*     */     protected int getInfo(CLSampler sampler, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 602 */       return CL10.clGetSamplerInfo(sampler, param_name, param_value, param_value_size_ret);
/*     */     }
/* 600 */   };
/*     */ 
/*     */   private static final class CLProgramUtil extends InfoUtilAbstract<CLProgram>
/*     */     implements CLProgram.CLProgramUtil
/*     */   {
/*     */     protected int getInfo(CLProgram program, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret)
/*     */     {
/* 477 */       return CL10.clGetProgramInfo(program, param_name, param_value, param_value_size_ret);
/*     */     }
/*     */ 
/*     */     protected int getInfoSizeArraySize(CLProgram program, int param_name) {
/* 481 */       switch (param_name) {
/*     */       case 4453:
/* 483 */         return getInfoInt(program, 4450);
/*     */       }
/* 485 */       throw new IllegalArgumentException("Unsupported parameter: " + LWJGLUtil.toHexString(param_name));
/*     */     }
/*     */ 
/*     */     public CLKernel[] createKernelsInProgram(CLProgram program)
/*     */     {
/* 490 */       IntBuffer numBuffer = APIUtil.getBufferInt();
/* 491 */       CL10.clCreateKernelsInProgram(program, null, numBuffer);
/*     */ 
/* 493 */       int num_kernels = numBuffer.get(0);
/* 494 */       if (num_kernels == 0) {
/* 495 */         return null;
/*     */       }
/* 497 */       PointerBuffer kernelIDs = APIUtil.getBufferPointer(num_kernels);
/* 498 */       CL10.clCreateKernelsInProgram(program, kernelIDs, null);
/*     */ 
/* 500 */       CLKernel[] kernels = new CLKernel[num_kernels];
/* 501 */       for (int i = 0; i < num_kernels; i++) {
/* 502 */         kernels[i] = program.getCLKernel(kernelIDs.get(i));
/*     */       }
/* 504 */       return kernels;
/*     */     }
/*     */ 
/*     */     public CLDevice[] getInfoDevices(CLProgram program) {
/* 508 */       program.checkValid();
/*     */ 
/* 510 */       int size = getInfoInt(program, 4450);
/* 511 */       PointerBuffer buffer = APIUtil.getBufferPointer(size);
/*     */ 
/* 513 */       CL10.clGetProgramInfo(program, 4451, buffer.getBuffer(), null);
/*     */ 
/* 515 */       CLPlatform platform = (CLPlatform)((CLContext)program.getParent()).getParent();
/* 516 */       CLDevice[] array = new CLDevice[size];
/* 517 */       for (int i = 0; i < size; i++) {
/* 518 */         array[i] = platform.getCLDevice(buffer.get(i));
/*     */       }
/* 520 */       return array;
/*     */     }
/*     */ 
/*     */     public ByteBuffer getInfoBinaries(CLProgram program, ByteBuffer target) {
/* 524 */       program.checkValid();
/*     */ 
/* 526 */       PointerBuffer sizes = getSizesBuffer(program, 4453);
/*     */ 
/* 528 */       int totalSize = 0;
/* 529 */       for (int i = 0; i < sizes.limit(); i++) {
/* 530 */         totalSize = (int)(totalSize + sizes.get(i));
/*     */       }
/* 532 */       if (target == null)
/* 533 */         target = BufferUtils.createByteBuffer(totalSize);
/* 534 */       else if (LWJGLUtil.DEBUG) {
/* 535 */         BufferChecks.checkBuffer(target, totalSize);
/*     */       }
/* 537 */       CL10.clGetProgramInfo(program, sizes, target, null);
/*     */ 
/* 539 */       return target;
/*     */     }
/*     */ 
/*     */     public ByteBuffer[] getInfoBinaries(CLProgram program, ByteBuffer[] target) {
/* 543 */       program.checkValid();
/*     */ 
/* 545 */       if (target == null) {
/* 546 */         PointerBuffer sizes = getSizesBuffer(program, 4453);
/*     */ 
/* 548 */         target = new ByteBuffer[sizes.remaining()];
/* 549 */         for (int i = 0; i < sizes.remaining(); i++)
/* 550 */           target[i] = BufferUtils.createByteBuffer((int)sizes.get(0));
/* 551 */       } else if (LWJGLUtil.DEBUG) {
/* 552 */         PointerBuffer sizes = getSizesBuffer(program, 4453);
/*     */ 
/* 554 */         if (target.length < sizes.remaining()) {
/* 555 */           throw new IllegalArgumentException("The target array is not big enough: " + sizes.remaining() + " buffers are required.");
/*     */         }
/* 557 */         for (int i = 0; i < target.length; i++) {
/* 558 */           BufferChecks.checkBuffer(target[i], (int)sizes.get(i));
/*     */         }
/*     */       }
/* 561 */       CL10.clGetProgramInfo(program, target, null);
/*     */ 
/* 563 */       return target;
/*     */     }
/*     */ 
/*     */     public String getBuildInfoString(CLProgram program, CLDevice device, int param_name) {
/* 567 */       program.checkValid();
/*     */ 
/* 569 */       int bytes = getBuildSizeRet(program, device, param_name);
/* 570 */       if (bytes <= 1) {
/* 571 */         return null;
/*     */       }
/* 573 */       ByteBuffer buffer = APIUtil.getBufferByte(bytes);
/* 574 */       CL10.clGetProgramBuildInfo(program, device, param_name, buffer, null);
/*     */ 
/* 576 */       buffer.limit(bytes - 1);
/* 577 */       return APIUtil.getString(buffer);
/*     */     }
/*     */ 
/*     */     public int getBuildInfoInt(CLProgram program, CLDevice device, int param_name) {
/* 581 */       program.checkValid();
/*     */ 
/* 583 */       ByteBuffer buffer = APIUtil.getBufferByte(4);
/* 584 */       CL10.clGetProgramBuildInfo(program, device, param_name, buffer, null);
/*     */ 
/* 586 */       return buffer.getInt(0);
/*     */     }
/*     */ 
/*     */     private static int getBuildSizeRet(CLProgram program, CLDevice device, int param_name) {
/* 590 */       PointerBuffer bytes = APIUtil.getBufferPointer();
/* 591 */       int errcode = CL10.clGetProgramBuildInfo(program, device, param_name, null, bytes);
/* 592 */       if (errcode != 0) {
/* 593 */         throw new IllegalArgumentException("Invalid parameter specified: " + LWJGLUtil.toHexString(param_name));
/*     */       }
/* 595 */       return (int)bytes.get(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class CLPlatformUtil extends InfoUtilAbstract<CLPlatform>
/*     */     implements CLPlatform.CLPlatformUtil
/*     */   {
/*     */     protected int getInfo(CLPlatform platform, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret)
/*     */     {
/* 424 */       return CL10.clGetPlatformInfo(platform, param_name, param_value, param_value_size_ret);
/*     */     }
/*     */ 
/*     */     public List<CLPlatform> getPlatforms(Filter<CLPlatform> filter) {
/* 428 */       IntBuffer numBuffer = APIUtil.getBufferInt();
/* 429 */       CL10.clGetPlatformIDs(null, numBuffer);
/*     */ 
/* 431 */       int num_platforms = numBuffer.get(0);
/* 432 */       if (num_platforms == 0) {
/* 433 */         return null;
/*     */       }
/* 435 */       PointerBuffer platformIDs = APIUtil.getBufferPointer(num_platforms);
/* 436 */       CL10.clGetPlatformIDs(platformIDs, null);
/*     */ 
/* 438 */       List platforms = new ArrayList(num_platforms);
/* 439 */       for (int i = 0; i < num_platforms; i++) {
/* 440 */         CLPlatform platform = CLPlatform.getCLPlatform(platformIDs.get(i));
/* 441 */         if ((filter == null) || (filter.accept(platform))) {
/* 442 */           platforms.add(platform);
/*     */         }
/*     */       }
/* 445 */       return platforms.size() == 0 ? null : platforms;
/*     */     }
/*     */ 
/*     */     public List<CLDevice> getDevices(CLPlatform platform, int device_type, Filter<CLDevice> filter) {
/* 449 */       platform.checkValid();
/*     */ 
/* 451 */       IntBuffer numBuffer = APIUtil.getBufferInt();
/* 452 */       CL10.clGetDeviceIDs(platform, device_type, null, numBuffer);
/*     */ 
/* 454 */       int num_devices = numBuffer.get(0);
/* 455 */       if (num_devices == 0) {
/* 456 */         return null;
/*     */       }
/* 458 */       PointerBuffer deviceIDs = APIUtil.getBufferPointer(num_devices);
/* 459 */       CL10.clGetDeviceIDs(platform, device_type, deviceIDs, null);
/*     */ 
/* 461 */       List devices = new ArrayList(num_devices);
/* 462 */       for (int i = 0; i < num_devices; i++) {
/* 463 */         CLDevice device = platform.getCLDevice(deviceIDs.get(i));
/* 464 */         if ((filter == null) || (filter.accept(device))) {
/* 465 */           devices.add(device);
/*     */         }
/*     */       }
/* 468 */       return devices.size() == 0 ? null : devices;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class CLMemUtil extends InfoUtilAbstract<CLMem>
/*     */     implements CLMem.CLMemUtil
/*     */   {
/*     */     protected int getInfo(CLMem mem, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret)
/*     */     {
/* 292 */       return CL10.clGetMemObjectInfo(mem, param_name, param_value, param_value_size_ret);
/*     */     }
/*     */ 
/*     */     public CLMem createImage2D(CLContext context, long flags, CLImageFormat image_format, long image_width, long image_height, long image_row_pitch, Buffer host_ptr, IntBuffer errcode_ret) {
/* 296 */       ByteBuffer formatBuffer = APIUtil.getBufferByte(8);
/* 297 */       formatBuffer.putInt(0, image_format.getChannelOrder());
/* 298 */       formatBuffer.putInt(4, image_format.getChannelType());
/*     */ 
/* 300 */       long function_pointer = CLCapabilities.clCreateImage2D;
/* 301 */       BufferChecks.checkFunctionAddress(function_pointer);
/* 302 */       if (errcode_ret != null)
/* 303 */         BufferChecks.checkBuffer(errcode_ret, 1);
/* 304 */       else if (LWJGLUtil.DEBUG) {
/* 305 */         errcode_ret = APIUtil.getBufferInt();
/*     */       }
/* 307 */       CLMem __result = new CLMem(CL10.nclCreateImage2D(context.getPointer(), flags, MemoryUtil.getAddress(formatBuffer, 0), image_width, image_height, image_row_pitch, MemoryUtil.getAddress0Safe(host_ptr) + (host_ptr != null ? BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage2DSize(formatBuffer, image_width, image_height, image_row_pitch)) : 0), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*     */ 
/* 310 */       if (LWJGLUtil.DEBUG)
/* 311 */         Util.checkCLError(errcode_ret.get(0));
/* 312 */       return __result;
/*     */     }
/*     */ 
/*     */     public CLMem createImage3D(CLContext context, long flags, CLImageFormat image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, Buffer host_ptr, IntBuffer errcode_ret) {
/* 316 */       ByteBuffer formatBuffer = APIUtil.getBufferByte(8);
/* 317 */       formatBuffer.putInt(0, image_format.getChannelOrder());
/* 318 */       formatBuffer.putInt(4, image_format.getChannelType());
/*     */ 
/* 320 */       long function_pointer = CLCapabilities.clCreateImage3D;
/* 321 */       BufferChecks.checkFunctionAddress(function_pointer);
/* 322 */       if (errcode_ret != null)
/* 323 */         BufferChecks.checkBuffer(errcode_ret, 1);
/* 324 */       else if (LWJGLUtil.DEBUG) {
/* 325 */         errcode_ret = APIUtil.getBufferInt();
/*     */       }
/* 327 */       CLMem __result = new CLMem(CL10.nclCreateImage3D(context.getPointer(), flags, MemoryUtil.getAddress(formatBuffer, 0), image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, MemoryUtil.getAddress0Safe(host_ptr) + (host_ptr != null ? BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage3DSize(formatBuffer, image_width, image_height, image_depth, image_row_pitch, image_slice_pitch)) : 0), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/*     */ 
/* 330 */       if (LWJGLUtil.DEBUG)
/* 331 */         Util.checkCLError(errcode_ret.get(0));
/* 332 */       return __result;
/*     */     }
/*     */ 
/*     */     public CLMem createSubBuffer(CLMem mem, long flags, int buffer_create_type, CLBufferRegion buffer_create_info, IntBuffer errcode_ret) {
/* 336 */       PointerBuffer infoBuffer = APIUtil.getBufferPointer(2);
/*     */ 
/* 338 */       infoBuffer.put(buffer_create_info.getOrigin());
/* 339 */       infoBuffer.put(buffer_create_info.getSize());
/*     */ 
/* 341 */       return CL11.clCreateSubBuffer(mem, flags, buffer_create_type, infoBuffer.getBuffer(), errcode_ret);
/*     */     }
/*     */ 
/*     */     public ByteBuffer getInfoHostBuffer(CLMem mem) {
/* 345 */       mem.checkValid();
/*     */ 
/* 347 */       if (LWJGLUtil.DEBUG) {
/* 348 */         long mem_flags = getInfoLong(mem, 4353);
/* 349 */         if ((mem_flags & 0x8) != 8L) {
/* 350 */           throw new IllegalArgumentException("The specified CLMem object does not use host memory.");
/*     */         }
/*     */       }
/* 353 */       long size = getInfoSize(mem, 4354);
/* 354 */       if (size == 0L) {
/* 355 */         return null;
/*     */       }
/* 357 */       long address = getInfoSize(mem, 4355);
/*     */ 
/* 359 */       return CL.getHostBuffer(address, (int)size);
/*     */     }
/*     */ 
/*     */     public long getImageInfoSize(CLMem mem, int param_name) {
/* 363 */       mem.checkValid();
/*     */ 
/* 365 */       PointerBuffer buffer = APIUtil.getBufferPointer();
/* 366 */       CL10.clGetImageInfo(mem, param_name, buffer.getBuffer(), null);
/*     */ 
/* 368 */       return buffer.get(0);
/*     */     }
/*     */ 
/*     */     public CLImageFormat getImageInfoFormat(CLMem mem) {
/* 372 */       mem.checkValid();
/*     */ 
/* 374 */       ByteBuffer format = APIUtil.getBufferByte(8);
/*     */ 
/* 376 */       CL10.clGetImageInfo(mem, 4368, format, null);
/*     */ 
/* 378 */       return new CLImageFormat(format.getInt(0), format.getInt(4));
/*     */     }
/*     */ 
/*     */     public int getImageInfoFormat(CLMem mem, int index) {
/* 382 */       mem.checkValid();
/*     */ 
/* 384 */       ByteBuffer format = APIUtil.getBufferByte(8);
/*     */ 
/* 386 */       CL10.clGetImageInfo(mem, 4368, format, null);
/*     */ 
/* 388 */       return format.getInt(index << 2);
/*     */     }
/*     */ 
/*     */     public int getGLObjectType(CLMem mem) {
/* 392 */       mem.checkValid();
/*     */ 
/* 394 */       IntBuffer buffer = APIUtil.getBufferInt();
/* 395 */       CL10GL.clGetGLObjectInfo(mem, buffer, null);
/*     */ 
/* 397 */       return buffer.get(0);
/*     */     }
/*     */ 
/*     */     public int getGLObjectName(CLMem mem) {
/* 401 */       mem.checkValid();
/*     */ 
/* 403 */       IntBuffer buffer = APIUtil.getBufferInt();
/* 404 */       CL10GL.clGetGLObjectInfo(mem, null, buffer);
/*     */ 
/* 406 */       return buffer.get(0);
/*     */     }
/*     */ 
/*     */     public int getGLTextureInfoInt(CLMem mem, int param_name) {
/* 410 */       mem.checkValid();
/*     */ 
/* 412 */       ByteBuffer buffer = APIUtil.getBufferByte(4);
/* 413 */       CL10GL.clGetGLTextureInfo(mem, param_name, buffer, null);
/*     */ 
/* 415 */       return buffer.getInt(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class CLKernelUtil extends InfoUtilAbstract<CLKernel>
/*     */     implements CLKernel.CLKernelUtil
/*     */   {
/*     */     public void setArg(CLKernel kernel, int index, byte value)
/*     */     {
/* 210 */       CL10.clSetKernelArg(kernel, index, 1L, APIUtil.getBufferByte(1).put(0, value));
/*     */     }
/*     */ 
/*     */     public void setArg(CLKernel kernel, int index, short value) {
/* 214 */       CL10.clSetKernelArg(kernel, index, 2L, APIUtil.getBufferShort().put(0, value));
/*     */     }
/*     */ 
/*     */     public void setArg(CLKernel kernel, int index, int value) {
/* 218 */       CL10.clSetKernelArg(kernel, index, 4L, APIUtil.getBufferInt().put(0, value));
/*     */     }
/*     */ 
/*     */     public void setArg(CLKernel kernel, int index, long value) {
/* 222 */       CL10.clSetKernelArg(kernel, index, 8L, APIUtil.getBufferLong().put(0, value));
/*     */     }
/*     */ 
/*     */     public void setArg(CLKernel kernel, int index, float value) {
/* 226 */       CL10.clSetKernelArg(kernel, index, 4L, APIUtil.getBufferFloat().put(0, value));
/*     */     }
/*     */ 
/*     */     public void setArg(CLKernel kernel, int index, double value) {
/* 230 */       CL10.clSetKernelArg(kernel, index, 8L, APIUtil.getBufferDouble().put(0, value));
/*     */     }
/*     */ 
/*     */     public void setArg(CLKernel kernel, int index, CLObject value) {
/* 234 */       CL10.clSetKernelArg(kernel, index, value);
/*     */     }
/*     */ 
/*     */     public void setArgSize(CLKernel kernel, int index, long size) {
/* 238 */       CL10.clSetKernelArg(kernel, index, size);
/*     */     }
/*     */ 
/*     */     protected int getInfo(CLKernel kernel, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 242 */       return CL10.clGetKernelInfo(kernel, param_name, param_value, param_value_size_ret);
/*     */     }
/*     */ 
/*     */     public long getWorkGroupInfoSize(CLKernel kernel, CLDevice device, int param_name) {
/* 246 */       device.checkValid();
/*     */ 
/* 248 */       PointerBuffer buffer = APIUtil.getBufferPointer();
/* 249 */       CL10.clGetKernelWorkGroupInfo(kernel, device, param_name, buffer.getBuffer(), null);
/*     */ 
/* 251 */       return buffer.get(0);
/*     */     }
/*     */ 
/*     */     public long[] getWorkGroupInfoSizeArray(CLKernel kernel, CLDevice device, int param_name) {
/* 255 */       device.checkValid();
/*     */       int size;
/* 258 */       switch (param_name) {
/*     */       case 4529:
/* 260 */         size = 3;
/* 261 */         break;
/*     */       default:
/* 263 */         throw new IllegalArgumentException("Unsupported parameter: " + LWJGLUtil.toHexString(param_name));
/*     */       }
/*     */ 
/* 266 */       PointerBuffer buffer = APIUtil.getBufferPointer(size);
/*     */ 
/* 268 */       CL10.clGetKernelWorkGroupInfo(kernel, device, param_name, buffer.getBuffer(), null);
/*     */ 
/* 270 */       long[] array = new long[size];
/* 271 */       for (int i = 0; i < size; i++) {
/* 272 */         array[i] = buffer.get(i);
/*     */       }
/* 274 */       return array;
/*     */     }
/*     */ 
/*     */     public long getWorkGroupInfoLong(CLKernel kernel, CLDevice device, int param_name) {
/* 278 */       device.checkValid();
/*     */ 
/* 280 */       ByteBuffer buffer = APIUtil.getBufferByte(8);
/* 281 */       CL10.clGetKernelWorkGroupInfo(kernel, device, param_name, buffer, null);
/*     */ 
/* 283 */       return buffer.getLong(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class CLEventUtil extends InfoUtilAbstract<CLEvent>
/*     */     implements CLEvent.CLEventUtil
/*     */   {
/*     */     protected int getInfo(CLEvent event, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret)
/*     */     {
/* 192 */       return CL10.clGetEventInfo(event, param_name, param_value, param_value_size_ret);
/*     */     }
/*     */ 
/*     */     public long getProfilingInfoLong(CLEvent event, int param_name) {
/* 196 */       event.checkValid();
/*     */ 
/* 198 */       ByteBuffer buffer = APIUtil.getBufferByte(8);
/* 199 */       CL10.clGetEventProfilingInfo(event, param_name, buffer, null);
/*     */ 
/* 201 */       return buffer.getLong(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class CLDeviceUtil extends InfoUtilAbstract<CLDevice>
/*     */   {
/*     */     protected int getInfo(CLDevice device, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret)
/*     */     {
/* 174 */       return CL10.clGetDeviceInfo(device, param_name, param_value, param_value_size_ret);
/*     */     }
/*     */ 
/*     */     protected int getInfoSizeArraySize(CLDevice device, int param_name) {
/* 178 */       switch (param_name) {
/*     */       case 4101:
/* 180 */         return getInfoInt(device, 4099);
/*     */       }
/* 182 */       throw new IllegalArgumentException("Unsupported parameter: " + LWJGLUtil.toHexString(param_name));
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class CLContextUtil extends InfoUtilAbstract<CLContext>
/*     */     implements CLContext.CLContextUtil
/*     */   {
/*     */     protected int getInfo(CLContext context, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret)
/*     */     {
/*  71 */       return CL10.clGetContextInfo(context, param_name, param_value, param_value_size_ret);
/*     */     }
/*     */ 
/*     */     public List<CLDevice> getInfoDevices(CLContext context) {
/*  75 */       context.checkValid();
/*     */       int num_devices;
/*     */       int num_devices;
/*  79 */       if (CLCapabilities.getPlatformCapabilities((CLPlatform)context.getParent()).OpenCL11) {
/*  80 */         num_devices = getInfoInt(context, 4227);
/*     */       } else {
/*  82 */         PointerBuffer size_ret = APIUtil.getBufferPointer();
/*  83 */         CL10.clGetContextInfo(context, 4225, null, size_ret);
/*  84 */         num_devices = (int)(size_ret.get(0) / PointerBuffer.getPointerSize());
/*     */       }
/*     */ 
/*  87 */       PointerBuffer deviceIDs = APIUtil.getBufferPointer(num_devices);
/*  88 */       CL10.clGetContextInfo(context, 4225, deviceIDs.getBuffer(), null);
/*     */ 
/*  90 */       List devices = new ArrayList(num_devices);
/*  91 */       for (int i = 0; i < num_devices; i++) {
/*  92 */         devices.add(((CLPlatform)context.getParent()).getCLDevice(deviceIDs.get(i)));
/*     */       }
/*  94 */       return devices.size() == 0 ? null : devices;
/*     */     }
/*     */ 
/*     */     public CLContext create(CLPlatform platform, List<CLDevice> devices, CLContextCallback pfn_notify, Drawable share_drawable, IntBuffer errcode_ret)
/*     */       throws LWJGLException
/*     */     {
/* 100 */       int propertyCount = 2 + (share_drawable == null ? 0 : 4) + 1;
/*     */ 
/* 102 */       PointerBuffer properties = APIUtil.getBufferPointer(propertyCount + devices.size());
/* 103 */       properties.put(4228L).put(platform);
/* 104 */       if (share_drawable != null)
/* 105 */         share_drawable.setCLSharingProperties(properties);
/* 106 */       properties.put(0L);
/*     */ 
/* 108 */       properties.position(propertyCount);
/* 109 */       for (CLDevice device : devices) {
/* 110 */         properties.put(device);
/*     */       }
/* 112 */       long function_pointer = CLCapabilities.clCreateContext;
/* 113 */       BufferChecks.checkFunctionAddress(function_pointer);
/* 114 */       if (errcode_ret != null)
/* 115 */         BufferChecks.checkBuffer(errcode_ret, 1);
/* 116 */       else if (LWJGLUtil.DEBUG)
/* 117 */         errcode_ret = APIUtil.getBufferInt();
/* 118 */       long user_data = (pfn_notify == null) || (pfn_notify.isCustom()) ? 0L : CallbackUtil.createGlobalRef(pfn_notify);
/* 119 */       CLContext __result = null;
/*     */       try {
/* 121 */         __result = new CLContext(CL10.nclCreateContext(MemoryUtil.getAddress0(properties.getBuffer()), devices.size(), MemoryUtil.getAddress(properties, propertyCount), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), platform);
/* 122 */         if (LWJGLUtil.DEBUG)
/* 123 */           Util.checkCLError(errcode_ret.get(0));
/* 124 */         return __result;
/*     */       } finally {
/* 126 */         if (__result != null) __result.setContextCallback(user_data); 
/*     */       }
/*     */     }
/*     */ 
/*     */     public CLContext createFromType(CLPlatform platform, long device_type, CLContextCallback pfn_notify, Drawable share_drawable, IntBuffer errcode_ret) throws LWJGLException {
/* 131 */       int propertyCount = 2 + (share_drawable == null ? 0 : 4) + 1;
/*     */ 
/* 133 */       PointerBuffer properties = APIUtil.getBufferPointer(propertyCount);
/* 134 */       properties.put(4228L).put(platform);
/* 135 */       if (share_drawable != null)
/* 136 */         share_drawable.setCLSharingProperties(properties);
/* 137 */       properties.put(0L);
/* 138 */       properties.flip();
/*     */ 
/* 140 */       return CL10.clCreateContextFromType(properties, device_type, pfn_notify, errcode_ret);
/*     */     }
/*     */ 
/*     */     public List<CLImageFormat> getSupportedImageFormats(CLContext context, long flags, int image_type, Filter<CLImageFormat> filter) {
/* 144 */       IntBuffer numBuffer = APIUtil.getBufferInt();
/* 145 */       CL10.clGetSupportedImageFormats(context, flags, image_type, null, numBuffer);
/*     */ 
/* 147 */       int num_image_formats = numBuffer.get(0);
/* 148 */       if (num_image_formats == 0) {
/* 149 */         return null;
/*     */       }
/* 151 */       ByteBuffer formatBuffer = BufferUtils.createByteBuffer(num_image_formats * 8);
/* 152 */       CL10.clGetSupportedImageFormats(context, flags, image_type, formatBuffer, null);
/*     */ 
/* 154 */       List formats = new ArrayList(num_image_formats);
/* 155 */       for (int i = 0; i < num_image_formats; i++) {
/* 156 */         int offset = num_image_formats * 8;
/* 157 */         CLImageFormat format = new CLImageFormat(formatBuffer.getInt(offset), formatBuffer.getInt(offset + 4));
/*     */ 
/* 161 */         if ((filter == null) || (filter.accept(format))) {
/* 162 */           formats.add(format);
/*     */         }
/*     */       }
/* 165 */       return formats.size() == 0 ? null : formats;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.InfoUtilFactory
 * JD-Core Version:    0.6.2
 */