package org.schema.schine.network.objects.remote;

import org.schema.schine.network.objects.NetworkObject;

public abstract class RemoteComparable
  extends RemoteField
  implements Comparable
{
  public RemoteComparable(Comparable paramComparable, NetworkObject paramNetworkObject)
  {
    super(paramComparable, paramNetworkObject);
  }
  
  public RemoteComparable(Comparable paramComparable, boolean paramBoolean)
  {
    super(paramComparable, paramBoolean);
  }
  
  public int compareTo(Comparable paramComparable)
  {
    return ((Comparable)get()).compareTo(paramComparable);
  }
  
  public void set(Comparable paramComparable)
  {
    set(paramComparable, this.forcedClientSending);
  }
  
  public void set(Comparable paramComparable, boolean paramBoolean)
  {
    if ((this.onServer) || (paramBoolean)) {
      setChanged((hasChanged()) || (!paramComparable.equals(get())));
    }
    super.set(paramComparable);
    if ((hasChanged()) && (this.observer != null)) {
      this.observer.update(this);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteComparable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */