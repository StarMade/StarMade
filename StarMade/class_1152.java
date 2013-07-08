import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public final class class_1152
  extends class_1177
{
  private class_1068[] field_248 = new class_1068[3];
  
  public class_1152()
  {
    class_48 localclass_481 = new class_48(8, 8, 8);
    class_1152 localclass_1152 = this;
    class_48 localclass_482 = new class_48(localclass_481.field_475, localclass_481.field_476, localclass_481.field_477);
    class_48 localclass_483 = new class_48(localclass_481.field_475 + 1, localclass_481.field_476 + 1, localclass_481.field_477 + 1);
    this.field_248[0] = new class_1109(localclass_481, localclass_1152.field_248, localclass_482, localclass_483);
    localclass_481 = new class_48(8, 8, 9);
    localclass_1152 = this;
    (localclass_482 = new class_48(-1, -1, -2)).a1(localclass_481);
    (localclass_483 = new class_48(2, 2, 5)).a1(localclass_481);
    this.field_248[1] = new class_1170(localclass_481, localclass_1152.field_248, localclass_482, localclass_483, org.schema.game.common.data.element.Element.orientationMapping[3]);
    localclass_481 = new class_48(8, 8, 9);
    localclass_1152 = this;
    (localclass_482 = new class_48(-2, -2, -2)).a1(localclass_481);
    (localclass_483 = new class_48(3, 3, 5)).a1(localclass_481);
    this.field_248[2] = new class_1060(localclass_1152.field_248, localclass_482, localclass_483, localclass_481);
    for (int i = 0; i < this.field_248.length; i++) {
      this.field_248[i].a7();
    }
  }
  
  public final void a3(SegmentController paramSegmentController, Segment paramSegment)
  {
    for (int i = 0; i < this.field_248.length; i++) {
      if ((this.field_248[i] instanceof class_1168)) {
        ((class_1168)this.field_248[i]).field_240 = paramSegment.a15().getControlElementMap();
      }
    }
    class_48 localclass_48 = new class_48();
    for (int j = 0; j < 16; j = (byte)(j + 1)) {
      for (int k = 0; k < 16; k = (byte)(k + 1)) {
        for (int m = 0; m < 16; m = (byte)(m + 1))
        {
          localclass_48.b1(paramSegment.field_34);
          localclass_48.field_475 += m;
          localclass_48.field_476 += k;
          localclass_48.field_477 += j;
          class_1068[] arrayOfclass_1068;
          int n = (arrayOfclass_1068 = this.field_248).length;
          for (int i1 = 0; i1 < n; i1++)
          {
            class_1068 localclass_1068;
            if ((localclass_1068 = arrayOfclass_1068[i1]).a3(localclass_48))
            {
              short s;
              if ((s = localclass_1068.b1(localclass_48)) == 32767) {
                break;
              }
              a12(m, k, j, paramSegment, Short.valueOf(s));
              break;
            }
          }
        }
      }
    }
    paramSegmentController.getSegmentBuffer().b6(paramSegment);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1152
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */