import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;
import org.schema.schine.network.StateInterface;

public abstract class class_908
{
  public final SegmentController field_195;
  public boolean field_195;
  public boolean field_1138 = true;
  
  public class_908(SegmentController paramSegmentController)
  {
    this.jdField_field_195_of_type_Boolean = true;
    this.jdField_field_195_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
    ((class_715)this.jdField_field_195_of_type_OrgSchemaGameCommonControllerSegmentController.getState().getController()).a().a(this.jdField_field_195_of_type_OrgSchemaGameCommonControllerSegmentController);
  }
  
  public abstract void a(Segment paramSegment);
  
  public abstract boolean a1();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_908
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */