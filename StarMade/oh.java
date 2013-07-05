/*    */ import javax.swing.JLabel;
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ 
/*    */ final class oh
/*    */   implements nd
/*    */ {
/*    */   oh(og paramog)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void a(ElementInformation paramElementInformation)
/*    */   {
/* 92 */     of.a(this.a.a, paramElementInformation.getId());
/* 93 */     of.a(this.a.a).setText(of.a(this.a.a) > 0 ? ElementKeyMap.getInfo(of.a(this.a.a)).toString() : "undefined");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     oh
 * JD-Core Version:    0.6.2
 */