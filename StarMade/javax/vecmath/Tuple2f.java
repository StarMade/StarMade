package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple2f
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 9011180388985266884L;
  public float field_577;
  public float field_578;
  
  public Tuple2f(float paramFloat1, float paramFloat2)
  {
    this.field_577 = paramFloat1;
    this.field_578 = paramFloat2;
  }
  
  public Tuple2f(float[] paramArrayOfFloat)
  {
    this.field_577 = paramArrayOfFloat[0];
    this.field_578 = paramArrayOfFloat[1];
  }
  
  public Tuple2f(Tuple2f paramTuple2f)
  {
    this.field_577 = paramTuple2f.field_577;
    this.field_578 = paramTuple2f.field_578;
  }
  
  public Tuple2f(Tuple2d paramTuple2d)
  {
    this.field_577 = ((float)paramTuple2d.field_580);
    this.field_578 = ((float)paramTuple2d.field_581);
  }
  
  public Tuple2f()
  {
    this.field_577 = 0.0F;
    this.field_578 = 0.0F;
  }
  
  public final void set(float paramFloat1, float paramFloat2)
  {
    this.field_577 = paramFloat1;
    this.field_578 = paramFloat2;
  }
  
  public final void set(float[] paramArrayOfFloat)
  {
    this.field_577 = paramArrayOfFloat[0];
    this.field_578 = paramArrayOfFloat[1];
  }
  
  public final void set(Tuple2f paramTuple2f)
  {
    this.field_577 = paramTuple2f.field_577;
    this.field_578 = paramTuple2f.field_578;
  }
  
  public final void set(Tuple2d paramTuple2d)
  {
    this.field_577 = ((float)paramTuple2d.field_580);
    this.field_578 = ((float)paramTuple2d.field_581);
  }
  
  public final void get(float[] paramArrayOfFloat)
  {
    paramArrayOfFloat[0] = this.field_577;
    paramArrayOfFloat[1] = this.field_578;
  }
  
  public final void add(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2)
  {
    paramTuple2f1.field_577 += paramTuple2f2.field_577;
    paramTuple2f1.field_578 += paramTuple2f2.field_578;
  }
  
  public final void add(Tuple2f paramTuple2f)
  {
    this.field_577 += paramTuple2f.field_577;
    this.field_578 += paramTuple2f.field_578;
  }
  
  public final void sub(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2)
  {
    paramTuple2f1.field_577 -= paramTuple2f2.field_577;
    paramTuple2f1.field_578 -= paramTuple2f2.field_578;
  }
  
  public final void sub(Tuple2f paramTuple2f)
  {
    this.field_577 -= paramTuple2f.field_577;
    this.field_578 -= paramTuple2f.field_578;
  }
  
  public final void negate(Tuple2f paramTuple2f)
  {
    this.field_577 = (-paramTuple2f.field_577);
    this.field_578 = (-paramTuple2f.field_578);
  }
  
  public final void negate()
  {
    this.field_577 = (-this.field_577);
    this.field_578 = (-this.field_578);
  }
  
  public final void scale(float paramFloat, Tuple2f paramTuple2f)
  {
    this.field_577 = (paramFloat * paramTuple2f.field_577);
    this.field_578 = (paramFloat * paramTuple2f.field_578);
  }
  
  public final void scale(float paramFloat)
  {
    this.field_577 *= paramFloat;
    this.field_578 *= paramFloat;
  }
  
  public final void scaleAdd(float paramFloat, Tuple2f paramTuple2f1, Tuple2f paramTuple2f2)
  {
    this.field_577 = (paramFloat * paramTuple2f1.field_577 + paramTuple2f2.field_577);
    this.field_578 = (paramFloat * paramTuple2f1.field_578 + paramTuple2f2.field_578);
  }
  
  public final void scaleAdd(float paramFloat, Tuple2f paramTuple2f)
  {
    this.field_577 = (paramFloat * this.field_577 + paramTuple2f.field_577);
    this.field_578 = (paramFloat * this.field_578 + paramTuple2f.field_578);
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_577);
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_578);
    return (int)(l ^ l >> 32);
  }
  
  public boolean equals(Tuple2f paramTuple2f)
  {
    try
    {
      return (this.field_577 == paramTuple2f.field_577) && (this.field_578 == paramTuple2f.field_578);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple2f localTuple2f = (Tuple2f)paramObject;
      return (this.field_577 == localTuple2f.field_577) && (this.field_578 == localTuple2f.field_578);
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
    float f = this.field_577 - paramTuple2f.field_577;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.field_578 - paramTuple2f.field_578;
    return (f < 0.0F ? -f : f) <= paramFloat;
  }
  
  public String toString()
  {
    return "(" + this.field_577 + ", " + this.field_578 + ")";
  }
  
  public final void clamp(float paramFloat1, float paramFloat2, Tuple2f paramTuple2f)
  {
    if (paramTuple2f.field_577 > paramFloat2) {
      this.field_577 = paramFloat2;
    } else if (paramTuple2f.field_577 < paramFloat1) {
      this.field_577 = paramFloat1;
    } else {
      this.field_577 = paramTuple2f.field_577;
    }
    if (paramTuple2f.field_578 > paramFloat2) {
      this.field_578 = paramFloat2;
    } else if (paramTuple2f.field_578 < paramFloat1) {
      this.field_578 = paramFloat1;
    } else {
      this.field_578 = paramTuple2f.field_578;
    }
  }
  
  public final void clampMin(float paramFloat, Tuple2f paramTuple2f)
  {
    if (paramTuple2f.field_577 < paramFloat) {
      this.field_577 = paramFloat;
    } else {
      this.field_577 = paramTuple2f.field_577;
    }
    if (paramTuple2f.field_578 < paramFloat) {
      this.field_578 = paramFloat;
    } else {
      this.field_578 = paramTuple2f.field_578;
    }
  }
  
  public final void clampMax(float paramFloat, Tuple2f paramTuple2f)
  {
    if (paramTuple2f.field_577 > paramFloat) {
      this.field_577 = paramFloat;
    } else {
      this.field_577 = paramTuple2f.field_577;
    }
    if (paramTuple2f.field_578 > paramFloat) {
      this.field_578 = paramFloat;
    } else {
      this.field_578 = paramTuple2f.field_578;
    }
  }
  
  public final void absolute(Tuple2f paramTuple2f)
  {
    this.field_577 = Math.abs(paramTuple2f.field_577);
    this.field_578 = Math.abs(paramTuple2f.field_578);
  }
  
  public final void clamp(float paramFloat1, float paramFloat2)
  {
    if (this.field_577 > paramFloat2) {
      this.field_577 = paramFloat2;
    } else if (this.field_577 < paramFloat1) {
      this.field_577 = paramFloat1;
    }
    if (this.field_578 > paramFloat2) {
      this.field_578 = paramFloat2;
    } else if (this.field_578 < paramFloat1) {
      this.field_578 = paramFloat1;
    }
  }
  
  public final void clampMin(float paramFloat)
  {
    if (this.field_577 < paramFloat) {
      this.field_577 = paramFloat;
    }
    if (this.field_578 < paramFloat) {
      this.field_578 = paramFloat;
    }
  }
  
  public final void clampMax(float paramFloat)
  {
    if (this.field_577 > paramFloat) {
      this.field_577 = paramFloat;
    }
    if (this.field_578 > paramFloat) {
      this.field_578 = paramFloat;
    }
  }
  
  public final void absolute()
  {
    this.field_577 = Math.abs(this.field_577);
    this.field_578 = Math.abs(this.field_578);
  }
  
  public final void interpolate(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2, float paramFloat)
  {
    this.field_577 = ((1.0F - paramFloat) * paramTuple2f1.field_577 + paramFloat * paramTuple2f2.field_577);
    this.field_578 = ((1.0F - paramFloat) * paramTuple2f1.field_578 + paramFloat * paramTuple2f2.field_578);
  }
  
  public final void interpolate(Tuple2f paramTuple2f, float paramFloat)
  {
    this.field_577 = ((1.0F - paramFloat) * this.field_577 + paramFloat * paramTuple2f.field_577);
    this.field_578 = ((1.0F - paramFloat) * this.field_578 + paramFloat * paramTuple2f.field_578);
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


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Tuple2f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */