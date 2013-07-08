/*   1:    */package org.schema.schine.network.objects.remote;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   4:    */import java.io.DataInputStream;
/*   5:    */import java.io.DataOutputStream;
/*   6:    */import org.schema.schine.network.objects.NetworkObject;
/*   7:    */import org.schema.schine.network.objects.remote.pool.ArrayBufferPool;
/*   8:    */
/*  10:    */public class RemoteArrayBuffer
/*  11:    */  extends RemoteBuffer
/*  12:    */{
/*  13:    */  private ArrayBufferPool pool;
/*  14:    */  private final int arraySize;
/*  15:    */  
/*  16:    */  public RemoteArrayBuffer(int paramInt, Class paramClass, NetworkObject paramNetworkObject)
/*  17:    */  {
/*  18: 18 */    super(paramClass, paramNetworkObject);
/*  19:    */    
/*  20: 20 */    this.arraySize = paramInt;
/*  21: 21 */    cacheConstructor();
/*  22:    */  }
/*  23:    */  
/*  24: 24 */  public RemoteArrayBuffer(int paramInt, Class paramClass, boolean paramBoolean) { super(paramClass, paramBoolean);
/*  25:    */    
/*  26: 26 */    this.arraySize = paramInt;
/*  27: 27 */    cacheConstructor();
/*  28:    */  }
/*  29:    */  
/*  33:    */  public boolean add(RemoteArray paramRemoteArray)
/*  34:    */  {
/*  35: 35 */    assert (((RemoteField[])paramRemoteArray.get()).length == this.arraySize) : ("Invalid Array Size: " + ((RemoteField[])paramRemoteArray.get()).length + " != " + this.arraySize);
/*  36: 36 */    return super.add(paramRemoteArray);
/*  37:    */  }
/*  38:    */  
/*  43:    */  public int byteLength()
/*  44:    */  {
/*  45: 45 */    return 4;
/*  46:    */  }
/*  47:    */  
/*  48:    */  public void cacheConstructor()
/*  49:    */  {
/*  50: 50 */    this.pool = ArrayBufferPool.get(this.clazz, Integer.valueOf(this.arraySize));
/*  51:    */  }
/*  52:    */  
/*  53:    */  public void clearReceiveBuffer() {
/*  54: 54 */    for (int i = 0; i < getReceiveBuffer().size(); i++) {
/*  55: 55 */      this.pool.release((StreamableArray)getReceiveBuffer().get(i));
/*  56:    */    }
/*  57: 57 */    getReceiveBuffer().clear();
/*  58:    */  }
/*  59:    */  
/*  60:    */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt) {
/*  61: 61 */    int i = paramDataInputStream.readInt();
/*  62:    */    
/*  65: 65 */    for (int j = 0; j < i; j++) {
/*  66: 66 */      RemoteArray localRemoteArray = (RemoteArray)this.pool.get(this.onServer);
/*  67: 67 */      assert (localRemoteArray.arrayLength() == this.arraySize) : (localRemoteArray.byteLength() + " / " + this.arraySize);
/*  68: 68 */      localRemoteArray.fromByteStream(paramDataInputStream, paramInt);
/*  69: 69 */      this.receiveBuffer.add(localRemoteArray);
/*  70:    */    }
/*  71:    */  }
/*  72:    */  
/*  78:    */  public int getArraySize()
/*  79:    */  {
/*  80: 80 */    return this.arraySize;
/*  81:    */  }
/*  82:    */  
/*  86:    */  public int toByteStream(DataOutputStream paramDataOutputStream)
/*  87:    */  {
/*  88: 88 */    int i = Math.min(8, ((ObjectArrayList)get()).size());
/*  89: 89 */    paramDataOutputStream.writeInt(i);
/*  90:    */    
/*  91: 91 */    int j = 0;
/*  92: 92 */    if (!((ObjectArrayList)get()).isEmpty())
/*  93:    */    {
/*  95: 95 */      for (int k = 0; k < i; k++) {
/*  96: 96 */        RemoteField localRemoteField = (RemoteField)((ObjectArrayList)get()).remove(0);
/*  97:    */        
/*  98: 98 */        j += localRemoteField.toByteStream(paramDataOutputStream);
/*  99: 99 */        localRemoteField.setChanged(false);
/* 100:    */      }
/* 101:    */    }
/* 102:    */    
/* 103:103 */    this.keepChanged = (!((ObjectArrayList)get()).isEmpty());
/* 104:    */    
/* 106:106 */    return j + 1;
/* 107:    */  }
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteArrayBuffer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */