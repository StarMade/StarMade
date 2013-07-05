/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.schema.game.network.objects.NetworkGameState;
/*     */ import org.schema.game.server.data.blueprintnw.BlueprintEntry;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ import org.schema.schine.network.objects.remote.RemoteInteger;
/*     */ 
/*     */ public final class gd extends yr
/*     */   implements ys
/*     */ {
/*     */   private yN jdField_a_of_type_YN;
/*     */   private yN b;
/*     */   private yN c;
/*     */   private yP jdField_a_of_type_YP;
/*     */ 
/*     */   public gd(ClientState paramClientState)
/*     */   {
/*  41 */     super(paramClientState, 510.0F, 60.0F);
/*     */   }
/*     */   public final aC a() {
/*  44 */     return ((ct)a()).a().a.a.jdField_a_of_type_AC;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  52 */     super.c();
/*  53 */     ct localct = (ct)a();
/*  54 */     this.jdField_a_of_type_YN = new yN(localct, 142, 25, new Vector4f(0.3F, 0.3F, 0.7F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.c(), "Create new entry", this, a());
/*     */ 
/*  60 */     this.jdField_a_of_type_YN.a = "save";
/*  61 */     this.jdField_a_of_type_YN.b(4, 1);
/*  62 */     this.b = new yN(localct, 140, 20, new Vector4f(0.3F, 0.7F, 0.5F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Save in local catalog", this, a());
/*     */ 
/*  68 */     this.b.a = "save_local";
/*  69 */     this.c = new yN(localct, 160, 20, new Vector4f(0.5F, 0.7F, 0.3F, 1.0F), new Vector4f(1.0F, 1.0F, 1.0F, 1.0F), d.o(), "Upload entry from local", this, a());
/*     */ 
/*  75 */     this.c.a = "upload";
/*  76 */     this.b.a().x = 220.0F;
/*     */ 
/*  78 */     this.c.a().x = 370.0F;
/*     */ 
/*  80 */     yP localyP = new yP(1, 1, localct);
/*     */     int i;
/*  83 */     if ((
/*  83 */       i = ((Integer)localct.a().a().saveSlotsAllowed.get()).intValue()) < 0)
/*     */     {
/*  84 */       localyP.a("Used: " + localct.a().a().a().size());
/*     */     }
/*  86 */     else localyP.a("Used: " + localct.a().a().a().size() + "/" + i);
/*     */ 
/*  88 */     localyP.a().x = 150.0F;
/*  89 */     localyP.a().y = 2.0F;
/*     */ 
/*  91 */     this.jdField_a_of_type_YP = new yP(1, 1, localct);
/*  92 */     this.jdField_a_of_type_YP.b = new ArrayList();
/*  93 */     this.jdField_a_of_type_YP.b.add("\"Create new Entry\" will save the ship you are currently in into this catalog. You can also save ");
/*  94 */     this.jdField_a_of_type_YP.b.add("a ship in your singleplayer (local) catalog, or upload an entry from it.");
/*  95 */     this.jdField_a_of_type_YP.a().y = (this.jdField_a_of_type_YN.a().y + this.jdField_a_of_type_YN.a() + 4.0F);
/*  96 */     a(this.jdField_a_of_type_YP);
/*  97 */     a(localyP);
/*  98 */     a(this.jdField_a_of_type_YN);
/*  99 */     a(this.b);
/* 100 */     a(this.c);
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 110 */     ((ct)a()).a().a.a.jdField_a_of_type_Ar.a();
/*     */ 
/* 114 */     super.b();
/*     */   }
/*     */ 
/*     */   public final void a(yz paramyz, xp paramxp)
/*     */   {
/* 120 */     if (paramxp.a()) {
/* 121 */       paramxp = ((ct)a()).a().a.a.jdField_a_of_type_Ar.a();
/* 122 */       paramxp = (((ct)a()).a() != null) || ((paramxp != null) && ((paramxp instanceof kd))) ? 1 : 0;
/*     */       Object localObject;
/* 123 */       if ("save".equals(paramyz.a)) {
/* 124 */         if (paramxp != 0) {
/* 125 */           paramyz = this; a().e(true); paramxp = "Please enter in a name for your blue print!"; (localObject = new gi(paramyz, (ct)paramyz.a(), "BluePrint", paramxp, "BLUEPRINT_" + System.currentTimeMillis())).a(new gj()); ((K)localObject).c(); return;
/*     */         }
/* 127 */         ((ct)a()).a().b("You must be in a\nship or have one\nselected to save it"); return;
/*     */       }
/* 129 */       if ("save_local".equals(paramyz.a)) {
/* 130 */         if (paramxp != 0) {
/* 131 */           paramyz = this; a().e(true); paramxp = "Please enter in a name for your blue print!"; (localObject = new ge(paramyz, (ct)paramyz.a(), "BluePrint", paramxp, "BLUEPRINT_" + System.currentTimeMillis())).a(new gf()); ((K)localObject).c(); return;
/*     */         }
/* 133 */         ((ct)a()).a().b("You must be in a\nship or have one\nselected to save it"); return;
/*     */       }
/* 135 */       if ("upload".equals(paramyz.a)) {
/* 136 */         paramyz = this; a().e(true); paramxp = tH.a.a(); localObject = "Please enter in a name for your blue print!\n\nAvailable:\n" + paramxp; (paramxp = new gg(paramyz, (ct)paramyz.a(), "BluePrint", localObject, paramxp.isEmpty() ? "" : ((BlueprintEntry)paramxp.get(0)).toString())).a(new gh()); paramxp.c();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 332 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     gd
 * JD-Core Version:    0.6.2
 */