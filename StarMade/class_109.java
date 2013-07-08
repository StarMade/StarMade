import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import org.schema.schine.network.client.ClientState;

public final class class_109
  extends class_196
  implements Observer
{
  private class_968 jdField_field_89_of_type_Class_968;
  private class_964 jdField_field_89_of_type_Class_964;
  private boolean field_90 = true;
  
  public class_109(ClientState paramClientState, class_1412 paramclass_1412)
  {
    super(paramClientState, paramclass_1412, "Faction Relationship Offers", "");
    ((class_371)a24()).a20().a141().deleteObserver(this);
    ((class_371)a24()).a20().a141().addObserver(this);
  }
  
  public final void c()
  {
    super.c();
    this.jdField_field_89_of_type_Class_968 = new class_968(410.0F, 110.0F, a24());
    this.jdField_field_89_of_type_Class_964 = new class_964(a24());
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_964);
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_968);
  }
  
  public final void b()
  {
    super.b();
    if (this.field_90)
    {
      class_109 localclass_109 = this;
      this.jdField_field_89_of_type_Class_964.clear();
      int i = 0;
      System.err.println("[GUI] UPDATING FACTION OFFER LIST: " + ((class_371)localclass_109.a24()).a20().a141().c23().size());
      Iterator localIterator = ((class_371)localclass_109.a24()).a20().a141().c23().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (class_629)localIterator.next();
        (localObject = new class_131(localclass_109.a24(), (class_629)localObject, i)).c();
        localclass_109.jdField_field_89_of_type_Class_964.a144((class_959)localObject);
        i++;
      }
      this.field_90 = false;
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    return paramObject instanceof class_86;
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    this.field_90 = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_109
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */