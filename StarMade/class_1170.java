import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ControlledElementContainer;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.common.data.world.Segment;

public final class class_1170
  extends class_1168
{
  private int field_244 = 0;
  private class_48 field_242 = new class_48();
  
  public class_1170(class_48 paramclass_481, class_1068[] paramArrayOfclass_1068, class_48 paramclass_482, class_48 paramclass_483, byte paramByte)
  {
    super(paramclass_481, paramArrayOfclass_1068, paramclass_482, paramclass_483, 8, paramByte);
  }
  
  protected final short a(class_48 paramclass_48)
  {
    if (paramclass_48.equals(this.field_241))
    {
      this.field_244 += 1;
      this.field_240.addDelayed(new ControlledElementContainer(ElementCollection.getIndex(class_747.field_136), new class_48(paramclass_48), (short)6, true, true));
      return 6;
    }
    this.field_242.b1(this.field_241);
    class_48 localclass_48 = org.schema.game.common.data.element.Element.DIRECTIONSi[a6(this.field_241)];
    this.field_242.a1(localclass_48);
    if (((localclass_48.field_475 > 0) && (paramclass_48.field_475 > this.field_241.field_475)) || ((localclass_48.field_475 < 0) && (paramclass_48.field_475 < this.field_241.field_475)) || ((localclass_48.field_476 > 0) && (paramclass_48.field_476 > this.field_241.field_476)) || ((localclass_48.field_476 < 0) && (paramclass_48.field_476 < this.field_241.field_476)) || ((localclass_48.field_477 > 0) && (paramclass_48.field_477 > this.field_241.field_477)) || ((localclass_48.field_477 < 0) && (paramclass_48.field_477 < this.field_241.field_477))) {
      return 32767;
    }
    this.field_242.b1(this.field_241);
    this.field_242.c1(localclass_48);
    this.field_240.addDelayed(new ControlledElementContainer(ElementCollection.getIndex(this.field_241), new class_48(paramclass_48), (short)16, true, true));
    this.field_244 += 1;
    return 16;
  }
  
  public final class_1156 a1(Segment paramSegment)
  {
    return null;
  }
  
  public final boolean a2()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1170
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */