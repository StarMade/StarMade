/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*   4:    */import java.util.Comparator;
/*   5:    */import java.util.NoSuchElementException;
/*   6:    */
/*  51:    */public class ObjectHeapPriorityQueue<K>
/*  52:    */  extends AbstractPriorityQueue<K>
/*  53:    */{
/*  54: 54 */  protected K[] heap = (Object[])ObjectArrays.EMPTY_ARRAY;
/*  55:    */  
/*  58:    */  protected int size;
/*  59:    */  
/*  61:    */  protected Comparator<? super K> c;
/*  62:    */  
/*  65:    */  public ObjectHeapPriorityQueue(int capacity, Comparator<? super K> c)
/*  66:    */  {
/*  67: 67 */    if (capacity > 0) this.heap = ((Object[])new Object[capacity]);
/*  68: 68 */    this.c = c;
/*  69:    */  }
/*  70:    */  
/*  73:    */  public ObjectHeapPriorityQueue(int capacity)
/*  74:    */  {
/*  75: 75 */    this(capacity, null);
/*  76:    */  }
/*  77:    */  
/*  80:    */  public ObjectHeapPriorityQueue(Comparator<? super K> c)
/*  81:    */  {
/*  82: 82 */    this(0, c);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public ObjectHeapPriorityQueue()
/*  86:    */  {
/*  87: 87 */    this(0, null);
/*  88:    */  }
/*  89:    */  
/*  98:    */  public ObjectHeapPriorityQueue(K[] a, int size, Comparator<? super K> c)
/*  99:    */  {
/* 100:100 */    this(c);
/* 101:101 */    this.heap = a;
/* 102:102 */    this.size = size;
/* 103:103 */    ObjectHeaps.makeHeap(a, size, c);
/* 104:    */  }
/* 105:    */  
/* 113:    */  public ObjectHeapPriorityQueue(K[] a, Comparator<? super K> c)
/* 114:    */  {
/* 115:115 */    this(a, a.length, c);
/* 116:    */  }
/* 117:    */  
/* 125:    */  public ObjectHeapPriorityQueue(K[] a, int size)
/* 126:    */  {
/* 127:127 */    this(a, size, null);
/* 128:    */  }
/* 129:    */  
/* 138:138 */  public ObjectHeapPriorityQueue(K[] a) { this(a, a.length); }
/* 139:    */  
/* 140:    */  public void enqueue(K x) {
/* 141:141 */    if (this.size == this.heap.length) this.heap = ObjectArrays.grow(this.heap, this.size + 1);
/* 142:142 */    this.heap[(this.size++)] = x;
/* 143:143 */    ObjectHeaps.upHeap(this.heap, this.size, this.size - 1, this.c);
/* 144:    */  }
/* 145:    */  
/* 146:    */  public K dequeue() {
/* 147:147 */    if (this.size == 0) { throw new NoSuchElementException();
/* 148:    */    }
/* 149:149 */    K result = this.heap[0];
/* 150:150 */    this.heap[0] = this.heap[(--this.size)];
/* 151:    */    
/* 152:152 */    this.heap[this.size] = null;
/* 153:    */    
/* 154:154 */    if (this.size != 0) ObjectHeaps.downHeap(this.heap, this.size, 0, this.c);
/* 155:155 */    return result;
/* 156:    */  }
/* 157:    */  
/* 158:    */  public K first() {
/* 159:159 */    if (this.size == 0) throw new NoSuchElementException();
/* 160:160 */    return this.heap[0];
/* 161:    */  }
/* 162:    */  
/* 163:    */  public void changed() {
/* 164:164 */    ObjectHeaps.downHeap(this.heap, this.size, 0, this.c);
/* 165:    */  }
/* 166:    */  
/* 167:167 */  public int size() { return this.size; }
/* 168:    */  
/* 169:    */  public void clear()
/* 170:    */  {
/* 171:171 */    ObjectArrays.fill(this.heap, 0, this.size, null);
/* 172:    */    
/* 173:173 */    this.size = 0;
/* 174:    */  }
/* 175:    */  
/* 178:    */  public void trim()
/* 179:    */  {
/* 180:180 */    this.heap = ObjectArrays.trim(this.heap, this.size);
/* 181:    */  }
/* 182:    */  
/* 183:183 */  public Comparator<? super K> comparator() { return this.c; }
/* 184:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */