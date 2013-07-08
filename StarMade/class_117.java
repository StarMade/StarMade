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

public final class class_117
  extends class_1363
  implements Observer
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_968 jdField_field_89_of_type_Class_968;
  private class_964 jdField_field_89_of_type_Class_964;
  private boolean jdField_field_89_of_type_Boolean = true;
  private boolean field_90;
  private ConcurrentHashMap jdField_field_89_of_type_JavaUtilConcurrentConcurrentHashMap = new ConcurrentHashMap();
  
  public class_117(ClientState paramClientState)
  {
    super(paramClientState);
    class_117 localclass_117 = this;
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("panel-std-gui-"), localclass_117.a24());
    localclass_117.jdField_field_89_of_type_Class_968 = new class_968(512.0F, 366.0F, localclass_117.a24());
    localclass_117.jdField_field_89_of_type_Class_964 = new class_964(localclass_117.a24());
    localclass_117.jdField_field_89_of_type_Class_968.c7(localclass_117.jdField_field_89_of_type_Class_964);
    localclass_117.a9(localclass_117.jdField_field_89_of_type_Class_972);
    localclass_117.jdField_field_89_of_type_Class_972.a9(localclass_117.jdField_field_89_of_type_Class_968);
    localclass_117.jdField_field_89_of_type_Class_968.a161(280.0F, 64.0F, 0.0F);
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
    (localObject = new class_104(a24())).a15("NAME", "KILLS", "DEATHS", "PING", "TEAM");
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
    if (this.field_90)
    {
      class_117 localclass_117 = this;
      synchronized (a24().getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        Object localObject1;
        for (int j = 0; j < localclass_117.jdField_field_89_of_type_Class_964.size(); j++) {
          if (((localObject1 = (class_748)localclass_117.jdField_field_89_of_type_Class_964.a145(j).field_89) != null) && (!localclass_117.a24().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(((class_748)localObject1).getId())))
          {
            localclass_117.jdField_field_89_of_type_Class_964.b20(j);
            localclass_117.jdField_field_89_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localObject1);
            j--;
          }
        }
        Iterator localIterator = localclass_117.a24().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        while (localIterator.hasNext()) {
          if ((((localObject1 = (Sendable)localIterator.next()) instanceof class_748)) && (!localclass_117.jdField_field_89_of_type_JavaUtilConcurrentConcurrentHashMap.containsKey(localObject1))) {
            localclass_117.jdField_field_89_of_type_Class_964.a144(new class_106(localclass_117, (class_748)localObject1, localclass_117.a24()));
          }
        }
      }
      localclass_117.jdField_field_89_of_type_Class_968.e();
      this.field_90 = false;
    }
    for (int i = 0; i < this.jdField_field_89_of_type_Class_964.size(); i++) {
      this.jdField_field_89_of_type_Class_964.a145(i).a12(paramclass_941);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_117
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */