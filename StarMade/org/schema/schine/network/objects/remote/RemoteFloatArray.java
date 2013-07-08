/*  1:   */package org.schema.schine.network.objects.remote;
/*  2:   */
/*  3:   */import java.util.Arrays;
/*  4:   */import org.schema.schine.network.objects.NetworkObject;
/*  5:   */
/* 10:   */public class RemoteFloatArray
/* 11:   */  extends RemoteArray
/* 12:   */{
/* 13:   */  private float[] transientArray;
/* 14:   */  
/* 15:   */  public RemoteFloatArray(int paramInt, NetworkObject paramNetworkObject)
/* 16:   */  {
/* 17:17 */    super(new RemoteFloat[paramInt], paramNetworkObject);
/* 18:   */  }
/* 19:   */  
/* 20:20 */  public RemoteFloatArray(int paramInt, boolean paramBoolean) { super(new RemoteFloat[paramInt], paramBoolean); }
/* 21:   */  
/* 23:   */  public int byteLength()
/* 24:   */  {
/* 25:25 */    return ((RemoteField[])get()).length << 2;
/* 26:   */  }
/* 27:   */  
/* 30:   */  public float[] getTransientArray()
/* 31:   */  {
/* 32:32 */    return this.transientArray;
/* 33:   */  }
/* 34:   */  
/* 35:   */  protected void init(RemoteField[] paramArrayOfRemoteField)
/* 36:   */  {
/* 37:37 */    set(paramArrayOfRemoteField);
/* 38:   */  }
/* 39:   */  
/* 50:   */  public void set(int paramInt, Float paramFloat)
/* 51:   */  {
/* 52:52 */    this.transientArray[paramInt] = paramFloat.floatValue();
/* 53:53 */    ((RemoteField[])super.get())[paramInt].set(paramFloat, this.forcedClientSending);
/* 54:   */  }
/* 55:   */  
/* 59:   */  public void set(RemoteField[] paramArrayOfRemoteField)
/* 60:   */  {
/* 61:61 */    super.set(paramArrayOfRemoteField);
/* 62:62 */    for (int i = 0; i < paramArrayOfRemoteField.length; i++) {
/* 63:63 */      ((RemoteField[])get())[i] = new RemoteFloat(0.0F, this.onServer);
/* 64:   */    }
/* 65:   */    
/* 66:66 */    this.transientArray = new float[paramArrayOfRemoteField.length];
/* 67:67 */    addObservers();
/* 68:   */  }
/* 69:   */  
/* 72:   */  public void setArray(float[] paramArrayOfFloat)
/* 73:   */  {
/* 74:74 */    if (paramArrayOfFloat == null) {
/* 75:75 */      throw new NullPointerException("cannot set array Null");
/* 76:   */    }
/* 77:77 */    if (paramArrayOfFloat.length != ((RemoteField[])get()).length) {
/* 78:78 */      throw new IllegalArgumentException("Cannot change array size of remote array");
/* 79:   */    }
/* 80:   */    
/* 81:81 */    for (int i = 0; i < this.transientArray.length; i++) {
/* 82:82 */      this.transientArray[i] = paramArrayOfFloat[i];
/* 83:83 */      get(i).set(Float.valueOf(paramArrayOfFloat[i]), this.forcedClientSending);
/* 84:   */    }
/* 85:   */  }
/* 86:   */  
/* 88:   */  public String toString()
/* 89:   */  {
/* 90:90 */    return "(rfA" + Arrays.toString(this.transientArray) + ")";
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.objects.remote.RemoteFloatArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */