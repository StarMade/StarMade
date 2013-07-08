package org.dom4j.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.dom4j.Branch;
import org.dom4j.Comment;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;

public abstract class AbstractBranch
  extends AbstractNode
  implements Branch
{
  protected static final int DEFAULT_CONTENT_LIST_SIZE = 5;
  
  public boolean isReadOnly()
  {
    return false;
  }
  
  public boolean hasContent()
  {
    return nodeCount() > 0;
  }
  
  public List content()
  {
    List backingList = contentList();
    return new ContentListFacade(this, backingList);
  }
  
  public String getText()
  {
    List content = contentList();
    if (content != null)
    {
      int size = content.size();
      if (size >= 1)
      {
        Object first = content.get(0);
        String firstText = getContentAsText(first);
        if (size == 1) {
          return firstText;
        }
        StringBuffer buffer = new StringBuffer(firstText);
        for (int local_i = 1; local_i < size; local_i++)
        {
          Object node = content.get(local_i);
          buffer.append(getContentAsText(node));
        }
        return buffer.toString();
      }
    }
    return "";
  }
  
  protected String getContentAsText(Object content)
  {
    if ((content instanceof Node))
    {
      Node node = (Node)content;
      switch (node.getNodeType())
      {
      case 3: 
      case 4: 
      case 5: 
        return node.getText();
      }
    }
    else if ((content instanceof String))
    {
      return (String)content;
    }
    return "";
  }
  
  protected String getContentAsStringValue(Object content)
  {
    if ((content instanceof Node))
    {
      Node node = (Node)content;
      switch (node.getNodeType())
      {
      case 1: 
      case 3: 
      case 4: 
      case 5: 
        return node.getStringValue();
      }
    }
    else if ((content instanceof String))
    {
      return (String)content;
    }
    return "";
  }
  
  public String getTextTrim()
  {
    String text = getText();
    StringBuffer textContent = new StringBuffer();
    StringTokenizer tokenizer = new StringTokenizer(text);
    while (tokenizer.hasMoreTokens())
    {
      String str = tokenizer.nextToken();
      textContent.append(str);
      if (tokenizer.hasMoreTokens()) {
        textContent.append(" ");
      }
    }
    return textContent.toString();
  }
  
  public void setProcessingInstructions(List listOfPIs)
  {
    Iterator iter = listOfPIs.iterator();
    while (iter.hasNext())
    {
      ProcessingInstruction local_pi = (ProcessingInstruction)iter.next();
      addNode(local_pi);
    }
  }
  
  public Element addElement(String name)
  {
    Element node = getDocumentFactory().createElement(name);
    add(node);
    return node;
  }
  
  public Element addElement(String qualifiedName, String namespaceURI)
  {
    Element node = getDocumentFactory().createElement(qualifiedName, namespaceURI);
    add(node);
    return node;
  }
  
  public Element addElement(QName qname)
  {
    Element node = getDocumentFactory().createElement(qname);
    add(node);
    return node;
  }
  
  public Element addElement(String name, String prefix, String uri)
  {
    Namespace namespace = Namespace.get(prefix, uri);
    QName qName = getDocumentFactory().createQName(name, namespace);
    return addElement(qName);
  }
  
  public void add(Node node)
  {
    switch (node.getNodeType())
    {
    case 1: 
      add((Element)node);
      break;
    case 8: 
      add((Comment)node);
      break;
    case 7: 
      add((ProcessingInstruction)node);
      break;
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
    case 8: 
      return remove((Comment)node);
    case 7: 
      return remove((ProcessingInstruction)node);
    }
    invalidNodeTypeAddException(node);
    return false;
  }
  
  public void add(Comment comment)
  {
    addNode(comment);
  }
  
  public void add(Element element)
  {
    addNode(element);
  }
  
  public void add(ProcessingInstruction local_pi)
  {
    addNode(local_pi);
  }
  
  public boolean remove(Comment comment)
  {
    return removeNode(comment);
  }
  
  public boolean remove(Element element)
  {
    return removeNode(element);
  }
  
  public boolean remove(ProcessingInstruction local_pi)
  {
    return removeNode(local_pi);
  }
  
  public Element elementByID(String elementID)
  {
    int local_i = 0;
    int size = nodeCount();
    while (local_i < size)
    {
      Node node = node(local_i);
      if ((node instanceof Element))
      {
        Element element = (Element)node;
        String local_id = elementID(element);
        if ((local_id != null) && (local_id.equals(elementID))) {
          return element;
        }
        element = element.elementByID(elementID);
        if (element != null) {
          return element;
        }
      }
      local_i++;
    }
    return null;
  }
  
  public void appendContent(Branch branch)
  {
    int local_i = 0;
    int size = branch.nodeCount();
    while (local_i < size)
    {
      Node node = branch.node(local_i);
      add((Node)node.clone());
      local_i++;
    }
  }
  
  public Node node(int index)
  {
    Object object = contentList().get(index);
    if ((object instanceof Node)) {
      return (Node)object;
    }
    if ((object instanceof String)) {
      return getDocumentFactory().createText(object.toString());
    }
    return null;
  }
  
  public int nodeCount()
  {
    return contentList().size();
  }
  
  public int indexOf(Node node)
  {
    return contentList().indexOf(node);
  }
  
  public Iterator nodeIterator()
  {
    return contentList().iterator();
  }
  
  protected String elementID(Element element)
  {
    return element.attributeValue("ID");
  }
  
  protected abstract List contentList();
  
  protected List createContentList()
  {
    return new ArrayList(5);
  }
  
  protected List createContentList(int size)
  {
    return new ArrayList(size);
  }
  
  protected BackedList createResultList()
  {
    return new BackedList(this, contentList());
  }
  
  protected List createSingleResultList(Object result)
  {
    BackedList list = new BackedList(this, contentList(), 1);
    list.addLocal(result);
    return list;
  }
  
  protected List createEmptyList()
  {
    return new BackedList(this, contentList(), 0);
  }
  
  protected abstract void addNode(Node paramNode);
  
  protected abstract void addNode(int paramInt, Node paramNode);
  
  protected abstract boolean removeNode(Node paramNode);
  
  protected abstract void childAdded(Node paramNode);
  
  protected abstract void childRemoved(Node paramNode);
  
  protected void contentRemoved()
  {
    List content = contentList();
    int local_i = 0;
    int size = content.size();
    while (local_i < size)
    {
      Object object = content.get(local_i);
      if ((object instanceof Node)) {
        childRemoved((Node)object);
      }
      local_i++;
    }
  }
  
  protected void invalidNodeTypeAddException(Node node)
  {
    throw new IllegalAddException("Invalid node type. Cannot add node: " + node + " to this branch: " + this);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.tree.AbstractBranch
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */