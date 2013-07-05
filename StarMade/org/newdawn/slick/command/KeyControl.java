/*    */ package org.newdawn.slick.command;
/*    */ 
/*    */ public class KeyControl
/*    */   implements Control
/*    */ {
/*    */   private int keycode;
/*    */ 
/*    */   public KeyControl(int keycode)
/*    */   {
/* 19 */     this.keycode = keycode;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/* 26 */     if ((o instanceof KeyControl)) {
/* 27 */       return ((KeyControl)o).keycode == this.keycode;
/*    */     }
/*    */ 
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 37 */     return this.keycode;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.command.KeyControl
 * JD-Core Version:    0.6.2
 */