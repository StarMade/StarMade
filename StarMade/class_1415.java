import com.bulletphysics.collision.dispatch.CollisionWorld.ConvexResultCallback;
import com.bulletphysics.collision.dispatch.CollisionWorld.LocalConvexResult;
import java.io.PrintStream;

final class class_1415
  extends CollisionWorld.ConvexResultCallback
{
  class_1415(class_1419 paramclass_1419) {}
  
  public final float addSingleResult(CollisionWorld.LocalConvexResult paramLocalConvexResult, boolean paramBoolean)
  {
    class_1419.access$002(this.field_279, true);
    System.err.println("hitIndicator: " + class_1419.access$000(this.field_279) + " " + paramLocalConvexResult.hitCollisionObject);
    return 0.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1415
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */