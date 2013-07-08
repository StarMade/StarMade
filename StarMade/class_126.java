import java.util.ArrayList;
import java.util.List;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
import org.schema.schine.network.client.ClientState;

public final class class_126
  extends class_922
  implements class_1412
{
  private class_930 jdField_field_89_of_type_Class_930;
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 field_90;
  private class_772 jdField_field_89_of_type_Class_772;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_126(ClientState paramClientState, class_772 paramclass_772)
  {
    super(paramClientState);
    this.field_96 = true;
    a141(this);
    this.jdField_field_89_of_type_Class_772 = paramclass_772;
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("tools-16x16-gui-"), a24());
    this.field_90 = new class_972(class_969.a2().a5("tools-16x16-gui-"), a24());
    this.jdField_field_89_of_type_Class_930 = new class_930(140, 30, class_29.d(), a24());
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    paramclass_1363 = null;
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0)) {
      try
      {
        this.jdField_field_89_of_type_Class_772.a13();
        return;
      }
      catch (StateParameterNotFoundException localStateParameterNotFoundException)
      {
        (paramclass_1363 = localStateParameterNotFoundException).printStackTrace();
        class_933.a2(paramclass_1363);
      }
    }
  }
  
  public final void a2() {}
  
  protected final void d() {}
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_930.b();
    this.jdField_field_89_of_type_Class_972.b();
    this.field_90.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_930.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_930.b1() + this.jdField_field_89_of_type_Class_972.b1() + this.field_90.b1();
  }
  
  public final boolean a4()
  {
    return false;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add(this.jdField_field_89_of_type_Class_772.a171().toString());
    this.jdField_field_89_of_type_Class_930.a161(0.0F, 5.0F, 0.0F);
    this.jdField_field_89_of_type_Class_930.c();
    this.jdField_field_89_of_type_Class_972.field_96 = true;
    this.field_90.field_96 = true;
    this.jdField_field_89_of_type_Class_972.a141(new class_124(this));
    this.field_90.a141(new class_83(this));
    this.jdField_field_89_of_type_Class_972.a_2(21);
    this.field_90.a_2(20);
    this.jdField_field_89_of_type_Class_930.a83().field_615 = this.jdField_field_89_of_type_Class_972.b1();
    this.field_90.a83().field_615 = (this.jdField_field_89_of_type_Class_972.b1() + this.jdField_field_89_of_type_Class_930.b1());
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_126
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */