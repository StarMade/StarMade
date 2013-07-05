/*     */ package org.lwjgl.opencl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.PointerBuffer;
/*     */ 
/*     */ abstract class InfoUtilAbstract<T extends CLObject>
/*     */   implements InfoUtil<T>
/*     */ {
/*     */   protected abstract int getInfo(T paramT, int paramInt, ByteBuffer paramByteBuffer, PointerBuffer paramPointerBuffer);
/*     */ 
/*     */   protected int getInfoSizeArraySize(T object, int param_name)
/*     */   {
/*  56 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   protected PointerBuffer getSizesBuffer(T object, int param_name) {
/*  60 */     int size = getInfoSizeArraySize(object, param_name);
/*     */ 
/*  62 */     PointerBuffer buffer = APIUtil.getBufferPointer(size);
/*  63 */     buffer.limit(size);
/*     */ 
/*  65 */     getInfo(object, param_name, buffer.getBuffer(), null);
/*     */ 
/*  67 */     return buffer;
/*     */   }
/*     */ 
/*     */   public int getInfoInt(T object, int param_name) {
/*  71 */     object.checkValid();
/*     */ 
/*  73 */     ByteBuffer buffer = APIUtil.getBufferByte(4);
/*  74 */     getInfo(object, param_name, buffer, null);
/*     */ 
/*  76 */     return buffer.getInt(0);
/*     */   }
/*     */ 
/*     */   public long getInfoSize(T object, int param_name) {
/*  80 */     object.checkValid();
/*     */ 
/*  82 */     PointerBuffer buffer = APIUtil.getBufferPointer();
/*  83 */     getInfo(object, param_name, buffer.getBuffer(), null);
/*     */ 
/*  85 */     return buffer.get(0);
/*     */   }
/*     */ 
/*     */   public long[] getInfoSizeArray(T object, int param_name) {
/*  89 */     object.checkValid();
/*     */ 
/*  91 */     int size = getInfoSizeArraySize(object, param_name);
/*  92 */     PointerBuffer buffer = APIUtil.getBufferPointer(size);
/*     */ 
/*  94 */     getInfo(object, param_name, buffer.getBuffer(), null);
/*     */ 
/*  96 */     long[] array = new long[size];
/*  97 */     for (int i = 0; i < size; i++) {
/*  98 */       array[i] = buffer.get(i);
/*     */     }
/* 100 */     return array;
/*     */   }
/*     */ 
/*     */   public long getInfoLong(T object, int param_name) {
/* 104 */     object.checkValid();
/*     */ 
/* 106 */     ByteBuffer buffer = APIUtil.getBufferByte(8);
/* 107 */     getInfo(object, param_name, buffer, null);
/*     */ 
/* 109 */     return buffer.getLong(0);
/*     */   }
/*     */ 
/*     */   public String getInfoString(T object, int param_name) {
/* 113 */     object.checkValid();
/*     */ 
/* 115 */     int bytes = getSizeRet(object, param_name);
/* 116 */     if (bytes <= 1) {
/* 117 */       return null;
/*     */     }
/* 119 */     ByteBuffer buffer = APIUtil.getBufferByte(bytes);
/* 120 */     getInfo(object, param_name, buffer, null);
/*     */ 
/* 122 */     buffer.limit(bytes - 1);
/* 123 */     return APIUtil.getString(buffer);
/*     */   }
/*     */ 
/*     */   protected final int getSizeRet(T object, int param_name) {
/* 127 */     PointerBuffer bytes = APIUtil.getBufferPointer();
/* 128 */     int errcode = getInfo(object, param_name, null, bytes);
/* 129 */     if (errcode != 0) {
/* 130 */       throw new IllegalArgumentException("Invalid parameter specified: " + LWJGLUtil.toHexString(param_name));
/*     */     }
/* 132 */     return (int)bytes.get(0);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.InfoUtilAbstract
 * JD-Core Version:    0.6.2
 */