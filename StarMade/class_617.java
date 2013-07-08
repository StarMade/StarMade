import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.vecmath.Vector3f;
import org.schema.game.network.objects.NetworkClientChannel;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteShort;

public class class_617
  extends class_611
{
  public int field_179;
  public Vector3f field_179;
  public Vector3f field_180 = new Vector3f();
  
  public class_617(byte paramByte, short paramShort)
  {
    super(paramByte, paramShort);
    this.jdField_field_179_of_type_JavaxVecmathVector3f = new Vector3f();
    if ((!jdField_field_179_of_type_Boolean) && (paramByte != 5)) {
      throw new AssertionError();
    }
  }
  
  public class_617(short paramShort)
  {
    this((byte)5, paramShort);
  }
  
  protected final void a(DataInputStream paramDataInputStream)
  {
    this.jdField_field_179_of_type_Int = paramDataInputStream.readInt();
    this.jdField_field_179_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
    this.field_180.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
  }
  
  protected final void a1(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(this.jdField_field_179_of_type_Int);
    paramDataOutputStream.writeFloat(this.jdField_field_179_of_type_JavaxVecmathVector3f.field_615);
    paramDataOutputStream.writeFloat(this.jdField_field_179_of_type_JavaxVecmathVector3f.field_616);
    paramDataOutputStream.writeFloat(this.jdField_field_179_of_type_JavaxVecmathVector3f.field_617);
    paramDataOutputStream.writeFloat(this.field_180.field_615);
    paramDataOutputStream.writeFloat(this.field_180.field_616);
    paramDataOutputStream.writeFloat(this.field_180.field_617);
  }
  
  public final void a2(class_371 paramclass_371, Short2ObjectArrayMap paramShort2ObjectArrayMap, class_45 paramclass_45)
  {
    if ((paramclass_371 = (class_597)paramShort2ObjectArrayMap.get(this.jdField_field_179_of_type_Short)) != null)
    {
      paramclass_371.b7(this.jdField_field_179_of_type_Int);
      paramclass_371.b8().origin.set(this.jdField_field_179_of_type_JavaxVecmathVector3f);
      paramclass_371.a2().set(this.field_180);
      return;
    }
    paramclass_45.a().missileMissingRequestBuffer.add(new RemoteShort(this.jdField_field_179_of_type_Short, false));
  }
  
  static
  {
    jdField_field_179_of_type_Boolean = !lt.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_617
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */