import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.newdawn.slick.Color;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_913
  extends class_43
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_194 jdField_field_89_of_type_Class_194;
  private class_194 jdField_field_90_of_type_Class_194;
  private class_194 jdField_field_92_of_type_Class_194;
  private static class_1416 jdField_field_89_of_type_Class_1416;
  private boolean jdField_field_89_of_type_Boolean;
  private class_482 jdField_field_89_of_type_Class_482;
  private class_194 jdField_field_93_of_type_Class_194;
  private String jdField_field_90_of_type_JavaLangString;
  private float jdField_field_89_of_type_Float = 0.0F;
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 jdField_field_90_of_type_Class_930;
  private class_930 jdField_field_92_of_type_Class_930;
  private Vector4f jdField_field_89_of_type_JavaxVecmathVector4f;
  private class_930 jdField_field_93_of_type_Class_930;
  private class_879 jdField_field_89_of_type_Class_879;
  
  public class_913(ClientState paramClientState, class_482 paramclass_482, String paramString)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_482 = paramclass_482;
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("info-panel-gui-"), paramClientState);
    this.jdField_field_90_of_type_JavaLangString = paramString;
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
    this.jdField_field_90_of_type_Class_194.a147().c6(this.jdField_field_89_of_type_JavaxVecmathVector4f);
    this.jdField_field_93_of_type_Class_194.a147().c6(this.jdField_field_89_of_type_JavaxVecmathVector4f);
    this.jdField_field_92_of_type_Class_194.a147().c6(this.jdField_field_89_of_type_JavaxVecmathVector4f);
    this.jdField_field_89_of_type_Class_972.a147().c6(this.jdField_field_89_of_type_JavaxVecmathVector4f);
    this.jdField_field_89_of_type_Class_930.a135(new Color(this.jdField_field_89_of_type_JavaxVecmathVector4f.field_596, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_597, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_598, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_599));
    this.jdField_field_89_of_type_Class_972.b();
    this.jdField_field_89_of_type_Class_194.a147().c6(null);
    this.jdField_field_90_of_type_Class_194.a147().c6(null);
    this.jdField_field_93_of_type_Class_194.a147().c6(null);
    this.jdField_field_92_of_type_Class_194.a147().c6(null);
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
    this.jdField_field_93_of_type_Class_930 = new class_930(10, 10, class_29.k(), a24());
    this.jdField_field_89_of_type_Class_879 = new class_879(a24());
    this.jdField_field_89_of_type_Class_930 = new class_930(200, 200, class_29.n(), a24());
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add(this.jdField_field_90_of_type_JavaLangString);
    this.jdField_field_90_of_type_Class_930 = new class_930(200, 30, class_29.d(), a24());
    this.jdField_field_90_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_90_of_type_Class_930.field_90.add("Tutorial");
    this.jdField_field_92_of_type_Class_930 = new class_930(200, 30, class_29.k(), a24());
    this.jdField_field_92_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_92_of_type_Class_930.field_90.add("(click to drag)");
    this.jdField_field_89_of_type_Class_194 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_678, "NEXT", this.jdField_field_89_of_type_Class_482);
    this.jdField_field_90_of_type_Class_194 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_679, "BACK", this.jdField_field_89_of_type_Class_482);
    this.jdField_field_93_of_type_Class_194 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_680, "SKIP", this.jdField_field_89_of_type_Class_482);
    this.jdField_field_92_of_type_Class_194 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_681, "END", this.jdField_field_89_of_type_Class_482);
    if (jdField_field_89_of_type_Class_1416 == null)
    {
      jdField_field_89_of_type_Class_1416 = new class_1416(a24(), this.jdField_field_89_of_type_Class_972);
      this.jdField_field_89_of_type_Class_972.h3(48);
      this.jdField_field_89_of_type_Class_972.a83().field_616 -= 100.0F;
    }
    else
    {
      this.jdField_field_89_of_type_Class_972.a83().set(jdField_field_89_of_type_Class_1416.field_89.a83());
      jdField_field_89_of_type_Class_1416 = new class_1416(a24(), this.jdField_field_89_of_type_Class_972);
    }
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_92_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_194);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_93_of_type_Class_194);
    this.jdField_field_89_of_type_Class_972.a9(jdField_field_89_of_type_Class_1416);
    if ((this.jdField_field_89_of_type_Class_482.a80() instanceof class_399))
    {
      this.jdField_field_93_of_type_Class_930.a136("Show tutorial next time");
      this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_93_of_type_Class_930);
      this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_879);
      this.jdField_field_93_of_type_Class_930.a161(133.0F, 165.0F, 0.0F);
      this.jdField_field_89_of_type_Class_879.a161(98.0F, 156.0F, 0.0F);
    }
    else
    {
      this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_194);
      this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_92_of_type_Class_194);
    }
    this.jdField_field_90_of_type_Class_930.a161(14.0F, 10.0F, 0.0F);
    this.jdField_field_92_of_type_Class_930.a161(280.0F, 15.0F, 0.0F);
    this.jdField_field_89_of_type_Class_930.a161(30.0F, 50.0F, 0.0F);
    this.jdField_field_89_of_type_Class_194.b28(0.5F, 0.5F, 0.5F);
    this.jdField_field_90_of_type_Class_194.b28(0.5F, 0.5F, 0.5F);
    this.jdField_field_93_of_type_Class_194.b28(0.5F, 0.5F, 0.5F);
    this.jdField_field_92_of_type_Class_194.b28(0.5F, 0.5F, 0.5F);
    this.jdField_field_89_of_type_Class_194.a161(330.0F, 158.0F, 0.0F);
    this.jdField_field_90_of_type_Class_194.a161(250.0F, 158.0F, 0.0F);
    this.jdField_field_93_of_type_Class_194.a161(110.0F, 158.0F, 0.0F);
    this.jdField_field_92_of_type_Class_194.a161(30.0F, 158.0F, 0.0F);
    this.jdField_field_93_of_type_Class_194.b21(true);
    if (((this.jdField_field_89_of_type_Class_482.a80() instanceof class_401)) || (!(this.jdField_field_89_of_type_Class_482.a80() instanceof class_403))) {
      this.jdField_field_93_of_type_Class_194.b21(false);
    }
    this.jdField_field_89_of_type_Boolean = true;
  }
  
  public final void a(float paramFloat)
  {
    this.jdField_field_89_of_type_Float = paramFloat;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_913
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */