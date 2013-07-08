import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import org.schema.game.common.data.player.ShipConfigurationNotFoundException;

public class class_330
  extends class_16
  implements class_1412
{
  private class_796 field_4;
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    paramclass_939 = null;
    if ((this.field_6) && (!this.field_4))
    {
      paramclass_939 = a6().getMouseEvents().iterator();
      while (paramclass_939.hasNext())
      {
        Object localObject;
        if (((localObject = (class_939)paramclass_939.next()).jdField_field_1163_of_type_Int == 0) && (!((class_939)localObject).jdField_field_1163_of_type_Boolean) && ((paramclass_1363 instanceof class_959)))
        {
          class_964 localclass_964;
          int i = (localclass_964 = (class_964)(localObject = (class_959)paramclass_1363).a154()).indexOf(localObject);
          localclass_964.e();
          ((class_959)localObject).a29(true);
          System.err.println("Controller manager call back: index: " + i);
          this.field_4 = ((class_796)((class_959)localObject).a139().b29());
        }
      }
    }
  }
  
  public void handleKeyEvent()
  {
    super.handleKeyEvent();
    if ((Keyboard.getEventKeyState()) && (Keyboard.getEventKey() >= 2) && (Keyboard.getEventKey() <= 11))
    {
      int i = Keyboard.getEventKey() - 2;
      class_330 localclass_330 = this;
      if (this.field_4 != null)
      {
        if ((!field_7) && (localclass_330.a6().a25() == null)) {
          throw new AssertionError();
        }
        if (!localclass_330.a6().a20().a131(localclass_330.a6().a25())) {
          localclass_330.a6().a20().a75().add(new class_359(localclass_330.a6().a20(), localclass_330.a6().a25().getUniqueIdentifier()));
        }
        try
        {
          class_359 localclass_359 = localclass_330.a6().a20().a128(localclass_330.a6().a25());
          int j = -1;
          class_48 localclass_48 = localclass_330.field_4.a2(new class_48());
          if (localclass_359.a19(localclass_48))
          {
            j = localclass_359.a23(localclass_48);
            localclass_330.setChanged();
          }
          if (j != i)
          {
            System.err.println("ASSINGING: " + i + " to " + localclass_330.field_4);
            localclass_359.a20(i, localclass_48, true);
            localclass_330.setChanged();
          }
          localclass_330.notifyObservers(localclass_330.field_4);
          return;
        }
        catch (ShipConfigurationNotFoundException localShipConfigurationNotFoundException)
        {
          localShipConfigurationNotFoundException;
        }
      }
    }
  }
  
  public final boolean a1()
  {
    return !a6().b().isEmpty();
  }
  
  public final void b2(boolean paramBoolean)
  {
    class_1046.field_1306 = !paramBoolean;
    super.b2(paramBoolean);
    a6().a14().field_4.field_4.field_4.a51().a45().jdField_field_4_of_type_Class_332.e2(paramBoolean);
    a6().a14().field_4.field_4.field_4.a51().a45().jdField_field_4_of_type_Class_453.e2(paramBoolean);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    class_1046.field_1306 = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_330
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */