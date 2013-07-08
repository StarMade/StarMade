package org.schema.schine.network.objects.remote;

import java.util.Arrays;
import org.schema.schine.network.objects.NetworkObject;

public class RemoteStringArray
  extends RemoteArray
{
  public RemoteStringArray(int paramInt, NetworkObject paramNetworkObject)
  {
    super(new RemoteString[paramInt], paramNetworkObject);
    for (int i = 0; i < paramInt; i++) {
      ((RemoteField[])get())[i] = new RemoteString(paramNetworkObject);
    }
    addObservers();
  }
  
  public RemoteStringArray(int paramInt, boolean paramBoolean)
  {
    super(new RemoteString[paramInt], paramBoolean);
    for (int i = 0; i < paramInt; i++) {
      ((RemoteField[])get())[i] = new RemoteString(paramBoolean);
    }
    addObservers();
  }
  
  public int byteLength()
  {
    int i = 0;
    for (int j = 0; j < ((RemoteField[])get()).length; j++) {
      i += ((RemoteField[])get())[j].byteLength();
    }
    return i;
  }
  
  protected void init(RemoteField[] paramArrayOfRemoteField)
  {
    set(paramArrayOfRemoteField);
  }
  
  public void set(int paramInt, String paramString)
  {
    ((RemoteField[])super.get())[paramInt].set(paramString, this.forcedClientSending);
  }
  
  public void set(RemoteField[] paramArrayOfRemoteField)
  {
    super.set(paramArrayOfRemoteField);
    for (int i = 0; i < paramArrayOfRemoteField.length; i++)
    {
      ((RemoteField[])get())[i] = new RemoteString(this.onServer);
      ((RemoteField[])get())[i].observer = this;
    }
  }
  
  public String toString()
  {
    return "RemoteStringArray" + Arrays.toString((Object[])get());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteStringArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */