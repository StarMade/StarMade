package org.hsqldb.types;

import java.io.Reader;
import org.hsqldb.SessionInterface;

public abstract interface ClobData extends LobData
{
  public abstract char[] getChars(SessionInterface paramSessionInterface, long paramLong, int paramInt);

  public abstract long length(SessionInterface paramSessionInterface);

  public abstract String getSubString(SessionInterface paramSessionInterface, long paramLong, int paramInt);

  public abstract ClobData getClob(SessionInterface paramSessionInterface, long paramLong1, long paramLong2);

  public abstract ClobData duplicate(SessionInterface paramSessionInterface);

  public abstract void truncate(SessionInterface paramSessionInterface, long paramLong);

  public abstract Reader getCharacterStream(SessionInterface paramSessionInterface);

  public abstract void setString(SessionInterface paramSessionInterface, long paramLong, String paramString);

  public abstract void setClob(SessionInterface paramSessionInterface, long paramLong1, ClobData paramClobData, long paramLong2, long paramLong3);

  public abstract void setChars(SessionInterface paramSessionInterface, long paramLong, char[] paramArrayOfChar, int paramInt1, int paramInt2);

  public abstract void setCharacterStream(SessionInterface paramSessionInterface, long paramLong, Reader paramReader);

  public abstract long position(SessionInterface paramSessionInterface, String paramString, long paramLong);

  public abstract long position(SessionInterface paramSessionInterface, ClobData paramClobData, long paramLong);

  public abstract long nonSpaceLength(SessionInterface paramSessionInterface);

  public abstract Reader getCharacterStream(SessionInterface paramSessionInterface, long paramLong1, long paramLong2);

  public abstract long getId();

  public abstract void setId(long paramLong);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.ClobData
 * JD-Core Version:    0.6.2
 */