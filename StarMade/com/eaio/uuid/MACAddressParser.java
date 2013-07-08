/*   1:    */package com.eaio.uuid;
/*   2:    */
/*  54:    */class MACAddressParser
/*  55:    */{
/*  56:    */  static String parse(String in)
/*  57:    */  {
/*  58: 58 */    String out = in;
/*  59:    */    
/*  62: 62 */    int hexStart = out.indexOf("0x");
/*  63: 63 */    if ((hexStart != -1) && (out.indexOf("ETHER") != -1)) {
/*  64: 64 */      int hexEnd = out.indexOf(' ', hexStart);
/*  65: 65 */      if (hexEnd > hexStart + 2) {
/*  66: 66 */        out = out.substring(hexStart, hexEnd);
/*  67:    */      }
/*  68:    */      
/*  69:    */    }
/*  70:    */    else
/*  71:    */    {
/*  72: 72 */      int octets = 0;
/*  73:    */      
/*  75: 75 */      if (out.indexOf('-') > -1) {
/*  76: 76 */        out = out.replace('-', ':');
/*  77:    */      }
/*  78:    */      
/*  79: 79 */      int lastIndex = out.lastIndexOf(':');
/*  80:    */      
/*  81: 81 */      if (lastIndex > out.length() - 2) {
/*  82: 82 */        out = null;
/*  83:    */      }
/*  84:    */      else
/*  85:    */      {
/*  86: 86 */        int end = Math.min(out.length(), lastIndex + 3);
/*  87:    */        
/*  88: 88 */        octets++;
/*  89: 89 */        int old = lastIndex;
/*  90: 90 */        while ((octets != 5) && (lastIndex != -1) && (lastIndex > 1)) {
/*  91: 91 */          lastIndex = out.lastIndexOf(':', --lastIndex);
/*  92: 92 */          if ((old - lastIndex == 3) || (old - lastIndex == 2)) {
/*  93: 93 */            octets++;
/*  94: 94 */            old = lastIndex;
/*  95:    */          }
/*  96:    */        }
/*  97:    */        
/*  98: 98 */        if ((octets == 5) && (lastIndex > 1)) {
/*  99: 99 */          out = out.substring(lastIndex - 2, end).trim();
/* 100:    */        }
/* 101:    */        else {
/* 102:102 */          out = null;
/* 103:    */        }
/* 104:    */      }
/* 105:    */    }
/* 106:    */    
/* 109:109 */    if ((out != null) && (out.startsWith("0x"))) {
/* 110:110 */      out = out.substring(2);
/* 111:    */    }
/* 112:    */    
/* 113:113 */    return out;
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.uuid.MACAddressParser
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */