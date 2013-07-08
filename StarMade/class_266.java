import java.util.Observable;
import java.util.Observer;
import org.schema.schine.network.client.ClientState;

public final class class_266
  extends class_1363
  implements Observer
{
  private class_160 jdField_field_89_of_type_Class_160;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_266(ClientState paramClientState)
  {
    super(paramClientState);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean)
    {
      this.jdField_field_89_of_type_Class_160.jdField_field_89_of_type_Boolean = true;
      this.jdField_field_89_of_type_Boolean = false;
    }
    k();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_160 = new class_280(a24());
    this.jdField_field_89_of_type_Class_160.c();
    a9(this.jdField_field_89_of_type_Class_160);
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_160.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_160.b1();
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_266
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */