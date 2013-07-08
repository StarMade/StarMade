import java.awt.Dimension;
import java.awt.Point;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_980
  extends class_1392
{
  private static class_1395 jdField_field_89_of_type_Class_1395;
  private static final Vector2f jdField_field_89_of_type_JavaxVecmathVector2f = new Vector2f(3.0F, 5.0F);
  private static Dimension jdField_field_89_of_type_JavaAwtDimension = new Dimension(16, 18);
  private static int jdField_field_89_of_type_Int = 0;
  private ArrayList jdField_field_89_of_type_JavaUtilArrayList = new ArrayList();
  private boolean jdField_field_89_of_type_Boolean = false;
  private float jdField_field_89_of_type_Float;
  private float field_90;
  public class_34 field_89;
  
  public class_980(List paramList)
  {
    this.jdField_field_89_of_type_Class_34 = new class_34();
    this.jdField_field_89_of_type_JavaUtilArrayList.addAll(paramList);
    this.field_90 = 3.0F;
    this.jdField_field_89_of_type_Float = 4.0F;
  }
  
  public final void b()
  {
    float f3 = a83().field_617;
    float f2 = a83().field_616;
    float f1 = a83().field_615;
    float f4 = f3;
    f3 = f2;
    f2 = f1;
    class_978 localclass_978 = class_978.field_1268;
    Object localObject2 = this.jdField_field_89_of_type_JavaUtilArrayList;
    Object localObject1 = this;
    float f6 = ((class_980)localObject1).jdField_field_89_of_type_Float;
    float f5 = ((class_980)localObject1).field_90;
    f4 = f4;
    f3 = f3;
    f2 = f2;
    localclass_978 = localclass_978;
    localObject1 = localObject2;
    localObject2 = this;
    if ((jdField_field_89_of_type_Int == 0) || (jdField_field_89_of_type_Class_1395 == null)) {
      ((class_980)localObject2).c();
    }
    jdField_field_89_of_type_Class_1395.a();
    GL11.glDisable(2929);
    GL11.glEnable(3553);
    GL11.glBlendFunc(1, 1);
    GL11.glEnable(3042);
    GL11.glListBase(jdField_field_89_of_type_Int);
    GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glEnable(2903);
    int i = 0;
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (String)((Iterator)localObject1).next();
      switch (class_982.field_1271[localclass_978.ordinal()])
      {
      case 1: 
        f2 -= f5 * ((String)localObject3).length();
        f3 -= f6;
        break;
      case 2: 
        f2 -= f5 * ((String)localObject3).length();
        break;
      case 3: 
        f2 -= f5 * ((String)localObject3).length();
        break;
      case 4: 
        f2 -= f5 * ((String)localObject3).length() / 2.0F;
        f3 -= f6;
        break;
      case 5: 
        f2 -= f5 * ((String)localObject3).length() / 2.0F;
        break;
      case 6: 
        f2 -= f5 * ((String)localObject3).length() / 2.0F;
        break;
      case 7: 
        f3 -= f6;
        break;
      case 8: 
        f3 -= f6 / 2.0F;
      }
      GlUtil.d1();
      GL11.glTranslatef(f2, f3, f4);
      if (((class_980)localObject2).jdField_field_89_of_type_Boolean)
      {
        class_984.jdField_field_89_of_type_JavaNioFloatBuffer.rewind();
        class_969.field_1259.store(class_984.jdField_field_89_of_type_JavaNioFloatBuffer);
        for (int j = 0; j < 3; j++) {
          for (int k = 0; k < 3; k++) {
            if (j == k) {
              class_984.jdField_field_89_of_type_JavaNioFloatBuffer.put((j << 2) + k, 1.0F);
            } else {
              class_984.jdField_field_89_of_type_JavaNioFloatBuffer.put((j << 2) + k, 0.0F);
            }
          }
        }
        class_984.jdField_field_89_of_type_JavaNioFloatBuffer.rewind();
        GlUtil.a10(class_984.jdField_field_89_of_type_JavaNioFloatBuffer);
      }
      GlUtil.b5(((class_980)localObject2).jdField_field_89_of_type_Class_34.field_450, ((class_980)localObject2).jdField_field_89_of_type_Class_34.field_451 + i, ((class_980)localObject2).jdField_field_89_of_type_Class_34.field_452);
      GL11.glScalef(((class_984)localObject2).jdField_field_89_of_type_JavaxVecmathVector3f.field_615 * f5, ((class_984)localObject2).jdField_field_89_of_type_JavaxVecmathVector3f.field_616 * f6, ((class_984)localObject2).jdField_field_89_of_type_JavaxVecmathVector3f.field_617);
      ByteBuffer localByteBuffer;
      (localByteBuffer = GlUtil.a5((localObject3 = ((String)localObject3).getBytes()).length, 0)).put((byte[])localObject3);
      GL11.glCallLists(localByteBuffer);
      i = (int)(i + (f6 + 4.0F * ((class_984)localObject2).jdField_field_89_of_type_JavaxVecmathVector3f.field_616));
      GlUtil.c2();
    }
    GL11.glDisable(3042);
    GL11.glDisable(3553);
    GL11.glEnable(2929);
  }
  
  public final float a3()
  {
    return this.jdField_field_89_of_type_Float;
  }
  
  public final void c()
  {
    if (jdField_field_89_of_type_Int == 0)
    {
      jdField_field_89_of_type_Class_1395 = class_969.a2().a5("fontset-standard").a153().a1();
      jdField_field_89_of_type_Int = GL11.glGenLists(256);
      int i = jdField_field_89_of_type_JavaAwtDimension.width;
      int j = jdField_field_89_of_type_JavaAwtDimension.height;
      for (int k = 32; k < 256; k = (char)(k + 1))
      {
        GL11.glNewList(jdField_field_89_of_type_Int + k, 4864);
        int m = (k - 32) / 16;
        int n = (k - 32) % 16;
        Point localPoint = new Point(n * i, (m + 1) * j);
        float f1 = jdField_field_89_of_type_Class_1395.field_1590;
        float f2 = jdField_field_89_of_type_Class_1395.field_1591;
        GL11.glBegin(7);
        GL11.glTexCoord2f(localPoint.x / f2, localPoint.y / f1);
        GL11.glVertex2f(0.0F, 0.0F);
        GL11.glTexCoord2f((localPoint.x + i) / f2, localPoint.y / f1);
        GL11.glVertex2f(jdField_field_89_of_type_JavaxVecmathVector2f.field_577, 0.0F);
        GL11.glTexCoord2f((localPoint.x + i) / f2, (localPoint.y - j + 1) / f1);
        GL11.glVertex2f(jdField_field_89_of_type_JavaxVecmathVector2f.field_577, jdField_field_89_of_type_JavaxVecmathVector2f.field_578);
        GL11.glTexCoord2f(localPoint.x / f2, (localPoint.y - j + 1) / f1);
        GL11.glVertex2f(0.0F, jdField_field_89_of_type_JavaxVecmathVector2f.field_578);
        GL11.glEnd();
        GL11.glTranslatef(jdField_field_89_of_type_JavaxVecmathVector2f.field_577, 0.0F, 0.0F);
        GL11.glEndList();
      }
    }
  }
  
  public final void d()
  {
    this.jdField_field_89_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_980
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */