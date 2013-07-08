/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.nio.Buffer;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.nio.DoubleBuffer;
/*   6:    */import java.nio.FloatBuffer;
/*   7:    */import java.nio.IntBuffer;
/*   8:    */import java.nio.LongBuffer;
/*   9:    */import java.nio.ShortBuffer;
/*  10:    */import java.util.ArrayList;
/*  11:    */import java.util.List;
/*  12:    */import org.lwjgl.BufferChecks;
/*  13:    */import org.lwjgl.BufferUtils;
/*  14:    */import org.lwjgl.LWJGLException;
/*  15:    */import org.lwjgl.LWJGLUtil;
/*  16:    */import org.lwjgl.MemoryUtil;
/*  17:    */import org.lwjgl.PointerBuffer;
/*  18:    */import org.lwjgl.opencl.api.CLBufferRegion;
/*  19:    */import org.lwjgl.opencl.api.CLImageFormat;
/*  20:    */import org.lwjgl.opencl.api.Filter;
/*  21:    */import org.lwjgl.opengl.Drawable;
/*  22:    */
/*  59:    */final class InfoUtilFactory
/*  60:    */{
/*  61: 61 */  static final InfoUtil<CLCommandQueue> CL_COMMAND_QUEUE_UTIL = new InfoUtilAbstract() {
/*  62:    */    protected int getInfo(CLCommandQueue object, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/*  63: 63 */      return CL10.clGetCommandQueueInfo(object, param_name, param_value, null);
/*  64:    */    }
/*  65:    */  };
/*  66:    */  
/*  67: 67 */  static final CLContext.CLContextUtil CL_CONTEXT_UTIL = new CLContextUtil(null);
/*  68:    */  
/*  69:    */  private static final class CLContextUtil extends InfoUtilAbstract<CLContext> implements CLContext.CLContextUtil {
/*  70:    */    protected int getInfo(CLContext context, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/*  71: 71 */      return CL10.clGetContextInfo(context, param_name, param_value, param_value_size_ret);
/*  72:    */    }
/*  73:    */    
/*  74:    */    public List<CLDevice> getInfoDevices(CLContext context) {
/*  75: 75 */      context.checkValid();
/*  76:    */      
/*  77:    */      int num_devices;
/*  78:    */      int num_devices;
/*  79: 79 */      if (CLCapabilities.getPlatformCapabilities((CLPlatform)context.getParent()).OpenCL11) {
/*  80: 80 */        num_devices = getInfoInt(context, 4227);
/*  81:    */      } else {
/*  82: 82 */        PointerBuffer size_ret = APIUtil.getBufferPointer();
/*  83: 83 */        CL10.clGetContextInfo(context, 4225, null, size_ret);
/*  84: 84 */        num_devices = (int)(size_ret.get(0) / PointerBuffer.getPointerSize());
/*  85:    */      }
/*  86:    */      
/*  87: 87 */      PointerBuffer deviceIDs = APIUtil.getBufferPointer(num_devices);
/*  88: 88 */      CL10.clGetContextInfo(context, 4225, deviceIDs.getBuffer(), null);
/*  89:    */      
/*  90: 90 */      List<CLDevice> devices = new ArrayList(num_devices);
/*  91: 91 */      for (int i = 0; i < num_devices; i++) {
/*  92: 92 */        devices.add(((CLPlatform)context.getParent()).getCLDevice(deviceIDs.get(i)));
/*  93:    */      }
/*  94: 94 */      return devices.size() == 0 ? null : devices;
/*  95:    */    }
/*  96:    */    
/*  97:    */    public CLContext create(CLPlatform platform, List<CLDevice> devices, CLContextCallback pfn_notify, Drawable share_drawable, IntBuffer errcode_ret)
/*  98:    */      throws LWJGLException
/*  99:    */    {
/* 100:100 */      int propertyCount = 2 + (share_drawable == null ? 0 : 4) + 1;
/* 101:    */      
/* 102:102 */      PointerBuffer properties = APIUtil.getBufferPointer(propertyCount + devices.size());
/* 103:103 */      properties.put(4228L).put(platform);
/* 104:104 */      if (share_drawable != null)
/* 105:105 */        share_drawable.setCLSharingProperties(properties);
/* 106:106 */      properties.put(0L);
/* 107:    */      
/* 108:108 */      properties.position(propertyCount);
/* 109:109 */      for (CLDevice device : devices) {
/* 110:110 */        properties.put(device);
/* 111:    */      }
/* 112:112 */      long function_pointer = CLCapabilities.clCreateContext;
/* 113:113 */      BufferChecks.checkFunctionAddress(function_pointer);
/* 114:114 */      if (errcode_ret != null) {
/* 115:115 */        BufferChecks.checkBuffer(errcode_ret, 1);
/* 116:116 */      } else if (LWJGLUtil.DEBUG)
/* 117:117 */        errcode_ret = APIUtil.getBufferInt();
/* 118:118 */      long user_data = (pfn_notify == null) || (pfn_notify.isCustom()) ? 0L : CallbackUtil.createGlobalRef(pfn_notify);
/* 119:119 */      CLContext __result = null;
/* 120:    */      try {
/* 121:121 */        __result = new CLContext(CL10.nclCreateContext(MemoryUtil.getAddress0(properties.getBuffer()), devices.size(), MemoryUtil.getAddress(properties, propertyCount), pfn_notify == null ? 0L : pfn_notify.getPointer(), user_data, MemoryUtil.getAddressSafe(errcode_ret), function_pointer), platform);
/* 122:122 */        if (LWJGLUtil.DEBUG)
/* 123:123 */          Util.checkCLError(errcode_ret.get(0));
/* 124:124 */        return __result;
/* 125:    */      } finally {
/* 126:126 */        if (__result != null) __result.setContextCallback(user_data);
/* 127:    */      }
/* 128:    */    }
/* 129:    */    
/* 130:    */    public CLContext createFromType(CLPlatform platform, long device_type, CLContextCallback pfn_notify, Drawable share_drawable, IntBuffer errcode_ret) throws LWJGLException {
/* 131:131 */      int propertyCount = 2 + (share_drawable == null ? 0 : 4) + 1;
/* 132:    */      
/* 133:133 */      PointerBuffer properties = APIUtil.getBufferPointer(propertyCount);
/* 134:134 */      properties.put(4228L).put(platform);
/* 135:135 */      if (share_drawable != null)
/* 136:136 */        share_drawable.setCLSharingProperties(properties);
/* 137:137 */      properties.put(0L);
/* 138:138 */      properties.flip();
/* 139:    */      
/* 140:140 */      return CL10.clCreateContextFromType(properties, device_type, pfn_notify, errcode_ret);
/* 141:    */    }
/* 142:    */    
/* 143:    */    public List<CLImageFormat> getSupportedImageFormats(CLContext context, long flags, int image_type, Filter<CLImageFormat> filter) {
/* 144:144 */      IntBuffer numBuffer = APIUtil.getBufferInt();
/* 145:145 */      CL10.clGetSupportedImageFormats(context, flags, image_type, null, numBuffer);
/* 146:    */      
/* 147:147 */      int num_image_formats = numBuffer.get(0);
/* 148:148 */      if (num_image_formats == 0) {
/* 149:149 */        return null;
/* 150:    */      }
/* 151:151 */      ByteBuffer formatBuffer = BufferUtils.createByteBuffer(num_image_formats * 8);
/* 152:152 */      CL10.clGetSupportedImageFormats(context, flags, image_type, formatBuffer, null);
/* 153:    */      
/* 154:154 */      List<CLImageFormat> formats = new ArrayList(num_image_formats);
/* 155:155 */      for (int i = 0; i < num_image_formats; i++) {
/* 156:156 */        int offset = num_image_formats * 8;
/* 157:157 */        CLImageFormat format = new CLImageFormat(formatBuffer.getInt(offset), formatBuffer.getInt(offset + 4));
/* 158:    */        
/* 161:161 */        if ((filter == null) || (filter.accept(format))) {
/* 162:162 */          formats.add(format);
/* 163:    */        }
/* 164:    */      }
/* 165:165 */      return formats.size() == 0 ? null : formats;
/* 166:    */    }
/* 167:    */  }
/* 168:    */  
/* 170:170 */  static final InfoUtil<CLDevice> CL_DEVICE_UTIL = new CLDeviceUtil(null);
/* 171:    */  
/* 172:    */  private static final class CLDeviceUtil extends InfoUtilAbstract<CLDevice> {
/* 173:    */    protected int getInfo(CLDevice device, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 174:174 */      return CL10.clGetDeviceInfo(device, param_name, param_value, param_value_size_ret);
/* 175:    */    }
/* 176:    */    
/* 177:    */    protected int getInfoSizeArraySize(CLDevice device, int param_name) {
/* 178:178 */      switch (param_name) {
/* 179:    */      case 4101: 
/* 180:180 */        return getInfoInt(device, 4099);
/* 181:    */      }
/* 182:182 */      throw new IllegalArgumentException("Unsupported parameter: " + LWJGLUtil.toHexString(param_name));
/* 183:    */    }
/* 184:    */  }
/* 185:    */  
/* 188:188 */  static final CLEvent.CLEventUtil CL_EVENT_UTIL = new CLEventUtil(null);
/* 189:    */  
/* 190:    */  private static final class CLEventUtil extends InfoUtilAbstract<CLEvent> implements CLEvent.CLEventUtil {
/* 191:    */    protected int getInfo(CLEvent event, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 192:192 */      return CL10.clGetEventInfo(event, param_name, param_value, param_value_size_ret);
/* 193:    */    }
/* 194:    */    
/* 195:    */    public long getProfilingInfoLong(CLEvent event, int param_name) {
/* 196:196 */      event.checkValid();
/* 197:    */      
/* 198:198 */      ByteBuffer buffer = APIUtil.getBufferByte(8);
/* 199:199 */      CL10.clGetEventProfilingInfo(event, param_name, buffer, null);
/* 200:    */      
/* 201:201 */      return buffer.getLong(0);
/* 202:    */    }
/* 203:    */  }
/* 204:    */  
/* 206:206 */  static final CLKernel.CLKernelUtil CL_KERNEL_UTIL = new CLKernelUtil(null);
/* 207:    */  
/* 208:    */  private static final class CLKernelUtil extends InfoUtilAbstract<CLKernel> implements CLKernel.CLKernelUtil {
/* 209:    */    public void setArg(CLKernel kernel, int index, byte value) {
/* 210:210 */      CL10.clSetKernelArg(kernel, index, 1L, APIUtil.getBufferByte(1).put(0, value));
/* 211:    */    }
/* 212:    */    
/* 213:    */    public void setArg(CLKernel kernel, int index, short value) {
/* 214:214 */      CL10.clSetKernelArg(kernel, index, 2L, APIUtil.getBufferShort().put(0, value));
/* 215:    */    }
/* 216:    */    
/* 217:    */    public void setArg(CLKernel kernel, int index, int value) {
/* 218:218 */      CL10.clSetKernelArg(kernel, index, 4L, APIUtil.getBufferInt().put(0, value));
/* 219:    */    }
/* 220:    */    
/* 221:    */    public void setArg(CLKernel kernel, int index, long value) {
/* 222:222 */      CL10.clSetKernelArg(kernel, index, 8L, APIUtil.getBufferLong().put(0, value));
/* 223:    */    }
/* 224:    */    
/* 225:    */    public void setArg(CLKernel kernel, int index, float value) {
/* 226:226 */      CL10.clSetKernelArg(kernel, index, 4L, APIUtil.getBufferFloat().put(0, value));
/* 227:    */    }
/* 228:    */    
/* 229:    */    public void setArg(CLKernel kernel, int index, double value) {
/* 230:230 */      CL10.clSetKernelArg(kernel, index, 8L, APIUtil.getBufferDouble().put(0, value));
/* 231:    */    }
/* 232:    */    
/* 233:    */    public void setArg(CLKernel kernel, int index, CLObject value) {
/* 234:234 */      CL10.clSetKernelArg(kernel, index, value);
/* 235:    */    }
/* 236:    */    
/* 237:    */    public void setArgSize(CLKernel kernel, int index, long size) {
/* 238:238 */      CL10.clSetKernelArg(kernel, index, size);
/* 239:    */    }
/* 240:    */    
/* 241:    */    protected int getInfo(CLKernel kernel, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 242:242 */      return CL10.clGetKernelInfo(kernel, param_name, param_value, param_value_size_ret);
/* 243:    */    }
/* 244:    */    
/* 245:    */    public long getWorkGroupInfoSize(CLKernel kernel, CLDevice device, int param_name) {
/* 246:246 */      device.checkValid();
/* 247:    */      
/* 248:248 */      PointerBuffer buffer = APIUtil.getBufferPointer();
/* 249:249 */      CL10.clGetKernelWorkGroupInfo(kernel, device, param_name, buffer.getBuffer(), null);
/* 250:    */      
/* 251:251 */      return buffer.get(0);
/* 252:    */    }
/* 253:    */    
/* 254:    */    public long[] getWorkGroupInfoSizeArray(CLKernel kernel, CLDevice device, int param_name) {
/* 255:255 */      device.checkValid();
/* 256:    */      
/* 257:    */      int size;
/* 258:258 */      switch (param_name) {
/* 259:    */      case 4529: 
/* 260:260 */        size = 3;
/* 261:261 */        break;
/* 262:    */      default: 
/* 263:263 */        throw new IllegalArgumentException("Unsupported parameter: " + LWJGLUtil.toHexString(param_name));
/* 264:    */      }
/* 265:    */      
/* 266:266 */      PointerBuffer buffer = APIUtil.getBufferPointer(size);
/* 267:    */      
/* 268:268 */      CL10.clGetKernelWorkGroupInfo(kernel, device, param_name, buffer.getBuffer(), null);
/* 269:    */      
/* 270:270 */      long[] array = new long[size];
/* 271:271 */      for (int i = 0; i < size; i++) {
/* 272:272 */        array[i] = buffer.get(i);
/* 273:    */      }
/* 274:274 */      return array;
/* 275:    */    }
/* 276:    */    
/* 277:    */    public long getWorkGroupInfoLong(CLKernel kernel, CLDevice device, int param_name) {
/* 278:278 */      device.checkValid();
/* 279:    */      
/* 280:280 */      ByteBuffer buffer = APIUtil.getBufferByte(8);
/* 281:281 */      CL10.clGetKernelWorkGroupInfo(kernel, device, param_name, buffer, null);
/* 282:    */      
/* 283:283 */      return buffer.getLong(0);
/* 284:    */    }
/* 285:    */  }
/* 286:    */  
/* 288:288 */  static final CLMem.CLMemUtil CL_MEM_UTIL = new CLMemUtil(null);
/* 289:    */  
/* 290:    */  private static final class CLMemUtil extends InfoUtilAbstract<CLMem> implements CLMem.CLMemUtil {
/* 291:    */    protected int getInfo(CLMem mem, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 292:292 */      return CL10.clGetMemObjectInfo(mem, param_name, param_value, param_value_size_ret);
/* 293:    */    }
/* 294:    */    
/* 295:    */    public CLMem createImage2D(CLContext context, long flags, CLImageFormat image_format, long image_width, long image_height, long image_row_pitch, Buffer host_ptr, IntBuffer errcode_ret) {
/* 296:296 */      ByteBuffer formatBuffer = APIUtil.getBufferByte(8);
/* 297:297 */      formatBuffer.putInt(0, image_format.getChannelOrder());
/* 298:298 */      formatBuffer.putInt(4, image_format.getChannelType());
/* 299:    */      
/* 300:300 */      long function_pointer = CLCapabilities.clCreateImage2D;
/* 301:301 */      BufferChecks.checkFunctionAddress(function_pointer);
/* 302:302 */      if (errcode_ret != null) {
/* 303:303 */        BufferChecks.checkBuffer(errcode_ret, 1);
/* 304:304 */      } else if (LWJGLUtil.DEBUG) {
/* 305:305 */        errcode_ret = APIUtil.getBufferInt();
/* 306:    */      }
/* 307:307 */      CLMem __result = new CLMem(CL10.nclCreateImage2D(context.getPointer(), flags, MemoryUtil.getAddress(formatBuffer, 0), image_width, image_height, image_row_pitch, MemoryUtil.getAddress0Safe(host_ptr) + (host_ptr != null ? BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage2DSize(formatBuffer, image_width, image_height, image_row_pitch)) : 0), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 308:    */      
/* 310:310 */      if (LWJGLUtil.DEBUG)
/* 311:311 */        Util.checkCLError(errcode_ret.get(0));
/* 312:312 */      return __result;
/* 313:    */    }
/* 314:    */    
/* 315:    */    public CLMem createImage3D(CLContext context, long flags, CLImageFormat image_format, long image_width, long image_height, long image_depth, long image_row_pitch, long image_slice_pitch, Buffer host_ptr, IntBuffer errcode_ret) {
/* 316:316 */      ByteBuffer formatBuffer = APIUtil.getBufferByte(8);
/* 317:317 */      formatBuffer.putInt(0, image_format.getChannelOrder());
/* 318:318 */      formatBuffer.putInt(4, image_format.getChannelType());
/* 319:    */      
/* 320:320 */      long function_pointer = CLCapabilities.clCreateImage3D;
/* 321:321 */      BufferChecks.checkFunctionAddress(function_pointer);
/* 322:322 */      if (errcode_ret != null) {
/* 323:323 */        BufferChecks.checkBuffer(errcode_ret, 1);
/* 324:324 */      } else if (LWJGLUtil.DEBUG) {
/* 325:325 */        errcode_ret = APIUtil.getBufferInt();
/* 326:    */      }
/* 327:327 */      CLMem __result = new CLMem(CL10.nclCreateImage3D(context.getPointer(), flags, MemoryUtil.getAddress(formatBuffer, 0), image_width, image_height, image_depth, image_row_pitch, image_slice_pitch, MemoryUtil.getAddress0Safe(host_ptr) + (host_ptr != null ? BufferChecks.checkBuffer(host_ptr, CLChecks.calculateImage3DSize(formatBuffer, image_width, image_height, image_depth, image_row_pitch, image_slice_pitch)) : 0), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 328:    */      
/* 330:330 */      if (LWJGLUtil.DEBUG)
/* 331:331 */        Util.checkCLError(errcode_ret.get(0));
/* 332:332 */      return __result;
/* 333:    */    }
/* 334:    */    
/* 335:    */    public CLMem createSubBuffer(CLMem mem, long flags, int buffer_create_type, CLBufferRegion buffer_create_info, IntBuffer errcode_ret) {
/* 336:336 */      PointerBuffer infoBuffer = APIUtil.getBufferPointer(2);
/* 337:    */      
/* 338:338 */      infoBuffer.put(buffer_create_info.getOrigin());
/* 339:339 */      infoBuffer.put(buffer_create_info.getSize());
/* 340:    */      
/* 341:341 */      return CL11.clCreateSubBuffer(mem, flags, buffer_create_type, infoBuffer.getBuffer(), errcode_ret);
/* 342:    */    }
/* 343:    */    
/* 344:    */    public ByteBuffer getInfoHostBuffer(CLMem mem) {
/* 345:345 */      mem.checkValid();
/* 346:    */      
/* 347:347 */      if (LWJGLUtil.DEBUG) {
/* 348:348 */        long mem_flags = getInfoLong(mem, 4353);
/* 349:349 */        if ((mem_flags & 0x8) != 8L) {
/* 350:350 */          throw new IllegalArgumentException("The specified CLMem object does not use host memory.");
/* 351:    */        }
/* 352:    */      }
/* 353:353 */      long size = getInfoSize(mem, 4354);
/* 354:354 */      if (size == 0L) {
/* 355:355 */        return null;
/* 356:    */      }
/* 357:357 */      long address = getInfoSize(mem, 4355);
/* 358:    */      
/* 359:359 */      return CL.getHostBuffer(address, (int)size);
/* 360:    */    }
/* 361:    */    
/* 362:    */    public long getImageInfoSize(CLMem mem, int param_name) {
/* 363:363 */      mem.checkValid();
/* 364:    */      
/* 365:365 */      PointerBuffer buffer = APIUtil.getBufferPointer();
/* 366:366 */      CL10.clGetImageInfo(mem, param_name, buffer.getBuffer(), null);
/* 367:    */      
/* 368:368 */      return buffer.get(0);
/* 369:    */    }
/* 370:    */    
/* 371:    */    public CLImageFormat getImageInfoFormat(CLMem mem) {
/* 372:372 */      mem.checkValid();
/* 373:    */      
/* 374:374 */      ByteBuffer format = APIUtil.getBufferByte(8);
/* 375:    */      
/* 376:376 */      CL10.clGetImageInfo(mem, 4368, format, null);
/* 377:    */      
/* 378:378 */      return new CLImageFormat(format.getInt(0), format.getInt(4));
/* 379:    */    }
/* 380:    */    
/* 381:    */    public int getImageInfoFormat(CLMem mem, int index) {
/* 382:382 */      mem.checkValid();
/* 383:    */      
/* 384:384 */      ByteBuffer format = APIUtil.getBufferByte(8);
/* 385:    */      
/* 386:386 */      CL10.clGetImageInfo(mem, 4368, format, null);
/* 387:    */      
/* 388:388 */      return format.getInt(index << 2);
/* 389:    */    }
/* 390:    */    
/* 391:    */    public int getGLObjectType(CLMem mem) {
/* 392:392 */      mem.checkValid();
/* 393:    */      
/* 394:394 */      IntBuffer buffer = APIUtil.getBufferInt();
/* 395:395 */      CL10GL.clGetGLObjectInfo(mem, buffer, null);
/* 396:    */      
/* 397:397 */      return buffer.get(0);
/* 398:    */    }
/* 399:    */    
/* 400:    */    public int getGLObjectName(CLMem mem) {
/* 401:401 */      mem.checkValid();
/* 402:    */      
/* 403:403 */      IntBuffer buffer = APIUtil.getBufferInt();
/* 404:404 */      CL10GL.clGetGLObjectInfo(mem, null, buffer);
/* 405:    */      
/* 406:406 */      return buffer.get(0);
/* 407:    */    }
/* 408:    */    
/* 409:    */    public int getGLTextureInfoInt(CLMem mem, int param_name) {
/* 410:410 */      mem.checkValid();
/* 411:    */      
/* 412:412 */      ByteBuffer buffer = APIUtil.getBufferByte(4);
/* 413:413 */      CL10GL.clGetGLTextureInfo(mem, param_name, buffer, null);
/* 414:    */      
/* 415:415 */      return buffer.getInt(0);
/* 416:    */    }
/* 417:    */  }
/* 418:    */  
/* 420:420 */  static final CLPlatform.CLPlatformUtil CL_PLATFORM_UTIL = new CLPlatformUtil(null);
/* 421:    */  
/* 422:    */  private static final class CLPlatformUtil extends InfoUtilAbstract<CLPlatform> implements CLPlatform.CLPlatformUtil {
/* 423:    */    protected int getInfo(CLPlatform platform, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 424:424 */      return CL10.clGetPlatformInfo(platform, param_name, param_value, param_value_size_ret);
/* 425:    */    }
/* 426:    */    
/* 427:    */    public List<CLPlatform> getPlatforms(Filter<CLPlatform> filter) {
/* 428:428 */      IntBuffer numBuffer = APIUtil.getBufferInt();
/* 429:429 */      CL10.clGetPlatformIDs(null, numBuffer);
/* 430:    */      
/* 431:431 */      int num_platforms = numBuffer.get(0);
/* 432:432 */      if (num_platforms == 0) {
/* 433:433 */        return null;
/* 434:    */      }
/* 435:435 */      PointerBuffer platformIDs = APIUtil.getBufferPointer(num_platforms);
/* 436:436 */      CL10.clGetPlatformIDs(platformIDs, null);
/* 437:    */      
/* 438:438 */      List<CLPlatform> platforms = new ArrayList(num_platforms);
/* 439:439 */      for (int i = 0; i < num_platforms; i++) {
/* 440:440 */        CLPlatform platform = CLPlatform.getCLPlatform(platformIDs.get(i));
/* 441:441 */        if ((filter == null) || (filter.accept(platform))) {
/* 442:442 */          platforms.add(platform);
/* 443:    */        }
/* 444:    */      }
/* 445:445 */      return platforms.size() == 0 ? null : platforms;
/* 446:    */    }
/* 447:    */    
/* 448:    */    public List<CLDevice> getDevices(CLPlatform platform, int device_type, Filter<CLDevice> filter) {
/* 449:449 */      platform.checkValid();
/* 450:    */      
/* 451:451 */      IntBuffer numBuffer = APIUtil.getBufferInt();
/* 452:452 */      CL10.clGetDeviceIDs(platform, device_type, null, numBuffer);
/* 453:    */      
/* 454:454 */      int num_devices = numBuffer.get(0);
/* 455:455 */      if (num_devices == 0) {
/* 456:456 */        return null;
/* 457:    */      }
/* 458:458 */      PointerBuffer deviceIDs = APIUtil.getBufferPointer(num_devices);
/* 459:459 */      CL10.clGetDeviceIDs(platform, device_type, deviceIDs, null);
/* 460:    */      
/* 461:461 */      List<CLDevice> devices = new ArrayList(num_devices);
/* 462:462 */      for (int i = 0; i < num_devices; i++) {
/* 463:463 */        CLDevice device = platform.getCLDevice(deviceIDs.get(i));
/* 464:464 */        if ((filter == null) || (filter.accept(device))) {
/* 465:465 */          devices.add(device);
/* 466:    */        }
/* 467:    */      }
/* 468:468 */      return devices.size() == 0 ? null : devices;
/* 469:    */    }
/* 470:    */  }
/* 471:    */  
/* 473:473 */  static final CLProgram.CLProgramUtil CL_PROGRAM_UTIL = new CLProgramUtil(null);
/* 474:    */  
/* 475:    */  private static final class CLProgramUtil extends InfoUtilAbstract<CLProgram> implements CLProgram.CLProgramUtil {
/* 476:    */    protected int getInfo(CLProgram program, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 477:477 */      return CL10.clGetProgramInfo(program, param_name, param_value, param_value_size_ret);
/* 478:    */    }
/* 479:    */    
/* 480:    */    protected int getInfoSizeArraySize(CLProgram program, int param_name) {
/* 481:481 */      switch (param_name) {
/* 482:    */      case 4453: 
/* 483:483 */        return getInfoInt(program, 4450);
/* 484:    */      }
/* 485:485 */      throw new IllegalArgumentException("Unsupported parameter: " + LWJGLUtil.toHexString(param_name));
/* 486:    */    }
/* 487:    */    
/* 488:    */    public CLKernel[] createKernelsInProgram(CLProgram program)
/* 489:    */    {
/* 490:490 */      IntBuffer numBuffer = APIUtil.getBufferInt();
/* 491:491 */      CL10.clCreateKernelsInProgram(program, null, numBuffer);
/* 492:    */      
/* 493:493 */      int num_kernels = numBuffer.get(0);
/* 494:494 */      if (num_kernels == 0) {
/* 495:495 */        return null;
/* 496:    */      }
/* 497:497 */      PointerBuffer kernelIDs = APIUtil.getBufferPointer(num_kernels);
/* 498:498 */      CL10.clCreateKernelsInProgram(program, kernelIDs, null);
/* 499:    */      
/* 500:500 */      CLKernel[] kernels = new CLKernel[num_kernels];
/* 501:501 */      for (int i = 0; i < num_kernels; i++) {
/* 502:502 */        kernels[i] = program.getCLKernel(kernelIDs.get(i));
/* 503:    */      }
/* 504:504 */      return kernels;
/* 505:    */    }
/* 506:    */    
/* 507:    */    public CLDevice[] getInfoDevices(CLProgram program) {
/* 508:508 */      program.checkValid();
/* 509:    */      
/* 510:510 */      int size = getInfoInt(program, 4450);
/* 511:511 */      PointerBuffer buffer = APIUtil.getBufferPointer(size);
/* 512:    */      
/* 513:513 */      CL10.clGetProgramInfo(program, 4451, buffer.getBuffer(), null);
/* 514:    */      
/* 515:515 */      CLPlatform platform = (CLPlatform)((CLContext)program.getParent()).getParent();
/* 516:516 */      CLDevice[] array = new CLDevice[size];
/* 517:517 */      for (int i = 0; i < size; i++) {
/* 518:518 */        array[i] = platform.getCLDevice(buffer.get(i));
/* 519:    */      }
/* 520:520 */      return array;
/* 521:    */    }
/* 522:    */    
/* 523:    */    public ByteBuffer getInfoBinaries(CLProgram program, ByteBuffer target) {
/* 524:524 */      program.checkValid();
/* 525:    */      
/* 526:526 */      PointerBuffer sizes = getSizesBuffer(program, 4453);
/* 527:    */      
/* 528:528 */      int totalSize = 0;
/* 529:529 */      for (int i = 0; i < sizes.limit(); i++) {
/* 530:530 */        totalSize = (int)(totalSize + sizes.get(i));
/* 531:    */      }
/* 532:532 */      if (target == null) {
/* 533:533 */        target = BufferUtils.createByteBuffer(totalSize);
/* 534:534 */      } else if (LWJGLUtil.DEBUG) {
/* 535:535 */        BufferChecks.checkBuffer(target, totalSize);
/* 536:    */      }
/* 537:537 */      CL10.clGetProgramInfo(program, sizes, target, null);
/* 538:    */      
/* 539:539 */      return target;
/* 540:    */    }
/* 541:    */    
/* 542:    */    public ByteBuffer[] getInfoBinaries(CLProgram program, ByteBuffer[] target) {
/* 543:543 */      program.checkValid();
/* 544:    */      
/* 545:545 */      if (target == null) {
/* 546:546 */        PointerBuffer sizes = getSizesBuffer(program, 4453);
/* 547:    */        
/* 548:548 */        target = new ByteBuffer[sizes.remaining()];
/* 549:549 */        for (int i = 0; i < sizes.remaining(); i++)
/* 550:550 */          target[i] = BufferUtils.createByteBuffer((int)sizes.get(0));
/* 551:551 */      } else if (LWJGLUtil.DEBUG) {
/* 552:552 */        PointerBuffer sizes = getSizesBuffer(program, 4453);
/* 553:    */        
/* 554:554 */        if (target.length < sizes.remaining()) {
/* 555:555 */          throw new IllegalArgumentException("The target array is not big enough: " + sizes.remaining() + " buffers are required.");
/* 556:    */        }
/* 557:557 */        for (int i = 0; i < target.length; i++) {
/* 558:558 */          BufferChecks.checkBuffer(target[i], (int)sizes.get(i));
/* 559:    */        }
/* 560:    */      }
/* 561:561 */      CL10.clGetProgramInfo(program, target, null);
/* 562:    */      
/* 563:563 */      return target;
/* 564:    */    }
/* 565:    */    
/* 566:    */    public String getBuildInfoString(CLProgram program, CLDevice device, int param_name) {
/* 567:567 */      program.checkValid();
/* 568:    */      
/* 569:569 */      int bytes = getBuildSizeRet(program, device, param_name);
/* 570:570 */      if (bytes <= 1) {
/* 571:571 */        return null;
/* 572:    */      }
/* 573:573 */      ByteBuffer buffer = APIUtil.getBufferByte(bytes);
/* 574:574 */      CL10.clGetProgramBuildInfo(program, device, param_name, buffer, null);
/* 575:    */      
/* 576:576 */      buffer.limit(bytes - 1);
/* 577:577 */      return APIUtil.getString(buffer);
/* 578:    */    }
/* 579:    */    
/* 580:    */    public int getBuildInfoInt(CLProgram program, CLDevice device, int param_name) {
/* 581:581 */      program.checkValid();
/* 582:    */      
/* 583:583 */      ByteBuffer buffer = APIUtil.getBufferByte(4);
/* 584:584 */      CL10.clGetProgramBuildInfo(program, device, param_name, buffer, null);
/* 585:    */      
/* 586:586 */      return buffer.getInt(0);
/* 587:    */    }
/* 588:    */    
/* 589:    */    private static int getBuildSizeRet(CLProgram program, CLDevice device, int param_name) {
/* 590:590 */      PointerBuffer bytes = APIUtil.getBufferPointer();
/* 591:591 */      int errcode = CL10.clGetProgramBuildInfo(program, device, param_name, null, bytes);
/* 592:592 */      if (errcode != 0) {
/* 593:593 */        throw new IllegalArgumentException("Invalid parameter specified: " + LWJGLUtil.toHexString(param_name));
/* 594:    */      }
/* 595:595 */      return (int)bytes.get(0);
/* 596:    */    }
/* 597:    */  }
/* 598:    */  
/* 600:600 */  static final InfoUtil<CLSampler> CL_SAMPLER_UTIL = new InfoUtilAbstract() {
/* 601:    */    protected int getInfo(CLSampler sampler, int param_name, ByteBuffer param_value, PointerBuffer param_value_size_ret) {
/* 602:602 */      return CL10.clGetSamplerInfo(sampler, param_name, param_value, param_value_size_ret);
/* 603:    */    }
/* 604:    */  };
/* 605:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.InfoUtilFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */