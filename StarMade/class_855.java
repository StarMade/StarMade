import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_855
  extends class_959
{
  private class_843 jdField_field_89_of_type_Class_843;
  private class_930 jdField_field_89_of_type_Class_930;
  private boolean jdField_field_89_of_type_Boolean;
  private boolean field_90;
  private class_1402 jdField_field_89_of_type_Class_1402;
  
  public class_855(ClientState paramClientState, String paramString, class_843 paramclass_843, boolean paramBoolean)
  {
    super(paramClientState);
    this.field_90 = paramBoolean;
    this.field_89 = paramString;
    this.jdField_field_89_of_type_Class_843 = paramclass_843;
    this.jdField_field_89_of_type_Class_930 = new class_930(315, 30, class_29.h(), paramClientState);
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add(paramString);
    this.field_89 = this;
    this.jdField_field_89_of_type_Class_930.a141(paramclass_843);
    this.jdField_field_89_of_type_Class_843.a141(paramclass_843);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    if (this.field_90) {
      this.jdField_field_89_of_type_Class_1402.b();
    }
    this.jdField_field_89_of_type_Class_930.b();
    this.jdField_field_89_of_type_Class_843.a83().field_615 = this.jdField_field_89_of_type_Class_930.b1();
    this.jdField_field_89_of_type_Class_843.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_843.a3() + 5.0F;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_930.b1() + this.jdField_field_89_of_type_Class_843.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Class_843.c();
    this.jdField_field_89_of_type_Class_930.a83().field_616 += 8.0F;
    this.jdField_field_89_of_type_Class_843.a83().field_616 += 8.0F;
    if (this.field_90) {
      this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), b1(), a3(), new Vector4f(0.06F, 0.06F, 0.06F, 0.3F));
    }
    this.jdField_field_89_of_type_Class_930.field_96 = true;
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_855
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */