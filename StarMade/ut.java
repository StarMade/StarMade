/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public final class ut extends uu
/*    */ {
/* 10 */   private q c = new q();
/* 11 */   private q d = new q();
/* 12 */   private q e = new q();
/* 13 */   private q f = new q();
/*    */   private boolean a;
/*    */ 
/*    */   public ut(boolean paramBoolean, uu[] paramArrayOfuu, q paramq1, q paramq2, int paramInt)
/*    */   {
/* 21 */     super(paramArrayOfuu, paramq1, paramq2, paramInt, 0);
/*    */ 
/* 14 */     new q();
/* 15 */     new q();
/* 16 */     new Vector3f();
/* 17 */     new Vector3f();
/*    */ 
/* 22 */     this.jdField_a_of_type_Boolean = paramBoolean;
/*    */   }
/*    */ 
/*    */   protected final short a(q paramq)
/*    */   {
/* 27 */     a(paramq, this.c);
/* 28 */     a(this.b, this.d);
/* 29 */     a(this.jdField_a_of_type_Q, this.e);
/*    */ 
/* 31 */     a(this.e, this.d);
/* 32 */     this.f.b((this.d.a - this.e.a) / 2, this.d.b, (this.d.c - this.e.c) / 2);
/*    */ 
/* 34 */     paramq = this.d.b - this.c.b - 1;
/*    */ 
/* 36 */     if ((this.f.equals(this.c)) || ((this.c.a >= this.f.a - paramq) && (this.c.a <= this.f.a + paramq) && (this.c.c >= this.f.c - paramq) && (this.c.c <= this.f.c + paramq)))
/*    */     {
/* 38 */       if ((paramq > (this.d.b - this.e.b) / 4) && (this.c.b > 1) && 
/* 39 */         (this.c.a >= this.f.a - paramq + 2) && (this.c.a <= this.f.a + paramq - 2) && (this.c.c >= this.f.c - paramq + 2) && (this.c.c <= this.f.c + paramq - 2))
/*    */       {
/* 42 */         if (((this.d.a - this.e.a) / 4 == this.c.a) && ((this.d.c - this.e.c) / 4 == this.c.c))
/*    */         {
/* 44 */           if (this.c.b < (this.d.b - this.e.b) / 8)
/* 45 */             return 2;
/* 46 */           if (this.c.b == (this.d.b - this.e.b) / 8) {
/* 47 */             return 55;
/*    */           }
/*    */         }
/* 50 */         if (((this.d.a - this.e.a) / 4 == this.c.a) && ((this.d.c - this.e.c) / 4 * 3 == this.c.c)) {
/* 51 */           if (this.c.b < (this.d.b - this.e.b) / 8)
/* 52 */             return 2;
/* 53 */           if (this.c.b == (this.d.b - this.e.b) / 8) {
/* 54 */             return 55;
/*    */           }
/*    */         }
/* 57 */         if (((this.d.a - this.e.a) / 4 * 3 == this.c.a) && ((this.d.c - this.e.c) / 4 == this.c.c)) {
/* 58 */           if (this.c.b < (this.d.b - this.e.b) / 8)
/* 59 */             return 2;
/* 60 */           if (this.c.b == (this.d.b - this.e.b) / 8) {
/* 61 */             return 55;
/*    */           }
/*    */         }
/* 64 */         if (((this.d.a - this.e.a) / 4 * 3 == this.c.a) && ((this.d.c - this.e.c) / 4 * 3 == this.c.c)) {
/* 65 */           if (this.c.b < (this.d.b - this.e.b) / 8)
/* 66 */             return 2;
/* 67 */           if (this.c.b == (this.d.b - this.e.b) / 8) {
/* 68 */             return 55;
/*    */           }
/*    */ 
/*    */         }
/*    */ 
/* 74 */         return 0;
/*    */       }
/*    */ 
/* 81 */       return 79;
/*    */     }
/*    */ 
/* 84 */     return 32767;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ut
 * JD-Core Version:    0.6.2
 */