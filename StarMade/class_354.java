import java.util.ArrayList;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.schine.graphicsengine.camera.Camera;

public class class_354
  implements class_965
{
  private class_1380 jdField_field_98_of_type_Class_1380;
  private int jdField_field_98_of_type_Int;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_106_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f field_108 = new Vector3f();
  private Vector3f field_109 = new Vector3f();
  private Vector3f field_110 = new Vector3f();
  private Vector3f field_111 = new Vector3f();
  private ArrayList jdField_field_98_of_type_JavaUtilArrayList = new ArrayList(16);
  private float jdField_field_98_of_type_Float;
  private float jdField_field_106_of_type_Float = 6.0F;
  
  public final void a() {}
  
  public class_354()
  {
    for (int i = 0; i < 16; i++) {
      this.jdField_field_98_of_type_JavaUtilArrayList.add(new class_352((byte)0));
    }
  }
  
  public final void b()
  {
    if ((!jdField_field_98_of_type_Boolean) && (this.jdField_field_98_of_type_Class_1380 == null)) {
      throw new AssertionError();
    }
    this.jdField_field_98_of_type_Int = 0;
    this.jdField_field_106_of_type_JavaxVecmathVector3f.set(class_971.field_98.a83());
    this.jdField_field_98_of_type_JavaxVecmathVector3f.set(class_969.a1().a83());
    this.field_108.sub(this.jdField_field_98_of_type_JavaxVecmathVector3f, this.jdField_field_106_of_type_JavaxVecmathVector3f);
    this.jdField_field_98_of_type_Float = this.field_108.length();
    this.field_109.scale(this.jdField_field_98_of_type_Float, class_969.a1().c10());
    this.field_109.add(this.jdField_field_98_of_type_JavaxVecmathVector3f);
    this.field_110.sub(this.field_109, this.jdField_field_106_of_type_JavaxVecmathVector3f);
    this.jdField_field_98_of_type_Float = this.field_110.length();
    this.field_110.normalize();
    Vector3f localVector3f = this.jdField_field_106_of_type_JavaxVecmathVector3f;
    a36(0.6F, 0.6F, 0.8F, 1.0F, localVector3f, 16.0F).jdField_field_89_of_type_Int = 3;
    localVector3f = this.jdField_field_106_of_type_JavaxVecmathVector3f;
    a36(0.6F, 0.6F, 0.8F, 1.0F, localVector3f, 16.0F).jdField_field_89_of_type_Int = 2;
    a38(0.8F, 0.8F, 1.0F, 0.9F, this.jdField_field_106_of_type_JavaxVecmathVector3f, 3.5F);
    this.jdField_field_98_of_type_Float *= 2.0F;
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.1F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a38(0.9F, 0.6F, 0.4F, 0.5F, this.field_111, 0.6F);
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.15F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a37(0.8F, 0.5F, 0.6F, this.field_111, 1.7F);
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.175F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a37(0.9F, 0.2F, 0.1F, this.field_111, 0.83F);
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.285F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a37(0.7F, 0.7F, 0.4F, this.field_111, 1.6F);
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.2755F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a38(0.9F, 0.9F, 0.2F, 0.5F, this.field_111, 0.8F);
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.4775F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a38(0.93F, 0.82F, 0.73F, 0.5F, this.field_111, 1.0F);
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.49F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a37(0.7F, 0.6F, 0.5F, this.field_111, 1.4F);
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.65F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a38(0.7F, 0.8F, 0.3F, 0.5F, this.field_111, 1.8F);
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.63F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a38(0.4F, 0.3F, 0.2F, 0.5F, this.field_111, 1.4F);
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.8F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a37(0.7F, 0.5F, 0.5F, this.field_111, 1.4F);
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.7825F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a38(0.8F, 0.5F, 0.1F, 0.5F, this.field_111, 0.6F);
    this.field_111.scale(this.jdField_field_98_of_type_Float, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a37(0.5F, 0.5F, 0.7F, this.field_111, 1.7F);
    this.field_111.scale(this.jdField_field_98_of_type_Float * 0.975F, this.field_110);
    this.field_111.add(this.jdField_field_106_of_type_JavaxVecmathVector3f);
    a38(0.4F, 0.1F, 0.9F, 0.5F, this.field_111, 2.0F);
    this.jdField_field_98_of_type_Class_1380.jdField_field_89_of_type_Int = 1;
    this.jdField_field_98_of_type_Class_1380.b21(false);
    class_1380.a171(this.jdField_field_98_of_type_Class_1380, this.jdField_field_98_of_type_JavaUtilArrayList, class_969.a1());
  }
  
  private class_352 a36(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Vector3f paramVector3f, float paramFloat5)
  {
    class_352 localclass_352;
    (localclass_352 = (class_352)this.jdField_field_98_of_type_JavaUtilArrayList.get(this.jdField_field_98_of_type_Int++)).jdField_field_89_of_type_JavaxVecmathVector3f.set(paramVector3f);
    localclass_352.jdField_field_89_of_type_Float = (paramFloat5 * this.jdField_field_106_of_type_Float);
    localclass_352.jdField_field_89_of_type_JavaxVecmathVector4f.set(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    return localclass_352;
  }
  
  private void a37(float paramFloat1, float paramFloat2, float paramFloat3, Vector3f paramVector3f, float paramFloat4)
  {
    a36(paramFloat1, paramFloat2, paramFloat3, 0.5F, paramVector3f, paramFloat4).jdField_field_89_of_type_Int = 0;
  }
  
  private void a38(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Vector3f paramVector3f, float paramFloat5)
  {
    a36(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramVector3f, paramFloat5).jdField_field_89_of_type_Int = 1;
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_Class_1380 = class_969.a2().a5("lens_flare-4x1-c-");
    if ((!jdField_field_98_of_type_Boolean) && (this.jdField_field_98_of_type_Class_1380 == null)) {
      throw new AssertionError();
    }
  }
  
  public static void d() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_354
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */