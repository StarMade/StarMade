import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_928
  extends class_1363
  implements class_1412
{
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 jdField_field_90_of_type_Class_930;
  public class_951 field_89;
  private boolean jdField_field_89_of_type_Boolean = true;
  public String field_90;
  private float jdField_field_89_of_type_Float;
  private String field_92;
  private boolean jdField_field_90_of_type_Boolean = false;
  
  public class_928(ClientState paramClientState)
  {
    this(paramClientState, false);
  }
  
  public class_928(UnicodeFont paramUnicodeFont, ClientState paramClientState)
  {
    this(paramUnicodeFont, paramClientState, (byte)0);
  }
  
  public class_928(ClientState paramClientState, boolean paramBoolean)
  {
    super(paramClientState);
    this.jdField_field_90_of_type_JavaLangString = "";
    this.jdField_field_89_of_type_Class_930 = new class_930(256, 32, paramClientState);
    this.jdField_field_90_of_type_Class_930 = new class_930(256, 32, paramClientState);
    this.jdField_field_90_of_type_Boolean = paramBoolean;
  }
  
  private class_928(UnicodeFont paramUnicodeFont, ClientState paramClientState, byte paramByte)
  {
    super(paramClientState);
    this.jdField_field_90_of_type_JavaLangString = "";
    this.jdField_field_89_of_type_Class_930 = new class_930(300, 300, paramUnicodeFont, paramClientState);
    this.jdField_field_90_of_type_Class_930 = new class_930(300, 300, paramUnicodeFont, paramClientState);
    this.jdField_field_90_of_type_Boolean = false;
  }
  
  public final void a2() {}
  
  protected final void d() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    String str1 = this.jdField_field_89_of_type_Class_951.a4();
    str1 = this.jdField_field_90_of_type_JavaLangString + str1;
    int j;
    int i;
    if (this.jdField_field_89_of_type_Class_951.c5().length() > 0)
    {
      j = 0 + this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getHeight(this.jdField_field_90_of_type_JavaLangString + this.jdField_field_89_of_type_Class_951.d4() + this.jdField_field_89_of_type_Class_951.c5());
      int k = this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getWidth(this.jdField_field_90_of_type_JavaLangString + this.jdField_field_89_of_type_Class_951.d4());
      int m = this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getWidth(this.jdField_field_90_of_type_JavaLangString + this.jdField_field_89_of_type_Class_951.d4() + this.jdField_field_89_of_type_Class_951.c5());
      int n = j;
      m += 1;
      j = k - 1;
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glBegin(7);
      GL11.glVertex2i(j, 0);
      GL11.glVertex2i(j, n);
      GL11.glVertex2i(m, n);
      GL11.glVertex2i(m, 0);
      GL11.glEnd();
      GL11.glDisable(3042);
      this.jdField_field_90_of_type_Class_930.field_90.set(0, str1);
      this.jdField_field_90_of_type_Class_930.a135(Color.white);
      this.jdField_field_90_of_type_Class_930.b();
      i = (int)this.jdField_field_90_of_type_Class_930.a83().field_615;
      this.jdField_field_90_of_type_Class_930.a83().field_615 = k;
      this.jdField_field_90_of_type_Class_930.field_90.set(0, this.jdField_field_89_of_type_Class_951.c5());
      this.jdField_field_90_of_type_Class_930.a135(Color.yellow);
      this.jdField_field_90_of_type_Class_930.b();
      this.jdField_field_90_of_type_Class_930.a83().field_615 = i;
    }
    else
    {
      this.jdField_field_90_of_type_Class_930.field_90.set(0, i);
      this.jdField_field_90_of_type_Class_930.a135(Color.white);
      this.jdField_field_90_of_type_Class_930.b();
    }
    float f = this.jdField_field_89_of_type_Class_930.a83().field_615;
    if (!this.jdField_field_89_of_type_Class_951.b7().equals(this.field_92))
    {
      String str2 = (j = this.jdField_field_89_of_type_Class_951.b7().lastIndexOf("\n")) >= 0 ? this.jdField_field_89_of_type_Class_951.b7().substring(j + 1) : this.jdField_field_89_of_type_Class_951.b7();
      this.jdField_field_89_of_type_Class_930.a83().field_615 = (this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getWidth(this.jdField_field_90_of_type_JavaLangString + str2) - 2);
      this.jdField_field_89_of_type_Class_930.a83().field_616 = (this.jdField_field_89_of_type_Class_951.b6() * this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getLineHeight());
      this.field_92 = new String(this.jdField_field_89_of_type_Class_951.b7());
    }
    if (f != this.jdField_field_89_of_type_Class_930.a83().field_615)
    {
      this.jdField_field_89_of_type_Float = 0.0F;
      this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = 1.0F;
    }
    this.jdField_field_89_of_type_Class_930.b();
    if (this.field_96) {
      l();
    }
    GlUtil.c2();
  }
  
  public final void a12(class_941 paramclass_941)
  {
    this.jdField_field_89_of_type_Float += paramclass_941.a();
    if (this.jdField_field_89_of_type_Float > 0.3D)
    {
      if (this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 > 0.0F) {
        this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = 0.0F;
      } else {
        this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = 1.0F;
      }
      this.jdField_field_89_of_type_Float = 0.0F;
    }
  }
  
  public final float a3()
  {
    return this.jdField_field_90_of_type_Class_930.a3();
  }
  
  public final String b14()
  {
    return "textInput";
  }
  
  public final float b1()
  {
    return this.jdField_field_90_of_type_Class_930.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_Int = 1;
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList(1);
    this.jdField_field_89_of_type_Class_930.field_90.add("|");
    this.jdField_field_89_of_type_Class_930.c();
    a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_90_of_type_Class_930.jdField_field_89_of_type_Int = 1;
    this.jdField_field_90_of_type_Class_930.field_90 = new ArrayList(1);
    this.jdField_field_90_of_type_Class_930.field_90.add("");
    this.jdField_field_90_of_type_Class_930.c();
    a9(this.jdField_field_90_of_type_Class_930);
    if (this.jdField_field_90_of_type_Boolean)
    {
      this.field_96 = true;
      a141(this);
    }
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      paramclass_1363 = 0;
      paramclass_939 = 0;
      StringBuffer localStringBuffer = new StringBuffer(this.jdField_field_89_of_type_Class_951.a4());
      class_939 localclass_9391 = 0;
      class_939 localclass_9392 = this.jdField_field_90_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getLineHeight();
      while (paramclass_939 < this.field_90.field_616 - 3.0F)
      {
        paramclass_939 = localclass_9392 * localclass_9391;
        localclass_9391++;
      }
      localclass_9391 -= 2;
      paramclass_939 = Math.max(0, Math.min(this.jdField_field_89_of_type_Class_951.a28(), localclass_9391));
      int i = 0;
      for (localclass_9391 = 0; localclass_9391 < paramclass_939; localclass_9391++) {
        if ((j = localStringBuffer.indexOf("\n", i + 1)) >= 0) {
          i = j;
        }
      }
      if (i > 0) {
        i = Math.max(0, Math.min(this.jdField_field_89_of_type_Class_951.a4().length(), i + 1));
      } else {
        i = 0;
      }
      for (class_1363 localclass_1363 = 0; (paramclass_1363 < this.field_90.field_615 - 5.0F) && (localclass_1363 < localStringBuffer.length()) && (i + localclass_1363 < localStringBuffer.length()); localclass_1363++) {
        paramclass_1363 = this.jdField_field_89_of_type_Class_930.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getWidth(this.jdField_field_90_of_type_JavaLangString + localStringBuffer.substring(i, i + localclass_1363));
      }
      paramclass_1363 = localclass_1363;
      int j = i + Math.max(0, paramclass_1363);
      j = Math.max(0, Math.min(this.jdField_field_89_of_type_Class_951.a4().length(), j));
      this.jdField_field_89_of_type_Class_951.a13(j);
      this.jdField_field_89_of_type_Class_951.f();
      this.jdField_field_89_of_type_Class_951.d();
    }
  }
  
  public final boolean a4()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_928
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */