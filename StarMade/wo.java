/*     */ import org.schema.schine.network.StateInterface;
/*     */ import org.schema.schine.network.server.ServerStateInterface;
/*     */ 
/*     */ public class wo
/*     */   implements wk
/*     */ {
/*     */   private String jdField_a_of_type_JavaLangString;
/*     */   public wl a;
/*     */   private final StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   public final boolean a;
/*     */ 
/*     */   public wo(String paramString, StateInterface paramStateInterface)
/*     */   {
/*  70 */     this.jdField_a_of_type_JavaLangString = paramString;
/*  71 */     this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  72 */     this.jdField_a_of_type_Boolean = (this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface instanceof ServerStateInterface);
/*     */   }
/*     */ 
/*     */   public final wl a()
/*     */   {
/*  78 */     return this.jdField_a_of_type_Wl;
/*     */   }
/*     */ 
/*     */   public final ws a()
/*     */   {
/*  89 */     return this.jdField_a_of_type_Wl.a();
/*     */   }
/*     */ 
/*     */   public final wt a()
/*     */   {
/*  99 */     return this.jdField_a_of_type_Wl.a().a().a();
/*     */   }
/*     */ 
/*     */   public final void d()
/*     */   {
/* 121 */     this.jdField_a_of_type_Wl = null;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 130 */     String str = this.jdField_a_of_type_JavaLangString;
/* 131 */     if (this.jdField_a_of_type_Wl == null) {
/* 132 */       return str + "[NULL_PROGRAM]";
/*     */     }
/* 134 */     if (this.jdField_a_of_type_Wl.a().a().a == null) {
/* 135 */       return str + "\n->[" + this.jdField_a_of_type_Wl.getClass().getSimpleName() + "->NULL_STATE]";
/*     */     }
/* 137 */     return str + "\n->[" + this.jdField_a_of_type_Wl.getClass().getSimpleName() + "->" + this.jdField_a_of_type_Wl.a().a().a.getClass().getSimpleName() + "]";
/*     */   }
/*     */ 
/*     */   public void a(xq paramxq)
/*     */   {
/* 151 */     if ((this.jdField_a_of_type_Wl != null) && (!this.jdField_a_of_type_Wl.b()))
/* 152 */       this.jdField_a_of_type_Wl.a().b();
/*     */   }
/*     */ 
/*     */   public boolean a() {
/* 156 */     return (this.jdField_a_of_type_Wl != null) && (!this.jdField_a_of_type_Wl.b());
/*     */   }
/*     */ 
/*     */   public StateInterface a()
/*     */   {
/* 164 */     return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     wo
 * JD-Core Version:    0.6.2
 */