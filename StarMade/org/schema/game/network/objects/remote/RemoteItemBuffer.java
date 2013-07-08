/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.lang.reflect.Constructor;
/*  5:   */import me;
/*  6:   */import org.schema.common.util.ByteUtil;
/*  7:   */import org.schema.schine.network.objects.NetworkObject;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  9:   */
/* 10:   */public class RemoteItemBuffer extends RemoteBuffer
/* 11:   */{
/* 12:   */  private Constructor constructor;
/* 13:   */  private static Constructor staticConstructor;
/* 14:   */  
/* 15:   */  static
/* 16:   */  {
/* 17:   */    try
/* 18:   */    {
/* 19:19 */      staticConstructor = RemoteItem.class.getConstructor(new Class[] { me.class, Boolean.class, Boolean.TYPE }); return;
/* 20:20 */    } catch (SecurityException localSecurityException) { 
/* 21:   */      
/* 26:26 */        localSecurityException;
/* 27:   */      
/* 28:22 */      if (!$assertionsDisabled) throw new AssertionError();
/* 29:   */      return; } catch (NoSuchMethodException localNoSuchMethodException) { 
/* 30:   */      
/* 32:26 */        localNoSuchMethodException;
/* 33:   */      
/* 34:25 */      if (!$assertionsDisabled) throw new AssertionError();
/* 35:   */    }
/* 36:   */  }
/* 37:   */  
/* 38:   */  public RemoteItemBuffer(NetworkObject paramNetworkObject)
/* 39:   */  {
/* 40:31 */    super(RemoteItem.class, paramNetworkObject);
/* 41:   */  }
/* 42:   */  
/* 43:34 */  public RemoteItemBuffer(boolean paramBoolean) { super(RemoteItem.class, paramBoolean); }
/* 44:   */  
/* 46:   */  public void cacheConstructor()
/* 47:   */  {
/* 48:39 */    this.constructor = staticConstructor;
/* 49:   */  }
/* 50:   */  
/* 51:   */  public void clearReceiveBuffer() {
/* 52:43 */    getReceiveBuffer().clear();
/* 53:   */  }
/* 54:   */  
/* 55:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 56:   */  {
/* 57:48 */    int i = ByteUtil.a(paramDataInputStream);
/* 58:   */    
/* 59:50 */    for (int j = 0; j < i; j++) {
/* 60:   */      RemoteItem localRemoteItem;
/* 61:52 */      (localRemoteItem = (RemoteItem)this.constructor.newInstance(new Object[] { new me(), Boolean.valueOf(false), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 62:53 */      getReceiveBuffer().add(localRemoteItem);
/* 63:   */    }
/* 64:   */  }
/* 65:   */  
/* 67:   */  public int toByteStream(java.io.DataOutputStream paramDataOutputStream)
/* 68:   */  {
/* 69:60 */    int i = 0;
/* 70:61 */    synchronized ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get())
/* 71:   */    {
/* 72:63 */      paramDataOutputStream.writeInt(((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).size());
/* 73:64 */      i += 4;
/* 74:   */      
/* 76:67 */      for (RemoteItem localRemoteItem : (it.unimi.dsi.fastutil.objects.ObjectArrayList)get()) {
/* 77:68 */        i += localRemoteItem.toByteStream(paramDataOutputStream);
/* 78:   */      }
/* 79:   */      
/* 80:71 */      ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).clear();
/* 81:   */    }
/* 82:   */    
/* 83:74 */    return i;
/* 84:   */  }
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteItemBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */