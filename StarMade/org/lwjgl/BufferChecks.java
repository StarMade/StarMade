/*     */ package org.lwjgl;
/*     */ 
/*     */ import java.nio.Buffer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ 
/*     */ public class BufferChecks
/*     */ {
/*     */   public static void checkFunctionAddress(long pointer)
/*     */   {
/*  57 */     if ((LWJGLUtil.CHECKS) && (pointer == 0L))
/*  58 */       throw new IllegalStateException("Function is not supported");
/*     */   }
/*     */ 
/*     */   public static void checkNullTerminated(ByteBuffer buf)
/*     */   {
/*  66 */     if ((LWJGLUtil.CHECKS) && (buf.get(buf.limit() - 1) != 0))
/*  67 */       throw new IllegalArgumentException("Missing null termination");
/*     */   }
/*     */ 
/*     */   public static void checkNullTerminated(ByteBuffer buf, int count)
/*     */   {
/*  72 */     if (LWJGLUtil.CHECKS) {
/*  73 */       int nullFound = 0;
/*  74 */       for (int i = buf.position(); i < buf.limit(); i++) {
/*  75 */         if (buf.get(i) == 0) {
/*  76 */           nullFound++;
/*     */         }
/*     */       }
/*  79 */       if (nullFound < count)
/*  80 */         throw new IllegalArgumentException("Missing null termination");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void checkNullTerminated(IntBuffer buf)
/*     */   {
/*  86 */     if ((LWJGLUtil.CHECKS) && (buf.get(buf.limit() - 1) != 0))
/*  87 */       throw new IllegalArgumentException("Missing null termination");
/*     */   }
/*     */ 
/*     */   public static void checkNullTerminated(LongBuffer buf)
/*     */   {
/*  93 */     if ((LWJGLUtil.CHECKS) && (buf.get(buf.limit() - 1) != 0L))
/*  94 */       throw new IllegalArgumentException("Missing null termination");
/*     */   }
/*     */ 
/*     */   public static void checkNullTerminated(PointerBuffer buf)
/*     */   {
/* 100 */     if ((LWJGLUtil.CHECKS) && (buf.get(buf.limit() - 1) != 0L))
/* 101 */       throw new IllegalArgumentException("Missing null termination");
/*     */   }
/*     */ 
/*     */   public static void checkNotNull(Object o)
/*     */   {
/* 106 */     if ((LWJGLUtil.CHECKS) && (o == null))
/* 107 */       throw new IllegalArgumentException("Null argument");
/*     */   }
/*     */ 
/*     */   public static void checkDirect(ByteBuffer buf)
/*     */   {
/* 114 */     if ((LWJGLUtil.CHECKS) && (!buf.isDirect()))
/* 115 */       throw new IllegalArgumentException("ByteBuffer is not direct");
/*     */   }
/*     */ 
/*     */   public static void checkDirect(ShortBuffer buf)
/*     */   {
/* 120 */     if ((LWJGLUtil.CHECKS) && (!buf.isDirect()))
/* 121 */       throw new IllegalArgumentException("ShortBuffer is not direct");
/*     */   }
/*     */ 
/*     */   public static void checkDirect(IntBuffer buf)
/*     */   {
/* 126 */     if ((LWJGLUtil.CHECKS) && (!buf.isDirect()))
/* 127 */       throw new IllegalArgumentException("IntBuffer is not direct");
/*     */   }
/*     */ 
/*     */   public static void checkDirect(LongBuffer buf)
/*     */   {
/* 132 */     if ((LWJGLUtil.CHECKS) && (!buf.isDirect()))
/* 133 */       throw new IllegalArgumentException("LongBuffer is not direct");
/*     */   }
/*     */ 
/*     */   public static void checkDirect(FloatBuffer buf)
/*     */   {
/* 138 */     if ((LWJGLUtil.CHECKS) && (!buf.isDirect()))
/* 139 */       throw new IllegalArgumentException("FloatBuffer is not direct");
/*     */   }
/*     */ 
/*     */   public static void checkDirect(DoubleBuffer buf)
/*     */   {
/* 144 */     if ((LWJGLUtil.CHECKS) && (!buf.isDirect()))
/* 145 */       throw new IllegalArgumentException("DoubleBuffer is not direct");
/*     */   }
/*     */ 
/*     */   public static void checkDirect(PointerBuffer buf)
/*     */   {
/*     */   }
/*     */ 
/*     */   public static void checkArray(Object[] array)
/*     */   {
/* 154 */     if ((LWJGLUtil.CHECKS) && ((array == null) || (array.length == 0)))
/* 155 */       throw new IllegalArgumentException("Invalid array");
/*     */   }
/*     */ 
/*     */   private static void throwBufferSizeException(Buffer buf, int size)
/*     */   {
/* 162 */     throw new IllegalArgumentException("Number of remaining buffer elements is " + buf.remaining() + ", must be at least " + size + ". Because at most " + size + " elements can be returned, a buffer with at least " + size + " elements is required, regardless of actual returned element count");
/*     */   }
/*     */ 
/*     */   private static void throwBufferSizeException(PointerBuffer buf, int size) {
/* 166 */     throw new IllegalArgumentException("Number of remaining pointer buffer elements is " + buf.remaining() + ", must be at least " + size);
/*     */   }
/*     */ 
/*     */   private static void throwArraySizeException(Object[] array, int size) {
/* 170 */     throw new IllegalArgumentException("Number of array elements is " + array.length + ", must be at least " + size);
/*     */   }
/*     */ 
/*     */   private static void throwArraySizeException(long[] array, int size) {
/* 174 */     throw new IllegalArgumentException("Number of array elements is " + array.length + ", must be at least " + size);
/*     */   }
/*     */ 
/*     */   public static void checkBufferSize(Buffer buf, int size)
/*     */   {
/* 188 */     if ((LWJGLUtil.CHECKS) && (buf.remaining() < size))
/* 189 */       throwBufferSizeException(buf, size);
/*     */   }
/*     */ 
/*     */   public static int checkBuffer(Buffer buffer, int size)
/*     */   {
/*     */     int posShift;
/* 204 */     if ((buffer instanceof ByteBuffer)) {
/* 205 */       checkBuffer((ByteBuffer)buffer, size);
/* 206 */       posShift = 0;
/*     */     }
/*     */     else
/*     */     {
/*     */       int posShift;
/* 207 */       if ((buffer instanceof ShortBuffer)) {
/* 208 */         checkBuffer((ShortBuffer)buffer, size);
/* 209 */         posShift = 1;
/*     */       }
/*     */       else
/*     */       {
/*     */         int posShift;
/* 210 */         if ((buffer instanceof IntBuffer)) {
/* 211 */           checkBuffer((IntBuffer)buffer, size);
/* 212 */           posShift = 2;
/*     */         }
/*     */         else
/*     */         {
/*     */           int posShift;
/* 213 */           if ((buffer instanceof LongBuffer)) {
/* 214 */             checkBuffer((LongBuffer)buffer, size);
/* 215 */             posShift = 4;
/*     */           }
/*     */           else
/*     */           {
/*     */             int posShift;
/* 216 */             if ((buffer instanceof FloatBuffer)) {
/* 217 */               checkBuffer((FloatBuffer)buffer, size);
/* 218 */               posShift = 2;
/*     */             }
/*     */             else
/*     */             {
/*     */               int posShift;
/* 219 */               if ((buffer instanceof DoubleBuffer)) {
/* 220 */                 checkBuffer((DoubleBuffer)buffer, size);
/* 221 */                 posShift = 4;
/*     */               } else {
/* 223 */                 throw new IllegalArgumentException("Unsupported Buffer type specified: " + buffer.getClass());
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     int posShift;
/* 225 */     return buffer.position() << posShift;
/*     */   }
/*     */ 
/*     */   public static void checkBuffer(ByteBuffer buf, int size) {
/* 229 */     if (LWJGLUtil.CHECKS) {
/* 230 */       checkBufferSize(buf, size);
/* 231 */       checkDirect(buf);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void checkBuffer(ShortBuffer buf, int size) {
/* 236 */     if (LWJGLUtil.CHECKS) {
/* 237 */       checkBufferSize(buf, size);
/* 238 */       checkDirect(buf);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void checkBuffer(IntBuffer buf, int size) {
/* 243 */     if (LWJGLUtil.CHECKS) {
/* 244 */       checkBufferSize(buf, size);
/* 245 */       checkDirect(buf);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void checkBuffer(LongBuffer buf, int size) {
/* 250 */     if (LWJGLUtil.CHECKS) {
/* 251 */       checkBufferSize(buf, size);
/* 252 */       checkDirect(buf);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void checkBuffer(FloatBuffer buf, int size) {
/* 257 */     if (LWJGLUtil.CHECKS) {
/* 258 */       checkBufferSize(buf, size);
/* 259 */       checkDirect(buf);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void checkBuffer(DoubleBuffer buf, int size) {
/* 264 */     if (LWJGLUtil.CHECKS) {
/* 265 */       checkBufferSize(buf, size);
/* 266 */       checkDirect(buf);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void checkBuffer(PointerBuffer buf, int size) {
/* 271 */     if ((LWJGLUtil.CHECKS) && (buf.remaining() < size))
/* 272 */       throwBufferSizeException(buf, size);
/*     */   }
/*     */ 
/*     */   public static void checkArray(Object[] array, int size)
/*     */   {
/* 277 */     if ((LWJGLUtil.CHECKS) && (array.length < size))
/* 278 */       throwArraySizeException(array, size);
/*     */   }
/*     */ 
/*     */   public static void checkArray(long[] array, int size) {
/* 282 */     if ((LWJGLUtil.CHECKS) && (array.length < size))
/* 283 */       throwArraySizeException(array, size);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.BufferChecks
 * JD-Core Version:    0.6.2
 */