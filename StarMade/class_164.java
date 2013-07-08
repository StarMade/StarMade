import java.util.Observable;
import java.util.Observer;
import org.schema.schine.network.client.ClientState;

public final class class_164
  extends class_1363
  implements Observer
{
  private class_160 jdField_field_89_of_type_Class_160;
  private class_93 jdField_field_89_of_type_Class_93;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_164(ClientState paramClientState)
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
    this.jdField_field_89_of_type_Class_93 = new class_93(a24());
    this.jdField_field_89_of_type_Class_93.c();
    this.jdField_field_89_of_type_Class_160 = new class_162(a24(), (int)(340.0F - this.jdField_field_89_of_type_Class_93.a3()));
    this.jdField_field_89_of_type_Class_160.c();
    this.jdField_field_89_of_type_Class_160.a83().field_616 = this.jdField_field_89_of_type_Class_93.a3();
    a9(this.jdField_field_89_of_type_Class_160);
    a9(this.jdField_field_89_of_type_Class_93);
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
 * Qualified Name:     class_164
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */