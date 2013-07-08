import com.bulletphysics.linearmath.Transform;
import java.io.PrintStream;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public final class class_1181
  extends class_1177
{
  private int jdField_field_248_of_type_Int = 16;
  private int jdField_field_257_of_type_Int = 48;
  private int jdField_field_258_of_type_Int = 4;
  private class_48 jdField_field_248_of_type_Class_48;
  private class_48 jdField_field_257_of_type_Class_48;
  private class_48 jdField_field_258_of_type_Class_48 = new class_48();
  private class_48 field_259 = new class_48();
  private Vector3f jdField_field_248_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_257_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_258_of_type_JavaxVecmathVector3f = new Vector3f();
  
  public class_1181(class_48 paramclass_48)
  {
    new Transform();
    this.jdField_field_248_of_type_Class_48 = paramclass_48;
    this.jdField_field_257_of_type_Class_48 = new class_48(this.jdField_field_257_of_type_Int, this.jdField_field_257_of_type_Int, this.jdField_field_257_of_type_Int);
  }
  
  public final void a3(SegmentController paramSegmentController, Segment paramSegment)
  {
    SegmentController localSegmentController = paramSegmentController;
    paramSegment = paramSegment;
    paramSegmentController = this;
    for (int i = 0; i < 16; i = (byte)(i + 1)) {
      for (int j = 0; j < 16; j = (byte)(j + 1)) {
        for (int k = 0; k < 16; k = (byte)(k + 1))
        {
          paramSegmentController.jdField_field_258_of_type_Class_48.b(k + paramSegment.field_34.field_475, j + paramSegment.field_34.field_476, i + paramSegment.field_34.field_477);
          paramSegmentController.field_259.a6(paramSegmentController.jdField_field_248_of_type_Class_48, paramSegmentController.jdField_field_258_of_type_Class_48);
          paramSegmentController.jdField_field_257_of_type_JavaxVecmathVector3f.set(paramSegmentController.jdField_field_257_of_type_Class_48.field_475, paramSegmentController.jdField_field_257_of_type_Class_48.field_476, paramSegmentController.jdField_field_257_of_type_Class_48.field_477);
          paramSegmentController.jdField_field_248_of_type_JavaxVecmathVector3f.set(paramSegmentController.jdField_field_248_of_type_Class_48.field_475, paramSegmentController.jdField_field_248_of_type_Class_48.field_476, paramSegmentController.jdField_field_248_of_type_Class_48.field_477);
          paramSegmentController.jdField_field_248_of_type_JavaxVecmathVector3f.field_615 -= paramSegmentController.jdField_field_257_of_type_Class_48.field_475;
          paramSegmentController.jdField_field_248_of_type_JavaxVecmathVector3f.field_616 -= paramSegmentController.jdField_field_257_of_type_Class_48.field_476;
          paramSegmentController.jdField_field_248_of_type_JavaxVecmathVector3f.field_617 -= paramSegmentController.jdField_field_257_of_type_Class_48.field_477;
          float f1 = paramSegmentController.jdField_field_248_of_type_JavaxVecmathVector3f.length();
          paramSegmentController.jdField_field_248_of_type_JavaxVecmathVector3f.normalize();
          float f2 = 0.0F;
          int m = 0;
          while (f2 < f1)
          {
            paramSegmentController.jdField_field_257_of_type_JavaxVecmathVector3f.add(paramSegmentController.jdField_field_248_of_type_JavaxVecmathVector3f);
            paramSegmentController.jdField_field_258_of_type_JavaxVecmathVector3f.set(paramSegmentController.jdField_field_257_of_type_JavaxVecmathVector3f);
            paramSegmentController.jdField_field_258_of_type_JavaxVecmathVector3f.field_615 -= paramSegmentController.jdField_field_258_of_type_Class_48.field_475;
            paramSegmentController.jdField_field_258_of_type_JavaxVecmathVector3f.field_616 -= paramSegmentController.jdField_field_258_of_type_Class_48.field_476;
            paramSegmentController.jdField_field_258_of_type_JavaxVecmathVector3f.field_617 -= paramSegmentController.jdField_field_258_of_type_Class_48.field_477;
            if (paramSegmentController.jdField_field_258_of_type_JavaxVecmathVector3f.length() < paramSegmentController.jdField_field_258_of_type_Int)
            {
              m = 1;
              break;
            }
            f2 += 1.0F;
          }
          if (paramSegmentController.jdField_field_258_of_type_Class_48.equals(paramSegmentController.jdField_field_248_of_type_Class_48))
          {
            System.err.println("PLACED DEATH START CENTER!");
            a12(k, j, i, paramSegment, Short.valueOf((short)65));
          }
          if (m == 0) {
            if ((paramSegmentController.field_259.a4() > paramSegmentController.jdField_field_248_of_type_Int + 1) && (paramSegmentController.field_259.a4() < paramSegmentController.jdField_field_257_of_type_Int)) {
              a12(k, j, i, paramSegment, Short.valueOf((short)5));
            } else if (paramSegmentController.field_259.a4() == paramSegmentController.jdField_field_248_of_type_Int + 1) {
              a12(k, j, i, paramSegment, Short.valueOf((short)80));
            }
          }
        }
      }
    }
    localSegmentController.getSegmentBuffer().b6(paramSegment);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1181
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */