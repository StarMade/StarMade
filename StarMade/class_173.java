import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import org.schema.schine.network.client.ClientState;

public abstract class class_173
  extends class_1363
  implements Observer, class_1412
{
  private class_968 jdField_field_89_of_type_Class_968;
  private class_964 jdField_field_89_of_type_Class_964;
  private int jdField_field_89_of_type_Int = 400;
  private int jdField_field_90_of_type_Int = 380;
  private class_1412 jdField_field_90_of_type_Class_1412;
  private int field_92;
  private boolean jdField_field_89_of_type_Boolean = true;
  private boolean jdField_field_90_of_type_Boolean;
  
  public class_173(ClientState paramClientState, class_1412 paramclass_1412)
  {
    super(paramClientState);
    this.jdField_field_90_of_type_Class_1412 = paramclass_1412;
    this.jdField_field_90_of_type_Boolean = true;
    ((class_371)super.a24()).a12().a51().addObserver(this);
  }
  
  public final class_371 a20()
  {
    return (class_371)super.a24();
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (a57() != this.field_92) {
      this.jdField_field_89_of_type_Boolean = true;
    }
    if (this.jdField_field_89_of_type_Boolean)
    {
      e();
      this.jdField_field_89_of_type_Boolean = false;
    }
    k();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_968 = new class_968(this.jdField_field_89_of_type_Int, this.jdField_field_90_of_type_Int, (class_371)super.a24());
    this.jdField_field_89_of_type_Class_964 = new class_964((class_371)super.a24());
    this.jdField_field_89_of_type_Class_964.a141(this);
    e();
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_964);
    a9(this.jdField_field_89_of_type_Class_968);
  }
  
  private void e()
  {
    Object localObject1 = ((class_371)super.a24()).a12().a51();
    int i = a57();
    this.jdField_field_89_of_type_Class_964.clear();
    if ((localObject1 = ((class_765)localObject1).a156(i)) != null)
    {
      Object localObject2 = ((class_773)localObject1).a162();
      int j = 0;
      localObject2 = ((HashMap)localObject2).values().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Object localObject3 = (class_758)((Iterator)localObject2).next();
        (localObject3 = new class_171(this, (class_371)super.a24(), (class_773)localObject1, (class_758)localObject3, j++)).c();
        this.jdField_field_89_of_type_Class_964.a144((class_959)localObject3);
      }
    }
    this.field_92 = i;
  }
  
  public abstract int a57();
  
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
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_173
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */