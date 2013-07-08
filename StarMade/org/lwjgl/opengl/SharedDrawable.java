/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import org.lwjgl.LWJGLException;
/*  4:   */
/* 44:   */public final class SharedDrawable
/* 45:   */  extends DrawableGL
/* 46:   */{
/* 47:   */  public SharedDrawable(Drawable drawable)
/* 48:   */    throws LWJGLException
/* 49:   */  {
/* 50:50 */    this.context = ((ContextGL)((DrawableLWJGL)drawable).createSharedContext());
/* 51:   */  }
/* 52:   */  
/* 53:   */  public ContextGL createSharedContext() {
/* 54:54 */    throw new UnsupportedOperationException();
/* 55:   */  }
/* 56:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.SharedDrawable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */