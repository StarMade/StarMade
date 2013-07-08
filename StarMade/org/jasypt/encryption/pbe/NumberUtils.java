/*   1:    */package org.jasypt.encryption.pbe;
/*   2:    */
/*   3:    */import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*   4:    */
/*  35:    */final class NumberUtils
/*  36:    */{
/*  37:    */  static byte[] byteArrayFromInt(int number)
/*  38:    */  {
/*  39: 39 */    byte b0 = (byte)(0xFF & number);
/*  40: 40 */    byte b1 = (byte)(0xFF & number >> 8);
/*  41: 41 */    byte b2 = (byte)(0xFF & number >> 16);
/*  42: 42 */    byte b3 = (byte)(0xFF & number >> 24);
/*  43: 43 */    return new byte[] { b3, b2, b1, b0 };
/*  44:    */  }
/*  45:    */  
/*  47:    */  static int intFromByteArray(byte[] byteArray)
/*  48:    */  {
/*  49: 49 */    if ((byteArray == null) || (byteArray.length == 0)) {
/*  50: 50 */      throw new IllegalArgumentException("Cannot convert an empty array into an int");
/*  51:    */    }
/*  52:    */    
/*  53: 53 */    int result = 0xFF & byteArray[0];
/*  54: 54 */    for (int i = 1; i < byteArray.length; i++) {
/*  55: 55 */      result = result << 8 | 0xFF & byteArray[i];
/*  56:    */    }
/*  57:    */    
/*  58: 58 */    return result;
/*  59:    */  }
/*  60:    */  
/*  64:    */  static byte[] processBigIntegerEncryptedByteArray(byte[] byteArray, int signum)
/*  65:    */  {
/*  66: 66 */    if (byteArray.length > 4)
/*  67:    */    {
/*  68: 68 */      int initialSize = byteArray.length;
/*  69:    */      
/*  70: 70 */      byte[] encryptedMessageExpectedSizeBytes = new byte[4];
/*  71: 71 */      System.arraycopy(byteArray, initialSize - 4, encryptedMessageExpectedSizeBytes, 0, 4);
/*  72:    */      
/*  73: 73 */      byte[] processedByteArray = new byte[initialSize - 4];
/*  74: 74 */      System.arraycopy(byteArray, 0, processedByteArray, 0, initialSize - 4);
/*  75:    */      
/*  76: 76 */      int expectedSize = intFromByteArray(encryptedMessageExpectedSizeBytes);
/*  77:    */      
/*  78: 78 */      if ((expectedSize < 0) || (expectedSize > maxSafeSizeInBytes())) {
/*  79: 79 */        throw new EncryptionOperationNotPossibleException();
/*  80:    */      }
/*  81:    */      
/*  85: 85 */      if (processedByteArray.length != expectedSize)
/*  86:    */      {
/*  90: 90 */        int sizeDifference = expectedSize - processedByteArray.length;
/*  91:    */        
/*  93: 93 */        byte[] paddedProcessedByteArray = new byte[expectedSize];
/*  94: 94 */        for (int i = 0; i < sizeDifference; i++) {
/*  95: 95 */          paddedProcessedByteArray[i] = (signum >= 0 ? 0 : -1);
/*  96:    */        }
/*  97:    */        
/* 101:101 */        System.arraycopy(processedByteArray, 0, paddedProcessedByteArray, sizeDifference, processedByteArray.length);
/* 102:    */        
/* 103:103 */        return paddedProcessedByteArray;
/* 104:    */      }
/* 105:    */      
/* 107:107 */      return processedByteArray;
/* 108:    */    }
/* 109:    */    
/* 111:111 */    return (byte[])byteArray.clone();
/* 112:    */  }
/* 113:    */  
/* 125:    */  private static int maxSafeSizeInBytes()
/* 126:    */  {
/* 127:127 */    long max = Runtime.getRuntime().maxMemory();
/* 128:128 */    long free = Runtime.getRuntime().freeMemory();
/* 129:129 */    long total = Runtime.getRuntime().totalMemory();
/* 130:130 */    return (int)((free + (max - total)) / 2L);
/* 131:    */  }
/* 132:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.encryption.pbe.NumberUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */