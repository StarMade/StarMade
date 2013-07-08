import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.schema.common.FastMath;
import org.schema.game.common.controller.CannotBeControlledException;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.controller.SegmentBufferManager;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.controller.elements.ManagerModule;
import org.schema.game.common.controller.elements.UsableControllableElementManager;
import org.schema.game.common.controller.elements.UsableControllableSingleElementManager;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ControlElementMapper;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_453
  extends class_485
{
  private boolean jdField_field_7_of_type_Boolean;
  private final class_433 jdField_field_4_of_type_Class_433 = new class_433();
  private class_243 jdField_field_4_of_type_Class_243;
  private class_457 jdField_field_4_of_type_Class_457;
  private class_796 jdField_field_4_of_type_Class_796;
  private CollisionWorld.ClosestRayResultCallback jdField_field_4_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback;
  private class_48 jdField_field_4_of_type_Class_48;
  private boolean field_132;
  private ArrayList jdField_field_4_of_type_JavaUtilArrayList;
  private int jdField_field_4_of_type_Int;
  private Segment jdField_field_4_of_type_OrgSchemaGameCommonDataWorldSegment;
  private boolean field_133;
  private SegmentController jdField_field_4_of_type_OrgSchemaGameCommonControllerSegmentController;
  private long jdField_field_5_of_type_Long;
  private class_713 jdField_field_4_of_type_Class_713;
  private int jdField_field_5_of_type_Int;
  private int field_6;
  private int jdField_field_7_of_type_Int;
  private ElementCollectionManager jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager;
  private ArrayList jdField_field_5_of_type_JavaUtilArrayList;
  
  public class_453(class_371 paramclass_371, class_457 paramclass_457)
  {
    super(paramclass_371);
    new Matrix4f();
    new Matrix4f();
    this.jdField_field_4_of_type_Class_48 = new class_48();
    this.jdField_field_4_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_5_of_type_Int = 0;
    this.field_6 = 0;
    this.jdField_field_7_of_type_Int = 0;
    this.jdField_field_4_of_type_Class_457 = paramclass_457;
  }
  
  public final SegmentController a68()
  {
    return this.jdField_field_4_of_type_Class_457.a1();
  }
  
  public final class_796 a40()
  {
    return this.jdField_field_4_of_type_Class_796;
  }
  
  private void a69(int paramInt, boolean paramBoolean)
  {
    class_798 localclass_798;
    Object localObject;
    for (;;)
    {
      localclass_798 = (class_798)a68();
      if (paramInt == -2147483648)
      {
        a6().a27().a99().a39(null, null);
        return;
      }
      if (paramBoolean)
      {
        this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
        this.jdField_field_5_of_type_Int = 0;
      }
      if (this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager != null) {
        break;
      }
      this.field_6 = FastMath.b1(this.field_6 + paramInt, localclass_798.a().getModules().size());
      if ((this.jdField_field_5_of_type_JavaUtilArrayList != null) && (this.jdField_field_7_of_type_Int < this.jdField_field_5_of_type_JavaUtilArrayList.size()) && (!paramBoolean))
      {
        this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = ((ElementCollectionManager)this.jdField_field_5_of_type_JavaUtilArrayList.get(this.jdField_field_7_of_type_Int));
        this.jdField_field_7_of_type_Int += 1;
      }
      else
      {
        this.jdField_field_7_of_type_Int = 0;
        this.jdField_field_5_of_type_JavaUtilArrayList = null;
        if (((localObject = (ManagerModule)localclass_798.a().getModules().get(this.field_6)).getElementManager() instanceof UsableControllableElementManager))
        {
          localObject = (UsableControllableElementManager)((ManagerModule)localObject).getElementManager();
          this.jdField_field_5_of_type_JavaUtilArrayList = ((UsableControllableElementManager)localObject).getCollectionManagers();
          if (((UsableControllableElementManager)localObject).getCollectionManagers().size() > 0)
          {
            this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = ((ElementCollectionManager)((UsableControllableElementManager)localObject).getCollectionManagers().get(0));
            break label260;
          }
          this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
          continue;
        }
        if ((((ManagerModule)localObject).getElementManager() instanceof UsableControllableSingleElementManager))
        {
          localObject = (UsableControllableSingleElementManager)((ManagerModule)localObject).getElementManager();
          this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = ((UsableControllableSingleElementManager)localObject).getCollection();
        }
      }
      label260:
      if ((this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager == null) || (this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.getCollection().size() != 0)) {
        break;
      }
      this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
    }
    if ((this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager != null) && (this.jdField_field_5_of_type_Int < this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.getCollection().size()) && (this.jdField_field_5_of_type_Int >= 0))
    {
      localObject = (ElementCollection)this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.getCollection().get(this.jdField_field_5_of_type_Int);
      if (Keyboard.isKeyDown(71)) {
        a6().a27().a99().a39(this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager.rawCollection, (class_797)localclass_798);
      } else {
        a6().a27().a99().a39(((ElementCollection)localObject).getNeighboringCollection(), (class_797)localclass_798);
      }
      this.jdField_field_5_of_type_Int = FastMath.b1(this.jdField_field_5_of_type_Int + paramInt, localclass_798.a().getModules().size());
      return;
    }
    this.jdField_field_5_of_type_Int = 0;
    this.jdField_field_4_of_type_OrgSchemaGameCommonControllerElementsElementCollectionManager = null;
  }
  
  public void handleKeyEvent()
  {
    if (Keyboard.getEventKeyState())
    {
      class_48 localclass_48 = null;
      Keyboard.getEventKey();
      if (a6().a14().field_4.field_4.field_4.a1()) {
        return;
      }
      if (Keyboard.getEventKey() == 73) {
        a69(1, false);
      }
      if (Keyboard.getEventKey() == 72) {
        a69(-1, false);
      }
      if (Keyboard.getEventKey() == 77) {
        a69(1, true);
      }
      if (Keyboard.getEventKey() == 76) {
        a69(-1, true);
      }
      if (Keyboard.getEventKey() == 80) {
        a69(-2147483648, true);
      }
      if (Keyboard.getEventKey() == class_367.field_738.a5())
      {
        this.jdField_field_7_of_type_Boolean = (!this.jdField_field_7_of_type_Boolean);
      }
      else
      {
        class_453 localclass_453;
        if (Keyboard.getEventKey() == class_367.field_736.a5())
        {
          localclass_453 = this;
          if ((this.field_132) && (localclass_453.jdField_field_4_of_type_Class_48 != null)) {
            if ((localclass_453.jdField_field_4_of_type_Class_796 != null) && (localclass_453.jdField_field_4_of_type_Class_796.a2(new class_48()).equals(localclass_453.jdField_field_4_of_type_Class_48))) {
              localclass_453.jdField_field_4_of_type_Class_796 = null;
            } else {
              try
              {
                localclass_453.jdField_field_4_of_type_Class_796 = localclass_453.jdField_field_4_of_type_Class_457.a1().getSegmentBuffer().a9(localclass_453.jdField_field_4_of_type_Class_48, true);
              }
              catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException1)
              {
                localCannotImmediateRequestOnClientException1;
              }
            }
          }
        }
        else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == class_367.field_755.a5()))
        {
          f2(true);
        }
        else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == class_367.field_756.a5()))
        {
          f2(false);
        }
        else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == class_367.field_757.a5()))
        {
          localclass_453 = this;
          if (!this.jdField_field_4_of_type_JavaUtilArrayList.isEmpty()) {
            try
            {
              localclass_453.jdField_field_4_of_type_Class_796 = localclass_453.jdField_field_4_of_type_Class_457.a1().getSegmentBuffer().a9(localclass_453.jdField_field_4_of_type_Class_457.a(), true);
              localclass_453.jdField_field_4_of_type_Int = Math.max(0, localclass_453.jdField_field_4_of_type_JavaUtilArrayList.indexOf(localclass_453.jdField_field_4_of_type_Class_457.a()));
            }
            catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException2)
            {
              localCannotImmediateRequestOnClientException2;
            }
          }
        }
        else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == class_367.field_730.a5()))
        {
          this.jdField_field_4_of_type_Class_243.a84(new class_48(8, 8, 0));
        }
        else if ((!Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == class_367.field_737.a5()))
        {
          localclass_453 = this;
          System.err.println("[CLIENT][SEGBUILDCONTROLLER] NORMAL CONNECTION");
          try
          {
            localclass_453.jdField_field_4_of_type_Class_457.a1().setCurrentBlockController(localclass_453.jdField_field_4_of_type_Class_796, localclass_453.jdField_field_4_of_type_Class_48);
          }
          catch (CannotBeControlledException localCannotBeControlledException1)
          {
            localclass_453.a81(localCannotBeControlledException1);
          }
        }
        else if ((Keyboard.isKeyDown(42)) && (Keyboard.getEventKey() == class_367.field_737.a5()))
        {
          localclass_453 = this;
          System.err.println("[CLIENT][SEGBUILDCONTROLLER] BULK CONNECTION");
          try
          {
            Object localObject = ((SegmentBufferManager)localclass_453.jdField_field_4_of_type_Class_457.a1().getSegmentBuffer()).a30(localclass_453.jdField_field_4_of_type_Class_48);
            new class_48();
            if ((localclass_453.jdField_field_4_of_type_Class_796 != null) && (localObject != null) && (((class_756)localObject).jdField_field_1016_of_type_JavaUtilArrayList.size() > 0))
            {
              System.err.println("[CLIENT][SEGBUILDCONTROLLER] BULK CONNECTING " + ((class_756)localObject).jdField_field_1016_of_type_JavaUtilArrayList.size() + " elements of type " + ((class_756)localObject).jdField_field_1016_of_type_Short);
              boolean bool = localclass_453.jdField_field_4_of_type_Class_457.a1().getControlElementMap().isControlling(localclass_453.jdField_field_4_of_type_Class_796.a2(new class_48()), localclass_453.jdField_field_4_of_type_Class_48, ((class_756)localObject).jdField_field_1016_of_type_Short);
              localObject = ((class_756)localObject).jdField_field_1016_of_type_JavaUtilArrayList.iterator();
              while (((Iterator)localObject).hasNext())
              {
                localclass_48 = (class_48)((Iterator)localObject).next();
                int i = bool ? 2 : 1;
                localclass_453.jdField_field_4_of_type_Class_457.a1().setCurrentBlockController(localclass_453.jdField_field_4_of_type_Class_796, localclass_48, i);
              }
            }
          }
          catch (CannotBeControlledException localCannotBeControlledException2)
          {
            localclass_453.a81(localCannotBeControlledException2);
          }
        }
      }
      if (class_367.field_758.a6()) {
        a6().a14().field_4.field_4.field_4.b();
      }
    }
    this.jdField_field_4_of_type_Class_243.handleKeyEvent();
  }
  
  public final void a12(class_939 paramclass_939)
  {
    if (System.currentTimeMillis() - a6().a14().field_4.field_4.field_4.jdField_field_4_of_type_Long < 300L) {
      return;
    }
    if ((this.jdField_field_4_of_type_Class_457.a1() != null) && (class_969.a1() != null) && (!this.jdField_field_4_of_type_Boolean))
    {
      int i = class_949.field_1175.b1() ? 1 : 0;
      int j = class_949.field_1175.b1() ? 0 : 1;
      Vector3f localVector3f1;
      Vector3f localVector3f2;
      if (paramclass_939.jdField_field_1163_of_type_Int == i)
      {
        localVector3f1 = new Vector3f(class_969.a1().a83());
        localVector3f2 = new Vector3f(class_969.a1().c10());
        if (Keyboard.isKeyDown(class_367.field_758.a5())) {
          (localVector3f2 = new Vector3f(a6().a27().b9())).sub(localVector3f1);
        }
        int k = 0;
        if (paramclass_939.jdField_field_1163_of_type_Boolean)
        {
          this.jdField_field_4_of_type_Class_713 = null;
          this.jdField_field_5_of_type_Long = System.currentTimeMillis();
        }
        else
        {
          k = (this.jdField_field_5_of_type_Long > 0L) && (System.currentTimeMillis() - this.jdField_field_5_of_type_Long > 500L) ? 1 : 0;
          this.jdField_field_5_of_type_Long = 0L;
        }
        if ((!paramclass_939.jdField_field_1163_of_type_Boolean) && (k == 0)) {
          a6().a14().field_4.field_4.field_4.a48(this.jdField_field_4_of_type_Class_457.a1(), localVector3f1, localVector3f2, new class_451(this), new class_713(), this.jdField_field_4_of_type_Class_433, 160.0F);
        }
      }
      if ((paramclass_939.jdField_field_1163_of_type_Int == j) && (!paramclass_939.jdField_field_1163_of_type_Boolean))
      {
        localVector3f1 = new Vector3f(class_969.a1().a83());
        localVector3f2 = new Vector3f(class_969.a1().c10());
        if (Keyboard.isKeyDown(class_367.field_758.a5())) {
          (localVector3f2 = new Vector3f(a6().a27().b9())).sub(localVector3f1);
        }
        a6().a14().field_4.field_4.field_4.a49(this.jdField_field_4_of_type_Class_457.a1(), localVector3f1, localVector3f2, 160.0F, this.jdField_field_4_of_type_Class_433);
      }
    }
  }
  
  public final void b2(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (this.jdField_field_4_of_type_Class_457.a1() != this.jdField_field_4_of_type_OrgSchemaGameCommonControllerSegmentController)
      {
        this.jdField_field_4_of_type_Class_796 = null;
        this.jdField_field_4_of_type_OrgSchemaGameCommonControllerSegmentController = this.jdField_field_4_of_type_Class_457.a1();
      }
      this.jdField_field_4_of_type_Class_457.a1().getControlElementMap().setObs(this);
      this.jdField_field_4_of_type_JavaUtilArrayList.clear();
      this.field_133 = true;
      if ((this.jdField_field_4_of_type_Class_243 == null) || (((class_960)this.jdField_field_4_of_type_Class_243.a184()).a142() != this.jdField_field_4_of_type_Class_457.a1()))
      {
        if (this.jdField_field_4_of_type_Class_457.a1() != null)
        {
          Transform localTransform = new Transform(this.jdField_field_4_of_type_Class_457.a1().getWorldTransform());
          GlUtil.d2(new Vector3f(0.0F, 1.0F, 0.0F), localTransform);
          GlUtil.c3(new Vector3f(1.0F, 0.0F, 0.0F), localTransform);
          GlUtil.a30(new Vector3f(0.0F, 0.0F, 1.0F), localTransform);
          a6();
          this.jdField_field_4_of_type_Class_243 = new class_243(class_969.a1(), this.jdField_field_4_of_type_Class_457);
          this.jdField_field_4_of_type_Class_243.a84(new class_48(8, 8, 0));
          this.jdField_field_4_of_type_Class_243.a(0.0F);
        }
        else if (this.jdField_field_4_of_type_Class_243 != null)
        {
          this.jdField_field_4_of_type_Class_243.a78(class_969.a1());
        }
      }
      else if (this.jdField_field_4_of_type_Class_243 != null) {
        this.jdField_field_4_of_type_Class_243.a78(class_969.a1());
      }
      if (this.jdField_field_4_of_type_Class_457.a1() != null)
      {
        a6().a27();
        class_227.a88(this.jdField_field_4_of_type_Class_457.a1());
      }
      a6().a4().c2("FlightMode");
      a6().a4().a26("BuildMode", "Build Mode", "(press " + class_367.field_728.b1() + " to switch to FLIGHT MODE; press " + class_367.field_732.b1() + " to exit structure)");
      if ((!field_161) && (this.jdField_field_4_of_type_Class_457.a1() == null)) {
        throw new AssertionError();
      }
      class_969.a9(this.jdField_field_4_of_type_Class_243);
    }
    super.b2(paramBoolean);
  }
  
  private void f2(boolean paramBoolean)
  {
    if (this.jdField_field_4_of_type_JavaUtilArrayList.isEmpty()) {
      return;
    }
    this.jdField_field_4_of_type_Int = FastMath.b1(this.jdField_field_4_of_type_Int + (paramBoolean ? 1 : -1), this.jdField_field_4_of_type_JavaUtilArrayList.size() - 1);
    System.err.println("SWITCH " + paramBoolean + " " + this.jdField_field_4_of_type_Int);
    try
    {
      this.jdField_field_4_of_type_Class_796 = this.jdField_field_4_of_type_Class_457.a1().getSegmentBuffer().a9((class_48)this.jdField_field_4_of_type_JavaUtilArrayList.get(this.jdField_field_4_of_type_Int), true);
      return;
    }
    catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException)
    {
      localCannotImmediateRequestOnClientException;
    }
  }
  
  public final void a15(class_941 paramclass_941)
  {
    if (this.jdField_field_4_of_type_Class_796 != null)
    {
      this.jdField_field_4_of_type_Class_796.a12();
      if (this.jdField_field_4_of_type_Class_796.a9() == 0) {
        this.jdField_field_4_of_type_Class_796 = null;
      }
    }
    if (this.field_133)
    {
      this.jdField_field_4_of_type_JavaUtilArrayList.clear();
      paramclass_941 = this.jdField_field_4_of_type_Class_457.a1().getControlElementMap().getControllingMap().keySet().iterator();
      while (paramclass_941.hasNext())
      {
        long l = ((Long)paramclass_941.next()).longValue();
        this.jdField_field_4_of_type_JavaUtilArrayList.add(ElementCollection.getPosFromIndex(l, new class_48()));
      }
      this.field_133 = false;
    }
    Vector3f localVector3f1;
    if ((this.jdField_field_5_of_type_Long > 0L) && (Keyboard.isKeyDown(class_367.field_758.a5())) && (System.currentTimeMillis() - this.jdField_field_5_of_type_Long > 500L))
    {
      paramclass_941 = new Vector3f(class_969.a1().a83());
      (localVector3f1 = new Vector3f(a6().a27().b9())).sub(paramclass_941);
      a6().a14().field_4.field_4.field_4.a48(this.jdField_field_4_of_type_Class_457.a1(), paramclass_941, localVector3f1, new class_449(this), this.jdField_field_4_of_type_Class_713, this.jdField_field_4_of_type_Class_433, 160.0F);
    }
    class_1046.field_1306 = !Keyboard.isKeyDown(class_367.field_758.a5());
    if (this.jdField_field_4_of_type_Class_457.a1() != null)
    {
      paramclass_941 = this;
      Object localObject = new Vector3f(class_969.a1().a83());
      localVector3f1 = new Vector3f(class_969.a1().c10());
      Vector3f localVector3f2 = new Vector3f((Vector3f)localObject);
      localVector3f1.scale(160.0F);
      localVector3f2.add(localVector3f1);
      if (Keyboard.isKeyDown(class_367.field_758.a5())) {
        localVector3f2 = new Vector3f(paramclass_941.a6().a27().b9());
      }
      paramclass_941.jdField_field_4_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback = ((PhysicsExt)paramclass_941.a6().a19()).testRayCollisionPoint((Vector3f)localObject, localVector3f2, false, null, paramclass_941.jdField_field_4_of_type_Class_457.a1(), false, paramclass_941.jdField_field_4_of_type_OrgSchemaGameCommonDataWorldSegment, true);
      if ((paramclass_941.jdField_field_4_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback != null) && (paramclass_941.jdField_field_4_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hasHit()) && ((paramclass_941.jdField_field_4_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback instanceof CubeRayCastResult)))
      {
        if (((localObject = (CubeRayCastResult)paramclass_941.jdField_field_4_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback).getSegment() != null) && (((CubeRayCastResult)localObject).cubePos != null))
        {
          ((CubeRayCastResult)localObject).getSegment().a14(((CubeRayCastResult)localObject).cubePos, paramclass_941.jdField_field_4_of_type_Class_48);
          ((CubeRayCastResult)localObject).getSegment().a16().getType(((CubeRayCastResult)localObject).cubePos);
          paramclass_941.jdField_field_4_of_type_OrgSchemaGameCommonDataWorldSegment = ((CubeRayCastResult)localObject).getSegment();
          paramclass_941.field_132 = true;
          return;
        }
        paramclass_941.jdField_field_4_of_type_OrgSchemaGameCommonDataWorldSegment = null;
        paramclass_941.field_132 = false;
        return;
      }
      paramclass_941.jdField_field_4_of_type_OrgSchemaGameCommonDataWorldSegment = null;
      paramclass_941.field_132 = false;
    }
  }
  
  public final void b()
  {
    this.field_133 = true;
  }
  
  public final class_433 a70()
  {
    return this.jdField_field_4_of_type_Class_433;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_453
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */