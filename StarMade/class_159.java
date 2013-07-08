import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import javax.vecmath.Vector3f;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteInteger;

public final class class_159
  extends class_1363
  implements Observer
{
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 field_90;
  private class_163 jdField_field_89_of_type_Class_163;
  private class_187 jdField_field_89_of_type_Class_187;
  private boolean jdField_field_89_of_type_Boolean;
  private class_194 jdField_field_89_of_type_Class_194;
  private class_934 jdField_field_89_of_type_Class_934;
  
  public class_159(ClientState paramClientState)
  {
    super(paramClientState);
    paramClientState = this;
    this.jdField_field_89_of_type_Class_972 = new class_972(class_969.a2().a5("inventory-gui-"), (class_371)paramClientState.a24());
    paramClientState.field_90 = new class_972(class_969.a2().a5("sell-item-dropzone-gui-"), (class_371)paramClientState.a24());
    paramClientState.field_90.field_96 = true;
    paramClientState.jdField_field_89_of_type_Class_163 = new class_163((class_371)paramClientState.a24());
    paramClientState.jdField_field_89_of_type_Class_187 = new class_187((class_371)paramClientState.a24());
    a49().addObserver(this);
  }
  
  public final void a9(class_1363 paramclass_1363)
  {
    this.jdField_field_89_of_type_Class_972.a9(paramclass_1363);
  }
  
  public final void a2() {}
  
  public final void b2(class_1363 paramclass_1363)
  {
    this.jdField_field_89_of_type_Class_972.b2(paramclass_1363);
  }
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Boolean)
    {
      this.jdField_field_89_of_type_Class_972.b2(this.jdField_field_89_of_type_Class_187);
      this.jdField_field_89_of_type_Class_972.b2(this.jdField_field_89_of_type_Class_163);
      this.jdField_field_89_of_type_Class_972.b2(this.jdField_field_89_of_type_Class_194);
      if (a49().field_4 == null)
      {
        System.err.println("!!!!!!!!!!!!!!!!!!! NO SECOND INVENTORY");
        this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_187);
      }
      else
      {
        this.jdField_field_89_of_type_Class_163.a54(a49().field_4);
        this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_163);
        if ((a49().field_4 instanceof class_627)) {
          this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_194);
        }
      }
      this.jdField_field_89_of_type_Boolean = false;
    }
    GlUtil.d1();
    r();
    if ((((class_371)super.a24()).getDragging() != null) && (((class_371)super.a24()).d2()))
    {
      this.field_90.a161(-47.0F, 100.0F, 0.0F);
      this.field_90.b();
    }
    else
    {
      this.field_90.b21(false);
    }
    l();
    class_159 localclass_1591 = this;
    class_1418 localclass_1418;
    if (((this.field_90.field_615 < 208.0F) || (localclass_1591.field_90.field_616 < 24.0F) || (localclass_1591.field_90.field_615 > 816.0F) || (localclass_1591.field_90.field_616 > 512.0F)) && (!((class_371)localclass_1591.a24()).a27().a92().a18().a22().a_()) && ((localclass_1418 = ((class_371)localclass_1591.a24()).getDragging()) != null) && ((localclass_1418 instanceof class_185)))
    {
      Object localObject1;
      Object localObject2;
      if (localclass_1591.field_90.a_())
      {
        localObject1 = ((class_371)localclass_1591.a24()).getMouseEvents().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (class_939)((Iterator)localObject1).next();
          if (localclass_1418.a2((class_939)localObject2))
          {
            localObject2 = (class_185)((class_371)localclass_1591.a24()).getDragging();
            if ((localObject1 = ((class_371)localclass_1591.a24()).a5()) != null) {
              ((class_371)localclass_1591.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_303.a34(((class_185)localObject2).a70(), ((class_185)localObject2).a68(true), (class_743)localObject1);
            } else {
              ((class_371)localclass_1591.a24()).a4().b1("ERROR: not in shop dist");
            }
            localclass_1418.a5(false);
            ((class_371)localclass_1591.a24()).setDragging(null);
            break;
          }
        }
      }
      else
      {
        ((class_185)localclass_1418).f();
        localObject1 = ((class_371)localclass_1591.a24()).getMouseEvents().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (class_939)((Iterator)localObject1).next();
          if (localclass_1418.a2((class_939)localObject2))
          {
            System.err.println("CHECKING " + localclass_1591 + " " + localclass_1591.hashCode() + " MOUSE NO MORE GRABBED");
            int i = 0;
            int j;
            if (((localclass_1418 instanceof class_185)) && (localclass_1418 != localclass_1591))
            {
              if (System.currentTimeMillis() - localclass_1418.a4() > 50L)
              {
                System.err.println("NOW DROPPING " + localclass_1418);
                class_185 localclass_185 = (class_185)localclass_1418;
                class_159 localclass_1592 = localclass_1591;
                System.err.println("[INVENTORYGUI] ITEM " + localclass_185.a70() + " #" + localclass_185.a68(true) + " thrown into trash");
                ((class_371)localclass_1592.a24()).a20().a127().dropOrPickupSlots.add(new RemoteInteger(Integer.valueOf(localclass_185.c5()), ((class_371)localclass_1592.a24()).a20().a127()));
              }
              else
              {
                System.err.println("NO DROP: time dragged to short");
              }
              j = 1;
            }
            if (!(localclass_1418 instanceof class_185)) {
              System.err.println("NO DROP: not a target: " + localclass_1591);
            }
            if ((localclass_1418 == localclass_1591) && (localclass_1418.b()))
            {
              localclass_1418.a5(false);
              ((class_371)localclass_1591.a24()).setDragging(null);
            }
            if (j != 0) {
              ((class_371)localclass_1591.a24()).setDragging(null);
            }
          }
        }
      }
    }
    this.jdField_field_89_of_type_Class_972.b();
    GlUtil.c2();
  }
  
  public final void a48(class_185 paramclass_185)
  {
    class_185 localclass_185 = paramclass_185;
    class_163 localclass_163;
    (localclass_163 = this.jdField_field_89_of_type_Class_163).field_89.a48(localclass_185);
    localclass_163.field_90.a48(localclass_185);
    localclass_185 = paramclass_185;
    this.jdField_field_89_of_type_Class_187.field_89.a48(localclass_185);
  }
  
  public final void e()
  {
    GlUtil.d1();
    this.jdField_field_89_of_type_Class_972.r();
    class_163 localclass_163;
    (localclass_163 = this.jdField_field_89_of_type_Class_163).field_89.e();
    localclass_163.field_90.e();
    this.jdField_field_89_of_type_Class_187.field_89.e();
    GlUtil.c2();
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Class_972.a3();
  }
  
  public final class_469 a49()
  {
    return ((class_371)super.a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_469;
  }
  
  public final float b1()
  {
    return this.jdField_field_89_of_type_Class_972.b1();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_934 = new class_934((class_371)super.a24(), 100, 20, "Drop Credits", new class_189(this));
    this.field_90.c();
    this.jdField_field_89_of_type_Class_972.c();
    super.a9(this.jdField_field_89_of_type_Class_972);
    this.jdField_field_89_of_type_Class_163.c();
    this.jdField_field_89_of_type_Class_187.c();
    this.jdField_field_89_of_type_Class_194 = new class_194(class_969.a2().a5("buttons-8x8-gui-"), (class_371)super.a24(), class_319.field_663, "CONVERT", a49());
    this.jdField_field_89_of_type_Class_194.b28(0.35F, 0.35F, 0.35F);
    this.jdField_field_89_of_type_Class_194.a161(500.0F, 264.0F, 0.0F);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_187);
    this.jdField_field_89_of_type_Class_972.a9(this.jdField_field_89_of_type_Class_934);
    this.jdField_field_89_of_type_Class_934.a161(680.0F, 70.0F, 0.0F);
    this.jdField_field_89_of_type_Class_934.b17(7, 1);
    this.jdField_field_89_of_type_Class_163.a161(260.0F, 96.0F, 0.0F);
    this.jdField_field_89_of_type_Class_187.a161(260.0F, 96.0F, 0.0F);
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_159
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */