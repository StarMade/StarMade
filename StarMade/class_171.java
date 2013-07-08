import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

final class class_171
  extends class_959
{
  private class_773 jdField_field_89_of_type_Class_773;
  private class_758 jdField_field_89_of_type_Class_758;
  private class_1402 jdField_field_89_of_type_Class_1402;
  private class_930 jdField_field_89_of_type_Class_930;
  private class_934 jdField_field_89_of_type_Class_934;
  private class_930 field_90;
  private Vector4f jdField_field_89_of_type_JavaxVecmathVector4f;
  
  public class_171(class_173 paramclass_173, ClientState paramClientState, class_773 paramclass_773, class_758 paramclass_758, int paramInt)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_773 = paramclass_773;
    this.jdField_field_89_of_type_Class_758 = paramclass_758;
    this.jdField_field_89_of_type_JavaxVecmathVector4f = (paramInt % 2 == 0 ? new Vector4f(0.1F, 0.1F, 0.1F, 1.0F) : new Vector4f(0.3F, 0.3F, 0.3F, 1.0F));
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 400.0F, 20.0F, this.jdField_field_89_of_type_JavaxVecmathVector4f);
    this.field_89 = this.jdField_field_89_of_type_Class_1402;
    this.field_90 = this.jdField_field_89_of_type_Class_1402;
    this.field_89 = new Object[] { paramclass_773, paramclass_758 };
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930 = new class_930(200, 20, a24());
    this.jdField_field_89_of_type_Class_930.a136(this.jdField_field_89_of_type_Class_758.field_136);
    Object localObject = new class_169(this);
    this.field_90 = new class_930(200, 20, a24());
    this.field_90.field_90 = new ArrayList();
    this.field_90.field_90.add(localObject);
    (localObject = new Vector4f(this.jdField_field_89_of_type_JavaxVecmathVector4f)).scale(1.1F);
    this.jdField_field_89_of_type_Class_934 = new class_934(a24(), 75, 20, (Vector4f)localObject, new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.l(), "Options", class_173.a58(this.jdField_field_89_of_type_Class_173));
    this.jdField_field_89_of_type_Class_934.b17(15, 1);
    this.jdField_field_89_of_type_Class_1402.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_1402.a9(this.field_90);
    if (class_173.a59(this.jdField_field_89_of_type_Class_173)) {
      this.jdField_field_89_of_type_Class_1402.a9(this.jdField_field_89_of_type_Class_934);
    }
    this.field_90.a83().field_615 = 200.0F;
    this.jdField_field_89_of_type_Class_934.a83().field_615 = 300.0F;
    super.c();
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Class_758.d7(this.jdField_field_89_of_type_Class_773)) {
      this.field_90.a137(0.7F, 0.7F);
    } else if ((this.jdField_field_89_of_type_Class_758.b24(this.jdField_field_89_of_type_Class_773)) || (this.jdField_field_89_of_type_Class_758.c15(this.jdField_field_89_of_type_Class_773))) {
      this.field_90.a137(0.9F, 0.9F);
    }
    super.b();
    this.field_90.a137(1.0F, 1.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_171
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */