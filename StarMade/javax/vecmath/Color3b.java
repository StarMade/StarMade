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
    this.field_607 = ((byte)paramColor.getRed());
    this.field_608 = ((byte)paramColor.getGreen());
    this.field_609 = ((byte)paramColor.getBlue());
  }
  
  public final Color get()
  {
    int i = this.field_607 & 0xFF;
    int j = this.field_608 & 0xFF;
    int k = this.field_609 & 0xFF;
    return new Color(i, j, k);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Color3b
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */