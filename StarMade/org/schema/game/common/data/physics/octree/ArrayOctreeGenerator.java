/*     */ package org.schema.game.common.data.physics.octree;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import o;
/*     */ 
/*     */ public class ArrayOctreeGenerator
/*     */ {
/*     */   public static void splitStart(o paramo1, o paramo2, byte paramByte)
/*     */   {
/*   7 */     paramo1 = new o(paramo1);
/*   8 */     (
/*   9 */       paramo2 = new o(paramByte, paramByte, paramByte))
/*   9 */       .a(paramo1);
/*     */ 
/*  11 */     o localo1 = new o(paramo1);
/*  12 */     o localo2 = new o(paramo2);
/*  13 */     localo1.a(paramByte, (byte)0, (byte)0);
/*  14 */     localo2.a(paramByte, (byte)0, (byte)0);
/*     */ 
/*  16 */     o localo3 = new o(paramo1);
/*  17 */     o localo4 = new o(paramo2);
/*  18 */     localo3.a(paramByte, (byte)0, paramByte);
/*  19 */     localo4.a(paramByte, (byte)0, paramByte);
/*     */ 
/*  21 */     o localo5 = new o(paramo1);
/*  22 */     o localo6 = new o(paramo2);
/*  23 */     localo5.a((byte)0, (byte)0, paramByte);
/*  24 */     localo6.a((byte)0, (byte)0, paramByte);
/*     */ 
/*  26 */     o localo7 = new o(paramo1);
/*  27 */     o localo8 = new o(paramo2);
/*  28 */     localo7.a((byte)0, paramByte, (byte)0);
/*  29 */     localo8.a((byte)0, paramByte, (byte)0);
/*     */ 
/*  31 */     o localo9 = new o(paramo1);
/*  32 */     o localo10 = new o(paramo2);
/*  33 */     localo9.a(paramByte, paramByte, (byte)0);
/*  34 */     localo10.a(paramByte, paramByte, (byte)0);
/*     */ 
/*  36 */     o localo11 = new o(paramo1);
/*  37 */     o localo12 = new o(paramo2);
/*  38 */     localo11.a(paramByte, paramByte, paramByte);
/*  39 */     localo12.a(paramByte, paramByte, paramByte);
/*     */ 
/*  41 */     o localo13 = new o(paramo1);
/*  42 */     o localo14 = new o(paramo2);
/*  43 */     localo13.a((byte)0, paramByte, paramByte);
/*  44 */     localo14.a((byte)0, paramByte, paramByte);
/*     */ 
/*  46 */     paramByte = (byte)(paramByte / 2);
/*  47 */     split(0, 0, paramo1, paramo2, paramByte);
/*  48 */     split(1, 0, localo1, localo2, paramByte);
/*  49 */     split(2, 0, localo3, localo4, paramByte);
/*  50 */     split(3, 0, localo5, localo6, paramByte);
/*  51 */     split(4, 0, localo7, localo8, paramByte);
/*  52 */     split(5, 0, localo9, localo10, paramByte);
/*  53 */     split(6, 0, localo11, localo12, paramByte);
/*  54 */     split(7, 0, localo13, localo14, paramByte);
/*     */   }
/*     */ 
/*     */   public static void split(int paramInt1, int paramInt2, o paramo1, o paramo2, byte paramByte)
/*     */   {
/*     */     while (true)
/*     */     {
/*     */       int i;
/*  62 */       put(i = ArrayOctree.getIndex(paramInt1, paramInt2), 
/*  62 */         paramo1, paramo2);
/*     */ 
/*  64 */       for (int j = paramo1.c + 8; j < paramo2.c + 8; j++) {
/*  65 */         for (int k = paramo1.b + 8; k < paramo2.b + 8; k++) {
/*  66 */           for (int m = paramo1.a + 8; m < paramo2.a + 8; m++) {
/*  67 */             putNodeIndex(m, k, j, paramInt2, i);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  73 */       if (paramInt2 >= 2) break;
/*  74 */       o localo2 = new o(paramo1);
/*     */       o localo3;
/*  75 */       (
/*  76 */         localo3 = new o(paramByte, paramByte, paramByte))
/*  76 */         .a(localo2);
/*     */ 
/*  78 */       o localo4 = new o(localo2);
/*  79 */       paramo1 = new o(localo3);
/*  80 */       localo4.a(paramByte, (byte)0, (byte)0);
/*  81 */       paramo1.a(paramByte, (byte)0, (byte)0);
/*     */ 
/*  83 */       paramo2 = new o(localo2);
/*  84 */       o localo1 = new o(localo3);
/*  85 */       paramo2.a(paramByte, (byte)0, paramByte);
/*  86 */       localo1.a(paramByte, (byte)0, paramByte);
/*     */ 
/*  88 */       o localo5 = new o(localo2);
/*  89 */       o localo6 = new o(localo3);
/*  90 */       localo5.a((byte)0, (byte)0, paramByte);
/*  91 */       localo6.a((byte)0, (byte)0, paramByte);
/*     */ 
/*  93 */       o localo7 = new o(localo2);
/*  94 */       o localo8 = new o(localo3);
/*  95 */       localo7.a((byte)0, paramByte, (byte)0);
/*  96 */       localo8.a((byte)0, paramByte, (byte)0);
/*     */ 
/*  98 */       o localo9 = new o(localo2);
/*  99 */       o localo10 = new o(localo3);
/* 100 */       localo9.a(paramByte, paramByte, (byte)0);
/* 101 */       localo10.a(paramByte, paramByte, (byte)0);
/*     */ 
/* 103 */       o localo11 = new o(localo2);
/* 104 */       o localo12 = new o(localo3);
/* 105 */       localo11.a(paramByte, paramByte, paramByte);
/* 106 */       localo12.a(paramByte, paramByte, paramByte);
/*     */ 
/* 108 */       o localo13 = new o(localo2);
/* 109 */       o localo14 = new o(localo3);
/* 110 */       localo13.a((byte)0, paramByte, paramByte);
/* 111 */       localo14.a((byte)0, paramByte, paramByte);
/*     */ 
/* 113 */       paramInt1 <<= 3;
/* 114 */       paramByte = (byte)(paramByte / 2);
/* 115 */       split(paramInt1, paramInt2 + 1, localo2, localo3, paramByte);
/* 116 */       split(paramInt1 + 1, paramInt2 + 1, localo4, paramo1, paramByte);
/* 117 */       split(paramInt1 + 2, paramInt2 + 1, paramo2, localo1, paramByte);
/* 118 */       split(paramInt1 + 3, paramInt2 + 1, localo5, localo6, paramByte);
/* 119 */       split(paramInt1 + 4, paramInt2 + 1, localo7, localo8, paramByte);
/* 120 */       split(paramInt1 + 5, paramInt2 + 1, localo9, localo10, paramByte);
/* 121 */       split(paramInt1 + 6, paramInt2 + 1, localo11, localo12, paramByte);
/* 122 */       paramo2 = localo14; paramo1 = localo13; paramInt2 += 1; paramInt1 += 7;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void putNodeIndex(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*     */   {
/* 136 */     paramInt1 = ((paramInt3 << 4 << 4) + (paramInt2 << 4) + paramInt1) * 3;
/* 137 */     ArrayOctree.indexBuffer.put(paramInt1 + paramInt4, paramInt5);
/*     */   }
/*     */   public static int getNodeIndex(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 140 */     paramInt1 = ((paramInt3 << 4 << 4) + (paramInt2 << 4) + paramInt1) * 3;
/* 141 */     return ArrayOctree.indexBuffer.get(paramInt1 + paramInt4);
/*     */   }
/*     */   public static void put(int paramInt, o paramo1, o paramo2) {
/* 144 */     paramInt *= 6;
/* 145 */     ArrayOctree.dimBuffer.put(paramInt, paramo1.a);
/* 146 */     ArrayOctree.dimBuffer.put(paramInt + 1, paramo1.b);
/* 147 */     ArrayOctree.dimBuffer.put(paramInt + 2, paramo1.c);
/* 148 */     ArrayOctree.dimBuffer.put(paramInt + 3, paramo2.a);
/* 149 */     ArrayOctree.dimBuffer.put(paramInt + 4, paramo2.b);
/* 150 */     ArrayOctree.dimBuffer.put(paramInt + 5, paramo2.c);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.ArrayOctreeGenerator
 * JD-Core Version:    0.6.2
 */