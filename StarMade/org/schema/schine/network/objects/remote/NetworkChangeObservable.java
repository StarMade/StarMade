package org.schema.schine.network.objects.remote;

public abstract interface NetworkChangeObservable
{
  public abstract boolean hasChanged();
  
  public abstract void setObserver(NetworkChangeObserver paramNetworkChangeObserver);
  
  public abstract void setChanged(boolean paramBoolean);
  
  public abstract boolean keepChanged();
  
  public abstract boolean initialSynchUpdateOnly();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.objects.remote.NetworkChangeObservable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */