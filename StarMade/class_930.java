import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.Color;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.UnicodeFont.DisplayList;
import org.schema.schine.graphicsengine.core.GLException;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_930
  extends class_1363
{
  private int jdField_field_93_of_type_Int;
  private int field_94;
  public UnicodeFont field_89;
  private boolean jdField_field_90_of_type_Boolean = true;
  public List field_90;
  public Color field_89;
  int jdField_field_89_of_type_Int = -1;
  private boolean jdField_field_92_of_type_Boolean = true;
  public boolean field_89;
  private boolean jdField_field_93_of_type_Boolean;
  private static UnicodeFont jdField_field_90_of_type_OrgNewdawnSlickUnicodeFont;
  private class_925 jdField_field_89_of_type_Class_925;
  private boolean field_100;
  private int field_95;
  private int field_96;
  int jdField_field_90_of_type_Int = -1;
  int jdField_field_92_of_type_Int = -1;
  private boolean field_216;
  
  public class_930(int paramInt1, int paramInt2, ClientState paramClientState)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_OrgNewdawnSlickColor = new Color(Color.white);
    this.jdField_field_93_of_type_Int = paramInt1;
    this.field_94 = paramInt2;
  }
  
  public class_930(int paramInt1, int paramInt2, UnicodeFont paramUnicodeFont, ClientState paramClientState)
  {
    this(paramInt1, paramInt2, paramClientState);
    this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont = paramUnicodeFont;
  }
  
  public class_930(UnicodeFont paramUnicodeFont, Color paramColor, ClientState paramClientState)
  {
    this(300, 300, paramUnicodeFont, paramClientState);
    a135(paramColor);
  }
  
  public final void e()
  {
    if ((!this.jdField_field_93_of_type_Boolean) || (!class_949.field_1206.b1())) {
      return;
    }
    this.field_100 = true;
    System.err.println("CACHING TEXTURE FBO");
    try
    {
      if (this.jdField_field_89_of_type_Class_925 == null)
      {
        class_930 localclass_930 = this;
        this.jdField_field_89_of_type_Class_925 = new class_925(localclass_930.field_96, localclass_930.field_95);
        localclass_930.jdField_field_89_of_type_Class_925.e();
      }
      if (!this.jdField_field_93_of_type_Boolean) {
        return;
      }
      GL11.glDisable(12288);
      GL11.glDisable(12289);
      GL11.glDisable(12290);
      GL11.glDisable(12291);
      this.jdField_field_89_of_type_Class_925.d();
      GL11.glClear(16640);
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      GL11.glDisable(2903);
      GlUtil.d1();
      GlUtil.a12(5889);
      GlUtil.d1();
      GlUtil.b2();
      GlUtil.a14(this.field_96, 0.0F, this.field_95);
      GL11.glViewport(0, 0, this.field_96, this.field_95);
      GlUtil.a12(5888);
      GlUtil.b2();
      GL11.glDisable(2884);
      g();
      GL11.glEnable(2884);
      this.jdField_field_89_of_type_Class_925.b1();
      GL11.glViewport(0, 0, class_933.b(), class_933.a());
      GlUtil.a12(5889);
      GlUtil.c2();
      GlUtil.a12(5888);
      GlUtil.c2();
      GL11.glEnable(2929);
      GL11.glEnable(2896);
      this.jdField_field_89_of_type_Boolean = true;
      this.field_100 = false;
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      this.jdField_field_93_of_type_Boolean = false;
      this.field_100 = false;
      localIllegalStateException.printStackTrace();
      return;
    }
    catch (GLException localGLException)
    {
      this.jdField_field_93_of_type_Boolean = false;
      this.field_100 = false;
      localGLException.printStackTrace();
    }
  }
  
  public final void a2() {}
  
  protected final void d() {}
  
  public final void b()
  {
    g();
  }
  
  private void g()
  {
    if (this.jdField_field_90_of_type_Boolean) {
      c();
    }
    if ((!this.jdField_field_89_of_type_Boolean) && (this.jdField_field_93_of_type_Boolean) && (!this.field_100)) {
      e();
    }
    GL11.glDisable(2896);
    GlUtil.d1();
    if (this.jdField_field_92_of_type_Boolean)
    {
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2929);
    }
    else
    {
      GL11.glDisable(3042);
    }
    float f = 0.0F;
    r();
    if (this.jdField_field_89_of_type_Boolean)
    {
      class_925 localclass_925 = this.jdField_field_89_of_type_Class_925;
      GL11.glDisable(2896);
      GL13.glActiveTexture(33984);
      GL11.glEnable(3553);
      GL13.glActiveTexture(33984);
      GL11.glBindTexture(3553, localclass_925.a3());
      GL11.glDisable(2884);
      GlUtil.d1();
      GL11.glBegin(7);
      GL11.glTexCoord2f(0.0F, 0.0F);
      GL11.glVertex2f(0.0F, 0.0F);
      GL11.glTexCoord2f(0.0F, 1.0F);
      GL11.glVertex2f(0.0F, localclass_925.field_1151);
      GL11.glTexCoord2f(1.0F, 1.0F);
      GL11.glVertex2f(localclass_925.field_1150, localclass_925.field_1151);
      GL11.glTexCoord2f(1.0F, 0.0F);
      GL11.glVertex2f(localclass_925.field_1150, 0.0F);
      GL11.glEnd();
      GlUtil.c2();
      GL11.glCullFace(1029);
      GL11.glBindTexture(3553, 0);
      GL11.glDisable(3553);
      GL11.glEnable(2896);
      GL11.glDisable(3042);
    }
    else
    {
      int i = 0;
      int j;
      if (this.jdField_field_90_of_type_Int >= 0) {
        f = (j = (int)Math.floor(this.jdField_field_90_of_type_Int / this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getLineHeight())) * this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getLineHeight();
      }
      for (i = j; (i < this.jdField_field_90_of_type_JavaUtilList.size()) && ((this.jdField_field_92_of_type_Int < 0) || (f <= this.jdField_field_92_of_type_Int)); i++)
      {
        Object localObject;
        if ((this.jdField_field_90_of_type_JavaUtilList.get(i) instanceof class_1422))
        {
          localObject = ((class_1422)this.jdField_field_90_of_type_JavaUtilList.get(i)).a();
          this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776 = ((Vector4f)localObject).field_596;
          this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777 = ((Vector4f)localObject).field_597;
          this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778 = ((Vector4f)localObject).field_598;
          this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = ((Vector4f)localObject).field_599;
          if (this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 <= 0.0F) {}
        }
        else
        {
          GlUtil.a38(this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776, this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778, this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777, this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779);
          if ((this.jdField_field_89_of_type_Int >= 0) && (i >= this.jdField_field_89_of_type_Int)) {
            break;
          }
          localObject = this.jdField_field_90_of_type_JavaUtilList.get(i).toString();
          localObject = this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.drawDisplayList(0.0F, f, (String)localObject, this.jdField_field_89_of_type_OrgNewdawnSlickColor, 0, ((String)localObject).length());
          f += ((UnicodeFont.DisplayList)localObject).height;
        }
      }
    }
    if ((this.jdField_field_90_of_type_Int >= 0) && (this.jdField_field_92_of_type_Int >= 0)) {
      this.field_94 = (this.jdField_field_90_of_type_JavaUtilList.size() * this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getLineHeight());
    } else {
      this.field_94 = ((int)f);
    }
    if (this.field_96) {
      l();
    }
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(3042);
    GL11.glEnable(2896);
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.field_94;
  }
  
  public final List b15()
  {
    return this.jdField_field_90_of_type_JavaUtilList;
  }
  
  public final float b1()
  {
    return this.jdField_field_93_of_type_Int;
  }
  
  public final void c()
  {
    if (this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont == null)
    {
      if (jdField_field_90_of_type_OrgNewdawnSlickUnicodeFont == null) {
        jdField_field_90_of_type_OrgNewdawnSlickUnicodeFont = class_29.l();
      }
      if (this.field_216) {
        this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont = class_29.m();
      } else {
        this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont = jdField_field_90_of_type_OrgNewdawnSlickUnicodeFont;
      }
    }
    this.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.setDisplayListCaching(true);
    if (this.jdField_field_90_of_type_JavaUtilList == null) {
      this.jdField_field_90_of_type_JavaUtilList = new ArrayList();
    }
    this.jdField_field_90_of_type_Boolean = false;
  }
  
  public final void a72(int paramInt)
  {
    this.jdField_field_93_of_type_Boolean = true;
    this.field_96 = paramInt;
    this.field_95 = 512;
  }
  
  public final void a135(Color paramColor)
  {
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776 = paramColor.field_1776;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777 = paramColor.field_1777;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778 = paramColor.field_1778;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = paramColor.field_1779;
  }
  
  public final void b16(List paramList)
  {
    this.jdField_field_90_of_type_JavaUtilList = paramList;
  }
  
  public final void a136(Object paramObject)
  {
    if (this.jdField_field_90_of_type_JavaUtilList == null) {
      this.jdField_field_90_of_type_JavaUtilList = new ArrayList();
    } else {
      this.jdField_field_90_of_type_JavaUtilList.clear();
    }
    this.jdField_field_90_of_type_JavaUtilList.add(paramObject);
  }
  
  public final void c6(Vector4f paramVector4f)
  {
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776 = paramVector4f.field_596;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777 = paramVector4f.field_597;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778 = paramVector4f.field_598;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = paramVector4f.field_599;
  }
  
  public final void a137(float paramFloat1, float paramFloat2)
  {
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1776 = 1.0F;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1777 = paramFloat1;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1778 = paramFloat2;
    this.jdField_field_89_of_type_OrgNewdawnSlickColor.field_1779 = 1.0F;
  }
  
  public final void f()
  {
    this.field_216 = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_930
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */