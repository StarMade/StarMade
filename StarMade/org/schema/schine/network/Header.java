package org.schema.schine.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.common.util.ByteUtil;

public class Header
{
  public static final byte packetByte = 42;
  public static final byte pingByte = 23;
  public static final byte testByte = 100;
  public static final byte logoutByte = 65;
  private byte commandId;
  public short packetId;
  byte commandType;
  private byte type;
  public static final byte TYPE_PARAMETRIZED_COMMAND = 111;
  public static final byte TYPE_STREAM_COMMAND = 123;
  
  public Header() {}
  
  public Header(byte paramByte1, short paramShort, byte paramByte2)
  {
    this.commandId = paramByte1;
    this.type = paramByte2;
    this.packetId = paramShort;
  }
  
  public Header(Class paramClass, int paramInt1, int paramInt2, short paramShort, byte paramByte)
  {
    this(NetUtil.commands.getByClass(paramClass).getId(), paramShort, paramByte);
  }
  
  public byte getCommandId()
  {
    return this.commandId;
  }
  
  public byte getType()
  {
    return this.type;
  }
  
  public void read(DataInputStream paramDataInputStream)
  {
    this.packetId = paramDataInputStream.readShort();
    this.commandId = paramDataInputStream.readByte();
    this.type = paramDataInputStream.readByte();
  }
  
  public String toString()
  {
    return "\n||commandId: " + getCommandId() + "; \n||type: " + getType() + "; \n||packetId: #" + this.packetId;
  }
  
  public void write(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.write(42);
    paramDataOutputStream.writeShort(this.packetId);
    paramDataOutputStream.writeByte(getCommandId());
    paramDataOutputStream.writeByte(getType());
  }
  
  public void writeToArray(byte[] paramArrayOfByte)
  {
    assert (paramArrayOfByte.length >= 5);
    paramArrayOfByte[0] = 42;
    ByteUtil.a13(this.packetId, paramArrayOfByte, 1);
    paramArrayOfByte[3] = getCommandId();
    paramArrayOfByte[4] = getType();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.Header
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */