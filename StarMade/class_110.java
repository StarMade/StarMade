import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_110
  extends class_1363
{
  private class_972 jdField_field_89_of_type_Class_972;
  private float jdField_field_90_of_type_Float = 5.0F;
  private float field_92;
  private float field_93;
  private class_930 jdField_field_89_of_type_Class_930;
  private boolean jdField_field_89_of_type_Boolean = true;
  private float field_94 = 0.0F;
  private String jdField_field_90_of_type_JavaLangString;
  private Color jdField_field_89_of_type_OrgNewdawnSlickColor = new Color(1, 1, 1, 1);
  public float field_89;
  
  public class_110(ClientState paramClientState, String paramString, Color paramColor)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Float = 0.0F;
    this.jdField_field_89_of_type_Class_930 = new class_930(300, 300, paramClientState);
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_90_of_type_JavaLangString = paramString;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776 = paramColor.field_1776;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777 = paramColor.field_1777;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778 = paramColor.field_1778;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = paramColor.field_1779;
    paramString = (paramClientState = paramString.split("\n")).length;
    for (paramColor = 0; paramColor < paramString; paramColor++)
    {
      Object localObject = paramClientState[paramColor];
      this.jdField_field_89_of_type_Class_930.field_90.add(localObject);
    }
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    if (this.field_93 < 0.0F) {
      return;
    }
    a83().field_615 = (class_933.b() - this.field_89.field_615 * this.jdField_field_89_of_type_Class_972.b1() - 96.0F);
    a83().field_616 = this.field_94;
    if (!j1()) {
      return;
    }
    float f = this.jdField_field_90_of_type_Float - this.field_92;
    this.jdField_field_89_of_type_Class_972.a147().a63().set(this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776, this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777, this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778, 1.0F);
    if (f < 1.0F)
    {
      this.jdField_field_89_of_type_Class_972.a147().a63().set(this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776, this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777, this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778, f);
      this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = f;
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
  
  public final boolean a4()
  {
    return this.field_92 < this.jdField_field_90_of_type_Float;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("std-message-gui-"), a24());
    this.jdField_field_89_of_type_Class_930.a161(30.0F, 30.0F, 0.0F);
    this.jdField_field_89_of_type_Class_930.a135(Color.white);
    this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont = class_29.b2();
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Class_972.a147().c6(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F));
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_930);
    this.field_94 = (-1.0F * (this.jdField_field_89_of_type_Class_972.a3() * this.field_89.field_616 + 5.0F));
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  public final void e()
  {
    String[] arrayOfString = this.jdField_field_90_of_type_JavaLangString.split("\n");
    this.jdField_field_89_of_type_Class_930.field_90.clear();
    for (String str : arrayOfString) {
      this.jdField_field_89_of_type_Class_930.field_90.add(str);
    }
    this.field_92 = 0.0F;
  }
  
  public final void a17(String paramString)
  {
    this.jdField_field_90_of_type_JavaLangString = paramString;
  }
  
  public final void f()
  {
    this.field_93 = 0.0F;
    this.field_92 = 0.0F;
  }
  
  public final void g()
  {
    if (this.field_92 < this.jdField_field_90_of_type_Float - 1.0F) {
      this.field_92 = (this.jdField_field_90_of_type_Float - 1.0F);
    }
  }
  
  public final void a12(class_941 paramclass_941)
  {
    if (this.jdField_field_89_of_type_Boolean) {
      return;
    }
    if (this.field_93 < 0.0F)
    {
      this.field_93 += paramclass_941.a();
      return;
    }
    this.field_92 += paramclass_941.a();
    float f1 = this.jdField_field_89_of_type_Float * (this.jdField_field_89_of_type_Class_972.a3() * this.field_89.field_616 + 5.0F);
    float f2 = Math.min(1.0F, Math.max(0.01F, Math.abs(this.field_94 - f1)) / (this.jdField_field_89_of_type_Class_972.a3() * this.field_89.field_616));
    if (this.field_94 > f1)
    {
      this.field_94 -= paramclass_941.a() * 1000.0F * f2;
      if (this.field_94 <= f1) {
        this.field_94 = f1;
      }
    }
    else if (this.field_94 < f1)
    {
      this.field_94 += paramclass_941.a() * 1000.0F * f2;
      if (this.field_94 >= f1) {
        this.field_94 = f1;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_110
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */