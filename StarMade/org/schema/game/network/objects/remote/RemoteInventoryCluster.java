/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import java.lang.reflect.Constructor;
/*  6:   */import mf;
/*  7:   */import org.schema.common.util.ByteUtil;
/*  8:   */import org.schema.schine.network.objects.NetworkObject;
/*  9:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 10:   */
/* 11:   */public class RemoteInventoryCluster extends RemoteBuffer
/* 12:   */{
/* 13:   */  private mh holder;
/* 14:   */  private static Constructor staticConstructor;
/* 15:   */  
/* 16:   */  static
/* 17:   */  {
/* 18:   */    try
/* 19:   */    {
/* 20:20 */      if (staticConstructor == null)
/* 21:21 */        staticConstructor = RemoteInventory.class.getConstructor(new Class[] { mf.class, mh.class, Boolean.TYPE, Boolean.TYPE });
/* 22:   */      return;
/* 23:23 */    } catch (SecurityException localSecurityException) { 
/* 24:   */      
/* 30:30 */        localSecurityException;
/* 31:   */      
/* 33:26 */      if (!$assertionsDisabled) throw new AssertionError();
/* 34:   */      return; } catch (NoSuchMethodException localNoSuchMethodException) { 
/* 35:   */      
/* 37:30 */        localNoSuchMethodException;
/* 38:   */      
/* 39:29 */      if (!$assertionsDisabled) throw new AssertionError();
/* 40:   */    }
/* 41:   */  }
/* 42:   */  
/* 43:   */  public RemoteInventoryCluster(mh parammh, NetworkObject paramNetworkObject)
/* 44:   */  {
/* 45:35 */    super(RemoteInventory.class, paramNetworkObject);
/* 46:36 */    this.holder = parammh;
/* 47:   */  }
/* 48:   */  
/* 53:   */  public void clearReceiveBuffer()
/* 54:   */  {
/* 55:45 */    getReceiveBuffer().clear();
/* 56:   */  }
/* 57:   */  
/* 58:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 59:   */  {
/* 60:50 */    int i = ByteUtil.a(paramDataInputStream);
/* 61:   */    
/* 62:52 */    for (int j = 0; j < i; j++) {
/* 63:   */      RemoteInventory localRemoteInventory;
/* 64:54 */      (localRemoteInventory = (RemoteInventory)staticConstructor.newInstance(new Object[] { null, this.holder, Boolean.valueOf(false), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 65:55 */      getReceiveBuffer().add(localRemoteInventory);
/* 66:   */    }
/* 67:   */  }
/* 68:   */  
/* 70:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 71:   */  {
/* 72:62 */    int i = 0;
/* 73:63 */    synchronized ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get())
/* 74:   */    {
/* 75:65 */      paramDataOutputStream.writeInt(((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).size());
/* 76:66 */      i += 4;
/* 77:   */      
/* 79:69 */      for (RemoteInventory localRemoteInventory : (it.unimi.dsi.fastutil.objects.ObjectArrayList)get()) {
/* 80:70 */        i += localRemoteInventory.toByteStream(paramDataOutputStream);
/* 81:   */      }
/* 82:   */      
/* 83:73 */      ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).clear();
/* 84:   */    }
/* 85:   */    
/* 86:76 */    return i;
/* 87:   */  }
/* 88:   */  
/* 89:   */  public void cacheConstructor() {}
/* 90:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventoryCluster
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */