package org.hsqldb.result;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.HsqlByteArrayOutputStream;
import org.hsqldb.rowio.RowOutputInterface;

public final class ResultLob extends Result
{
  long lobID;
  int subType;
  long blockOffset;
  long blockLength;
  byte[] byteBlock;
  char[] charBlock;
  Reader reader;
  InputStream stream;

  private ResultLob()
  {
    super(18);
  }

  public static ResultLob newLobGetLengthRequest(long paramLong)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 10;
    localResultLob.lobID = paramLong;
    return localResultLob;
  }

  public static ResultLob newLobGetBytesRequest(long paramLong1, long paramLong2, int paramInt)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 1;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong2;
    localResultLob.blockLength = paramInt;
    return localResultLob;
  }

  public static ResultLob newLobGetCharsRequest(long paramLong1, long paramLong2, int paramInt)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 3;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong2;
    localResultLob.blockLength = paramInt;
    return localResultLob;
  }

  public static ResultLob newLobSetBytesRequest(long paramLong1, long paramLong2, byte[] paramArrayOfByte)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 2;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong2;
    localResultLob.byteBlock = paramArrayOfByte;
    localResultLob.blockLength = paramArrayOfByte.length;
    return localResultLob;
  }

  public static ResultLob newLobSetCharsRequest(long paramLong1, long paramLong2, char[] paramArrayOfChar)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 4;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong2;
    localResultLob.charBlock = paramArrayOfChar;
    localResultLob.blockLength = paramArrayOfChar.length;
    return localResultLob;
  }

  public static ResultLob newLobTruncateRequest(long paramLong1, long paramLong2)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 9;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong2;
    return localResultLob;
  }

  public static ResultLob newLobGetBytesResponse(long paramLong1, long paramLong2, byte[] paramArrayOfByte)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 21;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong2;
    localResultLob.byteBlock = paramArrayOfByte;
    localResultLob.blockLength = paramArrayOfByte.length;
    return localResultLob;
  }

  public static ResultLob newLobGetCharsResponse(long paramLong1, long paramLong2, char[] paramArrayOfChar)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 23;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong2;
    localResultLob.charBlock = paramArrayOfChar;
    localResultLob.blockLength = paramArrayOfChar.length;
    return localResultLob;
  }

  public static ResultLob newLobSetResponse(long paramLong1, long paramLong2)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 22;
    localResultLob.lobID = paramLong1;
    localResultLob.blockLength = paramLong2;
    return localResultLob;
  }

  public static ResultLob newLobGetBytePatternPositionRequest(long paramLong1, byte[] paramArrayOfByte, long paramLong2)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 5;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong2;
    localResultLob.byteBlock = paramArrayOfByte;
    localResultLob.blockLength = paramArrayOfByte.length;
    return localResultLob;
  }

  public static ResultLob newLobGetBytePatternPositionRequest(long paramLong1, long paramLong2, long paramLong3)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 5;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong3;
    return localResultLob;
  }

  public static ResultLob newLobGetCharPatternPositionRequest(long paramLong1, char[] paramArrayOfChar, long paramLong2)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 6;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong2;
    localResultLob.charBlock = paramArrayOfChar;
    localResultLob.blockLength = paramArrayOfChar.length;
    return localResultLob;
  }

  public static ResultLob newLobGetCharPatternPositionRequest(long paramLong1, long paramLong2, long paramLong3)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 6;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong3;
    localResultLob.blockLength = paramLong2;
    return localResultLob;
  }

  public static ResultLob newLobCreateBlobRequest(long paramLong1, long paramLong2, InputStream paramInputStream, long paramLong3)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.lobID = paramLong2;
    localResultLob.subType = 7;
    localResultLob.blockLength = paramLong3;
    localResultLob.stream = paramInputStream;
    return localResultLob;
  }

  public static ResultLob newLobCreateClobRequest(long paramLong1, long paramLong2, Reader paramReader, long paramLong3)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.lobID = paramLong2;
    localResultLob.subType = 8;
    localResultLob.blockLength = paramLong3;
    localResultLob.reader = paramReader;
    return localResultLob;
  }

  public static ResultLob newLobGetTruncateLength(long paramLong)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 13;
    localResultLob.lobID = paramLong;
    return localResultLob;
  }

  public static ResultLob newLobCreateBlobResponse(long paramLong)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 27;
    localResultLob.lobID = paramLong;
    return localResultLob;
  }

  public static ResultLob newLobCreateClobResponse(long paramLong)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 28;
    localResultLob.lobID = paramLong;
    return localResultLob;
  }

  public static ResultLob newLobTruncateResponse(long paramLong1, long paramLong2)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 29;
    localResultLob.lobID = paramLong1;
    localResultLob.blockLength = paramLong2;
    return localResultLob;
  }

  public static ResultLob newLobGetRequest(long paramLong1, long paramLong2, long paramLong3)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 11;
    localResultLob.lobID = paramLong1;
    localResultLob.blockOffset = paramLong2;
    localResultLob.blockLength = paramLong3;
    return localResultLob;
  }

  public static ResultLob newLobDuplicateRequest(long paramLong)
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.subType = 12;
    localResultLob.lobID = paramLong;
    return localResultLob;
  }

  public static ResultLob newLob(DataInput paramDataInput, boolean paramBoolean)
    throws IOException
  {
    ResultLob localResultLob = new ResultLob();
    localResultLob.databaseID = paramDataInput.readInt();
    localResultLob.sessionID = paramDataInput.readLong();
    localResultLob.lobID = paramDataInput.readLong();
    localResultLob.subType = paramDataInput.readInt();
    int i;
    switch (localResultLob.subType)
    {
    case 7:
    case 8:
      localResultLob.blockOffset = paramDataInput.readLong();
      localResultLob.blockLength = paramDataInput.readLong();
      break;
    case 1:
    case 3:
    case 11:
    case 12:
      localResultLob.blockOffset = paramDataInput.readLong();
      localResultLob.blockLength = paramDataInput.readLong();
      break;
    case 2:
    case 5:
      localResultLob.blockOffset = paramDataInput.readLong();
      localResultLob.blockLength = paramDataInput.readLong();
      localResultLob.byteBlock = new byte[(int)localResultLob.blockLength];
      paramDataInput.readFully(localResultLob.byteBlock);
      break;
    case 4:
    case 6:
      localResultLob.blockOffset = paramDataInput.readLong();
      localResultLob.blockLength = paramDataInput.readLong();
      localResultLob.charBlock = new char[(int)localResultLob.blockLength];
      for (i = 0; i < localResultLob.charBlock.length; i++)
        localResultLob.charBlock[i] = paramDataInput.readChar();
      break;
    case 9:
    case 10:
      localResultLob.blockOffset = paramDataInput.readLong();
      break;
    case 21:
      localResultLob.blockOffset = paramDataInput.readLong();
      localResultLob.blockLength = paramDataInput.readLong();
      localResultLob.byteBlock = new byte[(int)localResultLob.blockLength];
      paramDataInput.readFully(localResultLob.byteBlock);
      break;
    case 23:
      localResultLob.blockOffset = paramDataInput.readLong();
      localResultLob.blockLength = paramDataInput.readLong();
      localResultLob.charBlock = new char[(int)localResultLob.blockLength];
      for (i = 0; i < localResultLob.charBlock.length; i++)
        localResultLob.charBlock[i] = paramDataInput.readChar();
      break;
    case 22:
    case 27:
    case 28:
    case 29:
      localResultLob.blockLength = paramDataInput.readLong();
      break;
    case 25:
    case 26:
      localResultLob.blockOffset = paramDataInput.readLong();
      break;
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 20:
    case 24:
    default:
      throw Error.runtimeError(201, "ResultLob");
    }
    if (paramBoolean)
      paramDataInput.readByte();
    return localResultLob;
  }

  public void write(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream, RowOutputInterface paramRowOutputInterface)
    throws IOException
  {
    writeBody(paramSessionInterface, paramDataOutputStream);
    paramDataOutputStream.writeByte(0);
    paramDataOutputStream.flush();
  }

  public void writeBody(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream)
    throws IOException
  {
    switch (this.subType)
    {
    case 7:
      if (this.blockLength >= 0L)
      {
        writeCreate(paramSessionInterface, paramDataOutputStream);
        return;
      }
      writeCreateByteSegments(paramSessionInterface, paramDataOutputStream);
      return;
    case 8:
      if (this.blockLength >= 0L)
      {
        writeCreate(paramSessionInterface, paramDataOutputStream);
        return;
      }
      writeCreateCharSegments(paramSessionInterface, paramDataOutputStream);
      return;
    }
    paramDataOutputStream.writeByte(this.mode);
    paramDataOutputStream.writeInt(this.databaseID);
    paramDataOutputStream.writeLong(this.sessionID);
    paramDataOutputStream.writeLong(this.lobID);
    paramDataOutputStream.writeInt(this.subType);
    switch (this.subType)
    {
    case 2:
    case 5:
      paramDataOutputStream.writeLong(this.blockOffset);
      paramDataOutputStream.writeLong(this.blockLength);
      paramDataOutputStream.write(this.byteBlock);
      break;
    case 4:
    case 6:
      paramDataOutputStream.writeLong(this.blockOffset);
      paramDataOutputStream.writeLong(this.blockLength);
      paramDataOutputStream.writeChars(this.charBlock);
      break;
    case 1:
    case 3:
    case 11:
    case 12:
      paramDataOutputStream.writeLong(this.blockOffset);
      paramDataOutputStream.writeLong(this.blockLength);
      break;
    case 9:
    case 10:
      paramDataOutputStream.writeLong(this.blockOffset);
      break;
    case 21:
      paramDataOutputStream.writeLong(this.blockOffset);
      paramDataOutputStream.writeLong(this.blockLength);
      paramDataOutputStream.write(this.byteBlock);
      break;
    case 23:
      paramDataOutputStream.writeLong(this.blockOffset);
      paramDataOutputStream.writeLong(this.blockLength);
      paramDataOutputStream.writeChars(this.charBlock);
      break;
    case 22:
    case 27:
    case 28:
    case 29:
      paramDataOutputStream.writeLong(this.blockLength);
      break;
    case 25:
    case 26:
      paramDataOutputStream.writeLong(this.blockOffset);
      break;
    case 7:
    case 8:
    case 13:
    case 14:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    case 20:
    case 24:
    default:
      throw Error.runtimeError(201, "ResultLob");
    }
  }

  private void writeCreate(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream)
    throws IOException
  {
    paramDataOutputStream.writeByte(this.mode);
    paramDataOutputStream.writeInt(this.databaseID);
    paramDataOutputStream.writeLong(this.sessionID);
    paramDataOutputStream.writeLong(this.lobID);
    paramDataOutputStream.writeInt(this.subType);
    paramDataOutputStream.writeLong(this.blockOffset);
    paramDataOutputStream.writeLong(this.blockLength);
    switch (this.subType)
    {
    case 7:
      paramDataOutputStream.write(this.stream, this.blockLength);
      break;
    case 8:
      paramDataOutputStream.write(this.reader, this.blockLength);
    }
  }

  private void writeCreateByteSegments(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream)
    throws IOException
  {
    int i = paramSessionInterface.getStreamBlockSize();
    long l = this.blockOffset;
    paramDataOutputStream.writeByte(this.mode);
    paramDataOutputStream.writeInt(this.databaseID);
    paramDataOutputStream.writeLong(this.sessionID);
    paramDataOutputStream.writeLong(this.lobID);
    paramDataOutputStream.writeInt(this.subType);
    HsqlByteArrayOutputStream localHsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(i);
    localHsqlByteArrayOutputStream.reset();
    localHsqlByteArrayOutputStream.write(this.stream, i);
    paramDataOutputStream.writeLong(l);
    paramDataOutputStream.writeLong(localHsqlByteArrayOutputStream.size());
    paramDataOutputStream.write(localHsqlByteArrayOutputStream.getBuffer(), 0, localHsqlByteArrayOutputStream.size());
    l += localHsqlByteArrayOutputStream.size();
    if (localHsqlByteArrayOutputStream.size() < i)
      return;
    while (true)
    {
      localHsqlByteArrayOutputStream.reset();
      localHsqlByteArrayOutputStream.write(this.stream, i);
      if (localHsqlByteArrayOutputStream.size() != 0)
      {
        paramDataOutputStream.writeByte(this.mode);
        paramDataOutputStream.writeInt(this.databaseID);
        paramDataOutputStream.writeLong(this.sessionID);
        paramDataOutputStream.writeLong(this.lobID);
        paramDataOutputStream.writeInt(2);
        paramDataOutputStream.writeLong(l);
        paramDataOutputStream.writeLong(localHsqlByteArrayOutputStream.size());
        paramDataOutputStream.write(localHsqlByteArrayOutputStream.getBuffer(), 0, localHsqlByteArrayOutputStream.size());
        l += localHsqlByteArrayOutputStream.size();
        if (localHsqlByteArrayOutputStream.size() < i)
          break;
      }
    }
  }

  private void writeCreateCharSegments(SessionInterface paramSessionInterface, DataOutputStream paramDataOutputStream)
    throws IOException
  {
    int i = paramSessionInterface.getStreamBlockSize();
    long l = this.blockOffset;
    paramDataOutputStream.writeByte(this.mode);
    paramDataOutputStream.writeInt(this.databaseID);
    paramDataOutputStream.writeLong(this.sessionID);
    paramDataOutputStream.writeLong(this.lobID);
    paramDataOutputStream.writeInt(this.subType);
    HsqlByteArrayOutputStream localHsqlByteArrayOutputStream = new HsqlByteArrayOutputStream(i);
    localHsqlByteArrayOutputStream.reset();
    localHsqlByteArrayOutputStream.write(this.reader, i / 2);
    paramDataOutputStream.writeLong(l);
    paramDataOutputStream.writeLong(localHsqlByteArrayOutputStream.size() / 2);
    paramDataOutputStream.write(localHsqlByteArrayOutputStream.getBuffer(), 0, localHsqlByteArrayOutputStream.size());
    l += localHsqlByteArrayOutputStream.size() / 2;
    if (localHsqlByteArrayOutputStream.size() < i)
      return;
    while (true)
    {
      localHsqlByteArrayOutputStream.reset();
      localHsqlByteArrayOutputStream.write(this.reader, i / 2);
      if (localHsqlByteArrayOutputStream.size() != 0)
      {
        paramDataOutputStream.writeByte(this.mode);
        paramDataOutputStream.writeInt(this.databaseID);
        paramDataOutputStream.writeLong(this.sessionID);
        paramDataOutputStream.writeLong(this.lobID);
        paramDataOutputStream.writeInt(4);
        paramDataOutputStream.writeLong(l);
        paramDataOutputStream.writeLong(localHsqlByteArrayOutputStream.size() / 2);
        paramDataOutputStream.write(localHsqlByteArrayOutputStream.getBuffer(), 0, localHsqlByteArrayOutputStream.size());
        l += localHsqlByteArrayOutputStream.size() / 2;
        if (localHsqlByteArrayOutputStream.size() < i)
          break;
      }
    }
  }

  public long getLobID()
  {
    return this.lobID;
  }

  public int getSubType()
  {
    return this.subType;
  }

  public long getOffset()
  {
    return this.blockOffset;
  }

  public long getBlockLength()
  {
    return this.blockLength;
  }

  public byte[] getByteArray()
  {
    return this.byteBlock;
  }

  public char[] getCharArray()
  {
    return this.charBlock;
  }

  public InputStream getInputStream()
  {
    return this.stream;
  }

  public Reader getReader()
  {
    return this.reader;
  }

  public static abstract interface LobResultTypes
  {
    public static final int REQUEST_GET_BYTES = 1;
    public static final int REQUEST_SET_BYTES = 2;
    public static final int REQUEST_GET_CHARS = 3;
    public static final int REQUEST_SET_CHARS = 4;
    public static final int REQUEST_GET_BYTE_PATTERN_POSITION = 5;
    public static final int REQUEST_GET_CHAR_PATTERN_POSITION = 6;
    public static final int REQUEST_CREATE_BYTES = 7;
    public static final int REQUEST_CREATE_CHARS = 8;
    public static final int REQUEST_TRUNCATE = 9;
    public static final int REQUEST_GET_LENGTH = 10;
    public static final int REQUEST_GET_LOB = 11;
    public static final int REQUEST_DUPLICATE_LOB = 12;
    public static final int REQUEST_GET_TRUNCATE_LENGTH = 13;
    public static final int RESPONSE_GET_BYTES = 21;
    public static final int RESPONSE_SET = 22;
    public static final int RESPONSE_GET_CHARS = 23;
    public static final int RESPONSE_GET_BYTE_PATTERN_POSITION = 25;
    public static final int RESPONSE_GET_CHAR_PATTERN_POSITION = 26;
    public static final int RESPONSE_CREATE_BYTES = 27;
    public static final int RESPONSE_CREATE_CHARS = 28;
    public static final int RESPONSE_TRUNCATE = 29;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.result.ResultLob
 * JD-Core Version:    0.6.2
 */