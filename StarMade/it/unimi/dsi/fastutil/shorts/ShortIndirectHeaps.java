/*     */ package it.unimi.dsi.fastutil.shorts;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.IntArrays;
/*     */ 
/*     */ public class ShortIndirectHeaps
/*     */ {
/*     */   public static int downHeap(short[] refArray, int[] heap, int[] inv, int size, int i, ShortComparator c)
/*     */   {
/*  67 */     if (i >= size) throw new IllegalArgumentException("Heap position (" + i + ") is larger than or equal to heap size (" + size + ")");
/*  68 */     int e = heap[i];
/*  69 */     short E = refArray[e];
/*     */ 
/*  71 */     if (c == null)
/*     */     {
/*     */       int child;
/*  72 */       while ((child = 2 * i + 1) < size) {
/*  73 */         if ((child + 1 < size) && (refArray[heap[(child + 1)]] < refArray[heap[child]])) child++;
/*  74 */         if (E <= refArray[heap[child]]) break;
/*  75 */         heap[i] = heap[child];
/*  76 */         inv[heap[i]] = i;
/*  77 */         i = child;
/*     */       }
/*     */     }
/*     */     int child;
/*  80 */     while ((child = 2 * i + 1) < size) {
/*  81 */       if ((child + 1 < size) && (c.compare(refArray[heap[(child + 1)]], refArray[heap[child]]) < 0)) child++;
/*  82 */       if (c.compare(E, refArray[heap[child]]) <= 0) break;
/*  83 */       heap[i] = heap[child];
/*  84 */       inv[heap[i]] = i;
/*  85 */       i = child;
/*     */     }
/*  87 */     heap[i] = e;
/*  88 */     inv[e] = i;
/*  89 */     return i;
/*     */   }
/*     */ 
/*     */   public static int upHeap(short[] refArray, int[] heap, int[] inv, int size, int i, ShortComparator c)
/*     */   {
/* 105 */     if (i >= size) throw new IllegalArgumentException("Heap position (" + i + ") is larger than or equal to heap size (" + size + ")");
/* 106 */     int e = heap[i];
/* 107 */     short E = refArray[e];
/*     */ 
/* 109 */     if (c == null)
/*     */     {
/*     */       int parent;
/* 110 */       while ((i != 0) && ((parent = (i - 1) / 2) >= 0) && 
/* 111 */         (refArray[heap[parent]] > E)) {
/* 112 */         heap[i] = heap[parent];
/* 113 */         inv[heap[i]] = i;
/* 114 */         i = parent;
/*     */       }
/*     */     }
/*     */     int parent;
/* 117 */     while ((i != 0) && ((parent = (i - 1) / 2) >= 0) && 
/* 118 */       (c.compare(refArray[heap[parent]], E) > 0)) {
/* 119 */       heap[i] = heap[parent];
/* 120 */       inv[heap[i]] = i;
/* 121 */       i = parent;
/*     */     }
/* 123 */     heap[i] = e;
/* 124 */     inv[e] = i;
/* 125 */     return i; } 
/*     */   public static void makeHeap(short[] refArray, int offset, int length, int[] heap, int[] inv, ShortComparator c) { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: iload_1
/*     */     //   2: iload_2
/*     */     //   3: invokestatic 13	it/unimi/dsi/fastutil/shorts/ShortArrays:ensureOffsetLength	([SII)V
/*     */     //   6: aload_3
/*     */     //   7: arraylength
/*     */     //   8: iload_2
/*     */     //   9: if_icmpge +45 -> 54
/*     */     //   12: new 2	java/lang/IllegalArgumentException
/*     */     //   15: dup
/*     */     //   16: new 3	java/lang/StringBuilder
/*     */     //   19: dup
/*     */     //   20: invokespecial 4	java/lang/StringBuilder:<init>	()V
/*     */     //   23: ldc 14
/*     */     //   25: invokevirtual 6	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   28: aload_3
/*     */     //   29: arraylength
/*     */     //   30: invokevirtual 7	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   33: ldc 15
/*     */     //   35: invokevirtual 6	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   38: iload_2
/*     */     //   39: invokevirtual 7	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   42: ldc 9
/*     */     //   44: invokevirtual 6	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   47: invokevirtual 10	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   50: invokespecial 11	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
/*     */     //   53: athrow
/*     */     //   54: aload 4
/*     */     //   56: arraylength
/*     */     //   57: aload_0
/*     */     //   58: arraylength
/*     */     //   59: if_icmpge +46 -> 105
/*     */     //   62: new 2	java/lang/IllegalArgumentException
/*     */     //   65: dup
/*     */     //   66: new 3	java/lang/StringBuilder
/*     */     //   69: dup
/*     */     //   70: invokespecial 4	java/lang/StringBuilder:<init>	()V
/*     */     //   73: ldc 16
/*     */     //   75: invokevirtual 6	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   78: aload_3
/*     */     //   79: arraylength
/*     */     //   80: invokevirtual 7	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   83: ldc 17
/*     */     //   85: invokevirtual 6	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   88: aload_0
/*     */     //   89: arraylength
/*     */     //   90: invokevirtual 7	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
/*     */     //   93: ldc 9
/*     */     //   95: invokevirtual 6	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   98: invokevirtual 10	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   101: invokespecial 11	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
/*     */     //   104: athrow
/*     */     //   105: aload 4
/*     */     //   107: iconst_0
/*     */     //   108: aload_0
/*     */     //   109: arraylength
/*     */     //   110: iconst_m1
/*     */     //   111: invokestatic 18	it/unimi/dsi/fastutil/ints/IntArrays:fill	([IIII)V
/*     */     //   114: iload_2
/*     */     //   115: istore 6
/*     */     //   117: iload 6
/*     */     //   119: iinc 6 255
/*     */     //   122: ifeq +20 -> 142
/*     */     //   125: aload 4
/*     */     //   127: aload_3
/*     */     //   128: iload 6
/*     */     //   130: iload_1
/*     */     //   131: iload 6
/*     */     //   133: iadd
/*     */     //   134: dup_x2
/*     */     //   135: iastore
/*     */     //   136: iload 6
/*     */     //   138: iastore
/*     */     //   139: goto -22 -> 117
/*     */     //   142: iload_2
/*     */     //   143: iconst_2
/*     */     //   144: idiv
/*     */     //   145: istore 6
/*     */     //   147: iload 6
/*     */     //   149: iinc 6 255
/*     */     //   152: ifeq +19 -> 171
/*     */     //   155: aload_0
/*     */     //   156: aload_3
/*     */     //   157: aload 4
/*     */     //   159: iload_2
/*     */     //   160: iload 6
/*     */     //   162: aload 5
/*     */     //   164: invokestatic 19	it/unimi/dsi/fastutil/shorts/ShortIndirectHeaps:downHeap	([S[I[IIILit/unimi/dsi/fastutil/shorts/ShortComparator;)I
/*     */     //   167: pop
/*     */     //   168: goto -21 -> 147
/*     */     //   171: return } 
/* 158 */   public static void makeHeap(short[] refArray, int[] heap, int[] inv, int size, ShortComparator c) { int i = size / 2;
/* 159 */     while (i-- != 0) downHeap(refArray, heap, inv, size, i, c);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.shorts.ShortIndirectHeaps
 * JD-Core Version:    0.6.2
 */