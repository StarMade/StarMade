/*   1:    */package org.jasypt.normalization;
/*   2:    */
/*   3:    */import java.text.Normalizer.Form;
/*   4:    */import org.jasypt.exceptions.EncryptionInitializationException;
/*   5:    */
/*  42:    */public final class Normalizer
/*  43:    */{
/*  44:    */  private static final String ICU_NORMALIZER_CLASS_NAME = "com.ibm.icu.text.Normalizer";
/*  45:    */  private static final String JDK_NORMALIZER_CLASS_NAME = "java.text.Normalizer";
/*  46: 46 */  private static Boolean useIcuNormalizer = null;
/*  47:    */  
/*  62:    */  public static String normalizeToNfc(String message)
/*  63:    */  {
/*  64: 64 */    return new String(normalizeToNfc(message.toCharArray()));
/*  65:    */  }
/*  66:    */  
/*  82:    */  public static char[] normalizeToNfc(char[] message)
/*  83:    */  {
/*  84: 84 */    if (useIcuNormalizer == null)
/*  85:    */    {
/*  86:    */      try
/*  87:    */      {
/*  88: 88 */        Thread.currentThread().getContextClassLoader().loadClass("com.ibm.icu.text.Normalizer");
/*  89: 89 */        useIcuNormalizer = Boolean.TRUE;
/*  90:    */      } catch (ClassNotFoundException e) {
/*  91:    */        try {
/*  92: 92 */          Thread.currentThread().getContextClassLoader().loadClass("java.text.Normalizer");
/*  93:    */        } catch (ClassNotFoundException e2) {
/*  94: 94 */          throw new EncryptionInitializationException("Cannot find a valid UNICODE normalizer: neither java.text.Normalizer nor com.ibm.icu.text.Normalizer have been found at the classpath. If you are using a version of the JDK older than JavaSE 6, you should include the icu4j library in your classpath.");
/*  95:    */        }
/*  96:    */        
/* 100:100 */        useIcuNormalizer = Boolean.FALSE;
/* 101:    */      }
/* 102:    */    }
/* 103:    */    
/* 104:104 */    if (useIcuNormalizer.booleanValue()) {
/* 105:105 */      return normalizeWithIcu4j(message);
/* 106:    */    }
/* 107:    */    
/* 108:108 */    return normalizeWithJavaNormalizer(message);
/* 109:    */  }
/* 110:    */  
/* 116:    */  private static char[] normalizeWithJavaNormalizer(char[] message)
/* 117:    */  {
/* 118:118 */    String messageStr = new String(message);
/* 119:119 */    String result = java.text.Normalizer.normalize(messageStr, Normalizer.Form.NFC);
/* 120:    */    
/* 121:121 */    return result.toCharArray();
/* 122:    */  }
/* 123:    */  
/* 127:    */  private static char[] normalizeWithIcu4j(char[] message)
/* 128:    */  {
/* 129:129 */    char[] normalizationResult = new char[message.length * 2];
/* 130:130 */    int normalizationResultSize = 0;
/* 131:    */    
/* 135:    */    for (;;)
/* 136:    */    {
/* 137:137 */      normalizationResultSize = com.ibm.icu.text.Normalizer.normalize(message, normalizationResult, com.ibm.icu.text.Normalizer.NFC, 0);
/* 138:    */      
/* 139:139 */      if (normalizationResultSize <= normalizationResult.length)
/* 140:    */      {
/* 142:142 */        char[] result = new char[normalizationResultSize];
/* 143:143 */        System.arraycopy(normalizationResult, 0, result, 0, normalizationResultSize);
/* 144:144 */        for (int i = 0; i < normalizationResult.length; i++) {
/* 145:145 */          normalizationResult[i] = '\000';
/* 146:    */        }
/* 147:147 */        return result;
/* 148:    */      }
/* 149:    */      
/* 150:150 */      for (int i = 0; i < normalizationResult.length; i++) {
/* 151:151 */        normalizationResult[i] = '\000';
/* 152:    */      }
/* 153:153 */      normalizationResult = new char[normalizationResultSize];
/* 154:    */    }
/* 155:    */  }
/* 156:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.normalization.Normalizer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */