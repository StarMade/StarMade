/*    */ package org.schema.schine.network;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import org.schema.common.util.ByteUtil;
/*    */ 
/*    */ public class Header
/*    */ {
/*    */   public static final byte packetByte = 42;
/*    */   public static final byte pingByte = 23;
/*    */   public static final byte testByte = 100;
/*    */   public static final byte logoutByte = 65;
/*    */   private byte commandId;
/*    */   public short packetId;
/*    */   byte commandType;
/*    */   private byte type;
/*    */   public static final byte TYPE_PARAMETRIZED_COMMAND = 111;
/*    */   public static final byte TYPE_STREAM_COMMAND = 123;
/*    */ 
/*    */   public Header()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Header(byte paramByte1, short paramShort, byte paramByte2)
/*    */   {
/* 26 */     this.commandId = paramByte1;
/* 27 */     this.type = paramByte2;
/* 28 */     this.packetId = paramShort;
/*    */   }
/*    */   public Header(Class paramClass, int paramInt1, int paramInt2, short paramShort, byte paramByte) {
/* 31 */     this(NetUtil.commands.getByClass(paramClass).getId(), paramShort, paramByte);
/*    */   }
/*    */ 
/*    */   public byte getCommandId()
/*    */   {
/* 37 */     return this.commandId;
/*    */   }
/*    */ 
/*    */   public byte getType()
/*    */   {
/* 44 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void read(DataInputStream paramDataInputStream)
/*    */   {
/* 49 */     this.packetId = paramDataInputStream.readShort();
/* 50 */     this.commandId = paramDataInputStream.readByte();
/* 51 */     this.type = paramDataInputStream.readByte();
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 55 */     return "\n||commandId: " + getCommandId() + "; \n||type: " + getType() + "; \n||packetId: #" + this.packetId;
/*    */   }
/*    */ 
/*    */   public void write(DataOutputStream paramDataOutputStream)
/*    */   {
/* 61 */     paramDataOutputStream.write(42);
/* 62 */     paramDataOutputStream.writeShort(this.packetId);
/* 63 */     paramDataOutputStream.writeByte(getCommandId());
/* 64 */     paramDataOutputStream.writeByte(getType());
/*    */   }
/*    */ 
/*    */   public void writeToArray(byte[] paramArrayOfByte)
/*    */   {
/* 69 */     assert (paramArrayOfByte.length >= 5);
/* 70 */     paramArrayOfByte[0] = 42;
/* 71 */     ByteUtil.a(this.packetId, paramArrayOfByte, 1);
/* 72 */     paramArrayOfByte[3] = getCommandId();
/* 73 */     paramArrayOfByte[4] = getType();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.Header
 * JD-Core Version:    0.6.2
 */