import org.schema.game.common.data.world.Segment;

public final class class_1059
  extends class_1168
{
  private boolean field_240;
  
  public class_1059(class_48 paramclass_481, class_1068[] paramArrayOfclass_1068, class_48 paramclass_482, class_48 paramclass_483, int paramInt)
  {
    super(paramclass_481, paramArrayOfclass_1068, paramclass_482, paramclass_483, paramInt, (byte)0);
  }
  
  protected final short a(class_48 paramclass_48)
  {
    if (paramclass_48.equals(this.field_241))
    {
      this.field_240 = true;
      return 120;
    }
    return 32767;
  }
  
  public final class_1156 a1(Segment paramSegment)
  {
    class_1158 localclass_1158;
    (localclass_1158 = new class_1158()).a1(this, paramSegment);
    this.field_240 = false;
    return localclass_1158;
  }
  
  public final boolean a2()
  {
    return this.field_240;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1059
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */