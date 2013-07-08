/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*  4:   */import java.io.DataInputStream;
/*  5:   */import java.io.DataOutputStream;
/*  6:   */import java.io.PrintStream;
/*  7:   */import java.lang.reflect.Constructor;
/*  8:   */import ka;
/*  9:   */import kc;
/* 10:   */import org.schema.schine.network.objects.NetworkObject;
/* 11:   */import org.schema.schine.network.objects.remote.RemoteBuffer;
/* 12:   */
/* 14:   */public class RemoteControlStructureBuffer
/* 15:   */  extends RemoteBuffer
/* 16:   */{
/* 17:   */  private kc segmentProvider;
/* 18:   */  private Constructor constructor;
/* 19:   */  private static Constructor staticConstructor;
/* 20:   */  
/* 21:   */  public RemoteControlStructureBuffer(kc paramkc, NetworkObject paramNetworkObject)
/* 22:   */  {
/* 23:23 */    super(RemoteControlStructure.class, paramNetworkObject);
/* 24:24 */    this.segmentProvider = paramkc;
/* 25:   */  }
/* 26:   */  
/* 27:27 */  public RemoteControlStructureBuffer(kc paramkc, boolean paramBoolean) { super(RemoteControlStructure.class, paramBoolean);
/* 28:28 */    this.segmentProvider = paramkc;
/* 29:   */  }
/* 30:   */  
/* 31:   */  public void cacheConstructor()
/* 32:   */  {
/* 33:   */    try {
/* 34:34 */      if (staticConstructor == null) {
/* 35:35 */        staticConstructor = RemoteControlStructure.class.getConstructor(new Class[] { kc.class, Boolean.TYPE });
/* 36:   */      }
/* 37:37 */      this.constructor = staticConstructor; return;
/* 38:   */    } catch (SecurityException localSecurityException) {
/* 39:39 */      System.err.println("CLASS " + this.clazz);
/* 40:40 */      localSecurityException.printStackTrace();
/* 41:   */      
/* 42:42 */      if (!$assertionsDisabled) throw new AssertionError();
/* 43:   */      return;
/* 44:44 */    } catch (NoSuchMethodException localNoSuchMethodException) { System.err.println("CLASS " + this.clazz);
/* 45:45 */      localNoSuchMethodException.printStackTrace();
/* 46:46 */      if (!$assertionsDisabled) throw new AssertionError();
/* 47:   */    }
/* 48:   */  }
/* 49:   */  
/* 50:   */  public void clearReceiveBuffer() {
/* 51:51 */    getReceiveBuffer().clear();
/* 52:   */  }
/* 53:   */  
/* 54:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 55:   */  {
/* 56:56 */    int i = paramDataInputStream.readInt();
/* 57:   */    
/* 58:58 */    for (int j = 0; j < i; j++)
/* 59:   */    {
/* 60:60 */      ((RemoteControlStructure)this.constructor.newInstance(new Object[] { this.segmentProvider, Boolean.valueOf(this.onServer) })).fromByteStream(paramDataInputStream, paramInt);
/* 61:   */    }
/* 62:   */  }
/* 63:   */  
/* 68:   */  public boolean add(RemoteControlStructure paramRemoteControlStructure)
/* 69:   */  {
/* 70:70 */    return super.add(paramRemoteControlStructure);
/* 71:   */  }
/* 72:   */  
/* 73:   */  public int toByteStream(DataOutputStream paramDataOutputStream) {
/* 74:74 */    int i = 0;
/* 75:   */    
/* 78:   */    ka localka;
/* 79:   */    
/* 81:81 */    if ((localka = this.segmentProvider.a()) != null)
/* 82:   */    {
/* 83:83 */      paramDataOutputStream.writeInt(((ObjectArrayList)get()).size());
/* 84:84 */      i += 4;
/* 85:85 */      for (RemoteControlStructure localRemoteControlStructure : (ObjectArrayList)get()) {
/* 86:86 */        i += localRemoteControlStructure.toByteStream(paramDataOutputStream, localka);
/* 87:   */      }
/* 88:   */    } else {
/* 89:89 */      paramDataOutputStream.writeInt(0);
/* 90:   */    }
/* 91:91 */    ((ObjectArrayList)get()).clear();
/* 92:   */    
/* 93:93 */    return i;
/* 94:   */  }
/* 95:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteControlStructureBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */