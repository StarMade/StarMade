import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class class_41
{
  private static DecimalFormat jdField_field_463_of_type_JavaTextDecimalFormat = new DecimalFormat("0000");
  private static DecimalFormat field_464 = new DecimalFormat("00");
  private static DecimalFormatSymbols jdField_field_463_of_type_JavaTextDecimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
  private static DecimalFormat field_465 = new DecimalFormat("#0.0", jdField_field_463_of_type_JavaTextDecimalFormatSymbols);
  private static Pattern jdField_field_463_of_type_JavaUtilRegexPattern = Pattern.compile("\n|\r");
  private static Random jdField_field_463_of_type_JavaUtilRandom = new Random();
  
  public static int a(String paramString)
  {
    paramString = jdField_field_463_of_type_JavaUtilRegexPattern.matcher(paramString);
    for (int i = 1; paramString.find(); i++) {}
    return i;
  }
  
  public static String a1(int paramInt)
  {
    return jdField_field_463_of_type_JavaTextDecimalFormat.format(paramInt);
  }
  
  public static String b(int paramInt)
  {
    return field_464.format(paramInt);
  }
  
  public static String a2(float paramFloat)
  {
    return field_465.format(paramFloat);
  }
  
  public static String a3(double paramDouble)
  {
    return field_465.format(paramDouble);
  }
  
  private static List a4(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    int[][] arrayOfInt = new int[paramArrayOfObject1.length + 1][paramArrayOfObject2.length + 1];
    for (int i = 1; i <= paramArrayOfObject1.length; i++) {
      for (j = 1; j <= paramArrayOfObject2.length; j++) {
        if (paramArrayOfObject1[(i - 1)].equals(paramArrayOfObject2[(j - 1)])) {
          arrayOfInt[i][j] = (1 + arrayOfInt[(i - 1)][(j - 1)]);
        } else {
          arrayOfInt[i][j] = Math.max(arrayOfInt[(i - 1)][j], arrayOfInt[i][(j - 1)]);
        }
      }
    }
    i = paramArrayOfObject1.length;
    int j = paramArrayOfObject2.length;
    LinkedList localLinkedList = new LinkedList();
    while ((i != 0) && (j != 0)) {
      if (paramArrayOfObject1[(i - 1)].equals(paramArrayOfObject2[(j - 1)]))
      {
        localLinkedList.add(paramArrayOfObject1[(i - 1)]);
        i--;
        j--;
      }
      else if (arrayOfInt[i][(j - 1)] >= arrayOfInt[(i - 1)][j])
      {
        j--;
      }
      else
      {
        i--;
      }
    }
    Collections.reverse(localLinkedList);
    return localLinkedList;
  }
  
  public static String a5(String paramString1, String paramString2)
  {
    paramString1 = paramString1.toCharArray();
    paramString2 = paramString2.toCharArray();
    Character[] arrayOfCharacter1 = new Character[paramString1.length];
    for (int i = 0; i < paramString1.length; i++) {
      arrayOfCharacter1[i] = Character.valueOf(paramString1[i]);
    }
    Character[] arrayOfCharacter2 = new Character[paramString2.length];
    for (paramString1 = 0; paramString1 < paramString2.length; paramString1++) {
      arrayOfCharacter2[paramString1] = Character.valueOf(paramString2[paramString1]);
    }
    paramString1 = new StringBuffer();
    paramString2 = a4(arrayOfCharacter1, arrayOfCharacter2);
    for (int j = 0; j < paramString2.size(); j++) {
      if ((arrayOfCharacter1[j].equals(paramString2.get(j))) && (arrayOfCharacter2[j].equals(paramString2.get(j)))) {
        paramString1.append(paramString2.get(j));
      }
    }
    return paramString1.toString();
  }
  
  public static String a6(String paramString, int paramInt)
  {
    if ((paramString = paramString.trim()).length() < paramInt) {
      return paramString;
    }
    if (paramString.substring(0, paramInt).contains("\n")) {
      return paramString.substring(0, paramString.indexOf("\n")).trim() + "\n\n" + a6(paramString.substring(paramString.indexOf("\n") + 1), paramInt);
    }
    int i;
    if ((i = Math.max(Math.max(paramString.lastIndexOf(" ", paramInt), paramString.lastIndexOf("\t", paramInt)), paramString.lastIndexOf("-", paramInt))) < 0) {
      return paramString;
    }
    return paramString.substring(0, i).trim() + "\n" + a6(paramString.substring(i), paramInt);
  }
  
  public static String c(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramInt);
    for (int i = 0; i < paramInt; i++) {
      localStringBuilder.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrsuvwxyz".charAt(jdField_field_463_of_type_JavaUtilRandom.nextInt(61)));
    }
    return localStringBuilder.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_41
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */