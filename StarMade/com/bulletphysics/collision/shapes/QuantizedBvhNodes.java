/*     */ package com.bulletphysics.collision.shapes;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class QuantizedBvhNodes
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final int STRIDE = 4;
/*     */   private int[] buf;
/*  51 */   private int size = 0;
/*     */ 
/*     */   public QuantizedBvhNodes() {
/*  54 */     resize(16);
/*     */   }
/*     */ 
/*     */   public int add() {
/*  58 */     while (this.size + 1 >= capacity()) {
/*  59 */       resize(capacity() * 2);
/*     */     }
/*  61 */     return this.size++;
/*     */   }
/*     */ 
/*     */   public int size() {
/*  65 */     return this.size;
/*     */   }
/*     */ 
/*     */   public int capacity() {
/*  69 */     return this.buf.length / 4;
/*     */   }
/*     */ 
/*     */   public void clear() {
/*  73 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public void resize(int num) {
/*  77 */     int[] oldBuf = this.buf;
/*     */ 
/*  79 */     this.buf = new int[num * 4];
/*  80 */     if (oldBuf != null)
/*  81 */       System.arraycopy(oldBuf, 0, this.buf, 0, Math.min(oldBuf.length, this.buf.length));
/*     */   }
/*     */ 
/*     */   public static int getNodeSize()
/*     */   {
/*  86 */     return 16;
/*     */   }
/*     */ 
/*     */   public void set(int destId, QuantizedBvhNodes srcNodes, int srcId)
/*     */   {
/*  93 */     int[] buf = this.buf;
/*  94 */     int[] srcBuf = srcNodes.buf;
/*     */ 
/*  96 */     buf[(destId * 4 + 0)] = srcBuf[(srcId * 4 + 0)];
/*  97 */     buf[(destId * 4 + 1)] = srcBuf[(srcId * 4 + 1)];
/*  98 */     buf[(destId * 4 + 2)] = srcBuf[(srcId * 4 + 2)];
/*  99 */     buf[(destId * 4 + 3)] = srcBuf[(srcId * 4 + 3)];
/*     */   }
/*     */ 
/*     */   public void swap(int id1, int id2)
/*     */   {
/* 106 */     int[] buf = this.buf;
/*     */ 
/* 108 */     int temp0 = buf[(id1 * 4 + 0)];
/* 109 */     int temp1 = buf[(id1 * 4 + 1)];
/* 110 */     int temp2 = buf[(id1 * 4 + 2)];
/* 111 */     int temp3 = buf[(id1 * 4 + 3)];
/*     */ 
/* 113 */     buf[(id1 * 4 + 0)] = buf[(id2 * 4 + 0)];
/* 114 */     buf[(id1 * 4 + 1)] = buf[(id2 * 4 + 1)];
/* 115 */     buf[(id1 * 4 + 2)] = buf[(id2 * 4 + 2)];
/* 116 */     buf[(id1 * 4 + 3)] = buf[(id2 * 4 + 3)];
/*     */ 
/* 118 */     buf[(id2 * 4 + 0)] = temp0;
/* 119 */     buf[(id2 * 4 + 1)] = temp1;
/* 120 */     buf[(id2 * 4 + 2)] = temp2;
/* 121 */     buf[(id2 * 4 + 3)] = temp3;
/*     */   }
/*     */ 
/*     */   public int getQuantizedAabbMin(int nodeId, int index) {
/* 125 */     switch (index) { case 0:
/*     */     default:
/* 127 */       return this.buf[(nodeId * 4 + 0)] & 0xFFFF;
/*     */     case 1:
/* 128 */       return this.buf[(nodeId * 4 + 0)] >>> 16 & 0xFFFF;
/* 129 */     case 2: } return this.buf[(nodeId * 4 + 1)] & 0xFFFF;
/*     */   }
/*     */ 
/*     */   public long getQuantizedAabbMin(int nodeId)
/*     */   {
/* 134 */     return this.buf[(nodeId * 4 + 0)] & 0xFFFFFFFF | (this.buf[(nodeId * 4 + 1)] & 0xFFFF) << 32;
/*     */   }
/*     */ 
/*     */   public void setQuantizedAabbMin(int nodeId, long value) {
/* 138 */     this.buf[(nodeId * 4 + 0)] = ((int)value);
/* 139 */     setQuantizedAabbMin(nodeId, 2, (short)(int)((value & 0x0) >>> 32));
/*     */   }
/*     */ 
/*     */   public void setQuantizedAabbMax(int nodeId, long value) {
/* 143 */     setQuantizedAabbMax(nodeId, 0, (short)(int)value);
/* 144 */     this.buf[(nodeId * 4 + 2)] = ((int)(value >>> 16));
/*     */   }
/*     */ 
/*     */   public void setQuantizedAabbMin(int nodeId, int index, int value) {
/* 148 */     switch (index) { case 0:
/* 149 */       this.buf[(nodeId * 4 + 0)] = (this.buf[(nodeId * 4 + 0)] & 0xFFFF0000 | value & 0xFFFF); break;
/*     */     case 1:
/* 150 */       this.buf[(nodeId * 4 + 0)] = (this.buf[(nodeId * 4 + 0)] & 0xFFFF | (value & 0xFFFF) << 16); break;
/*     */     case 2:
/* 151 */       this.buf[(nodeId * 4 + 1)] = (this.buf[(nodeId * 4 + 1)] & 0xFFFF0000 | value & 0xFFFF); }
/*     */   }
/*     */ 
/*     */   public int getQuantizedAabbMax(int nodeId, int index)
/*     */   {
/* 156 */     switch (index) { case 0:
/*     */     default:
/* 158 */       return this.buf[(nodeId * 4 + 1)] >>> 16 & 0xFFFF;
/*     */     case 1:
/* 159 */       return this.buf[(nodeId * 4 + 2)] & 0xFFFF;
/* 160 */     case 2: } return this.buf[(nodeId * 4 + 2)] >>> 16 & 0xFFFF;
/*     */   }
/*     */ 
/*     */   public long getQuantizedAabbMax(int nodeId)
/*     */   {
/* 165 */     return (this.buf[(nodeId * 4 + 1)] & 0xFFFF0000) >>> 16 | (this.buf[(nodeId * 4 + 2)] & 0xFFFFFFFF) << 16;
/*     */   }
/*     */ 
/*     */   public void setQuantizedAabbMax(int nodeId, int index, int value) {
/* 169 */     switch (index) { case 0:
/* 170 */       this.buf[(nodeId * 4 + 1)] = (this.buf[(nodeId * 4 + 1)] & 0xFFFF | (value & 0xFFFF) << 16); break;
/*     */     case 1:
/* 171 */       this.buf[(nodeId * 4 + 2)] = (this.buf[(nodeId * 4 + 2)] & 0xFFFF0000 | value & 0xFFFF); break;
/*     */     case 2:
/* 172 */       this.buf[(nodeId * 4 + 2)] = (this.buf[(nodeId * 4 + 2)] & 0xFFFF | (value & 0xFFFF) << 16); }
/*     */   }
/*     */ 
/*     */   public int getEscapeIndexOrTriangleIndex(int nodeId)
/*     */   {
/* 177 */     return this.buf[(nodeId * 4 + 3)];
/*     */   }
/*     */ 
/*     */   public void setEscapeIndexOrTriangleIndex(int nodeId, int value) {
/* 181 */     this.buf[(nodeId * 4 + 3)] = value;
/*     */   }
/*     */ 
/*     */   public boolean isLeafNode(int nodeId)
/*     */   {
/* 186 */     return getEscapeIndexOrTriangleIndex(nodeId) >= 0;
/*     */   }
/*     */ 
/*     */   public int getEscapeIndex(int nodeId) {
/* 190 */     assert (!isLeafNode(nodeId));
/* 191 */     return -getEscapeIndexOrTriangleIndex(nodeId);
/*     */   }
/*     */ 
/*     */   public int getTriangleIndex(int nodeId) {
/* 195 */     assert (isLeafNode(nodeId));
/*     */ 
/* 197 */     return getEscapeIndexOrTriangleIndex(nodeId) & 0x1FFFFF;
/*     */   }
/*     */ 
/*     */   public int getPartId(int nodeId) {
/* 201 */     assert (isLeafNode(nodeId));
/*     */ 
/* 203 */     return getEscapeIndexOrTriangleIndex(nodeId) >>> 21;
/*     */   }
/*     */ 
/*     */   public static int getCoord(long vec, int index) {
/* 207 */     switch (index) { case 0:
/*     */     default:
/* 209 */       return (int)(vec & 0xFFFF) & 0xFFFF;
/*     */     case 1:
/* 210 */       return (int)((vec & 0xFFFF0000) >>> 16) & 0xFFFF;
/* 211 */     case 2: } return (int)((vec & 0x0) >>> 32) & 0xFFFF;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.QuantizedBvhNodes
 * JD-Core Version:    0.6.2
 */