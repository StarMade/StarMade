import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

public final class class_860
  extends class_1363
  implements Observer
{
  private class_968 jdField_field_89_of_type_Class_968;
  private class_964 jdField_field_89_of_type_Class_964;
  private int jdField_field_89_of_type_Int = 540;
  private int jdField_field_90_of_type_Int = 345;
  private class_1412 jdField_field_90_of_type_Class_1412;
  private boolean jdField_field_89_of_type_Boolean;
  
  public class_860(ClientState paramClientState, class_1412 paramclass_1412)
  {
    super(paramClientState);
    this.jdField_field_90_of_type_Class_1412 = paramclass_1412;
    ((class_371)super.a24()).a12().a51().addObserver(this);
  }
  
  public final void a2() {}
  
  public final void b()
  {
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
    this.jdField_field_89_of_type_Class_964.a141(this.jdField_field_90_of_type_Class_1412);
    this.jdField_field_89_of_type_Class_964.field_96 = true;
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_964);
    e();
    a9(this.jdField_field_89_of_type_Class_968);
  }
  
  private void e()
  {
    this.jdField_field_89_of_type_Class_964.clear();
    Object localObject1 = ((class_371)super.a24()).a12().a51();
    int i = 0;
    localObject1 = ((class_765)localObject1).a101().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (class_773)((Iterator)localObject1).next();
      class_964 localclass_964 = new class_964((class_371)super.a24());
      class_1402 localclass_1402 = new class_1402((class_371)super.a24(), 510.0F, 80.0F, i % 2 == 0 ? new Vector4f(0.0F, 0.0F, 0.0F, 0.0F) : new Vector4f(0.1F, 0.1F, 0.1F, 0.5F));
      class_859 localclass_859;
      (localclass_859 = new class_859((class_371)super.a24(), (class_773)localObject2)).c();
      localclass_1402.a9(localclass_859);
      localclass_964.a144(new class_959(localclass_1402, localclass_1402, (class_371)super.a24()));
      (localObject2 = new class_99((class_371)super.a24(), localclass_964, new class_101(this, (class_371)super.a24(), (class_773)localObject2, "+", i), new class_101(this, (class_371)super.a24(), (class_773)localObject2, "-", i))).addObserver(this);
      this.jdField_field_89_of_type_Class_964.a144(new class_959((class_1363)localObject2, (class_1363)localObject2, (class_371)super.a24()));
      i++;
    }
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Int;
  }
  
  public final float b1()
  {
    return this.jdField_field_90_of_type_Int;
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    if ((paramObservable instanceof class_961))
    {
      this.jdField_field_89_of_type_Class_964.f();
      return;
    }
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_860
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */