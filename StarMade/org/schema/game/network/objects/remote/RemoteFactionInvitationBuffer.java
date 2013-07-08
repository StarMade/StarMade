/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.lang.reflect.Constructor;
/*  5:   */import lR;
/*  6:   */import org.schema.common.util.ByteUtil;
/*  7:   */import org.schema.schine.network.objects.NetworkObject;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/*  9:   */
/* 10:   */public class RemoteFactionInvitationBuffer extends RemoteBuffer
/* 11:   */{
/* 12:   */  private static Constructor staticConstructor;
/* 13:   */  
/* 14:   */  static
/* 15:   */  {
/* 16:   */    try
/* 17:   */    {
/* 18:18 */      staticConstructor = RemoteFactionInvitation.class.getConstructor(new Class[] { lR.class, Boolean.TYPE }); return;
/* 19:19 */    } catch (SecurityException localSecurityException) { 
/* 20:   */      
/* 26:26 */        localSecurityException;
/* 27:   */      
/* 29:22 */      if (!$assertionsDisabled) throw new AssertionError();
/* 30:   */      return; } catch (NoSuchMethodException localNoSuchMethodException) { 
/* 31:   */      
/* 33:26 */        localNoSuchMethodException;
/* 34:   */      
/* 35:25 */      if (!$assertionsDisabled) throw new AssertionError();
/* 36:   */    }
/* 37:   */  }
/* 38:   */  
/* 39:   */  public RemoteFactionInvitationBuffer(NetworkObject paramNetworkObject) {
/* 40:30 */    super(RemoteFactionInvitation.class, paramNetworkObject);
/* 41:   */  }
/* 42:   */  
/* 46:   */  public void clearReceiveBuffer()
/* 47:   */  {
/* 48:38 */    getReceiveBuffer().clear();
/* 49:   */  }
/* 50:   */  
/* 51:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 52:   */  {
/* 53:43 */    int i = ByteUtil.a(paramDataInputStream);
/* 54:   */    
/* 55:45 */    for (int j = 0; j < i; j++) {
/* 56:   */      RemoteFactionInvitation localRemoteFactionInvitation;
/* 57:47 */      (localRemoteFactionInvitation = (RemoteFactionInvitation)staticConstructor.newInstance(new Object[] { new lR(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 58:48 */      getReceiveBuffer().add(localRemoteFactionInvitation);
/* 59:   */    }
/* 60:   */  }
/* 61:   */  
/* 63:   */  public int toByteStream(java.io.DataOutputStream paramDataOutputStream)
/* 64:   */  {
/* 65:55 */    int i = 0;
/* 66:56 */    synchronized ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get())
/* 67:   */    {
/* 68:58 */      paramDataOutputStream.writeInt(((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).size());
/* 69:59 */      i += 4;
/* 70:   */      
/* 72:62 */      for (RemoteFactionInvitation localRemoteFactionInvitation : (it.unimi.dsi.fastutil.objects.ObjectArrayList)get()) {
/* 73:63 */        i += localRemoteFactionInvitation.toByteStream(paramDataOutputStream);
/* 74:   */      }
/* 75:   */      
/* 76:66 */      ((it.unimi.dsi.fastutil.objects.ObjectArrayList)get()).clear();
/* 77:   */    }
/* 78:   */    
/* 79:69 */    return i;
/* 80:   */  }
/* 81:   */  
/* 82:   */  public void cacheConstructor() {}
/* 83:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFactionInvitationBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */