import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.game.network.objects.NetworkClientChannel;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteShort;

public class class_613
  extends class_611
{
  public int field_179;
  
  public class_613(byte paramByte, short paramShort)
  {
    super(paramByte, paramShort);
  }
  
  public class_613(short paramShort)
  {
    this((byte)4, paramShort);
  }
  
  protected final void a(DataInputStream paramDataInputStream)
  {
    this.jdField_field_179_of_type_Int = paramDataInputStream.readInt();
    if ((!jdField_field_179_of_type_Boolean) && (this.field_180 != 4)) {
      throw new AssertionError();
    }
  }
  
  protected final void a1(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(this.jdField_field_179_of_type_Int);
  }
  
  public final void a2(class_371 paramclass_371, Short2ObjectArrayMap paramShort2ObjectArrayMap, class_45 paramclass_45)
  {
    if (((paramclass_371 = (class_597)paramShort2ObjectArrayMap.get(this.jdField_field_179_of_type_Short)) != null) && ((paramclass_371 instanceof class_609)))
    {
      ((class_609)paramclass_371).c5(this.jdField_field_179_of_type_Int);
      return;
    }
    paramclass_45.a().missileMissingRequestBuffer.add(new RemoteShort(this.jdField_field_179_of_type_Short, false));
  }
  
  static
  {
    jdField_field_179_of_type_Boolean = !lv.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_613
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */