import java.util.Random;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public abstract class class_1083
  extends class_1177
{
  private long field_248;
  protected boolean field_248;
  protected class_1119 field_248;
  
  public class_1083(long paramLong)
  {
    this.jdField_field_248_of_type_Boolean = false;
    this.jdField_field_248_of_type_Long = paramLong;
  }
  
  public abstract void a2(Random paramRandom);
  
  public void a3(SegmentController paramSegmentController, Segment paramSegment)
  {
    if (!this.jdField_field_248_of_type_Boolean)
    {
      this.jdField_field_248_of_type_Class_1119 = new class_1119(((class_864)paramSegmentController).getSeed());
      this.jdField_field_248_of_type_Class_1119.a5(this);
      a4();
      this.jdField_field_248_of_type_Boolean = true;
    }
    a5(paramSegment);
  }
  
  protected final void a4()
  {
    Random localRandom;
    if ((localRandom = new Random(this.jdField_field_248_of_type_Long)).nextInt(15) == 0) {
      a2(localRandom);
    }
  }
  
  protected final void a5(Segment paramSegment)
  {
    this.jdField_field_248_of_type_Class_1119.a3(paramSegment.a16(), 64 + paramSegment.field_34.field_475 / 16, Math.abs(paramSegment.field_34.field_476) / 16, 64 + paramSegment.field_34.field_477 / 16, paramSegment.field_34.field_476 < 0);
    this.jdField_field_248_of_type_Class_1119.a7(paramSegment);
  }
  
  public abstract short a();
  
  public abstract class_1123[] a1();
  
  public abstract short b();
  
  public abstract short c();
  
  public short d()
  {
    return 80;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1083
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */