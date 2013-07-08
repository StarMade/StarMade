/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import cI;
/*  4:   */import java.io.DataInputStream;
/*  5:   */import java.io.DataOutputStream;
/*  6:   */import java.lang.reflect.Constructor;
/*  7:   */import org.schema.common.util.ByteUtil;
/*  8:   */import org.schema.schine.network.objects.NetworkObject;
/*  9:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 10:   */
/* 11:   */public class RemoteMapEntryRequestBuffer extends RemoteBuffer
/* 12:   */{
/* 13:   */  private static Constructor staticConstructor;
/* 14:   */  
/* 15:   */  static
/* 16:   */  {
/* 17:   */    try
/* 18:   */    {
/* 19:19 */      if (staticConstructor == null)
/* 20:20 */        staticConstructor = RemoteMapEntryRequest.class.getConstructor(new Class[] { cI.class, Boolean.TYPE });
/* 21:   */      return;
/* 22:22 */    } catch (SecurityException localSecurityException) { 
/* 23:   */      
/* 29:29 */        localSecurityException;
/* 30:   */      
/* 32:25 */      if (!$assertionsDisabled) throw new AssertionError();
/* 33:   */      return; } catch (NoSuchMethodException localNoSuchMethodException) { 
/* 34:   */      
/* 36:29 */        localNoSuchMethodException;
/* 37:   */      
/* 38:28 */      if (!$assertionsDisabled) throw new AssertionError();
/* 39:   */    }
/* 40:   */  }
/* 41:   */  
/* 42:   */  public RemoteMapEntryRequestBuffer(NetworkObject paramNetworkObject)
/* 43:   */  {
/* 44:34 */    super(RemoteMapEntryRequest.class, paramNetworkObject);
/* 45:   */  }
/* 46:   */  
/* 51:   */  public void clearReceiveBuffer()
/* 52:   */  {
/* 53:43 */    getReceiveBuffer().clear();
/* 54:   */  }
/* 55:   */  
/* 56:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 57:   */  {
/* 58:48 */    int i = ByteUtil.a(paramDataInputStream);
/* 59:   */    
/* 60:50 */    for (int j = 0; j < i; j++) {
/* 61:   */      RemoteMapEntryRequest localRemoteMapEntryRequest;
/* 62:52 */      (localRemoteMapEntryRequest = (RemoteMapEntryRequest)staticConstructor.newInstance(new Object[] { new cI(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 63:53 */      getReceiveBuffer().add(localRemoteMapEntryRequest);
/* 64:   */    }
/* 65:   */  }
/* 66:   */  
/* 68:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 69:   */  {
/* 70:60 */    int i = 0;
/* 71:61 */    synchronized ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get())
/* 72:   */    {
/* 73:63 */      paramDataOutputStream.writeInt(((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).size());
/* 74:64 */      i += 4;
/* 75:   */      
/* 77:67 */      for (RemoteMapEntryRequest localRemoteMapEntryRequest : (it.unimi.dsi.fastutil.objects.ObjectArrayList)get()) {
/* 78:68 */        i += localRemoteMapEntryRequest.toByteStream(paramDataOutputStream);
/* 79:   */      }
/* 80:   */      
/* 81:71 */      ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).clear();
/* 82:   */    }
/* 83:   */    
/* 84:74 */    return i;
/* 85:   */  }
/* 86:   */  
/* 87:   */  public void cacheConstructor() {}
/* 88:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMapEntryRequestBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */