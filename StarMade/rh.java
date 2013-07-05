/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import javax.swing.AbstractListModel;
/*    */ import org.schema.game.network.StarMadePlayerStats;
/*    */ 
/*    */ public final class rh extends AbstractListModel
/*    */ {
/*    */   private static final long serialVersionUID = -2709488666387980490L;
/* 21 */   private ArrayList a = new ArrayList();
/*    */ 
/*    */   public final Object getElementAt(int paramInt)
/*    */   {
/* 31 */     return this.a.get(paramInt);
/*    */   }
/*    */ 
/*    */   public final int getSize()
/*    */   {
/* 36 */     return this.a.size();
/*    */   }
/*    */   public final void a(StarMadePlayerStats paramStarMadePlayerStats) {
/* 39 */     this.a.clear();
/* 40 */     for (int i = 0; i < paramStarMadePlayerStats.receivedPlayers.length; i++) {
/* 41 */       this.a.add(paramStarMadePlayerStats.receivedPlayers[i]);
/*    */     }
/* 43 */     System.err.println("[Starmote] All-Players Request has been awnsered " + this.a);
/* 44 */     Collections.sort(this.a, new ri());
/*    */ 
/* 53 */     fireContentsChanged(this, 0, this.a.size());
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rh
 * JD-Core Version:    0.6.2
 */