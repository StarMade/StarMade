import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_108
  extends class_1363
{
  private class_972 jdField_field_89_of_type_Class_972;
  private float jdField_field_89_of_type_Float;
  private float jdField_field_90_of_type_Float;
  private class_930 jdField_field_89_of_type_Class_930;
  private boolean jdField_field_89_of_type_Boolean = true;
  private class_1383 jdField_field_89_of_type_Class_1383 = new class_1383(30.0F);
  private String jdField_field_90_of_type_JavaLangString;
  private Color jdField_field_89_of_type_OrgNewdawnSlickColor = new Color(1, 1, 1, 1);
  private boolean jdField_field_90_of_type_Boolean = true;
  
  public class_108(ClientState paramClientState, String paramString, Color paramColor)
  {
    super(paramClientState);
    if (this.jdField_field_89_of_type_Class_972 == null)
    {
      this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("std-message-gui-"), paramClientState);
      this.jdField_field_89_of_type_Class_930 = new class_930(300, 300, paramClientState);
      paramClientState = null;
      this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    }
    Object localObject = null;
    this.jdField_field_90_of_type_JavaLangString = paramString;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776 = paramColor.field_1776;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777 = paramColor.field_1777;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778 = paramColor.field_1778;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = paramColor.field_1779;
    this.jdField_field_89_of_type_Class_930.field_90.clear();
    paramString = (paramClientState = paramString.split("\n")).length;
    for (paramColor = 0; paramColor < paramString; paramColor++)
    {
      localObject = paramClientState[paramColor];
      this.jdField_field_89_of_type_Class_930.field_90.add(localObject);
    }
  }
  
  public final void a2() {}
  
  public final void e()
  {
    this.jdField_field_90_of_type_Boolean = false;
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    if ((!this.jdField_field_90_of_type_Boolean) || (this.jdField_field_90_of_type_Float < 0.0F)) {
      return;
    }
    this.jdField_field_89_of_type_Class_972.a147().a63().set(this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776, this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777, this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778, 1.0F);
    if ((this.jdField_field_89_of_type_Float < 0.3F) && (this.jdField_field_89_of_type_Class_1383.a1() < 0.5F))
    {
      float f = Math.max(1.0F, 1.0F - this.jdField_field_89_of_type_Float * 5.0F);
      this.jdField_field_89_of_type_Class_972.a147().a63().set(f / 2.0F + this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776, f / 2.0F + this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777, f / 2.0F + this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778, 1.0F);
    }
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_972.b();
    GlUtil.c2();
    GL11.glDisable(3042);
    this.jdField_field_89_of_type_Class_972.a147().a63().set(1.0F, 1.0F, 1.0F, 1.0F);
    this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = 1.0F;
    this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776 = 1.0F;
    this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777 = 1.0F;
    this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778 = 1.0F;
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final String a16()
  {
    return this.jdField_field_90_of_type_JavaLangString;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930.a161(30.0F, 30.0F, 0.0F);
    this.jdField_field_89_of_type_Class_930.a135(Color.white);
    this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont = class_29.b2();
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Class_972.a147().c6(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  public final void f()
  {
    this.jdField_field_90_of_type_Boolean = true;
  }
  
  public final void g()
  {
    this.jdField_field_90_of_type_Float = 0.0F;
    this.jdField_field_89_of_type_Float = 0.0F;
  }
  
  public final void a12(class_941 paramclass_941)
  {
    if (this.jdField_field_90_of_type_Float < 0.0F)
    {
      this.jdField_field_90_of_type_Float += paramclass_941.a();
      return;
    }
    this.jdField_field_89_of_type_Float += paramclass_941.a();
    this.jdField_field_89_of_type_Class_1383.a(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_108
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */