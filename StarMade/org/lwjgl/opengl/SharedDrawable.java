package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;

public final class SharedDrawable
  extends DrawableGL
{
  public SharedDrawable(Drawable drawable)
    throws LWJGLException
  {
    this.context = ((ContextGL)((DrawableLWJGL)drawable).createSharedContext());
  }
  
  public ContextGL createSharedContext()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.SharedDrawable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */