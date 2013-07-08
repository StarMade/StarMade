import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class class_345
  implements class_347
{
  public static class_347[] a15(DataInputStream paramDataInputStream)
  {
    int i;
    class_347[] arrayOfclass_347 = new class_347[i = paramDataInputStream.readInt()];
    int j = 0;
    if (j < i)
    {
      DataInputStream localDataInputStream = paramDataInputStream;
      localObject = null;
      byte b;
      switch (b = localDataInputStream.readByte())
      {
      case 10: 
        tmpTernaryOp = j;
        break;
      case 5: 
        localObject = new class_337();
        tmpTernaryOp = arrayOfclass_347;
        break;
      case 2: 
        localObject = new class_337();
        break;
      case 4: 
        localObject = new class_349();
        break;
      case 1: 
        localObject = new class_337();
        break;
      case 3: 
        localObject = new class_337();
        break;
      case 6: 
      case 7: 
      case 8: 
      case 9: 
      default: 
        if (!field_136) {
          throw new AssertionError(b);
        }
        break;
      }
      ((class_345)localObject).a4(b);
      ((class_345)localObject).a1(localDataInputStream);
    }
  }
  
  public static void a16(DataOutputStream paramDataOutputStream, class_347[] paramArrayOfclass_347)
  {
    paramDataOutputStream.writeInt(paramArrayOfclass_347.length);
    for (int i = 0; i < paramArrayOfclass_347.length; i++)
    {
      paramDataOutputStream.writeByte(paramArrayOfclass_347[i].a3());
      paramArrayOfclass_347[i].a2(paramDataOutputStream);
    }
  }
  
  protected abstract void a1(DataInputStream paramDataInputStream);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_345
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */