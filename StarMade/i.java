/*   1:    */import java.text.DecimalFormat;
/*   2:    */import java.text.DecimalFormatSymbols;
/*   3:    */import java.util.Collections;
/*   4:    */import java.util.LinkedList;
/*   5:    */import java.util.List;
/*   6:    */import java.util.Locale;
/*   7:    */import java.util.Random;
/*   8:    */import java.util.regex.Matcher;
/*   9:    */import java.util.regex.Pattern;
/*  10:    */
/*  75:    */public final class i
/*  76:    */{
/*  77: 77 */  private static DecimalFormat jdField_a_of_type_JavaTextDecimalFormat = new DecimalFormat("0000");
/*  78: 78 */  private static DecimalFormat b = new DecimalFormat("00");
/*  79: 79 */  private static DecimalFormatSymbols jdField_a_of_type_JavaTextDecimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
/*  80:    */  
/*  84: 84 */  private static DecimalFormat c = new DecimalFormat("#0.0", jdField_a_of_type_JavaTextDecimalFormatSymbols);
/*  85:    */  
/* 102:102 */  private static Pattern jdField_a_of_type_JavaUtilRegexPattern = Pattern.compile("\n|\r");
/* 103:    */  
/* 104:104 */  public static int a(String paramString) { paramString = jdField_a_of_type_JavaUtilRegexPattern.matcher(paramString);
/* 105:105 */    int i = 1;
/* 106:106 */    while (paramString.find())
/* 107:    */    {
/* 108:108 */      i++;
/* 109:    */    }
/* 110:110 */    return i;
/* 111:    */  }
/* 112:    */  
/* 118:    */  public static String a(int paramInt)
/* 119:    */  {
/* 120:120 */    return jdField_a_of_type_JavaTextDecimalFormat.format(paramInt);
/* 121:    */  }
/* 122:    */  
/* 127:    */  public static String b(int paramInt)
/* 128:    */  {
/* 129:129 */    return b.format(paramInt);
/* 130:    */  }
/* 131:    */  
/* 132:132 */  public static String a(float paramFloat) { return c.format(paramFloat); }
/* 133:    */  
/* 134:    */  public static String a(double paramDouble) {
/* 135:135 */    return c.format(paramDouble);
/* 136:    */  }
/* 137:    */  
/* 138:    */  private static List a(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
/* 139:139 */    int[][] arrayOfInt = new int[paramArrayOfObject1.length + 1][paramArrayOfObject2.length + 1];
/* 140:    */    
/* 142:142 */    for (int i = 1; i <= paramArrayOfObject1.length; i++) {
/* 143:143 */      for (j = 1; j <= paramArrayOfObject2.length; j++) {
/* 144:144 */        if (paramArrayOfObject1[(i - 1)].equals(paramArrayOfObject2[(j - 1)])) {
/* 145:145 */          arrayOfInt[i][j] = (1 + arrayOfInt[(i - 1)][(j - 1)]);
/* 146:    */        } else {
/* 147:147 */          arrayOfInt[i][j] = Math.max(arrayOfInt[(i - 1)][j], arrayOfInt[i][(j - 1)]);
/* 148:    */        }
/* 149:    */      }
/* 150:    */    }
/* 151:151 */    i = paramArrayOfObject1.length;int j = paramArrayOfObject2.length;
/* 152:152 */    LinkedList localLinkedList = new LinkedList();
/* 153:    */    
/* 154:154 */    while ((i != 0) && (j != 0))
/* 155:    */    {
/* 156:156 */      if (paramArrayOfObject1[(i - 1)].equals(paramArrayOfObject2[(j - 1)]))
/* 157:    */      {
/* 158:158 */        localLinkedList.add(paramArrayOfObject1[(i - 1)]);
/* 159:159 */        i--;
/* 160:160 */        j--;
/* 161:    */      }
/* 162:162 */      else if (arrayOfInt[i][(j - 1)] >= arrayOfInt[(i - 1)][j])
/* 163:    */      {
/* 164:164 */        j--;
/* 165:    */      }
/* 166:    */      else
/* 167:    */      {
/* 168:168 */        i--;
/* 169:    */      }
/* 170:    */    }
/* 171:171 */    Collections.reverse(localLinkedList);
/* 172:172 */    return localLinkedList;
/* 173:    */  }
/* 174:    */  
/* 180:    */  public static String a(String paramString1, String paramString2)
/* 181:    */  {
/* 182:182 */    paramString1 = paramString1.toCharArray();
/* 183:183 */    paramString2 = paramString2.toCharArray();
/* 184:    */    
/* 185:185 */    Character[] arrayOfCharacter1 = new Character[paramString1.length];
/* 186:186 */    for (int i = 0; i < paramString1.length; i++) {
/* 187:187 */      arrayOfCharacter1[i] = Character.valueOf(paramString1[i]);
/* 188:    */    }
/* 189:189 */    Character[] arrayOfCharacter2 = new Character[paramString2.length];
/* 190:190 */    for (paramString1 = 0; paramString1 < paramString2.length; paramString1++) {
/* 191:191 */      arrayOfCharacter2[paramString1] = Character.valueOf(paramString2[paramString1]);
/* 192:    */    }
/* 193:193 */    paramString1 = new StringBuffer();
/* 194:194 */    paramString2 = a(arrayOfCharacter1, arrayOfCharacter2);
/* 195:195 */    for (int j = 0; j < paramString2.size(); j++) {
/* 196:196 */      if ((arrayOfCharacter1[j].equals(paramString2.get(j))) && (arrayOfCharacter2[j].equals(paramString2.get(j)))) {
/* 197:197 */        paramString1.append(paramString2.get(j));
/* 198:    */      }
/* 199:    */    }
/* 200:200 */    return paramString1.toString();
/* 201:    */  }
/* 202:    */  
/* 203:    */  public static String a(String paramString, int paramInt)
/* 204:    */  {
/* 205:205 */    if ((paramString = paramString.trim()).length() < paramInt)
/* 206:206 */      return paramString;
/* 207:207 */    if (paramString.substring(0, paramInt).contains("\n")) {
/* 208:208 */      return paramString.substring(0, paramString.indexOf("\n")).trim() + "\n\n" + a(paramString.substring(paramString.indexOf("\n") + 1), paramInt);
/* 209:    */    }
/* 210:    */    
/* 211:    */    int i;
/* 212:    */    
/* 213:213 */    if ((i = Math.max(Math.max(paramString.lastIndexOf(" ", paramInt), paramString.lastIndexOf("\t", paramInt)), paramString.lastIndexOf("-", paramInt))) < 0) {
/* 214:214 */      return paramString;
/* 215:    */    }
/* 216:216 */    return paramString.substring(0, i).trim() + "\n" + a(paramString.substring(i), paramInt);
/* 217:    */  }
/* 218:    */  
/* 236:236 */  private static Random jdField_a_of_type_JavaUtilRandom = new Random();
/* 237:    */  
/* 238:    */  public static String c(int paramInt) {
/* 239:239 */    StringBuilder localStringBuilder = new StringBuilder(paramInt);
/* 240:240 */    for (int i = 0; i < paramInt; i++) {
/* 241:241 */      localStringBuilder.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrsuvwxyz".charAt(jdField_a_of_type_JavaUtilRandom.nextInt(61)));
/* 242:    */    }
/* 243:243 */    return localStringBuilder.toString();
/* 244:    */  }
/* 245:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     i
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */