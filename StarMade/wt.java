/*     */ import java.io.Serializable;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public abstract class wt
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -493388144215505990L;
/*     */   private wk jdField_a_of_type_Wk;
/*  66 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private wq jdField_a_of_type_Wq;
/*     */ 
/*     */   public wt(wk paramwk)
/*     */   {
/*  77 */     this.jdField_a_of_type_Wk = paramwk;
/*  78 */     this.jdField_a_of_type_Wq = new wq(this);
/*     */   }
/*     */   public final wt a(wv paramwv, wt paramwt) {
/*  81 */     wt localwt = paramwt; paramwt = paramwv; (paramwv = this.jdField_a_of_type_Wq).b.add(localwt); paramwv.a.add(paramwt);
/*  82 */     return this;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/*  90 */     return (paramObject != null) && (getClass().equals(paramObject.getClass()));
/*     */   }
/*     */ 
/*     */   public wk a()
/*     */   {
/* 101 */     return this.jdField_a_of_type_Wk;
/*     */   }
/*     */ 
/*     */   public final wq a()
/*     */   {
/* 111 */     return this.jdField_a_of_type_Wq;
/*     */   }
/*     */ 
/*     */   public final boolean e()
/*     */   {
/* 120 */     return this.jdField_a_of_type_Boolean;
/*     */   }
/*     */ 
/*     */   public abstract boolean c();
/*     */ 
/*     */   public abstract boolean b();
/*     */ 
/*     */   public abstract boolean d();
/*     */ 
/*     */   public final void a(boolean paramBoolean)
/*     */   {
/* 159 */     this.jdField_a_of_type_Boolean = paramBoolean;
/*     */   }
/*     */ 
/*     */   public final void a(wv paramwv)
/*     */   {
/* 175 */     if ((!b) && (a() == null)) throw new AssertionError();
/* 176 */     if ((!b) && (a().a() == null)) throw new AssertionError();
/* 177 */     if ((!b) && (a().a().a() == null)) throw new AssertionError();
/* 178 */     a().a().a().a(paramwv);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 185 */     return getClass().getSimpleName();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wt
 * JD-Core Version:    0.6.2
 */