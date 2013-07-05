/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector4f;
/*     */ import org.schema.game.common.data.element.ElementInformation;
/*     */ import org.schema.schine.network.client.ClientState;
/*     */ 
/*     */ public final class ip extends yr
/*     */ {
/*     */   private final short jdField_a_of_type_Short;
/*     */   private final ElementInformation jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation;
/*     */   private int jdField_a_of_type_Int;
/*     */ 
/*     */   public ip(ClientState paramClientState, ElementInformation paramElementInformation, int paramInt)
/*     */   {
/*  74 */     super(paramClientState, 224.0F, 74.0F);
/*  75 */     this.jdField_a_of_type_Short = ((short)paramElementInformation.getBuildIconNum());
/*  76 */     this.a = Short.valueOf(paramElementInformation.getId());
/*  77 */     this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation = paramElementInformation;
/*  78 */     this.jdField_a_of_type_Int = paramInt;
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/*  92 */     super.c();
/*     */ 
/*  94 */     if (this.jdField_a_of_type_Int % 2 == 0) {
/*  95 */       yx localyx = new yx(a(), 224.0F, 74.0F, new Vector4f(0.06F, 0.06F, 0.06F, 1.0F));
/*  96 */       a(localyx);
/*     */     }
/*  98 */     int i = this.jdField_a_of_type_Short / 256;
/*     */ 
/* 100 */     Object localObject = xe.a().a("build-icons-" + i.b(i) + "-16x16-gui-");
/*     */ 
/* 102 */     (
/* 103 */       localObject = new yE((yg)localObject, a()))
/* 103 */       .a().y = 14.0F;
/*     */     yP localyP1;
/* 105 */     (
/* 106 */       localyP1 = new yP(224, 20, d.h(), a())).b = 
/* 106 */       new ArrayList();
/* 107 */     localyP1.b.add(this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation.getName());
/* 108 */     localyP1.a().y = 3.0F;
/*     */     yP localyP2;
/* 111 */     (
/* 112 */       localyP2 = new yP(224, 64, d.n(), a()))
/* 112 */       .a().x = 64.0F;
/* 113 */     localyP2.a().y = 24.0F;
/*     */ 
/* 115 */     int j = this.jdField_a_of_type_Short % 256;
/* 116 */     ((yE)localObject).a_(j);
/* 117 */     localyP2.b = new ArrayList();
/* 118 */     localyP2.b.add(new iq(this, this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation));
/* 119 */     localyP2.b.add(new ir(this, this.jdField_a_of_type_OrgSchemaGameCommonDataElementElementInformation));
/* 120 */     a(localyP1);
/* 121 */     a((yz)localObject);
/* 122 */     a(localyP2);
/*     */ 
/* 124 */     this.g = true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ip
 * JD-Core Version:    0.6.2
 */