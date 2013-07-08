package javax.vecmath;

import java.io.Serializable;

public class Point4i
  extends Tuple4i
  implements Serializable
{
  static final long serialVersionUID = 620124780244617983L;
  
  public Point4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public Point4i(int[] paramArrayOfInt)
  {
    super(paramArrayOfInt);
  }
  
  public Point4i(Tuple4i paramTuple4i)
  {
    super(paramTuple4i);
  }
  
  public Point4i() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Point4i
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */