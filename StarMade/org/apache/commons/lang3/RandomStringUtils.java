/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*   3:    */import java.util.Random;
/*   4:    */
/*  41:    */public class RandomStringUtils
/*  42:    */{
/*  43: 43 */  private static final Random RANDOM = new Random();
/*  44:    */  
/*  67:    */  public static String random(int count)
/*  68:    */  {
/*  69: 69 */    return random(count, false, false);
/*  70:    */  }
/*  71:    */  
/*  81:    */  public static String randomAscii(int count)
/*  82:    */  {
/*  83: 83 */    return random(count, 32, 127, false, false);
/*  84:    */  }
/*  85:    */  
/*  95:    */  public static String randomAlphabetic(int count)
/*  96:    */  {
/*  97: 97 */    return random(count, true, false);
/*  98:    */  }
/*  99:    */  
/* 109:    */  public static String randomAlphanumeric(int count)
/* 110:    */  {
/* 111:111 */    return random(count, true, true);
/* 112:    */  }
/* 113:    */  
/* 123:    */  public static String randomNumeric(int count)
/* 124:    */  {
/* 125:125 */    return random(count, false, true);
/* 126:    */  }
/* 127:    */  
/* 141:    */  public static String random(int count, boolean letters, boolean numbers)
/* 142:    */  {
/* 143:143 */    return random(count, 0, 0, letters, numbers);
/* 144:    */  }
/* 145:    */  
/* 161:    */  public static String random(int count, int start, int end, boolean letters, boolean numbers)
/* 162:    */  {
/* 163:163 */    return random(count, start, end, letters, numbers, null, RANDOM);
/* 164:    */  }
/* 165:    */  
/* 185:    */  public static String random(int count, int start, int end, boolean letters, boolean numbers, char... chars)
/* 186:    */  {
/* 187:187 */    return random(count, start, end, letters, numbers, chars, RANDOM);
/* 188:    */  }
/* 189:    */  
/* 223:    */  public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars, Random random)
/* 224:    */  {
/* 225:225 */    if (count == 0)
/* 226:226 */      return "";
/* 227:227 */    if (count < 0) {
/* 228:228 */      throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
/* 229:    */    }
/* 230:230 */    if ((start == 0) && (end == 0)) {
/* 231:231 */      end = 123;
/* 232:232 */      start = 32;
/* 233:233 */      if ((!letters) && (!numbers)) {
/* 234:234 */        start = 0;
/* 235:235 */        end = 2147483647;
/* 236:    */      }
/* 237:    */    }
/* 238:    */    
/* 239:239 */    char[] buffer = new char[count];
/* 240:240 */    int gap = end - start;
/* 241:    */    
/* 242:242 */    while (count-- != 0) { char ch;
/* 243:    */      char ch;
/* 244:244 */      if (chars == null) {
/* 245:245 */        ch = (char)(random.nextInt(gap) + start);
/* 246:    */      } else {
/* 247:247 */        ch = chars[(random.nextInt(gap) + start)];
/* 248:    */      }
/* 249:249 */      if (((letters) && (Character.isLetter(ch))) || ((numbers) && (Character.isDigit(ch))) || ((!letters) && (!numbers)))
/* 250:    */      {
/* 252:252 */        if ((ch >= 56320) && (ch <= 57343)) {
/* 253:253 */          if (count == 0) {
/* 254:254 */            count++;
/* 255:    */          }
/* 256:    */          else {
/* 257:257 */            buffer[count] = ch;
/* 258:258 */            count--;
/* 259:259 */            buffer[count] = ((char)(55296 + random.nextInt(128)));
/* 260:    */          }
/* 261:261 */        } else if ((ch >= 55296) && (ch <= 56191)) {
/* 262:262 */          if (count == 0) {
/* 263:263 */            count++;
/* 264:    */          }
/* 265:    */          else {
/* 266:266 */            buffer[count] = ((char)(56320 + random.nextInt(128)));
/* 267:267 */            count--;
/* 268:268 */            buffer[count] = ch;
/* 269:    */          }
/* 270:270 */        } else if ((ch >= 56192) && (ch <= 56319))
/* 271:    */        {
/* 272:272 */          count++;
/* 273:    */        } else {
/* 274:274 */          buffer[count] = ch;
/* 275:    */        }
/* 276:    */      } else {
/* 277:277 */        count++;
/* 278:    */      }
/* 279:    */    }
/* 280:280 */    return new String(buffer);
/* 281:    */  }
/* 282:    */  
/* 295:    */  public static String random(int count, String chars)
/* 296:    */  {
/* 297:297 */    if (chars == null) {
/* 298:298 */      return random(count, 0, 0, false, false, null, RANDOM);
/* 299:    */    }
/* 300:300 */    return random(count, chars.toCharArray());
/* 301:    */  }
/* 302:    */  
/* 314:    */  public static String random(int count, char... chars)
/* 315:    */  {
/* 316:316 */    if (chars == null) {
/* 317:317 */      return random(count, 0, 0, false, false, null, RANDOM);
/* 318:    */    }
/* 319:319 */    return random(count, 0, chars.length, false, false, chars, RANDOM);
/* 320:    */  }
/* 321:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.RandomStringUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */