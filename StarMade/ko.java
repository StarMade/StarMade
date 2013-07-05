/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public final class ko
/*     */   implements Ag
/*     */ {
/*     */   private final String jdField_a_of_type_JavaLangString;
/*     */   private xB jdField_a_of_type_XB;
/*     */   private Object jdField_a_of_type_JavaLangObject;
/*     */   private final kp jdField_a_of_type_Kp;
/*     */   private final kq jdField_a_of_type_Kq;
/*     */ 
/*     */   public ko(kq paramkq, Object paramObject, xB paramxB, kp paramkp)
/*     */   {
/*  43 */     this.jdField_a_of_type_JavaLangString = paramkq.jdField_a_of_type_JavaLangString;
/*  44 */     this.jdField_a_of_type_Kq = paramkq;
/*  45 */     this.jdField_a_of_type_XB = paramxB;
/*  46 */     this.jdField_a_of_type_Kp = paramkp;
/*  47 */     a(paramObject, false);
/*     */   }
/*     */ 
/*     */   public final void fromTagStructure(Ad paramAd)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final Object a()
/*     */   {
/*  87 */     return this.jdField_a_of_type_JavaLangObject;
/*     */   }
/*     */ 
/*     */   public final String a()
/*     */   {
/*  93 */     return this.jdField_a_of_type_JavaLangString;
/*     */   }
/*     */ 
/*     */   public final String b() {
/*  97 */     return this.jdField_a_of_type_Kq.name();
/*     */   }
/*     */ 
/*     */   public final kq a()
/*     */   {
/* 108 */     return this.jdField_a_of_type_Kq;
/*     */   }
/*     */ 
/*     */   public final String getUniqueIdentifier()
/*     */   {
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */   public final boolean a()
/*     */   {
/* 125 */     return ((this.jdField_a_of_type_JavaLangObject instanceof Boolean)) && (((Boolean)this.jdField_a_of_type_JavaLangObject).booleanValue());
/*     */   }
/*     */ 
/*     */   public final boolean isVolatile()
/*     */   {
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   public final void a(Object paramObject, boolean paramBoolean)
/*     */   {
/* 142 */     int i = !paramObject.equals(this.jdField_a_of_type_JavaLangObject) ? 1 : 0;
/* 143 */     this.jdField_a_of_type_JavaLangObject = paramObject;
/*     */ 
/* 145 */     if (i != 0)
/* 146 */       this.jdField_a_of_type_Kp.a(this, paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void a()
/*     */   {
/* 153 */     a(this.jdField_a_of_type_XB.a(), true);
/*     */   }
/*     */ 
/*     */   public final void b()
/*     */   {
/* 159 */     Object localObject = this.jdField_a_of_type_XB.a();
/*     */ 
/* 161 */     a(localObject, true);
/*     */   }
/*     */ 
/*     */   public final void a(String paramString, boolean paramBoolean)
/*     */   {
/* 172 */     a(this.jdField_a_of_type_XB.a(paramString), paramBoolean);
/*     */   }
/*     */ 
/*     */   public final void c()
/*     */   {
/* 178 */     a(this.jdField_a_of_type_XB.b(), true);
/*     */   }
/*     */ 
/*     */   public final String toString()
/*     */   {
/* 185 */     return this.jdField_a_of_type_JavaLangString + " (" + this.jdField_a_of_type_XB.a() + ") " + this.jdField_a_of_type_JavaLangObject + " " + Arrays.toString(this.jdField_a_of_type_XB.a);
/*     */   }
/*     */ 
/*     */   public final Ad toTagStructure()
/*     */   {
/* 197 */     Ad localAd1 = new Ad(Af.i, "type", this.jdField_a_of_type_Kq.name());
/* 198 */     Ad localAd2 = new Ad(Af.i, "state", this.jdField_a_of_type_JavaLangObject.toString());
/*     */ 
/* 200 */     return new Ad(Af.n, "AIElement", new Ad[] { localAd1, localAd2, new Ad(Af.a, "fin", null) });
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  29 */     new ArrayList();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ko
 * JD-Core Version:    0.6.2
 */