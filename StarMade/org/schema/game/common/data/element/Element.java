/*     */ package org.schema.game.common.data.element;
/*     */ 
/*     */ import javax.vecmath.Vector3f;
/*     */ import o;
/*     */ import q;
/*     */ 
/*     */ public abstract class Element
/*     */ {
/*     */   public static byte[] orientationMapping;
/*     */   public static byte[] orientationBackMapping;
/*     */   public static final float HALF_SIZE = 1.0F;
/*     */   public static final short TYPE_BLENDED_START = -1;
/*     */   public static final short TYPE_NONE = 0;
/*     */   public static final short TYPE_ALL = 32767;
/*     */   public static final int RIGHT = 0;
/*     */   public static final int LEFT = 1;
/*     */   public static final int TOP = 2;
/*     */   public static final int BOTTOM = 3;
/*     */   public static final int FRONT = 4;
/*     */   public static final int BACK = 5;
/*     */   public static final int TOP_FRONT = 0;
/*     */   public static final int TOP_RIGHT = 1;
/*     */   public static final int TOP_BACK = 2;
/*     */   public static final int TOP_LEFT = 3;
/*     */   public static final int BACK_FRONT = 4;
/*     */   public static final int BACK_RIGHT = 5;
/*     */   public static final int BACK_BACK = 6;
/*     */   public static final int BACK_LEFT = 7;
/*     */   public static final int TOP_FRONT_B = 8;
/*     */   public static final int TOP_RIGHT_B = 9;
/*     */   public static final int TOP_BACK_B = 10;
/*     */   public static final int TOP_LEFT_B = 11;
/*     */   public static final int BACK_FRONT_B = 12;
/*     */   public static final int BACK_RIGHT_B = 13;
/*     */   public static final int BACK_BACK_B = 14;
/*     */   public static final int BACK_LEFT_B = 15;
/*  86 */   public static final int[] OPPOSITE_SIDE = { 1, 0, 3, 2, 5, 4 };
/*     */   public static final int FLAG_RIGHT = 1;
/*     */   public static final int FLAG_LEFT = 2;
/*     */   public static final int FLAG_TOP = 4;
/*     */   public static final int FLAG_BOTTOM = 8;
/*     */   public static final int FLAG_FRONT = 16;
/*     */   public static final int FLAG_BACK = 32;
/* 104 */   public static final int[] SIDE_FLAG = { 1, 2, 4, 8, 16, 32 };
/*     */ 
/* 106 */   public static final o[] DIRECTIONSb = { new o(1.0F, 0.0F, 0.0F), new o(-1.0F, 0.0F, 0.0F), new o(0.0F, 1.0F, 0.0F), new o(0.0F, -1.0F, 0.0F), new o(0.0F, 0.0F, 1.0F), new o(0.0F, 0.0F, -1.0F) };
/*     */ 
/* 111 */   public static final q[] DIRECTIONSi = { new q(1, 0, 0), new q(-1, 0, 0), new q(0, 1, 0), new q(0, -1, 0), new q(0, 0, 1), new q(0, 0, -1) };
/*     */ 
/* 116 */   public static final Vector3f[] DIRECTIONSf = { new Vector3f(1.0F, 0.0F, 0.0F), new Vector3f(-1.0F, 0.0F, 0.0F), new Vector3f(0.0F, 1.0F, 0.0F), new Vector3f(0.0F, -1.0F, 0.0F), new Vector3f(0.0F, 0.0F, 1.0F), new Vector3f(0.0F, 0.0F, -1.0F) };
/*     */   public static final int SIDE_INDEX_COUNT = 4;
/*     */   public static final int INDEX_BOTTOM = 0;
/*     */   public static final int INDEX_TOP = 4;
/*     */   public static final int INDEX_FRONT = 8;
/*     */   public static final int INDEX_BACK = 16;
/*     */   public static final int INDEX_LEFT = 12;
/*     */   public static final int INDEX_RIGHT = 20;
/* 133 */   public static byte FULLVIS = 63;
/*     */   private static final float margin = 0.001F;
/*     */ 
/*     */   public static int getRelativeOrientation(Vector3f paramVector3f)
/*     */   {
/*  58 */     int i = 0;
/*  59 */     if ((Math.abs(paramVector3f.x) >= Math.abs(paramVector3f.y)) && (Math.abs(paramVector3f.x) >= Math.abs(paramVector3f.z)))
/*     */     {
/*  61 */       if (paramVector3f.x >= 0.0F)
/*  62 */         i = 0;
/*     */       else
/*  64 */         i = 1;
/*     */     }
/*  66 */     else if ((Math.abs(paramVector3f.y) >= Math.abs(paramVector3f.x)) && (Math.abs(paramVector3f.y) >= Math.abs(paramVector3f.z)))
/*     */     {
/*  68 */       if (paramVector3f.y >= 0.0F)
/*  69 */         i = 2;
/*     */       else {
/*  71 */         i = 3;
/*     */       }
/*     */     }
/*  74 */     else if ((Math.abs(paramVector3f.z) >= Math.abs(paramVector3f.y)) && (Math.abs(paramVector3f.z) >= Math.abs(paramVector3f.x)))
/*     */     {
/*  77 */       if (paramVector3f.z >= 0.0F)
/*  78 */         i = 4;
/*     */       else {
/*  80 */         i = 5;
/*     */       }
/*     */     }
/*     */ 
/*  84 */     return i;
/*     */   }
/*     */ 
/*     */   public static int countBits(int paramInt)
/*     */   {
/* 149 */     return ((
/* 149 */       paramInt = ((
/* 147 */       paramInt = ((
/* 145 */       paramInt = ((
/* 143 */       paramInt = (paramInt >>> 1 & 0x55555555) + (paramInt & 0x55555555)) >>> 
/* 143 */       2 & 0x33333333) + (paramInt & 0x33333333)) >>> 
/* 145 */       4 & 0xF0F0F0F) + (paramInt & 0xF0F0F0F)) >>> 
/* 147 */       8 & 0xFF00FF) + (paramInt & 0xFF00FF)) >>> 
/* 149 */       16) + (paramInt & 0xFFFF);
/*     */   }
/*     */   private static void createOrientationMapping() {
/* 152 */     (
/* 153 */       Element.orientationMapping = new byte[16])[
/* 153 */       4] = 0;
/* 154 */     orientationMapping[5] = 1;
/* 155 */     orientationMapping[2] = 2;
/* 156 */     orientationMapping[3] = 3;
/* 157 */     orientationMapping[1] = 4;
/* 158 */     orientationMapping[0] = 5;
/* 159 */     orientationMapping[6] = 6;
/* 160 */     orientationMapping[7] = 7;
/* 161 */     orientationMapping[12] = 8;
/* 162 */     orientationMapping[13] = 9;
/* 163 */     orientationMapping[10] = 10;
/* 164 */     orientationMapping[11] = 11;
/* 165 */     orientationMapping[9] = 12;
/* 166 */     orientationMapping[8] = 13;
/* 167 */     orientationMapping[14] = 14;
/* 168 */     orientationMapping[15] = 15;
/*     */ 
/* 171 */     (
/* 172 */       Element.orientationBackMapping = new byte[16])[
/* 172 */       0] = 4;
/* 173 */     orientationBackMapping[1] = 5;
/* 174 */     orientationBackMapping[2] = 2;
/* 175 */     orientationBackMapping[3] = 3;
/* 176 */     orientationBackMapping[4] = 1;
/* 177 */     orientationBackMapping[5] = 0;
/* 178 */     orientationBackMapping[6] = 6;
/* 179 */     orientationBackMapping[7] = 7;
/* 180 */     orientationBackMapping[8] = 12;
/* 181 */     orientationBackMapping[9] = 13;
/* 182 */     orientationBackMapping[10] = 10;
/* 183 */     orientationBackMapping[11] = 11;
/* 184 */     orientationBackMapping[12] = 9;
/* 185 */     orientationBackMapping[13] = 8;
/* 186 */     orientationBackMapping[14] = 14;
/* 187 */     orientationBackMapping[15] = 15;
/*     */   }
/*     */ 
/*     */   public static int getSide(Vector3f paramVector3f, q paramq)
/*     */   {
/* 202 */     return getSide(paramVector3f, paramq, 0.001F);
/*     */   }
/*     */   public static int getSide(Vector3f paramVector3f, q paramq, float paramFloat) {
/*     */     while (true) {
/* 206 */       float f1 = paramq.a - 0.5F;
/*     */ 
/* 209 */       float f2 = paramq.b - 0.5F;
/* 210 */       float f3 = paramq.c - 0.5F;
/*     */ 
/* 212 */       float f4 = paramq.a + 0.5F;
/* 213 */       float f5 = paramq.b + 0.5F;
/* 214 */       float f6 = paramq.c + 0.5F;
/*     */ 
/* 217 */       if (paramVector3f.x >= f4 - paramFloat)
/*     */       {
/* 219 */         return 0;
/*     */       }
/* 221 */       if (paramVector3f.y >= f5 - paramFloat)
/*     */       {
/* 223 */         return 2;
/*     */       }
/* 225 */       if (paramVector3f.z >= f6 - paramFloat)
/*     */       {
/* 227 */         return 4;
/*     */       }
/* 229 */       if (paramVector3f.x <= f1 + paramFloat)
/* 230 */         return 1;
/* 231 */       if (paramVector3f.y <= f2 + paramFloat)
/* 232 */         return 3;
/* 233 */       if (paramVector3f.z <= f3 + paramFloat) {
/* 234 */         return 5;
/*     */       }
/* 236 */       if (paramFloat >= 0.5F)
/*     */         break;
/* 238 */       paramFloat *= 2.0F;
/*     */     }
/*     */ 
/* 241 */     return -1;
/*     */   }
/*     */   public static String getSideString(int paramInt) {
/* 244 */     switch (paramInt) { case 1:
/* 245 */       return "LEFT";
/*     */     case 0:
/* 246 */       return "RIGHT";
/*     */     case 2:
/* 247 */       return "TOP";
/*     */     case 3:
/* 248 */       return "BOTTOM";
/*     */     case 4:
/* 249 */       return "FRONT";
/*     */     case 5:
/* 250 */       return "BACK";
/*     */     }
/* 252 */     return "[WARNING] UNKNOWN SIDE";
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 256 */     return "ELEMENT";
/*     */   }
/*     */   public static int getOpposite(int paramInt) {
/* 259 */     switch (paramInt) { case 1:
/* 260 */       return 0;
/*     */     case 0:
/* 261 */       return 1;
/*     */     case 2:
/* 262 */       return 3;
/*     */     case 3:
/* 263 */       return 2;
/*     */     case 4:
/* 264 */       return 5;
/*     */     case 5:
/* 265 */       return 4;
/*     */     }
/* 267 */     throw new RuntimeException("SIDE NOT FOUND: " + paramInt);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  21 */     createOrientationMapping();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.Element
 * JD-Core Version:    0.6.2
 */