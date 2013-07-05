/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class un extends uu
/*    */ {
/* 11 */   private q c = new q();
/* 12 */   private q d = new q();
/* 13 */   private q e = new q();
/* 14 */   private Vector3f jdField_a_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */   private Vector3f b;
/*    */ 
/*    */   public un(uu[] paramArrayOfuu, q paramq1, q paramq2)
/*    */   {
/* 18 */     super(paramArrayOfuu, paramq1, paramq2, 5, 0);
/*    */ 
/* 15 */     this.jdField_b_of_type_JavaxVecmathVector3f = new Vector3f();
/*    */   }
/*    */ 
/*    */   protected final short a(q paramq)
/*    */   {
/* 23 */     a(paramq, this.c);
/* 24 */     a(this.jdField_b_of_type_Q, this.d);
/* 25 */     a(this.jdField_a_of_type_Q, this.e);
/*    */ 
/* 27 */     a(this.e, this.d);
/*    */     float f1;
/* 32 */     if (this.c.b < this.d.b / 2) {
/* 33 */       paramq = this; this.jdField_a_of_type_JavaxVecmathVector3f.set(paramq.c.a + 0.5F, 0.0F, paramq.c.c + 0.5F); paramq.jdField_b_of_type_JavaxVecmathVector3f.set(paramq.d.a / 2.0F, 0.0F, paramq.d.c / 2.0F); paramq.jdField_a_of_type_JavaxVecmathVector3f.sub(paramq.jdField_b_of_type_JavaxVecmathVector3f); if ((f1 = paramq.jdField_a_of_type_JavaxVecmathVector3f.length()) < paramq.d.a / 2.0F) { int i = paramq.d.b / 2 - 4; if ((!jdField_a_of_type_Boolean) && (i <= 0)) throw new AssertionError(); if (paramq.c.b == i) { if ((paramq.c.a > 0) && (paramq.c.c > 0) && (paramq.c.a % 5 == 0) && (paramq.c.c % 5 == 0)) return 79; return 5; } if (paramq.c.b < i) { int j = paramq.c.b - i; short s = 5; if (j % 2 == 0) s = 75; if ((paramq.c.a < paramq.d.a - j) && (paramq.c.a > j) && (paramq.c.c < paramq.d.c - j) && (paramq.c.c > j)) { if (paramq.c.b == 0) for (i = 0; i < paramq.d.a / 8; i++) { float f2 = i * 8.0F; if ((f1 > f2) && (f1 < f2 + 1.5F)) { s = 76; break; } } return s; }  } if (f1 + 1.5F >= paramq.d.a / 2.0F) { if (paramq.c.b == i + 2) return 63; if (paramq.c.b == i + 3) return 76; return 5; }  } return 32767;
/*    */     }
/* 35 */     paramq = this; this.jdField_a_of_type_JavaxVecmathVector3f.set(paramq.c.a + 0.5F, paramq.c.b * 1.3F, paramq.c.c + 0.5F); paramq.jdField_b_of_type_JavaxVecmathVector3f.set(paramq.d.a / 2.0F, paramq.d.b / 2.0F, paramq.d.c / 2.0F); paramq.jdField_a_of_type_JavaxVecmathVector3f.sub(paramq.jdField_b_of_type_JavaxVecmathVector3f); if (((f1 = paramq.jdField_a_of_type_JavaxVecmathVector3f.length()) < paramq.d.a / 2.0F + 0.5F) && (paramq.c.b == (int)(paramq.d.b - 4.0F - 1.0F)) && (paramq.c.a > 0) && (paramq.c.c > 0) && (paramq.c.a % 10 == 0) && (paramq.c.c % 10 == 0)) return 55; if ((f1 < paramq.d.a / 2.0F + 0.5F) && ((f1 > paramq.d.a / 2.0F - 1.5F) || (paramq.c.b >= paramq.d.b - 4.0F) || (paramq.c.c == paramq.d.c - 1))) { if (paramq.c.b < paramq.d.b - 4.0F) return 63; return 5; } return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     un
 * JD-Core Version:    0.6.2
 */