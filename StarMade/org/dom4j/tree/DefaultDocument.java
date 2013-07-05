/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.DocumentType;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.IllegalAddException;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.ProcessingInstruction;
/*     */ import org.xml.sax.EntityResolver;
/*     */ 
/*     */ public class DefaultDocument extends AbstractDocument
/*     */ {
/*  34 */   protected static final List EMPTY_LIST = Collections.EMPTY_LIST;
/*     */ 
/*  36 */   protected static final Iterator EMPTY_ITERATOR = EMPTY_LIST.iterator();
/*     */   private String name;
/*     */   private Element rootElement;
/*     */   private List content;
/*     */   private DocumentType docType;
/*  53 */   private DocumentFactory documentFactory = DocumentFactory.getInstance();
/*     */   private transient EntityResolver entityResolver;
/*     */ 
/*     */   public DefaultDocument()
/*     */   {
/*     */   }
/*     */ 
/*     */   public DefaultDocument(String name)
/*     */   {
/*  62 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public DefaultDocument(Element rootElement) {
/*  66 */     this.rootElement = rootElement;
/*     */   }
/*     */ 
/*     */   public DefaultDocument(DocumentType docType) {
/*  70 */     this.docType = docType;
/*     */   }
/*     */ 
/*     */   public DefaultDocument(Element rootElement, DocumentType docType) {
/*  74 */     this.rootElement = rootElement;
/*  75 */     this.docType = docType;
/*     */   }
/*     */ 
/*     */   public DefaultDocument(String name, Element rootElement, DocumentType docType)
/*     */   {
/*  80 */     this.name = name;
/*  81 */     this.rootElement = rootElement;
/*  82 */     this.docType = docType;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  86 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  90 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Element getRootElement() {
/*  94 */     return this.rootElement;
/*     */   }
/*     */ 
/*     */   public DocumentType getDocType() {
/*  98 */     return this.docType;
/*     */   }
/*     */ 
/*     */   public void setDocType(DocumentType docType) {
/* 102 */     this.docType = docType;
/*     */   }
/*     */ 
/*     */   public Document addDocType(String docTypeName, String publicId, String systemId)
/*     */   {
/* 107 */     setDocType(getDocumentFactory().createDocType(docTypeName, publicId, systemId));
/*     */ 
/* 110 */     return this;
/*     */   }
/*     */ 
/*     */   public String getXMLEncoding() {
/* 114 */     return this.encoding;
/*     */   }
/*     */ 
/*     */   public EntityResolver getEntityResolver() {
/* 118 */     return this.entityResolver;
/*     */   }
/*     */ 
/*     */   public void setEntityResolver(EntityResolver entityResolver) {
/* 122 */     this.entityResolver = entityResolver;
/*     */   }
/*     */ 
/*     */   public Object clone() {
/* 126 */     DefaultDocument document = (DefaultDocument)super.clone();
/* 127 */     document.rootElement = null;
/* 128 */     document.content = null;
/* 129 */     document.appendContent(this);
/*     */ 
/* 131 */     return document;
/*     */   }
/*     */ 
/*     */   public List processingInstructions() {
/* 135 */     List source = contentList();
/* 136 */     List answer = createResultList();
/* 137 */     int size = source.size();
/*     */ 
/* 139 */     for (int i = 0; i < size; i++) {
/* 140 */       Object object = source.get(i);
/*     */ 
/* 142 */       if ((object instanceof ProcessingInstruction)) {
/* 143 */         answer.add(object);
/*     */       }
/*     */     }
/*     */ 
/* 147 */     return answer;
/*     */   }
/*     */ 
/*     */   public List processingInstructions(String target) {
/* 151 */     List source = contentList();
/* 152 */     List answer = createResultList();
/* 153 */     int size = source.size();
/*     */ 
/* 155 */     for (int i = 0; i < size; i++) {
/* 156 */       Object object = source.get(i);
/*     */ 
/* 158 */       if ((object instanceof ProcessingInstruction)) {
/* 159 */         ProcessingInstruction pi = (ProcessingInstruction)object;
/*     */ 
/* 161 */         if (target.equals(pi.getName())) {
/* 162 */           answer.add(pi);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 167 */     return answer;
/*     */   }
/*     */ 
/*     */   public ProcessingInstruction processingInstruction(String target) {
/* 171 */     List source = contentList();
/* 172 */     int size = source.size();
/*     */ 
/* 174 */     for (int i = 0; i < size; i++) {
/* 175 */       Object object = source.get(i);
/*     */ 
/* 177 */       if ((object instanceof ProcessingInstruction)) {
/* 178 */         ProcessingInstruction pi = (ProcessingInstruction)object;
/*     */ 
/* 180 */         if (target.equals(pi.getName())) {
/* 181 */           return pi;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 186 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean removeProcessingInstruction(String target) {
/* 190 */     List source = contentList();
/*     */ 
/* 192 */     for (Iterator iter = source.iterator(); iter.hasNext(); ) {
/* 193 */       Object object = iter.next();
/*     */ 
/* 195 */       if ((object instanceof ProcessingInstruction)) {
/* 196 */         ProcessingInstruction pi = (ProcessingInstruction)object;
/*     */ 
/* 198 */         if (target.equals(pi.getName())) {
/* 199 */           iter.remove();
/*     */ 
/* 201 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 206 */     return false;
/*     */   }
/*     */ 
/*     */   public void setContent(List content) {
/* 210 */     this.rootElement = null;
/* 211 */     contentRemoved();
/*     */ 
/* 213 */     if ((content instanceof ContentListFacade)) {
/* 214 */       content = ((ContentListFacade)content).getBackingList();
/*     */     }
/*     */ 
/* 217 */     if (content == null) {
/* 218 */       this.content = null;
/*     */     } else {
/* 220 */       int size = content.size();
/* 221 */       List newContent = createContentList(size);
/*     */ 
/* 223 */       for (int i = 0; i < size; i++) {
/* 224 */         Object object = content.get(i);
/*     */ 
/* 226 */         if ((object instanceof Node)) {
/* 227 */           Node node = (Node)object;
/* 228 */           Document doc = node.getDocument();
/*     */ 
/* 230 */           if ((doc != null) && (doc != this)) {
/* 231 */             node = (Node)node.clone();
/*     */           }
/*     */ 
/* 234 */           if ((node instanceof Element)) {
/* 235 */             if (this.rootElement == null)
/* 236 */               this.rootElement = ((Element)node);
/*     */             else {
/* 238 */               throw new IllegalAddException("A document may only contain one root element: " + content);
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/* 245 */           newContent.add(node);
/* 246 */           childAdded(node);
/*     */         }
/*     */       }
/*     */ 
/* 250 */       this.content = newContent;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clearContent() {
/* 255 */     contentRemoved();
/* 256 */     this.content = null;
/* 257 */     this.rootElement = null;
/*     */   }
/*     */ 
/*     */   public void setDocumentFactory(DocumentFactory documentFactory) {
/* 261 */     this.documentFactory = documentFactory;
/*     */   }
/*     */ 
/*     */   protected List contentList()
/*     */   {
/* 267 */     if (this.content == null) {
/* 268 */       this.content = createContentList();
/*     */ 
/* 270 */       if (this.rootElement != null) {
/* 271 */         this.content.add(this.rootElement);
/*     */       }
/*     */     }
/*     */ 
/* 275 */     return this.content;
/*     */   }
/*     */ 
/*     */   protected void addNode(Node node) {
/* 279 */     if (node != null) {
/* 280 */       Document document = node.getDocument();
/*     */ 
/* 282 */       if ((document != null) && (document != this))
/*     */       {
/* 284 */         String message = "The Node already has an existing document: " + document;
/*     */ 
/* 286 */         throw new IllegalAddException(this, node, message);
/*     */       }
/*     */ 
/* 289 */       contentList().add(node);
/* 290 */       childAdded(node);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void addNode(int index, Node node) {
/* 295 */     if (node != null) {
/* 296 */       Document document = node.getDocument();
/*     */ 
/* 298 */       if ((document != null) && (document != this))
/*     */       {
/* 300 */         String message = "The Node already has an existing document: " + document;
/*     */ 
/* 302 */         throw new IllegalAddException(this, node, message);
/*     */       }
/*     */ 
/* 305 */       contentList().add(index, node);
/* 306 */       childAdded(node);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected boolean removeNode(Node node) {
/* 311 */     if (node == this.rootElement) {
/* 312 */       this.rootElement = null;
/*     */     }
/*     */ 
/* 315 */     if (contentList().remove(node)) {
/* 316 */       childRemoved(node);
/*     */ 
/* 318 */       return true;
/*     */     }
/*     */ 
/* 321 */     return false;
/*     */   }
/*     */ 
/*     */   protected void rootElementAdded(Element element) {
/* 325 */     this.rootElement = element;
/* 326 */     element.setDocument(this);
/*     */   }
/*     */ 
/*     */   protected DocumentFactory getDocumentFactory() {
/* 330 */     return this.documentFactory;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultDocument
 * JD-Core Version:    0.6.2
 */