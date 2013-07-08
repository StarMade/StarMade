import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_959
  extends class_1363
{
  public class_1363 field_89;
  public class_1363 field_90;
  private boolean field_89;
  private boolean field_90;
  
  public class_959(ClientState paramClientState)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Boolean = true;
    this.jdField_field_90_of_type_Boolean = false;
  }
  
  public class_959(class_1363 paramclass_13631, class_1363 paramclass_13632, ClientState paramClientState)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Boolean = true;
    this.jdField_field_90_of_type_Boolean = false;
    this.jdField_field_89_of_type_Class_1363 = paramclass_13631;
    this.jdField_field_90_of_type_Class_1363 = paramclass_13632;
  }
  
  public void a2() {}
  
  protected final void d() {}
  
  public void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    this.field_95 = false;
    if ((a154() != null) && (((class_1363)a154()).a_())) {
      l();
    }
    this.jdField_field_89_of_type_Class_1363.b();
    if (this.jdField_field_90_of_type_Boolean)
    {
      class_959 localclass_959 = this;
      if (this.jdField_field_90_of_type_Class_1363 != null) {
        localclass_959.jdField_field_90_of_type_Class_1363.b();
      }
    }
    GlUtil.c2();
  }
  
  public final class_1363 a139()
  {
    return this.jdField_field_89_of_type_Class_1363;
  }
  
  public float a3()
  {
    return this.jdField_field_89_of_type_Class_1363.a3();
  }
  
  public final class_964 a140()
  {
    return (class_964)a154();
  }
  
  public final String b14()
  {
    return (this.jdField_field_90_of_type_Boolean ? "*" : "") + this.jdField_field_89_of_type_Class_1363.b14();
  }
  
  public final class_1363 b18()
  {
    return this.jdField_field_90_of_type_Class_1363;
  }
  
  public float b1()
  {
    return this.jdField_field_89_of_type_Class_1363.b1();
  }
  
  public final boolean b3()
  {
    return this.jdField_field_90_of_type_Boolean;
  }
  
  public void c()
  {
    this.jdField_field_89_of_type_Boolean = false;
  }
  
  public final void a141(class_1412 paramclass_1412)
  {
    if (!field_92) {
      throw new AssertionError("Cannot set callback to single list element. please set callback to super list");
    }
    throw new RuntimeException("Cannot set callback to single list element. please set callback to super list");
  }
  
  protected final void b19(class_1412 paramclass_1412)
  {
    super.a141(paramclass_1412);
  }
  
  public final void a29(boolean paramBoolean)
  {
    this.jdField_field_90_of_type_Boolean = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_959
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */