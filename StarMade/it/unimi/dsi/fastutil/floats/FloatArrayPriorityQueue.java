/*   1:    */package it.unimi.dsi.fastutil.floats;
/*   2:    */
/*   3:    */import java.util.NoSuchElementException;
/*   4:    */
/*  55:    */public class FloatArrayPriorityQueue
/*  56:    */  extends AbstractFloatPriorityQueue
/*  57:    */{
/*  58: 58 */  protected float[] array = FloatArrays.EMPTY_ARRAY;
/*  59:    */  
/*  61:    */  protected int size;
/*  62:    */  
/*  64:    */  protected FloatComparator c;
/*  65:    */  
/*  67:    */  protected int firstIndex;
/*  68:    */  
/*  70:    */  protected boolean firstIndexValid;
/*  71:    */  
/*  73:    */  public FloatArrayPriorityQueue(int capacity, FloatComparator c)
/*  74:    */  {
/*  75: 75 */    if (capacity > 0) this.array = new float[capacity];
/*  76: 76 */    this.c = c;
/*  77:    */  }
/*  78:    */  
/*  81:    */  public FloatArrayPriorityQueue(int capacity)
/*  82:    */  {
/*  83: 83 */    this(capacity, null);
/*  84:    */  }
/*  85:    */  
/*  88:    */  public FloatArrayPriorityQueue(FloatComparator c)
/*  89:    */  {
/*  90: 90 */    this(0, c);
/*  91:    */  }
/*  92:    */  
/*  93:    */  public FloatArrayPriorityQueue()
/*  94:    */  {
/*  95: 95 */    this(0, null);
/*  96:    */  }
/*  97:    */  
/* 104:    */  public FloatArrayPriorityQueue(float[] a, int size, FloatComparator c)
/* 105:    */  {
/* 106:106 */    this(c);
/* 107:107 */    this.array = a;
/* 108:108 */    this.size = size;
/* 109:    */  }
/* 110:    */  
/* 116:    */  public FloatArrayPriorityQueue(float[] a, FloatComparator c)
/* 117:    */  {
/* 118:118 */    this(a, a.length, c);
/* 119:    */  }
/* 120:    */  
/* 126:    */  public FloatArrayPriorityQueue(float[] a, int size)
/* 127:    */  {
/* 128:128 */    this(a, size, null);
/* 129:    */  }
/* 130:    */  
/* 135:    */  public FloatArrayPriorityQueue(float[] a)
/* 136:    */  {
/* 137:137 */    this(a, a.length);
/* 138:    */  }
/* 139:    */  
/* 143:    */  private int findFirst()
/* 144:    */  {
/* 145:145 */    if (this.firstIndexValid) return this.firstIndex;
/* 146:146 */    this.firstIndexValid = true;
/* 147:147 */    int i = this.size;
/* 148:148 */    i--;int firstIndex = i;
/* 149:149 */    float first = this.array[firstIndex];
/* 150:    */    
/* 151:151 */    for (this.c != null; i-- != 0;) if (this.array[i] < first) first = this.array[(firstIndex = i)];
/* 152:152 */    while (i-- != 0) if (this.c.compare(this.array[i], first) < 0) { first = this.array[(firstIndex = i)];
/* 153:    */      }
/* 154:154 */    return this.firstIndex = firstIndex;
/* 155:    */  }
/* 156:    */  
/* 157:    */  private void ensureNonEmpty() {
/* 158:158 */    if (this.size == 0) throw new NoSuchElementException();
/* 159:    */  }
/* 160:    */  
/* 161:    */  public void enqueue(float x) {
/* 162:162 */    if (this.size == this.array.length) this.array = FloatArrays.grow(this.array, this.size + 1);
/* 163:163 */    if (this.firstIndexValid) {
/* 164:164 */      if (this.c == null) { if (x < this.array[this.firstIndex]) this.firstIndex = this.size;
/* 165:165 */      } else if (this.c.compare(x, this.array[this.firstIndex]) < 0) this.firstIndex = this.size;
/* 166:    */    } else
/* 167:167 */      this.firstIndexValid = false;
/* 168:168 */    this.array[(this.size++)] = x;
/* 169:    */  }
/* 170:    */  
/* 171:    */  public float dequeueFloat() {
/* 172:172 */    ensureNonEmpty();
/* 173:173 */    int first = findFirst();
/* 174:174 */    float result = this.array[first];
/* 175:175 */    System.arraycopy(this.array, first + 1, this.array, first, --this.size - first);
/* 176:    */    
/* 179:179 */    this.firstIndexValid = false;
/* 180:180 */    return result;
/* 181:    */  }
/* 182:    */  
/* 183:    */  public float firstFloat() {
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
/* 197:    */  public void clear()
/* 198:    */  {
/* 199:199 */    this.size = 0;
/* 200:200 */    this.firstIndexValid = false;
/* 201:    */  }
/* 202:    */  
/* 205:    */  public void trim()
/* 206:    */  {
/* 207:207 */    this.array = FloatArrays.trim(this.array, this.size);
/* 208:    */  }
/* 209:    */  
/* 210:210 */  public FloatComparator comparator() { return this.c; }
/* 211:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.floats.FloatArrayPriorityQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */