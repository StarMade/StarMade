/*     */ package org.jaxen.xom;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import nu.xom.Attribute;
/*     */ import nu.xom.Builder;
/*     */ import nu.xom.Comment;
/*     */ import nu.xom.Document;
/*     */ import nu.xom.Element;
/*     */ import nu.xom.Node;
/*     */ import nu.xom.NodeFactory;
/*     */ import nu.xom.ParentNode;
/*     */ import nu.xom.ProcessingInstruction;
/*     */ import nu.xom.Text;
/*     */ import org.jaxen.BaseXPath;
/*     */ import org.jaxen.DefaultNavigator;
/*     */ import org.jaxen.FunctionCallException;
/*     */ import org.jaxen.JaxenConstants;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ import org.jaxen.XPath;
/*     */ import org.jaxen.saxpath.SAXPathException;
/*     */ import org.jaxen.util.SingleObjectIterator;
/*     */ 
/*     */ public class DocumentNavigator extends DefaultNavigator
/*     */ {
/*     */   private static final long serialVersionUID = 3159311338575942877L;
/*     */ 
/*     */   public boolean isAttribute(Object o)
/*     */   {
/*  96 */     return o instanceof Attribute;
/*     */   }
/*     */ 
/*     */   public boolean isComment(Object o) {
/* 100 */     return o instanceof Comment;
/*     */   }
/*     */ 
/*     */   public boolean isDocument(Object o) {
/* 104 */     return o instanceof Document;
/*     */   }
/*     */ 
/*     */   public boolean isElement(Object o) {
/* 108 */     return o instanceof Element;
/*     */   }
/*     */ 
/*     */   public boolean isNamespace(Object o) {
/* 112 */     return o instanceof XPathNamespace;
/*     */   }
/*     */ 
/*     */   public boolean isProcessingInstruction(Object o) {
/* 116 */     return o instanceof ProcessingInstruction;
/*     */   }
/*     */ 
/*     */   public boolean isText(Object o) {
/* 120 */     return o instanceof Text;
/*     */   }
/*     */ 
/*     */   public String getAttributeName(Object o)
/*     */   {
/* 126 */     return isAttribute(o) ? ((Attribute)o).getLocalName() : null;
/*     */   }
/*     */ 
/*     */   public String getAttributeNamespaceUri(Object o) {
/* 130 */     return isAttribute(o) ? ((Attribute)o).getNamespaceURI() : null;
/*     */   }
/*     */ 
/*     */   public String getAttributeQName(Object o) {
/* 134 */     return isAttribute(o) ? ((Attribute)o).getQualifiedName() : null;
/*     */   }
/*     */ 
/*     */   public String getAttributeStringValue(Object o) {
/* 138 */     return isAttribute(o) ? ((Attribute)o).getValue() : null;
/*     */   }
/*     */ 
/*     */   public String getCommentStringValue(Object o)
/*     */   {
/* 144 */     return isComment(o) ? ((Comment)o).getValue() : null;
/*     */   }
/*     */ 
/*     */   public String getElementName(Object o) {
/* 148 */     return isElement(o) ? ((Element)o).getLocalName() : null;
/*     */   }
/*     */ 
/*     */   public String getElementNamespaceUri(Object o) {
/* 152 */     return isElement(o) ? ((Element)o).getNamespaceURI() : null;
/*     */   }
/*     */ 
/*     */   public String getElementQName(Object o) {
/* 156 */     return isElement(o) ? ((Element)o).getQualifiedName() : null;
/*     */   }
/*     */ 
/*     */   public String getElementStringValue(Object o) {
/* 160 */     return (o instanceof Node) ? ((Node)o).getValue() : null;
/*     */   }
/*     */ 
/*     */   public String getNamespacePrefix(Object o)
/*     */   {
/* 166 */     if (isElement(o))
/* 167 */       return ((Element)o).getNamespacePrefix();
/* 168 */     if (isAttribute(o))
/* 169 */       return ((Attribute)o).getNamespacePrefix();
/* 170 */     if ((o instanceof XPathNamespace)) {
/* 171 */       return ((XPathNamespace)o).getNamespacePrefix();
/*     */     }
/* 173 */     return null;
/*     */   }
/*     */ 
/*     */   public String getNamespaceStringValue(Object o) {
/* 177 */     if (isElement(o))
/* 178 */       return ((Element)o).getNamespaceURI();
/* 179 */     if (isAttribute(o))
/* 180 */       return ((Attribute)o).getNamespaceURI();
/* 181 */     if ((o instanceof XPathNamespace)) {
/* 182 */       return ((XPathNamespace)o).getNamespaceURI();
/*     */     }
/* 184 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTextStringValue(Object o)
/*     */   {
/* 190 */     return (o instanceof Text) ? ((Text)o).getValue() : null;
/*     */   }
/*     */ 
/*     */   public Object getDocument(String s) throws FunctionCallException
/*     */   {
/*     */     try
/*     */     {
/* 197 */       return new Builder(new NodeFactory()).build(s);
/*     */     } catch (Exception pe) {
/* 199 */       throw new FunctionCallException(pe);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Object getDocumentNode(Object o) {
/* 204 */     ParentNode parent = null;
/* 205 */     if ((o instanceof ParentNode))
/* 206 */       parent = (ParentNode)o;
/* 207 */     else if ((o instanceof Node)) {
/* 208 */       parent = ((Node)o).getParent();
/*     */     }
/* 210 */     return parent.getDocument();
/*     */   }
/*     */ 
/*     */   public Iterator getAttributeAxisIterator(Object o)
/*     */   {
/* 240 */     if (isElement(o)) {
/* 241 */       return new IndexIterator(o, 0, ((Element)o).getAttributeCount()) {
/*     */         public Object get(Object o, int i) {
/* 243 */           return ((Element)o).getAttribute(i);
/*     */         }
/*     */       };
/*     */     }
/* 247 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getChildAxisIterator(Object o) {
/* 251 */     if ((isElement(o)) || ((o instanceof Document))) {
/* 252 */       return new IndexIterator(o, 0, ((ParentNode)o).getChildCount()) {
/*     */         public Object get(Object o, int i) {
/* 254 */           return ((ParentNode)o).getChild(i);
/*     */         }
/*     */       };
/*     */     }
/* 258 */     return JaxenConstants.EMPTY_ITERATOR;
/*     */   }
/*     */ 
/*     */   public Iterator getParentAxisIterator(Object o)
/*     */   {
/* 264 */     Object parent = null;
/* 265 */     if ((o instanceof Node))
/* 266 */       parent = ((Node)o).getParent();
/* 267 */     else if (isNamespace(o)) {
/* 268 */       parent = ((XPathNamespace)o).getElement();
/*     */     }
/* 270 */     return parent != null ? new SingleObjectIterator(parent) : null;
/*     */   }
/*     */ 
/*     */   public Object getParentNode(Object o) {
/* 274 */     return (o instanceof Node) ? ((Node)o).getParent() : null;
/*     */   }
/*     */ 
/*     */   public Iterator getPrecedingAxisIterator(Object o)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 280 */     return super.getPrecedingAxisIterator(o);
/*     */   }
/*     */ 
/*     */   public Iterator getPrecedingSiblingAxisIterator(Object o) throws UnsupportedAxisException {
/* 284 */     return super.getPrecedingSiblingAxisIterator(o);
/*     */   }
/*     */ 
/*     */   public String getProcessingInstructionData(Object o)
/*     */   {
/* 290 */     return (o instanceof ProcessingInstruction) ? ((ProcessingInstruction)o).getValue() : null;
/*     */   }
/*     */ 
/*     */   public String getProcessingInstructionTarget(Object o) {
/* 294 */     return (o instanceof ProcessingInstruction) ? ((ProcessingInstruction)o).getTarget() : null;
/*     */   }
/*     */ 
/*     */   public String translateNamespacePrefixToUri(String s, Object o)
/*     */   {
/* 300 */     Element element = null;
/* 301 */     if ((o instanceof Element))
/* 302 */       element = (Element)o;
/* 303 */     else if (!(o instanceof ParentNode))
/*     */     {
/* 305 */       if ((o instanceof Node)) {
/* 306 */         element = (Element)((Node)o).getParent();
/*     */       }
/* 308 */       else if ((o instanceof XPathNamespace))
/*     */       {
/* 310 */         element = ((XPathNamespace)o).getElement();
/*     */       }
/*     */     }
/* 312 */     if (element != null) {
/* 313 */       return element.getNamespaceURI(s);
/*     */     }
/* 315 */     return null;
/*     */   }
/*     */ 
/*     */   public XPath parseXPath(String s)
/*     */     throws SAXPathException
/*     */   {
/* 321 */     return new BaseXPath(s, this);
/*     */   }
/*     */ 
/*     */   private boolean addNamespaceForElement(Element elt, String uri, String prefix, Map map)
/*     */   {
/* 375 */     if ((uri != null) && (uri.length() > 0) && (!map.containsKey(prefix))) {
/* 376 */       map.put(prefix, new XPathNamespace(elt, uri, prefix));
/* 377 */       return true;
/*     */     }
/* 379 */     return false;
/*     */   }
/*     */ 
/*     */   public Iterator getNamespaceAxisIterator(Object o)
/*     */   {
/* 384 */     if (!isElement(o)) {
/* 385 */       return JaxenConstants.EMPTY_ITERATOR;
/*     */     }
/* 387 */     Map nsMap = new HashMap();
/* 388 */     Element elt = (Element)o;
/* 389 */     ParentNode parent = elt;
/*     */ 
/* 391 */     while ((parent instanceof Element)) {
/* 392 */       elt = (Element)parent;
/* 393 */       String uri = elt.getNamespaceURI();
/* 394 */       String prefix = elt.getNamespacePrefix();
/* 395 */       addNamespaceForElement(elt, uri, prefix, nsMap);
/* 396 */       int count = elt.getNamespaceDeclarationCount();
/* 397 */       for (int i = 0; i < count; i++) {
/* 398 */         prefix = elt.getNamespacePrefix(i);
/* 399 */         uri = elt.getNamespaceURI(prefix);
/* 400 */         addNamespaceForElement(elt, uri, prefix, nsMap);
/*     */       }
/* 402 */       parent = elt.getParent();
/*     */     }
/* 404 */     addNamespaceForElement(elt, "http://www.w3.org/XML/1998/namespace", "xml", nsMap);
/*     */ 
/* 406 */     return nsMap.values().iterator();
/*     */   }
/*     */ 
/*     */   private static class XPathNamespace
/*     */   {
/*     */     private Element element;
/*     */     private String uri;
/*     */     private String prefix;
/*     */ 
/*     */     public XPathNamespace(Element elt, String uri, String prefix)
/*     */     {
/* 339 */       this.element = elt;
/* 340 */       this.uri = uri;
/* 341 */       this.prefix = prefix;
/*     */     }
/*     */ 
/*     */     public Element getElement()
/*     */     {
/* 350 */       return this.element;
/*     */     }
/*     */ 
/*     */     public String getNamespaceURI()
/*     */     {
/* 355 */       return this.uri;
/*     */     }
/*     */ 
/*     */     public String getNamespacePrefix()
/*     */     {
/* 360 */       return this.prefix;
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 365 */       return "[xmlns:" + this.prefix + "=\"" + this.uri + "\", element=" + this.element.getLocalName() + "]";
/*     */     }
/*     */   }
/*     */ 
/*     */   private static abstract class IndexIterator
/*     */     implements Iterator
/*     */   {
/* 216 */     private Object o = null;
/* 217 */     private int pos = 0; private int end = -1;
/*     */ 
/* 219 */     public IndexIterator(Object o, int pos, int end) { this.o = o;
/* 220 */       this.pos = pos;
/* 221 */       this.end = end; }
/*     */ 
/*     */     public boolean hasNext() {
/* 224 */       return this.pos < this.end;
/*     */     }
/*     */     public abstract Object get(Object paramObject, int paramInt);
/*     */ 
/*     */     public Object next() {
/* 229 */       return get(this.o, this.pos++);
/*     */     }
/*     */ 
/*     */     public void remove() {
/* 233 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.xom.DocumentNavigator
 * JD-Core Version:    0.6.2
 */