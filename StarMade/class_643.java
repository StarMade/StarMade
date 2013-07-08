import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;

public abstract interface class_643
{
  public abstract RemoteInventoryBuffer getInventoriesChangeBuffer();
  
  public abstract RemoteInventoryMultModBuffer getInventoryMultModBuffer();
  
  public abstract RemoteIntArrayBuffer getInventoryUpdateBuffer();
  
  public abstract RemoteIntArrayBuffer getInventoryActivateBuffer();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_643
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */