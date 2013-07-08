package org.w3c.tidy;

import java.io.UnsupportedEncodingException;

public final class TidyUtils
{
  private static final short DIGIT = 1;
  private static final short LETTER = 2;
  private static final short NAMECHAR = 4;
  private static final short WHITE = 8;
  private static final short NEWLINE = 16;
  private static final short LOWERCASE = 32;
  private static final short UPPERCASE = 64;
  private static short[] lexmap = new short[''];
  
  static boolean toBoolean(int paramInt)
  {
    return paramInt != 0;
  }
  
  static int toUnsigned(int paramInt)
  {
    return paramInt & 0xFF;
  }
  
  static boolean wsubstrn(String paramString1, int paramInt, String paramString2)
  {
    int i = paramString1.indexOf(paramString2);
    return (i > -1) && (i <= paramInt);
  }
  
  static boolean wsubstrncase(String paramString1, int paramInt, String paramString2)
  {
    return wsubstrn(paramString1.toLowerCase(), paramInt, paramString2.toLowerCase());
  }
  
  static int wstrnchr(String paramString, int paramInt, char paramChar)
  {
    int i = paramString.indexOf(paramChar);
    if (i < paramInt) {
      return i;
    }
    return -1;
  }
  
  static boolean wsubstr(String paramString1, String paramString2)
  {
    int j = paramString1.length();
    int k = paramString2.length();
    for (int i = 0; i <= j - k; i++) {
      if (paramString2.equalsIgnoreCase(paramString1.substring(i))) {
        return true;
      }
    }
    return false;
  }
  
  static boolean isxdigit(char paramChar)
  {
    return (Character.isDigit(paramChar)) || ((Character.toLowerCase(paramChar) >= 'a') && (Character.toLowerCase(paramChar) <= 'f'));
  }
  
  static boolean isInValuesIgnoreCase(String[] paramArrayOfString, String paramString)
  {
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++) {
      if (paramArrayOfString[j].equalsIgnoreCase(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean findBadSubString(String paramString1, String paramString2, int paramInt)
  {
    int i = paramString1.length();
    int j = 0;
    while (i < paramInt)
    {
      String str = paramString2.substring(j, j + i);
      if (paramString1.equalsIgnoreCase(str)) {
        return !paramString1.equals(str);
      }
      j++;
      paramInt--;
    }
    return false;
  }
  
  static boolean isXMLLetter(char paramChar)
  {
    return ((paramChar >= 'A') && (paramChar <= 'Z')) || ((paramChar >= 'a') && (paramChar <= 'z')) || ((paramChar >= 'À') && (paramChar <= 'Ö')) || ((paramChar >= 'Ø') && (paramChar <= 'ö')) || ((paramChar >= 'ø') && (paramChar <= 'ÿ')) || ((paramChar >= 'Ā') && (paramChar <= 'ı')) || ((paramChar >= 'Ĵ') && (paramChar <= 'ľ')) || ((paramChar >= 'Ł') && (paramChar <= 'ň')) || ((paramChar >= 'Ŋ') && (paramChar <= 'ž')) || ((paramChar >= 'ƀ') && (paramChar <= 'ǃ')) || ((paramChar >= 'Ǎ') && (paramChar <= 'ǰ')) || ((paramChar >= 'Ǵ') && (paramChar <= 'ǵ')) || ((paramChar >= 'Ǻ') && (paramChar <= 'ȗ')) || ((paramChar >= 'ɐ') && (paramChar <= 'ʨ')) || ((paramChar >= 'ʻ') && (paramChar <= 'ˁ')) || (paramChar == 'Ά') || ((paramChar >= 'Έ') && (paramChar <= 'Ί')) || (paramChar == 'Ό') || ((paramChar >= 'Ύ') && (paramChar <= 'Ρ')) || ((paramChar >= 'Σ') && (paramChar <= 'ώ')) || ((paramChar >= 'ϐ') && (paramChar <= 'ϖ')) || (paramChar == 'Ϛ') || (paramChar == 'Ϝ') || (paramChar == 'Ϟ') || (paramChar == 'Ϡ') || ((paramChar >= 'Ϣ') && (paramChar <= 'ϳ')) || ((paramChar >= 'Ё') && (paramChar <= 'Ќ')) || ((paramChar >= 'Ў') && (paramChar <= 'я')) || ((paramChar >= 'ё') && (paramChar <= 'ќ')) || ((paramChar >= 'ў') && (paramChar <= 'ҁ')) || ((paramChar >= 'Ґ') && (paramChar <= 'ӄ')) || ((paramChar >= 'Ӈ') && (paramChar <= 'ӈ')) || ((paramChar >= 'Ӌ') && (paramChar <= 'ӌ')) || ((paramChar >= 'Ӑ') && (paramChar <= 'ӫ')) || ((paramChar >= 'Ӯ') && (paramChar <= 'ӵ')) || ((paramChar >= 'Ӹ') && (paramChar <= 'ӹ')) || ((paramChar >= 'Ա') && (paramChar <= 'Ֆ')) || (paramChar == 'ՙ') || ((paramChar >= 'ա') && (paramChar <= 'ֆ')) || ((paramChar >= 'א') && (paramChar <= 'ת')) || ((paramChar >= 'װ') && (paramChar <= 'ײ')) || ((paramChar >= 'ء') && (paramChar <= 'غ')) || ((paramChar >= 'ف') && (paramChar <= 'ي')) || ((paramChar >= 'ٱ') && (paramChar <= 'ڷ')) || ((paramChar >= 'ں') && (paramChar <= 'ھ')) || ((paramChar >= 'ۀ') && (paramChar <= 'ێ')) || ((paramChar >= 'ې') && (paramChar <= 'ۓ')) || (paramChar == 'ە') || ((paramChar >= 'ۥ') && (paramChar <= 'ۦ')) || ((paramChar >= 'अ') && (paramChar <= 'ह')) || (paramChar == 'ऽ') || ((paramChar >= 'क़') && (paramChar <= 'ॡ')) || ((paramChar >= 'অ') && (paramChar <= 'ঌ')) || ((paramChar >= 'এ') && (paramChar <= 'ঐ')) || ((paramChar >= 'ও') && (paramChar <= 'ন')) || ((paramChar >= 'প') && (paramChar <= 'র')) || (paramChar == 'ল') || ((paramChar >= 'শ') && (paramChar <= 'হ')) || ((paramChar >= 'ড়') && (paramChar <= 'ঢ়')) || ((paramChar >= 'য়') && (paramChar <= 'ৡ')) || ((paramChar >= 'ৰ') && (paramChar <= 'ৱ')) || ((paramChar >= 'ਅ') && (paramChar <= 'ਊ')) || ((paramChar >= 'ਏ') && (paramChar <= 'ਐ')) || ((paramChar >= 'ਓ') && (paramChar <= 'ਨ')) || ((paramChar >= 'ਪ') && (paramChar <= 'ਰ')) || ((paramChar >= 'ਲ') && (paramChar <= 'ਲ਼')) || ((paramChar >= 'ਵ') && (paramChar <= 'ਸ਼')) || ((paramChar >= 'ਸ') && (paramChar <= 'ਹ')) || ((paramChar >= 'ਖ਼') && (paramChar <= 'ੜ')) || (paramChar == 'ਫ਼') || ((paramChar >= 'ੲ') && (paramChar <= 'ੴ')) || ((paramChar >= 'અ') && (paramChar <= 'ઋ')) || (paramChar == 'ઍ') || ((paramChar >= 'એ') && (paramChar <= 'ઑ')) || ((paramChar >= 'ઓ') && (paramChar <= 'ન')) || ((paramChar >= 'પ') && (paramChar <= 'ર')) || ((paramChar >= 'લ') && (paramChar <= 'ળ')) || ((paramChar >= 'વ') && (paramChar <= 'હ')) || (paramChar == 'ઽ') || (paramChar == 'ૠ') || ((paramChar >= 'ଅ') && (paramChar <= 'ଌ')) || ((paramChar >= 'ଏ') && (paramChar <= 'ଐ')) || ((paramChar >= 'ଓ') && (paramChar <= 'ନ')) || ((paramChar >= 'ପ') && (paramChar <= 'ର')) || ((paramChar >= 'ଲ') && (paramChar <= 'ଳ')) || ((paramChar >= 'ଶ') && (paramChar <= 'ହ')) || (paramChar == 'ଽ') || ((paramChar >= 'ଡ଼') && (paramChar <= 'ଢ଼')) || ((paramChar >= 'ୟ') && (paramChar <= 'ୡ')) || ((paramChar >= 'அ') && (paramChar <= 'ஊ')) || ((paramChar >= 'எ') && (paramChar <= 'ஐ')) || ((paramChar >= 'ஒ') && (paramChar <= 'க')) || ((paramChar >= 'ங') && (paramChar <= 'ச')) || (paramChar == 'ஜ') || ((paramChar >= 'ஞ') && (paramChar <= 'ட')) || ((paramChar >= 'ண') && (paramChar <= 'த')) || ((paramChar >= 'ந') && (paramChar <= 'ப')) || ((paramChar >= 'ம') && (paramChar <= 'வ')) || ((paramChar >= 'ஷ') && (paramChar <= 'ஹ')) || ((paramChar >= 'అ') && (paramChar <= 'ఌ')) || ((paramChar >= 'ఎ') && (paramChar <= 'ఐ')) || ((paramChar >= 'ఒ') && (paramChar <= 'న')) || ((paramChar >= 'ప') && (paramChar <= 'ళ')) || ((paramChar >= 'వ') && (paramChar <= 'హ')) || ((paramChar >= 'ౠ') && (paramChar <= 'ౡ')) || ((paramChar >= 'ಅ') && (paramChar <= 'ಌ')) || ((paramChar >= 'ಎ') && (paramChar <= 'ಐ')) || ((paramChar >= 'ಒ') && (paramChar <= 'ನ')) || ((paramChar >= 'ಪ') && (paramChar <= 'ಳ')) || ((paramChar >= 'ವ') && (paramChar <= 'ಹ')) || (paramChar == 'ೞ') || ((paramChar >= 'ೠ') && (paramChar <= 'ೡ')) || ((paramChar >= 'അ') && (paramChar <= 'ഌ')) || ((paramChar >= 'എ') && (paramChar <= 'ഐ')) || ((paramChar >= 'ഒ') && (paramChar <= 'ന')) || ((paramChar >= 'പ') && (paramChar <= 'ഹ')) || ((paramChar >= 'ൠ') && (paramChar <= 'ൡ')) || ((paramChar >= 'ก') && (paramChar <= 'ฮ')) || (paramChar == 'ะ') || ((paramChar >= 'า') && (paramChar <= 'ำ')) || ((paramChar >= 'เ') && (paramChar <= 'ๅ')) || ((paramChar >= 'ກ') && (paramChar <= 'ຂ')) || (paramChar == 'ຄ') || ((paramChar >= 'ງ') && (paramChar <= 'ຈ')) || (paramChar == 'ຊ') || (paramChar == 'ຍ') || ((paramChar >= 'ດ') && (paramChar <= 'ທ')) || ((paramChar >= 'ນ') && (paramChar <= 'ຟ')) || ((paramChar >= 'ມ') && (paramChar <= 'ຣ')) || (paramChar == 'ລ') || (paramChar == 'ວ') || ((paramChar >= 'ສ') && (paramChar <= 'ຫ')) || ((paramChar >= 'ອ') && (paramChar <= 'ຮ')) || (paramChar == 'ະ') || ((paramChar >= 'າ') && (paramChar <= 'ຳ')) || (paramChar == 'ຽ') || ((paramChar >= 'ເ') && (paramChar <= 'ໄ')) || ((paramChar >= 'ཀ') && (paramChar <= 'ཇ')) || ((paramChar >= 'ཉ') && (paramChar <= 'ཀྵ')) || ((paramChar >= 'Ⴀ') && (paramChar <= 'Ⴥ')) || ((paramChar >= 'ა') && (paramChar <= 'ჶ')) || (paramChar == 'ᄀ') || ((paramChar >= 'ᄂ') && (paramChar <= 'ᄃ')) || ((paramChar >= 'ᄅ') && (paramChar <= 'ᄇ')) || (paramChar == 'ᄉ') || ((paramChar >= 'ᄋ') && (paramChar <= 'ᄌ')) || ((paramChar >= 'ᄎ') && (paramChar <= 'ᄒ')) || (paramChar == 'ᄼ') || (paramChar == 'ᄾ') || (paramChar == 'ᅀ') || (paramChar == 'ᅌ') || (paramChar == 'ᅎ') || (paramChar == 'ᅐ') || ((paramChar >= 'ᅔ') && (paramChar <= 'ᅕ')) || (paramChar == 'ᅙ') || ((paramChar >= 'ᅟ') && (paramChar <= 'ᅡ')) || (paramChar == 'ᅣ') || (paramChar == 'ᅥ') || (paramChar == 'ᅧ') || (paramChar == 'ᅩ') || ((paramChar >= 'ᅭ') && (paramChar <= 'ᅮ')) || ((paramChar >= 'ᅲ') && (paramChar <= 'ᅳ')) || (paramChar == 'ᅵ') || (paramChar == 'ᆞ') || (paramChar == 'ᆨ') || (paramChar == 'ᆫ') || ((paramChar >= 'ᆮ') && (paramChar <= 'ᆯ')) || ((paramChar >= 'ᆷ') && (paramChar <= 'ᆸ')) || (paramChar == 'ᆺ') || ((paramChar >= 'ᆼ') && (paramChar <= 'ᇂ')) || (paramChar == 'ᇫ') || (paramChar == 'ᇰ') || (paramChar == 'ᇹ') || ((paramChar >= 'Ḁ') && (paramChar <= 'ẛ')) || ((paramChar >= 'Ạ') && (paramChar <= 'ỹ')) || ((paramChar >= 'ἀ') && (paramChar <= 'ἕ')) || ((paramChar >= 'Ἐ') && (paramChar <= 'Ἕ')) || ((paramChar >= 'ἠ') && (paramChar <= 'ὅ')) || ((paramChar >= 'Ὀ') && (paramChar <= 'Ὅ')) || ((paramChar >= 'ὐ') && (paramChar <= 'ὗ')) || (paramChar == 'Ὑ') || (paramChar == 'Ὓ') || (paramChar == 'Ὕ') || ((paramChar >= 'Ὗ') && (paramChar <= 'ώ')) || ((paramChar >= 'ᾀ') && (paramChar <= 'ᾴ')) || ((paramChar >= 'ᾶ') && (paramChar <= 'ᾼ')) || (paramChar == 'ι') || ((paramChar >= 'ῂ') && (paramChar <= 'ῄ')) || ((paramChar >= 'ῆ') && (paramChar <= 'ῌ')) || ((paramChar >= 'ῐ') && (paramChar <= 'ΐ')) || ((paramChar >= 'ῖ') && (paramChar <= 'Ί')) || ((paramChar >= 'ῠ') && (paramChar <= 'Ῥ')) || ((paramChar >= 'ῲ') && (paramChar <= 'ῴ')) || ((paramChar >= 'ῶ') && (paramChar <= 'ῼ')) || (paramChar == 'Ω') || ((paramChar >= 'K') && (paramChar <= 'Å')) || (paramChar == '℮') || ((paramChar >= 'ↀ') && (paramChar <= 'ↂ')) || ((paramChar >= 'ぁ') && (paramChar <= 'ゔ')) || ((paramChar >= 'ァ') && (paramChar <= 'ヺ')) || ((paramChar >= 'ㄅ') && (paramChar <= 'ㄬ')) || ((paramChar >= 44032) && (paramChar <= 55203)) || ((paramChar >= '一') && (paramChar <= 40869)) || (paramChar == '〇') || ((paramChar >= '〡') && (paramChar <= '〩')) || ((paramChar >= '一') && (paramChar <= 40869)) || (paramChar == '〇') || ((paramChar >= '〡') && (paramChar <= '〩'));
  }
  
  static boolean isXMLNamechar(char paramChar)
  {
    return (isXMLLetter(paramChar)) || (paramChar == '.') || (paramChar == '_') || (paramChar == ':') || (paramChar == '-') || ((paramChar >= '̀') && (paramChar <= 'ͅ')) || ((paramChar >= '͠') && (paramChar <= '͡')) || ((paramChar >= '҃') && (paramChar <= '҆')) || ((paramChar >= '֑') && (paramChar <= '֡')) || ((paramChar >= '֣') && (paramChar <= 'ֹ')) || ((paramChar >= 'ֻ') && (paramChar <= 'ֽ')) || (paramChar == 'ֿ') || ((paramChar >= 'ׁ') && (paramChar <= 'ׂ')) || (paramChar == 'ׄ') || ((paramChar >= 'ً') && (paramChar <= 'ْ')) || (paramChar == 'ٰ') || ((paramChar >= 'ۖ') && (paramChar <= 'ۜ')) || ((paramChar >= '۝') && (paramChar <= '۟')) || ((paramChar >= '۠') && (paramChar <= 'ۤ')) || ((paramChar >= 'ۧ') && (paramChar <= 'ۨ')) || ((paramChar >= '۪') && (paramChar <= 'ۭ')) || ((paramChar >= 'ँ') && (paramChar <= 'ः')) || (paramChar == '़') || ((paramChar >= 'ा') && (paramChar <= 'ौ')) || (paramChar == '्') || ((paramChar >= '॑') && (paramChar <= '॔')) || ((paramChar >= 'ॢ') && (paramChar <= 'ॣ')) || ((paramChar >= 'ঁ') && (paramChar <= 'ঃ')) || (paramChar == '়') || (paramChar == 'া') || (paramChar == 'ি') || ((paramChar >= 'ী') && (paramChar <= 'ৄ')) || ((paramChar >= 'ে') && (paramChar <= 'ৈ')) || ((paramChar >= 'ো') && (paramChar <= '্')) || (paramChar == 'ৗ') || ((paramChar >= 'ৢ') && (paramChar <= 'ৣ')) || (paramChar == 'ਂ') || (paramChar == '਼') || (paramChar == 'ਾ') || (paramChar == 'ਿ') || ((paramChar >= 'ੀ') && (paramChar <= 'ੂ')) || ((paramChar >= 'ੇ') && (paramChar <= 'ੈ')) || ((paramChar >= 'ੋ') && (paramChar <= '੍')) || ((paramChar >= 'ੰ') && (paramChar <= 'ੱ')) || ((paramChar >= 'ઁ') && (paramChar <= 'ઃ')) || (paramChar == '઼') || ((paramChar >= 'ા') && (paramChar <= 'ૅ')) || ((paramChar >= 'ે') && (paramChar <= 'ૉ')) || ((paramChar >= 'ો') && (paramChar <= '્')) || ((paramChar >= 'ଁ') && (paramChar <= 'ଃ')) || (paramChar == '଼') || ((paramChar >= 'ା') && (paramChar <= 'ୃ')) || ((paramChar >= 'େ') && (paramChar <= 'ୈ')) || ((paramChar >= 'ୋ') && (paramChar <= '୍')) || ((paramChar >= 'ୖ') && (paramChar <= 'ୗ')) || ((paramChar >= 'ஂ') && (paramChar <= 'ஃ')) || ((paramChar >= 'ா') && (paramChar <= 'ூ')) || ((paramChar >= 'ெ') && (paramChar <= 'ை')) || ((paramChar >= 'ொ') && (paramChar <= '்')) || (paramChar == 'ௗ') || ((paramChar >= 'ఁ') && (paramChar <= 'ః')) || ((paramChar >= 'ా') && (paramChar <= 'ౄ')) || ((paramChar >= 'ె') && (paramChar <= 'ై')) || ((paramChar >= 'ొ') && (paramChar <= '్')) || ((paramChar >= 'ౕ') && (paramChar <= 'ౖ')) || ((paramChar >= 'ಂ') && (paramChar <= 'ಃ')) || ((paramChar >= 'ಾ') && (paramChar <= 'ೄ')) || ((paramChar >= 'ೆ') && (paramChar <= 'ೈ')) || ((paramChar >= 'ೊ') && (paramChar <= '್')) || ((paramChar >= 'ೕ') && (paramChar <= 'ೖ')) || ((paramChar >= 'ം') && (paramChar <= 'ഃ')) || ((paramChar >= 'ാ') && (paramChar <= 'ൃ')) || ((paramChar >= 'െ') && (paramChar <= 'ൈ')) || ((paramChar >= 'ൊ') && (paramChar <= '്')) || (paramChar == 'ൗ') || (paramChar == 'ั') || ((paramChar >= 'ิ') && (paramChar <= 'ฺ')) || ((paramChar >= '็') && (paramChar <= '๎')) || (paramChar == 'ັ') || ((paramChar >= 'ິ') && (paramChar <= 'ູ')) || ((paramChar >= 'ົ') && (paramChar <= 'ຼ')) || ((paramChar >= '່') && (paramChar <= 'ໍ')) || ((paramChar >= '༘') && (paramChar <= '༙')) || (paramChar == '༵') || (paramChar == '༷') || (paramChar == '༹') || (paramChar == '༾') || (paramChar == '༿') || ((paramChar >= 'ཱ') && (paramChar <= '྄')) || ((paramChar >= '྆') && (paramChar <= 'ྋ')) || ((paramChar >= 'ྐ') && (paramChar <= 'ྕ')) || (paramChar == 'ྗ') || ((paramChar >= 'ྙ') && (paramChar <= 'ྭ')) || ((paramChar >= 'ྱ') && (paramChar <= 'ྷ')) || (paramChar == 'ྐྵ') || ((paramChar >= '⃐') && (paramChar <= '⃜')) || (paramChar == '⃡') || ((paramChar >= '〪') && (paramChar <= '〯')) || (paramChar == '゙') || (paramChar == '゚') || ((paramChar >= '0') && (paramChar <= '9')) || ((paramChar >= '٠') && (paramChar <= '٩')) || ((paramChar >= '۰') && (paramChar <= '۹')) || ((paramChar >= '०') && (paramChar <= '९')) || ((paramChar >= '০') && (paramChar <= '৯')) || ((paramChar >= '੦') && (paramChar <= '੯')) || ((paramChar >= '૦') && (paramChar <= '૯')) || ((paramChar >= '୦') && (paramChar <= '୯')) || ((paramChar >= '௧') && (paramChar <= '௯')) || ((paramChar >= '౦') && (paramChar <= '౯')) || ((paramChar >= '೦') && (paramChar <= '೯')) || ((paramChar >= '൦') && (paramChar <= '൯')) || ((paramChar >= '๐') && (paramChar <= '๙')) || ((paramChar >= '໐') && (paramChar <= '໙')) || ((paramChar >= '༠') && (paramChar <= '༩')) || (paramChar == '·') || (paramChar == 'ː') || (paramChar == 'ˑ') || (paramChar == '·') || (paramChar == 'ـ') || (paramChar == 'ๆ') || (paramChar == 'ໆ') || (paramChar == '々') || ((paramChar >= '〱') && (paramChar <= '〵')) || ((paramChar >= 'ゝ') && (paramChar <= 'ゞ')) || ((paramChar >= 'ー') && (paramChar <= 'ヾ'));
  }
  
  static boolean isQuote(int paramInt)
  {
    return (paramInt == 39) || (paramInt == 34);
  }
  
  public static byte[] getBytes(String paramString)
  {
    try
    {
      return paramString.getBytes("UTF8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new Error("String to UTF-8 conversion failed: " + localUnsupportedEncodingException.getMessage());
    }
  }
  
  public static String getString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      return new String(paramArrayOfByte, paramInt1, paramInt2, "UTF8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new Error("UTF-8 to string conversion failed: " + localUnsupportedEncodingException.getMessage());
    }
  }
  
  public static int lastChar(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0)) {
      return paramString.charAt(paramString.length() - 1);
    }
    return 0;
  }
  
  public static boolean isWhite(char paramChar)
  {
    int i = map(paramChar);
    return toBoolean(i & 0x8);
  }
  
  public static boolean isDigit(char paramChar)
  {
    int i = map(paramChar);
    return toBoolean(i & 0x1);
  }
  
  public static boolean isLetter(char paramChar)
  {
    int i = map(paramChar);
    return toBoolean(i & 0x2);
  }
  
  public static boolean isNamechar(char paramChar)
  {
    int i = map(paramChar);
    return toBoolean(i & 0x4);
  }
  
  public static boolean isLower(char paramChar)
  {
    int i = map(paramChar);
    return toBoolean(i & 0x20);
  }
  
  public static boolean isUpper(char paramChar)
  {
    int i = map(paramChar);
    return toBoolean(i & 0x40);
  }
  
  public static char toLower(char paramChar)
  {
    int i = map(paramChar);
    if (toBoolean(i & 0x40)) {
      paramChar = (char)(paramChar + 'a' - 65);
    }
    return paramChar;
  }
  
  public static char toUpper(char paramChar)
  {
    int i = map(paramChar);
    if (toBoolean(i & 0x20)) {
      paramChar = (char)(paramChar + 'A' - 97);
    }
    return paramChar;
  }
  
  public static char foldCase(char paramChar, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!paramBoolean2) {
      if (paramBoolean1)
      {
        if (isLower(paramChar)) {
          paramChar = toUpper(paramChar);
        }
      }
      else if (isUpper(paramChar)) {
        paramChar = toLower(paramChar);
      }
    }
    return paramChar;
  }
  
  private static void mapStr(String paramString, short paramShort)
  {
    for (int j = 0; j < paramString.length(); j++)
    {
      int i = paramString.charAt(j);
      int tmp20_19 = i;
      short[] tmp20_16 = lexmap;
      tmp20_16[tmp20_19] = ((short)(tmp20_16[tmp20_19] | paramShort));
    }
  }
  
  private static short map(char paramChar)
  {
    return paramChar < '' ? lexmap[paramChar] : 0;
  }
  
  public static boolean isCharEncodingSupported(String paramString)
  {
    paramString = EncodingNameMapper.toJava(paramString);
    if (paramString == null) {
      return false;
    }
    try
    {
      "".getBytes(paramString);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      return false;
    }
    return true;
  }
  
  static
  {
    mapStr("\r\n\f", (short)24);
    mapStr(" \t", (short)8);
    mapStr("-.:_", (short)4);
    mapStr("0123456789", (short)5);
    mapStr("abcdefghijklmnopqrstuvwxyz", (short)38);
    mapStr("ABCDEFGHIJKLMNOPQRSTUVWXYZ", (short)70);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.TidyUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */