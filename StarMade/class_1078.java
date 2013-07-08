import java.util.Random;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.world.SegmentData;

public class class_1078
  extends class_1114
{
  public class_1078(short paramShort1, int paramInt, short paramShort2)
  {
    super(paramShort1, paramInt, paramShort2);
    if ((!jdField_field_247_of_type_Boolean) && (!ElementKeyMap.getInfo(paramShort1).isLeveled())) {
      throw new AssertionError();
    }
  }
  
  public final void a3(SegmentData paramSegmentData, int paramInt1, int paramInt2, int paramInt3, Random paramRandom)
  {
    int i = 1;
    for (float f = 0.1F; (i < 5) && (paramRandom.nextFloat() < f); f *= 0.9F) {
      i++;
    }
    paramRandom = ElementKeyMap.getLevel(this.jdField_field_247_of_type_Short, i);
    paramSegmentData.setInfoElementUnsynched((byte)Math.abs(paramInt1 % 16), (byte)Math.abs(paramInt2 % 16), (byte)Math.abs(paramInt3 % 16), paramRandom, false);
  }
  
  static
  {
    jdField_field_247_of_type_Boolean = !va.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1078
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */