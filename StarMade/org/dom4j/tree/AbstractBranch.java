/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.dom4j.Branch;
/*     */ import org.dom4j.Comment;
/*     */ import org.dom4j.DocumentFactory;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.IllegalAddException;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.ProcessingInstruction;
/*     */ import org.dom4j.QName;
/*     */ 
/*     */ public abstract class AbstractBranch extends AbstractNode
/*     */   implements Branch
/*     */ {
/*     */   protected static final int DEFAULT_CONTENT_LIST_SIZE = 5;
/*     */ 
/*     */   public boolean isReadOnly()
/*     */   {
/*  40 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean hasContent() {
/*  44 */     return nodeCount() > 0;
/*     */   }
/*     */ 
/*     */   public List content() {
/*  48 */     List backingList = contentList();
/*     */ 
/*  50 */     return new ContentListFacade(this, backingList);
/*     */   }
/*     */ 
/*     */   public String getText() {
/*  54 */     List content = contentList();
/*     */ 
/*  56 */     if (content != null) {
/*  57 */       int size = content.size();
/*     */ 
/*  59 */       if (size >= 1) {
/*  60 */         Object first = content.get(0);
/*  61 */         String firstText = getContentAsText(first);
/*     */ 
/*  63 */         if (size == 1)
/*     */         {
/*  65 */           return firstText;
/*     */         }
/*  67 */         StringBuffer buffer = new StringBuffer(firstText);
/*     */ 
/*  69 */         for (int i = 1; i < size; i++) {
/*  70 */           Object node = content.get(i);
/*  71 */           buffer.append(getContentAsText(node));
/*     */         }
/*     */ 
/*  74 */         return buffer.toString();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  79 */     return "";
/*     */   }
/*     */ 
/*     */   protected String getContentAsText(Object content)
/*     */   {
/*  92 */     if ((content instanceof Node)) {
/*  93 */       Node node = (Node)content;
/*     */ 
/*  95 */       switch (node.getNodeType())
/*     */       {
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/* 101 */         return node.getText();
/*     */       }
/*     */ 
/*     */     }
/* 106 */     else if ((content instanceof String)) {
/* 107 */       return (String)content;
/*     */     }
/*     */ 
/* 110 */     return "";
/*     */   }
/*     */ 
/*     */   protected String getContentAsStringValue(Object content)
/*     */   {
/* 122 */     if ((content instanceof Node)) {
/* 123 */       Node node = (Node)content;
/*     */ 
/* 125 */       switch (node.getNodeType())
/*     */       {
/*     */       case 1:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/* 132 */         return node.getStringValue();
/*     */       case 2:
/*     */       }
/*     */ 
/*     */     }
/* 137 */     else if ((content instanceof String)) {
/* 138 */       return (String)content;
/*     */     }
/*     */ 
/* 141 */     return "";
/*     */   }
/*     */ 
/*     */   public String getTextTrim() {
/* 145 */     String text = getText();
/*     */ 
/* 147 */     StringBuffer textContent = new StringBuffer();
/* 148 */     StringTokenizer tokenizer = new StringTokenizer(text);
/*     */ 
/* 150 */     while (tokenizer.hasMoreTokens()) {
/* 151 */       String str = tokenizer.nextToken();
/* 152 */       textContent.append(str);
/*     */ 
/* 154 */       if (tokenizer.hasMoreTokens()) {
/* 155 */         textContent.append(" ");
/*     */       }
/*     */     }
/*     */ 
/* 159 */     return textContent.toString();
/*     */   }
/*     */ 
/*     */   public void setProcessingInstructions(List listOfPIs) {
/* 163 */     for (Iterator iter = listOfPIs.iterator(); iter.hasNext(); ) {
/* 164 */       ProcessingInstruction pi = (ProcessingInstruction)iter.next();
/* 165 */       addNode(pi);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Element addElement(String name) {
/* 170 */     Element node = getDocumentFactory().createElement(name);
/* 171 */     add(node);
/*     */ 
/* 173 */     return node;
/*     */   }
/*     */ 
/*     */   public Element addElement(String qualifiedName, String namespaceURI) {
/* 177 */     Element node = getDocumentFactory().createElement(qualifiedName, namespaceURI);
/*     */ 
/* 179 */     add(node);
/*     */ 
/* 181 */     return node;
/*     */   }
/*     */ 
/*     */   public Element addElement(QName qname) {
/* 185 */     Element node = getDocumentFactory().createElement(qname);
/* 186 */     add(node);
/*     */ 
/* 188 */     return node;
/*     */   }
/*     */ 
/*     */   public Element addElement(String name, String prefix, String uri) {
/* 192 */     Namespace namespace = Namespace.get(prefix, uri);
/* 193 */     QName qName = getDocumentFactory().createQName(name, namespace);
/*     */ 
/* 195 */     return addElement(qName);
/*     */   }
/*     */ 
/*     */   public void add(Node node)
/*     */   {
/* 200 */     switch (node.getNodeType()) {
/*     */     case 1:
/* 202 */       add((Element)node);
/*     */ 
/* 204 */       break;
/*     */     case 8:
/* 207 */       add((Comment)node);
/*     */ 
/* 209 */       break;
/*     */     case 7:
/* 212 */       add((ProcessingInstruction)node);
/*     */ 
/* 214 */       break;
/*     */     default:
/* 217 */       invalidNodeTypeAddException(node);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean remove(Node node) {
/* 222 */     switch (node.getNodeType()) {
/*     */     case 1:
/* 224 */       return remove((Element)node);
/*     */     case 8:
/* 227 */       return remove((Comment)node);
/*     */     case 7:
/* 230 */       return remove((ProcessingInstruction)node);
/*     */     }
/*     */ 
/* 233 */     invalidNodeTypeAddException(node);
/*     */ 
/* 235 */     return false;
/*     */   }
/*     */ 
/*     */   public void add(Comment comment)
/*     */   {
/* 241 */     addNode(comment);
/*     */   }
/*     */ 
/*     */   public void add(Element element) {
/* 245 */     addNode(element);
/*     */   }
/*     */ 
/*     */   public void add(ProcessingInstruction pi) {
/* 249 */     addNode(pi);
/*     */   }
/*     */ 
/*     */   public boolean remove(Comment comment) {
/* 253 */     return removeNode(comment);
/*     */   }
/*     */ 
/*     */   public boolean remove(Element element) {
/* 257 */     return removeNode(element);
/*     */   }
/*     */ 
/*     */   public boolean remove(ProcessingInstruction pi) {
/* 261 */     return removeNode(pi);
/*     */   }
/*     */ 
/*     */   public Element elementByID(String elementID) {
/* 265 */     int i = 0; for (int size = nodeCount(); i < size; i++) {
/* 266 */       Node node = node(i);
/*     */ 
/* 268 */       if ((node instanceof Element)) {
/* 269 */         Element element = (Element)node;
/* 270 */         String id = elementID(element);
/*     */ 
/* 272 */         if ((id != null) && (id.equals(elementID))) {
/* 273 */           return element;
/*     */         }
/* 275 */         element = element.elementByID(elementID);
/*     */ 
/* 277 */         if (element != null) {
/* 278 */           return element;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 284 */     return null;
/*     */   }
/*     */ 
/*     */   public void appendContent(Branch branch) {
/* 288 */     int i = 0; for (int size = branch.nodeCount(); i < size; i++) {
/* 289 */       Node node = branch.node(i);
/* 290 */       add((Node)node.clone());
/*     */     }
/*     */   }
/*     */ 
/*     */   public Node node(int index) {
/* 295 */     Object object = contentList().get(index);
/*     */ 
/* 297 */     if ((object instanceof Node)) {
/* 298 */       return (Node)object;
/*     */     }
/*     */ 
/* 301 */     if ((object instanceof String)) {
/* 302 */       return getDocumentFactory().createText(object.toString());
/*     */     }
/*     */ 
/* 305 */     return null;
/*     */   }
/*     */ 
/*     */   public int nodeCount() {
/* 309 */     return contentList().size();
/*     */   }
/*     */ 
/*     */   public int indexOf(Node node) {
/* 313 */     return contentList().indexOf(node);
/*     */   }
/*     */ 
/*     */   public Iterator nodeIterator() {
/* 317 */     return contentList().iterator();
/*     */   }
/*     */ 
/*     */   protected String elementID(Element element)
/*     */   {
/* 333 */     return element.attributeValue("ID");
/*     */   }
/*     */ 
/*     */   protected abstract List contentList();
/*     */ 
/*     */   protected List createContentList()
/*     */   {
/* 350 */     return new ArrayList(5);
/*     */   }
/*     */ 
/*     */   protected List createContentList(int size)
/*     */   {
/* 363 */     return new ArrayList(size);
/*     */   }
/*     */ 
/*     */   protected BackedList createResultList()
/*     */   {
/* 373 */     return new BackedList(this, contentList());
/*     */   }
/*     */ 
/*     */   protected List createSingleResultList(Object result)
/*     */   {
/* 386 */     BackedList list = new BackedList(this, contentList(), 1);
/* 387 */     list.addLocal(result);
/*     */ 
/* 389 */     return list;
/*     */   }
/*     */ 
/*     */   protected List createEmptyList()
/*     */   {
/* 399 */     return new BackedList(this, contentList(), 0);
/*     */   }
/*     */ 
/*     */   protected abstract void addNode(Node paramNode);
/*     */ 
/*     */   protected abstract void addNode(int paramInt, Node paramNode);
/*     */ 
/*     */   protected abstract boolean removeNode(Node paramNode);
/*     */ 
/*     */   protected abstract void childAdded(Node paramNode);
/*     */ 
/*     */   protected abstract void childRemoved(Node paramNode);
/*     */ 
/*     */   protected void contentRemoved()
/*     */   {
/* 431 */     List content = contentList();
/*     */ 
/* 433 */     int i = 0; for (int size = content.size(); i < size; i++) {
/* 434 */       Object object = content.get(i);
/*     */ 
/* 436 */       if ((object instanceof Node))
/* 437 */         childRemoved((Node)object);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void invalidNodeTypeAddException(Node node)
/*     */   {
/* 453 */     throw new IllegalAddException("Invalid node type. Cannot add node: " + node + " to this branch: " + this);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractBranch
 * JD-Core Version:    0.6.2
 */