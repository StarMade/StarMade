import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_238
  extends class_1363
{
  private float jdField_field_90_of_type_Float = 5.0F;
  private float field_92;
  private float field_93;
  private class_930 jdField_field_89_of_type_Class_930;
  private boolean jdField_field_89_of_type_Boolean = true;
  private float field_94 = 0.0F;
  private Color jdField_field_89_of_type_OrgNewdawnSlickColor = new Color(1, 1, 1, 1);
  private String jdField_field_90_of_type_JavaLangString;
  public float field_89;
  private class_930 jdField_field_90_of_type_Class_930;
  
  public class_238(String paramString1, ClientState paramClientState, String paramString2, String paramString3, Color paramColor)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Float = 0.0F;
    this.jdField_field_90_of_type_JavaLangString = paramString1;
    this.jdField_field_89_of_type_Class_930 = new class_930(10, 40, class_29.f(), paramClientState);
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_90_of_type_Class_930 = new class_930(10, 40, class_29.c(), paramClientState);
    this.jdField_field_90_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776 = paramColor.field_1776;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777 = paramColor.field_1777;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778 = paramColor.field_1778;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = paramColor.field_1779;
    this.jdField_field_89_of_type_Class_930.field_90.add(paramString2);
    this.jdField_field_90_of_type_Class_930.field_90.add(paramString3);
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
    a83().field_615 = (class_933.b() / 2 - this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getWidth(this.jdField_field_89_of_type_Class_930.field_90.get(0).toString()) / 2);
    a83().field_616 = (this.field_94 + (int)(class_933.a() / 3.5F));
    if (!j1()) {
      return;
    }
    float f;
    if ((f = this.jdField_field_90_of_type_Float - this.field_92) < 1.0F)
    {
      this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = f;
      this.jdField_field_90_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = f;
    }
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_930.b();
    GlUtil.c2();
    a83().field_615 = (class_933.b() / 2 - this.jdField_field_90_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getWidth(this.jdField_field_90_of_type_Class_930.field_90.get(0).toString()) / 2);
    GlUtil.d1();
    r();
    GlUtil.c4(0.0F, this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getLineHeight() + 5, 0.0F);
    this.jdField_field_90_of_type_Class_930.b();
    GlUtil.c2();
    GL11.glDisable(3042);
    this.jdField_field_89_of_type_Class_930.a137(1.0F, 1.0F);
    this.jdField_field_90_of_type_Class_930.a137(1.0F, 1.0F);
  }
  
  public final boolean equals(Object paramObject)
  {
    return this.jdField_field_90_of_type_JavaLangString.equals(((class_238)paramObject).jdField_field_90_of_type_JavaLangString);
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_930.a3() + 5.0F + this.jdField_field_90_of_type_Class_930.a3();
  }
  
  public final String a16()
  {
    return this.jdField_field_90_of_type_JavaLangString;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_930.b1();
  }
  
  public final boolean a4()
  {
    return this.field_92 < this.jdField_field_90_of_type_Float;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930.a135(Color.white);
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Boolean = false;
    this.field_94 = (-1.0F * (a3() * this.field_89.field_616 + 5.0F));
  }
  
  public final void a_1()
  {
    this.field_92 = 0.0F;
  }
  
  public final void a17(String paramString)
  {
    this.jdField_field_89_of_type_Class_930.field_90.set(0, paramString);
  }
  
  public final void e()
  {
    this.field_93 = 0.0F;
    this.field_92 = 0.0F;
  }
  
  public final void f()
  {
    if (this.field_92 < this.jdField_field_90_of_type_Float - 1.0F) {
      this.field_92 = (this.jdField_field_90_of_type_Float - 1.0F);
    }
  }
  
  public final void a12(class_941 paramclass_941)
  {
    if (this.field_93 < 0.0F)
    {
      this.field_93 += paramclass_941.a();
      return;
    }
    this.field_92 += paramclass_941.a();
    float f1 = this.jdField_field_89_of_type_Float * (a3() * this.field_89.field_616 + 5.0F);
    float f2 = Math.min(1.0F, Math.max(0.01F, Math.abs(this.field_94 - f1)) / (a3() * this.field_89.field_616));
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
 * Qualified Name:     class_238
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */