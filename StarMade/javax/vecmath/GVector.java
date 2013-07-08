package javax.vecmath;

import java.io.Serializable;

public class GVector
  implements Serializable, Cloneable
{
  private int length;
  double[] values;
  static final long serialVersionUID = 1398850036893875112L;
  
  public GVector(int paramInt)
  {
    this.length = paramInt;
    this.values = new double[paramInt];
    for (int i = 0; i < paramInt; i++) {
      this.values[i] = 0.0D;
    }
  }
  
  public GVector(double[] paramArrayOfDouble)
  {
    this.length = paramArrayOfDouble.length;
    this.values = new double[paramArrayOfDouble.length];
    for (int i = 0; i < this.length; i++) {
      this.values[i] = paramArrayOfDouble[i];
    }
  }
  
  public GVector(GVector paramGVector)
  {
    this.values = new double[paramGVector.length];
    this.length = paramGVector.length;
    for (int i = 0; i < this.length; i++) {
      this.values[i] = paramGVector.values[i];
    }
  }
  
  public GVector(Tuple2f paramTuple2f)
  {
    this.values = new double[2];
    this.values[0] = paramTuple2f.field_577;
    this.values[1] = paramTuple2f.field_578;
    this.length = 2;
  }
  
  public GVector(Tuple3f paramTuple3f)
  {
    this.values = new double[3];
    this.values[0] = paramTuple3f.field_615;
    this.values[1] = paramTuple3f.field_616;
    this.values[2] = paramTuple3f.field_617;
    this.length = 3;
  }
  
  public GVector(Tuple3d paramTuple3d)
  {
    this.values = new double[3];
    this.values[0] = paramTuple3d.field_612;
    this.values[1] = paramTuple3d.field_613;
    this.values[2] = paramTuple3d.field_614;
    this.length = 3;
  }
  
  public GVector(Tuple4f paramTuple4f)
  {
    this.values = new double[4];
    this.values[0] = paramTuple4f.field_596;
    this.values[1] = paramTuple4f.field_597;
    this.values[2] = paramTuple4f.field_598;
    this.values[3] = paramTuple4f.field_599;
    this.length = 4;
  }
  
  public GVector(Tuple4d paramTuple4d)
  {
    this.values = new double[4];
    this.values[0] = paramTuple4d.field_600;
    this.values[1] = paramTuple4d.field_601;
    this.values[2] = paramTuple4d.field_602;
    this.values[3] = paramTuple4d.field_603;
    this.length = 4;
  }
  
  public GVector(double[] paramArrayOfDouble, int paramInt)
  {
    this.length = paramInt;
    this.values = new double[paramInt];
    for (int i = 0; i < paramInt; i++) {
      this.values[i] = paramArrayOfDouble[i];
    }
  }
  
  public final double norm()
  {
    double d = 0.0D;
    for (int i = 0; i < this.length; i++) {
      d += this.values[i] * this.values[i];
    }
    return Math.sqrt(d);
  }
  
  public final double normSquared()
  {
    double d = 0.0D;
    for (int i = 0; i < this.length; i++) {
      d += this.values[i] * this.values[i];
    }
    return d;
  }
  
  public final void normalize(GVector paramGVector)
  {
    double d1 = 0.0D;
    if (this.length != paramGVector.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector0"));
    }
    for (int i = 0; i < this.length; i++) {
      d1 += paramGVector.values[i] * paramGVector.values[i];
    }
    double d2 = 1.0D / Math.sqrt(d1);
    for (i = 0; i < this.length; i++) {
      paramGVector.values[i] *= d2;
    }
  }
  
  public final void normalize()
  {
    double d1 = 0.0D;
    for (int i = 0; i < this.length; i++) {
      d1 += this.values[i] * this.values[i];
    }
    double d2 = 1.0D / Math.sqrt(d1);
    for (i = 0; i < this.length; i++) {
      this.values[i] *= d2;
    }
  }
  
  public final void scale(double paramDouble, GVector paramGVector)
  {
    if (this.length != paramGVector.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector1"));
    }
    for (int i = 0; i < this.length; i++) {
      paramGVector.values[i] *= paramDouble;
    }
  }
  
  public final void scale(double paramDouble)
  {
    for (int i = 0; i < this.length; i++) {
      this.values[i] *= paramDouble;
    }
  }
  
  public final void scaleAdd(double paramDouble, GVector paramGVector1, GVector paramGVector2)
  {
    if (paramGVector2.length != paramGVector1.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector2"));
    }
    if (this.length != paramGVector1.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector3"));
    }
    for (int i = 0; i < this.length; i++) {
      this.values[i] = (paramGVector1.values[i] * paramDouble + paramGVector2.values[i]);
    }
  }
  
  public final void add(GVector paramGVector)
  {
    if (this.length != paramGVector.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector4"));
    }
    for (int i = 0; i < this.length; i++) {
      this.values[i] += paramGVector.values[i];
    }
  }
  
  public final void add(GVector paramGVector1, GVector paramGVector2)
  {
    if (paramGVector1.length != paramGVector2.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector5"));
    }
    if (this.length != paramGVector1.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector6"));
    }
    for (int i = 0; i < this.length; i++) {
      paramGVector1.values[i] += paramGVector2.values[i];
    }
  }
  
  public final void sub(GVector paramGVector)
  {
    if (this.length != paramGVector.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector7"));
    }
    for (int i = 0; i < this.length; i++) {
      this.values[i] -= paramGVector.values[i];
    }
  }
  
  public final void sub(GVector paramGVector1, GVector paramGVector2)
  {
    if (paramGVector1.length != paramGVector2.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector8"));
    }
    if (this.length != paramGVector1.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector9"));
    }
    for (int i = 0; i < this.length; i++) {
      paramGVector1.values[i] -= paramGVector2.values[i];
    }
  }
  
  public final void mul(GMatrix paramGMatrix, GVector paramGVector)
  {
    if (paramGMatrix.getNumCol() != paramGVector.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector10"));
    }
    if (this.length != paramGMatrix.getNumRow()) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector11"));
    }
    double[] arrayOfDouble;
    if (paramGVector != this) {
      arrayOfDouble = paramGVector.values;
    } else {
      arrayOfDouble = (double[])this.values.clone();
    }
    for (int i = this.length - 1; i >= 0; i--)
    {
      this.values[i] = 0.0D;
      for (int j = paramGVector.length - 1; j >= 0; j--) {
        this.values[i] += paramGMatrix.values[i][j] * arrayOfDouble[j];
      }
    }
  }
  
  public final void mul(GVector paramGVector, GMatrix paramGMatrix)
  {
    if (paramGMatrix.getNumRow() != paramGVector.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector12"));
    }
    if (this.length != paramGMatrix.getNumCol()) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector13"));
    }
    double[] arrayOfDouble;
    if (paramGVector != this) {
      arrayOfDouble = paramGVector.values;
    } else {
      arrayOfDouble = (double[])this.values.clone();
    }
    for (int i = this.length - 1; i >= 0; i--)
    {
      this.values[i] = 0.0D;
      for (int j = paramGVector.length - 1; j >= 0; j--) {
        this.values[i] += paramGMatrix.values[j][i] * arrayOfDouble[j];
      }
    }
  }
  
  public final void negate()
  {
    for (int i = this.length - 1; i >= 0; i--) {
      this.values[i] *= -1.0D;
    }
  }
  
  public final void zero()
  {
    for (int i = 0; i < this.length; i++) {
      this.values[i] = 0.0D;
    }
  }
  
  public final void setSize(int paramInt)
  {
    double[] arrayOfDouble = new double[paramInt];
    int j;
    if (this.length < paramInt) {
      j = this.length;
    } else {
      j = paramInt;
    }
    for (int i = 0; i < j; i++) {
      arrayOfDouble[i] = this.values[i];
    }
    this.length = paramInt;
    this.values = arrayOfDouble;
  }
  
  public final void set(double[] paramArrayOfDouble)
  {
    for (int i = this.length - 1; i >= 0; i--) {
      this.values[i] = paramArrayOfDouble[i];
    }
  }
  
  public final void set(GVector paramGVector)
  {
    if (this.length < paramGVector.length)
    {
      this.length = paramGVector.length;
      this.values = new double[this.length];
      for (i = 0; i < this.length; i++) {
        this.values[i] = paramGVector.values[i];
      }
    }
    for (int i = 0; i < paramGVector.length; i++) {
      this.values[i] = paramGVector.values[i];
    }
    for (i = paramGVector.length; i < this.length; i++) {
      this.values[i] = 0.0D;
    }
  }
  
  public final void set(Tuple2f paramTuple2f)
  {
    if (this.length < 2)
    {
      this.length = 2;
      this.values = new double[2];
    }
    this.values[0] = paramTuple2f.field_577;
    this.values[1] = paramTuple2f.field_578;
    for (int i = 2; i < this.length; i++) {
      this.values[i] = 0.0D;
    }
  }
  
  public final void set(Tuple3f paramTuple3f)
  {
    if (this.length < 3)
    {
      this.length = 3;
      this.values = new double[3];
    }
    this.values[0] = paramTuple3f.field_615;
    this.values[1] = paramTuple3f.field_616;
    this.values[2] = paramTuple3f.field_617;
    for (int i = 3; i < this.length; i++) {
      this.values[i] = 0.0D;
    }
  }
  
  public final void set(Tuple3d paramTuple3d)
  {
    if (this.length < 3)
    {
      this.length = 3;
      this.values = new double[3];
    }
    this.values[0] = paramTuple3d.field_612;
    this.values[1] = paramTuple3d.field_613;
    this.values[2] = paramTuple3d.field_614;
    for (int i = 3; i < this.length; i++) {
      this.values[i] = 0.0D;
    }
  }
  
  public final void set(Tuple4f paramTuple4f)
  {
    if (this.length < 4)
    {
      this.length = 4;
      this.values = new double[4];
    }
    this.values[0] = paramTuple4f.field_596;
    this.values[1] = paramTuple4f.field_597;
    this.values[2] = paramTuple4f.field_598;
    this.values[3] = paramTuple4f.field_599;
    for (int i = 4; i < this.length; i++) {
      this.values[i] = 0.0D;
    }
  }
  
  public final void set(Tuple4d paramTuple4d)
  {
    if (this.length < 4)
    {
      this.length = 4;
      this.values = new double[4];
    }
    this.values[0] = paramTuple4d.field_600;
    this.values[1] = paramTuple4d.field_601;
    this.values[2] = paramTuple4d.field_602;
    this.values[3] = paramTuple4d.field_603;
    for (int i = 4; i < this.length; i++) {
      this.values[i] = 0.0D;
    }
  }
  
  public final int getSize()
  {
    return this.values.length;
  }
  
  public final double getElement(int paramInt)
  {
    return this.values[paramInt];
  }
  
  public final void setElement(int paramInt, double paramDouble)
  {
    this.values[paramInt] = paramDouble;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(this.length * 8);
    for (int i = 0; i < this.length; i++) {
      localStringBuffer.append(this.values[i]).append(" ");
    }
    return localStringBuffer.toString();
  }
  
  public int hashCode()
  {
    long l = 1L;
    for (int i = 0; i < this.length; i++) {
      l = 31L * l + VecMathUtil.doubleToLongBits(this.values[i]);
    }
    return (int)(l ^ l >> 32);
  }
  
  public boolean equals(GVector paramGVector)
  {
    try
    {
      if (this.length != paramGVector.length) {
        return false;
      }
      for (int i = 0; i < this.length; i++) {
        if (this.values[i] != paramGVector.values[i]) {
          return false;
        }
      }
      return true;
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      GVector localGVector = (GVector)paramObject;
      if (this.length != localGVector.length) {
        return false;
      }
      for (int i = 0; i < this.length; i++) {
        if (this.values[i] != localGVector.values[i]) {
          return false;
        }
      }
      return true;
    }
    catch (ClassCastException localClassCastException)
    {
      return false;
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean epsilonEquals(GVector paramGVector, double paramDouble)
  {
    if (this.length != paramGVector.length) {
      return false;
    }
    for (int i = 0; i < this.length; i++)
    {
      double d = this.values[i] - paramGVector.values[i];
      if ((d < 0.0D ? -d : d) > paramDouble) {
        return false;
      }
    }
    return true;
  }
  
  public final double dot(GVector paramGVector)
  {
    if (this.length != paramGVector.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector14"));
    }
    double d = 0.0D;
    for (int i = 0; i < this.length; i++) {
      d += this.values[i] * paramGVector.values[i];
    }
    return d;
  }
  
  public final void SVDBackSolve(GMatrix paramGMatrix1, GMatrix paramGMatrix2, GMatrix paramGMatrix3, GVector paramGVector)
  {
    if ((paramGMatrix1.nRow != paramGVector.getSize()) || (paramGMatrix1.nRow != paramGMatrix1.nCol) || (paramGMatrix1.nRow != paramGMatrix2.nRow)) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector15"));
    }
    if ((paramGMatrix2.nCol != this.values.length) || (paramGMatrix2.nCol != paramGMatrix3.nCol) || (paramGMatrix2.nCol != paramGMatrix3.nRow)) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector23"));
    }
    GMatrix localGMatrix = new GMatrix(paramGMatrix1.nRow, paramGMatrix2.nCol);
    localGMatrix.mul(paramGMatrix1, paramGMatrix3);
    localGMatrix.mulTransposeRight(paramGMatrix1, paramGMatrix2);
    localGMatrix.invert();
    mul(localGMatrix, paramGVector);
  }
  
  public final void LUDBackSolve(GMatrix paramGMatrix, GVector paramGVector1, GVector paramGVector2)
  {
    int i = paramGMatrix.nRow * paramGMatrix.nCol;
    double[] arrayOfDouble1 = new double[i];
    double[] arrayOfDouble2 = new double[i];
    int[] arrayOfInt = new int[paramGVector1.getSize()];
    if (paramGMatrix.nRow != paramGVector1.getSize()) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector16"));
    }
    if (paramGMatrix.nRow != paramGVector2.getSize()) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector24"));
    }
    if (paramGMatrix.nRow != paramGMatrix.nCol) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector25"));
    }
    for (int j = 0; j < paramGMatrix.nRow; j++) {
      for (int k = 0; k < paramGMatrix.nCol; k++) {
        arrayOfDouble1[(j * paramGMatrix.nCol + k)] = paramGMatrix.values[j][k];
      }
    }
    for (j = 0; j < i; j++) {
      arrayOfDouble2[j] = 0.0D;
    }
    for (j = 0; j < paramGMatrix.nRow; j++) {
      arrayOfDouble2[(j * paramGMatrix.nCol)] = paramGVector1.values[j];
    }
    for (j = 0; j < paramGMatrix.nCol; j++) {
      arrayOfInt[j] = ((int)paramGVector2.values[j]);
    }
    GMatrix.luBacksubstitution(paramGMatrix.nRow, arrayOfDouble1, arrayOfInt, arrayOfDouble2);
    for (j = 0; j < paramGMatrix.nRow; j++) {
      this.values[j] = arrayOfDouble2[(j * paramGMatrix.nCol)];
    }
  }
  
  public final double angle(GVector paramGVector)
  {
    return Math.acos(dot(paramGVector) / (norm() * paramGVector.norm()));
  }
  
  /**
   * @deprecated
   */
  public final void interpolate(GVector paramGVector1, GVector paramGVector2, float paramFloat)
  {
    interpolate(paramGVector1, paramGVector2, paramFloat);
  }
  
  /**
   * @deprecated
   */
  public final void interpolate(GVector paramGVector, float paramFloat)
  {
    interpolate(paramGVector, paramFloat);
  }
  
  public final void interpolate(GVector paramGVector1, GVector paramGVector2, double paramDouble)
  {
    if (paramGVector2.length != paramGVector1.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector20"));
    }
    if (this.length != paramGVector1.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector21"));
    }
    for (int i = 0; i < this.length; i++) {
      this.values[i] = ((1.0D - paramDouble) * paramGVector1.values[i] + paramDouble * paramGVector2.values[i]);
    }
  }
  
  public final void interpolate(GVector paramGVector, double paramDouble)
  {
    if (paramGVector.length != this.length) {
      throw new MismatchedSizeException(VecMathI18N.getString("GVector22"));
    }
    for (int i = 0; i < this.length; i++) {
      this.values[i] = ((1.0D - paramDouble) * this.values[i] + paramDouble * paramGVector.values[i]);
    }
  }
  
  public Object clone()
  {
    GVector localGVector = null;
    try
    {
      localGVector = (GVector)super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new InternalError();
    }
    localGVector.values = new double[this.length];
    for (int i = 0; i < this.length; i++) {
      localGVector.values[i] = this.values[i];
    }
    return localGVector;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.GVector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */