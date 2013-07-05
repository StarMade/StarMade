/*     */ import java.util.Random;
/*     */ import org.schema.game.common.data.world.SegmentData;
/*     */ 
/*     */ public final class vb extends uX
/*     */ {
/*     */   public vb()
/*     */   {
/*  15 */     this((byte)0);
/*     */   }
/*     */ 
/*     */   private vb(byte paramByte)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final boolean a(SegmentData paramSegmentData, Random paramRandom, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  28 */     int i = paramRandom.nextInt(3) + 4;
/*  29 */     int j = 1;
/*     */ 
/*  31 */     if ((paramInt2 <= 0) || (paramInt2 + i + 1 > 256))
/*     */     {
/*  33 */       return false;
/*     */     }
/*     */     int m;
/*     */     int n;
/*     */     int i2;
/*  36 */     for (int k = paramInt2; k <= paramInt2 + 1 + i; k++)
/*     */     {
/*  38 */       m = 1;
/*     */ 
/*  40 */       if (k == paramInt2)
/*     */       {
/*  42 */         m = 0;
/*     */       }
/*     */ 
/*  45 */       if (k >= paramInt2 + 1 + i - 2)
/*     */       {
/*  47 */         m = 2;
/*     */       }
/*     */ 
/*  50 */       for (n = paramInt1 - m; (n <= paramInt1 + m) && (j != 0); n++)
/*     */       {
/*  52 */         for (i1 = paramInt3 - m; (i1 <= paramInt3 + m) && (j != 0); i1++)
/*     */         {
/*  54 */           if ((k >= 0) && (k < 64))
/*     */           {
/*  58 */             if (((
/*  58 */               i2 = paramSegmentData.getType(a(n), a(k), a(i1))) != 0) && 
/*  58 */               (i2 != 85) && (i2 != 82) && (i2 != 87) && (i2 != 84))
/*     */             {
/*  60 */               j = 0;
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/*  65 */             j = 0;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  71 */     if (j == 0)
/*     */     {
/*  73 */       return false;
/*     */     }
/*     */ 
/*  78 */     if ((((
/*  78 */       k = paramSegmentData.getType(a(paramInt1), a(paramInt2 - 1), a(paramInt3))) != 
/*  78 */       82) && (k != 87)) || (paramInt2 >= 256 - i - 1))
/*     */     {
/*  80 */       return false;
/*     */     }
/*     */ 
/*  83 */     paramSegmentData.setInfoElementUnsynched(a(paramInt1), a(paramInt2 - 1), a(paramInt3), (short)87, false);
/*  84 */     for (int i1 = paramInt2 - 3 + i; i1 <= paramInt2 + i; 
/*  87 */       i1++)
/*     */     {
/*  89 */       i2 = i1 - (paramInt2 + i);
/*  90 */       j = 1 - i2 / 2;
/*     */ 
/*  92 */       for (k = paramInt1 - j; k <= paramInt1 + j; k++)
/*     */       {
/*  94 */         m = k - paramInt1;
/*     */ 
/*  96 */         for (n = paramInt3 - j; n <= paramInt3 + j; n++)
/*     */         {
/*  98 */           int i3 = n - paramInt3;
/*     */ 
/* 100 */           if ((Math.abs(m) != j) || (Math.abs(i3) != j) || ((paramRandom.nextInt(2) != 0) && (i2 != 0)))
/*     */           {
/* 103 */             paramSegmentData.setInfoElementUnsynched(a(k), a(i1), a(n), (short)85, false);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 109 */     for (i1 = 0; i1 < i; )
/*     */     {
/* 114 */       if (((
/* 114 */         i2 = paramSegmentData.getType(a(paramInt1), a(paramInt2 + i1), a(paramInt3))) == 0) || 
/* 114 */         (i2 == 85))
/*     */       {
/* 116 */         paramSegmentData.setInfoElementUnsynched(a(paramInt1), a(paramInt2 + i1), a(paramInt3), (short)84, false);
/*     */       }
/*     */ 
/* 123 */       i1++;
/*     */     }
/*     */ 
/* 154 */     return true;
/*     */   }
/*     */ 
/*     */   public final int a(Random paramRandom)
/*     */   {
/* 201 */     return 4 + paramRandom.nextInt(8);
/*     */   }
/*     */ 
/*     */   public final int b(Random paramRandom) {
/* 205 */     return 4 + paramRandom.nextInt(8);
/*     */   }
/*     */ 
/*     */   private static byte a(int paramInt)
/*     */   {
/* 210 */     return (byte)(Math.abs(paramInt) % 16);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vb
 * JD-Core Version:    0.6.2
 */