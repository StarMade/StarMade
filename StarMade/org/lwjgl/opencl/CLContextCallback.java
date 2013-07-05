/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.PointerWrapperAbstract;
/*    */ 
/*    */ public abstract class CLContextCallback extends PointerWrapperAbstract
/*    */ {
/*    */   private final boolean custom;
/*    */ 
/*    */   protected CLContextCallback()
/*    */   {
/* 48 */     super(CallbackUtil.getContextCallback());
/* 49 */     this.custom = false;
/*    */   }
/*    */ 
/*    */   protected CLContextCallback(long pointer)
/*    */   {
/* 58 */     super(pointer);
/*    */ 
/* 60 */     if (pointer == 0L) {
/* 61 */       throw new RuntimeException("Invalid callback function pointer specified.");
/*    */     }
/* 63 */     this.custom = true;
/*    */   }
/*    */ 
/*    */   final boolean isCustom() {
/* 67 */     return this.custom;
/*    */   }
/*    */ 
/*    */   protected abstract void handleMessage(String paramString, ByteBuffer paramByteBuffer);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLContextCallback
 * JD-Core Version:    0.6.2
 */