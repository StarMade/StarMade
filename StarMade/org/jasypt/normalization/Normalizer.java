package org.jasypt.normalization;

import java.text.Normalizer.Form;
import org.jasypt.exceptions.EncryptionInitializationException;

public final class Normalizer
{
  private static final String ICU_NORMALIZER_CLASS_NAME = "com.ibm.icu.text.Normalizer";
  private static final String JDK_NORMALIZER_CLASS_NAME = "java.text.Normalizer";
  private static Boolean useIcuNormalizer = null;
  
  public static String normalizeToNfc(String message)
  {
    return new String(normalizeToNfc(message.toCharArray()));
  }
  
  public static char[] normalizeToNfc(char[] message)
  {
    if (useIcuNormalizer == null) {
      try
      {
        Thread.currentThread().getContextClassLoader().loadClass("com.ibm.icu.text.Normalizer");
        useIcuNormalizer = Boolean.TRUE;
      }
      catch (ClassNotFoundException local_e)
      {
        try
        {
          Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer");
        }
        catch (ClassNotFoundException local_e2)
        {
          throw new EncryptionInitializationException("Cannot find a valid UNICODE normalizer: neither java.text.Normalizer nor com.ibm.icu.text.Normalizer have been found at the classpath. If you are using a version of the JDK older than JavaSE 6, you should include the icu4j library in your classpath.");
        }
        useIcuNormalizer = Boolean.FALSE;
      }
    }
    if (useIcuNormalizer.booleanValue()) {
      return normalizeWithIcu4j(message);
    }
    return normalizeWithJavaNormalizer(message);
  }
  
  private static char[] normalizeWithJavaNormalizer(char[] message)
  {
    String messageStr = new String(message);
    String result = java.text.Normalizer.normalize(messageStr, Normalizer.Form.NFC);
    return result.toCharArray();
  }
  
  private static char[] normalizeWithIcu4j(char[] message)
  {
    char[] normalizationResult = new char[message.length * 2];
    int normalizationResultSize = 0;
    for (;;)
    {
      normalizationResultSize = com.ibm.icu.text.Normalizer.normalize(message, normalizationResult, com.ibm.icu.text.Normalizer.NFC, 0);
      if (normalizationResultSize <= normalizationResult.length)
      {
        char[] result = new char[normalizationResultSize];
        System.arraycopy(normalizationResult, 0, result, 0, normalizationResultSize);
        for (int local_i = 0; local_i < normalizationResult.length; local_i++) {
          normalizationResult[local_i] = '\000';
        }
        return result;
      }
      for (int result = 0; result < normalizationResult.length; result++) {
        normalizationResult[result] = '\000';
      }
      normalizationResult = new char[normalizationResultSize];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.normalization.Normalizer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */