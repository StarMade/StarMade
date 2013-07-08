package org.lwjgl.util.vector;

import java.nio.FloatBuffer;

public abstract interface ReadableVector
{
  public abstract float length();
  
  public abstract float lengthSquared();
  
  public abstract Vector store(FloatBuffer paramFloatBuffer);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.vector.ReadableVector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */