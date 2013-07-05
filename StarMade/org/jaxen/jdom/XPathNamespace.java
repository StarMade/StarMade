/*     */ package org.jaxen.jdom;
/*     */ 
/*     */ import org.jdom.Element;
/*     */ import org.jdom.Namespace;
/*     */ 
/*     */ public class XPathNamespace
/*     */ {
/*     */   private Element jdomElement;
/*     */   private Namespace jdomNamespace;
/*     */ 
/*     */   public XPathNamespace(Namespace jdomNamespace)
/*     */   {
/*  70 */     this.jdomNamespace = jdomNamespace;
/*     */   }
/*     */ 
/*     */   public XPathNamespace(Element jdomElement, Namespace jdomNamespace)
/*     */   {
/*  78 */     this.jdomElement = jdomElement;
/*  79 */     this.jdomNamespace = jdomNamespace;
/*     */   }
/*     */ 
/*     */   public Element getJDOMElement()
/*     */   {
/*  88 */     return this.jdomElement;
/*     */   }
/*     */ 
/*     */   public void setJDOMElement(Element jdomElement)
/*     */   {
/*  95 */     this.jdomElement = jdomElement;
/*     */   }
/*     */ 
/*     */   public Namespace getJDOMNamespace()
/*     */   {
/* 103 */     return this.jdomNamespace;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 108 */     return "[xmlns:" + this.jdomNamespace.getPrefix() + "=\"" + this.jdomNamespace.getURI() + "\", element=" + this.jdomElement.getName() + "]";
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.jdom.XPathNamespace
 * JD-Core Version:    0.6.2
 */