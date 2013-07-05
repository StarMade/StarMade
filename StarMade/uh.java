/*     */ import java.util.Random;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ 
/*     */ public final class uh extends uj
/*     */ {
/*     */   private uX[] a;
/*     */ 
/*     */   public uh(long paramLong)
/*     */   {
/*  27 */     super(paramLong);
/*  28 */     this.jdField_a_of_type_ArrayOfUX = new uX[9];
/*  29 */     this.jdField_a_of_type_ArrayOfUX[0] = new uZ(128, 3, 73);
/*  30 */     this.jdField_a_of_type_ArrayOfUX[1] = new uZ(87, 14, 73);
/*  31 */     this.jdField_a_of_type_ArrayOfUX[2] = new uZ(133, 6, 73);
/*  32 */     this.jdField_a_of_type_ArrayOfUX[3] = new uZ(72, 1, 73);
/*  33 */     this.jdField_a_of_type_ArrayOfUX[4] = new uY(this, 0);
/*  34 */     this.jdField_a_of_type_ArrayOfUX[5] = new uW(95, new short[] { 74, 74, 73 });
/*  35 */     this.jdField_a_of_type_ArrayOfUX[6] = new uW(103, new short[] { 74, 74, 73 });
/*  36 */     this.jdField_a_of_type_ArrayOfUX[7] = new uW(99, new short[] { 74, 74, 73 });
/*  37 */     this.jdField_a_of_type_ArrayOfUX[8] = new uW(107, new short[] { 74, 74, 73 });
/*     */   }
/*     */ 
/*     */   public final void a(SegmentController paramSegmentController, Segment paramSegment)
/*     */   {
/*  44 */     if (!this.jdField_a_of_type_Boolean) {
/*  45 */       this.jdField_a_of_type_UV = new uM(((jA)paramSegmentController).getSeed());
/*  46 */       this.jdField_a_of_type_UV.a(this);
/*  47 */       this.jdField_a_of_type_UV.b();
/*     */ 
/*  49 */       a();
/*     */ 
/*  51 */       this.jdField_a_of_type_Boolean = true;
/*     */     }
/*     */ 
/*  54 */     a(paramSegment);
/*     */   }
/*     */ 
/*     */   public final short a()
/*     */   {
/*  62 */     return 74;
/*     */   }
/*     */ 
/*     */   public final uX[] a()
/*     */   {
/*  69 */     return this.jdField_a_of_type_ArrayOfUX;
/*     */   }
/*     */ 
/*     */   public final short b()
/*     */   {
/*  76 */     return 73;
/*     */   }
/*     */ 
/*     */   public final short c()
/*     */   {
/*  81 */     return 74;
/*     */   }
/*     */ 
/*     */   public final void a(Random paramRandom)
/*     */   {
/*     */     int i;
/*  88 */     if ((
/*  88 */       i = paramRandom.nextInt(10)) == 0)
/*     */     {
/*  89 */       i = 3;
/*  90 */     } else if (i < 3)
/*  91 */       i = 2;
/*     */     else {
/*  93 */       i = 1;
/*     */     }
/*  95 */     uu[] arrayOfuu = new uu[i << 1];
/*  96 */     int j = 0;
/*  97 */     int k = 5;
/*  98 */     for (int m = 0; m < i; m++) {
/*  99 */       int n = paramRandom.nextInt(100) - 50;
/* 100 */       int i1 = paramRandom.nextInt(30);
/* 101 */       int i2 = paramRandom.nextInt(20) - 10;
/*     */ 
/* 103 */       ut localut = new ut(paramRandom.nextBoolean(), arrayOfuu, new q(n + -50, i2 + 20, n + -50), new q(n + 50, i1 + 60, n + 50), k--);
/* 104 */       arrayOfuu[(j++)] = localut;
/* 105 */       Object localObject = new q(n, i2 + 20 + 2, n);
/* 106 */       localObject = new uz((q)localObject, arrayOfuu, new q(((q)localObject).a - 1, ((q)localObject).b, ((q)localObject).c - 1), new q(((q)localObject).a + 1, ((q)localObject).b + 1, ((q)localObject).c + 1), 6);
/* 107 */       arrayOfuu[(j++)] = localObject;
/*     */     }
/*     */ 
/* 110 */     for (m = 0; m < arrayOfuu.length; m++) {
/* 111 */       arrayOfuu[m].a();
/*     */     }
/*     */ 
/* 114 */     this.jdField_a_of_type_UV.a(arrayOfuu);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uh
 * JD-Core Version:    0.6.2
 */