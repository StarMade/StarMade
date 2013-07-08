/*   1:    */package it.unimi.dsi.fastutil.chars;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.AbstractIndirectPriorityQueue;
/*   4:    */import it.unimi.dsi.fastutil.ints.IntArrays;
/*   5:    */import java.util.NoSuchElementException;
/*   6:    */
/*  59:    */public class CharArrayIndirectPriorityQueue
/*  60:    */  extends AbstractIndirectPriorityQueue<Character>
/*  61:    */  implements CharIndirectPriorityQueue
/*  62:    */{
/*  63:    */  protected char[] refArray;
/*  64: 64 */  protected int[] array = IntArrays.EMPTY_ARRAY;
/*  65:    */  
/*  67:    */  protected int size;
/*  68:    */  
/*  70:    */  protected CharComparator c;
/*  71:    */  
/*  73:    */  protected int firstIndex;
/*  74:    */  
/*  75:    */  protected boolean firstIndexValid;
/*  76:    */  
/*  78:    */  public CharArrayIndirectPriorityQueue(char[] refArray, int capacity, CharComparator c)
/*  79:    */  {
/*  80: 80 */    if (capacity > 0) this.array = new int[capacity];
/*  81: 81 */    this.refArray = refArray;
/*  82: 82 */    this.c = c;
/*  83:    */  }
/*  84:    */  
/*  88:    */  public CharArrayIndirectPriorityQueue(char[] refArray, int capacity)
/*  89:    */  {
/*  90: 90 */    this(refArray, capacity, null);
/*  91:    */  }
/*  92:    */  
/*  96:    */  public CharArrayIndirectPriorityQueue(char[] refArray, CharComparator c)
/*  97:    */  {
/*  98: 98 */    this(refArray, refArray.length, c);
/*  99:    */  }
/* 100:    */  
/* 102:    */  public CharArrayIndirectPriorityQueue(char[] refArray)
/* 103:    */  {
/* 104:104 */    this(refArray, refArray.length, null);
/* 105:    */  }
/* 106:    */  
/* 114:    */  public CharArrayIndirectPriorityQueue(char[] refArray, int[] a, int size, CharComparator c)
/* 115:    */  {
/* 116:116 */    this(refArray, 0, c);
/* 117:117 */    this.array = a;
/* 118:118 */    this.size = size;
/* 119:    */  }
/* 120:    */  
/* 127:    */  public CharArrayIndirectPriorityQueue(char[] refArray, int[] a, CharComparator c)
/* 128:    */  {
/* 129:129 */    this(refArray, a, a.length, c);
/* 130:    */  }
/* 131:    */  
/* 139:    */  public CharArrayIndirectPriorityQueue(char[] refArray, int[] a, int size)
/* 140:    */  {
/* 141:141 */    this(refArray, a, size, null);
/* 142:    */  }
/* 143:    */  
/* 151:    */  public CharArrayIndirectPriorityQueue(char[] refArray, int[] a)
/* 152:    */  {
/* 153:153 */    this(refArray, a, a.length);
/* 154:    */  }
/* 155:    */  
/* 159:    */  private int findFirst()
/* 160:    */  {
/* 161:161 */    if (this.firstIndexValid) return this.firstIndex;
/* 162:162 */    this.firstIndexValid = true;
/* 163:163 */    int i = this.size;
/* 164:164 */    i--;int firstIndex = i;
/* 165:165 */    char first = this.refArray[this.array[firstIndex]];
/* 166:    */    
/* 167:167 */    for (this.c != null; i-- != 0;) if (this.refArray[this.array[i]] < first) first = this.refArray[this.array[(firstIndex = i)]];
/* 168:168 */    while (i-- != 0) if (this.c.compare(this.refArray[this.array[i]], first) < 0) { first = this.refArray[this.array[(firstIndex = i)]];
/* 169:    */      }
/* 170:170 */    return this.firstIndex = firstIndex;
/* 171:    */  }
/* 172:    */  
/* 175:    */  private int findLast()
/* 176:    */  {
/* 177:177 */    int i = this.size;
/* 178:178 */    i--;int lastIndex = i;
/* 179:179 */    char last = this.refArray[this.array[lastIndex]];
/* 180:    */    
/* 181:181 */    for (this.c != null; i-- != 0;) if (last < this.refArray[this.array[i]]) last = this.refArray[this.array[(lastIndex = i)]];
/* 182:182 */    while (i-- != 0) if (this.c.compare(last, this.refArray[this.array[i]]) < 0) { last = this.refArray[this.array[(lastIndex = i)]];
/* 183:    */      }
/* 184:184 */    return lastIndex;
/* 185:    */  }
/* 186:    */  
/* 187:    */  protected final void ensureNonEmpty() {
/* 188:188 */    if (this.size == 0) { throw new NoSuchElementException();
/* 189:    */    }
/* 190:    */  }
/* 191:    */  
/* 195:    */  protected void ensureElement(int index)
/* 196:    */  {
/* 197:197 */    if (index < 0) throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
/* 198:198 */    if (index >= this.refArray.length) { throw new IndexOutOfBoundsException("Index (" + index + ") is larger than or equal to reference array size (" + this.refArray.length + ")");
/* 199:    */    }
/* 200:    */  }
/* 201:    */  
/* 206:    */  public void enqueue(int x)
/* 207:    */  {
/* 208:208 */    ensureElement(x);
/* 209:    */    
/* 210:210 */    if (this.size == this.array.length) this.array = IntArrays.grow(this.array, this.size + 1);
/* 211:211 */    if (this.firstIndexValid) {
/* 212:212 */      if (this.c == null) { if (this.refArray[x] < this.refArray[this.array[this.firstIndex]]) this.firstIndex = this.size;
/* 213:213 */      } else if (this.c.compare(this.refArray[x], this.refArray[this.array[this.firstIndex]]) < 0) this.firstIndex = this.size;
/* 214:    */    } else
/* 215:215 */      this.firstIndexValid = false;
/* 216:216 */    this.array[(this.size++)] = x;
/* 217:    */  }
/* 218:    */  
/* 219:    */  public int dequeue() {
/* 220:220 */    ensureNonEmpty();
/* 221:221 */    int firstIndex = findFirst();
/* 222:222 */    int result = this.array[firstIndex];
/* 223:223 */    if (--this.size != 0) System.arraycopy(this.array, firstIndex + 1, this.array, firstIndex, this.size - firstIndex);
/* 224:224 */    this.firstIndexValid = false;
/* 225:225 */    return result;
/* 226:    */  }
/* 227:    */  
/* 228:    */  public int first() {
/* 229:229 */    ensureNonEmpty();
/* 230:230 */    return this.array[findFirst()];
/* 231:    */  }
/* 232:    */  
/* 233:    */  public int last() {
/* 234:234 */    ensureNonEmpty();
/* 235:235 */    return this.array[findLast()];
/* 236:    */  }
/* 237:    */  
/* 238:    */  public void changed() {
/* 239:239 */    ensureNonEmpty();
/* 240:240 */    this.firstIndexValid = false;
/* 241:    */  }
/* 242:    */  
/* 248:    */  public void changed(int index)
/* 249:    */  {
/* 250:250 */    ensureElement(index);
/* 251:251 */    if (index == this.firstIndex) this.firstIndexValid = false;
/* 252:    */  }
/* 253:    */  
/* 254:    */  public void allChanged() {
/* 255:255 */    this.firstIndexValid = false;
/* 256:    */  }
/* 257:    */  
/* 258:    */  public boolean remove(int index) {
/* 259:259 */    ensureElement(index);
/* 260:260 */    int[] a = this.array;
/* 261:261 */    int i = this.size;
/* 262:262 */    while (i-- != 0) if (a[i] == index) break;
/* 263:263 */    if (i < 0) return false;
/* 264:264 */    this.firstIndexValid = false;
/* 265:265 */    if (--this.size != 0) System.arraycopy(a, i + 1, a, i, this.size - i);
/* 266:266 */    return true;
/* 267:    */  }
/* 268:    */  
/* 269:    */  public int front(int[] a) {
/* 270:270 */    char top = this.refArray[this.array[findFirst()]];
/* 271:271 */    int i = this.size;int c = 0;
/* 272:272 */    while (i-- != 0) if (top == this.refArray[this.array[i]]) a[(c++)] = this.array[i];
/* 273:273 */    return c;
/* 274:    */  }
/* 275:    */  
/* 276:276 */  public int size() { return this.size; }
/* 277:    */  
/* 278:278 */  public void clear() { this.size = 0;this.firstIndexValid = false;
/* 279:    */  }
/* 280:    */  
/* 282:    */  public void trim()
/* 283:    */  {
/* 284:284 */    this.array = IntArrays.trim(this.array, this.size);
/* 285:    */  }
/* 286:    */  
/* 287:287 */  public CharComparator comparator() { return this.c; }
/* 288:    */  
/* 289:    */  public String toString() {
/* 290:290 */    StringBuffer s = new StringBuffer();
/* 291:291 */    s.append("[");
/* 292:292 */    for (int i = 0; i < this.size; i++) {
/* 293:293 */      if (i != 0) s.append(", ");
/* 294:294 */      s.append(this.refArray[this.array[i]]);
/* 295:    */    }
/* 296:296 */    s.append("]");
/* 297:297 */    return s.toString();
/* 298:    */  }
/* 299:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.CharArrayIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */