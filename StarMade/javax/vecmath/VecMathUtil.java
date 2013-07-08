package javax.vecmath;

class VecMathUtil
{
  static int floatToIntBits(float paramFloat)
  {
    if (paramFloat == 0.0F) {
      return 0;
    }
    return Float.floatToIntBits(paramFloat);
  }
  
  static long doubleToLongBits(double paramDouble)
  {
    if (paramDouble == 0.0D) {
      return 0L;
    }
    return Double.doubleToLongBits(paramDouble);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.VecMathUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */