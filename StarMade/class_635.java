import java.util.Collection;
import java.util.HashMap;
import org.schema.schine.network.StateInterface;

public abstract interface class_635
{
  public abstract class_639 getInventory(class_48 paramclass_48);
  
  public abstract HashMap getInventories();
  
  public abstract String getName();
  
  public abstract StateInterface getState();
  
  public abstract String printInventories();
  
  public abstract void sendInventoryModification(int paramInt, class_48 paramclass_48);
  
  public abstract class_643 getInventoryNetworkObject();
  
  public abstract void sendInventoryModification(Collection paramCollection, class_48 paramclass_48);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_635
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */