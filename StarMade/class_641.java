import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.util.HashSet;
import java.util.Iterator;
import org.schema.game.common.data.element.BlockLevel;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.player.inventory.NoSlotFreeException;

public final class class_641
  extends class_627
{
  public class_641(class_635 paramclass_635, class_48 paramclass_48)
  {
    super(paramclass_635, paramclass_48);
  }
  
  public final int a3()
  {
    return 0;
  }
  
  public final int b5()
  {
    return 45;
  }
  
  public final int c2()
  {
    return 1;
  }
  
  public final int d1()
  {
    return 45;
  }
  
  public final void a13()
  {
    Iterator localIterator = ElementKeyMap.getLeveldkeyset().iterator();
    short s;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      s = ((Short)localIterator.next()).shortValue();
    } while ((b7(s) >= 10) && (!a59(s)));
    this.field_136 = false;
  }
  
  private boolean a59(short paramShort)
  {
    ElementInformation localElementInformation;
    short s = ElementKeyMap.getLevel((localElementInformation = ElementKeyMap.getInfo(paramShort)).getLevel().getIdBase(), localElementInformation.getLevel().getLevel() + 1);
    IntOpenHashSet localIntOpenHashSet = new IntOpenHashSet();
    boolean bool = false;
    if (s > 0)
    {
      try
      {
        int j = b7(paramShort);
        a43(paramShort, localIntOpenHashSet);
        int k = j;
        if (j >= 10)
        {
          int i = j / 10 * 10;
          k = j - i;
          i /= 10;
          localIntOpenHashSet.add(b8(s, i));
          bool = true;
        }
        localIntOpenHashSet.add(b8(paramShort, k));
      }
      catch (NoSlotFreeException localNoSlotFreeException) {}
      a56(localIntOpenHashSet);
    }
    return bool;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_641
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */