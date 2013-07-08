/*   1:    */package org.schema.game.common.data.world;
/*   2:    */
/*   3:    */import java.io.DataOutputStream;
/*   4:    */import java.nio.ByteBuffer;
/*   5:    */import java.util.Arrays;
/*   6:    */import kd;
/*   7:    */import org.schema.schine.network.StateInterface;
/*   8:    */import vg;
/*   9:    */
/*  10:    */public class SegmentData
/*  11:    */{
/*  12:    */  private Segment segment;
/*  13:    */  private byte[] data;
/*  14:    */  public static final int lightBlockSize = 39;
/*  15:    */  public static final int typeIndexStart = 0;
/*  16:    */  public static final int typeIndexEnd = 11;
/*  17:    */  public static final int hitpointsIndexStart = 11;
/*  18:    */  public static final int hitpointsIndexEnd = 20;
/*  19:    */  public static final int activeIndexStart = 20;
/*  20:    */  public static final int activeIndexEnd = 21;
/*  21:    */  public static final int orientationStart = 21;
/*  22:    */  public static final int orientationEnd = 24;
/*  23:    */  public static final int blockSize = 3;
/*  24:    */  
/*  25:    */  public static float byteArrayToFloat(byte[] paramArrayOfByte)
/*  26:    */  {
/*  27: 27 */    int i = 0;
/*  28: 28 */    int j = 0;
/*  29: 29 */    for (int k = 3; k >= 0; k--) {
/*  30: 30 */      i |= (paramArrayOfByte[j] & 0xFF) << (k << 3);
/*  31: 31 */      j++;
/*  32:    */    }
/*  33:    */    
/*  34: 34 */    return Float.intBitsToFloat(i);
/*  35:    */  }
/*  36:    */  
/*  41:    */  public static byte[] floatToByteArray(float paramFloat)
/*  42:    */  {
/*  43: 43 */    return intToByteArray(Float.floatToRawIntBits(paramFloat));
/*  44:    */  }
/*  45:    */  
/*  56:    */  public static byte[] intToByteArray(int paramInt)
/*  57:    */  {
/*  58: 58 */    byte[] arrayOfByte = new byte[4];
/*  59: 59 */    for (int i = 0; i < 4; i++) {
/*  60: 60 */      int j = arrayOfByte.length - 1 - i << 3;
/*  61: 61 */      arrayOfByte[i] = ((byte)(paramInt >>> j));
/*  62:    */    }
/*  63: 63 */    return arrayOfByte;
/*  64:    */  }
/*  65:    */  
/*  69:    */  public static final byte ACTIVE_BIT = 16;
/*  70:    */  
/*  73:    */  public static final int vis = 12;
/*  74:    */  
/*  77:    */  public static final int BLOCK_COUNT = 4096;
/*  78:    */  
/*  80:    */  public static final int TOTAL_SIZE = 12288;
/*  81:    */  
/*  83:    */  public static final int TOTAL_SIZE_LIGHT = 159744;
/*  84:    */  
/*  86:    */  public static final int SEG_TIMES_SEG_TIMES_SEG = 4096;
/*  87:    */  
/*  89:    */  public static final int SEG_TIMES_SEG = 256;
/*  90:    */  
/*  92:    */  public static final int SEG = 16;
/*  93:    */  
/*  95:    */  private static final int SEG_MINUS_ONE = 15;
/*  96:    */  
/*  98:    */  public static final int t = 255;
/*  99:    */  
/* 101:    */  public static final byte ANTI_BYTE = -16;
/* 102:    */  
/* 104:    */  private int size;
/* 105:    */  
/* 107:    */  public static boolean allNeighborsInside(byte paramByte1, byte paramByte2, byte paramByte3)
/* 108:    */  {
/* 109:109 */    return (paramByte1 < 15) && (paramByte2 < 15) && (paramByte3 < 15) && (paramByte1 > 0) && (paramByte2 > 0) && (paramByte3 > 0);
/* 110:    */  }
/* 111:    */  
/* 114:    */  public static int getInfoIndex(byte paramByte1, byte paramByte2, byte paramByte3)
/* 115:    */  {
/* 116:116 */    return 3 * ((paramByte3 << 8) + (paramByte2 << 4) + paramByte1);
/* 117:    */  }
/* 118:    */  
/* 119:    */  public static int getInfoIndex(o paramo)
/* 120:    */  {
/* 121:121 */    return getInfoIndex(paramo.a, paramo.b, paramo.c);
/* 122:    */  }
/* 123:    */  
/* 124:124 */  public static int getLightInfoIndexFromIndex(int paramInt) { return paramInt / 3 * 3 * 24; }
/* 125:    */  
/* 127:    */  public static boolean valid(byte paramByte1, byte paramByte2, byte paramByte3)
/* 128:    */  {
/* 129:129 */    return ((paramByte1 | paramByte2 | paramByte3) & 0xFFFFFFF0) == 0;
/* 130:    */  }
/* 131:    */  
/* 135:135 */  private final o min = new o();
/* 136:136 */  private final o max = new o();
/* 137:    */  
/* 138:    */  private final org.schema.game.common.data.physics.octree.ArrayOctree octree;
/* 139:139 */  private final o helperPos = new o();
/* 140:    */  
/* 141:    */  private boolean preserveControl;
/* 142:    */  private static final int MASK = 255;
/* 143:143 */  private boolean needsRevalidate = false;
/* 144:    */  
/* 145:    */  private boolean revalidating;
/* 146:    */  public static final int PIECE_ADDED = 0;
/* 147:    */  public static final int PIECE_REMOVED = 1;
/* 148:    */  public static final int PIECE_CHANGED = 2;
/* 149:    */  public static final int PIECE_UNCHANGED = 3;
/* 150:    */  public static final int PIECE_ACTIVE_CHANGED = 4;
/* 151:    */  
/* 152:    */  public SegmentData(boolean paramBoolean)
/* 153:    */  {
/* 154:154 */    this.octree = new org.schema.game.common.data.physics.octree.ArrayOctree(!paramBoolean);
/* 155:    */    
/* 156:156 */    this.data = new byte[12288];
/* 157:    */    
/* 158:158 */    resetBB();
/* 159:    */  }
/* 160:    */  
/* 167:    */  public int applySegmentData(o paramo, byte[] paramArrayOfByte)
/* 168:    */  {
/* 169:169 */    synchronized (this) {
/* 170:170 */      int i = getInfoIndex(paramo);
/* 171:171 */      int j = 0;
/* 172:172 */      boolean bool = isActive(i);
/* 173:173 */      int k = getType(i);
/* 174:174 */      byte[] arrayOfByte = getHitpoints(i);
/* 175:175 */      for (int m = i; m < i + 3; m++) {
/* 176:176 */        this.data[m] = paramArrayOfByte[(j++)];
/* 177:    */      }
/* 178:    */      
/* 179:179 */      m = getType(i);
/* 180:180 */      paramArrayOfByte = getHitpoints(i);
/* 181:    */      
/* 183:183 */      if (m != k)
/* 184:    */      {
/* 186:186 */        if ((k == 0) && (m != 0)) {
/* 187:187 */          onAddingElement(i, paramo.a, paramo.b, paramo.c, m, true);
/* 188:188 */          return 0;
/* 189:    */        }
/* 190:190 */        if ((k != 0) && (m == 0)) {
/* 191:191 */          onRemovingElement(i, paramo.a, paramo.b, paramo.c, k, true);
/* 192:192 */          return 1;
/* 193:    */        }
/* 194:194 */        return 2;
/* 195:    */      }
/* 196:    */      
/* 197:197 */      if (arrayOfByte != paramArrayOfByte) {
/* 198:198 */        return 2;
/* 199:    */      }
/* 200:200 */      if (bool != isActive(i)) {
/* 201:201 */        return 4;
/* 202:    */      }
/* 203:203 */      return 3;
/* 204:    */    }
/* 205:    */  }
/* 206:    */  
/* 214:214 */  public int arraySize() { return this.data.length; }
/* 215:    */  
/* 216:    */  public void assignData(Segment paramSegment) {
/* 217:217 */    paramSegment.a(this);
/* 218:218 */    setSegment(paramSegment);
/* 219:219 */    resetBB();
/* 220:    */  }
/* 221:    */  
/* 222:    */  public boolean contains(byte paramByte1, byte paramByte2, byte paramByte3) {
/* 223:223 */    if (valid(paramByte1, paramByte2, paramByte3)) {
/* 224:224 */      return containsUnsave(paramByte1, paramByte2, paramByte3);
/* 225:    */    }
/* 226:226 */    return false;
/* 227:    */  }
/* 228:    */  
/* 229:    */  public boolean contains(int paramInt) {
/* 230:230 */    return getType(paramInt) != 0;
/* 231:    */  }
/* 232:    */  
/* 249:    */  public boolean contains(o paramo)
/* 250:    */  {
/* 251:251 */    return contains(paramo.a, paramo.b, paramo.c);
/* 252:    */  }
/* 253:    */  
/* 254:    */  public boolean containsFast(int paramInt) {
/* 255:255 */    int i = this.data[(paramInt + 2)] & 0xFF;
/* 256:256 */    paramInt = this.data[(paramInt + 1)] & 0xFF;
/* 257:257 */    return (i != 0) || ((paramInt & 0x7) != 0);
/* 258:    */  }
/* 259:    */  
/* 261:261 */  public boolean containsFast(o paramo) { return containsFast(getInfoIndex(paramo)); }
/* 262:    */  
/* 263:    */  public boolean containsUnblended(byte paramByte1, byte paramByte2, byte paramByte3) {
/* 264:264 */    if (valid(paramByte1, paramByte2, paramByte3))
/* 265:    */    {
/* 266:266 */      return ((paramByte1 = getType(paramByte1, paramByte2, paramByte3)) != 0) && (!org.schema.game.common.data.element.ElementKeyMap.getInfo(paramByte1).isBlended());
/* 267:    */    }
/* 268:    */    
/* 269:269 */    return false;
/* 270:    */  }
/* 271:    */  
/* 273:    */  public boolean containsUnblended(o paramo)
/* 274:    */  {
/* 275:275 */    return containsUnblended(paramo.a, paramo.b, paramo.c);
/* 276:    */  }
/* 277:    */  
/* 278:278 */  public boolean containsUnsave(byte paramByte1, byte paramByte2, byte paramByte3) { return containsFast(getInfoIndex(paramByte1, paramByte2, paramByte3)); }
/* 279:    */  
/* 281:281 */  public boolean containsUnsave(int paramInt) { return getType(paramInt) != 0; }
/* 282:    */  
/* 283:    */  public void createFromByteBuffer(byte[] paramArrayOfByte, StateInterface arg2) {
/* 284:284 */    paramArrayOfByte = ByteBuffer.wrap(paramArrayOfByte);
/* 285:285 */    synchronized (this) {
/* 286:286 */      if (this.data == null) {
/* 287:287 */        this.data = new byte[12288];
/* 288:    */      }
/* 289:289 */      for (int i = 0; i < this.data.length; i++)
/* 290:290 */        this.data[i] = paramArrayOfByte.get();
/* 291:    */      return;
/* 292:    */    }
/* 293:    */  }
/* 294:    */  
/* 295:295 */  public void deSerialize(java.io.DataInputStream paramDataInputStream) { synchronized (this) {
/* 296:296 */      reset();
/* 297:297 */      paramDataInputStream.readFully(this.data);
/* 298:298 */      setNeedsRevalidate(true);
/* 299:299 */      return;
/* 300:    */    }
/* 301:    */  }
/* 302:    */  
/* 320:    */  public byte[] getAsBuffer()
/* 321:    */  {
/* 322:322 */    return this.data;
/* 323:    */  }
/* 324:    */  
/* 325:    */  public short getHitpoints(int paramInt) {
/* 326:326 */    return (short)org.schema.common.util.ByteUtil.a(org.schema.common.util.ByteUtil.a(this.data, paramInt), 11, 20);
/* 327:    */  }
/* 328:    */  
/* 329:329 */  public o getMax() { return this.max; }
/* 330:    */  
/* 331:    */  public o getMin() {
/* 332:332 */    return this.min;
/* 333:    */  }
/* 334:    */  
/* 361:    */  public org.schema.game.common.data.physics.octree.ArrayOctree getOctree()
/* 362:    */  {
/* 363:363 */    return this.octree;
/* 364:    */  }
/* 365:    */  
/* 366:    */  public byte getOrientation(int paramInt)
/* 367:    */  {
/* 368:368 */    return (byte)((this.data[paramInt] & 0xFF) >> 5 & 0x7);
/* 369:    */  }
/* 370:    */  
/* 371:    */  public static void main(String[] paramArrayOfString) {
/* 372:372 */    paramArrayOfString = new byte[3];
/* 373:373 */    byte[] arrayOfByte = new byte[3];
/* 374:    */    
/* 376:376 */    for (int i = 0; i < 8; i++) {
/* 377:377 */      new Object();org.schema.common.util.ByteUtil.a(paramArrayOfString, i, 21, 24, 0);
/* 378:378 */      new Object();int j = (byte)org.schema.common.util.ByteUtil.a(org.schema.common.util.ByteUtil.a(paramArrayOfString, 0), 21, 24);
/* 379:    */      
/* 382:382 */      int k = (byte)((paramArrayOfString[0] & 0xFF) >> 5 & 0x7);
/* 383:383 */      for (int m = 0; m < 3; m++) {
/* 384:384 */        System.err.println(m + ": " + Integer.toBinaryString(paramArrayOfString[m] & 0xFF));
/* 385:    */      }
/* 386:386 */      System.err.println("---------------");
/* 387:387 */      for (m = 0; m < 3; m++) {
/* 388:388 */        System.err.println(m + ": " + Integer.toBinaryString(arrayOfByte[m] & 0xFF));
/* 389:    */      }
/* 390:390 */      System.err.println("---------------");
/* 391:391 */      System.err.println("---------------");
/* 392:392 */      System.err.println("REAL: " + j);
/* 393:393 */      System.err.println("NEW : " + k);
/* 394:    */    }
/* 395:    */  }
/* 396:    */  
/* 399:    */  public Segment getSegment()
/* 400:    */  {
/* 401:401 */    return this.segment;
/* 402:    */  }
/* 403:    */  
/* 406:    */  public org.schema.game.common.controller.SegmentController getSegmentController()
/* 407:    */  {
/* 408:408 */    return this.segment.a();
/* 409:    */  }
/* 410:    */  
/* 411:    */  public byte[] getSegmentPieceData(int paramInt, byte[] paramArrayOfByte) {
/* 412:412 */    int i = 0;
/* 413:413 */    for (int j = paramInt; j < paramInt + 3; j++) {
/* 414:414 */      paramArrayOfByte[(i++)] = this.data[j];
/* 415:    */    }
/* 416:416 */    return paramArrayOfByte;
/* 417:    */  }
/* 418:    */  
/* 422:422 */  public int getSize() { return this.size; }
/* 423:    */  
/* 424:    */  public short getType(byte paramByte1, byte paramByte2, byte paramByte3) {
/* 425:425 */    paramByte1 = getInfoIndex(paramByte1, paramByte2, paramByte3);
/* 426:426 */    return getType(paramByte1);
/* 427:    */  }
/* 428:    */  
/* 429:429 */  public short getType(int paramInt) { int i = this.data[(paramInt + 2)] & 0xFF;
/* 430:430 */    paramInt = this.data[(paramInt + 1)] & 0xFF;
/* 431:    */    
/* 432:432 */    return (short)(i + ((paramInt & 0x7) << 8));
/* 433:    */  }
/* 434:    */  
/* 435:435 */  public void setType(int paramInt, short paramShort) { paramShort *= 3;
/* 436:436 */    this.data[(paramInt + 2)] = org.schema.common.util.ByteUtil.a[(paramShort + 2)]; int 
/* 437:437 */      tmp26_25 = (paramInt + 1); byte[] tmp26_20 = this.data;tmp26_20[tmp26_25] = ((byte)(tmp26_20[tmp26_25] - (this.data[(paramInt + 1)] & 0x1))); int 
/* 438:438 */      tmp48_47 = (paramInt + 1); byte[] tmp48_42 = this.data;tmp48_42[tmp48_47] = ((byte)(tmp48_42[tmp48_47] | org.schema.common.util.ByteUtil.a[(paramShort + 1)]));
/* 439:    */  }
/* 440:    */  
/* 441:    */  public short getType(o paramo) {
/* 442:442 */    return getType(paramo.a, paramo.b, paramo.c);
/* 443:    */  }
/* 444:    */  
/* 448:    */  public boolean isActive(int paramInt)
/* 449:    */  {
/* 450:450 */    if ((this.data[paramInt] & 0x10) == 0) { return true;
/* 451:    */    }
/* 452:452 */    return false;
/* 453:    */  }
/* 454:    */  
/* 456:    */  public boolean isRevalidating()
/* 457:    */  {
/* 458:458 */    return this.revalidating;
/* 459:    */  }
/* 460:    */  
/* 461:461 */  public boolean needsRevalidate() { return this.needsRevalidate; }
/* 462:    */  
/* 463:    */  public boolean neighbors(byte paramByte1, byte paramByte2, byte paramByte3)
/* 464:    */  {
/* 465:465 */    if (!allNeighborsInside(paramByte1, paramByte2, paramByte3)) {
/* 466:466 */      if (contains((byte)(paramByte1 - 1), paramByte2, paramByte3)) return true;
/* 467:467 */      if (contains((byte)(paramByte1 + 1), paramByte2, paramByte3)) return true;
/* 468:468 */      if (contains(paramByte1, (byte)(paramByte2 - 1), paramByte3)) return true;
/* 469:469 */      if (contains(paramByte1, (byte)(paramByte2 + 1), paramByte3)) return true;
/* 470:470 */      if (contains(paramByte1, paramByte2, (byte)(paramByte3 - 1))) return true;
/* 471:471 */      if (contains(paramByte1, paramByte2, (byte)(paramByte3 + 1))) return true;
/* 472:    */    } else {
/* 473:473 */      if (containsUnsave((byte)(paramByte1 - 1), paramByte2, paramByte3)) return true;
/* 474:474 */      if (containsUnsave((byte)(paramByte1 + 1), paramByte2, paramByte3)) return true;
/* 475:475 */      if (containsUnsave(paramByte1, (byte)(paramByte2 - 1), paramByte3)) return true;
/* 476:476 */      if (containsUnsave(paramByte1, (byte)(paramByte2 + 1), paramByte3)) return true;
/* 477:477 */      if (containsUnsave(paramByte1, paramByte2, (byte)(paramByte3 - 1))) return true;
/* 478:478 */      if (containsUnsave(paramByte1, paramByte2, (byte)(paramByte3 + 1))) return true;
/* 479:    */    }
/* 480:480 */    return false;
/* 481:    */  }
/* 482:    */  
/* 483:483 */  public void onAddingElement(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean) { synchronized (this) {
/* 484:484 */      onAddingElementUnsynched(paramInt, paramByte1, paramByte2, paramByte3, paramShort, paramBoolean, true);
/* 485:485 */      return;
/* 486:    */    } }
/* 487:    */  
/* 488:488 */  public void onAddingElementUnsynched(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean1, boolean paramBoolean2) { paramInt = getSize();
/* 489:    */    
/* 490:490 */    setSize(getSize() + 1);
/* 491:491 */    getOctree().insert(paramByte1, paramByte2, paramByte3);
/* 492:    */    try
/* 493:    */    {
/* 494:494 */      getSegmentController().onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, getSegment(), paramBoolean2);
/* 495:495 */    } catch (java.io.IOException localIOException) { 
/* 496:    */      
/* 499:499 */        localIOException;
/* 500:    */    } catch (InterruptedException localInterruptedException) {
/* 501:497 */      
/* 502:    */      
/* 503:499 */        localInterruptedException;
/* 504:    */    }
/* 505:    */    
/* 506:500 */    if (!this.revalidating) {
/* 507:501 */      getSegment().a(true);
/* 508:    */    }
/* 509:    */    
/* 511:505 */    updateBB(paramByte1, paramByte2, paramByte3, paramBoolean1, true);
/* 512:    */  }
/* 513:    */  
/* 514:508 */  public void onRemovingElement(int arg1, byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean) { synchronized (this) {
/* 515:509 */      int i = getSize();
/* 516:    */      
/* 517:511 */      setSize(i - 1);
/* 518:512 */      getOctree().delete(paramByte1, paramByte2, paramByte3);
/* 519:    */      try
/* 520:    */      {
/* 521:515 */        getSegmentController().onRemovedElement(paramShort, i, paramByte1, paramByte2, paramByte3, getSegment(), this.preserveControl);
/* 522:516 */      } catch (java.io.IOException localIOException) { 
/* 523:    */        
/* 526:520 */          localIOException;
/* 527:    */      } catch (InterruptedException localInterruptedException) {
/* 528:518 */        
/* 529:    */        
/* 530:520 */          localInterruptedException;
/* 531:    */      }
/* 532:    */      
/* 533:521 */      if (!this.revalidating) {
/* 534:522 */        getSegment().a(true);
/* 535:    */      }
/* 536:    */      
/* 538:526 */      updateBB(paramByte1, paramByte2, paramByte3, paramBoolean, false);
/* 539:527 */      return;
/* 540:    */    }
/* 541:    */  }
/* 542:    */  
/* 552:    */  public void removeInfoElement(byte paramByte1, byte paramByte2, byte paramByte3)
/* 553:    */  {
/* 554:542 */    setType(getInfoIndex(paramByte1, paramByte2, paramByte3), (short)0);
/* 555:    */  }
/* 556:    */  
/* 566:554 */  public void removeInfoElement(o paramo) { removeInfoElement(paramo.a, paramo.b, paramo.c); }
/* 567:    */  
/* 568:    */  public void reset() {
/* 569:557 */    synchronized (this) {
/* 570:558 */      this.preserveControl = true;
/* 571:559 */      if (getSegment() != null) {
/* 572:560 */        for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
/* 573:561 */          for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
/* 574:562 */            for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1)) {
/* 575:563 */              setInfoElementUnsynched(b3, b2, b1, (short)0, false);
/* 576:    */            }
/* 577:    */          }
/* 578:    */        }
/* 579:567 */        getSegment().b(0);
/* 580:    */      }
/* 581:    */      
/* 585:573 */      this.preserveControl = false;
/* 586:574 */      setSize(0);
/* 587:575 */      this.octree.reset();
/* 588:576 */      resetBB();
/* 589:577 */      return;
/* 590:    */    } }
/* 591:    */  
/* 592:580 */  public void resetFast() { setSize(0);
/* 593:581 */    Arrays.fill(this.data, (byte)0);
/* 594:582 */    setSize(0);
/* 595:583 */    this.octree.reset();
/* 596:584 */    resetBB();
/* 597:585 */    setSize(0);
/* 598:    */  }
/* 599:    */  
/* 600:    */  public static o getPositionFromIndex(short paramShort, o paramo)
/* 601:    */  {
/* 602:590 */    int i = (paramShort = (short)(paramShort / 3)) / 256 % 16;
/* 603:591 */    int j = paramShort % 256 / 16 % 16;
/* 604:592 */    paramShort = paramShort % 256 % 16;
/* 605:593 */    paramo.b((byte)paramShort, (byte)j, (byte)i);
/* 606:594 */    return paramo;
/* 607:    */  }
/* 608:    */  
/* 609:597 */  public void resetBB() { getMax().b((byte)-128, (byte)-128, (byte)-128);
/* 610:598 */    getMin().b((byte)127, (byte)127, (byte)127);
/* 611:    */  }
/* 612:    */  
/* 613:    */  public void restructBB(boolean paramBoolean)
/* 614:    */  {
/* 615:603 */    int i = getMin().a;
/* 616:604 */    int j = getMin().b;
/* 617:605 */    int k = getMin().c;
/* 618:    */    
/* 619:607 */    int m = getMax().a;
/* 620:608 */    int n = getMax().b;
/* 621:609 */    int i1 = getMax().c;
/* 622:    */    
/* 623:611 */    resetBB();
/* 624:    */    
/* 625:613 */    int i2 = 0;
/* 626:    */    
/* 629:617 */    for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
/* 630:618 */      for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
/* 631:619 */        for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1))
/* 632:    */        {
/* 633:621 */          if (containsFast(i2))
/* 634:    */          {
/* 640:628 */            updateBB(b3, b2, b1, false, true);
/* 641:    */          }
/* 642:    */          
/* 643:631 */          i2 += 3;
/* 644:    */        }
/* 645:    */      }
/* 646:    */    }
/* 647:635 */    if ((paramBoolean) && (
/* 648:636 */      (i != getMin().a) || (j != getMin().b) || (k != getMin().c) || (m != getMax().a) || (n != getMax().b) || (i1 != getMax().c)))
/* 649:    */    {
/* 651:639 */      getSegmentController().getSegmentBuffer().a(this);
/* 652:    */    }
/* 653:    */  }
/* 654:    */  
/* 655:    */  private void revalidate(byte paramByte1, byte paramByte2, byte paramByte3, int paramInt)
/* 656:    */  {
/* 657:    */    short s;
/* 658:646 */    if (((s = getType(paramInt)) != 0) && (org.schema.game.common.data.element.ElementKeyMap.exists(s)))
/* 659:    */    {
/* 660:648 */      onAddingElement(paramInt, paramByte1, paramByte2, paramByte3, s, false);return;
/* 661:    */    }
/* 662:650 */    if (s != 0) {
/* 663:651 */      setType(paramInt, (short)0);
/* 664:    */    }
/* 665:    */  }
/* 666:    */  
/* 667:    */  public void revalidateData() {
/* 668:656 */    synchronized (this) {
/* 669:657 */      this.revalidating = true;
/* 670:    */      
/* 675:663 */      assert (getSize() == 0) : (" size is " + getSize() + " in " + getSegment().a + " -> " + getSegmentController());
/* 676:664 */      int i = 0;
/* 677:665 */      for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
/* 678:666 */        for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
/* 679:667 */          for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1)) {
/* 680:668 */            revalidate(b3, b2, b1, i);
/* 681:669 */            i += 3;
/* 682:    */          }
/* 683:    */        }
/* 684:    */      }
/* 685:673 */      getSegmentController().getSegmentBuffer().b(getSegment());
/* 686:    */      
/* 695:683 */      setNeedsRevalidate(false);
/* 696:684 */      this.revalidating = false;
/* 697:685 */      getSegment().a(true);
/* 698:686 */      return;
/* 699:    */    }
/* 700:    */  }
/* 701:    */  
/* 702:    */  public void serialize(DataOutputStream paramDataOutputStream) {
/* 703:691 */    paramDataOutputStream.write(this.data);
/* 704:    */  }
/* 705:    */  
/* 720:708 */  public void setActive(int paramInt, boolean paramBoolean) { org.schema.common.util.ByteUtil.a(this.data, paramBoolean ? 0 : 1, 20, 21, paramInt); }
/* 721:    */  
/* 722:    */  public void setHitpoints(int paramInt, short paramShort) {
/* 723:711 */    assert ((paramShort >= 0) && (paramShort < 512));
/* 724:712 */    org.schema.common.util.ByteUtil.a(this.data, paramShort, 11, 20, paramInt);
/* 725:    */  }
/* 726:    */  
/* 727:715 */  public void setInfoElement(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean) { synchronized (this) {
/* 728:716 */      setInfoElementUnsynched(paramByte1, paramByte2, paramByte3, paramShort, (byte)-1, (byte)-1, paramBoolean);
/* 729:717 */      return;
/* 730:    */    }
/* 731:    */  }
/* 732:    */  
/* 733:721 */  public void setInfoElement(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, byte paramByte4, byte paramByte5, boolean paramBoolean) { synchronized (this) {
/* 734:722 */      setInfoElementUnsynched(paramByte1, paramByte2, paramByte3, paramShort, paramByte4, paramByte5, paramBoolean);
/* 735:723 */      return;
/* 736:    */    }
/* 737:    */  }
/* 738:    */  
/* 739:    */  public void setInfoElement(o paramo, short paramShort, boolean paramBoolean)
/* 740:    */  {
/* 741:729 */    setInfoElement(paramo.a, paramo.b, paramo.c, paramShort, (byte)-1, (byte)-1, paramBoolean);
/* 742:    */  }
/* 743:    */  
/* 744:    */  public void setInfoElement(o paramo, short paramShort, byte paramByte1, byte paramByte2, boolean paramBoolean)
/* 745:    */  {
/* 746:734 */    setInfoElement(paramo.a, paramo.b, paramo.c, paramShort, paramByte1, paramByte2, paramBoolean);
/* 747:    */  }
/* 748:    */  
/* 749:    */  public void setInfoElementUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean) {
/* 750:738 */    setInfoElementUnsynched(paramByte1, paramByte2, paramByte3, paramShort, (byte)-1, (byte)-1, paramBoolean);
/* 751:    */  }
/* 752:    */  
/* 762:    */  public void setInfoElementForcedAddUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean)
/* 763:    */  {
/* 764:752 */    setInfoElementForcedAddUnsynched(paramByte1, paramByte2, paramByte3, paramShort, (byte)-1, (byte)-1, paramBoolean);
/* 765:    */  }
/* 766:    */  
/* 777:    */  public void setInfoElementForcedAddUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, byte paramByte4, byte paramByte5, boolean paramBoolean)
/* 778:    */  {
/* 779:767 */    if (paramShort == 0)
/* 780:    */    {
/* 781:769 */      return;
/* 782:    */    }
/* 783:    */    
/* 784:772 */    int i = getInfoIndex(paramByte1, paramByte2, paramByte3);
/* 785:    */    
/* 787:775 */    setType(i, paramShort);
/* 788:776 */    if (paramByte4 >= 0) {
/* 789:777 */      setOrientation(i, paramByte4);
/* 790:    */    }
/* 791:779 */    if (paramByte5 >= 0) {
/* 792:780 */      setActive(i, paramByte5 != 0);
/* 793:    */    }
/* 794:    */    
/* 795:783 */    onAddingElementUnsynched(i, paramByte1, paramByte2, paramByte3, paramShort, paramBoolean, false);
/* 796:784 */    setHitpoints(i, org.schema.game.common.data.element.ElementKeyMap.getInfo(paramShort).getMaxHitPoints());
/* 797:    */  }
/* 798:    */  
/* 799:787 */  public void setInfoElementUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, byte paramByte4, byte paramByte5, boolean paramBoolean) { int i = getInfoIndex(paramByte1, paramByte2, paramByte3);
/* 800:788 */    short s = getType(i);
/* 801:    */    
/* 802:790 */    setType(i, paramShort);
/* 803:791 */    if (paramByte4 >= 0) {
/* 804:792 */      setOrientation(i, paramByte4);
/* 805:    */    }
/* 806:794 */    if (paramByte5 >= 0) {
/* 807:795 */      setActive(i, paramByte5 != 0);
/* 808:    */    }
/* 809:    */    
/* 810:798 */    assert ((getSegment() != null) && (getSegment().a() == this));
/* 811:    */    
/* 812:800 */    if (paramShort == 0) {
/* 813:801 */      setActive(i, false);
/* 814:802 */      setOrientation(i, (byte)0);
/* 815:    */      
/* 816:804 */      if (s != 0) {
/* 817:805 */        onRemovingElement(i, paramByte1, paramByte2, paramByte3, s, paramBoolean);
/* 818:    */      }
/* 819:    */      
/* 821:    */    }
/* 822:810 */    else if (s == 0) {
/* 823:811 */      onAddingElementUnsynched(i, paramByte1, paramByte2, paramByte3, paramShort, paramBoolean, true);
/* 824:    */      
/* 827:815 */      setHitpoints(i, org.schema.game.common.data.element.ElementKeyMap.getInfo(paramShort).getMaxHitPoints());
/* 828:    */    }
/* 829:    */  }
/* 830:    */  
/* 854:    */  public void setInfoElementUnsynched(o paramo, short paramShort, boolean paramBoolean)
/* 855:    */  {
/* 856:844 */    setInfoElementUnsynched(paramo.a, paramo.b, paramo.c, paramShort, paramBoolean);
/* 857:    */  }
/* 858:    */  
/* 859:    */  public void setNeedsRevalidate(boolean paramBoolean)
/* 860:    */  {
/* 861:849 */    this.needsRevalidate = paramBoolean;
/* 862:    */  }
/* 863:    */  
/* 865:    */  public void setOrientation(int paramInt, byte paramByte)
/* 866:    */  {
/* 867:855 */    assert ((paramByte >= 0) && (paramByte < 8)) : "NOT A SIDE INDEX";
/* 868:856 */    paramByte = org.schema.game.common.data.element.Element.orientationMapping[paramByte];
/* 869:    */    
/* 870:858 */    org.schema.common.util.ByteUtil.a(this.data, paramByte, 21, 24, paramInt);
/* 871:    */    
/* 872:860 */    assert (paramByte == getOrientation(paramInt)) : ("failed orientation coding: " + paramByte + " != result " + getOrientation(paramInt));
/* 873:    */  }
/* 874:    */  
/* 878:866 */  public void setSegment(Segment paramSegment) { this.segment = paramSegment; }
/* 879:    */  
/* 880:    */  public void setSize(int paramInt) {
/* 881:869 */    if ((paramInt < 0) || (paramInt > 4096)) {
/* 882:870 */      System.err.println("Exception WARNING: SEGMENT SIZE WRONG " + paramInt + " " + (this.segment != null ? this.segment.a().getState() + ": " + this.segment.a() + " " + this.segment : ""));
/* 883:    */      try {
/* 884:872 */        throw new IllegalArgumentException();
/* 885:873 */      } catch (Exception localException) { localException;
/* 886:    */      }
/* 887:    */    }
/* 888:    */    
/* 890:878 */    paramInt = Math.max(0, Math.min(paramInt, 4096));
/* 891:879 */    this.size = paramInt;
/* 892:880 */    if (this.segment != null) {
/* 893:881 */      this.segment.b(this.size);
/* 894:    */    }
/* 895:883 */    assert ((paramInt >= 0) && (paramInt <= 4096)) : ("arraySize: " + paramInt + " / 4096");
/* 896:    */  }
/* 897:    */  
/* 898:    */  private void updateBB(byte paramByte1, byte paramByte2, byte paramByte3, boolean paramBoolean1, boolean paramBoolean2) {
/* 899:887 */    if (paramBoolean2) {
/* 900:888 */      if (paramByte1 > getMax().a) {
/* 901:889 */        getMax().a = paramByte1;
/* 902:    */      }
/* 903:891 */      if (paramByte2 > getMax().b) {
/* 904:892 */        getMax().b = paramByte2;
/* 905:    */      }
/* 906:894 */      if (paramByte3 > getMax().c) {
/* 907:895 */        getMax().c = paramByte3;
/* 908:    */      }
/* 909:    */      
/* 911:899 */      if (paramByte1 < getMin().a) {
/* 912:900 */        getMin().a = paramByte1;
/* 913:    */      }
/* 914:902 */      if (paramByte2 < getMin().b) {
/* 915:903 */        getMin().b = paramByte2;
/* 916:    */      }
/* 917:905 */      if (paramByte3 < getMin().c) {
/* 918:906 */        getMin().c = paramByte3;
/* 919:    */      }
/* 920:908 */      if (paramBoolean1) {
/* 921:909 */        getSegmentController().getSegmentBuffer().b(getSegment());
/* 922:    */      }
/* 923:    */    }
/* 924:912 */    else if ((paramByte1 >= getMax().a) || (paramByte2 >= getMax().b) || (paramByte3 >= getMax().c) || (paramByte1 <= getMin().a) || (paramByte2 <= getMin().b) || (paramByte3 <= getMin().c))
/* 925:    */    {
/* 928:916 */      if (paramBoolean1) {
/* 929:917 */        if (vg.f == getSegment().a().getId()) {
/* 930:    */          try {
/* 931:919 */            throw new IllegalArgumentException("RESTRCUTCTION DEBUG");
/* 932:920 */          } catch (Exception localException) { localException;
/* 933:    */          }
/* 934:    */        }
/* 935:    */        
/* 937:925 */        restructBB(paramBoolean1);
/* 938:    */      }
/* 939:    */    }
/* 940:    */  }
/* 941:    */  
/* 947:    */  public void damage(float paramFloat1, q paramq, float paramFloat2)
/* 948:    */  {
/* 949:937 */    synchronized (this) {
/* 950:938 */      int i = 0;
/* 951:939 */      paramFloat2 *= paramFloat2;
/* 952:940 */      resetBB();
/* 953:941 */      int j = (getSegment().a.a(0, 0, 0)) && ((getSegmentController() instanceof kd)) ? 1 : 0;
/* 954:942 */      int m; for (int k = 0; k < 16; m = (byte)(k + 1)) { int i1;
/* 955:943 */        for (int n = 0; n < 16; i1 = (byte)(n + 1)) { int i3;
/* 956:944 */          for (int i2 = 0; i2 < 16; i3 = (byte)(i2 + 1))
/* 957:    */          {
/* 958:946 */            if (getType(i) != 0)
/* 959:    */            {
/* 960:948 */              float f1 = paramq.a - i2;
/* 961:949 */              float f2 = paramq.b - n;
/* 962:950 */              float f3 = paramq.c - k;
/* 963:    */              
/* 966:954 */              if ((f1 = f1 * f1 + f2 * f2 + f3 * f3) < paramFloat2) {
/* 967:955 */                f1 = (1.0F - Math.min(1.0F, f1 / paramFloat2)) * paramFloat1;
/* 968:    */                
/* 970:    */                int i4;
/* 971:    */                
/* 972:960 */                if ((i4 = getHitpoints(i)) - f1 <= 0.0F) {
/* 973:961 */                  setHitpoints(i, (short)0);
/* 974:962 */                  if ((j != 0) && (i2 == 8) && (n == 8) && (k == 8)) {
/* 975:963 */                    System.err.println("[HIT-SEGMENTDATA] Core Destroyed");
/* 976:    */                  } else {
/* 977:965 */                    setInfoElementUnsynched(i2, n, k, (short)0, false);
/* 978:    */                  }
/* 979:    */                } else {
/* 980:968 */                  setHitpoints(i, (short)(int)(i4 - f1));
/* 981:969 */                } } else { updateBB(i2, n, k, false, true);
/* 982:    */              }
/* 983:    */            }
/* 984:    */            
/* 988:976 */            assert ((getType(i) == 0) || (org.schema.game.common.data.element.ElementKeyMap.getInfo(getType(i)) != null));
/* 989:    */            
/* 990:978 */            i += 3;
/* 991:    */          }
/* 992:    */        }
/* 993:    */      }
/* 994:    */      
/* 996:984 */      getSegmentController().getSegmentBuffer().b(getSegment());
/* 997:985 */      return;
/* 998:    */    } }
/* 999:    */  
/* 1000:988 */  public boolean checkEmpty() { for (int i = 0; i < this.data.length; i += 3) {
/* 1001:989 */      if (getType(i) != 0) {
/* 1002:990 */        return false;
/* 1003:    */      }
/* 1004:    */    }
/* 1005:    */    
/* 1006:994 */    return true;
/* 1007:    */  }
/* 1008:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.SegmentData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */