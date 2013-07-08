import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.vecmath.Vector3f;

public class class_607
  extends class_611
{
  private Vector3f jdField_field_179_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public class_607(byte paramByte, short paramShort)
  {
    super(paramByte, paramShort);
    if ((!jdField_field_179_of_type_Boolean) && (paramByte != 3)) {
      throw new AssertionError();
    }
  }
  
  public class_607(short paramShort)
  {
    this((byte)3, paramShort);
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
  
  public final void b(DataOutputStream paramDataOutputStream)
  {
    super.b(paramDataOutputStream);
    if ((!jdField_field_179_of_type_Boolean) && (this.field_180 != 3)) {
      throw new AssertionError();
    }
  }
  
  public final void a2(class_371 paramclass_371, Short2ObjectArrayMap paramShort2ObjectArrayMap, class_45 paramclass_45)
  {
    if ((paramclass_371 = (class_597)paramShort2ObjectArrayMap.remove(this.jdField_field_179_of_type_Short)) != null) {
      paramclass_371.b1();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_607
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */