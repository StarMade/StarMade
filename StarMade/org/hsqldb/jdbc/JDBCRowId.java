package org.hsqldb.jdbc;

import java.io.IOException;
import java.sql.RowId;
import java.sql.SQLException;
import java.util.Arrays;
import org.hsqldb.lib.StringConverter;

public final class JDBCRowId
  implements RowId
{
  private int hash;
  private final byte[] id;
  
  public JDBCRowId(byte[] paramArrayOfByte)
    throws SQLException
  {
    if (paramArrayOfByte == null) {
      throw Util.nullArgument("id");
    }
    this.id = paramArrayOfByte;
  }
  
  public JDBCRowId(RowId paramRowId)
    throws SQLException
  {
    this(paramRowId.getBytes());
  }
  
  public JDBCRowId(String paramString)
    throws SQLException
  {
    if (paramString == null) {
      throw Util.nullArgument("hex");
    }
    try
    {
      this.id = StringConverter.hexStringToByteArray(paramString);
    }
    catch (IOException localIOException)
    {
      throw Util.sqlException(423, "hex: " + localIOException);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof JDBCRowId)) && (Arrays.equals(this.id, ((JDBCRowId)paramObject).id));
  }
  
  public byte[] getBytes()
  {
    return (byte[])this.id.clone();
  }
  
  public String toString()
  {
    return StringConverter.byteArrayToHexString(this.id);
  }
  
  public int hashCode()
  {
    if (this.hash == 0) {
      this.hash = Arrays.hashCode(this.id);
    }
    return this.hash;
  }
  
  Object id()
  {
    return this.id;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.jdbc.JDBCRowId
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */