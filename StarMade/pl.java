/*    */ import java.io.IOException;
/*    */ import java.util.Observable;
/*    */ 
/*    */ final class pl extends Observable
/*    */ {
/*    */   private pl(oU paramoU)
/*    */   {
/*    */   }
/*    */ 
/*    */   public final void a(jb paramjb)
/*    */   {
/*    */     try
/*    */     {
/* 65 */       xu.b();
/*    */     }
/*    */     catch (IOException localIOException) {
/* 68 */       localIOException.printStackTrace();
/*    */     }
/*    */ 
/* 69 */     if (oU.a(this.a)) {
/* 70 */       setChanged();
/* 71 */       notifyObservers();
/*    */     } else {
/* 73 */       org.schema.game.common.Starter.b = true;
/*    */     }
/* 75 */     setChanged();
/* 76 */     notifyObservers(paramjb);
/* 77 */     this.a.setVisible(false);
/* 78 */     this.a.setVisible(false);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pl
 * JD-Core Version:    0.6.2
 */