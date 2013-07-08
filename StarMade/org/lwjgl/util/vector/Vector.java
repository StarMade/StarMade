/*  1:   */package org.lwjgl.util.vector;
/*  2:   */
/*  3:   */import java.io.Serializable;
/*  4:   */import java.nio.FloatBuffer;
/*  5:   */
/* 53:   */public abstract class Vector
/* 54:   */  implements Serializable, ReadableVector
/* 55:   */{
/* 56:   */  public final float length()
/* 57:   */  {
/* 58:58 */    return (float)Math.sqrt(lengthSquared());
/* 59:   */  }
/* 60:   */  
/* 66:   */  public abstract float lengthSquared();
/* 67:   */  
/* 72:   */  public abstract Vector load(FloatBuffer paramFloatBuffer);
/* 73:   */  
/* 78:   */  public abstract Vector negate();
/* 79:   */  
/* 84:   */  public final Vector normalise()
/* 85:   */  {
/* 86:86 */    float len = length();
/* 87:87 */    if (len != 0.0F) {
/* 88:88 */      float l = 1.0F / len;
/* 89:89 */      return scale(l);
/* 90:   */    }
/* 91:91 */    throw new IllegalStateException("Zero length vector");
/* 92:   */  }
/* 93:   */  
/* 94:   */  public abstract Vector store(FloatBuffer paramFloatBuffer);
/* 95:   */  
/* 96:   */  public abstract Vector scale(float paramFloat);
/* 97:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Vector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */