/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import org.schema.schine.network.RegisteredClientOnServer;
/*     */ import org.schema.schine.network.server.ServerController;
/*     */ import org.schema.schine.network.server.ServerProcessor;
/*     */ import org.schema.schine.network.server.ServerState;
/*     */ 
/*     */ public final class pp extends AbstractTableModel
/*     */ {
/*     */   private static final long serialVersionUID = -8312184178260271728L;
/*     */   private ServerController a;
/*     */ 
/*     */   public final Class getColumnClass(int paramInt)
/*     */   {
/*  26 */     return getValueAt(0, paramInt).getClass();
/*     */   }
/*     */ 
/*     */   public final int getColumnCount()
/*     */   {
/*  31 */     return 5;
/*     */   }
/*     */ 
/*     */   public final String getColumnName(int paramInt)
/*     */   {
/*  41 */     if (paramInt == 0) {
/*  42 */       return "#";
/*     */     }
/*  44 */     if (paramInt == 1) {
/*  45 */       return "ID";
/*     */     }
/*  47 */     if (paramInt == 2) {
/*  48 */       return "Name";
/*     */     }
/*  50 */     if (paramInt == 3) {
/*  51 */       return "NetStatus";
/*     */     }
/*  53 */     if (paramInt == 4) {
/*  54 */       return "Ping";
/*     */     }
/*     */ 
/*  57 */     return "unknown";
/*     */   }
/*     */ 
/*     */   public final int getRowCount()
/*     */   {
/*     */     try {
/*  63 */       return this.a.getServerState().getClients().size();
/*     */     } catch (Exception localException) {
/*     */     }
/*  66 */     return 0;
/*     */   }
/*     */ 
/*     */   public final Object getValueAt(int paramInt1, int paramInt2)
/*     */   {
/*     */     try
/*     */     {
/*  75 */       Object localObject1 = this.a.getServerState().getClients().values();
/*  76 */       Object localObject2 = null;
/*  77 */       int i = 0;
/*  78 */       for (localObject1 = ((Collection)localObject1).iterator(); ((Iterator)localObject1).hasNext(); ) { RegisteredClientOnServer localRegisteredClientOnServer = (RegisteredClientOnServer)((Iterator)localObject1).next();
/*  79 */         if (i >= paramInt1) {
/*  80 */           localObject2 = localRegisteredClientOnServer;
/*  81 */           break;
/*     */         }
/*  83 */         i++;
/*     */       }
/*  85 */       localObject1 = localObject2.getProcessor();
/*  86 */       if (paramInt2 == 0) {
/*  87 */         return String.valueOf(paramInt1);
/*     */       }
/*  89 */       if (((ServerProcessor)localObject1).getClient() == null) {
/*  90 */         return "UNKNOWN -4242";
/*     */       }
/*  92 */       if (paramInt2 == 1) {
/*  93 */         return Integer.valueOf(localObject2.getId());
/*     */       }
/*  95 */       if (paramInt2 == 2) {
/*  96 */         return localObject2.getPlayerName();
/*     */       }
/*  98 */       if (paramInt2 == 3) {
/*  99 */         if (((ServerProcessor)localObject1).isAlive()) return "alive"; return "dead";
/*     */       }
/* 101 */       if (paramInt2 == 4) {
/* 102 */         return Long.valueOf(((ServerProcessor)localObject1).getPingTime());
/*     */       }
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/* 108 */     return "-";
/*     */   }
/*     */ 
/*     */   public final void a(ServerController paramServerController)
/*     */   {
/* 119 */     this.a = paramServerController;
/* 120 */     fireTableDataChanged();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     pp
 * JD-Core Version:    0.6.2
 */