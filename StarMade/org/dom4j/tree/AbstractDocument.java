/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Comment;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.DocumentType;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.IllegalAddException;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.ProcessingInstruction;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.Text;
/*     */ import org.dom4j.Visitor;
/*     */ import org.dom4j.io.OutputFormat;
/*     */ import org.dom4j.io.XMLWriter;
/*     */ 
/*     */ public abstract class AbstractDocument extends AbstractBranch
/*     */   implements Document
/*     */ {
/*     */   protected String encoding;
/*     */ 
/*     */   public short getNodeType()
/*     */   {
/*  49 */     return 9;
/*     */   }
/*     */ 
/*     */   public String getPath(Element context) {
/*  53 */     return "/";
/*     */   }
/*     */ 
/*     */   public String getUniquePath(Element context) {
/*  57 */     return "/";
/*     */   }
/*     */ 
/*     */   public Document getDocument() {
/*  61 */     return this;
/*     */   }
/*     */ 
/*     */   public String getXMLEncoding() {
/*  65 */     return null;
/*     */   }
/*     */ 
/*     */   public String getStringValue() {
/*  69 */     Element root = getRootElement();
/*     */ 
/*  71 */     return root != null ? root.getStringValue() : "";
/*     */   }
/*     */ 
/*     */   public String asXML() {
/*  75 */     OutputFormat format = new OutputFormat();
/*  76 */     format.setEncoding(this.encoding);
/*     */     try
/*     */     {
/*  79 */       StringWriter out = new StringWriter();
/*  80 */       XMLWriter writer = new XMLWriter(out, format);
/*  81 */       writer.write(this);
/*  82 */       writer.flush();
/*     */ 
/*  84 */       return out.toString();
/*     */     } catch (IOException e) {
/*  86 */       throw new RuntimeException("IOException while generating textual representation: " + e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void write(Writer out) throws IOException
/*     */   {
/*  92 */     OutputFormat format = new OutputFormat();
/*  93 */     format.setEncoding(this.encoding);
/*     */ 
/*  95 */     XMLWriter writer = new XMLWriter(out, format);
/*  96 */     writer.write(this);
/*     */   }
/*     */ 
/*     */   public void accept(Visitor visitor)
/*     */   {
/* 109 */     visitor.visit(this);
/*     */ 
/* 111 */     DocumentType docType = getDocType();
/*     */ 
/* 113 */     if (docType != null) {
/* 114 */       visitor.visit(docType);
/*     */     }
/*     */ 
/* 118 */     List content = content();
/*     */     Iterator iter;
/* 120 */     if (content != null)
/* 121 */       for (iter = content.iterator(); iter.hasNext(); ) {
/* 122 */         Object object = iter.next();
/*     */ 
/* 124 */         if ((object instanceof String)) {
/* 125 */           Text text = getDocumentFactory().createText((String)object);
/*     */ 
/* 127 */           visitor.visit(text);
/*     */         } else {
/* 129 */           Node node = (Node)object;
/* 130 */           node.accept(visitor);
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 137 */     return super.toString() + " [Document: name " + getName() + "]";
/*     */   }
/*     */ 
/*     */   public void normalize() {
/* 141 */     Element element = getRootElement();
/*     */ 
/* 143 */     if (element != null)
/* 144 */       element.normalize();
/*     */   }
/*     */ 
/*     */   public Document addComment(String comment)
/*     */   {
/* 149 */     Comment node = getDocumentFactory().createComment(comment);
/* 150 */     add(node);
/*     */ 
/* 152 */     return this;
/*     */   }
/*     */ 
/*     */   public Document addProcessingInstruction(String target, String data) {
/* 156 */     ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
/*     */ 
/* 158 */     add(node);
/*     */ 
/* 160 */     return this;
/*     */   }
/*     */ 
/*     */   public Document addProcessingInstruction(String target, Map data) {
/* 164 */     ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
/*     */ 
/* 166 */     add(node);
/*     */ 
/* 168 */     return this;
/*     */   }
/*     */ 
/*     */   public Element addElement(String name) {
/* 172 */     Element element = getDocumentFactory().createElement(name);
/* 173 */     add(element);
/*     */ 
/* 175 */     return element;
/*     */   }
/*     */ 
/*     */   public Element addElement(String qualifiedName, String namespaceURI) {
/* 179 */     Element element = getDocumentFactory().createElement(qualifiedName, namespaceURI);
/*     */ 
/* 181 */     add(element);
/*     */ 
/* 183 */     return element;
/*     */   }
/*     */ 
/*     */   public Element addElement(QName qName) {
/* 187 */     Element element = getDocumentFactory().createElement(qName);
/* 188 */     add(element);
/*     */ 
/* 190 */     return element;
/*     */   }
/*     */ 
/*     */   public void setRootElement(Element rootElement) {
/* 194 */     clearContent();
/*     */ 
/* 196 */     if (rootElement != null) {
/* 197 */       super.add(rootElement);
/* 198 */       rootElementAdded(rootElement);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void add(Element element) {
/* 203 */     checkAddElementAllowed(element);
/* 204 */     super.add(element);
/* 205 */     rootElementAdded(element);
/*     */   }
/*     */ 
/*     */   public boolean remove(Element element) {
/* 209 */     boolean answer = super.remove(element);
/* 210 */     Element root = getRootElement();
/*     */ 
/* 212 */     if ((root != null) && (answer)) {
/* 213 */       setRootElement(null);
/*     */     }
/*     */ 
/* 216 */     element.setDocument(null);
/*     */ 
/* 218 */     return answer;
/*     */   }
/*     */ 
/*     */   public Node asXPathResult(Element parent) {
/* 222 */     return this;
/*     */   }
/*     */ 
/*     */   protected void childAdded(Node node) {
/* 226 */     if (node != null)
/* 227 */       node.setDocument(this);
/*     */   }
/*     */ 
/*     */   protected void childRemoved(Node node)
/*     */   {
/* 232 */     if (node != null)
/* 233 */       node.setDocument(null);
/*     */   }
/*     */ 
/*     */   protected void checkAddElementAllowed(Element element)
/*     */   {
/* 238 */     Element root = getRootElement();
/*     */ 
/* 240 */     if (root != null)
/* 241 */       throw new IllegalAddException(this, element, "Cannot add another element to this Document as it already has a root element of: " + root.getQualifiedName());
/*     */   }
/*     */ 
/*     */   protected abstract void rootElementAdded(Element paramElement);
/*     */ 
/*     */   public void setXMLEncoding(String enc)
/*     */   {
/* 257 */     this.encoding = enc;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractDocument
 * JD-Core Version:    0.6.2
 */