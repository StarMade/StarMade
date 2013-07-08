package org.hsqldb.jdbc.pool;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Random;
import javax.transaction.xa.Xid;

public class JDBCXID
  implements Xid
{
  int formatID;
  byte[] txID;
  byte[] txBranch;
  int hash;
  boolean hashComputed;
  private static byte[] s_localIp = null;
  private static int s_txnSequenceNumber = 0;
  private static final int UXID_FORMAT_ID = 65261;
  
  public int getFormatId()
  {
    return this.formatID;
  }
  
  public byte[] getGlobalTransactionId()
  {
    return this.txID;
  }
  
  public byte[] getBranchQualifier()
  {
    return this.txBranch;
  }
  
  public JDBCXID(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    this.formatID = paramInt;
    this.txID = paramArrayOfByte1;
    this.txBranch = paramArrayOfByte2;
  }
  
  public int hashCode()
  {
    if (!this.hashComputed)
    {
      this.hash = 7;
      this.hash = (83 * this.hash + this.formatID);
      this.hash = (83 * this.hash + Arrays.hashCode(this.txID));
      this.hash = (83 * this.hash + Arrays.hashCode(this.txBranch));
      this.hashComputed = true;
    }
    return this.hash;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Xid))
    {
      Xid localXid = (Xid)paramObject;
      return (this.formatID == localXid.getFormatId()) && (Arrays.equals(this.txID, localXid.getGlobalTransactionId())) && (Arrays.equals(this.txBranch, localXid.getBranchQualifier()));
    }
    return false;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(512);
    localStringBuffer.append("formatId=").append(getFormatId());
    localStringBuffer.append(" globalTransactionId(").append(this.txID.length).append(")={0x");
    int j;
    for (int i = 0; i < this.txID.length; i++)
    {
      j = this.txID[i] & 0xFF;
      if (j < 16) {
        localStringBuffer.append("0").append(Integer.toHexString(this.txID[i] & 0xFF));
      }
      localStringBuffer.append(Integer.toHexString(this.txID[i] & 0xFF));
    }
    localStringBuffer.append("} branchQualifier(").append(this.txBranch.length).append("))={0x");
    for (i = 0; i < this.txBranch.length; i++)
    {
      j = this.txBranch[i] & 0xFF;
      if (j < 16) {
        localStringBuffer.append("0");
      }
      localStringBuffer.append(Integer.toHexString(this.txBranch[i] & 0xFF));
    }
    localStringBuffer.append("}");
    return localStringBuffer.toString();
  }
  
  private static int nextTxnSequenceNumber()
  {
    s_txnSequenceNumber += 1;
    return s_txnSequenceNumber;
  }
  
  private static byte[] getLocalIp()
  {
    if (null == s_localIp) {
      try
      {
        s_localIp = Inet4Address.getLocalHost().getAddress();
      }
      catch (Exception localException)
      {
        s_localIp = new byte[] { 127, 0, 0, 1 };
      }
    }
    return s_localIp;
  }
  
  public static Xid getUniqueXid(int paramInt)
  {
    Random localRandom = new Random(System.currentTimeMillis());
    int i = nextTxnSequenceNumber();
    int j = paramInt;
    int k = localRandom.nextInt();
    byte[] arrayOfByte1 = new byte[64];
    byte[] arrayOfByte2 = new byte[64];
    byte[] arrayOfByte3 = getLocalIp();
    System.arraycopy(arrayOfByte3, 0, arrayOfByte1, 0, 4);
    System.arraycopy(arrayOfByte3, 0, arrayOfByte2, 0, 4);
    for (int m = 0; m <= 3; m++)
    {
      arrayOfByte1[(m + 4)] = ((byte)(i % 256));
      arrayOfByte2[(m + 4)] = ((byte)(i % 256));
      i >>= 8;
      arrayOfByte1[(m + 8)] = ((byte)(j % 256));
      arrayOfByte2[(m + 8)] = ((byte)(j % 256));
      j >>= 8;
      arrayOfByte1[(m + 12)] = ((byte)(k % 256));
      arrayOfByte2[(m + 12)] = ((byte)(k % 256));
      k >>= 8;
    }
    return new JDBCXID(65261, arrayOfByte1, arrayOfByte2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.jdbc.pool.JDBCXID
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */