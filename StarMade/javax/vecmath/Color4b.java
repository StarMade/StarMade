package javax.vecmath;

import java.awt.Color;
import java.io.Serializable;

public class Color4b extends Tuple4b
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

  public Color4b()
  {
  }

  public final void set(Color paramColor)
  {
    this.x = ((byte)paramColor.getRed());
    this.y = ((byte)paramColor.getGreen());
    this.z = ((byte)paramColor.getBlue());
    this.w = ((byte)paramColor.getAlpha());
  }

  public final Color get()
  {
    int i = this.x & 0xFF;
    int j = this.y & 0xFF;
    int k = this.z & 0xFF;
    int m = this.w & 0xFF;
    return new Color(i, j, k, m);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Color4b
 * JD-Core Version:    0.6.2
 */