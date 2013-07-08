package org.dom4j.io;

import java.io.PrintStream;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.DocumentException;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.tree.NamespaceStack;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;

public class DOMWriter
{
  private static boolean loggedWarning = false;
  private static final String[] DEFAULT_DOM_DOCUMENT_CLASSES = { "org.apache.xerces.dom.DocumentImpl", "gnu.xml.dom.DomDocument", "org.apache.crimson.tree.XmlDocument", "com.sun.xml.tree.XmlDocument", "oracle.xml.parser.v2.XMLDocument", "oracle.xml.parser.XMLDocument", "org.dom4j.dom.DOMDocument" };
  private Class domDocumentClass;
  private NamespaceStack namespaceStack = new NamespaceStack();
  
  public DOMWriter() {}
  
  public DOMWriter(Class domDocumentClass)
  {
    this.domDocumentClass = domDocumentClass;
  }
  
  public Class getDomDocumentClass()
    throws DocumentException
  {
    Class result = this.domDocumentClass;
    if (result == null)
    {
      int size = DEFAULT_DOM_DOCUMENT_CLASSES.length;
      for (int local_i = 0; local_i < size; local_i++) {
        try
        {
          String name = DEFAULT_DOM_DOCUMENT_CLASSES[local_i];
          result = Class.forName(name, true, DOMWriter.class.getClassLoader());
          if (result != null) {
            break;
          }
        }
        catch (Exception name) {}
      }
    }
    return result;
  }
  
  public void setDomDocumentClass(Class domDocumentClass)
  {
    this.domDocumentClass = domDocumentClass;
  }
  
  public void setDomDocumentClassName(String name)
    throws DocumentException
  {
    try
    {
      this.domDocumentClass = Class.forName(name, true, DOMWriter.class.getClassLoader());
    }
    catch (Exception local_e)
    {
      throw new DocumentException("Could not load the DOM Document class: " + name, local_e);
    }
  }
  
  public org.w3c.dom.Document write(org.dom4j.Document document)
    throws DocumentException
  {
    if ((document instanceof org.w3c.dom.Document)) {
      return (org.w3c.dom.Document)document;
    }
    resetNamespaceStack();
    org.w3c.dom.Document domDocument = createDomDocument(document);
    appendDOMTree(domDocument, domDocument, document.content());
    this.namespaceStack.clear();
    return domDocument;
  }
  
  public org.w3c.dom.Document write(org.dom4j.Document document, DOMImplementation domImpl)
    throws DocumentException
  {
    if ((document instanceof org.w3c.dom.Document)) {
      return (org.w3c.dom.Document)document;
    }
    resetNamespaceStack();
    org.w3c.dom.Document domDocument = createDomDocument(document, domImpl);
    appendDOMTree(domDocument, domDocument, document.content());
    this.namespaceStack.clear();
    return domDocument;
  }
  
  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, List content)
  {
    int size = content.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = content.get(local_i);
      if ((object instanceof org.dom4j.Element))
      {
        appendDOMTree(domDocument, domCurrent, (org.dom4j.Element)object);
      }
      else if ((object instanceof String))
      {
        appendDOMTree(domDocument, domCurrent, (String)object);
      }
      else if ((object instanceof org.dom4j.Text))
      {
        org.dom4j.Text text = (org.dom4j.Text)object;
        appendDOMTree(domDocument, domCurrent, text.getText());
      }
      else if ((object instanceof CDATA))
      {
        appendDOMTree(domDocument, domCurrent, (CDATA)object);
      }
      else if ((object instanceof org.dom4j.Comment))
      {
        appendDOMTree(domDocument, domCurrent, (org.dom4j.Comment)object);
      }
      else if ((object instanceof Entity))
      {
        appendDOMTree(domDocument, domCurrent, (Entity)object);
      }
      else if ((object instanceof org.dom4j.ProcessingInstruction))
      {
        appendDOMTree(domDocument, domCurrent, (org.dom4j.ProcessingInstruction)object);
      }
    }
  }
  
  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, org.dom4j.Element element)
  {
    String elUri = element.getNamespaceURI();
    String elName = element.getQualifiedName();
    org.w3c.dom.Element domElement = domDocument.createElementNS(elUri, elName);
    int stackSize = this.namespaceStack.size();
    Namespace elementNamespace = element.getNamespace();
    if (isNamespaceDeclaration(elementNamespace))
    {
      this.namespaceStack.push(elementNamespace);
      writeNamespace(domElement, elementNamespace);
    }
    List declaredNamespaces = element.declaredNamespaces();
    int local_i = 0;
    int size = declaredNamespaces.size();
    while (local_i < size)
    {
      Namespace namespace = (Namespace)declaredNamespaces.get(local_i);
      if (isNamespaceDeclaration(namespace))
      {
        this.namespaceStack.push(namespace);
        writeNamespace(domElement, namespace);
      }
      local_i++;
    }
    int local_i = 0;
    int size = element.attributeCount();
    while (local_i < size)
    {
      Attribute namespace = element.attribute(local_i);
      String attUri = namespace.getNamespaceURI();
      String attName = namespace.getQualifiedName();
      String value = namespace.getValue();
      domElement.setAttributeNS(attUri, attName, value);
      local_i++;
    }
    appendDOMTree(domDocument, domElement, element.content());
    domCurrent.appendChild(domElement);
    while (this.namespaceStack.size() > stackSize) {
      this.namespaceStack.pop();
    }
  }
  
  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, CDATA cdata)
  {
    CDATASection domCDATA = domDocument.createCDATASection(cdata.getText());
    domCurrent.appendChild(domCDATA);
  }
  
  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, org.dom4j.Comment comment)
  {
    org.w3c.dom.Comment domComment = domDocument.createComment(comment.getText());
    domCurrent.appendChild(domComment);
  }
  
  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, String text)
  {
    org.w3c.dom.Text domText = domDocument.createTextNode(text);
    domCurrent.appendChild(domText);
  }
  
  protected void appendDOMTree(org.w3c.dom.Document domDocument, Node domCurrent, Entity entity)
  {
    EntityReference domEntity = domDocument.createEntityReference(entity.getName());
    domCurrent.appendChild(domEntity);
  }
  
  protected void appendDOMTree(org.w3c.dom.Document domDoc, Node domCurrent, org.dom4j.ProcessingInstruction local_pi)
  {
    org.w3c.dom.ProcessingInstruction domPI = domDoc.createProcessingInstruction(local_pi.getTarget(), local_pi.getText());
    domCurrent.appendChild(domPI);
  }
  
  protected void writeNamespace(org.w3c.dom.Element domElement, Namespace namespace)
  {
    String attributeName = attributeNameForNamespace(namespace);
    domElement.setAttribute(attributeName, namespace.getURI());
  }
  
  protected String attributeNameForNamespace(Namespace namespace)
  {
    String xmlns = "xmlns";
    String prefix = namespace.getPrefix();
    if (prefix.length() > 0) {
      return xmlns + ":" + prefix;
    }
    return xmlns;
  }
  
  protected org.w3c.dom.Document createDomDocument(org.dom4j.Document document)
    throws DocumentException
  {
    org.w3c.dom.Document result = null;
    if (this.domDocumentClass != null)
    {
      try
      {
        result = (org.w3c.dom.Document)this.domDocumentClass.newInstance();
      }
      catch (Exception local_e)
      {
        throw new DocumentException("Could not instantiate an instance of DOM Document with class: " + this.domDocumentClass.getName(), local_e);
      }
    }
    else
    {
      result = createDomDocumentViaJAXP();
      if (result == null)
      {
        Class local_e = getDomDocumentClass();
        try
        {
          result = (org.w3c.dom.Document)local_e.newInstance();
        }
        catch (Exception local_e1)
        {
          throw new DocumentException("Could not instantiate an instance of DOM Document with class: " + local_e.getName(), local_e1);
        }
      }
    }
    return result;
  }
  
  protected org.w3c.dom.Document createDomDocumentViaJAXP()
    throws DocumentException
  {
    try
    {
      return JAXPHelper.createDocument(false, true);
    }
    catch (Throwable local_e)
    {
      if (!loggedWarning)
      {
        loggedWarning = true;
        if (SAXHelper.isVerboseErrorReporting())
        {
          System.out.println("Warning: Caught exception attempting to use JAXP to create a W3C DOM document");
          System.out.println("Warning: Exception was: " + local_e);
          local_e.printStackTrace();
        }
        else
        {
          System.out.println("Warning: Error occurred using JAXP to create a DOM document.");
        }
      }
    }
    return null;
  }
  
  protected org.w3c.dom.Document createDomDocument(org.dom4j.Document document, DOMImplementation domImpl)
    throws DocumentException
  {
    String namespaceURI = null;
    String qualifiedName = null;
    DocumentType docType = null;
    return domImpl.createDocument(namespaceURI, qualifiedName, docType);
  }
  
  protected boolean isNamespaceDeclaration(Namespace local_ns)
  {
    if ((local_ns != null) && (local_ns != Namespace.NO_NAMESPACE) && (local_ns != Namespace.XML_NAMESPACE))
    {
      String uri = local_ns.getURI();
      if ((uri != null) && (uri.length() > 0) && (!this.namespaceStack.contains(local_ns))) {
        return true;
      }
    }
    return false;
  }
  
  protected void resetNamespaceStack()
  {
    this.namespaceStack.clear();
    this.namespaceStack.push(Namespace.XML_NAMESPACE);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.DOMWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */