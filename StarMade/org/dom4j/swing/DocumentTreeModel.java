/*    */ package org.dom4j.swing;
/*    */ 
/*    */ import javax.swing.tree.DefaultTreeModel;
/*    */ import org.dom4j.Document;
/*    */ 
/*    */ public class DocumentTreeModel extends DefaultTreeModel
/*    */ {
/*    */   protected Document document;
/*    */ 
/*    */   public DocumentTreeModel(Document document)
/*    */   {
/* 29 */     super(new BranchTreeNode(document));
/* 30 */     this.document = document;
/*    */   }
/*    */ 
/*    */   public Document getDocument()
/*    */   {
/* 43 */     return this.document;
/*    */   }
/*    */ 
/*    */   public void setDocument(Document document)
/*    */   {
/* 54 */     this.document = document;
/* 55 */     setRoot(new BranchTreeNode(document));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.DocumentTreeModel
 * JD-Core Version:    0.6.2
 */