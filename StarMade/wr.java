/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Vector;
/*     */ import org.schema.schine.ai.stateMachines.FSMException;
/*     */ 
/*     */ public final class wr
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1860839739177998472L;
/*     */   wt jdField_a_of_type_Wt;
/*     */   private ws jdField_a_of_type_Ws;
/*     */ 
/*     */   public wr(wt paramwt, ws paramws)
/*     */   {
/*  60 */     new HashMap();
/*     */ 
/*  78 */     this.jdField_a_of_type_Wt = paramwt;
/*  79 */     this.jdField_a_of_type_Ws = paramws;
/*  80 */     new wu();
/*     */   }
/*     */ 
/*     */   public final wt a()
/*     */   {
/*  89 */     return this.jdField_a_of_type_Wt;
/*     */   }
/*     */ 
/*     */   public final wt a(wv paramwv)
/*     */   {
/* 140 */     if (this.jdField_a_of_type_Wt == null) {
/* 141 */       throw new FSMException("ERROR (FSMclass): CURRENT STATE NOT FOUND " + this.jdField_a_of_type_Wt);
/*     */     }
/*     */ 
/* 145 */     if (this.jdField_a_of_type_Wt != this.jdField_a_of_type_Wt.a().a())
/* 146 */       throw new FSMException("ERROR The State <" + this.jdField_a_of_type_Wt + "> of gameObject [" + this.jdField_a_of_type_Wt.a() + "] is unequal with the firering state <" + this.jdField_a_of_type_Wt.a().a().toString() + ">");
/* 158 */     wq localwq;
/* 158 */     wv localwv = paramwv;
/*     */     Object localObject;
/*     */     int i;
/* 158 */     if ((i = (localObject = localwq = this.jdField_a_of_type_Wt.a()).jdField_a_of_type_JavaUtilVector
/* 158 */       .indexOf(localwv)) < 0) throw new FSMException(((wq)localObject).jdField_a_of_type_Wt, localwv);
/*     */ 
/* 160 */     if ((
/* 160 */       localObject = (wt)((wq)localObject).b.get(i)) == null)
/*     */     {
/* 161 */       System.err.println("could not set state: discarding");
/* 162 */       throw new FSMException(this.jdField_a_of_type_Wt, paramwv);
/*     */     }
/* 164 */     if (localObject == this.jdField_a_of_type_Wt)
/*     */     {
/* 168 */       this.jdField_a_of_type_Wt.b();
/* 169 */       this.jdField_a_of_type_Wt.a(true);
/* 170 */       return this.jdField_a_of_type_Wt;
/*     */     }
/*     */ 
/* 181 */     this.jdField_a_of_type_Wt.b();
/* 182 */     this.jdField_a_of_type_Wt = ((wt)localObject);
/* 183 */     this.jdField_a_of_type_Ws.b((wt)localObject);
/* 184 */     this.jdField_a_of_type_Wt.a(true);
/* 185 */     this.jdField_a_of_type_Ws.a(); wl.g();
/*     */ 
/* 189 */     return this.jdField_a_of_type_Wt;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wr
 * JD-Core Version:    0.6.2
 */