/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.Collections;
/*   5:    */import java.util.HashMap;
/*   6:    */import java.util.HashSet;
/*   7:    */import java.util.Map;
/*   8:    */import java.util.Set;
/*   9:    */
/*  44:    */public class CharSet
/*  45:    */  implements Serializable
/*  46:    */{
/*  47:    */  private static final long serialVersionUID = 5947847346149275958L;
/*  48: 48 */  public static final CharSet EMPTY = new CharSet(new String[] { (String)null });
/*  49:    */  
/*  54: 54 */  public static final CharSet ASCII_ALPHA = new CharSet(new String[] { "a-zA-Z" });
/*  55:    */  
/*  60: 60 */  public static final CharSet ASCII_ALPHA_LOWER = new CharSet(new String[] { "a-z" });
/*  61:    */  
/*  66: 66 */  public static final CharSet ASCII_ALPHA_UPPER = new CharSet(new String[] { "A-Z" });
/*  67:    */  
/*  72: 72 */  public static final CharSet ASCII_NUMERIC = new CharSet(new String[] { "0-9" });
/*  73:    */  
/*  79: 79 */  protected static final Map<String, CharSet> COMMON = Collections.synchronizedMap(new HashMap());
/*  80:    */  
/*  81:    */  static {
/*  82: 82 */    COMMON.put(null, EMPTY);
/*  83: 83 */    COMMON.put("", EMPTY);
/*  84: 84 */    COMMON.put("a-zA-Z", ASCII_ALPHA);
/*  85: 85 */    COMMON.put("A-Za-z", ASCII_ALPHA);
/*  86: 86 */    COMMON.put("a-z", ASCII_ALPHA_LOWER);
/*  87: 87 */    COMMON.put("A-Z", ASCII_ALPHA_UPPER);
/*  88: 88 */    COMMON.put("0-9", ASCII_NUMERIC);
/*  89:    */  }
/*  90:    */  
/*  92: 92 */  private final Set<CharRange> set = Collections.synchronizedSet(new HashSet());
/*  93:    */  
/* 137:    */  public static CharSet getInstance(String... setStrs)
/* 138:    */  {
/* 139:139 */    if (setStrs == null) {
/* 140:140 */      return null;
/* 141:    */    }
/* 142:142 */    if (setStrs.length == 1) {
/* 143:143 */      CharSet common = (CharSet)COMMON.get(setStrs[0]);
/* 144:144 */      if (common != null) {
/* 145:145 */        return common;
/* 146:    */      }
/* 147:    */    }
/* 148:148 */    return new CharSet(setStrs);
/* 149:    */  }
/* 150:    */  
/* 159:    */  protected CharSet(String... set)
/* 160:    */  {
/* 161:161 */    int sz = set.length;
/* 162:162 */    for (int i = 0; i < sz; i++) {
/* 163:163 */      add(set[i]);
/* 164:    */    }
/* 165:    */  }
/* 166:    */  
/* 172:    */  protected void add(String str)
/* 173:    */  {
/* 174:174 */    if (str == null) {
/* 175:175 */      return;
/* 176:    */    }
/* 177:    */    
/* 178:178 */    int len = str.length();
/* 179:179 */    int pos = 0;
/* 180:180 */    while (pos < len) {
/* 181:181 */      int remainder = len - pos;
/* 182:182 */      if ((remainder >= 4) && (str.charAt(pos) == '^') && (str.charAt(pos + 2) == '-'))
/* 183:    */      {
/* 184:184 */        this.set.add(CharRange.isNotIn(str.charAt(pos + 1), str.charAt(pos + 3)));
/* 185:185 */        pos += 4;
/* 186:186 */      } else if ((remainder >= 3) && (str.charAt(pos + 1) == '-'))
/* 187:    */      {
/* 188:188 */        this.set.add(CharRange.isIn(str.charAt(pos), str.charAt(pos + 2)));
/* 189:189 */        pos += 3;
/* 190:190 */      } else if ((remainder >= 2) && (str.charAt(pos) == '^'))
/* 191:    */      {
/* 192:192 */        this.set.add(CharRange.isNot(str.charAt(pos + 1)));
/* 193:193 */        pos += 2;
/* 194:    */      }
/* 195:    */      else {
/* 196:196 */        this.set.add(CharRange.is(str.charAt(pos)));
/* 197:197 */        pos++;
/* 198:    */      }
/* 199:    */    }
/* 200:    */  }
/* 201:    */  
/* 210:    */  CharRange[] getCharRanges()
/* 211:    */  {
/* 212:212 */    return (CharRange[])this.set.toArray(new CharRange[this.set.size()]);
/* 213:    */  }
/* 214:    */  
/* 222:    */  public boolean contains(char ch)
/* 223:    */  {
/* 224:224 */    for (CharRange range : this.set) {
/* 225:225 */      if (range.contains(ch)) {
/* 226:226 */        return true;
/* 227:    */      }
/* 228:    */    }
/* 229:229 */    return false;
/* 230:    */  }
/* 231:    */  
/* 245:    */  public boolean equals(Object obj)
/* 246:    */  {
/* 247:247 */    if (obj == this) {
/* 248:248 */      return true;
/* 249:    */    }
/* 250:250 */    if (!(obj instanceof CharSet)) {
/* 251:251 */      return false;
/* 252:    */    }
/* 253:253 */    CharSet other = (CharSet)obj;
/* 254:254 */    return this.set.equals(other.set);
/* 255:    */  }
/* 256:    */  
/* 263:    */  public int hashCode()
/* 264:    */  {
/* 265:265 */    return 89 + this.set.hashCode();
/* 266:    */  }
/* 267:    */  
/* 273:    */  public String toString()
/* 274:    */  {
/* 275:275 */    return this.set.toString();
/* 276:    */  }
/* 277:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.CharSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */