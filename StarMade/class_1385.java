import java.nio.IntBuffer;
import javax.vecmath.Color4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public abstract class class_1385
{
  private IntBuffer jdField_field_1582_of_type_JavaNioIntBuffer;
  int jdField_field_1582_of_type_Int;
  private int[] jdField_field_1582_of_type_ArrayOfInt;
  
  public class_1385()
  {
    new Color4f(0.0F, 0.0F, 0.0F, 0.0F);
    this.jdField_field_1582_of_type_ArrayOfInt = new int[0];
    this.jdField_field_1582_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);
    GL11.glGenTextures(this.jdField_field_1582_of_type_JavaNioIntBuffer);
    this.jdField_field_1582_of_type_Int = this.jdField_field_1582_of_type_JavaNioIntBuffer.get(0);
  }
  
  protected void finalize()
  {
    super.finalize();
  }
  
  public final int a1()
  {
    return this.jdField_field_1582_of_type_Int;
  }
  
  protected final void a2(int paramInt1, int paramInt2)
  {
    if (paramInt1 >= this.jdField_field_1582_of_type_ArrayOfInt.length)
    {
      int[] arrayOfInt = this.jdField_field_1582_of_type_ArrayOfInt;
      this.jdField_field_1582_of_type_ArrayOfInt = new int[paramInt1 + 1];
      System.arraycopy(arrayOfInt, 0, this.jdField_field_1582_of_type_ArrayOfInt, 0, arrayOfInt.length);
    }
    this.jdField_field_1582_of_type_ArrayOfInt[paramInt1] = paramInt2;
  }
  
  static
  {
    BufferUtils.createFloatBuffer(16);
    new int[] { 6409, 6410, 6407 }[3] = 6408;
    new int[] { 34026, 34027, 34029 }[3] = 34030;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1385
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */