/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.lang.reflect.Constructor;
/*  5:   */import mi;
/*  6:   */import org.schema.common.util.ByteUtil;
/*  7:   */import org.schema.schine.network.objects.NetworkObject;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  9:   */
/* 10:   */public class RemoteInventoryMultModBuffer extends RemoteBuffer
/* 11:   */{
/* 12:   */  private static Constructor staticConstructor;
/* 13:   */  
/* 14:   */  static
/* 15:   */  {
/* 16:   */    try
/* 17:   */    {
/* 18:18 */      if (staticConstructor == null)
/* 19:19 */        staticConstructor = RemoteInventoryMultMod.class.getConstructor(new Class[] { mi.class, Boolean.TYPE });
/* 20:   */      return;
/* 21:21 */    } catch (SecurityException localSecurityException) { 
/* 22:   */      
/* 28:28 */        localSecurityException;
/* 29:   */      
/* 31:24 */      if (!$assertionsDisabled) throw new AssertionError();
/* 32:   */      return; } catch (NoSuchMethodException localNoSuchMethodException) { 
/* 33:   */      
/* 35:28 */        localNoSuchMethodException;
/* 36:   */      
/* 37:27 */      if (!$assertionsDisabled) throw new AssertionError();
/* 38:   */    }
/* 39:   */  }
/* 40:   */  
/* 41:   */  public RemoteInventoryMultModBuffer(NetworkObject paramNetworkObject)
/* 42:   */  {
/* 43:33 */    super(RemoteInventoryMultMod.class, paramNetworkObject);
/* 44:   */  }
/* 45:   */  
/* 46:36 */  public RemoteInventoryMultModBuffer(boolean paramBoolean) { super(RemoteInventoryMultMod.class, paramBoolean); }
/* 47:   */  
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
/* 63:   */      RemoteInventoryMultMod localRemoteInventoryMultMod;
/* 64:54 */      (localRemoteInventoryMultMod = (RemoteInventoryMultMod)staticConstructor.newInstance(new Object[] { new mi(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 65:55 */      getReceiveBuffer().add(localRemoteInventoryMultMod);
/* 66:   */    }
/* 67:   */  }
/* 68:   */  
/* 70:   */  public int toByteStream(java.io.DataOutputStream paramDataOutputStream)
/* 71:   */  {
/* 72:62 */    int i = 0;
/* 73:63 */    synchronized ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get())
/* 74:   */    {
/* 75:65 */      paramDataOutputStream.writeInt(((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).size());
/* 76:66 */      i += 4;
/* 77:   */      
/* 79:69 */      for (RemoteInventoryMultMod localRemoteInventoryMultMod : (it.unimi.dsi.fastutil.objects.ObjectArrayList)get()) {
/* 80:70 */        i += localRemoteInventoryMultMod.toByteStream(paramDataOutputStream);
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */