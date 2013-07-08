import java.util.Random;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public final class class_1095
  extends class_1083
{
  private class_1123[] field_248;
  
  public class_1095(long paramLong)
  {
    super(paramLong);
    this.jdField_field_248_of_type_ArrayOfClass_1123 = new class_1123[9];
    this.jdField_field_248_of_type_ArrayOfClass_1123[0] = new class_1114(128, 3, 73);
    this.jdField_field_248_of_type_ArrayOfClass_1123[1] = new class_1114(87, 14, 73);
    this.jdField_field_248_of_type_ArrayOfClass_1123[2] = new class_1114(133, 6, 73);
    this.jdField_field_248_of_type_ArrayOfClass_1123[3] = new class_1114(72, 1, 73);
    this.jdField_field_248_of_type_ArrayOfClass_1123[4] = new class_1116(this, 0);
    this.jdField_field_248_of_type_ArrayOfClass_1123[5] = new class_1125(95, new short[] { 74, 74, 73 });
    this.jdField_field_248_of_type_ArrayOfClass_1123[6] = new class_1125(103, new short[] { 74, 74, 73 });
    this.jdField_field_248_of_type_ArrayOfClass_1123[7] = new class_1125(99, new short[] { 74, 74, 73 });
    this.jdField_field_248_of_type_ArrayOfClass_1123[8] = new class_1125(107, new short[] { 74, 74, 73 });
  }
  
  public final void a3(SegmentController paramSegmentController, Segment paramSegment)
  {
    if (!this.jdField_field_248_of_type_Boolean)
    {
      this.jdField_field_248_of_type_Class_1119 = new class_1142(((class_864)paramSegmentController).getSeed());
      this.jdField_field_248_of_type_Class_1119.a5(this);
      this.jdField_field_248_of_type_Class_1119.b();
      a4();
      this.jdField_field_248_of_type_Boolean = true;
    }
    a5(paramSegment);
  }
  
  public final short a()
  {
    return 74;
  }
  
  public final class_1123[] a1()
  {
    return this.jdField_field_248_of_type_ArrayOfClass_1123;
  }
  
  public final short b()
  {
    return 73;
  }
  
  public final short c()
  {
    return 74;
  }
  
  public final void a2(Random paramRandom)
  {
    int i;
    if ((i = paramRandom.nextInt(10)) == 0) {
      i = 3;
    } else if (i < 3) {
      i = 2;
    } else {
      i = 1;
    }
    class_1068[] arrayOfclass_1068 = new class_1068[i << 1];
    int j = 0;
    int k = 5;
    for (int m = 0; m < i; m++)
    {
      int n = paramRandom.nextInt(100) - 50;
      int i1 = paramRandom.nextInt(30);
      int i2 = paramRandom.nextInt(20) - 10;
      class_1103 localclass_1103 = new class_1103(paramRandom.nextBoolean(), arrayOfclass_1068, new class_48(n + -50, i2 + 20, n + -50), new class_48(n + 50, i1 + 60, n + 50), k--);
      arrayOfclass_1068[(j++)] = localclass_1103;
      Object localObject = new class_48(n, i2 + 20 + 2, n);
      localObject = new class_1059((class_48)localObject, arrayOfclass_1068, new class_48(((class_48)localObject).field_475 - 1, ((class_48)localObject).field_476, ((class_48)localObject).field_477 - 1), new class_48(((class_48)localObject).field_475 + 1, ((class_48)localObject).field_476 + 1, ((class_48)localObject).field_477 + 1), 6);
      arrayOfclass_1068[(j++)] = localObject;
    }
    for (m = 0; m < arrayOfclass_1068.length; m++) {
      arrayOfclass_1068[m].a7();
    }
    this.jdField_field_248_of_type_Class_1119.a6(arrayOfclass_1068);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1095
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */