/*  1:   */package org.schema.game.network.objects.remote;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import lP;
/*  6:   */import mc;
/*  7:   */import org.schema.schine.network.objects.NetworkObject;
/*  8:   */import org.schema.schine.network.objects.remote.RemoteField;
/*  9:   */import q;
/* 10:   */
/* 13:   */public class RemoteFaction
/* 14:   */  extends RemoteField
/* 15:   */{
/* 16:   */  public RemoteFaction(lP paramlP, NetworkObject paramNetworkObject)
/* 17:   */  {
/* 18:18 */    super(paramlP, paramNetworkObject);
/* 19:   */  }
/* 20:   */  
/* 21:21 */  public RemoteFaction(lP paramlP, boolean paramBoolean) { super(paramlP, paramBoolean); }
/* 22:   */  
/* 24:   */  public int byteLength()
/* 25:   */  {
/* 26:26 */    return ((lP)get()).b().length() + 4 + ((lP)get()).a().length() + 4 + ((lP)get()).c().length() + 4 + 4;
/* 27:   */  }
/* 28:   */  
/* 32:   */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/* 33:   */  {
/* 34:34 */    paramInt = paramDataInputStream.readUTF();
/* 35:35 */    String str1 = paramDataInputStream.readUTF();
/* 36:36 */    String str2 = paramDataInputStream.readUTF();
/* 37:37 */    int i = paramDataInputStream.readInt();
/* 38:38 */    boolean bool1 = paramDataInputStream.readBoolean();
/* 39:39 */    boolean bool2 = paramDataInputStream.readBoolean();
/* 40:40 */    boolean bool3 = paramDataInputStream.readBoolean();
/* 41:41 */    boolean bool4 = paramDataInputStream.readBoolean();
/* 42:42 */    String str3 = paramDataInputStream.readUTF();
/* 43:43 */    int j = paramDataInputStream.readInt();
/* 44:44 */    int k = paramDataInputStream.readInt();
/* 45:45 */    int m = paramDataInputStream.readInt();
/* 46:   */    
/* 47:47 */    ((lP)get()).a().a = paramDataInputStream.readInt();
/* 48:48 */    for (int n = 0; n < 5; n++) {
/* 49:49 */      ((lP)get()).a().a()[n] = paramDataInputStream.readLong();
/* 50:50 */      ((lP)get()).a().a()[n] = paramDataInputStream.readUTF();
/* 51:   */    }
/* 52:52 */    ((lP)get()).a(paramDataInputStream);
/* 53:   */    
/* 54:54 */    ((lP)get()).a().b(j, k, m);
/* 55:55 */    ((lP)get()).d(str3);
/* 56:56 */    ((lP)get()).b(paramInt);
/* 57:57 */    ((lP)get()).a(str1);
/* 58:58 */    ((lP)get()).c(str2);
/* 59:59 */    ((lP)get()).a(i);
/* 60:60 */    ((lP)get()).b(bool2);
/* 61:61 */    ((lP)get()).a(bool1);
/* 62:62 */    ((lP)get()).c(bool3);
/* 63:63 */    ((lP)get()).d(bool4);
/* 64:   */  }
/* 65:   */  
/* 69:   */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 70:   */  {
/* 71:71 */    paramDataOutputStream.writeUTF(((lP)get()).b());
/* 72:72 */    paramDataOutputStream.writeUTF(((lP)get()).a());
/* 73:73 */    paramDataOutputStream.writeUTF(((lP)get()).c());
/* 74:74 */    paramDataOutputStream.writeInt(((lP)get()).a());
/* 75:   */    
/* 76:76 */    paramDataOutputStream.writeBoolean(((lP)get()).a());
/* 77:77 */    paramDataOutputStream.writeBoolean(((lP)get()).b());
/* 78:78 */    paramDataOutputStream.writeBoolean(((lP)get()).c());
/* 79:79 */    paramDataOutputStream.writeBoolean(((lP)get()).d());
/* 80:80 */    paramDataOutputStream.writeUTF(((lP)get()).d());
/* 81:81 */    paramDataOutputStream.writeInt(((lP)get()).a().a);
/* 82:82 */    paramDataOutputStream.writeInt(((lP)get()).a().b);
/* 83:83 */    paramDataOutputStream.writeInt(((lP)get()).a().c);
/* 84:   */    
/* 85:85 */    paramDataOutputStream.writeInt(((lP)get()).a().a);
/* 86:86 */    for (int i = 0; i < 5; i++) {
/* 87:87 */      paramDataOutputStream.writeLong(((lP)get()).a().a()[i]);
/* 88:88 */      paramDataOutputStream.writeUTF(((lP)get()).a().a()[i]);
/* 89:   */    }
/* 90:90 */    ((lP)get()).a(paramDataOutputStream);
/* 91:   */    
/* 92:92 */    return byteLength();
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteFaction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */