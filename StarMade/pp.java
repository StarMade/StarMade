/*   1:    */import java.util.Collection;
/*   2:    */import java.util.HashMap;
/*   3:    */import java.util.Iterator;
/*   4:    */import javax.swing.table.AbstractTableModel;
/*   5:    */import org.schema.schine.network.RegisteredClientOnServer;
/*   6:    */import org.schema.schine.network.server.ServerController;
/*   7:    */import org.schema.schine.network.server.ServerProcessor;
/*   8:    */import org.schema.schine.network.server.ServerState;
/*   9:    */
/*  18:    */public final class pp
/*  19:    */  extends AbstractTableModel
/*  20:    */{
/*  21:    */  private static final long serialVersionUID = -8312184178260271728L;
/*  22:    */  private ServerController a;
/*  23:    */  
/*  24:    */  public final Class getColumnClass(int paramInt)
/*  25:    */  {
/*  26: 26 */    return getValueAt(0, paramInt).getClass();
/*  27:    */  }
/*  28:    */  
/*  29:    */  public final int getColumnCount()
/*  30:    */  {
/*  31: 31 */    return 5;
/*  32:    */  }
/*  33:    */  
/*  39:    */  public final String getColumnName(int paramInt)
/*  40:    */  {
/*  41: 41 */    if (paramInt == 0) {
/*  42: 42 */      return "#";
/*  43:    */    }
/*  44: 44 */    if (paramInt == 1) {
/*  45: 45 */      return "ID";
/*  46:    */    }
/*  47: 47 */    if (paramInt == 2) {
/*  48: 48 */      return "Name";
/*  49:    */    }
/*  50: 50 */    if (paramInt == 3) {
/*  51: 51 */      return "NetStatus";
/*  52:    */    }
/*  53: 53 */    if (paramInt == 4) {
/*  54: 54 */      return "Ping";
/*  55:    */    }
/*  56:    */    
/*  57: 57 */    return "unknown";
/*  58:    */  }
/*  59:    */  
/*  60:    */  public final int getRowCount()
/*  61:    */  {
/*  62:    */    try {
/*  63: 63 */      return this.a.getServerState().getClients().size();
/*  64:    */    }
/*  65:    */    catch (Exception localException) {}
/*  66: 66 */    return 0;
/*  67:    */  }
/*  68:    */  
/*  71:    */  public final Object getValueAt(int paramInt1, int paramInt2)
/*  72:    */  {
/*  73:    */    try
/*  74:    */    {
/*  75: 75 */      Object localObject1 = this.a.getServerState().getClients().values();
/*  76: 76 */      Object localObject2 = null;
/*  77: 77 */      int i = 0;
/*  78: 78 */      for (localObject1 = ((Collection)localObject1).iterator(); ((Iterator)localObject1).hasNext();) { RegisteredClientOnServer localRegisteredClientOnServer = (RegisteredClientOnServer)((Iterator)localObject1).next();
/*  79: 79 */        if (i >= paramInt1) {
/*  80: 80 */          localObject2 = localRegisteredClientOnServer;
/*  81: 81 */          break;
/*  82:    */        }
/*  83: 83 */        i++;
/*  84:    */      }
/*  85: 85 */      localObject1 = localObject2.getProcessor();
/*  86: 86 */      if (paramInt2 == 0) {
/*  87: 87 */        return String.valueOf(paramInt1);
/*  88:    */      }
/*  89: 89 */      if (((ServerProcessor)localObject1).getClient() == null) {
/*  90: 90 */        return "UNKNOWN -4242";
/*  91:    */      }
/*  92: 92 */      if (paramInt2 == 1) {
/*  93: 93 */        return Integer.valueOf(localObject2.getId());
/*  94:    */      }
/*  95: 95 */      if (paramInt2 == 2) {
/*  96: 96 */        return localObject2.getPlayerName();
/*  97:    */      }
/*  98: 98 */      if (paramInt2 == 3) {
/*  99: 99 */        if (((ServerProcessor)localObject1).isAlive()) return "alive"; return "dead";
/* 100:    */      }
/* 101:101 */      if (paramInt2 == 4) {
/* 102:102 */        return Long.valueOf(((ServerProcessor)localObject1).getPingTime());
/* 103:    */      }
/* 104:    */    }
/* 105:    */    catch (Exception localException) {}
/* 106:    */    
/* 108:108 */    return "-";
/* 109:    */  }
/* 110:    */  
/* 117:    */  public final void a(ServerController paramServerController)
/* 118:    */  {
/* 119:119 */    this.a = paramServerController;
/* 120:120 */    fireTableDataChanged();
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */