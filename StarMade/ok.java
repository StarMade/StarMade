/*  1:   */import java.awt.Component;
/*  2:   */import javax.swing.JTree;
/*  3:   */import javax.swing.tree.DefaultMutableTreeNode;
/*  4:   */import javax.swing.tree.DefaultTreeCellRenderer;
/*  5:   */import org.schema.game.common.data.element.ElementInformation;
/*  6:   */
/* 23:   */public final class ok
/* 24:   */  extends DefaultTreeCellRenderer
/* 25:   */{
/* 26:   */  private static final long serialVersionUID = -317191415562426777L;
/* 27:   */  
/* 28:   */  public final Component getTreeCellRendererComponent(JTree paramJTree, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt, boolean paramBoolean4)
/* 29:   */  {
/* 30:30 */    super.getTreeCellRendererComponent(paramJTree, paramObject, paramBoolean1, paramBoolean2, paramBoolean3, paramInt, paramBoolean4);
/* 31:   */    
/* 34:34 */    if (paramBoolean3) { paramJTree = null; if ((((DefaultMutableTreeNode)paramObject).getUserObject() instanceof ElementInformation)) {
/* 35:35 */        paramJTree = (ElementInformation)((DefaultMutableTreeNode)paramObject).getUserObject();
/* 36:36 */        setIcon(d.a(paramJTree.getTextureId()));
/* 37:37 */        setToolTipText("This book is in the Tutorial series.");
/* 38:38 */        return this; } }
/* 39:39 */    setToolTipText(null);
/* 40:   */    
/* 42:42 */    return this;
/* 43:   */  }
/* 44:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ok
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */