package com.eaio.uuid;

class MACAddressParser
{
  static String parse(String local_in)
  {
    String out = local_in;
    int hexStart = out.indexOf("0x");
    if ((hexStart != -1) && (out.indexOf("ETHER") != -1))
    {
      int hexEnd = out.indexOf(' ', hexStart);
      if (hexEnd > hexStart + 2) {
        out = out.substring(hexStart, hexEnd);
      }
    }
    else
    {
      int hexEnd = 0;
      if (out.indexOf('-') > -1) {
        out = out.replace('-', ':');
      }
      int lastIndex = out.lastIndexOf(':');
      if (lastIndex > out.length() - 2)
      {
        out = null;
      }
      else
      {
        int end = Math.min(out.length(), lastIndex + 3);
        hexEnd++;
        for (int old = lastIndex; (hexEnd != 5) && (lastIndex != -1) && (lastIndex > 1); old = lastIndex)
        {
          label114:
          lastIndex = out.lastIndexOf(':', --lastIndex);
          if ((old - lastIndex != 3) && (old - lastIndex != 2)) {
            break label114;
          }
          hexEnd++;
        }
        if ((hexEnd == 5) && (lastIndex > 1)) {
          out = out.substring(lastIndex - 2, end).trim();
        } else {
          out = null;
        }
      }
    }
    if ((out != null) && (out.startsWith("0x"))) {
      out = out.substring(2);
    }
    return out;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.eaio.uuid.MACAddressParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */