/*  1:   */package org.schema.schine.network;
/*  2:   */
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import org.schema.common.util.ByteUtil;
/*  6:   */
/*  9:   */public class Header
/* 10:   */{
/* 11:   */  public static final byte packetByte = 42;
/* 12:   */  public static final byte pingByte = 23;
/* 13:   */  public static final byte testByte = 100;
/* 14:   */  public static final byte logoutByte = 65;
/* 15:   */  private byte commandId;
/* 16:   */  public short packetId;
/* 17:   */  byte commandType;
/* 18:   */  private byte type;
/* 19:   */  public static final byte TYPE_PARAMETRIZED_COMMAND = 111;
/* 20:   */  public static final byte TYPE_STREAM_COMMAND = 123;
/* 21:   */  
/* 22:   */  public Header() {}
/* 23:   */  
/* 24:   */  public Header(byte paramByte1, short paramShort, byte paramByte2)
/* 25:   */  {
/* 26:26 */    this.commandId = paramByte1;
/* 27:27 */    this.type = paramByte2;
/* 28:28 */    this.packetId = paramShort;
/* 29:   */  }
/* 30:   */  
/* 31:31 */  public Header(Class paramClass, int paramInt1, int paramInt2, short paramShort, byte paramByte) { this(NetUtil.commands.getByClass(paramClass).getId(), paramShort, paramByte); }
/* 32:   */  
/* 35:   */  public byte getCommandId()
/* 36:   */  {
/* 37:37 */    return this.commandId;
/* 38:   */  }
/* 39:   */  
/* 42:   */  public byte getType()
/* 43:   */  {
/* 44:44 */    return this.type;
/* 45:   */  }
/* 46:   */  
/* 47:   */  public void read(DataInputStream paramDataInputStream)
/* 48:   */  {
/* 49:49 */    this.packetId = paramDataInputStream.readShort();
/* 50:50 */    this.commandId = paramDataInputStream.readByte();
/* 51:51 */    this.type = paramDataInputStream.readByte();
/* 52:   */  }
/* 53:   */  
/* 54:   */  public String toString() {
/* 55:55 */    return "\n||commandId: " + getCommandId() + "; \n||type: " + getType() + "; \n||packetId: #" + this.packetId;
/* 56:   */  }
/* 57:   */  
/* 59:   */  public void write(DataOutputStream paramDataOutputStream)
/* 60:   */  {
/* 61:61 */    paramDataOutputStream.write(42);
/* 62:62 */    paramDataOutputStream.writeShort(this.packetId);
/* 63:63 */    paramDataOutputStream.writeByte(getCommandId());
/* 64:64 */    paramDataOutputStream.writeByte(getType());
/* 65:   */  }
/* 66:   */  
/* 67:   */  public void writeToArray(byte[] paramArrayOfByte)
/* 68:   */  {
/* 69:69 */    assert (paramArrayOfByte.length >= 5);
/* 70:70 */    paramArrayOfByte[0] = 42;
/* 71:71 */    ByteUtil.a(this.packetId, paramArrayOfByte, 1);
/* 72:72 */    paramArrayOfByte[3] = getCommandId();
/* 73:73 */    paramArrayOfByte[4] = getType();
/* 74:   */  }
/* 75:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.Header
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */