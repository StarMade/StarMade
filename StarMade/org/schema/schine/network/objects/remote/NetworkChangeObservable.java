package org.schema.schine.network.objects.remote;

public abstract interface NetworkChangeObservable
{
  public abstract boolean hasChanged();

  public abstract void setObserver(NetworkChangeObserver paramNetworkChangeObserver);

  public abstract void setChanged(boolean paramBoolean);

  public abstract boolean keepChanged();

  public abstract boolean initialSynchUpdateOnly();
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.NetworkChangeObservable
 * JD-Core Version:    0.6.2
 */