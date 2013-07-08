import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.vecmath.Vector3f;

public class class_605
  extends class_611
{
  public Vector3f field_179;
  
  public class_605(byte paramByte, short paramShort)
  {
    super(paramByte, paramShort);
    this.jdField_field_179_of_type_JavaxVecmathVector3f = new Vector3f();
    if ((!jdField_field_179_of_type_Boolean) && (paramByte != 6)) {
      throw new AssertionError();
    }
  }
  
  public class_605(short paramShort)
  {
    this((byte)6, paramShort);
  }
  
  protected final void a(DataInputStream paramDataInputStream)
  {
    this.jdField_field_179_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
  }
  
  protected final void a1(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeFloat(this.jdField_field_179_of_type_JavaxVecmathVector3f.field_615);
    paramDataOutputStream.writeFloat(this.jdField_field_179_of_type_JavaxVecmathVector3f.field_616);
    paramDataOutputStream.writeFloat(this.jdField_field_179_of_type_JavaxVecmathVector3f.field_617);
  }
  
  public final void a2(class_371 paramclass_371, Short2ObjectArrayMap paramShort2ObjectArrayMap, class_45 paramclass_45)
  {
    if ((paramShort2ObjectArrayMap = (class_597)paramShort2ObjectArrayMap.get(this.jdField_field_179_of_type_Short)) != null)
    {
      paramShort2ObjectArrayMap.a2().set(this.jdField_field_179_of_type_JavaxVecmathVector3f);
      return;
    }
    paramclass_371.a4().a36().a2(this.jdField_field_179_of_type_Short, paramclass_45);
  }
  
  static
  {
    jdField_field_179_of_type_Boolean = !lr.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_605
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */