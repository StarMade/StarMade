/*   1:    */package org.schema.game.network.objects.remote;
/*   2:    */
/*   3:    */import jL;
/*   4:    */import java.io.DataInputStream;
/*   5:    */import java.io.DataOutputStream;
/*   6:    */import le;
/*   7:    */import org.schema.game.common.controller.SegmentController;
/*   8:    */import org.schema.game.common.data.world.Segment;
/*   9:    */import org.schema.schine.network.objects.NetworkObject;
/*  10:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  11:    */import org.schema.schine.network.server.ServerState;
/*  12:    */import q;
/*  13:    */
/*  16:    */public class RemoteSegmentPiece
/*  17:    */  extends RemoteField
/*  18:    */{
/*  19:    */  private SegmentController segmentController;
/*  20:    */  
/*  21:    */  public RemoteSegmentPiece(le paramle, SegmentController paramSegmentController, NetworkObject paramNetworkObject)
/*  22:    */  {
/*  23: 23 */    super(paramle, paramNetworkObject);
/*  24: 24 */    this.segmentController = paramSegmentController;
/*  25:    */  }
/*  26:    */  
/*  27: 27 */  public RemoteSegmentPiece(le paramle, SegmentController paramSegmentController, boolean paramBoolean) { super(paramle, paramBoolean);
/*  28: 28 */    this.segmentController = paramSegmentController;
/*  29:    */  }
/*  30:    */  
/*  31:    */  public int byteLength() {
/*  32: 32 */    return 22;
/*  33:    */  }
/*  34:    */  
/*  38:    */  public void fromByteStream(DataInputStream paramDataInputStream, int paramInt)
/*  39:    */  {
/*  40:    */    int i;
/*  41:    */    
/*  44:    */    int j;
/*  45:    */    
/*  48: 48 */    if ((paramInt = paramDataInputStream.readByte()) == 0) {
/*  49: 49 */      paramInt = paramDataInputStream.readByte();
/*  50: 50 */      i = paramDataInputStream.readByte();
/*  51: 51 */      j = paramDataInputStream.readByte();
/*  52: 52 */    } else if (paramInt == 1) {
/*  53: 53 */      paramInt = paramDataInputStream.readShort();
/*  54: 54 */      i = paramDataInputStream.readShort();
/*  55: 55 */      j = paramDataInputStream.readShort();
/*  56:    */    } else {
/*  57: 57 */      paramInt = paramDataInputStream.readInt();
/*  58: 58 */      i = paramDataInputStream.readInt();
/*  59: 59 */      j = paramDataInputStream.readInt();
/*  60:    */    }
/*  61:    */    
/*  63: 63 */    byte[] arrayOfByte = new byte[3];
/*  64: 64 */    paramDataInputStream.readFully(arrayOfByte);
/*  65:    */    
/*  69: 69 */    if ((paramDataInputStream = this.segmentController.getSegmentBuffer().a(new q(paramInt, i, j), this.segmentController.getState() instanceof ServerState)) != null) {
/*  70: 70 */      paramDataInputStream.a(arrayOfByte);
/*  71: 71 */      set(paramDataInputStream);
/*  72:    */    }
/*  73:    */  }
/*  74:    */  
/*  76:    */  public void writeDynamicPosition(int paramInt1, int paramInt2, int paramInt3, DataOutputStream paramDataOutputStream)
/*  77:    */  {
/*  78: 78 */    if ((paramInt1 >= -128) && (paramInt2 >= -128) && (paramInt3 >= -128) && (paramInt1 <= -128) && (paramInt2 <= -128) && (paramInt3 <= 127))
/*  79:    */    {
/*  80: 80 */      paramDataOutputStream.writeByte(0);
/*  81: 81 */      paramDataOutputStream.writeByte(paramInt1);
/*  82: 82 */      paramDataOutputStream.writeByte(paramInt2);
/*  83: 83 */      paramDataOutputStream.writeByte(paramInt3);return; }
/*  84: 84 */    if ((paramInt1 >= -32768) && (paramInt2 >= -32768) && (paramInt3 >= -32768) && (paramInt1 <= -32768) && (paramInt2 <= -32768) && (paramInt3 <= 32767))
/*  85:    */    {
/*  86: 86 */      paramDataOutputStream.writeByte(1);
/*  87: 87 */      paramDataOutputStream.writeShort(paramInt1);
/*  88: 88 */      paramDataOutputStream.writeShort(paramInt2);
/*  89: 89 */      paramDataOutputStream.writeShort(paramInt3);return;
/*  90:    */    }
/*  91: 91 */    paramDataOutputStream.writeByte(2);
/*  92: 92 */    paramDataOutputStream.writeInt(paramInt1);
/*  93: 93 */    paramDataOutputStream.writeInt(paramInt2);
/*  94: 94 */    paramDataOutputStream.writeInt(paramInt3);
/*  95:    */  }
/*  96:    */  
/* 100:    */  public int toByteStream(DataOutputStream paramDataOutputStream)
/* 101:    */  {
/* 102:102 */    assert (get() != null);
/* 103:103 */    writeDynamicPosition(((le)get()).a + ((le)get()).a().a.a, ((le)get()).b + ((le)get()).a().a.b, ((le)get()).c + ((le)get()).a().a.c, paramDataOutputStream);
/* 104:    */    
/* 109:109 */    paramDataOutputStream.write(((le)get()).a());
/* 110:    */    
/* 111:111 */    return 1;
/* 112:    */  }
/* 113:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.network.objects.remote.RemoteSegmentPiece
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */