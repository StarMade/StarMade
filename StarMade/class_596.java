import org.schema.game.network.objects.NetworkSun;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkEntity;

public class class_596
  extends class_598
{
  private NetworkSun field_136;
  
  public class_596(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public void newNetworkObject()
  {
    this.field_136 = new NetworkSun(getState());
  }
  
  public NetworkEntity getNetworkObject()
  {
    return this.field_136;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_596
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */