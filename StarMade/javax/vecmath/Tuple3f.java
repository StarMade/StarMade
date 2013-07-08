package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple3f
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 5019834619484343712L;
  public float field_615;
  public float field_616;
  public float field_617;
  
  public Tuple3f(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.field_615 = paramFloat1;
    this.field_616 = paramFloat2;
    this.field_617 = paramFloat3;
  }
  
  public Tuple3f(float[] paramArrayOfFloat)
  {
    this.field_615 = paramArrayOfFloat[0];
    this.field_616 = paramArrayOfFloat[1];
    this.field_617 = paramArrayOfFloat[2];
  }
  
  public Tuple3f(Tuple3f paramTuple3f)
  {
    this.field_615 = paramTuple3f.field_615;
    this.field_616 = paramTuple3f.field_616;
    this.field_617 = paramTuple3f.field_617;
  }
  
  public Tuple3f(Tuple3d paramTuple3d)
  {
    this.field_615 = ((float)paramTuple3d.field_612);
    this.field_616 = ((float)paramTuple3d.field_613);
    this.field_617 = ((float)paramTuple3d.field_614);
  }
  
  public Tuple3f()
  {
    this.field_615 = 0.0F;
    this.field_616 = 0.0F;
    this.field_617 = 0.0F;
  }
  
  public String toString()
  {
    return "(" + this.field_615 + ", " + this.field_616 + ", " + this.field_617 + ")";
  }
  
  public final void set(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.field_615 = paramFloat1;
    this.field_616 = paramFloat2;
    this.field_617 = paramFloat3;
  }
  
  public final void set(float[] paramArrayOfFloat)
  {
    this.field_615 = paramArrayOfFloat[0];
    this.field_616 = paramArrayOfFloat[1];
    this.field_617 = paramArrayOfFloat[2];
  }
  
  public final void set(Tuple3f paramTuple3f)
  {
    this.field_615 = paramTuple3f.field_615;
    this.field_616 = paramTuple3f.field_616;
    this.field_617 = paramTuple3f.field_617;
  }
  
  public final void set(Tuple3d paramTuple3d)
  {
    this.field_615 = ((float)paramTuple3d.field_612);
    this.field_616 = ((float)paramTuple3d.field_613);
    this.field_617 = ((float)paramTuple3d.field_614);
  }
  
  public final void get(float[] paramArrayOfFloat)
  {
    paramArrayOfFloat[0] = this.field_615;
    paramArrayOfFloat[1] = this.field_616;
    paramArrayOfFloat[2] = this.field_617;
  }
  
  public final void get(Tuple3f paramTuple3f)
  {
    paramTuple3f.field_615 = this.field_615;
    paramTuple3f.field_616 = this.field_616;
    paramTuple3f.field_617 = this.field_617;
  }
  
  public final void add(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2)
  {
    paramTuple3f1.field_615 += paramTuple3f2.field_615;
    paramTuple3f1.field_616 += paramTuple3f2.field_616;
    paramTuple3f1.field_617 += paramTuple3f2.field_617;
  }
  
  public final void add(Tuple3f paramTuple3f)
  {
    this.field_615 += paramTuple3f.field_615;
    this.field_616 += paramTuple3f.field_616;
    this.field_617 += paramTuple3f.field_617;
  }
  
  public final void sub(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2)
  {
    paramTuple3f1.field_615 -= paramTuple3f2.field_615;
    paramTuple3f1.field_616 -= paramTuple3f2.field_616;
    paramTuple3f1.field_617 -= paramTuple3f2.field_617;
  }
  
  public final void sub(Tuple3f paramTuple3f)
  {
    this.field_615 -= paramTuple3f.field_615;
    this.field_616 -= paramTuple3f.field_616;
    this.field_617 -= paramTuple3f.field_617;
  }
  
  public final void negate(Tuple3f paramTuple3f)
  {
    this.field_615 = (-paramTuple3f.field_615);
    this.field_616 = (-paramTuple3f.field_616);
    this.field_617 = (-paramTuple3f.field_617);
  }
  
  public final void negate()
  {
    this.field_615 = (-this.field_615);
    this.field_616 = (-this.field_616);
    this.field_617 = (-this.field_617);
  }
  
  public final void scale(float paramFloat, Tuple3f paramTuple3f)
  {
    this.field_615 = (paramFloat * paramTuple3f.field_615);
    this.field_616 = (paramFloat * paramTuple3f.field_616);
    this.field_617 = (paramFloat * paramTuple3f.field_617);
  }
  
  public final void scale(float paramFloat)
  {
    this.field_615 *= paramFloat;
    this.field_616 *= paramFloat;
    this.field_617 *= paramFloat;
  }
  
  public final void scaleAdd(float paramFloat, Tuple3f paramTuple3f1, Tuple3f paramTuple3f2)
  {
    this.field_615 = (paramFloat * paramTuple3f1.field_615 + paramTuple3f2.field_615);
    this.field_616 = (paramFloat * paramTuple3f1.field_616 + paramTuple3f2.field_616);
    this.field_617 = (paramFloat * paramTuple3f1.field_617 + paramTuple3f2.field_617);
  }
  
  public final void scaleAdd(float paramFloat, Tuple3f paramTuple3f)
  {
    this.field_615 = (paramFloat * this.field_615 + paramTuple3f.field_615);
    this.field_616 = (paramFloat * this.field_616 + paramTuple3f.field_616);
    this.field_617 = (paramFloat * this.field_617 + paramTuple3f.field_617);
  }
  
  public boolean equals(Tuple3f paramTuple3f)
  {
    try
    {
      return (this.field_615 == paramTuple3f.field_615) && (this.field_616 == paramTuple3f.field_616) && (this.field_617 == paramTuple3f.field_617);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple3f localTuple3f = (Tuple3f)paramObject;
      return (this.field_615 == localTuple3f.field_615) && (this.field_616 == localTuple3f.field_616) && (this.field_617 == localTuple3f.field_617);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException) {}
    return false;
  }
  
  public boolean epsilonEquals(Tuple3f paramTuple3f, float paramFloat)
  {
    float f = this.field_615 - paramTuple3f.field_615;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.field_616 - paramTuple3f.field_616;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.field_617 - paramTuple3f.field_617;
    return (f < 0.0F ? -f : f) <= paramFloat;
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_615);
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_616);
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_617);
    return (int)(l ^ l >> 32);
  }
  
  public final void clamp(float paramFloat1, float paramFloat2, Tuple3f paramTuple3f)
  {
    if (paramTuple3f.field_615 > paramFloat2) {
      this.field_615 = paramFloat2;
    } else if (paramTuple3f.field_615 < paramFloat1) {
      this.field_615 = paramFloat1;
    } else {
      this.field_615 = paramTuple3f.field_615;
    }
    if (paramTuple3f.field_616 > paramFloat2) {
      this.field_616 = paramFloat2;
    } else if (paramTuple3f.field_616 < paramFloat1) {
      this.field_616 = paramFloat1;
    } else {
      this.field_616 = paramTuple3f.field_616;
    }
    if (paramTuple3f.field_617 > paramFloat2) {
      this.field_617 = paramFloat2;
    } else if (paramTuple3f.field_617 < paramFloat1) {
      this.field_617 = paramFloat1;
    } else {
      this.field_617 = paramTuple3f.field_617;
    }
  }
  
  public final void clampMin(float paramFloat, Tuple3f paramTuple3f)
  {
    if (paramTuple3f.field_615 < paramFloat) {
      this.field_615 = paramFloat;
    } else {
      this.field_615 = paramTuple3f.field_615;
    }
    if (paramTuple3f.field_616 < paramFloat) {
      this.field_616 = paramFloat;
    } else {
      this.field_616 = paramTuple3f.field_616;
    }
    if (paramTuple3f.field_617 < paramFloat) {
      this.field_617 = paramFloat;
    } else {
      this.field_617 = paramTuple3f.field_617;
    }
  }
  
  public final void clampMax(float paramFloat, Tuple3f paramTuple3f)
  {
    if (paramTuple3f.field_615 > paramFloat) {
      this.field_615 = paramFloat;
    } else {
      this.field_615 = paramTuple3f.field_615;
    }
    if (paramTuple3f.field_616 > paramFloat) {
      this.field_616 = paramFloat;
    } else {
      this.field_616 = paramTuple3f.field_616;
    }
    if (paramTuple3f.field_617 > paramFloat) {
      this.field_617 = paramFloat;
    } else {
      this.field_617 = paramTuple3f.field_617;
    }
  }
  
  public final void absolute(Tuple3f paramTuple3f)
  {
    this.field_615 = Math.abs(paramTuple3f.field_615);
    this.field_616 = Math.abs(paramTuple3f.field_616);
    this.field_617 = Math.abs(paramTuple3f.field_617);
  }
  
  public final void clamp(float paramFloat1, float paramFloat2)
  {
    if (this.field_615 > paramFloat2) {
      this.field_615 = paramFloat2;
    } else if (this.field_615 < paramFloat1) {
      this.field_615 = paramFloat1;
    }
    if (this.field_616 > paramFloat2) {
      this.field_616 = paramFloat2;
    } else if (this.field_616 < paramFloat1) {
      this.field_616 = paramFloat1;
    }
    if (this.field_617 > paramFloat2) {
      this.field_617 = paramFloat2;
    } else if (this.field_617 < paramFloat1) {
      this.field_617 = paramFloat1;
    }
  }
  
  public final void clampMin(float paramFloat)
  {
    if (this.field_615 < paramFloat) {
      this.field_615 = paramFloat;
    }
    if (this.field_616 < paramFloat) {
      this.field_616 = paramFloat;
    }
    if (this.field_617 < paramFloat) {
      this.field_617 = paramFloat;
    }
  }
  
  public final void clampMax(float paramFloat)
  {
    if (this.field_615 > paramFloat) {
      this.field_615 = paramFloat;
    }
    if (this.field_616 > paramFloat) {
      this.field_616 = paramFloat;
    }
    if (this.field_617 > paramFloat) {
      this.field_617 = paramFloat;
    }
  }
  
  public final void absolute()
  {
    this.field_615 = Math.abs(this.field_615);
    this.field_616 = Math.abs(this.field_616);
    this.field_617 = Math.abs(this.field_617);
  }
  
  public final void interpolate(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2, float paramFloat)
  {
    this.field_615 = ((1.0F - paramFloat) * paramTuple3f1.field_615 + paramFloat * paramTuple3f2.field_615);
    this.field_616 = ((1.0F - paramFloat) * paramTuple3f1.field_616 + paramFloat * paramTuple3f2.field_616);
    this.field_617 = ((1.0F - paramFloat) * paramTuple3f1.field_617 + paramFloat * paramTuple3f2.field_617);
  }
  
  public final void interpolate(Tuple3f paramTuple3f, float paramFloat)
  {
    this.field_615 = ((1.0F - paramFloat) * this.field_615 + paramFloat * paramTuple3f.field_615);
    this.field_616 = ((1.0F - paramFloat) * this.field_616 + paramFloat * paramTuple3f.field_616);
    this.field_617 = ((1.0F - paramFloat) * this.field_617 + paramFloat * paramTuple3f.field_617);
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
 * Qualified Name:     javax.vecmath.Tuple3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */