import java.util.Random;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public class class_1138
{
  protected int field_253;
  public Random field_253;
  
  public class_1138()
  {
    this.jdField_field_253_of_type_Int = 8;
    this.jdField_field_253_of_type_JavaUtilRandom = new Random();
  }
  
  public final void a3(Segment paramSegment, int paramInt1, int paramInt2, short[] paramArrayOfShort)
  {
    int i = this.jdField_field_253_of_type_Int;
    this.jdField_field_253_of_type_JavaUtilRandom.setSeed(paramSegment.a15().getSeed());
    long l1 = this.jdField_field_253_of_type_JavaUtilRandom.nextLong();
    long l2 = this.jdField_field_253_of_type_JavaUtilRandom.nextLong();
    for (int j = paramInt1 - i; j <= paramInt1 + i; j++) {
      for (int k = paramInt2 - i; k <= paramInt2 + i; k++)
      {
        long l3 = j * l1;
        long l4 = k * l2;
        this.jdField_field_253_of_type_JavaUtilRandom.setSeed(l3 ^ l4 ^ paramSegment.a15().getSeed());
        a1(j, k, paramInt1, paramInt2, paramArrayOfShort);
      }
    }
  }
  
  protected void a1(int paramInt1, int paramInt2, int paramInt3, int paramInt4, short[] paramArrayOfShort) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1138
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */