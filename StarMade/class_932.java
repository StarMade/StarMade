import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.newdawn.slick.UnicodeFont;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_932
  extends class_959
{
  private class_922 jdField_field_89_of_type_Class_922;
  private class_930 jdField_field_89_of_type_Class_930;
  private boolean jdField_field_89_of_type_Boolean;
  private boolean field_90;
  private class_1402 jdField_field_89_of_type_Class_1402;
  private boolean field_92;
  
  public class_932(ClientState paramClientState, int paramInt, String paramString, class_922 paramclass_922, boolean paramBoolean)
  {
    this(paramClientState, paramInt, 30, class_29.h(), paramString, paramclass_922, paramBoolean, false);
  }
  
  public class_932(ClientState paramClientState, int paramInt1, int paramInt2, UnicodeFont paramUnicodeFont, String paramString, class_922 paramclass_922, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramClientState);
    this.field_89 = paramString;
    this.field_92 = paramBoolean2;
    this.jdField_field_89_of_type_Class_922 = paramclass_922;
    this.jdField_field_89_of_type_Class_930 = new class_930(paramInt1, paramInt2, paramUnicodeFont, paramClientState);
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add(paramString);
    this.field_90 = paramBoolean1;
    this.field_89 = this;
  }
  
  public class_932(ClientState paramClientState, String paramString, class_922 paramclass_922, boolean paramBoolean)
  {
    this(paramClientState, 280, paramString, paramclass_922, paramBoolean);
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
    if ((this.jdField_field_89_of_type_Class_922 instanceof class_963)) {
      this.jdField_field_89_of_type_Class_922.a83().field_615 = (512.0F - this.jdField_field_89_of_type_Class_930.b1() / 2.0F);
    } else if (this.field_92) {
      this.jdField_field_89_of_type_Class_922.a83().field_616 = (this.jdField_field_89_of_type_Class_930.a3() + 5.0F);
    } else {
      this.jdField_field_89_of_type_Class_922.a83().field_615 = this.jdField_field_89_of_type_Class_930.b1();
    }
    this.jdField_field_89_of_type_Class_922.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    if (this.field_92) {
      return this.jdField_field_89_of_type_Class_930.a3() + this.jdField_field_89_of_type_Class_922.a3() + 3.0F;
    }
    return this.jdField_field_89_of_type_Class_922.a3() + 5.0F;
  }
  
  public final float b1()
  {
    if (this.field_92) {
      return Math.max(this.jdField_field_89_of_type_Class_922.b1(), this.jdField_field_89_of_type_Class_930.b1());
    }
    return this.jdField_field_89_of_type_Class_930.b1() + this.jdField_field_89_of_type_Class_922.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Class_922.c();
    this.jdField_field_89_of_type_Class_930.a83().field_616 += 8.0F;
    if (this.field_90) {
      if ((this.jdField_field_89_of_type_Class_922 instanceof class_963)) {
        this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), 486.0F, a3(), new Vector4f(0.068F, 0.068F, 0.068F, 0.3F));
      } else {
        this.jdField_field_89_of_type_Class_1402 = new class_1402(a24(), b1(), a3(), new Vector4f(0.068F, 0.068F, 0.068F, 0.3F));
      }
    }
    this.jdField_field_89_of_type_Boolean = true;
  }
  
  public final void a12(class_941 paramclass_941)
  {
    this.jdField_field_89_of_type_Class_922.a12(paramclass_941);
    super.a12(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_932
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */