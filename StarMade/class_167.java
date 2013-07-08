import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;
import org.schema.schine.network.client.ClientState;

public class class_167
  extends class_1363
  implements Observer
{
  private int jdField_field_89_of_type_Int;
  private int jdField_field_90_of_type_Int;
  private class_968 jdField_field_89_of_type_Class_968;
  private class_964 jdField_field_89_of_type_Class_964;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_167(ClientState paramClientState)
  {
    super(paramClientState);
    ((class_371)paramClientState).a45().addObserver(this);
    this.jdField_field_89_of_type_Int = 540;
    this.jdField_field_90_of_type_Int = 205;
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (((class_371)a24()).a4().a29() == null) {
      return;
    }
    ((class_371)a24()).a4().a29().addObserver(this);
    if (this.jdField_field_89_of_type_Boolean)
    {
      System.err.println("[FACTIONNEWS] UPDATE NEWS");
      class_167 localclass_167 = this;
      Object localObject1 = ((class_371)a24()).a45().a156(((class_371)localclass_167.a24()).a20().h1());
      localclass_167.jdField_field_89_of_type_Class_964.clear();
      if (localObject1 != null)
      {
        localObject1 = (TreeSet)((class_371)localclass_167.a24()).a45().a162().get(Integer.valueOf(((class_773)localObject1).a3()));
        if ((!jdField_field_90_of_type_Boolean) && (((class_371)localclass_167.a24()).a4().a29() == null)) {
          throw new AssertionError();
        }
        if ((!jdField_field_90_of_type_Boolean) && (((class_371)localclass_167.a24()).a4().a29().a7() == null)) {
          throw new AssertionError();
        }
        if (localObject1 != null) {
          ((TreeSet)localObject1).addAll(((class_371)localclass_167.a24()).a4().a29().a7());
        } else {
          localObject1 = ((class_371)localclass_167.a24()).a4().a29().a7();
        }
        if (localObject1 != null)
        {
          int i = 0;
          localObject1 = ((TreeSet)localObject1).descendingSet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            Object localObject2 = (class_771)((Iterator)localObject1).next();
            localObject2 = new class_111(localclass_167.a24(), (class_771)localObject2, i);
            i++;
            localclass_167.jdField_field_89_of_type_Class_964.a144((class_959)localObject2);
          }
        }
        class_1414 localclass_1414 = new class_1414(localclass_167.a24(), 540.0F, 50.0F);
        localObject1 = new class_934((class_371)localclass_167.a24(), 200, 20, "Request next 5", new class_165(localclass_167));
        localclass_1414.a9((class_1363)localObject1);
        ((class_934)localObject1).a83().field_615 = 140.0F;
        ((class_934)localObject1).a83().field_616 = 12.0F;
        ((class_934)localObject1).b17(40, 2);
        localclass_167.jdField_field_89_of_type_Class_964.a144(new class_959(localclass_1414, localclass_1414, localclass_167.a24()));
      }
      this.jdField_field_89_of_type_Boolean = false;
    }
    k();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_968 = new class_968(this.jdField_field_89_of_type_Int, this.jdField_field_90_of_type_Int, a24());
    this.jdField_field_89_of_type_Class_964 = new class_964(a24());
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_964);
    this.jdField_field_89_of_type_Boolean = true;
    a9(this.jdField_field_89_of_type_Class_968);
  }
  
  public final float a3()
  {
    return this.jdField_field_90_of_type_Int;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Int;
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    System.err.println("[CLIENT][GUI][FACTIONNEWS] OBSERVER: GUI UPDATE FLAG NEWS");
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_167
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */