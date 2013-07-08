package org.dom4j.swing;

import javax.swing.tree.DefaultTreeModel;
import org.dom4j.Document;

public class DocumentTreeModel
  extends DefaultTreeModel
{
  protected Document document;
  
  public DocumentTreeModel(Document document)
  {
    super(new BranchTreeNode(document));
    this.document = document;
  }
  
  public Document getDocument()
  {
    return this.document;
  }
  
  public void setDocument(Document document)
  {
    this.document = document;
    setRoot(new BranchTreeNode(document));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.swing.DocumentTreeModel
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */