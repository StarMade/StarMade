/*    */ import java.awt.Component;
/*    */ import javax.swing.JTree;
/*    */ import javax.swing.tree.DefaultMutableTreeNode;
/*    */ import javax.swing.tree.DefaultTreeCellRenderer;
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ 
/*    */ public final class ok extends DefaultTreeCellRenderer
/*    */ {
/*    */   private static final long serialVersionUID = -317191415562426777L;
/*    */ 
/*    */   public final Component getTreeCellRendererComponent(JTree paramJTree, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt, boolean paramBoolean4)
/*    */   {
/* 30 */     super.getTreeCellRendererComponent(paramJTree, paramObject, paramBoolean1, paramBoolean2, paramBoolean3, paramInt, paramBoolean4);
/*    */ 
/* 34 */     if (paramBoolean3) { paramJTree = null; if ((((DefaultMutableTreeNode)paramObject).getUserObject() instanceof ElementInformation)) {
/* 35 */         paramJTree = (ElementInformation)((DefaultMutableTreeNode)paramObject).getUserObject();
/* 36 */         setIcon(d.a(paramJTree.getTextureId()));
/* 37 */         setToolTipText("This book is in the Tutorial series.");
/* 38 */         break label72;
/*    */       } } setToolTipText(null);
/*    */ 
/* 42 */     label72: return this;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ok
 * JD-Core Version:    0.6.2
 */