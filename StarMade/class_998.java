import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.FloatBuffer;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_998
  extends class_1392
  implements KeyListener
{
  private int jdField_field_89_of_type_Int;
  private static int jdField_field_90_of_type_Int;
  private Vector4f jdField_field_89_of_type_JavaxVecmathVector4f;
  private Vector4f jdField_field_90_of_type_JavaxVecmathVector4f;
  private Vector4f field_92;
  private static float[] jdField_field_89_of_type_ArrayOfFloat = new float[4];
  private static FloatBuffer jdField_field_90_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(4);
  
  public class_998()
  {
    new float[] { 0.0F, 1.0F, 0.0F }[3] = 0.0F;
    if (jdField_field_90_of_type_Int > 7) {
      throw new IllegalArgumentException("too many Lights in scene");
    }
    this.jdField_field_89_of_type_Int = (jdField_field_90_of_type_Int++);
    this.jdField_field_89_of_type_JavaxVecmathVector4f = new Vector4f(0.2F, 0.2F, 0.2F, 1.0F);
    this.jdField_field_90_of_type_JavaxVecmathVector4f = new Vector4f(0.6F, 0.6F, 0.6F, 1.0F);
    this.field_92 = new Vector4f(0.9F, 0.9F, 0.9F, 1.0F);
    a83().set(0.0F, 0.0F, 0.0F);
    new float[1][0] = 32.0F;
  }
  
  public final void b()
  {
    GlUtil.d1();
    class_998 localclass_998 = this;
    this.jdField_field_89_of_type_JavaxVecmathVector4f.get(jdField_field_89_of_type_ArrayOfFloat);
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_90_of_type_JavaNioFloatBuffer.put(jdField_field_89_of_type_ArrayOfFloat);
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    GL11.glLight(localclass_998.a57(), 4608, jdField_field_90_of_type_JavaNioFloatBuffer);
    localclass_998.jdField_field_90_of_type_JavaxVecmathVector4f.get(jdField_field_89_of_type_ArrayOfFloat);
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_90_of_type_JavaNioFloatBuffer.put(jdField_field_89_of_type_ArrayOfFloat);
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    GL11.glLight(localclass_998.a57(), 4609, jdField_field_90_of_type_JavaNioFloatBuffer);
    localclass_998.a83().get(jdField_field_89_of_type_ArrayOfFloat);
    jdField_field_89_of_type_ArrayOfFloat[3] = 1.0F;
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_90_of_type_JavaNioFloatBuffer.put(jdField_field_89_of_type_ArrayOfFloat);
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    GL11.glLight(localclass_998.a57(), 4611, jdField_field_90_of_type_JavaNioFloatBuffer);
    localclass_998.field_92.get(jdField_field_89_of_type_ArrayOfFloat);
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    jdField_field_90_of_type_JavaNioFloatBuffer.put(jdField_field_89_of_type_ArrayOfFloat);
    jdField_field_90_of_type_JavaNioFloatBuffer.rewind();
    GL11.glLight(localclass_998.a57(), 4610, jdField_field_90_of_type_JavaNioFloatBuffer);
    GL11.glEnable(2896);
    GL11.glEnable(localclass_998.a57());
    GlUtil.c2();
  }
  
  public final Vector4f a63()
  {
    return this.jdField_field_89_of_type_JavaxVecmathVector4f;
  }
  
  public final Vector4f c13()
  {
    return this.jdField_field_90_of_type_JavaxVecmathVector4f;
  }
  
  private int a57()
  {
    switch (this.jdField_field_89_of_type_Int)
    {
    case 0: 
      return 16384;
    case 1: 
      return 16385;
    case 2: 
      return 16386;
    case 3: 
      return 16387;
    case 4: 
      return 16388;
    case 5: 
      return 16389;
    case 6: 
      return 16390;
    case 7: 
      return 16391;
    }
    return 0;
  }
  
  public final Vector4f d8()
  {
    return this.field_92;
  }
  
  public final void keyPressed(KeyEvent paramKeyEvent)
  {
    float f = 100.0F;
    if (paramKeyEvent.isShiftDown()) {
      f = 1000.0F;
    }
    if (paramKeyEvent.isControlDown()) {
      f = 0.5F;
    }
    switch (paramKeyEvent.getKeyCode())
    {
    case 38: 
      a83().field_617 += f;
      return;
    case 40: 
      a83().field_617 -= f;
      return;
    case 37: 
      a83().field_615 += f;
      return;
    case 39: 
      a83().field_615 -= f;
      return;
    case 33: 
      a83().field_616 += f;
      return;
    case 34: 
      a83().field_616 -= f;
    }
  }
  
  public final void keyReleased(KeyEvent paramKeyEvent) {}
  
  public final void keyTyped(KeyEvent paramKeyEvent) {}
  
  public final void c() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_998
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */