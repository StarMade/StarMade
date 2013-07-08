import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

final class class_234
  implements class_955
{
  private Vector4f jdField_field_89_of_type_JavaxVecmathVector4f = new Vector4f();
  
  class_234(class_228 paramclass_228) {}
  
  public final int a105(class_1380 paramclass_1380)
  {
    return 10;
  }
  
  public final float a106(long paramLong)
  {
    return 0.1F + 0.07F * class_228.a31(this.jdField_field_89_of_type_Class_228).a1();
  }
  
  public final Vector3f a83()
  {
    return class_1220.a1(class_228.a32(this.jdField_field_89_of_type_Class_228).a3().getWorldTransform().origin, class_228.a32(this.jdField_field_89_of_type_Class_228).a20().a44());
  }
  
  public final Vector4f a63()
  {
    this.jdField_field_89_of_type_JavaxVecmathVector4f.set((1.0F - class_228.a31(this.jdField_field_89_of_type_Class_228).a1()) / 2.0F + 0.5F, 1.0F, (1.0F - class_228.a31(this.jdField_field_89_of_type_Class_228).a1()) / 2.0F + 0.5F, 1.0F);
    return this.jdField_field_89_of_type_JavaxVecmathVector4f;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_234
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */