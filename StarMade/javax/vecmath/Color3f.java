package javax.vecmath;

import java.awt.Color;
import java.io.Serializable;

public class Color3f
  extends Tuple3f
  implements Serializable
{
  static final long serialVersionUID = -1861792981817493659L;
  
  public Color3f(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public Color3f(float[] paramArrayOfFloat)
  {
    super(paramArrayOfFloat);
  }
  
  public Color3f(Color3f paramColor3f)
  {
    super(paramColor3f);
  }
  
  public Color3f(Tuple3f paramTuple3f)
  {
    super(paramTuple3f);
  }
  
  public Color3f(Tuple3d paramTuple3d)
  {
    super(paramTuple3d);
  }
  
  public Color3f(Color paramColor)
  {
    super(paramColor.getRed() / 255.0F, paramColor.getGreen() / 255.0F, paramColor.getBlue() / 255.0F);
  }
  
  public Color3f() {}
  
  public final void set(Color paramColor)
  {
    this.x = (paramColor.getRed() / 255.0F);
    this.y = (paramColor.getGreen() / 255.0F);
    this.z = (paramColor.getBlue() / 255.0F);
  }
  
  public final Color get()
  {
    int i = Math.round(this.x * 255.0F);
    int j = Math.round(this.y * 255.0F);
    int k = Math.round(this.z * 255.0F);
    return new Color(i, j, k);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Color3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */