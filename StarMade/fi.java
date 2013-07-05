/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.schema.schine.graphicsengine.core.GlUtil;
/*     */ import org.schema.schine.network.NetworkStateContainer;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ import org.schema.schine.network.objects.Sendable;
/*     */ 
/*     */ public final class fi extends yz
/*     */   implements Observer
/*     */ {
/*     */   private yE jdField_a_of_type_YE;
/*     */   private yG jdField_a_of_type_YG;
/*     */   private yA jdField_a_of_type_YA;
/* 160 */   private boolean jdField_a_of_type_Boolean = true;
/*     */   private boolean b;
/* 165 */   private ConcurrentHashMap jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap = new ConcurrentHashMap();
/*     */ 
/* 167 */   public fi(ClientState paramClientState) { super(paramClientState);
/*     */ 
/* 169 */     fi localfi = this; this.jdField_a_of_type_YE = new yE(xe.a().a("panel-std-gui-"), localfi.a()); localfi.jdField_a_of_type_YG = new yG(512.0F, 366.0F, localfi.a()); localfi.jdField_a_of_type_YA = new yA(localfi.a()); localfi.jdField_a_of_type_YG.c(localfi.jdField_a_of_type_YA); localfi.a(localfi.jdField_a_of_type_YE); localfi.jdField_a_of_type_YE.a(localfi.jdField_a_of_type_YG); localfi.jdField_a_of_type_YG.a(280.0F, 64.0F, 0.0F);
/*     */ 
/* 171 */     ((ct)paramClientState).addObserver(this);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 184 */     if (this.jdField_a_of_type_Boolean) {
/* 185 */       c();
/*     */     }
/*     */ 
/* 188 */     GlUtil.d();
/* 189 */     r();
/* 190 */     this.jdField_a_of_type_YE.b();
/* 191 */     GlUtil.c();
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 198 */     return this.jdField_a_of_type_YE.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 209 */     return this.jdField_a_of_type_YE.b();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 241 */     this.jdField_a_of_type_YE.c();
/* 242 */     this.jdField_a_of_type_YG.c();
/* 243 */     this.jdField_a_of_type_Boolean = false;
/* 244 */     (
/* 245 */       localObject = new fk(a()))
/* 245 */       .a("NAME", "KILLS", "DEATHS", "PING", "TEAM");
/* 246 */     Object localObject = new yD((yz)localObject, (yz)localObject, a());
/* 247 */     this.jdField_a_of_type_YA.a((yD)localObject);
/*     */   }
/*     */ 
/*     */   public final void update(Observable paramObservable, Object paramObject)
/*     */   {
/* 276 */     this.b = true;
/*     */   }
/*     */ 
/*     */   public final void a(xq paramxq)
/*     */   {
/* 283 */     super.a(paramxq);
/* 284 */     if (this.b) {
/* 285 */       fi localfi = this; synchronized (a().getLocalAndRemoteObjectContainer().getLocalObjects())
/*     */       {
/* 285 */         Object localObject1;
/* 285 */         for (int j = 0; j < localfi.jdField_a_of_type_YA.size(); j++) if (((localObject1 = (lE)localfi.jdField_a_of_type_YA.a(j).a) != null) && (!localfi.a().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(((lE)localObject1).getId()))) { localfi.jdField_a_of_type_YA.b(j); localfi.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localObject1); j--; } for (Iterator localIterator = localfi.a().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator(); localIterator.hasNext(); localfi.jdField_a_of_type_YA.a(new fj(localfi, (lE)localObject1, localfi.a()))) if ((!((localObject1 = (Sendable)localIterator.next()) instanceof lE)) || (localfi.jdField_a_of_type_JavaUtilConcurrentConcurrentHashMap.containsKey(localObject1)));  } localfi.jdField_a_of_type_YG.e();
/* 286 */       this.b = false;
/*     */     }
/*     */ 
/* 289 */     for (int i = 0; i < this.jdField_a_of_type_YA.size(); i++)
/* 290 */       this.jdField_a_of_type_YA.a(i).a(paramxq);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     fi
 * JD-Core Version:    0.6.2
 */