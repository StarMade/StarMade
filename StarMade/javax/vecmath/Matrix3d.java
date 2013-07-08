package javax.vecmath;

import java.io.PrintStream;
import java.io.Serializable;

public class Matrix3d
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 6837536777072402710L;
  public double m00;
  public double m01;
  public double m02;
  public double m10;
  public double m11;
  public double m12;
  public double m20;
  public double m21;
  public double m22;
  private static final double EPS = 1.110223024E-016D;
  private static final double ERR_EPS = 1.0E-008D;
  private static double xin;
  private static double yin;
  private static double zin;
  private static double xout;
  private static double yout;
  private static double zout;
  
  public Matrix3d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9)
  {
    this.m00 = paramDouble1;
    this.m01 = paramDouble2;
    this.m02 = paramDouble3;
    this.m10 = paramDouble4;
    this.m11 = paramDouble5;
    this.m12 = paramDouble6;
    this.m20 = paramDouble7;
    this.m21 = paramDouble8;
    this.m22 = paramDouble9;
  }
  
  public Matrix3d(double[] paramArrayOfDouble)
  {
    this.m00 = paramArrayOfDouble[0];
    this.m01 = paramArrayOfDouble[1];
    this.m02 = paramArrayOfDouble[2];
    this.m10 = paramArrayOfDouble[3];
    this.m11 = paramArrayOfDouble[4];
    this.m12 = paramArrayOfDouble[5];
    this.m20 = paramArrayOfDouble[6];
    this.m21 = paramArrayOfDouble[7];
    this.m22 = paramArrayOfDouble[8];
  }
  
  public Matrix3d(Matrix3d paramMatrix3d)
  {
    this.m00 = paramMatrix3d.m00;
    this.m01 = paramMatrix3d.m01;
    this.m02 = paramMatrix3d.m02;
    this.m10 = paramMatrix3d.m10;
    this.m11 = paramMatrix3d.m11;
    this.m12 = paramMatrix3d.m12;
    this.m20 = paramMatrix3d.m20;
    this.m21 = paramMatrix3d.m21;
    this.m22 = paramMatrix3d.m22;
  }
  
  public Matrix3d(Matrix3f paramMatrix3f)
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
  
  public Matrix3d()
  {
    this.m00 = 0.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = 0.0D;
    this.m12 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 0.0D;
  }
  
  public String toString()
  {
    return this.m00 + ", " + this.m01 + ", " + this.m02 + "\n" + this.m10 + ", " + this.m11 + ", " + this.m12 + "\n" + this.m20 + ", " + this.m21 + ", " + this.m22 + "\n";
  }
  
  public final void setIdentity()
  {
    this.m00 = 1.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = 1.0D;
    this.m12 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 1.0D;
  }
  
  public final void setScale(double paramDouble)
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    this.m00 = (arrayOfDouble1[0] * paramDouble);
    this.m01 = (arrayOfDouble1[1] * paramDouble);
    this.m02 = (arrayOfDouble1[2] * paramDouble);
    this.m10 = (arrayOfDouble1[3] * paramDouble);
    this.m11 = (arrayOfDouble1[4] * paramDouble);
    this.m12 = (arrayOfDouble1[5] * paramDouble);
    this.m20 = (arrayOfDouble1[6] * paramDouble);
    this.m21 = (arrayOfDouble1[7] * paramDouble);
    this.m22 = (arrayOfDouble1[8] * paramDouble);
  }
  
  public final void setElement(int paramInt1, int paramInt2, double paramDouble)
  {
    switch (paramInt1)
    {
    case 0: 
      switch (paramInt2)
      {
      case 0: 
        this.m00 = paramDouble;
        break;
      case 1: 
        this.m01 = paramDouble;
        break;
      case 2: 
        this.m02 = paramDouble;
        break;
      default: 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
      }
      break;
    case 1: 
      switch (paramInt2)
      {
      case 0: 
        this.m10 = paramDouble;
        break;
      case 1: 
        this.m11 = paramDouble;
        break;
      case 2: 
        this.m12 = paramDouble;
        break;
      default: 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
      }
      break;
    case 2: 
      switch (paramInt2)
      {
      case 0: 
        this.m20 = paramDouble;
        break;
      case 1: 
        this.m21 = paramDouble;
        break;
      case 2: 
        this.m22 = paramDouble;
        break;
      default: 
        throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
      }
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d0"));
    }
  }
  
  public final double getElement(int paramInt1, int paramInt2)
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
    throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d1"));
  }
  
  public final void getRow(int paramInt, Vector3d paramVector3d)
  {
    if (paramInt == 0)
    {
      paramVector3d.x = this.m00;
      paramVector3d.y = this.m01;
      paramVector3d.z = this.m02;
    }
    else if (paramInt == 1)
    {
      paramVector3d.x = this.m10;
      paramVector3d.y = this.m11;
      paramVector3d.z = this.m12;
    }
    else if (paramInt == 2)
    {
      paramVector3d.x = this.m20;
      paramVector3d.y = this.m21;
      paramVector3d.z = this.m22;
    }
    else
    {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d2"));
    }
  }
  
  public final void getRow(int paramInt, double[] paramArrayOfDouble)
  {
    if (paramInt == 0)
    {
      paramArrayOfDouble[0] = this.m00;
      paramArrayOfDouble[1] = this.m01;
      paramArrayOfDouble[2] = this.m02;
    }
    else if (paramInt == 1)
    {
      paramArrayOfDouble[0] = this.m10;
      paramArrayOfDouble[1] = this.m11;
      paramArrayOfDouble[2] = this.m12;
    }
    else if (paramInt == 2)
    {
      paramArrayOfDouble[0] = this.m20;
      paramArrayOfDouble[1] = this.m21;
      paramArrayOfDouble[2] = this.m22;
    }
    else
    {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d2"));
    }
  }
  
  public final void getColumn(int paramInt, Vector3d paramVector3d)
  {
    if (paramInt == 0)
    {
      paramVector3d.x = this.m00;
      paramVector3d.y = this.m10;
      paramVector3d.z = this.m20;
    }
    else if (paramInt == 1)
    {
      paramVector3d.x = this.m01;
      paramVector3d.y = this.m11;
      paramVector3d.z = this.m21;
    }
    else if (paramInt == 2)
    {
      paramVector3d.x = this.m02;
      paramVector3d.y = this.m12;
      paramVector3d.z = this.m22;
    }
    else
    {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d4"));
    }
  }
  
  public final void getColumn(int paramInt, double[] paramArrayOfDouble)
  {
    if (paramInt == 0)
    {
      paramArrayOfDouble[0] = this.m00;
      paramArrayOfDouble[1] = this.m10;
      paramArrayOfDouble[2] = this.m20;
    }
    else if (paramInt == 1)
    {
      paramArrayOfDouble[0] = this.m01;
      paramArrayOfDouble[1] = this.m11;
      paramArrayOfDouble[2] = this.m21;
    }
    else if (paramInt == 2)
    {
      paramArrayOfDouble[0] = this.m02;
      paramArrayOfDouble[1] = this.m12;
      paramArrayOfDouble[2] = this.m22;
    }
    else
    {
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d4"));
    }
  }
  
  public final void setRow(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramDouble1;
      this.m01 = paramDouble2;
      this.m02 = paramDouble3;
      break;
    case 1: 
      this.m10 = paramDouble1;
      this.m11 = paramDouble2;
      this.m12 = paramDouble3;
      break;
    case 2: 
      this.m20 = paramDouble1;
      this.m21 = paramDouble2;
      this.m22 = paramDouble3;
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
    }
  }
  
  public final void setRow(int paramInt, Vector3d paramVector3d)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramVector3d.x;
      this.m01 = paramVector3d.y;
      this.m02 = paramVector3d.z;
      break;
    case 1: 
      this.m10 = paramVector3d.x;
      this.m11 = paramVector3d.y;
      this.m12 = paramVector3d.z;
      break;
    case 2: 
      this.m20 = paramVector3d.x;
      this.m21 = paramVector3d.y;
      this.m22 = paramVector3d.z;
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
    }
  }
  
  public final void setRow(int paramInt, double[] paramArrayOfDouble)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramArrayOfDouble[0];
      this.m01 = paramArrayOfDouble[1];
      this.m02 = paramArrayOfDouble[2];
      break;
    case 1: 
      this.m10 = paramArrayOfDouble[0];
      this.m11 = paramArrayOfDouble[1];
      this.m12 = paramArrayOfDouble[2];
      break;
    case 2: 
      this.m20 = paramArrayOfDouble[0];
      this.m21 = paramArrayOfDouble[1];
      this.m22 = paramArrayOfDouble[2];
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d6"));
    }
  }
  
  public final void setColumn(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramDouble1;
      this.m10 = paramDouble2;
      this.m20 = paramDouble3;
      break;
    case 1: 
      this.m01 = paramDouble1;
      this.m11 = paramDouble2;
      this.m21 = paramDouble3;
      break;
    case 2: 
      this.m02 = paramDouble1;
      this.m12 = paramDouble2;
      this.m22 = paramDouble3;
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
    }
  }
  
  public final void setColumn(int paramInt, Vector3d paramVector3d)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramVector3d.x;
      this.m10 = paramVector3d.y;
      this.m20 = paramVector3d.z;
      break;
    case 1: 
      this.m01 = paramVector3d.x;
      this.m11 = paramVector3d.y;
      this.m21 = paramVector3d.z;
      break;
    case 2: 
      this.m02 = paramVector3d.x;
      this.m12 = paramVector3d.y;
      this.m22 = paramVector3d.z;
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
    }
  }
  
  public final void setColumn(int paramInt, double[] paramArrayOfDouble)
  {
    switch (paramInt)
    {
    case 0: 
      this.m00 = paramArrayOfDouble[0];
      this.m10 = paramArrayOfDouble[1];
      this.m20 = paramArrayOfDouble[2];
      break;
    case 1: 
      this.m01 = paramArrayOfDouble[0];
      this.m11 = paramArrayOfDouble[1];
      this.m21 = paramArrayOfDouble[2];
      break;
    case 2: 
      this.m02 = paramArrayOfDouble[0];
      this.m12 = paramArrayOfDouble[1];
      this.m22 = paramArrayOfDouble[2];
      break;
    default: 
      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix3d9"));
    }
  }
  
  public final double getScale()
  {
    double[] arrayOfDouble1 = new double[3];
    double[] arrayOfDouble2 = new double[9];
    getScaleRotate(arrayOfDouble1, arrayOfDouble2);
    return max3(arrayOfDouble1);
  }
  
  public final void add(double paramDouble)
  {
    this.m00 += paramDouble;
    this.m01 += paramDouble;
    this.m02 += paramDouble;
    this.m10 += paramDouble;
    this.m11 += paramDouble;
    this.m12 += paramDouble;
    this.m20 += paramDouble;
    this.m21 += paramDouble;
    this.m22 += paramDouble;
  }
  
  public final void add(double paramDouble, Matrix3d paramMatrix3d)
  {
    paramMatrix3d.m00 += paramDouble;
    paramMatrix3d.m01 += paramDouble;
    paramMatrix3d.m02 += paramDouble;
    paramMatrix3d.m10 += paramDouble;
    paramMatrix3d.m11 += paramDouble;
    paramMatrix3d.m12 += paramDouble;
    paramMatrix3d.m20 += paramDouble;
    paramMatrix3d.m21 += paramDouble;
    paramMatrix3d.m22 += paramDouble;
  }
  
  public final void add(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2)
  {
    paramMatrix3d1.m00 += paramMatrix3d2.m00;
    paramMatrix3d1.m01 += paramMatrix3d2.m01;
    paramMatrix3d1.m02 += paramMatrix3d2.m02;
    paramMatrix3d1.m10 += paramMatrix3d2.m10;
    paramMatrix3d1.m11 += paramMatrix3d2.m11;
    paramMatrix3d1.m12 += paramMatrix3d2.m12;
    paramMatrix3d1.m20 += paramMatrix3d2.m20;
    paramMatrix3d1.m21 += paramMatrix3d2.m21;
    paramMatrix3d1.m22 += paramMatrix3d2.m22;
  }
  
  public final void add(Matrix3d paramMatrix3d)
  {
    this.m00 += paramMatrix3d.m00;
    this.m01 += paramMatrix3d.m01;
    this.m02 += paramMatrix3d.m02;
    this.m10 += paramMatrix3d.m10;
    this.m11 += paramMatrix3d.m11;
    this.m12 += paramMatrix3d.m12;
    this.m20 += paramMatrix3d.m20;
    this.m21 += paramMatrix3d.m21;
    this.m22 += paramMatrix3d.m22;
  }
  
  public final void sub(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2)
  {
    paramMatrix3d1.m00 -= paramMatrix3d2.m00;
    paramMatrix3d1.m01 -= paramMatrix3d2.m01;
    paramMatrix3d1.m02 -= paramMatrix3d2.m02;
    paramMatrix3d1.m10 -= paramMatrix3d2.m10;
    paramMatrix3d1.m11 -= paramMatrix3d2.m11;
    paramMatrix3d1.m12 -= paramMatrix3d2.m12;
    paramMatrix3d1.m20 -= paramMatrix3d2.m20;
    paramMatrix3d1.m21 -= paramMatrix3d2.m21;
    paramMatrix3d1.m22 -= paramMatrix3d2.m22;
  }
  
  public final void sub(Matrix3d paramMatrix3d)
  {
    this.m00 -= paramMatrix3d.m00;
    this.m01 -= paramMatrix3d.m01;
    this.m02 -= paramMatrix3d.m02;
    this.m10 -= paramMatrix3d.m10;
    this.m11 -= paramMatrix3d.m11;
    this.m12 -= paramMatrix3d.m12;
    this.m20 -= paramMatrix3d.m20;
    this.m21 -= paramMatrix3d.m21;
    this.m22 -= paramMatrix3d.m22;
  }
  
  public final void transpose()
  {
    double d = this.m10;
    this.m10 = this.m01;
    this.m01 = d;
    d = this.m20;
    this.m20 = this.m02;
    this.m02 = d;
    d = this.m21;
    this.m21 = this.m12;
    this.m12 = d;
  }
  
  public final void transpose(Matrix3d paramMatrix3d)
  {
    if (this != paramMatrix3d)
    {
      this.m00 = paramMatrix3d.m00;
      this.m01 = paramMatrix3d.m10;
      this.m02 = paramMatrix3d.m20;
      this.m10 = paramMatrix3d.m01;
      this.m11 = paramMatrix3d.m11;
      this.m12 = paramMatrix3d.m21;
      this.m20 = paramMatrix3d.m02;
      this.m21 = paramMatrix3d.m12;
      this.m22 = paramMatrix3d.m22;
    }
    else
    {
      transpose();
    }
  }
  
  public final void set(Quat4d paramQuat4d)
  {
    this.m00 = (1.0D - 2.0D * paramQuat4d.y * paramQuat4d.y - 2.0D * paramQuat4d.z * paramQuat4d.z);
    this.m10 = (2.0D * (paramQuat4d.x * paramQuat4d.y + paramQuat4d.w * paramQuat4d.z));
    this.m20 = (2.0D * (paramQuat4d.x * paramQuat4d.z - paramQuat4d.w * paramQuat4d.y));
    this.m01 = (2.0D * (paramQuat4d.x * paramQuat4d.y - paramQuat4d.w * paramQuat4d.z));
    this.m11 = (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.z * paramQuat4d.z);
    this.m21 = (2.0D * (paramQuat4d.y * paramQuat4d.z + paramQuat4d.w * paramQuat4d.x));
    this.m02 = (2.0D * (paramQuat4d.x * paramQuat4d.z + paramQuat4d.w * paramQuat4d.y));
    this.m12 = (2.0D * (paramQuat4d.y * paramQuat4d.z - paramQuat4d.w * paramQuat4d.x));
    this.m22 = (1.0D - 2.0D * paramQuat4d.x * paramQuat4d.x - 2.0D * paramQuat4d.y * paramQuat4d.y);
  }
  
  public final void set(AxisAngle4d paramAxisAngle4d)
  {
    double d1 = Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
    if (d1 < 1.110223024E-016D)
    {
      this.m00 = 1.0D;
      this.m01 = 0.0D;
      this.m02 = 0.0D;
      this.m10 = 0.0D;
      this.m11 = 1.0D;
      this.m12 = 0.0D;
      this.m20 = 0.0D;
      this.m21 = 0.0D;
      this.m22 = 1.0D;
    }
    else
    {
      d1 = 1.0D / d1;
      double d2 = paramAxisAngle4d.x * d1;
      double d3 = paramAxisAngle4d.y * d1;
      double d4 = paramAxisAngle4d.z * d1;
      double d5 = Math.sin(paramAxisAngle4d.angle);
      double d6 = Math.cos(paramAxisAngle4d.angle);
      double d7 = 1.0D - d6;
      double d8 = paramAxisAngle4d.x * paramAxisAngle4d.z;
      double d9 = paramAxisAngle4d.x * paramAxisAngle4d.y;
      double d10 = paramAxisAngle4d.y * paramAxisAngle4d.z;
      this.m00 = (d7 * d2 * d2 + d6);
      this.m01 = (d7 * d9 - d5 * d4);
      this.m02 = (d7 * d8 + d5 * d3);
      this.m10 = (d7 * d9 + d5 * d4);
      this.m11 = (d7 * d3 * d3 + d6);
      this.m12 = (d7 * d10 - d5 * d2);
      this.m20 = (d7 * d8 - d5 * d3);
      this.m21 = (d7 * d10 + d5 * d2);
      this.m22 = (d7 * d4 * d4 + d6);
    }
  }
  
  public final void set(Quat4f paramQuat4f)
  {
    this.m00 = (1.0D - 2.0D * paramQuat4f.y * paramQuat4f.y - 2.0D * paramQuat4f.z * paramQuat4f.z);
    this.m10 = (2.0D * (paramQuat4f.x * paramQuat4f.y + paramQuat4f.w * paramQuat4f.z));
    this.m20 = (2.0D * (paramQuat4f.x * paramQuat4f.z - paramQuat4f.w * paramQuat4f.y));
    this.m01 = (2.0D * (paramQuat4f.x * paramQuat4f.y - paramQuat4f.w * paramQuat4f.z));
    this.m11 = (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.z * paramQuat4f.z);
    this.m21 = (2.0D * (paramQuat4f.y * paramQuat4f.z + paramQuat4f.w * paramQuat4f.x));
    this.m02 = (2.0D * (paramQuat4f.x * paramQuat4f.z + paramQuat4f.w * paramQuat4f.y));
    this.m12 = (2.0D * (paramQuat4f.y * paramQuat4f.z - paramQuat4f.w * paramQuat4f.x));
    this.m22 = (1.0D - 2.0D * paramQuat4f.x * paramQuat4f.x - 2.0D * paramQuat4f.y * paramQuat4f.y);
  }
  
  public final void set(AxisAngle4f paramAxisAngle4f)
  {
    double d1 = Math.sqrt(paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z);
    if (d1 < 1.110223024E-016D)
    {
      this.m00 = 1.0D;
      this.m01 = 0.0D;
      this.m02 = 0.0D;
      this.m10 = 0.0D;
      this.m11 = 1.0D;
      this.m12 = 0.0D;
      this.m20 = 0.0D;
      this.m21 = 0.0D;
      this.m22 = 1.0D;
    }
    else
    {
      d1 = 1.0D / d1;
      double d2 = paramAxisAngle4f.x * d1;
      double d3 = paramAxisAngle4f.y * d1;
      double d4 = paramAxisAngle4f.z * d1;
      double d5 = Math.sin(paramAxisAngle4f.angle);
      double d6 = Math.cos(paramAxisAngle4f.angle);
      double d7 = 1.0D - d6;
      double d8 = d2 * d4;
      double d9 = d2 * d3;
      double d10 = d3 * d4;
      this.m00 = (d7 * d2 * d2 + d6);
      this.m01 = (d7 * d9 - d5 * d4);
      this.m02 = (d7 * d8 + d5 * d3);
      this.m10 = (d7 * d9 + d5 * d4);
      this.m11 = (d7 * d3 * d3 + d6);
      this.m12 = (d7 * d10 - d5 * d2);
      this.m20 = (d7 * d8 - d5 * d3);
      this.m21 = (d7 * d10 + d5 * d2);
      this.m22 = (d7 * d4 * d4 + d6);
    }
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
    this.m00 = paramMatrix3d.m00;
    this.m01 = paramMatrix3d.m01;
    this.m02 = paramMatrix3d.m02;
    this.m10 = paramMatrix3d.m10;
    this.m11 = paramMatrix3d.m11;
    this.m12 = paramMatrix3d.m12;
    this.m20 = paramMatrix3d.m20;
    this.m21 = paramMatrix3d.m21;
    this.m22 = paramMatrix3d.m22;
  }
  
  public final void set(double[] paramArrayOfDouble)
  {
    this.m00 = paramArrayOfDouble[0];
    this.m01 = paramArrayOfDouble[1];
    this.m02 = paramArrayOfDouble[2];
    this.m10 = paramArrayOfDouble[3];
    this.m11 = paramArrayOfDouble[4];
    this.m12 = paramArrayOfDouble[5];
    this.m20 = paramArrayOfDouble[6];
    this.m21 = paramArrayOfDouble[7];
    this.m22 = paramArrayOfDouble[8];
  }
  
  public final void invert(Matrix3d paramMatrix3d)
  {
    invertGeneral(paramMatrix3d);
  }
  
  public final void invert()
  {
    invertGeneral(this);
  }
  
  private final void invertGeneral(Matrix3d paramMatrix3d)
  {
    double[] arrayOfDouble1 = new double[9];
    int[] arrayOfInt = new int[3];
    double[] arrayOfDouble2 = new double[9];
    arrayOfDouble2[0] = paramMatrix3d.m00;
    arrayOfDouble2[1] = paramMatrix3d.m01;
    arrayOfDouble2[2] = paramMatrix3d.m02;
    arrayOfDouble2[3] = paramMatrix3d.m10;
    arrayOfDouble2[4] = paramMatrix3d.m11;
    arrayOfDouble2[5] = paramMatrix3d.m12;
    arrayOfDouble2[6] = paramMatrix3d.m20;
    arrayOfDouble2[7] = paramMatrix3d.m21;
    arrayOfDouble2[8] = paramMatrix3d.m22;
    if (!luDecomposition(arrayOfDouble2, arrayOfInt)) {
      throw new SingularMatrixException(VecMathI18N.getString("Matrix3d12"));
    }
    for (int i = 0; i < 9; i++) {
      arrayOfDouble1[i] = 0.0D;
    }
    arrayOfDouble1[0] = 1.0D;
    arrayOfDouble1[4] = 1.0D;
    arrayOfDouble1[8] = 1.0D;
    luBacksubstitution(arrayOfDouble2, arrayOfInt, arrayOfDouble1);
    this.m00 = arrayOfDouble1[0];
    this.m01 = arrayOfDouble1[1];
    this.m02 = arrayOfDouble1[2];
    this.m10 = arrayOfDouble1[3];
    this.m11 = arrayOfDouble1[4];
    this.m12 = arrayOfDouble1[5];
    this.m20 = arrayOfDouble1[6];
    this.m21 = arrayOfDouble1[7];
    this.m22 = arrayOfDouble1[8];
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
        throw new RuntimeException(VecMathI18N.getString("Matrix3d13"));
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
  
  public final double determinant()
  {
    double d = this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) + this.m01 * (this.m12 * this.m20 - this.m10 * this.m22) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20);
    return d;
  }
  
  public final void set(double paramDouble)
  {
    this.m00 = paramDouble;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = paramDouble;
    this.m12 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = paramDouble;
  }
  
  public final void rotX(double paramDouble)
  {
    double d1 = Math.sin(paramDouble);
    double d2 = Math.cos(paramDouble);
    this.m00 = 1.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = d2;
    this.m12 = (-d1);
    this.m20 = 0.0D;
    this.m21 = d1;
    this.m22 = d2;
  }
  
  public final void rotY(double paramDouble)
  {
    double d1 = Math.sin(paramDouble);
    double d2 = Math.cos(paramDouble);
    this.m00 = d2;
    this.m01 = 0.0D;
    this.m02 = d1;
    this.m10 = 0.0D;
    this.m11 = 1.0D;
    this.m12 = 0.0D;
    this.m20 = (-d1);
    this.m21 = 0.0D;
    this.m22 = d2;
  }
  
  public final void rotZ(double paramDouble)
  {
    double d1 = Math.sin(paramDouble);
    double d2 = Math.cos(paramDouble);
    this.m00 = d2;
    this.m01 = (-d1);
    this.m02 = 0.0D;
    this.m10 = d1;
    this.m11 = d2;
    this.m12 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 1.0D;
  }
  
  public final void mul(double paramDouble)
  {
    this.m00 *= paramDouble;
    this.m01 *= paramDouble;
    this.m02 *= paramDouble;
    this.m10 *= paramDouble;
    this.m11 *= paramDouble;
    this.m12 *= paramDouble;
    this.m20 *= paramDouble;
    this.m21 *= paramDouble;
    this.m22 *= paramDouble;
  }
  
  public final void mul(double paramDouble, Matrix3d paramMatrix3d)
  {
    this.m00 = (paramDouble * paramMatrix3d.m00);
    this.m01 = (paramDouble * paramMatrix3d.m01);
    this.m02 = (paramDouble * paramMatrix3d.m02);
    this.m10 = (paramDouble * paramMatrix3d.m10);
    this.m11 = (paramDouble * paramMatrix3d.m11);
    this.m12 = (paramDouble * paramMatrix3d.m12);
    this.m20 = (paramDouble * paramMatrix3d.m20);
    this.m21 = (paramDouble * paramMatrix3d.m21);
    this.m22 = (paramDouble * paramMatrix3d.m22);
  }
  
  public final void mul(Matrix3d paramMatrix3d)
  {
    double d1 = this.m00 * paramMatrix3d.m00 + this.m01 * paramMatrix3d.m10 + this.m02 * paramMatrix3d.m20;
    double d2 = this.m00 * paramMatrix3d.m01 + this.m01 * paramMatrix3d.m11 + this.m02 * paramMatrix3d.m21;
    double d3 = this.m00 * paramMatrix3d.m02 + this.m01 * paramMatrix3d.m12 + this.m02 * paramMatrix3d.m22;
    double d4 = this.m10 * paramMatrix3d.m00 + this.m11 * paramMatrix3d.m10 + this.m12 * paramMatrix3d.m20;
    double d5 = this.m10 * paramMatrix3d.m01 + this.m11 * paramMatrix3d.m11 + this.m12 * paramMatrix3d.m21;
    double d6 = this.m10 * paramMatrix3d.m02 + this.m11 * paramMatrix3d.m12 + this.m12 * paramMatrix3d.m22;
    double d7 = this.m20 * paramMatrix3d.m00 + this.m21 * paramMatrix3d.m10 + this.m22 * paramMatrix3d.m20;
    double d8 = this.m20 * paramMatrix3d.m01 + this.m21 * paramMatrix3d.m11 + this.m22 * paramMatrix3d.m21;
    double d9 = this.m20 * paramMatrix3d.m02 + this.m21 * paramMatrix3d.m12 + this.m22 * paramMatrix3d.m22;
    this.m00 = d1;
    this.m01 = d2;
    this.m02 = d3;
    this.m10 = d4;
    this.m11 = d5;
    this.m12 = d6;
    this.m20 = d7;
    this.m21 = d8;
    this.m22 = d9;
  }
  
  public final void mul(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2)
  {
    if ((this != paramMatrix3d1) && (this != paramMatrix3d2))
    {
      this.m00 = (paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m02 * paramMatrix3d2.m20);
      this.m01 = (paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m21);
      this.m02 = (paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m01 * paramMatrix3d2.m12 + paramMatrix3d1.m02 * paramMatrix3d2.m22);
      this.m10 = (paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m20);
      this.m11 = (paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m21);
      this.m12 = (paramMatrix3d1.m10 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m12 * paramMatrix3d2.m22);
      this.m20 = (paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20);
      this.m21 = (paramMatrix3d1.m20 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21);
      this.m22 = (paramMatrix3d1.m20 * paramMatrix3d2.m02 + paramMatrix3d1.m21 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22);
    }
    else
    {
      double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m02 * paramMatrix3d2.m20;
      double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m21;
      double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m01 * paramMatrix3d2.m12 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
      double d4 = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m20;
      double d5 = paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m21;
      double d6 = paramMatrix3d1.m10 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
      double d7 = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
      double d8 = paramMatrix3d1.m20 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
      double d9 = paramMatrix3d1.m20 * paramMatrix3d2.m02 + paramMatrix3d1.m21 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m10 = d4;
      this.m11 = d5;
      this.m12 = d6;
      this.m20 = d7;
      this.m21 = d8;
      this.m22 = d9;
    }
  }
  
  public final void mulNormalize(Matrix3d paramMatrix3d)
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    double[] arrayOfDouble3 = new double[3];
    arrayOfDouble1[0] = (this.m00 * paramMatrix3d.m00 + this.m01 * paramMatrix3d.m10 + this.m02 * paramMatrix3d.m20);
    arrayOfDouble1[1] = (this.m00 * paramMatrix3d.m01 + this.m01 * paramMatrix3d.m11 + this.m02 * paramMatrix3d.m21);
    arrayOfDouble1[2] = (this.m00 * paramMatrix3d.m02 + this.m01 * paramMatrix3d.m12 + this.m02 * paramMatrix3d.m22);
    arrayOfDouble1[3] = (this.m10 * paramMatrix3d.m00 + this.m11 * paramMatrix3d.m10 + this.m12 * paramMatrix3d.m20);
    arrayOfDouble1[4] = (this.m10 * paramMatrix3d.m01 + this.m11 * paramMatrix3d.m11 + this.m12 * paramMatrix3d.m21);
    arrayOfDouble1[5] = (this.m10 * paramMatrix3d.m02 + this.m11 * paramMatrix3d.m12 + this.m12 * paramMatrix3d.m22);
    arrayOfDouble1[6] = (this.m20 * paramMatrix3d.m00 + this.m21 * paramMatrix3d.m10 + this.m22 * paramMatrix3d.m20);
    arrayOfDouble1[7] = (this.m20 * paramMatrix3d.m01 + this.m21 * paramMatrix3d.m11 + this.m22 * paramMatrix3d.m21);
    arrayOfDouble1[8] = (this.m20 * paramMatrix3d.m02 + this.m21 * paramMatrix3d.m12 + this.m22 * paramMatrix3d.m22);
    compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
    this.m00 = arrayOfDouble2[0];
    this.m01 = arrayOfDouble2[1];
    this.m02 = arrayOfDouble2[2];
    this.m10 = arrayOfDouble2[3];
    this.m11 = arrayOfDouble2[4];
    this.m12 = arrayOfDouble2[5];
    this.m20 = arrayOfDouble2[6];
    this.m21 = arrayOfDouble2[7];
    this.m22 = arrayOfDouble2[8];
  }
  
  public final void mulNormalize(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2)
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    double[] arrayOfDouble3 = new double[3];
    arrayOfDouble1[0] = (paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m02 * paramMatrix3d2.m20);
    arrayOfDouble1[1] = (paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m21);
    arrayOfDouble1[2] = (paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m01 * paramMatrix3d2.m12 + paramMatrix3d1.m02 * paramMatrix3d2.m22);
    arrayOfDouble1[3] = (paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m20);
    arrayOfDouble1[4] = (paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m21);
    arrayOfDouble1[5] = (paramMatrix3d1.m10 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m12 * paramMatrix3d2.m22);
    arrayOfDouble1[6] = (paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20);
    arrayOfDouble1[7] = (paramMatrix3d1.m20 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21);
    arrayOfDouble1[8] = (paramMatrix3d1.m20 * paramMatrix3d2.m02 + paramMatrix3d1.m21 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22);
    compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
    this.m00 = arrayOfDouble2[0];
    this.m01 = arrayOfDouble2[1];
    this.m02 = arrayOfDouble2[2];
    this.m10 = arrayOfDouble2[3];
    this.m11 = arrayOfDouble2[4];
    this.m12 = arrayOfDouble2[5];
    this.m20 = arrayOfDouble2[6];
    this.m21 = arrayOfDouble2[7];
    this.m22 = arrayOfDouble2[8];
  }
  
  public final void mulTransposeBoth(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2)
  {
    if ((this != paramMatrix3d1) && (this != paramMatrix3d2))
    {
      this.m00 = (paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m20 * paramMatrix3d2.m02);
      this.m01 = (paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m12);
      this.m02 = (paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m10 * paramMatrix3d2.m21 + paramMatrix3d1.m20 * paramMatrix3d2.m22);
      this.m10 = (paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m02);
      this.m11 = (paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m12);
      this.m12 = (paramMatrix3d1.m01 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m21 * paramMatrix3d2.m22);
      this.m20 = (paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02);
      this.m21 = (paramMatrix3d1.m02 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12);
      this.m22 = (paramMatrix3d1.m02 * paramMatrix3d2.m20 + paramMatrix3d1.m12 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22);
    }
    else
    {
      double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m01 + paramMatrix3d1.m20 * paramMatrix3d2.m02;
      double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m12;
      double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m10 * paramMatrix3d2.m21 + paramMatrix3d1.m20 * paramMatrix3d2.m22;
      double d4 = paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m21 * paramMatrix3d2.m02;
      double d5 = paramMatrix3d1.m01 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m12;
      double d6 = paramMatrix3d1.m01 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m21 * paramMatrix3d2.m22;
      double d7 = paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02;
      double d8 = paramMatrix3d1.m02 * paramMatrix3d2.m10 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12;
      double d9 = paramMatrix3d1.m02 * paramMatrix3d2.m20 + paramMatrix3d1.m12 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m10 = d4;
      this.m11 = d5;
      this.m12 = d6;
      this.m20 = d7;
      this.m21 = d8;
      this.m22 = d9;
    }
  }
  
  public final void mulTransposeRight(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2)
  {
    if ((this != paramMatrix3d1) && (this != paramMatrix3d2))
    {
      this.m00 = (paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m02 * paramMatrix3d2.m02);
      this.m01 = (paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m12);
      this.m02 = (paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m01 * paramMatrix3d2.m21 + paramMatrix3d1.m02 * paramMatrix3d2.m22);
      this.m10 = (paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m02);
      this.m11 = (paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m12);
      this.m12 = (paramMatrix3d1.m10 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m12 * paramMatrix3d2.m22);
      this.m20 = (paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02);
      this.m21 = (paramMatrix3d1.m20 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12);
      this.m22 = (paramMatrix3d1.m20 * paramMatrix3d2.m20 + paramMatrix3d1.m21 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22);
    }
    else
    {
      double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m02 * paramMatrix3d2.m02;
      double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m10 + paramMatrix3d1.m01 * paramMatrix3d2.m11 + paramMatrix3d1.m02 * paramMatrix3d2.m12;
      double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m20 + paramMatrix3d1.m01 * paramMatrix3d2.m21 + paramMatrix3d1.m02 * paramMatrix3d2.m22;
      double d4 = paramMatrix3d1.m10 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m02;
      double d5 = paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m12 * paramMatrix3d2.m12;
      double d6 = paramMatrix3d1.m10 * paramMatrix3d2.m20 + paramMatrix3d1.m11 * paramMatrix3d2.m21 + paramMatrix3d1.m12 * paramMatrix3d2.m22;
      double d7 = paramMatrix3d1.m20 * paramMatrix3d2.m00 + paramMatrix3d1.m21 * paramMatrix3d2.m01 + paramMatrix3d1.m22 * paramMatrix3d2.m02;
      double d8 = paramMatrix3d1.m20 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m12;
      double d9 = paramMatrix3d1.m20 * paramMatrix3d2.m20 + paramMatrix3d1.m21 * paramMatrix3d2.m21 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m10 = d4;
      this.m11 = d5;
      this.m12 = d6;
      this.m20 = d7;
      this.m21 = d8;
      this.m22 = d9;
    }
  }
  
  public final void mulTransposeLeft(Matrix3d paramMatrix3d1, Matrix3d paramMatrix3d2)
  {
    if ((this != paramMatrix3d1) && (this != paramMatrix3d2))
    {
      this.m00 = (paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m20 * paramMatrix3d2.m20);
      this.m01 = (paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m21);
      this.m02 = (paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m10 * paramMatrix3d2.m12 + paramMatrix3d1.m20 * paramMatrix3d2.m22);
      this.m10 = (paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m20);
      this.m11 = (paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m21);
      this.m12 = (paramMatrix3d1.m01 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m21 * paramMatrix3d2.m22);
      this.m20 = (paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20);
      this.m21 = (paramMatrix3d1.m02 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21);
      this.m22 = (paramMatrix3d1.m02 * paramMatrix3d2.m02 + paramMatrix3d1.m12 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22);
    }
    else
    {
      double d1 = paramMatrix3d1.m00 * paramMatrix3d2.m00 + paramMatrix3d1.m10 * paramMatrix3d2.m10 + paramMatrix3d1.m20 * paramMatrix3d2.m20;
      double d2 = paramMatrix3d1.m00 * paramMatrix3d2.m01 + paramMatrix3d1.m10 * paramMatrix3d2.m11 + paramMatrix3d1.m20 * paramMatrix3d2.m21;
      double d3 = paramMatrix3d1.m00 * paramMatrix3d2.m02 + paramMatrix3d1.m10 * paramMatrix3d2.m12 + paramMatrix3d1.m20 * paramMatrix3d2.m22;
      double d4 = paramMatrix3d1.m01 * paramMatrix3d2.m00 + paramMatrix3d1.m11 * paramMatrix3d2.m10 + paramMatrix3d1.m21 * paramMatrix3d2.m20;
      double d5 = paramMatrix3d1.m01 * paramMatrix3d2.m01 + paramMatrix3d1.m11 * paramMatrix3d2.m11 + paramMatrix3d1.m21 * paramMatrix3d2.m21;
      double d6 = paramMatrix3d1.m01 * paramMatrix3d2.m02 + paramMatrix3d1.m11 * paramMatrix3d2.m12 + paramMatrix3d1.m21 * paramMatrix3d2.m22;
      double d7 = paramMatrix3d1.m02 * paramMatrix3d2.m00 + paramMatrix3d1.m12 * paramMatrix3d2.m10 + paramMatrix3d1.m22 * paramMatrix3d2.m20;
      double d8 = paramMatrix3d1.m02 * paramMatrix3d2.m01 + paramMatrix3d1.m12 * paramMatrix3d2.m11 + paramMatrix3d1.m22 * paramMatrix3d2.m21;
      double d9 = paramMatrix3d1.m02 * paramMatrix3d2.m02 + paramMatrix3d1.m12 * paramMatrix3d2.m12 + paramMatrix3d1.m22 * paramMatrix3d2.m22;
      this.m00 = d1;
      this.m01 = d2;
      this.m02 = d3;
      this.m10 = d4;
      this.m11 = d5;
      this.m12 = d6;
      this.m20 = d7;
      this.m21 = d8;
      this.m22 = d9;
    }
  }
  
  public final void normalize()
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[3];
    getScaleRotate(arrayOfDouble2, arrayOfDouble1);
    this.m00 = arrayOfDouble1[0];
    this.m01 = arrayOfDouble1[1];
    this.m02 = arrayOfDouble1[2];
    this.m10 = arrayOfDouble1[3];
    this.m11 = arrayOfDouble1[4];
    this.m12 = arrayOfDouble1[5];
    this.m20 = arrayOfDouble1[6];
    this.m21 = arrayOfDouble1[7];
    this.m22 = arrayOfDouble1[8];
  }
  
  public final void normalize(Matrix3d paramMatrix3d)
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    double[] arrayOfDouble3 = new double[3];
    arrayOfDouble1[0] = paramMatrix3d.m00;
    arrayOfDouble1[1] = paramMatrix3d.m01;
    arrayOfDouble1[2] = paramMatrix3d.m02;
    arrayOfDouble1[3] = paramMatrix3d.m10;
    arrayOfDouble1[4] = paramMatrix3d.m11;
    arrayOfDouble1[5] = paramMatrix3d.m12;
    arrayOfDouble1[6] = paramMatrix3d.m20;
    arrayOfDouble1[7] = paramMatrix3d.m21;
    arrayOfDouble1[8] = paramMatrix3d.m22;
    compute_svd(arrayOfDouble1, arrayOfDouble3, arrayOfDouble2);
    this.m00 = arrayOfDouble2[0];
    this.m01 = arrayOfDouble2[1];
    this.m02 = arrayOfDouble2[2];
    this.m10 = arrayOfDouble2[3];
    this.m11 = arrayOfDouble2[4];
    this.m12 = arrayOfDouble2[5];
    this.m20 = arrayOfDouble2[6];
    this.m21 = arrayOfDouble2[7];
    this.m22 = arrayOfDouble2[8];
  }
  
  public final void normalizeCP()
  {
    double d = 1.0D / Math.sqrt(this.m00 * this.m00 + this.m10 * this.m10 + this.m20 * this.m20);
    this.m00 *= d;
    this.m10 *= d;
    this.m20 *= d;
    d = 1.0D / Math.sqrt(this.m01 * this.m01 + this.m11 * this.m11 + this.m21 * this.m21);
    this.m01 *= d;
    this.m11 *= d;
    this.m21 *= d;
    this.m02 = (this.m10 * this.m21 - this.m11 * this.m20);
    this.m12 = (this.m01 * this.m20 - this.m00 * this.m21);
    this.m22 = (this.m00 * this.m11 - this.m01 * this.m10);
  }
  
  public final void normalizeCP(Matrix3d paramMatrix3d)
  {
    double d = 1.0D / Math.sqrt(paramMatrix3d.m00 * paramMatrix3d.m00 + paramMatrix3d.m10 * paramMatrix3d.m10 + paramMatrix3d.m20 * paramMatrix3d.m20);
    paramMatrix3d.m00 *= d;
    paramMatrix3d.m10 *= d;
    paramMatrix3d.m20 *= d;
    d = 1.0D / Math.sqrt(paramMatrix3d.m01 * paramMatrix3d.m01 + paramMatrix3d.m11 * paramMatrix3d.m11 + paramMatrix3d.m21 * paramMatrix3d.m21);
    paramMatrix3d.m01 *= d;
    paramMatrix3d.m11 *= d;
    paramMatrix3d.m21 *= d;
    this.m02 = (this.m10 * this.m21 - this.m11 * this.m20);
    this.m12 = (this.m01 * this.m20 - this.m00 * this.m21);
    this.m22 = (this.m00 * this.m11 - this.m01 * this.m10);
  }
  
  public boolean equals(Matrix3d paramMatrix3d)
  {
    try
    {
      return (this.m00 == paramMatrix3d.m00) && (this.m01 == paramMatrix3d.m01) && (this.m02 == paramMatrix3d.m02) && (this.m10 == paramMatrix3d.m10) && (this.m11 == paramMatrix3d.m11) && (this.m12 == paramMatrix3d.m12) && (this.m20 == paramMatrix3d.m20) && (this.m21 == paramMatrix3d.m21) && (this.m22 == paramMatrix3d.m22);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Matrix3d localMatrix3d = (Matrix3d)paramObject;
      return (this.m00 == localMatrix3d.m00) && (this.m01 == localMatrix3d.m01) && (this.m02 == localMatrix3d.m02) && (this.m10 == localMatrix3d.m10) && (this.m11 == localMatrix3d.m11) && (this.m12 == localMatrix3d.m12) && (this.m20 == localMatrix3d.m20) && (this.m21 == localMatrix3d.m21) && (this.m22 == localMatrix3d.m22);
    }
    catch (ClassCastException localClassCastException)
    {
      return false;
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean epsilonEquals(Matrix3d paramMatrix3d, double paramDouble)
  {
    double d = this.m00 - paramMatrix3d.m00;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.m01 - paramMatrix3d.m01;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.m02 - paramMatrix3d.m02;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.m10 - paramMatrix3d.m10;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.m11 - paramMatrix3d.m11;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.m12 - paramMatrix3d.m12;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.m20 - paramMatrix3d.m20;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.m21 - paramMatrix3d.m21;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.m22 - paramMatrix3d.m22;
    return (d < 0.0D ? -d : d) <= paramDouble;
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m00);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m01);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m02);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m10);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m11);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m12);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m20);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m21);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.m22);
    return (int)(l ^ l >> 32);
  }
  
  public final void setZero()
  {
    this.m00 = 0.0D;
    this.m01 = 0.0D;
    this.m02 = 0.0D;
    this.m10 = 0.0D;
    this.m11 = 0.0D;
    this.m12 = 0.0D;
    this.m20 = 0.0D;
    this.m21 = 0.0D;
    this.m22 = 0.0D;
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
  
  public final void negate(Matrix3d paramMatrix3d)
  {
    this.m00 = (-paramMatrix3d.m00);
    this.m01 = (-paramMatrix3d.m01);
    this.m02 = (-paramMatrix3d.m02);
    this.m10 = (-paramMatrix3d.m10);
    this.m11 = (-paramMatrix3d.m11);
    this.m12 = (-paramMatrix3d.m12);
    this.m20 = (-paramMatrix3d.m20);
    this.m21 = (-paramMatrix3d.m21);
    this.m22 = (-paramMatrix3d.m22);
  }
  
  public final void transform(Tuple3d paramTuple3d)
  {
    double d1 = this.m00 * paramTuple3d.x + this.m01 * paramTuple3d.y + this.m02 * paramTuple3d.z;
    double d2 = this.m10 * paramTuple3d.x + this.m11 * paramTuple3d.y + this.m12 * paramTuple3d.z;
    double d3 = this.m20 * paramTuple3d.x + this.m21 * paramTuple3d.y + this.m22 * paramTuple3d.z;
    paramTuple3d.set(d1, d2, d3);
  }
  
  public final void transform(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2)
  {
    double d1 = this.m00 * paramTuple3d1.x + this.m01 * paramTuple3d1.y + this.m02 * paramTuple3d1.z;
    double d2 = this.m10 * paramTuple3d1.x + this.m11 * paramTuple3d1.y + this.m12 * paramTuple3d1.z;
    paramTuple3d2.z = (this.m20 * paramTuple3d1.x + this.m21 * paramTuple3d1.y + this.m22 * paramTuple3d1.z);
    paramTuple3d2.x = d1;
    paramTuple3d2.y = d2;
  }
  
  final void getScaleRotate(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
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
    compute_svd(arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2);
  }
  
  static void compute_svd(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3)
  {
    double[] arrayOfDouble1 = new double[9];
    double[] arrayOfDouble2 = new double[9];
    double[] arrayOfDouble3 = new double[9];
    double[] arrayOfDouble4 = new double[9];
    double[] arrayOfDouble5 = arrayOfDouble3;
    double[] arrayOfDouble6 = arrayOfDouble4;
    double[] arrayOfDouble7 = new double[9];
    double[] arrayOfDouble8 = new double[3];
    double[] arrayOfDouble9 = new double[3];
    int j = 0;
    for (int i = 0; i < 9; i++) {
      arrayOfDouble7[i] = paramArrayOfDouble1[i];
    }
    double d1;
    if (paramArrayOfDouble1[3] * paramArrayOfDouble1[3] < 1.110223024E-016D)
    {
      arrayOfDouble1[0] = 1.0D;
      arrayOfDouble1[1] = 0.0D;
      arrayOfDouble1[2] = 0.0D;
      arrayOfDouble1[3] = 0.0D;
      arrayOfDouble1[4] = 1.0D;
      arrayOfDouble1[5] = 0.0D;
      arrayOfDouble1[6] = 0.0D;
      arrayOfDouble1[7] = 0.0D;
      arrayOfDouble1[8] = 1.0D;
    }
    else if (paramArrayOfDouble1[0] * paramArrayOfDouble1[0] < 1.110223024E-016D)
    {
      arrayOfDouble5[0] = paramArrayOfDouble1[0];
      arrayOfDouble5[1] = paramArrayOfDouble1[1];
      arrayOfDouble5[2] = paramArrayOfDouble1[2];
      paramArrayOfDouble1[0] = paramArrayOfDouble1[3];
      paramArrayOfDouble1[1] = paramArrayOfDouble1[4];
      paramArrayOfDouble1[2] = paramArrayOfDouble1[5];
      paramArrayOfDouble1[3] = (-arrayOfDouble5[0]);
      paramArrayOfDouble1[4] = (-arrayOfDouble5[1]);
      paramArrayOfDouble1[5] = (-arrayOfDouble5[2]);
      arrayOfDouble1[0] = 0.0D;
      arrayOfDouble1[1] = 1.0D;
      arrayOfDouble1[2] = 0.0D;
      arrayOfDouble1[3] = -1.0D;
      arrayOfDouble1[4] = 0.0D;
      arrayOfDouble1[5] = 0.0D;
      arrayOfDouble1[6] = 0.0D;
      arrayOfDouble1[7] = 0.0D;
      arrayOfDouble1[8] = 1.0D;
    }
    else
    {
      d1 = 1.0D / Math.sqrt(paramArrayOfDouble1[0] * paramArrayOfDouble1[0] + paramArrayOfDouble1[3] * paramArrayOfDouble1[3]);
      double d2 = paramArrayOfDouble1[0] * d1;
      double d6 = paramArrayOfDouble1[3] * d1;
      arrayOfDouble5[0] = (d2 * paramArrayOfDouble1[0] + d6 * paramArrayOfDouble1[3]);
      arrayOfDouble5[1] = (d2 * paramArrayOfDouble1[1] + d6 * paramArrayOfDouble1[4]);
      arrayOfDouble5[2] = (d2 * paramArrayOfDouble1[2] + d6 * paramArrayOfDouble1[5]);
      paramArrayOfDouble1[3] = (-d6 * paramArrayOfDouble1[0] + d2 * paramArrayOfDouble1[3]);
      paramArrayOfDouble1[4] = (-d6 * paramArrayOfDouble1[1] + d2 * paramArrayOfDouble1[4]);
      paramArrayOfDouble1[5] = (-d6 * paramArrayOfDouble1[2] + d2 * paramArrayOfDouble1[5]);
      paramArrayOfDouble1[0] = arrayOfDouble5[0];
      paramArrayOfDouble1[1] = arrayOfDouble5[1];
      paramArrayOfDouble1[2] = arrayOfDouble5[2];
      arrayOfDouble1[0] = d2;
      arrayOfDouble1[1] = d6;
      arrayOfDouble1[2] = 0.0D;
      arrayOfDouble1[3] = (-d6);
      arrayOfDouble1[4] = d2;
      arrayOfDouble1[5] = 0.0D;
      arrayOfDouble1[6] = 0.0D;
      arrayOfDouble1[7] = 0.0D;
      arrayOfDouble1[8] = 1.0D;
    }
    if (paramArrayOfDouble1[6] * paramArrayOfDouble1[6] >= 1.110223024E-016D) {
      if (paramArrayOfDouble1[0] * paramArrayOfDouble1[0] < 1.110223024E-016D)
      {
        arrayOfDouble5[0] = paramArrayOfDouble1[0];
        arrayOfDouble5[1] = paramArrayOfDouble1[1];
        arrayOfDouble5[2] = paramArrayOfDouble1[2];
        paramArrayOfDouble1[0] = paramArrayOfDouble1[6];
        paramArrayOfDouble1[1] = paramArrayOfDouble1[7];
        paramArrayOfDouble1[2] = paramArrayOfDouble1[8];
        paramArrayOfDouble1[6] = (-arrayOfDouble5[0]);
        paramArrayOfDouble1[7] = (-arrayOfDouble5[1]);
        paramArrayOfDouble1[8] = (-arrayOfDouble5[2]);
        arrayOfDouble5[0] = arrayOfDouble1[0];
        arrayOfDouble5[1] = arrayOfDouble1[1];
        arrayOfDouble5[2] = arrayOfDouble1[2];
        arrayOfDouble1[0] = arrayOfDouble1[6];
        arrayOfDouble1[1] = arrayOfDouble1[7];
        arrayOfDouble1[2] = arrayOfDouble1[8];
        arrayOfDouble1[6] = (-arrayOfDouble5[0]);
        arrayOfDouble1[7] = (-arrayOfDouble5[1]);
        arrayOfDouble1[8] = (-arrayOfDouble5[2]);
      }
      else
      {
        d1 = 1.0D / Math.sqrt(paramArrayOfDouble1[0] * paramArrayOfDouble1[0] + paramArrayOfDouble1[6] * paramArrayOfDouble1[6]);
        double d3 = paramArrayOfDouble1[0] * d1;
        double d7 = paramArrayOfDouble1[6] * d1;
        arrayOfDouble5[0] = (d3 * paramArrayOfDouble1[0] + d7 * paramArrayOfDouble1[6]);
        arrayOfDouble5[1] = (d3 * paramArrayOfDouble1[1] + d7 * paramArrayOfDouble1[7]);
        arrayOfDouble5[2] = (d3 * paramArrayOfDouble1[2] + d7 * paramArrayOfDouble1[8]);
        paramArrayOfDouble1[6] = (-d7 * paramArrayOfDouble1[0] + d3 * paramArrayOfDouble1[6]);
        paramArrayOfDouble1[7] = (-d7 * paramArrayOfDouble1[1] + d3 * paramArrayOfDouble1[7]);
        paramArrayOfDouble1[8] = (-d7 * paramArrayOfDouble1[2] + d3 * paramArrayOfDouble1[8]);
        paramArrayOfDouble1[0] = arrayOfDouble5[0];
        paramArrayOfDouble1[1] = arrayOfDouble5[1];
        paramArrayOfDouble1[2] = arrayOfDouble5[2];
        arrayOfDouble5[0] = (d3 * arrayOfDouble1[0]);
        arrayOfDouble5[1] = (d3 * arrayOfDouble1[1]);
        arrayOfDouble1[2] = d7;
        arrayOfDouble5[6] = (-arrayOfDouble1[0] * d7);
        arrayOfDouble5[7] = (-arrayOfDouble1[1] * d7);
        arrayOfDouble1[8] = d3;
        arrayOfDouble1[0] = arrayOfDouble5[0];
        arrayOfDouble1[1] = arrayOfDouble5[1];
        arrayOfDouble1[6] = arrayOfDouble5[6];
        arrayOfDouble1[7] = arrayOfDouble5[7];
      }
    }
    if (paramArrayOfDouble1[2] * paramArrayOfDouble1[2] < 1.110223024E-016D)
    {
      arrayOfDouble2[0] = 1.0D;
      arrayOfDouble2[1] = 0.0D;
      arrayOfDouble2[2] = 0.0D;
      arrayOfDouble2[3] = 0.0D;
      arrayOfDouble2[4] = 1.0D;
      arrayOfDouble2[5] = 0.0D;
      arrayOfDouble2[6] = 0.0D;
      arrayOfDouble2[7] = 0.0D;
      arrayOfDouble2[8] = 1.0D;
    }
    else if (paramArrayOfDouble1[1] * paramArrayOfDouble1[1] < 1.110223024E-016D)
    {
      arrayOfDouble5[2] = paramArrayOfDouble1[2];
      arrayOfDouble5[5] = paramArrayOfDouble1[5];
      arrayOfDouble5[8] = paramArrayOfDouble1[8];
      paramArrayOfDouble1[2] = (-paramArrayOfDouble1[1]);
      paramArrayOfDouble1[5] = (-paramArrayOfDouble1[4]);
      paramArrayOfDouble1[8] = (-paramArrayOfDouble1[7]);
      paramArrayOfDouble1[1] = arrayOfDouble5[2];
      paramArrayOfDouble1[4] = arrayOfDouble5[5];
      paramArrayOfDouble1[7] = arrayOfDouble5[8];
      arrayOfDouble2[0] = 1.0D;
      arrayOfDouble2[1] = 0.0D;
      arrayOfDouble2[2] = 0.0D;
      arrayOfDouble2[3] = 0.0D;
      arrayOfDouble2[4] = 0.0D;
      arrayOfDouble2[5] = -1.0D;
      arrayOfDouble2[6] = 0.0D;
      arrayOfDouble2[7] = 1.0D;
      arrayOfDouble2[8] = 0.0D;
    }
    else
    {
      d1 = 1.0D / Math.sqrt(paramArrayOfDouble1[1] * paramArrayOfDouble1[1] + paramArrayOfDouble1[2] * paramArrayOfDouble1[2]);
      double d4 = paramArrayOfDouble1[1] * d1;
      double d8 = paramArrayOfDouble1[2] * d1;
      arrayOfDouble5[1] = (d4 * paramArrayOfDouble1[1] + d8 * paramArrayOfDouble1[2]);
      paramArrayOfDouble1[2] = (-d8 * paramArrayOfDouble1[1] + d4 * paramArrayOfDouble1[2]);
      paramArrayOfDouble1[1] = arrayOfDouble5[1];
      arrayOfDouble5[4] = (d4 * paramArrayOfDouble1[4] + d8 * paramArrayOfDouble1[5]);
      paramArrayOfDouble1[5] = (-d8 * paramArrayOfDouble1[4] + d4 * paramArrayOfDouble1[5]);
      paramArrayOfDouble1[4] = arrayOfDouble5[4];
      arrayOfDouble5[7] = (d4 * paramArrayOfDouble1[7] + d8 * paramArrayOfDouble1[8]);
      paramArrayOfDouble1[8] = (-d8 * paramArrayOfDouble1[7] + d4 * paramArrayOfDouble1[8]);
      paramArrayOfDouble1[7] = arrayOfDouble5[7];
      arrayOfDouble2[0] = 1.0D;
      arrayOfDouble2[1] = 0.0D;
      arrayOfDouble2[2] = 0.0D;
      arrayOfDouble2[3] = 0.0D;
      arrayOfDouble2[4] = d4;
      arrayOfDouble2[5] = (-d8);
      arrayOfDouble2[6] = 0.0D;
      arrayOfDouble2[7] = d8;
      arrayOfDouble2[8] = d4;
    }
    if (paramArrayOfDouble1[7] * paramArrayOfDouble1[7] >= 1.110223024E-016D) {
      if (paramArrayOfDouble1[4] * paramArrayOfDouble1[4] < 1.110223024E-016D)
      {
        arrayOfDouble5[3] = paramArrayOfDouble1[3];
        arrayOfDouble5[4] = paramArrayOfDouble1[4];
        arrayOfDouble5[5] = paramArrayOfDouble1[5];
        paramArrayOfDouble1[3] = paramArrayOfDouble1[6];
        paramArrayOfDouble1[4] = paramArrayOfDouble1[7];
        paramArrayOfDouble1[5] = paramArrayOfDouble1[8];
        paramArrayOfDouble1[6] = (-arrayOfDouble5[3]);
        paramArrayOfDouble1[7] = (-arrayOfDouble5[4]);
        paramArrayOfDouble1[8] = (-arrayOfDouble5[5]);
        arrayOfDouble5[3] = arrayOfDouble1[3];
        arrayOfDouble5[4] = arrayOfDouble1[4];
        arrayOfDouble5[5] = arrayOfDouble1[5];
        arrayOfDouble1[3] = arrayOfDouble1[6];
        arrayOfDouble1[4] = arrayOfDouble1[7];
        arrayOfDouble1[5] = arrayOfDouble1[8];
        arrayOfDouble1[6] = (-arrayOfDouble5[3]);
        arrayOfDouble1[7] = (-arrayOfDouble5[4]);
        arrayOfDouble1[8] = (-arrayOfDouble5[5]);
      }
      else
      {
        d1 = 1.0D / Math.sqrt(paramArrayOfDouble1[4] * paramArrayOfDouble1[4] + paramArrayOfDouble1[7] * paramArrayOfDouble1[7]);
        double d5 = paramArrayOfDouble1[4] * d1;
        double d9 = paramArrayOfDouble1[7] * d1;
        arrayOfDouble5[3] = (d5 * paramArrayOfDouble1[3] + d9 * paramArrayOfDouble1[6]);
        paramArrayOfDouble1[6] = (-d9 * paramArrayOfDouble1[3] + d5 * paramArrayOfDouble1[6]);
        paramArrayOfDouble1[3] = arrayOfDouble5[3];
        arrayOfDouble5[4] = (d5 * paramArrayOfDouble1[4] + d9 * paramArrayOfDouble1[7]);
        paramArrayOfDouble1[7] = (-d9 * paramArrayOfDouble1[4] + d5 * paramArrayOfDouble1[7]);
        paramArrayOfDouble1[4] = arrayOfDouble5[4];
        arrayOfDouble5[5] = (d5 * paramArrayOfDouble1[5] + d9 * paramArrayOfDouble1[8]);
        paramArrayOfDouble1[8] = (-d9 * paramArrayOfDouble1[5] + d5 * paramArrayOfDouble1[8]);
        paramArrayOfDouble1[5] = arrayOfDouble5[5];
        arrayOfDouble5[3] = (d5 * arrayOfDouble1[3] + d9 * arrayOfDouble1[6]);
        arrayOfDouble1[6] = (-d9 * arrayOfDouble1[3] + d5 * arrayOfDouble1[6]);
        arrayOfDouble1[3] = arrayOfDouble5[3];
        arrayOfDouble5[4] = (d5 * arrayOfDouble1[4] + d9 * arrayOfDouble1[7]);
        arrayOfDouble1[7] = (-d9 * arrayOfDouble1[4] + d5 * arrayOfDouble1[7]);
        arrayOfDouble1[4] = arrayOfDouble5[4];
        arrayOfDouble5[5] = (d5 * arrayOfDouble1[5] + d9 * arrayOfDouble1[8]);
        arrayOfDouble1[8] = (-d9 * arrayOfDouble1[5] + d5 * arrayOfDouble1[8]);
        arrayOfDouble1[5] = arrayOfDouble5[5];
      }
    }
    arrayOfDouble6[0] = paramArrayOfDouble1[0];
    arrayOfDouble6[1] = paramArrayOfDouble1[4];
    arrayOfDouble6[2] = paramArrayOfDouble1[8];
    arrayOfDouble8[0] = paramArrayOfDouble1[1];
    arrayOfDouble8[1] = paramArrayOfDouble1[5];
    if ((arrayOfDouble8[0] * arrayOfDouble8[0] >= 1.110223024E-016D) || (arrayOfDouble8[1] * arrayOfDouble8[1] >= 1.110223024E-016D)) {
      compute_qr(arrayOfDouble6, arrayOfDouble8, arrayOfDouble1, arrayOfDouble2);
    }
    arrayOfDouble9[0] = arrayOfDouble6[0];
    arrayOfDouble9[1] = arrayOfDouble6[1];
    arrayOfDouble9[2] = arrayOfDouble6[2];
    if ((almostEqual(Math.abs(arrayOfDouble9[0]), 1.0D)) && (almostEqual(Math.abs(arrayOfDouble9[1]), 1.0D)) && (almostEqual(Math.abs(arrayOfDouble9[2]), 1.0D)))
    {
      for (i = 0; i < 3; i++) {
        if (arrayOfDouble9[i] < 0.0D) {
          j++;
        }
      }
      if ((j == 0) || (j == 2))
      {
        double tmp2063_2062 = (paramArrayOfDouble2[2] = 1.0D);
        paramArrayOfDouble2[1] = tmp2063_2062;
        paramArrayOfDouble2[0] = tmp2063_2062;
        for (i = 0; i < 9; i++) {
          paramArrayOfDouble3[i] = arrayOfDouble7[i];
        }
        return;
      }
    }
    transpose_mat(arrayOfDouble1, arrayOfDouble3);
    transpose_mat(arrayOfDouble2, arrayOfDouble4);
    svdReorder(paramArrayOfDouble1, arrayOfDouble3, arrayOfDouble4, arrayOfDouble9, paramArrayOfDouble3, paramArrayOfDouble2);
  }
  
  static void svdReorder(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4, double[] paramArrayOfDouble5, double[] paramArrayOfDouble6)
  {
    int[] arrayOfInt1 = new int[3];
    int[] arrayOfInt2 = new int[3];
    double[] arrayOfDouble1 = new double[3];
    double[] arrayOfDouble2 = new double[9];
    if (paramArrayOfDouble4[0] < 0.0D)
    {
      paramArrayOfDouble4[0] = (-paramArrayOfDouble4[0]);
      paramArrayOfDouble3[0] = (-paramArrayOfDouble3[0]);
      paramArrayOfDouble3[1] = (-paramArrayOfDouble3[1]);
      paramArrayOfDouble3[2] = (-paramArrayOfDouble3[2]);
    }
    if (paramArrayOfDouble4[1] < 0.0D)
    {
      paramArrayOfDouble4[1] = (-paramArrayOfDouble4[1]);
      paramArrayOfDouble3[3] = (-paramArrayOfDouble3[3]);
      paramArrayOfDouble3[4] = (-paramArrayOfDouble3[4]);
      paramArrayOfDouble3[5] = (-paramArrayOfDouble3[5]);
    }
    if (paramArrayOfDouble4[2] < 0.0D)
    {
      paramArrayOfDouble4[2] = (-paramArrayOfDouble4[2]);
      paramArrayOfDouble3[6] = (-paramArrayOfDouble3[6]);
      paramArrayOfDouble3[7] = (-paramArrayOfDouble3[7]);
      paramArrayOfDouble3[8] = (-paramArrayOfDouble3[8]);
    }
    mat_mul(paramArrayOfDouble2, paramArrayOfDouble3, arrayOfDouble2);
    int n;
    if ((almostEqual(Math.abs(paramArrayOfDouble4[0]), Math.abs(paramArrayOfDouble4[1]))) && (almostEqual(Math.abs(paramArrayOfDouble4[1]), Math.abs(paramArrayOfDouble4[2]))))
    {
      for (n = 0; n < 9; n++) {
        paramArrayOfDouble5[n] = arrayOfDouble2[n];
      }
      n = 0;
    }
    while (n < 3)
    {
      paramArrayOfDouble6[n] = paramArrayOfDouble4[n];
      n++;
      continue;
      if (paramArrayOfDouble4[0] > paramArrayOfDouble4[1])
      {
        if (paramArrayOfDouble4[0] > paramArrayOfDouble4[2])
        {
          if (paramArrayOfDouble4[2] > paramArrayOfDouble4[1])
          {
            arrayOfInt1[0] = 0;
            arrayOfInt1[1] = 2;
            arrayOfInt1[2] = 1;
          }
          else
          {
            arrayOfInt1[0] = 0;
            arrayOfInt1[1] = 1;
            arrayOfInt1[2] = 2;
          }
        }
        else
        {
          arrayOfInt1[0] = 2;
          arrayOfInt1[1] = 0;
          arrayOfInt1[2] = 1;
        }
      }
      else if (paramArrayOfDouble4[1] > paramArrayOfDouble4[2])
      {
        if (paramArrayOfDouble4[2] > paramArrayOfDouble4[0])
        {
          arrayOfInt1[0] = 1;
          arrayOfInt1[1] = 2;
          arrayOfInt1[2] = 0;
        }
        else
        {
          arrayOfInt1[0] = 1;
          arrayOfInt1[1] = 0;
          arrayOfInt1[2] = 2;
        }
      }
      else
      {
        arrayOfInt1[0] = 2;
        arrayOfInt1[1] = 1;
        arrayOfInt1[2] = 0;
      }
      arrayOfDouble1[0] = (paramArrayOfDouble1[0] * paramArrayOfDouble1[0] + paramArrayOfDouble1[1] * paramArrayOfDouble1[1] + paramArrayOfDouble1[2] * paramArrayOfDouble1[2]);
      arrayOfDouble1[1] = (paramArrayOfDouble1[3] * paramArrayOfDouble1[3] + paramArrayOfDouble1[4] * paramArrayOfDouble1[4] + paramArrayOfDouble1[5] * paramArrayOfDouble1[5]);
      arrayOfDouble1[2] = (paramArrayOfDouble1[6] * paramArrayOfDouble1[6] + paramArrayOfDouble1[7] * paramArrayOfDouble1[7] + paramArrayOfDouble1[8] * paramArrayOfDouble1[8]);
      int i;
      int k;
      int j;
      if (arrayOfDouble1[0] > arrayOfDouble1[1])
      {
        if (arrayOfDouble1[0] > arrayOfDouble1[2])
        {
          if (arrayOfDouble1[2] > arrayOfDouble1[1])
          {
            i = 0;
            k = 1;
            j = 2;
          }
          else
          {
            i = 0;
            j = 1;
            k = 2;
          }
        }
        else
        {
          k = 0;
          i = 1;
          j = 2;
        }
      }
      else if (arrayOfDouble1[1] > arrayOfDouble1[2])
      {
        if (arrayOfDouble1[2] > arrayOfDouble1[0])
        {
          j = 0;
          k = 1;
          i = 2;
        }
        else
        {
          j = 0;
          i = 1;
          k = 2;
        }
      }
      else
      {
        k = 0;
        j = 1;
        i = 2;
      }
      int m = arrayOfInt1[i];
      paramArrayOfDouble6[0] = paramArrayOfDouble4[m];
      m = arrayOfInt1[j];
      paramArrayOfDouble6[1] = paramArrayOfDouble4[m];
      m = arrayOfInt1[k];
      paramArrayOfDouble6[2] = paramArrayOfDouble4[m];
      m = arrayOfInt1[i];
      paramArrayOfDouble5[0] = arrayOfDouble2[m];
      m = arrayOfInt1[i] + 3;
      paramArrayOfDouble5[3] = arrayOfDouble2[m];
      m = arrayOfInt1[i] + 6;
      paramArrayOfDouble5[6] = arrayOfDouble2[m];
      m = arrayOfInt1[j];
      paramArrayOfDouble5[1] = arrayOfDouble2[m];
      m = arrayOfInt1[j] + 3;
      paramArrayOfDouble5[4] = arrayOfDouble2[m];
      m = arrayOfInt1[j] + 6;
      paramArrayOfDouble5[7] = arrayOfDouble2[m];
      m = arrayOfInt1[k];
      paramArrayOfDouble5[2] = arrayOfDouble2[m];
      m = arrayOfInt1[k] + 3;
      paramArrayOfDouble5[5] = arrayOfDouble2[m];
      m = arrayOfInt1[k] + 6;
      paramArrayOfDouble5[8] = arrayOfDouble2[m];
    }
  }
  
  static int compute_qr(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4)
  {
    double[] arrayOfDouble1 = new double[2];
    double[] arrayOfDouble2 = new double[2];
    double[] arrayOfDouble3 = new double[2];
    double[] arrayOfDouble4 = new double[2];
    double[] arrayOfDouble5 = new double[9];
    double d7 = 1.0D;
    double d8 = -1.0D;
    int j = 0;
    int k = 1;
    if ((Math.abs(paramArrayOfDouble2[1]) < 4.89E-015D) || (Math.abs(paramArrayOfDouble2[0]) < 4.89E-015D)) {
      j = 1;
    }
    double d3;
    double d4;
    for (int i = 0; (i < 10) && (j == 0); i++)
    {
      double d1 = compute_shift(paramArrayOfDouble1[1], paramArrayOfDouble2[1], paramArrayOfDouble1[2]);
      double d5 = (Math.abs(paramArrayOfDouble1[0]) - d1) * (d_sign(d7, paramArrayOfDouble1[0]) + d1 / paramArrayOfDouble1[0]);
      double d6 = paramArrayOfDouble2[0];
      double d2 = compute_rot(d5, d6, arrayOfDouble4, arrayOfDouble2, 0, k);
      d5 = arrayOfDouble2[0] * paramArrayOfDouble1[0] + arrayOfDouble4[0] * paramArrayOfDouble2[0];
      paramArrayOfDouble2[0] = (arrayOfDouble2[0] * paramArrayOfDouble2[0] - arrayOfDouble4[0] * paramArrayOfDouble1[0]);
      d6 = arrayOfDouble4[0] * paramArrayOfDouble1[1];
      paramArrayOfDouble1[1] = (arrayOfDouble2[0] * paramArrayOfDouble1[1]);
      d2 = compute_rot(d5, d6, arrayOfDouble3, arrayOfDouble1, 0, k);
      k = 0;
      paramArrayOfDouble1[0] = d2;
      d5 = arrayOfDouble1[0] * paramArrayOfDouble2[0] + arrayOfDouble3[0] * paramArrayOfDouble1[1];
      paramArrayOfDouble1[1] = (arrayOfDouble1[0] * paramArrayOfDouble1[1] - arrayOfDouble3[0] * paramArrayOfDouble2[0]);
      d6 = arrayOfDouble3[0] * paramArrayOfDouble2[1];
      paramArrayOfDouble2[1] = (arrayOfDouble1[0] * paramArrayOfDouble2[1]);
      d2 = compute_rot(d5, d6, arrayOfDouble4, arrayOfDouble2, 1, k);
      paramArrayOfDouble2[0] = d2;
      d5 = arrayOfDouble2[1] * paramArrayOfDouble1[1] + arrayOfDouble4[1] * paramArrayOfDouble2[1];
      paramArrayOfDouble2[1] = (arrayOfDouble2[1] * paramArrayOfDouble2[1] - arrayOfDouble4[1] * paramArrayOfDouble1[1]);
      d6 = arrayOfDouble4[1] * paramArrayOfDouble1[2];
      paramArrayOfDouble1[2] = (arrayOfDouble2[1] * paramArrayOfDouble1[2]);
      d2 = compute_rot(d5, d6, arrayOfDouble3, arrayOfDouble1, 1, k);
      paramArrayOfDouble1[1] = d2;
      d5 = arrayOfDouble1[1] * paramArrayOfDouble2[1] + arrayOfDouble3[1] * paramArrayOfDouble1[2];
      paramArrayOfDouble1[2] = (arrayOfDouble1[1] * paramArrayOfDouble1[2] - arrayOfDouble3[1] * paramArrayOfDouble2[1]);
      paramArrayOfDouble2[1] = d5;
      d3 = paramArrayOfDouble3[0];
      paramArrayOfDouble3[0] = (arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[3]);
      paramArrayOfDouble3[3] = (-arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[3]);
      d3 = paramArrayOfDouble3[1];
      paramArrayOfDouble3[1] = (arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[4]);
      paramArrayOfDouble3[4] = (-arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[4]);
      d3 = paramArrayOfDouble3[2];
      paramArrayOfDouble3[2] = (arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[5]);
      paramArrayOfDouble3[5] = (-arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[5]);
      d3 = paramArrayOfDouble3[3];
      paramArrayOfDouble3[3] = (arrayOfDouble1[1] * d3 + arrayOfDouble3[1] * paramArrayOfDouble3[6]);
      paramArrayOfDouble3[6] = (-arrayOfDouble3[1] * d3 + arrayOfDouble1[1] * paramArrayOfDouble3[6]);
      d3 = paramArrayOfDouble3[4];
      paramArrayOfDouble3[4] = (arrayOfDouble1[1] * d3 + arrayOfDouble3[1] * paramArrayOfDouble3[7]);
      paramArrayOfDouble3[7] = (-arrayOfDouble3[1] * d3 + arrayOfDouble1[1] * paramArrayOfDouble3[7]);
      d3 = paramArrayOfDouble3[5];
      paramArrayOfDouble3[5] = (arrayOfDouble1[1] * d3 + arrayOfDouble3[1] * paramArrayOfDouble3[8]);
      paramArrayOfDouble3[8] = (-arrayOfDouble3[1] * d3 + arrayOfDouble1[1] * paramArrayOfDouble3[8]);
      d4 = paramArrayOfDouble4[0];
      paramArrayOfDouble4[0] = (arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[1]);
      paramArrayOfDouble4[1] = (-arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[1]);
      d4 = paramArrayOfDouble4[3];
      paramArrayOfDouble4[3] = (arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[4]);
      paramArrayOfDouble4[4] = (-arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[4]);
      d4 = paramArrayOfDouble4[6];
      paramArrayOfDouble4[6] = (arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[7]);
      paramArrayOfDouble4[7] = (-arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[7]);
      d4 = paramArrayOfDouble4[1];
      paramArrayOfDouble4[1] = (arrayOfDouble2[1] * d4 + arrayOfDouble4[1] * paramArrayOfDouble4[2]);
      paramArrayOfDouble4[2] = (-arrayOfDouble4[1] * d4 + arrayOfDouble2[1] * paramArrayOfDouble4[2]);
      d4 = paramArrayOfDouble4[4];
      paramArrayOfDouble4[4] = (arrayOfDouble2[1] * d4 + arrayOfDouble4[1] * paramArrayOfDouble4[5]);
      paramArrayOfDouble4[5] = (-arrayOfDouble4[1] * d4 + arrayOfDouble2[1] * paramArrayOfDouble4[5]);
      d4 = paramArrayOfDouble4[7];
      paramArrayOfDouble4[7] = (arrayOfDouble2[1] * d4 + arrayOfDouble4[1] * paramArrayOfDouble4[8]);
      paramArrayOfDouble4[8] = (-arrayOfDouble4[1] * d4 + arrayOfDouble2[1] * paramArrayOfDouble4[8]);
      arrayOfDouble5[0] = paramArrayOfDouble1[0];
      arrayOfDouble5[1] = paramArrayOfDouble2[0];
      arrayOfDouble5[2] = 0.0D;
      arrayOfDouble5[3] = 0.0D;
      arrayOfDouble5[4] = paramArrayOfDouble1[1];
      arrayOfDouble5[5] = paramArrayOfDouble2[1];
      arrayOfDouble5[6] = 0.0D;
      arrayOfDouble5[7] = 0.0D;
      arrayOfDouble5[8] = paramArrayOfDouble1[2];
      if ((Math.abs(paramArrayOfDouble2[1]) < 4.89E-015D) || (Math.abs(paramArrayOfDouble2[0]) < 4.89E-015D)) {
        j = 1;
      }
    }
    if (Math.abs(paramArrayOfDouble2[1]) < 4.89E-015D)
    {
      compute_2X2(paramArrayOfDouble1[0], paramArrayOfDouble2[0], paramArrayOfDouble1[1], paramArrayOfDouble1, arrayOfDouble3, arrayOfDouble1, arrayOfDouble4, arrayOfDouble2, 0);
      d3 = paramArrayOfDouble3[0];
      paramArrayOfDouble3[0] = (arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[3]);
      paramArrayOfDouble3[3] = (-arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[3]);
      d3 = paramArrayOfDouble3[1];
      paramArrayOfDouble3[1] = (arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[4]);
      paramArrayOfDouble3[4] = (-arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[4]);
      d3 = paramArrayOfDouble3[2];
      paramArrayOfDouble3[2] = (arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[5]);
      paramArrayOfDouble3[5] = (-arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[5]);
      d4 = paramArrayOfDouble4[0];
      paramArrayOfDouble4[0] = (arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[1]);
      paramArrayOfDouble4[1] = (-arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[1]);
      d4 = paramArrayOfDouble4[3];
      paramArrayOfDouble4[3] = (arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[4]);
      paramArrayOfDouble4[4] = (-arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[4]);
      d4 = paramArrayOfDouble4[6];
      paramArrayOfDouble4[6] = (arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[7]);
      paramArrayOfDouble4[7] = (-arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[7]);
    }
    else
    {
      compute_2X2(paramArrayOfDouble1[1], paramArrayOfDouble2[1], paramArrayOfDouble1[2], paramArrayOfDouble1, arrayOfDouble3, arrayOfDouble1, arrayOfDouble4, arrayOfDouble2, 1);
      d3 = paramArrayOfDouble3[3];
      paramArrayOfDouble3[3] = (arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[6]);
      paramArrayOfDouble3[6] = (-arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[6]);
      d3 = paramArrayOfDouble3[4];
      paramArrayOfDouble3[4] = (arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[7]);
      paramArrayOfDouble3[7] = (-arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[7]);
      d3 = paramArrayOfDouble3[5];
      paramArrayOfDouble3[5] = (arrayOfDouble1[0] * d3 + arrayOfDouble3[0] * paramArrayOfDouble3[8]);
      paramArrayOfDouble3[8] = (-arrayOfDouble3[0] * d3 + arrayOfDouble1[0] * paramArrayOfDouble3[8]);
      d4 = paramArrayOfDouble4[1];
      paramArrayOfDouble4[1] = (arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[2]);
      paramArrayOfDouble4[2] = (-arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[2]);
      d4 = paramArrayOfDouble4[4];
      paramArrayOfDouble4[4] = (arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[5]);
      paramArrayOfDouble4[5] = (-arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[5]);
      d4 = paramArrayOfDouble4[7];
      paramArrayOfDouble4[7] = (arrayOfDouble2[0] * d4 + arrayOfDouble4[0] * paramArrayOfDouble4[8]);
      paramArrayOfDouble4[8] = (-arrayOfDouble4[0] * d4 + arrayOfDouble2[0] * paramArrayOfDouble4[8]);
    }
    return 0;
  }
  
  static double max(double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 > paramDouble2) {
      return paramDouble1;
    }
    return paramDouble2;
  }
  
  static double min(double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 < paramDouble2) {
      return paramDouble1;
    }
    return paramDouble2;
  }
  
  static double d_sign(double paramDouble1, double paramDouble2)
  {
    double d = paramDouble1 >= 0.0D ? paramDouble1 : -paramDouble1;
    return paramDouble2 >= 0.0D ? d : -d;
  }
  
  static double compute_shift(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    double d6 = Math.abs(paramDouble1);
    double d7 = Math.abs(paramDouble2);
    double d8 = Math.abs(paramDouble3);
    double d3 = min(d6, d8);
    double d4 = max(d6, d8);
    double d12;
    double d1;
    if (d3 == 0.0D)
    {
      d12 = 0.0D;
      if (d4 != 0.0D) {
        d1 = min(d4, d7) / max(d4, d7);
      }
    }
    else
    {
      double d9;
      double d10;
      double d11;
      double d5;
      if (d7 < d4)
      {
        d9 = d3 / d4 + 1.0D;
        d10 = (d4 - d3) / d4;
        d1 = d7 / d4;
        d11 = d1 * d1;
        d5 = 2.0D / (Math.sqrt(d9 * d9 + d11) + Math.sqrt(d10 * d10 + d11));
        d12 = d3 * d5;
      }
      else
      {
        d11 = d4 / d7;
        if (d11 == 0.0D)
        {
          d12 = d3 * d4 / d7;
        }
        else
        {
          d9 = d3 / d4 + 1.0D;
          d10 = (d4 - d3) / d4;
          d1 = d9 * d11;
          double d2 = d10 * d11;
          d5 = 1.0D / (Math.sqrt(d1 * d1 + 1.0D) + Math.sqrt(d2 * d2 + 1.0D));
          d12 = d3 * d5 * d11;
          d12 += d12;
        }
      }
    }
    return d12;
  }
  
  static int compute_2X2(double paramDouble1, double paramDouble2, double paramDouble3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4, double[] paramArrayOfDouble5, int paramInt)
  {
    double d1 = 2.0D;
    double d2 = 1.0D;
    double d26 = paramArrayOfDouble1[0];
    double d25 = paramArrayOfDouble1[1];
    double d21 = 0.0D;
    double d22 = 0.0D;
    double d23 = 0.0D;
    double d24 = 0.0D;
    double d12 = 0.0D;
    double d16 = paramDouble1;
    double d13 = Math.abs(d16);
    double d18 = paramDouble3;
    double d15 = Math.abs(paramDouble3);
    int i = 1;
    int j;
    if (d15 > d13) {
      j = 1;
    } else {
      j = 0;
    }
    if (j != 0)
    {
      i = 3;
      double d4 = d16;
      d16 = d18;
      d18 = d4;
      d4 = d13;
      d13 = d15;
      d15 = d4;
    }
    double d17 = paramDouble2;
    double d14 = Math.abs(d17);
    if (d14 == 0.0D)
    {
      paramArrayOfDouble1[1] = d15;
      paramArrayOfDouble1[0] = d13;
      d21 = 1.0D;
      d22 = 1.0D;
      d23 = 0.0D;
      d24 = 0.0D;
    }
    else
    {
      int k = 1;
      if (d14 > d13)
      {
        i = 2;
        if (d13 / d14 < 1.110223024E-016D)
        {
          k = 0;
          d26 = d14;
          if (d15 > 1.0D) {
            d25 = d13 / (d14 / d15);
          } else {
            d25 = d13 / d14 * d15;
          }
          d21 = 1.0D;
          d23 = d18 / d17;
          d24 = 1.0D;
          d22 = d16 / d17;
        }
      }
      if (k != 0)
      {
        double d6 = d13 - d15;
        double d7;
        if (d6 == d13) {
          d7 = 1.0D;
        } else {
          d7 = d6 / d13;
        }
        double d8 = d17 / d16;
        double d11 = 2.0D - d7;
        double d19 = d8 * d8;
        double d20 = d11 * d11;
        double d10 = Math.sqrt(d20 + d19);
        double d9;
        if (d7 == 0.0D) {
          d9 = Math.abs(d8);
        } else {
          d9 = Math.sqrt(d7 * d7 + d19);
        }
        double d5 = (d10 + d9) * 0.5D;
        if (d14 > d13)
        {
          i = 2;
          if (d13 / d14 < 1.110223024E-016D)
          {
            k = 0;
            d26 = d14;
            if (d15 > 1.0D) {
              d25 = d13 / (d14 / d15);
            } else {
              d25 = d13 / d14 * d15;
            }
            d21 = 1.0D;
            d23 = d18 / d17;
            d24 = 1.0D;
            d22 = d16 / d17;
          }
        }
        if (k != 0)
        {
          d6 = d13 - d15;
          if (d6 == d13) {
            d7 = 1.0D;
          } else {
            d7 = d6 / d13;
          }
          d8 = d17 / d16;
          d11 = 2.0D - d7;
          d19 = d8 * d8;
          d20 = d11 * d11;
          d10 = Math.sqrt(d20 + d19);
          if (d7 == 0.0D) {
            d9 = Math.abs(d8);
          } else {
            d9 = Math.sqrt(d7 * d7 + d19);
          }
          d5 = (d10 + d9) * 0.5D;
          d25 = d15 / d5;
          d26 = d13 * d5;
          if (d19 == 0.0D)
          {
            if (d7 == 0.0D) {
              d11 = d_sign(d1, d16) * d_sign(d2, d17);
            } else {
              d11 = d17 / d_sign(d6, d16) + d8 / d11;
            }
          }
          else {
            d11 = (d8 / (d10 + d11) + d8 / (d9 + d7)) * (d5 + 1.0D);
          }
          d7 = Math.sqrt(d11 * d11 + 4.0D);
          d22 = 2.0D / d7;
          d24 = d11 / d7;
          d21 = (d22 + d24 * d8) / d5;
          d23 = d18 / d16 * d24 / d5;
        }
      }
      if (j != 0)
      {
        paramArrayOfDouble3[0] = d24;
        paramArrayOfDouble2[0] = d22;
        paramArrayOfDouble5[0] = d23;
        paramArrayOfDouble4[0] = d21;
      }
      else
      {
        paramArrayOfDouble3[0] = d21;
        paramArrayOfDouble2[0] = d23;
        paramArrayOfDouble5[0] = d22;
        paramArrayOfDouble4[0] = d24;
      }
      if (i == 1) {
        d12 = d_sign(d2, paramArrayOfDouble5[0]) * d_sign(d2, paramArrayOfDouble3[0]) * d_sign(d2, paramDouble1);
      }
      if (i == 2) {
        d12 = d_sign(d2, paramArrayOfDouble4[0]) * d_sign(d2, paramArrayOfDouble3[0]) * d_sign(d2, paramDouble2);
      }
      if (i == 3) {
        d12 = d_sign(d2, paramArrayOfDouble4[0]) * d_sign(d2, paramArrayOfDouble2[0]) * d_sign(d2, paramDouble3);
      }
      paramArrayOfDouble1[paramInt] = d_sign(d26, d12);
      double d3 = d12 * d_sign(d2, paramDouble1) * d_sign(d2, paramDouble3);
      paramArrayOfDouble1[(paramInt + 1)] = d_sign(d25, d3);
    }
    return 0;
  }
  
  static double compute_rot(double paramDouble1, double paramDouble2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2)
  {
    double d1;
    double d2;
    double d6;
    if (paramDouble2 == 0.0D)
    {
      d1 = 1.0D;
      d2 = 0.0D;
      d6 = paramDouble1;
    }
    else if (paramDouble1 == 0.0D)
    {
      d1 = 0.0D;
      d2 = 1.0D;
      d6 = paramDouble2;
    }
    else
    {
      double d4 = paramDouble1;
      double d5 = paramDouble2;
      double d3 = max(Math.abs(d4), Math.abs(d5));
      int k;
      int i;
      int j;
      if (d3 >= 4.994797680505588E+145D)
      {
        k = 0;
        while (d3 >= 4.994797680505588E+145D)
        {
          k++;
          d4 *= 2.002083095183101E-146D;
          d5 *= 2.002083095183101E-146D;
          d3 = max(Math.abs(d4), Math.abs(d5));
        }
        d6 = Math.sqrt(d4 * d4 + d5 * d5);
        d1 = d4 / d6;
        d2 = d5 / d6;
        i = k;
        for (j = 1; j <= k; j++) {
          d6 *= 4.994797680505588E+145D;
        }
      }
      if (d3 <= 2.002083095183101E-146D)
      {
        k = 0;
        while (d3 <= 2.002083095183101E-146D)
        {
          k++;
          d4 *= 4.994797680505588E+145D;
          d5 *= 4.994797680505588E+145D;
          d3 = max(Math.abs(d4), Math.abs(d5));
        }
        d6 = Math.sqrt(d4 * d4 + d5 * d5);
        d1 = d4 / d6;
        d2 = d5 / d6;
        i = k;
        for (j = 1; j <= k; j++) {
          d6 *= 2.002083095183101E-146D;
        }
      }
      d6 = Math.sqrt(d4 * d4 + d5 * d5);
      d1 = d4 / d6;
      d2 = d5 / d6;
      if ((Math.abs(paramDouble1) > Math.abs(paramDouble2)) && (d1 < 0.0D))
      {
        d1 = -d1;
        d2 = -d2;
        d6 = -d6;
      }
    }
    paramArrayOfDouble1[paramInt1] = d2;
    paramArrayOfDouble2[paramInt1] = d1;
    return d6;
  }
  
  static void print_mat(double[] paramArrayOfDouble)
  {
    for (int i = 0; i < 3; i++) {
      System.out.println(paramArrayOfDouble[(i * 3 + 0)] + " " + paramArrayOfDouble[(i * 3 + 1)] + " " + paramArrayOfDouble[(i * 3 + 2)] + "\n");
    }
  }
  
  static void print_det(double[] paramArrayOfDouble)
  {
    double d = paramArrayOfDouble[0] * paramArrayOfDouble[4] * paramArrayOfDouble[8] + paramArrayOfDouble[1] * paramArrayOfDouble[5] * paramArrayOfDouble[6] + paramArrayOfDouble[2] * paramArrayOfDouble[3] * paramArrayOfDouble[7] - paramArrayOfDouble[2] * paramArrayOfDouble[4] * paramArrayOfDouble[6] - paramArrayOfDouble[0] * paramArrayOfDouble[5] * paramArrayOfDouble[7] - paramArrayOfDouble[1] * paramArrayOfDouble[3] * paramArrayOfDouble[8];
    System.out.println("det= " + d);
  }
  
  static void mat_mul(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3)
  {
    double[] arrayOfDouble = new double[9];
    arrayOfDouble[0] = (paramArrayOfDouble1[0] * paramArrayOfDouble2[0] + paramArrayOfDouble1[1] * paramArrayOfDouble2[3] + paramArrayOfDouble1[2] * paramArrayOfDouble2[6]);
    arrayOfDouble[1] = (paramArrayOfDouble1[0] * paramArrayOfDouble2[1] + paramArrayOfDouble1[1] * paramArrayOfDouble2[4] + paramArrayOfDouble1[2] * paramArrayOfDouble2[7]);
    arrayOfDouble[2] = (paramArrayOfDouble1[0] * paramArrayOfDouble2[2] + paramArrayOfDouble1[1] * paramArrayOfDouble2[5] + paramArrayOfDouble1[2] * paramArrayOfDouble2[8]);
    arrayOfDouble[3] = (paramArrayOfDouble1[3] * paramArrayOfDouble2[0] + paramArrayOfDouble1[4] * paramArrayOfDouble2[3] + paramArrayOfDouble1[5] * paramArrayOfDouble2[6]);
    arrayOfDouble[4] = (paramArrayOfDouble1[3] * paramArrayOfDouble2[1] + paramArrayOfDouble1[4] * paramArrayOfDouble2[4] + paramArrayOfDouble1[5] * paramArrayOfDouble2[7]);
    arrayOfDouble[5] = (paramArrayOfDouble1[3] * paramArrayOfDouble2[2] + paramArrayOfDouble1[4] * paramArrayOfDouble2[5] + paramArrayOfDouble1[5] * paramArrayOfDouble2[8]);
    arrayOfDouble[6] = (paramArrayOfDouble1[6] * paramArrayOfDouble2[0] + paramArrayOfDouble1[7] * paramArrayOfDouble2[3] + paramArrayOfDouble1[8] * paramArrayOfDouble2[6]);
    arrayOfDouble[7] = (paramArrayOfDouble1[6] * paramArrayOfDouble2[1] + paramArrayOfDouble1[7] * paramArrayOfDouble2[4] + paramArrayOfDouble1[8] * paramArrayOfDouble2[7]);
    arrayOfDouble[8] = (paramArrayOfDouble1[6] * paramArrayOfDouble2[2] + paramArrayOfDouble1[7] * paramArrayOfDouble2[5] + paramArrayOfDouble1[8] * paramArrayOfDouble2[8]);
    for (int i = 0; i < 9; i++) {
      paramArrayOfDouble3[i] = arrayOfDouble[i];
    }
  }
  
  static void transpose_mat(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    paramArrayOfDouble2[0] = paramArrayOfDouble1[0];
    paramArrayOfDouble2[1] = paramArrayOfDouble1[3];
    paramArrayOfDouble2[2] = paramArrayOfDouble1[6];
    paramArrayOfDouble2[3] = paramArrayOfDouble1[1];
    paramArrayOfDouble2[4] = paramArrayOfDouble1[4];
    paramArrayOfDouble2[5] = paramArrayOfDouble1[7];
    paramArrayOfDouble2[6] = paramArrayOfDouble1[2];
    paramArrayOfDouble2[7] = paramArrayOfDouble1[5];
    paramArrayOfDouble2[8] = paramArrayOfDouble1[8];
  }
  
  static double max3(double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble[0] > paramArrayOfDouble[1])
    {
      if (paramArrayOfDouble[0] > paramArrayOfDouble[2]) {
        return paramArrayOfDouble[0];
      }
      return paramArrayOfDouble[2];
    }
    if (paramArrayOfDouble[1] > paramArrayOfDouble[2]) {
      return paramArrayOfDouble[1];
    }
    return paramArrayOfDouble[2];
  }
  
  private static final boolean almostEqual(double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 == paramDouble2) {
      return true;
    }
    double d1 = Math.abs(paramDouble1 - paramDouble2);
    double d2 = Math.abs(paramDouble1);
    double d3 = Math.abs(paramDouble2);
    double d4 = d2 >= d3 ? d2 : d3;
    if (d1 < 1.0E-006D) {
      return true;
    }
    return d1 / d4 < 0.0001D;
  }
  
  public Object clone()
  {
    Matrix3d localMatrix3d = null;
    try
    {
      localMatrix3d = (Matrix3d)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new InternalError();
    }
    return localMatrix3d;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Matrix3d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */