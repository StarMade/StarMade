/*     */ import org.schema.game.common.data.element.ElementKeyMap;
/*     */ 
/*     */ public final class bo extends bu
/*     */ {
/*     */   public bo(ct paramct, short paramShort, kf paramkf)
/*     */   {
/*  17 */     super(paramct, paramShort, "Buy Quantity", new bn(ElementKeyMap.getInfo(paramShort), paramkf), 1);
/*     */ 
/*  20 */     ((bn)this.jdField_a_of_type_JavaLangObject).a = 1;
/*     */ 
/*  22 */     a(new bp(this, paramShort));
/*     */ 
/*  83 */     this.a.a(new bq(this));
/*     */   }
/*     */ 
/*     */   public final boolean a(String paramString)
/*     */   {
/*  97 */     paramString = Integer.parseInt(paramString);
/*     */ 
/* 100 */     this.a.a().a().a(this.jdField_a_of_type_Short, paramString);
/* 101 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bo
 * JD-Core Version:    0.6.2
 */