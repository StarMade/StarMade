/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ 
/*    */ public final class az
/*    */ {
/* 11 */   public final q a = new q(8, 8, 8);
/*    */   public final q b;
/* 13 */   public final q c = new q(8, 8, 8);
/*    */   public boolean a;
/*    */   public boolean b;
/*    */   public boolean c;
/*    */   public int a;
/*    */   public int b;
/*    */ 
/*    */   public az()
/*    */   {
/* 12 */     this.jdField_b_of_type_Q = new q(8, 8, 8);
/*    */ 
/* 20 */     this.jdField_b_of_type_Int = 1;
/*    */   }
/*    */ 
/*    */   public static int a(short paramShort, boolean paramBoolean1, int paramInt, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
/*    */   {
/* 29 */     if (paramShort != 0) {
/* 30 */       if (!paramBoolean1) {
/* 31 */         paramInt += 8;
/*    */       }
/*    */ 
/* 34 */       if ((
/* 34 */         paramShort = ElementKeyMap.getInfo(paramShort))
/* 34 */         .getBlockStyle() > 0) {
/* 35 */         paramInt = org.schema.game.common.data.element.Element.orientationMapping[paramInt];
/*    */ 
/* 38 */         if (paramBoolean2) {
/* 39 */           paramInt = dO.a[(paramShort.blockStyle - 1)][paramInt];
/*    */         }
/*    */ 
/* 43 */         if (paramBoolean3) {
/* 44 */           paramInt = dO.b[(paramShort.blockStyle - 1)][paramInt];
/*    */         }
/*    */ 
/* 48 */         if (paramBoolean4) {
/* 49 */           paramInt = dO.c[(paramShort.blockStyle - 1)][paramInt];
/*    */         }
/*    */ 
/* 54 */         paramInt = org.schema.game.common.data.element.Element.orientationBackMapping[paramInt];
/*    */       }
/* 56 */       if (!paramBoolean1) {
/* 57 */         paramInt -= 8;
/*    */       }
/*    */     }
/*    */ 
/* 61 */     return paramInt;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     az
 * JD-Core Version:    0.6.2
 */