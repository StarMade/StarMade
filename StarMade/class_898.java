import org.schema.game.common.data.world.Segment;

public final class class_898
  extends class_908
{
  private class_1186 field_195;
  
  public class_898(class_705 paramclass_705, class_902 paramclass_902)
  {
    super(paramclass_705);
    switch (class_904.field_1137[paramclass_902.ordinal()])
    {
    case 1: 
      this.field_195 = new class_1183(paramclass_705.getSeed());
      return;
    case 2: 
      this.field_195 = new class_1128(paramclass_705.getSeed());
      return;
    case 3: 
      this.field_195 = new class_1126(paramclass_705.getSeed());
      return;
    case 4: 
      this.field_195 = new class_1188(paramclass_705.getSeed());
      return;
    case 5: 
      this.field_195 = new class_1132(paramclass_705.getSeed());
      return;
    case 6: 
      this.field_195 = new class_1089(paramclass_705.getSeed());
      return;
    case 7: 
      this.field_195 = new class_1130(paramclass_705.getSeed());
      return;
    }
    this.field_195 = new class_1091(paramclass_705.getSeed());
  }
  
  public final void a(Segment paramSegment)
  {
    this.field_195.a3(this.field_195, paramSegment);
  }
  
  public final boolean a1()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_898
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */