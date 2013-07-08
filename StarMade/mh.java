import java.util.Collection;
import java.util.HashMap;
import org.schema.schine.network.StateInterface;

public abstract interface mh
{
  public abstract mf getInventory(q paramq);
  
  public abstract HashMap getInventories();
  
  public abstract String getName();
  
  public abstract StateInterface getState();
  
  public abstract String printInventories();
  
  public abstract void sendInventoryModification(int paramInt, q paramq);
  
  public abstract ml getInventoryNetworkObject();
  
  public abstract void sendInventoryModification(Collection paramCollection, q paramq);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     mh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */