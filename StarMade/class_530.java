import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.schema.game.common.data.element.ElementInformation;

public final class class_530
  extends DefaultTreeCellRenderer
{
  private static final long serialVersionUID = -317191415562426777L;
  
  public final Component getTreeCellRendererComponent(JTree paramJTree, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt, boolean paramBoolean4)
  {
    super.getTreeCellRendererComponent(paramJTree, paramObject, paramBoolean1, paramBoolean2, paramBoolean3, paramInt, paramBoolean4);
    if (paramBoolean3)
    {
      paramJTree = null;
      if ((((DefaultMutableTreeNode)paramObject).getUserObject() instanceof ElementInformation))
      {
        paramJTree = (ElementInformation)((DefaultMutableTreeNode)paramObject).getUserObject();
        setIcon(class_29.a8(paramJTree.getTextureId()));
        setToolTipText("This book is in the Tutorial series.");
        return this;
      }
    }
    setToolTipText(null);
    return this;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_530
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */