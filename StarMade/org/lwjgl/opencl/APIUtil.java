/*   1:    */package org.lwjgl.opencl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.DoubleBuffer;
/*   5:    */import java.nio.FloatBuffer;
/*   6:    */import java.nio.IntBuffer;
/*   7:    */import java.nio.LongBuffer;
/*   8:    */import java.nio.ShortBuffer;
/*   9:    */import java.util.HashSet;
/*  10:    */import java.util.Set;
/*  11:    */import java.util.StringTokenizer;
/*  12:    */import org.lwjgl.BufferUtils;
/*  13:    */import org.lwjgl.LWJGLUtil;
/*  14:    */import org.lwjgl.MemoryUtil;
/*  15:    */import org.lwjgl.PointerBuffer;
/*  16:    */import org.lwjgl.PointerWrapper;
/*  17:    */
/*  54:    */final class APIUtil
/*  55:    */{
/*  56:    */  private static final int INITIAL_BUFFER_SIZE = 256;
/*  57:    */  private static final int INITIAL_LENGTHS_SIZE = 4;
/*  58:    */  private static final int BUFFERS_SIZE = 32;
/*  59: 59 */  private static final ThreadLocal<char[]> arrayTL = new ThreadLocal() {
/*  60: 60 */    protected char[] initialValue() { return new char[256]; }
/*  61:    */  };
/*  62:    */  
/*  63: 63 */  private static final ThreadLocal<ByteBuffer> bufferByteTL = new ThreadLocal() {
/*  64: 64 */    protected ByteBuffer initialValue() { return BufferUtils.createByteBuffer(256); }
/*  65:    */  };
/*  66:    */  
/*  67: 67 */  private static final ThreadLocal<PointerBuffer> bufferPointerTL = new ThreadLocal() {
/*  68: 68 */    protected PointerBuffer initialValue() { return BufferUtils.createPointerBuffer(256); }
/*  69:    */  };
/*  70:    */  
/*  71: 71 */  private static final ThreadLocal<PointerBuffer> lengthsTL = new ThreadLocal() {
/*  72: 72 */    protected PointerBuffer initialValue() { return BufferUtils.createPointerBuffer(4); }
/*  73:    */  };
/*  74:    */  
/*  75: 75 */  private static final ThreadLocal<Buffers> buffersTL = new ThreadLocal() {
/*  76: 76 */    protected APIUtil.Buffers initialValue() { return new APIUtil.Buffers(); }
/*  77:    */  };
/*  78:    */  
/*  81:    */  private static char[] getArray(int size)
/*  82:    */  {
/*  83: 83 */    char[] array = (char[])arrayTL.get();
/*  84:    */    
/*  85: 85 */    if (array.length < size) {
/*  86: 86 */      int sizeNew = array.length << 1;
/*  87: 87 */      while (sizeNew < size) {
/*  88: 88 */        sizeNew <<= 1;
/*  89:    */      }
/*  90: 90 */      array = new char[size];
/*  91: 91 */      arrayTL.set(array);
/*  92:    */    }
/*  93:    */    
/*  94: 94 */    return array;
/*  95:    */  }
/*  96:    */  
/*  97:    */  static ByteBuffer getBufferByte(int size) {
/*  98: 98 */    ByteBuffer buffer = (ByteBuffer)bufferByteTL.get();
/*  99:    */    
/* 100:100 */    if (buffer.capacity() < size) {
/* 101:101 */      int sizeNew = buffer.capacity() << 1;
/* 102:102 */      while (sizeNew < size) {
/* 103:103 */        sizeNew <<= 1;
/* 104:    */      }
/* 105:105 */      buffer = BufferUtils.createByteBuffer(size);
/* 106:106 */      bufferByteTL.set(buffer);
/* 107:    */    } else {
/* 108:108 */      buffer.clear();
/* 109:    */    }
/* 110:110 */    return buffer;
/* 111:    */  }
/* 112:    */  
/* 113:    */  private static ByteBuffer getBufferByteOffset(int size) {
/* 114:114 */    ByteBuffer buffer = (ByteBuffer)bufferByteTL.get();
/* 115:    */    
/* 116:116 */    if (buffer.capacity() < size) {
/* 117:117 */      int sizeNew = buffer.capacity() << 1;
/* 118:118 */      while (sizeNew < size) {
/* 119:119 */        sizeNew <<= 1;
/* 120:    */      }
/* 121:121 */      ByteBuffer bufferNew = BufferUtils.createByteBuffer(size);
/* 122:122 */      bufferNew.put(buffer);
/* 123:123 */      bufferByteTL.set(buffer = bufferNew);
/* 124:    */    } else {
/* 125:125 */      buffer.position(buffer.limit());
/* 126:126 */      buffer.limit(buffer.capacity());
/* 127:    */    }
/* 128:    */    
/* 129:129 */    return buffer;
/* 130:    */  }
/* 131:    */  
/* 132:    */  static PointerBuffer getBufferPointer(int size) {
/* 133:133 */    PointerBuffer buffer = (PointerBuffer)bufferPointerTL.get();
/* 134:    */    
/* 135:135 */    if (buffer.capacity() < size) {
/* 136:136 */      int sizeNew = buffer.capacity() << 1;
/* 137:137 */      while (sizeNew < size) {
/* 138:138 */        sizeNew <<= 1;
/* 139:    */      }
/* 140:140 */      buffer = BufferUtils.createPointerBuffer(size);
/* 141:141 */      bufferPointerTL.set(buffer);
/* 142:    */    } else {
/* 143:143 */      buffer.clear();
/* 144:    */    }
/* 145:145 */    return buffer;
/* 146:    */  }
/* 147:    */  
/* 148:148 */  static ShortBuffer getBufferShort() { return ((Buffers)buffersTL.get()).shorts; }
/* 149:    */  
/* 150:150 */  static IntBuffer getBufferInt() { return ((Buffers)buffersTL.get()).ints; }
/* 151:    */  
/* 152:152 */  static IntBuffer getBufferIntDebug() { return ((Buffers)buffersTL.get()).intsDebug; }
/* 153:    */  
/* 154:154 */  static LongBuffer getBufferLong() { return ((Buffers)buffersTL.get()).longs; }
/* 155:    */  
/* 156:156 */  static FloatBuffer getBufferFloat() { return ((Buffers)buffersTL.get()).floats; }
/* 157:    */  
/* 158:158 */  static DoubleBuffer getBufferDouble() { return ((Buffers)buffersTL.get()).doubles; }
/* 159:    */  
/* 160:160 */  static PointerBuffer getBufferPointer() { return ((Buffers)buffersTL.get()).pointers; }
/* 161:    */  
/* 162:    */  static PointerBuffer getLengths() {
/* 163:163 */    return getLengths(1);
/* 164:    */  }
/* 165:    */  
/* 166:    */  static PointerBuffer getLengths(int size) {
/* 167:167 */    PointerBuffer lengths = (PointerBuffer)lengthsTL.get();
/* 168:    */    
/* 169:169 */    if (lengths.capacity() < size) {
/* 170:170 */      int sizeNew = lengths.capacity();
/* 171:171 */      while (sizeNew < size) {
/* 172:172 */        sizeNew <<= 1;
/* 173:    */      }
/* 174:174 */      lengths = BufferUtils.createPointerBuffer(size);
/* 175:175 */      lengthsTL.set(lengths);
/* 176:    */    } else {
/* 177:177 */      lengths.clear();
/* 178:    */    }
/* 179:179 */    return lengths;
/* 180:    */  }
/* 181:    */  
/* 187:    */  private static ByteBuffer encode(ByteBuffer buffer, CharSequence string)
/* 188:    */  {
/* 189:189 */    for (int i = 0; i < string.length(); i++) {
/* 190:190 */      char c = string.charAt(i);
/* 191:191 */      if ((LWJGLUtil.DEBUG) && ('' <= c)) {
/* 192:192 */        buffer.put((byte)26);
/* 193:    */      } else {
/* 194:194 */        buffer.put((byte)c);
/* 195:    */      }
/* 196:    */    }
/* 197:197 */    return buffer;
/* 198:    */  }
/* 199:    */  
/* 206:    */  static String getString(ByteBuffer buffer)
/* 207:    */  {
/* 208:208 */    int length = buffer.remaining();
/* 209:209 */    char[] charArray = getArray(length);
/* 210:    */    
/* 211:211 */    for (int i = buffer.position(); i < buffer.limit(); i++) {
/* 212:212 */      charArray[(i - buffer.position())] = ((char)buffer.get(i));
/* 213:    */    }
/* 214:214 */    return new String(charArray, 0, length);
/* 215:    */  }
/* 216:    */  
/* 223:    */  static long getBuffer(CharSequence string)
/* 224:    */  {
/* 225:225 */    ByteBuffer buffer = encode(getBufferByte(string.length()), string);
/* 226:226 */    buffer.flip();
/* 227:227 */    return MemoryUtil.getAddress0(buffer);
/* 228:    */  }
/* 229:    */  
/* 236:    */  static long getBuffer(CharSequence string, int offset)
/* 237:    */  {
/* 238:238 */    ByteBuffer buffer = encode(getBufferByteOffset(offset + string.length()), string);
/* 239:239 */    buffer.flip();
/* 240:240 */    return MemoryUtil.getAddress(buffer);
/* 241:    */  }
/* 242:    */  
/* 249:    */  static long getBufferNT(CharSequence string)
/* 250:    */  {
/* 251:251 */    ByteBuffer buffer = encode(getBufferByte(string.length() + 1), string);
/* 252:252 */    buffer.put((byte)0);
/* 253:253 */    buffer.flip();
/* 254:254 */    return MemoryUtil.getAddress0(buffer);
/* 255:    */  }
/* 256:    */  
/* 257:    */  static int getTotalLength(CharSequence[] strings) {
/* 258:258 */    int length = 0;
/* 259:259 */    for (CharSequence string : strings) {
/* 260:260 */      length += string.length();
/* 261:    */    }
/* 262:262 */    return length;
/* 263:    */  }
/* 264:    */  
/* 271:    */  static long getBuffer(CharSequence[] strings)
/* 272:    */  {
/* 273:273 */    ByteBuffer buffer = getBufferByte(getTotalLength(strings));
/* 274:    */    
/* 275:275 */    for (CharSequence string : strings) {
/* 276:276 */      encode(buffer, string);
/* 277:    */    }
/* 278:278 */    buffer.flip();
/* 279:279 */    return MemoryUtil.getAddress0(buffer);
/* 280:    */  }
/* 281:    */  
/* 288:    */  static long getBufferNT(CharSequence[] strings)
/* 289:    */  {
/* 290:290 */    ByteBuffer buffer = getBufferByte(getTotalLength(strings) + strings.length);
/* 291:    */    
/* 292:292 */    for (CharSequence string : strings) {
/* 293:293 */      encode(buffer, string);
/* 294:294 */      buffer.put((byte)0);
/* 295:    */    }
/* 296:    */    
/* 297:297 */    buffer.flip();
/* 298:298 */    return MemoryUtil.getAddress0(buffer);
/* 299:    */  }
/* 300:    */  
/* 307:    */  static long getLengths(CharSequence[] strings)
/* 308:    */  {
/* 309:309 */    PointerBuffer buffer = getLengths(strings.length);
/* 310:    */    
/* 311:311 */    for (CharSequence string : strings) {
/* 312:312 */      buffer.put(string.length());
/* 313:    */    }
/* 314:314 */    buffer.flip();
/* 315:315 */    return MemoryUtil.getAddress0(buffer);
/* 316:    */  }
/* 317:    */  
/* 324:    */  static long getLengths(ByteBuffer[] buffers)
/* 325:    */  {
/* 326:326 */    PointerBuffer lengths = getLengths(buffers.length);
/* 327:    */    
/* 328:328 */    for (ByteBuffer buffer : buffers) {
/* 329:329 */      lengths.put(buffer.remaining());
/* 330:    */    }
/* 331:331 */    lengths.flip();
/* 332:332 */    return MemoryUtil.getAddress0(lengths);
/* 333:    */  }
/* 334:    */  
/* 335:    */  static int getSize(PointerBuffer lengths) {
/* 336:336 */    long size = 0L;
/* 337:337 */    for (int i = lengths.position(); i < lengths.limit(); i++) {
/* 338:338 */      size += lengths.get(i);
/* 339:    */    }
/* 340:340 */    return (int)size;
/* 341:    */  }
/* 342:    */  
/* 343:    */  static long getPointer(PointerWrapper pointer) {
/* 344:344 */    return MemoryUtil.getAddress0(getBufferPointer().put(0, pointer));
/* 345:    */  }
/* 346:    */  
/* 348:348 */  static long getPointerSafe(PointerWrapper pointer) { return MemoryUtil.getAddress0(getBufferPointer().put(0, pointer == null ? 0L : pointer.getPointer())); }
/* 349:    */  
/* 350:    */  private static abstract interface ObjectDestructor<T extends CLObjectChild> {
/* 351:    */    public abstract void release(T paramT);
/* 352:    */  }
/* 353:    */  
/* 354:    */  private static class Buffers {
/* 355:    */    final ShortBuffer shorts;
/* 356:    */    final IntBuffer ints;
/* 357:    */    final IntBuffer intsDebug;
/* 358:    */    final LongBuffer longs;
/* 359:    */    final FloatBuffer floats;
/* 360:    */    final DoubleBuffer doubles;
/* 361:    */    final PointerBuffer pointers;
/* 362:    */    
/* 363:    */    Buffers() {
/* 364:364 */      this.shorts = BufferUtils.createShortBuffer(32);
/* 365:365 */      this.ints = BufferUtils.createIntBuffer(32);
/* 366:366 */      this.intsDebug = BufferUtils.createIntBuffer(1);
/* 367:367 */      this.longs = BufferUtils.createLongBuffer(32);
/* 368:    */      
/* 369:369 */      this.floats = BufferUtils.createFloatBuffer(32);
/* 370:370 */      this.doubles = BufferUtils.createDoubleBuffer(32);
/* 371:    */      
/* 372:372 */      this.pointers = BufferUtils.createPointerBuffer(32);
/* 373:    */    }
/* 374:    */  }
/* 375:    */  
/* 382:    */  static Set<String> getExtensions(String extensionList)
/* 383:    */  {
/* 384:384 */    Set<String> extensions = new HashSet();
/* 385:    */    
/* 386:386 */    if (extensionList != null) {
/* 387:387 */      StringTokenizer tokenizer = new StringTokenizer(extensionList);
/* 388:388 */      while (tokenizer.hasMoreTokens()) {
/* 389:389 */        extensions.add(tokenizer.nextToken());
/* 390:    */      }
/* 391:    */    }
/* 392:392 */    return extensions;
/* 393:    */  }
/* 394:    */  
/* 395:    */  static boolean isDevicesParam(int param_name) {
/* 396:396 */    switch (param_name) {
/* 397:    */    case 4225: 
/* 398:    */    case 8198: 
/* 399:    */    case 8199: 
/* 400:    */    case 268435458: 
/* 401:    */    case 268435459: 
/* 402:402 */      return true;
/* 403:    */    }
/* 404:    */    
/* 405:405 */    return false;
/* 406:    */  }
/* 407:    */  
/* 408:    */  static CLPlatform getCLPlatform(PointerBuffer properties) {
/* 409:409 */    long platformID = 0L;
/* 410:    */    
/* 411:411 */    int keys = properties.remaining() / 2;
/* 412:412 */    for (int k = 0; k < keys; k++) {
/* 413:413 */      long key = properties.get(k << 1);
/* 414:414 */      if (key == 0L) {
/* 415:    */        break;
/* 416:    */      }
/* 417:417 */      if (key == 4228L) {
/* 418:418 */        platformID = properties.get((k << 1) + 1);
/* 419:419 */        break;
/* 420:    */      }
/* 421:    */    }
/* 422:    */    
/* 423:423 */    if (platformID == 0L) {
/* 424:424 */      throw new IllegalArgumentException("Could not find CL_CONTEXT_PLATFORM in cl_context_properties.");
/* 425:    */    }
/* 426:426 */    CLPlatform platform = CLPlatform.getCLPlatform(platformID);
/* 427:427 */    if (platform == null) {
/* 428:428 */      throw new IllegalStateException("Could not find a valid CLPlatform. Make sure clGetPlatformIDs has been used before.");
/* 429:    */    }
/* 430:430 */    return platform;
/* 431:    */  }
/* 432:    */  
/* 433:    */  static ByteBuffer getNativeKernelArgs(long user_func_ref, CLMem[] clMems, long[] sizes) {
/* 434:434 */    ByteBuffer args = getBufferByte(12 + (clMems == null ? 0 : clMems.length * (4 + PointerBuffer.getPointerSize())));
/* 435:    */    
/* 436:436 */    args.putLong(0, user_func_ref);
/* 437:437 */    if (clMems == null) {
/* 438:438 */      args.putInt(8, 0);
/* 439:    */    } else {
/* 440:440 */      args.putInt(8, clMems.length);
/* 441:441 */      int byteIndex = 12;
/* 442:442 */      for (int i = 0; i < clMems.length; i++) {
/* 443:443 */        if ((LWJGLUtil.DEBUG) && (!clMems[i].isValid()))
/* 444:444 */          throw new IllegalArgumentException("An invalid CLMem object was specified.");
/* 445:445 */        args.putInt(byteIndex, (int)sizes[i]);
/* 446:446 */        byteIndex += 4 + PointerBuffer.getPointerSize();
/* 447:    */      }
/* 448:    */    }
/* 449:    */    
/* 450:450 */    return args;
/* 451:    */  }
/* 452:    */  
/* 460:    */  static void releaseObjects(CLDevice device)
/* 461:    */  {
/* 462:462 */    if ((!device.isValid()) || (device.getReferenceCount() > 1)) {
/* 463:463 */      return;
/* 464:    */    }
/* 465:465 */    releaseObjects(device.getSubCLDeviceRegistry(), DESTRUCTOR_CLSubDevice);
/* 466:    */  }
/* 467:    */  
/* 473:    */  static void releaseObjects(CLContext context)
/* 474:    */  {
/* 475:475 */    if ((!context.isValid()) || (context.getReferenceCount() > 1)) {
/* 476:476 */      return;
/* 477:    */    }
/* 478:478 */    releaseObjects(context.getCLEventRegistry(), DESTRUCTOR_CLEvent);
/* 479:479 */    releaseObjects(context.getCLProgramRegistry(), DESTRUCTOR_CLProgram);
/* 480:480 */    releaseObjects(context.getCLSamplerRegistry(), DESTRUCTOR_CLSampler);
/* 481:481 */    releaseObjects(context.getCLMemRegistry(), DESTRUCTOR_CLMem);
/* 482:482 */    releaseObjects(context.getCLCommandQueueRegistry(), DESTRUCTOR_CLCommandQueue);
/* 483:    */  }
/* 484:    */  
/* 490:    */  static void releaseObjects(CLProgram program)
/* 491:    */  {
/* 492:492 */    if ((!program.isValid()) || (program.getReferenceCount() > 1)) {
/* 493:493 */      return;
/* 494:    */    }
/* 495:495 */    releaseObjects(program.getCLKernelRegistry(), DESTRUCTOR_CLKernel);
/* 496:    */  }
/* 497:    */  
/* 503:    */  static void releaseObjects(CLCommandQueue queue)
/* 504:    */  {
/* 505:505 */    if ((!queue.isValid()) || (queue.getReferenceCount() > 1)) {
/* 506:506 */      return;
/* 507:    */    }
/* 508:508 */    releaseObjects(queue.getCLEventRegistry(), DESTRUCTOR_CLEvent);
/* 509:    */  }
/* 510:    */  
/* 511:    */  private static <T extends CLObjectChild> void releaseObjects(CLObjectRegistry<T> registry, ObjectDestructor<T> destructor) {
/* 512:512 */    if (registry.isEmpty()) {
/* 513:513 */      return;
/* 514:    */    }
/* 515:515 */    for (FastLongMap.Entry<T> entry : registry.getAll()) {
/* 516:516 */      T object = (CLObjectChild)entry.value;
/* 517:517 */      while (object.isValid())
/* 518:518 */        destructor.release(object);
/* 519:    */    }
/* 520:    */  }
/* 521:    */  
/* 522:522 */  private static final ObjectDestructor<CLDevice> DESTRUCTOR_CLSubDevice = new ObjectDestructor() {
/* 523:523 */    public void release(CLDevice object) { EXTDeviceFission.clReleaseDeviceEXT(object); }
/* 524:    */  };
/* 525:525 */  private static final ObjectDestructor<CLMem> DESTRUCTOR_CLMem = new ObjectDestructor() {
/* 526:526 */    public void release(CLMem object) { CL10.clReleaseMemObject(object); }
/* 527:    */  };
/* 528:528 */  private static final ObjectDestructor<CLCommandQueue> DESTRUCTOR_CLCommandQueue = new ObjectDestructor() {
/* 529:529 */    public void release(CLCommandQueue object) { CL10.clReleaseCommandQueue(object); }
/* 530:    */  };
/* 531:531 */  private static final ObjectDestructor<CLSampler> DESTRUCTOR_CLSampler = new ObjectDestructor() {
/* 532:532 */    public void release(CLSampler object) { CL10.clReleaseSampler(object); }
/* 533:    */  };
/* 534:534 */  private static final ObjectDestructor<CLProgram> DESTRUCTOR_CLProgram = new ObjectDestructor() {
/* 535:535 */    public void release(CLProgram object) { CL10.clReleaseProgram(object); }
/* 536:    */  };
/* 537:537 */  private static final ObjectDestructor<CLKernel> DESTRUCTOR_CLKernel = new ObjectDestructor() {
/* 538:538 */    public void release(CLKernel object) { CL10.clReleaseKernel(object); }
/* 539:    */  };
/* 540:540 */  private static final ObjectDestructor<CLEvent> DESTRUCTOR_CLEvent = new ObjectDestructor() {
/* 541:541 */    public void release(CLEvent object) { CL10.clReleaseEvent(object); }
/* 542:    */  };
/* 543:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.APIUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */