/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import org.lwjgl.LWJGLUtil;
/*    */ 
/*    */ abstract class CLObjectChild<P extends CLObject> extends CLObjectRetainable
/*    */ {
/*    */   private final P parent;
/*    */ 
/*    */   protected CLObjectChild(long pointer, P parent)
/*    */   {
/* 46 */     super(pointer);
/*    */ 
/* 48 */     if ((LWJGLUtil.DEBUG) && (parent != null) && (!parent.isValid())) {
/* 49 */       throw new IllegalStateException("The parent specified is not a valid CL object.");
/*    */     }
/* 51 */     this.parent = parent;
/*    */   }
/*    */ 
/*    */   public P getParent() {
/* 55 */     return this.parent;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CLObjectChild
 * JD-Core Version:    0.6.2
 */