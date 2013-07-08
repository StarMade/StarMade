import com.bulletphysics.linearmath.Transform;
import java.util.Arrays;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.Element;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

public class class_796
{
  private byte[] jdField_field_1056_of_type_ArrayOfByte;
  private Segment jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment;
  private int jdField_field_1056_of_type_Int = -1;
  public byte field_1056;
  public byte field_1057;
  public byte field_1058;
  
  public class_796()
  {
    this.jdField_field_1056_of_type_ArrayOfByte = new byte[3];
  }
  
  public final void a(Segment paramSegment, class_35 paramclass_35)
  {
    this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
    a16(paramclass_35);
    if (paramSegment.g())
    {
      if (this.jdField_field_1056_of_type_ArrayOfByte == null)
      {
        this.jdField_field_1056_of_type_ArrayOfByte = new byte[3];
        return;
      }
      Arrays.fill(this.jdField_field_1056_of_type_ArrayOfByte, (byte)0);
      return;
    }
    this.jdField_field_1056_of_type_ArrayOfByte = paramSegment.a16().getSegmentPieceData(SegmentData.getInfoIndex(paramclass_35), this.jdField_field_1056_of_type_ArrayOfByte == null ? new byte[3] : this.jdField_field_1056_of_type_ArrayOfByte);
  }
  
  public class_796(Segment paramSegment, class_35 paramclass_35)
  {
    a(paramSegment, paramclass_35);
  }
  
  public class_796(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3)
  {
    this.jdField_field_1056_of_type_Byte = paramByte1;
    this.field_1057 = paramByte2;
    this.field_1058 = paramByte3;
    this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
    if (paramSegment.g())
    {
      this.jdField_field_1056_of_type_ArrayOfByte = new byte[3];
      return;
    }
    this.jdField_field_1056_of_type_ArrayOfByte = paramSegment.a16().getSegmentPieceData(SegmentData.getInfoIndex(paramByte1, paramByte2, paramByte3), new byte[3]);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject != null) && ((paramObject instanceof class_796))) {
      return ((paramObject = (class_796)paramObject).jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment.a15() == this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment.a15()) && (paramObject.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment.field_34.equals(this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment.field_34)) && (paramObject.jdField_field_1056_of_type_Byte == this.jdField_field_1056_of_type_Byte) && (paramObject.field_1057 == this.field_1057) && (paramObject.field_1058 == this.field_1058);
    }
    return false;
  }
  
  public final boolean a1(class_48 paramclass_48)
  {
    return this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment.a12(paramclass_48, this.jdField_field_1056_of_type_Byte, this.field_1057, this.field_1058);
  }
  
  public final class_48 a2(class_48 paramclass_48)
  {
    return this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment.a13(this.jdField_field_1056_of_type_Byte, this.field_1057, this.field_1058, paramclass_48);
  }
  
  public final byte a3()
  {
    byte b = 0;
    if (this.jdField_field_1056_of_type_Byte == 0) {
      b = 2;
    }
    if (this.jdField_field_1056_of_type_Byte == 15) {
      b = (byte)(b + 1);
    }
    if (this.field_1057 == 0) {
      b = (byte)(b + 8);
    }
    if (this.field_1057 == 15) {
      b = (byte)(b + 4);
    }
    if (this.field_1058 == 0) {
      b = (byte)(b + 32);
    }
    if (this.field_1058 == 15) {
      b = (byte)(b + 16);
    }
    return b;
  }
  
  public final byte[] a4()
  {
    return this.jdField_field_1056_of_type_ArrayOfByte;
  }
  
  public final int a5()
  {
    return (short)ByteUtil.a1(ByteUtil.a4(this.jdField_field_1056_of_type_ArrayOfByte, 0), 11, 20);
  }
  
  public final int b()
  {
    return this.jdField_field_1056_of_type_Int;
  }
  
  public final byte b1()
  {
    return (byte)ByteUtil.a1(ByteUtil.a4(this.jdField_field_1056_of_type_ArrayOfByte, 0), 21, 24);
  }
  
  public final class_35 a6(class_35 paramclass_35)
  {
    paramclass_35.b(this.jdField_field_1056_of_type_Byte, this.field_1057, this.field_1058);
    return paramclass_35;
  }
  
  public final Segment a7()
  {
    return this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment;
  }
  
  public final void a8(Transform paramTransform)
  {
    paramTransform.set(this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment.a15().getWorldTransform());
    Object localObject = a2(new class_48());
    localObject = new Vector3f(((class_48)localObject).field_475 - 8, ((class_48)localObject).field_476 - 8, ((class_48)localObject).field_477 - 8);
    paramTransform.basis.transform((Tuple3f)localObject);
    paramTransform.origin.add((Tuple3f)localObject);
  }
  
  public final short a9()
  {
    return (short)ByteUtil.a1(ByteUtil.a4(this.jdField_field_1056_of_type_ArrayOfByte, 0), 0, 11);
  }
  
  public int hashCode()
  {
    class_796 localclass_796 = this;
    long l = 7L + localclass_796.jdField_field_1056_of_type_Byte;
    l = 7L * l + localclass_796.field_1057;
    long tmp45_44 = (7L * l + localclass_796.field_1058);
    return this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment.field_34.hashCode() + (byte)(int)(tmp45_44 ^ tmp45_44 >> 8);
  }
  
  public final boolean a10()
  {
    return ByteUtil.a1(ByteUtil.a4(this.jdField_field_1056_of_type_ArrayOfByte, 0), 20, 21) == 0;
  }
  
  public final boolean b2()
  {
    return (this.jdField_field_1056_of_type_Byte == 0) || (this.field_1057 == 0) || (this.field_1058 == 0) || (this.jdField_field_1056_of_type_Byte == 15) || (this.field_1057 == 15) || (this.field_1058 == 15);
  }
  
  public final void a11(Segment paramSegment, byte paramByte1, byte paramByte2, byte paramByte3)
  {
    this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
    class_796 localclass_796 = this;
    this.jdField_field_1056_of_type_Byte = paramByte1;
    localclass_796.field_1057 = paramByte2;
    localclass_796.field_1058 = paramByte3;
    if ((paramSegment.g()) || (paramSegment.a16() == null))
    {
      Arrays.fill(this.jdField_field_1056_of_type_ArrayOfByte, (byte)0);
      return;
    }
    this.jdField_field_1056_of_type_ArrayOfByte = paramSegment.a16().getSegmentPieceData(SegmentData.getInfoIndex(paramByte1, paramByte2, paramByte3), this.jdField_field_1056_of_type_ArrayOfByte);
  }
  
  public final void a12()
  {
    a11(this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment, this.jdField_field_1056_of_type_Byte, this.field_1057, this.field_1058);
  }
  
  public final void a13(boolean paramBoolean)
  {
    ByteUtil.a8(this.jdField_field_1056_of_type_ArrayOfByte, paramBoolean ? 0 : 1, 20, 21, 0);
  }
  
  public final void a14(byte[] paramArrayOfByte)
  {
    this.jdField_field_1056_of_type_ArrayOfByte = paramArrayOfByte;
  }
  
  public final void a15(int paramInt)
  {
    this.jdField_field_1056_of_type_Int = paramInt;
  }
  
  public final void a16(class_35 paramclass_35)
  {
    this.jdField_field_1056_of_type_Byte = paramclass_35.field_453;
    this.field_1057 = paramclass_35.field_454;
    this.field_1058 = paramclass_35.field_455;
  }
  
  public final void a17(Segment paramSegment)
  {
    this.jdField_field_1056_of_type_OrgSchemaGameCommonDataWorldSegment = paramSegment;
  }
  
  public String toString()
  {
    return a2(new class_48()).toString() + "[" + (a9() != 0 ? ElementKeyMap.getInfo(a9()).getName() : "NONE") + "]o[" + Element.getSideString(Element.orientationBackMapping[b1()]) + "]";
  }
  
  static
  {
    new class_48();
    new class_48();
    new Vector3f();
    new Vector3f();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_796
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */