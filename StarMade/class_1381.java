import java.nio.FloatBuffer;
import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_1381
  extends class_1387
{
  public final Vector3f a(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, boolean paramBoolean)
  {
    (paramVector3f4 = new Vector3f(paramVector3f4)).normalize();
    this.jdField_field_1583_of_type_JavaxVecmathVector3f.set(paramVector3f1);
    this.jdField_field_1584_of_type_JavaxVecmathVector3f.sub(paramVector3f1, paramVector3f3);
    this.jdField_field_1584_of_type_JavaxVecmathVector3f.normalize();
    this.jdField_field_1583_of_type_JavaNioFloatBuffer.rewind();
    GlUtil.a16(paramVector3f1.field_615, paramVector3f1.field_616, paramVector3f1.field_617, this.jdField_field_1584_of_type_JavaNioFloatBuffer, this.jdField_field_1585_of_type_JavaNioFloatBuffer, this.jdField_field_1583_of_type_JavaNioIntBuffer, this.jdField_field_1583_of_type_JavaNioFloatBuffer);
    this.jdField_field_1583_of_type_JavaxVecmathVector3f.set(this.jdField_field_1583_of_type_JavaNioFloatBuffer.get(0), this.jdField_field_1583_of_type_JavaNioFloatBuffer.get(1), this.jdField_field_1583_of_type_JavaNioFloatBuffer.get(2));
    paramVector3f1 = paramVector3f4.dot(this.jdField_field_1584_of_type_JavaxVecmathVector3f);
    a1(this.field_1586);
    paramVector3f2.set(this.jdField_field_1583_of_type_JavaxVecmathVector3f.field_615, 600.0F - this.jdField_field_1583_of_type_JavaxVecmathVector3f.field_616, 0.0F);
    if (paramVector3f1 < 0.0F)
    {
      (paramVector3f1 = new Vector3f()).sub(this.field_1586, paramVector3f2);
      if (paramVector3f1.length() == 0.0F) {
        paramVector3f1.set(this.jdField_field_1585_of_type_JavaxVecmathVector3f);
      }
      paramVector3f1.normalize();
      this.jdField_field_1585_of_type_JavaxVecmathVector3f.set(paramVector3f1);
      if (paramBoolean) {
        paramVector3f1.scale(10000000.0F);
      }
      paramVector3f2.add(paramVector3f1);
    }
    return paramVector3f2;
  }
  
  public final Vector3f a1(Vector3f paramVector3f)
  {
    paramVector3f.set(400.0F, 300.0F, 0.0F);
    return paramVector3f;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1381
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */