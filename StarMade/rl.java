/*    */ import java.awt.Component;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.ListCellRenderer;
/*    */ import javax.swing.UIManager;
/*    */ 
/*    */ public final class rl extends JLabel
/*    */   implements ListCellRenderer
/*    */ {
/*    */   private static final long serialVersionUID = 6987354272651292846L;
/*    */ 
/*    */   public final Component getListCellRendererComponent(JList paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/*    */   {
/* 22 */     if ((paramObject instanceof lE)) {
/* 23 */       paramJList = (lE)paramObject;
/*    */ 
/* 25 */       setText(paramJList.getName());
/*    */     } else {
/* 27 */       setText("StarmoteSynchException");
/*    */     }
/*    */ 
/* 30 */     setOpaque(true);
/*    */ 
/* 32 */     if (paramBoolean1) {
/* 33 */       setForeground(UIManager.getColor("List.selectionForeground"));
/* 34 */       setBackground(UIManager.getColor("List.selectionBackground"));
/*    */     }
/*    */     else
/*    */     {
/* 38 */       setBackground(UIManager.getColor("List.background"));
/*    */     }
/*    */ 
/* 41 */     return this;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     rl
 * JD-Core Version:    0.6.2
 */