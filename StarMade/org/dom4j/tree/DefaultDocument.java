package org.dom4j.tree;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.xml.sax.EntityResolver;

public class DefaultDocument
  extends AbstractDocument
{
  protected static final List EMPTY_LIST = Collections.EMPTY_LIST;
  protected static final Iterator EMPTY_ITERATOR = EMPTY_LIST.iterator();
  private String name;
  private Element rootElement;
  private List content;
  private DocumentType docType;
  private DocumentFactory documentFactory = DocumentFactory.getInstance();
  private transient EntityResolver entityResolver;
  
  public DefaultDocument() {}
  
  public DefaultDocument(String name)
  {
    this.name = name;
  }
  
  public DefaultDocument(Element rootElement)
  {
    this.rootElement = rootElement;
  }
  
  public DefaultDocument(DocumentType docType)
  {
    this.docType = docType;
  }
  
  public DefaultDocument(Element rootElement, DocumentType docType)
  {
    this.rootElement = rootElement;
    this.docType = docType;
  }
  
  public DefaultDocument(String name, Element rootElement, DocumentType docType)
  {
    this.name = name;
    this.rootElement = rootElement;
    this.docType = docType;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public Element getRootElement()
  {
    return this.rootElement;
  }
  
  public DocumentType getDocType()
  {
    return this.docType;
  }
  
  public void setDocType(DocumentType docType)
  {
    this.docType = docType;
  }
  
  public Document addDocType(String docTypeName, String publicId, String systemId)
  {
    setDocType(getDocumentFactory().createDocType(docTypeName, publicId, systemId));
    return this;
  }
  
  public String getXMLEncoding()
  {
    return this.encoding;
  }
  
  public EntityResolver getEntityResolver()
  {
    return this.entityResolver;
  }
  
  public void setEntityResolver(EntityResolver entityResolver)
  {
    this.entityResolver = entityResolver;
  }
  
  public Object clone()
  {
    DefaultDocument document = (DefaultDocument)super.clone();
    document.rootElement = null;
    document.content = null;
    document.appendContent(this);
    return document;
  }
  
  public List processingInstructions()
  {
    List source = contentList();
    List answer = createResultList();
    int size = source.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = source.get(local_i);
      if ((object instanceof ProcessingInstruction)) {
        answer.add(object);
      }
    }
    return answer;
  }
  
  public List processingInstructions(String target)
  {
    List source = contentList();
    List answer = createResultList();
    int size = source.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = source.get(local_i);
      if ((object instanceof ProcessingInstruction))
      {
        ProcessingInstruction local_pi = (ProcessingInstruction)object;
        if (target.equals(local_pi.getName())) {
          answer.add(local_pi);
        }
      }
    }
    return answer;
  }
  
  public ProcessingInstruction processingInstruction(String target)
  {
    List source = contentList();
    int size = source.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = source.get(local_i);
      if ((object instanceof ProcessingInstruction))
      {
        ProcessingInstruction local_pi = (ProcessingInstruction)object;
        if (target.equals(local_pi.getName())) {
          return local_pi;
        }
      }
    }
    return null;
  }
  
  public boolean removeProcessingInstruction(String target)
  {
    List source = contentList();
    Iterator iter = source.iterator();
    while (iter.hasNext())
    {
      Object object = iter.next();
      if ((object instanceof ProcessingInstruction))
      {
        ProcessingInstruction local_pi = (ProcessingInstruction)object;
        if (target.equals(local_pi.getName()))
        {
          iter.remove();
          return true;
        }
      }
    }
    return false;
  }
  
  public void setContent(List content)
  {
    this.rootElement = null;
    contentRemoved();
    if ((content instanceof ContentListFacade)) {
      content = ((ContentListFacade)content).getBackingList();
    }
    if (content == null)
    {
      this.content = null;
    }
    else
    {
      int size = content.size();
      List newContent = createContentList(size);
      for (int local_i = 0; local_i < size; local_i++)
      {
        Object object = content.get(local_i);
        if ((object instanceof Node))
        {
          Node node = (Node)object;
          Document doc = node.getDocument();
          if ((doc != null) && (doc != this)) {
            node = (Node)node.clone();
          }
          if ((node instanceof Element)) {
            if (this.rootElement == null) {
              this.rootElement = ((Element)node);
            } else {
              throw new IllegalAddException("A document may only contain one root element: " + content);
            }
          }
          newContent.add(node);
          childAdded(node);
        }
      }
      this.content = newContent;
    }
  }
  
  public void clearContent()
  {
    contentRemoved();
    this.content = null;
    this.rootElement = null;
  }
  
  public void setDocumentFactory(DocumentFactory documentFactory)
  {
    this.documentFactory = documentFactory;
  }
  
  protected List contentList()
  {
    if (this.content == null)
    {
      this.content = createContentList();
      if (this.rootElement != null) {
        this.content.add(this.rootElement);
      }
    }
    return this.content;
  }
  
  protected void addNode(Node node)
  {
    if (node != null)
    {
      Document document = node.getDocument();
      if ((document != null) && (document != this))
      {
        String message = "The Node already has an existing document: " + document;
        throw new IllegalAddException(this, node, message);
      }
      contentList().add(node);
      childAdded(node);
    }
  }
  
  protected void addNode(int index, Node node)
  {
    if (node != null)
    {
      Document document = node.getDocument();
      if ((document != null) && (document != this))
      {
        String message = "The Node already has an existing document: " + document;
        throw new IllegalAddException(this, node, message);
      }
      contentList().add(index, node);
      childAdded(node);
    }
  }
  
  protected boolean removeNode(Node node)
  {
    if (node == this.rootElement) {
      this.rootElement = null;
    }
    if (contentList().remove(node))
    {
      childRemoved(node);
      return true;
    }
    return false;
  }
  
  protected void rootElementAdded(Element element)
  {
    this.rootElement = element;
    element.setDocument(this);
  }
  
  protected DocumentFactory getDocumentFactory()
  {
    return this.documentFactory;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.DefaultDocument
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */