/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import javax.vecmath.Vector3f;
/*  6:   */import me;
/*  7:   */import org.schema.schine.network.objects.NetworkObject;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  9:   */
/* 16:   */public class RemoteItem
/* 17:   */  extends RemoteField
/* 18:   */{
/* 19:   */  private boolean add;
/* 20:   */  
/* 21:   */  public RemoteItem(me paramme, Boolean paramBoolean, NetworkObject paramNetworkObject)
/* 22:   */  {
/* 23:23 */    super(paramme, paramNetworkObject);
/* 24:24 */    setAdd(paramBoolean.booleanValue());
/* 25:   */  }
/* 26:   */  
/* 27:27 */  public RemoteItem(me paramme, Boolean paramBoolean, boolean paramBoolean1) { super(paramme, paramBoolean1);
/* 28:28 */    setAdd(paramBoolean.booleanValue());
/* 29:   */  }
/* 30:   */  
/* 31:   */  public int byteLength()
/* 32:   */  {
/* 33:33 */    return 22;
/* 34:   */  }
/* 35:   */  
/* 39:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 40:   */  {
/* 41:41 */    paramInt = paramDataInputStream.readInt();
/* 42:42 */    setAdd(paramDataInputStream.readBoolean());
/* 43:   */    
/* 44:44 */    if (isAdd()) {
/* 45:45 */      short s = paramDataInputStream.readShort();
/* 46:   */      
/* 49:49 */      int i = paramDataInputStream.readInt();
/* 50:50 */      float f1 = paramDataInputStream.readFloat();
/* 51:51 */      float f2 = paramDataInputStream.readFloat();
/* 52:52 */      paramDataInputStream = paramDataInputStream.readFloat();
/* 53:53 */      ((me)get()).a(paramInt, s, i, new Vector3f(f1, f2, paramDataInputStream));
/* 54:54 */      return; }
/* 55:55 */    ((me)get()).a(paramInt, (short)-1, 0, null);
/* 56:   */  }
/* 57:   */  
/* 62:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 63:   */  {
/* 64:64 */    paramDataOutputStream.writeInt(((me)get()).b());
/* 65:   */    
/* 66:66 */    paramDataOutputStream.writeBoolean(isAdd());
/* 67:67 */    int i = 5;
/* 68:68 */    if (isAdd()) {
/* 69:69 */      paramDataOutputStream.writeShort(((me)get()).a());
/* 70:70 */      paramDataOutputStream.writeInt(((me)get()).a());
/* 71:71 */      paramDataOutputStream.writeFloat(((me)get()).a().x);
/* 72:72 */      paramDataOutputStream.writeFloat(((me)get()).a().y);
/* 73:73 */      paramDataOutputStream.writeFloat(((me)get()).a().z);
/* 74:74 */      i += 18;
/* 75:   */    }
/* 76:   */    
/* 80:80 */    return i;
/* 81:   */  }
/* 82:   */  
/* 85:   */  public boolean isAdd()
/* 86:   */  {
/* 87:87 */    return this.add;
/* 88:   */  }
/* 89:   */  
/* 92:   */  public void setAdd(boolean paramBoolean)
/* 93:   */  {
/* 94:94 */    this.add = paramBoolean;
/* 95:   */  }
/* 96:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteItem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */