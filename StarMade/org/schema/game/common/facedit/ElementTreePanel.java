/*    */ package org.schema.game.common.facedit;
/*    */ 
/*    */ import java.awt.GridLayout;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTree;
/*    */ import javax.swing.event.TreeSelectionListener;
/*    */ import javax.swing.tree.DefaultMutableTreeNode;
/*    */ import javax.swing.tree.TreeSelectionModel;
/*    */ import ok;
/*    */ import org.schema.game.common.data.element.ElementCategory;
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ 
/*    */ public class ElementTreePanel extends JPanel
/*    */ {
/*    */   private static final long serialVersionUID = -3092265231213367761L;
/*    */   private JTree a;
/*    */ 
/*    */   public ElementTreePanel(TreeSelectionListener paramTreeSelectionListener)
/*    */   {
/* 27 */     setLayout(new GridLayout(0, 1, 0, 0));
/*    */ 
/* 29 */     ElementKeyMap.initializeData();
/*    */ 
/* 31 */     ElementCategory localElementCategory = ElementKeyMap.getCategoryHirarchy();
/* 32 */     DefaultMutableTreeNode localDefaultMutableTreeNode = new DefaultMutableTreeNode("Root");
/* 33 */     a(localDefaultMutableTreeNode, localElementCategory);
/*    */ 
/* 35 */     this.a = new JTree(localDefaultMutableTreeNode);
/* 36 */     this.a.setCellRenderer(new ok());
/*    */ 
/* 38 */     add(this.a);
/*    */ 
/* 40 */     this.a.getSelectionModel().setSelectionMode(1);
/*    */ 
/* 44 */     this.a.addTreeSelectionListener(paramTreeSelectionListener);
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/*    */   }
/*    */ 
/*    */   private DefaultMutableTreeNode a(DefaultMutableTreeNode paramDefaultMutableTreeNode, ElementCategory paramElementCategory)
/*    */   {
/* 54 */     for (Iterator localIterator = paramElementCategory.getChildren().iterator(); localIterator.hasNext(); ) { localObject = (ElementCategory)localIterator.next();
/* 55 */       localDefaultMutableTreeNode = new DefaultMutableTreeNode(localObject);
/* 56 */       paramDefaultMutableTreeNode.add(a(localDefaultMutableTreeNode, (ElementCategory)localObject));
/*    */     }
/* 58 */     Object localObject;
/*    */     DefaultMutableTreeNode localDefaultMutableTreeNode;
/* 58 */     for (localIterator = paramElementCategory.getInfoElements().iterator(); localIterator.hasNext(); ) { localObject = (ElementInformation)localIterator.next();
/* 59 */       localDefaultMutableTreeNode = new DefaultMutableTreeNode(localObject);
/* 60 */       paramDefaultMutableTreeNode.add(localDefaultMutableTreeNode);
/*    */     }
/*    */ 
/* 63 */     return paramDefaultMutableTreeNode;
/*    */   }
/*    */ 
/*    */   public final JTree a()
/*    */   {
/* 78 */     return this.a;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.facedit.ElementTreePanel
 * JD-Core Version:    0.6.2
 */