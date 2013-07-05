package org.hsqldb.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import org.hsqldb.store.BitMap;

public final class ServerAcl
{
  protected static final byte[] ALL_SET_4BYTES = { -1, -1, -1, -1 };
  protected static final byte[] ALL_SET_16BYTES = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
  private PrintWriter pw = null;
  private List aclEntries;
  private static AclEntry PROHIBIT_ALL_IPV4;
  private static AclEntry PROHIBIT_ALL_IPV6;
  private File aclFile;
  private long lastLoadTime = 0L;

  public static String dottedNotation(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      if (i > 0)
        localStringBuffer.append('.');
      localStringBuffer.append(paramArrayOfByte[i] & 0xFF);
    }
    return localStringBuffer.toString();
  }

  public static String colonNotation(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length / 2 * 2 != paramArrayOfByte.length)
      throw new RuntimeException("At this time .colonNotation only handles even byte quantities");
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfByte.length; i += 2)
    {
      if (i > 0)
        localStringBuffer.append(':');
      localStringBuffer.append(Integer.toHexString((paramArrayOfByte[i] & 0xFF) * 256 + (paramArrayOfByte[(i + 1)] & 0xFF)));
    }
    return localStringBuffer.toString();
  }

  public void setPrintWriter(PrintWriter paramPrintWriter)
  {
    this.pw = paramPrintWriter;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < this.aclEntries.size(); i++)
    {
      if (i > 0)
        localStringBuffer.append('\n');
      localStringBuffer.append("Entry " + (i + 1) + ": " + this.aclEntries.get(i));
    }
    return localStringBuffer.toString();
  }

  public boolean permitAccess(String paramString)
  {
    try
    {
      return permitAccess(InetAddress.getByName(paramString).getAddress());
    }
    catch (UnknownHostException localUnknownHostException)
    {
      println("'" + paramString + "' denied because failed to resolve to an addr");
    }
    return false;
  }

  public boolean permitAccess(byte[] paramArrayOfByte)
  {
    ensureAclsUptodate();
    for (int i = 0; i < this.aclEntries.size(); i++)
      if (((AclEntry)this.aclEntries.get(i)).matches(paramArrayOfByte))
      {
        AclEntry localAclEntry = (AclEntry)this.aclEntries.get(i);
        println("Addr '" + dottedNotation(paramArrayOfByte) + "' matched rule #" + (i + 1) + ":  " + localAclEntry);
        return localAclEntry.allow;
      }
    throw new RuntimeException("No rule matches address '" + dottedNotation(paramArrayOfByte) + "'");
  }

  private void println(String paramString)
  {
    if (this.pw == null)
      return;
    this.pw.println(paramString);
    this.pw.flush();
  }

  public ServerAcl(File paramFile)
    throws IOException, ServerAcl.AclFormatException
  {
    this.aclFile = paramFile;
    this.aclEntries = load();
  }

  protected synchronized void ensureAclsUptodate()
  {
    if (this.lastLoadTime > this.aclFile.lastModified())
      return;
    try
    {
      this.aclEntries = load();
      println("ACLs reloaded from file");
      return;
    }
    catch (Exception localException)
    {
      println("Failed to reload ACL file.  Retaining old ACLs.  " + localException);
    }
  }

  protected List load()
    throws IOException, ServerAcl.AclFormatException
  {
    if (!this.aclFile.exists())
      throw new IOException("File '" + this.aclFile.getAbsolutePath() + "' is not present");
    if (!this.aclFile.isFile())
      throw new IOException("'" + this.aclFile.getAbsolutePath() + "' is not a regular file");
    if (!this.aclFile.canRead())
      throw new IOException("'" + this.aclFile.getAbsolutePath() + "' is not accessible");
    String str4 = null;
    int j = 0;
    BufferedReader localBufferedReader = new BufferedReader(new FileReader(this.aclFile));
    ArrayList localArrayList = new ArrayList();
    try
    {
      String str1;
      while ((str1 = localBufferedReader.readLine()) != null)
      {
        j++;
        str1 = str1.trim();
        if ((str1.length() >= 1) && (str1.charAt(0) != '#'))
        {
          StringTokenizer localStringTokenizer = new StringTokenizer(str1);
          byte[] arrayOfByte;
          int k;
          boolean bool;
          try
          {
            if (localStringTokenizer.countTokens() != 2)
              throw new InternalException(null);
            String str2 = localStringTokenizer.nextToken();
            String str3 = localStringTokenizer.nextToken();
            int i = str3.indexOf('/');
            if (i > -1)
            {
              str4 = str3.substring(i + 1);
              str3 = str3.substring(0, i);
            }
            arrayOfByte = InetAddress.getByName(str3).getAddress();
            k = str4 == null ? arrayOfByte.length * 8 : Integer.parseInt(str4);
            if (str2.equalsIgnoreCase("allow"))
              bool = true;
            else if (str2.equalsIgnoreCase("permit"))
              bool = true;
            else if (str2.equalsIgnoreCase("accept"))
              bool = true;
            else if (str2.equalsIgnoreCase("prohibit"))
              bool = false;
            else if (str2.equalsIgnoreCase("deny"))
              bool = false;
            else if (str2.equalsIgnoreCase("reject"))
              bool = false;
            else
              throw new InternalException(null);
          }
          catch (NumberFormatException localNumberFormatException)
          {
            throw new AclFormatException("Syntax error at ACL file '" + this.aclFile.getAbsolutePath() + "', line " + j);
          }
          catch (InternalException localInternalException)
          {
            throw new AclFormatException("Syntax error at ACL file '" + this.aclFile.getAbsolutePath() + "', line " + j);
          }
          try
          {
            localArrayList.add(new AclEntry(arrayOfByte, k, bool));
          }
          catch (AclFormatException localAclFormatException)
          {
            throw new AclFormatException("Syntax error at ACL file '" + this.aclFile.getAbsolutePath() + "', line " + j + ": " + localAclFormatException.toString());
          }
        }
      }
    }
    finally
    {
      localBufferedReader.close();
    }
    localArrayList.add(PROHIBIT_ALL_IPV4);
    localArrayList.add(PROHIBIT_ALL_IPV6);
    this.lastLoadTime = new Date().getTime();
    return localArrayList;
  }

  public static void main(String[] paramArrayOfString)
    throws ServerAcl.AclFormatException, IOException
  {
    if (paramArrayOfString.length > 1)
      throw new RuntimeException("Try: java -cp path/to/hsqldb.jar " + ServerAcl.class.getName() + " --help");
    if ((paramArrayOfString.length > 0) && (paramArrayOfString[0].equals("--help")))
    {
      System.err.println("SYNTAX: java -cp path/to/hsqldb.jar " + ServerAcl.class.getName() + " [filepath.txt]");
      System.err.println("ACL file path defaults to 'acl.txt' in the current directory.");
      System.exit(0);
    }
    ServerAcl localServerAcl = new ServerAcl(new File(paramArrayOfString.length == 0 ? "acl.txt" : paramArrayOfString[0]));
    localServerAcl.setPrintWriter(new PrintWriter(System.out));
    System.out.println(localServerAcl.toString());
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter hostnames or IP addresses to be tested (one per line).");
    String str;
    while ((str = localBufferedReader.readLine()) != null)
    {
      str = str.trim();
      if (str.length() >= 1)
        System.out.println(Boolean.toString(localServerAcl.permitAccess(str)));
    }
  }

  static
  {
    try
    {
      PROHIBIT_ALL_IPV4 = new AclEntry(InetAddress.getByName("0.0.0.0").getAddress(), 0, false);
      PROHIBIT_ALL_IPV6 = new AclEntry(InetAddress.getByName("::").getAddress(), 0, false);
    }
    catch (UnknownHostException localUnknownHostException)
    {
      throw new RuntimeException("Unexpected problem in static initializer", localUnknownHostException);
    }
    catch (AclFormatException localAclFormatException)
    {
      throw new RuntimeException("Unexpected problem in static initializer", localAclFormatException);
    }
  }

  private static final class InternalException extends Exception
  {
  }

  private static final class AclEntry
  {
    private byte[] value;
    private byte[] mask;
    private int bitBlockSize;
    public boolean allow;

    public AclEntry(byte[] paramArrayOfByte, int paramInt, boolean paramBoolean)
      throws ServerAcl.AclFormatException
    {
      byte[] arrayOfByte = null;
      switch (paramArrayOfByte.length)
      {
      case 4:
        arrayOfByte = ServerAcl.ALL_SET_4BYTES;
        break;
      case 16:
        arrayOfByte = ServerAcl.ALL_SET_16BYTES;
        break;
      default:
        throw new IllegalArgumentException(new StringBuilder().append("Only 4 and 16 bytes supported, not ").append(paramArrayOfByte.length).toString());
      }
      if (paramInt > paramArrayOfByte.length * 8)
        throw new IllegalArgumentException(new StringBuilder().append("Specified ").append(paramInt).append(" significant bits, but value only has ").append(paramArrayOfByte.length * 8).append(" bits").toString());
      this.bitBlockSize = paramInt;
      this.value = paramArrayOfByte;
      this.mask = BitMap.leftShift(arrayOfByte, paramArrayOfByte.length * 8 - paramInt);
      if (this.mask.length != paramArrayOfByte.length)
        throw new RuntimeException(new StringBuilder().append("Basic program assertion failed.  Generated mask length ").append(this.mask.length).append(" (bytes) does not match given value length ").append(paramArrayOfByte.length).append(" (bytes).").toString());
      this.allow = paramBoolean;
      validateMask();
    }

    public String toString()
    {
      StringBuffer localStringBuffer = new StringBuffer("Addrs ");
      localStringBuffer.append(this.value.length == 16 ? new StringBuilder().append("[").append(ServerAcl.colonNotation(this.value)).append(']').toString() : ServerAcl.dottedNotation(this.value));
      localStringBuffer.append(new StringBuilder().append("/").append(this.bitBlockSize).append(' ').append(this.allow ? "ALLOW" : "DENY").toString());
      return localStringBuffer.toString();
    }

    public boolean matches(byte[] paramArrayOfByte)
    {
      if (this.value.length != paramArrayOfByte.length)
        return false;
      return !BitMap.hasAnyBitSet(BitMap.xor(this.value, BitMap.and(paramArrayOfByte, this.mask)));
    }

    public void validateMask()
      throws ServerAcl.AclFormatException
    {
      if (BitMap.hasAnyBitSet(BitMap.and(this.value, BitMap.not(this.mask))))
        throw new ServerAcl.AclFormatException(new StringBuilder().append("The base address '").append(ServerAcl.dottedNotation(this.value)).append("' is too specific for block-size-spec /").append(this.bitBlockSize).toString());
    }
  }

  public static final class AclFormatException extends Exception
  {
    public AclFormatException(String paramString)
    {
      super();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.server.ServerAcl
 * JD-Core Version:    0.6.2
 */