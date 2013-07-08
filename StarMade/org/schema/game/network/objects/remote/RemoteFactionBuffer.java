/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.lang.reflect.Constructor;
/*  5:   */import lP;
/*  6:   */import org.schema.common.util.ByteUtil;
/*  7:   */import org.schema.schine.network.objects.NetworkObject;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  9:   */
/* 10:   */public class RemoteFactionBuffer extends RemoteBuffer
/* 11:   */{
/* 12:   */  private static Constructor staticConstructor;
/* 13:   */  
/* 14:   */  static
/* 15:   */  {
/* 16:   */    try
/* 17:   */    {
/* 18:18 */      if (staticConstructor == null)
/* 19:19 */        staticConstructor = RemoteFaction.class.getConstructor(new Class[] { lP.class, Boolean.TYPE });
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
/* 41:   */  public RemoteFactionBuffer(NetworkObject paramNetworkObject)
/* 42:   */  {
/* 43:33 */    super(RemoteFaction.class, paramNetworkObject);
/* 44:   */  }
/* 45:   */  
/* 49:   */  public void clearReceiveBuffer()
/* 50:   */  {
/* 51:41 */    getReceiveBuffer().clear();
/* 52:   */  }
/* 53:   */  
/* 54:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 55:   */  {
/* 56:46 */    int i = ByteUtil.a(paramDataInputStream);
/* 57:   */    
/* 58:48 */    for (int j = 0; j < i; j++) {
/* 59:   */      RemoteFaction localRemoteFaction;
/* 60:50 */      (localRemoteFaction = (RemoteFaction)staticConstructor.newInstance(new Object[] { new lP(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 61:51 */      getReceiveBuffer().add(localRemoteFaction);
/* 62:   */    }
/* 63:   */  }
/* 64:   */  
/* 66:   */  public int toByteStream(java.io.DataOutputStream paramDataOutputStream)
/* 67:   */  {
/* 68:58 */    int i = 0;
/* 69:59 */    synchronized ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get())
/* 70:   */    {
/* 71:61 */      paramDataOutputStream.writeInt(((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).size());
/* 72:62 */      i += 4;
/* 73:   */      
/* 75:65 */      for (RemoteFaction localRemoteFaction : (it.unimi.dsi.fastutil.objects.ObjectArrayList)get()) {
/* 76:66 */        i += localRemoteFaction.toByteStream(paramDataOutputStream);
/* 77:   */      }
/* 78:   */      
/* 79:69 */      ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).clear();
/* 80:   */    }
/* 81:   */    
/* 82:72 */    return i;
/* 83:   */  }
/* 84:   */  
/* 85:   */  public void cacheConstructor() {}
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */