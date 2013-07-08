package org.jasypt.encryption.pbe;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

final class NumberUtils
{
  static byte[] byteArrayFromInt(int number)
  {
    byte local_b0 = (byte)(0xFF & number);
    byte local_b1 = (byte)(0xFF & number >> 8);
    byte local_b2 = (byte)(0xFF & number >> 16);
    byte local_b3 = (byte)(0xFF & number >> 24);
    return new byte[] { local_b3, local_b2, local_b1, local_b0 };
  }
  
  static int intFromByteArray(byte[] byteArray)
  {
    if ((byteArray == null) || (byteArray.length == 0)) {
      throw new IllegalArgumentException("Cannot convert an empty array into an int");
    }
    int result = 0xFF & byteArray[0];
    for (int local_i = 1; local_i < byteArray.length; local_i++) {
      result = result << 8 | 0xFF & byteArray[local_i];
    }
    return result;
  }
  
  static byte[] processBigIntegerEncryptedByteArray(byte[] byteArray, int signum)
  {
    if (byteArray.length > 4)
    {
      int initialSize = byteArray.length;
      byte[] encryptedMessageExpectedSizeBytes = new byte[4];
      System.arraycopy(byteArray, initialSize - 4, encryptedMessageExpectedSizeBytes, 0, 4);
      byte[] processedByteArray = new byte[initialSize - 4];
      System.arraycopy(byteArray, 0, processedByteArray, 0, initialSize - 4);
      int expectedSize = intFromByteArray(encryptedMessageExpectedSizeBytes);
      if ((expectedSize < 0) || (expectedSize > maxSafeSizeInBytes())) {
        throw new EncryptionOperationNotPossibleException();
      }
      if (processedByteArray.length != expectedSize)
      {
        int sizeDifference = expectedSize - processedByteArray.length;
        byte[] paddedProcessedByteArray = new byte[expectedSize];
        for (int local_i = 0; local_i < sizeDifference; local_i++) {
          paddedProcessedByteArray[local_i] = (signum >= 0 ? 0 : -1);
        }
        System.arraycopy(processedByteArray, 0, paddedProcessedByteArray, sizeDifference, processedByteArray.length);
        return paddedProcessedByteArray;
      }
      return processedByteArray;
    }
    return (byte[])byteArray.clone();
  }
  
  private static int maxSafeSizeInBytes()
  {
    long max = Runtime.getRuntime().maxMemory();
    long free = Runtime.getRuntime().freeMemory();
    long total = Runtime.getRuntime().totalMemory();
    return (int)((free + (max - total)) / 2L);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.encryption.pbe.NumberUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */