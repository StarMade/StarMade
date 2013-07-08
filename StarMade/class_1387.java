import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.vecmath.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.schine.graphicsengine.camera.Camera;

public class class_1387
{
  FloatBuffer jdField_field_1583_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(3);
  public FloatBuffer field_1584;
  public FloatBuffer field_1585;
  public IntBuffer field_1583;
  Vector3f jdField_field_1583_of_type_JavaxVecmathVector3f = new Vector3f();
  Vector3f field_1584;
  protected Vector3f field_1585;
  protected Vector3f field_1586 = new Vector3f();
  
  public class_1387()
  {
    this.jdField_field_1584_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
    this.jdField_field_1585_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
    this.jdField_field_1583_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(16);
    this.jdField_field_1584_of_type_JavaxVecmathVector3f = new Vector3f();
    this.jdField_field_1585_of_type_JavaxVecmathVector3f = new Vector3f();
    BufferUtils.createIntBuffer(16);
    BufferUtils.createFloatBuffer(16);
    BufferUtils.createFloatBuffer(16);
    BufferUtils.createFloatBuffer(3);
  }
  
  public final Vector3f a2(Vector3f paramVector3f1, Vector3f paramVector3f2, boolean paramBoolean)
  {
    return a3(paramVector3f1, paramVector3f2, paramBoolean, class_969.a1());
  }
  
  public final Vector3f a3(Vector3f paramVector3f1, Vector3f paramVector3f2, boolean paramBoolean, Camera paramCamera)
  {
    return a(paramVector3f1, paramVector3f2, paramCamera.a83(), paramCamera.c10(), paramBoolean);
  }
  
  public Vector3f a(Vector3f paramVector3f1, Vector3f paramVector3f2, Vector3f paramVector3f3, Vector3f paramVector3f4, boolean paramBoolean)
  {
    this.jdField_field_1583_of_type_JavaxVecmathVector3f.set(paramVector3f1);
    this.jdField_field_1584_of_type_JavaxVecmathVector3f.sub(paramVector3f1, paramVector3f3);
    this.jdField_field_1584_of_type_JavaxVecmathVector3f.length();
    this.jdField_field_1584_of_type_JavaxVecmathVector3f.normalize();
    this.jdField_field_1583_of_type_JavaNioFloatBuffer.rewind();
    GLU.gluProject(this.jdField_field_1583_of_type_JavaxVecmathVector3f.field_615, this.jdField_field_1583_of_type_JavaxVecmathVector3f.field_616, this.jdField_field_1583_of_type_JavaxVecmathVector3f.field_617, this.jdField_field_1584_of_type_JavaNioFloatBuffer, this.jdField_field_1585_of_type_JavaNioFloatBuffer, this.jdField_field_1583_of_type_JavaNioIntBuffer, this.jdField_field_1583_of_type_JavaNioFloatBuffer);
    this.jdField_field_1583_of_type_JavaxVecmathVector3f.set(this.jdField_field_1583_of_type_JavaNioFloatBuffer.get(0), this.jdField_field_1583_of_type_JavaNioFloatBuffer.get(1), this.jdField_field_1583_of_type_JavaNioFloatBuffer.get(2));
    paramVector3f1 = paramVector3f4.dot(this.jdField_field_1584_of_type_JavaxVecmathVector3f);
    a1(this.field_1586);
    paramVector3f2.set(this.jdField_field_1583_of_type_JavaxVecmathVector3f.field_615, Display.getDisplayMode().getHeight() - this.jdField_field_1583_of_type_JavaxVecmathVector3f.field_616, 0.0F);
    if (paramVector3f1 < 0.0F)
    {
      (paramVector3f1 = new Vector3f()).sub(this.field_1586, paramVector3f2);
      if (paramVector3f1.length() == 0.0F) {
        paramVector3f1.set(this.jdField_field_1585_of_type_JavaxVecmathVector3f);
      }
      paramVector3f1.normalize();
      this.jdField_field_1585_of_type_JavaxVecmathVector3f.set(paramVector3f1);
      if (paramBoolean) {
        paramVector3f1.scale(10000000.0F);
      }
      paramVector3f2.add(paramVector3f1);
    }
    return paramVector3f2;
  }
  
  public Vector3f a1(Vector3f paramVector3f)
  {
    paramVector3f.set(Display.getDisplayMode().getWidth() / 2, Display.getDisplayMode().getHeight() / 2, 0.0F);
    return paramVector3f;
  }
  
  public final void a4()
  {
    this.jdField_field_1584_of_type_JavaNioFloatBuffer.rewind();
    this.jdField_field_1585_of_type_JavaNioFloatBuffer.rewind();
    this.jdField_field_1583_of_type_JavaNioIntBuffer.rewind();
    class_969.field_1259.store(this.jdField_field_1584_of_type_JavaNioFloatBuffer);
    class_969.field_1260.store(this.jdField_field_1585_of_type_JavaNioFloatBuffer);
    this.jdField_field_1583_of_type_JavaNioIntBuffer.put(0);
    this.jdField_field_1583_of_type_JavaNioIntBuffer.put(0);
    this.jdField_field_1583_of_type_JavaNioIntBuffer.put(Display.getDisplayMode().getWidth() - 5);
    this.jdField_field_1583_of_type_JavaNioIntBuffer.put(Display.getDisplayMode().getHeight() - 5);
    this.jdField_field_1583_of_type_JavaNioIntBuffer.rewind();
    this.jdField_field_1584_of_type_JavaNioFloatBuffer.rewind();
    this.jdField_field_1585_of_type_JavaNioFloatBuffer.rewind();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1387
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */