/*     */ import java.text.DecimalFormat;
/*     */ import java.text.DecimalFormatSymbols;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Random;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public final class i
/*     */ {
/*  77 */   private static DecimalFormat jdField_a_of_type_JavaTextDecimalFormat = new DecimalFormat("0000");
/*  78 */   private static DecimalFormat b = new DecimalFormat("00");
/*  79 */   private static DecimalFormatSymbols jdField_a_of_type_JavaTextDecimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
/*     */ 
/*  84 */   private static DecimalFormat c = new DecimalFormat("#0.0", jdField_a_of_type_JavaTextDecimalFormatSymbols);
/*     */ 
/* 102 */   private static Pattern jdField_a_of_type_JavaUtilRegexPattern = Pattern.compile("\n|\r");
/*     */ 
/* 236 */   private static Random jdField_a_of_type_JavaUtilRandom = new Random();
/*     */ 
/*     */   public static int a(String paramString)
/*     */   {
/* 104 */     paramString = jdField_a_of_type_JavaUtilRegexPattern.matcher(paramString);
/* 105 */     int i = 1;
/* 106 */     while (paramString.find())
/*     */     {
/* 108 */       i++;
/*     */     }
/* 110 */     return i;
/*     */   }
/*     */ 
/*     */   public static String a(int paramInt)
/*     */   {
/* 120 */     return jdField_a_of_type_JavaTextDecimalFormat.format(paramInt);
/*     */   }
/*     */ 
/*     */   public static String b(int paramInt)
/*     */   {
/* 129 */     return b.format(paramInt);
/*     */   }
/*     */   public static String a(float paramFloat) {
/* 132 */     return c.format(paramFloat);
/*     */   }
/*     */   public static String a(double paramDouble) {
/* 135 */     return c.format(paramDouble);
/*     */   }
/*     */ 
/*     */   private static List a(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
/* 139 */     int[][] arrayOfInt = new int[paramArrayOfObject1.length + 1][paramArrayOfObject2.length + 1];
/*     */ 
/* 142 */     for (int i = 1; i <= paramArrayOfObject1.length; i++) {
/* 143 */       for (j = 1; j <= paramArrayOfObject2.length; j++) {
/* 144 */         if (paramArrayOfObject1[(i - 1)].equals(paramArrayOfObject2[(j - 1)]))
/* 145 */           arrayOfInt[i][j] = (1 + arrayOfInt[(i - 1)][(j - 1)]);
/*     */         else {
/* 147 */           arrayOfInt[i][j] = Math.max(arrayOfInt[(i - 1)][j], arrayOfInt[i][(j - 1)]);
/*     */         }
/*     */       }
/*     */     }
/* 151 */     i = paramArrayOfObject1.length; int j = paramArrayOfObject2.length;
/* 152 */     LinkedList localLinkedList = new LinkedList();
/*     */ 
/* 154 */     while ((i != 0) && (j != 0))
/*     */     {
/* 156 */       if (paramArrayOfObject1[(i - 1)].equals(paramArrayOfObject2[(j - 1)]))
/*     */       {
/* 158 */         localLinkedList.add(paramArrayOfObject1[(i - 1)]);
/* 159 */         i--;
/* 160 */         j--;
/*     */       }
/* 162 */       else if (arrayOfInt[i][(j - 1)] >= arrayOfInt[(i - 1)][j])
/*     */       {
/* 164 */         j--;
/*     */       }
/*     */       else
/*     */       {
/* 168 */         i--;
/*     */       }
/*     */     }
/* 171 */     Collections.reverse(localLinkedList);
/* 172 */     return localLinkedList;
/*     */   }
/*     */ 
/*     */   public static String a(String paramString1, String paramString2)
/*     */   {
/* 182 */     paramString1 = paramString1.toCharArray();
/* 183 */     paramString2 = paramString2.toCharArray();
/*     */ 
/* 185 */     Character[] arrayOfCharacter1 = new Character[paramString1.length];
/* 186 */     for (int i = 0; i < paramString1.length; i++) {
/* 187 */       arrayOfCharacter1[i] = Character.valueOf(paramString1[i]);
/*     */     }
/* 189 */     Character[] arrayOfCharacter2 = new Character[paramString2.length];
/* 190 */     for (paramString1 = 0; paramString1 < paramString2.length; paramString1++) {
/* 191 */       arrayOfCharacter2[paramString1] = Character.valueOf(paramString2[paramString1]);
/*     */     }
/* 193 */     paramString1 = new StringBuffer();
/* 194 */     paramString2 = a(arrayOfCharacter1, arrayOfCharacter2);
/* 195 */     for (int j = 0; j < paramString2.size(); j++) {
/* 196 */       if ((arrayOfCharacter1[j].equals(paramString2.get(j))) && (arrayOfCharacter2[j].equals(paramString2.get(j)))) {
/* 197 */         paramString1.append(paramString2.get(j));
/*     */       }
/*     */     }
/* 200 */     return paramString1.toString();
/*     */   }
/*     */ 
/*     */   public static String a(String paramString, int paramInt)
/*     */   {
/* 205 */     if ((
/* 205 */       paramString = paramString.trim())
/* 205 */       .length() < paramInt)
/* 206 */       return paramString;
/* 207 */     if (paramString.substring(0, paramInt).contains("\n"))
/* 208 */       return paramString.substring(0, paramString.indexOf("\n")).trim() + "\n\n" + a(paramString.substring(paramString.indexOf("\n") + 1), paramInt);
/*     */     int i;
/* 213 */     if ((
/* 213 */       i = Math.max(Math.max(paramString.lastIndexOf(" ", paramInt), paramString.lastIndexOf("\t", paramInt)), paramString.lastIndexOf("-", paramInt))) < 0)
/*     */     {
/* 214 */       return paramString;
/*     */     }
/* 216 */     return paramString.substring(0, i).trim() + "\n" + a(paramString.substring(i), paramInt);
/*     */   }
/*     */ 
/*     */   public static String c(int paramInt)
/*     */   {
/* 239 */     StringBuilder localStringBuilder = new StringBuilder(paramInt);
/* 240 */     for (int i = 0; i < paramInt; i++) {
/* 241 */       localStringBuilder.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrsuvwxyz".charAt(jdField_a_of_type_JavaUtilRandom.nextInt(61)));
/*     */     }
/* 243 */     return localStringBuilder.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     i
 * JD-Core Version:    0.6.2
 */