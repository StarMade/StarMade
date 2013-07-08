package org.dom4j.tree;

import java.util.List;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class BaseElement
  extends AbstractElement
{
  private QName qname;
  private Branch parentBranch;
  protected List content;
  protected List attributes;
  
  public BaseElement(String name)
  {
    this.qname = getDocumentFactory().createQName(name);
  }
  
  public BaseElement(QName qname)
  {
    this.qname = qname;
  }
  
  public BaseElement(String name, Namespace namespace)
  {
    this.qname = getDocumentFactory().createQName(name, namespace);
  }
  
  public Element getParent()
  {
    Element result = null;
    if ((this.parentBranch instanceof Element)) {
      result = (Element)this.parentBranch;
    }
    return result;
  }
  
  public void setParent(Element parent)
  {
    if (((this.parentBranch instanceof Element)) || (parent != null)) {
      this.parentBranch = parent;
    }
  }
  
  public Document getDocument()
  {
    if ((this.parentBranch instanceof Document)) {
      return (Document)this.parentBranch;
    }
    if ((this.parentBranch instanceof Element))
    {
      Element parent = (Element)this.parentBranch;
      return parent.getDocument();
    }
    return null;
  }
  
  public void setDocument(Document document)
  {
    if (((this.parentBranch instanceof Document)) || (document != null)) {
      this.parentBranch = document;
    }
  }
  
  public boolean supportsParent()
  {
    return true;
  }
  
  public QName getQName()
  {
    return this.qname;
  }
  
  public void setQName(QName name)
  {
    this.qname = name;
  }
  
  public void clearContent()
  {
    contentList().clear();
  }
  
  public void setContent(List content)
  {
    this.content = content;
    if ((content instanceof ContentListFacade)) {
      this.content = ((ContentListFacade)content).getBackingList();
    }
  }
  
  public void setAttributes(List attributes)
  {
    this.attributes = attributes;
    if ((attributes instanceof ContentListFacade)) {
      this.attributes = ((ContentListFacade)attributes).getBackingList();
    }
  }
  
  protected List contentList()
  {
    if (this.content == null) {
      this.content = createContentList();
    }
    return this.content;
  }
  
  protected List attributeList()
  {
    if (this.attributes == null) {
      this.attributes = createAttributeList();
    }
    return this.attributes;
  }
  
  protected List attributeList(int size)
  {
    if (this.attributes == null) {
      this.attributes = createAttributeList(size);
    }
    return this.attributes;
  }
  
  protected void setAttributeList(List attributeList)
  {
    this.attributes = attributeList;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.BaseElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */