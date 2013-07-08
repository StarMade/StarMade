package org.schema.schine.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import org.schema.schine.network.client.ClientProcessor;
import org.schema.schine.network.client.ClientStateInterface;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public abstract class Command
{
  public static final int MODE_NO_RETURN = 0;
  public static final int MODE_RETURN = 1;
  private String[] attribs;
  protected int mode = 0;
  private byte field_1673;
  public static long sendingTime;
  
  public static void getHeader() {}
  
  public abstract void clientAnswerProcess(Object[] paramArrayOfObject, ClientStateInterface paramClientStateInterface, short paramShort);
  
  public void createReturnToClient(ServerStateInterface paramServerStateInterface, ServerProcessor paramServerProcessor, short paramShort, Object... paramVarArgs)
  {
    writeAndCommitParametriziedCommand(paramVarArgs, 0, paramServerProcessor.getClient() != null ? paramServerProcessor.getClient().getId() : -4242, paramShort, paramServerProcessor);
  }
  
  public String[] getAttribs()
  {
    return this.attribs;
  }
  
  public byte getId()
  {
    return this.field_1673;
  }
  
  public int getMode()
  {
    return this.mode;
  }
  
  public Object[] readParameters(Header paramHeader, DataInputStream paramDataInputStream)
  {
    Object[] arrayOfObject = new Object[paramHeader = paramDataInputStream.readInt()];
    for (Header localHeader = 0; localHeader < paramHeader; localHeader++)
    {
      int i;
      byte[] arrayOfByte;
      switch (i = (byte)paramDataInputStream.read())
      {
      case 2: 
        arrayOfObject[localHeader] = Long.valueOf(paramDataInputStream.readLong());
        break;
      case 4: 
        arrayOfObject[localHeader] = paramDataInputStream.readUTF();
        break;
      case 3: 
        arrayOfObject[localHeader] = Float.valueOf(paramDataInputStream.readFloat());
        break;
      case 1: 
        arrayOfObject[localHeader] = Integer.valueOf(paramDataInputStream.readInt());
        break;
      case 5: 
        arrayOfObject[localHeader] = Boolean.valueOf(paramDataInputStream.readBoolean());
        break;
      case 6: 
        arrayOfObject[localHeader] = Byte.valueOf(paramDataInputStream.readByte());
        break;
      case 7: 
        arrayOfObject[localHeader] = Short.valueOf(paramDataInputStream.readShort());
        break;
      case 8: 
        arrayOfByte = new byte[paramDataInputStream.readInt()];
        paramDataInputStream.readFully(arrayOfByte);
        arrayOfObject[localHeader] = arrayOfByte;
        break;
      default: 
        throw new IllegalArgumentException("Type: " + arrayOfByte + " unknown. parameter " + localHeader + " of " + paramHeader + " in command " + this);
      }
    }
    return arrayOfObject;
  }
  
  public abstract void serverProcess(ServerProcessor paramServerProcessor, Object[] paramArrayOfObject, ServerStateInterface paramServerStateInterface, short paramShort);
  
  public void setId(byte paramByte)
  {
    this.field_1673 = paramByte;
  }
  
  public byte[] successDoNothing()
  {
    return NetUtil.COMMAND_SUCCESS_DO_NOTHING;
  }
  
  public String toString()
  {
    return getClass().getSimpleName() + "[" + (this.mode == 0 ? "void" : "return") + "]";
  }
  
  public void writeAndCommitParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, short paramShort, NetworkProcessor paramNetworkProcessor)
  {
    long l1 = System.currentTimeMillis();
    synchronized (paramNetworkProcessor.getLock())
    {
      Header localHeader = new Header(getId(), paramShort, (byte)111);
      if ((paramNetworkProcessor instanceof ClientProcessor)) {
        ((ClientProcessor)paramNetworkProcessor).lastPacketId = paramShort;
      }
      localHeader.write(paramNetworkProcessor.getOut());
      writeParametriziedCommand(paramArrayOfObject, paramInt1, paramInt2, paramNetworkProcessor);
      paramNetworkProcessor.flushDoubleOutBuffer();
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 7L) {
      System.err.println("[COMMAND] WARNING: WRITING OUT NT COMMAND " + this + " ON " + paramNetworkProcessor.getState() + " TOOK: " + l2);
    }
  }
  
  public void writeParametriziedCommand(Object[] paramArrayOfObject, int paramInt1, int paramInt2, NetworkProcessor paramNetworkProcessor)
  {
    paramNetworkProcessor.getOut().writeInt(paramArrayOfObject.length);
    for (paramInt1 = 0; paramInt1 < paramArrayOfObject.length; paramInt1++) {
      if ((paramArrayOfObject[paramInt1] instanceof Long))
      {
        paramNetworkProcessor.getOut().write(2);
        paramNetworkProcessor.getOut().writeLong(((Long)paramArrayOfObject[paramInt1]).longValue());
      }
      else if ((paramArrayOfObject[paramInt1] instanceof String))
      {
        paramNetworkProcessor.getOut().write(4);
        paramNetworkProcessor.getOut().writeUTF((String)paramArrayOfObject[paramInt1]);
      }
      else if ((paramArrayOfObject[paramInt1] instanceof Float))
      {
        paramNetworkProcessor.getOut().write(3);
        paramNetworkProcessor.getOut().writeFloat(((Float)paramArrayOfObject[paramInt1]).floatValue());
      }
      else if ((paramArrayOfObject[paramInt1] instanceof Integer))
      {
        paramNetworkProcessor.getOut().write(1);
        paramNetworkProcessor.getOut().writeInt(((Integer)paramArrayOfObject[paramInt1]).intValue());
      }
      else if ((paramArrayOfObject[paramInt1] instanceof Boolean))
      {
        paramNetworkProcessor.getOut().write(5);
        paramNetworkProcessor.getOut().writeBoolean(((Boolean)paramArrayOfObject[paramInt1]).booleanValue());
      }
      else if ((paramArrayOfObject[paramInt1] instanceof Byte))
      {
        paramNetworkProcessor.getOut().write(6);
        paramNetworkProcessor.getOut().writeByte(((Byte)paramArrayOfObject[paramInt1]).byteValue());
      }
      else if ((paramArrayOfObject[paramInt1] instanceof Short))
      {
        paramNetworkProcessor.getOut().write(7);
        paramNetworkProcessor.getOut().writeShort(((Short)paramArrayOfObject[paramInt1]).shortValue());
      }
      else if ((paramArrayOfObject[paramInt1] instanceof byte[]))
      {
        paramInt2 = (byte[])paramArrayOfObject[paramInt1];
        paramNetworkProcessor.getOut().write(8);
        paramNetworkProcessor.getOut().writeInt(paramInt2.length);
        paramNetworkProcessor.getOut().write(paramInt2);
      }
      else if (!$assertionsDisabled)
      {
        throw new AssertionError("UNKNOWN OBJECT TYPE: " + paramArrayOfObject[paramInt1] + " : " + paramArrayOfObject[paramInt1].getClass());
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.Command
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */