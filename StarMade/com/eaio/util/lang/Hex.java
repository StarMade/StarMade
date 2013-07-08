/*   1:    */package com.eaio.util.lang;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */
/*  41:    */public final class Hex
/*  42:    */{
/*  43: 43 */  private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*  44:    */  
/*  52:    */  public static Appendable append(Appendable a, short in)
/*  53:    */  {
/*  54: 54 */    return append(a, in, 4);
/*  55:    */  }
/*  56:    */  
/*  64:    */  public static Appendable append(Appendable a, short in, int length)
/*  65:    */  {
/*  66: 66 */    return append(a, in, length);
/*  67:    */  }
/*  68:    */  
/*  75:    */  public static Appendable append(Appendable a, int in)
/*  76:    */  {
/*  77: 77 */    return append(a, in, 8);
/*  78:    */  }
/*  79:    */  
/*  87:    */  public static Appendable append(Appendable a, int in, int length)
/*  88:    */  {
/*  89: 89 */    return append(a, in, length);
/*  90:    */  }
/*  91:    */  
/*  98:    */  public static Appendable append(Appendable a, long in)
/*  99:    */  {
/* 100:100 */    return append(a, in, 16);
/* 101:    */  }
/* 102:    */  
/* 109:    */  public static Appendable append(Appendable a, long in, int length)
/* 110:    */  {
/* 111:    */    try
/* 112:    */    {
/* 113:113 */      int lim = (length << 2) - 4;
/* 114:114 */      while (lim >= 0) {
/* 115:115 */        a.append(DIGITS[((byte)(int)(in >> lim) & 0xF)]);
/* 116:116 */        lim -= 4;
/* 117:    */      }
/* 118:    */    }
/* 119:    */    catch (IOException ex) {}
/* 120:    */    
/* 122:122 */    return a;
/* 123:    */  }
/* 124:    */  
/* 130:    */  public static Appendable append(Appendable a, byte[] bytes)
/* 131:    */  {
/* 132:    */    try
/* 133:    */    {
/* 134:134 */      for (byte b : bytes) {
/* 135:135 */        a.append(DIGITS[((byte)((b & 0xF0) >> 4))]);
/* 136:136 */        a.append(DIGITS[((byte)(b & 0xF))]);
/* 137:    */      }
/* 138:    */    }
/* 139:    */    catch (IOException ex) {}
/* 140:    */    
/* 142:142 */    return a;
/* 143:    */  }
/* 144:    */  
/* 154:    */  public static long parseLong(CharSequence s)
/* 155:    */  {
/* 156:156 */    long out = 0L;
/* 157:157 */    byte shifts = 0;
/* 158:    */    
/* 159:159 */    for (int i = 0; (i < s.length()) && (shifts < 16); i++) {
/* 160:160 */      char c = s.charAt(i);
/* 161:161 */      if ((c > '/') && (c < ':')) {
/* 162:162 */        shifts = (byte)(shifts + 1);
/* 163:163 */        out <<= 4;
/* 164:164 */        out |= c - '0';
/* 165:    */      }
/* 166:166 */      else if ((c > '@') && (c < 'G')) {
/* 167:167 */        shifts = (byte)(shifts + 1);
/* 168:168 */        out <<= 4;
/* 169:169 */        out |= c - '7';
/* 170:    */      }
/* 171:171 */      else if ((c > '`') && (c < 'g')) {
/* 172:172 */        shifts = (byte)(shifts + 1);
/* 173:173 */        out <<= 4;
/* 174:174 */        out |= c - 'W';
/* 175:    */      }
/* 176:    */    }
/* 177:177 */    return out;
/* 178:    */  }
/* 179:    */  
/* 189:    */  public static short parseShort(String s)
/* 190:    */  {
/* 191:191 */    short out = 0;
/* 192:192 */    byte shifts = 0;
/* 193:    */    
/* 194:194 */    for (int i = 0; (i < s.length()) && (shifts < 4); i++) {
/* 195:195 */      char c = s.charAt(i);
/* 196:196 */      if ((c > '/') && (c < ':')) {
/* 197:197 */        shifts = (byte)(shifts + 1);
/* 198:198 */        out = (short)(out << 4);
/* 199:199 */        out = (short)(out | c - '0');
/* 200:    */      }
/* 201:201 */      else if ((c > '@') && (c < 'G')) {
/* 202:202 */        shifts = (byte)(shifts + 1);
/* 203:203 */        out = (short)(out << 4);
/* 204:204 */        out = (short)(out | c - '7');
/* 205:    */      }
/* 206:206 */      else if ((c > '`') && (c < 'g')) {
/* 207:207 */        shifts = (byte)(shifts + 1);
/* 208:208 */        out = (short)(out << 4);
/* 209:209 */        out = (short)(out | c - 'W');
/* 210:    */      }
/* 211:    */    }
/* 212:212 */    return out;
/* 213:    */  }
/* 214:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.eaio.util.lang.Hex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */