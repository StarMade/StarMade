/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class hv extends yz
/*     */ {
/*     */   private yN jdField_a_of_type_YN;
/*     */   private yN b;
/*     */   private yN c;
/*     */   private yx jdField_a_of_type_Yx;
/*     */   private yP jdField_a_of_type_YP;
/*     */   private hK jdField_a_of_type_HK;
/*     */   private gz jdField_a_of_type_Gz;
/*     */   private yN d;
/*     */ 
/*     */   public hv(ClientState paramClientState)
/*     */   {
/*  38 */     super(paramClientState);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/*  49 */     this.jdField_a_of_type_YP.b.set(0, "Current Faction: " + ((ct)a()).a().a().a());
/*     */ 
/*  51 */     k();
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  61 */     aO localaO = ((ct)a()).a().a.a.a;
/*     */ 
/*  63 */     this.jdField_a_of_type_Yx = new yx(a(), 540.0F, 30.0F, new Vector4f(0.0F, 0.3F, 0.0F, 0.5F));
/*  64 */     this.jdField_a_of_type_Yx.c = 3.0F;
/*  65 */     this.jdField_a_of_type_YP = new yP(200, 30, d.d(), a());
/*  66 */     this.jdField_a_of_type_YP.a("Current Faction: ");
/*  67 */     this.jdField_a_of_type_Yx.a(this.jdField_a_of_type_YP);
/*     */ 
/*  69 */     this.jdField_a_of_type_YN = new yN(a(), 130, 20, "Create new faction", localaO, localaO);
/*     */ 
/*  71 */     this.jdField_a_of_type_YN.a = "CREATE_FACTION";
/*  72 */     this.jdField_a_of_type_YN.a(0.0F, 35.0F, 0.0F);
/*     */ 
/*  75 */     this.b = new yN(a(), 100, 20, "Leave Faction", localaO, localaO);
/*     */ 
/*  77 */     this.b.a = "LEAVE_FACTION";
/*  78 */     this.b.a(400.0F, 35.0F, 0.0F);
/*     */ 
/*  80 */     hw localhw = new hw(this);
/*     */ 
/*  86 */     this.c = new yN(a(), 100, 20, localhw, localaO, localaO);
/*  87 */     this.c.a = "INCOMING_INVITES";
/*  88 */     this.c.a(150.0F, 35.0F, 0.0F);
/*     */ 
/*  91 */     this.d = new yN(a(), 120, 20, "Pending Invites", localaO, localaO);
/*  92 */     this.d.a = "OUTGOING_INVITES";
/*  93 */     this.d.a(260.0F, 35.0F, 0.0F);
/*     */ 
/*  95 */     this.jdField_a_of_type_Gz = new hx(a());
/*     */ 
/* 104 */     this.jdField_a_of_type_Gz.c();
/* 105 */     this.jdField_a_of_type_Gz.a(0.0F, 60.0F, 0.0F);
/*     */ 
/* 108 */     this.jdField_a_of_type_HK = new hK(a());
/* 109 */     this.jdField_a_of_type_HK.a(0.0F, 140.0F, 0.0F);
/* 110 */     this.jdField_a_of_type_HK.c();
/* 111 */     this.g = true;
/*     */ 
/* 113 */     a(this.jdField_a_of_type_YN);
/* 114 */     a(this.jdField_a_of_type_Yx);
/* 115 */     a(this.b);
/* 116 */     a(this.c);
/* 117 */     a(this.d);
/* 118 */     a(this.jdField_a_of_type_Gz);
/* 119 */     a(this.jdField_a_of_type_HK);
/*     */   }
/*     */ 
/*     */   public final float a()
/*     */   {
/* 125 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public final float b()
/*     */   {
/* 131 */     return 0.0F;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     hv
 * JD-Core Version:    0.6.2
 */