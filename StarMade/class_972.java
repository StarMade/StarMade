import java.util.Iterator;
import java.util.List;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_972
  extends class_1363
{
  public class_1380 field_89;
  public int field_89;
  private float field_89;
  private float jdField_field_90_of_type_Float;
  public boolean field_89;
  
  public class_972(class_1380 paramclass_1380, ClientState paramClientState)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Int = -1;
    this.jdField_field_89_of_type_Class_1380 = paramclass_1380;
    this.jdField_field_89_of_type_Float = paramclass_1380.a57();
    this.jdField_field_90_of_type_Float = paramclass_1380.c5();
  }
  
  public final void a2() {}
  
  protected final void d() {}
  
  public void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      return;
    }
    GlUtil.d1();
    this.field_95 = false;
    if (this.jdField_field_89_of_type_Int >= 0)
    {
      if ((!jdField_field_90_of_type_Boolean) && (this.jdField_field_89_of_type_Int < 0)) {
        throw new AssertionError(this.jdField_field_89_of_type_Int);
      }
      this.jdField_field_89_of_type_Class_1380.b13(this.jdField_field_89_of_type_Int);
    }
    r();
    this.jdField_field_89_of_type_Class_1380.b();
    if (this.field_96) {
      l();
    }
    Iterator localIterator = this.field_89.iterator();
    while (localIterator.hasNext()) {
      ((class_984)localIterator.next()).b();
    }
    GlUtil.c2();
  }
  
  public float a3()
  {
    return this.jdField_field_89_of_type_Float;
  }
  
  public final String b14()
  {
    return "(s" + this.jdField_field_89_of_type_Int + ")" + super.b14();
  }
  
  public final class_1380 a147()
  {
    return this.jdField_field_89_of_type_Class_1380;
  }
  
  public float b1()
  {
    return this.jdField_field_90_of_type_Float;
  }
  
  public final boolean h2()
  {
    return this.jdField_field_89_of_type_Boolean;
  }
  
  public final void c() {}
  
  public final void b21(boolean paramBoolean)
  {
    this.jdField_field_89_of_type_Boolean = paramBoolean;
  }
  
  public final void a148(class_1380 paramclass_1380)
  {
    this.jdField_field_89_of_type_Class_1380 = paramclass_1380;
  }
  
  public void a_2(int paramInt)
  {
    if ((!jdField_field_90_of_type_Boolean) && (paramInt < 0)) {
      throw new AssertionError(this.jdField_field_89_of_type_Int);
    }
    if ((!jdField_field_90_of_type_Boolean) && (paramInt > this.jdField_field_89_of_type_Class_1380.b12())) {
      throw new AssertionError(paramInt + " / " + this.jdField_field_89_of_type_Class_1380.b12());
    }
    this.jdField_field_89_of_type_Int = paramInt;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_972
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */