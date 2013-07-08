import javax.vecmath.Vector3f;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
import org.schema.schine.network.client.ClientState;

public abstract class class_1404
  extends class_922
  implements class_1412
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 jdField_field_90_of_type_Class_972;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_1404(ClientState paramClientState)
  {
    super(paramClientState);
    this.field_96 = true;
    super.a141(this);
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("tools-16x16-gui-"), a24());
    this.jdField_field_90_of_type_Class_972 = new class_972(class_969.a2().a5("tools-16x16-gui-"), a24());
  }
  
  public final void a1(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      if (b3()) {
        try
        {
          f();
          return;
        }
        catch (StateParameterNotFoundException localStateParameterNotFoundException1)
        {
          localStateParameterNotFoundException1.printStackTrace();
          return;
        }
      }
      try
      {
        e();
        return;
      }
      catch (StateParameterNotFoundException localStateParameterNotFoundException2)
      {
        localStateParameterNotFoundException2;
      }
    }
  }
  
  public final void a141(class_1412 paramclass_1412)
  {
    if (!jdField_field_90_of_type_Boolean) {
      throw new AssertionError("CANNOT SET CALLBACK BESIDES OWN");
    }
  }
  
  protected abstract void e();
  
  protected abstract void f();
  
  protected abstract boolean b3();
  
  public final void a2() {}
  
  protected final void d() {}
  
  public final void b()
  {
    if (!this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    l();
    this.jdField_field_89_of_type_Class_972.b();
    if (b3()) {
      this.jdField_field_90_of_type_Class_972.b();
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
    this.jdField_field_90_of_type_Class_972.a_2(19);
    this.jdField_field_89_of_type_Class_972.a83().field_616 += 2.0F;
    this.jdField_field_90_of_type_Class_972.a83().set(this.jdField_field_89_of_type_Class_972.a83());
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1404
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */