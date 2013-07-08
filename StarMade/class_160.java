import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.schine.network.client.ClientState;

public abstract class class_160
  extends class_1363
  implements Observer
{
  private class_968 jdField_field_89_of_type_Class_968;
  private class_1414 jdField_field_89_of_type_Class_1414;
  private class_964 jdField_field_89_of_type_Class_964;
  boolean jdField_field_89_of_type_Boolean = true;
  private boolean jdField_field_90_of_type_Boolean;
  private class_934 jdField_field_89_of_type_Class_934;
  private class_934 jdField_field_90_of_type_Class_934;
  private class_934 field_92;
  private class_180 jdField_field_89_of_type_Class_180;
  private class_934 field_93;
  
  public class_160(ClientState paramClientState, boolean paramBoolean)
  {
    this(paramClientState, 340, paramBoolean);
  }
  
  public class_160(ClientState paramClientState, int paramInt, boolean paramBoolean)
  {
    super(paramClientState);
    this.jdField_field_90_of_type_Boolean = paramBoolean;
    this.jdField_field_89_of_type_Class_968 = new class_968(542.0F, paramInt - 30, paramClientState);
    this.jdField_field_89_of_type_Class_1414 = new class_1414(paramClientState, 542.0F, 30.0F);
    this.jdField_field_89_of_type_Class_964 = new class_964(a24());
  }
  
  public final void a2() {}
  
  public abstract Collection a50();
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean)
    {
      class_964 localclass_964 = this.jdField_field_89_of_type_Class_964;
      class_160 localclass_160 = this;
      Object localObject1 = a50();
      HashSet localHashSet = new HashSet();
      Iterator localIterator = localclass_964.iterator();
      Object localObject3;
      while (localIterator.hasNext()) {
        if ((((localObject2 = (class_959)localIterator.next()) instanceof class_248)) && (((localObject3 = (class_248)localObject2).a139() instanceof class_961)) && (((class_961)((class_248)localObject3).a139()).c1())) {
          localHashSet.add(((class_248)localObject3).a108().field_136);
        }
      }
      localclass_964.clear();
      int i = 0;
      Object localObject2 = ((Collection)localObject1).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (class_781)((Iterator)localObject2).next();
        localObject1 = new class_274(localclass_160.a24(), localclass_160, (class_781)localObject3, localclass_160.jdField_field_90_of_type_Boolean, i);
        localclass_964.a144(new class_248((class_1363)localObject1, (class_1363)localObject1, (class_781)localObject3, localclass_160.a24()));
        ((class_961)localObject1).a29(localHashSet.contains(((class_781)localObject3).field_136));
        i++;
      }
      if (localclass_160.jdField_field_89_of_type_Class_180 == null) {
        localclass_160.a51(class_180.jdField_field_627_of_type_Class_180);
      } else {
        localclass_160.b8(localclass_160.jdField_field_89_of_type_Class_180);
      }
      localclass_160.b2(localclass_160.jdField_field_89_of_type_Class_1414);
      if (localclass_964.size() > 0) {
        localclass_160.a9(localclass_160.jdField_field_89_of_type_Class_1414);
      }
      this.jdField_field_89_of_type_Boolean = false;
    }
    k();
  }
  
  public final void a51(class_180 paramclass_180)
  {
    paramclass_180.jdField_field_627_of_type_JavaUtilComparator = Collections.reverseOrder(paramclass_180.jdField_field_627_of_type_JavaUtilComparator);
    b8(paramclass_180);
  }
  
  private void b8(class_180 paramclass_180)
  {
    Collections.sort(this.jdField_field_89_of_type_Class_964, paramclass_180.jdField_field_627_of_type_JavaUtilComparator);
    this.jdField_field_89_of_type_Class_180 = paramclass_180;
    this.jdField_field_89_of_type_Class_934.field_89.set(0.7F, 0.7F, 0.7F, 0.7F);
    this.jdField_field_90_of_type_Class_934.field_89.set(0.7F, 0.7F, 0.7F, 0.7F);
    this.field_92.field_89.set(0.7F, 0.7F, 0.7F, 0.7F);
    this.field_93.field_89.set(0.7F, 0.7F, 0.7F, 0.7F);
    switch (class_182.field_631[paramclass_180.ordinal()])
    {
    case 1: 
      this.jdField_field_89_of_type_Class_934.field_89.set(1.0F, 1.0F, 1.0F, 1.0F);
      break;
    case 2: 
      this.jdField_field_90_of_type_Class_934.field_89.set(1.0F, 1.0F, 1.0F, 1.0F);
      break;
    case 3: 
      this.field_92.field_89.set(1.0F, 1.0F, 1.0F, 1.0F);
      break;
    case 4: 
      this.field_93.field_89.set(1.0F, 1.0F, 1.0F, 1.0F);
    }
    for (paramclass_180 = 0; paramclass_180 < this.jdField_field_89_of_type_Class_964.size(); paramclass_180++) {
      ((class_274)this.jdField_field_89_of_type_Class_964.a145(paramclass_180).a139()).a72(paramclass_180);
    }
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_968.c7(this.jdField_field_89_of_type_Class_964);
    this.jdField_field_89_of_type_Class_968.a83().field_616 = this.jdField_field_89_of_type_Class_1414.a3();
    this.jdField_field_89_of_type_Class_934 = new class_934(a24(), 217, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.o(), "Name", new class_158(this));
    this.jdField_field_90_of_type_Class_934 = new class_934(a24(), 140, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.o(), "Owner", new class_188(this));
    this.field_92 = new class_934(a24(), 90, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.o(), "Price", new class_186(this));
    this.field_93 = new class_934(a24(), 50, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), class_29.o(), "Rating", new class_184(this));
    this.jdField_field_90_of_type_Class_934.a83().field_615 = (this.jdField_field_89_of_type_Class_934.a83().field_615 + 217.0F);
    this.field_92.a83().field_615 = (this.jdField_field_90_of_type_Class_934.a83().field_615 + 140.0F);
    this.field_93.a83().field_615 = (this.field_92.a83().field_615 + 90.0F);
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_89_of_type_Class_934);
    this.jdField_field_89_of_type_Class_1414.a9(this.jdField_field_90_of_type_Class_934);
    this.jdField_field_89_of_type_Class_1414.a9(this.field_92);
    this.jdField_field_89_of_type_Class_1414.a9(this.field_93);
    a9(this.jdField_field_89_of_type_Class_1414);
    a9(this.jdField_field_89_of_type_Class_968);
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_1414.a3() + this.jdField_field_89_of_type_Class_968.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_1414.b1();
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if ((paramObservable instanceof class_961)) {
      this.jdField_field_89_of_type_Class_964.f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_160
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */