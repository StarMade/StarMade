/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.LWJGLException;
/*    */ 
/*    */ public final class SharedDrawable extends DrawableGL
/*    */ {
/*    */   public SharedDrawable(Drawable drawable)
/*    */     throws LWJGLException
/*    */   {
/* 50 */     this.context = ((ContextGL)((DrawableLWJGL)drawable).createSharedContext());
/*    */   }
/*    */ 
/*    */   public ContextGL createSharedContext() {
/* 54 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.SharedDrawable
 * JD-Core Version:    0.6.2
 */