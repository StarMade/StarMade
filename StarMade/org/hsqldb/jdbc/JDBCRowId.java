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
  private final byte[] field_1874;
  
  public JDBCRowId(byte[] paramArrayOfByte)
    throws SQLException
  {
    if (paramArrayOfByte == null) {
      throw Util.nullArgument("id");
    }
    this.field_1874 = paramArrayOfByte;
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
      this.field_1874 = StringConverter.hexStringToByteArray(paramString);
    }
    catch (IOException localIOException)
    {
      throw Util.sqlException(423, "hex: " + localIOException);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof JDBCRowId)) && (Arrays.equals(this.field_1874, ((JDBCRowId)paramObject).field_1874));
  }
  
  public byte[] getBytes()
  {
    return (byte[])this.field_1874.clone();
  }
  
  public String toString()
  {
    return StringConverter.byteArrayToHexString(this.field_1874);
  }
  
  public int hashCode()
  {
    if (this.hash == 0) {
      this.hash = Arrays.hashCode(this.field_1874);
    }
    return this.hash;
  }
  
  Object id()
  {
    return this.field_1874;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.jdbc.JDBCRowId
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */