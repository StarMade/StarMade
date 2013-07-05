/*    */ import java.awt.Component;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.TableCellRenderer;
/*    */ 
/*    */ public final class sc extends JLabel
/*    */   implements TableCellRenderer
/*    */ {
/*    */   public final Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
/*    */   {
/* 16 */     paramJTable = (rY)paramObject;
/* 17 */     if (paramInt2 == 0) {
/* 18 */       setText(paramJTable.a());
/* 19 */       return this;
/*    */     }
/* 21 */     return paramJTable.a();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sc
 * JD-Core Version:    0.6.2
 */