import com.bulletphysics.collision.shapes.TriangleCallback;
import javax.vecmath.Vector3f;
import org.lwjgl.opengl.GL11;

final class class_931
  extends TriangleCallback
{
  public boolean field_217 = false;
  
  public final void processTriangle(Vector3f[] paramArrayOfVector3f, int paramInt1, int paramInt2)
  {
    GL11.glDisable(2896);
    GL11.glDisable(3553);
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
    if (this.field_217)
    {
      GL11.glBegin(1);
      GL11.glColor3f(1.0F, 0.0F, 0.0F);
      GL11.glVertex3f(paramArrayOfVector3f[0].field_615, paramArrayOfVector3f[0].field_616, paramArrayOfVector3f[0].field_617);
      GL11.glVertex3f(paramArrayOfVector3f[1].field_615, paramArrayOfVector3f[1].field_616, paramArrayOfVector3f[1].field_617);
      GL11.glColor3f(0.0F, 1.0F, 0.0F);
      GL11.glVertex3f(paramArrayOfVector3f[2].field_615, paramArrayOfVector3f[2].field_616, paramArrayOfVector3f[2].field_617);
      GL11.glVertex3f(paramArrayOfVector3f[1].field_615, paramArrayOfVector3f[1].field_616, paramArrayOfVector3f[1].field_617);
      GL11.glColor3f(0.0F, 0.0F, 1.0F);
      GL11.glVertex3f(paramArrayOfVector3f[2].field_615, paramArrayOfVector3f[2].field_616, paramArrayOfVector3f[2].field_617);
      GL11.glVertex3f(paramArrayOfVector3f[0].field_615, paramArrayOfVector3f[0].field_616, paramArrayOfVector3f[0].field_617);
      GL11.glEnd();
    }
    else
    {
      GL11.glBegin(4);
      GL11.glColor3f(1.0F, 0.0F, 0.0F);
      GL11.glVertex3f(paramArrayOfVector3f[0].field_615, paramArrayOfVector3f[0].field_616, paramArrayOfVector3f[0].field_617);
      GL11.glColor3f(0.0F, 1.0F, 0.0F);
      GL11.glVertex3f(paramArrayOfVector3f[1].field_615, paramArrayOfVector3f[1].field_616, paramArrayOfVector3f[1].field_617);
      GL11.glColor3f(0.0F, 0.0F, 1.0F);
      GL11.glVertex3f(paramArrayOfVector3f[2].field_615, paramArrayOfVector3f[2].field_616, paramArrayOfVector3f[2].field_617);
      GL11.glEnd();
    }
    GL11.glEnable(2896);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_931
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */