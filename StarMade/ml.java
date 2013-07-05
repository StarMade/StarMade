import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;

public abstract interface ml
{
  public abstract RemoteInventoryBuffer getInventoriesChangeBuffer();

  public abstract RemoteInventoryMultModBuffer getInventoryMultModBuffer();

  public abstract RemoteIntArrayBuffer getInventoryUpdateBuffer();

  public abstract RemoteIntArrayBuffer getInventoryActivateBuffer();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ml
 * JD-Core Version:    0.6.2
 */