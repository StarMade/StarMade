/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import org.schema.schine.network.objects.NetworkObject;
/*  4:   */import s;
/*  5:   */
/*  7:   */public class RemoteVector4i
/*  8:   */  extends RemoteIntArray
/*  9:   */{
/* 10:10 */  public RemoteVector4i(NetworkObject paramNetworkObject) { super(4, paramNetworkObject); }
/* 11:   */  
/* 12:   */  public RemoteVector4i(s params, NetworkObject paramNetworkObject) {
/* 13:13 */    super(4, paramNetworkObject);
/* 14:14 */    set(params);
/* 15:   */  }
/* 16:   */  
/* 18:18 */  public RemoteVector4i(boolean paramBoolean) { super(4, paramBoolean); }
/* 19:   */  
/* 20:   */  public RemoteVector4i(s params, boolean paramBoolean) {
/* 21:21 */    super(4, paramBoolean);
/* 22:22 */    set(params);
/* 23:   */  }
/* 24:   */  
/* 27:27 */  public s getVector() { return getVector(new s()); }
/* 28:   */  
/* 29:   */  public s getVector(s params) {
/* 30:30 */    params.a(super.getIntArray()[0], super.getIntArray()[1], super.getIntArray()[2], super.getIntArray()[3]);
/* 31:31 */    return params;
/* 32:   */  }
/* 33:   */  
/* 34:34 */  public void set(s params) { super.set(0, params.a);
/* 35:35 */    super.set(1, params.b);
/* 36:36 */    super.set(2, params.c);
/* 37:37 */    super.set(3, params.d);
/* 38:   */  }
/* 39:   */  
/* 40:   */  public String toString() {
/* 41:41 */    return "(r" + getVector() + ")";
/* 42:   */  }
/* 43:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector4i
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */