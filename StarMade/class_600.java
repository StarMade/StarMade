import org.schema.game.network.objects.NetworkBlackHole;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkEntity;

public class class_600
  extends class_598
{
  private NetworkBlackHole field_136;
  
  public class_600(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public void newNetworkObject()
  {
    this.field_136 = new NetworkBlackHole(getState());
  }
  
  public NetworkEntity getNetworkObject()
  {
    return this.field_136;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_600
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */