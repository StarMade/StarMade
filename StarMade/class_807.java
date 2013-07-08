import com.bulletphysics.linearmath.Transform;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.network.objects.NetworkSegmentController;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteVector3i;

public final class class_807
  implements Runnable
{
  private ArrayList jdField_field_1063_of_type_JavaUtilArrayList;
  private Transform jdField_field_1063_of_type_ComBulletphysicsLinearmathTransform;
  private float jdField_field_1063_of_type_Float;
  private float field_1064;
  private class_809 jdField_field_1063_of_type_Class_809;
  private EditableSendableSegmentController jdField_field_1063_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController;
  private boolean jdField_field_1063_of_type_Boolean;
  
  public class_807(EditableSendableSegmentController paramEditableSendableSegmentController, Transform paramTransform, float paramFloat1, float paramFloat2, class_809 paramclass_809)
  {
    this.jdField_field_1063_of_type_ComBulletphysicsLinearmathTransform = paramTransform;
    this.jdField_field_1063_of_type_Float = paramFloat1;
    this.field_1064 = paramFloat2;
    this.jdField_field_1063_of_type_Class_809 = paramclass_809;
    this.jdField_field_1063_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController = paramEditableSendableSegmentController;
  }
  
  public final void run()
  {
    new HashSet();
    long l1 = System.currentTimeMillis();
    try
    {
      this.jdField_field_1063_of_type_JavaUtilArrayList = this.jdField_field_1063_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getRadius(this.jdField_field_1063_of_type_ComBulletphysicsLinearmathTransform.origin, this.jdField_field_1063_of_type_Float);
      long l2 = System.currentTimeMillis() - l1;
      Vector3f localVector3f = new Vector3f(this.jdField_field_1063_of_type_ComBulletphysicsLinearmathTransform.origin);
      this.jdField_field_1063_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getWorldTransformInverse().transform(localVector3f);
      localVector3f.field_615 += 8.0F;
      localVector3f.field_616 += 8.0F;
      localVector3f.field_617 += 8.0F;
      Iterator localIterator = this.jdField_field_1063_of_type_JavaUtilArrayList.iterator();
      while (localIterator.hasNext()) {
        ((Segment)localIterator.next()).a23(this.field_1064, localVector3f, this.jdField_field_1063_of_type_Float);
      }
      if (this.jdField_field_1063_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getSegmentBuffer().a9(class_747.field_136, true).a5() <= 0) {
        this.jdField_field_1063_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.setFlagCoreDestroyedByExplosion(this.jdField_field_1063_of_type_Class_809 != null ? this.jdField_field_1063_of_type_Class_809 : new Object());
      }
      this.jdField_field_1063_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.setFlagCharacterExitCheckByExplosion(true);
      long l3 = System.currentTimeMillis() - l1 - l2;
      long l4 = System.currentTimeMillis() - l1 - l2 - l3;
      System.err.println("[EXPLOSION] " + this + " SERVER EXPLOSION HANDLE: TOTAL: " + (System.currentTimeMillis() - l1) + " HITS:" + this.jdField_field_1063_of_type_JavaUtilArrayList.size() + ": " + this.jdField_field_1063_of_type_Class_809 + "->" + this.jdField_field_1063_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController + "; ExplosionRadius " + this.jdField_field_1063_of_type_Float + " TIMES RADIUS: " + l2 + "; HANDLE: " + l3 + "; REMOTE: " + l4 + ": dam 0; set 0; rem 0");
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
    this.jdField_field_1063_of_type_Boolean = true;
  }
  
  public final void a()
  {
    Iterator localIterator = this.jdField_field_1063_of_type_JavaUtilArrayList.iterator();
    while (localIterator.hasNext())
    {
      Segment localSegment = (Segment)localIterator.next();
      synchronized (this.jdField_field_1063_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject())
      {
        ((class_672)localSegment).a46(System.currentTimeMillis());
        this.jdField_field_1063_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject().dirtySegmentBuffer.add(new RemoteVector3i(localSegment.field_34, this.jdField_field_1063_of_type_OrgSchemaGameCommonControllerEditableSendableSegmentController.getNetworkObject()));
      }
    }
  }
  
  public final boolean a1()
  {
    return this.jdField_field_1063_of_type_Boolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_807
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */