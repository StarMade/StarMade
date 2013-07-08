/*  1:   */import java.io.PrintStream;
/*  2:   */import java.util.ArrayList;
/*  3:   */import java.util.Collections;
/*  4:   */import javax.swing.AbstractListModel;
/*  5:   */import org.schema.game.network.StarMadePlayerStats;
/*  6:   */
/* 17:   */public final class rh
/* 18:   */  extends AbstractListModel
/* 19:   */{
/* 20:   */  private static final long serialVersionUID = -2709488666387980490L;
/* 21:21 */  private ArrayList a = new ArrayList();
/* 22:   */  
/* 29:   */  public final Object getElementAt(int paramInt)
/* 30:   */  {
/* 31:31 */    return this.a.get(paramInt);
/* 32:   */  }
/* 33:   */  
/* 36:36 */  public final int getSize() { return this.a.size(); }
/* 37:   */  
/* 38:   */  public final void a(StarMadePlayerStats paramStarMadePlayerStats) {
/* 39:39 */    this.a.clear();
/* 40:40 */    for (int i = 0; i < paramStarMadePlayerStats.receivedPlayers.length; i++) {
/* 41:41 */      this.a.add(paramStarMadePlayerStats.receivedPlayers[i]);
/* 42:   */    }
/* 43:43 */    System.err.println("[Starmote] All-Players Request has been awnsered " + this.a);
/* 44:44 */    Collections.sort(this.a, new ri());
/* 45:   */    
/* 53:53 */    fireContentsChanged(this, 0, this.a.size());
/* 54:   */  }
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rh
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */