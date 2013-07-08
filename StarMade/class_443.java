import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.schema.common.FastMath;
import org.schema.game.common.controller.BlockedByDockedElementException;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.controller.ElementPositionBlockedException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.player.inventory.NoSlotFreeException;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.game.network.objects.NetworkSegmentController;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteShort;

public class class_443
  extends class_16
{
  private int jdField_field_4_of_type_Int;
  private HashMap jdField_field_4_of_type_JavaUtilHashMap;
  private class_342 jdField_field_4_of_type_Class_342;
  private class_447 jdField_field_4_of_type_Class_447;
  private class_431 jdField_field_4_of_type_Class_431;
  private class_797 jdField_field_4_of_type_Class_797;
  private class_459 jdField_field_4_of_type_Class_459;
  private int field_5 = 4;
  
  public class_443(class_371 paramclass_371)
  {
    super(paramclass_371);
    paramclass_371 = this;
    class_443 localclass_443 = this;
    this.jdField_field_4_of_type_JavaUtilHashMap = new HashMap();
    localclass_443.jdField_field_4_of_type_JavaUtilHashMap.put(Integer.valueOf(2), Integer.valueOf(0));
    localclass_443.jdField_field_4_of_type_JavaUtilHashMap.put(Integer.valueOf(3), Integer.valueOf(1));
    localclass_443.jdField_field_4_of_type_JavaUtilHashMap.put(Integer.valueOf(4), Integer.valueOf(2));
    localclass_443.jdField_field_4_of_type_JavaUtilHashMap.put(Integer.valueOf(5), Integer.valueOf(3));
    localclass_443.jdField_field_4_of_type_JavaUtilHashMap.put(Integer.valueOf(6), Integer.valueOf(4));
    localclass_443.jdField_field_4_of_type_JavaUtilHashMap.put(Integer.valueOf(7), Integer.valueOf(5));
    localclass_443.jdField_field_4_of_type_JavaUtilHashMap.put(Integer.valueOf(8), Integer.valueOf(6));
    localclass_443.jdField_field_4_of_type_JavaUtilHashMap.put(Integer.valueOf(9), Integer.valueOf(7));
    localclass_443.jdField_field_4_of_type_JavaUtilHashMap.put(Integer.valueOf(10), Integer.valueOf(8));
    localclass_443.jdField_field_4_of_type_JavaUtilHashMap.put(Integer.valueOf(11), Integer.valueOf(9));
    paramclass_371.jdField_field_4_of_type_Class_342 = new class_342(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_431 = new class_431(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_447 = new class_447(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_459 = new class_459(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_459);
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_342);
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_431);
    paramclass_371.jdField_field_4_of_type_JavaUtilHashSet.add(paramclass_371.jdField_field_4_of_type_Class_447);
  }
  
  public final int a48(EditableSendableSegmentController paramEditableSendableSegmentController, Vector3f paramVector3f1, Vector3f paramVector3f2, class_479 paramclass_479, class_713 paramclass_713, class_433 paramclass_433, float paramFloat)
  {
    if (!a6().a4().c4(paramEditableSendableSegmentController)) {
      return 0;
    }
    short s;
    class_48 localclass_48;
    if ((s = a54()) == 0)
    {
      a6().a4().d1("No element available to build!\nSelected slot is empty!");
      if (paramclass_433.jdField_field_795_of_type_Int != 0) {
        try
        {
          class_796 localclass_796 = paramEditableSendableSegmentController.getNextToNearestPiece(paramVector3f1, paramVector3f2, new class_48(), paramFloat, true, new class_48(), new class_48());
          if ((paramclass_433.jdField_field_795_of_type_Int > 0) && (localclass_796 != null))
          {
            localclass_48 = localclass_796.a2(new class_48());
            switch (paramclass_433.jdField_field_795_of_type_Int)
            {
            case 1: 
              System.err.println("SYM XY PLANE SET");
              paramclass_433.jdField_field_795_of_type_Class_48.field_477 = localclass_48.field_477;
              paramclass_433.jdField_field_795_of_type_Boolean = true;
              break;
            case 2: 
              System.err.println("SYM XZ PLANE SET");
              paramclass_433.jdField_field_796_of_type_Class_48.field_476 = localclass_48.field_476;
              paramclass_433.jdField_field_796_of_type_Boolean = true;
              break;
            case 4: 
              System.err.println("SYM YZ PLANE SET");
              paramclass_433.jdField_field_797_of_type_Class_48.field_475 = localclass_48.field_475;
              paramclass_433.jdField_field_797_of_type_Boolean = true;
            }
            paramclass_433.jdField_field_795_of_type_Int = 0;
          }
        }
        catch (ElementPositionBlockedException localElementPositionBlockedException2)
        {
          localElementPositionBlockedException2;
        }
        catch (BlockedByDockedElementException localBlockedByDockedElementException2)
        {
          localBlockedByDockedElementException2;
        }
      }
      return 0;
    }
    if (a6().a20().getInventory(null).a18(this.jdField_field_4_of_type_Int))
    {
      a6().a4().d1("No element available to build!\nSelected slot is empty!");
      return 0;
    }
    if ((!field_7) && (a6().a20().getInventory(null).a41(this.jdField_field_4_of_type_Int) <= 0)) {
      throw new AssertionError();
    }
    int i = a6().a20().getInventory(null).a41(this.jdField_field_4_of_type_Int);
    try
    {
      localclass_48 = new class_48(1, 1, 1);
      if (class_367.field_758.a6()) {
        localclass_48.b1(this.jdField_field_4_of_type_Class_459.a35());
      }
      localObject = ElementKeyMap.getInfo(s);
      boolean bool = true;
      int j;
      if ((j = this.field_5) > 7) {
        if (((ElementInformation)localObject).getBlockStyle() == 1)
        {
          j -= 8;
          bool = false;
          System.err.println("[CLIENT][PLACEBLOCK] BLOCK ORIENTATION (1) EXTENSION: " + (j + 4) + " -> " + j);
        }
        else if (((ElementInformation)localObject).getBlockStyle() == 2)
        {
          j -= 8;
          bool = false;
          System.err.println("[CLIENT][PLACEBLOCK] BLOCK ORIENTATION (2) EXTENSION: " + (j + 8) + " -> " + j);
        }
        else
        {
          System.err.println("[CLIENT][PLACEBLOCK] Exception: Block orientation was set over 8 on block style " + localObject + ": " + j);
          j = 0;
        }
      }
      if ((paramclass_479 = paramEditableSendableSegmentController.getNearestIntersection(s, paramVector3f1, paramVector3f2, paramclass_479, j, bool, paramclass_713, localclass_48, i, paramFloat, paramclass_433)) > 0)
      {
        paramclass_713 = ((class_371)paramEditableSendableSegmentController.getState()).a20().getInventory(null);
        try
        {
          paramclass_713.a47(this.jdField_field_4_of_type_Int, s, -paramclass_479);
          a6().a20().sendInventoryModification(this.jdField_field_4_of_type_Int, null);
          class_969.b("0022_action - buttons push big");
          if (s == 56) {
            a6().a4().d1("INFO:\nUse gravity when you are\noutside the ship. Look at\ngravity module and press 'R'");
          }
          paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.forceClientUpdates();
          paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.set(a6().a20().getId());
          return paramclass_479;
        }
        catch (NoSlotFreeException localNoSlotFreeException)
        {
          localNoSlotFreeException.printStackTrace();
          System.err.println("[WARNING] ILLEGIT BLOCK WITH SLOT " + this.jdField_field_4_of_type_Int);
          paramEditableSendableSegmentController.getNearestIntersectingElementPosition(paramVector3f1, paramVector3f2, new class_48(1, 1, 1), paramFloat, new class_441(), paramclass_433);
        }
      }
    }
    catch (ElementPositionBlockedException localElementPositionBlockedException1)
    {
      Object localObject = "unknown";
      if (localElementPositionBlockedException1.field_1893 != null) {
        if ((localElementPositionBlockedException1.field_1893 instanceof SegmentController)) {
          localObject = ((SegmentController)localElementPositionBlockedException1.field_1893).getRealName();
        } else if ((localElementPositionBlockedException1.field_1893 instanceof class_797)) {
          localObject = ((class_797)localElementPositionBlockedException1.field_1893).toNiceString();
        }
      }
      a6().a4().b1("Cannot build here!\nPosition is blocked by\n" + (String)localObject);
    }
    catch (BlockedByDockedElementException localBlockedByDockedElementException1)
    {
      a6().a27().a90().a27(localBlockedByDockedElementException1.field_1785);
      a6().a4().b1("Cannot build here!\nPosition blocked\nby active docking area!");
    }
    return 0;
  }
  
  public final void a49(EditableSendableSegmentController paramEditableSendableSegmentController, Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, class_433 paramclass_433)
  {
    if (!a6().a4().c4(paramEditableSendableSegmentController)) {
      return;
    }
    class_48 localclass_48 = new class_48(1, 1, 1);
    if (class_367.field_758.a6()) {
      localclass_48.b1(this.jdField_field_4_of_type_Class_459.a35());
    }
    IntOpenHashSet localIntOpenHashSet = new IntOpenHashSet(localclass_48.field_475 * localclass_48.field_476 * localclass_48.field_477);
    paramEditableSendableSegmentController.getNearestIntersectingElementPosition(paramVector3f1, paramVector3f2, localclass_48, paramFloat, new class_439(this, paramEditableSendableSegmentController, localIntOpenHashSet), paramclass_433);
    class_969.b("0022_action - buttons push medium");
    a6().a20().sendInventoryModification(localIntOpenHashSet, null);
    paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.forceClientUpdates();
    paramEditableSendableSegmentController.getNetworkObject().lastModifiedPlayerId.set(a6().a20().getId());
  }
  
  public final int a28()
  {
    return this.field_5;
  }
  
  public final class_459 a50()
  {
    return this.jdField_field_4_of_type_Class_459;
  }
  
  public final class_342 a51()
  {
    return this.jdField_field_4_of_type_Class_342;
  }
  
  public final class_431 a52()
  {
    return this.jdField_field_4_of_type_Class_431;
  }
  
  public final class_447 a53()
  {
    return this.jdField_field_4_of_type_Class_447;
  }
  
  public final class_797 a43()
  {
    return this.jdField_field_4_of_type_Class_797;
  }
  
  public final int b6()
  {
    return this.jdField_field_4_of_type_Int;
  }
  
  public final short a54()
  {
    return a6().a20().getInventory(null).a45(this.jdField_field_4_of_type_Int);
  }
  
  public final int a55(int paramInt)
  {
    if (this.jdField_field_4_of_type_JavaUtilHashMap.containsKey(Integer.valueOf(paramInt))) {
      return ((Integer)this.jdField_field_4_of_type_JavaUtilHashMap.get(Integer.valueOf(paramInt))).intValue();
    }
    System.err.println("[WARNING] UNKNOWN SLOT KEY: " + paramInt + ": " + this.jdField_field_4_of_type_JavaUtilHashMap);
    return -1;
  }
  
  public void handleKeyEvent()
  {
    if (class_12.b1()) {
      return;
    }
    synchronized (a6().b())
    {
      int i;
      if ((i = a6().b().size()) > 0)
      {
        ((class_12)a6().b().get(i - 1)).handleKeyEvent();
        return;
      }
    }
    if (Keyboard.getEventKeyState())
    {
      if (a6().b().isEmpty())
      {
        if (Keyboard.getEventKey() == class_367.field_725.a5()) {
          a6().a20().a127().dropOrPickupSlots.add(new RemoteInteger(Integer.valueOf(this.jdField_field_4_of_type_Int), a6().a20().a127()));
        }
        if (Keyboard.getEventKey() == class_367.field_749.a5())
        {
          d3(1);
        }
        else if (Keyboard.getEventKey() == class_367.field_750.a5())
        {
          d3(-1);
        }
        else if (Keyboard.getEventKey() == class_367.field_751.a5())
        {
          ??? = this;
          Vector3f localVector3f1 = new Vector3f();
          Vector3f localVector3f2 = new Vector3f();
          float f = 0.0F;
          if (((class_443)???).a6().a6() != null) {
            localVector3f1.set(((class_443)???).a6().a6().getWorldTransform().origin);
          } else {
            localVector3f1.set(class_969.a1().a83());
          }
          Object localObject2 = null;
          Iterator localIterator = ((class_443)???).a6().a7().values().iterator();
          while (localIterator.hasNext())
          {
            class_797 localclass_797 = (class_797)localIterator.next();
            localVector3f2.sub(localclass_797.getWorldTransformClient().origin, localVector3f1);
            if ((localclass_797 != ((class_443)???).a6().a6()) && ((localObject2 == null) || (localVector3f2.length() < f)))
            {
              System.err.println("!!!!!!!!!NEAREST IS NOW " + localclass_797);
              localObject2 = localclass_797;
              f = localVector3f2.length();
            }
          }
          ((class_443)???).jdField_field_4_of_type_Class_797 = localObject2;
        }
        else if (Keyboard.getEventKey() == class_367.field_752.a5())
        {
          c1();
        }
      }
      ??? = this;
      if ((!this.jdField_field_4_of_type_Boolean) && (((class_16)???).field_5))
      {
        if ((!field_7) && (!((class_443)???).a6().b().isEmpty())) {
          throw new AssertionError();
        }
        ((class_443)???).a6().a20().a127().handleKeyEvent(Keyboard.getEventKeyState(), Keyboard.getEventKey());
      }
    }
    if (a6().b().isEmpty()) {
      super.handleKeyEvent();
    }
  }
  
  public final void b()
  {
    class_969.a1().getWorldTransform();
    if (a6().a25() != null) {
      a6().a25().getWorldTransform();
    } else {
      new Transform().setIdentity();
    }
    if (Keyboard.getEventKey() == 52)
    {
      c3((this.field_5 + 1) % 8);
      short s;
      ElementInformation localElementInformation;
      if (((s = a54()) != 0) && ((localElementInformation = ElementKeyMap.getInfo(s)).getBlockStyle() > 0)) {
        System.err.println("BLOCK ORIENTATION: " + this.field_5 + "; " + class_384.field_102[(localElementInformation.getBlockStyle() - 1)][org.schema.game.common.data.element.Element.orientationMapping[this.field_5]].toString());
      }
    }
  }
  
  public final void a12(class_939 paramclass_939)
  {
    if ((a6().a27().a92().a5()) || (class_12.b1()) || (a6().b().size() > 0)) {
      return;
    }
    if ((class_949.field_1242.a4().equals("SLOTS")) && (!Keyboard.isKeyDown(42))) {
      this.jdField_field_4_of_type_Int = FastMath.b1(this.jdField_field_4_of_type_Int + (paramclass_939.field_1168 < 0 ? -1 : paramclass_939.field_1168 > 0 ? 1 : 0), 10);
    }
    super.a12(paramclass_939);
  }
  
  public final boolean a1()
  {
    if (this.jdField_field_4_of_type_JavaUtilHashMap.containsKey(Integer.valueOf(Keyboard.getEventKey())))
    {
      int i = a54();
      this.jdField_field_4_of_type_Int = ((Integer)this.jdField_field_4_of_type_JavaUtilHashMap.get(Integer.valueOf(Keyboard.getEventKey()))).intValue();
      if (i != a54()) {
        c3(4);
      }
      System.err.println("Selected slot is now: " + this.jdField_field_4_of_type_Int);
      return true;
    }
    return false;
  }
  
  protected final void a14(boolean paramBoolean)
  {
    super.a14(paramBoolean);
    if (paramBoolean) {
      a6().a20().a127().keyboardOfController.set(Short.valueOf((short)0));
    }
  }
  
  public final void b2(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if ((!this.jdField_field_4_of_type_Class_342.field_5) && (!this.jdField_field_4_of_type_Class_431.field_5) && (!this.jdField_field_4_of_type_Class_431.d1()) && (!this.jdField_field_4_of_type_Class_342.d1()))
      {
        if ((!field_7) && (a6().a3() == null)) {
          throw new AssertionError();
        }
        this.jdField_field_4_of_type_Class_431.d2(true);
      }
    }
    else {
      a6().a20().a127().keyboardOfController.set(Short.valueOf((short)0));
    }
    super.b2(paramBoolean);
  }
  
  private void d3(int paramInt)
  {
    ObjectIterator localObjectIterator = a6().a7().values().iterator();
    int i = paramInt > 0 ? 2147483647 : -2147483648;
    int j = this.jdField_field_4_of_type_Class_797 != null ? this.jdField_field_4_of_type_Class_797.getId() : -1;
    class_797 localclass_797;
    while (localObjectIterator.hasNext())
    {
      localclass_797 = (class_797)localObjectIterator.next();
      if (this.jdField_field_4_of_type_Class_797 == null)
      {
        e3(-paramInt);
        return;
      }
      if ((paramInt > 0) && (localclass_797.getId() > j) && (localclass_797.getId() < i)) {
        i = localclass_797.getId();
      }
      if ((paramInt <= 0) && (localclass_797.getId() < j) && (localclass_797.getId() > i)) {
        i = localclass_797.getId();
      }
    }
    if ((i == 2147483647) || (i == -2147483648))
    {
      e3(paramInt);
      return;
    }
    if (a6().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(i))
    {
      localclass_797 = (class_797)a6().getLocalAndRemoteObjectContainer().getLocalObjects().get(i);
      this.jdField_field_4_of_type_Class_797 = localclass_797;
    }
  }
  
  private void e3(int paramInt)
  {
    ObjectIterator localObjectIterator = a6().a7().values().iterator();
    class_797 localclass_797;
    for (int i = paramInt > 0 ? 2147483647 : -2147483648; localObjectIterator.hasNext(); i = localclass_797.getId())
    {
      localclass_797 = (class_797)localObjectIterator.next();
      if ((paramInt > 0) && (localclass_797.getId() < i)) {
        i = localclass_797.getId();
      }
      if ((paramInt > 0) || (localclass_797.getId() <= i)) {}
    }
    if (a6().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(i))
    {
      localclass_797 = (class_797)a6().getLocalAndRemoteObjectContainer().getLocalObjects().get(i);
      this.jdField_field_4_of_type_Class_797 = localclass_797;
    }
  }
  
  private void c1()
  {
    if ((a6().a23() == null) || (a6().a23().a26() == null)) {
      return;
    }
    class_1387 localclass_1387 = a6().a23().a26();
    Vector3f localVector3f1 = new Vector3f();
    float f = 0.0F;
    Object localObject = null;
    Vector3f localVector3f2 = localclass_1387.a1(new Vector3f());
    Iterator localIterator = a6().a7().values().iterator();
    while (localIterator.hasNext())
    {
      class_797 localclass_797;
      if ((localclass_797 = (class_797)localIterator.next()) != a6().a6())
      {
        localVector3f1.set(localclass_797.getWorldTransform().origin);
        if (a6().a6() != null) {
          localVector3f1.sub(a6().a6().getWorldTransform().origin);
        } else {
          localVector3f1.sub(class_969.a1().a83());
        }
        Vector3f localVector3f3 = localclass_1387.a2(localclass_797.getWorldTransformClient().origin, new Vector3f(), true);
        Vector3f localVector3f4;
        (localVector3f4 = new Vector3f()).sub(localVector3f3, localVector3f2);
        if ((localVector3f4.length() < 250.0F) && ((localObject == null) || (localVector3f4.length() < f)))
        {
          localObject = localclass_797;
          f = localVector3f4.length();
        }
      }
    }
    this.jdField_field_4_of_type_Class_797 = localObject;
  }
  
  public final void c3(int paramInt)
  {
    System.err.println("SET ORIENTATION " + paramInt);
    this.field_5 = paramInt;
  }
  
  public final void a56(class_797 paramclass_797)
  {
    this.jdField_field_4_of_type_Class_797 = paramclass_797;
  }
  
  public final void a15(class_941 paramclass_941)
  {
    super.a15(paramclass_941);
    if (a6().b().isEmpty()) {
      a6().a20().a13();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_443
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */