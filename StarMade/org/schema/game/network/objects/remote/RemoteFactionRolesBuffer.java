/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.lang.reflect.Constructor;
/*  5:   */import mc;
/*  6:   */import org.schema.common.util.ByteUtil;
/*  7:   */import org.schema.schine.network.objects.NetworkObject;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  9:   */
/* 10:   */public class RemoteFactionRolesBuffer extends RemoteBuffer
/* 11:   */{
/* 12:   */  private static Constructor staticConstructor;
/* 13:   */  
/* 14:   */  static
/* 15:   */  {
/* 16:   */    try
/* 17:   */    {
/* 18:18 */      if (staticConstructor == null)
/* 19:19 */        staticConstructor = RemoteFactionRoles.class.getConstructor(new Class[] { mc.class, Boolean.TYPE });
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
/* 41:   */  public RemoteFactionRolesBuffer(boolean paramBoolean) {
/* 42:32 */    super(RemoteFactionRoles.class, paramBoolean);
/* 43:   */  }
/* 44:   */  
/* 45:   */  public RemoteFactionRolesBuffer(NetworkObject paramNetworkObject) {
/* 46:36 */    super(RemoteFactionRoles.class, paramNetworkObject);
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
/* 62:   */      RemoteFactionRoles localRemoteFactionRoles;
/* 63:53 */      (localRemoteFactionRoles = (RemoteFactionRoles)staticConstructor.newInstance(new Object[] { new mc(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 64:54 */      getReceiveBuffer().add(localRemoteFactionRoles);
/* 65:   */    }
/* 66:   */  }
/* 67:   */  
/* 69:   */  public int toByteStream(java.io.DataOutputStream paramDataOutputStream)
/* 70:   */  {
/* 71:61 */    int i = 0;
/* 72:62 */    synchronized ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get())
/* 73:   */    {
/* 74:64 */      paramDataOutputStream.writeInt(((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).size());
/* 75:65 */      i += 4;
/* 76:   */      
/* 78:68 */      for (RemoteFactionRoles localRemoteFactionRoles : (it.unimi.dsi.fastutil.objects.ObjectArrayList)get()) {
/* 79:69 */        i += localRemoteFactionRoles.toByteStream(paramDataOutputStream);
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
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionRolesBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */