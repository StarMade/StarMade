/*  1:   */package org.dom4j.swing;
/*  2:   */
/*  3:   */import javax.swing.tree.DefaultTreeModel;
/*  4:   */import org.dom4j.Document;
/*  5:   */
/* 22:   */public class DocumentTreeModel
/* 23:   */  extends DefaultTreeModel
/* 24:   */{
/* 25:   */  protected Document document;
/* 26:   */  
/* 27:   */  public DocumentTreeModel(Document document)
/* 28:   */  {
/* 29:29 */    super(new BranchTreeNode(document));
/* 30:30 */    this.document = document;
/* 31:   */  }
/* 32:   */  
/* 41:   */  public Document getDocument()
/* 42:   */  {
/* 43:43 */    return this.document;
/* 44:   */  }
/* 45:   */  
/* 52:   */  public void setDocument(Document document)
/* 53:   */  {
/* 54:54 */    this.document = document;
/* 55:55 */    setRoot(new BranchTreeNode(document));
/* 56:   */  }
/* 57:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.swing.DocumentTreeModel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */