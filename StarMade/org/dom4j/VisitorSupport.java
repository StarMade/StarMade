package org.dom4j;

public abstract class VisitorSupport
  implements Visitor
{
  public void visit(Document document) {}
  
  public void visit(DocumentType documentType) {}
  
  public void visit(Element node) {}
  
  public void visit(Attribute node) {}
  
  public void visit(CDATA node) {}
  
  public void visit(Comment node) {}
  
  public void visit(Entity node) {}
  
  public void visit(Namespace namespace) {}
  
  public void visit(ProcessingInstruction node) {}
  
  public void visit(Text node) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.VisitorSupport
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */