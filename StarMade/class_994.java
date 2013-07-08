import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.schema.schine.graphicsengine.core.GlUtil;

public final class class_994
  extends class_1426
{
  private Vector4f jdField_field_226_of_type_JavaxVecmathVector4f = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
  private Transform jdField_field_225_of_type_ComBulletphysicsLinearmathTransform;
  private Vector3f jdField_field_225_of_type_JavaxVecmathVector3f;
  private Vector3f jdField_field_226_of_type_JavaxVecmathVector3f;
  private static Vector3f[][] jdField_field_225_of_type_Array2dOfJavaxVecmathVector3f = ;
  
  public class_994(Vector3f paramVector3f1, Vector3f paramVector3f2, Transform paramTransform, float paramFloat1, float paramFloat2)
  {
    this.jdField_field_226_of_type_JavaxVecmathVector4f.set(1.0F, paramFloat1, paramFloat2, 1.0F);
    this.jdField_field_225_of_type_ComBulletphysicsLinearmathTransform = paramTransform;
    this.jdField_field_225_of_type_JavaxVecmathVector3f = paramVector3f1;
    this.jdField_field_226_of_type_JavaxVecmathVector3f = paramVector3f2;
  }
  
  public final void a()
  {
    Object localObject1 = class_1353.a1(this.jdField_field_225_of_type_JavaxVecmathVector3f, this.jdField_field_226_of_type_JavaxVecmathVector3f, jdField_field_225_of_type_Array2dOfJavaxVecmathVector3f);
    Object localObject2 = localObject1;
    Transform localTransform = this.jdField_field_225_of_type_ComBulletphysicsLinearmathTransform;
    localObject1 = this.jdField_field_226_of_type_JavaxVecmathVector4f;
    GL11.glPolygonMode(1032, 6913);
    GL11.glDisable(2896);
    GL11.glDisable(2884);
    GL11.glEnable(2903);
    GL11.glDisable(32879);
    GL11.glDisable(3553);
    GL11.glDisable(3552);
    GL11.glEnable(3042);
    GlUtil.a38(((Vector4f)localObject1).field_596, ((Vector4f)localObject1).field_597, ((Vector4f)localObject1).field_598, ((Vector4f)localObject1).field_599);
    GlUtil.d1();
    GlUtil.b3(localTransform);
    GL11.glBegin(7);
    for (int i = 0; i < localObject2.length; i++) {
      for (int j = 0; j < localObject2[i].length; j++) {
        GL11.glVertex3f(localObject2[i][j].field_615, localObject2[i][j].field_616, localObject2[i][j].field_617);
      }
    }
    GL11.glEnd();
    GlUtil.c2();
    GL11.glEnable(2929);
    GL11.glEnable(2896);
    GL11.glDisable(2903);
    GL11.glEnable(2884);
    GL11.glDisable(3042);
    GL11.glPolygonMode(1032, 6914);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_994
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */