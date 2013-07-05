/*    */ package org.dom4j.xpath;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.dom4j.Document;
/*    */ import org.dom4j.Element;
/*    */ import org.dom4j.Namespace;
/*    */ import org.dom4j.Node;
/*    */ import org.jaxen.NamespaceContext;
/*    */ 
/*    */ public class DefaultNamespaceContext
/*    */   implements NamespaceContext, Serializable
/*    */ {
/*    */   private final Element element;
/*    */ 
/*    */   public DefaultNamespaceContext(Element element)
/*    */   {
/* 32 */     this.element = element;
/*    */   }
/*    */ 
/*    */   public static DefaultNamespaceContext create(Object node) {
/* 36 */     Element element = null;
/*    */ 
/* 38 */     if ((node instanceof Element)) {
/* 39 */       element = (Element)node;
/* 40 */     } else if ((node instanceof Document)) {
/* 41 */       Document doc = (Document)node;
/* 42 */       element = doc.getRootElement();
/* 43 */     } else if ((node instanceof Node)) {
/* 44 */       element = ((Node)node).getParent();
/*    */     }
/*    */ 
/* 47 */     if (element != null) {
/* 48 */       return new DefaultNamespaceContext(element);
/*    */     }
/*    */ 
/* 51 */     return null;
/*    */   }
/*    */ 
/*    */   public String translateNamespacePrefixToUri(String prefix) {
/* 55 */     if ((prefix != null) && (prefix.length() > 0)) {
/* 56 */       Namespace ns = this.element.getNamespaceForPrefix(prefix);
/*    */ 
/* 58 */       if (ns != null) {
/* 59 */         return ns.getURI();
/*    */       }
/*    */     }
/*    */ 
/* 63 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.xpath.DefaultNamespaceContext
 * JD-Core Version:    0.6.2
 */