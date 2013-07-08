/*   1:    */package it.unimi.dsi.fastutil.ints;
/*   2:    */
/*   3:    */import java.util.NoSuchElementException;
/*   4:    */
/*  59:    */public class IntHeapIndirectPriorityQueue
/*  60:    */  extends IntHeapSemiIndirectPriorityQueue
/*  61:    */{
/*  62:    */  protected int[] inv;
/*  63:    */  
/*  64:    */  public IntHeapIndirectPriorityQueue(int[] refArray, int capacity, IntComparator c)
/*  65:    */  {
/*  66: 66 */    super(refArray, capacity, c);
/*  67: 67 */    if (capacity > 0) this.heap = new int[capacity];
/*  68: 68 */    this.refArray = refArray;
/*  69: 69 */    this.c = c;
/*  70: 70 */    this.inv = new int[refArray.length];
/*  71: 71 */    IntArrays.fill(this.inv, -1);
/*  72:    */  }
/*  73:    */  
/*  77:    */  public IntHeapIndirectPriorityQueue(int[] refArray, int capacity)
/*  78:    */  {
/*  79: 79 */    this(refArray, capacity, null);
/*  80:    */  }
/*  81:    */  
/*  85:    */  public IntHeapIndirectPriorityQueue(int[] refArray, IntComparator c)
/*  86:    */  {
/*  87: 87 */    this(refArray, refArray.length, c);
/*  88:    */  }
/*  89:    */  
/*  91:    */  public IntHeapIndirectPriorityQueue(int[] refArray)
/*  92:    */  {
/*  93: 93 */    this(refArray, refArray.length, null);
/*  94:    */  }
/*  95:    */  
/* 105:    */  public IntHeapIndirectPriorityQueue(int[] refArray, int[] a, int size, IntComparator c)
/* 106:    */  {
/* 107:107 */    this(refArray, 0, c);
/* 108:108 */    this.heap = a;
/* 109:109 */    this.size = size;
/* 110:110 */    int i = size;
/* 111:111 */    while (i-- != 0) {
/* 112:112 */      if (this.inv[a[i]] != -1) throw new IllegalArgumentException("Index " + a[i] + " appears twice in the heap");
/* 113:113 */      this.inv[a[i]] = i;
/* 114:    */    }
/* 115:115 */    IntIndirectHeaps.makeHeap(refArray, a, this.inv, size, c);
/* 116:    */  }
/* 117:    */  
/* 126:    */  public IntHeapIndirectPriorityQueue(int[] refArray, int[] a, IntComparator c)
/* 127:    */  {
/* 128:128 */    this(refArray, a, a.length, c);
/* 129:    */  }
/* 130:    */  
/* 139:    */  public IntHeapIndirectPriorityQueue(int[] refArray, int[] a, int size)
/* 140:    */  {
/* 141:141 */    this(refArray, a, size, null);
/* 142:    */  }
/* 143:    */  
/* 153:153 */  public IntHeapIndirectPriorityQueue(int[] refArray, int[] a) { this(refArray, a, a.length); }
/* 154:    */  
/* 155:    */  public void enqueue(int x) {
/* 156:156 */    if (this.inv[x] >= 0) throw new IllegalArgumentException("Index " + x + " belongs to the queue");
/* 157:157 */    if (this.size == this.heap.length) { this.heap = IntArrays.grow(this.heap, this.size + 1);
/* 158:    */    }
/* 159:159 */    int tmp83_82 = x;this.heap[this.size] = tmp83_82;this.inv[tmp83_82] = (this.size++);
/* 160:    */    
/* 161:161 */    IntIndirectHeaps.upHeap(this.refArray, this.heap, this.inv, this.size, this.size - 1, this.c);
/* 162:    */  }
/* 163:    */  
/* 164:    */  public boolean contains(int index) {
/* 165:165 */    return this.inv[index] >= 0;
/* 166:    */  }
/* 167:    */  
/* 168:    */  public int dequeue() {
/* 169:169 */    if (this.size == 0) throw new NoSuchElementException();
/* 170:170 */    int result = this.heap[0];
/* 171:171 */    if (--this.size != 0) { int tmp54_53 = this.heap[this.size];this.heap[0] = tmp54_53;this.inv[tmp54_53] = 0; }
/* 172:172 */    this.inv[result] = -1;
/* 173:    */    
/* 174:174 */    if (this.size != 0) IntIndirectHeaps.downHeap(this.refArray, this.heap, this.inv, this.size, 0, this.c);
/* 175:175 */    return result;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public void changed() {
/* 179:179 */    IntIndirectHeaps.downHeap(this.refArray, this.heap, this.inv, this.size, 0, this.c);
/* 180:    */  }
/* 181:    */  
/* 182:    */  public void changed(int index) {
/* 183:183 */    int pos = this.inv[index];
/* 184:184 */    if (pos < 0) throw new IllegalArgumentException("Index " + index + " does not belong to the queue");
/* 185:185 */    int newPos = IntIndirectHeaps.upHeap(this.refArray, this.heap, this.inv, this.size, pos, this.c);
/* 186:186 */    IntIndirectHeaps.downHeap(this.refArray, this.heap, this.inv, this.size, newPos, this.c);
/* 187:    */  }
/* 188:    */  
/* 191:    */  public void allChanged()
/* 192:    */  {
/* 193:193 */    IntIndirectHeaps.makeHeap(this.refArray, this.heap, this.inv, this.size, this.c);
/* 194:    */  }
/* 195:    */  
/* 196:    */  public boolean remove(int index)
/* 197:    */  {
/* 198:198 */    int result = this.inv[index];
/* 199:199 */    if (result < 0) return false;
/* 200:200 */    this.inv[index] = -1;
/* 201:    */    
/* 202:202 */    if (result < --this.size) {
/* 203:203 */      int tmp53_52 = this.heap[this.size];this.heap[result] = tmp53_52;this.inv[tmp53_52] = result;
/* 204:204 */      int newPos = IntIndirectHeaps.upHeap(this.refArray, this.heap, this.inv, this.size, result, this.c);
/* 205:205 */      IntIndirectHeaps.downHeap(this.refArray, this.heap, this.inv, this.size, newPos, this.c);
/* 206:    */    }
/* 207:    */    
/* 208:208 */    return true;
/* 209:    */  }
/* 210:    */  
/* 211:    */  public void clear()
/* 212:    */  {
/* 213:213 */    this.size = 0;
/* 214:214 */    IntArrays.fill(this.inv, -1);
/* 215:    */  }
/* 216:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.ints.IntHeapIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */