/*   1:    */package it.unimi.dsi.fastutil.longs;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.ints.IntArrays;
/*   4:    */
/*  62:    */public class LongSemiIndirectHeaps
/*  63:    */{
/*  64:    */  public static int downHeap(long[] refArray, int[] heap, int size, int i, LongComparator c)
/*  65:    */  {
/*  66: 66 */    if (i >= size) throw new IllegalArgumentException("Heap position (" + i + ") is larger than or equal to heap size (" + size + ")");
/*  67: 67 */    int e = heap[i];
/*  68: 68 */    long E = refArray[e];
/*  69:    */    
/*  70: 70 */    if (c == null) { int child;
/*  71: 71 */      while ((child = 2 * i + 1) < size) {
/*  72: 72 */        if ((child + 1 < size) && (refArray[heap[(child + 1)]] < refArray[heap[child]])) child++;
/*  73: 73 */        if (E <= refArray[heap[child]]) break;
/*  74: 74 */        heap[i] = heap[child];
/*  75: 75 */        i = child;
/*  76:    */      } }
/*  77:    */    int child;
/*  78: 78 */    while ((child = 2 * i + 1) < size) {
/*  79: 79 */      if ((child + 1 < size) && (c.compare(refArray[heap[(child + 1)]], refArray[heap[child]]) < 0)) child++;
/*  80: 80 */      if (c.compare(E, refArray[heap[child]]) <= 0) break;
/*  81: 81 */      heap[i] = heap[child];
/*  82: 82 */      i = child;
/*  83:    */    }
/*  84: 84 */    heap[i] = e;
/*  85: 85 */    return i;
/*  86:    */  }
/*  87:    */  
/*  96:    */  public static int upHeap(long[] refArray, int[] heap, int size, int i, LongComparator c)
/*  97:    */  {
/*  98: 98 */    if (i >= size) throw new IllegalArgumentException("Heap position (" + i + ") is larger than or equal to heap size (" + size + ")");
/*  99: 99 */    int e = heap[i];
/* 100:    */    
/* 101:101 */    long E = refArray[e];
/* 102:102 */    if (c == null) { int parent;
/* 103:103 */      while ((i != 0) && ((parent = (i - 1) / 2) >= 0) && 
/* 104:104 */        (refArray[heap[parent]] > E)) {
/* 105:105 */        heap[i] = heap[parent];
/* 106:106 */        i = parent;
/* 107:    */      } }
/* 108:    */    int parent;
/* 109:109 */    while ((i != 0) && ((parent = (i - 1) / 2) >= 0) && 
/* 110:110 */      (c.compare(refArray[heap[parent]], E) > 0)) {
/* 111:111 */      heap[i] = heap[parent];
/* 112:112 */      i = parent;
/* 113:    */    }
/* 114:114 */    heap[i] = e;
/* 115:115 */    return i;
/* 116:    */  }
/* 117:    */  
/* 124:    */  public static void makeHeap(long[] refArray, int offset, int length, int[] heap, LongComparator c)
/* 125:    */  {
/* 126:126 */    LongArrays.ensureOffsetLength(refArray, offset, length);
/* 127:127 */    if (heap.length < length) throw new IllegalArgumentException("The heap length (" + heap.length + ") is smaller than the number of elements (" + length + ")");
/* 128:128 */    int i = length;
/* 129:129 */    while (i-- != 0) heap[i] = (offset + i);
/* 130:130 */    i = length / 2;
/* 131:131 */    while (i-- != 0) { downHeap(refArray, heap, length, i, c);
/* 132:    */    }
/* 133:    */  }
/* 134:    */  
/* 142:    */  public static int[] makeHeap(long[] refArray, int offset, int length, LongComparator c)
/* 143:    */  {
/* 144:144 */    int[] heap = length <= 0 ? IntArrays.EMPTY_ARRAY : new int[length];
/* 145:145 */    makeHeap(refArray, offset, length, heap, c);
/* 146:146 */    return heap;
/* 147:    */  }
/* 148:    */  
/* 158:    */  public static void makeHeap(long[] refArray, int[] heap, int size, LongComparator c)
/* 159:    */  {
/* 160:160 */    int i = size / 2;
/* 161:161 */    while (i-- != 0) { downHeap(refArray, heap, size, i, c);
/* 162:    */    }
/* 163:    */  }
/* 164:    */  
/* 180:    */  public static int front(long[] refArray, int[] heap, int size, int[] a)
/* 181:    */  {
/* 182:182 */    long top = refArray[heap[0]];
/* 183:183 */    int j = 0;
/* 184:184 */    int l = 0;
/* 185:185 */    int r = 1;
/* 186:186 */    int f = 0;
/* 187:187 */    for (int i = 0; i < r; i++) {
/* 188:188 */      if (i == f) {
/* 189:189 */        if (l >= r) break;
/* 190:190 */        f = (f << 1) + 1;
/* 191:191 */        i = l;
/* 192:192 */        l = -1;
/* 193:    */      }
/* 194:194 */      if (top == refArray[heap[i]]) {
/* 195:195 */        a[(j++)] = heap[i];
/* 196:196 */        if (l == -1) l = i * 2 + 1;
/* 197:197 */        r = Math.min(size, i * 2 + 3);
/* 198:    */      }
/* 199:    */    }
/* 200:    */    
/* 201:201 */    return j;
/* 202:    */  }
/* 203:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.longs.LongSemiIndirectHeaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */