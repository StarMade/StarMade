package org.schema.game.client.view;

import class_1376;
import class_1377;
import class_1433;
import class_213;
import class_219;
import class_220;
import class_222;
import class_227;
import class_229;
import class_233;
import class_249;
import class_311;
import class_353;
import class_371;
import class_394;
import class_400;
import class_48;
import class_657;
import class_797;
import class_836;
import class_886;
import class_941;
import class_949;
import class_965;
import class_969;
import class_971;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.game.client.view.cubes.CubeMeshBufferContainer;
import org.schema.game.client.view.cubes.CubeOptOptMesh;
import org.schema.game.common.controller.SegmentBufferManager;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.BeamHandler.BeamState;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.objects.NetworkEntity;
import org.schema.schine.network.objects.remote.RemoteBoolean;

public class SegmentDrawer
  implements class_965
{
  private HashSet jdField_field_98_of_type_JavaUtilHashSet;
  private HashSet jdField_field_106_of_type_JavaUtilHashSet;
  private HashSet jdField_field_108_of_type_JavaUtilHashSet = new HashSet();
  public static int field_98;
  public int field_106;
  public int field_108;
  public static boolean field_98;
  private final ArrayList jdField_field_98_of_type_JavaUtilArrayList;
  public class_657[] field_98;
  private class_657[] jdField_field_110_of_type_ArrayOfClass_657;
  public class_657[] field_106;
  public class_657[] field_108;
  public class_657[] field_109;
  private int jdField_field_109_of_type_Int;
  public ObjectArrayList field_98;
  private boolean jdField_field_108_of_type_Boolean = true;
  public class_353 field_98;
  private class_371 jdField_field_98_of_type_Class_371;
  private final class_213 jdField_field_98_of_type_Class_213;
  private final class_219 jdField_field_98_of_type_Class_219;
  public static class_394 field_98;
  private HashSet jdField_field_109_of_type_JavaUtilHashSet = new HashSet();
  private SegmentController jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController;
  private long jdField_field_98_of_type_Long;
  private boolean jdField_field_109_of_type_Boolean;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f;
  private Vector3f jdField_field_106_of_type_JavaxVecmathVector3f;
  private final ArrayList jdField_field_106_of_type_JavaUtilArrayList;
  private ArrayList jdField_field_108_of_type_JavaUtilArrayList;
  private int jdField_field_110_of_type_Int;
  private Matrix4f jdField_field_98_of_type_OrgLwjglUtilVectorMatrix4f;
  private class_48 jdField_field_98_of_type_Class_48;
  private class_1433 jdField_field_98_of_type_Class_1433;
  private boolean jdField_field_110_of_type_Boolean;
  private long jdField_field_106_of_type_Long;
  private static boolean field_111;
  private final class_48 jdField_field_106_of_type_Class_48;
  private final class_48 jdField_field_108_of_type_Class_48;
  private static Transform jdField_field_98_of_type_ComBulletphysicsLinearmathTransform;
  public static boolean field_106;
  
  public static void main(String... paramVarArgs)
  {
    for (paramVarArgs = 0; paramVarArgs < 99; paramVarArgs++)
    {
      long l1 = System.nanoTime();
      long l2 = System.currentTimeMillis();
      Thread.sleep(12L);
      l2 = System.currentTimeMillis() - l2;
      System.out.println("millis: " + l2);
      long l3 = (l1 = System.nanoTime() - l1) / 1000000L;
      System.out.println("millis from nanos: " + l3 + ", nanos: " + l1 + " 1000000");
    }
  }
  
  public SegmentDrawer(class_371 paramclass_371)
  {
    this.jdField_field_106_of_type_Int = 1;
    this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList(512);
    this.jdField_field_98_of_type_Class_353 = new class_353((byte)0);
    new Vector3f();
    this.jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_106_of_type_JavaxVecmathVector3f = new Vector3f();
    new Transform();
    new Transform();
    new Vector3f();
    new Vector3f();
    this.jdField_field_106_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_108_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_110_of_type_Int = 0;
    this.jdField_field_98_of_type_OrgLwjglUtilVectorMatrix4f = new Matrix4f();
    BufferUtils.createFloatBuffer(16);
    this.jdField_field_98_of_type_Class_48 = new class_48(-1, 0, 0);
    this.jdField_field_98_of_type_Class_1433 = new class_1433(8.0F);
    this.jdField_field_106_of_type_Class_48 = new class_48();
    this.jdField_field_108_of_type_Class_48 = new class_48();
    new Matrix4f();
    new Matrix4f();
    this.jdField_field_98_of_type_Class_371 = paramclass_371;
    jdField_field_98_of_type_Class_394 = new class_394();
    this.jdField_field_98_of_type_ArrayOfClass_657 = new class_657[jdField_field_98_of_type_Class_394.field_787];
    this.jdField_field_110_of_type_ArrayOfClass_657 = new class_657[jdField_field_98_of_type_Class_394.field_787];
    this.jdField_field_106_of_type_ArrayOfClass_657 = new class_657[jdField_field_98_of_type_Class_394.field_787];
    this.jdField_field_108_of_type_ArrayOfClass_657 = new class_657[jdField_field_98_of_type_Class_394.field_787];
    this.jdField_field_109_of_type_ArrayOfClass_657 = new class_657[jdField_field_98_of_type_Class_394.field_787];
    this.jdField_field_98_of_type_Class_213 = new class_213(this);
    this.jdField_field_98_of_type_Class_219 = new class_219(this);
    this.jdField_field_98_of_type_JavaUtilArrayList = new ArrayList();
  }
  
  private void g()
  {
    synchronized (this.jdField_field_109_of_type_JavaUtilHashSet)
    {
      for (int i = 0; i < this.jdField_field_109_of_type_Int; i++) {
        if ((this.jdField_field_108_of_type_ArrayOfClass_657[i] != null) && (this.jdField_field_108_of_type_ArrayOfClass_657[i].a4() < this.jdField_field_106_of_type_Int))
        {
          this.jdField_field_108_of_type_ArrayOfClass_657[i].b7(false);
          this.jdField_field_109_of_type_JavaUtilHashSet.add(this.jdField_field_108_of_type_ArrayOfClass_657[i]);
        }
      }
      Object localObject3 = null;
      synchronized (this.jdField_field_106_of_type_JavaUtilArrayList)
      {
        this.jdField_field_109_of_type_JavaUtilHashSet.addAll(this.jdField_field_106_of_type_JavaUtilArrayList);
        this.jdField_field_106_of_type_JavaUtilArrayList.clear();
      }
      ??? = this.jdField_field_109_of_type_JavaUtilHashSet.iterator();
      while (((Iterator)???).hasNext())
      {
        class_657 localclass_657;
        if (!(localclass_657 = (class_657)((Iterator)???).next()).c())
        {
          localclass_657.c1();
          localclass_657.b3();
          localclass_657.b7(false);
          this.jdField_field_108_of_type_JavaUtilArrayList.add(localclass_657);
        }
      }
      this.jdField_field_108_of_type_JavaUtilArrayList.isEmpty();
      if ((this.jdField_field_108_of_type_JavaUtilArrayList.isEmpty()) && (!this.jdField_field_109_of_type_JavaUtilHashSet.isEmpty())) {
        System.err.println("not Disposed LEFT: " + this.jdField_field_108_of_type_JavaUtilArrayList.size() + "/" + this.jdField_field_109_of_type_JavaUtilHashSet.size());
      }
      this.jdField_field_109_of_type_JavaUtilHashSet.removeAll(this.jdField_field_108_of_type_JavaUtilArrayList);
      this.jdField_field_108_of_type_JavaUtilArrayList.clear();
    }
    synchronized (this.jdField_field_98_of_type_Class_219.field_640)
    {
      this.jdField_field_98_of_type_Class_219.field_640.notify();
    }
    jdField_field_98_of_type_Class_394.a1(this.jdField_field_106_of_type_Int);
  }
  
  public final void a()
  {
    jdField_field_98_of_type_Class_394.b();
  }
  
  public final void d()
  {
    synchronized (this.jdField_field_98_of_type_JavaUtilArrayList)
    {
      this.jdField_field_98_of_type_JavaUtilArrayList.clear();
      return;
    }
  }
  
  private void h()
  {
    class_1376.field_1566.field_1578 = CubeOptOptMesh.jdField_field_89_of_type_Class_836;
    class_1376.field_1566.b();
    GL11.glDisable(2896);
    GL13.glActiveTexture(33984);
    GL11.glEnable(2929);
    GL11.glEnable(2884);
    if (!field_111)
    {
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
    }
    else
    {
      GL11.glDisable(3042);
    }
    this.jdField_field_98_of_type_Class_353.field_698 = 0L;
    this.jdField_field_98_of_type_Class_353.field_699 = 0L;
    GL11.glEnableClientState(32884);
  }
  
  public final void b()
  {
    field_111 = Keyboard.isKeyDown(88);
    Keyboard.isKeyDown(66);
    long l1 = System.currentTimeMillis();
    this.jdField_field_106_of_type_Long = l1;
    if (this.jdField_field_108_of_type_Boolean) {
      c();
    }
    if (jdField_field_98_of_type_Boolean)
    {
      System.err.println("Executing FULL vis update");
      synchronized (this.jdField_field_98_of_type_JavaUtilArrayList)
      {
        Iterator localIterator = this.jdField_field_98_of_type_JavaUtilArrayList.iterator();
        while (localIterator.hasNext())
        {
          Object localObject1 = (SegmentController)localIterator.next();
          localObject4 = localObject1;
          localObject1 = this;
          ((SegmentController)localObject4).getSegmentBuffer().a18(new class_311((SegmentDrawer)localObject1), false);
        }
      }
    }
    long l2 = System.currentTimeMillis();
    if (System.currentTimeMillis() - this.jdField_field_98_of_type_Class_213.jdField_field_637_of_type_Long > 1000L)
    {
      this.jdField_field_98_of_type_Class_213.jdField_field_637_of_type_Long = System.currentTimeMillis();
      this.jdField_field_98_of_type_Class_213.jdField_field_637_of_type_JavaLangObject = Integer.valueOf(this.jdField_field_98_of_type_Class_213.jdField_field_637_of_type_Int);
      this.jdField_field_98_of_type_Class_213.jdField_field_637_of_type_Int = 0;
    }
    class_971.jdField_field_98_of_type_JavaUtilArrayList.add("CONTEXT UPDATES: " + this.jdField_field_98_of_type_Class_213.jdField_field_637_of_type_JavaLangObject + "; enqueued: " + this.jdField_field_98_of_type_JavaUtilHashSet.size());
    this.jdField_field_98_of_type_Long = System.currentTimeMillis();
    System.currentTimeMillis();
    System.currentTimeMillis();
    h();
    this.jdField_field_98_of_type_Class_353.jdField_field_688_of_type_Long = 0L;
    this.jdField_field_98_of_type_Class_353.jdField_field_689_of_type_Long = 0L;
    this.jdField_field_98_of_type_Class_353.field_700 = 0L;
    this.jdField_field_98_of_type_Class_353.field_701 = 0L;
    this.jdField_field_98_of_type_Class_353.field_690 = 0L;
    this.jdField_field_98_of_type_Class_353.field_691 = 0L;
    this.jdField_field_98_of_type_Class_353.field_693 = 0L;
    this.jdField_field_98_of_type_Class_353.field_695 = 0L;
    this.jdField_field_98_of_type_Class_353.field_692 = 0L;
    this.jdField_field_98_of_type_Class_353.field_694 = 0L;
    this.jdField_field_98_of_type_Class_353.field_702 = 0L;
    this.jdField_field_98_of_type_Class_353.field_703 = 0L;
    this.jdField_field_98_of_type_OrgLwjglUtilVectorMatrix4f.load(class_969.jdField_field_1259_of_type_OrgLwjglUtilVectorMatrix4f);
    GlUtil.d1();
    this.jdField_field_98_of_type_Class_353.field_697 = 0L;
    this.jdField_field_110_of_type_Int += 1;
    long l3;
    if ((l3 = System.currentTimeMillis() - l2) > 10L) {
      System.err.println("PREPARE TIME OF 0 elements: " + l3);
    }
    Object localObject4 = this.jdField_field_98_of_type_Class_371.a27().a89().a9();
    BeamHandler.BeamState localBeamState = null;
    boolean bool;
    if (((bool = class_949.field_1186.b1())) && (!((ObjectHeapPriorityQueue)localObject4).isEmpty())) {
      localBeamState = (BeamHandler.BeamState)((ObjectHeapPriorityQueue)localObject4).dequeue();
    }
    synchronized (this.jdField_field_98_of_type_ArrayOfClass_657)
    {
      long l4 = System.currentTimeMillis();
      synchronized (this.jdField_field_98_of_type_Class_219.field_640)
      {
        if (this.jdField_field_109_of_type_Boolean)
        {
          g();
          this.jdField_field_109_of_type_Boolean = false;
        }
      }
      long l5;
      if ((l5 = System.currentTimeMillis() - l4) > 10L) {
        System.err.println("RESORTING TIME OF 0 elements: " + l5);
      }
      int i = Math.min(((Integer)class_949.field_1248.a4()).intValue(), this.jdField_field_109_of_type_Int);
      long l6 = System.nanoTime();
      long l7 = System.currentTimeMillis();
      this.jdField_field_98_of_type_Class_48.b(-1, 0, 0);
      class_400.b();
      if (field_111)
      {
        GL11.glDisable(3042);
        j = 0;
        class_657 localclass_6572;
        for (int k = 0; k < i; k++)
        {
          localclass_6572 = this.jdField_field_98_of_type_ArrayOfClass_657[k];
          if (a63(localclass_6572))
          {
            this.jdField_field_110_of_type_ArrayOfClass_657[j] = localclass_6572;
            j++;
          }
        }
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        for (k = j - 1; k >= 0; k--)
        {
          localclass_6572 = this.jdField_field_110_of_type_ArrayOfClass_657[k];
          a63(localclass_6572);
        }
      }
      else
      {
        for (j = i - 1; j >= 0; j--)
        {
          class_657 localclass_6571 = this.jdField_field_98_of_type_ArrayOfClass_657[j];
          if (bool)
          {
            int m = 0;
            while ((localBeamState != null) && (localclass_6571.jdField_field_34_of_type_Float < localBeamState.camDistStart))
            {
              if (m == 0)
              {
                GlUtil.c2();
                i();
              }
              GlUtil.d1();
              m = 1;
              this.jdField_field_98_of_type_Class_371.a27().a89().g();
              class_229.a10(localBeamState, jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
              GlUtil.c2();
              if (!((ObjectHeapPriorityQueue)localObject4).isEmpty()) {
                localBeamState = (BeamHandler.BeamState)((ObjectHeapPriorityQueue)localObject4).dequeue();
              } else {
                localBeamState = null;
              }
            }
            if (m != 0)
            {
              this.jdField_field_98_of_type_Class_371.a27().a89();
              class_233.h();
              h();
              GlUtil.d1();
            }
          }
          a63(localclass_6571);
        }
      }
      int j = (int)(System.currentTimeMillis() - l7);
      long l8;
      if ((float)(l8 = System.nanoTime() - l6) / 1000000.0F > 25.0F) {
        System.err.println("DRAWING TIME OF " + i + " elements: " + j + "(" + (float)l8 / 1000000.0F + "); unifroms: " + (float)this.jdField_field_98_of_type_Class_353.field_693 / 1000000.0F + "; pointer " + (float)this.jdField_field_98_of_type_Class_353.field_690 / 1000000.0F + "; upChk " + (float)this.jdField_field_98_of_type_Class_353.field_701 / 1000000.0F + "; frust " + (float)this.jdField_field_98_of_type_Class_353.field_700 / 1000000.0F + "; update " + (float)this.jdField_field_98_of_type_Class_353.field_699 / 1000000.0F + "; draw: " + (float)this.jdField_field_98_of_type_Class_353.field_694 / 1000000.0F + "; totD: " + (float)this.jdField_field_98_of_type_Class_353.field_695 / 1000000.0F);
      }
    }
    i();
    System.currentTimeMillis();
    GlUtil.c2();
    if ((bool) && ((!((ObjectHeapPriorityQueue)localObject4).isEmpty()) || (localBeamState != null)))
    {
      this.jdField_field_98_of_type_Class_371.a27().a89().g();
      if (localBeamState != null)
      {
        GlUtil.d1();
        class_229.a10(localBeamState, jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
        GlUtil.c2();
      }
      while (!((ObjectHeapPriorityQueue)localObject4).isEmpty())
      {
        GlUtil.d1();
        class_229.a10((BeamHandler.BeamState)((ObjectHeapPriorityQueue)localObject4).dequeue(), jdField_field_98_of_type_ComBulletphysicsLinearmathTransform);
        GlUtil.c2();
      }
      this.jdField_field_98_of_type_Class_371.a27().a89();
      class_233.h();
    }
    System.currentTimeMillis();
    if (System.currentTimeMillis() - this.jdField_field_98_of_type_Class_353.field_704 > 1000L)
    {
      this.jdField_field_98_of_type_Class_353.jdField_field_689_of_type_Int = this.jdField_field_98_of_type_Class_353.jdField_field_688_of_type_Int;
      this.jdField_field_98_of_type_Class_353.jdField_field_688_of_type_Int = 0;
      this.jdField_field_98_of_type_Class_353.field_704 = System.currentTimeMillis();
    }
    class_971.jdField_field_98_of_type_JavaUtilArrayList.add("LUR: " + this.jdField_field_98_of_type_Class_353.jdField_field_689_of_type_Int);
    class_971.jdField_field_98_of_type_JavaUtilArrayList.add("RQU/RSEG/RR: " + class_371.field_147 + " / " + class_371.field_148 + " / " + class_371.field_149);
    if (Keyboard.isKeyDown(64)) {
      GlUtil.f1();
    }
  }
  
  private void i()
  {
    GL11.glDisableClientState(32884);
    this.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController = null;
    GL11.glDisable(3042);
    GL11.glShadeModel(7425);
    GL15.glBindBuffer(34962, 0);
    class_1376.field_1566.d();
  }
  
  private boolean a63(class_657 paramclass_657)
  {
    long l1 = System.nanoTime();
    class_657 localclass_657 = paramclass_657;
    Object localObject = this;
    if ((!localclass_657.d1()) && (localclass_657.jdField_field_34_of_type_Boolean) && (!localclass_657.c()))
    {
      SegmentBufferManager localSegmentBufferManager = (SegmentBufferManager)localclass_657.a15().getSegmentBuffer();
      ((SegmentDrawer)localObject).jdField_field_106_of_type_Class_48.b1(localclass_657.jdField_field_34_of_type_Class_48);
      ((SegmentDrawer)localObject).jdField_field_108_of_type_Class_48.b1(localclass_657.jdField_field_34_of_type_Class_48);
      ((SegmentDrawer)localObject).jdField_field_106_of_type_Class_48.c(48, 48, 48);
      ((SegmentDrawer)localObject).jdField_field_108_of_type_Class_48.a(48, 48, 48);
      long l3;
      if ((((l3 = System.currentTimeMillis() - localclass_657.field_35) > 100L) && (localSegmentBufferManager.a29(((SegmentDrawer)localObject).jdField_field_106_of_type_Class_48, ((SegmentDrawer)localObject).jdField_field_108_of_type_Class_48) > localclass_657.field_36)) || (l3 > 10000L))
      {
        localclass_657.jdField_field_34_of_type_Boolean = false;
        localclass_657.e(true);
        ((SegmentDrawer)localObject).jdField_field_98_of_type_Class_353.jdField_field_688_of_type_Int += 1;
      }
    }
    if (((localclass_657.d1()) || (localclass_657.a32() == null)) && (!localclass_657.c())) {
      ((SegmentDrawer)localObject).jdField_field_98_of_type_Class_213.a1(localclass_657);
    }
    if ((((localclass_657.a32() == null ? 0 : 1) == 0 ? 1 : 0) | (!paramclass_657.b6() ? 1 : 0)) != 0)
    {
      class_353.a(this.jdField_field_98_of_type_Class_353, System.nanoTime() - l1);
      class_353.b(this.jdField_field_98_of_type_Class_353, System.nanoTime() - l1);
      return false;
    }
    class_353.a(this.jdField_field_98_of_type_Class_353, System.nanoTime() - l1);
    paramclass_657.jdField_field_34_of_type_Long = this.jdField_field_98_of_type_Long;
    localclass_657 = paramclass_657;
    localObject = this;
    localclass_657.a30(((SegmentDrawer)localObject).jdField_field_98_of_type_JavaxVecmathVector3f, ((SegmentDrawer)localObject).jdField_field_106_of_type_JavaxVecmathVector3f);
    if (!class_969.a1().a185(((SegmentDrawer)localObject).jdField_field_98_of_type_JavaxVecmathVector3f, ((SegmentDrawer)localObject).jdField_field_106_of_type_JavaxVecmathVector3f))
    {
      class_353.b(this.jdField_field_98_of_type_Class_353, System.nanoTime() - l1);
      return false;
    }
    long l2 = System.nanoTime() - l1;
    this.jdField_field_98_of_type_Class_353.field_700 += l2;
    if (this.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController != paramclass_657.a15())
    {
      GlUtil.c2();
      GlUtil.d1();
      GlUtil.b3(paramclass_657.a15().getWorldTransformClient());
      this.jdField_field_98_of_type_Class_48.b(-1, 0, 0);
      this.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController = paramclass_657.a15();
      if (this.jdField_field_110_of_type_Boolean)
      {
        GlUtil.a33(class_1376.field_1566, "selectTime", 0.0F);
        this.jdField_field_110_of_type_Boolean = false;
      }
      if ((class_227.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController == this.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController) && (System.currentTimeMillis() - class_227.jdField_field_89_of_type_Long < 8000L)) {
        this.jdField_field_110_of_type_Boolean = true;
      }
    }
    if (this.jdField_field_110_of_type_Boolean) {
      GlUtil.a33(class_1376.field_1566, "selectTime", this.jdField_field_98_of_type_Class_1433.a1() * 0.34F);
    }
    l2 = System.nanoTime() - l1;
    this.jdField_field_98_of_type_Class_353.field_699 += l2;
    this.jdField_field_98_of_type_Class_353.jdField_field_688_of_type_Long += 1L;
    l2 = System.nanoTime() - l1;
    this.jdField_field_98_of_type_Class_353.field_698 += l2;
    if (paramclass_657.a32() != null)
    {
      class_657.d();
      if (this.jdField_field_98_of_type_Class_48.field_475 == -1)
      {
        if (CubeMeshBufferContainer.field_1665 < 3) {
          GL11.glTranslatef(paramclass_657.jdField_field_34_of_type_Class_48.field_475, paramclass_657.jdField_field_34_of_type_Class_48.field_476, paramclass_657.jdField_field_34_of_type_Class_48.field_477);
        }
      }
      else if (CubeMeshBufferContainer.field_1665 < 3) {
        GL11.glTranslatef(-this.jdField_field_98_of_type_Class_48.field_475 + paramclass_657.jdField_field_34_of_type_Class_48.field_475, -this.jdField_field_98_of_type_Class_48.field_476 + paramclass_657.jdField_field_34_of_type_Class_48.field_476, -this.jdField_field_98_of_type_Class_48.field_477 + paramclass_657.jdField_field_34_of_type_Class_48.field_477);
      }
      paramclass_657.a32().a3();
      class_371.field_152 += 1;
      if ((class_949.field_1195.b1()) && ((localObject = this.jdField_field_98_of_type_Class_371.a27().a95().a3(paramclass_657.a15())) != null) && (((class_222)localObject).a5()))
      {
        class_1376.field_1566.d();
        class_1376.field_1550.field_1578 = ((class_222)localObject).a6();
        class_1376.field_1550.b();
        GlUtil.a39(class_1376.field_1550, "segPos", paramclass_657.jdField_field_34_of_type_Class_48.field_475, paramclass_657.jdField_field_34_of_type_Class_48.field_476, paramclass_657.jdField_field_34_of_type_Class_48.field_477);
        GlUtil.a33(class_1376.field_1568, "m_ShieldPercentage", ((class_222)localObject).a7());
        paramclass_657.a32().a3();
        class_1376.field_1550.d();
        class_1376.field_1566.b();
      }
      this.jdField_field_98_of_type_Class_48.b1(paramclass_657.jdField_field_34_of_type_Class_48);
      class_353 tmp844_833 = this.jdField_field_98_of_type_Class_353;
      tmp844_833.jdField_field_689_of_type_Long = tmp844_833.jdField_field_689_of_type_Long;
      l2 = paramclass_657.a32().field_794.field_93;
      this.jdField_field_98_of_type_Class_353.field_690 += l2;
      class_353 tmp890_879 = this.jdField_field_98_of_type_Class_353;
      tmp890_879.field_691 = tmp890_879.field_691;
      class_353 tmp909_898 = this.jdField_field_98_of_type_Class_353;
      tmp909_898.field_692 = tmp909_898.field_692;
      class_353 tmp928_917 = this.jdField_field_98_of_type_Class_353;
      tmp928_917.field_693 = tmp928_917.field_693;
      class_353 tmp947_936 = this.jdField_field_98_of_type_Class_353;
      tmp947_936.field_694 = tmp947_936.field_694;
      l2 = paramclass_657.a32().field_794.field_90;
      this.jdField_field_98_of_type_Class_353.field_702 += l2;
    }
    l2 = paramclass_657.g() ? 0L : paramclass_657.b1();
    this.jdField_field_98_of_type_Class_353.field_697 += l2;
    l2 = CubeOptOptMesh.jdField_field_89_of_type_Long;
    this.jdField_field_98_of_type_Class_353.field_703 += l2;
    class_353.b(this.jdField_field_98_of_type_Class_353, System.nanoTime() - l1);
    return paramclass_657.a32().a1() > 0;
  }
  
  public final ArrayList a57()
  {
    return this.jdField_field_106_of_type_JavaUtilArrayList;
  }
  
  public final void e()
  {
    synchronized (this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectArrayList)
    {
      while (!this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.isEmpty())
      {
        class_657 localclass_6571 = (class_657)this.jdField_field_98_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.remove(0);
        if ((!field_112) && (!localclass_6571.c())) {
          throw new AssertionError(localclass_6571.jdField_field_34_of_type_Class_48);
        }
        if ((!field_112) && (localclass_6571.b5() == null)) {
          throw new AssertionError(localclass_6571.jdField_field_34_of_type_Class_48);
        }
        synchronized (localclass_6571.jdField_field_34_of_type_JavaLangObject)
        {
          class_657 localclass_6572 = localclass_6571;
          SegmentDrawer localSegmentDrawer = this;
          if ((!field_112) && (localclass_6572.b4() == null)) {
            throw new AssertionError();
          }
          System.currentTimeMillis();
          localclass_6572.b5().a2(localclass_6572.b4());
          localSegmentDrawer.jdField_field_98_of_type_Class_371.a27().a102().a4(localclass_6572);
          System.currentTimeMillis();
          localclass_6571.c1();
          localclass_6571.a2();
          localclass_6571.b7(true);
          localclass_6571.d2(false);
        }
      }
      GL15.glBindBuffer(34962, 0);
      return;
    }
  }
  
  public final void c()
  {
    f();
    this.jdField_field_98_of_type_Class_213.start();
    this.jdField_field_98_of_type_Class_219.start();
    this.jdField_field_108_of_type_Boolean = false;
  }
  
  public final void a1(class_941 paramclass_941)
  {
    class_394.a();
    class_941 localclass_941 = paramclass_941;
    class_836 localclass_836;
    (localclass_836 = CubeOptOptMesh.jdField_field_89_of_type_Class_836).jdField_field_107_of_type_Float += localclass_941.a();
    if (localclass_836.jdField_field_107_of_type_Float > 0.5F)
    {
      localclass_836.jdField_field_107_of_type_Float -= 0.5F;
      localclass_836.jdField_field_107_of_type_Int = ((localclass_836.jdField_field_107_of_type_Int + 1) % 4);
    }
    this.jdField_field_98_of_type_Class_213.a2();
    this.jdField_field_98_of_type_Class_1433.a(paramclass_941);
  }
  
  public final void f()
  {
    synchronized (this.jdField_field_98_of_type_JavaUtilArrayList)
    {
      Object localObject2;
      for (int i = 0; i < this.jdField_field_98_of_type_JavaUtilArrayList.size(); i++) {
        if ((((Boolean)(localObject2 = (SegmentController)this.jdField_field_98_of_type_JavaUtilArrayList.get(i)).getNetworkObject().markedDeleted.get()).booleanValue()) || (!this.jdField_field_98_of_type_Class_371.a7().containsKey(((SegmentController)localObject2).getId())))
        {
          this.jdField_field_98_of_type_JavaUtilArrayList.remove(i);
          i--;
        }
      }
      Iterator localIterator = this.jdField_field_98_of_type_Class_371.a7().values().iterator();
      while (localIterator.hasNext()) {
        if (((localObject2 = (class_797)localIterator.next()) instanceof SegmentController))
        {
          localObject2 = (SegmentController)localObject2;
          if ((!this.jdField_field_98_of_type_JavaUtilArrayList.contains(localObject2)) && (localObject2 != null)) {
            this.jdField_field_98_of_type_JavaUtilArrayList.add(localObject2);
          }
        }
      }
      return;
    }
  }
  
  static
  {
    jdField_field_98_of_type_Int = 0;
    field_111 = false;
    (SegmentDrawer.jdField_field_98_of_type_ComBulletphysicsLinearmathTransform = new Transform()).setIdentity();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.client.view.SegmentDrawer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */