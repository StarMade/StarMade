import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.newdawn.slick.Color;
import org.schema.common.FastMath;
import org.schema.game.common.data.world.Universe;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public class class_883
  extends class_1363
{
  public static ArrayList field_89;
  public static HashSet field_89;
  private class_972 jdField_field_89_of_type_Class_972;
  private class_972 jdField_field_90_of_type_Class_972;
  private class_930 jdField_field_89_of_type_Class_930;
  private Int2IntArrayMap jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap = new Int2IntArrayMap();
  private class_1387 jdField_field_89_of_type_Class_1387;
  private Vector3f jdField_field_92_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector4f jdField_field_89_of_type_JavaxVecmathVector4f;
  private long jdField_field_89_of_type_Long;
  private boolean jdField_field_89_of_type_Boolean;
  private class_1382 jdField_field_89_of_type_Class_1382;
  private class_1431 jdField_field_89_of_type_Class_1431;
  private class_797 jdField_field_89_of_type_Class_797;
  private static float jdField_field_89_of_type_Float;
  private int jdField_field_89_of_type_Int;
  private final Transform[] jdField_field_89_of_type_ArrayOfComBulletphysicsLinearmathTransform;
  private final String[] jdField_field_89_of_type_ArrayOfJavaLangString;
  private class_48[] jdField_field_89_of_type_ArrayOfClass_48;
  private class_48 jdField_field_89_of_type_Class_48;
  private Vector3f field_93;
  private Vector3f field_94;
  private Transform jdField_field_90_of_type_ComBulletphysicsLinearmathTransform;
  private static FloatBuffer jdField_field_90_of_type_JavaNioFloatBuffer;
  private Transform jdField_field_92_of_type_ComBulletphysicsLinearmathTransform;
  private final ArrayList jdField_field_90_of_type_JavaUtilArrayList;
  
  public static void a128(class_797 paramclass_797, Vector4f paramVector4f, boolean paramBoolean, class_371 paramclass_371)
  {
    if (paramBoolean)
    {
      paramVector4f.field_596 = (0.3F + jdField_field_89_of_type_Float);
      paramVector4f.field_597 = (0.3F + jdField_field_89_of_type_Float);
      paramVector4f.field_598 = (0.3F + jdField_field_89_of_type_Float);
      return;
    }
    paramBoolean = paramBoolean ? jdField_field_89_of_type_Float : 0.0F;
    if ((paramclass_797 instanceof class_365)) {
      ((class_365)paramclass_797).a26();
    }
    paramclass_371 = paramclass_371.a20().a142(paramclass_797.getFactionId());
    paramclass_797.getRelationColor(paramclass_371, paramVector4f, paramBoolean);
  }
  
  public class_883(ClientState paramClientState)
  {
    super(paramClientState);
    new Vector3f();
    this.jdField_field_89_of_type_JavaxVecmathVector4f = new Vector4f(0.0F, 1.0F, 0.0F, 1.0F);
    new Vector3f();
    this.jdField_field_89_of_type_Class_48 = new class_48();
    this.field_93 = new Vector3f();
    this.field_94 = new Vector3f();
    this.jdField_field_90_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform = new Transform();
    this.jdField_field_90_of_type_JavaUtilArrayList = new ArrayList();
    class_1380 localclass_1380 = class_969.a2().a5("hud-target-c-4x4-gui-");
    this.jdField_field_89_of_type_Class_972 = new class_972(localclass_1380, paramClientState);
    this.jdField_field_90_of_type_Class_972 = new class_972(class_969.a2().a5("hud-target-c-4x4-gui-"), paramClientState);
    this.jdField_field_89_of_type_Class_930 = new class_930(32, 32, paramClientState);
    this.jdField_field_89_of_type_Class_1431 = new class_1433(8.0F);
    this.jdField_field_89_of_type_Class_1387 = ((class_371)a24()).a23().a26();
    this.jdField_field_89_of_type_ArrayOfClass_48 = new class_48[6];
    this.jdField_field_89_of_type_ArrayOfComBulletphysicsLinearmathTransform = new Transform[6];
    this.jdField_field_89_of_type_ArrayOfJavaLangString = new String[6];
    for (int i = 0; i < this.jdField_field_89_of_type_ArrayOfComBulletphysicsLinearmathTransform.length; i++)
    {
      this.jdField_field_89_of_type_ArrayOfClass_48[i] = new class_48();
      this.jdField_field_89_of_type_ArrayOfComBulletphysicsLinearmathTransform[i] = new Transform();
      this.jdField_field_89_of_type_ArrayOfComBulletphysicsLinearmathTransform[i].setIdentity();
    }
    a84(((class_371)paramClientState).a17());
  }
  
  private void a84(class_48 paramclass_48)
  {
    int i = Universe.getSectorSizeWithMargin();
    class_48 localclass_481 = new class_48();
    Vector3f localVector3f = new Vector3f();
    localclass_481.b1(paramclass_48);
    for (int j = 0; j < 6; j++)
    {
      this.jdField_field_89_of_type_ArrayOfClass_48[j].b1(paramclass_48);
      localclass_481.b1(paramclass_48);
      class_48 localclass_482 = org.schema.game.common.data.element.Element.DIRECTIONSi[j];
      localclass_481.a1(localclass_482);
      this.jdField_field_89_of_type_ArrayOfClass_48[j].b1(localclass_481);
      localVector3f.set(localclass_482.field_475 * i, localclass_482.field_476 * i, localclass_482.field_477 * i);
      this.jdField_field_89_of_type_ArrayOfJavaLangString[j] = ("Sector " + localclass_481);
      this.jdField_field_89_of_type_ArrayOfComBulletphysicsLinearmathTransform[j].origin.set(localVector3f);
    }
  }
  
  public final void a2() {}
  
  protected final void d() {}
  
  public final void b()
  {
    if (this.jdField_field_89_of_type_Long + 50L < System.currentTimeMillis())
    {
      this.jdField_field_89_of_type_Boolean = true;
      this.jdField_field_89_of_type_Long = System.currentTimeMillis();
    }
    else
    {
      this.jdField_field_89_of_type_Boolean = false;
    }
    jdField_field_89_of_type_Float = 0.5F + this.jdField_field_89_of_type_Class_1431.a1() * 0.5F;
    Object localObject6 = null;
    Object localObject1 = ((class_371)a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_443;
    this.jdField_field_89_of_type_Class_797 = ((class_443)localObject1).a43();
    this.jdField_field_89_of_type_Class_1382 = null;
    if ((class_969.a1().a184() instanceof class_960))
    {
      localObject1 = (class_960)class_969.a1().a184();
      this.jdField_field_89_of_type_Class_1382 = ((class_960)localObject1).a142();
    }
    Object localObject5;
    Object localObject3;
    float f6;
    Object localObject4;
    Vector3f localVector3f4;
    for (int i = 0; i < this.jdField_field_89_of_type_ArrayOfComBulletphysicsLinearmathTransform.length; i++)
    {
      if ((!jdField_field_90_of_type_Boolean) && (this.jdField_field_89_of_type_ArrayOfJavaLangString[i] == null)) {
        throw new AssertionError();
      }
      this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform.set(this.jdField_field_89_of_type_ArrayOfComBulletphysicsLinearmathTransform[i]);
      localObject5 = this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform;
      int k = i;
      localObject3 = this;
      class_48 localclass_481;
      class_48 localclass_482 = class_789.a192(localclass_481 = this.jdField_field_89_of_type_ArrayOfClass_48[k], ((class_883)localObject3).jdField_field_89_of_type_Class_48);
      long l1 = ((class_371)((class_883)localObject3).a24()).a4().calculateStartTime();
      (localObject6 = new class_48(localclass_481)).c1(((class_371)((class_883)localObject3).a24()).a20().a44());
      ((Transform)localObject5).setIdentity();
      Vector3f localVector3f1;
      if (class_791.a19(((class_371)((class_883)localObject3).a24()).a20().a44()))
      {
        f6 = (float)((System.currentTimeMillis() - l1) % 1200000L) / 1200000.0F;
        localclass_482.a5(16);
        localclass_482.a(8, 8, 8);
        localclass_482.c1(((class_371)((class_883)localObject3).a24()).a20().a44());
        ((class_883)localObject3).field_93.set(((class_48)localObject6).field_475 * Universe.getSectorSizeWithMargin(), ((class_48)localObject6).field_476 * Universe.getSectorSizeWithMargin(), ((class_48)localObject6).field_477 * Universe.getSectorSizeWithMargin());
        ((class_883)localObject3).field_94.set(localclass_482.field_475 * Universe.getSectorSizeWithMargin(), localclass_482.field_476 * Universe.getSectorSizeWithMargin(), localclass_482.field_477 * Universe.getSectorSizeWithMargin());
        ((class_883)localObject3).jdField_field_90_of_type_ComBulletphysicsLinearmathTransform.setIdentity();
        if (((class_883)localObject3).field_94.lengthSquared() > 0.0F)
        {
          ((Transform)localObject5).origin.add(((class_883)localObject3).field_94);
          ((Transform)localObject5).basis.rotX(6.283186F * f6);
          (localVector3f1 = new Vector3f()).sub(((class_883)localObject3).field_93, ((class_883)localObject3).field_94);
          ((Transform)localObject5).origin.add(localVector3f1);
          ((Transform)localObject5).basis.transform(((Transform)localObject5).origin);
        }
        else
        {
          ((Transform)localObject5).basis.rotX(6.283186F * f6);
          ((Transform)localObject5).origin.set(((class_883)localObject3).field_93);
          ((Transform)localObject5).basis.transform(((Transform)localObject5).origin);
        }
      }
      else
      {
        ((Transform)localObject5).origin.set(((class_48)localObject6).field_475 * Universe.getSectorSizeWithMargin(), ((class_48)localObject6).field_476 * Universe.getSectorSizeWithMargin(), ((class_48)localObject6).field_477 * Universe.getSectorSizeWithMargin());
      }
      if (localclass_481.equals(((class_371)((class_883)localObject3).a24()).a4().a32().field_771)) {
        this.jdField_field_89_of_type_JavaxVecmathVector4f.set(0.1F + jdField_field_89_of_type_Float, 0.8F + jdField_field_89_of_type_Float, 0.6F + jdField_field_89_of_type_Float, 0.4F + jdField_field_89_of_type_Float);
      } else {
        this.jdField_field_89_of_type_JavaxVecmathVector4f.set(0.4F, 0.4F, 0.4F, 0.4F);
      }
      int m = -100 - i;
      localObject5 = this.jdField_field_89_of_type_ArrayOfJavaLangString[i];
      localObject4 = this.jdField_field_92_of_type_ComBulletphysicsLinearmathTransform;
      localObject3 = this;
      if (class_969.a1() != null)
      {
        ((class_883)localObject3).jdField_field_92_of_type_JavaxVecmathVector3f.set(((Transform)localObject4).origin);
        ((class_883)localObject3).jdField_field_92_of_type_JavaxVecmathVector3f.sub(class_969.a1().a83());
        float f5 = ((class_883)localObject3).jdField_field_92_of_type_JavaxVecmathVector3f.length();
        localVector3f4 = ((class_883)localObject3).jdField_field_89_of_type_Class_1387.a1(new Vector3f());
        localObject6 = ((class_883)localObject3).jdField_field_89_of_type_Class_1387.a2(((Transform)localObject4).origin, new Vector3f(), true);
        (localVector3f1 = new Vector3f((Vector3f)localObject6)).sub(localVector3f4);
        ((class_883)localObject3).jdField_field_89_of_type_Class_972.a147().c6(((class_883)localObject3).jdField_field_89_of_type_JavaxVecmathVector4f);
        ((class_883)localObject3).jdField_field_90_of_type_Class_972.a147().c6(((class_883)localObject3).jdField_field_89_of_type_JavaxVecmathVector4f);
        float f3;
        if (localVector3f1.length() > ((class_883)localObject3).jdField_field_89_of_type_Int)
        {
          localVector3f1.normalize();
          localVector3f1.scale(((class_883)localObject3).jdField_field_89_of_type_Int);
          localVector3f1.add(localVector3f4);
          ((class_883)localObject3).jdField_field_89_of_type_Class_972.a_2(0);
          ((class_883)localObject3).jdField_field_89_of_type_Class_972.a83().set(localVector3f1);
          Vector3f localVector3f5;
          (localVector3f5 = new Vector3f(((class_883)localObject3).jdField_field_89_of_type_Class_972.a83())).sub(localVector3f4);
          localVector3f5.scale(-1.0F);
          localVector3f5.normalize();
          f3 = class_49.a2(new Vector3f(0.0F, 1.0F, 0.0F), localVector3f5);
          ((class_883)localObject3).jdField_field_89_of_type_Class_972.b27(57.295776F * f3);
          ((class_883)localObject3).jdField_field_89_of_type_Class_972.b();
          ((class_883)localObject3).jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(m, 1);
        }
        else
        {
          f3.add(localVector3f4);
          if (!((class_883)localObject3).jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.containsKey(m)) {
            ((class_883)localObject3).jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(m, 1);
          }
          int i1 = ((class_883)localObject3).jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.get(m);
          ((class_883)localObject3).jdField_field_90_of_type_Class_972.a_2(i1);
          if (((class_883)localObject3).jdField_field_89_of_type_Boolean) {
            ((class_883)localObject3).jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(m, Math.min(15, i1 + 1));
          }
          ((class_883)localObject3).jdField_field_90_of_type_Class_972.a83().set(f3);
          ((class_883)localObject3).jdField_field_90_of_type_Class_972.b();
          ((class_883)localObject3).jdField_field_89_of_type_Class_930.a83().set(f3);
          ((class_883)localObject3).jdField_field_89_of_type_Class_930.field_90.set(0, localObject5);
          ((class_883)localObject3).jdField_field_89_of_type_Class_930.field_90.set(1, (int)f5 + "m");
          ((class_883)localObject3).jdField_field_89_of_type_Class_930.b();
        }
      }
    }
    GlUtil.d1();
    class_1380.b30(this.jdField_field_89_of_type_Class_972.a147());
    Object localObject2 = ((class_371)a24()).a7().values().iterator();
    while (((Iterator)localObject2).hasNext()) {
      if ((localObject3 = (class_797)((Iterator)localObject2).next()) != this.jdField_field_89_of_type_Class_1382)
      {
        localObject4 = localObject3;
        localObject3 = this;
        localObject5 = (class_371)a24();
        if ((class_969.a1() != null) && ((!(localObject4 instanceof class_747)) || (!((class_747)localObject4).c3())))
        {
          ((class_883)localObject3).jdField_field_92_of_type_JavaxVecmathVector3f.set(((class_797)localObject4).getWorldTransformClient().origin);
          if (((class_371)localObject5).a6() != null) {
            ((class_883)localObject3).jdField_field_92_of_type_JavaxVecmathVector3f.sub(((class_371)localObject5).a6().getWorldTransform().origin);
          } else {
            ((class_883)localObject3).jdField_field_92_of_type_JavaxVecmathVector3f.sub(class_969.a1().a83());
          }
          float f2 = ((class_883)localObject3).jdField_field_92_of_type_JavaxVecmathVector3f.length();
          if (((localObject4 != ((class_883)localObject3).jdField_field_89_of_type_Class_797) && (f2 > 1200.0F)) || (f2 < 1.0F) || (!((class_797)localObject4).isHidden()))
          {
            a128((class_797)localObject4, ((class_883)localObject3).jdField_field_89_of_type_JavaxVecmathVector4f, localObject4 == ((class_883)localObject3).jdField_field_89_of_type_Class_797, (class_371)localObject5);
            if (localObject4 == ((class_883)localObject3).jdField_field_89_of_type_Class_797) {
              ((class_883)localObject3).jdField_field_89_of_type_JavaxVecmathVector4f.field_599 = 1.0F;
            } else {
              ((class_883)localObject3).jdField_field_89_of_type_JavaxVecmathVector4f.field_599 = Math.min(1.0F, 1200.0F / (f2 * 10.0F));
            }
            Vector3f localVector3f3 = ((class_883)localObject3).jdField_field_89_of_type_Class_1387.a1(new Vector3f());
            localVector3f4 = ((class_883)localObject3).jdField_field_89_of_type_Class_1387.a2(((class_797)localObject4).getWorldTransformClient().origin, new Vector3f(), true);
            (localObject6 = new Vector3f(localVector3f4)).sub(localVector3f3);
            if (((Vector3f)localObject6).length() > ((class_883)localObject3).jdField_field_89_of_type_Int)
            {
              ((Vector3f)localObject6).normalize();
              ((Vector3f)localObject6).scale(((class_883)localObject3).jdField_field_89_of_type_Int);
              ((Vector3f)localObject6).add(localVector3f3);
              ((class_883)localObject3).jdField_field_89_of_type_Class_972.a83().set((Tuple3f)localObject6);
              ((class_883)localObject3).jdField_field_89_of_type_Class_972.a147().c6(((class_883)localObject3).jdField_field_89_of_type_JavaxVecmathVector4f);
              ((class_883)localObject3).jdField_field_89_of_type_Class_972.a147().b13(0);
              Vector3f localVector3f2;
              (localVector3f2 = new Vector3f(((class_883)localObject3).jdField_field_89_of_type_Class_972.a83())).sub(localVector3f3);
              localVector3f2.scale(-1.0F);
              localVector3f2.normalize();
              f6 = class_49.a2(new Vector3f(0.0F, 1.0F, 0.0F), localVector3f2);
              jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
              class_969.field_1259.store(jdField_field_90_of_type_JavaNioFloatBuffer);
              float f4 = FastMath.i(f6);
              float f1 = FastMath.j(f6);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(0, f1);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(4, -f4);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(8, 0.0F);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(1, f4);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(5, f1);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(9, 0.0F);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(2, 0.0F);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(6, 0.0F);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(10, 1.0F);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(12, class_969.field_1259.m30 + ((Vector3f)localObject6).field_615);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(13, class_969.field_1259.m31 + ((Vector3f)localObject6).field_616);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(14, class_969.field_1259.m32 + ((Vector3f)localObject6).field_617);
              jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
              GL11.glLoadMatrix(jdField_field_90_of_type_JavaNioFloatBuffer);
              class_1380.a148(((class_883)localObject3).jdField_field_89_of_type_Class_972.a147());
              if (localObject4 == ((class_883)localObject3).jdField_field_89_of_type_Class_797)
              {
                ((class_883)localObject3).jdField_field_89_of_type_Class_930.a83().set((Tuple3f)localObject6);
                ((class_883)localObject3).jdField_field_89_of_type_Class_930.field_90.set(0, ((class_797)localObject4).toNiceString());
                ((class_883)localObject3).jdField_field_89_of_type_Class_930.field_90.set(1, (int)f2 + "m");
              }
              ((class_883)localObject3).jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(((class_797)localObject4).getId(), 1);
            }
            else
            {
              ((class_883)localObject3).jdField_field_90_of_type_Class_972.a147().c6(((class_883)localObject3).jdField_field_89_of_type_JavaxVecmathVector4f);
              ((Vector3f)localObject6).add(localVector3f3);
              if (!((class_883)localObject3).jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.containsKey(((class_797)localObject4).getId())) {
                ((class_883)localObject3).jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(((class_797)localObject4).getId(), 1);
              }
              int n = ((class_883)localObject3).jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.get(((class_797)localObject4).getId());
              ((class_883)localObject3).jdField_field_90_of_type_Class_972.a_2(n);
              ((class_883)localObject3).jdField_field_90_of_type_Class_972.a147().b13(n);
              if (((class_883)localObject3).jdField_field_89_of_type_Boolean) {
                ((class_883)localObject3).jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.put(((class_797)localObject4).getId(), Math.min(15, n + 1));
              }
              jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
              class_969.field_1259.store(jdField_field_90_of_type_JavaNioFloatBuffer);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(12, class_969.field_1259.m30 + ((Vector3f)localObject6).field_615);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(13, class_969.field_1259.m31 + ((Vector3f)localObject6).field_616);
              jdField_field_90_of_type_JavaNioFloatBuffer.put(14, class_969.field_1259.m32 + ((Vector3f)localObject6).field_617);
              jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
              GL11.glLoadMatrix(jdField_field_90_of_type_JavaNioFloatBuffer);
              class_1380.a148(((class_883)localObject3).jdField_field_90_of_type_Class_972.a147());
              ((class_883)localObject3).jdField_field_90_of_type_Class_972.a83().set((Tuple3f)localObject6);
              if (localObject4 == ((class_883)localObject3).jdField_field_89_of_type_Class_797) {
                ((class_883)localObject3).jdField_field_90_of_type_JavaUtilArrayList.add(new class_885(((class_797)localObject4).toNiceString(), (int)f2 + "m", (Vector3f)localObject6));
              }
              if (((localObject4 instanceof class_747)) && (((class_747)localObject4).b14() > 0L))
              {
                long l2 = ((class_747)localObject4).a28() - (System.currentTimeMillis() - ((class_747)localObject4).b14());
                ((class_883)localObject3).jdField_field_90_of_type_JavaUtilArrayList.add(new class_885("CORE OVERHEATING", l2 / 1000L + "sec", (Vector3f)localObject6));
              }
            }
          }
        }
      }
    }
    class_1380.c14(this.jdField_field_89_of_type_Class_972.a147());
    GlUtil.c2();
    while (!this.jdField_field_90_of_type_JavaUtilArrayList.isEmpty())
    {
      localObject2 = (class_885)this.jdField_field_90_of_type_JavaUtilArrayList.remove(0);
      this.jdField_field_89_of_type_Class_930.a83().set(((class_885)localObject2).jdField_field_1119_of_type_JavaxVecmathVector3f);
      this.jdField_field_89_of_type_Class_930.field_90.set(0, ((class_885)localObject2).jdField_field_1119_of_type_JavaLangString);
      this.jdField_field_89_of_type_Class_930.field_90.set(1, ((class_885)localObject2).field_1120);
      this.jdField_field_89_of_type_Class_930.b();
    }
    try
    {
      for (int j = 0; j < jdField_field_89_of_type_JavaUtilArrayList.size(); j++) {
        a129((class_251)jdField_field_89_of_type_JavaUtilArrayList.get(j), class_969.a1(), true, 100.0F, this.jdField_field_89_of_type_Class_1387);
      }
      return;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {}
  }
  
  public final void e()
  {
    if (((class_371)a24()).a14().field_4.field_4.jdField_field_4_of_type_Class_475.field_6)
    {
      class_228 localclass_228 = ((class_371)a24()).a27().a101();
      Iterator localIterator = jdField_field_89_of_type_JavaUtilHashSet.iterator();
      while (localIterator.hasNext())
      {
        class_438 localclass_438 = (class_438)localIterator.next();
        class_48 localclass_48 = localclass_228.a29().a(new class_48());
        for (int i = 0; i < localclass_438.jdField_field_136_of_type_ArrayOfClass_347.length; i++)
        {
          class_347 localclass_347;
          if (((localclass_347 = localclass_438.jdField_field_136_of_type_ArrayOfClass_347[i]).a7()) && (localclass_228.a29().field_117.equals(localclass_438.jdField_field_136_of_type_Class_48)) && (localclass_347.a5(class_228.a28(), localclass_48))) {
            a129(localclass_347.a6(localclass_438.jdField_field_136_of_type_Class_48), localclass_228.a26(), false, 4000.0F, localclass_228.a27());
          }
        }
      }
    }
  }
  
  private void a129(class_251 paramclass_251, Camera paramCamera, boolean paramBoolean, float paramFloat, class_1387 paramclass_1387)
  {
    class_371 localclass_371 = (class_371)a24();
    this.jdField_field_92_of_type_JavaxVecmathVector3f.set(paramclass_251.a().origin);
    if ((paramBoolean) && (localclass_371.a6() != null) && (!(localclass_371.a6() instanceof class_747))) {
      this.jdField_field_92_of_type_JavaxVecmathVector3f.sub(localclass_371.a6().getWorldTransform().origin);
    } else {
      this.jdField_field_92_of_type_JavaxVecmathVector3f.sub(paramCamera.a83());
    }
    if (this.jdField_field_92_of_type_JavaxVecmathVector3f.length() > paramFloat) {
      return;
    }
    paramBoolean = paramclass_1387.a1(new Vector3f());
    paramCamera = paramclass_1387.a3(paramclass_251.a().origin, new Vector3f(), true, paramCamera);
    (paramCamera = new Vector3f(paramCamera)).sub(paramBoolean);
    this.jdField_field_90_of_type_Class_972.a147().c6(this.jdField_field_89_of_type_JavaxVecmathVector4f);
    paramCamera.add(paramBoolean);
    this.jdField_field_89_of_type_Class_930.a83().set(paramCamera);
    this.jdField_field_89_of_type_Class_930.field_90.set(0, paramclass_251.jdField_field_104_of_type_JavaLangString);
    this.jdField_field_89_of_type_Class_930.field_90.set(1, "");
    this.jdField_field_89_of_type_Class_930.b28(1.0F, 1.0F, 1.0F);
    if (paramclass_251.jdField_field_104_of_type_JavaxVecmathVector4f != null)
    {
      this.jdField_field_89_of_type_Class_930.field_89.field_1776 = paramclass_251.jdField_field_104_of_type_JavaxVecmathVector4f.field_596;
      this.jdField_field_89_of_type_Class_930.field_89.field_1777 = paramclass_251.jdField_field_104_of_type_JavaxVecmathVector4f.field_597;
      this.jdField_field_89_of_type_Class_930.field_89.field_1778 = paramclass_251.jdField_field_104_of_type_JavaxVecmathVector4f.field_598;
      this.jdField_field_89_of_type_Class_930.field_89.field_1779 = paramclass_251.jdField_field_104_of_type_JavaxVecmathVector4f.field_599;
    }
    this.jdField_field_89_of_type_Class_930.b();
    this.jdField_field_89_of_type_Class_930.field_89.field_1776 = 1.0F;
    this.jdField_field_89_of_type_Class_930.field_89.field_1777 = 1.0F;
    this.jdField_field_89_of_type_Class_930.field_89.field_1778 = 1.0F;
    this.jdField_field_89_of_type_Class_930.field_89.field_1779 = 1.0F;
  }
  
  public final float a3()
  {
    return Display.getDisplayMode().getHeight();
  }
  
  public final float b1()
  {
    return Display.getDisplayMode().getWidth();
  }
  
  public final void c()
  {
    this.jdField_field_89_of_type_Class_972.c();
    this.jdField_field_89_of_type_Class_930 = new class_930(32, 32, class_29.b2(), a24());
    this.jdField_field_89_of_type_Class_930.a135(Color.white);
    this.jdField_field_89_of_type_Class_930.field_90 = new ArrayList();
    this.jdField_field_89_of_type_Class_930.field_90.add("");
    this.jdField_field_89_of_type_Class_930.field_90.add("");
    this.jdField_field_89_of_type_Class_930.a161(2.0F, 2.0F, 0.0F);
    this.jdField_field_89_of_type_Class_930.c();
  }
  
  public final void f()
  {
    this.jdField_field_89_of_type_ItUnimiDsiFastutilIntsInt2IntArrayMap.clear();
    class_371 localclass_371 = (class_371)a24();
    a84(localclass_371.a20().a44());
  }
  
  public final void a12(class_941 paramclass_941)
  {
    this.jdField_field_89_of_type_Int = ((int)Math.min(class_933.a() / 2.4F, class_933.b() / 2.4F));
    this.jdField_field_89_of_type_Class_1431.a(paramclass_941);
    try
    {
      for (int i = 0; i < jdField_field_89_of_type_JavaUtilArrayList.size(); i++)
      {
        ((class_251)jdField_field_89_of_type_JavaUtilArrayList.get(i)).a1(paramclass_941);
        if (!((class_251)jdField_field_89_of_type_JavaUtilArrayList.get(i)).a2())
        {
          jdField_field_89_of_type_JavaUtilArrayList.remove(i);
          i--;
        }
      }
      return;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {}
  }
  
  static
  {
    jdField_field_89_of_type_JavaUtilArrayList = new ArrayList();
    jdField_field_89_of_type_JavaUtilHashSet = new HashSet();
    jdField_field_90_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
    BufferUtils.createFloatBuffer(16);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_883
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */