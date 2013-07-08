/*  1:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  2:   */import org.schema.schine.network.objects.NetworkObject;
/*  3:   */import org.schema.schine.network.objects.remote.RemoteIntArray;
/*  4:   */import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
/*  5:   */
/*  9:   */public abstract class md
/* 10:   */  extends mf
/* 11:   */{
/* 12:   */  boolean a;
/* 13:   */  
/* 14:   */  public md(mh parammh, q paramq)
/* 15:   */  {
/* 16:16 */    super(parammh, paramq);
/* 17:   */  }
/* 18:   */  
/* 20:   */  public String toString()
/* 21:   */  {
/* 22:22 */    return "ActiveInventory: " + this.jdField_a_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.toString();
/* 23:   */  }
/* 24:   */  
/* 25:   */  public abstract void a();
/* 26:   */  
/* 27:   */  public final void b() {
/* 28:28 */    this.jdField_a_of_type_Boolean = true;
/* 29:   */  }
/* 30:   */  
/* 32:   */  public final boolean a()
/* 33:   */  {
/* 34:34 */    return this.jdField_a_of_type_Boolean;
/* 35:   */  }
/* 36:   */  
/* 37:   */  public final void c() {
/* 38:   */    RemoteIntArray localRemoteIntArray;
/* 39:39 */    (localRemoteIntArray = new RemoteIntArray(3, (NetworkObject)this.jdField_a_of_type_Mh.getInventoryNetworkObject())).set(0, this.jdField_a_of_type_Q.a);
/* 40:40 */    localRemoteIntArray.set(1, this.jdField_a_of_type_Q.b);
/* 41:41 */    localRemoteIntArray.set(2, this.jdField_a_of_type_Q.c);
/* 42:42 */    this.jdField_a_of_type_Mh.getInventoryNetworkObject().getInventoryActivateBuffer().add(localRemoteIntArray);
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     md
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */