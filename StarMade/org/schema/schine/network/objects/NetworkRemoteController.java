package org.schema.schine.network.objects;

import java.util.Arrays;
import org.schema.schine.network.StateInterface;

public class NetworkRemoteController
  extends NetworkObject
{
  public long clientID;
  public long field_87;
  public String name = "unknownController";
  public String[] fNames;
  public int[] fTypes;
  public String[] fValues;
  public int[] fControllable;
  public long entityID;
  public String customName;
  
  public NetworkRemoteController(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public void onDelete(StateInterface paramStateInterface) {}
  
  public void onInit(StateInterface paramStateInterface) {}
  
  public String toString()
  {
    return "[" + this.field_87 + "]" + this.name + " " + Arrays.toString(this.fNames) + ", " + Arrays.toString(this.fValues);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.NetworkRemoteController
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */