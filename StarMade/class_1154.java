import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.SynchronizationContainerController;

public final class class_1154
  extends class_1156
{
  public final void a()
  {
    System.err.println("[SERVER] EXECUTING REGION HOOK: " + this);
    SegmentController localSegmentController;
    class_1041 localclass_1041 = (class_1041)(localSegmentController = this.jdField_field_1383_of_type_OrgSchemaGameCommonDataWorldSegment.a15()).getState();
    class_747 localclass_747;
    (localclass_747 = new class_747(localclass_1041)).setId(localclass_1041.getNextFreeObjectId());
    localclass_747.setCreatorThread(new class_730(localclass_747));
    localclass_747.setFactionId(localSegmentController.getFactionId());
    localclass_747.setSectorId(localSegmentController.getSectorId());
    localclass_747.setUniqueIdentifier("ENTITY_SHIP_AITURRET_" + System.currentTimeMillis());
    localclass_747.getMinPos().b1(new class_48(-2, -2, -2));
    localclass_747.getMaxPos().b1(new class_48(2, 2, 2));
    localclass_747.setRealName("Turret");
    if (localSegmentController.getFactionId() != 0)
    {
      localclass_747.a87().a183(class_776.field_1032).a174("Any", true);
      localclass_747.a87().a183(class_776.field_1033).a174("Turret", true);
      localclass_747.a87().a183(class_776.field_1034).a174("true", true);
      localclass_747.a87().a13();
    }
    localclass_747.initialize();
    localclass_747.getInitialTransform().setIdentity();
    localclass_747.getInitialTransform().origin.set(((class_1111)this.jdField_field_1383_of_type_Class_1168).field_241.field_475 - 8, ((class_1111)this.jdField_field_1383_of_type_Class_1168).field_241.field_476 - 8, ((class_1111)this.jdField_field_1383_of_type_Class_1168).field_241.field_477 - 8);
    localclass_747.getDockingController().a7(localSegmentController.getUniqueIdentifier(), ((class_1111)this.jdField_field_1383_of_type_Class_1168).field_241);
    localclass_1041.a59().a4().addNewSynchronizedObjectQueued(localclass_747);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1154
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */