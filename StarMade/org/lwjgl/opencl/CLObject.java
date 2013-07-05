/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import org.lwjgl.PointerWrapperAbstract;
/*    */ 
/*    */ abstract class CLObject extends PointerWrapperAbstract
/*    */ {
/*    */   protected CLObject(long pointer)
/*    */   {
/* 44 */     super(pointer);
/*    */   }
/*    */ 
/*    */   final long getPointerUnsafe() {
/* 48 */     return this.pointer;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLObject
 * JD-Core Version:    0.6.2
 */