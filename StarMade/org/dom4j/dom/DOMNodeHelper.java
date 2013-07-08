package org.dom4j.dom;

import java.io.PrintStream;
import java.util.List;
import org.dom4j.Branch;
import org.dom4j.CharacterData;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DOMNodeHelper
{
  public static final NodeList EMPTY_NODE_LIST = new EmptyNodeList();
  
  public static boolean supports(org.dom4j.Node node, String feature, String version)
  {
    return false;
  }
  
  public static String getNamespaceURI(org.dom4j.Node node)
  {
    return null;
  }
  
  public static String getPrefix(org.dom4j.Node node)
  {
    return null;
  }
  
  public static String getLocalName(org.dom4j.Node node)
  {
    return null;
  }
  
  public static void setPrefix(org.dom4j.Node node, String prefix)
    throws DOMException
  {}
  
  public static String getNodeValue(org.dom4j.Node node)
    throws DOMException
  {
    return node.getText();
  }
  
  public static void setNodeValue(org.dom4j.Node node, String nodeValue)
    throws DOMException
  {
    node.setText(nodeValue);
  }
  
  public static org.w3c.dom.Node getParentNode(org.dom4j.Node node)
  {
    return asDOMNode(node.getParent());
  }
  
  public static NodeList getChildNodes(org.dom4j.Node node)
  {
    return EMPTY_NODE_LIST;
  }
  
  public static org.w3c.dom.Node getFirstChild(org.dom4j.Node node)
  {
    return null;
  }
  
  public static org.w3c.dom.Node getLastChild(org.dom4j.Node node)
  {
    return null;
  }
  
  public static org.w3c.dom.Node getPreviousSibling(org.dom4j.Node node)
  {
    org.dom4j.Element parent = node.getParent();
    if (parent != null)
    {
      int index = parent.indexOf(node);
      if (index > 0)
      {
        org.dom4j.Node previous = parent.node(index - 1);
        return asDOMNode(previous);
      }
    }
    return null;
  }
  
  public static org.w3c.dom.Node getNextSibling(org.dom4j.Node node)
  {
    org.dom4j.Element parent = node.getParent();
    if (parent != null)
    {
      int index = parent.indexOf(node);
      if (index >= 0)
      {
        index++;
        if (index < parent.nodeCount())
        {
          org.dom4j.Node next = parent.node(index);
          return asDOMNode(next);
        }
      }
    }
    return null;
  }
  
  public static NamedNodeMap getAttributes(org.dom4j.Node node)
  {
    return null;
  }
  
  public static org.w3c.dom.Document getOwnerDocument(org.dom4j.Node node)
  {
    return asDOMDocument(node.getDocument());
  }
  
  public static org.w3c.dom.Node insertBefore(org.dom4j.Node node, org.w3c.dom.Node newChild, org.w3c.dom.Node refChild)
    throws DOMException
  {
    if ((node instanceof Branch))
    {
      Branch branch = (Branch)node;
      List list = branch.content();
      int index = list.indexOf(refChild);
      if (index < 0) {
        branch.add((org.dom4j.Node)newChild);
      } else {
        list.add(index, newChild);
      }
      return newChild;
    }
    throw new DOMException((short)3, "Children not allowed for this node: " + node);
  }
  
  public static org.w3c.dom.Node replaceChild(org.dom4j.Node node, org.w3c.dom.Node newChild, org.w3c.dom.Node oldChild)
    throws DOMException
  {
    if ((node instanceof Branch))
    {
      Branch branch = (Branch)node;
      List list = branch.content();
      int index = list.indexOf(oldChild);
      if (index < 0) {
        throw new DOMException((short)8, "Tried to replace a non existing child for node: " + node);
      }
      list.set(index, newChild);
      return oldChild;
    }
    throw new DOMException((short)3, "Children not allowed for this node: " + node);
  }
  
  public static org.w3c.dom.Node removeChild(org.dom4j.Node node, org.w3c.dom.Node oldChild)
    throws DOMException
  {
    if ((node instanceof Branch))
    {
      Branch branch = (Branch)node;
      branch.remove((org.dom4j.Node)oldChild);
      return oldChild;
    }
    throw new DOMException((short)3, "Children not allowed for this node: " + node);
  }
  
  public static org.w3c.dom.Node appendChild(org.dom4j.Node node, org.w3c.dom.Node newChild)
    throws DOMException
  {
    if ((node instanceof Branch))
    {
      Branch branch = (Branch)node;
      org.w3c.dom.Node previousParent = newChild.getParentNode();
      if (previousParent != null) {
        previousParent.removeChild(newChild);
      }
      branch.add((org.dom4j.Node)newChild);
      return newChild;
    }
    throw new DOMException((short)3, "Children not allowed for this node: " + node);
  }
  
  public static boolean hasChildNodes(org.dom4j.Node node)
  {
    return false;
  }
  
  public static org.w3c.dom.Node cloneNode(org.dom4j.Node node, boolean deep)
  {
    return asDOMNode((org.dom4j.Node)node.clone());
  }
  
  public static void normalize(org.dom4j.Node node) {}
  
  public static boolean isSupported(org.dom4j.Node local_n, String feature, String version)
  {
    return false;
  }
  
  public static boolean hasAttributes(org.dom4j.Node node)
  {
    if ((node != null) && ((node instanceof org.dom4j.Element))) {
      return ((org.dom4j.Element)node).attributeCount() > 0;
    }
    return false;
  }
  
  public static String getData(CharacterData charData)
    throws DOMException
  {
    return charData.getText();
  }
  
  public static void setData(CharacterData charData, String data)
    throws DOMException
  {
    charData.setText(data);
  }
  
  public static int getLength(CharacterData charData)
  {
    String text = charData.getText();
    return text != null ? text.length() : 0;
  }
  
  public static String substringData(CharacterData charData, int offset, int count)
    throws DOMException
  {
    if (count < 0) {
      throw new DOMException((short)1, "Illegal value for count: " + count);
    }
    String text = charData.getText();
    int length = text != null ? text.length() : 0;
    if ((offset < 0) || (offset >= length)) {
      throw new DOMException((short)1, "No text at offset: " + offset);
    }
    if (offset + count > length) {
      return text.substring(offset);
    }
    return text.substring(offset, offset + count);
  }
  
  public static void appendData(CharacterData charData, String arg)
    throws DOMException
  {
    if (charData.isReadOnly()) {
      throw new DOMException((short)7, "CharacterData node is read only: " + charData);
    }
    String text = charData.getText();
    if (text == null) {
      charData.setText(text);
    } else {
      charData.setText(text + arg);
    }
  }
  
  public static void insertData(CharacterData data, int offset, String arg)
    throws DOMException
  {
    if (data.isReadOnly()) {
      throw new DOMException((short)7, "CharacterData node is read only: " + data);
    }
    String text = data.getText();
    if (text == null)
    {
      data.setText(arg);
    }
    else
    {
      int length = text.length();
      if ((offset < 0) || (offset > length)) {
        throw new DOMException((short)1, "No text at offset: " + offset);
      }
      StringBuffer buffer = new StringBuffer(text);
      buffer.insert(offset, arg);
      data.setText(buffer.toString());
    }
  }
  
  public static void deleteData(CharacterData charData, int offset, int count)
    throws DOMException
  {
    if (charData.isReadOnly()) {
      throw new DOMException((short)7, "CharacterData node is read only: " + charData);
    }
    if (count < 0) {
      throw new DOMException((short)1, "Illegal value for count: " + count);
    }
    String text = charData.getText();
    if (text != null)
    {
      int length = text.length();
      if ((offset < 0) || (offset >= length)) {
        throw new DOMException((short)1, "No text at offset: " + offset);
      }
      StringBuffer buffer = new StringBuffer(text);
      buffer.delete(offset, offset + count);
      charData.setText(buffer.toString());
    }
  }
  
  public static void replaceData(CharacterData charData, int offset, int count, String arg)
    throws DOMException
  {
    if (charData.isReadOnly()) {
      throw new DOMException((short)7, "CharacterData node is read only: " + charData);
    }
    if (count < 0) {
      throw new DOMException((short)1, "Illegal value for count: " + count);
    }
    String text = charData.getText();
    if (text != null)
    {
      int length = text.length();
      if ((offset < 0) || (offset >= length)) {
        throw new DOMException((short)1, "No text at offset: " + offset);
      }
      StringBuffer buffer = new StringBuffer(text);
      buffer.replace(offset, offset + count, arg);
      charData.setText(buffer.toString());
    }
  }
  
  public static void appendElementsByTagName(List list, Branch parent, String name)
  {
    boolean isStar = "*".equals(name);
    int local_i = 0;
    int size = parent.nodeCount();
    while (local_i < size)
    {
      org.dom4j.Node node = parent.node(local_i);
      if ((node instanceof org.dom4j.Element))
      {
        org.dom4j.Element element = (org.dom4j.Element)node;
        if ((isStar) || (name.equals(element.getName()))) {
          list.add(element);
        }
        appendElementsByTagName(list, element, name);
      }
      local_i++;
    }
  }
  
  public static void appendElementsByTagNameNS(List list, Branch parent, String namespace, String localName)
  {
    boolean isStarNS = "*".equals(namespace);
    boolean isStar = "*".equals(localName);
    int local_i = 0;
    int size = parent.nodeCount();
    while (local_i < size)
    {
      org.dom4j.Node node = parent.node(local_i);
      if ((node instanceof org.dom4j.Element))
      {
        org.dom4j.Element element = (org.dom4j.Element)node;
        if (((isStarNS) || (((namespace != null) && (namespace.length() != 0)) || ((element.getNamespaceURI() == null) || (element.getNamespaceURI().length() == 0) || ((namespace != null) && (namespace.equals(element.getNamespaceURI())))))) && ((isStar) || (localName.equals(element.getName())))) {
          list.add(element);
        }
        appendElementsByTagNameNS(list, element, namespace, localName);
      }
      local_i++;
    }
  }
  
  public static NodeList createNodeList(List list)
  {
    new NodeList()
    {
      private final List val$list;
      
      public org.w3c.dom.Node item(int index)
      {
        if (index >= getLength()) {
          return null;
        }
        return DOMNodeHelper.asDOMNode((org.dom4j.Node)this.val$list.get(index));
      }
      
      public int getLength()
      {
        return this.val$list.size();
      }
    };
  }
  
  public static org.w3c.dom.Node asDOMNode(org.dom4j.Node node)
  {
    if (node == null) {
      return null;
    }
    if ((node instanceof org.w3c.dom.Node)) {
      return (org.w3c.dom.Node)node;
    }
    System.out.println("Cannot convert: " + node + " into a W3C DOM Node");
    notSupported();
    return null;
  }
  
  public static org.w3c.dom.Document asDOMDocument(org.dom4j.Document document)
  {
    if (document == null) {
      return null;
    }
    if ((document instanceof org.w3c.dom.Document)) {
      return (org.w3c.dom.Document)document;
    }
    notSupported();
    return null;
  }
  
  public static org.w3c.dom.DocumentType asDOMDocumentType(org.dom4j.DocumentType local_dt)
  {
    if (local_dt == null) {
      return null;
    }
    if ((local_dt instanceof org.w3c.dom.DocumentType)) {
      return (org.w3c.dom.DocumentType)local_dt;
    }
    notSupported();
    return null;
  }
  
  public static Text asDOMText(CharacterData text)
  {
    if (text == null) {
      return null;
    }
    if ((text instanceof Text)) {
      return (Text)text;
    }
    notSupported();
    return null;
  }
  
  public static org.w3c.dom.Element asDOMElement(org.dom4j.Node element)
  {
    if (element == null) {
      return null;
    }
    if ((element instanceof org.w3c.dom.Element)) {
      return (org.w3c.dom.Element)element;
    }
    notSupported();
    return null;
  }
  
  public static Attr asDOMAttr(org.dom4j.Node attribute)
  {
    if (attribute == null) {
      return null;
    }
    if ((attribute instanceof Attr)) {
      return (Attr)attribute;
    }
    notSupported();
    return null;
  }
  
  public static void notSupported()
  {
    throw new DOMException((short)9, "Not supported yet");
  }
  
  public static class EmptyNodeList
    implements NodeList
  {
    public org.w3c.dom.Node item(int index)
    {
      return null;
    }
    
    public int getLength()
    {
      return 0;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.dom.DOMNodeHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */