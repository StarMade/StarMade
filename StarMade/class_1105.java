import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public final class class_1105
  extends class_1177
{
  private static int jdField_field_248_of_type_Int = 15;
  private byte jdField_field_248_of_type_Byte = 3;
  private Transform jdField_field_248_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  
  public final void a3(SegmentController paramSegmentController, Segment paramSegment)
  {
    if ((paramSegment.field_34.field_476 < jdField_field_248_of_type_Int << 4) && (paramSegment.field_34.field_476 > -jdField_field_248_of_type_Int << 4) && (paramSegment.field_34.field_475 == 0) && (paramSegment.field_34.field_477 == 0))
    {
      SegmentController localSegmentController = paramSegmentController;
      paramSegment = paramSegment;
      paramSegmentController = this;
      new Vector3f();
      int i = (byte)(8 - paramSegmentController.jdField_field_248_of_type_Byte);
      int j = (byte)(8 + paramSegmentController.jdField_field_248_of_type_Byte);
      byte b;
      for (int k = i; k < j; k = (byte)(k + 1))
      {
        for (b = 0; b < 16; b = (byte)(b + 1)) {
          for (int m = i; m < j; m = (byte)(m + 1)) {
            if (((m > i) || (k > i)) && ((m < j - 1) || (k < j - 1)) && ((m > i) || (k < j - 1)) && ((m < j - 1) || (k > i))) {
              a12(m, b, k, paramSegment, Short.valueOf((short)5));
            }
          }
        }
        localSegmentController.getSegmentBuffer().b6(paramSegment);
      }
      if (Math.random() < 0.1000000014901161D) {
        paramSegmentController.a7(b, paramSegment);
      }
      if (paramSegment.field_34.a3(0, 0, 0))
      {
        paramSegmentController.a7((byte)0, paramSegment);
        a12(0, 0, 8, paramSegment, Short.valueOf((short)94));
        a12(15, 0, 8, paramSegment, Short.valueOf((short)94));
        a12(8, 0, 0, paramSegment, Short.valueOf((short)94));
        a12(8, 0, 15, paramSegment, Short.valueOf((short)94));
      }
    }
  }
  
  private void a7(byte paramByte, Segment paramSegment)
  {
    short s = Math.random() < 0.5D ? 5 : 75;
    this.jdField_field_248_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    Vector3f localVector3f = new Vector3f(1.0F, 0.0F, 0.0F);
    for (float f = 0.0F; f < 6.283186F; f += 0.05F)
    {
      localVector3f.set(1.0F, 0.0F, 0.0F);
      this.jdField_field_248_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
      this.jdField_field_248_of_type_ComBulletphysicsLinearmathTransform.basis.rotY(f);
      this.jdField_field_248_of_type_ComBulletphysicsLinearmathTransform.transform(localVector3f);
      localVector3f.scale(7.0F);
      int i = 8 + (int)(localVector3f.field_615 - 0.5F);
      int j = 8 + (int)(localVector3f.field_617 - 0.5F);
      a12(i, paramByte, j, paramSegment, Short.valueOf(s));
    }
    paramSegment.a15().getSegmentBuffer().b6(paramSegment);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1105
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */