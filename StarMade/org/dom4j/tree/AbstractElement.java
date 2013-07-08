package org.dom4j.tree;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.CharacterData;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.IllegalAddException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.Visitor;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.Attributes;

public abstract class AbstractElement
  extends AbstractBranch
  implements Element
{
  private static final DocumentFactory DOCUMENT_FACTORY = ;
  protected static final List EMPTY_LIST = Collections.EMPTY_LIST;
  protected static final Iterator EMPTY_ITERATOR = EMPTY_LIST.iterator();
  protected static final boolean VERBOSE_TOSTRING = false;
  protected static final boolean USE_STRINGVALUE_SEPARATOR = false;
  
  public short getNodeType()
  {
    return 1;
  }
  
  public boolean isRootElement()
  {
    Document document = getDocument();
    if (document != null)
    {
      Element root = document.getRootElement();
      if (root == this) {
        return true;
      }
    }
    return false;
  }
  
  public void setName(String name)
  {
    setQName(getDocumentFactory().createQName(name));
  }
  
  public void setNamespace(Namespace namespace)
  {
    setQName(getDocumentFactory().createQName(getName(), namespace));
  }
  
  public String getXPathNameStep()
  {
    String uri = getNamespaceURI();
    if ((uri == null) || (uri.length() == 0)) {
      return getName();
    }
    String prefix = getNamespacePrefix();
    if ((prefix == null) || (prefix.length() == 0)) {
      return "*[name()='" + getName() + "']";
    }
    return getQualifiedName();
  }
  
  public String getPath(Element context)
  {
    if (this == context) {
      return ".";
    }
    Element parent = getParent();
    if (parent == null) {
      return "/" + getXPathNameStep();
    }
    if (parent == context) {
      return getXPathNameStep();
    }
    return parent.getPath(context) + "/" + getXPathNameStep();
  }
  
  public String getUniquePath(Element context)
  {
    Element parent = getParent();
    if (parent == null) {
      return "/" + getXPathNameStep();
    }
    StringBuffer buffer = new StringBuffer();
    if (parent != context)
    {
      buffer.append(parent.getUniquePath(context));
      buffer.append("/");
    }
    buffer.append(getXPathNameStep());
    List mySiblings = parent.elements(getQName());
    if (mySiblings.size() > 1)
    {
      int idx = mySiblings.indexOf(this);
      if (idx >= 0)
      {
        buffer.append("[");
        buffer.append(Integer.toString(++idx));
        buffer.append("]");
      }
    }
    return buffer.toString();
  }
  
  public String asXML()
  {
    try
    {
      StringWriter out = new StringWriter();
      XMLWriter writer = new XMLWriter(out, new OutputFormat());
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
    XMLWriter writer = new XMLWriter(out, new OutputFormat());
    writer.write(this);
  }
  
  public void accept(Visitor visitor)
  {
    visitor.visit(this);
    int local_i = 0;
    int size = attributeCount();
    while (local_i < size)
    {
      Attribute attribute = attribute(local_i);
      visitor.visit(attribute);
      local_i++;
    }
    int local_i = 0;
    int size = nodeCount();
    while (local_i < size)
    {
      Node attribute = node(local_i);
      attribute.accept(visitor);
      local_i++;
    }
  }
  
  public String toString()
  {
    String uri = getNamespaceURI();
    if ((uri != null) && (uri.length() > 0)) {
      return super.toString() + " [Element: <" + getQualifiedName() + " uri: " + uri + " attributes: " + attributeList() + "/>]";
    }
    return super.toString() + " [Element: <" + getQualifiedName() + " attributes: " + attributeList() + "/>]";
  }
  
  public Namespace getNamespace()
  {
    return getQName().getNamespace();
  }
  
  public String getName()
  {
    return getQName().getName();
  }
  
  public String getNamespacePrefix()
  {
    return getQName().getNamespacePrefix();
  }
  
  public String getNamespaceURI()
  {
    return getQName().getNamespaceURI();
  }
  
  public String getQualifiedName()
  {
    return getQName().getQualifiedName();
  }
  
  public Object getData()
  {
    return getText();
  }
  
  public void setData(Object data) {}
  
  public Node node(int index)
  {
    if (index >= 0)
    {
      List list = contentList();
      if (index >= list.size()) {
        return null;
      }
      Object node = list.get(index);
      if (node != null)
      {
        if ((node instanceof Node)) {
          return (Node)node;
        }
        return getDocumentFactory().createText(node.toString());
      }
    }
    return null;
  }
  
  public int indexOf(Node node)
  {
    return contentList().indexOf(node);
  }
  
  public int nodeCount()
  {
    return contentList().size();
  }
  
  public Iterator nodeIterator()
  {
    return contentList().iterator();
  }
  
  public Element element(String name)
  {
    List list = contentList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof Element))
      {
        Element element = (Element)object;
        if (name.equals(element.getName())) {
          return element;
        }
      }
    }
    return null;
  }
  
  public Element element(QName qName)
  {
    List list = contentList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof Element))
      {
        Element element = (Element)object;
        if (qName.equals(element.getQName())) {
          return element;
        }
      }
    }
    return null;
  }
  
  public Element element(String name, Namespace namespace)
  {
    return element(getDocumentFactory().createQName(name, namespace));
  }
  
  public List elements()
  {
    List list = contentList();
    BackedList answer = createResultList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof Element)) {
        answer.addLocal(object);
      }
    }
    return answer;
  }
  
  public List elements(String name)
  {
    List list = contentList();
    BackedList answer = createResultList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof Element))
      {
        Element element = (Element)object;
        if (name.equals(element.getName())) {
          answer.addLocal(element);
        }
      }
    }
    return answer;
  }
  
  public List elements(QName qName)
  {
    List list = contentList();
    BackedList answer = createResultList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof Element))
      {
        Element element = (Element)object;
        if (qName.equals(element.getQName())) {
          answer.addLocal(element);
        }
      }
    }
    return answer;
  }
  
  public List elements(String name, Namespace namespace)
  {
    return elements(getDocumentFactory().createQName(name, namespace));
  }
  
  public Iterator elementIterator()
  {
    List list = elements();
    return list.iterator();
  }
  
  public Iterator elementIterator(String name)
  {
    List list = elements(name);
    return list.iterator();
  }
  
  public Iterator elementIterator(QName qName)
  {
    List list = elements(qName);
    return list.iterator();
  }
  
  public Iterator elementIterator(String name, Namespace local_ns)
  {
    return elementIterator(getDocumentFactory().createQName(name, local_ns));
  }
  
  public List attributes()
  {
    return new ContentListFacade(this, attributeList());
  }
  
  public Iterator attributeIterator()
  {
    return attributeList().iterator();
  }
  
  public Attribute attribute(int index)
  {
    return (Attribute)attributeList().get(index);
  }
  
  public int attributeCount()
  {
    return attributeList().size();
  }
  
  public Attribute attribute(String name)
  {
    List list = attributeList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Attribute attribute = (Attribute)list.get(local_i);
      if (name.equals(attribute.getName())) {
        return attribute;
      }
    }
    return null;
  }
  
  public Attribute attribute(QName qName)
  {
    List list = attributeList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Attribute attribute = (Attribute)list.get(local_i);
      if (qName.equals(attribute.getQName())) {
        return attribute;
      }
    }
    return null;
  }
  
  public Attribute attribute(String name, Namespace namespace)
  {
    return attribute(getDocumentFactory().createQName(name, namespace));
  }
  
  public void setAttributes(Attributes attributes, NamespaceStack namespaceStack, boolean noNamespaceAttributes)
  {
    int size = attributes.getLength();
    if (size > 0)
    {
      DocumentFactory factory = getDocumentFactory();
      if (size == 1)
      {
        String name = attributes.getQName(0);
        if ((noNamespaceAttributes) || (!name.startsWith("xmlns")))
        {
          String attributeURI = attributes.getURI(0);
          String attributeLocalName = attributes.getLocalName(0);
          String attributeValue = attributes.getValue(0);
          QName attributeQName = namespaceStack.getAttributeQName(attributeURI, attributeLocalName, name);
          add(factory.createAttribute(this, attributeQName, attributeValue));
        }
      }
      else
      {
        List name = attributeList(size);
        name.clear();
        for (int attributeURI = 0; attributeURI < size; attributeURI++)
        {
          String attributeLocalName = attributes.getQName(attributeURI);
          if ((noNamespaceAttributes) || (!attributeLocalName.startsWith("xmlns")))
          {
            String attributeValue = attributes.getURI(attributeURI);
            String attributeQName = attributes.getLocalName(attributeURI);
            String attributeValue = attributes.getValue(attributeURI);
            QName attributeQName = namespaceStack.getAttributeQName(attributeValue, attributeQName, attributeLocalName);
            Attribute attribute = factory.createAttribute(this, attributeQName, attributeValue);
            name.add(attribute);
            childAdded(attribute);
          }
        }
      }
    }
  }
  
  public String attributeValue(String name)
  {
    Attribute attrib = attribute(name);
    if (attrib == null) {
      return null;
    }
    return attrib.getValue();
  }
  
  public String attributeValue(QName qName)
  {
    Attribute attrib = attribute(qName);
    if (attrib == null) {
      return null;
    }
    return attrib.getValue();
  }
  
  public String attributeValue(String name, String defaultValue)
  {
    String answer = attributeValue(name);
    return answer != null ? answer : defaultValue;
  }
  
  public String attributeValue(QName qName, String defaultValue)
  {
    String answer = attributeValue(qName);
    return answer != null ? answer : defaultValue;
  }
  
  /**
   * @deprecated
   */
  public void setAttributeValue(String name, String value)
  {
    addAttribute(name, value);
  }
  
  /**
   * @deprecated
   */
  public void setAttributeValue(QName qName, String value)
  {
    addAttribute(qName, value);
  }
  
  public void add(Attribute attribute)
  {
    if (attribute.getParent() != null)
    {
      String message = "The Attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"";
      throw new IllegalAddException(this, attribute, message);
    }
    if (attribute.getValue() == null)
    {
      Attribute message = attribute(attribute.getQName());
      if (message != null) {
        remove(message);
      }
    }
    else
    {
      attributeList().add(attribute);
      childAdded(attribute);
    }
  }
  
  public boolean remove(Attribute attribute)
  {
    List list = attributeList();
    boolean answer = list.remove(attribute);
    if (answer)
    {
      childRemoved(attribute);
    }
    else
    {
      Attribute copy = attribute(attribute.getQName());
      if (copy != null)
      {
        list.remove(copy);
        answer = true;
      }
    }
    return answer;
  }
  
  public List processingInstructions()
  {
    List list = contentList();
    BackedList answer = createResultList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof ProcessingInstruction)) {
        answer.addLocal(object);
      }
    }
    return answer;
  }
  
  public List processingInstructions(String target)
  {
    List list = contentList();
    BackedList answer = createResultList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof ProcessingInstruction))
      {
        ProcessingInstruction local_pi = (ProcessingInstruction)object;
        if (target.equals(local_pi.getName())) {
          answer.addLocal(local_pi);
        }
      }
    }
    return answer;
  }
  
  public ProcessingInstruction processingInstruction(String target)
  {
    List list = contentList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
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
    List list = contentList();
    Iterator iter = list.iterator();
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
  
  public Node getXPathResult(int index)
  {
    Node answer = node(index);
    if ((answer != null) && (!answer.supportsParent())) {
      return answer.asXPathResult(this);
    }
    return answer;
  }
  
  public Element addAttribute(String name, String value)
  {
    Attribute attribute = attribute(name);
    if (value != null)
    {
      if (attribute == null)
      {
        add(getDocumentFactory().createAttribute(this, name, value));
      }
      else if (attribute.isReadOnly())
      {
        remove(attribute);
        add(getDocumentFactory().createAttribute(this, name, value));
      }
      else
      {
        attribute.setValue(value);
      }
    }
    else if (attribute != null) {
      remove(attribute);
    }
    return this;
  }
  
  public Element addAttribute(QName qName, String value)
  {
    Attribute attribute = attribute(qName);
    if (value != null)
    {
      if (attribute == null)
      {
        add(getDocumentFactory().createAttribute(this, qName, value));
      }
      else if (attribute.isReadOnly())
      {
        remove(attribute);
        add(getDocumentFactory().createAttribute(this, qName, value));
      }
      else
      {
        attribute.setValue(value);
      }
    }
    else if (attribute != null) {
      remove(attribute);
    }
    return this;
  }
  
  public Element addCDATA(String cdata)
  {
    CDATA node = getDocumentFactory().createCDATA(cdata);
    addNewNode(node);
    return this;
  }
  
  public Element addComment(String comment)
  {
    Comment node = getDocumentFactory().createComment(comment);
    addNewNode(node);
    return this;
  }
  
  public Element addElement(String name)
  {
    DocumentFactory factory = getDocumentFactory();
    int index = name.indexOf(":");
    String prefix = "";
    String localName = name;
    Namespace namespace = null;
    if (index > 0)
    {
      prefix = name.substring(0, index);
      localName = name.substring(index + 1);
      namespace = getNamespaceForPrefix(prefix);
      if (namespace == null) {
        throw new IllegalAddException("No such namespace prefix: " + prefix + " is in scope on: " + this + " so cannot add element: " + name);
      }
    }
    else
    {
      namespace = getNamespaceForPrefix("");
    }
    Element node;
    Element node;
    if (namespace != null)
    {
      QName qname = factory.createQName(localName, namespace);
      node = factory.createElement(qname);
    }
    else
    {
      node = factory.createElement(name);
    }
    addNewNode(node);
    return node;
  }
  
  public Element addEntity(String name, String text)
  {
    Entity node = getDocumentFactory().createEntity(name, text);
    addNewNode(node);
    return this;
  }
  
  public Element addNamespace(String prefix, String uri)
  {
    Namespace node = getDocumentFactory().createNamespace(prefix, uri);
    addNewNode(node);
    return this;
  }
  
  public Element addProcessingInstruction(String target, String data)
  {
    ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
    addNewNode(node);
    return this;
  }
  
  public Element addProcessingInstruction(String target, Map data)
  {
    ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
    addNewNode(node);
    return this;
  }
  
  public Element addText(String text)
  {
    Text node = getDocumentFactory().createText(text);
    addNewNode(node);
    return this;
  }
  
  public void add(Node node)
  {
    switch (node.getNodeType())
    {
    case 1: 
      add((Element)node);
      break;
    case 2: 
      add((Attribute)node);
      break;
    case 3: 
      add((Text)node);
      break;
    case 4: 
      add((CDATA)node);
      break;
    case 5: 
      add((Entity)node);
      break;
    case 7: 
      add((ProcessingInstruction)node);
      break;
    case 8: 
      add((Comment)node);
      break;
    case 13: 
      add((Namespace)node);
      break;
    case 6: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    default: 
      invalidNodeTypeAddException(node);
    }
  }
  
  public boolean remove(Node node)
  {
    switch (node.getNodeType())
    {
    case 1: 
      return remove((Element)node);
    case 2: 
      return remove((Attribute)node);
    case 3: 
      return remove((Text)node);
    case 4: 
      return remove((CDATA)node);
    case 5: 
      return remove((Entity)node);
    case 7: 
      return remove((ProcessingInstruction)node);
    case 8: 
      return remove((Comment)node);
    case 13: 
      return remove((Namespace)node);
    }
    return false;
  }
  
  public void add(CDATA cdata)
  {
    addNode(cdata);
  }
  
  public void add(Comment comment)
  {
    addNode(comment);
  }
  
  public void add(Element element)
  {
    addNode(element);
  }
  
  public void add(Entity entity)
  {
    addNode(entity);
  }
  
  public void add(Namespace namespace)
  {
    addNode(namespace);
  }
  
  public void add(ProcessingInstruction local_pi)
  {
    addNode(local_pi);
  }
  
  public void add(Text text)
  {
    addNode(text);
  }
  
  public boolean remove(CDATA cdata)
  {
    return removeNode(cdata);
  }
  
  public boolean remove(Comment comment)
  {
    return removeNode(comment);
  }
  
  public boolean remove(Element element)
  {
    return removeNode(element);
  }
  
  public boolean remove(Entity entity)
  {
    return removeNode(entity);
  }
  
  public boolean remove(Namespace namespace)
  {
    return removeNode(namespace);
  }
  
  public boolean remove(ProcessingInstruction local_pi)
  {
    return removeNode(local_pi);
  }
  
  public boolean remove(Text text)
  {
    return removeNode(text);
  }
  
  public boolean hasMixedContent()
  {
    List content = contentList();
    if ((content == null) || (content.isEmpty()) || (content.size() < 2)) {
      return false;
    }
    Class prevClass = null;
    Iterator iter = content.iterator();
    while (iter.hasNext())
    {
      Object object = iter.next();
      Class newClass = object.getClass();
      if (newClass != prevClass)
      {
        if (prevClass != null) {
          return true;
        }
        prevClass = newClass;
      }
    }
    return false;
  }
  
  public boolean isTextOnly()
  {
    List content = contentList();
    if ((content == null) || (content.isEmpty())) {
      return true;
    }
    Iterator iter = content.iterator();
    while (iter.hasNext())
    {
      Object object = iter.next();
      if ((!(object instanceof CharacterData)) && (!(object instanceof String))) {
        return false;
      }
    }
    return true;
  }
  
  public void setText(String text)
  {
    List allContent = contentList();
    if (allContent != null)
    {
      Iterator local_it = allContent.iterator();
      while (local_it.hasNext())
      {
        Node node = (Node)local_it.next();
        switch (node.getNodeType())
        {
        case 3: 
        case 4: 
        case 5: 
          local_it.remove();
        }
      }
    }
    addText(text);
  }
  
  public String getStringValue()
  {
    List list = contentList();
    int size = list.size();
    if (size > 0)
    {
      if (size == 1) {
        return getContentAsStringValue(list.get(0));
      }
      StringBuffer buffer = new StringBuffer();
      for (int local_i = 0; local_i < size; local_i++)
      {
        Object node = list.get(local_i);
        String string = getContentAsStringValue(node);
        if (string.length() > 0) {
          buffer.append(string);
        }
      }
      return buffer.toString();
    }
    return "";
  }
  
  public void normalize()
  {
    List content = contentList();
    Text previousText = null;
    int local_i = 0;
    while (local_i < content.size())
    {
      Node node = (Node)content.get(local_i);
      if ((node instanceof Text))
      {
        Text text = (Text)node;
        if (previousText != null)
        {
          previousText.appendText(text.getText());
          remove(text);
        }
        else
        {
          String value = text.getText();
          if ((value == null) || (value.length() <= 0))
          {
            remove(text);
          }
          else
          {
            previousText = text;
            local_i++;
          }
        }
      }
      else
      {
        if ((node instanceof Element))
        {
          Element text = (Element)node;
          text.normalize();
        }
        previousText = null;
        local_i++;
      }
    }
  }
  
  public String elementText(String name)
  {
    Element element = element(name);
    return element != null ? element.getText() : null;
  }
  
  public String elementText(QName qName)
  {
    Element element = element(qName);
    return element != null ? element.getText() : null;
  }
  
  public String elementTextTrim(String name)
  {
    Element element = element(name);
    return element != null ? element.getTextTrim() : null;
  }
  
  public String elementTextTrim(QName qName)
  {
    Element element = element(qName);
    return element != null ? element.getTextTrim() : null;
  }
  
  public void appendAttributes(Element element)
  {
    int local_i = 0;
    int size = element.attributeCount();
    while (local_i < size)
    {
      Attribute attribute = element.attribute(local_i);
      if (attribute.supportsParent()) {
        addAttribute(attribute.getQName(), attribute.getValue());
      } else {
        add(attribute);
      }
      local_i++;
    }
  }
  
  public Element createCopy()
  {
    Element clone = createElement(getQName());
    clone.appendAttributes(this);
    clone.appendContent(this);
    return clone;
  }
  
  public Element createCopy(String name)
  {
    Element clone = createElement(name);
    clone.appendAttributes(this);
    clone.appendContent(this);
    return clone;
  }
  
  public Element createCopy(QName qName)
  {
    Element clone = createElement(qName);
    clone.appendAttributes(this);
    clone.appendContent(this);
    return clone;
  }
  
  public QName getQName(String qualifiedName)
  {
    String prefix = "";
    String localName = qualifiedName;
    int index = qualifiedName.indexOf(":");
    if (index > 0)
    {
      prefix = qualifiedName.substring(0, index);
      localName = qualifiedName.substring(index + 1);
    }
    Namespace namespace = getNamespaceForPrefix(prefix);
    if (namespace != null) {
      return getDocumentFactory().createQName(localName, namespace);
    }
    return getDocumentFactory().createQName(localName);
  }
  
  public Namespace getNamespaceForPrefix(String prefix)
  {
    if (prefix == null) {
      prefix = "";
    }
    if (prefix.equals(getNamespacePrefix())) {
      return getNamespace();
    }
    if (prefix.equals("xml")) {
      return Namespace.XML_NAMESPACE;
    }
    List list = contentList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof Namespace))
      {
        Namespace namespace = (Namespace)object;
        if (prefix.equals(namespace.getPrefix())) {
          return namespace;
        }
      }
    }
    Element list = getParent();
    if (list != null)
    {
      Namespace size = list.getNamespaceForPrefix(prefix);
      if (size != null) {
        return size;
      }
    }
    if ((prefix == null) || (prefix.length() <= 0)) {
      return Namespace.NO_NAMESPACE;
    }
    return null;
  }
  
  public Namespace getNamespaceForURI(String uri)
  {
    if ((uri == null) || (uri.length() <= 0)) {
      return Namespace.NO_NAMESPACE;
    }
    if (uri.equals(getNamespaceURI())) {
      return getNamespace();
    }
    List list = contentList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof Namespace))
      {
        Namespace namespace = (Namespace)object;
        if (uri.equals(namespace.getURI())) {
          return namespace;
        }
      }
    }
    return null;
  }
  
  public List getNamespacesForURI(String uri)
  {
    BackedList answer = createResultList();
    List list = contentList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if (((object instanceof Namespace)) && (((Namespace)object).getURI().equals(uri))) {
        answer.addLocal(object);
      }
    }
    return answer;
  }
  
  public List declaredNamespaces()
  {
    BackedList answer = createResultList();
    List list = contentList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof Namespace)) {
        answer.addLocal(object);
      }
    }
    return answer;
  }
  
  public List additionalNamespaces()
  {
    List list = contentList();
    int size = list.size();
    BackedList answer = createResultList();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof Namespace))
      {
        Namespace namespace = (Namespace)object;
        if (!namespace.equals(getNamespace())) {
          answer.addLocal(namespace);
        }
      }
    }
    return answer;
  }
  
  public List additionalNamespaces(String defaultNamespaceURI)
  {
    List list = contentList();
    BackedList answer = createResultList();
    int size = list.size();
    for (int local_i = 0; local_i < size; local_i++)
    {
      Object object = list.get(local_i);
      if ((object instanceof Namespace))
      {
        Namespace namespace = (Namespace)object;
        if (!defaultNamespaceURI.equals(namespace.getURI())) {
          answer.addLocal(namespace);
        }
      }
    }
    return answer;
  }
  
  public void ensureAttributesCapacity(int minCapacity)
  {
    if (minCapacity > 1)
    {
      List list = attributeList();
      if ((list instanceof ArrayList))
      {
        ArrayList arrayList = (ArrayList)list;
        arrayList.ensureCapacity(minCapacity);
      }
    }
  }
  
  protected Element createElement(String name)
  {
    return getDocumentFactory().createElement(name);
  }
  
  protected Element createElement(QName qName)
  {
    return getDocumentFactory().createElement(qName);
  }
  
  protected void addNode(Node node)
  {
    if (node.getParent() != null)
    {
      String message = "The Node already has an existing parent of \"" + node.getParent().getQualifiedName() + "\"";
      throw new IllegalAddException(this, node, message);
    }
    addNewNode(node);
  }
  
  protected void addNode(int index, Node node)
  {
    if (node.getParent() != null)
    {
      String message = "The Node already has an existing parent of \"" + node.getParent().getQualifiedName() + "\"";
      throw new IllegalAddException(this, node, message);
    }
    addNewNode(index, node);
  }
  
  protected void addNewNode(Node node)
  {
    contentList().add(node);
    childAdded(node);
  }
  
  protected void addNewNode(int index, Node node)
  {
    contentList().add(index, node);
    childAdded(node);
  }
  
  protected boolean removeNode(Node node)
  {
    boolean answer = contentList().remove(node);
    if (answer) {
      childRemoved(node);
    }
    return answer;
  }
  
  protected void childAdded(Node node)
  {
    if (node != null) {
      node.setParent(this);
    }
  }
  
  protected void childRemoved(Node node)
  {
    if (node != null)
    {
      node.setParent(null);
      node.setDocument(null);
    }
  }
  
  protected abstract List attributeList();
  
  protected abstract List attributeList(int paramInt);
  
  protected DocumentFactory getDocumentFactory()
  {
    QName qName = getQName();
    if (qName != null)
    {
      DocumentFactory factory = qName.getDocumentFactory();
      if (factory != null) {
        return factory;
      }
    }
    return DOCUMENT_FACTORY;
  }
  
  protected List createAttributeList()
  {
    return createAttributeList(5);
  }
  
  protected List createAttributeList(int size)
  {
    return new ArrayList(size);
  }
  
  protected Iterator createSingleIterator(Object result)
  {
    return new SingleIterator(result);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.AbstractElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */