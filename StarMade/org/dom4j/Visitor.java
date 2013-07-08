package org.dom4j;

public abstract interface Visitor
{
  public abstract void visit(Document paramDocument);
  
  public abstract void visit(DocumentType paramDocumentType);
  
  public abstract void visit(Element paramElement);
  
  public abstract void visit(Attribute paramAttribute);
  
  public abstract void visit(CDATA paramCDATA);
  
  public abstract void visit(Comment paramComment);
  
  public abstract void visit(Entity paramEntity);
  
  public abstract void visit(Namespace paramNamespace);
  
  public abstract void visit(ProcessingInstruction paramProcessingInstruction);
  
  public abstract void visit(Text paramText);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.Visitor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */