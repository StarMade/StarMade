/*      */ package org.dom4j.tree;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.dom4j.Attribute;
/*      */ import org.dom4j.Branch;
/*      */ import org.dom4j.Document;
/*      */ import org.dom4j.DocumentFactory;
/*      */ import org.dom4j.Element;
/*      */ import org.dom4j.IllegalAddException;
/*      */ import org.dom4j.Namespace;
/*      */ import org.dom4j.Node;
/*      */ import org.dom4j.ProcessingInstruction;
/*      */ import org.dom4j.QName;
/*      */ 
/*      */ public class DefaultElement extends AbstractElement
/*      */ {
/*   36 */   private static final transient DocumentFactory DOCUMENT_FACTORY = DocumentFactory.getInstance();
/*      */   private QName qname;
/*      */   private Branch parentBranch;
/*      */   private Object content;
/*      */   private Object attributes;
/*      */ 
/*      */   public DefaultElement(String name)
/*      */   {
/*   61 */     this.qname = DOCUMENT_FACTORY.createQName(name);
/*      */   }
/*      */ 
/*      */   public DefaultElement(QName qname) {
/*   65 */     this.qname = qname;
/*      */   }
/*      */ 
/*      */   public DefaultElement(QName qname, int attributeCount) {
/*   69 */     this.qname = qname;
/*      */ 
/*   71 */     if (attributeCount > 1)
/*   72 */       this.attributes = new ArrayList(attributeCount);
/*      */   }
/*      */ 
/*      */   public DefaultElement(String name, Namespace namespace)
/*      */   {
/*   77 */     this.qname = DOCUMENT_FACTORY.createQName(name, namespace);
/*      */   }
/*      */ 
/*      */   public Element getParent() {
/*   81 */     Element result = null;
/*      */ 
/*   83 */     if ((this.parentBranch instanceof Element)) {
/*   84 */       result = (Element)this.parentBranch;
/*      */     }
/*      */ 
/*   87 */     return result;
/*      */   }
/*      */ 
/*      */   public void setParent(Element parent) {
/*   91 */     if (((this.parentBranch instanceof Element)) || (parent != null))
/*   92 */       this.parentBranch = parent;
/*      */   }
/*      */ 
/*      */   public Document getDocument()
/*      */   {
/*   97 */     if ((this.parentBranch instanceof Document))
/*   98 */       return (Document)this.parentBranch;
/*   99 */     if ((this.parentBranch instanceof Element)) {
/*  100 */       Element parent = (Element)this.parentBranch;
/*      */ 
/*  102 */       return parent.getDocument();
/*      */     }
/*      */ 
/*  105 */     return null;
/*      */   }
/*      */ 
/*      */   public void setDocument(Document document) {
/*  109 */     if (((this.parentBranch instanceof Document)) || (document != null))
/*  110 */       this.parentBranch = document;
/*      */   }
/*      */ 
/*      */   public boolean supportsParent()
/*      */   {
/*  115 */     return true;
/*      */   }
/*      */ 
/*      */   public QName getQName() {
/*  119 */     return this.qname;
/*      */   }
/*      */ 
/*      */   public void setQName(QName name) {
/*  123 */     this.qname = name;
/*      */   }
/*      */ 
/*      */   public String getText() {
/*  127 */     Object contentShadow = this.content;
/*      */ 
/*  129 */     if ((contentShadow instanceof List)) {
/*  130 */       return super.getText();
/*      */     }
/*  132 */     if (contentShadow != null) {
/*  133 */       return getContentAsText(contentShadow);
/*      */     }
/*  135 */     return "";
/*      */   }
/*      */ 
/*      */   public String getStringValue()
/*      */   {
/*  141 */     Object contentShadow = this.content;
/*      */ 
/*  143 */     if ((contentShadow instanceof List)) {
/*  144 */       List list = (List)contentShadow;
/*      */ 
/*  146 */       int size = list.size();
/*      */ 
/*  148 */       if (size > 0) {
/*  149 */         if (size == 1)
/*      */         {
/*  151 */           return getContentAsStringValue(list.get(0));
/*      */         }
/*  153 */         StringBuffer buffer = new StringBuffer();
/*      */ 
/*  155 */         for (int i = 0; i < size; i++) {
/*  156 */           Object node = list.get(i);
/*      */ 
/*  158 */           String string = getContentAsStringValue(node);
/*      */ 
/*  160 */           if (string.length() > 0)
/*      */           {
/*  167 */             buffer.append(string);
/*      */           }
/*      */         }
/*      */ 
/*  171 */         return buffer.toString();
/*      */       }
/*      */ 
/*      */     }
/*  175 */     else if (contentShadow != null) {
/*  176 */       return getContentAsStringValue(contentShadow);
/*      */     }
/*      */ 
/*  180 */     return "";
/*      */   }
/*      */ 
/*      */   public Object clone() {
/*  184 */     DefaultElement answer = (DefaultElement)super.clone();
/*      */ 
/*  186 */     if (answer != this) {
/*  187 */       answer.content = null;
/*      */ 
/*  189 */       answer.attributes = null;
/*      */ 
/*  191 */       answer.appendAttributes(this);
/*      */ 
/*  193 */       answer.appendContent(this);
/*      */     }
/*      */ 
/*  196 */     return answer;
/*      */   }
/*      */ 
/*      */   public Namespace getNamespaceForPrefix(String prefix) {
/*  200 */     if (prefix == null) {
/*  201 */       prefix = "";
/*      */     }
/*      */ 
/*  204 */     if (prefix.equals(getNamespacePrefix()))
/*  205 */       return getNamespace();
/*  206 */     if (prefix.equals("xml")) {
/*  207 */       return Namespace.XML_NAMESPACE;
/*      */     }
/*  209 */     Object contentShadow = this.content;
/*      */ 
/*  211 */     if ((contentShadow instanceof List)) {
/*  212 */       List list = (List)contentShadow;
/*      */ 
/*  214 */       int size = list.size();
/*      */ 
/*  216 */       for (int i = 0; i < size; i++) {
/*  217 */         Object object = list.get(i);
/*      */ 
/*  219 */         if ((object instanceof Namespace)) {
/*  220 */           Namespace namespace = (Namespace)object;
/*      */ 
/*  222 */           if (prefix.equals(namespace.getPrefix()))
/*  223 */             return namespace;
/*      */         }
/*      */       }
/*      */     }
/*  227 */     else if ((contentShadow instanceof Namespace)) {
/*  228 */       Namespace namespace = (Namespace)contentShadow;
/*      */ 
/*  230 */       if (prefix.equals(namespace.getPrefix())) {
/*  231 */         return namespace;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  236 */     Element parent = getParent();
/*      */ 
/*  238 */     if (parent != null) {
/*  239 */       Namespace answer = parent.getNamespaceForPrefix(prefix);
/*      */ 
/*  241 */       if (answer != null) {
/*  242 */         return answer;
/*      */       }
/*      */     }
/*      */ 
/*  246 */     if ((prefix == null) || (prefix.length() <= 0)) {
/*  247 */       return Namespace.NO_NAMESPACE;
/*      */     }
/*      */ 
/*  250 */     return null;
/*      */   }
/*      */ 
/*      */   public Namespace getNamespaceForURI(String uri) {
/*  254 */     if ((uri == null) || (uri.length() <= 0))
/*  255 */       return Namespace.NO_NAMESPACE;
/*  256 */     if (uri.equals(getNamespaceURI())) {
/*  257 */       return getNamespace();
/*      */     }
/*  259 */     Object contentShadow = this.content;
/*      */ 
/*  261 */     if ((contentShadow instanceof List)) {
/*  262 */       List list = (List)contentShadow;
/*      */ 
/*  264 */       int size = list.size();
/*      */ 
/*  266 */       for (int i = 0; i < size; i++) {
/*  267 */         Object object = list.get(i);
/*      */ 
/*  269 */         if ((object instanceof Namespace)) {
/*  270 */           Namespace namespace = (Namespace)object;
/*      */ 
/*  272 */           if (uri.equals(namespace.getURI()))
/*  273 */             return namespace;
/*      */         }
/*      */       }
/*      */     }
/*  277 */     else if ((contentShadow instanceof Namespace)) {
/*  278 */       Namespace namespace = (Namespace)contentShadow;
/*      */ 
/*  280 */       if (uri.equals(namespace.getURI())) {
/*  281 */         return namespace;
/*      */       }
/*      */     }
/*      */ 
/*  285 */     Element parent = getParent();
/*      */ 
/*  287 */     if (parent != null) {
/*  288 */       return parent.getNamespaceForURI(uri);
/*      */     }
/*      */ 
/*  291 */     return null;
/*      */   }
/*      */ 
/*      */   public List declaredNamespaces()
/*      */   {
/*  296 */     BackedList answer = createResultList();
/*      */ 
/*  303 */     Object contentShadow = this.content;
/*      */ 
/*  305 */     if ((contentShadow instanceof List)) {
/*  306 */       List list = (List)contentShadow;
/*      */ 
/*  308 */       int size = list.size();
/*      */ 
/*  310 */       for (int i = 0; i < size; i++) {
/*  311 */         Object object = list.get(i);
/*      */ 
/*  313 */         if ((object instanceof Namespace)) {
/*  314 */           answer.addLocal(object);
/*      */         }
/*      */       }
/*      */     }
/*  318 */     else if ((contentShadow instanceof Namespace)) {
/*  319 */       answer.addLocal(contentShadow);
/*      */     }
/*      */ 
/*  323 */     return answer;
/*      */   }
/*      */ 
/*      */   public List additionalNamespaces() {
/*  327 */     Object contentShadow = this.content;
/*      */ 
/*  329 */     if ((contentShadow instanceof List)) {
/*  330 */       List list = (List)contentShadow;
/*      */ 
/*  332 */       int size = list.size();
/*      */ 
/*  334 */       BackedList answer = createResultList();
/*      */ 
/*  336 */       for (int i = 0; i < size; i++) {
/*  337 */         Object object = list.get(i);
/*      */ 
/*  339 */         if ((object instanceof Namespace)) {
/*  340 */           Namespace namespace = (Namespace)object;
/*      */ 
/*  342 */           if (!namespace.equals(getNamespace())) {
/*  343 */             answer.addLocal(namespace);
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  348 */       return answer;
/*      */     }
/*  350 */     if ((contentShadow instanceof Namespace)) {
/*  351 */       Namespace namespace = (Namespace)contentShadow;
/*      */ 
/*  353 */       if (namespace.equals(getNamespace())) {
/*  354 */         return createEmptyList();
/*      */       }
/*      */ 
/*  357 */       return createSingleResultList(namespace);
/*      */     }
/*  359 */     return createEmptyList();
/*      */   }
/*      */ 
/*      */   public List additionalNamespaces(String defaultNamespaceURI)
/*      */   {
/*  365 */     Object contentShadow = this.content;
/*      */ 
/*  367 */     if ((contentShadow instanceof List)) {
/*  368 */       List list = (List)contentShadow;
/*      */ 
/*  370 */       BackedList answer = createResultList();
/*      */ 
/*  372 */       int size = list.size();
/*      */ 
/*  374 */       for (int i = 0; i < size; i++) {
/*  375 */         Object object = list.get(i);
/*      */ 
/*  377 */         if ((object instanceof Namespace)) {
/*  378 */           Namespace namespace = (Namespace)object;
/*      */ 
/*  380 */           if (!defaultNamespaceURI.equals(namespace.getURI())) {
/*  381 */             answer.addLocal(namespace);
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  386 */       return answer;
/*      */     }
/*  388 */     if ((contentShadow instanceof Namespace)) {
/*  389 */       Namespace namespace = (Namespace)contentShadow;
/*      */ 
/*  391 */       if (!defaultNamespaceURI.equals(namespace.getURI())) {
/*  392 */         return createSingleResultList(namespace);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  397 */     return createEmptyList();
/*      */   }
/*      */ 
/*      */   public List processingInstructions()
/*      */   {
/*  402 */     Object contentShadow = this.content;
/*      */ 
/*  404 */     if ((contentShadow instanceof List)) {
/*  405 */       List list = (List)contentShadow;
/*      */ 
/*  407 */       BackedList answer = createResultList();
/*      */ 
/*  409 */       int size = list.size();
/*      */ 
/*  411 */       for (int i = 0; i < size; i++) {
/*  412 */         Object object = list.get(i);
/*      */ 
/*  414 */         if ((object instanceof ProcessingInstruction)) {
/*  415 */           answer.addLocal(object);
/*      */         }
/*      */       }
/*      */ 
/*  419 */       return answer;
/*      */     }
/*  421 */     if ((contentShadow instanceof ProcessingInstruction)) {
/*  422 */       return createSingleResultList(contentShadow);
/*      */     }
/*      */ 
/*  425 */     return createEmptyList();
/*      */   }
/*      */ 
/*      */   public List processingInstructions(String target)
/*      */   {
/*  430 */     Object shadow = this.content;
/*      */ 
/*  432 */     if ((shadow instanceof List)) {
/*  433 */       List list = (List)shadow;
/*      */ 
/*  435 */       BackedList answer = createResultList();
/*      */ 
/*  437 */       int size = list.size();
/*      */ 
/*  439 */       for (int i = 0; i < size; i++) {
/*  440 */         Object object = list.get(i);
/*      */ 
/*  442 */         if ((object instanceof ProcessingInstruction)) {
/*  443 */           ProcessingInstruction pi = (ProcessingInstruction)object;
/*      */ 
/*  445 */           if (target.equals(pi.getName())) {
/*  446 */             answer.addLocal(pi);
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  451 */       return answer;
/*      */     }
/*  453 */     if ((shadow instanceof ProcessingInstruction)) {
/*  454 */       ProcessingInstruction pi = (ProcessingInstruction)shadow;
/*      */ 
/*  456 */       if (target.equals(pi.getName())) {
/*  457 */         return createSingleResultList(pi);
/*      */       }
/*      */     }
/*      */ 
/*  461 */     return createEmptyList();
/*      */   }
/*      */ 
/*      */   public ProcessingInstruction processingInstruction(String target)
/*      */   {
/*  466 */     Object shadow = this.content;
/*      */ 
/*  468 */     if ((shadow instanceof List)) {
/*  469 */       List list = (List)shadow;
/*      */ 
/*  471 */       int size = list.size();
/*      */ 
/*  473 */       for (int i = 0; i < size; i++) {
/*  474 */         Object object = list.get(i);
/*      */ 
/*  476 */         if ((object instanceof ProcessingInstruction)) {
/*  477 */           ProcessingInstruction pi = (ProcessingInstruction)object;
/*      */ 
/*  479 */           if (target.equals(pi.getName())) {
/*  480 */             return pi;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  485 */     else if ((shadow instanceof ProcessingInstruction)) {
/*  486 */       ProcessingInstruction pi = (ProcessingInstruction)shadow;
/*      */ 
/*  488 */       if (target.equals(pi.getName())) {
/*  489 */         return pi;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  494 */     return null;
/*      */   }
/*      */ 
/*      */   public boolean removeProcessingInstruction(String target) {
/*  498 */     Object shadow = this.content;
/*      */     Iterator iter;
/*  500 */     if ((shadow instanceof List)) {
/*  501 */       List list = (List)shadow;
/*      */ 
/*  503 */       for (iter = list.iterator(); iter.hasNext(); ) {
/*  504 */         Object object = iter.next();
/*      */ 
/*  506 */         if ((object instanceof ProcessingInstruction)) {
/*  507 */           ProcessingInstruction pi = (ProcessingInstruction)object;
/*      */ 
/*  509 */           if (target.equals(pi.getName())) {
/*  510 */             iter.remove();
/*      */ 
/*  512 */             return true;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  517 */     else if ((shadow instanceof ProcessingInstruction)) {
/*  518 */       ProcessingInstruction pi = (ProcessingInstruction)shadow;
/*      */ 
/*  520 */       if (target.equals(pi.getName())) {
/*  521 */         this.content = null;
/*      */ 
/*  523 */         return true;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  528 */     return false;
/*      */   }
/*      */ 
/*      */   public Element element(String name) {
/*  532 */     Object contentShadow = this.content;
/*      */ 
/*  534 */     if ((contentShadow instanceof List)) {
/*  535 */       List list = (List)contentShadow;
/*      */ 
/*  537 */       int size = list.size();
/*      */ 
/*  539 */       for (int i = 0; i < size; i++) {
/*  540 */         Object object = list.get(i);
/*      */ 
/*  542 */         if ((object instanceof Element)) {
/*  543 */           Element element = (Element)object;
/*      */ 
/*  545 */           if (name.equals(element.getName())) {
/*  546 */             return element;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  551 */     else if ((contentShadow instanceof Element)) {
/*  552 */       Element element = (Element)contentShadow;
/*      */ 
/*  554 */       if (name.equals(element.getName())) {
/*  555 */         return element;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  560 */     return null;
/*      */   }
/*      */ 
/*      */   public Element element(QName qName) {
/*  564 */     Object contentShadow = this.content;
/*      */ 
/*  566 */     if ((contentShadow instanceof List)) {
/*  567 */       List list = (List)contentShadow;
/*      */ 
/*  569 */       int size = list.size();
/*      */ 
/*  571 */       for (int i = 0; i < size; i++) {
/*  572 */         Object object = list.get(i);
/*      */ 
/*  574 */         if ((object instanceof Element)) {
/*  575 */           Element element = (Element)object;
/*      */ 
/*  577 */           if (qName.equals(element.getQName())) {
/*  578 */             return element;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  583 */     else if ((contentShadow instanceof Element)) {
/*  584 */       Element element = (Element)contentShadow;
/*      */ 
/*  586 */       if (qName.equals(element.getQName())) {
/*  587 */         return element;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  592 */     return null;
/*      */   }
/*      */ 
/*      */   public Element element(String name, Namespace namespace) {
/*  596 */     return element(getDocumentFactory().createQName(name, namespace));
/*      */   }
/*      */ 
/*      */   public void setContent(List content) {
/*  600 */     contentRemoved();
/*      */ 
/*  602 */     if ((content instanceof ContentListFacade)) {
/*  603 */       content = ((ContentListFacade)content).getBackingList();
/*      */     }
/*      */ 
/*  606 */     if (content == null) {
/*  607 */       this.content = null;
/*      */     } else {
/*  609 */       int size = content.size();
/*      */ 
/*  611 */       List newContent = createContentList(size);
/*      */ 
/*  613 */       for (int i = 0; i < size; i++) {
/*  614 */         Object object = content.get(i);
/*      */ 
/*  616 */         if ((object instanceof Node)) {
/*  617 */           Node node = (Node)object;
/*  618 */           Element parent = node.getParent();
/*      */ 
/*  620 */           if ((parent != null) && (parent != this)) {
/*  621 */             node = (Node)node.clone();
/*      */           }
/*      */ 
/*  624 */           newContent.add(node);
/*  625 */           childAdded(node);
/*  626 */         } else if (object != null) {
/*  627 */           String text = object.toString();
/*  628 */           Node node = getDocumentFactory().createText(text);
/*  629 */           newContent.add(node);
/*  630 */           childAdded(node);
/*      */         }
/*      */       }
/*      */ 
/*  634 */       this.content = newContent;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void clearContent() {
/*  639 */     if (this.content != null) {
/*  640 */       contentRemoved();
/*      */ 
/*  642 */       this.content = null;
/*      */     }
/*      */   }
/*      */ 
/*      */   public Node node(int index) {
/*  647 */     if (index >= 0) {
/*  648 */       Object contentShadow = this.content;
/*      */       Object node;
/*      */       Object node;
/*  651 */       if ((contentShadow instanceof List)) {
/*  652 */         List list = (List)contentShadow;
/*      */ 
/*  654 */         if (index >= list.size()) {
/*  655 */           return null;
/*      */         }
/*      */ 
/*  658 */         node = list.get(index);
/*      */       } else {
/*  660 */         node = index == 0 ? contentShadow : null;
/*      */       }
/*      */ 
/*  663 */       if (node != null) {
/*  664 */         if ((node instanceof Node)) {
/*  665 */           return (Node)node;
/*      */         }
/*  667 */         return new DefaultText(node.toString());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  672 */     return null;
/*      */   }
/*      */ 
/*      */   public int indexOf(Node node) {
/*  676 */     Object contentShadow = this.content;
/*      */ 
/*  678 */     if ((contentShadow instanceof List)) {
/*  679 */       List list = (List)contentShadow;
/*      */ 
/*  681 */       return list.indexOf(node);
/*      */     }
/*  683 */     if ((contentShadow != null) && (contentShadow.equals(node))) {
/*  684 */       return 0;
/*      */     }
/*  686 */     return -1;
/*      */   }
/*      */ 
/*      */   public int nodeCount()
/*      */   {
/*  692 */     Object contentShadow = this.content;
/*      */ 
/*  694 */     if ((contentShadow instanceof List)) {
/*  695 */       List list = (List)contentShadow;
/*      */ 
/*  697 */       return list.size();
/*      */     }
/*  699 */     return contentShadow != null ? 1 : 0;
/*      */   }
/*      */ 
/*      */   public Iterator nodeIterator()
/*      */   {
/*  704 */     Object contentShadow = this.content;
/*      */ 
/*  706 */     if ((contentShadow instanceof List)) {
/*  707 */       List list = (List)contentShadow;
/*      */ 
/*  709 */       return list.iterator();
/*      */     }
/*  711 */     if (contentShadow != null) {
/*  712 */       return createSingleIterator(contentShadow);
/*      */     }
/*  714 */     return EMPTY_ITERATOR;
/*      */   }
/*      */ 
/*      */   public List attributes()
/*      */   {
/*  720 */     return new ContentListFacade(this, attributeList());
/*      */   }
/*      */ 
/*      */   public void setAttributes(List attributes) {
/*  724 */     if ((attributes instanceof ContentListFacade)) {
/*  725 */       attributes = ((ContentListFacade)attributes).getBackingList();
/*      */     }
/*      */ 
/*  728 */     this.attributes = attributes;
/*      */   }
/*      */ 
/*      */   public Iterator attributeIterator() {
/*  732 */     Object attributesShadow = this.attributes;
/*      */ 
/*  734 */     if ((attributesShadow instanceof List)) {
/*  735 */       List list = (List)attributesShadow;
/*      */ 
/*  737 */       return list.iterator();
/*  738 */     }if (attributesShadow != null) {
/*  739 */       return createSingleIterator(attributesShadow);
/*      */     }
/*  741 */     return EMPTY_ITERATOR;
/*      */   }
/*      */ 
/*      */   public Attribute attribute(int index)
/*      */   {
/*  746 */     Object attributesShadow = this.attributes;
/*      */ 
/*  748 */     if ((attributesShadow instanceof List)) {
/*  749 */       List list = (List)attributesShadow;
/*      */ 
/*  751 */       return (Attribute)list.get(index);
/*  752 */     }if ((attributesShadow != null) && (index == 0)) {
/*  753 */       return (Attribute)attributesShadow;
/*      */     }
/*  755 */     return null;
/*      */   }
/*      */ 
/*      */   public int attributeCount()
/*      */   {
/*  760 */     Object attributesShadow = this.attributes;
/*      */ 
/*  762 */     if ((attributesShadow instanceof List)) {
/*  763 */       List list = (List)attributesShadow;
/*      */ 
/*  765 */       return list.size();
/*      */     }
/*  767 */     return attributesShadow != null ? 1 : 0;
/*      */   }
/*      */ 
/*      */   public Attribute attribute(String name)
/*      */   {
/*  772 */     Object attributesShadow = this.attributes;
/*      */ 
/*  774 */     if ((attributesShadow instanceof List)) {
/*  775 */       List list = (List)attributesShadow;
/*      */ 
/*  777 */       int size = list.size();
/*      */ 
/*  779 */       for (int i = 0; i < size; i++) {
/*  780 */         Attribute attribute = (Attribute)list.get(i);
/*      */ 
/*  782 */         if (name.equals(attribute.getName()))
/*  783 */           return attribute;
/*      */       }
/*      */     }
/*  786 */     else if (attributesShadow != null) {
/*  787 */       Attribute attribute = (Attribute)attributesShadow;
/*      */ 
/*  789 */       if (name.equals(attribute.getName())) {
/*  790 */         return attribute;
/*      */       }
/*      */     }
/*      */ 
/*  794 */     return null;
/*      */   }
/*      */ 
/*      */   public Attribute attribute(QName qName) {
/*  798 */     Object attributesShadow = this.attributes;
/*      */ 
/*  800 */     if ((attributesShadow instanceof List)) {
/*  801 */       List list = (List)attributesShadow;
/*      */ 
/*  803 */       int size = list.size();
/*      */ 
/*  805 */       for (int i = 0; i < size; i++) {
/*  806 */         Attribute attribute = (Attribute)list.get(i);
/*      */ 
/*  808 */         if (qName.equals(attribute.getQName()))
/*  809 */           return attribute;
/*      */       }
/*      */     }
/*  812 */     else if (attributesShadow != null) {
/*  813 */       Attribute attribute = (Attribute)attributesShadow;
/*      */ 
/*  815 */       if (qName.equals(attribute.getQName())) {
/*  816 */         return attribute;
/*      */       }
/*      */     }
/*      */ 
/*  820 */     return null;
/*      */   }
/*      */ 
/*      */   public Attribute attribute(String name, Namespace namespace) {
/*  824 */     return attribute(getDocumentFactory().createQName(name, namespace));
/*      */   }
/*      */ 
/*      */   public void add(Attribute attribute) {
/*  828 */     if (attribute.getParent() != null) {
/*  829 */       String message = "The Attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"";
/*      */ 
/*  832 */       throw new IllegalAddException(this, attribute, message);
/*      */     }
/*      */ 
/*  835 */     if (attribute.getValue() == null)
/*      */     {
/*  839 */       Attribute oldAttribute = attribute(attribute.getQName());
/*      */ 
/*  841 */       if (oldAttribute != null)
/*  842 */         remove(oldAttribute);
/*      */     }
/*      */     else {
/*  845 */       if (this.attributes == null)
/*  846 */         this.attributes = attribute;
/*      */       else {
/*  848 */         attributeList().add(attribute);
/*      */       }
/*      */ 
/*  851 */       childAdded(attribute);
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean remove(Attribute attribute) {
/*  856 */     boolean answer = false;
/*  857 */     Object attributesShadow = this.attributes;
/*      */ 
/*  859 */     if ((attributesShadow instanceof List)) {
/*  860 */       List list = (List)attributesShadow;
/*      */ 
/*  862 */       answer = list.remove(attribute);
/*      */ 
/*  864 */       if (!answer)
/*      */       {
/*  866 */         Attribute copy = attribute(attribute.getQName());
/*      */ 
/*  868 */         if (copy != null) {
/*  869 */           list.remove(copy);
/*      */ 
/*  871 */           answer = true;
/*      */         }
/*      */       }
/*  874 */     } else if (attributesShadow != null) {
/*  875 */       if (attribute.equals(attributesShadow)) {
/*  876 */         this.attributes = null;
/*      */ 
/*  878 */         answer = true;
/*      */       }
/*      */       else {
/*  881 */         Attribute other = (Attribute)attributesShadow;
/*      */ 
/*  883 */         if (attribute.getQName().equals(other.getQName())) {
/*  884 */           this.attributes = null;
/*      */ 
/*  886 */           answer = true;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  891 */     if (answer) {
/*  892 */       childRemoved(attribute);
/*      */     }
/*      */ 
/*  895 */     return answer;
/*      */   }
/*      */ 
/*      */   protected void addNewNode(Node node)
/*      */   {
/*  901 */     Object contentShadow = this.content;
/*      */ 
/*  903 */     if (contentShadow == null) {
/*  904 */       this.content = node;
/*      */     }
/*  906 */     else if ((contentShadow instanceof List)) {
/*  907 */       List list = (List)contentShadow;
/*      */ 
/*  909 */       list.add(node);
/*      */     } else {
/*  911 */       List list = createContentList();
/*      */ 
/*  913 */       list.add(contentShadow);
/*      */ 
/*  915 */       list.add(node);
/*      */ 
/*  917 */       this.content = list;
/*      */     }
/*      */ 
/*  921 */     childAdded(node);
/*      */   }
/*      */ 
/*      */   protected boolean removeNode(Node node) {
/*  925 */     boolean answer = false;
/*  926 */     Object contentShadow = this.content;
/*      */ 
/*  928 */     if (contentShadow != null) {
/*  929 */       if (contentShadow == node) {
/*  930 */         this.content = null;
/*      */ 
/*  932 */         answer = true;
/*  933 */       } else if ((contentShadow instanceof List)) {
/*  934 */         List list = (List)contentShadow;
/*      */ 
/*  936 */         answer = list.remove(node);
/*      */       }
/*      */     }
/*      */ 
/*  940 */     if (answer) {
/*  941 */       childRemoved(node);
/*      */     }
/*      */ 
/*  944 */     return answer;
/*      */   }
/*      */ 
/*      */   protected List contentList() {
/*  948 */     Object contentShadow = this.content;
/*      */ 
/*  950 */     if ((contentShadow instanceof List)) {
/*  951 */       return (List)contentShadow;
/*      */     }
/*  953 */     List list = createContentList();
/*      */ 
/*  955 */     if (contentShadow != null) {
/*  956 */       list.add(contentShadow);
/*      */     }
/*      */ 
/*  959 */     this.content = list;
/*      */ 
/*  961 */     return list;
/*      */   }
/*      */ 
/*      */   protected List attributeList()
/*      */   {
/*  966 */     Object attributesShadow = this.attributes;
/*      */ 
/*  968 */     if ((attributesShadow instanceof List))
/*  969 */       return (List)attributesShadow;
/*  970 */     if (attributesShadow != null) {
/*  971 */       List list = createAttributeList();
/*      */ 
/*  973 */       list.add(attributesShadow);
/*      */ 
/*  975 */       this.attributes = list;
/*      */ 
/*  977 */       return list;
/*      */     }
/*  979 */     List list = createAttributeList();
/*      */ 
/*  981 */     this.attributes = list;
/*      */ 
/*  983 */     return list;
/*      */   }
/*      */ 
/*      */   protected List attributeList(int size)
/*      */   {
/*  988 */     Object attributesShadow = this.attributes;
/*      */ 
/*  990 */     if ((attributesShadow instanceof List))
/*  991 */       return (List)attributesShadow;
/*  992 */     if (attributesShadow != null) {
/*  993 */       List list = createAttributeList(size);
/*      */ 
/*  995 */       list.add(attributesShadow);
/*      */ 
/*  997 */       this.attributes = list;
/*      */ 
/*  999 */       return list;
/*      */     }
/* 1001 */     List list = createAttributeList(size);
/*      */ 
/* 1003 */     this.attributes = list;
/*      */ 
/* 1005 */     return list;
/*      */   }
/*      */ 
/*      */   protected void setAttributeList(List attributeList)
/*      */   {
/* 1010 */     this.attributes = attributeList;
/*      */   }
/*      */ 
/*      */   protected DocumentFactory getDocumentFactory() {
/* 1014 */     DocumentFactory factory = this.qname.getDocumentFactory();
/*      */ 
/* 1016 */     return factory != null ? factory : DOCUMENT_FACTORY;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultElement
 * JD-Core Version:    0.6.2
 */