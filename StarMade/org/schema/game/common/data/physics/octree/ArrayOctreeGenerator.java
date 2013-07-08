/*   1:    */package org.schema.game.common.data.physics.octree;
/*   2:    */
/*   3:    */import java.nio.IntBuffer;
/*   4:    */
/*   5:    */public class ArrayOctreeGenerator {
/*   6:    */  public static void splitStart(o paramo1, o paramo2, byte paramByte) {
/*   7:  7 */    paramo1 = new o(paramo1);
/*   8:  8 */    (
/*   9:  9 */      paramo2 = new o(paramByte, paramByte, paramByte)).a(paramo1);
/*  10:    */    
/*  11: 11 */    o localo1 = new o(paramo1);
/*  12: 12 */    o localo2 = new o(paramo2);
/*  13: 13 */    localo1.a(paramByte, (byte)0, (byte)0);
/*  14: 14 */    localo2.a(paramByte, (byte)0, (byte)0);
/*  15:    */    
/*  16: 16 */    o localo3 = new o(paramo1);
/*  17: 17 */    o localo4 = new o(paramo2);
/*  18: 18 */    localo3.a(paramByte, (byte)0, paramByte);
/*  19: 19 */    localo4.a(paramByte, (byte)0, paramByte);
/*  20:    */    
/*  21: 21 */    o localo5 = new o(paramo1);
/*  22: 22 */    o localo6 = new o(paramo2);
/*  23: 23 */    localo5.a((byte)0, (byte)0, paramByte);
/*  24: 24 */    localo6.a((byte)0, (byte)0, paramByte);
/*  25:    */    
/*  26: 26 */    o localo7 = new o(paramo1);
/*  27: 27 */    o localo8 = new o(paramo2);
/*  28: 28 */    localo7.a((byte)0, paramByte, (byte)0);
/*  29: 29 */    localo8.a((byte)0, paramByte, (byte)0);
/*  30:    */    
/*  31: 31 */    o localo9 = new o(paramo1);
/*  32: 32 */    o localo10 = new o(paramo2);
/*  33: 33 */    localo9.a(paramByte, paramByte, (byte)0);
/*  34: 34 */    localo10.a(paramByte, paramByte, (byte)0);
/*  35:    */    
/*  36: 36 */    o localo11 = new o(paramo1);
/*  37: 37 */    o localo12 = new o(paramo2);
/*  38: 38 */    localo11.a(paramByte, paramByte, paramByte);
/*  39: 39 */    localo12.a(paramByte, paramByte, paramByte);
/*  40:    */    
/*  41: 41 */    o localo13 = new o(paramo1);
/*  42: 42 */    o localo14 = new o(paramo2);
/*  43: 43 */    localo13.a((byte)0, paramByte, paramByte);
/*  44: 44 */    localo14.a((byte)0, paramByte, paramByte);
/*  45:    */    
/*  46: 46 */    paramByte = (byte)(paramByte / 2);
/*  47: 47 */    split(0, 0, paramo1, paramo2, paramByte);
/*  48: 48 */    split(1, 0, localo1, localo2, paramByte);
/*  49: 49 */    split(2, 0, localo3, localo4, paramByte);
/*  50: 50 */    split(3, 0, localo5, localo6, paramByte);
/*  51: 51 */    split(4, 0, localo7, localo8, paramByte);
/*  52: 52 */    split(5, 0, localo9, localo10, paramByte);
/*  53: 53 */    split(6, 0, localo11, localo12, paramByte);
/*  54: 54 */    split(7, 0, localo13, localo14, paramByte);
/*  55:    */  }
/*  56:    */  
/*  57:    */  public static void split(int paramInt1, int paramInt2, o paramo1, o paramo2, byte paramByte)
/*  58:    */  {
/*  59:    */    for (;;)
/*  60:    */    {
/*  61:    */      int i;
/*  62: 62 */      put(i = ArrayOctree.getIndex(paramInt1, paramInt2), paramo1, paramo2);
/*  63:    */      
/*  64: 64 */      for (int j = paramo1.c + 8; j < paramo2.c + 8; j++) {
/*  65: 65 */        for (int k = paramo1.b + 8; k < paramo2.b + 8; k++) {
/*  66: 66 */          for (int m = paramo1.a + 8; m < paramo2.a + 8; m++) {
/*  67: 67 */            putNodeIndex(m, k, j, paramInt2, i);
/*  68:    */          }
/*  69:    */        }
/*  70:    */      }
/*  71:    */      
/*  73: 73 */      if (paramInt2 >= 2) break;
/*  74: 74 */      o localo2 = new o(paramo1);
/*  75:    */      o localo3;
/*  76: 76 */      (localo3 = new o(paramByte, paramByte, paramByte)).a(localo2);
/*  77:    */      
/*  78: 78 */      o localo4 = new o(localo2);
/*  79: 79 */      paramo1 = new o(localo3);
/*  80: 80 */      localo4.a(paramByte, (byte)0, (byte)0);
/*  81: 81 */      paramo1.a(paramByte, (byte)0, (byte)0);
/*  82:    */      
/*  83: 83 */      paramo2 = new o(localo2);
/*  84: 84 */      o localo1 = new o(localo3);
/*  85: 85 */      paramo2.a(paramByte, (byte)0, paramByte);
/*  86: 86 */      localo1.a(paramByte, (byte)0, paramByte);
/*  87:    */      
/*  88: 88 */      o localo5 = new o(localo2);
/*  89: 89 */      o localo6 = new o(localo3);
/*  90: 90 */      localo5.a((byte)0, (byte)0, paramByte);
/*  91: 91 */      localo6.a((byte)0, (byte)0, paramByte);
/*  92:    */      
/*  93: 93 */      o localo7 = new o(localo2);
/*  94: 94 */      o localo8 = new o(localo3);
/*  95: 95 */      localo7.a((byte)0, paramByte, (byte)0);
/*  96: 96 */      localo8.a((byte)0, paramByte, (byte)0);
/*  97:    */      
/*  98: 98 */      o localo9 = new o(localo2);
/*  99: 99 */      o localo10 = new o(localo3);
/* 100:100 */      localo9.a(paramByte, paramByte, (byte)0);
/* 101:101 */      localo10.a(paramByte, paramByte, (byte)0);
/* 102:    */      
/* 103:103 */      o localo11 = new o(localo2);
/* 104:104 */      o localo12 = new o(localo3);
/* 105:105 */      localo11.a(paramByte, paramByte, paramByte);
/* 106:106 */      localo12.a(paramByte, paramByte, paramByte);
/* 107:    */      
/* 108:108 */      o localo13 = new o(localo2);
/* 109:109 */      o localo14 = new o(localo3);
/* 110:110 */      localo13.a((byte)0, paramByte, paramByte);
/* 111:111 */      localo14.a((byte)0, paramByte, paramByte);
/* 112:    */      
/* 113:113 */      paramInt1 <<= 3;
/* 114:114 */      paramByte = (byte)(paramByte / 2);
/* 115:115 */      split(paramInt1, paramInt2 + 1, localo2, localo3, paramByte);
/* 116:116 */      split(paramInt1 + 1, paramInt2 + 1, localo4, paramo1, paramByte);
/* 117:117 */      split(paramInt1 + 2, paramInt2 + 1, paramo2, localo1, paramByte);
/* 118:118 */      split(paramInt1 + 3, paramInt2 + 1, localo5, localo6, paramByte);
/* 119:119 */      split(paramInt1 + 4, paramInt2 + 1, localo7, localo8, paramByte);
/* 120:120 */      split(paramInt1 + 5, paramInt2 + 1, localo9, localo10, paramByte);
/* 121:121 */      split(paramInt1 + 6, paramInt2 + 1, localo11, localo12, paramByte);
/* 122:122 */      paramo2 = localo14;paramo1 = localo13;paramInt2 += 1;paramInt1 += 7;
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 134:    */  public static void putNodeIndex(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/* 135:    */  {
/* 136:136 */    paramInt1 = ((paramInt3 << 4 << 4) + (paramInt2 << 4) + paramInt1) * 3;
/* 137:137 */    ArrayOctree.indexBuffer.put(paramInt1 + paramInt4, paramInt5);
/* 138:    */  }
/* 139:    */  
/* 140:140 */  public static int getNodeIndex(int paramInt1, int paramInt2, int paramInt3, int paramInt4) { paramInt1 = ((paramInt3 << 4 << 4) + (paramInt2 << 4) + paramInt1) * 3;
/* 141:141 */    return ArrayOctree.indexBuffer.get(paramInt1 + paramInt4);
/* 142:    */  }
/* 143:    */  
/* 144:144 */  public static void put(int paramInt, o paramo1, o paramo2) { paramInt *= 6;
/* 145:145 */    ArrayOctree.dimBuffer.put(paramInt, paramo1.a);
/* 146:146 */    ArrayOctree.dimBuffer.put(paramInt + 1, paramo1.b);
/* 147:147 */    ArrayOctree.dimBuffer.put(paramInt + 2, paramo1.c);
/* 148:148 */    ArrayOctree.dimBuffer.put(paramInt + 3, paramo2.a);
/* 149:149 */    ArrayOctree.dimBuffer.put(paramInt + 4, paramo2.b);
/* 150:150 */    ArrayOctree.dimBuffer.put(paramInt + 5, paramo2.c);
/* 151:    */  }
/* 152:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.ArrayOctreeGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */