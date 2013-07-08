package org.lwjgl.util.vector;

import java.io.Serializable;
import java.nio.FloatBuffer;

public abstract class Vector
  implements Serializable, ReadableVector
{
  public final float length()
  {
    return (float)Math.sqrt(lengthSquared());
  }
  
  public abstract float lengthSquared();
  
  public abstract Vector load(FloatBuffer paramFloatBuffer);
  
  public abstract Vector negate();
  
  public final Vector normalise()
  {
    float len = length();
    if (len != 0.0F)
    {
      float local_l = 1.0F / len;
      return scale(local_l);
    }
    throw new IllegalStateException("Zero length vector");
  }
  
  public abstract Vector store(FloatBuffer paramFloatBuffer);
  
  public abstract Vector scale(float paramFloat);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.vector.Vector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */