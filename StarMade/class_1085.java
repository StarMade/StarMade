import java.util.Random;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public final class class_1085
  extends class_1083
{
  private class_1123[] field_248;
  
  public final void a3(SegmentController paramSegmentController, Segment paramSegment)
  {
    if (!this.jdField_field_248_of_type_Boolean)
    {
      this.jdField_field_248_of_type_Class_1119 = new class_1140(((class_864)paramSegmentController).getSeed());
      this.jdField_field_248_of_type_Class_1119.a5(this);
      a4();
      this.jdField_field_248_of_type_Boolean = true;
    }
    a5(paramSegment);
  }
  
  public class_1085(long paramLong)
  {
    super(paramLong);
    this.jdField_field_248_of_type_ArrayOfClass_1123 = new class_1123[8];
    this.jdField_field_248_of_type_ArrayOfClass_1123[0] = new class_1114(137, 4, 275);
    this.jdField_field_248_of_type_ArrayOfClass_1123[1] = new class_1114(132, 4, 275);
    this.jdField_field_248_of_type_ArrayOfClass_1123[2] = new class_1114(130, 4, 275);
    this.jdField_field_248_of_type_ArrayOfClass_1123[3] = new class_1114(129, 4, 275);
    this.jdField_field_248_of_type_ArrayOfClass_1123[4] = new class_1125(Integer.valueOf(1), 280, new short[] { 274, 64 });
    this.jdField_field_248_of_type_ArrayOfClass_1123[5] = new class_1125(Integer.valueOf(1), 279, new short[] { 274, 64 });
    this.jdField_field_248_of_type_ArrayOfClass_1123[6] = new class_1125(Integer.valueOf(1), 281, new short[] { 274, 64 });
    this.jdField_field_248_of_type_ArrayOfClass_1123[7] = new class_1125(Integer.valueOf(1), 278, new short[] { 274, 64 });
  }
  
  public final short a()
  {
    return 64;
  }
  
  public final short d()
  {
    return 286;
  }
  
  public final class_1123[] a1()
  {
    return this.jdField_field_248_of_type_ArrayOfClass_1123;
  }
  
  public final short b()
  {
    return 275;
  }
  
  public final short c()
  {
    return 274;
  }
  
  public final void a2(Random paramRandom) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1085
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */