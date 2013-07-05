/*    */ package org.dom4j.dom;
/*    */ 
/*    */ import org.w3c.dom.Attr;
/*    */ import org.w3c.dom.DOMException;
/*    */ import org.w3c.dom.NamedNodeMap;
/*    */ import org.w3c.dom.Node;
/*    */ 
/*    */ public class DOMAttributeNodeMap
/*    */   implements NamedNodeMap
/*    */ {
/*    */   private DOMElement element;
/*    */ 
/*    */   public DOMAttributeNodeMap(DOMElement element)
/*    */   {
/* 27 */     this.element = element;
/*    */   }
/*    */ 
/*    */   public void foo()
/*    */     throws DOMException
/*    */   {
/* 33 */     DOMNodeHelper.notSupported();
/*    */   }
/*    */ 
/*    */   public Node getNamedItem(String name) {
/* 37 */     return this.element.getAttributeNode(name);
/*    */   }
/*    */ 
/*    */   public Node setNamedItem(Node arg) throws DOMException {
/* 41 */     if ((arg instanceof Attr)) {
/* 42 */       return this.element.setAttributeNode((Attr)arg);
/*    */     }
/* 44 */     throw new DOMException((short)9, "Node is not an Attr: " + arg);
/*    */   }
/*    */ 
/*    */   public Node removeNamedItem(String name)
/*    */     throws DOMException
/*    */   {
/* 50 */     Attr attr = this.element.getAttributeNode(name);
/*    */ 
/* 52 */     if (attr == null) {
/* 53 */       throw new DOMException((short)8, "No attribute named " + name);
/*    */     }
/*    */ 
/* 57 */     return this.element.removeAttributeNode(attr);
/*    */   }
/*    */ 
/*    */   public Node item(int index) {
/* 61 */     return DOMNodeHelper.asDOMAttr(this.element.attribute(index));
/*    */   }
/*    */ 
/*    */   public int getLength() {
/* 65 */     return this.element.attributeCount();
/*    */   }
/*    */ 
/*    */   public Node getNamedItemNS(String namespaceURI, String localName) {
/* 69 */     return this.element.getAttributeNodeNS(namespaceURI, localName);
/*    */   }
/*    */ 
/*    */   public Node setNamedItemNS(Node arg) throws DOMException {
/* 73 */     if ((arg instanceof Attr)) {
/* 74 */       return this.element.setAttributeNodeNS((Attr)arg);
/*    */     }
/* 76 */     throw new DOMException((short)9, "Node is not an Attr: " + arg);
/*    */   }
/*    */ 
/*    */   public Node removeNamedItemNS(String namespaceURI, String localName)
/*    */     throws DOMException
/*    */   {
/* 83 */     Attr attr = this.element.getAttributeNodeNS(namespaceURI, localName);
/*    */ 
/* 86 */     if (attr != null) {
/* 87 */       return this.element.removeAttributeNode(attr);
/*    */     }
/*    */ 
/* 90 */     return attr;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMAttributeNodeMap
 * JD-Core Version:    0.6.2
 */