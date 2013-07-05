/*     */ package org.schema.game.common.data.world;
/*     */ 
/*     */ import jL;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Arrays;
/*     */ import kd;
/*     */ import o;
/*     */ import org.schema.common.util.ByteUtil;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ import org.schema.game.common.data.physics.octree.ArrayOctree;
/*     */ import org.schema.schine.network.StateInterface;
/*     */ import q;
/*     */ import vg;
/*     */ 
/*     */ public class SegmentData
/*     */ {
/*     */   private Segment segment;
/*     */   private byte[] data;
/*     */   public static final int lightBlockSize = 39;
/*     */   public static final int typeIndexStart = 0;
/*     */   public static final int typeIndexEnd = 11;
/*     */   public static final int hitpointsIndexStart = 11;
/*     */   public static final int hitpointsIndexEnd = 20;
/*     */   public static final int activeIndexStart = 20;
/*     */   public static final int activeIndexEnd = 21;
/*     */   public static final int orientationStart = 21;
/*     */   public static final int orientationEnd = 24;
/*     */   public static final int blockSize = 3;
/*     */   public static final byte ACTIVE_BIT = 16;
/*     */   public static final int vis = 12;
/*     */   public static final int BLOCK_COUNT = 4096;
/*     */   public static final int TOTAL_SIZE = 12288;
/*     */   public static final int TOTAL_SIZE_LIGHT = 159744;
/*     */   public static final int SEG_TIMES_SEG_TIMES_SEG = 4096;
/*     */   public static final int SEG_TIMES_SEG = 256;
/*     */   public static final int SEG = 16;
/*     */   private static final int SEG_MINUS_ONE = 15;
/*     */   public static final int t = 255;
/*     */   public static final byte ANTI_BYTE = -16;
/*     */   private int size;
/* 135 */   private final o min = new o();
/* 136 */   private final o max = new o();
/*     */   private final ArrayOctree octree;
/* 139 */   private final o helperPos = new o();
/*     */   private boolean preserveControl;
/*     */   private static final int MASK = 255;
/* 143 */   private boolean needsRevalidate = false;
/*     */   private boolean revalidating;
/*     */   public static final int PIECE_ADDED = 0;
/*     */   public static final int PIECE_REMOVED = 1;
/*     */   public static final int PIECE_CHANGED = 2;
/*     */   public static final int PIECE_UNCHANGED = 3;
/*     */   public static final int PIECE_ACTIVE_CHANGED = 4;
/*     */ 
/*     */   public static float byteArrayToFloat(byte[] paramArrayOfByte)
/*     */   {
/*  27 */     int i = 0;
/*  28 */     int j = 0;
/*  29 */     for (int k = 3; k >= 0; k--) {
/*  30 */       i |= (paramArrayOfByte[j] & 0xFF) << (k << 3);
/*  31 */       j++;
/*     */     }
/*     */ 
/*  34 */     return Float.intBitsToFloat(i);
/*     */   }
/*     */ 
/*     */   public static byte[] floatToByteArray(float paramFloat)
/*     */   {
/*  43 */     return intToByteArray(Float.floatToRawIntBits(paramFloat));
/*     */   }
/*     */ 
/*     */   public static byte[] intToByteArray(int paramInt)
/*     */   {
/*  58 */     byte[] arrayOfByte = new byte[4];
/*  59 */     for (int i = 0; i < 4; i++) {
/*  60 */       int j = arrayOfByte.length - 1 - i << 3;
/*  61 */       arrayOfByte[i] = ((byte)(paramInt >>> j));
/*     */     }
/*  63 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   public static boolean allNeighborsInside(byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/* 109 */     return (paramByte1 < 15) && (paramByte2 < 15) && (paramByte3 < 15) && (paramByte1 > 0) && (paramByte2 > 0) && (paramByte3 > 0);
/*     */   }
/*     */ 
/*     */   public static int getInfoIndex(byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/* 116 */     return 3 * ((paramByte3 << 8) + (paramByte2 << 4) + paramByte1);
/*     */   }
/*     */ 
/*     */   public static int getInfoIndex(o paramo)
/*     */   {
/* 121 */     return getInfoIndex(paramo.a, paramo.b, paramo.c);
/*     */   }
/*     */   public static int getLightInfoIndexFromIndex(int paramInt) {
/* 124 */     return paramInt / 3 * 3 * 24;
/*     */   }
/*     */ 
/*     */   public static boolean valid(byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/* 129 */     return ((paramByte1 | paramByte2 | paramByte3) & 0xFFFFFFF0) == 0;
/*     */   }
/*     */ 
/*     */   public SegmentData(boolean paramBoolean)
/*     */   {
/* 154 */     this.octree = new ArrayOctree(!paramBoolean);
/*     */ 
/* 156 */     this.data = new byte[12288];
/*     */ 
/* 158 */     resetBB();
/*     */   }
/*     */ 
/*     */   public int applySegmentData(o paramo, byte[] paramArrayOfByte)
/*     */   {
/* 169 */     synchronized (this) {
/* 170 */       int i = getInfoIndex(paramo);
/* 171 */       int j = 0;
/* 172 */       boolean bool = isActive(i);
/* 173 */       int k = getType(i);
/* 174 */       byte[] arrayOfByte = getHitpoints(i);
/* 175 */       for (int m = i; m < i + 3; m++) {
/* 176 */         this.data[m] = paramArrayOfByte[(j++)];
/*     */       }
/*     */ 
/* 179 */       m = getType(i);
/* 180 */       paramArrayOfByte = getHitpoints(i);
/*     */ 
/* 183 */       if (m != k)
/*     */       {
/* 186 */         if ((k == 0) && (m != 0)) {
/* 187 */           onAddingElement(i, paramo.a, paramo.b, paramo.c, m, true);
/* 188 */           return 0;
/*     */         }
/* 190 */         if ((k != 0) && (m == 0)) {
/* 191 */           onRemovingElement(i, paramo.a, paramo.b, paramo.c, k, true);
/* 192 */           return 1;
/*     */         }
/* 194 */         return 2;
/*     */       }
/*     */ 
/* 197 */       if (arrayOfByte != paramArrayOfByte) {
/* 198 */         return 2;
/*     */       }
/* 200 */       if (bool != isActive(i)) {
/* 201 */         return 4;
/*     */       }
/* 203 */       return 3;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int arraySize()
/*     */   {
/* 214 */     return this.data.length;
/*     */   }
/*     */   public void assignData(Segment paramSegment) {
/* 217 */     paramSegment.a(this);
/* 218 */     setSegment(paramSegment);
/* 219 */     resetBB();
/*     */   }
/*     */ 
/*     */   public boolean contains(byte paramByte1, byte paramByte2, byte paramByte3) {
/* 223 */     if (valid(paramByte1, paramByte2, paramByte3)) {
/* 224 */       return containsUnsave(paramByte1, paramByte2, paramByte3);
/*     */     }
/* 226 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean contains(int paramInt) {
/* 230 */     return getType(paramInt) != 0;
/*     */   }
/*     */ 
/*     */   public boolean contains(o paramo)
/*     */   {
/* 251 */     return contains(paramo.a, paramo.b, paramo.c);
/*     */   }
/*     */ 
/*     */   public boolean containsFast(int paramInt) {
/* 255 */     int i = this.data[(paramInt + 2)] & 0xFF;
/* 256 */     paramInt = this.data[(paramInt + 1)] & 0xFF;
/* 257 */     return (i != 0) || ((paramInt & 0x7) != 0);
/*     */   }
/*     */ 
/*     */   public boolean containsFast(o paramo) {
/* 261 */     return containsFast(getInfoIndex(paramo));
/*     */   }
/*     */   public boolean containsUnblended(byte paramByte1, byte paramByte2, byte paramByte3) {
/* 264 */     if (valid(paramByte1, paramByte2, paramByte3))
/*     */     {
/* 266 */       return ((
/* 266 */         paramByte1 = getType(paramByte1, paramByte2, paramByte3)) != 0) && 
/* 266 */         (!ElementKeyMap.getInfo(paramByte1).isBlended());
/*     */     }
/*     */ 
/* 269 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean containsUnblended(o paramo)
/*     */   {
/* 275 */     return containsUnblended(paramo.a, paramo.b, paramo.c);
/*     */   }
/*     */   public boolean containsUnsave(byte paramByte1, byte paramByte2, byte paramByte3) {
/* 278 */     return containsFast(getInfoIndex(paramByte1, paramByte2, paramByte3));
/*     */   }
/*     */   public boolean containsUnsave(int paramInt) {
/* 281 */     return getType(paramInt) != 0;
/*     */   }
/*     */   public void createFromByteBuffer(byte[] paramArrayOfByte, StateInterface arg2) {
/* 284 */     paramArrayOfByte = ByteBuffer.wrap(paramArrayOfByte);
/* 285 */     synchronized (this) {
/* 286 */       if (this.data == null) {
/* 287 */         this.data = new byte[12288];
/*     */       }
/* 289 */       for (int i = 0; i < this.data.length; i++)
/* 290 */         this.data[i] = paramArrayOfByte.get(); return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void deSerialize(DataInputStream paramDataInputStream) {
/* 295 */     synchronized (this) {
/* 296 */       reset();
/* 297 */       paramDataInputStream.readFully(this.data);
/* 298 */       setNeedsRevalidate(true);
/* 299 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte[] getAsBuffer()
/*     */   {
/* 322 */     return this.data;
/*     */   }
/*     */ 
/*     */   public short getHitpoints(int paramInt) {
/* 326 */     return (short)ByteUtil.a(ByteUtil.a(this.data, paramInt), 11, 20);
/*     */   }
/*     */   public o getMax() {
/* 329 */     return this.max;
/*     */   }
/*     */   public o getMin() {
/* 332 */     return this.min;
/*     */   }
/*     */ 
/*     */   public ArrayOctree getOctree()
/*     */   {
/* 363 */     return this.octree;
/*     */   }
/*     */   public byte getOrientation(int paramInt) {
/* 366 */     return (byte)ByteUtil.a(ByteUtil.a(this.data, paramInt), 21, 24);
/*     */   }
/*     */ 
/*     */   public Segment getSegment()
/*     */   {
/* 372 */     return this.segment;
/*     */   }
/*     */ 
/*     */   public SegmentController getSegmentController()
/*     */   {
/* 379 */     return this.segment.a();
/*     */   }
/*     */ 
/*     */   public byte[] getSegmentPieceData(int paramInt, byte[] paramArrayOfByte) {
/* 383 */     int i = 0;
/* 384 */     for (int j = paramInt; j < paramInt + 3; j++) {
/* 385 */       paramArrayOfByte[(i++)] = this.data[j];
/*     */     }
/* 387 */     return paramArrayOfByte;
/*     */   }
/*     */ 
/*     */   public int getSize()
/*     */   {
/* 393 */     return this.size;
/*     */   }
/*     */   public short getType(byte paramByte1, byte paramByte2, byte paramByte3) {
/* 396 */     paramByte1 = getInfoIndex(paramByte1, paramByte2, paramByte3);
/* 397 */     return getType(paramByte1);
/*     */   }
/*     */   public short getType(int paramInt) {
/* 400 */     int i = this.data[(paramInt + 2)] & 0xFF;
/* 401 */     paramInt = this.data[(paramInt + 1)] & 0xFF;
/*     */ 
/* 403 */     return (short)(i + ((paramInt & 0x7) << 8));
/*     */   }
/*     */ 
/*     */   public void setType(int paramInt, short paramShort) {
/* 406 */     paramShort *= 3;
/* 407 */     this.data[(paramInt + 2)] = ByteUtil.a[(paramShort + 2)];
/*     */     int tmp26_25 = (paramInt + 1);
/*     */     byte[] tmp26_20 = this.data; tmp26_20[tmp26_25] = ((byte)(tmp26_20[tmp26_25] - (this.data[(paramInt + 1)] & 0x7)));
/*     */     int tmp49_48 = (paramInt + 1);
/*     */     byte[] tmp49_43 = this.data; tmp49_43[tmp49_48] = ((byte)(tmp49_43[tmp49_48] | ByteUtil.a[(paramShort + 1)]));
/*     */   }
/*     */ 
/*     */   public short getType(o paramo) {
/* 413 */     return getType(paramo.a, paramo.b, paramo.c);
/*     */   }
/*     */ 
/*     */   public boolean isActive(int paramInt)
/*     */   {
/* 421 */     if ((this.data[paramInt] & 0x10) == 0) return true;
/*     */ 
/* 423 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isRevalidating()
/*     */   {
/* 429 */     return this.revalidating;
/*     */   }
/*     */   public boolean needsRevalidate() {
/* 432 */     return this.needsRevalidate;
/*     */   }
/*     */ 
/*     */   public boolean neighbors(byte paramByte1, byte paramByte2, byte paramByte3) {
/* 436 */     if (!allNeighborsInside(paramByte1, paramByte2, paramByte3)) {
/* 437 */       if (contains((byte)(paramByte1 - 1), paramByte2, paramByte3)) return true;
/* 438 */       if (contains((byte)(paramByte1 + 1), paramByte2, paramByte3)) return true;
/* 439 */       if (contains(paramByte1, (byte)(paramByte2 - 1), paramByte3)) return true;
/* 440 */       if (contains(paramByte1, (byte)(paramByte2 + 1), paramByte3)) return true;
/* 441 */       if (contains(paramByte1, paramByte2, (byte)(paramByte3 - 1))) return true;
/* 442 */       if (contains(paramByte1, paramByte2, (byte)(paramByte3 + 1))) return true; 
/*     */     }
/* 444 */     else { if (containsUnsave((byte)(paramByte1 - 1), paramByte2, paramByte3)) return true;
/* 445 */       if (containsUnsave((byte)(paramByte1 + 1), paramByte2, paramByte3)) return true;
/* 446 */       if (containsUnsave(paramByte1, (byte)(paramByte2 - 1), paramByte3)) return true;
/* 447 */       if (containsUnsave(paramByte1, (byte)(paramByte2 + 1), paramByte3)) return true;
/* 448 */       if (containsUnsave(paramByte1, paramByte2, (byte)(paramByte3 - 1))) return true;
/* 449 */       if (containsUnsave(paramByte1, paramByte2, (byte)(paramByte3 + 1))) return true;
/*     */     }
/* 451 */     return false;
/*     */   }
/*     */   public void onAddingElement(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean) {
/* 454 */     synchronized (this) {
/* 455 */       onAddingElementUnsynched(paramInt, paramByte1, paramByte2, paramByte3, paramShort, paramBoolean, true);
/* 456 */       return;
/*     */     }
/*     */   }
/* 459 */   public void onAddingElementUnsynched(int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean1, boolean paramBoolean2) { paramInt = getSize();
/*     */ 
/* 461 */     setSize(getSize() + 1);
/* 462 */     getOctree().insert(paramByte1, paramByte2, paramByte3);
/*     */     try
/*     */     {
/* 465 */       getSegmentController().onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, getSegment(), paramBoolean2);
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 470 */       localIOException.printStackTrace();
/*     */     }
/*     */     catch (InterruptedException localInterruptedException)
/*     */     {
/* 470 */       localInterruptedException.printStackTrace();
/*     */     }
/*     */ 
/* 471 */     if (!this.revalidating) {
/* 472 */       getSegment().a(true);
/*     */     }
/*     */ 
/* 476 */     updateBB(paramByte1, paramByte2, paramByte3, paramBoolean1, true); }
/*     */ 
/*     */   public void onRemovingElement(int arg1, byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean) {
/* 479 */     synchronized (this) {
/* 480 */       int i = getSize();
/*     */ 
/* 482 */       setSize(i - 1);
/* 483 */       getOctree().delete(paramByte1, paramByte2, paramByte3);
/*     */       try
/*     */       {
/* 486 */         getSegmentController().onRemovedElement(paramShort, i, paramByte1, paramByte2, paramByte3, getSegment(), this.preserveControl);
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/* 491 */         localIOException.printStackTrace();
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/* 491 */         localInterruptedException.printStackTrace();
/*     */       }
/*     */ 
/* 492 */       if (!this.revalidating) {
/* 493 */         getSegment().a(true);
/*     */       }
/*     */ 
/* 497 */       updateBB(paramByte1, paramByte2, paramByte3, paramBoolean, false);
/* 498 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removeInfoElement(byte paramByte1, byte paramByte2, byte paramByte3)
/*     */   {
/* 513 */     setType(getInfoIndex(paramByte1, paramByte2, paramByte3), (short)0);
/*     */   }
/*     */ 
/*     */   public void removeInfoElement(o paramo)
/*     */   {
/* 525 */     removeInfoElement(paramo.a, paramo.b, paramo.c);
/*     */   }
/*     */   public void reset() {
/* 528 */     synchronized (this) {
/* 529 */       this.preserveControl = true;
/* 530 */       if (getSegment() != null) {
/* 531 */         for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
/* 532 */           for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
/* 533 */             for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1)) {
/* 534 */               setInfoElementUnsynched(b3, b2, b1, (short)0, false);
/*     */             }
/*     */           }
/*     */         }
/* 538 */         getSegment().b(0);
/*     */       }
/*     */ 
/* 544 */       this.preserveControl = false;
/* 545 */       setSize(0);
/* 546 */       this.octree.reset();
/* 547 */       resetBB();
/* 548 */       return;
/*     */     }
/*     */   }
/* 551 */   public void resetFast() { setSize(0);
/* 552 */     Arrays.fill(this.data, (byte)0);
/* 553 */     setSize(0);
/* 554 */     this.octree.reset();
/* 555 */     resetBB();
/* 556 */     setSize(0);
/*     */   }
/*     */ 
/*     */   public static o getPositionFromIndex(short paramShort, o paramo)
/*     */   {
/* 561 */     int i = (
/* 561 */       paramShort = (short)(paramShort / 3)) / 
/* 561 */       256 % 16;
/* 562 */     int j = paramShort % 256 / 16 % 16;
/* 563 */     paramShort = paramShort % 256 % 16;
/* 564 */     paramo.b((byte)paramShort, (byte)j, (byte)i);
/* 565 */     return paramo;
/*     */   }
/*     */   public void resetBB() {
/* 568 */     getMax().b((byte)-128, (byte)-128, (byte)-128);
/* 569 */     getMin().b((byte)127, (byte)127, (byte)127);
/*     */   }
/*     */ 
/*     */   public void restructBB(boolean paramBoolean)
/*     */   {
/* 574 */     int i = getMin().a;
/* 575 */     int j = getMin().b;
/* 576 */     int k = getMin().c;
/*     */ 
/* 578 */     int m = getMax().a;
/* 579 */     int n = getMax().b;
/* 580 */     int i1 = getMax().c;
/*     */ 
/* 582 */     resetBB();
/*     */ 
/* 584 */     int i2 = 0;
/*     */ 
/* 588 */     for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
/* 589 */       for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
/* 590 */         for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1))
/*     */         {
/* 592 */           if (containsFast(i2))
/*     */           {
/* 599 */             updateBB(b3, b2, b1, false, true);
/*     */           }
/*     */ 
/* 602 */           i2 += 3;
/*     */         }
/*     */       }
/*     */     }
/* 606 */     if ((paramBoolean) && (
/* 607 */       (i != getMin().a) || (j != getMin().b) || (k != getMin().c) || (m != getMax().a) || (n != getMax().b) || (i1 != getMax().c)))
/*     */     {
/* 610 */       getSegmentController().getSegmentBuffer().a(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void revalidate(byte paramByte1, byte paramByte2, byte paramByte3, int paramInt)
/*     */   {
/*     */     short s;
/* 617 */     if (((
/* 617 */       s = getType(paramInt)) != 0) && 
/* 617 */       (ElementKeyMap.exists(s)))
/*     */     {
/* 619 */       onAddingElement(paramInt, paramByte1, paramByte2, paramByte3, s, false);
/*     */     }
/*     */   }
/*     */ 
/* 623 */   public void revalidateData() { synchronized (this) {
/* 624 */       this.revalidating = true;
/*     */ 
/* 630 */       assert (getSize() == 0) : (" size is " + getSize() + " in " + getSegment().a + " -> " + getSegmentController());
/* 631 */       int i = 0;
/* 632 */       for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
/* 633 */         for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
/* 634 */           for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1)) {
/* 635 */             revalidate(b3, b2, b1, i);
/* 636 */             i += 3;
/*     */           }
/*     */         }
/*     */       }
/* 640 */       getSegmentController().getSegmentBuffer().b(getSegment());
/*     */ 
/* 650 */       setNeedsRevalidate(false);
/* 651 */       this.revalidating = false;
/* 652 */       getSegment().a(true);
/* 653 */       return;
/*     */     } }
/*     */ 
/*     */   public void serialize(DataOutputStream paramDataOutputStream)
/*     */   {
/* 658 */     paramDataOutputStream.write(this.data);
/*     */   }
/*     */ 
/*     */   public void setActive(int paramInt, boolean paramBoolean)
/*     */   {
/* 675 */     ByteUtil.a(this.data, paramBoolean ? 0 : 1, 20, 21, paramInt);
/*     */   }
/*     */   public void setHitpoints(int paramInt, short paramShort) {
/* 678 */     assert ((paramShort >= 0) && (paramShort < 512));
/* 679 */     ByteUtil.a(this.data, paramShort, 11, 20, paramInt);
/*     */   }
/*     */   public void setInfoElement(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean) {
/* 682 */     synchronized (this) {
/* 683 */       setInfoElementUnsynched(paramByte1, paramByte2, paramByte3, paramShort, (byte)-1, (byte)-1, paramBoolean);
/* 684 */       return;
/*     */     }
/*     */   }
/*     */ 
/* 688 */   public void setInfoElement(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, byte paramByte4, byte paramByte5, boolean paramBoolean) { synchronized (this) {
/* 689 */       setInfoElementUnsynched(paramByte1, paramByte2, paramByte3, paramShort, paramByte4, paramByte5, paramBoolean);
/* 690 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setInfoElement(o paramo, short paramShort, boolean paramBoolean)
/*     */   {
/* 696 */     setInfoElement(paramo.a, paramo.b, paramo.c, paramShort, (byte)-1, (byte)-1, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void setInfoElement(o paramo, short paramShort, byte paramByte1, byte paramByte2, boolean paramBoolean)
/*     */   {
/* 701 */     setInfoElement(paramo.a, paramo.b, paramo.c, paramShort, paramByte1, paramByte2, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void setInfoElementUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean) {
/* 705 */     setInfoElementUnsynched(paramByte1, paramByte2, paramByte3, paramShort, (byte)-1, (byte)-1, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void setInfoElementForcedAddUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, boolean paramBoolean)
/*     */   {
/* 719 */     setInfoElementForcedAddUnsynched(paramByte1, paramByte2, paramByte3, paramShort, (byte)-1, (byte)-1, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void setInfoElementForcedAddUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, byte paramByte4, byte paramByte5, boolean paramBoolean)
/*     */   {
/* 734 */     if (paramShort == 0)
/*     */     {
/* 736 */       return;
/*     */     }
/*     */ 
/* 739 */     int i = getInfoIndex(paramByte1, paramByte2, paramByte3);
/*     */ 
/* 742 */     setType(i, paramShort);
/* 743 */     if (paramByte4 >= 0) {
/* 744 */       setOrientation(i, paramByte4);
/*     */     }
/* 746 */     if (paramByte5 >= 0) {
/* 747 */       setActive(i, paramByte5 != 0);
/*     */     }
/*     */ 
/* 750 */     onAddingElementUnsynched(i, paramByte1, paramByte2, paramByte3, paramShort, paramBoolean, false);
/* 751 */     setHitpoints(i, ElementKeyMap.getInfo(paramShort).getMaxHitPoints());
/*     */   }
/*     */   public void setInfoElementUnsynched(byte paramByte1, byte paramByte2, byte paramByte3, short paramShort, byte paramByte4, byte paramByte5, boolean paramBoolean) {
/* 754 */     int i = getInfoIndex(paramByte1, paramByte2, paramByte3);
/* 755 */     short s = getType(i);
/*     */ 
/* 757 */     setType(i, paramShort);
/* 758 */     if (paramByte4 >= 0) {
/* 759 */       setOrientation(i, paramByte4);
/*     */     }
/* 761 */     if (paramByte5 >= 0) {
/* 762 */       setActive(i, paramByte5 != 0);
/*     */     }
/*     */ 
/* 765 */     assert ((getSegment() != null) && (getSegment().a() == this));
/*     */ 
/* 767 */     if (paramShort == 0) {
/* 768 */       setActive(i, false);
/* 769 */       setOrientation(i, (byte)0);
/*     */ 
/* 771 */       if (s != 0) {
/* 772 */         onRemovingElement(i, paramByte1, paramByte2, paramByte3, s, paramBoolean);
/*     */       }
/*     */ 
/*     */     }
/* 777 */     else if (s == 0) {
/* 778 */       onAddingElementUnsynched(i, paramByte1, paramByte2, paramByte3, paramShort, paramBoolean, true);
/*     */ 
/* 782 */       setHitpoints(i, ElementKeyMap.getInfo(paramShort).getMaxHitPoints());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setInfoElementUnsynched(o paramo, short paramShort, boolean paramBoolean)
/*     */   {
/* 811 */     setInfoElementUnsynched(paramo.a, paramo.b, paramo.c, paramShort, paramBoolean);
/*     */   }
/*     */ 
/*     */   public void setNeedsRevalidate(boolean paramBoolean)
/*     */   {
/* 816 */     this.needsRevalidate = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setOrientation(int paramInt, byte paramByte)
/*     */   {
/* 822 */     assert ((paramByte >= 0) && (paramByte < 8)) : "NOT A SIDE INDEX";
/* 823 */     paramByte = org.schema.game.common.data.element.Element.orientationMapping[paramByte];
/*     */ 
/* 825 */     ByteUtil.a(this.data, paramByte, 21, 24, paramInt);
/*     */ 
/* 827 */     assert (paramByte == getOrientation(paramInt)) : ("failed orientation coding: " + paramByte + " != result " + getOrientation(paramInt));
/*     */   }
/*     */ 
/*     */   public void setSegment(Segment paramSegment)
/*     */   {
/* 833 */     this.segment = paramSegment;
/*     */   }
/*     */   public void setSize(int paramInt) {
/* 836 */     if ((paramInt < 0) || (paramInt > 4096)) {
/* 837 */       System.err.println("Exception WARNING: SEGMENT SIZE WRONG " + paramInt + " " + (this.segment != null ? this.segment.a().getState() + ": " + this.segment.a() + " " + this.segment : ""));
/*     */       try {
/* 839 */         throw new IllegalArgumentException(); } catch (Exception localException) { localException
/* 840 */           .printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 845 */     paramInt = Math.max(0, Math.min(paramInt, 4096));
/* 846 */     this.size = paramInt;
/* 847 */     if (this.segment != null) {
/* 848 */       this.segment.b(this.size);
/*     */     }
/* 850 */     assert ((paramInt >= 0) && (paramInt <= 4096)) : ("arraySize: " + paramInt + " / 4096");
/*     */   }
/*     */ 
/*     */   private void updateBB(byte paramByte1, byte paramByte2, byte paramByte3, boolean paramBoolean1, boolean paramBoolean2) {
/* 854 */     if (paramBoolean2) {
/* 855 */       if (paramByte1 > getMax().a) {
/* 856 */         getMax().a = paramByte1;
/*     */       }
/* 858 */       if (paramByte2 > getMax().b) {
/* 859 */         getMax().b = paramByte2;
/*     */       }
/* 861 */       if (paramByte3 > getMax().c) {
/* 862 */         getMax().c = paramByte3;
/*     */       }
/*     */ 
/* 866 */       if (paramByte1 < getMin().a) {
/* 867 */         getMin().a = paramByte1;
/*     */       }
/* 869 */       if (paramByte2 < getMin().b) {
/* 870 */         getMin().b = paramByte2;
/*     */       }
/* 872 */       if (paramByte3 < getMin().c) {
/* 873 */         getMin().c = paramByte3;
/*     */       }
/* 875 */       if (paramBoolean1) {
/* 876 */         getSegmentController().getSegmentBuffer().b(getSegment());
/*     */       }
/*     */     }
/* 879 */     else if ((paramByte1 >= getMax().a) || (paramByte2 >= getMax().b) || (paramByte3 >= getMax().c) || (paramByte1 <= getMin().a) || (paramByte2 <= getMin().b) || (paramByte3 <= getMin().c))
/*     */     {
/* 883 */       if (paramBoolean1) {
/* 884 */         if (vg.f == getSegment().a().getId()) {
/*     */           try {
/* 886 */             throw new IllegalArgumentException("RESTRCUTCTION DEBUG"); } catch (Exception localException) { localException
/* 887 */               .printStackTrace();
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 892 */         restructBB(paramBoolean1);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void damage(float paramFloat1, q paramq, float paramFloat2)
/*     */   {
/* 904 */     synchronized (this) {
/* 905 */       int i = 0;
/* 906 */       paramFloat2 *= paramFloat2;
/* 907 */       resetBB();
/* 908 */       int j = (getSegment().a.a(0, 0, 0)) && ((getSegmentController() instanceof kd)) ? 1 : 0;
/*     */       int m;
/* 909 */       for (int k = 0; k < 16; m = (byte)(k + 1))
/*     */       {
/*     */         int i1;
/* 910 */         for (int n = 0; n < 16; i1 = (byte)(n + 1))
/*     */         {
/*     */           int i3;
/* 911 */           for (int i2 = 0; i2 < 16; i3 = (byte)(i2 + 1))
/*     */           {
/* 913 */             if (getType(i) != 0)
/*     */             {
/* 915 */               float f1 = paramq.a - i2;
/* 916 */               float f2 = paramq.b - n;
/* 917 */               float f3 = paramq.c - k;
/*     */ 
/* 921 */               if ((
/* 921 */                 f1 = f1 * f1 + f2 * f2 + f3 * f3) < 
/* 921 */                 paramFloat2) {
/* 922 */                 f1 = (1.0F - Math.min(1.0F, f1 / paramFloat2)) * paramFloat1;
/*     */                 int i4;
/* 927 */                 if ((i4 = getHitpoints(i)) - 
/* 927 */                   f1 <= 0.0F) {
/* 928 */                   setHitpoints(i, (short)0);
/* 929 */                   if ((j != 0) && (i2 == 8) && (n == 8) && (k == 8))
/* 930 */                     System.err.println("[HIT-SEGMENTDATA] Core Destroyed");
/*     */                   else
/* 932 */                     setInfoElementUnsynched(i2, n, k, (short)0, false);
/*     */                 }
/*     */                 else {
/* 935 */                   setHitpoints(i, (short)(int)(i4 - f1));
/*     */                 } } else { updateBB(i2, n, k, false, true); }
/*     */ 
/*     */ 
/*     */             }
/*     */ 
/* 943 */             assert ((getType(i) == 0) || (ElementKeyMap.getInfo(getType(i)) != null));
/*     */ 
/* 945 */             i += 3;
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 951 */       getSegmentController().getSegmentBuffer().b(getSegment());
/* 952 */       return;
/*     */     }
/*     */   }
/* 955 */   public boolean checkEmpty() { for (int i = 0; i < this.data.length; i += 3) {
/* 956 */       if (getType(i) != 0) {
/* 957 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 961 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.SegmentData
 * JD-Core Version:    0.6.2
 */