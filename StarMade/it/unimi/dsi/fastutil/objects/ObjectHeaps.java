/*   1:    */package it.unimi.dsi.fastutil.objects;
/*   2:    */
/*   3:    */import java.util.Comparator;
/*   4:    */
/*  60:    */public class ObjectHeaps
/*  61:    */{
/*  62:    */  public static <K> int downHeap(K[] heap, int size, int i, Comparator<? super K> c)
/*  63:    */  {
/*  64: 64 */    if (i >= size) throw new IllegalArgumentException("Heap position (" + i + ") is larger than or equal to heap size (" + size + ")");
/*  65: 65 */    K e = heap[i];
/*  66:    */    
/*  67: 67 */    if (c == null) { int child;
/*  68: 68 */      while ((child = 2 * i + 1) < size) {
/*  69: 69 */        if ((child + 1 < size) && (((Comparable)heap[(child + 1)]).compareTo(heap[child]) < 0)) child++;
/*  70: 70 */        if (((Comparable)e).compareTo(heap[child]) <= 0) break;
/*  71: 71 */        heap[i] = heap[child];
/*  72: 72 */        i = child;
/*  73:    */      } }
/*  74:    */    int child;
/*  75: 75 */    while ((child = 2 * i + 1) < size) {
/*  76: 76 */      if ((child + 1 < size) && (c.compare(heap[(child + 1)], heap[child]) < 0)) child++;
/*  77: 77 */      if (c.compare(e, heap[child]) <= 0) break;
/*  78: 78 */      heap[i] = heap[child];
/*  79: 79 */      i = child;
/*  80:    */    }
/*  81: 81 */    heap[i] = e;
/*  82: 82 */    return i;
/*  83:    */  }
/*  84:    */  
/*  92:    */  public static <K> int upHeap(K[] heap, int size, int i, Comparator<K> c)
/*  93:    */  {
/*  94: 94 */    if (i >= size) throw new IllegalArgumentException("Heap position (" + i + ") is larger than or equal to heap size (" + size + ")");
/*  95: 95 */    K e = heap[i];
/*  96:    */    
/*  97: 97 */    if (c == null) { int parent;
/*  98: 98 */      while ((i != 0) && ((parent = (i - 1) / 2) >= 0) && 
/*  99: 99 */        (((Comparable)heap[parent]).compareTo(e) > 0)) {
/* 100:100 */        heap[i] = heap[parent];
/* 101:101 */        i = parent;
/* 102:    */      } }
/* 103:    */    int parent;
/* 104:104 */    while ((i != 0) && ((parent = (i - 1) / 2) >= 0) && 
/* 105:105 */      (c.compare(heap[parent], e) > 0)) {
/* 106:106 */      heap[i] = heap[parent];
/* 107:107 */      i = parent;
/* 108:    */    }
/* 109:109 */    heap[i] = e;
/* 110:110 */    return i;
/* 111:    */  }
/* 112:    */  
/* 117:    */  public static <K> void makeHeap(K[] heap, int size, Comparator<K> c)
/* 118:    */  {
/* 119:119 */    int i = size / 2;
/* 120:120 */    while (i-- != 0) downHeap(heap, size, i, c);
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.objects.ObjectHeaps
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */