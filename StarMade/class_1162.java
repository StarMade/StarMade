import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public final class class_1162
  extends class_1177
{
  private class_1068[] jdField_field_248_of_type_ArrayOfClass_1068;
  private class_48 jdField_field_248_of_type_Class_48 = new class_48(-25, -20, -30);
  private class_48 field_257 = new class_48(25, 10, 30);
  private class_48 field_258 = new class_48(-15, 0, -15);
  private class_48 field_259 = new class_48(15, 60, 15);
  private class_48 field_260 = new class_48(-40, 0, -40);
  private class_48 field_261 = new class_48(40, 20, 40);
  
  private class_1068 a8(class_48 paramclass_48, int paramInt)
  {
    class_48 localclass_481;
    (localclass_481 = new class_48(this.jdField_field_248_of_type_Class_48)).a1(paramclass_48);
    class_48 localclass_482;
    (localclass_482 = new class_48(this.field_257)).a1(paramclass_48);
    return new class_1099(this.jdField_field_248_of_type_ArrayOfClass_1068, localclass_481, localclass_482, paramInt);
  }
  
  private class_1068 a9(class_48 paramclass_48)
  {
    class_48 localclass_481;
    (localclass_481 = new class_48(this.field_258)).a1(paramclass_48);
    class_48 localclass_482;
    (localclass_482 = new class_48(this.field_259)).a1(paramclass_48);
    return new class_1101(this.jdField_field_248_of_type_ArrayOfClass_1068, localclass_481, localclass_482);
  }
  
  private class_1068 a10(class_48 paramclass_48, int paramInt1, int paramInt2)
  {
    class_48 localclass_48;
    (localclass_48 = new class_48(-5, -5, 0)).a1(paramclass_48);
    (paramInt1 = new class_48(5, 5, paramInt1)).a1(paramclass_48);
    return new class_1171(this.jdField_field_248_of_type_ArrayOfClass_1068, localclass_48, paramInt1, paramInt2);
  }
  
  private class_1068 b1(class_48 paramclass_48, int paramInt1, int paramInt2)
  {
    class_48 localclass_48;
    (localclass_48 = new class_48(-4, -4, 0)).a1(paramclass_48);
    (paramInt1 = new class_48(4, 4, paramInt1)).a1(paramclass_48);
    return new class_1173(this.jdField_field_248_of_type_ArrayOfClass_1068, localclass_48, paramInt1, paramInt2);
  }
  
  public final void a3(SegmentController paramSegmentController, Segment paramSegment)
  {
    if (this.jdField_field_248_of_type_ArrayOfClass_1068 == null)
    {
      this.jdField_field_248_of_type_ArrayOfClass_1068 = new class_1068[24];
      this.jdField_field_248_of_type_ArrayOfClass_1068[0] = a8(new class_48(0, 0, -55), 0);
      this.jdField_field_248_of_type_ArrayOfClass_1068[1] = a8(new class_48(0, 0, -55), 1);
      this.jdField_field_248_of_type_ArrayOfClass_1068[2] = a8(new class_48(0, 0, 55), 2);
      this.jdField_field_248_of_type_ArrayOfClass_1068[3] = a8(new class_48(0, 0, 55), 3);
      this.jdField_field_248_of_type_ArrayOfClass_1068[4] = a10(new class_48(0, -15, -28), 55, 0);
      this.jdField_field_248_of_type_ArrayOfClass_1068[5] = a10(new class_48(0, -15, -28), 55, 1);
      this.jdField_field_248_of_type_ArrayOfClass_1068[6] = b1(new class_48(0, -15, -28), 55, 0);
      this.jdField_field_248_of_type_ArrayOfClass_1068[7] = b1(new class_48(0, -15, -28), 55, 1);
      localObject1 = new class_48(0, -19, 0);
      class_1162 localclass_11621 = this;
      (localObject2 = new class_48(-5, 0, -5)).a1((class_48)localObject1);
      (localclass_48 = new class_48(5, 80, 5)).a1((class_48)localObject1);
      this.jdField_field_248_of_type_ArrayOfClass_1068[8] = new class_1062(localclass_11621.jdField_field_248_of_type_ArrayOfClass_1068, (class_48)localObject2, localclass_48);
      localObject1 = new class_48(0, -19, 0);
      localclass_11621 = this;
      (localObject2 = new class_48(-4, 0, -4)).a1((class_48)localObject1);
      (localclass_48 = new class_48(4, 80, 4)).a1((class_48)localObject1);
      this.jdField_field_248_of_type_ArrayOfClass_1068[9] = new class_1064(localclass_11621.jdField_field_248_of_type_ArrayOfClass_1068, (class_48)localObject2, localclass_48);
      localObject1 = new class_48(0, 54, 0);
      localclass_11621 = this;
      (localclass_48 = new class_48(localclass_11621.field_260)).a1((class_48)localObject1);
      (localObject2 = new class_48(localclass_11621.field_261)).a1((class_48)localObject1);
      this.jdField_field_248_of_type_ArrayOfClass_1068[10] = new class_1107(localclass_11621.jdField_field_248_of_type_ArrayOfClass_1068, localclass_48, (class_48)localObject2);
      localObject1 = new class_48(0, 54 - this.field_261.field_476, 0);
      localclass_11621 = this;
      (localclass_48 = new class_48(localclass_11621.field_260)).a1((class_48)localObject1);
      (localObject2 = new class_48(localclass_11621.field_261)).a1((class_48)localObject1);
      this.jdField_field_248_of_type_ArrayOfClass_1068[11] = new class_1097(localclass_11621.jdField_field_248_of_type_ArrayOfClass_1068, localclass_48, (class_48)localObject2);
      this.jdField_field_248_of_type_ArrayOfClass_1068[12] = a9(new class_48(50, -20, 50));
      this.jdField_field_248_of_type_ArrayOfClass_1068[13] = a9(new class_48(50, -20, -50));
      this.jdField_field_248_of_type_ArrayOfClass_1068[14] = a9(new class_48(-50, -20, -50));
      this.jdField_field_248_of_type_ArrayOfClass_1068[15] = a9(new class_48(-50, -20, 50));
      this.jdField_field_248_of_type_ArrayOfClass_1068[16] = a10(new class_48(50, 20, -50), 100, 0);
      this.jdField_field_248_of_type_ArrayOfClass_1068[17] = a10(new class_48(50, 20, -50), 100, 1);
      this.jdField_field_248_of_type_ArrayOfClass_1068[18] = b1(new class_48(50, 20, -50), 80, 0);
      this.jdField_field_248_of_type_ArrayOfClass_1068[19] = b1(new class_48(50, 20, -50), 80, 1);
      this.jdField_field_248_of_type_ArrayOfClass_1068[20] = a10(new class_48(-50, 20, -50), 100, 0);
      this.jdField_field_248_of_type_ArrayOfClass_1068[21] = a10(new class_48(-50, 20, -50), 100, 1);
      this.jdField_field_248_of_type_ArrayOfClass_1068[22] = b1(new class_48(-50, 20, -50), 80, 0);
      this.jdField_field_248_of_type_ArrayOfClass_1068[23] = b1(new class_48(-50, 20, -50), 80, 1);
      for (int i = 0; i < this.jdField_field_248_of_type_ArrayOfClass_1068.length; i++) {
        this.jdField_field_248_of_type_ArrayOfClass_1068[i].a7();
      }
    }
    Object localObject2 = paramSegmentController;
    Object localObject1 = paramSegment;
    class_1162 localclass_11622 = this;
    class_48 localclass_48 = new class_48();
    for (int j = 0; j < 16; j = (byte)(j + 1)) {
      for (paramSegmentController = 0; paramSegmentController < 16; paramSegmentController = (byte)(paramSegmentController + 1)) {
        for (paramSegment = 0; paramSegment < 16; paramSegment = (byte)(paramSegment + 1))
        {
          localclass_48.b1(((Segment)localObject1).field_34);
          localclass_48.field_475 += paramSegment;
          localclass_48.field_476 += paramSegmentController;
          localclass_48.field_477 += j;
          class_1068[] arrayOfclass_1068;
          int k = (arrayOfclass_1068 = localclass_11622.jdField_field_248_of_type_ArrayOfClass_1068).length;
          for (int m = 0; m < k; m++)
          {
            class_1068 localclass_1068;
            if ((localclass_1068 = arrayOfclass_1068[m]).a3(localclass_48))
            {
              short s;
              if ((s = localclass_1068.b1(localclass_48)) == 32767) {
                break;
              }
              a12(paramSegment, paramSegmentController, j, (Segment)localObject1, Short.valueOf(s));
              break;
            }
          }
        }
      }
    }
    ((SegmentController)localObject2).getSegmentBuffer().b6((Segment)localObject1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1162
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */