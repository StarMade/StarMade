import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class class_611
{
  public final short field_179;
  public final byte field_180;
  
  public class_611(byte paramByte, short paramShort)
  {
    this.field_180 = paramByte;
    this.field_179 = paramShort;
  }
  
  public void b(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeByte(this.field_180);
    paramDataOutputStream.writeShort(this.field_179);
    a1(paramDataOutputStream);
  }
  
  protected abstract void a(DataInputStream paramDataInputStream);
  
  protected abstract void a1(DataOutputStream paramDataOutputStream);
  
  public static class_611 a3(DataInputStream paramDataInputStream)
  {
    byte b;
    Object localObject;
    switch (b = paramDataInputStream.readByte())
    {
    case 1: 
      localObject = new class_615(b, paramDataInputStream.readShort());
      break;
    case 2: 
      localObject = new class_603(localObject, paramDataInputStream.readShort());
      break;
    case 3: 
      localObject = new class_607(localObject, paramDataInputStream.readShort());
      break;
    case 4: 
      localObject = new class_613(localObject, paramDataInputStream.readShort());
      break;
    case 5: 
      localObject = new class_617(localObject, paramDataInputStream.readShort());
      break;
    case 6: 
      localObject = new class_605(localObject, paramDataInputStream.readShort());
      break;
    default: 
      throw new IllegalArgumentException("Missile Update type not found " + localObject);
    }
    ((class_611)localObject).a(paramDataInputStream);
    return localObject;
  }
  
  public abstract void a2(class_371 paramclass_371, Short2ObjectArrayMap paramShort2ObjectArrayMap, class_45 paramclass_45);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_611
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */