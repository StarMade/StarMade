package javax.vecmath;

import java.io.PrintStream;
import java.io.Serializable;

public class GMatrix
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 2777097312029690941L;
  private static final boolean debug = false;
  int nRow;
  int nCol;
  double[][] values;
  private static final double EPS = 1.0E-010D;

  public GMatrix(int paramInt1, int paramInt2)
  {
    this.values = new double[paramInt1][paramInt2];
    this.nRow = paramInt1;
    this.nCol = paramInt2;
    for (int i = 0; i < paramInt1; i++)
      for (int j = 0; j < paramInt2; j++)
        this.values[i][j] = 0.0D;
    int k;
    if (paramInt1 < paramInt2)
      k = paramInt1;
    else
      k = paramInt2;
    for (i = 0; i < k; i++)
      this.values[i][i] = 1.0D;
  }

  public GMatrix(int paramInt1, int paramInt2, double[] paramArrayOfDouble)
  {
    this.values = new double[paramInt1][paramInt2];
    this.nRow = paramInt1;
    this.nCol = paramInt2;
    for (int i = 0; i < paramInt1; i++)
      for (int j = 0; j < paramInt2; j++)
        this.values[i][j] = paramArrayOfDouble[(i * paramInt2 + j)];
  }

  public GMatrix(GMatrix paramGMatrix)
  {
    this.nRow = paramGMatrix.nRow;
    this.nCol = paramGMatrix.nCol;
    this.values = new double[this.nRow][this.nCol];
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        this.values[i][j] = paramGMatrix.values[i][j];
  }

  public final void mul(GMatrix paramGMatrix)
  {
    if ((this.nCol != paramGMatrix.nRow) || (this.nCol != paramGMatrix.nCol))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix0"));
    double[][] arrayOfDouble = new double[this.nRow][this.nCol];
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
      {
        arrayOfDouble[i][j] = 0.0D;
        for (int k = 0; k < this.nCol; k++)
          arrayOfDouble[i][j] += this.values[i][k] * paramGMatrix.values[k][j];
      }
    this.values = arrayOfDouble;
  }

  public final void mul(GMatrix paramGMatrix1, GMatrix paramGMatrix2)
  {
    if ((paramGMatrix1.nCol != paramGMatrix2.nRow) || (this.nRow != paramGMatrix1.nRow) || (this.nCol != paramGMatrix2.nCol))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix1"));
    double[][] arrayOfDouble = new double[this.nRow][this.nCol];
    for (int i = 0; i < paramGMatrix1.nRow; i++)
      for (int j = 0; j < paramGMatrix2.nCol; j++)
      {
        arrayOfDouble[i][j] = 0.0D;
        for (int k = 0; k < paramGMatrix1.nCol; k++)
          arrayOfDouble[i][j] += paramGMatrix1.values[i][k] * paramGMatrix2.values[k][j];
      }
    this.values = arrayOfDouble;
  }

  public final void mul(GVector paramGVector1, GVector paramGVector2)
  {
    if (this.nRow < paramGVector1.getSize())
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix2"));
    if (this.nCol < paramGVector2.getSize())
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix3"));
    for (int i = 0; i < paramGVector1.getSize(); i++)
      for (int j = 0; j < paramGVector2.getSize(); j++)
        this.values[i][j] = (paramGVector1.values[i] * paramGVector2.values[j]);
  }

  public final void add(GMatrix paramGMatrix)
  {
    if (this.nRow != paramGMatrix.nRow)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix4"));
    if (this.nCol != paramGMatrix.nCol)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix5"));
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        this.values[i][j] += paramGMatrix.values[i][j];
  }

  public final void add(GMatrix paramGMatrix1, GMatrix paramGMatrix2)
  {
    if (paramGMatrix2.nRow != paramGMatrix1.nRow)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix6"));
    if (paramGMatrix2.nCol != paramGMatrix1.nCol)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix7"));
    if ((this.nCol != paramGMatrix1.nCol) || (this.nRow != paramGMatrix1.nRow))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix8"));
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        paramGMatrix1.values[i][j] += paramGMatrix2.values[i][j];
  }

  public final void sub(GMatrix paramGMatrix)
  {
    if (this.nRow != paramGMatrix.nRow)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix9"));
    if (this.nCol != paramGMatrix.nCol)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix28"));
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        this.values[i][j] -= paramGMatrix.values[i][j];
  }

  public final void sub(GMatrix paramGMatrix1, GMatrix paramGMatrix2)
  {
    if (paramGMatrix2.nRow != paramGMatrix1.nRow)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix10"));
    if (paramGMatrix2.nCol != paramGMatrix1.nCol)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix11"));
    if ((this.nRow != paramGMatrix1.nRow) || (this.nCol != paramGMatrix1.nCol))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix12"));
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        paramGMatrix1.values[i][j] -= paramGMatrix2.values[i][j];
  }

  public final void negate()
  {
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        this.values[i][j] = (-this.values[i][j]);
  }

  public final void negate(GMatrix paramGMatrix)
  {
    if ((this.nRow != paramGMatrix.nRow) || (this.nCol != paramGMatrix.nCol))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix13"));
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        this.values[i][j] = (-paramGMatrix.values[i][j]);
  }

  public final void setIdentity()
  {
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        this.values[i][j] = 0.0D;
    int k;
    if (this.nRow < this.nCol)
      k = this.nRow;
    else
      k = this.nCol;
    for (i = 0; i < k; i++)
      this.values[i][i] = 1.0D;
  }

  public final void setZero()
  {
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        this.values[i][j] = 0.0D;
  }

  public final void identityMinus()
  {
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        this.values[i][j] = (-this.values[i][j]);
    int k;
    if (this.nRow < this.nCol)
      k = this.nRow;
    else
      k = this.nCol;
    for (i = 0; i < k; i++)
      this.values[i][i] += 1.0D;
  }

  public final void invert()
  {
    invertGeneral(this);
  }

  public final void invert(GMatrix paramGMatrix)
  {
    invertGeneral(paramGMatrix);
  }

  public final void copySubMatrix(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, GMatrix paramGMatrix)
  {
    int j;
    if (this != paramGMatrix)
      for (i = 0; i < paramInt3; i++)
        for (j = 0; j < paramInt4; j++)
          paramGMatrix.values[(paramInt5 + i)][(paramInt6 + j)] = this.values[(paramInt1 + i)][(paramInt2 + j)];
    double[][] arrayOfDouble = new double[paramInt3][paramInt4];
    for (int i = 0; i < paramInt3; i++)
      for (j = 0; j < paramInt4; j++)
        arrayOfDouble[i][j] = this.values[(paramInt1 + i)][(paramInt2 + j)];
    for (i = 0; i < paramInt3; i++)
      for (j = 0; j < paramInt4; j++)
        paramGMatrix.values[(paramInt5 + i)][(paramInt6 + j)] = arrayOfDouble[i][j];
  }

  public final void setSize(int paramInt1, int paramInt2)
  {
    double[][] arrayOfDouble = new double[paramInt1][paramInt2];
    int k;
    if (this.nRow < paramInt1)
      k = this.nRow;
    else
      k = paramInt1;
    int m;
    if (this.nCol < paramInt2)
      m = this.nCol;
    else
      m = paramInt2;
    for (int i = 0; i < k; i++)
      for (int j = 0; j < m; j++)
        arrayOfDouble[i][j] = this.values[i][j];
    this.nRow = paramInt1;
    this.nCol = paramInt2;
    this.values = arrayOfDouble;
  }

  public final void set(double[] paramArrayOfDouble)
  {
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        this.values[i][j] = paramArrayOfDouble[(this.nCol * i + j)];
  }

  public final void set(Matrix3f paramMatrix3f)
  {
    if ((this.nCol < 3) || (this.nRow < 3))
    {
      this.nCol = 3;
      this.nRow = 3;
      this.values = new double[this.nRow][this.nCol];
    }
    this.values[0][0] = paramMatrix3f.m00;
    this.values[0][1] = paramMatrix3f.m01;
    this.values[0][2] = paramMatrix3f.m02;
    this.values[1][0] = paramMatrix3f.m10;
    this.values[1][1] = paramMatrix3f.m11;
    this.values[1][2] = paramMatrix3f.m12;
    this.values[2][0] = paramMatrix3f.m20;
    this.values[2][1] = paramMatrix3f.m21;
    this.values[2][2] = paramMatrix3f.m22;
    for (int i = 3; i < this.nRow; i++)
      for (int j = 3; j < this.nCol; j++)
        this.values[i][j] = 0.0D;
  }

  public final void set(Matrix3d paramMatrix3d)
  {
    if ((this.nRow < 3) || (this.nCol < 3))
    {
      this.values = new double[3][3];
      this.nRow = 3;
      this.nCol = 3;
    }
    this.values[0][0] = paramMatrix3d.m00;
    this.values[0][1] = paramMatrix3d.m01;
    this.values[0][2] = paramMatrix3d.m02;
    this.values[1][0] = paramMatrix3d.m10;
    this.values[1][1] = paramMatrix3d.m11;
    this.values[1][2] = paramMatrix3d.m12;
    this.values[2][0] = paramMatrix3d.m20;
    this.values[2][1] = paramMatrix3d.m21;
    this.values[2][2] = paramMatrix3d.m22;
    for (int i = 3; i < this.nRow; i++)
      for (int j = 3; j < this.nCol; j++)
        this.values[i][j] = 0.0D;
  }

  public final void set(Matrix4f paramMatrix4f)
  {
    if ((this.nRow < 4) || (this.nCol < 4))
    {
      this.values = new double[4][4];
      this.nRow = 4;
      this.nCol = 4;
    }
    this.values[0][0] = paramMatrix4f.m00;
    this.values[0][1] = paramMatrix4f.m01;
    this.values[0][2] = paramMatrix4f.m02;
    this.values[0][3] = paramMatrix4f.m03;
    this.values[1][0] = paramMatrix4f.m10;
    this.values[1][1] = paramMatrix4f.m11;
    this.values[1][2] = paramMatrix4f.m12;
    this.values[1][3] = paramMatrix4f.m13;
    this.values[2][0] = paramMatrix4f.m20;
    this.values[2][1] = paramMatrix4f.m21;
    this.values[2][2] = paramMatrix4f.m22;
    this.values[2][3] = paramMatrix4f.m23;
    this.values[3][0] = paramMatrix4f.m30;
    this.values[3][1] = paramMatrix4f.m31;
    this.values[3][2] = paramMatrix4f.m32;
    this.values[3][3] = paramMatrix4f.m33;
    for (int i = 4; i < this.nRow; i++)
      for (int j = 4; j < this.nCol; j++)
        this.values[i][j] = 0.0D;
  }

  public final void set(Matrix4d paramMatrix4d)
  {
    if ((this.nRow < 4) || (this.nCol < 4))
    {
      this.values = new double[4][4];
      this.nRow = 4;
      this.nCol = 4;
    }
    this.values[0][0] = paramMatrix4d.m00;
    this.values[0][1] = paramMatrix4d.m01;
    this.values[0][2] = paramMatrix4d.m02;
    this.values[0][3] = paramMatrix4d.m03;
    this.values[1][0] = paramMatrix4d.m10;
    this.values[1][1] = paramMatrix4d.m11;
    this.values[1][2] = paramMatrix4d.m12;
    this.values[1][3] = paramMatrix4d.m13;
    this.values[2][0] = paramMatrix4d.m20;
    this.values[2][1] = paramMatrix4d.m21;
    this.values[2][2] = paramMatrix4d.m22;
    this.values[2][3] = paramMatrix4d.m23;
    this.values[3][0] = paramMatrix4d.m30;
    this.values[3][1] = paramMatrix4d.m31;
    this.values[3][2] = paramMatrix4d.m32;
    this.values[3][3] = paramMatrix4d.m33;
    for (int i = 4; i < this.nRow; i++)
      for (int j = 4; j < this.nCol; j++)
        this.values[i][j] = 0.0D;
  }

  public final void set(GMatrix paramGMatrix)
  {
    if ((this.nRow < paramGMatrix.nRow) || (this.nCol < paramGMatrix.nCol))
    {
      this.nRow = paramGMatrix.nRow;
      this.nCol = paramGMatrix.nCol;
      this.values = new double[this.nRow][this.nCol];
    }
    int j;
    for (int i = 0; i < Math.min(this.nRow, paramGMatrix.nRow); i++)
      for (j = 0; j < Math.min(this.nCol, paramGMatrix.nCol); j++)
        this.values[i][j] = paramGMatrix.values[i][j];
    for (i = paramGMatrix.nRow; i < this.nRow; i++)
      for (j = paramGMatrix.nCol; j < this.nCol; j++)
        this.values[i][j] = 0.0D;
  }

  public final int getNumRow()
  {
    return this.nRow;
  }

  public final int getNumCol()
  {
    return this.nCol;
  }

  public final double getElement(int paramInt1, int paramInt2)
  {
    return this.values[paramInt1][paramInt2];
  }

  public final void setElement(int paramInt1, int paramInt2, double paramDouble)
  {
    this.values[paramInt1][paramInt2] = paramDouble;
  }

  public final void getRow(int paramInt, double[] paramArrayOfDouble)
  {
    for (int i = 0; i < this.nCol; i++)
      paramArrayOfDouble[i] = this.values[paramInt][i];
  }

  public final void getRow(int paramInt, GVector paramGVector)
  {
    if (paramGVector.getSize() < this.nCol)
      paramGVector.setSize(this.nCol);
    for (int i = 0; i < this.nCol; i++)
      paramGVector.values[i] = this.values[paramInt][i];
  }

  public final void getColumn(int paramInt, double[] paramArrayOfDouble)
  {
    for (int i = 0; i < this.nRow; i++)
      paramArrayOfDouble[i] = this.values[i][paramInt];
  }

  public final void getColumn(int paramInt, GVector paramGVector)
  {
    if (paramGVector.getSize() < this.nRow)
      paramGVector.setSize(this.nRow);
    for (int i = 0; i < this.nRow; i++)
      paramGVector.values[i] = this.values[i][paramInt];
  }

  public final void get(Matrix3d paramMatrix3d)
  {
    if ((this.nRow < 3) || (this.nCol < 3))
    {
      paramMatrix3d.setZero();
      if (this.nCol > 0)
      {
        if (this.nRow > 0)
        {
          paramMatrix3d.m00 = this.values[0][0];
          if (this.nRow > 1)
          {
            paramMatrix3d.m10 = this.values[1][0];
            if (this.nRow > 2)
              paramMatrix3d.m20 = this.values[2][0];
          }
        }
        if (this.nCol > 1)
        {
          if (this.nRow > 0)
          {
            paramMatrix3d.m01 = this.values[0][1];
            if (this.nRow > 1)
            {
              paramMatrix3d.m11 = this.values[1][1];
              if (this.nRow > 2)
                paramMatrix3d.m21 = this.values[2][1];
            }
          }
          if ((this.nCol > 2) && (this.nRow > 0))
          {
            paramMatrix3d.m02 = this.values[0][2];
            if (this.nRow > 1)
            {
              paramMatrix3d.m12 = this.values[1][2];
              if (this.nRow > 2)
                paramMatrix3d.m22 = this.values[2][2];
            }
          }
        }
      }
    }
    else
    {
      paramMatrix3d.m00 = this.values[0][0];
      paramMatrix3d.m01 = this.values[0][1];
      paramMatrix3d.m02 = this.values[0][2];
      paramMatrix3d.m10 = this.values[1][0];
      paramMatrix3d.m11 = this.values[1][1];
      paramMatrix3d.m12 = this.values[1][2];
      paramMatrix3d.m20 = this.values[2][0];
      paramMatrix3d.m21 = this.values[2][1];
      paramMatrix3d.m22 = this.values[2][2];
    }
  }

  public final void get(Matrix3f paramMatrix3f)
  {
    if ((this.nRow < 3) || (this.nCol < 3))
    {
      paramMatrix3f.setZero();
      if (this.nCol > 0)
      {
        if (this.nRow > 0)
        {
          paramMatrix3f.m00 = ((float)this.values[0][0]);
          if (this.nRow > 1)
          {
            paramMatrix3f.m10 = ((float)this.values[1][0]);
            if (this.nRow > 2)
              paramMatrix3f.m20 = ((float)this.values[2][0]);
          }
        }
        if (this.nCol > 1)
        {
          if (this.nRow > 0)
          {
            paramMatrix3f.m01 = ((float)this.values[0][1]);
            if (this.nRow > 1)
            {
              paramMatrix3f.m11 = ((float)this.values[1][1]);
              if (this.nRow > 2)
                paramMatrix3f.m21 = ((float)this.values[2][1]);
            }
          }
          if ((this.nCol > 2) && (this.nRow > 0))
          {
            paramMatrix3f.m02 = ((float)this.values[0][2]);
            if (this.nRow > 1)
            {
              paramMatrix3f.m12 = ((float)this.values[1][2]);
              if (this.nRow > 2)
                paramMatrix3f.m22 = ((float)this.values[2][2]);
            }
          }
        }
      }
    }
    else
    {
      paramMatrix3f.m00 = ((float)this.values[0][0]);
      paramMatrix3f.m01 = ((float)this.values[0][1]);
      paramMatrix3f.m02 = ((float)this.values[0][2]);
      paramMatrix3f.m10 = ((float)this.values[1][0]);
      paramMatrix3f.m11 = ((float)this.values[1][1]);
      paramMatrix3f.m12 = ((float)this.values[1][2]);
      paramMatrix3f.m20 = ((float)this.values[2][0]);
      paramMatrix3f.m21 = ((float)this.values[2][1]);
      paramMatrix3f.m22 = ((float)this.values[2][2]);
    }
  }

  public final void get(Matrix4d paramMatrix4d)
  {
    if ((this.nRow < 4) || (this.nCol < 4))
    {
      paramMatrix4d.setZero();
      if (this.nCol > 0)
      {
        if (this.nRow > 0)
        {
          paramMatrix4d.m00 = this.values[0][0];
          if (this.nRow > 1)
          {
            paramMatrix4d.m10 = this.values[1][0];
            if (this.nRow > 2)
            {
              paramMatrix4d.m20 = this.values[2][0];
              if (this.nRow > 3)
                paramMatrix4d.m30 = this.values[3][0];
            }
          }
        }
        if (this.nCol > 1)
        {
          if (this.nRow > 0)
          {
            paramMatrix4d.m01 = this.values[0][1];
            if (this.nRow > 1)
            {
              paramMatrix4d.m11 = this.values[1][1];
              if (this.nRow > 2)
              {
                paramMatrix4d.m21 = this.values[2][1];
                if (this.nRow > 3)
                  paramMatrix4d.m31 = this.values[3][1];
              }
            }
          }
          if (this.nCol > 2)
          {
            if (this.nRow > 0)
            {
              paramMatrix4d.m02 = this.values[0][2];
              if (this.nRow > 1)
              {
                paramMatrix4d.m12 = this.values[1][2];
                if (this.nRow > 2)
                {
                  paramMatrix4d.m22 = this.values[2][2];
                  if (this.nRow > 3)
                    paramMatrix4d.m32 = this.values[3][2];
                }
              }
            }
            if ((this.nCol > 3) && (this.nRow > 0))
            {
              paramMatrix4d.m03 = this.values[0][3];
              if (this.nRow > 1)
              {
                paramMatrix4d.m13 = this.values[1][3];
                if (this.nRow > 2)
                {
                  paramMatrix4d.m23 = this.values[2][3];
                  if (this.nRow > 3)
                    paramMatrix4d.m33 = this.values[3][3];
                }
              }
            }
          }
        }
      }
    }
    else
    {
      paramMatrix4d.m00 = this.values[0][0];
      paramMatrix4d.m01 = this.values[0][1];
      paramMatrix4d.m02 = this.values[0][2];
      paramMatrix4d.m03 = this.values[0][3];
      paramMatrix4d.m10 = this.values[1][0];
      paramMatrix4d.m11 = this.values[1][1];
      paramMatrix4d.m12 = this.values[1][2];
      paramMatrix4d.m13 = this.values[1][3];
      paramMatrix4d.m20 = this.values[2][0];
      paramMatrix4d.m21 = this.values[2][1];
      paramMatrix4d.m22 = this.values[2][2];
      paramMatrix4d.m23 = this.values[2][3];
      paramMatrix4d.m30 = this.values[3][0];
      paramMatrix4d.m31 = this.values[3][1];
      paramMatrix4d.m32 = this.values[3][2];
      paramMatrix4d.m33 = this.values[3][3];
    }
  }

  public final void get(Matrix4f paramMatrix4f)
  {
    if ((this.nRow < 4) || (this.nCol < 4))
    {
      paramMatrix4f.setZero();
      if (this.nCol > 0)
      {
        if (this.nRow > 0)
        {
          paramMatrix4f.m00 = ((float)this.values[0][0]);
          if (this.nRow > 1)
          {
            paramMatrix4f.m10 = ((float)this.values[1][0]);
            if (this.nRow > 2)
            {
              paramMatrix4f.m20 = ((float)this.values[2][0]);
              if (this.nRow > 3)
                paramMatrix4f.m30 = ((float)this.values[3][0]);
            }
          }
        }
        if (this.nCol > 1)
        {
          if (this.nRow > 0)
          {
            paramMatrix4f.m01 = ((float)this.values[0][1]);
            if (this.nRow > 1)
            {
              paramMatrix4f.m11 = ((float)this.values[1][1]);
              if (this.nRow > 2)
              {
                paramMatrix4f.m21 = ((float)this.values[2][1]);
                if (this.nRow > 3)
                  paramMatrix4f.m31 = ((float)this.values[3][1]);
              }
            }
          }
          if (this.nCol > 2)
          {
            if (this.nRow > 0)
            {
              paramMatrix4f.m02 = ((float)this.values[0][2]);
              if (this.nRow > 1)
              {
                paramMatrix4f.m12 = ((float)this.values[1][2]);
                if (this.nRow > 2)
                {
                  paramMatrix4f.m22 = ((float)this.values[2][2]);
                  if (this.nRow > 3)
                    paramMatrix4f.m32 = ((float)this.values[3][2]);
                }
              }
            }
            if ((this.nCol > 3) && (this.nRow > 0))
            {
              paramMatrix4f.m03 = ((float)this.values[0][3]);
              if (this.nRow > 1)
              {
                paramMatrix4f.m13 = ((float)this.values[1][3]);
                if (this.nRow > 2)
                {
                  paramMatrix4f.m23 = ((float)this.values[2][3]);
                  if (this.nRow > 3)
                    paramMatrix4f.m33 = ((float)this.values[3][3]);
                }
              }
            }
          }
        }
      }
    }
    else
    {
      paramMatrix4f.m00 = ((float)this.values[0][0]);
      paramMatrix4f.m01 = ((float)this.values[0][1]);
      paramMatrix4f.m02 = ((float)this.values[0][2]);
      paramMatrix4f.m03 = ((float)this.values[0][3]);
      paramMatrix4f.m10 = ((float)this.values[1][0]);
      paramMatrix4f.m11 = ((float)this.values[1][1]);
      paramMatrix4f.m12 = ((float)this.values[1][2]);
      paramMatrix4f.m13 = ((float)this.values[1][3]);
      paramMatrix4f.m20 = ((float)this.values[2][0]);
      paramMatrix4f.m21 = ((float)this.values[2][1]);
      paramMatrix4f.m22 = ((float)this.values[2][2]);
      paramMatrix4f.m23 = ((float)this.values[2][3]);
      paramMatrix4f.m30 = ((float)this.values[3][0]);
      paramMatrix4f.m31 = ((float)this.values[3][1]);
      paramMatrix4f.m32 = ((float)this.values[3][2]);
      paramMatrix4f.m33 = ((float)this.values[3][3]);
    }
  }

  public final void get(GMatrix paramGMatrix)
  {
    int k;
    if (this.nCol < paramGMatrix.nCol)
      k = this.nCol;
    else
      k = paramGMatrix.nCol;
    int m;
    if (this.nRow < paramGMatrix.nRow)
      m = this.nRow;
    else
      m = paramGMatrix.nRow;
    for (int i = 0; i < m; i++)
      for (j = 0; j < k; j++)
        paramGMatrix.values[i][j] = this.values[i][j];
    for (i = m; i < paramGMatrix.nRow; i++)
      for (j = 0; j < paramGMatrix.nCol; j++)
        paramGMatrix.values[i][j] = 0.0D;
    for (int j = k; j < paramGMatrix.nCol; j++)
      for (i = 0; i < m; i++)
        paramGMatrix.values[i][j] = 0.0D;
  }

  public final void setRow(int paramInt, double[] paramArrayOfDouble)
  {
    for (int i = 0; i < this.nCol; i++)
      this.values[paramInt][i] = paramArrayOfDouble[i];
  }

  public final void setRow(int paramInt, GVector paramGVector)
  {
    for (int i = 0; i < this.nCol; i++)
      this.values[paramInt][i] = paramGVector.values[i];
  }

  public final void setColumn(int paramInt, double[] paramArrayOfDouble)
  {
    for (int i = 0; i < this.nRow; i++)
      this.values[i][paramInt] = paramArrayOfDouble[i];
  }

  public final void setColumn(int paramInt, GVector paramGVector)
  {
    for (int i = 0; i < this.nRow; i++)
      this.values[i][paramInt] = paramGVector.values[i];
  }

  public final void mulTransposeBoth(GMatrix paramGMatrix1, GMatrix paramGMatrix2)
  {
    if ((paramGMatrix1.nRow != paramGMatrix2.nCol) || (this.nRow != paramGMatrix1.nCol) || (this.nCol != paramGMatrix2.nRow))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix14"));
    int i;
    int j;
    int k;
    if ((paramGMatrix1 == this) || (paramGMatrix2 == this))
    {
      double[][] arrayOfDouble = new double[this.nRow][this.nCol];
      for (i = 0; i < this.nRow; i++)
        for (j = 0; j < this.nCol; j++)
        {
          arrayOfDouble[i][j] = 0.0D;
          for (k = 0; k < paramGMatrix1.nRow; k++)
            arrayOfDouble[i][j] += paramGMatrix1.values[k][i] * paramGMatrix2.values[j][k];
        }
      this.values = arrayOfDouble;
    }
    else
    {
      for (i = 0; i < this.nRow; i++)
        for (j = 0; j < this.nCol; j++)
        {
          this.values[i][j] = 0.0D;
          for (k = 0; k < paramGMatrix1.nRow; k++)
            this.values[i][j] += paramGMatrix1.values[k][i] * paramGMatrix2.values[j][k];
        }
    }
  }

  public final void mulTransposeRight(GMatrix paramGMatrix1, GMatrix paramGMatrix2)
  {
    if ((paramGMatrix1.nCol != paramGMatrix2.nCol) || (this.nCol != paramGMatrix2.nRow) || (this.nRow != paramGMatrix1.nRow))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix15"));
    int i;
    int j;
    int k;
    if ((paramGMatrix1 == this) || (paramGMatrix2 == this))
    {
      double[][] arrayOfDouble = new double[this.nRow][this.nCol];
      for (i = 0; i < this.nRow; i++)
        for (j = 0; j < this.nCol; j++)
        {
          arrayOfDouble[i][j] = 0.0D;
          for (k = 0; k < paramGMatrix1.nCol; k++)
            arrayOfDouble[i][j] += paramGMatrix1.values[i][k] * paramGMatrix2.values[j][k];
        }
      this.values = arrayOfDouble;
    }
    else
    {
      for (i = 0; i < this.nRow; i++)
        for (j = 0; j < this.nCol; j++)
        {
          this.values[i][j] = 0.0D;
          for (k = 0; k < paramGMatrix1.nCol; k++)
            this.values[i][j] += paramGMatrix1.values[i][k] * paramGMatrix2.values[j][k];
        }
    }
  }

  public final void mulTransposeLeft(GMatrix paramGMatrix1, GMatrix paramGMatrix2)
  {
    if ((paramGMatrix1.nRow != paramGMatrix2.nRow) || (this.nCol != paramGMatrix2.nCol) || (this.nRow != paramGMatrix1.nCol))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix16"));
    int i;
    int j;
    int k;
    if ((paramGMatrix1 == this) || (paramGMatrix2 == this))
    {
      double[][] arrayOfDouble = new double[this.nRow][this.nCol];
      for (i = 0; i < this.nRow; i++)
        for (j = 0; j < this.nCol; j++)
        {
          arrayOfDouble[i][j] = 0.0D;
          for (k = 0; k < paramGMatrix1.nRow; k++)
            arrayOfDouble[i][j] += paramGMatrix1.values[k][i] * paramGMatrix2.values[k][j];
        }
      this.values = arrayOfDouble;
    }
    else
    {
      for (i = 0; i < this.nRow; i++)
        for (j = 0; j < this.nCol; j++)
        {
          this.values[i][j] = 0.0D;
          for (k = 0; k < paramGMatrix1.nRow; k++)
            this.values[i][j] += paramGMatrix1.values[k][i] * paramGMatrix2.values[k][j];
        }
    }
  }

  public final void transpose()
  {
    int i;
    int j;
    if (this.nRow != this.nCol)
    {
      i = this.nRow;
      this.nRow = this.nCol;
      this.nCol = i;
      double[][] arrayOfDouble = new double[this.nRow][this.nCol];
      for (i = 0; i < this.nRow; i++)
        for (j = 0; j < this.nCol; j++)
          arrayOfDouble[i][j] = this.values[j][i];
      this.values = arrayOfDouble;
    }
    else
    {
      for (i = 0; i < this.nRow; i++)
        for (j = 0; j < i; j++)
        {
          double d = this.values[i][j];
          this.values[i][j] = this.values[j][i];
          this.values[j][i] = d;
        }
    }
  }

  public final void transpose(GMatrix paramGMatrix)
  {
    if ((this.nRow != paramGMatrix.nCol) || (this.nCol != paramGMatrix.nRow))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix17"));
    if (paramGMatrix != this)
      for (int i = 0; i < this.nRow; i++)
        for (int j = 0; j < this.nCol; j++)
          this.values[i][j] = paramGMatrix.values[j][i];
    transpose();
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(this.nRow * this.nCol * 8);
    for (int i = 0; i < this.nRow; i++)
    {
      for (int j = 0; j < this.nCol; j++)
        localStringBuffer.append(this.values[i][j]).append(" ");
      localStringBuffer.append("\n");
    }
    return localStringBuffer.toString();
  }

  private static void checkMatrix(GMatrix paramGMatrix)
  {
    for (int i = 0; i < paramGMatrix.nRow; i++)
    {
      for (int j = 0; j < paramGMatrix.nCol; j++)
        if (Math.abs(paramGMatrix.values[i][j]) < 1.0E-010D)
          System.out.print(" 0.0     ");
        else
          System.out.print(" " + paramGMatrix.values[i][j]);
      System.out.print("\n");
    }
  }

  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + this.nRow;
    l = 31L * l + this.nCol;
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        l = 31L * l + VecMathUtil.doubleToLongBits(this.values[i][j]);
    return (int)(l ^ l >> 32);
  }

  public boolean equals(GMatrix paramGMatrix)
  {
    try
    {
      if ((this.nRow != paramGMatrix.nRow) || (this.nCol != paramGMatrix.nCol))
        return false;
      for (int i = 0; i < this.nRow; i++)
        for (int j = 0; j < this.nCol; j++)
          if (this.values[i][j] != paramGMatrix.values[i][j])
            return false;
      return true;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
    return false;
  }

  public boolean equals(Object paramObject)
  {
    try
    {
      GMatrix localGMatrix = (GMatrix)paramObject;
      if ((this.nRow != localGMatrix.nRow) || (this.nCol != localGMatrix.nCol))
        return false;
      for (int i = 0; i < this.nRow; i++)
        for (int j = 0; j < this.nCol; j++)
          if (this.values[i][j] != localGMatrix.values[i][j])
            return false;
      return true;
    }
    catch (ClassCastException localClassCastException)
    {
      return false;
    }
    catch (NullPointerException localNullPointerException)
    {
    }
    return false;
  }

  /** @deprecated */
  public boolean epsilonEquals(GMatrix paramGMatrix, float paramFloat)
  {
    return epsilonEquals(paramGMatrix, paramFloat);
  }

  public boolean epsilonEquals(GMatrix paramGMatrix, double paramDouble)
  {
    if ((this.nRow != paramGMatrix.nRow) || (this.nCol != paramGMatrix.nCol))
      return false;
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
      {
        double d = this.values[i][j] - paramGMatrix.values[i][j];
        if ((d < 0.0D ? -d : d) > paramDouble)
          return false;
      }
    return true;
  }

  public final double trace()
  {
    int j;
    if (this.nRow < this.nCol)
      j = this.nRow;
    else
      j = this.nCol;
    double d = 0.0D;
    for (int i = 0; i < j; i++)
      d += this.values[i][i];
    return d;
  }

  public final int SVD(GMatrix paramGMatrix1, GMatrix paramGMatrix2, GMatrix paramGMatrix3)
  {
    if ((this.nCol != paramGMatrix3.nCol) || (this.nCol != paramGMatrix3.nRow))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix18"));
    if ((this.nRow != paramGMatrix1.nRow) || (this.nRow != paramGMatrix1.nCol))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix25"));
    if ((this.nRow != paramGMatrix2.nRow) || (this.nCol != paramGMatrix2.nCol))
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix26"));
    if ((this.nRow == 2) && (this.nCol == 2) && (this.values[1][0] == 0.0D))
    {
      paramGMatrix1.setIdentity();
      paramGMatrix3.setIdentity();
      if (this.values[0][1] == 0.0D)
        return 2;
      double[] arrayOfDouble1 = new double[1];
      double[] arrayOfDouble2 = new double[1];
      double[] arrayOfDouble3 = new double[1];
      double[] arrayOfDouble4 = new double[1];
      double[] arrayOfDouble5 = new double[2];
      arrayOfDouble5[0] = this.values[0][0];
      arrayOfDouble5[1] = this.values[1][1];
      compute_2X2(this.values[0][0], this.values[0][1], this.values[1][1], arrayOfDouble5, arrayOfDouble1, arrayOfDouble3, arrayOfDouble2, arrayOfDouble4, 0);
      update_u(0, paramGMatrix1, arrayOfDouble3, arrayOfDouble1);
      update_v(0, paramGMatrix3, arrayOfDouble4, arrayOfDouble2);
      return 2;
    }
    return computeSVD(this, paramGMatrix1, paramGMatrix2, paramGMatrix3);
  }

  public final int LUD(GMatrix paramGMatrix, GVector paramGVector)
  {
    int i = paramGMatrix.nRow * paramGMatrix.nCol;
    double[] arrayOfDouble = new double[i];
    int[] arrayOfInt1 = new int[1];
    int[] arrayOfInt2 = new int[paramGMatrix.nRow];
    if (this.nRow != this.nCol)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix19"));
    if (this.nRow != paramGMatrix.nRow)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix27"));
    if (this.nCol != paramGMatrix.nCol)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix27"));
    if (paramGMatrix.nRow != paramGVector.getSize())
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix20"));
    int k;
    for (int j = 0; j < this.nRow; j++)
      for (k = 0; k < this.nCol; k++)
        arrayOfDouble[(j * this.nCol + k)] = this.values[j][k];
    if (!luDecomposition(paramGMatrix.nRow, arrayOfDouble, arrayOfInt2, arrayOfInt1))
      throw new SingularMatrixException(VecMathI18N.getString("GMatrix21"));
    for (j = 0; j < this.nRow; j++)
      for (k = 0; k < this.nCol; k++)
        paramGMatrix.values[j][k] = arrayOfDouble[(j * this.nCol + k)];
    for (j = 0; j < paramGMatrix.nRow; j++)
      paramGVector.values[j] = arrayOfInt2[j];
    return arrayOfInt1[0];
  }

  public final void setScale(double paramDouble)
  {
    int k;
    if (this.nRow < this.nCol)
      k = this.nRow;
    else
      k = this.nCol;
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        this.values[i][j] = 0.0D;
    for (i = 0; i < k; i++)
      this.values[i][i] = paramDouble;
  }

  final void invertGeneral(GMatrix paramGMatrix)
  {
    int i = paramGMatrix.nRow * paramGMatrix.nCol;
    double[] arrayOfDouble1 = new double[i];
    double[] arrayOfDouble2 = new double[i];
    int[] arrayOfInt1 = new int[paramGMatrix.nRow];
    int[] arrayOfInt2 = new int[1];
    if (paramGMatrix.nRow != paramGMatrix.nCol)
      throw new MismatchedSizeException(VecMathI18N.getString("GMatrix22"));
    int k;
    for (int j = 0; j < this.nRow; j++)
      for (k = 0; k < this.nCol; k++)
        arrayOfDouble1[(j * this.nCol + k)] = paramGMatrix.values[j][k];
    if (!luDecomposition(paramGMatrix.nRow, arrayOfDouble1, arrayOfInt1, arrayOfInt2))
      throw new SingularMatrixException(VecMathI18N.getString("GMatrix21"));
    for (j = 0; j < i; j++)
      arrayOfDouble2[j] = 0.0D;
    for (j = 0; j < this.nCol; j++)
      arrayOfDouble2[(j + j * this.nCol)] = 1.0D;
    luBacksubstitution(paramGMatrix.nRow, arrayOfDouble1, arrayOfInt1, arrayOfDouble2);
    for (j = 0; j < this.nRow; j++)
      for (k = 0; k < this.nCol; k++)
        this.values[j][k] = arrayOfDouble2[(j * this.nCol + k)];
  }

  static boolean luDecomposition(int paramInt, double[] paramArrayOfDouble, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    double[] arrayOfDouble = new double[paramInt];
    int k = 0;
    int m = 0;
    paramArrayOfInt2[0] = 1;
    int i = paramInt;
    double d1;
    double d2;
    while (i-- != 0)
    {
      d1 = 0.0D;
      j = paramInt;
      while (j-- != 0)
      {
        d2 = paramArrayOfDouble[(k++)];
        d2 = Math.abs(d2);
        if (d2 > d1)
          d1 = d2;
      }
      if (d1 == 0.0D)
        return false;
      arrayOfDouble[(m++)] = (1.0D / d1);
    }
    int n = 0;
    for (int j = 0; j < paramInt; j++)
    {
      int i3;
      double d3;
      int i2;
      int i4;
      int i5;
      for (i = 0; i < j; i++)
      {
        i3 = n + paramInt * i + j;
        d3 = paramArrayOfDouble[i3];
        i2 = i;
        i4 = n + paramInt * i;
        i5 = n + j;
        while (i2-- != 0)
        {
          d3 -= paramArrayOfDouble[i4] * paramArrayOfDouble[i5];
          i4++;
          i5 += paramInt;
        }
        paramArrayOfDouble[i3] = d3;
      }
      d1 = 0.0D;
      int i1 = -1;
      for (i = j; i < paramInt; i++)
      {
        i3 = n + paramInt * i + j;
        d3 = paramArrayOfDouble[i3];
        i2 = j;
        i4 = n + paramInt * i;
        i5 = n + j;
        while (i2-- != 0)
        {
          d3 -= paramArrayOfDouble[i4] * paramArrayOfDouble[i5];
          i4++;
          i5 += paramInt;
        }
        paramArrayOfDouble[i3] = d3;
        if ((d2 = arrayOfDouble[i] * Math.abs(d3)) >= d1)
        {
          d1 = d2;
          i1 = i;
        }
      }
      if (i1 < 0)
        throw new RuntimeException(VecMathI18N.getString("GMatrix24"));
      if (j != i1)
      {
        i2 = paramInt;
        i4 = n + paramInt * i1;
        i5 = n + paramInt * j;
        while (i2-- != 0)
        {
          d2 = paramArrayOfDouble[i4];
          paramArrayOfDouble[(i4++)] = paramArrayOfDouble[i5];
          paramArrayOfDouble[(i5++)] = d2;
        }
        arrayOfDouble[i1] = arrayOfDouble[j];
        paramArrayOfInt2[0] = (-paramArrayOfInt2[0]);
      }
      paramArrayOfInt1[j] = i1;
      if (paramArrayOfDouble[(n + paramInt * j + j)] == 0.0D)
        return false;
      if (j != paramInt - 1)
      {
        d2 = 1.0D / paramArrayOfDouble[(n + paramInt * j + j)];
        i3 = n + paramInt * (j + 1) + j;
        i = paramInt - 1 - j;
        while (i-- != 0)
        {
          paramArrayOfDouble[i3] *= d2;
          i3 += paramInt;
        }
      }
    }
    return true;
  }

  static void luBacksubstitution(int paramInt, double[] paramArrayOfDouble1, int[] paramArrayOfInt, double[] paramArrayOfDouble2)
  {
    int i1 = 0;
    for (int n = 0; n < paramInt; n++)
    {
      int i2 = n;
      int j = -1;
      int i3;
      int m;
      for (int i = 0; i < paramInt; i++)
      {
        int k = paramArrayOfInt[(i1 + i)];
        double d2 = paramArrayOfDouble2[(i2 + paramInt * k)];
        paramArrayOfDouble2[(i2 + paramInt * k)] = paramArrayOfDouble2[(i2 + paramInt * i)];
        if (j >= 0)
        {
          i3 = i * paramInt;
          for (m = j; m <= i - 1; m++)
            d2 -= paramArrayOfDouble1[(i3 + m)] * paramArrayOfDouble2[(i2 + paramInt * m)];
        }
        if (d2 != 0.0D)
          j = i;
        paramArrayOfDouble2[(i2 + paramInt * i)] = d2;
      }
      for (i = 0; i < paramInt; i++)
      {
        int i4 = paramInt - 1 - i;
        i3 = paramInt * i4;
        double d1 = 0.0D;
        for (m = 1; m <= i; m++)
          d1 += paramArrayOfDouble1[(i3 + paramInt - m)] * paramArrayOfDouble2[(i2 + paramInt * (paramInt - m))];
        paramArrayOfDouble2[(i2 + paramInt * i4)] = ((paramArrayOfDouble2[(i2 + paramInt * i4)] - d1) / paramArrayOfDouble1[(i3 + i4)]);
      }
    }
  }

  static int computeSVD(GMatrix paramGMatrix1, GMatrix paramGMatrix2, GMatrix paramGMatrix3, GMatrix paramGMatrix4)
  {
    GMatrix localGMatrix1 = new GMatrix(paramGMatrix1.nRow, paramGMatrix1.nCol);
    GMatrix localGMatrix2 = new GMatrix(paramGMatrix1.nRow, paramGMatrix1.nCol);
    GMatrix localGMatrix3 = new GMatrix(paramGMatrix1.nRow, paramGMatrix1.nCol);
    GMatrix localGMatrix4 = new GMatrix(paramGMatrix1);
    int i4;
    int i3;
    if (localGMatrix4.nRow >= localGMatrix4.nCol)
    {
      i4 = localGMatrix4.nCol;
      i3 = localGMatrix4.nCol - 1;
    }
    else
    {
      i4 = localGMatrix4.nRow;
      i3 = localGMatrix4.nRow;
    }
    int i5;
    if (localGMatrix4.nRow > localGMatrix4.nCol)
      i5 = localGMatrix4.nRow;
    else
      i5 = localGMatrix4.nCol;
    double[] arrayOfDouble1 = new double[i5];
    double[] arrayOfDouble2 = new double[i4];
    double[] arrayOfDouble3 = new double[i3];
    int i2 = 0;
    paramGMatrix2.setIdentity();
    paramGMatrix4.setIdentity();
    int m = localGMatrix4.nRow;
    int n = localGMatrix4.nCol;
    for (int i1 = 0; i1 < i4; i1++)
    {
      double d1;
      double d2;
      int j;
      int k;
      double d3;
      if (m > 1)
      {
        d1 = 0.0D;
        for (i = 0; i < m; i++)
          d1 += localGMatrix4.values[(i + i1)][i1] * localGMatrix4.values[(i + i1)][i1];
        d1 = Math.sqrt(d1);
        if (localGMatrix4.values[i1][i1] == 0.0D)
          arrayOfDouble1[0] = d1;
        else
          arrayOfDouble1[0] = (localGMatrix4.values[i1][i1] + d_sign(d1, localGMatrix4.values[i1][i1]));
        for (i = 1; i < m; i++)
          arrayOfDouble1[i] = localGMatrix4.values[(i1 + i)][i1];
        d2 = 0.0D;
        for (i = 0; i < m; i++)
          d2 += arrayOfDouble1[i] * arrayOfDouble1[i];
        d2 = 2.0D / d2;
        for (j = i1; j < localGMatrix4.nRow; j++)
          for (k = i1; k < localGMatrix4.nRow; k++)
            localGMatrix2.values[j][k] = (-d2 * arrayOfDouble1[(j - i1)] * arrayOfDouble1[(k - i1)]);
        for (i = i1; i < localGMatrix4.nRow; i++)
          localGMatrix2.values[i][i] += 1.0D;
        d3 = 0.0D;
        for (i = i1; i < localGMatrix4.nRow; i++)
          d3 += localGMatrix2.values[i1][i] * localGMatrix4.values[i][i1];
        localGMatrix4.values[i1][i1] = d3;
        for (j = i1; j < localGMatrix4.nRow; j++)
          for (k = i1 + 1; k < localGMatrix4.nCol; k++)
          {
            localGMatrix1.values[j][k] = 0.0D;
            for (i = i1; i < localGMatrix4.nCol; i++)
              localGMatrix1.values[j][k] += localGMatrix2.values[j][i] * localGMatrix4.values[i][k];
          }
        for (j = i1; j < localGMatrix4.nRow; j++)
          for (k = i1 + 1; k < localGMatrix4.nCol; k++)
            localGMatrix4.values[j][k] = localGMatrix1.values[j][k];
        for (j = i1; j < localGMatrix4.nRow; j++)
          for (k = 0; k < localGMatrix4.nCol; k++)
          {
            localGMatrix1.values[j][k] = 0.0D;
            for (i = i1; i < localGMatrix4.nCol; i++)
              localGMatrix1.values[j][k] += localGMatrix2.values[j][i] * paramGMatrix2.values[i][k];
          }
        for (j = i1; j < localGMatrix4.nRow; j++)
          for (k = 0; k < localGMatrix4.nCol; k++)
            paramGMatrix2.values[j][k] = localGMatrix1.values[j][k];
        m--;
      }
      if (n > 2)
      {
        d1 = 0.0D;
        for (i = 1; i < n; i++)
          d1 += localGMatrix4.values[i1][(i1 + i)] * localGMatrix4.values[i1][(i1 + i)];
        d1 = Math.sqrt(d1);
        if (localGMatrix4.values[i1][(i1 + 1)] == 0.0D)
          arrayOfDouble1[0] = d1;
        else
          arrayOfDouble1[0] = (localGMatrix4.values[i1][(i1 + 1)] + d_sign(d1, localGMatrix4.values[i1][(i1 + 1)]));
        for (i = 1; i < n - 1; i++)
          arrayOfDouble1[i] = localGMatrix4.values[i1][(i1 + i + 1)];
        d2 = 0.0D;
        for (i = 0; i < n - 1; i++)
          d2 += arrayOfDouble1[i] * arrayOfDouble1[i];
        d2 = 2.0D / d2;
        for (j = i1 + 1; j < n; j++)
          for (k = i1 + 1; k < localGMatrix4.nCol; k++)
            localGMatrix3.values[j][k] = (-d2 * arrayOfDouble1[(j - i1 - 1)] * arrayOfDouble1[(k - i1 - 1)]);
        for (i = i1 + 1; i < localGMatrix4.nCol; i++)
          localGMatrix3.values[i][i] += 1.0D;
        d3 = 0.0D;
        for (i = i1; i < localGMatrix4.nCol; i++)
          d3 += localGMatrix3.values[i][(i1 + 1)] * localGMatrix4.values[i1][i];
        localGMatrix4.values[i1][(i1 + 1)] = d3;
        for (j = i1 + 1; j < localGMatrix4.nRow; j++)
          for (k = i1 + 1; k < localGMatrix4.nCol; k++)
          {
            localGMatrix1.values[j][k] = 0.0D;
            for (i = i1 + 1; i < localGMatrix4.nCol; i++)
              localGMatrix1.values[j][k] += localGMatrix3.values[i][k] * localGMatrix4.values[j][i];
          }
        for (j = i1 + 1; j < localGMatrix4.nRow; j++)
          for (k = i1 + 1; k < localGMatrix4.nCol; k++)
            localGMatrix4.values[j][k] = localGMatrix1.values[j][k];
        for (j = 0; j < localGMatrix4.nRow; j++)
          for (k = i1 + 1; k < localGMatrix4.nCol; k++)
          {
            localGMatrix1.values[j][k] = 0.0D;
            for (i = i1 + 1; i < localGMatrix4.nCol; i++)
              localGMatrix1.values[j][k] += localGMatrix3.values[i][k] * paramGMatrix4.values[j][i];
          }
        for (j = 0; j < localGMatrix4.nRow; j++)
          for (k = i1 + 1; k < localGMatrix4.nCol; k++)
            paramGMatrix4.values[j][k] = localGMatrix1.values[j][k];
        n--;
      }
    }
    for (int i = 0; i < i4; i++)
      arrayOfDouble2[i] = localGMatrix4.values[i][i];
    for (i = 0; i < i3; i++)
      arrayOfDouble3[i] = localGMatrix4.values[i][(i + 1)];
    if ((localGMatrix4.nRow == 2) && (localGMatrix4.nCol == 2))
    {
      double[] arrayOfDouble4 = new double[1];
      double[] arrayOfDouble5 = new double[1];
      double[] arrayOfDouble6 = new double[1];
      double[] arrayOfDouble7 = new double[1];
      compute_2X2(arrayOfDouble2[0], arrayOfDouble3[0], arrayOfDouble2[1], arrayOfDouble2, arrayOfDouble6, arrayOfDouble4, arrayOfDouble7, arrayOfDouble5, 0);
      update_u(0, paramGMatrix2, arrayOfDouble4, arrayOfDouble6);
      update_v(0, paramGMatrix4, arrayOfDouble5, arrayOfDouble7);
      return 2;
    }
    compute_qr(0, arrayOfDouble3.length - 1, arrayOfDouble2, arrayOfDouble3, paramGMatrix2, paramGMatrix4);
    i2 = arrayOfDouble2.length;
    return i2;
  }

  static void compute_qr(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, GMatrix paramGMatrix1, GMatrix paramGMatrix2)
  {
    double[] arrayOfDouble1 = new double[1];
    double[] arrayOfDouble2 = new double[1];
    double[] arrayOfDouble3 = new double[1];
    double[] arrayOfDouble4 = new double[1];
    GMatrix localGMatrix = new GMatrix(paramGMatrix1.nCol, paramGMatrix2.nRow);
    double d5 = 1.0D;
    double d6 = -1.0D;
    int n = 0;
    double d3 = 0.0D;
    double d4 = 0.0D;
    for (int j = 0; (j < 2) && (n == 0); j++)
    {
      double d2;
      for (i = paramInt1; i <= paramInt2; i++)
      {
        if (i == paramInt1)
        {
          int m;
          if (paramArrayOfDouble2.length == paramArrayOfDouble1.length)
            m = paramInt2;
          else
            m = paramInt2 + 1;
          double d1 = compute_shift(paramArrayOfDouble1[(m - 1)], paramArrayOfDouble2[paramInt2], paramArrayOfDouble1[m]);
          d3 = (Math.abs(paramArrayOfDouble1[i]) - d1) * (d_sign(d5, paramArrayOfDouble1[i]) + d1 / paramArrayOfDouble1[i]);
          d4 = paramArrayOfDouble2[i];
        }
        d2 = compute_rot(d3, d4, arrayOfDouble4, arrayOfDouble2);
        if (i != paramInt1)
          paramArrayOfDouble2[(i - 1)] = d2;
        d3 = arrayOfDouble2[0] * paramArrayOfDouble1[i] + arrayOfDouble4[0] * paramArrayOfDouble2[i];
        paramArrayOfDouble2[i] = (arrayOfDouble2[0] * paramArrayOfDouble2[i] - arrayOfDouble4[0] * paramArrayOfDouble1[i]);
        d4 = arrayOfDouble4[0] * paramArrayOfDouble1[(i + 1)];
        paramArrayOfDouble1[(i + 1)] = (arrayOfDouble2[0] * paramArrayOfDouble1[(i + 1)]);
        update_v(i, paramGMatrix2, arrayOfDouble2, arrayOfDouble4);
        d2 = compute_rot(d3, d4, arrayOfDouble3, arrayOfDouble1);
        paramArrayOfDouble1[i] = d2;
        d3 = arrayOfDouble1[0] * paramArrayOfDouble2[i] + arrayOfDouble3[0] * paramArrayOfDouble1[(i + 1)];
        paramArrayOfDouble1[(i + 1)] = (arrayOfDouble1[0] * paramArrayOfDouble1[(i + 1)] - arrayOfDouble3[0] * paramArrayOfDouble2[i]);
        if (i < paramInt2)
        {
          d4 = arrayOfDouble3[0] * paramArrayOfDouble2[(i + 1)];
          paramArrayOfDouble2[(i + 1)] = (arrayOfDouble1[0] * paramArrayOfDouble2[(i + 1)]);
        }
        update_u(i, paramGMatrix1, arrayOfDouble1, arrayOfDouble3);
      }
      if (paramArrayOfDouble1.length == paramArrayOfDouble2.length)
      {
        d2 = compute_rot(d3, d4, arrayOfDouble4, arrayOfDouble2);
        d3 = arrayOfDouble2[0] * paramArrayOfDouble1[i] + arrayOfDouble4[0] * paramArrayOfDouble2[i];
        paramArrayOfDouble2[i] = (arrayOfDouble2[0] * paramArrayOfDouble2[i] - arrayOfDouble4[0] * paramArrayOfDouble1[i]);
        paramArrayOfDouble1[(i + 1)] = (arrayOfDouble2[0] * paramArrayOfDouble1[(i + 1)]);
        update_v(i, paramGMatrix2, arrayOfDouble2, arrayOfDouble4);
      }
      while ((paramInt2 - paramInt1 > 1) && (Math.abs(paramArrayOfDouble2[paramInt2]) < 4.89E-015D))
        paramInt2--;
      for (int k = paramInt2 - 2; k > paramInt1; k--)
        if (Math.abs(paramArrayOfDouble2[k]) < 4.89E-015D)
        {
          compute_qr(k + 1, paramInt2, paramArrayOfDouble1, paramArrayOfDouble2, paramGMatrix1, paramGMatrix2);
          for (paramInt2 = k - 1; (paramInt2 - paramInt1 > 1) && (Math.abs(paramArrayOfDouble2[paramInt2]) < 4.89E-015D); paramInt2--);
        }
      if ((paramInt2 - paramInt1 <= 1) && (Math.abs(paramArrayOfDouble2[(paramInt1 + 1)]) < 4.89E-015D))
        n = 1;
    }
    if (Math.abs(paramArrayOfDouble2[1]) < 4.89E-015D)
    {
      compute_2X2(paramArrayOfDouble1[paramInt1], paramArrayOfDouble2[paramInt1], paramArrayOfDouble1[(paramInt1 + 1)], paramArrayOfDouble1, arrayOfDouble3, arrayOfDouble1, arrayOfDouble4, arrayOfDouble2, 0);
      paramArrayOfDouble2[paramInt1] = 0.0D;
      paramArrayOfDouble2[(paramInt1 + 1)] = 0.0D;
    }
    int i = paramInt1;
    update_u(i, paramGMatrix1, arrayOfDouble1, arrayOfDouble3);
    update_v(i, paramGMatrix2, arrayOfDouble2, arrayOfDouble4);
  }

  private static void print_se(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    System.out.println("\ns =" + paramArrayOfDouble1[0] + " " + paramArrayOfDouble1[1] + " " + paramArrayOfDouble1[2]);
    System.out.println("e =" + paramArrayOfDouble2[0] + " " + paramArrayOfDouble2[1]);
  }

  private static void update_v(int paramInt, GMatrix paramGMatrix, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    for (int i = 0; i < paramGMatrix.nRow; i++)
    {
      double d = paramGMatrix.values[i][paramInt];
      paramGMatrix.values[i][paramInt] = (paramArrayOfDouble1[0] * d + paramArrayOfDouble2[0] * paramGMatrix.values[i][(paramInt + 1)]);
      paramGMatrix.values[i][(paramInt + 1)] = (-paramArrayOfDouble2[0] * d + paramArrayOfDouble1[0] * paramGMatrix.values[i][(paramInt + 1)]);
    }
  }

  private static void chase_up(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt, GMatrix paramGMatrix)
  {
    double[] arrayOfDouble1 = new double[1];
    double[] arrayOfDouble2 = new double[1];
    GMatrix localGMatrix1 = new GMatrix(paramGMatrix.nRow, paramGMatrix.nCol);
    GMatrix localGMatrix2 = new GMatrix(paramGMatrix.nRow, paramGMatrix.nCol);
    double d1 = paramArrayOfDouble2[paramInt];
    double d2 = paramArrayOfDouble1[paramInt];
    for (int i = paramInt; i > 0; i--)
    {
      double d3 = compute_rot(d1, d2, arrayOfDouble2, arrayOfDouble1);
      d1 = -paramArrayOfDouble2[(i - 1)] * arrayOfDouble2[0];
      d2 = paramArrayOfDouble1[(i - 1)];
      paramArrayOfDouble1[i] = d3;
      paramArrayOfDouble2[(i - 1)] *= arrayOfDouble1[0];
      update_v_split(i, paramInt + 1, paramGMatrix, arrayOfDouble1, arrayOfDouble2, localGMatrix1, localGMatrix2);
    }
    paramArrayOfDouble1[(i + 1)] = compute_rot(d1, d2, arrayOfDouble2, arrayOfDouble1);
    update_v_split(i, paramInt + 1, paramGMatrix, arrayOfDouble1, arrayOfDouble2, localGMatrix1, localGMatrix2);
  }

  private static void chase_across(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt, GMatrix paramGMatrix)
  {
    double[] arrayOfDouble1 = new double[1];
    double[] arrayOfDouble2 = new double[1];
    GMatrix localGMatrix1 = new GMatrix(paramGMatrix.nRow, paramGMatrix.nCol);
    GMatrix localGMatrix2 = new GMatrix(paramGMatrix.nRow, paramGMatrix.nCol);
    double d2 = paramArrayOfDouble2[paramInt];
    double d1 = paramArrayOfDouble1[(paramInt + 1)];
    for (int i = paramInt; i < paramGMatrix.nCol - 2; i++)
    {
      double d3 = compute_rot(d1, d2, arrayOfDouble2, arrayOfDouble1);
      d2 = -paramArrayOfDouble2[(i + 1)] * arrayOfDouble2[0];
      d1 = paramArrayOfDouble1[(i + 2)];
      paramArrayOfDouble1[(i + 1)] = d3;
      paramArrayOfDouble2[(i + 1)] *= arrayOfDouble1[0];
      update_u_split(paramInt, i + 1, paramGMatrix, arrayOfDouble1, arrayOfDouble2, localGMatrix1, localGMatrix2);
    }
    paramArrayOfDouble1[(i + 1)] = compute_rot(d1, d2, arrayOfDouble2, arrayOfDouble1);
    update_u_split(paramInt, i + 1, paramGMatrix, arrayOfDouble1, arrayOfDouble2, localGMatrix1, localGMatrix2);
  }

  private static void update_v_split(int paramInt1, int paramInt2, GMatrix paramGMatrix1, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, GMatrix paramGMatrix2, GMatrix paramGMatrix3)
  {
    for (int i = 0; i < paramGMatrix1.nRow; i++)
    {
      double d = paramGMatrix1.values[i][paramInt1];
      paramGMatrix1.values[i][paramInt1] = (paramArrayOfDouble1[0] * d - paramArrayOfDouble2[0] * paramGMatrix1.values[i][paramInt2]);
      paramGMatrix1.values[i][paramInt2] = (paramArrayOfDouble2[0] * d + paramArrayOfDouble1[0] * paramGMatrix1.values[i][paramInt2]);
    }
    System.out.println("topr    =" + paramInt1);
    System.out.println("bottomr =" + paramInt2);
    System.out.println("cosr =" + paramArrayOfDouble1[0]);
    System.out.println("sinr =" + paramArrayOfDouble2[0]);
    System.out.println("\nm =");
    checkMatrix(paramGMatrix3);
    System.out.println("\nv =");
    checkMatrix(paramGMatrix2);
    paramGMatrix3.mul(paramGMatrix3, paramGMatrix2);
    System.out.println("\nt*m =");
    checkMatrix(paramGMatrix3);
  }

  private static void update_u_split(int paramInt1, int paramInt2, GMatrix paramGMatrix1, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, GMatrix paramGMatrix2, GMatrix paramGMatrix3)
  {
    for (int i = 0; i < paramGMatrix1.nCol; i++)
    {
      double d = paramGMatrix1.values[paramInt1][i];
      paramGMatrix1.values[paramInt1][i] = (paramArrayOfDouble1[0] * d - paramArrayOfDouble2[0] * paramGMatrix1.values[paramInt2][i]);
      paramGMatrix1.values[paramInt2][i] = (paramArrayOfDouble2[0] * d + paramArrayOfDouble1[0] * paramGMatrix1.values[paramInt2][i]);
    }
    System.out.println("\nm=");
    checkMatrix(paramGMatrix3);
    System.out.println("\nu=");
    checkMatrix(paramGMatrix2);
    paramGMatrix3.mul(paramGMatrix2, paramGMatrix3);
    System.out.println("\nt*m=");
    checkMatrix(paramGMatrix3);
  }

  private static void update_u(int paramInt, GMatrix paramGMatrix, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    for (int i = 0; i < paramGMatrix.nCol; i++)
    {
      double d = paramGMatrix.values[paramInt][i];
      paramGMatrix.values[paramInt][i] = (paramArrayOfDouble1[0] * d + paramArrayOfDouble2[0] * paramGMatrix.values[(paramInt + 1)][i]);
      paramGMatrix.values[(paramInt + 1)][i] = (-paramArrayOfDouble2[0] * d + paramArrayOfDouble1[0] * paramGMatrix.values[(paramInt + 1)][i]);
    }
  }

  private static void print_m(GMatrix paramGMatrix1, GMatrix paramGMatrix2, GMatrix paramGMatrix3)
  {
    GMatrix localGMatrix = new GMatrix(paramGMatrix1.nCol, paramGMatrix1.nRow);
    localGMatrix.mul(paramGMatrix2, localGMatrix);
    localGMatrix.mul(localGMatrix, paramGMatrix3);
    System.out.println("\n m = \n" + toString(localGMatrix));
  }

  private static String toString(GMatrix paramGMatrix)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramGMatrix.nRow * paramGMatrix.nCol * 8);
    for (int i = 0; i < paramGMatrix.nRow; i++)
    {
      for (int j = 0; j < paramGMatrix.nCol; j++)
        if (Math.abs(paramGMatrix.values[i][j]) < 1.E-009D)
          localStringBuffer.append("0.0000 ");
        else
          localStringBuffer.append(paramGMatrix.values[i][j]).append(" ");
      localStringBuffer.append("\n");
    }
    return localStringBuffer.toString();
  }

  private static void print_svd(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, GMatrix paramGMatrix1, GMatrix paramGMatrix2)
  {
    GMatrix localGMatrix = new GMatrix(paramGMatrix1.nCol, paramGMatrix2.nRow);
    System.out.println(" \ns = ");
    for (int i = 0; i < paramArrayOfDouble1.length; i++)
      System.out.println(" " + paramArrayOfDouble1[i]);
    System.out.println(" \ne = ");
    for (i = 0; i < paramArrayOfDouble2.length; i++)
      System.out.println(" " + paramArrayOfDouble2[i]);
    System.out.println(" \nu  = \n" + paramGMatrix1.toString());
    System.out.println(" \nv  = \n" + paramGMatrix2.toString());
    localGMatrix.setIdentity();
    for (i = 0; i < paramArrayOfDouble1.length; i++)
      localGMatrix.values[i][i] = paramArrayOfDouble1[i];
    for (i = 0; i < paramArrayOfDouble2.length; i++)
      localGMatrix.values[i][(i + 1)] = paramArrayOfDouble2[i];
    System.out.println(" \nm  = \n" + localGMatrix.toString());
    localGMatrix.mulTransposeLeft(paramGMatrix1, localGMatrix);
    localGMatrix.mulTransposeRight(localGMatrix, paramGMatrix2);
    System.out.println(" \n u.transpose*m*v.transpose  = \n" + localGMatrix.toString());
  }

  static double max(double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 > paramDouble2)
      return paramDouble1;
    return paramDouble2;
  }

  static double min(double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 < paramDouble2)
      return paramDouble1;
    return paramDouble2;
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
      if (d4 != 0.0D)
        d1 = min(d4, d7) / max(d4, d7);
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
    if (d15 > d13)
      j = 1;
    else
      j = 0;
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
        if (d13 / d14 < 1.0E-010D)
        {
          k = 0;
          d26 = d14;
          if (d15 > 1.0D)
            d25 = d13 / (d14 / d15);
          else
            d25 = d13 / d14 * d15;
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
        if (d6 == d13)
          d7 = 1.0D;
        else
          d7 = d6 / d13;
        double d8 = d17 / d16;
        double d11 = 2.0D - d7;
        double d19 = d8 * d8;
        double d20 = d11 * d11;
        double d10 = Math.sqrt(d20 + d19);
        double d9;
        if (d7 == 0.0D)
          d9 = Math.abs(d8);
        else
          d9 = Math.sqrt(d7 * d7 + d19);
        double d5 = (d10 + d9) * 0.5D;
        if (d14 > d13)
        {
          i = 2;
          if (d13 / d14 < 1.0E-010D)
          {
            k = 0;
            d26 = d14;
            if (d15 > 1.0D)
              d25 = d13 / (d14 / d15);
            else
              d25 = d13 / d14 * d15;
            d21 = 1.0D;
            d23 = d18 / d17;
            d24 = 1.0D;
            d22 = d16 / d17;
          }
        }
        if (k != 0)
        {
          d6 = d13 - d15;
          if (d6 == d13)
            d7 = 1.0D;
          else
            d7 = d6 / d13;
          d8 = d17 / d16;
          d11 = 2.0D - d7;
          d19 = d8 * d8;
          d20 = d11 * d11;
          d10 = Math.sqrt(d20 + d19);
          if (d7 == 0.0D)
            d9 = Math.abs(d8);
          else
            d9 = Math.sqrt(d7 * d7 + d19);
          d5 = (d10 + d9) * 0.5D;
          d25 = d15 / d5;
          d26 = d13 * d5;
          if (d19 == 0.0D)
          {
            if (d7 == 0.0D)
              d11 = d_sign(d1, d16) * d_sign(d2, d17);
            else
              d11 = d17 / d_sign(d6, d16) + d8 / d11;
          }
          else
            d11 = (d8 / (d10 + d11) + d8 / (d9 + d7)) * (d5 + 1.0D);
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
      if (i == 1)
        d12 = d_sign(d2, paramArrayOfDouble5[0]) * d_sign(d2, paramArrayOfDouble3[0]) * d_sign(d2, paramDouble1);
      if (i == 2)
        d12 = d_sign(d2, paramArrayOfDouble4[0]) * d_sign(d2, paramArrayOfDouble3[0]) * d_sign(d2, paramDouble2);
      if (i == 3)
        d12 = d_sign(d2, paramArrayOfDouble4[0]) * d_sign(d2, paramArrayOfDouble2[0]) * d_sign(d2, paramDouble3);
      paramArrayOfDouble1[paramInt] = d_sign(d26, d12);
      double d3 = d12 * d_sign(d2, paramDouble1) * d_sign(d2, paramDouble3);
      paramArrayOfDouble1[(paramInt + 1)] = d_sign(d25, d3);
    }
    return 0;
  }

  static double compute_rot(double paramDouble1, double paramDouble2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
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
        for (j = 1; j <= k; j++)
          d6 *= 4.994797680505588E+145D;
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
        for (j = 1; j <= k; j++)
          d6 *= 2.002083095183101E-146D;
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
    paramArrayOfDouble1[0] = d2;
    paramArrayOfDouble2[0] = d1;
    return d6;
  }

  static double d_sign(double paramDouble1, double paramDouble2)
  {
    double d = paramDouble1 >= 0.0D ? paramDouble1 : -paramDouble1;
    return paramDouble2 >= 0.0D ? d : -d;
  }

  public Object clone()
  {
    GMatrix localGMatrix = null;
    try
    {
      localGMatrix = (GMatrix)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new InternalError();
    }
    localGMatrix.values = new double[this.nRow][this.nCol];
    for (int i = 0; i < this.nRow; i++)
      for (int j = 0; j < this.nCol; j++)
        localGMatrix.values[i][j] = this.values[i][j];
    return localGMatrix;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.GMatrix
 * JD-Core Version:    0.6.2
 */