import java.util.Random;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public final class class_1093
  extends class_1083
{
  private class_1123[] field_248;
  
  public class_1093(long paramLong)
  {
    super(paramLong);
    this.jdField_field_248_of_type_ArrayOfClass_1123 = new class_1123[9];
    this.jdField_field_248_of_type_ArrayOfClass_1123[0] = new class_1078(128, 3, 91);
    this.jdField_field_248_of_type_ArrayOfClass_1123[1] = new class_1114(87, 14, 91);
    this.jdField_field_248_of_type_ArrayOfClass_1123[2] = new class_1078(133, 6, 91);
    this.jdField_field_248_of_type_ArrayOfClass_1123[3] = new class_1078(72, 4, 91);
    this.jdField_field_248_of_type_ArrayOfClass_1123[4] = new class_1078(129, 4, 91);
    this.jdField_field_248_of_type_ArrayOfClass_1123[5] = new class_1125(97, new short[] { 90, 92 });
    this.jdField_field_248_of_type_ArrayOfClass_1123[6] = new class_1125(101, new short[] { 90, 92 });
    this.jdField_field_248_of_type_ArrayOfClass_1123[7] = new class_1125(105, new short[] { 90, 92 });
    this.jdField_field_248_of_type_ArrayOfClass_1123[8] = new class_1125(109, new short[] { 90, 92 });
  }
  
  public final void a3(SegmentController paramSegmentController, Segment paramSegment)
  {
    if (!this.jdField_field_248_of_type_Boolean)
    {
      this.jdField_field_248_of_type_Class_1119 = new class_1160(((class_864)paramSegmentController).getSeed());
      this.jdField_field_248_of_type_Class_1119.a5(this);
      a4();
      this.jdField_field_248_of_type_Boolean = true;
    }
    a5(paramSegment);
  }
  
  public final short a()
  {
    return 92;
  }
  
  public final class_1123[] a1()
  {
    return this.jdField_field_248_of_type_ArrayOfClass_1123;
  }
  
  public final short b()
  {
    return 91;
  }
  
  public final short c()
  {
    return 90;
  }
  
  public final void a2(Random paramRandom) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1093
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */