import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.opengl.GL11;
import org.schema.game.common.controller.SegmentController;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.client.ClientState;

public final class class_889
  extends class_1363
{
  private class_371 jdField_field_89_of_type_Class_371;
  private float jdField_field_89_of_type_Float;
  private Vector4f jdField_field_89_of_type_JavaxVecmathVector4f = new Vector4f();
  private Transform field_90 = new Transform();
  private Vector3f field_92 = new Vector3f();
  
  public class_889(ClientState paramClientState, float paramFloat)
  {
    super(paramClientState);
    this.jdField_field_89_of_type_Class_371 = ((class_371)paramClientState);
    this.jdField_field_89_of_type_Float = paramFloat;
  }
  
  public final void a2() {}
  
  public final void b()
  {
    class_797 localclass_7971;
    if ((localclass_7971 = this.jdField_field_89_of_type_Class_371.a6()) != null)
    {
      if ((localclass_7971 instanceof SegmentController))
      {
        this.field_90.set(((SegmentController)localclass_7971).getWorldTransformInverse());
      }
      else if ((localclass_7971 instanceof class_750))
      {
        this.field_90.set(class_969.a1().getWorldTransform());
        this.field_90.inverse();
      }
      else
      {
        this.field_90.set(localclass_7971.getWorldTransform());
        this.field_90.inverse();
      }
      localclass_7971 = ((class_371)a24()).a14().field_4.field_4.field_4.a43();
      GL11.glPointSize(2.0F);
      GL11.glBegin(0);
      Iterator localIterator = this.jdField_field_89_of_type_Class_371.a7().values().iterator();
      while (localIterator.hasNext())
      {
        class_797 localclass_7972 = (class_797)localIterator.next();
        this.field_92.set(localclass_7972.getWorldTransformClient().origin);
        this.field_90.transform(this.field_92);
        this.field_92.scale(0.07F);
        if (this.field_92.length() < this.jdField_field_89_of_type_Float / 2.0F)
        {
          class_883.a128(localclass_7972, this.jdField_field_89_of_type_JavaxVecmathVector4f, localclass_7971 == localclass_7972, this.jdField_field_89_of_type_Class_371);
          this.field_92.field_615 = (this.jdField_field_89_of_type_Float / 2.0F - this.field_92.field_615);
          this.field_92.field_617 = (this.jdField_field_89_of_type_Float / 2.0F - this.field_92.field_617);
          GlUtil.a38(this.jdField_field_89_of_type_JavaxVecmathVector4f.field_596, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_597, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_598, this.jdField_field_89_of_type_JavaxVecmathVector4f.field_599);
          GL11.glVertex2f(this.field_92.field_615, this.field_92.field_617);
        }
      }
      GlUtil.a38(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnd();
    }
  }
  
  public final void c() {}
  
  public final float a3()
  {
    return 0.0F;
  }
  
  public final float b1()
  {
    return 0.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_889
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */