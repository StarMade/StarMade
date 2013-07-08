import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;
import org.schema.schine.network.client.ClientState;

public abstract class class_114
  extends class_968
  implements Observer
{
  private boolean jdField_field_89_of_type_Boolean = true;
  private class_964 jdField_field_89_of_type_Class_964;
  
  public class_114(float paramFloat, ClientState paramClientState)
  {
    super(paramFloat, 140.0F, paramClientState);
    ((class_371)a24()).a20().a141().deleteObserver(this);
    ((class_371)a24()).a20().a141().addObserver(this);
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_964 = new class_964(a24());
    c7(this.jdField_field_89_of_type_Class_964);
    super.c();
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean)
    {
      a27(this.jdField_field_89_of_type_Class_964);
      this.jdField_field_89_of_type_Boolean = false;
    }
    super.b();
  }
  
  protected abstract void a27(class_964 paramclass_964);
  
  public void update(Observable paramObservable, Object paramObject)
  {
    System.err.println("FACTION LIST NEEDS UPDATE");
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_114
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */