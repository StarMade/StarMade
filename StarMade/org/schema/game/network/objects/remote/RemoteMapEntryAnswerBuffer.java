/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import cH;
/*  4:   */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*  5:   */import java.io.DataInputStream;
/*  6:   */import java.io.DataOutputStream;
/*  7:   */import java.lang.reflect.Constructor;
/*  8:   */import org.schema.common.util.ByteUtil;
/*  9:   */import org.schema.schine.network.objects.NetworkObject;
/* 10:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 11:   */
/* 12:   */public class RemoteMapEntryAnswerBuffer
/* 13:   */  extends RemoteBuffer
/* 14:   */{
/* 15:   */  private static Constructor staticConstructor;
/* 16:   */  
/* 17:   */  static
/* 18:   */  {
/* 19:   */    try
/* 20:   */    {
/* 21:21 */      if (staticConstructor == null)
/* 22:22 */        staticConstructor = RemoteMapEntryAnswer.class.getConstructor(new Class[] { cH.class, Boolean.TYPE });
/* 23:   */      return;
/* 24:24 */    } catch (SecurityException localSecurityException) { 
/* 25:   */      
/* 31:31 */        localSecurityException;
/* 32:   */      
/* 34:27 */      if (!$assertionsDisabled) throw new AssertionError();
/* 35:   */      return; } catch (NoSuchMethodException localNoSuchMethodException) { 
/* 36:   */      
/* 38:31 */        localNoSuchMethodException;
/* 39:   */      
/* 40:30 */      if (!$assertionsDisabled) throw new AssertionError();
/* 41:   */    }
/* 42:   */  }
/* 43:   */  
/* 44:   */  public RemoteMapEntryAnswerBuffer(NetworkObject paramNetworkObject)
/* 45:   */  {
/* 46:36 */    super(RemoteMapEntryAnswer.class, paramNetworkObject);
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
/* 63:   */      RemoteMapEntryAnswer localRemoteMapEntryAnswer;
/* 64:54 */      (localRemoteMapEntryAnswer = (RemoteMapEntryAnswer)staticConstructor.newInstance(new Object[] { new cH(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 65:55 */      getReceiveBuffer().add(localRemoteMapEntryAnswer);
/* 66:   */    }
/* 67:   */  }
/* 68:   */  
/* 70:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 71:   */  {
/* 72:62 */    int i = 0;
/* 73:63 */    synchronized ((ObjectArrayList)get())
/* 74:   */    {
/* 75:65 */      paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 76:66 */      i += 4;
/* 77:   */      
/* 79:69 */      for (RemoteMapEntryAnswer localRemoteMapEntryAnswer : (ObjectArrayList)get()) {
/* 80:70 */        i += localRemoteMapEntryAnswer.toByteStream(paramDataOutputStream);
/* 81:   */      }
/* 82:   */      
/* 83:73 */      ((ObjectArrayList)get()).clear();
/* 84:   */    }
/* 85:   */    
/* 86:76 */    return i;
/* 87:   */  }
/* 88:   */  
/* 89:   */  public void cacheConstructor() {}
/* 90:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteMapEntryAnswerBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */