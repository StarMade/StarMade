/*  1:   */package org.schema.game.common.facedit;
/*  2:   */
/*  3:   */import java.awt.GridLayout;
/*  4:   */import java.util.ArrayList;
/*  5:   */import java.util.Iterator;
/*  6:   */import javax.swing.JPanel;
/*  7:   */import javax.swing.JTree;
/*  8:   */import javax.swing.event.TreeSelectionListener;
/*  9:   */import javax.swing.tree.DefaultMutableTreeNode;
/* 10:   */import javax.swing.tree.TreeSelectionModel;
/* 11:   */import ok;
/* 12:   */import org.schema.game.common.data.element.ElementCategory;
/* 13:   */import org.schema.game.common.data.element.ElementInformation;
/* 14:   */import org.schema.game.common.data.element.ElementKeyMap;
/* 15:   */
/* 19:   */public class ElementTreePanel
/* 20:   */  extends JPanel
/* 21:   */{
/* 22:   */  private static final long serialVersionUID = -3092265231213367761L;
/* 23:   */  private JTree a;
/* 24:   */  
/* 25:   */  public ElementTreePanel(TreeSelectionListener paramTreeSelectionListener)
/* 26:   */  {
/* 27:27 */    setLayout(new GridLayout(0, 1, 0, 0));
/* 28:   */    
/* 29:29 */    ElementKeyMap.initializeData();
/* 30:   */    
/* 31:31 */    ElementCategory localElementCategory = ElementKeyMap.getCategoryHirarchy();
/* 32:32 */    DefaultMutableTreeNode localDefaultMutableTreeNode = new DefaultMutableTreeNode("Root");
/* 33:33 */    a(localDefaultMutableTreeNode, localElementCategory);
/* 34:   */    
/* 35:35 */    this.a = new JTree(localDefaultMutableTreeNode);
/* 36:36 */    this.a.setCellRenderer(new ok());
/* 37:   */    
/* 38:38 */    add(this.a);
/* 39:   */    
/* 40:40 */    this.a.getSelectionModel().setSelectionMode(1);
/* 41:   */    
/* 44:44 */    this.a.addTreeSelectionListener(paramTreeSelectionListener);
/* 45:   */  }
/* 46:   */  
/* 49:   */  public static void main(String[] paramArrayOfString) {}
/* 50:   */  
/* 52:   */  private DefaultMutableTreeNode a(DefaultMutableTreeNode paramDefaultMutableTreeNode, ElementCategory paramElementCategory)
/* 53:   */  {
/* 54:54 */    for (Iterator localIterator = paramElementCategory.getChildren().iterator(); localIterator.hasNext();) { localObject = (ElementCategory)localIterator.next();
/* 55:55 */      localDefaultMutableTreeNode = new DefaultMutableTreeNode(localObject);
/* 56:56 */      paramDefaultMutableTreeNode.add(a(localDefaultMutableTreeNode, (ElementCategory)localObject)); }
/* 57:   */    Object localObject;
/* 58:58 */    DefaultMutableTreeNode localDefaultMutableTreeNode; for (localIterator = paramElementCategory.getInfoElements().iterator(); localIterator.hasNext();) { localObject = (ElementInformation)localIterator.next();
/* 59:59 */      localDefaultMutableTreeNode = new DefaultMutableTreeNode(localObject);
/* 60:60 */      paramDefaultMutableTreeNode.add(localDefaultMutableTreeNode);
/* 61:   */    }
/* 62:   */    
/* 63:63 */    return paramDefaultMutableTreeNode;
/* 64:   */  }
/* 65:   */  
/* 76:   */  public final JTree a()
/* 77:   */  {
/* 78:78 */    return this.a;
/* 79:   */  }
/* 80:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.facedit.ElementTreePanel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */