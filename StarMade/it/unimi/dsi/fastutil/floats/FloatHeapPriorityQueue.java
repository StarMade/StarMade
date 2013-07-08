/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import java.util.NoSuchElementException;
/*   4:    */
/*  50:    */public class FloatHeapPriorityQueue
/*  51:    */  extends AbstractFloatPriorityQueue
/*  52:    */{
/*  53: 53 */  protected float[] heap = FloatArrays.EMPTY_ARRAY;
/*  54:    */  
/*  57:    */  protected int size;
/*  58:    */  
/*  60:    */  protected FloatComparator c;
/*  61:    */  
/*  64:    */  public FloatHeapPriorityQueue(int capacity, FloatComparator c)
/*  65:    */  {
/*  66: 66 */    if (capacity > 0) this.heap = new float[capacity];
/*  67: 67 */    this.c = c;
/*  68:    */  }
/*  69:    */  
/*  72:    */  public FloatHeapPriorityQueue(int capacity)
/*  73:    */  {
/*  74: 74 */    this(capacity, null);
/*  75:    */  }
/*  76:    */  
/*  79:    */  public FloatHeapPriorityQueue(FloatComparator c)
/*  80:    */  {
/*  81: 81 */    this(0, c);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public FloatHeapPriorityQueue()
/*  85:    */  {
/*  86: 86 */    this(0, null);
/*  87:    */  }
/*  88:    */  
/*  97:    */  public FloatHeapPriorityQueue(float[] a, int size, FloatComparator c)
/*  98:    */  {
/*  99: 99 */    this(c);
/* 100:100 */    this.heap = a;
/* 101:101 */    this.size = size;
/* 102:102 */    FloatHeaps.makeHeap(a, size, c);
/* 103:    */  }
/* 104:    */  
/* 112:    */  public FloatHeapPriorityQueue(float[] a, FloatComparator c)
/* 113:    */  {
/* 114:114 */    this(a, a.length, c);
/* 115:    */  }
/* 116:    */  
/* 124:    */  public FloatHeapPriorityQueue(float[] a, int size)
/* 125:    */  {
/* 126:126 */    this(a, size, null);
/* 127:    */  }
/* 128:    */  
/* 137:137 */  public FloatHeapPriorityQueue(float[] a) { this(a, a.length); }
/* 138:    */  
/* 139:    */  public void enqueue(float x) {
/* 140:140 */    if (this.size == this.heap.length) { this.heap = FloatArrays.grow(this.heap, this.size + 1);
/* 141:    */    }
/* 142:142 */    this.heap[(this.size++)] = x;
/* 143:143 */    FloatHeaps.upHeap(this.heap, this.size, this.size - 1, this.c);
/* 144:    */  }
/* 145:    */  
/* 146:    */  public float dequeueFloat() {
/* 147:147 */    if (this.size == 0) { throw new NoSuchElementException();
/* 148:    */    }
/* 149:149 */    float result = this.heap[0];
/* 150:150 */    this.heap[0] = this.heap[(--this.size)];
/* 151:    */    
/* 154:154 */    if (this.size != 0) FloatHeaps.downHeap(this.heap, this.size, 0, this.c);
/* 155:155 */    return result;
/* 156:    */  }
/* 157:    */  
/* 158:    */  public float firstFloat() {
/* 159:159 */    if (this.size == 0) throw new NoSuchElementException();
/* 160:160 */    return this.heap[0];
/* 161:    */  }
/* 162:    */  
/* 163:    */  public void changed() {
/* 164:164 */    FloatHeaps.downHeap(this.heap, this.size, 0, this.c);
/* 165:    */  }
/* 166:    */  
/* 167:167 */  public int size() { return this.size; }
/* 168:    */  
/* 171:    */  public void clear()
/* 172:    */  {
/* 173:173 */    this.size = 0;
/* 174:    */  }
/* 175:    */  
/* 178:    */  public void trim()
/* 179:    */  {
/* 180:180 */    this.heap = FloatArrays.trim(this.heap, this.size);
/* 181:    */  }
/* 182:    */  
/* 183:183 */  public FloatComparator comparator() { return this.c; }
/* 184:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatHeapPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */