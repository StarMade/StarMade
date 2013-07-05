/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class GREMEDYStringMarker
/*    */ {
/*    */   public static void glStringMarkerGREMEDY(ByteBuffer string)
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glStringMarkerGREMEDY;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     BufferChecks.checkDirect(string);
/* 17 */     nglStringMarkerGREMEDY(string.remaining(), MemoryUtil.getAddress(string), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglStringMarkerGREMEDY(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glStringMarkerGREMEDY(CharSequence string) {
/* 23 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 24 */     long function_pointer = caps.glStringMarkerGREMEDY;
/* 25 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 26 */     nglStringMarkerGREMEDY(string.length(), APIUtil.getBuffer(caps, string), function_pointer);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GREMEDYStringMarker
 * JD-Core Version:    0.6.2
 */