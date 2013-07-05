/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class RandomStringUtils
/*     */ {
/*  43 */   private static final Random RANDOM = new Random();
/*     */ 
/*     */   public static String random(int count)
/*     */   {
/*  69 */     return random(count, false, false);
/*     */   }
/*     */ 
/*     */   public static String randomAscii(int count)
/*     */   {
/*  83 */     return random(count, 32, 127, false, false);
/*     */   }
/*     */ 
/*     */   public static String randomAlphabetic(int count)
/*     */   {
/*  97 */     return random(count, true, false);
/*     */   }
/*     */ 
/*     */   public static String randomAlphanumeric(int count)
/*     */   {
/* 111 */     return random(count, true, true);
/*     */   }
/*     */ 
/*     */   public static String randomNumeric(int count)
/*     */   {
/* 125 */     return random(count, false, true);
/*     */   }
/*     */ 
/*     */   public static String random(int count, boolean letters, boolean numbers)
/*     */   {
/* 143 */     return random(count, 0, 0, letters, numbers);
/*     */   }
/*     */ 
/*     */   public static String random(int count, int start, int end, boolean letters, boolean numbers)
/*     */   {
/* 163 */     return random(count, start, end, letters, numbers, null, RANDOM);
/*     */   }
/*     */ 
/*     */   public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars)
/*     */   {
/* 187 */     return random(count, start, end, letters, numbers, chars, RANDOM);
/*     */   }
/*     */ 
/*     */   public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars, Random random)
/*     */   {
/* 225 */     if (count == 0)
/* 226 */       return "";
/* 227 */     if (count < 0) {
/* 228 */       throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
/*     */     }
/* 230 */     if ((start == 0) && (end == 0)) {
/* 231 */       end = 123;
/* 232 */       start = 32;
/* 233 */       if ((!letters) && (!numbers)) {
/* 234 */         start = 0;
/* 235 */         end = 2147483647;
/*     */       }
/*     */     }
/*     */ 
/* 239 */     char[] buffer = new char[count];
/* 240 */     int gap = end - start;
/*     */ 
/* 242 */     while (count-- != 0)
/*     */     {
/*     */       char ch;
/*     */       char ch;
/* 244 */       if (chars == null)
/* 245 */         ch = (char)(random.nextInt(gap) + start);
/*     */       else {
/* 247 */         ch = chars[(random.nextInt(gap) + start)];
/*     */       }
/* 249 */       if (((letters) && (Character.isLetter(ch))) || ((numbers) && (Character.isDigit(ch))) || ((!letters) && (!numbers)))
/*     */       {
/* 252 */         if ((ch >= 56320) && (ch <= 57343)) {
/* 253 */           if (count == 0) {
/* 254 */             count++;
/*     */           }
/*     */           else {
/* 257 */             buffer[count] = ch;
/* 258 */             count--;
/* 259 */             buffer[count] = ((char)(55296 + random.nextInt(128)));
/*     */           }
/* 261 */         } else if ((ch >= 55296) && (ch <= 56191)) {
/* 262 */           if (count == 0) {
/* 263 */             count++;
/*     */           }
/*     */           else {
/* 266 */             buffer[count] = ((char)(56320 + random.nextInt(128)));
/* 267 */             count--;
/* 268 */             buffer[count] = ch;
/*     */           }
/* 270 */         } else if ((ch >= 56192) && (ch <= 56319))
/*     */         {
/* 272 */           count++;
/*     */         }
/* 274 */         else buffer[count] = ch;
/*     */       }
/*     */       else {
/* 277 */         count++;
/*     */       }
/*     */     }
/* 280 */     return new String(buffer);
/*     */   }
/*     */ 
/*     */   public static String random(int count, String chars)
/*     */   {
/* 297 */     if (chars == null) {
/* 298 */       return random(count, 0, 0, false, false, null, RANDOM);
/*     */     }
/* 300 */     return random(count, chars.toCharArray());
/*     */   }
/*     */ 
/*     */   public static String random(int count, char[] chars)
/*     */   {
/* 316 */     if (chars == null) {
/* 317 */       return random(count, 0, 0, false, false, null, RANDOM);
/*     */     }
/* 319 */     return random(count, 0, chars.length, false, false, chars, RANDOM);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.RandomStringUtils
 * JD-Core Version:    0.6.2
 */