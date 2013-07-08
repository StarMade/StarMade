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
/* 11:   */public class RemoteInventoryBuffer extends RemoteBuffer
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
/* 43:   */  public RemoteInventoryBuffer(mh parammh, NetworkObject paramNetworkObject)
/* 44:   */  {
/* 45:35 */    super(RemoteInventory.class, paramNetworkObject);
/* 46:36 */    this.holder = parammh;
/* 47:   */  }
/* 48:   */  
/* 52:   */  public void clearReceiveBuffer()
/* 53:   */  {
/* 54:44 */    getReceiveBuffer().clear();
/* 55:   */  }
/* 56:   */  
/* 57:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 58:   */  {
/* 59:49 */    int i = ByteUtil.a(paramDataInputStream);
/* 60:   */    
/* 61:51 */    for (int j = 0; j < i; j++) {
/* 62:   */      RemoteInventory localRemoteInventory;
/* 63:53 */      (localRemoteInventory = (RemoteInventory)staticConstructor.newInstance(new Object[] { null, this.holder, Boolean.valueOf(false), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 64:54 */      getReceiveBuffer().add(localRemoteInventory);
/* 65:   */    }
/* 66:   */  }
/* 67:   */  
/* 69:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 70:   */  {
/* 71:61 */    int i = 0;
/* 72:62 */    synchronized ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get())
/* 73:   */    {
/* 74:64 */      paramDataOutputStream.writeInt(((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).size());
/* 75:65 */      i += 4;
/* 76:   */      
/* 78:68 */      for (RemoteInventory localRemoteInventory : (it.unimi.dsi.fastutil.objects.ObjectArrayList)get()) {
/* 79:69 */        i += localRemoteInventory.toByteStream(paramDataOutputStream);
/* 80:   */      }
/* 81:   */      
/* 82:72 */      ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).clear();
/* 83:   */    }
/* 84:   */    
/* 85:75 */    return i;
/* 86:   */  }
/* 87:   */  
/* 88:   */  public void cacheConstructor() {}
/* 89:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventoryBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */