package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;

abstract interface DrawableLWJGL
  extends Drawable
{
  public abstract void setPixelFormat(PixelFormatLWJGL paramPixelFormatLWJGL)
    throws LWJGLException;
  
  public abstract void setPixelFormat(PixelFormatLWJGL paramPixelFormatLWJGL, ContextAttribs paramContextAttribs)
    throws LWJGLException;
  
  public abstract PixelFormatLWJGL getPixelFormat();
  
  public abstract Context getContext();
  
  public abstract Context createSharedContext()
    throws LWJGLException;
  
  public abstract void checkGLError();
  
  public abstract void setSwapInterval(int paramInt);
  
  public abstract void swapBuffers()
    throws LWJGLException;
  
  public abstract void initContext(float paramFloat1, float paramFloat2, float paramFloat3);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.DrawableLWJGL
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */