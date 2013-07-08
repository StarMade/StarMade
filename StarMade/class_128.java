import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_128
  extends class_1363
{
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 jdField_field_90_of_type_Class_930;
  private class_972 jdField_field_89_of_type_Class_972;
  private String jdField_field_90_of_type_JavaLangString;
  private boolean jdField_field_89_of_type_Boolean = true;
  private static UnicodeFont jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont;
  
  public class_128(ClientState paramClientState, String paramString)
  {
    super(paramClientState);
    this.jdField_field_90_of_type_JavaLangString = paramString;
  }
  
  public final void a2()
  {
    this.jdField_field_89_of_type_Class_972.a2();
    this.jdField_field_89_of_type_Class_930.a2();
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    this.jdField_field_89_of_type_Class_930.field_90.set(0, this.jdField_field_90_of_type_JavaLangString);
    GlUtil.d1();
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    r();
    if (0L < System.currentTimeMillis()) {
      this.jdField_field_90_of_type_Class_930.field_90.clear();
    }
    this.jdField_field_89_of_type_Class_972.b();
    GL11.glDisable(3042);
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return 256.0F;
  }
  
  public final float b1()
  {
    return 256.0F;
  }
  
  public final void c()
  {
    if (jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont == null)
    {
      localObject = new Font("Arial", 1, 28);
      (class_128.jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont = new UnicodeFont((Font)localObject)).getEffects().add(new OutlineEffect(4, Color.black));
      jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.getEffects().add(new ColorEffect(Color.white));
      jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.addAsciiGlyphs();
      try
      {
        jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont.loadGlyphs();
      }
      catch (SlickException localSlickException)
      {
        localSlickException;
      }
    }
    this.jdField_field_89_of_type_Class_930 = new class_930(256, 64, jdField_field_89_of_type_OrgNewdawnSlickUnicodeFont, a24());
    this.jdField_field_90_of_type_Class_930 = new class_930(256, 64, a24());
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("panel-std-gui-"), a24());
    (localObject = new ArrayList()).add(this.jdField_field_90_of_type_JavaLangString);
    this.jdField_field_89_of_type_Class_930.field_90 = ((List)localObject);
    Object localObject = new ArrayList();
    this.jdField_field_90_of_type_Class_930.field_90 = ((List)localObject);
    this.jdField_field_89_of_type_Class_972.h3(48);
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_89_of_type_Class_930.c();
    a9(this.jdField_field_89_of_type_Class_972);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_930);
    this.jdField_field_89_of_type_Class_930.a161(280.0F, 80.0F, 0.0F);
    this.jdField_field_90_of_type_Class_930.a161(300.0F, 30.0F, 0.0F);
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  public final void a17(String paramString)
  {
    this.jdField_field_90_of_type_JavaLangString = paramString;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_128
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */