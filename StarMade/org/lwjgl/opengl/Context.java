package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;

abstract interface Context
{
  public abstract boolean isCurrent()
    throws LWJGLException;
  
  public abstract void makeCurrent()
    throws LWJGLException;
  
  public abstract void releaseCurrent()
    throws LWJGLException;
  
  public abstract void releaseDrawable()
    throws LWJGLException;
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.Context
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */