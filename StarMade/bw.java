/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ 
/*    */ public final class bw extends bu
/*    */ {
/*    */   public bw(ct paramct, short paramShort, int paramInt, kf paramkf)
/*    */   {
/* 18 */     super(paramct, paramShort, "Sell Quantity", new bv(ElementKeyMap.getInfo(paramShort), paramkf), paramInt);
/*    */ 
/* 21 */     ((bv)this.jdField_a_of_type_JavaLangObject).a = (-paramInt);
/*    */ 
/* 23 */     a(new bx(this, paramShort));
/*    */ 
/* 74 */     this.a.a(new by(this));
/*    */   }
/*    */ 
/*    */   public final boolean a(String paramString)
/*    */   {
/* 93 */     paramString = Integer.parseInt(paramString);
/*    */ 
/* 96 */     this.a.a().a().b(this.jdField_a_of_type_Short, paramString);
/* 97 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     bw
 * JD-Core Version:    0.6.2
 */