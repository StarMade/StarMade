import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.newdawn.slick.Color;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_257
  extends class_43
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_194 jdField_field_89_of_type_Class_194;
  private class_194 jdField_field_90_of_type_Class_194;
  private class_968 jdField_field_89_of_type_Class_968;
  private static class_1416 jdField_field_89_of_type_Class_1416;
  private boolean jdField_field_89_of_type_Boolean;
  private class_42 jdField_field_89_of_type_Class_42;
  private String jdField_field_90_of_type_JavaLangString;
  private float jdField_field_89_of_type_Float = 0.0F;
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 jdField_field_90_of_type_Class_930;
  private class_930 jdField_field_92_of_type_Class_930;
  private Vector4f jdField_field_89_of_type_JavaxVecmathVector4f;
  private String jdField_field_92_of_type_JavaLangString;
  
  public class_257(ClientState paramClientState, class_42 paramclass_42, String paramString1, String paramString2)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_42 = paramclass_42;
    this.jdField_field_92_of_type_JavaLangString = paramString2;
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("info-panel-gui-"), paramClientState);
    this.jdField_field_89_of_type_Class_968 = new class_968(363.0F, 110.0F, paramClientState);
    this.jdField_field_90_of_type_JavaLangString = paramString1;
    this.jdField_field_89_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F - this.jdField_field_89_of_type_Float);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_JavaxVecmathVector4f.set(1.0F, 1.0F, 1.0F, 1.0F - this.jdField_field_89_of_type_Float);
    this.jdField_field_89_of_type_Class_194.a147().c6(this.jdField_field_89_of_type_JavaxVecmathVector4f);
    this.jdField_field_89_of_type_Class_972.a147().c6(this.jdField_field_89_of_type_JavaxVecmathVector4f);
    this.jdField_field_89_of_type_Class_930.a135(new Color(this.jdField_field_89_of_type_JavaxVecmathVector4f.field_596, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_597, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_598, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_599));
    this.jdField_field_89_of_type_Class_972.b();
    this.jdField_field_89_of_type_Class_194.a147().c6(null);
    this.jdField_field_89_of_type_Class_972.a147().c6(null);
    this.jdField_field_89_of_type_Class_930.a135(new Color(1, 1, 1, 1));
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930 = new class_930(220, 100, class_29.n(), a24());
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add(this.jdField_field_90_of_type_JavaLangString);
    this.jdField_field_90_of_type_Class_930 = new class_930(200, 30, class_29.d(), a24());
    this.jdField_field_90_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_90_of_type_Class_930.field_90.add(this.jdField_field_92_of_type_JavaLangString);
    this.jdField_field_92_of_type_Class_930 = new class_930(200, 30, class_29.k(), a24());
    this.jdField_field_92_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_92_of_type_Class_930.field_90.add("(click to drag)");
    this.jdField_field_89_of_type_Class_194 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_663, "OK", this.jdField_field_89_of_type_Class_42);
    this.jdField_field_90_of_type_Class_194 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_664, "CANCEL", this.jdField_field_89_of_type_Class_42);
    if (jdField_field_89_of_type_Class_1416 == null)
    {
      jdField_field_89_of_type_Class_1416 = new class_1416(a24(), this.jdField_field_89_of_type_Class_972);
      this.jdField_field_89_of_type_Class_972.h3(48);
    }
    else
    {
      this.jdField_field_89_of_type_Class_972.a83().set(jdField_field_89_of_type_Class_1416.field_89.a83());
      jdField_field_89_of_type_Class_1416 = new class_1416(a24(), this.jdField_field_89_of_type_Class_972);
    }
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_92_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_968);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_194);
    this.jdField_field_89_of_type_Class_972.a9(jdField_field_89_of_type_Class_1416);
    this.jdField_field_90_of_type_Class_930.a161(14.0F, 10.0F, 0.0F);
    this.jdField_field_92_of_type_Class_930.a161(280.0F, 15.0F, 0.0F);
    this.jdField_field_89_of_type_Class_968.a161(23.0F, 42.0F, 0.0F);
    this.jdField_field_89_of_type_Class_194.b28(0.5F, 0.5F, 0.5F);
    this.jdField_field_90_of_type_Class_194.b28(0.5F, 0.5F, 0.5F);
    this.jdField_field_89_of_type_Class_194.a161(330.0F, 158.0F, 0.0F);
    this.jdField_field_90_of_type_Class_194.a161(220.0F, 158.0F, 0.0F);
    this.jdField_field_89_of_type_Boolean = true;
  }
  
  public final void a(float paramFloat)
  {
    this.jdField_field_89_of_type_Float = paramFloat;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_257
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */