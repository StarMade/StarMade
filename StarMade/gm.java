/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Observable;
/*     */ import java.util.Observer;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public abstract class gm extends yz
/*     */   implements Observer
/*     */ {
/*     */   private yG jdField_a_of_type_YG;
/*     */   private yr jdField_a_of_type_Yr;
/*     */   private yA jdField_a_of_type_YA;
/*  35 */   boolean jdField_a_of_type_Boolean = true;
/*     */   private boolean jdField_b_of_type_Boolean;
/*     */   private yN jdField_a_of_type_YN;
/*     */   private yN jdField_b_of_type_YN;
/*     */   private yN c;
/*     */   private gs jdField_a_of_type_Gs;
/*     */   private yN d;
/*     */ 
/*     */   public gm(ClientState paramClientState, boolean paramBoolean)
/*     */   {
/*  48 */     this(paramClientState, 340, paramBoolean);
/*     */   }
/*     */   public gm(ClientState paramClientState, int paramInt, boolean paramBoolean) {
/*  51 */     super(paramClientState);
/*  52 */     this.jdField_b_of_type_Boolean = paramBoolean;
/*  53 */     this.jdField_a_of_type_YG = new yG(542.0F, paramInt - 30, paramClientState);
/*  54 */     this.jdField_a_of_type_Yr = new yr(paramClientState, 542.0F, 30.0F);
/*  55 */     this.jdField_a_of_type_YA = new yA(a());
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public abstract Collection a();
/*     */ 
/*     */   public final void b()
/*     */   {
/* 102 */     if (this.jdField_a_of_type_Boolean) {
/* 103 */       yA localyA = this.jdField_a_of_type_YA; gm localgm = this; Object localObject1 = a(); HashSet localHashSet = new HashSet();
/*     */       Object localObject3;
/* 103 */       for (Iterator localIterator = localyA.iterator(); localIterator.hasNext(); localHashSet.add(((fV)localObject3).a().a)) if ((!((localObject2 = (yD)localIterator.next()) instanceof fV)) || (!((localObject3 = (fV)localObject2).a() instanceof yC)) || (!((yC)((fV)localObject3).a()).c())); localyA.clear(); int i = 0; for (Object localObject2 = ((Collection)localObject1).iterator(); ((Iterator)localObject2).hasNext(); i++) { localObject3 = (lL)((Iterator)localObject2).next(); localObject1 = new fM(localgm.a(), localgm, (lL)localObject3, localgm.jdField_b_of_type_Boolean, i); localyA.a(new fV((yz)localObject1, (yz)localObject1, (lL)localObject3, localgm.a())); ((yC)localObject1).a(localHashSet.contains(((lL)localObject3).a)); } if (localgm.jdField_a_of_type_Gs == null) localgm.a(gs.jdField_a_of_type_Gs); else localgm.b(localgm.jdField_a_of_type_Gs); localgm.b(localgm.jdField_a_of_type_Yr); if (localyA.size() > 0) localgm.a(localgm.jdField_a_of_type_Yr);
/* 104 */       this.jdField_a_of_type_Boolean = false;
/*     */     }
/* 106 */     k();
/*     */   }
/*     */ 
/*     */   public final void a(gs paramgs)
/*     */   {
/* 153 */     paramgs.jdField_a_of_type_JavaUtilComparator = Collections.reverseOrder(paramgs.jdField_a_of_type_JavaUtilComparator);
/* 154 */     b(paramgs);
/*     */   }
/*     */   private void b(gs paramgs) {
/* 157 */     Collections.sort(this.jdField_a_of_type_YA, paramgs.jdField_a_of_type_JavaUtilComparator);
/* 158 */     this.jdField_a_of_type_Gs = paramgs;
/* 159 */     this.jdField_a_of_type_YN.a.set(0.7F, 0.7F, 0.7F, 0.7F);
/* 160 */     this.jdField_b_of_type_YN.a.set(0.7F, 0.7F, 0.7F, 0.7F);
/* 161 */     this.c.a.set(0.7F, 0.7F, 0.7F, 0.7F);
/* 162 */     this.d.a.set(0.7F, 0.7F, 0.7F, 0.7F);
/* 163 */     switch (gr.a[paramgs.ordinal()]) { case 1:
/* 164 */       this.jdField_a_of_type_YN.a.set(1.0F, 1.0F, 1.0F, 1.0F); break;
/*     */     case 2:
/* 165 */       this.jdField_b_of_type_YN.a.set(1.0F, 1.0F, 1.0F, 1.0F); break;
/*     */     case 3:
/* 166 */       this.c.a.set(1.0F, 1.0F, 1.0F, 1.0F); break;
/*     */     case 4:
/* 167 */       this.d.a.set(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */ 
/* 170 */     for (paramgs = 0; paramgs < this.jdField_a_of_type_YA.size(); paramgs++)
/* 171 */       ((fM)this.jdField_a_of_type_YA.a(paramgs).a()).a(paramgs);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 177 */     this.jdField_a_of_type_YG.c(this.jdField_a_of_type_YA);
/*     */ 
/* 179 */     this.jdField_a_of_type_YG.a().y = this.jdField_a_of_type_Yr.a();
/*     */ 
/* 184 */     this.jdField_a_of_type_YN = new yN(a(), 217, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Name", new gn(this));
/*     */ 
/* 199 */     this.jdField_b_of_type_YN = new yN(a(), 140, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Owner", new go(this));
/*     */ 
/* 214 */     this.c = new yN(a(), 90, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Price", new gp(this));
/*     */ 
/* 229 */     this.d = new yN(a(), 50, 20, new Vector4f(0.4F, 0.4F, 0.4F, 0.5F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Rating", new gq(this));
/*     */ 
/* 247 */     this.jdField_b_of_type_YN.a().x = (this.jdField_a_of_type_YN.a().x + 217.0F);
/*     */ 
/* 249 */     this.c.a().x = (this.jdField_b_of_type_YN.a().x + 140.0F);
/*     */ 
/* 251 */     this.d.a().x = (this.c.a().x + 90.0F);
/*     */ 
/* 253 */     this.jdField_a_of_type_Yr.a(this.jdField_a_of_type_YN);
/* 254 */     this.jdField_a_of_type_Yr.a(this.jdField_b_of_type_YN);
/* 255 */     this.jdField_a_of_type_Yr.a(this.c);
/* 256 */     this.jdField_a_of_type_Yr.a(this.d);
/*     */ 
/* 259 */     a(this.jdField_a_of_type_Yr);
/*     */ 
/* 261 */     a(this.jdField_a_of_type_YG);
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 268 */     return this.jdField_a_of_type_Yr.a() + this.jdField_a_of_type_YG.a();
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 273 */     return this.jdField_a_of_type_Yr.b();
/*     */   }
/*     */ 
/*     */   public void update(Observable paramObservable, Object paramObject)
/*     */   {
/* 286 */     if ((paramObservable instanceof yC))
/* 287 */       this.jdField_a_of_type_YA.f();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gm
 * JD-Core Version:    0.6.2
 */