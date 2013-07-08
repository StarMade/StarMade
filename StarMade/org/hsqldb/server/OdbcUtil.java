package org.hsqldb.server;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import org.hsqldb.ColumnBase;
import org.hsqldb.lib.DataOutputStream;
import org.hsqldb.lib.IntKeyHashMap;
import org.hsqldb.result.ResultMetaData;

public class OdbcUtil
{
  static final int ODBC_SM_DATABASE = 64;
  static final int ODBC_SM_USER = 32;
  static final int ODBC_SM_OPTIONS = 64;
  static final int ODBC_SM_UNUSED = 64;
  static final int ODBC_SM_TTY = 64;
  static final int ODBC_AUTH_REQ_PASSWORD = 3;
  static final int ODBC_AUTH_REQ_OK = 0;
  static String[][] hardcodedParams = { { "client_encoding", "SQL_ASCII" }, { "DateStyle", "ISO, MDY" }, { "integer_datetimes", "on" }, { "is_superuser", "on" }, { "server_encoding", "SQL_ASCII" }, { "server_version", "8.3.1" }, { "session_authorization", "blaine" }, { "standard_conforming_strings", "off" }, { "TimeZone", "US/Eastern" } };
  static final int ODBC_SIMPLE_MODE = 0;
  static final int ODBC_EXTENDED_MODE = 1;
  static final int ODBC_EXT_RECOVER_MODE = 2;
  static final int ODBC_SEVERITY_FATAL = 1;
  static final int ODBC_SEVERITY_ERROR = 2;
  static final int ODBC_SEVERITY_PANIC = 3;
  static final int ODBC_SEVERITY_WARNING = 4;
  static final int ODBC_SEVERITY_NOTICE = 5;
  static final int ODBC_SEVERITY_DEBUG = 6;
  static final int ODBC_SEVERITY_INFO = 7;
  static final int ODBC_SEVERITY_LOG = 8;
  static IntKeyHashMap odbcSeverityMap = new IntKeyHashMap();
  
  static void validateInputPacketSize(OdbcPacketInputStream paramOdbcPacketInputStream)
    throws RecoverableOdbcFailure
  {
    int i = -1;
    try
    {
      i = paramOdbcPacketInputStream.available();
    }
    catch (IOException localIOException) {}
    if (i < 1) {
      return;
    }
    throw new RecoverableOdbcFailure("Client supplied bad length for " + paramOdbcPacketInputStream.packetType + " packet.  " + i + " bytes available after processing", "Bad length for " + paramOdbcPacketInputStream.packetType + " packet.  " + i + " extra bytes", "08P01");
  }
  
  static String echoBackReplyString(String paramString, int paramInt)
  {
    String str1 = paramString.trim().toUpperCase(Locale.ENGLISH);
    for (int i = 0; (i < str1.length()) && (!Character.isWhitespace(str1.charAt(i))); i++) {}
    StringBuffer localStringBuffer = new StringBuffer(str1.substring(0, i));
    String str2 = localStringBuffer.toString();
    if ((str2.equals("UPDATE")) || (str2.equals("DELETE")))
    {
      localStringBuffer.append(" " + paramInt);
    }
    else if ((str2.equals("CREATE")) || (str2.equals("DROP")))
    {
      for (int j = i; (j < str1.length()) && (Character.isWhitespace(str1.charAt(j))); j++) {}
      for (int k = j; (k < str1.length()) && (Character.isWhitespace(str1.charAt(k))); k++) {}
      localStringBuffer.append(" " + str1.substring(j, k));
    }
    else if (str2.equals("INSERT"))
    {
      localStringBuffer.append(" 0 " + paramInt);
    }
    return localStringBuffer.toString();
  }
  
  static void writeParam(String paramString1, String paramString2, DataOutputStream paramDataOutputStream)
    throws IOException
  {
    OdbcPacketOutputStream localOdbcPacketOutputStream = OdbcPacketOutputStream.newOdbcPacketOutputStream();
    localOdbcPacketOutputStream.write(paramString1);
    localOdbcPacketOutputStream.write(paramString2);
    localOdbcPacketOutputStream.xmit('S', paramDataOutputStream);
    localOdbcPacketOutputStream.close();
  }
  
  static void alertClient(int paramInt, String paramString, DataOutputStream paramDataOutputStream)
    throws IOException
  {
    alertClient(paramInt, paramString, null, paramDataOutputStream);
  }
  
  static void alertClient(int paramInt, String paramString1, String paramString2, DataOutputStream paramDataOutputStream)
    throws IOException
  {
    if (paramString2 == null) {
      paramString2 = "XX000";
    }
    if (!odbcSeverityMap.containsKey(paramInt)) {
      throw new IllegalArgumentException("Unknown severity value (" + paramInt + ')');
    }
    OdbcPacketOutputStream localOdbcPacketOutputStream = OdbcPacketOutputStream.newOdbcPacketOutputStream();
    localOdbcPacketOutputStream.write("S" + odbcSeverityMap.get(paramInt));
    if (paramInt < 5) {
      localOdbcPacketOutputStream.write("C" + paramString2);
    }
    localOdbcPacketOutputStream.write("M" + paramString1);
    localOdbcPacketOutputStream.writeByte(0);
    localOdbcPacketOutputStream.xmit(paramInt < 5 ? 'E' : 'N', paramDataOutputStream);
    localOdbcPacketOutputStream.close();
  }
  
  static String revertMungledPreparedQuery(String paramString)
  {
    return paramString.replaceAll("\\$\\d+", "?");
  }
  
  public static int getTableOidForColumn(int paramInt, ResultMetaData paramResultMetaData)
  {
    if (!paramResultMetaData.isTableColumn(paramInt)) {
      return 0;
    }
    ColumnBase localColumnBase = paramResultMetaData.columns[paramInt];
    int i = (localColumnBase.getSchemaNameString() + '.' + localColumnBase.getTableNameString()).hashCode();
    if (i < 0) {
      i *= -1;
    }
    return i;
  }
  
  public static short getIdForColumn(int paramInt, ResultMetaData paramResultMetaData)
  {
    if (!paramResultMetaData.isTableColumn(paramInt)) {
      return 0;
    }
    short s = (short)paramResultMetaData.getGeneratedColumnNames()[paramInt].hashCode();
    if (s < 0) {
      s = (short)(s * -1);
    }
    return s;
  }
  
  public static String hexCharsToOctalOctets(String paramString)
  {
    int i = paramString.length();
    if (i != i / 2 * 2) {
      throw new IllegalArgumentException("Hex character lists contains an odd number of characters: " + i);
    }
    StringBuffer localStringBuffer = new StringBuffer();
    for (int k = 0; k < i; k++)
    {
      int j = 0;
      char c = paramString.charAt(k);
      if ((c >= 'a') && (c <= 'f')) {
        j += '\n' + c - 97;
      } else if ((c >= 'A') && (c <= 'F')) {
        j += '\n' + c - 65;
      } else if ((c >= '0') && (c <= '9')) {
        j += c - '0';
      } else {
        throw new IllegalArgumentException("Non-hex character in input at offset " + k + ": " + c);
      }
      j <<= 4;
      c = paramString.charAt(++k);
      if ((c >= 'a') && (c <= 'f')) {
        j += '\n' + c - 97;
      } else if ((c >= 'A') && (c <= 'F')) {
        j += '\n' + c - 65;
      } else if ((c >= '0') && (c <= '9')) {
        j += c - '0';
      } else {
        throw new IllegalArgumentException("Non-hex character in input at offset " + k + ": " + c);
      }
      localStringBuffer.append('\\');
      localStringBuffer.append((char)(48 + (j >> 6)));
      localStringBuffer.append((char)(48 + (j >> 3 & 0x7)));
      localStringBuffer.append((char)(48 + (j & 0x7)));
    }
    return localStringBuffer.toString();
  }
  
  public static void main(String[] paramArrayOfString)
  {
    System.out.println("(" + hexCharsToOctalOctets(paramArrayOfString[0]) + ')');
  }
  
  static
  {
    odbcSeverityMap.put(1, "FATAL");
    odbcSeverityMap.put(2, "ERROR");
    odbcSeverityMap.put(3, "PANIC");
    odbcSeverityMap.put(4, "WARNING");
    odbcSeverityMap.put(5, "NOTICE");
    odbcSeverityMap.put(6, "DEBUG");
    odbcSeverityMap.put(7, "INFO");
    odbcSeverityMap.put(8, "LOG");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.server.OdbcUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */