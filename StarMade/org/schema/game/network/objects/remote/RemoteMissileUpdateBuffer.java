/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*  4:   */import java.io.DataInputStream;
/*  5:   */import java.io.DataOutputStream;
/*  6:   */import lw;
/*  7:   */import org.schema.common.util.ByteUtil;
/*  8:   */import org.schema.schine.network.objects.NetworkObject;
/*  9:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 10:   */
/* 16:   */public class RemoteMissileUpdateBuffer
/* 17:   */  extends RemoteBuffer
/* 18:   */{
/* 19:   */  public RemoteMissileUpdateBuffer(NetworkObject paramNetworkObject)
/* 20:   */  {
/* 21:21 */    super(RemoteMissileUpdate.class, paramNetworkObject);
/* 22:   */  }
/* 23:   */  
/* 32:   */  public void cacheConstructor() {}
/* 33:   */  
/* 42:   */  public void clearReceiveBuffer()
/* 43:   */  {
/* 44:44 */    getReceiveBuffer().clear();
/* 45:   */  }
/* 46:   */  
/* 47:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 48:   */  {
/* 49:49 */    paramInt = ByteUtil.a(paramDataInputStream);
/* 50:   */    
/* 51:51 */    for (int i = 0; i < paramInt; i++)
/* 52:   */    {
/* 53:53 */      Object localObject = lw.a(paramDataInputStream);
/* 54:54 */      localObject = new RemoteMissileUpdate((lw)localObject, this.onServer);
/* 55:55 */      getReceiveBuffer().add(localObject);
/* 56:   */    }
/* 57:   */  }
/* 58:   */  
/* 63:   */  public boolean add(RemoteMissileUpdate paramRemoteMissileUpdate)
/* 64:   */  {
/* 65:65 */    return super.add(paramRemoteMissileUpdate);
/* 66:   */  }
/* 67:   */  
/* 68:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 69:   */  {
/* 70:70 */    int i = 0;
/* 71:71 */    synchronized ((ObjectArrayList)get())
/* 72:   */    {
/* 73:73 */      paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 74:74 */      i += 4;
/* 75:   */      
/* 77:77 */      for (RemoteMissileUpdate localRemoteMissileUpdate : (ObjectArrayList)get()) {
/* 78:78 */        i += localRemoteMissileUpdate.toByteStream(paramDataOutputStream);
/* 79:   */      }
/* 80:   */      
/* 81:81 */      ((ObjectArrayList)get()).clear();
/* 82:   */    }
/* 83:   */    
/* 84:84 */    return i;
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */