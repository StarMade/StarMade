/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.lang.reflect.Constructor;
/*  5:   */import lW;
/*  6:   */import org.schema.common.util.ByteUtil;
/*  7:   */import org.schema.schine.network.objects.NetworkObject;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  9:   */
/* 10:   */public class RemoteFactionNewsPostBuffer extends RemoteBuffer
/* 11:   */{
/* 12:   */  private static Constructor staticConstructor;
/* 13:   */  
/* 14:   */  static
/* 15:   */  {
/* 16:   */    try
/* 17:   */    {
/* 18:18 */      if (staticConstructor == null)
/* 19:19 */        staticConstructor = RemoteFactionNewsPost.class.getConstructor(new Class[] { lW.class, Boolean.TYPE });
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
/* 41:   */  public RemoteFactionNewsPostBuffer(NetworkObject paramNetworkObject) {
/* 42:32 */    super(RemoteFactionNewsPost.class, paramNetworkObject);
/* 43:   */  }
/* 44:   */  
/* 48:   */  public void clearReceiveBuffer()
/* 49:   */  {
/* 50:40 */    getReceiveBuffer().clear();
/* 51:   */  }
/* 52:   */  
/* 53:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 54:   */  {
/* 55:45 */    int i = ByteUtil.a(paramDataInputStream);
/* 56:   */    
/* 57:47 */    for (int j = 0; j < i; j++) {
/* 58:   */      RemoteFactionNewsPost localRemoteFactionNewsPost;
/* 59:49 */      (localRemoteFactionNewsPost = (RemoteFactionNewsPost)staticConstructor.newInstance(new Object[] { new lW(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 60:50 */      getReceiveBuffer().add(localRemoteFactionNewsPost);
/* 61:   */    }
/* 62:   */  }
/* 63:   */  
/* 65:   */  public int toByteStream(java.io.DataOutputStream paramDataOutputStream)
/* 66:   */  {
/* 67:57 */    int i = 0;
/* 68:58 */    synchronized ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get())
/* 69:   */    {
/* 70:60 */      paramDataOutputStream.writeInt(((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).size());
/* 71:61 */      i += 4;
/* 72:   */      
/* 74:64 */      for (RemoteFactionNewsPost localRemoteFactionNewsPost : (it.unimi.dsi.fastutil.objects.ObjectArrayList)get()) {
/* 75:65 */        i += localRemoteFactionNewsPost.toByteStream(paramDataOutputStream);
/* 76:   */      }
/* 77:   */      
/* 78:68 */      ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).clear();
/* 79:   */    }
/* 80:   */    
/* 81:71 */    return i;
/* 82:   */  }
/* 83:   */  
/* 84:   */  public void cacheConstructor() {}
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionNewsPostBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */