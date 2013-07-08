import org.schema.game.common.data.world.Segment;

public final class class_728
  extends class_908
{
  private class_1177 field_195;
  
  public class_728(class_737 paramclass_737, class_780 paramclass_780)
  {
    super(paramclass_737);
    if (paramclass_780 == class_780.field_1037)
    {
      paramclass_737.getSeed();
      this.field_195 = new class_1162();
      return;
    }
    if (paramclass_780 == class_780.field_1039)
    {
      this.field_195 = new class_1166(paramclass_737.getSeed());
      return;
    }
    paramclass_737.getSeed();
    this.field_195 = new class_1175();
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
 * Qualified Name:     class_728
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */