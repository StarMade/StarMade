import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.table.AbstractTableModel;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.server.ServerController;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerState;

public final class class_1343
  extends AbstractTableModel
{
  private static final long serialVersionUID = -8312184178260271728L;
  private ServerController field_1523;
  
  public final Class getColumnClass(int paramInt)
  {
    return getValueAt(0, paramInt).getClass();
  }
  
  public final int getColumnCount()
  {
    return 5;
  }
  
  public final String getColumnName(int paramInt)
  {
    if (paramInt == 0) {
      return "#";
    }
    if (paramInt == 1) {
      return "ID";
    }
    if (paramInt == 2) {
      return "Name";
    }
    if (paramInt == 3) {
      return "NetStatus";
    }
    if (paramInt == 4) {
      return "Ping";
    }
    return "unknown";
  }
  
  public final int getRowCount()
  {
    try
    {
      return this.field_1523.getServerState().getClients().size();
    }
    catch (Exception localException) {}
    return 0;
  }
  
  public final Object getValueAt(int paramInt1, int paramInt2)
  {
    try
    {
      Object localObject1 = this.field_1523.getServerState().getClients().values();
      Object localObject2 = null;
      int i = 0;
      localObject1 = ((Collection)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        RegisteredClientOnServer localRegisteredClientOnServer = (RegisteredClientOnServer)((Iterator)localObject1).next();
        if (i >= paramInt1)
        {
          localObject2 = localRegisteredClientOnServer;
          break;
        }
        i++;
      }
      localObject1 = localObject2.getProcessor();
      if (paramInt2 == 0) {
        return String.valueOf(paramInt1);
      }
      if (((ServerProcessor)localObject1).getClient() == null) {
        return "UNKNOWN -4242";
      }
      if (paramInt2 == 1) {
        return Integer.valueOf(localObject2.getId());
      }
      if (paramInt2 == 2) {
        return localObject2.getPlayerName();
      }
      if (paramInt2 == 3)
      {
        if (((ServerProcessor)localObject1).isAlive()) {
          return "alive";
        }
        return "dead";
      }
      if (paramInt2 == 4) {
        return Long.valueOf(((ServerProcessor)localObject1).getPingTime());
      }
    }
    catch (Exception localException) {}
    return "-";
  }
  
  public final void a(ServerController paramServerController)
  {
    this.field_1523 = paramServerController;
    fireTableDataChanged();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1343
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */