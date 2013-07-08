/*   1:    */package org.schema.schine.network.objects.remote;
/*   2:    */
/*   3:    */import java.io.DataInputStream;
/*   4:    */import java.io.DataOutputStream;
/*   5:    */import org.schema.schine.network.objects.NetworkObject;
/*   6:    */
/*  14:    */public abstract class RemoteField
/*  15:    */  implements Streamable
/*  16:    */{
/*  17:    */  protected NetworkChangeObserver observer;
/*  18:    */  private boolean changed;
/*  19:    */  private Object value;
/*  20:    */  public final boolean onServer;
/*  21:    */  protected boolean keepChanged;
/*  22: 22 */  protected boolean forcedClientSending = false;
/*  23:    */  
/*  24:    */  public RemoteField(Object paramObject, boolean paramBoolean1, boolean paramBoolean2)
/*  25:    */  {
/*  26: 26 */    this.value = paramObject;
/*  27:    */    
/*  28: 28 */    this.onServer = paramBoolean2;
/*  29: 29 */    this.changed = paramBoolean1;
/*  30:    */  }
/*  31:    */  
/*  32:    */  public RemoteField(Object paramObject, boolean paramBoolean, NetworkObject paramNetworkObject) {
/*  33: 33 */    this(paramObject, paramBoolean, paramNetworkObject.isOnServer());
/*  34: 34 */    assert (paramNetworkObject != null);
/*  35:    */  }
/*  36:    */  
/*  37: 37 */  public RemoteField(Object paramObject, boolean paramBoolean) { this(paramObject, false, paramBoolean); }
/*  38:    */  
/*  39:    */  public RemoteField(Object paramObject, NetworkObject paramNetworkObject) {
/*  40: 40 */    this(paramObject, false, paramNetworkObject);
/*  41:    */  }
/*  42:    */  
/*  43:    */  public void forceClientUpdates() {
/*  44: 44 */    this.forcedClientSending = true;
/*  45:    */  }
/*  46:    */  
/*  48:    */  public abstract void fromByteStream(DataInputStream paramDataInputStream, int paramInt);
/*  49:    */  
/*  50:    */  public void cleanAtRelease() {}
/*  51:    */  
/*  52:    */  public Object get()
/*  53:    */  {
/*  54: 54 */    return this.value;
/*  55:    */  }
/*  56:    */  
/*  64:    */  public final boolean hasChanged()
/*  65:    */  {
/*  66: 66 */    return this.changed;
/*  67:    */  }
/*  68:    */  
/*  74:    */  public boolean keepChanged()
/*  75:    */  {
/*  76: 76 */    return this.keepChanged;
/*  77:    */  }
/*  78:    */  
/*  79: 79 */  public boolean initialSynchUpdateOnly() { return false; }
/*  80:    */  
/*  81:    */  public void set(Object paramObject)
/*  82:    */  {
/*  83: 83 */    this.value = paramObject;
/*  84:    */  }
/*  85:    */  
/*  86: 86 */  public void set(Object paramObject, boolean paramBoolean) { set(paramObject); }
/*  87:    */  
/*  94:    */  public synchronized void setObserver(NetworkChangeObserver paramNetworkChangeObserver)
/*  95:    */  {
/*  96: 96 */    this.observer = paramNetworkChangeObserver;
/*  97:    */  }
/*  98:    */  
/* 101:    */  public void setChanged(boolean paramBoolean)
/* 102:    */  {
/* 103:103 */    this.changed = paramBoolean;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public abstract int toByteStream(DataOutputStream paramDataOutputStream);
/* 107:    */  
/* 108:    */  public String toString()
/* 109:    */  {
/* 110:110 */    return this.value.toString();
/* 111:    */  }
/* 112:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteField
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */