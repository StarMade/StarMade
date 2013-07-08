package javax.vecmath;

import java.awt.Color;
import java.io.Serializable;

public class Color4b
  extends Tuple4b
  implements Serializable
{
  static final long serialVersionUID = -105080578052502155L;
  
  public Color4b(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4)
  {
    super(paramByte1, paramByte2, paramByte3, paramByte4);
  }
  
  public Color4b(byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
  }
  
  public Color4b(Color4b paramColor4b)
  {
    super(paramColor4b);
  }
  
  public Color4b(Tuple4b paramTuple4b)
  {
    super(paramTuple4b);
  }
  
  public Color4b(Color paramColor)
  {
    super((byte)paramColor.getRed(), (byte)paramColor.getGreen(), (byte)paramColor.getBlue(), (byte)paramColor.getAlpha());
  }
  
  public Color4b() {}
  
  public final void set(Color paramColor)
  {
    this.field_592 = ((byte)paramColor.getRed());
    this.field_593 = ((byte)paramColor.getGreen());
    this.field_594 = ((byte)paramColor.getBlue());
    this.field_595 = ((byte)paramColor.getAlpha());
  }
  
  public final Color get()
  {
    int i = this.field_592 & 0xFF;
    int j = this.field_593 & 0xFF;
    int k = this.field_594 & 0xFF;
    int m = this.field_595 & 0xFF;
    return new Color(i, j, k, m);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Color4b
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */