/*   1:    */package it.unimi.dsi.fastutil.doubles;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.AbstractIndirectPriorityQueue;
/*   4:    */import it.unimi.dsi.fastutil.ints.IntArrays;
/*   5:    */import java.util.NoSuchElementException;
/*   6:    */
/*  57:    */public class DoubleHeapSemiIndirectPriorityQueue
/*  58:    */  extends AbstractIndirectPriorityQueue<Double>
/*  59:    */  implements DoubleIndirectPriorityQueue
/*  60:    */{
/*  61:    */  protected double[] refArray;
/*  62: 62 */  protected int[] heap = IntArrays.EMPTY_ARRAY;
/*  63:    */  
/*  65:    */  protected int size;
/*  66:    */  
/*  68:    */  protected DoubleComparator c;
/*  69:    */  
/*  72:    */  public DoubleHeapSemiIndirectPriorityQueue(double[] refArray, int capacity, DoubleComparator c)
/*  73:    */  {
/*  74: 74 */    if (capacity > 0) this.heap = new int[capacity];
/*  75: 75 */    this.refArray = refArray;
/*  76: 76 */    this.c = c;
/*  77:    */  }
/*  78:    */  
/*  82:    */  public DoubleHeapSemiIndirectPriorityQueue(double[] refArray, int capacity)
/*  83:    */  {
/*  84: 84 */    this(refArray, capacity, null);
/*  85:    */  }
/*  86:    */  
/*  90:    */  public DoubleHeapSemiIndirectPriorityQueue(double[] refArray, DoubleComparator c)
/*  91:    */  {
/*  92: 92 */    this(refArray, refArray.length, c);
/*  93:    */  }
/*  94:    */  
/*  96:    */  public DoubleHeapSemiIndirectPriorityQueue(double[] refArray)
/*  97:    */  {
/*  98: 98 */    this(refArray, refArray.length, null);
/*  99:    */  }
/* 100:    */  
/* 110:    */  public DoubleHeapSemiIndirectPriorityQueue(double[] refArray, int[] a, int size, DoubleComparator c)
/* 111:    */  {
/* 112:112 */    this(refArray, 0, c);
/* 113:113 */    this.heap = a;
/* 114:114 */    this.size = size;
/* 115:115 */    DoubleSemiIndirectHeaps.makeHeap(refArray, a, size, c);
/* 116:    */  }
/* 117:    */  
/* 126:    */  public DoubleHeapSemiIndirectPriorityQueue(double[] refArray, int[] a, DoubleComparator c)
/* 127:    */  {
/* 128:128 */    this(refArray, a, a.length, c);
/* 129:    */  }
/* 130:    */  
/* 139:    */  public DoubleHeapSemiIndirectPriorityQueue(double[] refArray, int[] a, int size)
/* 140:    */  {
/* 141:141 */    this(refArray, a, size, null);
/* 142:    */  }
/* 143:    */  
/* 151:    */  public DoubleHeapSemiIndirectPriorityQueue(double[] refArray, int[] a)
/* 152:    */  {
/* 153:153 */    this(refArray, a, a.length);
/* 154:    */  }
/* 155:    */  
/* 160:    */  protected void ensureElement(int index)
/* 161:    */  {
/* 162:162 */    if (index < 0) throw new IndexOutOfBoundsException("Index (" + index + ") is negative");
/* 163:163 */    if (index >= this.refArray.length) throw new IndexOutOfBoundsException("Index (" + index + ") is larger than or equal to reference array size (" + this.refArray.length + ")");
/* 164:    */  }
/* 165:    */  
/* 166:    */  public void enqueue(int x) {
/* 167:167 */    ensureElement(x);
/* 168:    */    
/* 169:169 */    if (this.size == this.heap.length) { this.heap = IntArrays.grow(this.heap, this.size + 1);
/* 170:    */    }
/* 171:171 */    this.heap[(this.size++)] = x;
/* 172:172 */    DoubleSemiIndirectHeaps.upHeap(this.refArray, this.heap, this.size, this.size - 1, this.c);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public int dequeue() {
/* 176:176 */    if (this.size == 0) throw new NoSuchElementException();
/* 177:177 */    int result = this.heap[0];
/* 178:178 */    this.heap[0] = this.heap[(--this.size)];
/* 179:179 */    if (this.size != 0) DoubleSemiIndirectHeaps.downHeap(this.refArray, this.heap, this.size, 0, this.c);
/* 180:180 */    return result;
/* 181:    */  }
/* 182:    */  
/* 183:    */  public int first() {
/* 184:184 */    if (this.size == 0) throw new NoSuchElementException();
/* 185:185 */    return this.heap[0];
/* 186:    */  }
/* 187:    */  
/* 195:    */  public void changed()
/* 196:    */  {
/* 197:197 */    DoubleSemiIndirectHeaps.downHeap(this.refArray, this.heap, this.size, 0, this.c);
/* 198:    */  }
/* 199:    */  
/* 202:    */  public void allChanged()
/* 203:    */  {
/* 204:204 */    DoubleSemiIndirectHeaps.makeHeap(this.refArray, this.heap, this.size, this.c);
/* 205:    */  }
/* 206:    */  
/* 207:207 */  public int size() { return this.size; }
/* 208:    */  
/* 209:209 */  public void clear() { this.size = 0; }
/* 210:    */  
/* 213:    */  public void trim()
/* 214:    */  {
/* 215:215 */    this.heap = IntArrays.trim(this.heap, this.size);
/* 216:    */  }
/* 217:    */  
/* 218:218 */  public DoubleComparator comparator() { return this.c; }
/* 219:    */  
/* 220:    */  public int front(int[] a) {
/* 221:221 */    return DoubleSemiIndirectHeaps.front(this.refArray, this.heap, this.size, a);
/* 222:    */  }
/* 223:    */  
/* 224:    */  public String toString() {
/* 225:225 */    StringBuffer s = new StringBuffer();
/* 226:226 */    s.append("[");
/* 227:227 */    for (int i = 0; i < this.size; i++) {
/* 228:228 */      if (i != 0) s.append(", ");
/* 229:229 */      s.append(this.refArray[this.heap[i]]);
/* 230:    */    }
/* 231:231 */    s.append("]");
/* 232:232 */    return s.toString();
/* 233:    */  }
/* 234:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.doubles.DoubleHeapSemiIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */