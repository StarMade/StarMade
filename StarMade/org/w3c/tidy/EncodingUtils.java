package org.w3c.tidy;

public final class EncodingUtils
{
  public static final int UNICODE_BOM_BE = 65279;
  public static final int UNICODE_BOM = 65279;
  public static final int UNICODE_BOM_LE = 65534;
  public static final int UNICODE_BOM_UTF8 = 15711167;
  public static final int FSM_ASCII = 0;
  public static final int FSM_ESC = 1;
  public static final int FSM_ESCD = 2;
  public static final int FSM_ESCDP = 3;
  public static final int FSM_ESCP = 4;
  public static final int FSM_NONASCII = 5;
  public static final int MAX_UTF8_FROM_UCS4 = 1114111;
  public static final int MAX_UTF16_FROM_UCS4 = 1114111;
  public static final int LOW_UTF16_SURROGATE = 55296;
  public static final int UTF16_SURROGATES_BEGIN = 65536;
  public static final int UTF16_LOW_SURROGATE_BEGIN = 55296;
  public static final int UTF16_LOW_SURROGATE_END = 56319;
  public static final int UTF16_HIGH_SURROGATE_BEGIN = 56320;
  public static final int UTF16_HIGH_SURROGATE_END = 57343;
  public static final int HIGH_UTF16_SURROGATE = 57343;
  private static final int UTF8_BYTE_SWAP_NOT_A_CHAR = 65534;
  private static final int UTF8_NOT_A_CHAR = 65535;
  private static final int[] WIN2UNICODE = { 8364, 0, 8218, 402, 8222, 8230, 8224, 8225, 710, 8240, 352, 8249, 338, 0, 381, 0, 0, 8216, 8217, 8220, 8221, 8226, 8211, 8212, 732, 8482, 353, 8250, 339, 0, 382, 376 };
  private static final int[] MAC2UNICODE = { 196, 197, 199, 201, 209, 214, 220, 225, 224, 226, 228, 227, 229, 231, 233, 232, 234, 235, 237, 236, 238, 239, 241, 243, 242, 244, 246, 245, 250, 249, 251, 252, 8224, 176, 162, 163, 167, 8226, 182, 223, 174, 169, 8482, 180, 168, 8800, 198, 216, 8734, 177, 8804, 8805, 165, 181, 8706, 8721, 8719, 960, 8747, 170, 186, 937, 230, 248, 191, 161, 172, 8730, 402, 8776, 8710, 171, 187, 8230, 160, 192, 195, 213, 338, 339, 8211, 8212, 8220, 8221, 8216, 8217, 247, 9674, 255, 376, 8260, 8364, 8249, 8250, 64257, 64258, 8225, 183, 8218, 8222, 8240, 194, 202, 193, 203, 200, 205, 206, 207, 204, 211, 212, 63743, 210, 218, 219, 217, 305, 710, 732, 175, 728, 729, 730, 184, 733, 731, 711 };
  private static final int[] SYMBOL2UNICODE = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 8704, 35, 8707, 37, 38, 8717, 40, 41, 8727, 43, 44, 8722, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 8773, 913, 914, 935, 916, 917, 934, 915, 919, 921, 977, 922, 923, 924, 925, 927, 928, 920, 929, 931, 932, 933, 962, 937, 926, 936, 918, 91, 8756, 93, 8869, 95, 175, 945, 946, 967, 948, 949, 966, 947, 951, 953, 981, 954, 955, 956, 957, 959, 960, 952, 961, 963, 964, 965, 982, 969, 958, 968, 950, 123, 124, 125, 8764, 63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 160, 978, 8242, 8804, 8260, 8734, 402, 9827, 9830, 9829, 9824, 8596, 8592, 8593, 8594, 8595, 176, 177, 8243, 8805, 215, 8733, 8706, 183, 247, 8800, 8801, 8776, 8230, 63, 63, 8629, 8501, 8465, 8476, 8472, 8855, 8853, 8709, 8745, 8746, 8835, 8839, 8836, 8834, 8838, 8712, 8713, 8736, 8711, 174, 169, 8482, 8719, 8730, 8901, 172, 8743, 8744, 8660, 8656, 8657, 8658, 8659, 9674, 9001, 174, 169, 8482, 8721, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 8364, 9002, 8747, 8992, 63, 8993, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63 };
  private static final ValidUTF8Sequence[] VALID_UTF8 = { new ValidUTF8Sequence(0, 127, 1, new char[] { '\000', '', '\000', '\000', '\000', '\000', '\000', '\000' }), new ValidUTF8Sequence(128, 2047, 2, new char[] { 'Â', 'ß', '', '¿', '\000', '\000', '\000', '\000' }), new ValidUTF8Sequence(2048, 4095, 3, new char[] { 'à', 'à', ' ', '¿', '', '¿', '\000', '\000' }), new ValidUTF8Sequence(4096, 65535, 3, new char[] { 'á', 'ï', '', '¿', '', '¿', '\000', '\000' }), new ValidUTF8Sequence(65536, 262143, 4, new char[] { 'ð', 'ð', '', '¿', '', '¿', '', '¿' }), new ValidUTF8Sequence(262144, 1048575, 4, new char[] { 'ñ', 'ó', '', '¿', '', '¿', '', '¿' }), new ValidUTF8Sequence(1048576, 1114111, 4, new char[] { 'ô', 'ô', '', '', '', '¿', '', '¿' }) };
  private static final int NUM_UTF8_SEQUENCES = VALID_UTF8.length;
  private static final int[] OFFSET_UTF8_SEQUENCES = { 0, 1, 2, 4, NUM_UTF8_SEQUENCES };
  
  protected static int decodeWin1252(int paramInt)
  {
    return WIN2UNICODE[(paramInt - 128)];
  }
  
  protected static int decodeMacRoman(int paramInt)
  {
    if (127 < paramInt) {
      paramInt = MAC2UNICODE[(paramInt - 128)];
    }
    return paramInt;
  }
  
  static int decodeSymbolFont(int paramInt)
  {
    if (paramInt > 255) {
      return paramInt;
    }
    return SYMBOL2UNICODE[paramInt];
  }
  
  static boolean decodeUTF8BytesToChar(int[] paramArrayOfInt1, int paramInt1, byte[] paramArrayOfByte, GetBytes paramGetBytes, int[] paramArrayOfInt2, int paramInt2)
  {
    byte[] arrayOfByte = new byte[10];
    int i = 0;
    int j = 0;
    int m = 0;
    boolean bool = false;
    if (paramArrayOfByte.length != 0) {
      arrayOfByte = paramArrayOfByte;
    }
    if (paramInt1 == -1)
    {
      paramArrayOfInt1[0] = paramInt1;
      paramArrayOfInt2[0] = 1;
      return false;
    }
    i = TidyUtils.toUnsigned(paramInt1);
    if (i <= 127)
    {
      j = i;
      m = 1;
    }
    else if ((i & 0xE0) == 192)
    {
      j = i & 0x1F;
      m = 2;
    }
    else if ((i & 0xF0) == 224)
    {
      j = i & 0xF;
      m = 3;
    }
    else if ((i & 0xF8) == 240)
    {
      j = i & 0x7;
      m = 4;
    }
    else if ((i & 0xFC) == 248)
    {
      j = i & 0x3;
      m = 5;
      bool = true;
    }
    else if ((i & 0xFE) == 252)
    {
      j = i & 0x1;
      m = 6;
      bool = true;
    }
    else
    {
      j = i;
      m = 1;
      bool = true;
    }
    for (int k = 1; k < m; k++)
    {
      int[] arrayOfInt1 = new int[1];
      int[] arrayOfInt2;
      if ((paramGetBytes != null) && (m - k > 0))
      {
        arrayOfInt1[0] = 1;
        arrayOfInt2 = new int[] { arrayOfByte[(paramInt2 + k - 1)] };
        paramGetBytes.doGet(arrayOfInt2, arrayOfInt1, false);
        if (arrayOfInt1[0] <= 0)
        {
          bool = true;
          m = k;
          break;
        }
      }
      if ((arrayOfByte[(paramInt2 + k - 1)] & 0xC0) != 128)
      {
        bool = true;
        m = k;
        if (paramGetBytes == null) {
          break;
        }
        arrayOfInt2 = new int[] { arrayOfByte[(paramInt2 + k - 1)] };
        arrayOfInt1[0] = 1;
        paramGetBytes.doGet(arrayOfInt2, arrayOfInt1, true);
        break;
      }
      j = j << 6 | arrayOfByte[(paramInt2 + k - 1)] & 0x3F;
    }
    if ((!bool) && ((j == 65534) || (j == 65535))) {
      bool = true;
    }
    if ((!bool) && (j > 1114111)) {
      bool = true;
    }
    if ((!bool) && (j >= 55296) && (j <= 57343)) {
      bool = true;
    }
    if (!bool)
    {
      int n = OFFSET_UTF8_SEQUENCES[(m - 1)];
      int i1 = OFFSET_UTF8_SEQUENCES[m] - 1;
      if ((j < VALID_UTF8[n].lowChar) || (j > VALID_UTF8[i1].highChar))
      {
        bool = true;
      }
      else
      {
        bool = true;
        for (k = n; k <= i1; k++) {
          for (int i2 = 0; i2 < m; i2++)
          {
            int i3;
            if (!TidyUtils.toBoolean(i2)) {
              i3 = (char)paramInt1;
            } else {
              i3 = (char)arrayOfByte[(paramInt2 + i2 - 1)];
            }
            if ((i3 >= VALID_UTF8[k].validBytes[(i2 * 2)]) && (i3 <= VALID_UTF8[k].validBytes[(i2 * 2 + 1)])) {
              bool = false;
            }
            if (bool) {
              break;
            }
          }
        }
      }
    }
    paramArrayOfInt2[0] = m;
    paramArrayOfInt1[0] = j;
    return bool;
  }
  
  static boolean encodeCharToUTF8Bytes(int paramInt, byte[] paramArrayOfByte, PutBytes paramPutBytes, int[] paramArrayOfInt)
  {
    int i = 0;
    byte[] arrayOfByte = new byte[10];
    if (paramArrayOfByte != null) {
      arrayOfByte = paramArrayOfByte;
    }
    boolean bool = false;
    if (paramInt <= 127)
    {
      arrayOfByte[0] = ((byte)paramInt);
      i = 1;
    }
    else if (paramInt <= 2047)
    {
      arrayOfByte[0] = ((byte)(0xC0 | paramInt >> 6));
      arrayOfByte[1] = ((byte)(0x80 | paramInt & 0x3F));
      i = 2;
    }
    else if (paramInt <= 65535)
    {
      arrayOfByte[0] = ((byte)(0xE0 | paramInt >> 12));
      arrayOfByte[1] = ((byte)(0x80 | paramInt >> 6 & 0x3F));
      arrayOfByte[2] = ((byte)(0x80 | paramInt & 0x3F));
      i = 3;
      if ((paramInt == 65534) || (paramInt == 65535)) {
        bool = true;
      } else if ((paramInt >= 55296) && (paramInt <= 57343)) {
        bool = true;
      }
    }
    else if (paramInt <= 2097151)
    {
      arrayOfByte[0] = ((byte)(0xF0 | paramInt >> 18));
      arrayOfByte[1] = ((byte)(0x80 | paramInt >> 12 & 0x3F));
      arrayOfByte[2] = ((byte)(0x80 | paramInt >> 6 & 0x3F));
      arrayOfByte[3] = ((byte)(0x80 | paramInt & 0x3F));
      i = 4;
      if (paramInt > 1114111) {
        bool = true;
      }
    }
    else if (paramInt <= 67108863)
    {
      arrayOfByte[0] = ((byte)(0xF8 | paramInt >> 24));
      arrayOfByte[1] = ((byte)(0x80 | paramInt >> 18));
      arrayOfByte[2] = ((byte)(0x80 | paramInt >> 12 & 0x3F));
      arrayOfByte[3] = ((byte)(0x80 | paramInt >> 6 & 0x3F));
      arrayOfByte[4] = ((byte)(0x80 | paramInt & 0x3F));
      i = 5;
      bool = true;
    }
    else if (paramInt <= 2147483647)
    {
      arrayOfByte[0] = ((byte)(0xFC | paramInt >> 30));
      arrayOfByte[1] = ((byte)(0x80 | paramInt >> 24 & 0x3F));
      arrayOfByte[2] = ((byte)(0x80 | paramInt >> 18 & 0x3F));
      arrayOfByte[3] = ((byte)(0x80 | paramInt >> 12 & 0x3F));
      arrayOfByte[4] = ((byte)(0x80 | paramInt >> 6 & 0x3F));
      arrayOfByte[5] = ((byte)(0x80 | paramInt & 0x3F));
      i = 6;
      bool = true;
    }
    else
    {
      bool = true;
    }
    if ((!bool) && (paramPutBytes != null))
    {
      int[] arrayOfInt = { i };
      paramPutBytes.doPut(arrayOfByte, arrayOfInt);
      if (arrayOfInt[0] < i) {
        bool = true;
      }
    }
    paramArrayOfInt[0] = i;
    return bool;
  }
  
  static abstract interface PutBytes
  {
    public abstract void doPut(byte[] paramArrayOfByte, int[] paramArrayOfInt);
  }
  
  static abstract interface GetBytes
  {
    public abstract void doGet(int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean paramBoolean);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.EncodingUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */