package org.schema.game.common.facedit;

import class_530;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import org.schema.game.common.data.element.ElementCategory;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

public class ElementTreePanel
  extends JPanel
{
  private static final long serialVersionUID = -3092265231213367761L;
  private JTree field_567;
  
  public ElementTreePanel(TreeSelectionListener paramTreeSelectionListener)
  {
    setLayout(new GridLayout(0, 1, 0, 0));
    ElementKeyMap.initializeData();
    ElementCategory localElementCategory = ElementKeyMap.getCategoryHirarchy();
    DefaultMutableTreeNode localDefaultMutableTreeNode = new DefaultMutableTreeNode("Root");
    a(localDefaultMutableTreeNode, localElementCategory);
    this.field_567 = new JTree(localDefaultMutableTreeNode);
    this.field_567.setCellRenderer(new class_530());
    add(this.field_567);
    this.field_567.getSelectionModel().setSelectionMode(1);
    this.field_567.addTreeSelectionListener(paramTreeSelectionListener);
  }
  
  public static void main(String[] paramArrayOfString) {}
  
  private DefaultMutableTreeNode a(DefaultMutableTreeNode paramDefaultMutableTreeNode, ElementCategory paramElementCategory)
  {
    Iterator localIterator = paramElementCategory.getChildren().iterator();
    Object localObject;
    DefaultMutableTreeNode localDefaultMutableTreeNode;
    while (localIterator.hasNext())
    {
      localObject = (ElementCategory)localIterator.next();
      localDefaultMutableTreeNode = new DefaultMutableTreeNode(localObject);
      paramDefaultMutableTreeNode.add(a(localDefaultMutableTreeNode, (ElementCategory)localObject));
    }
    localIterator = paramElementCategory.getInfoElements().iterator();
    while (localIterator.hasNext())
    {
      localObject = (ElementInformation)localIterator.next();
      localDefaultMutableTreeNode = new DefaultMutableTreeNode(localObject);
      paramDefaultMutableTreeNode.add(localDefaultMutableTreeNode);
    }
    return paramDefaultMutableTreeNode;
  }
  
  public final JTree a1()
  {
    return this.field_567;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.facedit.ElementTreePanel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */