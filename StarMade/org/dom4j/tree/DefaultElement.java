package org.dom4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;

public class DefaultElement
  extends AbstractElement
{
  private static final transient DocumentFactory DOCUMENT_FACTORY = ;
  private QName qname;
  private Branch parentBranch;
  private Object content;
  private Object attributes;
  
  public DefaultElement(String name)
  {
    this.qname = DOCUMENT_FACTORY.createQName(name);
  }
  
  public DefaultElement(QName qname)
  {
    this.qname = qname;
  }
  
  public DefaultElement(QName qname, int attributeCount)
  {
    this.qname = qname;
    if (attributeCount > 1) {
      this.attributes = new ArrayList(attributeCount);
    }
  }
  
  public DefaultElement(String name, Namespace namespace)
  {
    this.qname = DOCUMENT_FACTORY.createQName(name, namespace);
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
  
  public String getText()
  {
    Object contentShadow = this.content;
    if ((contentShadow instanceof List)) {
      return super.getText();
    }
    if (contentShadow != null) {
      return getContentAsText(contentShadow);
    }
    return "";
  }
  
  public String getStringValue()
  {
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
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
    }
    else if (contentShadow != null)
    {
      return getContentAsStringValue(contentShadow);
    }
    return "";
  }
  
  public Object clone()
  {
    DefaultElement answer = (DefaultElement)super.clone();
    if (answer != this)
    {
      answer.content = null;
      answer.attributes = null;
      answer.appendAttributes(this);
      answer.appendContent(this);
    }
    return answer;
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
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
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
    }
    else if ((contentShadow instanceof Namespace))
    {
      Namespace list = (Namespace)contentShadow;
      if (prefix.equals(list.getPrefix())) {
        return list;
      }
    }
    Element contentShadow = getParent();
    if (contentShadow != null)
    {
      Namespace list = contentShadow.getNamespaceForPrefix(prefix);
      if (list != null) {
        return list;
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
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
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
    }
    else if ((contentShadow instanceof Namespace))
    {
      Namespace list = (Namespace)contentShadow;
      if (uri.equals(list.getURI())) {
        return list;
      }
    }
    Element list = getParent();
    if (list != null) {
      return list.getNamespaceForURI(uri);
    }
    return null;
  }
  
  public List declaredNamespaces()
  {
    BackedList answer = createResultList();
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
      int size = list.size();
      for (int local_i = 0; local_i < size; local_i++)
      {
        Object object = list.get(local_i);
        if ((object instanceof Namespace)) {
          answer.addLocal(object);
        }
      }
    }
    else if ((contentShadow instanceof Namespace))
    {
      answer.addLocal(contentShadow);
    }
    return answer;
  }
  
  public List additionalNamespaces()
  {
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
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
    if ((contentShadow instanceof Namespace))
    {
      Namespace list = (Namespace)contentShadow;
      if (list.equals(getNamespace())) {
        return createEmptyList();
      }
      return createSingleResultList(list);
    }
    return createEmptyList();
  }
  
  public List additionalNamespaces(String defaultNamespaceURI)
  {
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
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
    if ((contentShadow instanceof Namespace))
    {
      Namespace list = (Namespace)contentShadow;
      if (!defaultNamespaceURI.equals(list.getURI())) {
        return createSingleResultList(list);
      }
    }
    return createEmptyList();
  }
  
  public List processingInstructions()
  {
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
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
    if ((contentShadow instanceof ProcessingInstruction)) {
      return createSingleResultList(contentShadow);
    }
    return createEmptyList();
  }
  
  public List processingInstructions(String target)
  {
    Object shadow = this.content;
    if ((shadow instanceof List))
    {
      List list = (List)shadow;
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
    if ((shadow instanceof ProcessingInstruction))
    {
      ProcessingInstruction list = (ProcessingInstruction)shadow;
      if (target.equals(list.getName())) {
        return createSingleResultList(list);
      }
    }
    return createEmptyList();
  }
  
  public ProcessingInstruction processingInstruction(String target)
  {
    Object shadow = this.content;
    if ((shadow instanceof List))
    {
      List list = (List)shadow;
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
    }
    else if ((shadow instanceof ProcessingInstruction))
    {
      ProcessingInstruction list = (ProcessingInstruction)shadow;
      if (target.equals(list.getName())) {
        return list;
      }
    }
    return null;
  }
  
  public boolean removeProcessingInstruction(String target)
  {
    Object shadow = this.content;
    if ((shadow instanceof List))
    {
      List list = (List)shadow;
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
    }
    else if ((shadow instanceof ProcessingInstruction))
    {
      ProcessingInstruction list = (ProcessingInstruction)shadow;
      if (target.equals(list.getName()))
      {
        this.content = null;
        return true;
      }
    }
    return false;
  }
  
  public Element element(String name)
  {
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
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
    }
    else if ((contentShadow instanceof Element))
    {
      Element list = (Element)contentShadow;
      if (name.equals(list.getName())) {
        return list;
      }
    }
    return null;
  }
  
  public Element element(QName qName)
  {
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
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
    }
    else if ((contentShadow instanceof Element))
    {
      Element list = (Element)contentShadow;
      if (qName.equals(list.getQName())) {
        return list;
      }
    }
    return null;
  }
  
  public Element element(String name, Namespace namespace)
  {
    return element(getDocumentFactory().createQName(name, namespace));
  }
  
  public void setContent(List content)
  {
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
          Element parent = node.getParent();
          if ((parent != null) && (parent != this)) {
            node = (Node)node.clone();
          }
          newContent.add(node);
          childAdded(node);
        }
        else if (object != null)
        {
          String node = object.toString();
          Node parent = getDocumentFactory().createText(node);
          newContent.add(parent);
          childAdded(parent);
        }
      }
      this.content = newContent;
    }
  }
  
  public void clearContent()
  {
    if (this.content != null)
    {
      contentRemoved();
      this.content = null;
    }
  }
  
  public Node node(int index)
  {
    if (index >= 0)
    {
      Object contentShadow = this.content;
      Object node;
      Object node;
      if ((contentShadow instanceof List))
      {
        List list = (List)contentShadow;
        if (index >= list.size()) {
          return null;
        }
        node = list.get(index);
      }
      else
      {
        node = index == 0 ? contentShadow : null;
      }
      if (node != null)
      {
        if ((node instanceof Node)) {
          return (Node)node;
        }
        return new DefaultText(node.toString());
      }
    }
    return null;
  }
  
  public int indexOf(Node node)
  {
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
      return list.indexOf(node);
    }
    if ((contentShadow != null) && (contentShadow.equals(node))) {
      return 0;
    }
    return -1;
  }
  
  public int nodeCount()
  {
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
      return list.size();
    }
    return contentShadow != null ? 1 : 0;
  }
  
  public Iterator nodeIterator()
  {
    Object contentShadow = this.content;
    if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
      return list.iterator();
    }
    if (contentShadow != null) {
      return createSingleIterator(contentShadow);
    }
    return EMPTY_ITERATOR;
  }
  
  public List attributes()
  {
    return new ContentListFacade(this, attributeList());
  }
  
  public void setAttributes(List attributes)
  {
    if ((attributes instanceof ContentListFacade)) {
      attributes = ((ContentListFacade)attributes).getBackingList();
    }
    this.attributes = attributes;
  }
  
  public Iterator attributeIterator()
  {
    Object attributesShadow = this.attributes;
    if ((attributesShadow instanceof List))
    {
      List list = (List)attributesShadow;
      return list.iterator();
    }
    if (attributesShadow != null) {
      return createSingleIterator(attributesShadow);
    }
    return EMPTY_ITERATOR;
  }
  
  public Attribute attribute(int index)
  {
    Object attributesShadow = this.attributes;
    if ((attributesShadow instanceof List))
    {
      List list = (List)attributesShadow;
      return (Attribute)list.get(index);
    }
    if ((attributesShadow != null) && (index == 0)) {
      return (Attribute)attributesShadow;
    }
    return null;
  }
  
  public int attributeCount()
  {
    Object attributesShadow = this.attributes;
    if ((attributesShadow instanceof List))
    {
      List list = (List)attributesShadow;
      return list.size();
    }
    return attributesShadow != null ? 1 : 0;
  }
  
  public Attribute attribute(String name)
  {
    Object attributesShadow = this.attributes;
    if ((attributesShadow instanceof List))
    {
      List list = (List)attributesShadow;
      int size = list.size();
      for (int local_i = 0; local_i < size; local_i++)
      {
        Attribute attribute = (Attribute)list.get(local_i);
        if (name.equals(attribute.getName())) {
          return attribute;
        }
      }
    }
    else if (attributesShadow != null)
    {
      Attribute list = (Attribute)attributesShadow;
      if (name.equals(list.getName())) {
        return list;
      }
    }
    return null;
  }
  
  public Attribute attribute(QName qName)
  {
    Object attributesShadow = this.attributes;
    if ((attributesShadow instanceof List))
    {
      List list = (List)attributesShadow;
      int size = list.size();
      for (int local_i = 0; local_i < size; local_i++)
      {
        Attribute attribute = (Attribute)list.get(local_i);
        if (qName.equals(attribute.getQName())) {
          return attribute;
        }
      }
    }
    else if (attributesShadow != null)
    {
      Attribute list = (Attribute)attributesShadow;
      if (qName.equals(list.getQName())) {
        return list;
      }
    }
    return null;
  }
  
  public Attribute attribute(String name, Namespace namespace)
  {
    return attribute(getDocumentFactory().createQName(name, namespace));
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
      if (this.attributes == null) {
        this.attributes = attribute;
      } else {
        attributeList().add(attribute);
      }
      childAdded(attribute);
    }
  }
  
  public boolean remove(Attribute attribute)
  {
    boolean answer = false;
    Object attributesShadow = this.attributes;
    if ((attributesShadow instanceof List))
    {
      List list = (List)attributesShadow;
      answer = list.remove(attribute);
      if (!answer)
      {
        Attribute copy = attribute(attribute.getQName());
        if (copy != null)
        {
          list.remove(copy);
          answer = true;
        }
      }
    }
    else if (attributesShadow != null)
    {
      if (attribute.equals(attributesShadow))
      {
        this.attributes = null;
        answer = true;
      }
      else
      {
        Attribute list = (Attribute)attributesShadow;
        if (attribute.getQName().equals(list.getQName()))
        {
          this.attributes = null;
          answer = true;
        }
      }
    }
    if (answer) {
      childRemoved(attribute);
    }
    return answer;
  }
  
  protected void addNewNode(Node node)
  {
    Object contentShadow = this.content;
    if (contentShadow == null)
    {
      this.content = node;
    }
    else if ((contentShadow instanceof List))
    {
      List list = (List)contentShadow;
      list.add(node);
    }
    else
    {
      List list = createContentList();
      list.add(contentShadow);
      list.add(node);
      this.content = list;
    }
    childAdded(node);
  }
  
  protected boolean removeNode(Node node)
  {
    boolean answer = false;
    Object contentShadow = this.content;
    if (contentShadow != null) {
      if (contentShadow == node)
      {
        this.content = null;
        answer = true;
      }
      else if ((contentShadow instanceof List))
      {
        List list = (List)contentShadow;
        answer = list.remove(node);
      }
    }
    if (answer) {
      childRemoved(node);
    }
    return answer;
  }
  
  protected List contentList()
  {
    Object contentShadow = this.content;
    if ((contentShadow instanceof List)) {
      return (List)contentShadow;
    }
    List list = createContentList();
    if (contentShadow != null) {
      list.add(contentShadow);
    }
    this.content = list;
    return list;
  }
  
  protected List attributeList()
  {
    Object attributesShadow = this.attributes;
    if ((attributesShadow instanceof List)) {
      return (List)attributesShadow;
    }
    if (attributesShadow != null)
    {
      List list = createAttributeList();
      list.add(attributesShadow);
      this.attributes = list;
      return list;
    }
    List list = createAttributeList();
    this.attributes = list;
    return list;
  }
  
  protected List attributeList(int size)
  {
    Object attributesShadow = this.attributes;
    if ((attributesShadow instanceof List)) {
      return (List)attributesShadow;
    }
    if (attributesShadow != null)
    {
      List list = createAttributeList(size);
      list.add(attributesShadow);
      this.attributes = list;
      return list;
    }
    List list = createAttributeList(size);
    this.attributes = list;
    return list;
  }
  
  protected void setAttributeList(List attributeList)
  {
    this.attributes = attributeList;
  }
  
  protected DocumentFactory getDocumentFactory()
  {
    DocumentFactory factory = this.qname.getDocumentFactory();
    return factory != null ? factory : DOCUMENT_FACTORY;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.DefaultElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */