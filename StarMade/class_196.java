import java.util.ArrayList;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_196
  extends class_1363
{
  private class_972 jdField_field_90_of_type_Class_972;
  private class_972 jdField_field_92_of_type_Class_972;
  private class_930 jdField_field_90_of_type_Class_930;
  protected class_930 field_89;
  private class_930 jdField_field_92_of_type_Class_930;
  protected class_972 field_89;
  protected class_1414 field_89;
  public boolean field_89;
  private boolean jdField_field_90_of_type_Boolean = true;
  private long field_89;
  private long jdField_field_90_of_type_Long;
  private boolean jdField_field_92_of_type_Boolean = true;
  private class_1414 jdField_field_90_of_type_Class_1414;
  
  public class_196(ClientState paramClientState, class_1412 paramclass_1412, Object paramObject1, Object paramObject2)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Boolean = true;
    a141(paramclass_1412);
    this.jdField_field_90_of_type_Class_930 = new class_930(256, 64, class_29.d(), paramClientState);
    this.jdField_field_89_of_type_Class_930 = new class_930(256, 64, class_29.b2(), paramClientState);
    this.jdField_field_92_of_type_Class_930 = new class_930(256, 64, paramClientState);
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("textinput-panel-gui-"), paramClientState);
    this.jdField_field_90_of_type_Class_972 = new class_972(class_969.a2().a5("buttons-8x8-gui-"), paramClientState);
    this.jdField_field_90_of_type_Class_972.a_2(class_319.field_663.a(false));
    this.jdField_field_90_of_type_Class_972.a141(paramclass_1412);
    this.jdField_field_90_of_type_Class_972.field_89 = "OK";
    this.jdField_field_90_of_type_Class_972.field_96 = true;
    this.jdField_field_92_of_type_Class_972 = new class_972(class_969.a2().a5("buttons-8x8-gui-"), paramClientState);
    this.jdField_field_92_of_type_Class_972.a_2(3);
    this.jdField_field_92_of_type_Class_972.a141(paramclass_1412);
    this.jdField_field_92_of_type_Class_972.field_89 = Integer.valueOf(class_319.field_664.a(false));
    this.jdField_field_92_of_type_Class_972.field_89 = "CANCEL";
    this.jdField_field_92_of_type_Class_972.field_96 = true;
    this.jdField_field_90_of_type_Class_1414 = new class_1414(a24(), 39.0F, 26.0F);
    this.jdField_field_90_of_type_Class_1414.a141(paramclass_1412);
    this.jdField_field_90_of_type_Class_1414.field_89 = "X";
    this.jdField_field_90_of_type_Class_1414.field_96 = true;
    (paramClientState = new ArrayList()).add(paramObject1);
    this.jdField_field_90_of_type_Class_930.field_90 = paramClientState;
    (paramClientState = new ArrayList()).add(paramObject2);
    this.jdField_field_89_of_type_Class_930.field_90 = paramClientState;
    paramClientState = new ArrayList();
    this.jdField_field_92_of_type_Class_930.field_90 = paramClientState;
    this.jdField_field_89_of_type_Class_972.h3(48);
  }
  
  public void a2()
  {
    this.jdField_field_89_of_type_Class_972.a2();
    this.jdField_field_90_of_type_Class_930.a2();
  }
  
  public void b()
  {
    if (this.jdField_field_92_of_type_Boolean) {
      c();
    }
    if (k1()) {
      this.jdField_field_89_of_type_Class_972.h3(48);
    }
    GlUtil.d1();
    r();
    if (this.jdField_field_89_of_type_Long < System.currentTimeMillis() - this.jdField_field_90_of_type_Long) {
      this.jdField_field_92_of_type_Class_930.field_90.clear();
    }
    this.jdField_field_90_of_type_Class_972.a_2(class_319.field_663.a(this.jdField_field_90_of_type_Class_972.a_()));
    this.jdField_field_92_of_type_Class_972.a_2(class_319.field_664.a(this.jdField_field_92_of_type_Class_972.a_()));
    this.jdField_field_89_of_type_Class_972.b();
    GlUtil.c2();
  }
  
  public float a3()
  {
    return 256.0F;
  }
  
  public float b1()
  {
    return 256.0F;
  }
  
  public void c()
  {
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_90_of_type_Class_930.c();
    this.jdField_field_90_of_type_Class_972.c();
    this.jdField_field_92_of_type_Class_972.c();
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_90_of_type_Class_1414.a83().set(472.0F, 8.0F, 0.0F);
    this.jdField_field_90_of_type_Class_1414.c();
    a9(this.jdField_field_89_of_type_Class_972);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_92_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_1414);
    if (this.jdField_field_89_of_type_Boolean) {
      this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_90_of_type_Class_972);
    }
    if (this.jdField_field_90_of_type_Boolean) {
      this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_92_of_type_Class_972);
    }
    this.jdField_field_90_of_type_Class_930.a161(20.0F, 20.0F, 0.0F);
    this.jdField_field_89_of_type_Class_930.a161(2.0F, 2.0F, 0.0F);
    this.jdField_field_89_of_type_Class_1414 = new class_1414(a24());
    this.jdField_field_89_of_type_Class_1414.a161(46.0F, 62.0F, 0.0F);
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_930);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_1414);
    this.jdField_field_92_of_type_Class_930.a161(20.0F, 50.0F, 0.0F);
    this.jdField_field_90_of_type_Class_972.a161(335.0F, 213.0F, 0.0F);
    this.jdField_field_90_of_type_Class_972.b28(0.45F, 0.45F, 0.45F);
    this.jdField_field_92_of_type_Class_972.a161(400.0F, 213.0F, 0.0F);
    this.jdField_field_92_of_type_Class_972.b28(0.45F, 0.45F, 0.45F);
    this.jdField_field_92_of_type_Boolean = false;
  }
  
  public final void a17(String paramString)
  {
    this.jdField_field_92_of_type_Class_930.field_90.add(paramString);
    this.jdField_field_89_of_type_Long = System.currentTimeMillis();
    this.jdField_field_90_of_type_Long = 2000L;
  }
  
  public final void e()
  {
    this.jdField_field_89_of_type_Boolean = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_196
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */