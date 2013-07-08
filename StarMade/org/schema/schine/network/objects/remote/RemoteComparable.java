/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import org.schema.schine.network.objects.NetworkObject;
/*  4:   */
/*  5:   */public abstract class RemoteComparable extends RemoteField implements Comparable
/*  6:   */{
/*  7:   */  public RemoteComparable(Comparable paramComparable, NetworkObject paramNetworkObject)
/*  8:   */  {
/*  9: 9 */    super(paramComparable, paramNetworkObject);
/* 10:   */  }
/* 11:   */  
/* 12:   */  public RemoteComparable(Comparable paramComparable, boolean paramBoolean)
/* 13:   */  {
/* 14:14 */    super(paramComparable, paramBoolean);
/* 15:   */  }
/* 16:   */  
/* 21:   */  public int compareTo(Comparable paramComparable)
/* 22:   */  {
/* 23:23 */    return ((Comparable)get()).compareTo(paramComparable);
/* 24:   */  }
/* 25:   */  
/* 26:   */  public void set(Comparable paramComparable)
/* 27:   */  {
/* 28:28 */    set(paramComparable, this.forcedClientSending);
/* 29:   */  }
/* 30:   */  
/* 33:   */  public void set(Comparable paramComparable, boolean paramBoolean)
/* 34:   */  {
/* 35:35 */    if ((this.onServer) || (paramBoolean))
/* 36:   */    {
/* 37:37 */      setChanged((hasChanged()) || (!paramComparable.equals(get())));
/* 38:   */    }
/* 39:   */    
/* 40:40 */    super.set(paramComparable);
/* 41:   */    
/* 42:42 */    if ((hasChanged()) && (this.observer != null))
/* 43:   */    {
/* 44:44 */      this.observer.update(this);
/* 45:   */    }
/* 46:   */  }
/* 47:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteComparable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */