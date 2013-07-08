import java.io.DataInputStream;
import java.util.Arrays;
import org.schema.common.util.ByteUtil;
import org.schema.game.common.data.physics.octree.Octree;

public class class_793
{
  private byte[] jdField_field_1051_of_type_ArrayOfByte;
  private class_35 jdField_field_1051_of_type_Class_35 = new class_35();
  private class_35 field_1052 = new class_35();
  private Octree jdField_field_1051_of_type_OrgSchemaGameCommonDataPhysicsOctreeOctree;
  
  public static int a(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    if (!jdField_field_1051_of_type_Boolean)
    {
      if (((paramByte1 < 16) && (paramByte2 < 16) && (paramByte3 < 16) ? 1 : 0) != 0) {}
      if (((paramByte1 >= 0) && (paramByte2 >= 0) && (paramByte3 >= 0) ? 1 : 0) == 0) {
        throw new AssertionError(paramByte1 + ", " + paramByte2 + ", " + paramByte3 + ": DIM 16;");
      }
    }
    return 6 * ((paramByte3 << 8) + (paramByte2 << 4) + paramByte1);
  }
  
  public class_793()
  {
    new class_35();
    this.jdField_field_1051_of_type_OrgSchemaGameCommonDataPhysicsOctreeOctree = new Octree(2, false);
    this.jdField_field_1051_of_type_ArrayOfByte = new byte[24576];
    a3();
  }
  
  public final void a1(DataInputStream paramDataInputStream)
  {
    synchronized (this)
    {
      class_793 localclass_793 = this;
      synchronized (this)
      {
        Arrays.fill(localclass_793.jdField_field_1051_of_type_ArrayOfByte, (byte)0);
        localclass_793.jdField_field_1051_of_type_OrgSchemaGameCommonDataPhysicsOctreeOctree.reset();
        localclass_793.a3();
      }
      paramDataInputStream.readFully(this.jdField_field_1051_of_type_ArrayOfByte);
      return;
    }
  }
  
  public final short a2(int paramInt)
  {
    return ByteUtil.a12(this.jdField_field_1051_of_type_ArrayOfByte, paramInt);
  }
  
  private void a3()
  {
    this.field_1052.b((byte)-128, (byte)-128, (byte)-128);
    this.jdField_field_1051_of_type_Class_35.b((byte)127, (byte)127, (byte)127);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_793
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */