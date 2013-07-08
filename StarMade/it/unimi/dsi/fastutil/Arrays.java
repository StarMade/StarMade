/*   1:    */package it.unimi.dsi.fastutil;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.ints.IntComparator;
/*   4:    */
/*  40:    */public class Arrays
/*  41:    */{
/*  42:    */  private static final int SMALL = 7;
/*  43:    */  private static final int MEDIUM = 40;
/*  44:    */  
/*  45:    */  public static void ensureFromTo(int arrayLength, int from, int to)
/*  46:    */  {
/*  47: 47 */    if (from < 0) throw new ArrayIndexOutOfBoundsException("Start index (" + from + ") is negative");
/*  48: 48 */    if (from > to) throw new IllegalArgumentException("Start index (" + from + ") is greater than end index (" + to + ")");
/*  49: 49 */    if (to > arrayLength) { throw new ArrayIndexOutOfBoundsException("End index (" + to + ") is greater than array length (" + arrayLength + ")");
/*  50:    */    }
/*  51:    */  }
/*  52:    */  
/*  61:    */  public static void ensureOffsetLength(int arrayLength, int offset, int length)
/*  62:    */  {
/*  63: 63 */    if (offset < 0) throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
/*  64: 64 */    if (length < 0) throw new IllegalArgumentException("Length (" + length + ") is negative");
/*  65: 65 */    if (offset + length > arrayLength) { throw new ArrayIndexOutOfBoundsException("Last index (" + (offset + length) + ") is greater than array length (" + arrayLength + ")");
/*  66:    */    }
/*  67:    */  }
/*  68:    */  
/*  76:    */  private static void inPlaceMerge(int from, int mid, int to, IntComparator comp, Swapper swapper)
/*  77:    */  {
/*  78: 78 */    if ((from >= mid) || (mid >= to)) return;
/*  79: 79 */    if (to - from == 2) {
/*  80: 80 */      if (comp.compare(mid, from) < 0) { swapper.swap(from, mid);
/*  81:    */      }
/*  82:    */      return;
/*  83:    */    }
/*  84:    */    int secondCut;
/*  85:    */    int secondCut;
/*  86:    */    int firstCut;
/*  87: 87 */    if (mid - from > to - mid) {
/*  88: 88 */      int firstCut = from + (mid - from) / 2;
/*  89: 89 */      secondCut = lowerBound(mid, to, firstCut, comp);
/*  90:    */    }
/*  91:    */    else {
/*  92: 92 */      secondCut = mid + (to - mid) / 2;
/*  93: 93 */      firstCut = upperBound(from, mid, secondCut, comp);
/*  94:    */    }
/*  95:    */    
/*  96: 96 */    int first2 = firstCut;
/*  97: 97 */    int middle2 = mid;
/*  98: 98 */    int last2 = secondCut;
/*  99: 99 */    if ((middle2 != first2) && (middle2 != last2)) {
/* 100:100 */      int first1 = first2;
/* 101:101 */      int last1 = middle2;
/* 102:102 */      while (first1 < --last1)
/* 103:103 */        swapper.swap(first1++, last1);
/* 104:104 */      first1 = middle2;
/* 105:105 */      last1 = last2;
/* 106:106 */      while (first1 < --last1)
/* 107:107 */        swapper.swap(first1++, last1);
/* 108:108 */      first1 = first2;
/* 109:109 */      last1 = last2;
/* 110:110 */      while (first1 < --last1) {
/* 111:111 */        swapper.swap(first1++, last1);
/* 112:    */      }
/* 113:    */    }
/* 114:114 */    mid = firstCut + (secondCut - mid);
/* 115:115 */    inPlaceMerge(from, firstCut, mid, comp, swapper);
/* 116:116 */    inPlaceMerge(mid, secondCut, to, comp, swapper);
/* 117:    */  }
/* 118:    */  
/* 131:    */  private static int lowerBound(int from, int to, int pos, IntComparator comp)
/* 132:    */  {
/* 133:133 */    int len = to - from;
/* 134:134 */    while (len > 0) {
/* 135:135 */      int half = len / 2;
/* 136:136 */      int middle = from + half;
/* 137:137 */      if (comp.compare(middle, pos) < 0) {
/* 138:138 */        from = middle + 1;
/* 139:139 */        len -= half + 1;
/* 140:    */      }
/* 141:    */      else {
/* 142:142 */        len = half;
/* 143:    */      }
/* 144:    */    }
/* 145:145 */    return from;
/* 146:    */  }
/* 147:    */  
/* 161:    */  private static int upperBound(int from, int mid, int pos, IntComparator comp)
/* 162:    */  {
/* 163:163 */    int len = mid - from;
/* 164:164 */    while (len > 0) {
/* 165:165 */      int half = len / 2;
/* 166:166 */      int middle = from + half;
/* 167:167 */      if (comp.compare(pos, middle) < 0) {
/* 168:168 */        len = half;
/* 169:    */      }
/* 170:    */      else {
/* 171:171 */        from = middle + 1;
/* 172:172 */        len -= half + 1;
/* 173:    */      }
/* 174:    */    }
/* 175:175 */    return from;
/* 176:    */  }
/* 177:    */  
/* 180:    */  private static int med3(int a, int b, int c, IntComparator comp)
/* 181:    */  {
/* 182:182 */    int ab = comp.compare(a, b);
/* 183:183 */    int ac = comp.compare(a, c);
/* 184:184 */    int bc = comp.compare(b, c);
/* 185:185 */    return ac > 0 ? c : bc > 0 ? b : ab < 0 ? a : ac < 0 ? c : bc < 0 ? b : a;
/* 186:    */  }
/* 187:    */  
/* 209:    */  public static void mergeSort(int from, int to, IntComparator c, Swapper swapper)
/* 210:    */  {
/* 211:211 */    int length = to - from;
/* 212:    */    
/* 214:214 */    if (length < 7) {
/* 215:215 */      for (int i = from; i < to; i++) {
/* 216:216 */        for (int j = i; (j > from) && (c.compare(j - 1, j) > 0); j--) {
/* 217:217 */          swapper.swap(j, j - 1);
/* 218:    */        }
/* 219:    */      }
/* 220:220 */      return;
/* 221:    */    }
/* 222:    */    
/* 224:224 */    int mid = from + to >>> 1;
/* 225:225 */    mergeSort(from, mid, c, swapper);
/* 226:226 */    mergeSort(mid, to, c, swapper);
/* 227:    */    
/* 230:230 */    if (c.compare(mid - 1, mid) <= 0) { return;
/* 231:    */    }
/* 232:    */    
/* 233:233 */    inPlaceMerge(from, mid, to, c, swapper);
/* 234:    */  }
/* 235:    */  
/* 248:    */  public static void quickSort(int from, int to, IntComparator comp, Swapper swapper)
/* 249:    */  {
/* 250:250 */    int len = to - from;
/* 251:    */    
/* 252:252 */    if (len < 7) {
/* 253:253 */      for (int i = from; i < to; i++) {
/* 254:254 */        for (int j = i; (j > from) && (comp.compare(j - 1, j) > 0); j--)
/* 255:255 */          swapper.swap(j, j - 1);
/* 256:    */      }
/* 257:257 */      return;
/* 258:    */    }
/* 259:    */    
/* 261:261 */    int m = from + len / 2;
/* 262:262 */    if (len > 7) {
/* 263:263 */      int l = from;
/* 264:264 */      int n = to - 1;
/* 265:265 */      if (len > 40) {
/* 266:266 */        int s = len / 8;
/* 267:267 */        l = med3(l, l + s, l + 2 * s, comp);
/* 268:268 */        m = med3(m - s, m, m + s, comp);
/* 269:269 */        n = med3(n - 2 * s, n - s, n, comp);
/* 270:    */      }
/* 271:271 */      m = med3(l, m, n, comp);
/* 272:    */    }
/* 273:    */    
/* 275:275 */    int a = from;
/* 276:276 */    int b = a;
/* 277:277 */    int c = to - 1;
/* 278:    */    
/* 279:279 */    int d = c;
/* 280:    */    for (;;) {
/* 281:    */      int comparison;
/* 282:282 */      if ((b <= c) && ((comparison = comp.compare(b, m)) <= 0)) {
/* 283:283 */        if (comparison == 0) {
/* 284:284 */          if (a == m) { m = b;
/* 285:285 */          } else if (b == m) m = a;
/* 286:286 */          swapper.swap(a++, b);
/* 287:    */        }
/* 288:288 */        b++;
/* 289:    */      } else { int comparison;
/* 290:290 */        while ((c >= b) && ((comparison = comp.compare(c, m)) >= 0)) {
/* 291:291 */          if (comparison == 0) {
/* 292:292 */            if (c == m) { m = d;
/* 293:293 */            } else if (d == m) m = c;
/* 294:294 */            swapper.swap(c, d--);
/* 295:    */          }
/* 296:296 */          c--;
/* 297:    */        }
/* 298:298 */        if (b > c) break;
/* 299:299 */        if (b == m) { m = d;
/* 300:300 */        } else if (c == m) m = c;
/* 301:301 */        swapper.swap(b++, c--);
/* 302:    */      }
/* 303:    */    }
/* 304:    */    
/* 306:306 */    int n = to;
/* 307:307 */    int s = Math.min(a - from, b - a);
/* 308:308 */    vecSwap(swapper, from, b - s, s);
/* 309:309 */    s = Math.min(d - c, n - d - 1);
/* 310:310 */    vecSwap(swapper, b, n - s, s);
/* 311:    */    
/* 313:313 */    if ((s = b - a) > 1) quickSort(from, from + s, comp, swapper);
/* 314:314 */    if ((s = d - c) > 1) { quickSort(n - s, n, comp, swapper);
/* 315:    */    }
/* 316:    */  }
/* 317:    */  
/* 320:    */  private static void vecSwap(Swapper swapper, int from, int l, int s)
/* 321:    */  {
/* 322:322 */    for (int i = 0; i < s; l++) { swapper.swap(from, l);i++;from++;
/* 323:    */    }
/* 324:    */  }
/* 325:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.Arrays
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */