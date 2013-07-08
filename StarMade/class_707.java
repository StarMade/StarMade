import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.collision.shapes.CompoundShapeChild;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.CollectionNotLoadedException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerModuleCollection;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.CubesCompoundShape;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.physics.RigidBodyExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkSegmentController;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteVector3f;
import org.schema.schine.network.objects.remote.RemoteVector4i;
import org.schema.schine.network.server.ServerMessage;

public class class_707
{
  private ElementDocking jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking = null;
  private final Set jdField_field_973_of_type_JavaUtilSet = new HashSet();
  private String jdField_field_973_of_type_JavaLangString = null;
  private class_48 jdField_field_973_of_type_Class_48;
  private boolean jdField_field_973_of_type_Boolean;
  private long jdField_field_973_of_type_Long;
  private final SegmentController jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController;
  private final Transform jdField_field_973_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private final Transform jdField_field_974_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Vector3f jdField_field_973_of_type_JavaxVecmathVector3f = new Vector3f();
  public Quat4f field_973;
  private long jdField_field_974_of_type_Long;
  private final Vector3f jdField_field_974_of_type_JavaxVecmathVector3f = new Vector3f();
  private long jdField_field_975_of_type_Long;
  private boolean jdField_field_974_of_type_Boolean;
  private final Quat4f jdField_field_974_of_type_JavaxVecmathQuat4f = new Quat4f();
  private final Quat4f jdField_field_975_of_type_JavaxVecmathQuat4f = new Quat4f();
  public class_1129 field_973;
  
  public class_707(SegmentController paramSegmentController)
  {
    this.jdField_field_973_of_type_JavaxVecmathQuat4f = new Quat4f();
    this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    this.jdField_field_973_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    this.jdField_field_974_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
  }
  
  public final void a()
  {
    if ((this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) || (this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()))
    {
      if (this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_field_974_of_type_Boolean)
      {
        int i = 0;
        SegmentController localSegmentController;
        if ((((localSegmentController = this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a7().a15()) instanceof class_798)) && ((((class_798)localSegmentController).a() instanceof DockingBlockManagerInterface)))
        {
          Object localObject = (DockingBlockManagerInterface)((class_798)localSegmentController).a();
          class_48 localclass_48 = this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a2(new class_48());
          if (this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
            try
            {
              this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a7().a15().getSegmentBuffer().a10(this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a2(new class_48()), true, this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.from);
              this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a7().a15().getSegmentBuffer().a10(localclass_48, true, this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740);
            }
            catch (IOException localIOException)
            {
              localIOException;
            }
            catch (InterruptedException localInterruptedException)
            {
              localInterruptedException;
            }
          }
          Iterator localIterator2 = ((DockingBlockManagerInterface)localObject).getDockingBlock().iterator();
          int j;
          do
          {
            if (!localIterator2.hasNext()) {
              break;
            }
            localObject = (ManagerModuleCollection)localIterator2.next();
            j = 0;
            try
            {
              localObject = ((ManagerModuleCollection)localObject).getCollectionManagers().iterator();
              while (((Iterator)localObject).hasNext())
              {
                DockingBlockCollectionManager localDockingBlockCollectionManager;
                if ((localDockingBlockCollectionManager = (DockingBlockCollectionManager)((Iterator)localObject).next()).getControllerPos().equals(localclass_48))
                {
                  i = 1;
                  if (!localDockingBlockCollectionManager.isObjectDockable(this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController))
                  {
                    if (this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
                      b1();
                    } else if (this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()) {
                      ((class_371)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a4().b1("Size of docked structure\ntoo big for docking area!\nUndocking " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getRealName());
                    }
                    j = 1;
                    break;
                  }
                }
              }
            }
            catch (ConcurrentModificationException localConcurrentModificationException)
            {
              localConcurrentModificationException.printStackTrace();
              System.err.println("Exception could be catched and handeled by deferring docking valid request");
              throw new CollectionNotLoadedException();
            }
          } while (j == 0);
        }
        if (i == 0) {
          if (this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
            b1();
          } else if ((this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()) || (localSegmentController.isClientOwnObject())) {
            ((class_371)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a4().b1("Docking module has been removed\n\nUndocking  " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getRealName());
          }
        }
      }
      Iterator localIterator1 = this.jdField_field_973_of_type_JavaUtilSet.iterator();
      while (localIterator1.hasNext()) {
        ((ElementDocking)localIterator1.next()).from.a7().a15().flagUpdateDocking();
      }
    }
  }
  
  public final boolean a1()
  {
    return System.currentTimeMillis() - this.jdField_field_973_of_type_Long > 1000L;
  }
  
  private static boolean a2(class_796 paramclass_796)
  {
    return paramclass_796.a9() == 7;
  }
  
  private boolean a3(class_796 paramclass_7961, class_796 paramclass_7962)
  {
    SegmentController localSegmentController = paramclass_7962.a7().a15();
    Object localObject1;
    Object localObject2;
    if (!paramclass_7962.a7().a15().getDockingController().jdField_field_973_of_type_JavaUtilSet.isEmpty())
    {
      localObject1 = paramclass_7962.a7().a15().getDockingController().jdField_field_973_of_type_JavaUtilSet.iterator();
      while (((Iterator)localObject1).hasNext()) {
        if (((localObject2 = (ElementDocking)((Iterator)localObject1).next()).field_1740.equals(paramclass_7962)) && (!((class_365)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController).a26().isEmpty()))
        {
          ((class_1041)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a59().a53(((class_748)((class_365)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController).a26().get(0)).getName(), "Cannot dock!\nDocking block in use by\n" + ((ElementDocking)localObject2).from.a7().a15().toNiceString(), 3);
          System.err.println("[DOCKING] WARNING: two ships are trying to dock to the same block " + ((ElementDocking)localObject2).field_1740 + ": in use by " + ((ElementDocking)localObject2).from.a7().a15() + "; trying: " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController);
          this.jdField_field_973_of_type_Long = System.currentTimeMillis();
          return false;
        }
      }
    }
    if (paramclass_7962.a7().a15().getDockingController().jdField_field_974_of_type_Boolean)
    {
      System.err.println("ERROR: cannot dock onto docked object: " + paramclass_7961.a7().a15() + " -> " + paramclass_7962.a7().a15());
      if ((this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && ((this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController instanceof class_365)) && (((class_365)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController).a26().isEmpty())) {
        ((class_1041)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a59().a53(((class_748)((class_365)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController).a26().get(0)).getName(), "Chain docking is not\nyet implemented\n(will come soon)", 3);
      }
      this.jdField_field_973_of_type_Long = System.currentTimeMillis();
      return false;
    }
    if (!this.jdField_field_973_of_type_JavaUtilSet.isEmpty())
    {
      System.err.println("ERROR: cannot dock onto with already docked object: " + paramclass_7961.a7().a15() + " -> " + paramclass_7962.a7().a15());
      if ((this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && ((this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController instanceof class_365)) && (!((class_365)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController).a26().isEmpty())) {
        ((class_1041)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a59().a53(((class_748)((class_365)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController).a26().get(0)).getName(), "Chain docking is not\nyet implemented\n(will come soon)", 3);
      }
      this.jdField_field_973_of_type_Long = System.currentTimeMillis();
      return false;
    }
    if ((!this.jdField_field_974_of_type_Boolean) || (this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a7().a15() != localSegmentController))
    {
      a8(new ElementDocking(paramclass_7961, paramclass_7962));
      this.jdField_field_973_of_type_Long = System.currentTimeMillis();
      localSegmentController.getDockingController().jdField_field_973_of_type_JavaUtilSet.add(new ElementDocking(paramclass_7961, paramclass_7962));
      d();
      (localObject1 = new Transform()).setIdentity();
      localObject2 = this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a2(new class_48());
      ((Transform)localObject1).origin.set(((class_48)localObject2).field_475 - 8, ((class_48)localObject2).field_476 - 8, ((class_48)localObject2).field_477 - 8);
      switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.b1()])
      {
      case 0: 
        GlUtil.e(this.jdField_field_973_of_type_JavaxVecmathVector3f, (Transform)localObject1);
        break;
      case 1: 
        GlUtil.d(this.jdField_field_973_of_type_JavaxVecmathVector3f, (Transform)localObject1);
        break;
      case 2: 
        GlUtil.f(this.jdField_field_973_of_type_JavaxVecmathVector3f, (Transform)localObject1);
        break;
      case 3: 
        GlUtil.b(this.jdField_field_973_of_type_JavaxVecmathVector3f, (Transform)localObject1);
        break;
      case 4: 
        GlUtil.c(this.jdField_field_973_of_type_JavaxVecmathVector3f, (Transform)localObject1);
        break;
      case 5: 
        GlUtil.a4(this.jdField_field_973_of_type_JavaxVecmathVector3f, (Transform)localObject1);
      }
      paramclass_7961 = this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a7().a15().getSegmentBuffer().a6();
      if (this.jdField_field_974_of_type_JavaxVecmathVector3f.length() <= 0.0F) {
        this.jdField_field_974_of_type_JavaxVecmathVector3f.set(paramclass_7961.field_1273);
      }
      if (a2(this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740))
      {
        this.jdField_field_973_of_type_JavaxVecmathVector3f.scale(1.5F);
      }
      else
      {
        float f1 = 0.0F;
        switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.b1()])
        {
        case 0: 
          f1 = this.jdField_field_974_of_type_JavaxVecmathVector3f.field_616;
          break;
        case 1: 
          f1 = this.jdField_field_974_of_type_JavaxVecmathVector3f.field_616;
          break;
        case 2: 
          f1 = this.jdField_field_974_of_type_JavaxVecmathVector3f.field_616;
          break;
        case 3: 
          f1 = this.jdField_field_974_of_type_JavaxVecmathVector3f.field_616;
          break;
        case 4: 
          f1 = this.jdField_field_974_of_type_JavaxVecmathVector3f.field_616;
          break;
        case 5: 
          f1 = this.jdField_field_974_of_type_JavaxVecmathVector3f.field_616;
        }
        System.err.println("[DOCK] NOW DOCKING: " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController + ": BOUNDING BOX: " + paramclass_7961 + "; DIST: " + f1);
        f1 = Math.abs(f1);
        this.jdField_field_973_of_type_JavaxVecmathVector3f.scale(f1 + 0.5F);
      }
      ((Transform)localObject1).origin.add(this.jdField_field_973_of_type_JavaxVecmathVector3f);
      this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.onPhysicsRemove();
      localSegmentController.onPhysicsRemove();
      Object localObject3 = (CompoundShape)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShape();
      paramclass_7961 = (CompoundShape)localSegmentController.getPhysicsDataContainer().getShape();
      for (int i = 0; i < ((CompoundShape)localObject3).getNumChildShapes(); i++)
      {
        this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a12();
        int j = 0;
        switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.b1()])
        {
        case 2: 
          break;
        case 3: 
          ((Transform)localObject1).basis.rotX(3.141593F);
          break;
        case 4: 
          ((Transform)localObject1).basis.rotX(1.570796F);
          break;
        case 5: 
          Matrix3f localMatrix3f;
          (localMatrix3f = new Matrix3f()).rotY(-1.570796F);
          ((Transform)localObject1).basis.rotZ(1.570796F);
          ((Transform)localObject1).basis.mul(localMatrix3f);
          localMatrix3f.rotZ(1.570796F);
          ((Transform)localObject1).basis.mul(localMatrix3f);
          break;
        case 1: 
          ((Transform)localObject1).basis.rotZ(1.570796F);
          break;
        case 0: 
          ((Transform)localObject1).basis.rotZ(-1.570796F);
        }
        paramclass_7961.addChildShape((Transform)localObject1, ((CompoundShape)localObject3).getChildShape(i));
        class_29.a5(((Transform)localObject1).basis, this.jdField_field_973_of_type_JavaxVecmathQuat4f);
        this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setShapeChield((CompoundShapeChild)paramclass_7961.getChildList().get(paramclass_7961.getChildList().size() - 1));
      }
      paramclass_7961.recalculateLocalAabb();
      float f2 = ((RigidBodyExt)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject()).getInvMass();
      this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().updateMass(this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getMass(), true);
      float f3 = ((RigidBodyExt)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject()).getInvMass();
      System.err.println("[DOCKING] " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController + " MASS: " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getMass() + ", invBidyMass: " + f2 + "/" + f3 + " --TO-- " + localSegmentController + " MASS: " + localSegmentController.getMass());
      if (this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getSectorId() != localSegmentController.getSectorId())
      {
        this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.setSectorId(localSegmentController.getSectorId());
        if ((this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController instanceof class_365))
        {
          localObject4 = null;
          localObject1 = ((class_365)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController).a26().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject3 = (class_748)((Iterator)localObject1).next();
            System.err.println("[SERVER][DOCKING] sector docking on border! " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController + " has players attached. Doing Sector Change for " + localObject3);
            class_48 localclass_48;
            if (this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
              localclass_48 = ((class_1041)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a62().getSector(localSegmentController.getSectorId()).field_136;
            } else {
              localclass_48 = ((class_665)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(localSegmentController.getSectorId())).a34();
            }
            ((class_748)localObject3).a73(new class_48(localclass_48));
            ((class_748)localObject3).c13(localSegmentController.getSectorId());
            if ((localObject4 = ((class_748)localObject3).a121()) != null)
            {
              System.err.println("[SERVER][DOCKING] sector docking on border! " + localclass_48 + " has CHARACTER. Doing Sector Change for " + localObject4 + ": ");
              ((class_750)localObject4).setSectorId(localSegmentController.getSectorId());
            }
            else
            {
              System.err.println("[SERVER] WARNING NO PLAYER CHARACTER ATTACHED TO " + localObject3);
            }
          }
        }
      }
      Object localObject4 = localSegmentController.getPhysics().getBodyFromShape(paramclass_7961, localSegmentController.getMass() > 0.0F ? localSegmentController.getMass() + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getMass() : 0.0F, localSegmentController.getWorldTransform());
      System.err.println("[DOCKING] ADDED CHILD COMPOUND: " + paramclass_7961.getNumChildShapes() + "; " + paramclass_7961 + ": InvMass " + ((RigidBody)localObject4).getInvMass());
      ((RigidBody)localObject4).setUserPointer(Integer.valueOf(localSegmentController.getId()));
      if ((!jdField_field_975_of_type_Boolean) && (((RigidBody)localObject4).getCollisionShape() != paramclass_7961)) {
        throw new AssertionError();
      }
      localSegmentController.getPhysicsDataContainer().setObject((CollisionObject)localObject4);
      localObject1 = localSegmentController.getDockingController().jdField_field_973_of_type_JavaUtilSet.iterator();
      while (((Iterator)localObject1).hasNext()) {
        ((ElementDocking)((Iterator)localObject1).next()).from.a7().a15().getPhysicsDataContainer().setObject(null);
      }
      localSegmentController.getPhysicsDataContainer().updatePhysical();
      localSegmentController.onPhysicsAdd();
      ((RigidBodyExt)localSegmentController.getPhysicsDataContainer().getObject()).activate(true);
      if ((this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController instanceof class_753)) {
        ((class_753)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController).handleNTDockChanged();
      }
      if ((!jdField_field_975_of_type_Boolean) && (localSegmentController.getPhysicsDataContainer().getShape() != paramclass_7961)) {
        throw new AssertionError();
      }
      if ((!jdField_field_975_of_type_Boolean) && (localSegmentController.getPhysicsDataContainer().getShape() != ((RigidBody)localObject4).getCollisionShape())) {
        throw new AssertionError();
      }
      System.err.println("[SEGCON] NOW DOCKED ON " + paramclass_7962 + " " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController + " -> " + paramclass_7962.a7().a15() + "  on " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState());
      if ((!this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (((class_371)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a8() == this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getSectorId())) {
        class_969.a8("0022_ambience sfx - ambient hangar sounds hydraulics", this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform(), 0.99F);
      }
      return true;
    }
    return false;
  }
  
  private boolean b(class_796 paramclass_7961, class_796 paramclass_7962)
  {
    paramclass_7961.a12();
    if (paramclass_7961.a9() == 0)
    {
      System.err.println("[DOCKING] NOT DOCKING " + paramclass_7962.a7().a15() + " ON NOTHING: " + paramclass_7961 + " ON " + paramclass_7961.a7().a15());
      return false;
    }
    if (paramclass_7962.a7().a15().getFactionId() != paramclass_7961.a7().a15().getFactionId())
    {
      paramclass_7961 = null;
      if ((!this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (((class_371)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a25() == this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController)) {
        ((class_371)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState()).a4().b1("You cannot dock on a\nship of another\nfaction");
      }
      System.err.println("[DOCKING] NOT DOCKING: faction does not equal " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState());
      if ((this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (System.currentTimeMillis() - this.jdField_field_974_of_type_Long > 4000L))
      {
        if ((this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController instanceof class_365))
        {
          paramclass_7961 = ((class_365)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController).a26().iterator();
          while (paramclass_7961.hasNext()) {
            (paramclass_7962 = (class_748)paramclass_7961.next()).a129(new ServerMessage("You cannot dock on a\nship of another\nfaction", 3, paramclass_7962.getId()));
          }
        }
        this.jdField_field_974_of_type_Long = System.currentTimeMillis();
      }
    }
    else
    {
      if (ElementKeyMap.getInfo(paramclass_7961.a9()).isDockable())
      {
        System.err.println("[DOCKING] " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController + " DOING THE DOCK TO " + paramclass_7961.a7().a15());
        return a3(paramclass_7962, paramclass_7961);
      }
      if (!jdField_field_975_of_type_Boolean) {
        throw new AssertionError(paramclass_7961.a9());
      }
    }
    return false;
  }
  
  public final ElementDocking a4()
  {
    return this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking;
  }
  
  public final Set a5()
  {
    return this.jdField_field_973_of_type_JavaUtilSet;
  }
  
  public final void a6(NetworkSegmentController paramNetworkSegmentController)
  {
    if (this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
    {
      if (this.jdField_field_974_of_type_Boolean) {
        synchronized (paramNetworkSegmentController)
        {
          paramNetworkSegmentController.dockingSize.set(this.jdField_field_974_of_type_JavaxVecmathVector3f);
          paramNetworkSegmentController.dockedTo.set(this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a7().a15().getUniqueIdentifier());
          paramNetworkSegmentController.dockedElement.set(new class_46(this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a2(new class_48()), 0));
        }
      }
      paramNetworkSegmentController.dockingSize.set(new Vector3f(0.0F, 0.0F, 0.0F));
      paramNetworkSegmentController.dockedTo.set("NONE");
      System.err.println("[DOCKING] SET NT DOCK TO " + (String)paramNetworkSegmentController.dockedTo.get());
    }
  }
  
  public final void a7(String paramString, class_48 paramclass_48)
  {
    if (a1())
    {
      this.jdField_field_973_of_type_JavaLangString = paramString;
      this.jdField_field_973_of_type_Class_48 = paramclass_48;
    }
  }
  
  public final void b1()
  {
    if (a1())
    {
      System.err.println("[DOCKING] REQUEST UNDOCK " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController + "; " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState());
      this.jdField_field_973_of_type_Boolean = true;
    }
  }
  
  private void a8(ElementDocking paramElementDocking)
  {
    this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking = paramElementDocking;
    this.jdField_field_974_of_type_Boolean = (paramElementDocking != null);
  }
  
  public final void a9(class_69 paramclass_69)
  {
    if ((!jdField_field_975_of_type_Boolean) && (!paramclass_69.a2().equals("dock"))) {
      throw new AssertionError();
    }
    String str = (String)(paramclass_69 = (class_69[])paramclass_69.a4())[0].a4();
    if ((paramclass_69.length > 4) && (paramclass_69[4].a3() == class_79.field_549)) {
      ((Byte)paramclass_69[4].a4()).byteValue();
    }
    if (!str.equals("NONE"))
    {
      this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.setHidden(true);
      a7(str, (class_48)paramclass_69[1].a4());
    }
    if ((paramclass_69.length > 3) && (paramclass_69[3].a3() == class_79.field_557) && ("s".equals(paramclass_69[3]))) {
      this.jdField_field_974_of_type_JavaxVecmathVector3f.set((Vector3f)paramclass_69[3].a4());
    }
  }
  
  public final class_69 a10()
  {
    if (this.jdField_field_973_of_type_JavaLangString != null) {
      try
      {
        throw new IllegalStateException("Exception DELAYED DOCK OF " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController + " TO " + this.jdField_field_973_of_type_JavaLangString + " HAS FAILED");
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        System.err.println("Exception successfully catched: rewriting desired docking");
        this.jdField_field_973_of_type_Class_1129 = new class_1129(this.jdField_field_973_of_type_JavaLangString, this.jdField_field_973_of_type_Class_48);
      }
    }
    class_69 localclass_691;
    if (this.jdField_field_973_of_type_Class_1129 == null) {
      localclass_691 = new class_69(class_79.field_556, "dockedTo", this.jdField_field_974_of_type_Boolean ? this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a7().a15().getUniqueIdentifier() : "NONE");
    } else {
      localclass_691 = new class_69(class_79.field_556, "dockedTo", this.jdField_field_973_of_type_Class_1129.jdField_field_1378_of_type_JavaLangString);
    }
    if (!((String)localclass_691.a4()).equals("NONE")) {
      System.err.println("WROTE DOCKED TO " + localclass_691.a4());
    }
    class_69 localclass_692;
    if (this.jdField_field_973_of_type_Class_1129 == null) {
      localclass_692 = new class_69(class_79.field_558, "dockedToPos", this.jdField_field_974_of_type_Boolean ? this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a2(new class_48()) : new class_48());
    } else {
      localclass_692 = new class_69(class_79.field_558, "dockedToPos", this.jdField_field_973_of_type_Class_1129.jdField_field_1378_of_type_Class_48);
    }
    class_69 localclass_693 = new class_69(class_79.field_549, null, Byte.valueOf((byte)0));
    class_69 localclass_694 = new class_69(class_79.field_549, null, Byte.valueOf((byte)0));
    class_69 localclass_695 = new class_69(class_79.field_557, "s", this.jdField_field_974_of_type_JavaxVecmathVector3f);
    return new class_69(class_79.field_561, "dock", new class_69[] { localclass_691, localclass_692, localclass_693, localclass_694, localclass_695, new class_69(class_79.field_548, null, null) });
  }
  
  private void c()
  {
    if (this.jdField_field_974_of_type_Boolean)
    {
      ElementDocking localElementDocking;
      if (((localElementDocking = this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking).field_1740.a9() == 0) || (ElementKeyMap.getInfo(localElementDocking.field_1740.a9()).isDockable()))
      {
        System.err.println("NOW UNDOCKING: " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController + "; " + this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + "; DOCKED TO TYPE: " + localElementDocking.field_1740.a9());
        SegmentController localSegmentController;
        if (!(localSegmentController = localElementDocking.field_1740.a7().a15()).getDockingController().jdField_field_973_of_type_JavaUtilSet.remove(localElementDocking)) {
          System.err.println("Exception: WARNING! UNDOCK UNSUCCESSFULL " + localElementDocking + ": " + localSegmentController.getDockingController().jdField_field_973_of_type_JavaUtilSet);
        }
        a8(null);
        a11(localSegmentController, this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController);
        this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setObject(null);
        this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setShape(null);
        this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().setShapeChield(null);
        this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().initialTransform.set(this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
        this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getRemoteTransformable().a7().set(this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
        this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.initPhysics();
        this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.onPhysicsAdd();
        this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.setFlagPhysicsInit(false);
        this.jdField_field_974_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
        if ((this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController instanceof class_753)) {
          ((class_753)this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController).handleNTDockChanged();
        }
      }
      else if (!jdField_field_975_of_type_Boolean)
      {
        throw new AssertionError(localElementDocking.field_1740.a9());
      }
      this.jdField_field_973_of_type_Long = System.currentTimeMillis();
    }
  }
  
  private static void a11(SegmentController paramSegmentController1, SegmentController paramSegmentController2)
  {
    paramSegmentController1.onPhysicsRemove();
    if (paramSegmentController1.getDockingController().jdField_field_973_of_type_JavaUtilSet.size() > 0)
    {
      CubesCompoundShape localCubesCompoundShape;
      int i = (localCubesCompoundShape = (CubesCompoundShape)paramSegmentController1.getPhysicsDataContainer().getShape()).getNumChildShapes();
      localCubesCompoundShape.removeChildShape(paramSegmentController2.getPhysicsDataContainer().getShapeChild().childShape);
      if (paramSegmentController1.getMass() > 0.0F) {
        paramSegmentController1.getPhysicsDataContainer().updateMass(paramSegmentController1.getMass(), true);
      }
      if (localCubesCompoundShape.getNumChildShapes() != i - 1) {
        System.err.println("[DOCKING] UPDATING SHAPE, BUT NO SHAPE HAS BEEN REMOVED (DELETION OF DOCKED OBJECT)");
      }
      (paramSegmentController2 = paramSegmentController1.getPhysics().getBodyFromShape(localCubesCompoundShape, paramSegmentController1.getMass(), paramSegmentController1.getWorldTransform())).setUserPointer(Integer.valueOf(paramSegmentController1.getId()));
      if ((!jdField_field_975_of_type_Boolean) && (paramSegmentController2.getCollisionShape() != localCubesCompoundShape)) {
        throw new AssertionError();
      }
      paramSegmentController1.getPhysicsDataContainer().setObject(paramSegmentController2);
      Iterator localIterator = paramSegmentController1.getDockingController().jdField_field_973_of_type_JavaUtilSet.iterator();
      while (localIterator.hasNext()) {
        ((ElementDocking)localIterator.next()).from.a7().a15().getPhysicsDataContainer().setObject(null);
      }
      if ((!jdField_field_975_of_type_Boolean) && (paramSegmentController1.getPhysicsDataContainer().getShape() != localCubesCompoundShape)) {
        throw new AssertionError();
      }
      if ((!jdField_field_975_of_type_Boolean) && (paramSegmentController1.getPhysicsDataContainer().getShape() != paramSegmentController2.getCollisionShape())) {
        throw new AssertionError();
      }
      paramSegmentController1.onPhysicsAdd();
      if (paramSegmentController1.getMass() > 0.0F) {
        paramSegmentController1.getPhysicsDataContainer().updateMass(paramSegmentController1.getMass(), true);
      }
      return;
    }
    System.err.println("[DOCKING] doing complete physics reset for " + paramSegmentController1);
    paramSegmentController1.getPhysicsDataContainer().setObject(null);
    paramSegmentController1.getPhysicsDataContainer().setShape(null);
    paramSegmentController1.getPhysicsDataContainer().setShapeChield(null);
    paramSegmentController1.getPhysicsDataContainer().initialTransform.set(paramSegmentController1.getWorldTransform());
    paramSegmentController1.getRemoteTransformable().a7().set(paramSegmentController1.getWorldTransform());
    paramSegmentController1.initPhysics();
    paramSegmentController1.onPhysicsAdd();
  }
  
  private void d()
  {
    this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a8(this.jdField_field_973_of_type_ComBulletphysicsLinearmathTransform);
    switch (org.schema.game.common.data.element.Element.orientationBackMapping[this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.b1()])
    {
    case 0: 
      GlUtil.e(this.jdField_field_973_of_type_JavaxVecmathVector3f, this.jdField_field_973_of_type_ComBulletphysicsLinearmathTransform);
      break;
    case 1: 
      GlUtil.d(this.jdField_field_973_of_type_JavaxVecmathVector3f, this.jdField_field_973_of_type_ComBulletphysicsLinearmathTransform);
      break;
    case 2: 
      GlUtil.f(this.jdField_field_973_of_type_JavaxVecmathVector3f, this.jdField_field_973_of_type_ComBulletphysicsLinearmathTransform);
      break;
    case 3: 
      GlUtil.b(this.jdField_field_973_of_type_JavaxVecmathVector3f, this.jdField_field_973_of_type_ComBulletphysicsLinearmathTransform);
      break;
    case 4: 
      GlUtil.c(this.jdField_field_973_of_type_JavaxVecmathVector3f, this.jdField_field_973_of_type_ComBulletphysicsLinearmathTransform);
      break;
    case 5: 
      GlUtil.a4(this.jdField_field_973_of_type_JavaxVecmathVector3f, this.jdField_field_973_of_type_ComBulletphysicsLinearmathTransform);
    }
    class_988 localclass_988 = this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.from.a7().a15().getSegmentBuffer().a6();
    Vector3f localVector3f;
    (localVector3f = new Vector3f()).sub(localclass_988.field_1274, localclass_988.field_1273);
    if (a2(this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740)) {
      this.jdField_field_973_of_type_JavaxVecmathVector3f.scale(4.5F);
    } else {
      this.jdField_field_973_of_type_JavaxVecmathVector3f.scale(localVector3f.field_616 / 2.0F);
    }
    this.jdField_field_973_of_type_ComBulletphysicsLinearmathTransform.origin.add(this.jdField_field_973_of_type_JavaxVecmathVector3f);
    this.jdField_field_974_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_973_of_type_ComBulletphysicsLinearmathTransform);
    this.jdField_field_974_of_type_ComBulletphysicsLinearmathTransform.inverse();
  }
  
  public final void b2(NetworkSegmentController paramNetworkSegmentController)
  {
    if (!this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
    {
      paramNetworkSegmentController.dockingSize.getVector(this.jdField_field_974_of_type_JavaxVecmathVector3f);
      String str = (String)paramNetworkSegmentController.dockedTo.get();
      int i = (!this.jdField_field_974_of_type_Boolean) && (!str.equals("NONE")) ? 1 : 0;
      int j = (this.jdField_field_974_of_type_Boolean) && (!str.equals("NONE")) && (!str.equals(this.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a7().a15().getUniqueIdentifier())) ? 1 : 0;
      int k = (this.jdField_field_974_of_type_Boolean) && (str.equals("NONE")) ? 1 : 0;
      if ((i != 0) || (j != 0))
      {
        paramNetworkSegmentController = paramNetworkSegmentController.dockedElement.getVector();
        a7(str, new class_48(paramNetworkSegmentController.field_467, paramNetworkSegmentController.field_468, paramNetworkSegmentController.field_469));
      }
      if (k != 0) {
        b1();
      }
    }
  }
  
  public final void a12(class_941 paramclass_941)
  {
    try
    {
      class_941 localclass_9411 = paramclass_941;
      paramclass_941 = this;
      if ((!this.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().isInitialized()) || (!paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a6().a6()) || (System.currentTimeMillis() - paramclass_941.jdField_field_975_of_type_Long < 1000L)) {
        return;
      }
      Object localObject1;
      Object localObject2;
      if (paramclass_941.jdField_field_973_of_type_JavaLangString != null)
      {
        localTransform = null;
        synchronized (paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects())
        {
          localObject1 = paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator();
          while (((Iterator)localObject1).hasNext()) {
            if ((((localObject2 = (Sendable)((Iterator)localObject1).next()) instanceof SegmentController)) && (((SegmentController)localObject2).getUniqueIdentifier().equals(paramclass_941.jdField_field_973_of_type_JavaLangString)))
            {
              if ((!(localObject1 = (SegmentController)localObject2).getPhysicsDataContainer().isInitialized()) || (!((SegmentController)localObject1).getSegmentBuffer().a6().a6()) || (!((SegmentController)localObject1).getSegmentBuffer().a6().c()))
              {
                System.err.println("[DOCKING] TARGET PHYSICS NOT YET INITIALIZED: " + paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " with " + localObject2 + " ON " + (paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "SERVER" : "CLIENT"));
                paramclass_941.jdField_field_975_of_type_Long = System.currentTimeMillis();
                return;
              }
              if ((!paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().isInitialized()) || (!paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a6().a6()) || (!paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a6().c()))
              {
                System.err.println("[DOCKING] SELF PHYSICS NOT YET INITIALIZED: " + paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " with " + localObject2 + " ON " + (paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "SERVER" : "CLIENT"));
                paramclass_941.jdField_field_975_of_type_Long = System.currentTimeMillis();
                return;
              }
              if ((((SegmentController)localObject1).getTotalElements() <= 0) || (paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getTotalElements() <= 0)) {
                System.err.println("[DOCKING][LANDING] Object has zero elements: " + paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " with " + localObject2 + " ON " + (paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "SERVER" : "CLIENT"));
              }
              try
              {
                localObject2 = ((SegmentController)localObject1).getSegmentBuffer().a9(paramclass_941.jdField_field_973_of_type_Class_48, true);
                System.err.println("[DOCKING] " + paramclass_941.jdField_field_973_of_type_Class_48 + " -> Piece: " + localObject2 + ": " + (paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "SERVER" : "CLIENT") + "; selfBB " + paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a6().a10() + "; targetBB " + ((SegmentController)localObject1).getSegmentBuffer().a6().a10());
                localObject1 = paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().a9(class_747.field_136, true);
                if (localObject2 != null) {
                  if ((((class_796)localObject2).a7().a15().getPhysicsDataContainer().isInitialized()) && (((class_796)localObject1).a7().a15().getPhysicsDataContainer().isInitialized()))
                  {
                    if (paramclass_941.b((class_796)localObject2, (class_796)localObject1)) {
                      paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.setHiddenVolatile(false);
                    } else if (paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
                      paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.setHidden(false);
                    }
                    paramclass_941.jdField_field_973_of_type_JavaLangString = null;
                  }
                  else
                  {
                    System.err.println("[DOCKING] Deffered delayed dock ");
                  }
                }
              }
              catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException)
              {
                paramclass_941.jdField_field_975_of_type_Long = System.currentTimeMillis();
                System.err.println("[CLIENT][DOCKING] Dock has been deleayed (cannot immediate request)");
              }
            }
          }
        }
      }
      if (paramclass_941.jdField_field_973_of_type_Boolean)
      {
        localTransform = null;
        System.err.println("[DOCKING] Delayed undock requested on " + (paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer() ? "Server" : "Client"));
        paramclass_941.c();
        paramclass_941.jdField_field_973_of_type_Boolean = false;
      }
      Transform localTransform = null;
      if ((!paramclass_941.jdField_field_973_of_type_JavaUtilSet.isEmpty()) && (paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().isInitialized()))
      {
        if ((!jdField_field_975_of_type_Boolean) && (paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject().getCollisionShape() != paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShape())) {
          throw new AssertionError(paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController + ": " + paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject().getCollisionShape() + "; " + paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShape());
        }
        ??? = null;
        localObject1 = paramclass_941.jdField_field_973_of_type_JavaUtilSet.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          if (((localObject2 = (ElementDocking)((Iterator)localObject1).next()) == null) || (((ElementDocking)localObject2).from == null))
          {
            if (!jdField_field_975_of_type_Boolean) {
              throw new AssertionError();
            }
            throw new NullPointerException("Invalid docking: " + localObject2);
          }
          if (((ElementDocking)localObject2).from.a7().a15().getPhysicsDataContainer().isInitialized()) {
            if (!paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(((ElementDocking)localObject2).from.a7().a15().getId()))
            {
              System.err.println("[DOCKING] UPDATING " + paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController + " MASS BECAUSE DOCKED SHIP DOESNT EXIST ANYMORE: " + ((ElementDocking)localObject2).from.a7().a15());
              a11(paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController, ((ElementDocking)localObject2).from.a7().a15());
              ??? = localObject2;
            }
            else
            {
              class_941 localclass_9412 = localclass_9411;
              class_707 localclass_707;
              if ((localclass_707 = ((ElementDocking)localObject2).from.a7().a15().getDockingController()).jdField_field_974_of_type_Boolean)
              {
                localclass_707.d();
                if (a2(localclass_707.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740))
                {
                  class_29.a5((localTransform = localclass_707.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getShapeChild().transform).basis, localclass_707.jdField_field_974_of_type_JavaxVecmathQuat4f);
                  localclass_707.jdField_field_974_of_type_JavaxVecmathQuat4f.normalize();
                  localclass_707.jdField_field_973_of_type_JavaxVecmathQuat4f.normalize();
                  if (localclass_707.jdField_field_973_of_type_JavaxVecmathQuat4f.field_599 != 0.0F)
                  {
                    if (localclass_707.jdField_field_974_of_type_JavaxVecmathQuat4f.field_599 == 0.0F)
                    {
                      localclass_707.jdField_field_975_of_type_JavaxVecmathQuat4f.set(localclass_707.jdField_field_973_of_type_JavaxVecmathQuat4f);
                    }
                    else
                    {
                      class_29.a4(localclass_707.jdField_field_974_of_type_JavaxVecmathQuat4f, localclass_707.jdField_field_973_of_type_JavaxVecmathQuat4f, Math.min(1.0F, localclass_9412.a() * 50.0F), localclass_707.jdField_field_975_of_type_JavaxVecmathQuat4f);
                      localclass_707.jdField_field_975_of_type_JavaxVecmathQuat4f.normalize();
                    }
                    localTransform.basis.set(localclass_707.jdField_field_975_of_type_JavaxVecmathQuat4f);
                  }
                }
              }
            }
          }
          if (paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject() == null) {
            throw new NullPointerException("Tried chain update");
          }
          ((ElementDocking)localObject2).from.a7().a15().getPhysicsDataContainer().updatePhysical(paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject());
        }
        if (??? != null) {
          paramclass_941.jdField_field_973_of_type_JavaUtilSet.remove(???);
        }
        ((CompoundShape)paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getPhysicsDataContainer().getObject().getCollisionShape()).recalculateLocalAabb();
      }
      if ((paramclass_941.jdField_field_974_of_type_Boolean) && (!paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a7().a15().getId())))
      {
        System.err.println("[DOCKING] undocking this " + paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonControllerSegmentController + " because mothership is deleted: " + paramclass_941.jdField_field_973_of_type_OrgSchemaGameCommonDataElementElementDocking.field_1740.a7().a15());
        paramclass_941.c();
      }
      return;
    }
    catch (IOException localIOException)
    {
      (paramclass_941 = localIOException).printStackTrace();
      throw new ErrorDialogException(paramclass_941.getMessage());
    }
    catch (InterruptedException localInterruptedException)
    {
      (paramclass_941 = localInterruptedException).printStackTrace();
      throw new ErrorDialogException(paramclass_941.getMessage());
    }
  }
  
  public final boolean b3()
  {
    return this.jdField_field_974_of_type_Boolean;
  }
  
  public final Vector3f a13()
  {
    return this.jdField_field_974_of_type_JavaxVecmathVector3f;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_707
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */