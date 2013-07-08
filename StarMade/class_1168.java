import java.util.Collection;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.world.Segment;

public abstract class class_1168
  extends class_1068
{
  public final class_48 field_241;
  public ControlElementMap field_240;
  private byte field_240;
  
  public class_1168(class_48 paramclass_481, class_1068[] paramArrayOfclass_1068, class_48 paramclass_482, class_48 paramclass_483, int paramInt, byte paramByte)
  {
    super(paramArrayOfclass_1068, paramclass_482, paramclass_483, paramInt, 0);
    this.field_241 = paramclass_481;
    this.field_240 = paramByte;
  }
  
  public final void a5(Collection paramCollection, Segment paramSegment)
  {
    paramSegment = a1(paramSegment);
    synchronized (paramCollection)
    {
      paramCollection.add(paramSegment);
      return;
    }
  }
  
  public abstract class_1156 a1(Segment paramSegment);
  
  public abstract boolean a2();
  
  public final byte a6(class_48 paramclass_48)
  {
    if (paramclass_48.equals(this.field_241)) {
      return this.field_240;
    }
    return -1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1168
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */