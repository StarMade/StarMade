/*     */ package org.jasypt.commons;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*     */ 
/*     */ public final class CommonUtils
/*     */ {
/*     */   public static final String STRING_OUTPUT_TYPE_BASE64 = "base64";
/*     */   public static final String STRING_OUTPUT_TYPE_HEXADECIMAL = "hexadecimal";
/*  44 */   private static final List STRING_OUTPUT_TYPE_HEXADECIMAL_NAMES = Arrays.asList(new String[] { "HEXADECIMAL", "HEXA", "0X", "HEX", "HEXADEC" });
/*     */ 
/*  51 */   private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */ 
/*     */   public static Boolean getStandardBooleanValue(String valueStr)
/*     */   {
/*  58 */     if (valueStr == null) {
/*  59 */       return null;
/*     */     }
/*  61 */     String upperValue = valueStr.toUpperCase();
/*  62 */     if (("TRUE".equals(upperValue)) || ("ON".equals(upperValue)) || ("YES".equals(upperValue))) {
/*  63 */       return Boolean.TRUE;
/*     */     }
/*  65 */     if (("FALSE".equals(upperValue)) || ("OFF".equals(upperValue)) || ("NO".equals(upperValue))) {
/*  66 */       return Boolean.FALSE;
/*     */     }
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getStandardStringOutputType(String valueStr)
/*     */   {
/*  73 */     if (valueStr == null) {
/*  74 */       return null;
/*     */     }
/*  76 */     if (STRING_OUTPUT_TYPE_HEXADECIMAL_NAMES.contains(valueStr.toUpperCase())) {
/*  77 */       return "hexadecimal";
/*     */     }
/*  79 */     return "base64";
/*     */   }
/*     */ 
/*     */   public static String toHexadecimal(byte[] message)
/*     */   {
/*  84 */     if (message == null) {
/*  85 */       return null;
/*     */     }
/*  87 */     StringBuffer buffer = new StringBuffer();
/*  88 */     for (int i = 0; i < message.length; i++) {
/*  89 */       int curByte = message[i] & 0xFF;
/*  90 */       buffer.append(hexDigits[(curByte >> 4)]);
/*  91 */       buffer.append(hexDigits[(curByte & 0xF)]);
/*     */     }
/*  93 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   public static byte[] fromHexadecimal(String message)
/*     */   {
/*  98 */     if (message == null) {
/*  99 */       return null;
/*     */     }
/* 101 */     if (message.length() % 2 != 0)
/* 102 */       throw new EncryptionOperationNotPossibleException();
/*     */     try
/*     */     {
/* 105 */       byte[] result = new byte[message.length() / 2];
/* 106 */       for (int i = 0; i < message.length(); i += 2) {
/* 107 */         int first = Integer.parseInt("" + message.charAt(i), 16);
/* 108 */         int second = Integer.parseInt("" + message.charAt(i + 1), 16);
/* 109 */         result[(i / 2)] = ((byte)(0 + ((first & 0xFF) << 4) + (second & 0xFF)));
/*     */       }
/* 111 */       return result; } catch (Exception e) {
/*     */     }
/* 113 */     throw new EncryptionOperationNotPossibleException();
/*     */   }
/*     */ 
/*     */   public static boolean isEmpty(String string)
/*     */   {
/* 119 */     if ((string == null) || (string.length() == 0)) {
/* 120 */       return true;
/*     */     }
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isNotEmpty(String string)
/*     */   {
/* 127 */     if ((string == null) || (string.length() == 0)) {
/* 128 */       return false;
/*     */     }
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */   public static void validateNotNull(Object object, String message)
/*     */   {
/* 135 */     if (object == null)
/* 136 */       throw new IllegalArgumentException(message);
/*     */   }
/*     */ 
/*     */   public static void validateNotEmpty(String string, String message)
/*     */   {
/* 142 */     if (isEmpty(string))
/* 143 */       throw new IllegalArgumentException(message);
/*     */   }
/*     */ 
/*     */   public static void validateIsTrue(boolean expression, String message)
/*     */   {
/* 149 */     if (!expression)
/* 150 */       throw new IllegalArgumentException(message);
/*     */   }
/*     */ 
/*     */   public static String[] split(String string)
/*     */   {
/* 159 */     return split(string, null);
/*     */   }
/*     */ 
/*     */   public static String[] split(String string, String separators)
/*     */   {
/* 165 */     if (string == null) {
/* 166 */       return null;
/*     */     }
/*     */ 
/* 169 */     int length = string.length();
/*     */ 
/* 171 */     if (length == 0) {
/* 172 */       return new String[0];
/*     */     }
/*     */ 
/* 175 */     List results = new ArrayList();
/* 176 */     int i = 0;
/* 177 */     int start = 0;
/* 178 */     boolean tokenInProgress = false;
/*     */ 
/* 180 */     if (separators == null)
/*     */     {
/* 182 */       while (i < length)
/* 183 */         if (Character.isWhitespace(string.charAt(i))) {
/* 184 */           if (tokenInProgress) {
/* 185 */             results.add(string.substring(start, i));
/* 186 */             tokenInProgress = false;
/*     */           }
/* 188 */           i++; start = i;
/*     */         }
/*     */         else {
/* 191 */           tokenInProgress = true;
/* 192 */           i++;
/*     */         }
/*     */     }
/* 195 */     if (separators.length() == 1)
/*     */     {
/* 197 */       char separator = separators.charAt(0);
/* 198 */       while (i < length)
/* 199 */         if (string.charAt(i) == separator) {
/* 200 */           if (tokenInProgress) {
/* 201 */             results.add(string.substring(start, i));
/* 202 */             tokenInProgress = false;
/*     */           }
/* 204 */           i++; start = i;
/*     */         }
/*     */         else {
/* 207 */           tokenInProgress = true;
/* 208 */           i++;
/*     */         }
/*     */     }
/*     */     else
/*     */     {
/* 213 */       while (i < length) {
/* 214 */         if (separators.indexOf(string.charAt(i)) >= 0) {
/* 215 */           if (tokenInProgress) {
/* 216 */             results.add(string.substring(start, i));
/* 217 */             tokenInProgress = false;
/*     */           }
/* 219 */           i++; start = i;
/*     */         }
/*     */         else {
/* 222 */           tokenInProgress = true;
/* 223 */           i++;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 228 */     if (tokenInProgress) {
/* 229 */       results.add(string.substring(start, i));
/*     */     }
/*     */ 
/* 232 */     return (String[])results.toArray(new String[results.size()]);
/*     */   }
/*     */ 
/*     */   public static String substringBefore(String string, String separator)
/*     */   {
/* 241 */     if ((isEmpty(string)) || (separator == null)) {
/* 242 */       return string;
/*     */     }
/* 244 */     if (separator.length() == 0) {
/* 245 */       return "";
/*     */     }
/* 247 */     int pos = string.indexOf(separator);
/* 248 */     if (pos == -1) {
/* 249 */       return string;
/*     */     }
/* 251 */     return string.substring(0, pos);
/*     */   }
/*     */ 
/*     */   public static String substringAfter(String string, String separator)
/*     */   {
/* 258 */     if (isEmpty(string)) {
/* 259 */       return string;
/*     */     }
/* 261 */     if (separator == null) {
/* 262 */       return "";
/*     */     }
/* 264 */     int pos = string.indexOf(separator);
/* 265 */     if (pos == -1) {
/* 266 */       return "";
/*     */     }
/* 268 */     return string.substring(pos + separator.length());
/*     */   }
/*     */ 
/*     */   public static int nextRandomInt()
/*     */   {
/* 274 */     return (int)(Math.random() * 2147483647.0D);
/*     */   }
/*     */ 
/*     */   public static byte[] appendArrays(byte[] firstArray, byte[] secondArray)
/*     */   {
/* 281 */     validateNotNull(firstArray, "Appended array cannot be null");
/* 282 */     validateNotNull(secondArray, "Appended array cannot be null");
/*     */ 
/* 284 */     byte[] result = new byte[firstArray.length + secondArray.length];
/*     */ 
/* 286 */     System.arraycopy(firstArray, 0, result, 0, firstArray.length);
/* 287 */     System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
/*     */ 
/* 289 */     return result;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.commons.CommonUtils
 * JD-Core Version:    0.6.2
 */