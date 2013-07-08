import java.io.PrintStream;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.schema.game.common.controller.SegmentController;

public final class class_202
  extends class_201
  implements class_954
{
  public final void handleKeyEvent()
  {
    class_48 localclass_48;
    (localclass_48 = new class_48(this.jdField_field_89_of_type_Class_48)).a1(this.jdField_field_89_of_type_Class_457.a());
    if (!this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().b1(localclass_48)) {
      while ((this.jdField_field_89_of_type_Class_48.a4() > 0.0F) && (!this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().b1(localclass_48)))
      {
        this.jdField_field_89_of_type_Class_48.field_475 /= 2;
        this.jdField_field_89_of_type_Class_48.field_476 /= 2;
        this.jdField_field_89_of_type_Class_48.field_477 /= 2;
        localclass_48.b1(this.jdField_field_89_of_type_Class_48);
        localclass_48.a1(this.jdField_field_89_of_type_Class_457.a());
        System.err.println("[CAM] SEARCHING anchor");
      }
    }
    int i = 1;
    Vector3f localVector3f = new Vector3f(localclass_48.field_475, localclass_48.field_476, localclass_48.field_477);
    if (Keyboard.getEventKeyState())
    {
      if (!Keyboard.isKeyDown(42))
      {
        if (Keyboard.getEventKey() == class_367.field_711.a5()) {
          localVector3f.sub(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getCamLeftLocal());
        } else if (Keyboard.getEventKey() == class_367.field_712.a5()) {
          localVector3f.add(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getCamLeftLocal());
        } else if (Keyboard.getEventKey() == class_367.field_713.a5()) {
          localVector3f.add(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getCamForwLocal());
        } else if (Keyboard.getEventKey() == class_367.field_714.a5()) {
          localVector3f.sub(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getCamForwLocal());
        } else if (Keyboard.getEventKey() == class_367.field_715.a5()) {
          localVector3f.add(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getCamUpLocal());
        } else if (Keyboard.getEventKey() == class_367.field_716.a5()) {
          localVector3f.sub(this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getCamUpLocal());
        } else {
          i = 0;
        }
      }
      else {
        i = 0;
      }
      localclass_48.b(Math.round(localVector3f.field_615), Math.round(localVector3f.field_616), Math.round(localVector3f.field_617));
      if (i != 0)
      {
        if (this.jdField_field_89_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentBuffer().b1(localclass_48))
        {
          localclass_48.c1(this.jdField_field_89_of_type_Class_457.a());
          System.err.println("EXISTS!. pos mod set to " + localclass_48);
          this.jdField_field_89_of_type_Class_48.b1(localclass_48);
          return;
        }
        System.err.println("NOT EXISTS!. pos mod NOT set to " + localclass_48);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_202
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */