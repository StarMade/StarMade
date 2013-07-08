package javax.vecmath;

import java.io.Serializable;

public class Matrix3f
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 329697160112089834L;
  public float m00;
  public float m01;
  public float m02;
  public float m10;
  public float m11;
  public float m12;
  public float m20;
  public float m21;
  public float m22;
  private static final double EPS = 1.0E-008D;
  
  public Matrix3f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9)
  {
    this.m00 = paramFloat1;
    this.m01 = paramFloat2;
    this.m02 = paramFloat3;
    this.m10 = paramFloat4;
    this.m11 = paramFloat5;
    this.m12 = paramFloat6;
    this.m20 = paramFloat7;
    this.m21 = paramFloat8;
    this.m22 = paramFloat9;
  }
  
  public Matrix3f(float[] paramArrayOfFloat)
  {
    this.m00 = paramArrayOfFloat[0];
    this.m01 = paramArrayOfFloat[1];
    this.m02 = paramArrayOfFloat[2];
    this.m10 = paramArrayOfFloat[3];
    this.m11 = paramArrayOfFloat[4];
    this.m12 = paramArrayOfFloat[5];
    this.m20 = paramArrayOfFloat[6];
    this.m21 = paramArrayOfFloat[7];
    this.m22 = paramArrayOfFloat[8];
  }
  
  public Matrix3f(Matrix3d paramMatrix3d)
  {
    this.m00 = ((float)paramMatrix3d.m00);
    this.m01 = ((float)paramMatrix3d.m01);
    this.m02 = ((float)paramMatrix3d.m02);
    this.m10 = ((float)paramMatrix3d.m10);
    this.m11 = ((float)paramMatrix3d.m11);
    this.m12 = ((float)paramMatrix3d.m12);
    this.m20 = ((float)paramMatrix3d.m20);
    this.m21 = ((float)paramMatrix3d.m21);
    this.m22 = ((float)paramMatrix3d.m22);
  }
  
  public Matrix3f(Matrix3f paramMatrix3f)
  {
    this.m00 = paramMatrix3f.m00;
    this.m01 = paramMatrix3f.m01;
    this.m02 = paramMatrix3f.m02;
    this.m10 = paramMatrix3f.m10;
    this.m11 = paramMatrix3f.m11;
    this.m12 = paramMatrix3f.m12;
    this.m20 = paramMatrix3f.m20;
    this.m21 = paramMatrix3f.m21;
    this.m22 = paramMatrix3f.m22;
  }
  
  public Matrix3f()
  {
    this.m00 = 0.0F;
    this.m01 = 0.0F;
    this.m02 = 0.0F;
    this.m10 = 0.0F;
    this.m11 = 0.0F;
    this.m12 = 0.0F;
    this.m20 = 0.0F;
    this.m21 = 0.0F;
    this.m22 = 0.0F;
  }
  
  public String toString()
  {
    return this.m00 + ", " + this.m01 + ", " + this.m02 + "\n" + this.m10 + ", " + this.m11 + ", " + this.m12 + "\n" + this.m20 + ", " + this.m21 + ", " + this.m22 + "\n";
  }
  
  public final void setIdentity()
  {
    this.m00 = 1.0F;
    this.m01 = 0.0F;
    this.m02 = 0.0F;
    this.m10 = 0.0F;
    this.m11 = 1.0F;
    this.m12 = 0.0F;
    this.m20 = 0.0F;
    this.m21 = 0.0F;
    this.m22 = 1.0F;
  }
  
  public final void setScale(float paramFloat)
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    this.m00 = ((float)(arrayOfDouble1[0] * paramFloat));
    this.m01 = ((float)(arrayOfDouble1[1] * paramFloat));
    this.m02 = ((float)(arrayOfDouble1[2] * paramFloat));
    this.m10 = ((float)(arrayOfDouble1[3] * paramFloat));
    this.m11 = ((float)(arrayOfDouble1[4] * paramFloat));
    this.m12 = ((float)(arrayOfDouble1[5] * paramFloat));
    this.m20 = ((float)(arrayOfDouble1[6] * paramFloat));
    this.m21 = ((float)(arrayOfDouble1[7] * paramFloat));
    this.m22 = ((float)(arrayOfDouble1[8] * paramFloat));
  }
  
  public final void setElement(int paramInt1, int paramInt2, float paramFloat)
  {
    switch (paramInt1)
    {
    case 0: 
      switch (paramInt2)
      {
      case 0: 
        this.m00 = paramFloat;
        break;
      case 1: 
        this.m01 = paramFloat;
        break;
      case 2: 
        this.m02 = paramFloat;
        break;
      default: 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
      }
      break;
    case 1: 
      switch (paramInt2)
      {
      case 0: 
        this.m10 = paramFloat;
        break;
      case 1: 
        this.m11 = paramFloat;
        break;
      case 2: 
        this.m12 = paramFloat;
        break;
      default: 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
      }
      break;
    case 2: 
      switch (paramInt2)
      {
      case 0: 
        this.m20 = paramFloat;
        break;
      case 1: 
        this.m21 = paramFloat;
        break;
      case 2: 
        this.m22 = paramFloat;
        break;
      default: 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
      }
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f0"));
    }
  }
  
  public final void getRow(int paramInt, Vector3f paramVector3f)
  {
    if (paramInt == 0)
    {
      paramVector3f.field_615 = this.m00;
      paramVector3f.field_616 = this.m01;
      paramVector3f.field_617 = this.m02;
    }
    else if (paramInt == 1)
    {
      paramVector3f.field_615 = this.m10;
      paramVector3f.field_616 = this.m11;
      paramVector3f.field_617 = this.m12;
    }
    else if (paramInt == 2)
    {
      paramVector3f.field_615 = this.m20;
      paramVector3f.field_616 = this.m21;
      paramVector3f.field_617 = this.m22;
    }
    else
    {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f1"));
    }
  }
  
  public final void getRow(int paramInt, float[] paramArrayOfFloat)
  {
    if (paramInt == 0)
    {
      paramArrayOfFloat[0] = this.m00;
      paramArrayOfFloat[1] = this.m01;
      paramArrayOfFloat[2] = this.m02;
    }
    else if (paramInt == 1)
    {
      paramArrayOfFloat[0] = this.m10;
      paramArrayOfFloat[1] = this.m11;
      paramArrayOfFloat[2] = this.m12;
    }
    else if (paramInt == 2)
    {
      paramArrayOfFloat[0] = this.m20;
      paramArrayOfFloat[1] = this.m21;
      paramArrayOfFloat[2] = this.m22;
    }
    else
    {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f1"));
    }
  }
  
  public final void getColumn(int paramInt, Vector3f paramVector3f)
  {
    if (paramInt == 0)
    {
      paramVector3f.field_615 = this.m00;
      paramVector3f.field_616 = this.m10;
      paramVector3f.field_617 = this.m20;
    }
    else if (paramInt == 1)
    {
      paramVector3f.field_615 = this.m01;
      paramVector3f.field_616 = this.m11;
      paramVector3f.field_617 = this.m21;
    }
    else if (paramInt == 2)
    {
      paramVector3f.field_615 = this.m02;
      paramVector3f.field_616 = this.m12;
      paramVector3f.field_617 = this.m22;
    }
    else
    {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f3"));
    }
  }
  
  public final void getColumn(int paramInt, float[] paramArrayOfFloat)
  {
    if (paramInt == 0)
    {
      paramArrayOfFloat[0] = this.m00;
      paramArrayOfFloat[1] = this.m10;
      paramArrayOfFloat[2] = this.m20;
    }
    else if (paramInt == 1)
    {
      paramArrayOfFloat[0] = this.m01;
      paramArrayOfFloat[1] = this.m11;
      paramArrayOfFloat[2] = this.m21;
    }
    else if (paramInt == 2)
    {
      paramArrayOfFloat[0] = this.m02;
      paramArrayOfFloat[1] = this.m12;
      paramArrayOfFloat[2] = this.m22;
    }
    else
    {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f3"));
    }
  }
  
  public final float getElement(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    case 0: 
      switch (paramInt2)
      {
      case 0: 
        return this.m00;
      case 1: 
        return this.m01;
      case 2: 
        return this.m02;
      }
      break;
    case 1: 
      switch (paramInt2)
      {
      case 0: 
        return this.m10;
      case 1: 
        return this.m11;
      case 2: 
        return this.m12;
      }
      break;
    case 2: 
      switch (paramInt2)
      {
      case 0: 
        return this.m20;
      case 1: 
        return this.m21;
      case 2: 
        return this.m22;
      }
      break;
    }
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f5"));
  }
  
  public final void setRow(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramFloat1;
      this.m01 = paramFloat2;
      this.m02 = paramFloat3;
      break;
    case 1: 
      this.m10 = paramFloat1;
      this.m11 = paramFloat2;
      this.m12 = paramFloat3;
      break;
    case 2: 
      this.m20 = paramFloat1;
      this.m21 = paramFloat2;
      this.m22 = paramFloat3;
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f6"));
    }
  }
  
  public final void setRow(int paramInt, Vector3f paramVector3f)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramVector3f.field_615;
      this.m01 = paramVector3f.field_616;
      this.m02 = paramVector3f.field_617;
      break;
    case 1: 
      this.m10 = paramVector3f.field_615;
      this.m11 = paramVector3f.field_616;
      this.m12 = paramVector3f.field_617;
      break;
    case 2: 
      this.m20 = paramVector3f.field_615;
      this.m21 = paramVector3f.field_616;
      this.m22 = paramVector3f.field_617;
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f6"));
    }
  }
  
  public final void setRow(int paramInt, float[] paramArrayOfFloat)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramArrayOfFloat[0];
      this.m01 = paramArrayOfFloat[1];
      this.m02 = paramArrayOfFloat[2];
      break;
    case 1: 
      this.m10 = paramArrayOfFloat[0];
      this.m11 = paramArrayOfFloat[1];
      this.m12 = paramArrayOfFloat[2];
      break;
    case 2: 
      this.m20 = paramArrayOfFloat[0];
      this.m21 = paramArrayOfFloat[1];
      this.m22 = paramArrayOfFloat[2];
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f6"));
    }
  }
  
  public final void setColumn(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramFloat1;
      this.m10 = paramFloat2;
      this.m20 = paramFloat3;
      break;
    case 1: 
      this.m01 = paramFloat1;
      this.m11 = paramFloat2;
      this.m21 = paramFloat3;
      break;
    case 2: 
      this.m02 = paramFloat1;
      this.m12 = paramFloat2;
      this.m22 = paramFloat3;
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f9"));
    }
  }
  
  public final void setColumn(int paramInt, Vector3f paramVector3f)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramVector3f.field_615;
      this.m10 = paramVector3f.field_616;
      this.m20 = paramVector3f.field_617;
      break;
    case 1: 
      this.m01 = paramVector3f.field_615;
      this.m11 = paramVector3f.field_616;
      this.m21 = paramVector3f.field_617;
      break;
    case 2: 
      this.m02 = paramVector3f.field_615;
      this.m12 = paramVector3f.field_616;
      this.m22 = paramVector3f.field_617;
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f9"));
    }
  }
  
  public final void setColumn(int paramInt, float[] paramArrayOfFloat)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramArrayOfFloat[0];
      this.m10 = paramArrayOfFloat[1];
      this.m20 = paramArrayOfFloat[2];
      break;
    case 1: 
      this.m01 = paramArrayOfFloat[0];
      this.m11 = paramArrayOfFloat[1];
      this.m21 = paramArrayOfFloat[2];
      break;
    case 2: 
      this.m02 = paramArrayOfFloat[0];
      this.m12 = paramArrayOfFloat[1];
      this.m22 = paramArrayOfFloat[2];
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3f9"));
    }
  }
  
  public final float getScale()
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    return (float)Matrix3d.max3(arrayOfDouble2);
  }
  
  public final void add(float paramFloat)
  {
    this.m00 += paramFloat;
    this.m01 += paramFloat;
    this.m02 += paramFloat;
    this.m10 += paramFloat;
    this.m11 += paramFloat;
    this.m12 += paramFloat;
    this.m20 += paramFloat;
    this.m21 += paramFloat;
    this.m22 += paramFloat;
  }
  
  public final void add(float paramFloat, Matrix3f paramMatrix3f)
  {
    paramMatrix3f.m00 += paramFloat;
    paramMatrix3f.m01 += paramFloat;
    paramMatrix3f.m02 += paramFloat;
    paramMatrix3f.m10 += paramFloat;
    paramMatrix3f.m11 += paramFloat;
    paramMatrix3f.m12 += paramFloat;
    paramMatrix3f.m20 += paramFloat;
    paramMatrix3f.m21 += paramFloat;
    paramMatrix3f.m22 += paramFloat;
  }
  
  public final void add(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2)
  {
    paramMatrix3f1.m00 += paramMatrix3f2.m00;
    paramMatrix3f1.m01 += paramMatrix3f2.m01;
    paramMatrix3f1.m02 += paramMatrix3f2.m02;
    paramMatrix3f1.m10 += paramMatrix3f2.m10;
    paramMatrix3f1.m11 += paramMatrix3f2.m11;
    paramMatrix3f1.m12 += paramMatrix3f2.m12;
    paramMatrix3f1.m20 += paramMatrix3f2.m20;
    paramMatrix3f1.m21 += paramMatrix3f2.m21;
    paramMatrix3f1.m22 += paramMatrix3f2.m22;
  }
  
  public final void add(Matrix3f paramMatrix3f)
  {
    this.m00 += paramMatrix3f.m00;
    this.m01 += paramMatrix3f.m01;
    this.m02 += paramMatrix3f.m02;
    this.m10 += paramMatrix3f.m10;
    this.m11 += paramMatrix3f.m11;
    this.m12 += paramMatrix3f.m12;
    this.m20 += paramMatrix3f.m20;
    this.m21 += paramMatrix3f.m21;
    this.m22 += paramMatrix3f.m22;
  }
  
  public final void sub(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2)
  {
    paramMatrix3f1.m00 -= paramMatrix3f2.m00;
    paramMatrix3f1.m01 -= paramMatrix3f2.m01;
    paramMatrix3f1.m02 -= paramMatrix3f2.m02;
    paramMatrix3f1.m10 -= paramMatrix3f2.m10;
    paramMatrix3f1.m11 -= paramMatrix3f2.m11;
    paramMatrix3f1.m12 -= paramMatrix3f2.m12;
    paramMatrix3f1.m20 -= paramMatrix3f2.m20;
    paramMatrix3f1.m21 -= paramMatrix3f2.m21;
    paramMatrix3f1.m22 -= paramMatrix3f2.m22;
  }
  
  public final void sub(Matrix3f paramMatrix3f)
  {
    this.m00 -= paramMatrix3f.m00;
    this.m01 -= paramMatrix3f.m01;
    this.m02 -= paramMatrix3f.m02;
    this.m10 -= paramMatrix3f.m10;
    this.m11 -= paramMatrix3f.m11;
    this.m12 -= paramMatrix3f.m12;
    this.m20 -= paramMatrix3f.m20;
    this.m21 -= paramMatrix3f.m21;
    this.m22 -= paramMatrix3f.m22;
  }
  
  public final void transpose()
  {
    float f = this.m10;
    this.m10 = this.m01;
    this.m01 = f;
    f = this.m20;
    this.m20 = this.m02;
    this.m02 = f;
    f = this.m21;
    this.m21 = this.m12;
    this.m12 = f;
  }
  
  public final void transpose(Matrix3f paramMatrix3f)
  {
    if (this != paramMatrix3f)
    {
      this.m00 = paramMatrix3f.m00;
      this.m01 = paramMatrix3f.m10;
      this.m02 = paramMatrix3f.m20;
      this.m10 = paramMatrix3f.m01;
      this.m11 = paramMatrix3f.m11;
      this.m12 = paramMatrix3f.m21;
      this.m20 = paramMatrix3f.m02;
      this.m21 = paramMatrix3f.m12;
      this.m22 = paramMatrix3f.m22;
    }
    else
    {
      transpose();
    }
  }
  
  public final void set(Quat4f paramQuat4f)
  {
    this.m00 = (1.0F - 2.0F * paramQuat4f.field_597 * paramQuat4f.field_597 - 2.0F * paramQuat4f.field_598 * paramQuat4f.field_598);
    this.m10 = (2.0F * (paramQuat4f.field_596 * paramQuat4f.field_597 + paramQuat4f.field_599 * paramQuat4f.field_598));
    this.m20 = (2.0F * (paramQuat4f.field_596 * paramQuat4f.field_598 - paramQuat4f.field_599 * paramQuat4f.field_597));
    this.m01 = (2.0F * (paramQuat4f.field_596 * paramQuat4f.field_597 - paramQuat4f.field_599 * paramQuat4f.field_598));
    this.m11 = (1.0F - 2.0F * paramQuat4f.field_596 * paramQuat4f.field_596 - 2.0F * paramQuat4f.field_598 * paramQuat4f.field_598);
    this.m21 = (2.0F * (paramQuat4f.field_597 * paramQuat4f.field_598 + paramQuat4f.field_599 * paramQuat4f.field_596));
    this.m02 = (2.0F * (paramQuat4f.field_596 * paramQuat4f.field_598 + paramQuat4f.field_599 * paramQuat4f.field_597));
    this.m12 = (2.0F * (paramQuat4f.field_597 * paramQuat4f.field_598 - paramQuat4f.field_599 * paramQuat4f.field_596));
    this.m22 = (1.0F - 2.0F * paramQuat4f.field_596 * paramQuat4f.field_596 - 2.0F * paramQuat4f.field_597 * paramQuat4f.field_597);
  }
  
  public final void set(AxisAngle4f paramAxisAngle4f)
  {
    float f1 = (float)Math.sqrt(paramAxisAngle4f.field_586 * paramAxisAngle4f.field_586 + paramAxisAngle4f.field_587 * paramAxisAngle4f.field_587 + paramAxisAngle4f.field_588 * paramAxisAngle4f.field_588);
    if (f1 < 1.0E-008D)
    {
      this.m00 = 1.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = 1.0F;
      this.m12 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 1.0F;
    }
    else
    {
      f1 = 1.0F / f1;
      float f2 = paramAxisAngle4f.field_586 * f1;
      float f3 = paramAxisAngle4f.field_587 * f1;
      float f4 = paramAxisAngle4f.field_588 * f1;
      float f5 = (float)Math.sin(paramAxisAngle4f.angle);
      float f6 = (float)Math.cos(paramAxisAngle4f.angle);
      float f7 = 1.0F - f6;
      float f8 = f2 * f4;
      float f9 = f2 * f3;
      float f10 = f3 * f4;
      this.m00 = (f7 * f2 * f2 + f6);
      this.m01 = (f7 * f9 - f5 * f4);
      this.m02 = (f7 * f8 + f5 * f3);
      this.m10 = (f7 * f9 + f5 * f4);
      this.m11 = (f7 * f3 * f3 + f6);
      this.m12 = (f7 * f10 - f5 * f2);
      this.m20 = (f7 * f8 - f5 * f3);
      this.m21 = (f7 * f10 + f5 * f2);
      this.m22 = (f7 * f4 * f4 + f6);
    }
  }
  
  public final void set(AxisAngle4d paramAxisAngle4d)
  {
    double d1 = Math.sqrt(paramAxisAngle4d.field_589 * paramAxisAngle4d.field_589 + paramAxisAngle4d.field_590 * paramAxisAngle4d.field_590 + paramAxisAngle4d.field_591 * paramAxisAngle4d.field_591);
    if (d1 < 1.0E-008D)
    {
      this.m00 = 1.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = 1.0F;
      this.m12 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 1.0F;
    }
    else
    {
      d1 = 1.0D / d1;
      double d2 = paramAxisAngle4d.field_589 * d1;
      double d3 = paramAxisAngle4d.field_590 * d1;
      double d4 = paramAxisAngle4d.field_591 * d1;
      double d5 = Math.sin(paramAxisAngle4d.angle);
      double d6 = Math.cos(paramAxisAngle4d.angle);
      double d7 = 1.0D - d6;
      double d8 = d2 * d4;
      double d9 = d2 * d3;
      double d10 = d3 * d4;
      this.m00 = ((float)(d7 * d2 * d2 + d6));
      this.m01 = ((float)(d7 * d9 - d5 * d4));
      this.m02 = ((float)(d7 * d8 + d5 * d3));
      this.m10 = ((float)(d7 * d9 + d5 * d4));
      this.m11 = ((float)(d7 * d3 * d3 + d6));
      this.m12 = ((float)(d7 * d10 - d5 * d2));
      this.m20 = ((float)(d7 * d8 - d5 * d3));
      this.m21 = ((float)(d7 * d10 + d5 * d2));
      this.m22 = ((float)(d7 * d4 * d4 + d6));
    }
  }
  
  public final void set(Quat4d paramQuat4d)
  {
    this.m00 = ((float)(1.0D - 2.0D * paramQuat4d.field_601 * paramQuat4d.field_601 - 2.0D * paramQuat4d.field_602 * paramQuat4d.field_602));
    this.m10 = ((float)(2.0D * (paramQuat4d.field_600 * paramQuat4d.field_601 + paramQuat4d.field_603 * paramQuat4d.field_602)));
    this.m20 = ((float)(2.0D * (paramQuat4d.field_600 * paramQuat4d.field_602 - paramQuat4d.field_603 * paramQuat4d.field_601)));
    this.m01 = ((float)(2.0D * (paramQuat4d.field_600 * paramQuat4d.field_601 - paramQuat4d.field_603 * paramQuat4d.field_602)));
    this.m11 = ((float)(1.0D - 2.0D * paramQuat4d.field_600 * paramQuat4d.field_600 - 2.0D * paramQuat4d.field_602 * paramQuat4d.field_602));
    this.m21 = ((float)(2.0D * (paramQuat4d.field_601 * paramQuat4d.field_602 + paramQuat4d.field_603 * paramQuat4d.field_600)));
    this.m02 = ((float)(2.0D * (paramQuat4d.field_600 * paramQuat4d.field_602 + paramQuat4d.field_603 * paramQuat4d.field_601)));
    this.m12 = ((float)(2.0D * (paramQuat4d.field_601 * paramQuat4d.field_602 - paramQuat4d.field_603 * paramQuat4d.field_600)));
    this.m22 = ((float)(1.0D - 2.0D * paramQuat4d.field_600 * paramQuat4d.field_600 - 2.0D * paramQuat4d.field_601 * paramQuat4d.field_601));
  }
  
  public final void set(float[] paramArrayOfFloat)
  {
    this.m00 = paramArrayOfFloat[0];
    this.m01 = paramArrayOfFloat[1];
    this.m02 = paramArrayOfFloat[2];
    this.m10 = paramArrayOfFloat[3];
    this.m11 = paramArrayOfFloat[4];
    this.m12 = paramArrayOfFloat[5];
    this.m20 = paramArrayOfFloat[6];
    this.m21 = paramArrayOfFloat[7];
    this.m22 = paramArrayOfFloat[8];
  }
  
  public final void set(Matrix3f paramMatrix3f)
  {
    this.m00 = paramMatrix3f.m00;
    this.m01 = paramMatrix3f.m01;
    this.m02 = paramMatrix3f.m02;
    this.m10 = paramMatrix3f.m10;
    this.m11 = paramMatrix3f.m11;
    this.m12 = paramMatrix3f.m12;
    this.m20 = paramMatrix3f.m20;
    this.m21 = paramMatrix3f.m21;
    this.m22 = paramMatrix3f.m22;
  }
  
  public final void set(Matrix3d paramMatrix3d)
  {
    this.m00 = ((float)paramMatrix3d.m00);
    this.m01 = ((float)paramMatrix3d.m01);
    this.m02 = ((float)paramMatrix3d.m02);
    this.m10 = ((float)paramMatrix3d.m10);
    this.m11 = ((float)paramMatrix3d.m11);
    this.m12 = ((float)paramMatrix3d.m12);
    this.m20 = ((float)paramMatrix3d.m20);
    this.m21 = ((float)paramMatrix3d.m21);
    this.m22 = ((float)paramMatrix3d.m22);
  }
  
  public final void invert(Matrix3f paramMatrix3f)
  {
    invertGeneral(paramMatrix3f);
  }
  
  public final void invert()
  {
    invertGeneral(this);
  }
  
  private final void invertGeneral(Matrix3f paramMatrix3f)
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    int[] arrayOfInt = new int[3];
    arrayOfDouble1[0] = paramMatrix3f.m00;
    arrayOfDouble1[1] = paramMatrix3f.m01;
    arrayOfDouble1[2] = paramMatrix3f.m02;
    arrayOfDouble1[3] = paramMatrix3f.m10;
    arrayOfDouble1[4] = paramMatrix3f.m11;
    arrayOfDouble1[5] = paramMatrix3f.m12;
    arrayOfDouble1[6] = paramMatrix3f.m20;
    arrayOfDouble1[7] = paramMatrix3f.m21;
    arrayOfDouble1[8] = paramMatrix3f.m22;
    if (!luDecomposition(arrayOfDouble1, arrayOfInt)) {
      throw new SingularMatrixException(VecMathI18N.getString("Matrix3f12"));
    }
    for (int i = 0; i < 9; i++) {
      arrayOfDouble2[i] = 0.0D;
    }
    arrayOfDouble2[0] = 1.0D;
    arrayOfDouble2[4] = 1.0D;
    arrayOfDouble2[8] = 1.0D;
    luBacksubstitution(arrayOfDouble1, arrayOfInt, arrayOfDouble2);
    this.m00 = ((float)arrayOfDouble2[0]);
    this.m01 = ((float)arrayOfDouble2[1]);
    this.m02 = ((float)arrayOfDouble2[2]);
    this.m10 = ((float)arrayOfDouble2[3]);
    this.m11 = ((float)arrayOfDouble2[4]);
    this.m12 = ((float)arrayOfDouble2[5]);
    this.m20 = ((float)arrayOfDouble2[6]);
    this.m21 = ((float)arrayOfDouble2[7]);
    this.m22 = ((float)arrayOfDouble2[8]);
  }
  
  static boolean luDecomposition(double[] paramArrayOfDouble, int[] paramArrayOfInt)
  {
    double[] arrayOfDouble = new double[3];
    double d2 = 0;
    int j = 0;
    double d1 = 3;
    double d3;
    while (d1-- != 0)
    {
      d3 = 0.0D;
      i = 3;
      while (i-- != 0)
      {
        double d4 = paramArrayOfDouble[(d2++)];
        d4 = Math.abs(d4);
        if (d4 > d3) {
          d3 = d4;
        }
      }
      if (d3 == 0.0D) {
        return false;
      }
      arrayOfDouble[(j++)] = (1.0D / d3);
    }
    int i = 0;
    for (d1 = 0; d1 < 3; d1++)
    {
      int m;
      double d5;
      int n;
      int i1;
      int k;
      for (d2 = 0; d2 < d1; d2++)
      {
        m = i + 3 * d2 + d1;
        d5 = paramArrayOfDouble[m];
        d3 = d2;
        n = i + 3 * d2;
        for (i1 = i + d1; d3-- != 0; i1 += 3)
        {
          d5 -= paramArrayOfDouble[n] * paramArrayOfDouble[i1];
          n++;
        }
        paramArrayOfDouble[m] = d5;
      }
      double d6 = 0.0D;
      j = -1;
      double d7;
      for (d2 = d1; d2 < 3; d2++)
      {
        m = i + 3 * d2 + d1;
        d5 = paramArrayOfDouble[m];
        k = d1;
        n = i + 3 * d2;
        for (i1 = i + d1; k-- != 0; i1 += 3)
        {
          d5 -= paramArrayOfDouble[n] * paramArrayOfDouble[i1];
          n++;
        }
        paramArrayOfDouble[m] = d5;
        if ((d7 = arrayOfDouble[d2] * Math.abs(d5)) >= d6)
        {
          d6 = d7;
          j = d2;
        }
      }
      if (j < 0) {
        throw new RuntimeException(VecMathI18N.getString("Matrix3f13"));
      }
      if (d1 != j)
      {
        k = 3;
        n = i + 3 * j;
        i1 = i + 3 * d1;
        while (k-- != 0)
        {
          d7 = paramArrayOfDouble[n];
          paramArrayOfDouble[(n++)] = paramArrayOfDouble[i1];
          paramArrayOfDouble[(i1++)] = d7;
        }
        arrayOfDouble[j] = arrayOfDouble[d1];
      }
      paramArrayOfInt[d1] = j;
      if (paramArrayOfDouble[(i + 3 * d1 + d1)] == 0.0D) {
        return false;
      }
      if (d1 != 2)
      {
        d7 = 1.0D / paramArrayOfDouble[(i + 3 * d1 + d1)];
        m = i + 3 * (d1 + 1) + d1;
        d2 = 2 - d1;
        while (d2-- != 0)
        {
          paramArrayOfDouble[m] *= d7;
          m += 3;
        }
      }
    }
    return true;
  }
  
  static void luBacksubstitution(double[] paramArrayOfDouble1, int[] paramArrayOfInt, double[] paramArrayOfDouble2)
  {
    int i1 = 0;
    for (int n = 0; n < 3; n++)
    {
      int i2 = n;
      int j = -1;
      for (int i = 0; i < 3; i++)
      {
        int k = paramArrayOfInt[(i1 + i)];
        double d = paramArrayOfDouble2[(i2 + 3 * k)];
        paramArrayOfDouble2[(i2 + 3 * k)] = paramArrayOfDouble2[(i2 + 3 * i)];
        if (j >= 0)
        {
          i3 = i * 3;
          for (int m = j; m <= i - 1; m++) {
            d -= paramArrayOfDouble1[(i3 + m)] * paramArrayOfDouble2[(i2 + 3 * m)];
          }
        }
        if (d != 0.0D) {
          j = i;
        }
        paramArrayOfDouble2[(i2 + 3 * i)] = d;
      }
      int i3 = 6;
      paramArrayOfDouble2[(i2 + 6)] /= paramArrayOfDouble1[(i3 + 2)];
      i3 -= 3;
      paramArrayOfDouble2[(i2 + 3)] = ((paramArrayOfDouble2[(i2 + 3)] - paramArrayOfDouble1[(i3 + 2)] * paramArrayOfDouble2[(i2 + 6)]) / paramArrayOfDouble1[(i3 + 1)]);
      i3 -= 3;
      paramArrayOfDouble2[(i2 + 0)] = ((paramArrayOfDouble2[(i2 + 0)] - paramArrayOfDouble1[(i3 + 1)] * paramArrayOfDouble2[(i2 + 3)] - paramArrayOfDouble1[(i3 + 2)] * paramArrayOfDouble2[(i2 + 6)]) / paramArrayOfDouble1[(i3 + 0)]);
    }
  }
  
  public final float determinant()
  {
    float f = this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) + this.m01 * (this.m12 * this.m20 - this.m10 * this.m22) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20);
    return f;
  }
  
  public final void set(float paramFloat)
  {
    this.m00 = paramFloat;
    this.m01 = 0.0F;
    this.m02 = 0.0F;
    this.m10 = 0.0F;
    this.m11 = paramFloat;
    this.m12 = 0.0F;
    this.m20 = 0.0F;
    this.m21 = 0.0F;
    this.m22 = paramFloat;
  }
  
  public final void rotX(float paramFloat)
  {
    float f1 = (float)Math.sin(paramFloat);
    float f2 = (float)Math.cos(paramFloat);
    this.m00 = 1.0F;
    this.m01 = 0.0F;
    this.m02 = 0.0F;
    this.m10 = 0.0F;
    this.m11 = f2;
    this.m12 = (-f1);
    this.m20 = 0.0F;
    this.m21 = f1;
    this.m22 = f2;
  }
  
  public final void rotY(float paramFloat)
  {
    float f1 = (float)Math.sin(paramFloat);
    float f2 = (float)Math.cos(paramFloat);
    this.m00 = f2;
    this.m01 = 0.0F;
    this.m02 = f1;
    this.m10 = 0.0F;
    this.m11 = 1.0F;
    this.m12 = 0.0F;
    this.m20 = (-f1);
    this.m21 = 0.0F;
    this.m22 = f2;
  }
  
  public final void rotZ(float paramFloat)
  {
    float f1 = (float)Math.sin(paramFloat);
    float f2 = (float)Math.cos(paramFloat);
    this.m00 = f2;
    this.m01 = (-f1);
    this.m02 = 0.0F;
    this.m10 = f1;
    this.m11 = f2;
    this.m12 = 0.0F;
    this.m20 = 0.0F;
    this.m21 = 0.0F;
    this.m22 = 1.0F;
  }
  
  public final void mul(float paramFloat)
  {
    this.m00 *= paramFloat;
    this.m01 *= paramFloat;
    this.m02 *= paramFloat;
    this.m10 *= paramFloat;
    this.m11 *= paramFloat;
    this.m12 *= paramFloat;
    this.m20 *= paramFloat;
    this.m21 *= paramFloat;
    this.m22 *= paramFloat;
  }
  
  public final void mul(float paramFloat, Matrix3f paramMatrix3f)
  {
    this.m00 = (paramFloat * paramMatrix3f.m00);
    this.m01 = (paramFloat * paramMatrix3f.m01);
    this.m02 = (paramFloat * paramMatrix3f.m02);
    this.m10 = (paramFloat * paramMatrix3f.m10);
    this.m11 = (paramFloat * paramMatrix3f.m11);
    this.m12 = (paramFloat * paramMatrix3f.m12);
    this.m20 = (paramFloat * paramMatrix3f.m20);
    this.m21 = (paramFloat * paramMatrix3f.m21);
    this.m22 = (paramFloat * paramMatrix3f.m22);
  }
  
  public final void mul(Matrix3f paramMatrix3f)
  {
    float f1 = this.m00 * paramMatrix3f.m00 + this.m01 * paramMatrix3f.m10 + this.m02 * paramMatrix3f.m20;
    float f2 = this.m00 * paramMatrix3f.m01 + this.m01 * paramMatrix3f.m11 + this.m02 * paramMatrix3f.m21;
    float f3 = this.m00 * paramMatrix3f.m02 + this.m01 * paramMatrix3f.m12 + this.m02 * paramMatrix3f.m22;
    float f4 = this.m10 * paramMatrix3f.m00 + this.m11 * paramMatrix3f.m10 + this.m12 * paramMatrix3f.m20;
    float f5 = this.m10 * paramMatrix3f.m01 + this.m11 * paramMatrix3f.m11 + this.m12 * paramMatrix3f.m21;
    float f6 = this.m10 * paramMatrix3f.m02 + this.m11 * paramMatrix3f.m12 + this.m12 * paramMatrix3f.m22;
    float f7 = this.m20 * paramMatrix3f.m00 + this.m21 * paramMatrix3f.m10 + this.m22 * paramMatrix3f.m20;
    float f8 = this.m20 * paramMatrix3f.m01 + this.m21 * paramMatrix3f.m11 + this.m22 * paramMatrix3f.m21;
    float f9 = this.m20 * paramMatrix3f.m02 + this.m21 * paramMatrix3f.m12 + this.m22 * paramMatrix3f.m22;
    this.m00 = f1;
    this.m01 = f2;
    this.m02 = f3;
    this.m10 = f4;
    this.m11 = f5;
    this.m12 = f6;
    this.m20 = f7;
    this.m21 = f8;
    this.m22 = f9;
  }
  
  public final void mul(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2)
  {
    if ((this != paramMatrix3f1) && (this != paramMatrix3f2))
    {
      this.m00 = (paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m01 * paramMatrix3f2.m10 + paramMatrix3f1.m02 * paramMatrix3f2.m20);
      this.m01 = (paramMatrix3f1.m00 * paramMatrix3f2.m01 + paramMatrix3f1.m01 * paramMatrix3f2.m11 + paramMatrix3f1.m02 * paramMatrix3f2.m21);
      this.m02 = (paramMatrix3f1.m00 * paramMatrix3f2.m02 + paramMatrix3f1.m01 * paramMatrix3f2.m12 + paramMatrix3f1.m02 * paramMatrix3f2.m22);
      this.m10 = (paramMatrix3f1.m10 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m10 + paramMatrix3f1.m12 * paramMatrix3f2.m20);
      this.m11 = (paramMatrix3f1.m10 * paramMatrix3f2.m01 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m12 * paramMatrix3f2.m21);
      this.m12 = (paramMatrix3f1.m10 * paramMatrix3f2.m02 + paramMatrix3f1.m11 * paramMatrix3f2.m12 + paramMatrix3f1.m12 * paramMatrix3f2.m22);
      this.m20 = (paramMatrix3f1.m20 * paramMatrix3f2.m00 + paramMatrix3f1.m21 * paramMatrix3f2.m10 + paramMatrix3f1.m22 * paramMatrix3f2.m20);
      this.m21 = (paramMatrix3f1.m20 * paramMatrix3f2.m01 + paramMatrix3f1.m21 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m21);
      this.m22 = (paramMatrix3f1.m20 * paramMatrix3f2.m02 + paramMatrix3f1.m21 * paramMatrix3f2.m12 + paramMatrix3f1.m22 * paramMatrix3f2.m22);
    }
    else
    {
      float f1 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m01 * paramMatrix3f2.m10 + paramMatrix3f1.m02 * paramMatrix3f2.m20;
      float f2 = paramMatrix3f1.m00 * paramMatrix3f2.m01 + paramMatrix3f1.m01 * paramMatrix3f2.m11 + paramMatrix3f1.m02 * paramMatrix3f2.m21;
      float f3 = paramMatrix3f1.m00 * paramMatrix3f2.m02 + paramMatrix3f1.m01 * paramMatrix3f2.m12 + paramMatrix3f1.m02 * paramMatrix3f2.m22;
      float f4 = paramMatrix3f1.m10 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m10 + paramMatrix3f1.m12 * paramMatrix3f2.m20;
      float f5 = paramMatrix3f1.m10 * paramMatrix3f2.m01 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m12 * paramMatrix3f2.m21;
      float f6 = paramMatrix3f1.m10 * paramMatrix3f2.m02 + paramMatrix3f1.m11 * paramMatrix3f2.m12 + paramMatrix3f1.m12 * paramMatrix3f2.m22;
      float f7 = paramMatrix3f1.m20 * paramMatrix3f2.m00 + paramMatrix3f1.m21 * paramMatrix3f2.m10 + paramMatrix3f1.m22 * paramMatrix3f2.m20;
      float f8 = paramMatrix3f1.m20 * paramMatrix3f2.m01 + paramMatrix3f1.m21 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m21;
      float f9 = paramMatrix3f1.m20 * paramMatrix3f2.m02 + paramMatrix3f1.m21 * paramMatrix3f2.m12 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
      this.m00 = f1;
      this.m01 = f2;
      this.m02 = f3;
      this.m10 = f4;
      this.m11 = f5;
      this.m12 = f6;
      this.m20 = f7;
      this.m21 = f8;
      this.m22 = f9;
    }
  }
  
  public final void mulNormalize(Matrix3f paramMatrix3f)
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    double[] arrayOfDouble3 = new double[3];
    arrayOfDouble1[0] = (this.m00 * paramMatrix3f.m00 + this.m01 * paramMatrix3f.m10 + this.m02 * paramMatrix3f.m20);
    arrayOfDouble1[1] = (this.m00 * paramMatrix3f.m01 + this.m01 * paramMatrix3f.m11 + this.m02 * paramMatrix3f.m21);
    arrayOfDouble1[2] = (this.m00 * paramMatrix3f.m02 + this.m01 * paramMatrix3f.m12 + this.m02 * paramMatrix3f.m22);
    arrayOfDouble1[3] = (this.m10 * paramMatrix3f.m00 + this.m11 * paramMatrix3f.m10 + this.m12 * paramMatrix3f.m20);
    arrayOfDouble1[4] = (this.m10 * paramMatrix3f.m01 + this.m11 * paramMatrix3f.m11 + this.m12 * paramMatrix3f.m21);
    arrayOfDouble1[5] = (this.m10 * paramMatrix3f.m02 + this.m11 * paramMatrix3f.m12 + this.m12 * paramMatrix3f.m22);
    arrayOfDouble1[6] = (this.m20 * paramMatrix3f.m00 + this.m21 * paramMatrix3f.m10 + this.m22 * paramMatrix3f.m20);
    arrayOfDouble1[7] = (this.m20 * paramMatrix3f.m01 + this.m21 * paramMatrix3f.m11 + this.m22 * paramMatrix3f.m21);
    arrayOfDouble1[8] = (this.m20 * paramMatrix3f.m02 + this.m21 * paramMatrix3f.m12 + this.m22 * paramMatrix3f.m22);
    Matrix3d.compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
    this.m00 = ((float)arrayOfDouble2[0]);
    this.m01 = ((float)arrayOfDouble2[1]);
    this.m02 = ((float)arrayOfDouble2[2]);
    this.m10 = ((float)arrayOfDouble2[3]);
    this.m11 = ((float)arrayOfDouble2[4]);
    this.m12 = ((float)arrayOfDouble2[5]);
    this.m20 = ((float)arrayOfDouble2[6]);
    this.m21 = ((float)arrayOfDouble2[7]);
    this.m22 = ((float)arrayOfDouble2[8]);
  }
  
  public final void mulNormalize(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2)
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    double[] arrayOfDouble3 = new double[3];
    arrayOfDouble1[0] = (paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m01 * paramMatrix3f2.m10 + paramMatrix3f1.m02 * paramMatrix3f2.m20);
    arrayOfDouble1[1] = (paramMatrix3f1.m00 * paramMatrix3f2.m01 + paramMatrix3f1.m01 * paramMatrix3f2.m11 + paramMatrix3f1.m02 * paramMatrix3f2.m21);
    arrayOfDouble1[2] = (paramMatrix3f1.m00 * paramMatrix3f2.m02 + paramMatrix3f1.m01 * paramMatrix3f2.m12 + paramMatrix3f1.m02 * paramMatrix3f2.m22);
    arrayOfDouble1[3] = (paramMatrix3f1.m10 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m10 + paramMatrix3f1.m12 * paramMatrix3f2.m20);
    arrayOfDouble1[4] = (paramMatrix3f1.m10 * paramMatrix3f2.m01 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m12 * paramMatrix3f2.m21);
    arrayOfDouble1[5] = (paramMatrix3f1.m10 * paramMatrix3f2.m02 + paramMatrix3f1.m11 * paramMatrix3f2.m12 + paramMatrix3f1.m12 * paramMatrix3f2.m22);
    arrayOfDouble1[6] = (paramMatrix3f1.m20 * paramMatrix3f2.m00 + paramMatrix3f1.m21 * paramMatrix3f2.m10 + paramMatrix3f1.m22 * paramMatrix3f2.m20);
    arrayOfDouble1[7] = (paramMatrix3f1.m20 * paramMatrix3f2.m01 + paramMatrix3f1.m21 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m21);
    arrayOfDouble1[8] = (paramMatrix3f1.m20 * paramMatrix3f2.m02 + paramMatrix3f1.m21 * paramMatrix3f2.m12 + paramMatrix3f1.m22 * paramMatrix3f2.m22);
    Matrix3d.compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
    this.m00 = ((float)arrayOfDouble2[0]);
    this.m01 = ((float)arrayOfDouble2[1]);
    this.m02 = ((float)arrayOfDouble2[2]);
    this.m10 = ((float)arrayOfDouble2[3]);
    this.m11 = ((float)arrayOfDouble2[4]);
    this.m12 = ((float)arrayOfDouble2[5]);
    this.m20 = ((float)arrayOfDouble2[6]);
    this.m21 = ((float)arrayOfDouble2[7]);
    this.m22 = ((float)arrayOfDouble2[8]);
  }
  
  public final void mulTransposeBoth(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2)
  {
    if ((this != paramMatrix3f1) && (this != paramMatrix3f2))
    {
      this.m00 = (paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m10 * paramMatrix3f2.m01 + paramMatrix3f1.m20 * paramMatrix3f2.m02);
      this.m01 = (paramMatrix3f1.m00 * paramMatrix3f2.m10 + paramMatrix3f1.m10 * paramMatrix3f2.m11 + paramMatrix3f1.m20 * paramMatrix3f2.m12);
      this.m02 = (paramMatrix3f1.m00 * paramMatrix3f2.m20 + paramMatrix3f1.m10 * paramMatrix3f2.m21 + paramMatrix3f1.m20 * paramMatrix3f2.m22);
      this.m10 = (paramMatrix3f1.m01 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m01 + paramMatrix3f1.m21 * paramMatrix3f2.m02);
      this.m11 = (paramMatrix3f1.m01 * paramMatrix3f2.m10 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m21 * paramMatrix3f2.m12);
      this.m12 = (paramMatrix3f1.m01 * paramMatrix3f2.m20 + paramMatrix3f1.m11 * paramMatrix3f2.m21 + paramMatrix3f1.m21 * paramMatrix3f2.m22);
      this.m20 = (paramMatrix3f1.m02 * paramMatrix3f2.m00 + paramMatrix3f1.m12 * paramMatrix3f2.m01 + paramMatrix3f1.m22 * paramMatrix3f2.m02);
      this.m21 = (paramMatrix3f1.m02 * paramMatrix3f2.m10 + paramMatrix3f1.m12 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m12);
      this.m22 = (paramMatrix3f1.m02 * paramMatrix3f2.m20 + paramMatrix3f1.m12 * paramMatrix3f2.m21 + paramMatrix3f1.m22 * paramMatrix3f2.m22);
    }
    else
    {
      float f1 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m10 * paramMatrix3f2.m01 + paramMatrix3f1.m20 * paramMatrix3f2.m02;
      float f2 = paramMatrix3f1.m00 * paramMatrix3f2.m10 + paramMatrix3f1.m10 * paramMatrix3f2.m11 + paramMatrix3f1.m20 * paramMatrix3f2.m12;
      float f3 = paramMatrix3f1.m00 * paramMatrix3f2.m20 + paramMatrix3f1.m10 * paramMatrix3f2.m21 + paramMatrix3f1.m20 * paramMatrix3f2.m22;
      float f4 = paramMatrix3f1.m01 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m01 + paramMatrix3f1.m21 * paramMatrix3f2.m02;
      float f5 = paramMatrix3f1.m01 * paramMatrix3f2.m10 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m21 * paramMatrix3f2.m12;
      float f6 = paramMatrix3f1.m01 * paramMatrix3f2.m20 + paramMatrix3f1.m11 * paramMatrix3f2.m21 + paramMatrix3f1.m21 * paramMatrix3f2.m22;
      float f7 = paramMatrix3f1.m02 * paramMatrix3f2.m00 + paramMatrix3f1.m12 * paramMatrix3f2.m01 + paramMatrix3f1.m22 * paramMatrix3f2.m02;
      float f8 = paramMatrix3f1.m02 * paramMatrix3f2.m10 + paramMatrix3f1.m12 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m12;
      float f9 = paramMatrix3f1.m02 * paramMatrix3f2.m20 + paramMatrix3f1.m12 * paramMatrix3f2.m21 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
      this.m00 = f1;
      this.m01 = f2;
      this.m02 = f3;
      this.m10 = f4;
      this.m11 = f5;
      this.m12 = f6;
      this.m20 = f7;
      this.m21 = f8;
      this.m22 = f9;
    }
  }
  
  public final void mulTransposeRight(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2)
  {
    if ((this != paramMatrix3f1) && (this != paramMatrix3f2))
    {
      this.m00 = (paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m01 * paramMatrix3f2.m01 + paramMatrix3f1.m02 * paramMatrix3f2.m02);
      this.m01 = (paramMatrix3f1.m00 * paramMatrix3f2.m10 + paramMatrix3f1.m01 * paramMatrix3f2.m11 + paramMatrix3f1.m02 * paramMatrix3f2.m12);
      this.m02 = (paramMatrix3f1.m00 * paramMatrix3f2.m20 + paramMatrix3f1.m01 * paramMatrix3f2.m21 + paramMatrix3f1.m02 * paramMatrix3f2.m22);
      this.m10 = (paramMatrix3f1.m10 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m01 + paramMatrix3f1.m12 * paramMatrix3f2.m02);
      this.m11 = (paramMatrix3f1.m10 * paramMatrix3f2.m10 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m12 * paramMatrix3f2.m12);
      this.m12 = (paramMatrix3f1.m10 * paramMatrix3f2.m20 + paramMatrix3f1.m11 * paramMatrix3f2.m21 + paramMatrix3f1.m12 * paramMatrix3f2.m22);
      this.m20 = (paramMatrix3f1.m20 * paramMatrix3f2.m00 + paramMatrix3f1.m21 * paramMatrix3f2.m01 + paramMatrix3f1.m22 * paramMatrix3f2.m02);
      this.m21 = (paramMatrix3f1.m20 * paramMatrix3f2.m10 + paramMatrix3f1.m21 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m12);
      this.m22 = (paramMatrix3f1.m20 * paramMatrix3f2.m20 + paramMatrix3f1.m21 * paramMatrix3f2.m21 + paramMatrix3f1.m22 * paramMatrix3f2.m22);
    }
    else
    {
      float f1 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m01 * paramMatrix3f2.m01 + paramMatrix3f1.m02 * paramMatrix3f2.m02;
      float f2 = paramMatrix3f1.m00 * paramMatrix3f2.m10 + paramMatrix3f1.m01 * paramMatrix3f2.m11 + paramMatrix3f1.m02 * paramMatrix3f2.m12;
      float f3 = paramMatrix3f1.m00 * paramMatrix3f2.m20 + paramMatrix3f1.m01 * paramMatrix3f2.m21 + paramMatrix3f1.m02 * paramMatrix3f2.m22;
      float f4 = paramMatrix3f1.m10 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m01 + paramMatrix3f1.m12 * paramMatrix3f2.m02;
      float f5 = paramMatrix3f1.m10 * paramMatrix3f2.m10 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m12 * paramMatrix3f2.m12;
      float f6 = paramMatrix3f1.m10 * paramMatrix3f2.m20 + paramMatrix3f1.m11 * paramMatrix3f2.m21 + paramMatrix3f1.m12 * paramMatrix3f2.m22;
      float f7 = paramMatrix3f1.m20 * paramMatrix3f2.m00 + paramMatrix3f1.m21 * paramMatrix3f2.m01 + paramMatrix3f1.m22 * paramMatrix3f2.m02;
      float f8 = paramMatrix3f1.m20 * paramMatrix3f2.m10 + paramMatrix3f1.m21 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m12;
      float f9 = paramMatrix3f1.m20 * paramMatrix3f2.m20 + paramMatrix3f1.m21 * paramMatrix3f2.m21 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
      this.m00 = f1;
      this.m01 = f2;
      this.m02 = f3;
      this.m10 = f4;
      this.m11 = f5;
      this.m12 = f6;
      this.m20 = f7;
      this.m21 = f8;
      this.m22 = f9;
    }
  }
  
  public final void mulTransposeLeft(Matrix3f paramMatrix3f1, Matrix3f paramMatrix3f2)
  {
    if ((this != paramMatrix3f1) && (this != paramMatrix3f2))
    {
      this.m00 = (paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m10 * paramMatrix3f2.m10 + paramMatrix3f1.m20 * paramMatrix3f2.m20);
      this.m01 = (paramMatrix3f1.m00 * paramMatrix3f2.m01 + paramMatrix3f1.m10 * paramMatrix3f2.m11 + paramMatrix3f1.m20 * paramMatrix3f2.m21);
      this.m02 = (paramMatrix3f1.m00 * paramMatrix3f2.m02 + paramMatrix3f1.m10 * paramMatrix3f2.m12 + paramMatrix3f1.m20 * paramMatrix3f2.m22);
      this.m10 = (paramMatrix3f1.m01 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m10 + paramMatrix3f1.m21 * paramMatrix3f2.m20);
      this.m11 = (paramMatrix3f1.m01 * paramMatrix3f2.m01 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m21 * paramMatrix3f2.m21);
      this.m12 = (paramMatrix3f1.m01 * paramMatrix3f2.m02 + paramMatrix3f1.m11 * paramMatrix3f2.m12 + paramMatrix3f1.m21 * paramMatrix3f2.m22);
      this.m20 = (paramMatrix3f1.m02 * paramMatrix3f2.m00 + paramMatrix3f1.m12 * paramMatrix3f2.m10 + paramMatrix3f1.m22 * paramMatrix3f2.m20);
      this.m21 = (paramMatrix3f1.m02 * paramMatrix3f2.m01 + paramMatrix3f1.m12 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m21);
      this.m22 = (paramMatrix3f1.m02 * paramMatrix3f2.m02 + paramMatrix3f1.m12 * paramMatrix3f2.m12 + paramMatrix3f1.m22 * paramMatrix3f2.m22);
    }
    else
    {
      float f1 = paramMatrix3f1.m00 * paramMatrix3f2.m00 + paramMatrix3f1.m10 * paramMatrix3f2.m10 + paramMatrix3f1.m20 * paramMatrix3f2.m20;
      float f2 = paramMatrix3f1.m00 * paramMatrix3f2.m01 + paramMatrix3f1.m10 * paramMatrix3f2.m11 + paramMatrix3f1.m20 * paramMatrix3f2.m21;
      float f3 = paramMatrix3f1.m00 * paramMatrix3f2.m02 + paramMatrix3f1.m10 * paramMatrix3f2.m12 + paramMatrix3f1.m20 * paramMatrix3f2.m22;
      float f4 = paramMatrix3f1.m01 * paramMatrix3f2.m00 + paramMatrix3f1.m11 * paramMatrix3f2.m10 + paramMatrix3f1.m21 * paramMatrix3f2.m20;
      float f5 = paramMatrix3f1.m01 * paramMatrix3f2.m01 + paramMatrix3f1.m11 * paramMatrix3f2.m11 + paramMatrix3f1.m21 * paramMatrix3f2.m21;
      float f6 = paramMatrix3f1.m01 * paramMatrix3f2.m02 + paramMatrix3f1.m11 * paramMatrix3f2.m12 + paramMatrix3f1.m21 * paramMatrix3f2.m22;
      float f7 = paramMatrix3f1.m02 * paramMatrix3f2.m00 + paramMatrix3f1.m12 * paramMatrix3f2.m10 + paramMatrix3f1.m22 * paramMatrix3f2.m20;
      float f8 = paramMatrix3f1.m02 * paramMatrix3f2.m01 + paramMatrix3f1.m12 * paramMatrix3f2.m11 + paramMatrix3f1.m22 * paramMatrix3f2.m21;
      float f9 = paramMatrix3f1.m02 * paramMatrix3f2.m02 + paramMatrix3f1.m12 * paramMatrix3f2.m12 + paramMatrix3f1.m22 * paramMatrix3f2.m22;
      this.m00 = f1;
      this.m01 = f2;
      this.m02 = f3;
      this.m10 = f4;
      this.m11 = f5;
      this.m12 = f6;
      this.m20 = f7;
      this.m21 = f8;
      this.m22 = f9;
    }
  }
  
  public final void normalize()
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    this.m00 = ((float)arrayOfDouble1[0]);
    this.m01 = ((float)arrayOfDouble1[1]);
    this.m02 = ((float)arrayOfDouble1[2]);
    this.m10 = ((float)arrayOfDouble1[3]);
    this.m11 = ((float)arrayOfDouble1[4]);
    this.m12 = ((float)arrayOfDouble1[5]);
    this.m20 = ((float)arrayOfDouble1[6]);
    this.m21 = ((float)arrayOfDouble1[7]);
    this.m22 = ((float)arrayOfDouble1[8]);
  }
  
  public final void normalize(Matrix3f paramMatrix3f)
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    double[] arrayOfDouble3 = new double[3];
    arrayOfDouble1[0] = paramMatrix3f.m00;
    arrayOfDouble1[1] = paramMatrix3f.m01;
    arrayOfDouble1[2] = paramMatrix3f.m02;
    arrayOfDouble1[3] = paramMatrix3f.m10;
    arrayOfDouble1[4] = paramMatrix3f.m11;
    arrayOfDouble1[5] = paramMatrix3f.m12;
    arrayOfDouble1[6] = paramMatrix3f.m20;
    arrayOfDouble1[7] = paramMatrix3f.m21;
    arrayOfDouble1[8] = paramMatrix3f.m22;
    Matrix3d.compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
    this.m00 = ((float)arrayOfDouble2[0]);
    this.m01 = ((float)arrayOfDouble2[1]);
    this.m02 = ((float)arrayOfDouble2[2]);
    this.m10 = ((float)arrayOfDouble2[3]);
    this.m11 = ((float)arrayOfDouble2[4]);
    this.m12 = ((float)arrayOfDouble2[5]);
    this.m20 = ((float)arrayOfDouble2[6]);
    this.m21 = ((float)arrayOfDouble2[7]);
    this.m22 = ((float)arrayOfDouble2[8]);
  }
  
  public final void normalizeCP()
  {
    float f = 1.0F / (float)Math.sqrt(this.m00 * this.m00 + this.m10 * this.m10 + this.m20 * this.m20);
    this.m00 *= f;
    this.m10 *= f;
    this.m20 *= f;
    f = 1.0F / (float)Math.sqrt(this.m01 * this.m01 + this.m11 * this.m11 + this.m21 * this.m21);
    this.m01 *= f;
    this.m11 *= f;
    this.m21 *= f;
    this.m02 = (this.m10 * this.m21 - this.m11 * this.m20);
    this.m12 = (this.m01 * this.m20 - this.m00 * this.m21);
    this.m22 = (this.m00 * this.m11 - this.m01 * this.m10);
  }
  
  public final void normalizeCP(Matrix3f paramMatrix3f)
  {
    float f = 1.0F / (float)Math.sqrt(paramMatrix3f.m00 * paramMatrix3f.m00 + paramMatrix3f.m10 * paramMatrix3f.m10 + paramMatrix3f.m20 * paramMatrix3f.m20);
    paramMatrix3f.m00 *= f;
    paramMatrix3f.m10 *= f;
    paramMatrix3f.m20 *= f;
    f = 1.0F / (float)Math.sqrt(paramMatrix3f.m01 * paramMatrix3f.m01 + paramMatrix3f.m11 * paramMatrix3f.m11 + paramMatrix3f.m21 * paramMatrix3f.m21);
    paramMatrix3f.m01 *= f;
    paramMatrix3f.m11 *= f;
    paramMatrix3f.m21 *= f;
    this.m02 = (this.m10 * this.m21 - this.m11 * this.m20);
    this.m12 = (this.m01 * this.m20 - this.m00 * this.m21);
    this.m22 = (this.m00 * this.m11 - this.m01 * this.m10);
  }
  
  public boolean equals(Matrix3f paramMatrix3f)
  {
    try
    {
      return (this.m00 == paramMatrix3f.m00) && (this.m01 == paramMatrix3f.m01) && (this.m02 == paramMatrix3f.m02) && (this.m10 == paramMatrix3f.m10) && (this.m11 == paramMatrix3f.m11) && (this.m12 == paramMatrix3f.m12) && (this.m20 == paramMatrix3f.m20) && (this.m21 == paramMatrix3f.m21) && (this.m22 == paramMatrix3f.m22);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Matrix3f localMatrix3f = (Matrix3f)paramObject;
      return (this.m00 == localMatrix3f.m00) && (this.m01 == localMatrix3f.m01) && (this.m02 == localMatrix3f.m02) && (this.m10 == localMatrix3f.m10) && (this.m11 == localMatrix3f.m11) && (this.m12 == localMatrix3f.m12) && (this.m20 == localMatrix3f.m20) && (this.m21 == localMatrix3f.m21) && (this.m22 == localMatrix3f.m22);
    }
    catch (ClassCastException localClassCastException)
    {
      return false;
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean epsilonEquals(Matrix3f paramMatrix3f, float paramFloat)
  {
    boolean bool = true;
    if (Math.abs(this.m00 - paramMatrix3f.m00) > paramFloat) {
      bool = false;
    }
    if (Math.abs(this.m01 - paramMatrix3f.m01) > paramFloat) {
      bool = false;
    }
    if (Math.abs(this.m02 - paramMatrix3f.m02) > paramFloat) {
      bool = false;
    }
    if (Math.abs(this.m10 - paramMatrix3f.m10) > paramFloat) {
      bool = false;
    }
    if (Math.abs(this.m11 - paramMatrix3f.m11) > paramFloat) {
      bool = false;
    }
    if (Math.abs(this.m12 - paramMatrix3f.m12) > paramFloat) {
      bool = false;
    }
    if (Math.abs(this.m20 - paramMatrix3f.m20) > paramFloat) {
      bool = false;
    }
    if (Math.abs(this.m21 - paramMatrix3f.m21) > paramFloat) {
      bool = false;
    }
    if (Math.abs(this.m22 - paramMatrix3f.m22) > paramFloat) {
      bool = false;
    }
    return bool;
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.floatToIntBits(this.m00);
    l = 31L * l + VecMathUtil.floatToIntBits(this.m01);
    l = 31L * l + VecMathUtil.floatToIntBits(this.m02);
    l = 31L * l + VecMathUtil.floatToIntBits(this.m10);
    l = 31L * l + VecMathUtil.floatToIntBits(this.m11);
    l = 31L * l + VecMathUtil.floatToIntBits(this.m12);
    l = 31L * l + VecMathUtil.floatToIntBits(this.m20);
    l = 31L * l + VecMathUtil.floatToIntBits(this.m21);
    l = 31L * l + VecMathUtil.floatToIntBits(this.m22);
    return (int)(l ^ l >> 32);
  }
  
  public final void setZero()
  {
    this.m00 = 0.0F;
    this.m01 = 0.0F;
    this.m02 = 0.0F;
    this.m10 = 0.0F;
    this.m11 = 0.0F;
    this.m12 = 0.0F;
    this.m20 = 0.0F;
    this.m21 = 0.0F;
    this.m22 = 0.0F;
  }
  
  public final void negate()
  {
    this.m00 = (-this.m00);
    this.m01 = (-this.m01);
    this.m02 = (-this.m02);
    this.m10 = (-this.m10);
    this.m11 = (-this.m11);
    this.m12 = (-this.m12);
    this.m20 = (-this.m20);
    this.m21 = (-this.m21);
    this.m22 = (-this.m22);
  }
  
  public final void negate(Matrix3f paramMatrix3f)
  {
    this.m00 = (-paramMatrix3f.m00);
    this.m01 = (-paramMatrix3f.m01);
    this.m02 = (-paramMatrix3f.m02);
    this.m10 = (-paramMatrix3f.m10);
    this.m11 = (-paramMatrix3f.m11);
    this.m12 = (-paramMatrix3f.m12);
    this.m20 = (-paramMatrix3f.m20);
    this.m21 = (-paramMatrix3f.m21);
    this.m22 = (-paramMatrix3f.m22);
  }
  
  public final void transform(Tuple3f paramTuple3f)
  {
    float f1 = this.m00 * paramTuple3f.field_615 + this.m01 * paramTuple3f.field_616 + this.m02 * paramTuple3f.field_617;
    float f2 = this.m10 * paramTuple3f.field_615 + this.m11 * paramTuple3f.field_616 + this.m12 * paramTuple3f.field_617;
    float f3 = this.m20 * paramTuple3f.field_615 + this.m21 * paramTuple3f.field_616 + this.m22 * paramTuple3f.field_617;
    paramTuple3f.set(f1, f2, f3);
  }
  
  public final void transform(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2)
  {
    float f1 = this.m00 * paramTuple3f1.field_615 + this.m01 * paramTuple3f1.field_616 + this.m02 * paramTuple3f1.field_617;
    float f2 = this.m10 * paramTuple3f1.field_615 + this.m11 * paramTuple3f1.field_616 + this.m12 * paramTuple3f1.field_617;
    paramTuple3f2.field_617 = (this.m20 * paramTuple3f1.field_615 + this.m21 * paramTuple3f1.field_616 + this.m22 * paramTuple3f1.field_617);
    paramTuple3f2.field_615 = f1;
    paramTuple3f2.field_616 = f2;
  }
  
  void getScaleRotate(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    double[] arrayOfDouble = new double[9];
    arrayOfDouble[0] = this.m00;
    arrayOfDouble[1] = this.m01;
    arrayOfDouble[2] = this.m02;
    arrayOfDouble[3] = this.m10;
    arrayOfDouble[4] = this.m11;
    arrayOfDouble[5] = this.m12;
    arrayOfDouble[6] = this.m20;
    arrayOfDouble[7] = this.m21;
    arrayOfDouble[8] = this.m22;
    Matrix3d.compute_svd(arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2);
  }
  
  public Object clone()
  {
    Matrix3f localMatrix3f = null;
    try
    {
      localMatrix3f = (Matrix3f)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new InternalError();
    }
    return localMatrix3f;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Matrix3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */