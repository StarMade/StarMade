/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import javax.vecmath.Vector4f;
/*  4:   */import org.schema.schine.network.objects.NetworkObject;
/*  5:   */
/*  8:   */public class RemoteVector4f
/*  9:   */  extends RemoteFloatPrimitiveArray
/* 10:   */{
/* 11:11 */  public RemoteVector4f(NetworkObject paramNetworkObject) { super(4, paramNetworkObject); }
/* 12:   */  
/* 13:   */  public RemoteVector4f(Vector4f paramVector4f, NetworkObject paramNetworkObject) {
/* 14:14 */    super(4, paramNetworkObject);
/* 15:15 */    set(paramVector4f);
/* 16:   */  }
/* 17:   */  
/* 19:19 */  public RemoteVector4f(boolean paramBoolean) { super(4, paramBoolean); }
/* 20:   */  
/* 21:   */  public RemoteVector4f(Vector4f paramVector4f, boolean paramBoolean) {
/* 22:22 */    super(4, paramBoolean);
/* 23:23 */    set(paramVector4f);
/* 24:   */  }
/* 25:   */  
/* 28:28 */  public Vector4f getVector() { return getVector(new Vector4f()); }
/* 29:   */  
/* 30:   */  public Vector4f getVector(Vector4f paramVector4f) {
/* 31:31 */    paramVector4f.set(super.getFloatArray()[0], super.getFloatArray()[1], super.getFloatArray()[2], super.getFloatArray()[3]);
/* 32:32 */    return paramVector4f;
/* 33:   */  }
/* 34:   */  
/* 35:35 */  public void set(Vector4f paramVector4f) { super.set(0, paramVector4f.x);
/* 36:36 */    super.set(1, paramVector4f.y);
/* 37:37 */    super.set(2, paramVector4f.z);
/* 38:38 */    super.set(3, paramVector4f.w);
/* 39:   */  }
/* 40:   */  
/* 41:   */  public String toString() {
/* 42:42 */    return "(r" + getVector() + ")";
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteVector4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */