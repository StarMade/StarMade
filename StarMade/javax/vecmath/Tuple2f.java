package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple2f
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 9011180388985266884L;
  public float x;
  public float y;
  
  public Tuple2f(float paramFloat1, float paramFloat2)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
  }
  
  public Tuple2f(float[] paramArrayOfFloat)
  {
    this.x = paramArrayOfFloat[0];
    this.y = paramArrayOfFloat[1];
  }
  
  public Tuple2f(Tuple2f paramTuple2f)
  {
    this.x = paramTuple2f.x;
    this.y = paramTuple2f.y;
  }
  
  public Tuple2f(Tuple2d paramTuple2d)
  {
    this.x = ((float)paramTuple2d.x);
    this.y = ((float)paramTuple2d.y);
  }
  
  public Tuple2f()
  {
    this.x = 0.0F;
    this.y = 0.0F;
  }
  
  public final void set(float paramFloat1, float paramFloat2)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
  }
  
  public final void set(float[] paramArrayOfFloat)
  {
    this.x = paramArrayOfFloat[0];
    this.y = paramArrayOfFloat[1];
  }
  
  public final void set(Tuple2f paramTuple2f)
  {
    this.x = paramTuple2f.x;
    this.y = paramTuple2f.y;
  }
  
  public final void set(Tuple2d paramTuple2d)
  {
    this.x = ((float)paramTuple2d.x);
    this.y = ((float)paramTuple2d.y);
  }
  
  public final void get(float[] paramArrayOfFloat)
  {
    paramArrayOfFloat[0] = this.x;
    paramArrayOfFloat[1] = this.y;
  }
  
  public final void add(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2)
  {
    paramTuple2f1.x += paramTuple2f2.x;
    paramTuple2f1.y += paramTuple2f2.y;
  }
  
  public final void add(Tuple2f paramTuple2f)
  {
    this.x += paramTuple2f.x;
    this.y += paramTuple2f.y;
  }
  
  public final void sub(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2)
  {
    paramTuple2f1.x -= paramTuple2f2.x;
    paramTuple2f1.y -= paramTuple2f2.y;
  }
  
  public final void sub(Tuple2f paramTuple2f)
  {
    this.x -= paramTuple2f.x;
    this.y -= paramTuple2f.y;
  }
  
  public final void negate(Tuple2f paramTuple2f)
  {
    this.x = (-paramTuple2f.x);
    this.y = (-paramTuple2f.y);
  }
  
  public final void negate()
  {
    this.x = (-this.x);
    this.y = (-this.y);
  }
  
  public final void scale(float paramFloat, Tuple2f paramTuple2f)
  {
    this.x = (paramFloat * paramTuple2f.x);
    this.y = (paramFloat * paramTuple2f.y);
  }
  
  public final void scale(float paramFloat)
  {
    this.x *= paramFloat;
    this.y *= paramFloat;
  }
  
  public final void scaleAdd(float paramFloat, Tuple2f paramTuple2f1, Tuple2f paramTuple2f2)
  {
    this.x = (paramFloat * paramTuple2f1.x + paramTuple2f2.x);
    this.y = (paramFloat * paramTuple2f1.y + paramTuple2f2.y);
  }
  
  public final void scaleAdd(float paramFloat, Tuple2f paramTuple2f)
  {
    this.x = (paramFloat * this.x + paramTuple2f.x);
    this.y = (paramFloat * this.y + paramTuple2f.y);
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.floatToIntBits(this.x);
    l = 31L * l + VecMathUtil.floatToIntBits(this.y);
    return (int)(l ^ l >> 32);
  }
  
  public boolean equals(Tuple2f paramTuple2f)
  {
    try
    {
      return (this.x == paramTuple2f.x) && (this.y == paramTuple2f.y);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple2f localTuple2f = (Tuple2f)paramObject;
      return (this.x == localTuple2f.x) && (this.y == localTuple2f.y);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException) {}
    return false;
  }
  
  public boolean epsilonEquals(Tuple2f paramTuple2f, float paramFloat)
  {
    float f = this.x - paramTuple2f.x;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.y - paramTuple2f.y;
    return (f < 0.0F ? -f : f) <= paramFloat;
  }
  
  public String toString()
  {
    return "(" + this.x + ", " + this.y + ")";
  }
  
  public final void clamp(float paramFloat1, float paramFloat2, Tuple2f paramTuple2f)
  {
    if (paramTuple2f.x > paramFloat2) {
      this.x = paramFloat2;
    } else if (paramTuple2f.x < paramFloat1) {
      this.x = paramFloat1;
    } else {
      this.x = paramTuple2f.x;
    }
    if (paramTuple2f.y > paramFloat2) {
      this.y = paramFloat2;
    } else if (paramTuple2f.y < paramFloat1) {
      this.y = paramFloat1;
    } else {
      this.y = paramTuple2f.y;
    }
  }
  
  public final void clampMin(float paramFloat, Tuple2f paramTuple2f)
  {
    if (paramTuple2f.x < paramFloat) {
      this.x = paramFloat;
    } else {
      this.x = paramTuple2f.x;
    }
    if (paramTuple2f.y < paramFloat) {
      this.y = paramFloat;
    } else {
      this.y = paramTuple2f.y;
    }
  }
  
  public final void clampMax(float paramFloat, Tuple2f paramTuple2f)
  {
    if (paramTuple2f.x > paramFloat) {
      this.x = paramFloat;
    } else {
      this.x = paramTuple2f.x;
    }
    if (paramTuple2f.y > paramFloat) {
      this.y = paramFloat;
    } else {
      this.y = paramTuple2f.y;
    }
  }
  
  public final void absolute(Tuple2f paramTuple2f)
  {
    this.x = Math.abs(paramTuple2f.x);
    this.y = Math.abs(paramTuple2f.y);
  }
  
  public final void clamp(float paramFloat1, float paramFloat2)
  {
    if (this.x > paramFloat2) {
      this.x = paramFloat2;
    } else if (this.x < paramFloat1) {
      this.x = paramFloat1;
    }
    if (this.y > paramFloat2) {
      this.y = paramFloat2;
    } else if (this.y < paramFloat1) {
      this.y = paramFloat1;
    }
  }
  
  public final void clampMin(float paramFloat)
  {
    if (this.x < paramFloat) {
      this.x = paramFloat;
    }
    if (this.y < paramFloat) {
      this.y = paramFloat;
    }
  }
  
  public final void clampMax(float paramFloat)
  {
    if (this.x > paramFloat) {
      this.x = paramFloat;
    }
    if (this.y > paramFloat) {
      this.y = paramFloat;
    }
  }
  
  public final void absolute()
  {
    this.x = Math.abs(this.x);
    this.y = Math.abs(this.y);
  }
  
  public final void interpolate(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2, float paramFloat)
  {
    this.x = ((1.0F - paramFloat) * paramTuple2f1.x + paramFloat * paramTuple2f2.x);
    this.y = ((1.0F - paramFloat) * paramTuple2f1.y + paramFloat * paramTuple2f2.y);
  }
  
  public final void interpolate(Tuple2f paramTuple2f, float paramFloat)
  {
    this.x = ((1.0F - paramFloat) * this.x + paramFloat * paramTuple2f.x);
    this.y = ((1.0F - paramFloat) * this.y + paramFloat * paramTuple2f.y);
  }
  
  public Object clone()
  {
    try
    {
      return super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new InternalError();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Tuple2f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */