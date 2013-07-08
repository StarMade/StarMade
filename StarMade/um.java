/*  1:   */import com.bulletphysics.linearmath.Transform;
/*  2:   */import javax.vecmath.Matrix3f;
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */import org.schema.game.common.controller.SegmentController;
/*  5:   */import org.schema.game.common.data.world.Segment;
/*  6:   */
/* 12:   */public final class um
/* 13:   */  extends tW
/* 14:   */{
/* 15:15 */  private static int jdField_a_of_type_Int = 15;
/* 16:   */  
/* 17:17 */  private byte jdField_a_of_type_Byte = 3;
/* 18:   */  
/* 19:19 */  private Transform jdField_a_of_type_ComBulletphysicsLinearmathTransform = new Transform();
/* 20:   */  
/* 69:   */  public final void a(SegmentController paramSegmentController, Segment paramSegment)
/* 70:   */  {
/* 71:71 */    if ((paramSegment.a.b < jdField_a_of_type_Int << 4) && (paramSegment.a.b > -jdField_a_of_type_Int << 4) && (paramSegment.a.jdField_a_of_type_Int == 0) && (paramSegment.a.c == 0))
/* 72:   */    {
/* 73:73 */      SegmentController localSegmentController = paramSegmentController;paramSegment = paramSegment;paramSegmentController = this;new Vector3f();int i = (byte)(8 - paramSegmentController.jdField_a_of_type_Byte);int j = (byte)(8 + paramSegmentController.jdField_a_of_type_Byte); byte b; for (int k = i; k < j; k = (byte)(k + 1)) { for (b = 0; b < 16; b = (byte)(b + 1)) for (int m = i; m < j; m = (byte)(m + 1)) if (((m > i) || (k > i)) && ((m < j - 1) || (k < j - 1)) && ((m > i) || (k < j - 1)) && ((m < j - 1) || (k > i))) a(m, b, k, paramSegment, Short.valueOf((short)5)); localSegmentController.getSegmentBuffer().b(paramSegment); } if (Math.random() < 0.1000000014901161D) paramSegmentController.a(b, paramSegment); if (paramSegment.a.a(0, 0, 0)) { paramSegmentController.a((byte)0, paramSegment);a(0, 0, 8, paramSegment, Short.valueOf((short)94));a(15, 0, 8, paramSegment, Short.valueOf((short)94));a(8, 0, 0, paramSegment, Short.valueOf((short)94));a(8, 0, 15, paramSegment, Short.valueOf((short)94));
/* 74:   */      }
/* 75:   */    } }
/* 76:   */  
/* 77:77 */  private void a(byte paramByte, Segment paramSegment) { short s = Math.random() < 0.5D ? 5 : 75;
/* 78:78 */    this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/* 79:79 */    Vector3f localVector3f = new Vector3f(1.0F, 0.0F, 0.0F);
/* 80:   */    
/* 81:81 */    for (float f = 0.0F; f < 6.283186F; f += 0.05F) {
/* 82:82 */      localVector3f.set(1.0F, 0.0F, 0.0F);
/* 83:83 */      this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
/* 84:84 */      this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.basis.rotY(f);
/* 85:85 */      this.jdField_a_of_type_ComBulletphysicsLinearmathTransform.transform(localVector3f);
/* 86:86 */      localVector3f.scale(7.0F);
/* 87:87 */      int i = 8 + (int)(localVector3f.x - 0.5F);
/* 88:88 */      int j = 8 + (int)(localVector3f.z - 0.5F);
/* 89:   */      
/* 90:90 */      a(i, paramByte, j, paramSegment, Short.valueOf(s));
/* 91:   */    }
/* 92:92 */    paramSegment.a().getSegmentBuffer().b(paramSegment);
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     um
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */