/*   1:    */package org.jasypt.commons;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Arrays;
/*   5:    */import java.util.List;
/*   6:    */import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
/*   7:    */
/*  40:    */public final class CommonUtils
/*  41:    */{
/*  42:    */  public static final String STRING_OUTPUT_TYPE_BASE64 = "base64";
/*  43:    */  public static final String STRING_OUTPUT_TYPE_HEXADECIMAL = "hexadecimal";
/*  44: 44 */  private static final List STRING_OUTPUT_TYPE_HEXADECIMAL_NAMES = Arrays.asList(new String[] { "HEXADECIMAL", "HEXA", "0X", "HEX", "HEXADEC" });
/*  45:    */  
/*  51: 51 */  private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*  52:    */  
/*  56:    */  public static Boolean getStandardBooleanValue(String valueStr)
/*  57:    */  {
/*  58: 58 */    if (valueStr == null) {
/*  59: 59 */      return null;
/*  60:    */    }
/*  61: 61 */    String upperValue = valueStr.toUpperCase();
/*  62: 62 */    if (("TRUE".equals(upperValue)) || ("ON".equals(upperValue)) || ("YES".equals(upperValue))) {
/*  63: 63 */      return Boolean.TRUE;
/*  64:    */    }
/*  65: 65 */    if (("FALSE".equals(upperValue)) || ("OFF".equals(upperValue)) || ("NO".equals(upperValue))) {
/*  66: 66 */      return Boolean.FALSE;
/*  67:    */    }
/*  68: 68 */    return null;
/*  69:    */  }
/*  70:    */  
/*  71:    */  public static String getStandardStringOutputType(String valueStr)
/*  72:    */  {
/*  73: 73 */    if (valueStr == null) {
/*  74: 74 */      return null;
/*  75:    */    }
/*  76: 76 */    if (STRING_OUTPUT_TYPE_HEXADECIMAL_NAMES.contains(valueStr.toUpperCase())) {
/*  77: 77 */      return "hexadecimal";
/*  78:    */    }
/*  79: 79 */    return "base64";
/*  80:    */  }
/*  81:    */  
/*  82:    */  public static String toHexadecimal(byte[] message)
/*  83:    */  {
/*  84: 84 */    if (message == null) {
/*  85: 85 */      return null;
/*  86:    */    }
/*  87: 87 */    StringBuffer buffer = new StringBuffer();
/*  88: 88 */    for (int i = 0; i < message.length; i++) {
/*  89: 89 */      int curByte = message[i] & 0xFF;
/*  90: 90 */      buffer.append(hexDigits[(curByte >> 4)]);
/*  91: 91 */      buffer.append(hexDigits[(curByte & 0xF)]);
/*  92:    */    }
/*  93: 93 */    return buffer.toString();
/*  94:    */  }
/*  95:    */  
/*  96:    */  public static byte[] fromHexadecimal(String message)
/*  97:    */  {
/*  98: 98 */    if (message == null) {
/*  99: 99 */      return null;
/* 100:    */    }
/* 101:101 */    if (message.length() % 2 != 0) {
/* 102:102 */      throw new EncryptionOperationNotPossibleException();
/* 103:    */    }
/* 104:    */    try {
/* 105:105 */      byte[] result = new byte[message.length() / 2];
/* 106:106 */      for (int i = 0; i < message.length(); i += 2) {
/* 107:107 */        int first = Integer.parseInt("" + message.charAt(i), 16);
/* 108:108 */        int second = Integer.parseInt("" + message.charAt(i + 1), 16);
/* 109:109 */        result[(i / 2)] = ((byte)(0 + ((first & 0xFF) << 4) + (second & 0xFF)));
/* 110:    */      }
/* 111:111 */      return result;
/* 112:    */    } catch (Exception e) {
/* 113:113 */      throw new EncryptionOperationNotPossibleException();
/* 114:    */    }
/* 115:    */  }
/* 116:    */  
/* 117:    */  public static boolean isEmpty(String string)
/* 118:    */  {
/* 119:119 */    if ((string == null) || (string.length() == 0)) {
/* 120:120 */      return true;
/* 121:    */    }
/* 122:122 */    return false;
/* 123:    */  }
/* 124:    */  
/* 125:    */  public static boolean isNotEmpty(String string)
/* 126:    */  {
/* 127:127 */    if ((string == null) || (string.length() == 0)) {
/* 128:128 */      return false;
/* 129:    */    }
/* 130:130 */    return true;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public static void validateNotNull(Object object, String message)
/* 134:    */  {
/* 135:135 */    if (object == null) {
/* 136:136 */      throw new IllegalArgumentException(message);
/* 137:    */    }
/* 138:    */  }
/* 139:    */  
/* 140:    */  public static void validateNotEmpty(String string, String message)
/* 141:    */  {
/* 142:142 */    if (isEmpty(string)) {
/* 143:143 */      throw new IllegalArgumentException(message);
/* 144:    */    }
/* 145:    */  }
/* 146:    */  
/* 147:    */  public static void validateIsTrue(boolean expression, String message)
/* 148:    */  {
/* 149:149 */    if (!expression) {
/* 150:150 */      throw new IllegalArgumentException(message);
/* 151:    */    }
/* 152:    */  }
/* 153:    */  
/* 157:    */  public static String[] split(String string)
/* 158:    */  {
/* 159:159 */    return split(string, null);
/* 160:    */  }
/* 161:    */  
/* 163:    */  public static String[] split(String string, String separators)
/* 164:    */  {
/* 165:165 */    if (string == null) {
/* 166:166 */      return null;
/* 167:    */    }
/* 168:    */    
/* 169:169 */    int length = string.length();
/* 170:    */    
/* 171:171 */    if (length == 0) {
/* 172:172 */      return new String[0];
/* 173:    */    }
/* 174:    */    
/* 175:175 */    List results = new ArrayList();
/* 176:176 */    int i = 0;
/* 177:177 */    int start = 0;
/* 178:178 */    boolean tokenInProgress = false;
/* 179:    */    
/* 180:180 */    if (separators == null)
/* 181:    */    {
/* 182:182 */      while (i < length)
/* 183:183 */        if (Character.isWhitespace(string.charAt(i))) {
/* 184:184 */          if (tokenInProgress) {
/* 185:185 */            results.add(string.substring(start, i));
/* 186:186 */            tokenInProgress = false;
/* 187:    */          }
/* 188:188 */          i++;start = i;
/* 189:    */        }
/* 190:    */        else {
/* 191:191 */          tokenInProgress = true;
/* 192:192 */          i++;
/* 193:    */        }
/* 194:    */    }
/* 195:195 */    if (separators.length() == 1)
/* 196:    */    {
/* 197:197 */      char separator = separators.charAt(0);
/* 198:198 */      while (i < length) {
/* 199:199 */        if (string.charAt(i) == separator) {
/* 200:200 */          if (tokenInProgress) {
/* 201:201 */            results.add(string.substring(start, i));
/* 202:202 */            tokenInProgress = false;
/* 203:    */          }
/* 204:204 */          i++;start = i;
/* 205:    */        }
/* 206:    */        else {
/* 207:207 */          tokenInProgress = true;
/* 208:208 */          i++;
/* 209:    */        }
/* 210:    */      }
/* 211:    */    }
/* 212:    */    else {
/* 213:213 */      while (i < length) {
/* 214:214 */        if (separators.indexOf(string.charAt(i)) >= 0) {
/* 215:215 */          if (tokenInProgress) {
/* 216:216 */            results.add(string.substring(start, i));
/* 217:217 */            tokenInProgress = false;
/* 218:    */          }
/* 219:219 */          i++;start = i;
/* 220:    */        }
/* 221:    */        else {
/* 222:222 */          tokenInProgress = true;
/* 223:223 */          i++;
/* 224:    */        }
/* 225:    */      }
/* 226:    */    }
/* 227:    */    
/* 228:228 */    if (tokenInProgress) {
/* 229:229 */      results.add(string.substring(start, i));
/* 230:    */    }
/* 231:    */    
/* 232:232 */    return (String[])results.toArray(new String[results.size()]);
/* 233:    */  }
/* 234:    */  
/* 239:    */  public static String substringBefore(String string, String separator)
/* 240:    */  {
/* 241:241 */    if ((isEmpty(string)) || (separator == null)) {
/* 242:242 */      return string;
/* 243:    */    }
/* 244:244 */    if (separator.length() == 0) {
/* 245:245 */      return "";
/* 246:    */    }
/* 247:247 */    int pos = string.indexOf(separator);
/* 248:248 */    if (pos == -1) {
/* 249:249 */      return string;
/* 250:    */    }
/* 251:251 */    return string.substring(0, pos);
/* 252:    */  }
/* 253:    */  
/* 256:    */  public static String substringAfter(String string, String separator)
/* 257:    */  {
/* 258:258 */    if (isEmpty(string)) {
/* 259:259 */      return string;
/* 260:    */    }
/* 261:261 */    if (separator == null) {
/* 262:262 */      return "";
/* 263:    */    }
/* 264:264 */    int pos = string.indexOf(separator);
/* 265:265 */    if (pos == -1) {
/* 266:266 */      return "";
/* 267:    */    }
/* 268:268 */    return string.substring(pos + separator.length());
/* 269:    */  }
/* 270:    */  
/* 272:    */  public static int nextRandomInt()
/* 273:    */  {
/* 274:274 */    return (int)(Math.random() * 2147483647.0D);
/* 275:    */  }
/* 276:    */  
/* 279:    */  public static byte[] appendArrays(byte[] firstArray, byte[] secondArray)
/* 280:    */  {
/* 281:281 */    validateNotNull(firstArray, "Appended array cannot be null");
/* 282:282 */    validateNotNull(secondArray, "Appended array cannot be null");
/* 283:    */    
/* 284:284 */    byte[] result = new byte[firstArray.length + secondArray.length];
/* 285:    */    
/* 286:286 */    System.arraycopy(firstArray, 0, result, 0, firstArray.length);
/* 287:287 */    System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
/* 288:    */    
/* 289:289 */    return result;
/* 290:    */  }
/* 291:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jasypt.commons.CommonUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */