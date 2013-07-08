package javax.vecmath;

import java.awt.Color;
import java.io.Serializable;

public class Color3b
  extends Tuple3b
  implements Serializable
{
  static final long serialVersionUID = 6632576088353444794L;
  
  public Color3b(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    super(paramByte1, paramByte2, paramByte3);
  }
  
  public Color3b(byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
  }
  
  public Color3b(Color3b paramColor3b)
  {
    super(paramColor3b);
  }
  
  public Color3b(Tuple3b paramTuple3b)
  {
    super(paramTuple3b);
  }
  
  public Color3b(Color paramColor)
  {
    super((byte)paramColor.getRed(), (byte)paramColor.getGreen(), (byte)paramColor.getBlue());
  }
  
  public Color3b() {}
  
  public final void set(Color paramColor)
  {
    this.x = ((byte)paramColor.getRed());
    this.y = ((byte)paramColor.getGreen());
    this.z = ((byte)paramColor.getBlue());
  }
  
  public final Color get()
  {
    int i = this.x & 0xFF;
    int j = this.y & 0xFF;
    int k = this.z & 0xFF;
    return new Color(i, j, k);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Color3b
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */