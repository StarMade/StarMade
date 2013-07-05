package javax.vecmath;

import java.io.Serializable;

public class TexCoord3f extends Tuple3f
  implements Serializable
{
  static final long serialVersionUID = -3517736544731446513L;

  public TexCoord3f(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramFloat1, paramFloat2, paramFloat3);
  }

  public TexCoord3f(float[] paramArrayOfFloat)
  {
    super(paramArrayOfFloat);
  }

  public TexCoord3f(TexCoord3f paramTexCoord3f)
  {
    super(paramTexCoord3f);
  }

  public TexCoord3f(Tuple3f paramTuple3f)
  {
    super(paramTuple3f);
  }

  public TexCoord3f(Tuple3d paramTuple3d)
  {
    super(paramTuple3d);
  }

  public TexCoord3f()
  {
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.TexCoord3f
 * JD-Core Version:    0.6.2
 */