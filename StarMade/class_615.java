import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import javax.vecmath.Vector3f;

public class class_615
  extends class_611
{
  public Vector3f field_179;
  public Vector3f field_180;
  public int field_179;
  public byte field_179;
  public int field_180;
  
  public class_615(byte paramByte, short paramShort)
  {
    super(paramByte, paramShort);
    this.jdField_field_179_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_180_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_180_of_type_Int = -666;
    if ((!jdField_field_179_of_type_Boolean) && (paramByte != 1)) {
      throw new AssertionError();
    }
  }
  
  public class_615(short paramShort, Vector3f paramVector3f1, Vector3f paramVector3f2, int paramInt1, byte paramByte, int paramInt2)
  {
    super((byte)1, paramShort);
    this.jdField_field_179_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_180_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_180_of_type_Int = -666;
    this.jdField_field_179_of_type_JavaxVecmathVector3f.set(paramVector3f1);
    this.jdField_field_180_of_type_JavaxVecmathVector3f.set(paramVector3f2);
    this.jdField_field_179_of_type_Int = paramInt1;
    this.jdField_field_179_of_type_Byte = paramByte;
    this.jdField_field_180_of_type_Int = paramInt2;
  }
  
  protected final void a(DataInputStream paramDataInputStream)
  {
    this.jdField_field_179_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
    this.jdField_field_180_of_type_JavaxVecmathVector3f.set(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
    this.jdField_field_179_of_type_Int = paramDataInputStream.readInt();
    this.jdField_field_180_of_type_Int = paramDataInputStream.readInt();
    this.jdField_field_179_of_type_Byte = paramDataInputStream.readByte();
  }
  
  protected final void a1(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeFloat(this.jdField_field_179_of_type_JavaxVecmathVector3f.field_615);
    paramDataOutputStream.writeFloat(this.jdField_field_179_of_type_JavaxVecmathVector3f.field_616);
    paramDataOutputStream.writeFloat(this.jdField_field_179_of_type_JavaxVecmathVector3f.field_617);
    paramDataOutputStream.writeFloat(this.jdField_field_180_of_type_JavaxVecmathVector3f.field_615);
    paramDataOutputStream.writeFloat(this.jdField_field_180_of_type_JavaxVecmathVector3f.field_616);
    paramDataOutputStream.writeFloat(this.jdField_field_180_of_type_JavaxVecmathVector3f.field_617);
    paramDataOutputStream.writeInt(this.jdField_field_179_of_type_Int);
    paramDataOutputStream.writeInt(this.jdField_field_180_of_type_Int);
    paramDataOutputStream.writeByte(this.jdField_field_179_of_type_Byte);
  }
  
  public final void a2(class_371 paramclass_371, Short2ObjectArrayMap paramShort2ObjectArrayMap, class_45 paramclass_45)
  {
    if (!paramShort2ObjectArrayMap.containsKey(this.jdField_field_179_of_type_Short))
    {
      paramclass_45 = paramclass_371;
      paramclass_371 = this;
      switch (this.jdField_field_179_of_type_Byte)
      {
      case 1: 
        paramclass_45 = new class_792(paramclass_45);
        break;
      case 2: 
        paramclass_45 = new class_599(paramclass_45);
        break;
      case 3: 
        paramclass_45 = new class_601(paramclass_45);
        break;
      default: 
        throw new IllegalArgumentException("Missile Type unknown " + paramclass_371.jdField_field_179_of_type_Byte);
      }
      paramclass_45.a20(paramclass_371);
      paramclass_371 = paramclass_45;
      System.err.println("[CLIENT] SPAWNING NEW MISSILE " + paramclass_371);
      paramShort2ObjectArrayMap.put(paramclass_371.a18(), paramclass_371);
      paramclass_371.a7();
      return;
    }
    System.err.println("[CLIENT] not adding missile (already exists) ID " + this.jdField_field_179_of_type_Short + " -> " + paramShort2ObjectArrayMap.get(this.jdField_field_179_of_type_Short));
  }
  
  static
  {
    jdField_field_179_of_type_Boolean = !lu.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_615
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */