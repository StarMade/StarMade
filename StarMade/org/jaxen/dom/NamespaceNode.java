package org.jaxen.dom;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public class NamespaceNode
  implements Node
{
  public static final short NAMESPACE_NODE = 13;
  private Node parent;
  private String name;
  private String value;
  private HashMap userData = new HashMap();
  
  public NamespaceNode(Node parent, String name, String value)
  {
    this.parent = parent;
    this.name = (name == null ? "" : name);
    this.value = value;
  }
  
  NamespaceNode(Node parent, Node attribute)
  {
    String attributeName = attribute.getNodeName();
    if (attributeName.equals("xmlns")) {
      this.name = "";
    } else if (attributeName.startsWith("xmlns:")) {
      this.name = attributeName.substring(6);
    } else {
      this.name = attributeName;
    }
    this.parent = parent;
    this.value = attribute.getNodeValue();
  }
  
  public String getNodeName()
  {
    return this.name;
  }
  
  public String getNodeValue()
  {
    return this.value;
  }
  
  public void setNodeValue(String value)
    throws DOMException
  {
    disallowModification();
  }
  
  public short getNodeType()
  {
    return 13;
  }
  
  public Node getParentNode()
  {
    return this.parent;
  }
  
  public NodeList getChildNodes()
  {
    return new EmptyNodeList(null);
  }
  
  public Node getFirstChild()
  {
    return null;
  }
  
  public Node getLastChild()
  {
    return null;
  }
  
  public Node getPreviousSibling()
  {
    return null;
  }
  
  public Node getNextSibling()
  {
    return null;
  }
  
  public NamedNodeMap getAttributes()
  {
    return null;
  }
  
  public Document getOwnerDocument()
  {
    if (this.parent == null) {
      return null;
    }
    return this.parent.getOwnerDocument();
  }
  
  public Node insertBefore(Node newChild, Node refChild)
    throws DOMException
  {
    disallowModification();
    return null;
  }
  
  public Node replaceChild(Node newChild, Node oldChild)
    throws DOMException
  {
    disallowModification();
    return null;
  }
  
  public Node removeChild(Node oldChild)
    throws DOMException
  {
    disallowModification();
    return null;
  }
  
  public Node appendChild(Node newChild)
    throws DOMException
  {
    disallowModification();
    return null;
  }
  
  public boolean hasChildNodes()
  {
    return false;
  }
  
  public Node cloneNode(boolean deep)
  {
    return new NamespaceNode(this.parent, this.name, this.value);
  }
  
  public void normalize() {}
  
  public boolean isSupported(String feature, String version)
  {
    return false;
  }
  
  public String getNamespaceURI()
  {
    return null;
  }
  
  public String getPrefix()
  {
    return null;
  }
  
  public void setPrefix(String prefix)
    throws DOMException
  {
    disallowModification();
  }
  
  public String getLocalName()
  {
    return this.name;
  }
  
  public boolean hasAttributes()
  {
    return false;
  }
  
  private void disallowModification()
    throws DOMException
  {
    throw new DOMException((short)7, "Namespace node may not be modified");
  }
  
  public int hashCode()
  {
    return hashCode(this.parent) + hashCode(this.name) + hashCode(this.value);
  }
  
  public boolean equals(Object local_o)
  {
    if (local_o == this) {
      return true;
    }
    if (local_o == null) {
      return false;
    }
    if ((local_o instanceof NamespaceNode))
    {
      NamespaceNode local_ns = (NamespaceNode)local_o;
      return (equals(this.parent, local_ns.getParentNode())) && (equals(this.name, local_ns.getNodeName())) && (equals(this.value, local_ns.getNodeValue()));
    }
    return false;
  }
  
  private int hashCode(Object local_o)
  {
    return local_o == null ? 0 : local_o.hashCode();
  }
  
  private boolean equals(Object local_a, Object local_b)
  {
    return ((local_a == null) && (local_b == null)) || ((local_a != null) && (local_a.equals(local_b)));
  }
  
  public String getBaseURI()
  {
    Class clazz = Node.class;
    try
    {
      Class[] args = new Class[0];
      Method getBaseURI = clazz.getMethod("getBaseURI", args);
      String base = (String)getBaseURI.invoke(getParentNode(), args);
      return base;
    }
    catch (Exception args) {}
    return null;
  }
  
  public short compareDocumentPosition(Node other)
    throws DOMException
  {
    DOMException local_ex = new DOMException((short)9, "DOM level 3 interfaces are not fully implemented in Jaxen's NamespaceNode class");
    throw local_ex;
  }
  
  public String getTextContent()
  {
    return this.value;
  }
  
  public void setTextContent(String textContent)
    throws DOMException
  {
    disallowModification();
  }
  
  public boolean isSameNode(Node other)
  {
    boolean local_a = isEqualNode(other);
    Node thisParent = getParentNode();
    Node thatParent = other.getParentNode();
    boolean local_b;
    try
    {
      Class clazz = Node.class;
      Class[] args = { clazz };
      Method isEqual = clazz.getMethod("isEqual", args);
      Object[] args2 = new Object[1];
      args2[0] = thatParent;
      Boolean result = (Boolean)isEqual.invoke(thisParent, args2);
      local_b = result.booleanValue();
    }
    catch (NoSuchMethodException clazz)
    {
      local_b = thisParent.equals(thatParent);
    }
    catch (InvocationTargetException clazz)
    {
      local_b = thisParent.equals(thatParent);
    }
    catch (IllegalAccessException clazz)
    {
      local_b = thisParent.equals(thatParent);
    }
    return (local_a) && (local_b);
  }
  
  public String lookupPrefix(String namespaceURI)
  {
    try
    {
      Class clazz = Node.class;
      Class[] argTypes = { String.class };
      Method lookupPrefix = clazz.getMethod("lookupPrefix", argTypes);
      String[] args = { namespaceURI };
      String result = (String)lookupPrefix.invoke(this.parent, args);
      return result;
    }
    catch (NoSuchMethodException clazz)
    {
      throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
    }
    catch (InvocationTargetException clazz)
    {
      throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
    }
    catch (IllegalAccessException clazz)
    {
      throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
    }
  }
  
  public boolean isDefaultNamespace(String namespaceURI)
  {
    return namespaceURI.equals(lookupNamespaceURI(null));
  }
  
  public String lookupNamespaceURI(String prefix)
  {
    try
    {
      Class clazz = Node.class;
      Class[] argTypes = { String.class };
      Method lookupNamespaceURI = clazz.getMethod("lookupNamespaceURI", argTypes);
      String[] args = { prefix };
      String result = (String)lookupNamespaceURI.invoke(this.parent, args);
      return result;
    }
    catch (NoSuchMethodException clazz)
    {
      throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
    }
    catch (InvocationTargetException clazz)
    {
      throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
    }
    catch (IllegalAccessException clazz)
    {
      throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
    }
  }
  
  public boolean isEqualNode(Node arg)
  {
    if (arg.getNodeType() == getNodeType())
    {
      NamespaceNode other = (NamespaceNode)arg;
      if ((other.name == null) && (this.name != null)) {
        return false;
      }
      if ((other.name != null) && (this.name == null)) {
        return false;
      }
      if ((other.value == null) && (this.value != null)) {
        return false;
      }
      if ((other.value != null) && (this.value == null)) {
        return false;
      }
      if ((other.name == null) && (this.name == null)) {
        return other.value.equals(this.value);
      }
      return (other.name.equals(this.name)) && (other.value.equals(this.value));
    }
    return false;
  }
  
  public Object getFeature(String feature, String version)
  {
    return null;
  }
  
  public Object setUserData(String key, Object data, UserDataHandler handler)
  {
    Object oldValue = getUserData(key);
    this.userData.put(key, data);
    return oldValue;
  }
  
  public Object getUserData(String key)
  {
    return this.userData.get(key);
  }
  
  private static class EmptyNodeList
    implements NodeList
  {
    private EmptyNodeList() {}
    
    public int getLength()
    {
      return 0;
    }
    
    public Node item(int index)
    {
      return null;
    }
    
    EmptyNodeList(NamespaceNode.1 local_x0)
    {
      this();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.dom.NamespaceNode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */