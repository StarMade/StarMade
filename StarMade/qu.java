/*    */ import java.awt.Component;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.ListCellRenderer;
/*    */ import javax.swing.UIManager;
/*    */ import org.schema.schine.network.objects.Sendable;
/*    */ 
/*    */ public final class qu extends JLabel
/*    */   implements ListCellRenderer
/*    */ {
/*    */   private static final long serialVersionUID = 6987354272651292846L;
/*    */ 
/*    */   public final Component getListCellRendererComponent(JList paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/*    */   {
/* 23 */     paramJList = (Sendable)paramObject;
/*    */ 
/* 25 */     setText(paramJList != null ? paramJList.toString() : "NULL");
/*    */ 
/* 27 */     setOpaque(true);
/*    */ 
/* 29 */     if (paramBoolean1) {
/* 30 */       setForeground(UIManager.getColor("List.selectionForeground"));
/* 31 */       setBackground(UIManager.getColor("List.selectionBackground"));
/*    */     }
/*    */     else
/*    */     {
/* 35 */       setBackground(UIManager.getColor("List.background"));
/*    */     }
/*    */ 
/* 38 */     return this;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     qu
 * JD-Core Version:    0.6.2
 */