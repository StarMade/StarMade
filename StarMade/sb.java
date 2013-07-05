/*    */ import java.awt.Component;
/*    */ import java.util.EventObject;
/*    */ import javax.swing.AbstractCellEditor;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.TableCellEditor;
/*    */ 
/*    */ public final class sb extends AbstractCellEditor
/*    */   implements TableCellEditor
/*    */ {
/*    */   public final Object getCellEditorValue()
/*    */   {
/* 14 */     return null;
/*    */   }
/*    */ 
/*    */   public final Component getTableCellEditorComponent(JTable paramJTable, Object paramObject, boolean paramBoolean, int paramInt1, int paramInt2)
/*    */   {
/* 21 */     return ((rY)paramObject)
/* 21 */       .a();
/*    */   }
/*    */ 
/*    */   public final boolean isCellEditable(EventObject paramEventObject)
/*    */   {
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   public final boolean shouldSelectCell(EventObject paramEventObject)
/*    */   {
/* 49 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     sb
 * JD-Core Version:    0.6.2
 */