import java.util.Iterator;
import java.util.Set;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.world.Segment;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.StateInterface;

public final class class_766
  extends class_774
{
  public class_766(StateInterface paramStateInterface, class_747 paramclass_747)
  {
    super(paramStateInterface, paramclass_747);
  }
  
  public final String getUniqueIdentifier()
  {
    return this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.getUniqueIdentifier() + "_AI";
  }
  
  public final boolean isVolatile()
  {
    return false;
  }
  
  public final void a168(SegmentController paramSegmentController)
  {
    super.a168(paramSegmentController);
    Iterator localIterator = null;
    if (this.jdField_field_136_of_type_Class_989.a1())
    {
      if ((this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().b3()) && (this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a4().from.a7().a15() == paramSegmentController)) {
        return;
      }
      if (!this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a5().isEmpty())
      {
        localIterator = this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.getDockingController().a5().iterator();
        while (localIterator.hasNext()) {
          if (((ElementDocking)localIterator.next()).from.a7().a15() == paramSegmentController) {
            return;
          }
        }
      }
      ((class_1260)this.jdField_field_136_of_type_Class_989).a28(paramSegmentController);
    }
  }
  
  protected final void b30(class_772 paramclass_772)
  {
    if (paramclass_772.a172() == class_776.field_1033) {
      if (paramclass_772.a171().equals("Turret")) {
        this.jdField_field_136_of_type_Class_989 = new class_1139((class_747)this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController, a183(class_776.field_1034).a7());
      } else {
        this.jdField_field_136_of_type_Class_989 = new class_1244((class_747)this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController, a183(class_776.field_1034).a7());
      }
    }
    super.b30(paramclass_772);
  }
  
  protected final void a169(class_772 paramclass_772)
  {
    if (paramclass_772.a172() == class_776.field_1034)
    {
      paramclass_772.a7();
      this.jdField_field_136_of_type_Class_989 = new class_1260("shipAiEntity", (class_747)this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController);
    }
  }
  
  public final void a117(class_809 paramclass_809)
  {
    if (this.jdField_field_136_of_type_Class_989.a1())
    {
      if ((paramclass_809 != null) && (this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.getFactionId() == -1)) {
        ((class_1041)paramclass_809.getState()).a59().a52(this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController);
      }
      if ((this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController != null) && (this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.getFactionId() != 0)) {
        this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController.setFactionId(0);
      }
    }
    super.a117(paramclass_809);
  }
  
  protected final void b4()
  {
    class_772 localclass_772;
    if ((localclass_772 = a183(class_776.field_1033)).a171().equals("Turret"))
    {
      this.jdField_field_136_of_type_Class_989 = new class_1139((class_747)this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController, a183(class_776.field_1034).a7());
      return;
    }
    if (localclass_772.a171().equals("Ship")) {
      this.jdField_field_136_of_type_Class_989 = new class_1244((class_747)this.jdField_field_136_of_type_OrgSchemaGameCommonControllerSegmentController, a183(class_776.field_1034).a7());
    }
  }
  
  public final boolean b2()
  {
    return a183(class_776.field_1034).a7();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_766
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */