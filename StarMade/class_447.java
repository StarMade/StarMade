import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ControlElementMapper;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.network.NetworkStateContainer;

public class class_447
  extends class_16
  implements class_457
{
  private Object jdField_field_4_of_type_JavaLangObject = new Object();
  private class_796 jdField_field_4_of_type_Class_796;
  private class_434 jdField_field_4_of_type_Class_434;
  private class_453 jdField_field_4_of_type_Class_453;
  
  public class_447(class_371 paramclass_371)
  {
    super(paramclass_371);
    paramclass_371 = this;
    this.jdField_field_4_of_type_Class_434 = new class_434(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_453 = new class_453(paramclass_371.a6(), paramclass_371);
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_434);
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_453);
  }
  
  private void b()
  {
    synchronized (this.jdField_field_4_of_type_JavaLangObject)
    {
      class_48 localclass_48 = null;
      if (this.jdField_field_4_of_type_Class_796 != null)
      {
        SegmentController localSegmentController = this.jdField_field_4_of_type_Class_796.a7().a15();
        localclass_48 = this.jdField_field_4_of_type_Class_796.a2(new class_48());
        System.err.println("EXIT SHIP FROM EXTRYPOINT " + localclass_48);
        a6().a4().a14((class_365)localSegmentController, a6().a3(), localclass_48, new class_48(), true);
      }
      return;
    }
  }
  
  public final class_48 a35()
  {
    return this.jdField_field_4_of_type_Class_796.a2(new class_48());
  }
  
  public final class_796 a40()
  {
    return this.jdField_field_4_of_type_Class_796;
  }
  
  public final class_453 a36()
  {
    return this.jdField_field_4_of_type_Class_453;
  }
  
  public final EditableSendableSegmentController a37()
  {
    return (EditableSendableSegmentController)this.jdField_field_4_of_type_Class_796.a7().a15();
  }
  
  public final class_434 a67()
  {
    return this.jdField_field_4_of_type_Class_434;
  }
  
  public void handleKeyEvent()
  {
    super.handleKeyEvent();
    if ((Keyboard.getEventKeyState()) && (Keyboard.getEventKey() == class_367.field_732.a5())) {
      b();
    }
  }
  
  public final void b2(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (this.jdField_field_4_of_type_Class_796.a7() == null)
      {
        System.err.println("Exception: entered has been null");
        if ((!field_7) && (this.jdField_field_4_of_type_Class_796 == null)) {
          throw new AssertionError();
        }
        return;
      }
      Object localObject;
      class_846 localclass_846 = (localObject = this.jdField_field_4_of_type_Class_796.a7().a15()).getControlElementMap().getControlledElements((short)32767, this.jdField_field_4_of_type_Class_796.a2(new class_48()));
      if (!a6().a20().a131((SegmentController)localObject))
      {
        class_359 localclass_359 = new class_359(a6().a20(), ((SegmentController)localObject).getUniqueIdentifier());
        int i = 0;
        try
        {
          localclass_48 = new class_48();
          localObject = ((SegmentController)localObject).getControlElementMap().getControllingMap().keySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            long l = ((Long)((Iterator)localObject).next()).longValue();
            if (localclass_846.field_1094.contains(ElementCollection.getPosFromIndex(l, localclass_48))) {
              localclass_359.a20(i, new class_48(localclass_48), true);
            }
            if (i > 10) {
              break;
            }
            i++;
          }
        }
        catch (ConcurrentModificationException localConcurrentModificationException)
        {
          class_48 localclass_48 = null;
          localConcurrentModificationException.printStackTrace();
        }
        a6().a20().a75().add(localclass_359);
      }
      if (this.jdField_field_4_of_type_Class_796.a9() == 123) {
        this.jdField_field_4_of_type_Class_453.c2(true);
      } else {
        this.jdField_field_4_of_type_Class_434.c2(true);
      }
      this.jdField_field_4_of_type_Class_796.a7().a15();
      this.jdField_field_4_of_type_Class_796.a2(new class_48());
    }
    else
    {
      if ((this.jdField_field_4_of_type_Class_796 != null) && (this.jdField_field_4_of_type_Class_796.a7().a15() == a6().a6())) {
        a6().a32(null);
      }
      this.jdField_field_4_of_type_Class_434.c2(false);
      this.jdField_field_4_of_type_Class_453.c2(false);
    }
    if ((!field_7) && (paramBoolean) && (this.jdField_field_4_of_type_Class_796 == null)) {
      throw new AssertionError(": Entered: " + this.jdField_field_4_of_type_Class_796.a7().a15() + " -> " + this.jdField_field_4_of_type_Class_796.a2(new class_48()));
    }
    super.b2(paramBoolean);
  }
  
  public final void a16(class_796 paramclass_796)
  {
    synchronized (this.jdField_field_4_of_type_JavaLangObject)
    {
      this.jdField_field_4_of_type_Class_796 = paramclass_796;
      return;
    }
  }
  
  public final void a15(class_941 arg1)
  {
    super.a15(???);
    if (!a6().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_field_4_of_type_Class_796.a7().a15().getId())) {
      b();
    }
    synchronized (this.jdField_field_4_of_type_JavaLangObject)
    {
      if (this.jdField_field_4_of_type_Class_796 != null)
      {
        this.jdField_field_4_of_type_Class_796.a12();
        if (this.jdField_field_4_of_type_Class_796.a9() == 0)
        {
          Object localObject1 = this.jdField_field_4_of_type_Class_796.a2(new class_48());
          try
          {
            if ((localObject1 = this.jdField_field_4_of_type_Class_796.a7().a15().getSegmentBuffer().a9((class_48)localObject1, true)).a9() == 0) {
              b();
            } else {
              this.jdField_field_4_of_type_Class_796 = ((class_796)localObject1);
            }
          }
          catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {}
        }
        if (!a6().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(this.jdField_field_4_of_type_Class_796.a7().a15().getId())) {
          b();
        }
      }
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_447
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */