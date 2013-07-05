/*    */ package org.newdawn.slick.util;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.BufferUtils;
/*    */ 
/*    */ public class GlUtil
/*    */ {
/* 37 */   private static ByteBuffer[] dynamicByteBuffer = new ByteBuffer[1];
/*    */ 
/*    */   public static void destroyDirectByteBuffer(ByteBuffer toBeDestroyed)
/*    */     throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException
/*    */   {
/* 26 */     Method cleanerMethod = toBeDestroyed.getClass().getMethod("cleaner", new Class[0]);
/* 27 */     cleanerMethod.setAccessible(true);
/* 28 */     Object cleaner = cleanerMethod.invoke(toBeDestroyed, new Object[0]);
/* 29 */     Method cleanMethod = cleaner.getClass().getMethod("clean", new Class[0]);
/* 30 */     cleanMethod.setAccessible(true);
/* 31 */     cleanMethod.invoke(cleaner, new Object[0]);
/*    */   }
/*    */ 
/*    */   public static ByteBuffer getDynamicByteBuffer(int size) {
/* 35 */     return getDynamicByteBuffer(size, 0);
/*    */   }
/*    */ 
/*    */   public static ByteBuffer getDynamicByteBuffer(int size, int index) {
/* 39 */     ByteBuffer dynamicByteBuffer = dynamicByteBuffer[index];
/* 40 */     if ((dynamicByteBuffer == null) || (dynamicByteBuffer.capacity() < size)) {
/* 41 */       if (dynamicByteBuffer != null)
/*    */       {
/*    */         try {
/* 44 */           destroyDirectByteBuffer(dynamicByteBuffer);
/*    */         } catch (IllegalArgumentException e) {
/* 46 */           e.printStackTrace();
/*    */         } catch (SecurityException e) {
/* 48 */           e.printStackTrace();
/*    */         } catch (IllegalAccessException e) {
/* 50 */           e.printStackTrace();
/*    */         } catch (InvocationTargetException e) {
/* 52 */           e.printStackTrace();
/*    */         } catch (NoSuchMethodException e) {
/* 54 */           e.printStackTrace();
/*    */         }
/* 56 */         dynamicByteBuffer = null;
/* 57 */         System.gc();
/*    */       }
/*    */ 
/* 60 */       dynamicByteBuffer = BufferUtils.createByteBuffer(size);
/*    */     }
/* 62 */     dynamicByteBuffer.limit(size);
/* 63 */     dynamicByteBuffer.rewind();
/* 64 */     return dynamicByteBuffer;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.GlUtil
 * JD-Core Version:    0.6.2
 */