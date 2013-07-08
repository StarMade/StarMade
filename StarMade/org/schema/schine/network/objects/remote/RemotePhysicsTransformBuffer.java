/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*  4:   */import java.io.DataInputStream;
/*  5:   */import java.io.DataOutputStream;
/*  6:   */import java.io.PrintStream;
/*  7:   */import java.lang.reflect.Constructor;
/*  8:   */import org.schema.schine.network.objects.NetworkObject;
/*  9:   */import org.schema.schine.network.objects.NetworkTransformation;
/* 10:   */
/* 12:   */public class RemotePhysicsTransformBuffer
/* 13:   */  extends RemoteBuffer
/* 14:   */{
/* 15:   */  private Constructor constructor;
/* 16:   */  private static Constructor staticConstructor;
/* 17:   */  
/* 18:   */  public RemotePhysicsTransformBuffer(NetworkObject paramNetworkObject)
/* 19:   */  {
/* 20:20 */    super(RemotePhysicsTransform.class, paramNetworkObject);
/* 21:   */  }
/* 22:   */  
/* 23:   */  public void cacheConstructor()
/* 24:   */  {
/* 25:   */    try {
/* 26:26 */      if (staticConstructor == null) {
/* 27:27 */        staticConstructor = RemotePhysicsTransform.class.getConstructor(new Class[] { NetworkTransformation.class, Boolean.TYPE });
/* 28:   */      }
/* 29:29 */      this.constructor = staticConstructor; return;
/* 30:   */    } catch (SecurityException localSecurityException) {
/* 31:31 */      System.err.println("CLASS " + this.clazz);
/* 32:32 */      localSecurityException.printStackTrace();
/* 33:   */      
/* 34:34 */      if (!$assertionsDisabled) throw new AssertionError();
/* 35:   */      return;
/* 36:36 */    } catch (NoSuchMethodException localNoSuchMethodException) { System.err.println("CLASS " + this.clazz);
/* 37:37 */      localNoSuchMethodException.printStackTrace();
/* 38:38 */      if (!$assertionsDisabled) throw new AssertionError();
/* 39:   */    }
/* 40:   */  }
/* 41:   */  
/* 42:   */  public void clearReceiveBuffer() {
/* 43:43 */    getReceiveBuffer().clear();
/* 44:   */  }
/* 45:   */  
/* 46:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 47:   */  {
/* 48:48 */    int i = paramDataInputStream.readInt();
/* 49:   */    
/* 50:50 */    for (int j = 0; j < i; j++) {
/* 51:   */      RemotePhysicsTransform localRemotePhysicsTransform;
/* 52:52 */      (localRemotePhysicsTransform = (RemotePhysicsTransform)this.constructor.newInstance(new Object[] { new NetworkTransformation(), Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 53:53 */      getReceiveBuffer().add(localRemotePhysicsTransform);
/* 54:   */    }
/* 55:   */  }
/* 56:   */  
/* 58:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 59:   */  {
/* 60:60 */    int i = 0;
/* 61:   */    
/* 63:63 */    paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 64:64 */    i += 4;
/* 65:   */    
/* 67:67 */    for (RemotePhysicsTransform localRemotePhysicsTransform : (ObjectArrayList)get()) {
/* 68:68 */      i += localRemotePhysicsTransform.toByteStream(paramDataOutputStream);
/* 69:   */    }
/* 70:   */    
/* 71:71 */    ((ObjectArrayList)get()).clear();
/* 72:72 */    if (!this.onServer) {
/* 73:73 */      System.err.println("###########################----------------> SENT " + this);
/* 74:   */    }
/* 75:   */    
/* 76:76 */    return i;
/* 77:   */  }
/* 78:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemotePhysicsTransformBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */