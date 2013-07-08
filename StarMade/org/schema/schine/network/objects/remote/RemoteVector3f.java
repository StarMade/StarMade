/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */import org.schema.schine.network.objects.NetworkObject;
/*  5:   */
/*  7:   */public class RemoteVector3f
/*  8:   */  extends RemoteFloatPrimitiveArray
/*  9:   */{
/* 10:10 */  public RemoteVector3f(NetworkObject paramNetworkObject) { super(3, paramNetworkObject); }
/* 11:   */  
/* 12:   */  public RemoteVector3f(NetworkObject paramNetworkObject, Vector3f paramVector3f) {
/* 13:13 */    this(paramNetworkObject);
/* 14:14 */    set(paramVector3f);
/* 15:   */  }
/* 16:   */  
/* 17:17 */  public RemoteVector3f(boolean paramBoolean) { super(3, paramBoolean); }
/* 18:   */  
/* 19:   */  public RemoteVector3f(boolean paramBoolean, Vector3f paramVector3f) {
/* 20:20 */    this(paramBoolean);
/* 21:21 */    set(paramVector3f);
/* 22:   */  }
/* 23:   */  
/* 25:25 */  public Vector3f getVector() { return getVector(new Vector3f()); }
/* 26:   */  
/* 27:   */  public Vector3f getVector(Vector3f paramVector3f) {
/* 28:28 */    paramVector3f.set(super.getFloatArray()[0], super.getFloatArray()[1], super.getFloatArray()[2]);
/* 29:29 */    return paramVector3f;
/* 30:   */  }
/* 31:   */  
/* 32:32 */  public void set(Vector3f paramVector3f) { super.set(0, paramVector3f.x);
/* 33:33 */    super.set(1, paramVector3f.y);
/* 34:34 */    super.set(2, paramVector3f.z);
/* 35:   */  }
/* 36:   */  
/* 37:   */  public String toString() {
/* 38:38 */    return "(r" + getVector() + ")";
/* 39:   */  }
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */