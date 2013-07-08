import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_200
  extends class_1363
{
  private class_194 jdField_field_89_of_type_Class_194;
  private class_194 jdField_field_90_of_type_Class_194;
  private class_194 field_92;
  private class_194 field_93;
  private class_194 field_94;
  private class_930 jdField_field_89_of_type_Class_930;
  private class_930 jdField_field_90_of_type_Class_930;
  private class_972 jdField_field_89_of_type_Class_972;
  private long jdField_field_89_of_type_Long;
  private long jdField_field_90_of_type_Long;
  private class_1412 jdField_field_90_of_type_Class_1412;
  private String jdField_field_90_of_type_JavaLangString;
  private class_1363 jdField_field_89_of_type_Class_1363;
  private boolean jdField_field_89_of_type_Boolean = true;
  
  public class_200(ClientState paramClientState, class_1412 paramclass_1412, String paramString)
  {
    super(paramClientState);
    this.jdField_field_90_of_type_Class_1412 = paramclass_1412;
    this.jdField_field_90_of_type_JavaLangString = paramString;
    this.jdField_field_89_of_type_Class_1363 = new class_1414(a24(), 39.0F, 26.0F);
    this.jdField_field_89_of_type_Class_1363.a141(paramclass_1412);
    this.jdField_field_89_of_type_Class_1363.field_89 = "X";
    this.jdField_field_89_of_type_Class_1363.field_96 = true;
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
    this.field_92.b21(!((class_371)a24()).e());
    GlUtil.d1();
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    r();
    if (this.jdField_field_89_of_type_Long < System.currentTimeMillis() - this.jdField_field_90_of_type_Long) {
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
    this.jdField_field_89_of_type_Class_930 = new class_930(256, 64, class_29.e(), a24());
    this.jdField_field_90_of_type_Class_930 = new class_930(256, 64, a24());
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("menu-panel-gui-"), a24());
    this.jdField_field_89_of_type_Class_194 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_667, "RESUME", this.jdField_field_90_of_type_Class_1412);
    this.jdField_field_90_of_type_Class_194 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_666, "EXIT", this.jdField_field_90_of_type_Class_1412);
    this.field_94 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_669, "EXIT_TO_WINDOWS", this.jdField_field_90_of_type_Class_1412);
    this.field_93 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_668, "OPTIONS", this.jdField_field_90_of_type_Class_1412);
    this.field_92 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), a24(), class_319.field_670, "SUICIDE", this.jdField_field_90_of_type_Class_1412);
    (localArrayList = new ArrayList()).add(this.jdField_field_90_of_type_JavaLangString);
    this.jdField_field_89_of_type_Class_930.field_90 = localArrayList;
    ArrayList localArrayList = new ArrayList();
    this.jdField_field_90_of_type_Class_930.field_90 = localArrayList;
    this.jdField_field_89_of_type_Class_972.h3(48);
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Class_194.c();
    this.jdField_field_90_of_type_Class_194.c();
    a9(this.jdField_field_89_of_type_Class_972);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_194);
    this.jdField_field_89_of_type_Class_972.a9(this.field_92);
    this.field_92.b21(true);
    this.jdField_field_89_of_type_Class_972.a9(this.field_93);
    this.jdField_field_89_of_type_Class_972.a9(this.field_94);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_1363.a161(216.0F, 0.0F, 0.0F);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_1363);
    this.jdField_field_89_of_type_Class_930.a161(50.0F, 10.0F, 0.0F);
    this.jdField_field_90_of_type_Class_930.a161(40.0F, 30.0F, 0.0F);
    this.jdField_field_89_of_type_Class_194.a161(55.0F, 76.0F, 0.0F);
    this.field_92.a161(55.0F, 152.0F, 0.0F);
    this.field_93.a161(55.0F, 228.0F, 0.0F);
    this.jdField_field_90_of_type_Class_194.a161(55.0F, 304.0F, 0.0F);
    this.field_94.a161(55.0F, 380.0F, 0.0F);
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  public final void a17(String paramString)
  {
    this.jdField_field_90_of_type_Class_930.field_90.add(paramString);
    this.jdField_field_89_of_type_Long = System.currentTimeMillis();
    this.jdField_field_90_of_type_Long = 2000L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_200
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */