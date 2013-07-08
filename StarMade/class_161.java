import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_161
  extends class_1363
{
  private class_185[] jdField_field_89_of_type_ArrayOfClass_185 = new class_185[256];
  private class_930[] jdField_field_89_of_type_ArrayOfClass_930 = new class_930[256];
  private class_972 jdField_field_89_of_type_Class_972;
  private class_185 jdField_field_89_of_type_Class_185;
  private class_930 jdField_field_89_of_type_Class_930;
  private class_639 jdField_field_89_of_type_Class_639;
  
  public class_161(class_371 paramclass_371)
  {
    super(paramclass_371);
    this.jdField_field_89_of_type_Class_930 = a53(class_29.e(), paramclass_371);
    this.jdField_field_89_of_type_Class_185 = new class_185(paramclass_371, false, this);
    this.jdField_field_89_of_type_Class_185.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_185.b28(1.0F, 1.0F, 1.0F);
    for (int i = 0; i < this.jdField_field_89_of_type_ArrayOfClass_185.length; i++)
    {
      this.jdField_field_89_of_type_ArrayOfClass_930[i] = a53(class_29.e(), paramclass_371);
      this.jdField_field_89_of_type_ArrayOfClass_185[i] = new class_185(paramclass_371, true, this);
      this.jdField_field_89_of_type_ArrayOfClass_185[i].a9(this.jdField_field_89_of_type_ArrayOfClass_930[i]);
      this.jdField_field_89_of_type_ArrayOfClass_185[i].b28(1.0F, 1.0F, 1.0F);
    }
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("build-icons-00-16x16-gui-"), paramclass_371);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    
    if (((class_371)a24()).a14().field_4.field_4.field_4.a51().a45().field_4 == null) {
      return;
    }
    r();
    for (int i = this.jdField_field_89_of_type_Class_639.a3(); i < this.jdField_field_89_of_type_Class_639.b5(); i++)
    {
      if ((!jdField_field_89_of_type_Boolean) && (i < this.jdField_field_89_of_type_Class_639.a3())) {
        throw new AssertionError();
      }
      short s = 0;
      int k = 511;
      int m = 0;
      if (!this.jdField_field_89_of_type_Class_639.a18(i))
      {
        s = this.jdField_field_89_of_type_Class_639.a45(i);
        m = this.jdField_field_89_of_type_Class_639.a41(i);
        k = ElementKeyMap.getInfo(s).getBuildIconNum();
      }
      int n = (i - this.jdField_field_89_of_type_Class_639.a3()) / 7;
      int i1 = (i - this.jdField_field_89_of_type_Class_639.a3()) % 7;
      l();
      GlUtil.d1();
      this.jdField_field_89_of_type_ArrayOfClass_185[i].f4(i);
      this.jdField_field_89_of_type_ArrayOfClass_185[i].a74(s);
      this.jdField_field_89_of_type_ArrayOfClass_185[i].a72(m);
      if ((j = this.jdField_field_89_of_type_ArrayOfClass_185[i].a68(false)) > 0)
      {
        this.jdField_field_89_of_type_ArrayOfClass_930[i].field_90.set(0, String.valueOf(j));
      }
      else
      {
        k = 511;
        this.jdField_field_89_of_type_ArrayOfClass_930[i].field_90.set(0, "");
      }
      this.jdField_field_89_of_type_ArrayOfClass_185[i].b21(false);
      this.jdField_field_89_of_type_ArrayOfClass_185[i].a83().field_616 = (3.0F + n * 72.0F);
      this.jdField_field_89_of_type_ArrayOfClass_185[i].a83().field_615 = (3.0F + i1 * 72.0F);
      int j = k / 256;
      this.jdField_field_89_of_type_ArrayOfClass_185[i].e4(j);
      k = (short)(k % 256);
      this.jdField_field_89_of_type_ArrayOfClass_185[i].a147().b13(k);
      this.jdField_field_89_of_type_ArrayOfClass_185[i].b();
      GlUtil.c2();
    }
    this.jdField_field_89_of_type_ArrayOfClass_185[0].a147().b13(0);
    GlUtil.c2();
  }
  
  public final void a48(class_185 paramclass_185)
  {
    class_1363.i();
    this.jdField_field_89_of_type_Class_185.a161(Mouse.getX() - paramclass_185.a57(), class_933.a() - Mouse.getY() - paramclass_185.b12(), 0.0F);
    int i;
    int j = (i = ElementKeyMap.getInfo(paramclass_185.a70()).getBuildIconNum()) / 256;
    this.jdField_field_89_of_type_Class_185.e4(j);
    i %= 256;
    this.jdField_field_89_of_type_Class_185.a_2(i);
    this.jdField_field_89_of_type_Class_930.field_90.set(0, String.valueOf(paramclass_185.a68(true)));
    this.jdField_field_89_of_type_Class_185.b();
    class_1363.h1();
  }
  
  public final void e()
  {
    
    for (int i = 0; i < this.jdField_field_89_of_type_ArrayOfClass_185.length; i++) {
      this.jdField_field_89_of_type_ArrayOfClass_185[i].e();
    }
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return ((class_1363)a154()).a3();
  }
  
  public final class_639 a52()
  {
    return this.jdField_field_89_of_type_Class_639;
  }
  
  public final float b1()
  {
    return ((class_1363)a154()).b1();
  }
  
  private static class_930 a53(UnicodeFont paramUnicodeFont, ClientState paramClientState)
  {
    (paramUnicodeFont = new class_930(32, 32, paramUnicodeFont, paramClientState)).a135(Color.white);
    paramUnicodeFont.field_90 = new ArrayList();
    paramUnicodeFont.field_90.add("undefined");
    paramUnicodeFont.a161(2.0F, 2.0F, 0.0F);
    return paramUnicodeFont;
  }
  
  public final boolean a_()
  {
    return (super.a_()) && ((a154() == null) || (((class_1363)a154()).a_()));
  }
  
  public final void c()
  {
    for (int i = 0; i < this.jdField_field_89_of_type_ArrayOfClass_185.length; i++)
    {
      this.jdField_field_89_of_type_ArrayOfClass_185[i].c();
      this.jdField_field_89_of_type_ArrayOfClass_930[i].c();
    }
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_89_of_type_Class_185.c();
    this.jdField_field_89_of_type_Class_930.c();
  }
  
  public final void a54(class_639 paramclass_639)
  {
    this.jdField_field_89_of_type_Class_639 = paramclass_639;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_161
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */