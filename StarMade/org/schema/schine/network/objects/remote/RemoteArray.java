/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.schine.network.objects.NetworkObject;
/*  6:   */
/*  9:   */public abstract class RemoteArray
/* 10:   */  extends RemoteField
/* 11:   */  implements NetworkChangeObserver, StreamableArray
/* 12:   */{
/* 13:   */  public RemoteArray(RemoteField[] paramArrayOfRemoteField, NetworkObject paramNetworkObject)
/* 14:   */  {
/* 15:15 */    super(paramArrayOfRemoteField, paramNetworkObject);
/* 16:16 */    init(paramArrayOfRemoteField);
/* 17:   */  }
/* 18:   */  
/* 19:19 */  public RemoteArray(RemoteField[] paramArrayOfRemoteField, boolean paramBoolean) { super(paramArrayOfRemoteField, paramBoolean);
/* 20:20 */    init(paramArrayOfRemoteField);
/* 21:   */  }
/* 22:   */  
/* 23:   */  protected void addObservers()
/* 24:   */  {
/* 25:25 */    for (int i = 0; i < ((RemoteField[])get()).length; i++)
/* 26:26 */      ((RemoteField[])get())[i].observer = this;
/* 27:   */  }
/* 28:   */  
/* 29:   */  public int arrayLength() {
/* 30:30 */    return ((RemoteField[])get()).length;
/* 31:   */  }
/* 32:   */  
/* 38:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 39:   */  {
/* 40:40 */    for (int i = 0; i < ((RemoteField[])get()).length; i++) {
/* 41:41 */      ((RemoteField[])get())[i].fromByteStream(paramDataInputStream, paramInt);
/* 42:42 */      set(i, (Comparable)get(i).get());
/* 43:   */    }
/* 44:   */  }
/* 45:   */  
/* 47:47 */  public RemoteField get(int paramInt) { return ((RemoteField[])super.get())[paramInt]; }
/* 48:   */  
/* 49:   */  protected abstract void init(RemoteField[] paramArrayOfRemoteField);
/* 50:   */  
/* 51:   */  public abstract void set(int paramInt, Comparable paramComparable);
/* 52:   */  
/* 53:   */  public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 54:54 */    for (int i = 0; i < ((RemoteField[])get()).length; i++) {
/* 55:55 */      ((RemoteField[])get())[i].toByteStream(paramDataOutputStream);
/* 56:   */    }
/* 57:57 */    return byteLength();
/* 58:   */  }
/* 59:   */  
/* 63:   */  public void update(Streamable paramStreamable)
/* 64:   */  {
/* 65:65 */    setChanged(true);
/* 66:   */    
/* 67:67 */    paramStreamable.setChanged(false);
/* 68:68 */    if (this.observer != null) {
/* 69:69 */      this.observer.update(this);
/* 70:   */    }
/* 71:   */  }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */