/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public abstract class uu
/*     */ {
/*     */   protected final q a;
/*     */   protected final q b;
/*     */   public final int a;
/*     */   private uu[] a;
/*     */   private final uu[] jdField_b_of_type_ArrayOfUu;
/*     */   private final int jdField_b_of_type_Int;
/*     */ 
/*     */   public uu(uu[] paramArrayOfuu, q paramq1, q paramq2, int paramInt1, int paramInt2)
/*     */   {
/*  24 */     this.jdField_b_of_type_ArrayOfUu = paramArrayOfuu;
/*     */ 
/*  26 */     this.jdField_a_of_type_Int = paramInt1;
/*     */ 
/*  28 */     this.jdField_b_of_type_Int = paramInt2;
/*     */ 
/*  31 */     if (paramInt2 % 2 != 0) {
/*  32 */       paramArrayOfuu = paramq1.jdField_a_of_type_Int; paramq1.jdField_a_of_type_Int = paramq1.c; paramq1.c = paramArrayOfuu;
/*     */ 
/*  34 */       paramArrayOfuu = paramq2.jdField_a_of_type_Int; paramq2.jdField_a_of_type_Int = paramq2.c; paramq2.c = paramArrayOfuu;
/*     */     }
/*  36 */     a(paramq1, paramq2);
/*     */ 
/*  38 */     this.jdField_a_of_type_Q = paramq1;
/*  39 */     this.jdField_b_of_type_Q = paramq2;
/*     */   }
/*     */ 
/*     */   public final void a() {
/*  43 */     ObjectArrayList localObjectArrayList = new ObjectArrayList();
/*     */ 
/*  45 */     Vector3f localVector3f1 = new Vector3f(this.jdField_a_of_type_Q.jdField_a_of_type_Int, this.jdField_a_of_type_Q.jdField_b_of_type_Int, this.jdField_a_of_type_Q.c);
/*  46 */     Vector3f localVector3f2 = new Vector3f(this.jdField_b_of_type_Q.jdField_a_of_type_Int, this.jdField_b_of_type_Q.jdField_b_of_type_Int, this.jdField_b_of_type_Q.c);
/*     */ 
/*  48 */     Vector3f localVector3f3 = new Vector3f();
/*  49 */     Vector3f localVector3f4 = new Vector3f();
/*  50 */     for (int i = 0; i < this.jdField_b_of_type_ArrayOfUu.length; i++) {
/*  51 */       uu localuu = this.jdField_b_of_type_ArrayOfUu[i];
/*  52 */       localVector3f3.set(localuu.jdField_a_of_type_Q.jdField_a_of_type_Int, localuu.jdField_a_of_type_Q.jdField_b_of_type_Int, localuu.jdField_a_of_type_Q.c);
/*  53 */       localVector3f4.set(localuu.jdField_b_of_type_Q.jdField_a_of_type_Int, localuu.jdField_b_of_type_Q.jdField_b_of_type_Int, localuu.jdField_b_of_type_Q.c);
/*     */ 
/*  55 */       if ((localuu != this) && (AabbUtil2.testAabbAgainstAabb2(localVector3f1, localVector3f2, localVector3f3, localVector3f4))) {
/*  56 */         localObjectArrayList.add(localuu);
/*     */       }
/*     */     }
/*     */ 
/*  60 */     this.jdField_a_of_type_ArrayOfUu = new uu[localObjectArrayList.size()];
/*  61 */     for (i = 0; i < localObjectArrayList.size(); i++)
/*  62 */       this.jdField_a_of_type_ArrayOfUu[i] = ((uu)localObjectArrayList.get(i));
/*     */   }
/*     */ 
/*     */   public boolean a(q paramq)
/*     */   {
/*  69 */     return (paramq.jdField_a_of_type_Int < this.jdField_b_of_type_Q.jdField_a_of_type_Int) && (paramq.jdField_a_of_type_Int >= this.jdField_a_of_type_Q.jdField_a_of_type_Int) && (paramq.jdField_b_of_type_Int < this.jdField_b_of_type_Q.jdField_b_of_type_Int) && (paramq.jdField_b_of_type_Int >= this.jdField_a_of_type_Q.jdField_b_of_type_Int) && (paramq.c < this.jdField_b_of_type_Q.c) && (paramq.c >= this.jdField_a_of_type_Q.c);
/*     */   }
/*     */ 
/*     */   public final short b(q paramq)
/*     */   {
/*  74 */     for (int i = 0; i < this.jdField_a_of_type_ArrayOfUu.length; i++) {
/*  75 */       if ((this.jdField_a_of_type_ArrayOfUu[i].jdField_a_of_type_Int > this.jdField_a_of_type_Int) && (this.jdField_a_of_type_ArrayOfUu[i].a(paramq)))
/*     */       {
/*  78 */         if ((
/*  78 */           j = this.jdField_a_of_type_ArrayOfUu[i].b(paramq)) != 
/*  78 */           32767) {
/*  79 */           return j;
/*     */         }
/*     */       }
/*     */     }
/*  83 */     i = a(paramq);
/*  84 */     for (int j = 0; (j < this.jdField_a_of_type_ArrayOfUu.length) && ((i == 0) || (i == 32767)); j++) {
/*  85 */       if ((this.jdField_a_of_type_ArrayOfUu[j].jdField_a_of_type_Int == this.jdField_a_of_type_Int) && (this.jdField_a_of_type_ArrayOfUu[j].a(paramq))) {
/*  86 */         i = this.jdField_a_of_type_ArrayOfUu[j].a(paramq);
/*     */       }
/*     */     }
/*  89 */     return i;
/*     */   }
/*     */ 
/*     */   protected final q a(q paramq1, q paramq2)
/*     */   {
/*  97 */     paramq2.b(paramq1);
/*  98 */     paramq2.c(this.jdField_a_of_type_Q);
/*     */ 
/* 100 */     switch (this.jdField_b_of_type_Int)
/*     */     {
/*     */     case 0:
/* 105 */       break;
/*     */     case 1:
/* 107 */       paramq1 = paramq2.jdField_a_of_type_Int; paramq2.jdField_a_of_type_Int = paramq2.c; paramq2.c = (paramq1 - 1); break;
/*     */     case 2:
/* 109 */       paramq2.c = (this.jdField_b_of_type_Q.c - this.jdField_a_of_type_Q.c - paramq2.c); break;
/*     */     case 3:
/* 111 */       paramq1 = paramq2.jdField_a_of_type_Int; paramq2.jdField_a_of_type_Int = paramq2.c; paramq2.c = paramq1; paramq2.c = (this.jdField_b_of_type_Q.c - this.jdField_a_of_type_Q.c - paramq2.c);
/*     */     }
/*     */ 
/* 114 */     return paramq2;
/*     */   }
/*     */ 
/*     */   protected abstract short a(q paramq);
/*     */ 
/*     */   public static void a(q paramq1, q paramq2)
/*     */   {
/*     */     int i;
/* 119 */     if (paramq2.jdField_a_of_type_Int < paramq1.jdField_a_of_type_Int) {
/* 120 */       i = paramq2.jdField_a_of_type_Int + 1;
/* 121 */       paramq1.jdField_a_of_type_Int += 1;
/* 122 */       paramq1.jdField_a_of_type_Int = i;
/*     */     }
/* 124 */     if (paramq2.jdField_b_of_type_Int < paramq1.jdField_b_of_type_Int) {
/* 125 */       i = paramq2.jdField_b_of_type_Int + 1;
/* 126 */       paramq1.jdField_b_of_type_Int += 1;
/* 127 */       paramq1.jdField_b_of_type_Int = i;
/*     */     }
/* 129 */     if (paramq2.c < paramq1.c) {
/* 130 */       i = paramq2.c + 1;
/* 131 */       paramq1.c += 1;
/* 132 */       paramq1.c = i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public byte a(q paramq)
/*     */   {
/* 144 */     return -1;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     uu
 * JD-Core Version:    0.6.2
 */