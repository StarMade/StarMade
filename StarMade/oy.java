/*    */ import java.util.ArrayList;
/*    */ import org.schema.game.common.data.element.BlockFactory;
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ 
/*    */ public final class oy
/*    */ {
/*    */   ArrayList a;
/*    */   public short a;
/*    */ 
/*    */   public oy()
/*    */   {
/* 11 */     this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*    */   }
/*    */ 
/*    */   public final void a(short paramShort)
/*    */   {
/*    */     ElementInformation localElementInformation;
/* 18 */     if ((
/* 18 */       localElementInformation = ElementKeyMap.getInfo(paramShort))
/* 18 */       .getFactory() != null) {
/* 19 */       this.jdField_a_of_type_Short = localElementInformation.getFactory().enhancer;
/* 20 */       for (int i = 0; i < localElementInformation.getFactory().input.length; i++)
/*    */       {
/*    */         oz localoz;
/* 21 */         (
/* 22 */           localoz = new oz()).jdField_a_of_type_Short = 
/* 22 */           paramShort;
/*    */ 
/* 24 */         for (int j = 0; j < localElementInformation.getFactory().input[i].length; j++) {
/* 25 */           localoz.jdField_a_of_type_JavaUtilArrayList.add(localElementInformation.getFactory().input[i][j]);
/*    */         }
/* 27 */         for (j = 0; j < localElementInformation.getFactory().output[i].length; j++) {
/* 28 */           localoz.b.add(localElementInformation.getFactory().output[i][j]);
/*    */         }
/* 30 */         this.jdField_a_of_type_JavaUtilArrayList.add(localoz);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public final String toString()
/*    */   {
/* 63 */     return "Factory Products: " + this.jdField_a_of_type_JavaUtilArrayList.size();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     oy
 * JD-Core Version:    0.6.2
 */