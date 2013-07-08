package org.schema.game.client.view.cubes;

import class_371;
import class_48;
import class_836;
import class_949;
import com.bulletphysics.util.ObjectArrayList;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBMapBufferRange;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GLContext;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.forms.Mesh;

public class CubeOptOptMesh
  extends Mesh
{
  private static final int field_100 = class_949.field_1235.a4().equals("STATIC") ? 35044 : class_949.field_1235.a4().equals("DYNAMIC") ? 35048 : 35040;
  private IntBuffer jdField_field_93_of_type_JavaNioIntBuffer;
  private static String jdField_field_90_of_type_JavaLangString;
  public static int field_89;
  public static class_836 field_89;
  public static int field_90;
  public static int field_92;
  private long jdField_field_92_of_type_Long;
  public int field_93;
  private static int field_216;
  public static int field_94 = 0;
  private static ByteBuffer jdField_field_89_of_type_JavaNioByteBuffer;
  private static boolean jdField_field_89_of_type_Boolean = true;
  private static boolean jdField_field_90_of_type_Boolean;
  private static boolean jdField_field_92_of_type_Boolean;
  public static long field_89;
  public long field_90;
  private boolean jdField_field_93_of_type_Boolean;
  private int field_275;
  private int field_323;
  private int field_324;
  private static int field_325 = 42;
  
  public static void d()
  {
    jdField_field_89_of_type_Class_836 = new class_836();
    jdField_field_90_of_type_JavaLangString = "CubeMesh(4096)";
    new Vector3f();
  }
  
  public CubeOptOptMesh()
  {
    new ObjectArrayList();
    new class_48();
    this.field_275 = (field_216++);
    this.field_89 = jdField_field_90_of_type_JavaLangString;
    this.field_97 = 8192;
    this.field_96 = 3;
  }
  
  public final void a2()
  {
    super.a2();
    this.jdField_field_93_of_type_JavaNioIntBuffer.rewind();
    GL15.glDeleteBuffers(this.jdField_field_93_of_type_JavaNioIntBuffer);
    jdField_field_89_of_type_Int -= 1;
    this.jdField_field_93_of_type_Boolean = false;
  }
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new float[8];
    for (int i = 0; i < 8; i++)
    {
      paramArrayOfString[i] = (i / 8.0F);
      System.err.println("FF: " + paramArrayOfString[i]);
    }
  }
  
  public final void a181(FloatBuffer paramFloatBuffer)
  {
    if (!jdField_field_92_of_type_Boolean)
    {
      jdField_field_90_of_type_Boolean = GLContext.getCapabilities().GL_ARB_map_buffer_range;
      System.err.println("USE BUFFER RANGE: " + jdField_field_90_of_type_Boolean);
      jdField_field_92_of_type_Boolean = true;
    }
    int i = 0;
    paramFloatBuffer.flip();
    boolean bool1;
    if (!this.jdField_field_93_of_type_Boolean)
    {
      FloatBuffer localFloatBuffer1 = paramFloatBuffer;
      CubeOptOptMesh localCubeOptOptMesh = this;
      this.field_96 = 3;
      localCubeOptOptMesh.jdField_field_93_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
      jdField_field_89_of_type_Int += 1;
      localCubeOptOptMesh.jdField_field_93_of_type_JavaNioIntBuffer.rewind();
      GL15.glGenBuffers(localCubeOptOptMesh.jdField_field_93_of_type_JavaNioIntBuffer);
      GL15.glBindBuffer(34962, localCubeOptOptMesh.b12());
      GL15.glBufferData(34962, localFloatBuffer1.limit() << 2, field_100);
      class_371.field_153 += (localFloatBuffer1.limit() << 2);
      class_371.field_154 += (49152 * CubeMeshBufferContainer.field_1665 << 2);
      localCubeOptOptMesh.field_323 = localFloatBuffer1.limit();
      GL15.glBindBuffer(34962, 0);
      localCubeOptOptMesh.jdField_field_93_of_type_Boolean = true;
      bool1 = true;
    }
    if (paramFloatBuffer.limit() != 0)
    {
      long l1 = 0L;
      long l2 = 0L;
      long l3 = 0L;
      GL15.glBindBuffer(34962, b12());
      if (this.field_323 != paramFloatBuffer.limit())
      {
        class_371.field_153 = (class_371.field_153 = class_371.field_153 - (this.field_323 << 2)) + (paramFloatBuffer.limit() << 2);
        GL15.glBufferData(34962, paramFloatBuffer.limit() << 2, field_100);
        this.field_323 = paramFloatBuffer.limit();
      }
      long l5 = System.nanoTime();
      if (jdField_field_89_of_type_Boolean)
      {
        if (jdField_field_90_of_type_Boolean) {
          jdField_field_89_of_type_JavaNioByteBuffer = ARBMapBufferRange.glMapBufferRange(34962, 0L, paramFloatBuffer.limit() << 2, field_325, jdField_field_89_of_type_JavaNioByteBuffer == null ? null : jdField_field_89_of_type_JavaNioByteBuffer);
        } else {
          jdField_field_89_of_type_JavaNioByteBuffer = GL15.glMapBuffer(34962, 35001, jdField_field_89_of_type_JavaNioByteBuffer == null ? null : jdField_field_89_of_type_JavaNioByteBuffer);
        }
        if ((jdField_field_89_of_type_JavaNioByteBuffer == null) && (jdField_field_89_of_type_Boolean))
        {
          jdField_field_89_of_type_Boolean = false;
          System.err.println("[Exception]WARNING: MAPPED BUFFER HAS BEEN TURNED OFF " + GlUtil.a7());
        }
      }
      long l6 = System.nanoTime() - l5;
      boolean bool2 = false;
      long l4;
      if (jdField_field_89_of_type_Boolean)
      {
        long l7 = System.nanoTime();
        FloatBuffer localFloatBuffer2 = jdField_field_89_of_type_JavaNioByteBuffer.order(ByteOrder.nativeOrder()).asFloatBuffer();
        l2 = (System.nanoTime() - l7) / 1000000L;
        l7 = System.nanoTime();
        localFloatBuffer2.put(paramFloatBuffer);
        l1 = (System.nanoTime() - l7) / 1000000L;
        localFloatBuffer2.flip();
        l7 = System.nanoTime();
        bool2 = GL15.glUnmapBuffer(34962);
        l4 = (System.nanoTime() - l7) / 1000000L;
      }
      else
      {
        GL15.glBufferSubData(34962, 0L, paramFloatBuffer);
      }
      if ((CubeOptOptMesh.jdField_field_89_of_type_Long = (CubeOptOptMesh.jdField_field_89_of_type_Long = System.nanoTime() - l5) / 1000000L) > 10L) {
        System.err.println("[CUBE] WARNING: context switch time: " + jdField_field_89_of_type_Long + " ms : " + l6 / 1000000L + "ms: O " + l2 + "; P " + l1 + "; U " + l4 + "::; map " + jdField_field_89_of_type_Boolean + "; range " + jdField_field_90_of_type_Boolean + "; init " + bool1 + "  unmap " + bool2);
      }
    }
    this.field_324 = (paramFloatBuffer.limit() / CubeMeshBufferContainer.field_1665);
  }
  
  public final void b()
  {
    this.jdField_field_90_of_type_Long = 0L;
    this.jdField_field_93_of_type_Int = 0;
    jdField_field_89_of_type_Long = 0L;
    this.jdField_field_92_of_type_Long = System.nanoTime();
    CubeOptOptMesh localCubeOptOptMesh1 = this;
    if (this.jdField_field_93_of_type_Boolean)
    {
      jdField_field_92_of_type_Int += 1;
      CubeOptOptMesh localCubeOptOptMesh2 = localCubeOptOptMesh1;
      long l = System.nanoTime();
      GL15.glBindBuffer(34962, localCubeOptOptMesh2.b12());
      CubeOptOptMesh localCubeOptOptMesh3 = localCubeOptOptMesh2;
      GL11.glVertexPointer(CubeMeshBufferContainer.field_1665, 5126, 0, 0L);
      GL11.glDrawArrays(7, 0, localCubeOptOptMesh3.field_324);
      localCubeOptOptMesh2.jdField_field_90_of_type_Long += System.nanoTime() - l;
      localCubeOptOptMesh1.jdField_field_93_of_type_Int = ((int)(System.nanoTime() - localCubeOptOptMesh1.jdField_field_92_of_type_Long));
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return this.field_275 == ((CubeOptOptMesh)paramObject).field_275;
  }
  
  private int b12()
  {
    return this.jdField_field_93_of_type_JavaNioIntBuffer.get(0);
  }
  
  public int hashCode()
  {
    return this.field_275;
  }
  
  public String toString()
  {
    return super.toString() + ":" + hashCode();
  }
  
  static
  {
    BufferUtils.createFloatBuffer(1048576 * CubeMeshBufferContainer.field_1665);
    BufferUtils.createFloatBuffer(4);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.client.view.cubes.CubeOptOptMesh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */