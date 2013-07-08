/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import org.schema.schine.network.objects.NetworkObject;
/*  4:   */import q;
/*  5:   */
/*  7:   */public class RemoteVector3i
/*  8:   */  extends RemoteIntArray
/*  9:   */{
/* 10:10 */  public RemoteVector3i(NetworkObject paramNetworkObject) { super(3, paramNetworkObject); }
/* 11:   */  
/* 12:   */  public RemoteVector3i(q paramq, NetworkObject paramNetworkObject) {
/* 13:13 */    super(3, paramNetworkObject);
/* 14:14 */    set(paramq);
/* 15:   */  }
/* 16:   */  
/* 17:17 */  public RemoteVector3i(boolean paramBoolean) { super(3, paramBoolean); }
/* 18:   */  
/* 19:   */  public RemoteVector3i(q paramq, boolean paramBoolean) {
/* 20:20 */    super(3, paramBoolean);
/* 21:21 */    set(paramq);
/* 22:   */  }
/* 23:   */  
/* 25:25 */  public q getVector() { return getVector(new q()); }
/* 26:   */  
/* 27:   */  public q getVector(q paramq) {
/* 28:28 */    paramq.b(super.getIntArray()[0], super.getIntArray()[1], super.getIntArray()[2]);
/* 29:29 */    return paramq;
/* 30:   */  }
/* 31:   */  
/* 32:32 */  public void set(q paramq) { super.set(0, paramq.a);
/* 33:33 */    super.set(1, paramq.b);
/* 34:34 */    super.set(2, paramq.c);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public String toString() {
/* 38:38 */    return "(r" + getVector() + ")";
/* 39:   */  }
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector3i
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */