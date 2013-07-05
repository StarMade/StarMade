/*     */ import java.io.Serializable;
/*     */ import org.schema.schine.ai.stateMachines.FSMException;
/*     */ 
/*     */ public abstract class ws
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6064345513645124981L;
/*     */   private wt jdField_a_of_type_Wt;
/*     */   private wr jdField_a_of_type_Wr;
/*     */   public wk a;
/*     */   private wl jdField_a_of_type_Wl;
/*     */ 
/*     */   public ws(wk paramwk, wl paramwl)
/*     */   {
/*  82 */     if ((!jdField_a_of_type_Boolean) && (paramwk == null)) throw new AssertionError();
/*     */ 
/*  84 */     this.jdField_a_of_type_Wk = paramwk;
/*     */ 
/*  86 */     this.jdField_a_of_type_Wl = paramwl;
/*     */ 
/*  88 */     a();
/*     */   }
/*     */ 
/*     */   public abstract void a();
/*     */ 
/*     */   public final wr a()
/*     */   {
/* 116 */     return this.jdField_a_of_type_Wr;
/*     */   }
/*     */ 
/*     */   public wl a()
/*     */   {
/* 125 */     return this.jdField_a_of_type_Wl;
/*     */   }
/*     */ 
/*     */   public final void a(wt paramwt)
/*     */   {
/* 160 */     b(paramwt);
/* 161 */     this.jdField_a_of_type_Wr = new wr(paramwt, this);
/*     */   }
/*     */ 
/*     */   protected final void b(wt paramwt)
/*     */   {
/* 170 */     if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Wk == null)) throw new AssertionError();
/* 171 */     this.jdField_a_of_type_Wt = paramwt;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 180 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 189 */     if (this.jdField_a_of_type_Wt == null)
/* 190 */       throw new FSMException("[CRITICAL] no state set! please set the FiniteStateMachine.setStartState(State state) Method in createFSM()");
/*     */     wt localwt;
/* 195 */     if ((
/* 195 */       localwt = this.jdField_a_of_type_Wt)
/* 195 */       .e())
/*     */     {
/* 197 */       this.jdField_a_of_type_Wt.c();
/* 198 */       localwt.a(false); return;
/*     */     }
/*     */ 
/* 202 */     this.jdField_a_of_type_Wt.d();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ws
 * JD-Core Version:    0.6.2
 */