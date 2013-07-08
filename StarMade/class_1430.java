import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.io.PrintStream;
import java.io.Serializable;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public final class class_1430
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private IntBuffer jdField_field_1640_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private IntBuffer jdField_field_1641_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
  private FloatBuffer jdField_field_1640_of_type_JavaNioFloatBuffer;
  private FloatBuffer jdField_field_1641_of_type_JavaNioFloatBuffer;
  private int jdField_field_1640_of_type_Int = 0;
  
  public class_1430(int paramInt)
  {
    this.jdField_field_1640_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(paramInt << 2);
    this.jdField_field_1641_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(paramInt << 2);
  }
  
  public final void a(HashMap paramHashMap, class_1384 paramclass_1384)
  {
    this.jdField_field_1640_of_type_JavaNioFloatBuffer.rewind();
    this.jdField_field_1641_of_type_JavaNioFloatBuffer.rewind();
    paramHashMap = paramHashMap.entrySet().iterator();
    while (paramHashMap.hasNext())
    {
      Map.Entry localEntry;
      Vector localVector;
      if ((localVector = (Vector)(localEntry = (Map.Entry)paramHashMap.next()).getValue()).size() > 4) {
        for (i = 0; i < localVector.size(); i++)
        {
          int j = Math.min(localVector.size() - 1, i);
          class_1432 localclass_14321 = (class_1432)localVector.get(j);
          System.err.println("[BONE] Exception WARNING: vertex bone weight influence exceeded " + localEntry.getKey() + ": " + paramclass_1384.a6().get(localclass_14321.field_1646) + " -> " + localEntry.getKey());
        }
      }
      for (int i = 0; i < 4; i++)
      {
        int k = Math.min(localVector.size() - 1, i);
        class_1432 localclass_14322 = (class_1432)localVector.get(k);
        this.jdField_field_1640_of_type_JavaNioFloatBuffer.put((localclass_14322.jdField_field_1645_of_type_Int << 2) + i, localclass_14322.field_1646);
        this.jdField_field_1641_of_type_JavaNioFloatBuffer.put((localclass_14322.jdField_field_1645_of_type_Int << 2) + i, i < localVector.size() ? localclass_14322.jdField_field_1645_of_type_Float : 0.0F);
      }
    }
    d();
    this.jdField_field_1640_of_type_JavaNioFloatBuffer.rewind();
    this.jdField_field_1641_of_type_JavaNioFloatBuffer.rewind();
  }
  
  public final void a1()
  {
    this.jdField_field_1640_of_type_JavaNioFloatBuffer.rewind();
    this.jdField_field_1641_of_type_JavaNioFloatBuffer.rewind();
    GL15.glGenBuffers(this.jdField_field_1640_of_type_JavaNioIntBuffer);
    GL15.glBindBuffer(34962, this.jdField_field_1640_of_type_JavaNioIntBuffer.get(0));
    GL15.glBufferData(34962, this.jdField_field_1640_of_type_JavaNioFloatBuffer, 35044);
    GL15.glBindBuffer(34962, 0);
    GL15.glGenBuffers(this.jdField_field_1641_of_type_JavaNioIntBuffer);
    GL15.glBindBuffer(34962, this.jdField_field_1641_of_type_JavaNioIntBuffer.get(0));
    GL15.glBufferData(34962, this.jdField_field_1641_of_type_JavaNioFloatBuffer, 35044);
    GL15.glBindBuffer(34962, 0);
  }
  
  public final void b()
  {
    GL20.glEnableVertexAttribArray(3);
    GL20.glEnableVertexAttribArray(4);
    GL15.glBindBuffer(34962, this.jdField_field_1640_of_type_JavaNioIntBuffer.get(0));
    GL20.glVertexAttribPointer(3, 4, 5126, false, 0, 0L);
    GL15.glBindBuffer(34962, this.jdField_field_1641_of_type_JavaNioIntBuffer.get(0));
    GL20.glVertexAttribPointer(4, 4, 5126, false, 0, 0L);
  }
  
  private void d()
  {
    int i = this.jdField_field_1641_of_type_JavaNioFloatBuffer.capacity() / 4;
    this.jdField_field_1641_of_type_JavaNioFloatBuffer.rewind();
    for (int j = 0; j < i; j++)
    {
      float f1 = this.jdField_field_1641_of_type_JavaNioFloatBuffer.get();
      float f2 = this.jdField_field_1641_of_type_JavaNioFloatBuffer.get();
      float f3 = this.jdField_field_1641_of_type_JavaNioFloatBuffer.get();
      float f4;
      if ((f4 = this.jdField_field_1641_of_type_JavaNioFloatBuffer.get()) > 0.01F) {
        this.jdField_field_1640_of_type_Int = Math.max(this.jdField_field_1640_of_type_Int, 4);
      } else if (f3 > 0.01F) {
        this.jdField_field_1640_of_type_Int = Math.max(this.jdField_field_1640_of_type_Int, 3);
      } else if (f2 > 0.01F) {
        this.jdField_field_1640_of_type_Int = Math.max(this.jdField_field_1640_of_type_Int, 2);
      } else if (f1 > 0.01F) {
        this.jdField_field_1640_of_type_Int = Math.max(this.jdField_field_1640_of_type_Int, 1);
      }
      float f5;
      if ((f5 = f1 + f2 + f3 + f4) != 1.0F)
      {
        this.jdField_field_1641_of_type_JavaNioFloatBuffer.position(this.jdField_field_1641_of_type_JavaNioFloatBuffer.position() - 4);
        this.jdField_field_1641_of_type_JavaNioFloatBuffer.put(f1 / f5);
        this.jdField_field_1641_of_type_JavaNioFloatBuffer.put(f2 / f5);
        this.jdField_field_1641_of_type_JavaNioFloatBuffer.put(f3 / f5);
        this.jdField_field_1641_of_type_JavaNioFloatBuffer.put(f4 / f5);
      }
    }
    this.jdField_field_1641_of_type_JavaNioFloatBuffer.rewind();
  }
  
  public static void c()
  {
    GL15.glBindBuffer(34962, 0);
    GL20.glDisableVertexAttribArray(3);
    GL20.glDisableVertexAttribArray(4);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1430
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */