import java.io.DataInputStream;
import java.io.DataOutputStream;

public final class class_349
  extends class_337
{
  public class_810 field_136;
  
  public final int a9(class_1380 paramclass_1380)
  {
    switch (class_351.field_687[this.field_136.ordinal()])
    {
    case 1: 
      return 1;
    case 2: 
      return 2;
    case 3: 
      return 0;
    case 4: 
      return 5;
    case 5: 
      return 3;
    }
    return super.a9(paramclass_1380);
  }
  
  protected final void a1(DataInputStream paramDataInputStream)
  {
    super.a1(paramDataInputStream);
    this.field_136 = class_810.values()[paramDataInputStream.readByte()];
  }
  
  public final void a2(DataOutputStream paramDataOutputStream)
  {
    super.a2(paramDataOutputStream);
    paramDataOutputStream.writeByte(this.field_136.ordinal());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_349
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */