/*  1:   */package org.apache.commons.lang3.text.translate;
/*  2:   */
/*  3:   */import java.io.IOException;
/*  4:   */import java.io.Writer;
/*  5:   */import java.util.HashMap;
/*  6:   */
/* 32:   */public class LookupTranslator
/* 33:   */  extends CharSequenceTranslator
/* 34:   */{
/* 35:   */  private final HashMap<CharSequence, CharSequence> lookupMap;
/* 36:   */  private final int shortest;
/* 37:   */  private final int longest;
/* 38:   */  
/* 39:   */  public LookupTranslator(CharSequence[]... lookup)
/* 40:   */  {
/* 41:41 */    this.lookupMap = new HashMap();
/* 42:42 */    int _shortest = 2147483647;
/* 43:43 */    int _longest = 0;
/* 44:44 */    if (lookup != null) {
/* 45:45 */      for (CharSequence[] seq : lookup) {
/* 46:46 */        this.lookupMap.put(seq[0], seq[1]);
/* 47:47 */        int sz = seq[0].length();
/* 48:48 */        if (sz < _shortest) {
/* 49:49 */          _shortest = sz;
/* 50:   */        }
/* 51:51 */        if (sz > _longest) {
/* 52:52 */          _longest = sz;
/* 53:   */        }
/* 54:   */      }
/* 55:   */    }
/* 56:56 */    this.shortest = _shortest;
/* 57:57 */    this.longest = _longest;
/* 58:   */  }
/* 59:   */  
/* 62:   */  public int translate(CharSequence input, int index, Writer out)
/* 63:   */    throws IOException
/* 64:   */  {
/* 65:65 */    int max = this.longest;
/* 66:66 */    if (index + this.longest > input.length()) {
/* 67:67 */      max = input.length() - index;
/* 68:   */    }
/* 69:   */    
/* 70:70 */    for (int i = max; i >= this.shortest; i--) {
/* 71:71 */      CharSequence subSeq = input.subSequence(index, index + i);
/* 72:72 */      CharSequence result = (CharSequence)this.lookupMap.get(subSeq);
/* 73:73 */      if (result != null) {
/* 74:74 */        out.write(result.toString());
/* 75:75 */        return i;
/* 76:   */      }
/* 77:   */    }
/* 78:78 */    return 0;
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.text.translate.LookupTranslator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */