/*   1:    */package org.apache.ws.commons.util;
/*   2:    */
/*   3:    */import java.text.FieldPosition;
/*   4:    */import java.text.Format;
/*   5:    */import java.text.ParsePosition;
/*   6:    */import java.util.Calendar;
/*   7:    */import java.util.TimeZone;
/*   8:    */
/*  26:    */public class XsDateTimeFormat
/*  27:    */  extends Format
/*  28:    */{
/*  29:    */  private static final long serialVersionUID = 3258131340871479609L;
/*  30:    */  final boolean parseDate;
/*  31:    */  final boolean parseTime;
/*  32:    */  
/*  33:    */  XsDateTimeFormat(boolean pParseDate, boolean pParseTime)
/*  34:    */  {
/*  35: 35 */    this.parseDate = pParseDate;
/*  36: 36 */    this.parseTime = pParseTime;
/*  37:    */  }
/*  38:    */  
/*  40:    */  public XsDateTimeFormat()
/*  41:    */  {
/*  42: 42 */    this(true, true);
/*  43:    */  }
/*  44:    */  
/*  45:    */  private int parseInt(String pString, int pOffset, StringBuffer pDigits) {
/*  46: 46 */    int length = pString.length();
/*  47: 47 */    pDigits.setLength(0);
/*  48: 48 */    while (pOffset < length) {
/*  49: 49 */      char c = pString.charAt(pOffset);
/*  50: 50 */      if (!Character.isDigit(c)) break;
/*  51: 51 */      pDigits.append(c);
/*  52: 52 */      pOffset++;
/*  53:    */    }
/*  54:    */    
/*  57: 57 */    return pOffset;
/*  58:    */  }
/*  59:    */  
/*  60:    */  public Object parseObject(String pString, ParsePosition pParsePosition) {
/*  61: 61 */    if (pString == null) {
/*  62: 62 */      throw new NullPointerException("The String argument must not be null.");
/*  63:    */    }
/*  64: 64 */    if (pParsePosition == null) {
/*  65: 65 */      throw new NullPointerException("The ParsePosition argument must not be null.");
/*  66:    */    }
/*  67: 67 */    int offset = pParsePosition.getIndex();
/*  68: 68 */    int length = pString.length();
/*  69:    */    
/*  70: 70 */    boolean isMinus = false;
/*  71: 71 */    StringBuffer digits = new StringBuffer();
/*  72:    */    int mday;
/*  73: 73 */    int month; int year; if (this.parseDate)
/*  74:    */    {
/*  75: 75 */      if (offset < length) {
/*  76: 76 */        char c = pString.charAt(offset);
/*  77: 77 */        if (c == '+') {
/*  78: 78 */          offset++;
/*  79: 79 */        } else if (c == '-') {
/*  80: 80 */          offset++;
/*  81: 81 */          isMinus = true;
/*  82:    */        }
/*  83:    */      }
/*  84:    */      
/*  85: 85 */      offset = parseInt(pString, offset, digits);
/*  86: 86 */      if (digits.length() < 4) {
/*  87: 87 */        pParsePosition.setErrorIndex(offset);
/*  88: 88 */        return null;
/*  89:    */      }
/*  90: 90 */      int year = Integer.parseInt(digits.toString());
/*  91:    */      
/*  92: 92 */      if ((offset < length) && (pString.charAt(offset) == '-')) {
/*  93: 93 */        offset++;
/*  94:    */      } else {
/*  95: 95 */        pParsePosition.setErrorIndex(offset);
/*  96: 96 */        return null;
/*  97:    */      }
/*  98:    */      
/*  99: 99 */      offset = parseInt(pString, offset, digits);
/* 100:100 */      if (digits.length() != 2) {
/* 101:101 */        pParsePosition.setErrorIndex(offset);
/* 102:102 */        return null;
/* 103:    */      }
/* 104:104 */      int month = Integer.parseInt(digits.toString());
/* 105:    */      
/* 106:106 */      if ((offset < length) && (pString.charAt(offset) == '-')) {
/* 107:107 */        offset++;
/* 108:    */      } else {
/* 109:109 */        pParsePosition.setErrorIndex(offset);
/* 110:110 */        return null;
/* 111:    */      }
/* 112:    */      
/* 113:113 */      offset = parseInt(pString, offset, digits);
/* 114:114 */      if (digits.length() != 2) {
/* 115:115 */        pParsePosition.setErrorIndex(offset);
/* 116:116 */        return null;
/* 117:    */      }
/* 118:118 */      int mday = Integer.parseInt(digits.toString());
/* 119:    */      
/* 120:120 */      if (this.parseTime) {
/* 121:121 */        if ((offset < length) && (pString.charAt(offset) == 'T')) {
/* 122:122 */          offset++;
/* 123:    */        } else {
/* 124:124 */          pParsePosition.setErrorIndex(offset);
/* 125:125 */          return null;
/* 126:    */        }
/* 127:    */      }
/* 128:    */    } else {
/* 129:129 */      year = month = mday = 0; }
/* 130:    */    int millis;
/* 131:    */    int millis;
/* 132:    */    int second;
/* 133:133 */    int minute; int hour; if (this.parseTime) {
/* 134:134 */      offset = parseInt(pString, offset, digits);
/* 135:135 */      if (digits.length() != 2) {
/* 136:136 */        pParsePosition.setErrorIndex(offset);
/* 137:137 */        return null;
/* 138:    */      }
/* 139:139 */      int hour = Integer.parseInt(digits.toString());
/* 140:    */      
/* 141:141 */      if ((offset < length) && (pString.charAt(offset) == ':')) {
/* 142:142 */        offset++;
/* 143:    */      } else {
/* 144:144 */        pParsePosition.setErrorIndex(offset);
/* 145:145 */        return null;
/* 146:    */      }
/* 147:    */      
/* 148:148 */      offset = parseInt(pString, offset, digits);
/* 149:149 */      if (digits.length() != 2) {
/* 150:150 */        pParsePosition.setErrorIndex(offset);
/* 151:151 */        return null;
/* 152:    */      }
/* 153:153 */      int minute = Integer.parseInt(digits.toString());
/* 154:    */      
/* 155:155 */      if ((offset < length) && (pString.charAt(offset) == ':')) {
/* 156:156 */        offset++;
/* 157:    */      } else {
/* 158:158 */        pParsePosition.setErrorIndex(offset);
/* 159:159 */        return null;
/* 160:    */      }
/* 161:    */      
/* 162:162 */      offset = parseInt(pString, offset, digits);
/* 163:163 */      if (digits.length() != 2) {
/* 164:164 */        pParsePosition.setErrorIndex(offset);
/* 165:165 */        return null;
/* 166:    */      }
/* 167:167 */      int second = Integer.parseInt(digits.toString());
/* 168:    */      int millis;
/* 169:169 */      if ((offset < length) && (pString.charAt(offset) == '.')) {
/* 170:170 */        offset++;
/* 171:171 */        offset = parseInt(pString, offset, digits);
/* 172:172 */        if (digits.length() > 0) {
/* 173:173 */          int millis = Integer.parseInt(digits.toString());
/* 174:174 */          if (millis > 999) {
/* 175:175 */            pParsePosition.setErrorIndex(offset);
/* 176:176 */            return null;
/* 177:    */          }
/* 178:178 */          for (int i = digits.length(); i < 3; i++) {
/* 179:179 */            millis *= 10;
/* 180:    */          }
/* 181:    */        } else {
/* 182:182 */          millis = 0;
/* 183:    */        }
/* 184:    */      } else {
/* 185:185 */        millis = 0;
/* 186:    */      }
/* 187:    */    } else {
/* 188:188 */      hour = minute = second = millis = 0;
/* 189:    */    }
/* 190:    */    
/* 191:191 */    digits.setLength(0);
/* 192:192 */    digits.append("GMT");
/* 193:193 */    if (offset < length) {
/* 194:194 */      char c = pString.charAt(offset);
/* 195:195 */      if (c == 'Z')
/* 196:    */      {
/* 197:197 */        offset++;
/* 198:198 */      } else if ((c == '+') || (c == '-')) {
/* 199:199 */        digits.append(c);
/* 200:200 */        offset++;
/* 201:201 */        for (int i = 0; i < 5; i++) {
/* 202:202 */          if (offset >= length) {
/* 203:203 */            pParsePosition.setErrorIndex(offset);
/* 204:204 */            return null;
/* 205:    */          }
/* 206:206 */          c = pString.charAt(offset);
/* 207:207 */          if (((i != 2) && (Character.isDigit(c))) || ((i == 2) && (c == ':')))
/* 208:    */          {
/* 209:209 */            digits.append(c);
/* 210:    */          } else {
/* 211:211 */            pParsePosition.setErrorIndex(offset);
/* 212:212 */            return null;
/* 213:    */          }
/* 214:214 */          offset++;
/* 215:    */        }
/* 216:    */      }
/* 217:    */    }
/* 218:    */    
/* 219:219 */    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(digits.toString()));
/* 220:220 */    cal.set(isMinus ? -year : year, this.parseDate ? month - 1 : month, mday, hour, minute, second);
/* 221:221 */    cal.set(14, millis);
/* 222:222 */    pParsePosition.setIndex(offset);
/* 223:223 */    return cal;
/* 224:    */  }
/* 225:    */  
/* 226:    */  private void append(StringBuffer pBuffer, int pNum, int pMinLen) {
/* 227:227 */    String s = Integer.toString(pNum);
/* 228:228 */    for (int i = s.length(); i < pMinLen; i++) {
/* 229:229 */      pBuffer.append('0');
/* 230:    */    }
/* 231:231 */    pBuffer.append(s);
/* 232:    */  }
/* 233:    */  
/* 234:    */  public StringBuffer format(Object pCalendar, StringBuffer pBuffer, FieldPosition pPos) {
/* 235:235 */    if (pCalendar == null) {
/* 236:236 */      throw new NullPointerException("The Calendar argument must not be null.");
/* 237:    */    }
/* 238:238 */    if (pBuffer == null) {
/* 239:239 */      throw new NullPointerException("The StringBuffer argument must not be null.");
/* 240:    */    }
/* 241:241 */    if (pPos == null) {
/* 242:242 */      throw new NullPointerException("The FieldPosition argument must not be null.");
/* 243:    */    }
/* 244:    */    
/* 245:245 */    Calendar cal = (Calendar)pCalendar;
/* 246:246 */    if (this.parseDate) {
/* 247:247 */      int year = cal.get(1);
/* 248:248 */      if (year < 0) {
/* 249:249 */        pBuffer.append('-');
/* 250:250 */        year = -year;
/* 251:    */      }
/* 252:252 */      append(pBuffer, year, 4);
/* 253:253 */      pBuffer.append('-');
/* 254:254 */      append(pBuffer, cal.get(2) + 1, 2);
/* 255:255 */      pBuffer.append('-');
/* 256:256 */      append(pBuffer, cal.get(5), 2);
/* 257:257 */      if (this.parseTime) {
/* 258:258 */        pBuffer.append('T');
/* 259:    */      }
/* 260:    */    }
/* 261:261 */    if (this.parseTime) {
/* 262:262 */      append(pBuffer, cal.get(11), 2);
/* 263:263 */      pBuffer.append(':');
/* 264:264 */      append(pBuffer, cal.get(12), 2);
/* 265:265 */      pBuffer.append(':');
/* 266:266 */      append(pBuffer, cal.get(13), 2);
/* 267:267 */      int millis = cal.get(14);
/* 268:268 */      if (millis > 0) {
/* 269:269 */        pBuffer.append('.');
/* 270:270 */        append(pBuffer, millis, 3);
/* 271:    */      }
/* 272:    */    }
/* 273:273 */    TimeZone tz = cal.getTimeZone();
/* 274:    */    
/* 275:275 */    int offset = cal.get(15);
/* 276:276 */    if (tz.inDaylightTime(cal.getTime())) {
/* 277:277 */      offset += cal.get(16);
/* 278:    */    }
/* 279:279 */    if (offset == 0) {
/* 280:280 */      pBuffer.append('Z');
/* 281:    */    } else {
/* 282:282 */      if (offset < 0) {
/* 283:283 */        pBuffer.append('-');
/* 284:284 */        offset = -offset;
/* 285:    */      } else {
/* 286:286 */        pBuffer.append('+');
/* 287:    */      }
/* 288:288 */      int minutes = offset / 60000;
/* 289:289 */      int hours = minutes / 60;
/* 290:290 */      minutes -= hours * 60;
/* 291:291 */      append(pBuffer, hours, 2);
/* 292:292 */      pBuffer.append(':');
/* 293:293 */      append(pBuffer, minutes, 2);
/* 294:    */    }
/* 295:295 */    return pBuffer;
/* 296:    */  }
/* 297:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.util.XsDateTimeFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */