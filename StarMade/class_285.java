import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ControlElementMapper;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GlUtil;

public class class_285
  implements class_965
{
  private final SegmentController jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController;
  private final Map jdField_field_98_of_type_JavaUtilMap = new HashMap();
  private int jdField_field_98_of_type_Int;
  private Vector3f jdField_field_98_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_106_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_108_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_109_of_type_JavaxVecmathVector3f = new Vector3f(0.0F, 1.0F, 0.0F);
  private Vector3f field_110 = new Vector3f();
  private static Vector3f[] jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f;
  private static Vector3f[] jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f;
  private static Vector3f[] jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f;
  private static Vector3f[] jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f;
  private Matrix3f jdField_field_98_of_type_JavaxVecmathMatrix3f = new Matrix3f();
  private AxisAngle4f jdField_field_98_of_type_JavaxVecmathAxisAngle4f = new AxisAngle4f();
  private int jdField_field_106_of_type_Int;
  private boolean jdField_field_98_of_type_Boolean = true;
  private boolean jdField_field_106_of_type_Boolean;
  private int jdField_field_108_of_type_Int;
  private int jdField_field_109_of_type_Int;
  private class_1393 jdField_field_98_of_type_Class_1393;
  private static IntBuffer jdField_field_98_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private static IntBuffer jdField_field_106_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private static IntBuffer jdField_field_108_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  
  public class_285(SegmentController paramSegmentController)
  {
    this.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
  }
  
  public final void a()
  {
    if (this.jdField_field_98_of_type_Int != 0) {
      GL15.glDeleteBuffers(this.jdField_field_98_of_type_Int);
    }
  }
  
  public final void b()
  {
    if (this.jdField_field_98_of_type_Boolean)
    {
      class_285 localclass_285 = this;
      this.jdField_field_98_of_type_JavaUtilMap.clear();
      Iterator localIterator = localclass_285.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController.getControlElementMap().getControllingMap().getAll().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry;
        if (((ObjectOpenHashSet)(localEntry = (Map.Entry)localIterator.next()).getValue()).size() > 0)
        {
          short s = 0;
          ObjectIterator localObjectIterator = ((ObjectOpenHashSet)localEntry.getValue()).iterator();
          while ((localObjectIterator.hasNext()) && ((s = ((class_916)localObjectIterator.next()).jdField_field_1139_of_type_Short) == 32767)) {}
          if (ElementKeyMap.getFactorykeyset().contains(Short.valueOf(s))) {
            localclass_285.jdField_field_98_of_type_JavaUtilMap.put(ElementCollection.getPosFromIndex(((Long)localEntry.getKey()).longValue(), new class_48()), localEntry.getValue());
          } else {
            System.err.println("the type is not a factory type: " + s);
          }
        }
      }
      e();
      this.jdField_field_98_of_type_Boolean = false;
    }
    if ((!this.jdField_field_106_of_type_Boolean) && (this.jdField_field_98_of_type_JavaUtilMap.size() > 0))
    {
      GL11.glEnable(2903);
      GL11.glEnable(2896);
      this.jdField_field_98_of_type_Class_1393.a2();
      GL11.glEnableClientState(32884);
      GL11.glEnableClientState(32885);
      GL11.glEnableClientState(32888);
      GL15.glBindBuffer(34962, this.jdField_field_109_of_type_Int);
      GL11.glTexCoordPointer(3, 5126, 0, 0L);
      GL15.glBindBuffer(34962, this.jdField_field_108_of_type_Int);
      GL11.glNormalPointer(5126, 0, 0L);
      GL15.glBindBuffer(34962, this.jdField_field_98_of_type_Int);
      GL11.glVertexPointer(3, 5126, 0, 0L);
      GL11.glDrawArrays(7, 0, this.jdField_field_106_of_type_Int);
      GL15.glBindBuffer(34962, 0);
      GL11.glDisableClientState(32884);
      GL11.glDisableClientState(32885);
      GL11.glDisableClientState(32888);
      GL11.glDisable(2903);
    }
  }
  
  private void e()
  {
    int i = 0;
    Object localObject1 = this.jdField_field_98_of_type_JavaUtilMap.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = ((ObjectOpenHashSet)((Map.Entry)((Iterator)localObject1).next()).getValue()).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        ((Iterator)localObject2).next();
        i++;
      }
    }
    this.jdField_field_106_of_type_Boolean = (i == 0);
    if (this.jdField_field_106_of_type_Boolean)
    {
      System.err.println("NOTHING TO DRAW");
      return;
    }
    System.err.println("UPDATING CONNECTIONS: " + i);
    this.jdField_field_106_of_type_Int = (i << 1 << 1 << 3);
    localObject1 = GlUtil.a5(this.jdField_field_106_of_type_Int * 3 << 2, 0).asFloatBuffer();
    FloatBuffer localFloatBuffer = GlUtil.a5(this.jdField_field_106_of_type_Int * 3 << 2, 1).asFloatBuffer();
    Object localObject2 = GlUtil.a5(this.jdField_field_106_of_type_Int * 3 << 2, 2).asFloatBuffer();
    i = 0;
    Iterator localIterator = this.jdField_field_98_of_type_JavaUtilMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject3 = (Map.Entry)localIterator.next();
      this.jdField_field_98_of_type_JavaxVecmathVector3f.set(-8.0F + ((class_48)((Map.Entry)localObject3).getKey()).field_475, -8.0F + ((class_48)((Map.Entry)localObject3).getKey()).field_476, -8.0F + ((class_48)((Map.Entry)localObject3).getKey()).field_477);
      this.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransformClient().transform(this.jdField_field_98_of_type_JavaxVecmathVector3f);
      localObject3 = ((ObjectOpenHashSet)((Map.Entry)localObject3).getValue()).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        class_916 localclass_916 = (class_916)((Iterator)localObject3).next();
        this.jdField_field_106_of_type_JavaxVecmathVector3f.set(-8.0F + localclass_916.jdField_field_1139_of_type_Int, -8.0F + localclass_916.field_1140, -8.0F + localclass_916.field_1141);
        this.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController.getWorldTransformClient().transform(this.jdField_field_106_of_type_JavaxVecmathVector3f);
        i += a20(this.jdField_field_98_of_type_JavaxVecmathVector3f, this.jdField_field_106_of_type_JavaxVecmathVector3f, (FloatBuffer)localObject1, localFloatBuffer, (FloatBuffer)localObject2);
      }
    }
    if ((!jdField_field_108_of_type_Boolean) && (this.jdField_field_106_of_type_Int != i)) {
      throw new AssertionError(this.jdField_field_106_of_type_Int + "/" + i);
    }
    if (this.jdField_field_98_of_type_Int == 0)
    {
      GL15.glGenBuffers(jdField_field_98_of_type_JavaNioIntBuffer);
      this.jdField_field_98_of_type_Int = jdField_field_98_of_type_JavaNioIntBuffer.get(0);
      GL15.glGenBuffers(jdField_field_106_of_type_JavaNioIntBuffer);
      this.jdField_field_108_of_type_Int = jdField_field_106_of_type_JavaNioIntBuffer.get(0);
      GL15.glGenBuffers(jdField_field_108_of_type_JavaNioIntBuffer);
      this.jdField_field_109_of_type_Int = jdField_field_108_of_type_JavaNioIntBuffer.get(0);
    }
    GL15.glBindBuffer(34962, this.jdField_field_98_of_type_Int);
    ((FloatBuffer)localObject1).flip();
    System.err.println("BUFFER LIMIT " + ((FloatBuffer)localObject1).limit() + " / " + (this.jdField_field_106_of_type_Int * 3 << 2) + " (" + this.jdField_field_106_of_type_Int + ")");
    GL15.glBufferData(34962, (FloatBuffer)localObject1, 35044);
    GL15.glBindBuffer(34962, this.jdField_field_108_of_type_Int);
    localFloatBuffer.flip();
    GL15.glBufferData(34962, localFloatBuffer, 35044);
    GL15.glBindBuffer(34962, this.jdField_field_109_of_type_Int);
    ((FloatBuffer)localObject2).flip();
    GL15.glBufferData(34962, (FloatBuffer)localObject2, 35044);
    GL15.glBindBuffer(34962, 0);
    this.jdField_field_98_of_type_Class_1393 = new class_1393();
    this.jdField_field_98_of_type_Class_1393.f();
    this.jdField_field_98_of_type_Class_1393.c1(new float[] { 0.9F, 0.9F, 0.9F, 1.0F });
  }
  
  private int a20(Vector3f paramVector3f1, Vector3f paramVector3f2, FloatBuffer paramFloatBuffer1, FloatBuffer paramFloatBuffer2, FloatBuffer paramFloatBuffer3)
  {
    this.jdField_field_108_of_type_JavaxVecmathVector3f.sub(paramVector3f2, paramVector3f1);
    this.field_110.cross(this.jdField_field_108_of_type_JavaxVecmathVector3f, this.jdField_field_109_of_type_JavaxVecmathVector3f);
    float f1 = 0.0F;
    float f2 = 0.0F;
    for (int i = 0; i < 8; i++)
    {
      this.jdField_field_98_of_type_JavaxVecmathAxisAngle4f.set(this.jdField_field_108_of_type_JavaxVecmathVector3f, f1);
      this.jdField_field_98_of_type_JavaxVecmathMatrix3f.set(this.jdField_field_98_of_type_JavaxVecmathAxisAngle4f);
      jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[i].set(0.0F, 0.1F, 0.0F);
      this.jdField_field_98_of_type_JavaxVecmathMatrix3f.transform(jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[i]);
      jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[i].set(jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[i]);
      jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[i].set(jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[i]);
      jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[i].add(paramVector3f1);
      jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[i].add(paramVector3f2);
      jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f[i].set(0.0F, f2, 0.0F);
      f2 += 0.125F;
      f1 += 0.7853982F;
    }
    for (paramVector3f1 = 0; paramVector3f1 < 8; paramVector3f1++)
    {
      GlUtil.a24(paramFloatBuffer1, jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
      GlUtil.a24(paramFloatBuffer2, jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
      jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1].field_615 = 0.0F;
      GlUtil.a24(paramFloatBuffer3, jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
      GlUtil.a24(paramFloatBuffer1, jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
      GlUtil.a24(paramFloatBuffer2, jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
      jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)].field_615 = 0.0F;
      GlUtil.a24(paramFloatBuffer3, jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
      GlUtil.a24(paramFloatBuffer1, jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
      GlUtil.a24(paramFloatBuffer2, jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
      jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)].field_615 = 1.0F;
      GlUtil.a24(paramFloatBuffer3, jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f[((paramVector3f1 + 1) % 8)]);
      GlUtil.a24(paramFloatBuffer1, jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
      GlUtil.a24(paramFloatBuffer2, jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
      jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1].field_615 = 1.0F;
      GlUtil.a24(paramFloatBuffer3, jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f[paramVector3f1]);
    }
    return 32;
  }
  
  public final void c() {}
  
  public final void d()
  {
    this.jdField_field_98_of_type_Boolean = true;
  }
  
  public final SegmentController a21()
  {
    return this.jdField_field_98_of_type_OrgSchemaGameCommonControllerSegmentController;
  }
  
  static
  {
    jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
    jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
    jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
    jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f = new Vector3f[8];
    for (int i = 0; i < 8; i++)
    {
      jdField_field_98_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
      jdField_field_108_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
      jdField_field_106_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
      jdField_field_109_of_type_ArrayOfJavaxVecmathVector3f[i] = new Vector3f();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_285
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */