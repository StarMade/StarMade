/*   1:    */package it.unimi.dsi.fastutil.shorts;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.ints.IntArrays;
/*   4:    */
/*  63:    */public class ShortIndirectHeaps
/*  64:    */{
/*  65:    */  public static int downHeap(short[] refArray, int[] heap, int[] inv, int size, int i, ShortComparator c)
/*  66:    */  {
/*  67: 67 */    if (i >= size) throw new IllegalArgumentException("Heap position (" + i + ") is larger than or equal to heap size (" + size + ")");
/*  68: 68 */    int e = heap[i];
/*  69: 69 */    short E = refArray[e];
/*  70:    */    
/*  71: 71 */    if (c == null) { int child;
/*  72: 72 */      while ((child = 2 * i + 1) < size) {
/*  73: 73 */        if ((child + 1 < size) && (refArray[heap[(child + 1)]] < refArray[heap[child]])) child++;
/*  74: 74 */        if (E <= refArray[heap[child]]) break;
/*  75: 75 */        heap[i] = heap[child];
/*  76: 76 */        inv[heap[i]] = i;
/*  77: 77 */        i = child;
/*  78:    */      } }
/*  79:    */    int child;
/*  80: 80 */    while ((child = 2 * i + 1) < size) {
/*  81: 81 */      if ((child + 1 < size) && (c.compare(refArray[heap[(child + 1)]], refArray[heap[child]]) < 0)) child++;
/*  82: 82 */      if (c.compare(E, refArray[heap[child]]) <= 0) break;
/*  83: 83 */      heap[i] = heap[child];
/*  84: 84 */      inv[heap[i]] = i;
/*  85: 85 */      i = child;
/*  86:    */    }
/*  87: 87 */    heap[i] = e;
/*  88: 88 */    inv[e] = i;
/*  89: 89 */    return i;
/*  90:    */  }
/*  91:    */  
/* 103:    */  public static int upHeap(short[] refArray, int[] heap, int[] inv, int size, int i, ShortComparator c)
/* 104:    */  {
/* 105:105 */    if (i >= size) throw new IllegalArgumentException("Heap position (" + i + ") is larger than or equal to heap size (" + size + ")");
/* 106:106 */    int e = heap[i];
/* 107:107 */    short E = refArray[e];
/* 108:    */    
/* 109:109 */    if (c == null) { int parent;
/* 110:110 */      while ((i != 0) && ((parent = (i - 1) / 2) >= 0) && 
/* 111:111 */        (refArray[heap[parent]] > E)) {
/* 112:112 */        heap[i] = heap[parent];
/* 113:113 */        inv[heap[i]] = i;
/* 114:114 */        i = parent;
/* 115:    */      } }
/* 116:    */    int parent;
/* 117:117 */    while ((i != 0) && ((parent = (i - 1) / 2) >= 0) && 
/* 118:118 */      (c.compare(refArray[heap[parent]], E) > 0)) {
/* 119:119 */      heap[i] = heap[parent];
/* 120:120 */      inv[heap[i]] = i;
/* 121:121 */      i = parent;
/* 122:    */    }
/* 123:123 */    heap[i] = e;
/* 124:124 */    inv[e] = i;
/* 125:125 */    return i;
/* 126:    */  }
/* 127:    */  
/* 135:    */  public static void makeHeap(short[] refArray, int offset, int length, int[] heap, int[] inv, ShortComparator c)
/* 136:    */  {
/* 137:137 */    ShortArrays.ensureOffsetLength(refArray, offset, length);
/* 138:138 */    if (heap.length < length) throw new IllegalArgumentException("The heap length (" + heap.length + ") is smaller than the number of elements (" + length + ")");
/* 139:139 */    if (inv.length < refArray.length) throw new IllegalArgumentException("The inversion array length (" + heap.length + ") is smaller than the length of the reference array (" + refArray.length + ")");
/* 140:140 */    IntArrays.fill(inv, 0, refArray.length, -1);
/* 141:141 */    int i = length;
/* 142:142 */    for (; i-- != 0; inv[// INTERNAL ERROR //

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortIndirectHeaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */