package org.apache.commons.lang3;

public class CharSetUtils
{
  public static String squeeze(String str, String... set)
  {
    if ((StringUtils.isEmpty(str)) || (deepEmpty(set))) {
      return str;
    }
    CharSet chars = CharSet.getInstance(set);
    StringBuilder buffer = new StringBuilder(str.length());
    char[] chrs = str.toCharArray();
    int local_sz = chrs.length;
    char lastChar = ' ';
    char local_ch = ' ';
    for (int local_i = 0; local_i < local_sz; local_i++)
    {
      local_ch = chrs[local_i];
      if ((local_ch != lastChar) || (local_i == 0) || (!chars.contains(local_ch)))
      {
        buffer.append(local_ch);
        lastChar = local_ch;
      }
    }
    return buffer.toString();
  }
  
  public static int count(String str, String... set)
  {
    if ((StringUtils.isEmpty(str)) || (deepEmpty(set))) {
      return 0;
    }
    CharSet chars = CharSet.getInstance(set);
    int count = 0;
    for (char local_c : str.toCharArray()) {
      if (chars.contains(local_c)) {
        count++;
      }
    }
    return count;
  }
  
  public static String keep(String str, String... set)
  {
    if (str == null) {
      return null;
    }
    if ((str.length() == 0) || (deepEmpty(set))) {
      return "";
    }
    return modify(str, set, true);
  }
  
  public static String delete(String str, String... set)
  {
    if ((StringUtils.isEmpty(str)) || (deepEmpty(set))) {
      return str;
    }
    return modify(str, set, false);
  }
  
  private static String modify(String str, String[] set, boolean expect)
  {
    CharSet chars = CharSet.getInstance(set);
    StringBuilder buffer = new StringBuilder(str.length());
    char[] chrs = str.toCharArray();
    int local_sz = chrs.length;
    for (int local_i = 0; local_i < local_sz; local_i++) {
      if (chars.contains(chrs[local_i]) == expect) {
        buffer.append(chrs[local_i]);
      }
    }
    return buffer.toString();
  }
  
  private static boolean deepEmpty(String[] strings)
  {
    if (strings != null) {
      for (String local_s : strings) {
        if (StringUtils.isNotEmpty(local_s)) {
          return false;
        }
      }
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.CharSetUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */