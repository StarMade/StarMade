import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.Sendable;

public final class class_242
  extends class_1363
  implements Observer
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_968 jdField_field_89_of_type_Class_968;
  private class_964 jdField_field_89_of_type_Class_964;
  private boolean jdField_field_89_of_type_Boolean = true;
  private boolean field_90;
  private ConcurrentHashMap jdField_field_89_of_type_JavaUtilConcurrentConcurrentHashMap = new ConcurrentHashMap();
  private float jdField_field_89_of_type_Float;
  
  public class_242(ClientState paramClientState)
  {
    super(paramClientState);
    class_242 localclass_242 = this;
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("panel-std-gui-"), localclass_242.a24());
    localclass_242.jdField_field_89_of_type_Class_968 = new class_968(800.0F, 366.0F, localclass_242.a24());
    localclass_242.jdField_field_89_of_type_Class_964 = new class_964(localclass_242.a24());
    localclass_242.jdField_field_89_of_type_Class_968.c7(localclass_242.jdField_field_89_of_type_Class_964);
    localclass_242.a9(localclass_242.jdField_field_89_of_type_Class_972);
    localclass_242.jdField_field_89_of_type_Class_972.a9(localclass_242.jdField_field_89_of_type_Class_968);
    localclass_242.jdField_field_89_of_type_Class_968.a161(280.0F, 64.0F, 0.0F);
    ((class_371)paramClientState).addObserver(this);
  }
  
  public final void a2() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean) {
      c();
    }
    GlUtil.d1();
    r();
    this.jdField_field_89_of_type_Class_972.b();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_89_of_type_Class_968.c();
    this.jdField_field_89_of_type_Boolean = false;
    (localObject = new class_190(a24())).a15("ID", "NAME", "TYPE", "sector", "...");
    Object localObject = new class_959((class_1363)localObject, (class_1363)localObject, a24());
    this.jdField_field_89_of_type_Class_964.a144((class_959)localObject);
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    this.field_90 = true;
  }
  
  public final void a12(class_941 paramclass_941)
  {
    super.a12(paramclass_941);
    this.jdField_field_89_of_type_Float += paramclass_941.a();
    if (this.jdField_field_89_of_type_Float > 1.0F)
    {
      this.field_90 = true;
      this.jdField_field_89_of_type_Float = 0.0F;
    }
    if (this.field_90)
    {
      class_242 localclass_242 = this;
      synchronized (a24().getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        localclass_242.jdField_field_89_of_type_Class_964.clear();
        localclass_242.jdField_field_89_of_type_JavaUtilConcurrentConcurrentHashMap.clear();
        Iterator localIterator = localclass_242.a24().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        while (localIterator.hasNext())
        {
          Sendable localSendable = (Sendable)localIterator.next();
          if (!localclass_242.jdField_field_89_of_type_JavaUtilConcurrentConcurrentHashMap.containsKey(localSendable)) {
            localclass_242.jdField_field_89_of_type_Class_964.a144(new class_240(localclass_242, localSendable, localclass_242.a24()));
          }
        }
      }
      localclass_242.jdField_field_89_of_type_Class_968.e();
      this.field_90 = false;
    }
    for (int i = 0; i < this.jdField_field_89_of_type_Class_964.size(); i++) {
      this.jdField_field_89_of_type_Class_964.a145(i).a12(paramclass_941);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_242
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */