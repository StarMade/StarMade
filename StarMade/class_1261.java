import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.AbstractListModel;
import org.schema.game.network.StarMadePlayerStats;

public final class class_1261
  extends AbstractListModel
{
  private static final long serialVersionUID = -2709488666387980490L;
  private ArrayList field_1441 = new ArrayList();
  
  public final Object getElementAt(int paramInt)
  {
    return this.field_1441.get(paramInt);
  }
  
  public final int getSize()
  {
    return this.field_1441.size();
  }
  
  public final void a(StarMadePlayerStats paramStarMadePlayerStats)
  {
    this.field_1441.clear();
    for (int i = 0; i < paramStarMadePlayerStats.receivedPlayers.length; i++) {
      this.field_1441.add(paramStarMadePlayerStats.receivedPlayers[i]);
    }
    System.err.println("[Starmote] All-Players Request has been awnsered " + this.field_1441);
    Collections.sort(this.field_1441, new class_1259());
    fireContentsChanged(this, 0, this.field_1441.size());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1261
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */