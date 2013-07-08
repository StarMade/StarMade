/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.AbstractIndirectPriorityQueue;
/*   4:    */import it.unimi.dsi.fastutil.IndirectPriorityQueue;
/*   5:    */import it.unimi.dsi.fastutil.ints.IntArrays;
/*   6:    */import java.util.Comparator;
/*   7:    */import java.util.NoSuchElementException;
/*   8:    */
/*  58:    */public class ObjectHeapSemiIndirectPriorityQueue<K>
/*  59:    */  extends AbstractIndirectPriorityQueue<K>
/*  60:    */  implements IndirectPriorityQueue<K>
/*  61:    */{
/*  62:    */  protected K[] refArray;
/*  63: 63 */  protected int[] heap = IntArrays.EMPTY_ARRAY;
/*  64:    */  
/*  66:    */  protected int size;
/*  67:    */  
/*  69:    */  protected Comparator<? super K> c;
/*  70:    */  
/*  73:    */  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int capacity, Comparator<? super K> c)
/*  74:    */  {
/*  75: 75 */    if (capacity > 0) this.heap = new int[capacity];
/*  76: 76 */    this.refArray = refArray;
/*  77: 77 */    this.c = c;
/*  78:    */  }
/*  79:    */  
/*  83:    */  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int capacity)
/*  84:    */  {
/*  85: 85 */    this(refArray, capacity, null);
/*  86:    */  }
/*  87:    */  
/*  91:    */  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, Comparator<? super K> c)
/*  92:    */  {
/*  93: 93 */    this(refArray, refArray.length, c);
/*  94:    */  }
/*  95:    */  
/*  97:    */  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray)
/*  98:    */  {
/*  99: 99 */    this(refArray, refArray.length, null);
/* 100:    */  }
/* 101:    */  
/* 111:    */  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] a, int size, Comparator<? super K> c)
/* 112:    */  {
/* 113:113 */    this(refArray, 0, c);
/* 114:114 */    this.heap = a;
/* 115:115 */    this.size = size;
/* 116:116 */    ObjectSemiIndirectHeaps.makeHeap(refArray, a, size, c);
/* 117:    */  }
/* 118:    */  
/* 127:    */  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] a, Comparator<? super K> c)
/* 128:    */  {
/* 129:129 */    this(refArray, a, a.length, c);
/* 130:    */  }
/* 131:    */  
/* 140:    */  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] a, int size)
/* 141:    */  {
/* 142:142 */    this(refArray, a, size, null);
/* 143:    */  }
/* 144:    */  
/* 152:    */  public ObjectHeapSemiIndirectPriorityQueue(K[] refArray, int[] a)
/* 153:    */  {
/* 154:154 */    this(refArray, a, a.length);
/* 155:    */  }
/* 156:    */  
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
/* 172:172 */    ObjectSemiIndirectHeaps.upHeap(this.refArray, this.heap, this.size, this.size - 1, this.c);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public int dequeue() {
/* 176:176 */    if (this.size == 0) throw new NoSuchElementException();
/* 177:177 */    int result = this.heap[0];
/* 178:178 */    this.heap[0] = this.heap[(--this.size)];
/* 179:179 */    if (this.size != 0) ObjectSemiIndirectHeaps.downHeap(this.refArray, this.heap, this.size, 0, this.c);
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
/* 197:197 */    ObjectSemiIndirectHeaps.downHeap(this.refArray, this.heap, this.size, 0, this.c);
/* 198:    */  }
/* 199:    */  
/* 202:    */  public void allChanged()
/* 203:    */  {
/* 204:204 */    ObjectSemiIndirectHeaps.makeHeap(this.refArray, this.heap, this.size, this.c);
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
/* 218:218 */  public Comparator<? super K> comparator() { return this.c; }
/* 219:    */  
/* 220:    */  public int front(int[] a) {
/* 221:221 */    return ObjectSemiIndirectHeaps.front(this.refArray, this.heap, this.size, a);
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
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectHeapSemiIndirectPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */