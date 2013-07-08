import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
import org.schema.schine.network.client.ClientState;

public class class_120
  extends class_922
  implements class_1412
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 field_90;
  private class_772 jdField_field_89_of_type_Class_772;
  
  public class_120(ClientState paramClientState, class_772 paramclass_772)
  {
    super(paramClientState);
    this.field_96 = true;
    a141(this);
    this.jdField_field_89_of_type_Class_772 = paramclass_772;
    if ((!jdField_field_89_of_type_Boolean) && (!(paramclass_772.a171() instanceof Boolean))) {
      throw new AssertionError();
    }
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("tools-16x16-gui-"), a24());
    this.field_90 = new class_972(class_969.a2().a5("tools-16x16-gui-"), a24());
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
    GlUtil.d1();
    r();
    l();
    this.jdField_field_89_of_type_Class_972.b();
    if (this.jdField_field_89_of_type_Class_772.a7()) {
      this.field_90.b();
    }
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  public final boolean a4()
  {
    return false;
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972.a_2(18);
    this.field_90.a_2(19);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_120
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */