package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

public class LookupTranslator
  extends CharSequenceTranslator
{
  private final HashMap<CharSequence, CharSequence> lookupMap = new HashMap();
  private final int shortest;
  private final int longest;
  
  public LookupTranslator(CharSequence[]... lookup)
  {
    int _shortest = 2147483647;
    int _longest = 0;
    if (lookup != null) {
      for (CharSequence[] seq : lookup)
      {
        this.lookupMap.put(seq[0], seq[1]);
        int local_sz = seq[0].length();
        if (local_sz < _shortest) {
          _shortest = local_sz;
        }
        if (local_sz > _longest) {
          _longest = local_sz;
        }
      }
    }
    this.shortest = _shortest;
    this.longest = _longest;
  }
  
  public int translate(CharSequence input, int index, Writer out)
    throws IOException
  {
    int max = this.longest;
    if (index + this.longest > input.length()) {
      max = input.length() - index;
    }
    for (int local_i = max; local_i >= this.shortest; local_i--)
    {
      CharSequence subSeq = input.subSequence(index, index + local_i);
      CharSequence result = (CharSequence)this.lookupMap.get(subSeq);
      if (result != null)
      {
        out.write(result.toString());
        return local_i;
      }
    }
    return 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.text.translate.LookupTranslator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */