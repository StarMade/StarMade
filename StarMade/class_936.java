import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_936
  extends class_1363
{
  private class_1402 jdField_field_89_of_type_Class_1402;
  private class_930 jdField_field_89_of_type_Class_930;
  private String[] jdField_field_89_of_type_ArrayOfJavaLangString;
  private String jdField_field_90_of_type_JavaLangString;
  private class_972 jdField_field_89_of_type_Class_972;
  private boolean jdField_field_89_of_type_Boolean;
  private boolean jdField_field_90_of_type_Boolean = true;
  private final Vector3f jdField_field_92_of_type_JavaxVecmathVector3f = new Vector3f();
  private boolean jdField_field_92_of_type_Boolean;
  private float jdField_field_89_of_type_Float;
  private boolean jdField_field_93_of_type_Boolean;
  private final Vector3f jdField_field_93_of_type_JavaxVecmathVector3f;
  
  public class_936(ClientState paramClientState, String paramString, class_972 paramclass_972)
  {
    super(paramClientState);
    new Vector3f();
    this.jdField_field_92_of_type_Boolean = true;
    this.jdField_field_89_of_type_Float = -1.0F;
    this.jdField_field_93_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 0.6F, 0.0F);
    a17(paramString);
    this.jdField_field_89_of_type_Class_972 = paramclass_972;
  }
  
  public final void a2() {}
  
  protected final void d() {}
  
  public final void e()
  {
    this.jdField_field_93_of_type_Boolean = true;
    b();
    this.jdField_field_93_of_type_Boolean = false;
  }
  
  public final void b()
  {
    if (this.jdField_field_92_of_type_Boolean) {
      c();
    }
    if ((!field_100) && (this.jdField_field_89_of_type_Class_972 == null)) {
      throw new AssertionError();
    }
    if ((!this.jdField_field_90_of_type_Boolean) || (this.jdField_field_89_of_type_Class_972.a_()) || (this.jdField_field_93_of_type_Boolean))
    {
      GlUtil.d1();
      if (this.jdField_field_90_of_type_Boolean) {
        a161(Mouse.getX(), class_933.a() - Mouse.getY() + 20, 0.0F);
      }
      this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776 = this.jdField_field_93_of_type_JavaxVecmathVector3f.field_615;
      this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777 = this.jdField_field_93_of_type_JavaxVecmathVector3f.field_616;
      this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778 = this.jdField_field_93_of_type_JavaxVecmathVector3f.field_617;
      r();
      f();
      float f1 = this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779;
      float f2 = this.jdField_field_89_of_type_Class_1402.a63().field_599;
      if (this.jdField_field_89_of_type_Float != -1.0F)
      {
        this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 *= this.jdField_field_89_of_type_Float;
        this.jdField_field_89_of_type_Class_1402.a63().field_599 *= this.jdField_field_89_of_type_Float;
      }
      this.jdField_field_92_of_type_JavaxVecmathVector3f.set(class_969.field_1259.m30, class_969.field_1259.m31, class_969.field_1259.m32);
      this.jdField_field_89_of_type_Class_930.a83().field_615 = 4.0F;
      this.jdField_field_89_of_type_Class_930.a83().field_616 = 3.0F;
      this.jdField_field_89_of_type_Class_1402.b();
      this.jdField_field_89_of_type_Class_930.b();
      Iterator localIterator = this.field_89.iterator();
      while (localIterator.hasNext()) {
        ((class_984)localIterator.next()).b();
      }
      GlUtil.c2();
      this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = f1;
      this.jdField_field_89_of_type_Class_1402.a63().field_599 = f2;
    }
  }
  
  public final float a3()
  {
    return 0.0F;
  }
  
  public final float b1()
  {
    return 0.0F;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930 = new class_930(200, 200, class_29.c(), a24());
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 10.0F, 10.0F, new Vector4f(0.0F, 0.1F, 0.0F, 0.6F));
    this.jdField_field_89_of_type_Class_1402.field_92 = 5.0F;
    this.jdField_field_92_of_type_Boolean = false;
  }
  
  public final void a17(String paramString)
  {
    if (!paramString.equals(this.jdField_field_90_of_type_JavaLangString))
    {
      this.jdField_field_89_of_type_ArrayOfJavaLangString = paramString.split("\n");
      this.jdField_field_90_of_type_JavaLangString = paramString;
      this.jdField_field_89_of_type_Boolean = true;
    }
  }
  
  private void f()
  {
    if (this.jdField_field_89_of_type_Boolean)
    {
      this.jdField_field_89_of_type_Class_930.field_90.clear();
      int i = 0;
      for (int j = 0; j < this.jdField_field_89_of_type_ArrayOfJavaLangString.length; j++)
      {
        this.jdField_field_89_of_type_Class_930.field_90.add(this.jdField_field_89_of_type_ArrayOfJavaLangString[j]);
        i = Math.max(i, this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getWidth(this.jdField_field_89_of_type_ArrayOfJavaLangString[j]));
      }
      this.jdField_field_89_of_type_Class_1402.c4(i + 8);
      this.jdField_field_89_of_type_Class_1402.b13(this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getLineHeight() * this.jdField_field_89_of_type_ArrayOfJavaLangString.length + 6);
      this.jdField_field_89_of_type_Boolean = false;
    }
  }
  
  public final void a29(boolean paramBoolean)
  {
    this.jdField_field_90_of_type_Boolean = paramBoolean;
  }
  
  public final void a(float paramFloat)
  {
    this.jdField_field_89_of_type_Float = paramFloat;
  }
  
  public final Vector3f b9()
  {
    return this.jdField_field_93_of_type_JavaxVecmathVector3f;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_936
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */