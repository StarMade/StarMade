import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.PrintStream;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerModuleCollection;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockCollectionManager;
import org.schema.game.common.controller.elements.dockingBlock.DockingBlockManagerInterface;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.Element;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public final class class_331
  implements class_965
{
  private boolean jdField_field_110_of_type_Boolean = true;
  class_371 jdField_field_98_of_type_Class_371;
  CollisionWorld.ClosestRayResultCallback jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback;
  class_1383 jdField_field_98_of_type_Class_1383;
  class_1383 jdField_field_106_of_type_Class_1383;
  private long jdField_field_98_of_type_Long;
  private class_796 jdField_field_108_of_type_Class_796;
  class_297 jdField_field_98_of_type_Class_297;
  Segment jdField_field_98_of_type_OrgSchemaGameCommonDataWorldSegment;
  class_35 jdField_field_98_of_type_Class_35 = new class_35();
  Segment jdField_field_106_of_type_OrgSchemaGameCommonDataWorldSegment = null;
  class_796 jdField_field_98_of_type_Class_796;
  ElementInformation jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation;
  class_796 jdField_field_106_of_type_Class_796;
  ElementInformation jdField_field_106_of_type_OrgSchemaGameCommonDataElementElementInformation;
  boolean jdField_field_98_of_type_Boolean;
  boolean jdField_field_106_of_type_Boolean;
  boolean jdField_field_108_of_type_Boolean;
  private Transform jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private Mesh jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh;
  private class_48 jdField_field_108_of_type_Class_48;
  private int jdField_field_98_of_type_Int;
  private long jdField_field_106_of_type_Long;
  private class_48 field_109;
  private class_221 jdField_field_98_of_type_Class_221;
  private class_48 jdField_field_110_of_type_Class_48;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f;
  class_48 jdField_field_98_of_type_Class_48;
  class_48 jdField_field_106_of_type_Class_48;
  public boolean field_109;
  
  public class_331(class_371 paramclass_371)
  {
    new Transform();
    this.jdField_field_108_of_type_Class_48 = new class_48();
    this.jdField_field_98_of_type_Int = -1;
    this.field_109 = new class_48();
    this.jdField_field_110_of_type_Class_48 = new class_48();
    this.jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_98_of_type_Class_48 = new class_48();
    this.jdField_field_106_of_type_Class_48 = new class_48();
    this.jdField_field_98_of_type_Class_371 = paramclass_371;
    this.jdField_field_98_of_type_Class_1383 = new class_1383();
    this.jdField_field_106_of_type_Class_1383 = new class_1383(6.1F);
    this.jdField_field_98_of_type_Class_297 = new class_297(new Transform(), "");
  }
  
  public final void a27(class_796 paramclass_796)
  {
    this.jdField_field_98_of_type_Long = System.currentTimeMillis();
    this.jdField_field_108_of_type_Class_796 = paramclass_796;
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (this.jdField_field_110_of_type_Boolean) {
      c();
    }
    Vector3f localVector3f1;
    if (this.jdField_field_108_of_type_Class_796 != null)
    {
      GlUtil.d1();
      this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.h1();
      localVector3f1 = null;
      class_1376.field_1569.field_1578 = this.jdField_field_98_of_type_Class_221;
      class_1376.field_1569.b();
      GL11.glDisable(2884);
      c2(this.jdField_field_108_of_type_Class_796.a7().a15(), this.jdField_field_108_of_type_Class_796);
      GL11.glEnable(2884);
      class_1376.field_1569.d();
      this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.m1();
      GlUtil.c2();
      if (System.currentTimeMillis() - this.jdField_field_98_of_type_Long > 3000L) {
        this.jdField_field_108_of_type_Class_796 = null;
      }
    }
    Object localObject1 = this.jdField_field_98_of_type_Class_371.a25();
    Object localObject3;
    if ((!a35().a36().g()) && (a33().c()))
    {
      class_227.field_89 = false;
      localObject1 = this;
      if (this.jdField_field_110_of_type_Boolean) {
        ((class_331)localObject1).c();
      }
      if (((class_331)localObject1).jdField_field_98_of_type_Int != ((class_331)localObject1).jdField_field_98_of_type_Class_371.a14().a18().a79().a60().a28())
      {
        if (((class_331)localObject1).jdField_field_98_of_type_Int >= 0) {
          ((class_331)localObject1).jdField_field_106_of_type_Long = System.currentTimeMillis();
        }
        ((class_331)localObject1).jdField_field_98_of_type_Int = ((class_331)localObject1).jdField_field_98_of_type_Class_371.a14().a18().a79().a60().a28();
      }
      if ((localObject3 = ((class_331)localObject1).a33().a40()) != null)
      {
        GlUtil.d1();
        ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.h1();
        localVector3f1 = null;
        class_1376.field_1569.field_1578 = ((class_331)localObject1).jdField_field_98_of_type_Class_221;
        class_1376.field_1569.b();
        GlUtil.a41(class_1376.field_1569, "selectionColor", 0.9F, 0.6F, 0.2F, 0.65F);
        ((class_331)localObject1).b3(((class_796)localObject3).a7().a15(), (class_796)localObject3);
        GlUtil.a41(class_1376.field_1569, "selectionColor", 0.4F, 0.1F, 0.9F, 0.65F);
        ((class_331)localObject1).a28(((class_796)localObject3).a7().a15(), (class_796)localObject3);
        class_1376.field_1569.d();
        ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.m1();
        GlUtil.c2();
      }
      if ((((class_331)localObject1).jdField_field_98_of_type_Class_371.a3() != null) && (System.currentTimeMillis() - ((class_331)localObject1).jdField_field_106_of_type_Long < 3000L)) {
        ((class_331)localObject1).b4(((class_331)localObject1).jdField_field_98_of_type_Class_371.a3().getWorldTransform());
      }
      return;
    }
    class_227.field_89 = true;
    Object localObject2;
    if ((a35().a36().g()) && (localObject1 != null))
    {
      GlUtil.d1();
      this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.h1();
      localVector3f1 = null;
      class_1376.field_1569.field_1578 = this.jdField_field_98_of_type_Class_221;
      class_1376.field_1569.b();
      GL11.glDisable(2884);
      localObject2 = a31((SegmentController)localObject1);
      GlUtil.a41(class_1376.field_1569, "selectionColor", 1.0F, 1.0F, 0.0F, 1.0F);
      a29((SegmentController)localObject1);
      c2((SegmentController)localObject1, null);
      GlUtil.a41(class_1376.field_1569, "selectionColor", 0.9F, 0.6F, 0.2F, 0.65F);
      b3((SegmentController)localObject1, a35().a36().a40());
      GlUtil.a41(class_1376.field_1569, "selectionColor", 0.4F, 0.1F, 0.9F, 0.65F);
      a28((SegmentController)localObject1, a35().a36().a40());
      GL11.glEnable(2884);
      class_1376.field_1569.d();
      this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.m1();
      if (((localObject3 = a32().a70()).jdField_field_795_of_type_Boolean) || (((class_433)localObject3).jdField_field_796_of_type_Boolean) || (((class_433)localObject3).jdField_field_797_of_type_Boolean)) {
        b2((SegmentController)localObject1);
      }
      if (((class_433)localObject3).jdField_field_795_of_type_Int > 0) {
        a30((SegmentController)localObject1, ((class_433)localObject3).jdField_field_795_of_type_Int, this.jdField_field_110_of_type_Class_48, ((class_433)localObject3).jdField_field_796_of_type_Int * 0.5F);
      }
      localObject3 = localObject1;
      localObject1 = this;
      CubeRayCastResult localCubeRayCastResult;
      if ((this.jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback != null) && (((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hasHit()) && ((((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback instanceof CubeRayCastResult)) && ((localCubeRayCastResult = (CubeRayCastResult)((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback).getSegment() != null) && (!class_367.field_758.a6()))
      {
        ((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.set(((SegmentController)localObject3).getWorldTransform());
        (localVector3f1 = new Vector3f(localCubeRayCastResult.getSegment().field_34.field_475, localCubeRayCastResult.getSegment().field_34.field_476, localCubeRayCastResult.getSegment().field_34.field_477)).field_615 += localCubeRayCastResult.cubePos.field_453 - 8;
        localVector3f1.field_616 += localCubeRayCastResult.cubePos.field_454 - 8;
        localVector3f1.field_617 += localCubeRayCastResult.cubePos.field_455 - 8;
        short s = ((class_331)localObject1).jdField_field_98_of_type_Class_371.a14().a18().a79().a60().a54();
        Vector3f localVector3f2 = new Vector3f();
        class_796 localclass_796;
        if (((localclass_796 = ((class_331)localObject1).a32().a40()) != null) && (localclass_796.a9() != 0) && (s != 0))
        {
          localObject4 = localclass_796.a2(new class_48());
          localVector3f2.set(((class_48)localObject4).field_475 - 8, ((class_48)localObject4).field_476 - 8, ((class_48)localObject4).field_477 - 8);
        }
        else
        {
          break label1265;
        }
        Object localObject4 = new Vector3f(((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hitPointWorld);
        ((SegmentController)localObject3).getWorldTransformInverse().transform((Vector3f)localObject4);
        new Vector3f();
        switch (Element.getSide((Vector3f)localObject4, ((class_331)localObject1).jdField_field_108_of_type_Class_48))
        {
        case 0: 
          localVector3f1.field_615 += 1.0F;
          break;
        case 1: 
          localVector3f1.field_615 -= 1.0F;
          break;
        case 2: 
          localVector3f1.field_616 += 1.0F;
          break;
        case 3: 
          localVector3f1.field_616 -= 1.0F;
          break;
        case 4: 
          localVector3f1.field_617 += 1.0F;
          break;
        case 5: 
          localVector3f1.field_617 -= 1.0F;
          break;
        default: 
          System.err.println("[BUILDMODEDRAWER] WARNING: NO SIDE recognized!!!");
        }
        ((class_331)localObject1).jdField_field_108_of_type_Class_48.b((int)localVector3f1.field_615, (int)localVector3f1.field_616, (int)localVector3f1.field_617);
        GlUtil.d1();
        GlUtil.b3(((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
        GL11.glDisable(3553);
        GL11.glEnable(2903);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(4.0F);
        if (ElementKeyMap.getInfo(s).getControlledBy().contains(Short.valueOf(localclass_796.a9()))) {
          GlUtil.a38(0.0F, 0.8F, 0.0F, 1.0F);
        } else {
          GlUtil.a38(0.8F, 0.0F, 0.0F, 0.6F);
        }
        GL11.glBegin(1);
        GL11.glVertex3f(localVector3f2.field_615, localVector3f2.field_616, localVector3f2.field_617);
        GL11.glVertex3f(localVector3f1.field_615, localVector3f1.field_616, localVector3f1.field_617);
        GL11.glEnd();
        GL11.glLineWidth(2.0F);
        GL11.glDisable(3042);
        GL11.glDisable(2903);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GlUtil.c2();
        new Transform(((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
      }
      label1265:
      if (localObject2 != null) {
        b4((Transform)localObject2);
      }
      GlUtil.c2();
    }
    if (a34().a36().g())
    {
      localObject2 = a34().a40().a7().a15();
      GlUtil.d1();
      this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.h1();
      localVector3f1 = null;
      class_1376.field_1569.field_1578 = this.jdField_field_98_of_type_Class_221;
      class_1376.field_1569.b();
      GL11.glDisable(2884);
      GlUtil.a41(class_1376.field_1569, "selectionColor", 0.7F, 0.77F, 0.1F, 1.0F);
      localObject3 = a31((SegmentController)localObject2);
      c2((SegmentController)localObject2, null);
      GlUtil.a41(class_1376.field_1569, "selectionColor", 1.0F, 1.0F, 0.0F, 1.0F);
      a29((SegmentController)localObject2);
      GlUtil.a41(class_1376.field_1569, "selectionColor", 0.9F, 0.6F, 0.2F, 0.65F);
      b3((SegmentController)localObject2, a34().a36().a40());
      GlUtil.a41(class_1376.field_1569, "selectionColor", 0.4F, 0.1F, 0.9F, 0.65F);
      a28((SegmentController)localObject2, a34().a36().a40());
      GL11.glEnable(2884);
      class_1376.field_1569.d();
      this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.m1();
      if (((localObject1 = a34().a36().a70()).jdField_field_795_of_type_Boolean) || (((class_433)localObject1).jdField_field_796_of_type_Boolean) || (((class_433)localObject1).jdField_field_797_of_type_Boolean)) {
        b2((SegmentController)localObject2);
      }
      if (((class_433)localObject1).jdField_field_795_of_type_Int > 0) {
        a30((SegmentController)localObject2, ((class_433)localObject1).jdField_field_795_of_type_Int, this.jdField_field_110_of_type_Class_48, ((class_433)localObject1).jdField_field_796_of_type_Int * 0.5F);
      }
      if (localObject3 != null) {
        b4((Transform)localObject3);
      }
      GlUtil.c2();
    }
  }
  
  private void a28(SegmentController paramSegmentController, class_796 paramclass_796)
  {
    try
    {
      if ((class_949.field_1255.b1()) && (paramclass_796 != null))
      {
        paramclass_796 = paramclass_796.a2(this.field_109);
        if ((paramclass_796 = paramSegmentController.getControlElementMap().getControlledElements((short)32767, paramclass_796)) != null)
        {
          a14(paramSegmentController.getWorldTransform());
          paramSegmentController = null;
          paramSegmentController = paramclass_796.field_1094.iterator();
          while (paramSegmentController.hasNext())
          {
            paramclass_796 = (class_916)paramSegmentController.next();
            class_1383 localclass_1383 = this.jdField_field_106_of_type_Class_1383;
            class_796 localclass_796 = paramclass_796;
            paramclass_796 = this;
            if (localclass_796 != null)
            {
              paramclass_796.jdField_field_98_of_type_JavaxVecmathVector3f.set(localclass_796.field_1139 - 8, localclass_796.field_1140 - 8, localclass_796.field_1141 - 8);
              GlUtil.d1();
              GlUtil.c4(paramclass_796.jdField_field_98_of_type_JavaxVecmathVector3f.field_615, paramclass_796.jdField_field_98_of_type_JavaxVecmathVector3f.field_616, paramclass_796.jdField_field_98_of_type_JavaxVecmathVector3f.field_617);
              float tmp160_159 = (1.05F + localclass_1383.a1() * 0.05F);
              float tmp161_160 = tmp160_159;
              GlUtil.b5(tmp160_159, tmp161_160, tmp161_160);
              paramclass_796.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.i();
              GlUtil.c2();
            }
          }
          d();
        }
      }
      return;
    }
    catch (ConcurrentModificationException localConcurrentModificationException)
    {
      localConcurrentModificationException;
    }
  }
  
  private void a14(Transform paramTransform)
  {
    this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.set(paramTransform);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glDisable(2896);
    GL11.glEnable(2903);
    GlUtil.a38(1.0F, 0.0F, 1.0F, 0.6F);
    GlUtil.d1();
    GlUtil.b3(paramTransform);
  }
  
  private static void d()
  {
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glEnable(2896);
    GL11.glDisable(2903);
    GL11.glDisable(3042);
    GlUtil.c2();
  }
  
  private void a29(SegmentController paramSegmentController)
  {
    if ((class_969.a1() instanceof class_243))
    {
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.set(paramSegmentController.getWorldTransform());
      paramSegmentController = ((class_243)class_969.a1()).b9();
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.basis.transform(paramSegmentController);
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin.add(paramSegmentController);
      GlUtil.d1();
      GlUtil.b3(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
      GL11.glScalef(1.01F, 1.01F, 1.01F);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2903);
      GlUtil.a38(0.0F, 0.0F, 1.0F, 0.6F);
      GlUtil.b5(0.1F, 0.1F, 0.1F);
      this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.i();
      GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(2896);
      GL11.glDisable(2903);
      GL11.glDisable(3042);
      GlUtil.c2();
    }
  }
  
  private void b2(SegmentController paramSegmentController)
  {
    class_433 localclass_433 = a32().a70();
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(2903);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 0.7F);
    GL11.glDisable(2884);
    float f = localclass_433.jdField_field_796_of_type_Int * 0.5F;
    Vector3f localVector3f = new Vector3f(localclass_433.jdField_field_797_of_type_Class_48.field_475 - 8 + f, localclass_433.jdField_field_796_of_type_Class_48.field_476 - 8 + f, localclass_433.jdField_field_795_of_type_Class_48.field_477 - 8 + f);
    this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.set(paramSegmentController.getWorldTransform());
    GlUtil.d1();
    GlUtil.b3(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
    GL11.glBegin(1);
    if ((localclass_433.jdField_field_795_of_type_Boolean) && (localclass_433.jdField_field_796_of_type_Boolean) && (localclass_433.jdField_field_797_of_type_Boolean))
    {
      GL11.glVertex3f(paramSegmentController.getMinPos().field_475 << 4, localVector3f.field_616, localVector3f.field_617);
      GL11.glVertex3f(paramSegmentController.getMaxPos().field_475 << 4, localVector3f.field_616, localVector3f.field_617);
      GL11.glVertex3f(localVector3f.field_615, paramSegmentController.getMinPos().field_476 << 4, localVector3f.field_617);
      GL11.glVertex3f(localVector3f.field_615, paramSegmentController.getMaxPos().field_476 << 4, localVector3f.field_617);
      GL11.glVertex3f(localVector3f.field_615, localVector3f.field_616, paramSegmentController.getMinPos().field_477 << 4);
      GL11.glVertex3f(localVector3f.field_615, localVector3f.field_616, paramSegmentController.getMaxPos().field_477 << 4);
    }
    else if ((localclass_433.jdField_field_795_of_type_Boolean) && (localclass_433.jdField_field_796_of_type_Boolean))
    {
      GL11.glVertex3f(paramSegmentController.getMinPos().field_475 << 4, localVector3f.field_616, localVector3f.field_617);
      GL11.glVertex3f(paramSegmentController.getMaxPos().field_475 << 4, localVector3f.field_616, localVector3f.field_617);
    }
    else if ((localclass_433.jdField_field_795_of_type_Boolean) && (localclass_433.jdField_field_797_of_type_Boolean))
    {
      GL11.glVertex3f(localVector3f.field_615, paramSegmentController.getMinPos().field_476 << 4, localVector3f.field_617);
      GL11.glVertex3f(localVector3f.field_615, paramSegmentController.getMaxPos().field_476 << 4, localVector3f.field_617);
    }
    else if ((localclass_433.jdField_field_796_of_type_Boolean) && (localclass_433.jdField_field_797_of_type_Boolean))
    {
      GL11.glVertex3f(localVector3f.field_615, localVector3f.field_616, paramSegmentController.getMinPos().field_477 << 4);
      GL11.glVertex3f(localVector3f.field_615, localVector3f.field_616, paramSegmentController.getMaxPos().field_477 << 4);
    }
    GL11.glEnd();
    GlUtil.c2();
    GL11.glBindTexture(3553, 0);
    GL11.glEnable(2884);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glEnable(2896);
    GL11.glDisable(2903);
    GL11.glDisable(3042);
    if (localclass_433.jdField_field_795_of_type_Boolean) {
      a30(paramSegmentController, 1, localclass_433.jdField_field_795_of_type_Class_48, f);
    }
    if (localclass_433.jdField_field_796_of_type_Boolean) {
      a30(paramSegmentController, 2, localclass_433.jdField_field_796_of_type_Class_48, f);
    }
    if (localclass_433.jdField_field_797_of_type_Boolean) {
      a30(paramSegmentController, 4, localclass_433.jdField_field_797_of_type_Class_48, f);
    }
  }
  
  private void a30(SegmentController paramSegmentController, int paramInt, class_48 paramclass_48, float paramFloat)
  {
    GL11.glDepthMask(false);
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 1);
    GL11.glEnable(2903);
    GL11.glDisable(2884);
    class_969.a2().a5("shield_tex").a153().a1().a();
    Vector3f localVector3f;
    if (paramInt == 1)
    {
      (localVector3f = new Vector3f(0.0F, 0.0F, paramclass_48.field_477)).field_615 -= 8.0F;
      localVector3f.field_616 -= 8.0F;
      localVector3f.field_617 -= 8.0F;
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.set(paramSegmentController.getWorldTransform());
      GlUtil.d1();
      GlUtil.b3(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
      GlUtil.a38(0.0F, 0.0F, 1.0F, 0.3F);
      GL11.glBegin(7);
      GL11.glTexCoord2f(0.0F, 0.0F);
      GL11.glVertex3f(paramSegmentController.getMinPos().field_475 << 4, paramSegmentController.getMinPos().field_476 << 4, localVector3f.field_617 + paramFloat);
      GL11.glTexCoord2f(0.0F, paramSegmentController.getMaxPos().field_476 / 0.07F);
      GL11.glVertex3f(paramSegmentController.getMinPos().field_475 << 4, paramSegmentController.getMaxPos().field_476 << 4, localVector3f.field_617 + paramFloat);
      GL11.glTexCoord2f(paramSegmentController.getMaxPos().field_475 / 0.07F, paramSegmentController.getMaxPos().field_476 / 0.07F);
      GL11.glVertex3f(paramSegmentController.getMaxPos().field_475 << 4, paramSegmentController.getMaxPos().field_476 << 4, localVector3f.field_617 + paramFloat);
      GL11.glTexCoord2f(paramSegmentController.getMaxPos().field_475 / 0.07F, 0.0F);
      GL11.glVertex3f(paramSegmentController.getMaxPos().field_475 << 4, paramSegmentController.getMinPos().field_476 << 4, localVector3f.field_617 + paramFloat);
      GL11.glEnd();
      GlUtil.c2();
    }
    if (paramInt == 2)
    {
      (localVector3f = new Vector3f(0.0F, paramclass_48.field_476, 0.0F)).field_615 -= 8.0F;
      localVector3f.field_616 -= 8.0F;
      localVector3f.field_617 -= 8.0F;
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.set(paramSegmentController.getWorldTransform());
      GlUtil.d1();
      GlUtil.b3(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
      GlUtil.a38(0.0F, 1.0F, 0.0F, 0.3F);
      GL11.glBegin(7);
      GL11.glTexCoord2f(0.0F, 0.0F);
      GL11.glVertex3f(paramSegmentController.getMinPos().field_475 << 4, localVector3f.field_616 + paramFloat, paramSegmentController.getMinPos().field_477 << 4);
      GL11.glTexCoord2f(0.0F, paramSegmentController.getMaxPos().field_477 / 0.07F);
      GL11.glVertex3f(paramSegmentController.getMinPos().field_475 << 4, localVector3f.field_616 + paramFloat, paramSegmentController.getMaxPos().field_477 << 4);
      GL11.glTexCoord2f(paramSegmentController.getMaxPos().field_475 / 0.07F, paramSegmentController.getMaxPos().field_477 / 0.07F);
      GL11.glVertex3f(paramSegmentController.getMaxPos().field_475 << 4, localVector3f.field_616 + paramFloat, paramSegmentController.getMaxPos().field_477 << 4);
      GL11.glTexCoord2f(paramSegmentController.getMaxPos().field_475 / 0.07F, 0.0F);
      GL11.glVertex3f(paramSegmentController.getMaxPos().field_475 << 4, localVector3f.field_616 + paramFloat, paramSegmentController.getMinPos().field_477 << 4);
      GL11.glEnd();
      GlUtil.c2();
    }
    if (paramInt == 4)
    {
      (localVector3f = new Vector3f(paramclass_48.field_475, 0.0F, 0.0F)).field_615 -= 8.0F;
      localVector3f.field_616 -= 8.0F;
      localVector3f.field_617 -= 8.0F;
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.set(paramSegmentController.getWorldTransform());
      GlUtil.d1();
      GlUtil.b3(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
      GlUtil.a38(1.0F, 0.0F, 0.0F, 0.3F);
      GL11.glBegin(7);
      GL11.glTexCoord2f(0.0F, 0.0F);
      GL11.glVertex3f(localVector3f.field_615 + paramFloat, paramSegmentController.getMinPos().field_476 << 4, paramSegmentController.getMinPos().field_477 << 4);
      GL11.glTexCoord2f(0.0F, paramSegmentController.getMaxPos().field_477 / 0.07F);
      GL11.glVertex3f(localVector3f.field_615 + paramFloat, paramSegmentController.getMinPos().field_476 << 4, paramSegmentController.getMaxPos().field_477 << 4);
      GL11.glTexCoord2f(paramSegmentController.getMaxPos().field_476 / 0.07F, paramSegmentController.getMaxPos().field_477 / 0.07F);
      GL11.glVertex3f(localVector3f.field_615 + paramFloat, paramSegmentController.getMaxPos().field_476 << 4, paramSegmentController.getMaxPos().field_477 << 4);
      GL11.glTexCoord2f(paramSegmentController.getMaxPos().field_476 / 0.07F, 0.0F);
      GL11.glVertex3f(localVector3f.field_615 + paramFloat, paramSegmentController.getMaxPos().field_476 << 4, paramSegmentController.getMinPos().field_477 << 4);
      GL11.glEnd();
      GlUtil.c2();
    }
    GL11.glBindTexture(3553, 0);
    GL11.glEnable(2884);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glEnable(2896);
    GL11.glDisable(2903);
    GL11.glDisable(3042);
    GL11.glDepthMask(true);
  }
  
  private void b3(SegmentController paramSegmentController, class_796 paramclass_796)
  {
    if (paramclass_796 != null)
    {
      a14(paramSegmentController.getWorldTransform());
      paramclass_796.a12();
      paramSegmentController = paramclass_796.a2(this.field_109);
      class_1383 localclass_1383 = this.jdField_field_98_of_type_Class_1383;
      paramclass_796 = paramSegmentController;
      paramSegmentController = this;
      if (paramclass_796 != null)
      {
        paramSegmentController.jdField_field_98_of_type_JavaxVecmathVector3f.set(paramclass_796.field_475 - 8, paramclass_796.field_476 - 8, paramclass_796.field_477 - 8);
        GlUtil.d1();
        GlUtil.c4(paramSegmentController.jdField_field_98_of_type_JavaxVecmathVector3f.field_615, paramSegmentController.jdField_field_98_of_type_JavaxVecmathVector3f.field_616, paramSegmentController.jdField_field_98_of_type_JavaxVecmathVector3f.field_617);
        float tmp108_107 = (1.05F + localclass_1383.a1() * 0.05F);
        float tmp109_108 = tmp108_107;
        GlUtil.b5(tmp108_107, tmp109_108, tmp109_108);
        paramSegmentController.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.i();
        GlUtil.c2();
      }
      d();
    }
  }
  
  private void c2(SegmentController paramSegmentController, class_796 paramclass_796)
  {
    try
    {
      if (((paramSegmentController instanceof class_798)) && ((((class_798)paramSegmentController).a() instanceof DockingBlockManagerInterface)))
      {
        Iterator localIterator = ((DockingBlockManagerInterface)((class_798)paramSegmentController).a()).getDockingBlock().iterator();
        while (localIterator.hasNext())
        {
          Object localObject1 = (ManagerModuleCollection)localIterator.next();
          class_796 localclass_796 = paramclass_796;
          SegmentController localSegmentController = paramSegmentController;
          Object localObject2 = localObject1;
          localObject1 = this;
          localObject2 = ((ManagerModuleCollection)localObject2).getCollectionManagers().iterator();
          while (((Iterator)localObject2).hasNext())
          {
            DockingBlockCollectionManager localDockingBlockCollectionManager = (DockingBlockCollectionManager)((Iterator)localObject2).next();
            if ((localclass_796 == null) || (localDockingBlockCollectionManager.getControllerElement().equals(localclass_796)))
            {
              Object localObject3 = new class_48();
              class_48 localclass_48 = new class_48();
              localDockingBlockCollectionManager.getDockingArea((class_48)localObject3, localclass_48);
              localObject3 = new Vector3f(Math.abs(((class_48)localObject3).field_475 - localclass_48.field_475) / 2.0F, Math.abs(((class_48)localObject3).field_476 - localclass_48.field_476) / 2.0F, Math.abs(((class_48)localObject3).field_477 - localclass_48.field_477) / 2.0F);
              localclass_48 = localDockingBlockCollectionManager.getControllerElement().a2(new class_48());
              Vector3f localVector3f = new Vector3f();
              switch (Element.orientationBackMapping[localDockingBlockCollectionManager.getControllerElement().b1()])
              {
              case 0: 
                localVector3f.set(localclass_48.field_475 + ((Vector3f)localObject3).field_615 / 2.0F - 8.0F + 0.5F, localclass_48.field_476 - 8, localclass_48.field_477 - 8);
                break;
              case 1: 
                localVector3f.set(localclass_48.field_475 - ((Vector3f)localObject3).field_615 / 2.0F - 8.0F - 0.5F, localclass_48.field_476 - 8, localclass_48.field_477 - 8);
                break;
              case 2: 
                localVector3f.set(localclass_48.field_475 - 8, localclass_48.field_476 + ((Vector3f)localObject3).field_616 / 2.0F - 8.0F + 0.5F, localclass_48.field_477 - 8);
                break;
              case 3: 
                localVector3f.set(localclass_48.field_475 - 8, localclass_48.field_476 - ((Vector3f)localObject3).field_616 / 2.0F - 8.0F - 0.5F, localclass_48.field_477 - 8);
                break;
              case 4: 
                localVector3f.set(localclass_48.field_475 - 8, localclass_48.field_476 - 8, localclass_48.field_477 + ((Vector3f)localObject3).field_617 / 2.0F - 8.0F + 0.5F);
                break;
              case 5: 
                localVector3f.set(localclass_48.field_475 - 8, localclass_48.field_476 - 8, localclass_48.field_477 - ((Vector3f)localObject3).field_617 / 2.0F - 8.0F - 0.5F);
              }
              ((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.set(localSegmentController.getWorldTransform());
              ((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.basis.transform(localVector3f);
              ((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin.add(localVector3f);
              GlUtil.d1();
              GlUtil.b3(((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
              GL11.glScalef(1.01F, 1.01F, 1.01F);
              GL11.glDisable(2896);
              GL11.glEnable(3042);
              GL11.glBlendFunc(770, 771);
              GL11.glEnable(2903);
              if (localDockingBlockCollectionManager.hasCollision()) {
                GlUtil.a41(class_1376.field_1569, "selectionColor", 1.0F, 0.0F, 0.0F, 1.0F);
              } else {
                GlUtil.a41(class_1376.field_1569, "selectionColor", 0.0F, 1.0F, 0.0F, 1.0F);
              }
              GlUtil.b5(((Vector3f)localObject3).field_615, ((Vector3f)localObject3).field_616, ((Vector3f)localObject3).field_617);
              ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.i();
              GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
              GL11.glEnable(2896);
              GL11.glDisable(2903);
              GL11.glDisable(3042);
              GlUtil.c2();
            }
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      System.err.println("CATCHED THIS EXCEPTION (just a warning)");
    }
  }
  
  private void b4(Transform paramTransform)
  {
    GlUtil.d1();
    Mesh localMesh = (Mesh)class_969.a2().a4("Arrow").a152().get(0);
    paramTransform = new Transform(paramTransform);
    SegmentController.setContraintFrameOrientation((byte)this.jdField_field_98_of_type_Class_371.a14().a18().a79().a60().a28(), paramTransform, GlUtil.d(new Vector3f(), paramTransform), GlUtil.f(new Vector3f(), paramTransform), GlUtil.c(new Vector3f(), paramTransform));
    Vector3f localVector3f;
    (localVector3f = new Vector3f(0.0F, 0.0F, 0.1F)).scale(this.jdField_field_98_of_type_Class_1383.a1() / 5.0F);
    localVector3f.field_617 -= 0.3F;
    paramTransform.basis.transform(localVector3f);
    paramTransform.origin.add(localVector3f);
    GlUtil.b3(paramTransform);
    GlUtil.b5(0.13F, 0.13F, 0.13F);
    GL11.glEnable(3042);
    GL11.glDisable(2884);
    GL11.glEnable(2896);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(2903);
    GlUtil.a38(1.0F, 1.0F, 1.0F, this.jdField_field_98_of_type_Class_1383.a1() - 0.5F);
    localMesh.b();
    GlUtil.c2();
    GL11.glDisable(2903);
    GL11.glDisable(3042);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  private Transform a31(SegmentController paramSegmentController)
  {
    if ((this.jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback != null) && (this.jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hasHit()) && ((this.jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback instanceof CubeRayCastResult)))
    {
      if ((localObject = (CubeRayCastResult)this.jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback).getSegment() == null) {
        return null;
      }
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.set(paramSegmentController.getWorldTransform());
      Vector3f localVector3f1;
      (localVector3f1 = new Vector3f(((CubeRayCastResult)localObject).getSegment().field_34.field_475, ((CubeRayCastResult)localObject).getSegment().field_34.field_476, ((CubeRayCastResult)localObject).getSegment().field_34.field_477)).field_615 += ((CubeRayCastResult)localObject).cubePos.field_453 - 8;
      localVector3f1.field_616 += ((CubeRayCastResult)localObject).cubePos.field_454 - 8;
      localVector3f1.field_617 += ((CubeRayCastResult)localObject).cubePos.field_455 - 8;
      Object localObject = new Vector3f(this.jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hitPointWorld);
      paramSegmentController.getWorldTransformInverse().transform((Vector3f)localObject);
      this.jdField_field_108_of_type_Class_48.b((int)localVector3f1.field_615, (int)localVector3f1.field_616, (int)localVector3f1.field_617);
      this.jdField_field_110_of_type_Class_48.b(this.jdField_field_108_of_type_Class_48.field_475 + 8, this.jdField_field_108_of_type_Class_48.field_476 + 8, this.jdField_field_108_of_type_Class_48.field_477 + 8);
      paramSegmentController = this.jdField_field_98_of_type_Class_371.a14().a18().a79().a60().a50();
      Vector3f localVector3f2 = new Vector3f(paramSegmentController.c4(), paramSegmentController.b6(), paramSegmentController.a28());
      if (!paramSegmentController.field_7)
      {
        GlUtil.a41(class_1376.field_1569, "selectionColor", 0.7F, 0.1F, 0.1F, 1.0F);
        localVector3f2.field_615 = (-localVector3f2.field_615);
        localVector3f2.field_616 = (-localVector3f2.field_616);
        localVector3f2.field_617 = (-localVector3f2.field_617);
      }
      else
      {
        GlUtil.a41(class_1376.field_1569, "selectionColor", 0.7F, 0.77F, 0.1F, 1.0F);
      }
      if (!class_367.field_758.a6()) {
        localVector3f2.set(1.0F, 1.0F, 1.0F);
      }
      Vector3f localVector3f3 = new Vector3f();
      switch (Element.getSide((Vector3f)localObject, this.jdField_field_108_of_type_Class_48))
      {
      case 0: 
        if (paramSegmentController.field_7) {
          localVector3f1.field_615 += 1.0F;
        }
        this.jdField_field_110_of_type_Class_48.field_475 += 1;
        localVector3f3.set(localVector3f2.field_615, localVector3f2.field_616, localVector3f2.field_617);
        break;
      case 1: 
        if (paramSegmentController.field_7) {
          localVector3f1.field_615 -= 1.0F;
        }
        this.jdField_field_110_of_type_Class_48.field_475 -= 1;
        localVector3f3.set(-localVector3f2.field_615, localVector3f2.field_616, localVector3f2.field_617);
        break;
      case 2: 
        if (paramSegmentController.field_7) {
          localVector3f1.field_616 += 1.0F;
        }
        this.jdField_field_110_of_type_Class_48.field_476 += 1;
        localVector3f3.set(localVector3f2.field_615, localVector3f2.field_616, localVector3f2.field_617);
        break;
      case 3: 
        if (paramSegmentController.field_7) {
          localVector3f1.field_616 -= 1.0F;
        }
        this.jdField_field_110_of_type_Class_48.field_476 -= 1;
        localVector3f3.set(localVector3f2.field_615, -localVector3f2.field_616, localVector3f2.field_617);
        break;
      case 4: 
        if (paramSegmentController.field_7) {
          localVector3f1.field_617 += 1.0F;
        }
        this.jdField_field_110_of_type_Class_48.field_477 += 1;
        localVector3f3.set(localVector3f2.field_615, localVector3f2.field_616, localVector3f2.field_617);
        break;
      case 5: 
        if (paramSegmentController.field_7) {
          localVector3f1.field_617 -= 1.0F;
        }
        this.jdField_field_110_of_type_Class_48.field_477 -= 1;
        localVector3f3.set(localVector3f2.field_615, localVector3f2.field_616, -localVector3f2.field_617);
        break;
      default: 
        System.err.println("[BUILDMODEDRAWER] WARNING: NO SIDE recognized!!!");
      }
      localVector3f1.field_615 += localVector3f3.field_615 / 2.0F - 0.5F * Math.signum(localVector3f3.field_615);
      localVector3f1.field_616 += localVector3f3.field_616 / 2.0F - 0.5F * Math.signum(localVector3f3.field_616);
      localVector3f1.field_617 += localVector3f3.field_617 / 2.0F - 0.5F * Math.signum(localVector3f3.field_617);
      paramSegmentController = new Vector3f(localVector3f1);
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.basis.transform(paramSegmentController);
      this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform.origin.add(paramSegmentController);
      GlUtil.d1();
      GlUtil.b3(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
      localVector3f3.scale(0.99993F);
      GL11.glScalef(localVector3f3.field_615, localVector3f3.field_616, localVector3f3.field_617);
      GL11.glEnable(3042);
      GL11.glDisable(2896);
      GL11.glBlendFunc(770, 771);
      GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
      this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.i();
      GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(2896);
      GL11.glDisable(3042);
      GlUtil.c2();
      return new Transform(this.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
    }
    return null;
  }
  
  public final class_453 a32()
  {
    if (a34().a36().g()) {
      return a34().a36();
    }
    if (a35().a36().g()) {
      return a35().a36();
    }
    return null;
  }
  
  public final class_431 a33()
  {
    return this.jdField_field_98_of_type_Class_371.a14().a18().a79().a60().a52();
  }
  
  public final class_447 a34()
  {
    return this.jdField_field_98_of_type_Class_371.a14().a18().a79().a60().a53();
  }
  
  public final class_328 a35()
  {
    return this.jdField_field_98_of_type_Class_371.a14().a18().a79().a60().a51().a45();
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh = ((Mesh)class_969.a2().a4("Box").a152().get(0));
    this.jdField_field_98_of_type_Class_221 = new class_221(this.jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.a153().a1().field_1592);
    this.jdField_field_110_of_type_Boolean = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_331
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */