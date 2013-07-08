import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectArrayList;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.schema.game.client.view.SegmentDrawer;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ElementClassNotFoundException;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GLException;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public final class class_227
  extends class_984
{
  public static boolean field_89;
  private class_371 jdField_field_89_of_type_Class_371;
  private class_842 jdField_field_89_of_type_Class_842;
  private class_824 jdField_field_89_of_type_Class_824;
  private class_293 jdField_field_89_of_type_Class_293;
  private class_224 jdField_field_89_of_type_Class_224;
  private class_254 jdField_field_89_of_type_Class_254;
  private final class_247 jdField_field_89_of_type_Class_247;
  private ArrayList jdField_field_89_of_type_JavaUtilArrayList;
  private final class_218 jdField_field_89_of_type_Class_218;
  private class_1433 jdField_field_89_of_type_Class_1433 = new class_1433(9.0F);
  private class_834 jdField_field_89_of_type_Class_834;
  private class_237 jdField_field_89_of_type_Class_237;
  private class_826 jdField_field_89_of_type_Class_826;
  private class_283 jdField_field_89_of_type_Class_283;
  private final class_261 jdField_field_89_of_type_Class_261;
  private final class_331 jdField_field_89_of_type_Class_331;
  private final class_228 jdField_field_89_of_type_Class_228;
  private boolean field_95;
  private SegmentDrawer jdField_field_89_of_type_OrgSchemaGameClientViewSegmentDrawer;
  private class_402 jdField_field_89_of_type_Class_402;
  public static SegmentController field_89;
  public static long field_89;
  private final class_233 jdField_field_89_of_type_Class_233;
  private final class_220 jdField_field_89_of_type_Class_220;
  private final class_249 jdField_field_89_of_type_Class_249;
  private final class_398 jdField_field_89_of_type_Class_398 = new class_398();
  private Vector3f jdField_field_90_of_type_JavaxVecmathVector3f = new Vector3f();
  public static boolean field_90;
  public static boolean field_92 = false;
  public static boolean field_93 = false;
  private class_820 jdField_field_89_of_type_Class_820;
  private class_245 jdField_field_89_of_type_Class_245;
  private Integer[] jdField_field_89_of_type_ArrayOfJavaLangInteger;
  private Integer[] jdField_field_90_of_type_ArrayOfJavaLangInteger = new Integer[0];
  private int jdField_field_89_of_type_Int = -2;
  private float jdField_field_89_of_type_Float;
  private class_48 jdField_field_89_of_type_Class_48 = new class_48();
  private class_48 jdField_field_90_of_type_Class_48 = new class_48();
  private Transform jdField_field_90_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private boolean field_96;
  private boolean field_97;
  private boolean field_100;
  
  public class_227(class_371 paramclass_371)
  {
    this.jdField_field_89_of_type_Class_371 = paramclass_371;
    this.jdField_field_89_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_89_of_type_Class_233 = new class_233();
    this.jdField_field_89_of_type_OrgSchemaGameClientViewSegmentDrawer = new SegmentDrawer(paramclass_371);
    this.jdField_field_89_of_type_Class_220 = new class_220();
    this.jdField_field_89_of_type_Class_249 = new class_249();
    this.jdField_field_89_of_type_Class_331 = new class_331(paramclass_371);
    this.jdField_field_89_of_type_Class_228 = new class_228(paramclass_371);
    this.jdField_field_89_of_type_Class_247 = new class_247();
    this.jdField_field_89_of_type_Class_261 = new class_261(paramclass_371.a15());
    this.jdField_field_89_of_type_Class_834 = new class_834();
    this.jdField_field_89_of_type_Class_237 = new class_237();
    this.jdField_field_89_of_type_Class_826 = new class_826();
    this.jdField_field_89_of_type_Class_824 = new class_824();
    this.jdField_field_89_of_type_Class_402 = new class_402(paramclass_371);
    this.jdField_field_89_of_type_Class_218 = new class_218();
  }
  
  public final void a2() {}
  
  public final void d()
  {
    this.jdField_field_89_of_type_Class_218.d();
    this.jdField_field_89_of_type_Class_220.d();
    this.jdField_field_89_of_type_Class_233.e();
    this.jdField_field_89_of_type_OrgSchemaGameClientViewSegmentDrawer.d();
    class_402 localclass_402 = this.jdField_field_89_of_type_Class_402;
    while (localclass_402.jdField_field_98_of_type_JavaUtilArrayList.size() > 0) {
      ((class_404)localclass_402.jdField_field_98_of_type_JavaUtilArrayList.remove(0)).e();
    }
  }
  
  private void s()
  {
    StringBuffer localStringBuffer;
    (localStringBuffer = new StringBuffer()).append("Physics Data (!!warning slow): ");
    Iterator localIterator1 = this.jdField_field_89_of_type_Class_371.a19().getDynamicsWorld().getCollisionObjectArray().iterator();
    while (localIterator1.hasNext())
    {
      CollisionObject localCollisionObject = (CollisionObject)localIterator1.next();
      localStringBuffer.append(localCollisionObject.getCollisionShape().getClass().getSimpleName() + ": ");
      Iterator localIterator2 = this.jdField_field_89_of_type_Class_371.a7().values().iterator();
      while (localIterator2.hasNext())
      {
        class_797 localclass_797;
        if ((((localclass_797 = (class_797)localIterator2.next()) instanceof class_1421)) && (((class_1421)localclass_797).getPhysicsDataContainer().getObject() == localCollisionObject)) {
          localStringBuffer.append(localclass_797);
        }
      }
      localStringBuffer.append("; \n");
    }
    class_971.jdField_field_98_of_type_JavaUtilArrayList.add(localStringBuffer.toString());
  }
  
  private void a87(long paramLong, class_48 paramclass_481, class_48 paramclass_482, Transform paramTransform)
  {
    paramLong = (float)((System.currentTimeMillis() - paramLong) % 1200000L) / 1200000.0F;
    paramclass_481.a5(16);
    paramclass_481.a(8, 8, 8);
    paramclass_481.c1(this.jdField_field_89_of_type_Class_371.a20().a44());
    this.jdField_field_89_of_type_Float = paramLong;
    paramLong = new Vector3f(paramclass_482.field_475 * Universe.getSectorSizeWithMargin(), paramclass_482.field_476 * Universe.getSectorSizeWithMargin(), paramclass_482.field_477 * Universe.getSectorSizeWithMargin());
    new Vector3f(paramclass_481.field_475 * Universe.getSectorSizeWithMargin(), paramclass_481.field_476 * Universe.getSectorSizeWithMargin(), paramclass_481.field_477 * Universe.getSectorSizeWithMargin());
    paramTransform.setIdentity();
    if (class_791.a19(this.jdField_field_89_of_type_Class_371.a20().a44())) {
      paramTransform.basis.rotX(6.283186F * this.jdField_field_89_of_type_Float);
    }
    paramTransform.transform(paramLong);
    class_971.jdField_field_98_of_type_Class_998.c12(paramLong);
  }
  
  private void a73(long paramLong)
  {
    class_659 localclass_659 = this.jdField_field_89_of_type_Class_371.a20().a137();
    int i = -1;
    class_48 localclass_48 = new class_48();
    Object localObject3;
    for (int j = 0; j < 27; j++)
    {
      int k = localclass_659.a6(j);
      localObject3 = class_808.values()[k];
      localclass_659.a(j, this.jdField_field_89_of_type_Class_48);
      this.jdField_field_90_of_type_Class_48.b((this.jdField_field_89_of_type_Class_48.field_475 << 4) + 8, (this.jdField_field_89_of_type_Class_48.field_476 << 4) + 8, (this.jdField_field_89_of_type_Class_48.field_477 << 4) + 8);
      (localObject2 = new class_48(this.jdField_field_90_of_type_Class_48)).c1(this.jdField_field_89_of_type_Class_371.a20().a44());
      if ((i < 0) || (((class_48)localObject2).a4() < localclass_48.a4()))
      {
        i = j;
        localclass_48.b1((class_48)localObject2);
      }
      this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
      if (localObject3 == class_808.field_1069)
      {
        a87(paramLong, this.jdField_field_89_of_type_Class_48, (class_48)localObject2, this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform);
      }
      else if (localObject3 == class_808.field_1070)
      {
        a87(paramLong, this.jdField_field_89_of_type_Class_48, (class_48)localObject2, this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform);
        this.jdField_field_89_of_type_Class_237.b();
      }
    }
    class_789.a192(this.jdField_field_89_of_type_Class_371.a20().a44(), this.jdField_field_89_of_type_Class_48);
    if (!localclass_659.a1().equals(this.jdField_field_89_of_type_Class_48))
    {
      System.err.println("[CLIENT] WARNING: System not yet right: " + this.jdField_field_89_of_type_Class_48 + " / " + localclass_659.a1());
      return;
    }
    j = localclass_659.a6(localclass_659.a5(this.jdField_field_89_of_type_Class_48));
    Object localObject2 = class_808.values()[j];
    this.jdField_field_90_of_type_Class_48.b((this.jdField_field_89_of_type_Class_48.field_475 << 4) + 8, (this.jdField_field_89_of_type_Class_48.field_476 << 4) + 8, (this.jdField_field_89_of_type_Class_48.field_477 << 4) + 8);
    (localObject3 = new class_48(this.jdField_field_90_of_type_Class_48)).c1(this.jdField_field_89_of_type_Class_371.a20().a44());
    a87(paramLong, this.jdField_field_89_of_type_Class_48, (class_48)localObject3, this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform);
    GL11.glDepthRange(0.0D, 1.0D);
    Object localObject1 = localObject3;
    this.jdField_field_89_of_type_Class_826.jdField_field_98_of_type_Class_48.b1(localObject1);
    if ((!class_949.field_1240.b1()) && (localObject2 == class_808.field_1069)) {
      this.jdField_field_89_of_type_Class_826.b();
    }
    class_971.jdField_field_98_of_type_JavaUtilArrayList.add("#####SECTORSYSTEM " + this.jdField_field_89_of_type_Class_48 + " CENTER: " + this.jdField_field_90_of_type_Class_48 + ": " + localObject2);
  }
  
  public final void b()
  {
    if (class_949.field_1187.b1()) {
      this.jdField_field_89_of_type_Class_371.a14().b3(0);
    }
    class_810 localclass_810 = null;
    int i = 0;
    Object localObject2;
    if (((localObject2 = this.jdField_field_89_of_type_Class_826).jdField_field_98_of_type_Class_48.field_475 == 0) && (((class_826)localObject2).jdField_field_98_of_type_Class_48.field_476 == 0) && (((class_826)localObject2).jdField_field_98_of_type_Class_48.field_477 == 0))
    {
      GL11.glDisable(2929);
      GL11.glDisable(2884);
      GlUtil.d1();
      GlUtil.a31(class_969.a1().a83());
      GlUtil.b5(3.0F, 3.0F, 3.0F);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      class_1376.field_1549.c();
      GlUtil.a33(class_1376.field_1549, "time", ((class_826)localObject2).jdField_field_98_of_type_Float * 10.0F);
      ((class_826)localObject2).jdField_field_98_of_type_OrgSchemaSchineGraphicsengineFormsMesh.b();
      class_1377.e();
      GL11.glDisable(3042);
      GlUtil.c2();
      GL11.glEnable(2929);
      GL11.glEnable(2884);
      GL11.glClear(256);
    }
    Object localObject3;
    if (this.jdField_field_89_of_type_Class_371.a20().a136().a2() != this.jdField_field_89_of_type_Int) {
      synchronized (this.jdField_field_89_of_type_Class_371.a20().a136())
      {
        this.jdField_field_89_of_type_Int = this.jdField_field_89_of_type_Class_371.a20().a136().a2();
        localObject2 = this;
        localObject3 = new class_225((class_227)localObject2);
        ((class_227)localObject2).jdField_field_89_of_type_Class_371.getThreadPool().execute((Runnable)localObject3);
      }
    }
    if (this.jdField_field_89_of_type_ArrayOfJavaLangInteger != null)
    {
      this.jdField_field_90_of_type_ArrayOfJavaLangInteger = this.jdField_field_89_of_type_ArrayOfJavaLangInteger;
      this.jdField_field_89_of_type_ArrayOfJavaLangInteger = null;
    }
    this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
    ??? = new class_48();
    long l = this.jdField_field_89_of_type_Class_371.a4().calculateStartTime();
    for (int j = 0; j < this.jdField_field_90_of_type_ArrayOfJavaLangInteger.length; j++)
    {
      int k = this.jdField_field_90_of_type_ArrayOfJavaLangInteger[j].intValue();
      this.jdField_field_89_of_type_Class_371.a20().a136().a(k, (class_48)???);
      localObject3 = class_808.values()[this.jdField_field_89_of_type_Class_371.a20().a136().b(k)];
      class_810.values();
      this.jdField_field_89_of_type_Class_371.a20().a136().a1(k);
      if (localObject3 == class_808.field_1067) {
        if (this.jdField_field_89_of_type_Class_371.a20().a44().equals(???))
        {
          localclass_810 = class_810.values()[this.jdField_field_89_of_type_Class_371.a20().a136().a1(k)];
          this.jdField_field_89_of_type_Class_842.field_98 = localclass_810;
          if (this.jdField_field_89_of_type_Class_842.a5()) {
            i = 1;
          } else {
            this.jdField_field_89_of_type_Class_842.b();
          }
        }
        else
        {
          (localObject3 = new class_48((class_48)???)).c1(this.jdField_field_89_of_type_Class_371.a20().a44());
          this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform.basis.setIdentity();
          class_48 localclass_48 = class_789.a192((class_48)???, new class_48());
          if (class_791.a19((class_48)???))
          {
            float f = (float)((System.currentTimeMillis() - l) % 1200000L) / 1200000.0F;
            if (class_791.a19(this.jdField_field_89_of_type_Class_371.a20().a44())) {
              this.jdField_field_89_of_type_Class_834.jdField_field_98_of_type_Float = f;
            } else {
              this.jdField_field_89_of_type_Class_834.jdField_field_98_of_type_Float = 0.0F;
            }
            localclass_48.a5(16);
            localclass_48.a(8, 8, 8);
            localclass_48.c1(this.jdField_field_89_of_type_Class_371.a20().a44());
            this.jdField_field_89_of_type_Class_834.jdField_field_98_of_type_Class_48.b1(localclass_48);
          }
          else
          {
            this.jdField_field_89_of_type_Class_834.jdField_field_98_of_type_Float = 0.0F;
          }
          this.jdField_field_89_of_type_Class_834.a45((class_48)localObject3);
          this.jdField_field_89_of_type_Class_834.a46(class_810.values()[this.jdField_field_89_of_type_Class_371.a20().a136().a1(k)]);
          this.jdField_field_89_of_type_Class_834.b();
        }
      }
    }
    GL11.glDepthRange(0.0D, 1.0D);
    this.jdField_field_89_of_type_Class_402.b();
    this.jdField_field_89_of_type_Class_283.b();
    this.jdField_field_89_of_type_Class_247.b();
    for (j = 0; j < this.jdField_field_89_of_type_JavaUtilArrayList.size(); j++) {
      ((class_305)this.jdField_field_89_of_type_JavaUtilArrayList.get(j)).b();
    }
    this.jdField_field_89_of_type_Class_233.f();
    class_40.a("SEGMENTS");
    this.jdField_field_89_of_type_OrgSchemaGameClientViewSegmentDrawer.b();
    class_40.b("SEGMENTS");
    this.jdField_field_89_of_type_Class_398.b();
    if (Keyboard.isKeyDown(class_367.field_758.a5())) {
      if (((((localObject3 = this.jdField_field_89_of_type_Class_371.a14().field_4.field_4.field_4).a51().a45().field_4.field_6) || (((class_443)localObject3).a53().a36().field_6)) && (jdField_field_89_of_type_Boolean) ? 1 : 0) != 0) {
        this.jdField_field_90_of_type_JavaxVecmathVector3f = class_971.a56(this.jdField_field_90_of_type_JavaxVecmathVector3f);
      }
    }
    this.jdField_field_89_of_type_Class_245.b();
    this.jdField_field_89_of_type_Class_331.b();
    try
    {
      if ((jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController != null) && (System.currentTimeMillis() - jdField_field_89_of_type_Long < 8000L) && (this.jdField_field_89_of_type_Class_371.a14() != null) && (this.jdField_field_89_of_type_Class_371.a14().field_4.field_4.field_4.a51().a45().field_4.field_6))
      {
        System.currentTimeMillis();
        GlUtil.d1();
        Mesh localMesh = (Mesh)class_969.a2().a4("Arrow").field_89.get(0);
        localObject3 = new Transform(jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransform());
        (??? = new Vector3f(0.0F, 0.0F, 1.0F)).scale(this.jdField_field_89_of_type_Class_1433.a1());
        ((Transform)localObject3).basis.transform((Tuple3f)???);
        ((Transform)localObject3).origin.add((Tuple3f)???);
        GlUtil.b3((Transform)localObject3);
        GlUtil.b5(0.3F, 0.3F, 0.3F);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2903);
        GlUtil.a38(1.0F, 1.0F, 1.0F, this.jdField_field_89_of_type_Class_1433.a1());
        localMesh.b();
        GlUtil.c2();
        GL11.glDisable(2903);
        GL11.glDisable(3042);
        GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
      }
      else
      {
        jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController = null;
        jdField_field_89_of_type_Long = 0L;
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      localNullPointerException;
    }
    if (i != 0) {
      this.jdField_field_89_of_type_Class_842.b();
    }
    this.jdField_field_89_of_type_Class_218.b();
    this.jdField_field_89_of_type_Class_224.jdField_field_98_of_type_Boolean = this.jdField_field_89_of_type_Class_842.a5();
    this.jdField_field_89_of_type_Class_224.b();
    this.jdField_field_89_of_type_Class_249.a2();
    this.jdField_field_89_of_type_Class_220.b();
    this.jdField_field_89_of_type_Class_293.b();
    this.jdField_field_89_of_type_Class_254.b();
    if ((localclass_810 != null) && (i == 0))
    {
      this.jdField_field_89_of_type_Class_834.a45(new class_48(0, 0, 0));
      this.jdField_field_89_of_type_Class_834.a46(localclass_810);
      this.jdField_field_89_of_type_Class_834.b();
    }
    a73(l);
    this.jdField_field_89_of_type_Class_228.b();
    if (class_949.field_1222.b1()) {
      s();
    }
    SegmentDrawer.jdField_field_98_of_type_Boolean = false;
  }
  
  public final void e()
  {
    if (field_93)
    {
      t();
      field_93 = false;
    }
    class_40.a("GUI");
    this.jdField_field_89_of_type_Class_261.b();
    class_40.b("GUI");
    if (field_92)
    {
      t();
      field_92 = false;
    }
    class_192.e();
    class_820 localclass_820;
    if (class_949.field_1189.b1())
    {
      if (this.jdField_field_89_of_type_Class_820 == null) {
        this.jdField_field_89_of_type_Class_820 = new class_820();
      }
      class_820 tmp79_76 = this.jdField_field_89_of_type_Class_820;
      tmp79_76.field_1082 = (localclass_820 = tmp79_76).field_1084;
      localclass_820.field_1083 = localclass_820.field_1085;
      localclass_820.a();
    }
    if (jdField_field_90_of_type_Boolean)
    {
      localclass_820 = new class_820();
      if (this.jdField_field_89_of_type_Class_820 == null) {
        this.jdField_field_89_of_type_Class_820 = new class_820();
      }
      localclass_820.jdField_field_1081_of_type_Int = this.jdField_field_89_of_type_Class_820.jdField_field_1081_of_type_Int;
      try
      {
        class_925 localclass_925;
        (localclass_925 = new class_925(1024, 1024)).e();
        localclass_925.d();
        GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        GL11.glClear(16640);
        localclass_820.field_1082 = 32;
        localclass_820.field_1083 = 32;
        GL11.glViewport(0, 0, 1024, 1024);
        localclass_820.jdField_field_1081_of_type_Boolean = true;
        localclass_820.a();
        localclass_820.jdField_field_1081_of_type_Boolean = false;
        System.err.println("SHEET: " + localclass_820.jdField_field_1081_of_type_Int + " WRITING SCREEN TO DISK: ./data/image-resource/build-icons-" + class_41.b(localclass_820.jdField_field_1081_of_type_Int) + "-16x16-gui-");
        GlUtil.a43("./data/image-resource/build-icons-" + class_41.b(localclass_820.jdField_field_1081_of_type_Int) + "-16x16-gui-", "png", 1024, 1024);
        GL11.glViewport(0, 0, class_933.b(), class_933.a());
        localclass_925.b1();
        localclass_925.a();
      }
      catch (GLException localGLException)
      {
        localGLException;
      }
      jdField_field_90_of_type_Boolean = false;
    }
  }
  
  private static void t()
  {
    File[] arrayOfFile1 = new File("./").listFiles();
    int i = 0;
    int j = 1;
    label125:
    while (j != 0)
    {
      j = 0;
      File[] arrayOfFile2 = arrayOfFile1;
      int k = arrayOfFile1.length;
      for (int m = 0;; m++)
      {
        if (m >= k) {
          break label125;
        }
        if (arrayOfFile2[m].getName().startsWith("starmade-screenshot-" + class_41.a1(i) + ".png"))
        {
          System.err.println("Screen Already Exists: ./starmade-screenshot-" + class_41.a1(i) + ".png");
          i++;
          j = 1;
          break;
        }
      }
    }
    GlUtil.a43("./starmade-screenshot-" + class_41.a1(i), "png", class_933.b(), class_933.a());
  }
  
  public final void f()
  {
    Keyboard.enableRepeatEvents(true);
    if ((class_949.field_1189.b1()) && (this.jdField_field_89_of_type_Class_820 != null))
    {
      class_820 localclass_820 = this.jdField_field_89_of_type_Class_820;
      if (Keyboard.getEventKeyState())
      {
        int i;
        switch (Keyboard.getEventKey())
        {
        case 17: 
          localclass_820.field_1085 += 8;
          break;
        case 30: 
          localclass_820.field_1084 -= 8;
          break;
        case 31: 
          localclass_820.field_1085 -= 8;
          break;
        case 32: 
          localclass_820.field_1084 += 8;
          break;
        case 205: 
          i = ElementKeyMap.highestType / 256 + 1;
          localclass_820.jdField_field_1081_of_type_Int = ((localclass_820.jdField_field_1081_of_type_Int + 1) % i);
          break;
        case 203: 
          i = ElementKeyMap.highestType / 256 + 1;
          if (localclass_820.jdField_field_1081_of_type_Int - 1 < 0) {
            localclass_820.jdField_field_1081_of_type_Int = (i - 1);
          } else {
            localclass_820.jdField_field_1081_of_type_Int -= 1;
          }
          break;
        }
      }
    }
    Keyboard.enableRepeatEvents(false);
  }
  
  public final void g()
  {
    this.jdField_field_89_of_type_Class_371.a23();
    class_307.b7().b();
    Vector3f localVector3f = class_971.jdField_field_98_of_type_Class_998.a83();
    GlUtil.d1();
    GlUtil.c4(localVector3f.field_615, localVector3f.field_616, localVector3f.field_617);
    float f2 = 4.0F;
    float f1;
    if ((f1 = (float)Math.pow(localVector3f.length(), 1.299999952316284D)) < 30000.0F)
    {
      f2 = f1 / 30000.0F;
      f2 = 4.0F * f2;
    }
    class_969.a2().a4("Sphere").b28(f2, f2, f2);
    class_969.a2().a4("Sphere").b();
    GlUtil.c2();
  }
  
  public static void a88(SegmentController paramSegmentController)
  {
    jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    jdField_field_89_of_type_Long = System.currentTimeMillis();
  }
  
  public final Vector3f b9()
  {
    return this.jdField_field_90_of_type_JavaxVecmathVector3f;
  }
  
  public final class_233 a89()
  {
    return this.jdField_field_89_of_type_Class_233;
  }
  
  public final class_331 a90()
  {
    return this.jdField_field_89_of_type_Class_331;
  }
  
  public final class_293 a91()
  {
    return this.jdField_field_89_of_type_Class_293;
  }
  
  public final class_261 a92()
  {
    return this.jdField_field_89_of_type_Class_261;
  }
  
  public final class_247 a93()
  {
    return this.jdField_field_89_of_type_Class_247;
  }
  
  public final SegmentDrawer a94()
  {
    return this.jdField_field_89_of_type_OrgSchemaGameClientViewSegmentDrawer;
  }
  
  public final class_220 a95()
  {
    return this.jdField_field_89_of_type_Class_220;
  }
  
  public final class_824 a96()
  {
    return this.jdField_field_89_of_type_Class_824;
  }
  
  public final class_254 a97()
  {
    return this.jdField_field_89_of_type_Class_254;
  }
  
  public final void h1()
  {
    class_40.a("CONTEXT");
    this.jdField_field_89_of_type_OrgSchemaGameClientViewSegmentDrawer.e();
    class_40.b("CONTEXT");
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_842 = new class_842();
    this.jdField_field_89_of_type_Class_842.c();
    this.jdField_field_89_of_type_Class_218.c();
    this.jdField_field_89_of_type_Class_402.c();
    this.jdField_field_89_of_type_Class_247.c();
    this.jdField_field_89_of_type_OrgSchemaGameClientViewSegmentDrawer.c();
    this.jdField_field_89_of_type_Class_834.c();
    this.jdField_field_89_of_type_Class_237.c();
    this.jdField_field_89_of_type_Class_826.c();
    this.jdField_field_89_of_type_Class_331.c();
    this.jdField_field_89_of_type_Class_228.c1();
    this.jdField_field_89_of_type_Class_824.c();
    this.jdField_field_89_of_type_Class_293 = new class_293();
    this.jdField_field_89_of_type_Class_293.c();
    this.jdField_field_89_of_type_Class_261.c();
    this.jdField_field_89_of_type_Class_224 = new class_224();
    this.jdField_field_89_of_type_Class_224.c();
    this.jdField_field_89_of_type_Class_254 = new class_254(this.jdField_field_89_of_type_Class_371);
    this.jdField_field_89_of_type_Class_254.c();
    this.jdField_field_89_of_type_Class_245 = new class_245(this.jdField_field_89_of_type_Class_371);
    this.jdField_field_89_of_type_Class_245.c();
    this.jdField_field_89_of_type_Class_283 = new class_283(this.jdField_field_89_of_type_Class_371);
    this.jdField_field_89_of_type_Class_283.c();
    class_305 localclass_305 = new class_305(this.jdField_field_89_of_type_Class_371.getParticleController());
    this.jdField_field_89_of_type_Class_371.a27().jdField_field_89_of_type_JavaUtilArrayList.add(localclass_305);
    localclass_305.c();
    this.field_95 = true;
  }
  
  public final void i()
  {
    long l1 = System.currentTimeMillis();
    this.jdField_field_89_of_type_Class_261.d();
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[WORLDDRAWER] WARNING: SECTOR CHANGE UPDATE FOR DRAWER TOOK " + l2);
    }
  }
  
  public final void j()
  {
    class_826 localclass_826;
    if ((this.jdField_field_89_of_type_Class_826 != null) && ((localclass_826 = this.jdField_field_89_of_type_Class_826).field_108 > 5))
    {
      localclass_826.field_106 = GL15.glGetQueryObjectui(localclass_826.jdField_field_98_of_type_Int, 34918);
      localclass_826.field_108 = 0;
    }
  }
  
  public final void a12(class_941 paramclass_941)
  {
    if (!this.field_95) {
      return;
    }
    class_40.a("update");
    long l1 = System.currentTimeMillis();
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[DRAWER][WARNING] synUPDATE took " + l2 + " ms");
    }
    l1 = System.currentTimeMillis();
    if (this.field_97)
    {
      this.jdField_field_89_of_type_OrgSchemaGameClientViewSegmentDrawer.f();
      this.field_97 = false;
    }
    long l3;
    if ((l3 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[DRAWER][WARNING] seg controller set update took " + l3 + " ms");
    }
    l1 = System.currentTimeMillis();
    if (this.field_96)
    {
      u();
      this.field_96 = false;
    }
    long l4;
    if ((l4 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[DRAWER][WARNING] segManControllerUpdate took " + l4 + " ms");
    }
    if (this.field_100)
    {
      this.jdField_field_89_of_type_Class_402.d();
      this.field_100 = false;
    }
    this.jdField_field_89_of_type_Class_1433.a(paramclass_941);
    Object localObject4 = null;
    Object localObject2 = paramclass_941;
    if ((localObject1 = this.jdField_field_89_of_type_Class_824).field_98 != null) {
      ((class_824)localObject1).field_98.field_107.a((class_941)localObject2);
    }
    this.jdField_field_89_of_type_Class_402.a1(paramclass_941);
    this.jdField_field_89_of_type_Class_834.a1(paramclass_941);
    localObject2 = paramclass_941;
    (localObject1 = this.jdField_field_89_of_type_Class_237).jdField_field_98_of_type_Float += ((class_941)localObject2).a() * 0.07F;
    if (((class_237)localObject1).jdField_field_98_of_type_Float > 1.0F)
    {
      Object tmp301_300 = localObject1;
      tmp301_300.jdField_field_98_of_type_Float = ((float)(tmp301_300.jdField_field_98_of_type_Float - Math.floor(((class_237)localObject1).jdField_field_98_of_type_Float)));
    }
    localObject2 = paramclass_941;
    this.jdField_field_89_of_type_Class_826.jdField_field_98_of_type_Float += ((class_941)localObject2).a() * 0.07F;
    this.jdField_field_89_of_type_Class_228.a15(paramclass_941);
    this.jdField_field_89_of_type_OrgSchemaGameClientViewSegmentDrawer.a1(paramclass_941);
    localObject2 = paramclass_941;
    this.jdField_field_89_of_type_Class_293.field_98.a6((class_941)localObject2);
    localObject2 = paramclass_941;
    if (!(localObject1 = this.jdField_field_89_of_type_Class_224).jdField_field_98_of_type_Boolean)
    {
      ((class_224)localObject1).jdField_field_98_of_type_JavaxVecmathVector3f.set(class_969.a1().a83());
      ((class_224)localObject1).jdField_field_98_of_type_JavaxVecmathVector3f.sub(((class_224)localObject1).jdField_field_106_of_type_JavaxVecmathVector3f);
      float f = ((class_224)localObject1).jdField_field_98_of_type_JavaxVecmathVector3f.length();
      localObject1.jdField_field_106_of_type_Float += f;
      if (((class_224)localObject1).jdField_field_106_of_type_Float > 0.05F)
      {
        ((class_224)localObject1).jdField_field_98_of_type_Class_1360.a12(Math.min(((class_224)localObject1).jdField_field_106_of_type_Float, ((class_224)localObject1).jdField_field_98_of_type_Float), ((class_224)localObject1).jdField_field_98_of_type_Float);
        ((class_224)localObject1).jdField_field_106_of_type_Float = 0.0F;
      }
    }
    ((class_224)localObject1).jdField_field_98_of_type_Class_1360.a6((class_941)localObject2);
    ((class_224)localObject1).jdField_field_106_of_type_JavaxVecmathVector3f.set(class_969.a1().a83());
    this.jdField_field_89_of_type_Class_220.a1(paramclass_941);
    class_249.b();
    this.jdField_field_89_of_type_Class_218.a1(paramclass_941);
    this.jdField_field_89_of_type_Class_254.a1(paramclass_941);
    this.jdField_field_89_of_type_Class_261.a1(paramclass_941);
    localObject2 = paramclass_941;
    Object localObject1 = this.jdField_field_89_of_type_Class_331;
    class_883.jdField_field_89_of_type_JavaUtilArrayList.remove(((class_331)localObject1).jdField_field_98_of_type_Class_297);
    if (((((class_331)localObject1).a34().a36().field_6) || (((class_331)localObject1).a35().field_4.field_6) || (((class_331)localObject1).a33().field_5)) && (((class_331)localObject1).jdField_field_98_of_type_Class_371.a3() != null))
    {
      if (((class_331)localObject1).field_109)
      {
        ((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataWorldSegment = null;
        ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataWorldSegment = null;
        ((class_331)localObject1).jdField_field_98_of_type_Class_796 = null;
        ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation = null;
        ((class_331)localObject1).field_109 = false;
      }
      ((class_331)localObject1).jdField_field_98_of_type_Class_1383.a((class_941)localObject2);
      ((class_331)localObject1).jdField_field_106_of_type_Class_1383.a((class_941)localObject2);
      try
      {
        localObject3 = ((class_331)localObject1).a32();
        localObject2 = new Vector3f(class_969.a1().a83());
        if ((localObject3 == null) && (((class_331)localObject1).jdField_field_98_of_type_Class_371.a3() != null)) {
          ((Vector3f)localObject2).set(((class_331)localObject1).jdField_field_98_of_type_Class_371.a3().a145().origin);
        }
        localObject4 = new Vector3f((Vector3f)localObject2);
        Vector3f localVector3f;
        if (!Float.isNaN((localVector3f = new Vector3f(class_969.a1().c10())).field_615))
        {
          if (Keyboard.isKeyDown(class_367.field_758.a5()))
          {
            localObject4 = new Vector3f(((class_331)localObject1).jdField_field_98_of_type_Class_371.a27().jdField_field_90_of_type_JavaxVecmathVector3f);
            localVector3f.sub((Tuple3f)localObject4, (Tuple3f)localObject2);
            localVector3f.normalize();
          }
          localVector3f.scale(localObject3 != null ? 160.0F : 6.0F);
          ((Vector3f)localObject4).add(localVector3f);
          ((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback = ((PhysicsExt)((class_331)localObject1).jdField_field_98_of_type_Class_371.a19()).testRayCollisionPoint((Vector3f)localObject2, (Vector3f)localObject4, false, null, localObject3 != null ? ((class_453)localObject3).a68() : null, false, ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataWorldSegment, true);
          if ((((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback != null) && (((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback.hasHit()) && ((((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback instanceof CubeRayCastResult)) && ((localObject2 = (CubeRayCastResult)((class_331)localObject1).jdField_field_98_of_type_ComBulletphysicsCollisionDispatchCollisionWorld$ClosestRayResultCallback).getSegment() != null))
          {
            ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataWorldSegment = ((CubeRayCastResult)localObject2).getSegment();
            if (((((CubeRayCastResult)localObject2).getSegment() != null) && (((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataWorldSegment != null) && (!((CubeRayCastResult)localObject2).getSegment().equals(((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataWorldSegment))) || (!((CubeRayCastResult)localObject2).cubePos.equals(((class_331)localObject1).jdField_field_98_of_type_Class_35)) || (((class_331)localObject1).jdField_field_98_of_type_Class_796 == null))
            {
              ((class_331)localObject1).jdField_field_98_of_type_Class_35.b1(((CubeRayCastResult)localObject2).cubePos);
              ((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataWorldSegment = ((CubeRayCastResult)localObject2).getSegment();
              ((class_331)localObject1).jdField_field_98_of_type_Class_796 = new class_796(((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataWorldSegment, ((class_331)localObject1).jdField_field_98_of_type_Class_35);
              ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation = ElementKeyMap.getInfo(((class_331)localObject1).jdField_field_98_of_type_Class_796.a9());
            }
          }
          else
          {
            ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataWorldSegment = null;
            ((class_331)localObject1).jdField_field_98_of_type_Class_796 = null;
            ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation = null;
          }
          if (((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation != null) {
            if (((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation.isEnterable())
            {
              ((class_331)localObject1).jdField_field_98_of_type_Class_297.field_104 = ("Enter " + ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + " [" + class_367.field_732.b1() + "]");
              ((class_331)localObject1).jdField_field_98_of_type_Class_796.a8(((class_331)localObject1).jdField_field_98_of_type_Class_297.field_104);
              class_883.jdField_field_89_of_type_JavaUtilArrayList.add(((class_331)localObject1).jdField_field_98_of_type_Class_297);
            }
            else if (((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation.getFactory() != null)
            {
              localObject2 = "[" + class_367.field_736.b1() + "]: Enter Connection Mode";
              String str1;
              if ((localObject4 = ((class_331)localObject1).a33().field_4) != null) {
                if (!((class_796)localObject4).equals(((class_331)localObject1).jdField_field_98_of_type_Class_796))
                {
                  boolean bool = ((class_796)localObject4).a7().a15().getControlElementMap().isControlling(((class_796)localObject4).a2(((class_331)localObject1).jdField_field_98_of_type_Class_48), ((class_331)localObject1).jdField_field_98_of_type_Class_796.a2(new class_48(((class_331)localObject1).jdField_field_106_of_type_Class_48)), ((class_331)localObject1).jdField_field_98_of_type_Class_796.a9());
                  str1 = "[" + class_367.field_737.b1() + "]: " + (!bool ? "Connect" : "Disconnect") + " this Block to " + ElementKeyMap.getInfo(((class_796)localObject4).a9()).getName() + "\n[" + class_367.field_736.b1() + "]: Switch Connection Mode";
                }
                else
                {
                  str1 = "[" + class_367.field_736.b1() + "]: Exit Connection Mode";
                }
              }
              ((class_331)localObject1).jdField_field_98_of_type_Class_297.field_104 = ("[" + class_367.field_733.b1() + "]: Open Block Inventory\n" + str1);
              ((class_331)localObject1).jdField_field_98_of_type_Class_796.a8(((class_331)localObject1).jdField_field_98_of_type_Class_297.field_104);
              class_883.jdField_field_89_of_type_JavaUtilArrayList.add(((class_331)localObject1).jdField_field_98_of_type_Class_297);
            }
            else if (((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation.canActivate())
            {
              if (((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation.getId() == 16) {
                ((class_331)localObject1).jdField_field_98_of_type_Class_297.field_104 = ("Make Output [" + class_367.field_733.b1() + "] " + (((class_331)localObject1).jdField_field_98_of_type_Class_796.a10() ? "ON" : "OFF"));
              } else {
                ((class_331)localObject1).jdField_field_98_of_type_Class_297.field_104 = ("Activate " + ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + " [" + class_367.field_733.b1() + "]");
              }
              ((class_331)localObject1).jdField_field_98_of_type_Class_796.a8(((class_331)localObject1).jdField_field_98_of_type_Class_297.field_104);
              class_883.jdField_field_89_of_type_JavaUtilArrayList.add(((class_331)localObject1).jdField_field_98_of_type_Class_297);
            }
          }
          if (localObject3 != null)
          {
            if (((class_453)localObject3).a40() != null)
            {
              ((class_453)localObject3).a40().a12();
              if ((((class_331)localObject1).jdField_field_106_of_type_Class_796 != ((class_453)localObject3).a40()) && (((class_453)localObject3).a40().a9() != 0))
              {
                ((class_331)localObject1).jdField_field_106_of_type_Class_796 = ((class_453)localObject3).a40();
                ((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataElementElementInformation = ElementKeyMap.getInfo(((class_331)localObject1).jdField_field_106_of_type_Class_796.a9());
              }
              else if (((class_453)localObject3).a40().a9() != 0) {}
            }
            else
            {
              ((class_331)localObject1).jdField_field_106_of_type_Class_796 = null;
              ((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataElementElementInformation = null;
            }
            ((class_331)localObject1).jdField_field_98_of_type_Boolean = false;
            ((class_331)localObject1).jdField_field_106_of_type_Boolean = false;
            ((class_331)localObject1).field_108 = false;
            short s;
            if ((((class_331)localObject1).jdField_field_106_of_type_Class_796 != null) && ((s = ((class_331)localObject1).jdField_field_106_of_type_Class_796.a9()) != 0) && (((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation != null))
            {
              if (((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataElementElementInformation != null) {
                try
                {
                  if (((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation.getControlledBy().contains(Short.valueOf(s))) {
                    ((class_331)localObject1).jdField_field_98_of_type_Boolean = true;
                  }
                }
                catch (ElementClassNotFoundException localElementClassNotFoundException)
                {
                  localElementClassNotFoundException;
                }
              }
              if (!((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation.getControlling().isEmpty()) {
                ((class_331)localObject1).jdField_field_106_of_type_Boolean = true;
              }
            }
            if ((((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataElementElementInformation != null) && (((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation == ((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataElementElementInformation))
            {
              ((class_331)localObject1).field_108 = true;
              ((class_331)localObject1).jdField_field_106_of_type_Boolean = false;
            }
            String str2;
            if ((localObject1 = localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataElementElementInformation != null)
            {
              str2 = "SELECTED:\n" + ((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + (((class_331)localObject1).field_108 ? "\ndeselect with " + class_367.field_736.b1() : "");
              ((class_331)localObject1).jdField_field_98_of_type_Class_371.a4().b2(str2);
            }
            else
            {
              ((class_331)localObject1).jdField_field_98_of_type_Class_371.a4().c();
            }
            if (((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation != null)
            {
              str2 = ((class_331)localObject1).jdField_field_98_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + (((class_331)localObject1).jdField_field_98_of_type_Boolean ? "\n\n(dis)connect to " + ((class_331)localObject1).jdField_field_106_of_type_OrgSchemaGameCommonDataElementElementInformation.getName() + "\nwith " + class_367.field_737.b1() : "") + (((class_331)localObject1).jdField_field_106_of_type_Boolean ? "\nselect with " + class_367.field_737.b1() : "");
              ((class_331)localObject1).jdField_field_98_of_type_Class_371.a4().a12(str2);
            }
            else
            {
              ((class_331)localObject1).jdField_field_98_of_type_Class_371.a4().b();
            }
          }
        }
      }
      catch (Exception localException)
      {
        Object localObject3;
        (localObject3 = localException).printStackTrace();
        System.err.println("[BUILDMODEDRAWER] " + localObject3.getClass().getSimpleName() + ": " + ((Exception)localObject3).getMessage());
      }
    }
    this.jdField_field_89_of_type_Class_233.a5(paramclass_941);
    class_40.b("update");
    if (class_40.a1("update") > 15L) {
      System.err.println("[DRAWER][WARNING] update took " + class_40.a1("update") + " ms");
    }
  }
  
  private void u()
  {
    long l1 = System.currentTimeMillis();
    this.jdField_field_89_of_type_Class_218.d();
    this.jdField_field_89_of_type_Class_220.d();
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 5L) {
      System.err.println("[WORLDDRAWER] WARNING: CLEAR TOOK " + l2);
    }
    this.jdField_field_89_of_type_Class_233.a7(this.jdField_field_89_of_type_Class_371.a7());
    l1 = System.currentTimeMillis();
    this.jdField_field_89_of_type_Class_249.a1(this.jdField_field_89_of_type_Class_371.a7());
    Iterator localIterator = this.jdField_field_89_of_type_Class_371.a7().values().iterator();
    while (localIterator.hasNext())
    {
      class_797 localclass_797 = (class_797)localIterator.next();
      long l4 = System.currentTimeMillis();
      long l6;
      if ((localclass_797 instanceof class_747))
      {
        l6 = System.currentTimeMillis();
        long l8 = System.currentTimeMillis();
        Object localObject = new class_295((class_747)localclass_797);
        this.jdField_field_89_of_type_Class_218.jdField_field_98_of_type_JavaUtilArrayList.add(localObject);
        long l7;
        if ((l7 = System.currentTimeMillis() - l8) > 3L) {
          System.err.println("[BEAMDRAWER] WARNING SHIP PLUM ADD of " + localclass_797 + " took " + l7 + " ms");
        }
        l8 = System.currentTimeMillis();
        localObject = new class_356((class_747)localclass_797);
        this.jdField_field_89_of_type_Class_218.field_106.add(localObject);
        if ((l7 = System.currentTimeMillis() - l8) > 3L) {
          System.err.println("[BEAMDRAWER] WARNING SHIP MUZZLE ADD of " + localclass_797 + " took " + l7 + " ms");
        }
        l8 = System.currentTimeMillis();
        this.jdField_field_89_of_type_Class_220.a2((class_747)localclass_797);
        if ((l7 = System.currentTimeMillis() - l8) > 3L) {
          System.err.println("[BEAMDRAWER] WARNING SHIP SHIELD ADD of " + localclass_797 + " took " + l7 + " ms");
        }
        long l9;
        if ((l9 = System.currentTimeMillis() - l6) > 5L) {
          System.err.println("[BEAMDRAWER] WARNING SHIP NORMAL ADD of " + localclass_797 + " took " + l9 + " ms");
        }
      }
      if (((localclass_797 instanceof class_737)) || ((localclass_797 instanceof class_602)))
      {
        this.jdField_field_89_of_type_Class_220.a2((class_798)localclass_797);
        this.jdField_field_89_of_type_Class_249.a((class_798)localclass_797);
      }
      if ((l6 = System.currentTimeMillis() - l4) > 5L) {
        System.err.println("[WORLDDRAWER] WARNING: DRAWER UPDATE OF " + localclass_797 + " took " + l6);
      }
    }
    long l3;
    if ((l3 = System.currentTimeMillis() - l1) > 5L) {
      System.err.println("[WORLDDRAWER] WARNING: ADD TOOK " + l3);
    }
    l1 = System.currentTimeMillis();
    try
    {
      this.jdField_field_89_of_type_Class_283.d();
    }
    catch (ErrorDialogException localErrorDialogException2)
    {
      ErrorDialogException localErrorDialogException1;
      (localErrorDialogException1 = localErrorDialogException2).printStackTrace();
      class_933.a2(localErrorDialogException1);
    }
    long l5;
    if ((l5 = System.currentTimeMillis() - l1) > 5L) {
      System.err.println("[WORLDDRAWER] WARNING: CONNECTION UPDATE TOOK " + l5);
    }
  }
  
  public final class_283 a98()
  {
    return this.jdField_field_89_of_type_Class_283;
  }
  
  public final class_398 a99()
  {
    return this.jdField_field_89_of_type_Class_398;
  }
  
  public final void k()
  {
    this.field_97 = true;
  }
  
  public final class_218 a100()
  {
    return this.jdField_field_89_of_type_Class_218;
  }
  
  public final class_228 a101()
  {
    return this.jdField_field_89_of_type_Class_228;
  }
  
  public final class_249 a102()
  {
    return this.jdField_field_89_of_type_Class_249;
  }
  
  public final void l()
  {
    this.field_100 = true;
  }
  
  public final void m1()
  {
    this.field_96 = true;
  }
  
  static
  {
    jdField_field_90_of_type_Boolean = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_227
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */