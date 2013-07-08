package org.apache.commons.lang3;

public class CharSequenceUtils
{
  public static CharSequence subSequence(CharSequence local_cs, int start)
  {
    return local_cs == null ? null : local_cs.subSequence(start, local_cs.length());
  }
  
  static int indexOf(CharSequence local_cs, int searchChar, int start)
  {
    if ((local_cs instanceof String)) {
      return ((String)local_cs).indexOf(searchChar, start);
    }
    int local_sz = local_cs.length();
    if (start < 0) {
      start = 0;
    }
    for (int local_i = start; local_i < local_sz; local_i++) {
      if (local_cs.charAt(local_i) == searchChar) {
        return local_i;
      }
    }
    return -1;
  }
  
  static int indexOf(CharSequence local_cs, CharSequence searchChar, int start)
  {
    return local_cs.toString().indexOf(searchChar.toString(), start);
  }
  
  static int lastIndexOf(CharSequence local_cs, int searchChar, int start)
  {
    if ((local_cs instanceof String)) {
      return ((String)local_cs).lastIndexOf(searchChar, start);
    }
    int local_sz = local_cs.length();
    if (start < 0) {
      return -1;
    }
    if (start >= local_sz) {
      start = local_sz - 1;
    }
    for (int local_i = start; local_i >= 0; local_i--) {
      if (local_cs.charAt(local_i) == searchChar) {
        return local_i;
      }
    }
    return -1;
  }
  
  static int lastIndexOf(CharSequence local_cs, CharSequence searchChar, int start)
  {
    return local_cs.toString().lastIndexOf(searchChar.toString(), start);
  }
  
  static char[] toCharArray(CharSequence local_cs)
  {
    if ((local_cs instanceof String)) {
      return ((String)local_cs).toCharArray();
    }
    int local_sz = local_cs.length();
    char[] array = new char[local_cs.length()];
    for (int local_i = 0; local_i < local_sz; local_i++) {
      array[local_i] = local_cs.charAt(local_i);
    }
    return array;
  }
  
  static boolean regionMatches(CharSequence local_cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length)
  {
    if (((local_cs instanceof String)) && ((substring instanceof String))) {
      return ((String)local_cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);
    }
    return local_cs.toString().regionMatches(ignoreCase, thisStart, substring.toString(), start, length);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.CharSequenceUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */