/*     */ package org.jaxen.dom;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.HashMap;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.UserDataHandler;
/*     */ 
/*     */ public class NamespaceNode
/*     */   implements Node
/*     */ {
/*     */   public static final short NAMESPACE_NODE = 13;
/*     */   private Node parent;
/*     */   private String name;
/*     */   private String value;
/* 835 */   private HashMap userData = new HashMap();
/*     */ 
/*     */   public NamespaceNode(Node parent, String name, String value)
/*     */   {
/* 130 */     this.parent = parent;
/* 131 */     this.name = (name == null ? "" : name);
/* 132 */     this.value = value;
/*     */   }
/*     */ 
/*     */   NamespaceNode(Node parent, Node attribute)
/*     */   {
/* 145 */     String attributeName = attribute.getNodeName();
/*     */ 
/* 147 */     if (attributeName.equals("xmlns")) {
/* 148 */       this.name = "";
/*     */     }
/* 150 */     else if (attributeName.startsWith("xmlns:")) {
/* 151 */       this.name = attributeName.substring(6);
/*     */     }
/*     */     else {
/* 154 */       this.name = attributeName;
/*     */     }
/* 156 */     this.parent = parent;
/* 157 */     this.value = attribute.getNodeValue();
/*     */   }
/*     */ 
/*     */   public String getNodeName()
/*     */   {
/* 174 */     return this.name;
/*     */   }
/*     */ 
/*     */   public String getNodeValue()
/*     */   {
/* 185 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setNodeValue(String value)
/*     */     throws DOMException
/*     */   {
/* 197 */     disallowModification();
/*     */   }
/*     */ 
/*     */   public short getNodeType()
/*     */   {
/* 208 */     return 13;
/*     */   }
/*     */ 
/*     */   public Node getParentNode()
/*     */   {
/* 223 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public NodeList getChildNodes()
/*     */   {
/* 234 */     return new EmptyNodeList(null);
/*     */   }
/*     */ 
/*     */   public Node getFirstChild()
/*     */   {
/* 245 */     return null;
/*     */   }
/*     */ 
/*     */   public Node getLastChild()
/*     */   {
/* 256 */     return null;
/*     */   }
/*     */ 
/*     */   public Node getPreviousSibling()
/*     */   {
/* 267 */     return null;
/*     */   }
/*     */ 
/*     */   public Node getNextSibling()
/*     */   {
/* 278 */     return null;
/*     */   }
/*     */ 
/*     */   public NamedNodeMap getAttributes()
/*     */   {
/* 289 */     return null;
/*     */   }
/*     */ 
/*     */   public Document getOwnerDocument()
/*     */   {
/* 300 */     if (this.parent == null) return null;
/* 301 */     return this.parent.getOwnerDocument();
/*     */   }
/*     */ 
/*     */   public Node insertBefore(Node newChild, Node refChild)
/*     */     throws DOMException
/*     */   {
/* 317 */     disallowModification();
/* 318 */     return null;
/*     */   }
/*     */ 
/*     */   public Node replaceChild(Node newChild, Node oldChild)
/*     */     throws DOMException
/*     */   {
/* 333 */     disallowModification();
/* 334 */     return null;
/*     */   }
/*     */ 
/*     */   public Node removeChild(Node oldChild)
/*     */     throws DOMException
/*     */   {
/* 348 */     disallowModification();
/* 349 */     return null;
/*     */   }
/*     */ 
/*     */   public Node appendChild(Node newChild)
/*     */     throws DOMException
/*     */   {
/* 363 */     disallowModification();
/* 364 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean hasChildNodes()
/*     */   {
/* 375 */     return false;
/*     */   }
/*     */ 
/*     */   public Node cloneNode(boolean deep)
/*     */   {
/* 388 */     return new NamespaceNode(this.parent, this.name, this.value);
/*     */   }
/*     */ 
/*     */   public void normalize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean isSupported(String feature, String version)
/*     */   {
/* 413 */     return false;
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI()
/*     */   {
/* 427 */     return null;
/*     */   }
/*     */ 
/*     */   public String getPrefix()
/*     */   {
/* 442 */     return null;
/*     */   }
/*     */ 
/*     */   public void setPrefix(String prefix)
/*     */     throws DOMException
/*     */   {
/* 455 */     disallowModification();
/*     */   }
/*     */ 
/*     */   public String getLocalName()
/*     */   {
/* 467 */     return this.name;
/*     */   }
/*     */ 
/*     */   public boolean hasAttributes()
/*     */   {
/* 478 */     return false;
/*     */   }
/*     */ 
/*     */   private void disallowModification()
/*     */     throws DOMException
/*     */   {
/* 489 */     throw new DOMException((short)7, "Namespace node may not be modified");
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 507 */     return hashCode(this.parent) + hashCode(this.name) + hashCode(this.value);
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 523 */     if (o == this) return true;
/* 524 */     if (o == null) return false;
/* 525 */     if ((o instanceof NamespaceNode)) {
/* 526 */       NamespaceNode ns = (NamespaceNode)o;
/* 527 */       return (equals(this.parent, ns.getParentNode())) && (equals(this.name, ns.getNodeName())) && (equals(this.value, ns.getNodeValue()));
/*     */     }
/*     */ 
/* 531 */     return false;
/*     */   }
/*     */ 
/*     */   private int hashCode(Object o)
/*     */   {
/* 545 */     return o == null ? 0 : o.hashCode();
/*     */   }
/*     */ 
/*     */   private boolean equals(Object a, Object b)
/*     */   {
/* 559 */     return ((a == null) && (b == null)) || ((a != null) && (a.equals(b)));
/*     */   }
/*     */ 
/*     */   public String getBaseURI()
/*     */   {
/* 619 */     Class clazz = Node.class;
/*     */     try {
/* 621 */       Class[] args = new Class[0];
/* 622 */       Method getBaseURI = clazz.getMethod("getBaseURI", args);
/* 623 */       return (String)getBaseURI.invoke(getParentNode(), args);
/*     */     }
/*     */     catch (Exception ex) {
/*     */     }
/* 627 */     return null;
/*     */   }
/*     */ 
/*     */   public short compareDocumentPosition(Node other)
/*     */     throws DOMException
/*     */   {
/* 642 */     DOMException ex = new DOMException((short)9, "DOM level 3 interfaces are not fully implemented in Jaxen's NamespaceNode class");
/*     */ 
/* 646 */     throw ex;
/*     */   }
/*     */ 
/*     */   public String getTextContent()
/*     */   {
/* 657 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setTextContent(String textContent)
/*     */     throws DOMException
/*     */   {
/* 669 */     disallowModification();
/*     */   }
/*     */ 
/*     */   public boolean isSameNode(Node other)
/*     */   {
/* 683 */     boolean a = isEqualNode(other);
/*     */ 
/* 690 */     Node thisParent = getParentNode();
/* 691 */     Node thatParent = other.getParentNode();
/*     */     boolean b;
/*     */     try
/*     */     {
/* 693 */       Class clazz = Node.class;
/* 694 */       Class[] args = { clazz };
/* 695 */       Method isEqual = clazz.getMethod("isEqual", args);
/* 696 */       Object[] args2 = new Object[1];
/* 697 */       args2[0] = thatParent;
/* 698 */       Boolean result = (Boolean)isEqual.invoke(thisParent, args2);
/* 699 */       b = result.booleanValue();
/*     */     }
/*     */     catch (NoSuchMethodException ex) {
/* 702 */       b = thisParent.equals(thatParent);
/*     */     }
/*     */     catch (InvocationTargetException ex) {
/* 705 */       b = thisParent.equals(thatParent);
/*     */     }
/*     */     catch (IllegalAccessException ex) {
/* 708 */       b = thisParent.equals(thatParent);
/*     */     }
/*     */ 
/* 711 */     return (a) && (b);
/*     */   }
/*     */ 
/*     */   public String lookupPrefix(String namespaceURI)
/*     */   {
/*     */     try
/*     */     {
/* 731 */       Class clazz = Node.class;
/* 732 */       Class[] argTypes = { String.class };
/* 733 */       Method lookupPrefix = clazz.getMethod("lookupPrefix", argTypes);
/* 734 */       String[] args = { namespaceURI };
/* 735 */       return (String)lookupPrefix.invoke(this.parent, args);
/*     */     }
/*     */     catch (NoSuchMethodException ex)
/*     */     {
/* 739 */       throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
/*     */     }
/*     */     catch (InvocationTargetException ex) {
/* 742 */       throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
/*     */     } catch (IllegalAccessException ex) {
/*     */     }
/* 745 */     throw new UnsupportedOperationException("Cannot lookup prefixes in DOM 2");
/*     */   }
/*     */ 
/*     */   public boolean isDefaultNamespace(String namespaceURI)
/*     */   {
/* 762 */     return namespaceURI.equals(lookupNamespaceURI(null));
/*     */   }
/*     */ 
/*     */   public String lookupNamespaceURI(String prefix)
/*     */   {
/*     */     try
/*     */     {
/* 781 */       Class clazz = Node.class;
/* 782 */       Class[] argTypes = { String.class };
/* 783 */       Method lookupNamespaceURI = clazz.getMethod("lookupNamespaceURI", argTypes);
/* 784 */       String[] args = { prefix };
/* 785 */       return (String)lookupNamespaceURI.invoke(this.parent, args);
/*     */     }
/*     */     catch (NoSuchMethodException ex)
/*     */     {
/* 789 */       throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
/*     */     }
/*     */     catch (InvocationTargetException ex) {
/* 792 */       throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
/*     */     } catch (IllegalAccessException ex) {
/*     */     }
/* 795 */     throw new UnsupportedOperationException("Cannot lookup namespace URIs in DOM 2");
/*     */   }
/*     */ 
/*     */   public boolean isEqualNode(Node arg)
/*     */   {
/* 808 */     if (arg.getNodeType() == getNodeType()) {
/* 809 */       NamespaceNode other = (NamespaceNode)arg;
/* 810 */       if ((other.name == null) && (this.name != null)) return false;
/* 811 */       if ((other.name != null) && (this.name == null)) return false;
/* 812 */       if ((other.value == null) && (this.value != null)) return false;
/* 813 */       if ((other.value != null) && (this.value == null)) return false;
/* 814 */       if ((other.name == null) && (this.name == null)) {
/* 815 */         return other.value.equals(this.value);
/*     */       }
/*     */ 
/* 818 */       return (other.name.equals(this.name)) && (other.value.equals(this.value));
/*     */     }
/* 820 */     return false;
/*     */   }
/*     */ 
/*     */   public Object getFeature(String feature, String version)
/*     */   {
/* 830 */     return null;
/*     */   }
/*     */ 
/*     */   public Object setUserData(String key, Object data, UserDataHandler handler)
/*     */   {
/* 848 */     Object oldValue = getUserData(key);
/* 849 */     this.userData.put(key, data);
/* 850 */     return oldValue;
/*     */   }
/*     */ 
/*     */   public Object getUserData(String key)
/*     */   {
/* 862 */     return this.userData.get(key);
/*     */   }
/*     */ 
/*     */   private static class EmptyNodeList
/*     */     implements NodeList
/*     */   {
/*     */     private EmptyNodeList()
/*     */     {
/*     */     }
/*     */ 
/*     */     public int getLength()
/*     */     {
/* 594 */       return 0;
/*     */     }
/*     */ 
/*     */     public Node item(int index)
/*     */     {
/* 603 */       return null;
/*     */     }
/*     */ 
/*     */     EmptyNodeList(NamespaceNode.1 x0)
/*     */     {
/* 586 */       this();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.dom.NamespaceNode
 * JD-Core Version:    0.6.2
 */