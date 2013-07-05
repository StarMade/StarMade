/*      */ package org.jaxen.dom;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.NoSuchElementException;
/*      */ import javax.xml.parsers.DocumentBuilder;
/*      */ import javax.xml.parsers.DocumentBuilderFactory;
/*      */ import javax.xml.parsers.ParserConfigurationException;
/*      */ import org.jaxen.DefaultNavigator;
/*      */ import org.jaxen.FunctionCallException;
/*      */ import org.jaxen.JaxenConstants;
/*      */ import org.jaxen.Navigator;
/*      */ import org.jaxen.XPath;
/*      */ import org.jaxen.saxpath.SAXPathException;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.NamedNodeMap;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
/*      */ import org.w3c.dom.ProcessingInstruction;
/*      */ import org.xml.sax.SAXException;
/*      */ 
/*      */ public class DocumentNavigator extends DefaultNavigator
/*      */ {
/*      */   private static final long serialVersionUID = 8460943068889528115L;
/*  110 */   private static final DocumentNavigator SINGLETON = new DocumentNavigator();
/*      */ 
/*      */   public static Navigator getInstance()
/*      */   {
/*  134 */     return SINGLETON;
/*      */   }
/*      */   public Iterator getChildAxisIterator(Object contextNode) {
/*      */     // Byte code:
/*      */     //   0: aload_1
/*      */     //   1: checkcast 3	org/w3c/dom/Node
/*      */     //   4: astore_2
/*      */     //   5: aload_2
/*      */     //   6: invokeinterface 4 1 0
/*      */     //   11: iconst_1
/*      */     //   12: if_icmpeq +14 -> 26
/*      */     //   15: aload_2
/*      */     //   16: invokeinterface 4 1 0
/*      */     //   21: bipush 9
/*      */     //   23: if_icmpne +16 -> 39
/*      */     //   26: new 5	org/jaxen/dom/DocumentNavigator$1
/*      */     //   29: dup
/*      */     //   30: aload_0
/*      */     //   31: aload_1
/*      */     //   32: checkcast 3	org/w3c/dom/Node
/*      */     //   35: invokespecial 6	org/jaxen/dom/DocumentNavigator$1:<init>	(Lorg/jaxen/dom/DocumentNavigator;Lorg/w3c/dom/Node;)V
/*      */     //   38: areturn
/*      */     //   39: getstatic 7	org/jaxen/JaxenConstants:EMPTY_ITERATOR	Ljava/util/Iterator;
/*      */     //   42: areturn
/*      */   }
/*      */   public Iterator getParentAxisIterator(Object contextNode) {
/*      */     // Byte code:
/*      */     //   0: aload_1
/*      */     //   1: checkcast 3	org/w3c/dom/Node
/*      */     //   4: astore_2
/*      */     //   5: aload_2
/*      */     //   6: invokeinterface 4 1 0
/*      */     //   11: iconst_2
/*      */     //   12: if_icmpne +13 -> 25
/*      */     //   15: new 8	org/jaxen/dom/DocumentNavigator$2
/*      */     //   18: dup
/*      */     //   19: aload_0
/*      */     //   20: aload_2
/*      */     //   21: invokespecial 9	org/jaxen/dom/DocumentNavigator$2:<init>	(Lorg/jaxen/dom/DocumentNavigator;Lorg/w3c/dom/Node;)V
/*      */     //   24: areturn
/*      */     //   25: new 10	org/jaxen/dom/DocumentNavigator$3
/*      */     //   28: dup
/*      */     //   29: aload_0
/*      */     //   30: aload_2
/*      */     //   31: invokespecial 11	org/jaxen/dom/DocumentNavigator$3:<init>	(Lorg/jaxen/dom/DocumentNavigator;Lorg/w3c/dom/Node;)V
/*      */     //   34: areturn
/*      */   }
/*  220 */   public Object getParentNode(Object child) { Node node = (Node)child;
/*  221 */     if (node.getNodeType() == 2) {
/*  222 */       return ((Attr)node).getOwnerElement();
/*      */     }
/*  224 */     return node.getParentNode();
/*      */   }
/*      */ 
/*      */   public Iterator getFollowingSiblingAxisIterator(Object contextNode)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: new 15	org/jaxen/dom/DocumentNavigator$4
/*      */     //   3: dup
/*      */     //   4: aload_0
/*      */     //   5: aload_1
/*      */     //   6: checkcast 3	org/w3c/dom/Node
/*      */     //   9: invokespecial 16	org/jaxen/dom/DocumentNavigator$4:<init>	(Lorg/jaxen/dom/DocumentNavigator;Lorg/w3c/dom/Node;)V
/*      */     //   12: areturn
/*      */   }
/*      */ 
/*      */   public Iterator getPrecedingSiblingAxisIterator(Object contextNode)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: new 17	org/jaxen/dom/DocumentNavigator$5
/*      */     //   3: dup
/*      */     //   4: aload_0
/*      */     //   5: aload_1
/*      */     //   6: checkcast 3	org/w3c/dom/Node
/*      */     //   9: invokespecial 18	org/jaxen/dom/DocumentNavigator$5:<init>	(Lorg/jaxen/dom/DocumentNavigator;Lorg/w3c/dom/Node;)V
/*      */     //   12: areturn
/*      */   }
/*      */ 
/*      */   public Iterator getFollowingAxisIterator(Object contextNode)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: new 19	org/jaxen/dom/DocumentNavigator$6
/*      */     //   3: dup
/*      */     //   4: aload_0
/*      */     //   5: aload_1
/*      */     //   6: checkcast 3	org/w3c/dom/Node
/*      */     //   9: invokespecial 20	org/jaxen/dom/DocumentNavigator$6:<init>	(Lorg/jaxen/dom/DocumentNavigator;Lorg/w3c/dom/Node;)V
/*      */     //   12: areturn
/*      */   }
/*      */ 
/*      */   public Iterator getAttributeAxisIterator(Object contextNode)
/*      */   {
/*  315 */     if (isElement(contextNode)) {
/*  316 */       return new AttributeIterator((Node)contextNode);
/*      */     }
/*      */ 
/*  319 */     return JaxenConstants.EMPTY_ITERATOR;
/*      */   }
/*      */ 
/*      */   public Iterator getNamespaceAxisIterator(Object contextNode)
/*      */   {
/*  353 */     if (isElement(contextNode))
/*      */     {
/*  355 */       HashMap nsMap = new HashMap();
/*      */ 
/*  360 */       for (Node n = (Node)contextNode; 
/*  361 */         n != null; 
/*  362 */         n = n.getParentNode())
/*      */       {
/*  365 */         String myNamespace = n.getNamespaceURI();
/*  366 */         if ((myNamespace != null) && (!"".equals(myNamespace))) {
/*  367 */           String myPrefix = n.getPrefix();
/*  368 */           if (!nsMap.containsKey(myPrefix)) {
/*  369 */             NamespaceNode ns = new NamespaceNode((Node)contextNode, myPrefix, myNamespace);
/*  370 */             nsMap.put(myPrefix, ns);
/*      */           }
/*      */         }
/*      */ 
/*  374 */         if (n.hasAttributes()) {
/*  375 */           NamedNodeMap atts = n.getAttributes();
/*  376 */           int length = atts.getLength();
/*      */ 
/*  378 */           for (int i = 0; i < length; i++) {
/*  379 */             Attr att = (Attr)atts.item(i);
/*      */ 
/*  381 */             String attributeNamespace = att.getNamespaceURI();
/*  382 */             if (!"http://www.w3.org/2000/xmlns/".equals(attributeNamespace))
/*      */             {
/*  384 */               if (attributeNamespace != null) {
/*  385 */                 String prefix = att.getPrefix();
/*  386 */                 NamespaceNode ns = new NamespaceNode((Node)contextNode, prefix, attributeNamespace);
/*      */ 
/*  389 */                 if (!nsMap.containsKey(prefix)) nsMap.put(prefix, ns);
/*      */               }
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*  395 */           for (int i = 0; i < length; i++) {
/*  396 */             Attr att = (Attr)atts.item(i);
/*      */ 
/*  398 */             String attributeNamespace = att.getNamespaceURI();
/*  399 */             if ("http://www.w3.org/2000/xmlns/".equals(attributeNamespace)) {
/*  400 */               NamespaceNode ns = new NamespaceNode((Node)contextNode, att);
/*      */ 
/*  403 */               String name = ns.getNodeName();
/*  404 */               if (!nsMap.containsKey(name)) nsMap.put(name, ns);
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  413 */       nsMap.put("xml", new NamespaceNode((Node)contextNode, "xml", "http://www.w3.org/XML/1998/namespace"));
/*      */ 
/*  421 */       NamespaceNode defaultNS = (NamespaceNode)nsMap.get("");
/*  422 */       if ((defaultNS != null) && (defaultNS.getNodeValue().length() == 0)) {
/*  423 */         nsMap.remove("");
/*      */       }
/*  425 */       return nsMap.values().iterator();
/*      */     }
/*      */ 
/*  428 */     return JaxenConstants.EMPTY_ITERATOR;
/*      */   }
/*      */ 
/*      */   public XPath parseXPath(String xpath)
/*      */     throws SAXPathException
/*      */   {
/*  441 */     return new DOMXPath(xpath);
/*      */   }
/*      */ 
/*      */   public Object getDocumentNode(Object contextNode)
/*      */   {
/*  452 */     if (isDocument(contextNode)) return contextNode;
/*  453 */     return ((Node)contextNode).getOwnerDocument();
/*      */   }
/*      */ 
/*      */   public String getElementNamespaceUri(Object element)
/*      */   {
/*      */     try
/*      */     {
/*  469 */       Node node = (Node)element;
/*  470 */       if (node.getNodeType() == 1)
/*  471 */         return node.getNamespaceURI();
/*      */     }
/*      */     catch (ClassCastException ex)
/*      */     {
/*      */     }
/*  476 */     return null;
/*      */   }
/*      */ 
/*      */   public String getElementName(Object element)
/*      */   {
/*  489 */     if (isElement(element)) {
/*  490 */       String name = ((Node)element).getLocalName();
/*  491 */       if (name == null) name = ((Node)element).getNodeName();
/*  492 */       return name;
/*      */     }
/*  494 */     return null;
/*      */   }
/*      */ 
/*      */   public String getElementQName(Object element)
/*      */   {
/*      */     try
/*      */     {
/*  508 */       Node node = (Node)element;
/*  509 */       if (node.getNodeType() == 1)
/*  510 */         return node.getNodeName();
/*      */     }
/*      */     catch (ClassCastException ex)
/*      */     {
/*      */     }
/*  515 */     return null;
/*      */   }
/*      */ 
/*      */   public String getAttributeNamespaceUri(Object attribute)
/*      */   {
/*      */     try
/*      */     {
/*  530 */       Node node = (Node)attribute;
/*  531 */       if (node.getNodeType() == 2)
/*  532 */         return node.getNamespaceURI();
/*      */     }
/*      */     catch (ClassCastException ex)
/*      */     {
/*      */     }
/*  537 */     return null;
/*      */   }
/*      */ 
/*      */   public String getAttributeName(Object attribute)
/*      */   {
/*  550 */     if (isAttribute(attribute)) {
/*  551 */       String name = ((Node)attribute).getLocalName();
/*  552 */       if (name == null) name = ((Node)attribute).getNodeName();
/*  553 */       return name;
/*      */     }
/*  555 */     return null;
/*      */   }
/*      */ 
/*      */   public String getAttributeQName(Object attribute)
/*      */   {
/*      */     try
/*      */     {
/*  570 */       Node node = (Node)attribute;
/*  571 */       if (node.getNodeType() == 2)
/*  572 */         return node.getNodeName();
/*      */     }
/*      */     catch (ClassCastException ex)
/*      */     {
/*      */     }
/*  577 */     return null;
/*      */   }
/*      */ 
/*      */   public boolean isDocument(Object object)
/*      */   {
/*  589 */     return ((object instanceof Node)) && (((Node)object).getNodeType() == 9);
/*      */   }
/*      */ 
/*      */   public boolean isNamespace(Object object)
/*      */   {
/*  602 */     return object instanceof NamespaceNode;
/*      */   }
/*      */ 
/*      */   public boolean isElement(Object object)
/*      */   {
/*  614 */     return ((object instanceof Node)) && (((Node)object).getNodeType() == 1);
/*      */   }
/*      */ 
/*      */   public boolean isAttribute(Object object)
/*      */   {
/*  629 */     return ((object instanceof Node)) && (((Node)object).getNodeType() == 2) && (!"http://www.w3.org/2000/xmlns/".equals(((Node)object).getNamespaceURI()));
/*      */   }
/*      */ 
/*      */   public boolean isComment(Object object)
/*      */   {
/*  643 */     return ((object instanceof Node)) && (((Node)object).getNodeType() == 8);
/*      */   }
/*      */ 
/*      */   public boolean isText(Object object)
/*      */   {
/*  656 */     if ((object instanceof Node)) {
/*  657 */       switch (((Node)object).getNodeType()) {
/*      */       case 3:
/*      */       case 4:
/*  660 */         return true;
/*      */       }
/*  662 */       return false;
/*      */     }
/*      */ 
/*  665 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean isProcessingInstruction(Object object)
/*      */   {
/*  678 */     return ((object instanceof Node)) && (((Node)object).getNodeType() == 7);
/*      */   }
/*      */ 
/*      */   public String getElementStringValue(Object object)
/*      */   {
/*  692 */     if (isElement(object)) {
/*  693 */       return getStringValue((Node)object, new StringBuffer()).toString();
/*      */     }
/*      */ 
/*  696 */     return null;
/*      */   }
/*      */ 
/*      */   private StringBuffer getStringValue(Node node, StringBuffer buffer)
/*      */   {
/*  710 */     if (isText(node)) {
/*  711 */       buffer.append(node.getNodeValue());
/*      */     } else {
/*  713 */       NodeList children = node.getChildNodes();
/*  714 */       int length = children.getLength();
/*  715 */       for (int i = 0; i < length; i++) {
/*  716 */         getStringValue(children.item(i), buffer);
/*      */       }
/*      */     }
/*  719 */     return buffer;
/*      */   }
/*      */ 
/*      */   public String getAttributeStringValue(Object object)
/*      */   {
/*  732 */     if (isAttribute(object)) return ((Node)object).getNodeValue();
/*  733 */     return null;
/*      */   }
/*      */ 
/*      */   public String getTextStringValue(Object object)
/*      */   {
/*  745 */     if (isText(object)) return ((Node)object).getNodeValue();
/*  746 */     return null;
/*      */   }
/*      */ 
/*      */   public String getCommentStringValue(Object object)
/*      */   {
/*  758 */     if (isComment(object)) return ((Node)object).getNodeValue();
/*  759 */     return null;
/*      */   }
/*      */ 
/*      */   public String getNamespaceStringValue(Object object)
/*      */   {
/*  772 */     if (isNamespace(object)) return ((NamespaceNode)object).getNodeValue();
/*  773 */     return null;
/*      */   }
/*      */ 
/*      */   public String getNamespacePrefix(Object object)
/*      */   {
/*  785 */     if (isNamespace(object)) return ((NamespaceNode)object).getLocalName();
/*  786 */     return null;
/*      */   }
/*      */ 
/*      */   public String translateNamespacePrefixToUri(String prefix, Object element)
/*      */   {
/*  799 */     Iterator it = getNamespaceAxisIterator(element);
/*  800 */     while (it.hasNext()) {
/*  801 */       NamespaceNode ns = (NamespaceNode)it.next();
/*  802 */       if (prefix.equals(ns.getNodeName())) return ns.getNodeValue();
/*      */     }
/*  804 */     return null;
/*      */   }
/*      */ 
/*      */   public Object getDocument(String uri)
/*      */     throws FunctionCallException
/*      */   {
/*      */     try
/*      */     {
/*  823 */       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
/*  824 */       factory.setNamespaceAware(true);
/*  825 */       DocumentBuilder builder = factory.newDocumentBuilder();
/*  826 */       return builder.parse(uri);
/*      */     }
/*      */     catch (ParserConfigurationException e) {
/*  829 */       throw new FunctionCallException("JAXP setup error in document() function: " + e.getMessage(), e);
/*      */     }
/*      */     catch (SAXException e) {
/*  832 */       throw new FunctionCallException("XML error in document() function: " + e.getMessage(), e);
/*      */     }
/*      */     catch (IOException e) {
/*  835 */       throw new FunctionCallException("I/O error in document() function: " + e.getMessage(), e);
/*      */     }
/*      */   }
/*      */ 
/*      */   public String getProcessingInstructionTarget(Object obj)
/*      */   {
/*  850 */     if (isProcessingInstruction(obj)) {
/*  851 */       ProcessingInstruction pi = (ProcessingInstruction)obj;
/*  852 */       return pi.getTarget();
/*      */     }
/*  854 */     throw new ClassCastException(obj + " is not a processing instruction");
/*      */   }
/*      */ 
/*      */   public String getProcessingInstructionData(Object obj)
/*      */   {
/*  867 */     if (isProcessingInstruction(obj)) {
/*  868 */       ProcessingInstruction pi = (ProcessingInstruction)obj;
/*  869 */       return pi.getData();
/*      */     }
/*  871 */     throw new ClassCastException(obj + " is not a processing instruction");
/*      */   }
/*      */ 
/*      */   public Object getElementById(Object object, String elementId)
/*      */   {
/* 1066 */     Document doc = (Document)getDocumentNode(object);
/* 1067 */     if (doc != null) return doc.getElementById(elementId);
/* 1068 */     return null;
/*      */   }
/*      */ 
/*      */   private static class AttributeIterator
/*      */     implements Iterator
/*      */   {
/*      */     private NamedNodeMap map;
/*      */     private int pos;
/* 1040 */     private int lastAttribute = -1;
/*      */ 
/*      */     AttributeIterator(Node parent)
/*      */     {
/* 1004 */       this.map = parent.getAttributes();
/* 1005 */       this.pos = 0;
/* 1006 */       for (int i = this.map.getLength() - 1; i >= 0; i--) {
/* 1007 */         Node node = this.map.item(i);
/* 1008 */         if (!"http://www.w3.org/2000/xmlns/".equals(node.getNamespaceURI())) {
/* 1009 */           this.lastAttribute = i;
/* 1010 */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*      */     public boolean hasNext()
/*      */     {
/* 1017 */       return this.pos <= this.lastAttribute;
/*      */     }
/*      */ 
/*      */     public Object next()
/*      */     {
/* 1022 */       Node attr = this.map.item(this.pos++);
/* 1023 */       if (attr == null) throw new NoSuchElementException();
/* 1024 */       if ("http://www.w3.org/2000/xmlns/".equals(attr.getNamespaceURI()))
/*      */       {
/* 1027 */         return next();
/*      */       }
/* 1029 */       return attr;
/*      */     }
/*      */ 
/*      */     public void remove()
/*      */     {
/* 1034 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */ 
/*      */   abstract class NodeIterator
/*      */     implements Iterator
/*      */   {
/*      */     private Node node;
/*      */ 
/*      */     public NodeIterator(Node contextNode)
/*      */     {
/*  903 */       this.node = getFirstNode(contextNode);
/*  904 */       while (!isXPathNode(this.node))
/*  905 */         this.node = getNextNode(this.node);
/*      */     }
/*      */ 
/*      */     public boolean hasNext()
/*      */     {
/*  911 */       return this.node != null;
/*      */     }
/*      */ 
/*      */     public Object next()
/*      */     {
/*  916 */       if (this.node == null) throw new NoSuchElementException();
/*  917 */       Node ret = this.node;
/*  918 */       this.node = getNextNode(this.node);
/*  919 */       while (!isXPathNode(this.node)) {
/*  920 */         this.node = getNextNode(this.node);
/*      */       }
/*  922 */       return ret;
/*      */     }
/*      */ 
/*      */     public void remove()
/*      */     {
/*  927 */       throw new UnsupportedOperationException();
/*      */     }
/*      */ 
/*      */     protected abstract Node getFirstNode(Node paramNode);
/*      */ 
/*      */     protected abstract Node getNextNode(Node paramNode);
/*      */ 
/*      */     private boolean isXPathNode(Node node)
/*      */     {
/*  967 */       if (node == null) return true;
/*      */ 
/*  969 */       switch (node.getNodeType()) {
/*      */       case 5:
/*      */       case 6:
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*  975 */         return false;
/*      */       case 7:
/*      */       case 8:
/*  977 */       case 9: } return true;
/*      */     }
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.dom.DocumentNavigator
 * JD-Core Version:    0.6.2
 */