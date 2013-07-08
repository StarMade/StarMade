package org.dom4j.tree;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.Visitor;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public abstract class AbstractDocument
  extends AbstractBranch
  implements Document
{
  protected String encoding;
  
  public short getNodeType()
  {
    return 9;
  }
  
  public String getPath(Element context)
  {
    return "/";
  }
  
  public String getUniquePath(Element context)
  {
    return "/";
  }
  
  public Document getDocument()
  {
    return this;
  }
  
  public String getXMLEncoding()
  {
    return null;
  }
  
  public String getStringValue()
  {
    Element root = getRootElement();
    return root != null ? root.getStringValue() : "";
  }
  
  public String asXML()
  {
    OutputFormat format = new OutputFormat();
    format.setEncoding(this.encoding);
    try
    {
      StringWriter out = new StringWriter();
      XMLWriter writer = new XMLWriter(out, format);
      writer.write(this);
      writer.flush();
      return out.toString();
    }
    catch (IOException out)
    {
      throw new RuntimeException("IOException while generating textual representation: " + out.getMessage());
    }
  }
  
  public void write(Writer out)
    throws IOException
  {
    OutputFormat format = new OutputFormat();
    format.setEncoding(this.encoding);
    XMLWriter writer = new XMLWriter(out, format);
    writer.write(this);
  }
  
  public void accept(Visitor visitor)
  {
    visitor.visit(this);
    DocumentType docType = getDocType();
    if (docType != null) {
      visitor.visit(docType);
    }
    List content = content();
    if (content != null)
    {
      Iterator iter = content.iterator();
      while (iter.hasNext())
      {
        Object object = iter.next();
        if ((object instanceof String))
        {
          Text text = getDocumentFactory().createText((String)object);
          visitor.visit(text);
        }
        else
        {
          Node text = (Node)object;
          text.accept(visitor);
        }
      }
    }
  }
  
  public String toString()
  {
    return super.toString() + " [Document: name " + getName() + "]";
  }
  
  public void normalize()
  {
    Element element = getRootElement();
    if (element != null) {
      element.normalize();
    }
  }
  
  public Document addComment(String comment)
  {
    Comment node = getDocumentFactory().createComment(comment);
    add(node);
    return this;
  }
  
  public Document addProcessingInstruction(String target, String data)
  {
    ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
    add(node);
    return this;
  }
  
  public Document addProcessingInstruction(String target, Map data)
  {
    ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
    add(node);
    return this;
  }
  
  public Element addElement(String name)
  {
    Element element = getDocumentFactory().createElement(name);
    add(element);
    return element;
  }
  
  public Element addElement(String qualifiedName, String namespaceURI)
  {
    Element element = getDocumentFactory().createElement(qualifiedName, namespaceURI);
    add(element);
    return element;
  }
  
  public Element addElement(QName qName)
  {
    Element element = getDocumentFactory().createElement(qName);
    add(element);
    return element;
  }
  
  public void setRootElement(Element rootElement)
  {
    clearContent();
    if (rootElement != null)
    {
      super.add(rootElement);
      rootElementAdded(rootElement);
    }
  }
  
  public void add(Element element)
  {
    checkAddElementAllowed(element);
    super.add(element);
    rootElementAdded(element);
  }
  
  public boolean remove(Element element)
  {
    boolean answer = super.remove(element);
    Element root = getRootElement();
    if ((root != null) && (answer)) {
      setRootElement(null);
    }
    element.setDocument(null);
    return answer;
  }
  
  public Node asXPathResult(Element parent)
  {
    return this;
  }
  
  protected void childAdded(Node node)
  {
    if (node != null) {
      node.setDocument(this);
    }
  }
  
  protected void childRemoved(Node node)
  {
    if (node != null) {
      node.setDocument(null);
    }
  }
  
  protected void checkAddElementAllowed(Element element)
  {
    Element root = getRootElement();
    if (root != null) {
      throw new IllegalAddException(this, element, "Cannot add another element to this Document as it already has a root element of: " + root.getQualifiedName());
    }
  }
  
  protected abstract void rootElementAdded(Element paramElement);
  
  public void setXMLEncoding(String enc)
  {
    this.encoding = enc;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.AbstractDocument
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */