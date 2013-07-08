/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.AbstractPriorityQueue;
/*   4:    */import java.util.Comparator;
/*   5:    */import java.util.NoSuchElementException;
/*   6:    */
/*  56:    */public class ObjectArrayPriorityQueue<K>
/*  57:    */  extends AbstractPriorityQueue<K>
/*  58:    */{
/*  59: 59 */  protected K[] array = (Object[])ObjectArrays.EMPTY_ARRAY;
/*  60:    */  
/*  62:    */  protected int size;
/*  63:    */  
/*  65:    */  protected Comparator<? super K> c;
/*  66:    */  
/*  68:    */  protected int firstIndex;
/*  69:    */  
/*  71:    */  protected boolean firstIndexValid;
/*  72:    */  
/*  74:    */  public ObjectArrayPriorityQueue(int capacity, Comparator<? super K> c)
/*  75:    */  {
/*  76: 76 */    if (capacity > 0) this.array = ((Object[])new Object[capacity]);
/*  77: 77 */    this.c = c;
/*  78:    */  }
/*  79:    */  
/*  82:    */  public ObjectArrayPriorityQueue(int capacity)
/*  83:    */  {
/*  84: 84 */    this(capacity, null);
/*  85:    */  }
/*  86:    */  
/*  89:    */  public ObjectArrayPriorityQueue(Comparator<? super K> c)
/*  90:    */  {
/*  91: 91 */    this(0, c);
/*  92:    */  }
/*  93:    */  
/*  94:    */  public ObjectArrayPriorityQueue()
/*  95:    */  {
/*  96: 96 */    this(0, null);
/*  97:    */  }
/*  98:    */  
/* 105:    */  public ObjectArrayPriorityQueue(K[] a, int size, Comparator<? super K> c)
/* 106:    */  {
/* 107:107 */    this(c);
/* 108:108 */    this.array = a;
/* 109:109 */    this.size = size;
/* 110:    */  }
/* 111:    */  
/* 117:    */  public ObjectArrayPriorityQueue(K[] a, Comparator<? super K> c)
/* 118:    */  {
/* 119:119 */    this(a, a.length, c);
/* 120:    */  }
/* 121:    */  
/* 127:    */  public ObjectArrayPriorityQueue(K[] a, int size)
/* 128:    */  {
/* 129:129 */    this(a, size, null);
/* 130:    */  }
/* 131:    */  
/* 136:    */  public ObjectArrayPriorityQueue(K[] a)
/* 137:    */  {
/* 138:138 */    this(a, a.length);
/* 139:    */  }
/* 140:    */  
/* 143:    */  private int findFirst()
/* 144:    */  {
/* 145:145 */    if (this.firstIndexValid) return this.firstIndex;
/* 146:146 */    this.firstIndexValid = true;
/* 147:147 */    int i = this.size;
/* 148:148 */    i--;int firstIndex = i;
/* 149:149 */    K first = this.array[firstIndex];
/* 150:    */    
/* 151:151 */    for (this.c != null; i-- != 0;) if (((Comparable)this.array[i]).compareTo(first) < 0) first = this.array[(firstIndex = i)];
/* 152:152 */    while (i-- != 0) if (this.c.compare(this.array[i], first) < 0) { first = this.array[(firstIndex = i)];
/* 153:    */      }
/* 154:154 */    return this.firstIndex = firstIndex;
/* 155:    */  }
/* 156:    */  
/* 157:    */  private void ensureNonEmpty() {
/* 158:158 */    if (this.size == 0) throw new NoSuchElementException();
/* 159:    */  }
/* 160:    */  
/* 161:    */  public void enqueue(K x) {
/* 162:162 */    if (this.size == this.array.length) this.array = ObjectArrays.grow(this.array, this.size + 1);
/* 163:163 */    if (this.firstIndexValid) {
/* 164:164 */      if (this.c == null) { if (((Comparable)x).compareTo(this.array[this.firstIndex]) < 0) this.firstIndex = this.size;
/* 165:165 */      } else if (this.c.compare(x, this.array[this.firstIndex]) < 0) this.firstIndex = this.size;
/* 166:    */    } else
/* 167:167 */      this.firstIndexValid = false;
/* 168:168 */    this.array[(this.size++)] = x;
/* 169:    */  }
/* 170:    */  
/* 171:    */  public K dequeue() {
/* 172:172 */    ensureNonEmpty();
/* 173:173 */    int first = findFirst();
/* 174:174 */    K result = this.array[first];
/* 175:175 */    System.arraycopy(this.array, first + 1, this.array, first, --this.size - first);
/* 176:    */    
/* 177:177 */    this.array[this.size] = null;
/* 178:    */    
/* 179:179 */    this.firstIndexValid = false;
/* 180:180 */    return result;
/* 181:    */  }
/* 182:    */  
/* 183:    */  public K first() {
/* 184:184 */    ensureNonEmpty();
/* 185:185 */    return this.array[findFirst()];
/* 186:    */  }
/* 187:    */  
/* 188:    */  public void changed() {
/* 189:189 */    ensureNonEmpty();
/* 190:190 */    this.firstIndexValid = false;
/* 191:    */  }
/* 192:    */  
/* 193:193 */  public int size() { return this.size; }
/* 194:    */  
/* 195:    */  public void clear()
/* 196:    */  {
/* 197:197 */    ObjectArrays.fill(this.array, 0, this.size, null);
/* 198:    */    
/* 199:199 */    this.size = 0;
/* 200:200 */    this.firstIndexValid = false;
/* 201:    */  }
/* 202:    */  
/* 205:    */  public void trim()
/* 206:    */  {
/* 207:207 */    this.array = ObjectArrays.trim(this.array, this.size);
/* 208:    */  }
/* 209:    */  
/* 210:210 */  public Comparator<? super K> comparator() { return this.c; }
/* 211:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectArrayPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */