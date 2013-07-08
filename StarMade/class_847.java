import com.bulletphysics.linearmath.Transform;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.network.client.ClientState;

public final class class_847
  extends class_1363
{
  private boolean jdField_field_89_of_type_Boolean;
  private final class_797 jdField_field_89_of_type_Class_797;
  private final String field_90;
  
  public class_847(class_177 paramclass_177, ClientState paramClientState, class_797 paramclass_797, boolean paramBoolean)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_797 = paramclass_797;
    this.jdField_field_89_of_type_Boolean = paramBoolean;
    if ((paramclass_797 instanceof class_747))
    {
      this.field_90 = "Ship";
      return;
    }
    if ((paramclass_797 instanceof class_743))
    {
      this.field_90 = "Shop";
      return;
    }
    if ((paramclass_797 instanceof class_782))
    {
      this.field_90 = "Death Star";
      return;
    }
    if ((paramclass_797 instanceof class_705))
    {
      this.field_90 = "Asteroid";
      return;
    }
    if ((paramclass_797 instanceof class_750))
    {
      this.field_90 = "Player";
      return;
    }
    this.field_90 = "Other";
  }
  
  public final void a2() {}
  
  public final void b()
  {
    this.jdField_field_89_of_type_Boolean = (class_177.a61(this.jdField_field_89_of_type_Class_177) == this.jdField_field_89_of_type_Class_797);
    class_371 localclass_371 = (class_371)a24();
    class_177.b9().set(this.jdField_field_89_of_type_Class_797.getWorldTransformClient().origin);
    if (localclass_371.a6() != null) {
      class_177.b9().sub(localclass_371.a6().getWorldTransform().origin);
    } else {
      class_177.b9().sub(class_969.a1().a83());
    }
    float f = class_177.b9().length();
    String str2 = this.jdField_field_89_of_type_Class_797.toNiceString();
    String str1 = (int)f + "m";
    class_177.a62(this.jdField_field_89_of_type_Class_177).field_90.set(0, this.field_90);
    class_177.a62(this.jdField_field_89_of_type_Class_177).field_90.set(1, str2);
    class_177.a62(this.jdField_field_89_of_type_Class_177).field_90.set(2, str1);
    class_177.a62(this.jdField_field_89_of_type_Class_177).a83().set(5.0F, 5.0F, 0.0F);
    class_883.a128(this.jdField_field_89_of_type_Class_797, class_177.a63(), this.jdField_field_89_of_type_Class_797 == class_177.b10(this.jdField_field_89_of_type_Class_177), (class_371)a24());
    class_177.a63().field_599 = 0.8F;
    class_177.a64(this.jdField_field_89_of_type_Class_177).a147().c6(class_177.a63());
    if (this.jdField_field_89_of_type_Boolean) {
      class_177.b11(this.jdField_field_89_of_type_Class_177).a_2(5);
    } else {
      class_177.b11(this.jdField_field_89_of_type_Class_177).a_2(4);
    }
    class_177.b11(this.jdField_field_89_of_type_Class_177).b();
  }
  
  public final float a3()
  {
    return 64.0F;
  }
  
  public final class_797 a60()
  {
    return this.jdField_field_89_of_type_Class_797;
  }
  
  public final float b1()
  {
    return 255.0F;
  }
  
  public final void c() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_847
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */