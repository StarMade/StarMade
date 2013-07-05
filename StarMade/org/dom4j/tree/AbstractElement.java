/*      */ package org.dom4j.tree;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.StringWriter;
/*      */ import java.io.Writer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.dom4j.Attribute;
/*      */ import org.dom4j.CDATA;
/*      */ import org.dom4j.CharacterData;
/*      */ import org.dom4j.Comment;
/*      */ import org.dom4j.Document;
/*      */ import org.dom4j.DocumentFactory;
/*      */ import org.dom4j.Element;
/*      */ import org.dom4j.Entity;
/*      */ import org.dom4j.IllegalAddException;
/*      */ import org.dom4j.Namespace;
/*      */ import org.dom4j.Node;
/*      */ import org.dom4j.ProcessingInstruction;
/*      */ import org.dom4j.QName;
/*      */ import org.dom4j.Text;
/*      */ import org.dom4j.Visitor;
/*      */ import org.dom4j.io.OutputFormat;
/*      */ import org.dom4j.io.XMLWriter;
/*      */ import org.xml.sax.Attributes;
/*      */ 
/*      */ public abstract class AbstractElement extends AbstractBranch
/*      */   implements Element
/*      */ {
/*   51 */   private static final DocumentFactory DOCUMENT_FACTORY = DocumentFactory.getInstance();
/*      */ 
/*   54 */   protected static final List EMPTY_LIST = Collections.EMPTY_LIST;
/*      */ 
/*   56 */   protected static final Iterator EMPTY_ITERATOR = EMPTY_LIST.iterator();
/*      */   protected static final boolean VERBOSE_TOSTRING = false;
/*      */   protected static final boolean USE_STRINGVALUE_SEPARATOR = false;
/*      */ 
/*      */   public short getNodeType()
/*      */   {
/*   66 */     return 1;
/*      */   }
/*      */ 
/*      */   public boolean isRootElement() {
/*   70 */     Document document = getDocument();
/*      */ 
/*   72 */     if (document != null) {
/*   73 */       Element root = document.getRootElement();
/*      */ 
/*   75 */       if (root == this) {
/*   76 */         return true;
/*      */       }
/*      */     }
/*      */ 
/*   80 */     return false;
/*      */   }
/*      */ 
/*      */   public void setName(String name) {
/*   84 */     setQName(getDocumentFactory().createQName(name));
/*      */   }
/*      */ 
/*      */   public void setNamespace(Namespace namespace) {
/*   88 */     setQName(getDocumentFactory().createQName(getName(), namespace));
/*      */   }
/*      */ 
/*      */   public String getXPathNameStep()
/*      */   {
/*  100 */     String uri = getNamespaceURI();
/*      */ 
/*  102 */     if ((uri == null) || (uri.length() == 0)) {
/*  103 */       return getName();
/*      */     }
/*      */ 
/*  106 */     String prefix = getNamespacePrefix();
/*      */ 
/*  108 */     if ((prefix == null) || (prefix.length() == 0)) {
/*  109 */       return "*[name()='" + getName() + "']";
/*      */     }
/*      */ 
/*  112 */     return getQualifiedName();
/*      */   }
/*      */ 
/*      */   public String getPath(Element context) {
/*  116 */     if (this == context) {
/*  117 */       return ".";
/*      */     }
/*      */ 
/*  120 */     Element parent = getParent();
/*      */ 
/*  122 */     if (parent == null)
/*  123 */       return "/" + getXPathNameStep();
/*  124 */     if (parent == context) {
/*  125 */       return getXPathNameStep();
/*      */     }
/*      */ 
/*  128 */     return parent.getPath(context) + "/" + getXPathNameStep();
/*      */   }
/*      */ 
/*      */   public String getUniquePath(Element context) {
/*  132 */     Element parent = getParent();
/*      */ 
/*  134 */     if (parent == null) {
/*  135 */       return "/" + getXPathNameStep();
/*      */     }
/*      */ 
/*  138 */     StringBuffer buffer = new StringBuffer();
/*      */ 
/*  140 */     if (parent != context) {
/*  141 */       buffer.append(parent.getUniquePath(context));
/*      */ 
/*  143 */       buffer.append("/");
/*      */     }
/*      */ 
/*  146 */     buffer.append(getXPathNameStep());
/*      */ 
/*  148 */     List mySiblings = parent.elements(getQName());
/*      */ 
/*  150 */     if (mySiblings.size() > 1) {
/*  151 */       int idx = mySiblings.indexOf(this);
/*      */ 
/*  153 */       if (idx >= 0) {
/*  154 */         buffer.append("[");
/*      */ 
/*  156 */         buffer.append(Integer.toString(++idx));
/*      */ 
/*  158 */         buffer.append("]");
/*      */       }
/*      */     }
/*      */ 
/*  162 */     return buffer.toString();
/*      */   }
/*      */ 
/*      */   public String asXML() {
/*      */     try {
/*  167 */       StringWriter out = new StringWriter();
/*  168 */       XMLWriter writer = new XMLWriter(out, new OutputFormat());
/*      */ 
/*  170 */       writer.write(this);
/*  171 */       writer.flush();
/*      */ 
/*  173 */       return out.toString();
/*      */     } catch (IOException e) {
/*  175 */       throw new RuntimeException("IOException while generating textual representation: " + e.getMessage());
/*      */     }
/*      */   }
/*      */ 
/*      */   public void write(Writer out) throws IOException
/*      */   {
/*  181 */     XMLWriter writer = new XMLWriter(out, new OutputFormat());
/*  182 */     writer.write(this);
/*      */   }
/*      */ 
/*      */   public void accept(Visitor visitor)
/*      */   {
/*  195 */     visitor.visit(this);
/*      */ 
/*  198 */     int i = 0; for (int size = attributeCount(); i < size; i++) {
/*  199 */       Attribute attribute = attribute(i);
/*      */ 
/*  201 */       visitor.visit(attribute);
/*      */     }
/*      */ 
/*  205 */     int i = 0; for (int size = nodeCount(); i < size; i++) {
/*  206 */       Node node = node(i);
/*      */ 
/*  208 */       node.accept(visitor);
/*      */     }
/*      */   }
/*      */ 
/*      */   public String toString() {
/*  213 */     String uri = getNamespaceURI();
/*      */ 
/*  215 */     if ((uri != null) && (uri.length() > 0))
/*      */     {
/*  221 */       return super.toString() + " [Element: <" + getQualifiedName() + " uri: " + uri + " attributes: " + attributeList() + "/>]";
/*      */     }
/*      */ 
/*  231 */     return super.toString() + " [Element: <" + getQualifiedName() + " attributes: " + attributeList() + "/>]";
/*      */   }
/*      */ 
/*      */   public Namespace getNamespace()
/*      */   {
/*  240 */     return getQName().getNamespace();
/*      */   }
/*      */ 
/*      */   public String getName() {
/*  244 */     return getQName().getName();
/*      */   }
/*      */ 
/*      */   public String getNamespacePrefix() {
/*  248 */     return getQName().getNamespacePrefix();
/*      */   }
/*      */ 
/*      */   public String getNamespaceURI() {
/*  252 */     return getQName().getNamespaceURI();
/*      */   }
/*      */ 
/*      */   public String getQualifiedName() {
/*  256 */     return getQName().getQualifiedName();
/*      */   }
/*      */ 
/*      */   public Object getData() {
/*  260 */     return getText();
/*      */   }
/*      */ 
/*      */   public void setData(Object data)
/*      */   {
/*      */   }
/*      */ 
/*      */   public Node node(int index)
/*      */   {
/*  270 */     if (index >= 0) {
/*  271 */       List list = contentList();
/*      */ 
/*  273 */       if (index >= list.size()) {
/*  274 */         return null;
/*      */       }
/*      */ 
/*  277 */       Object node = list.get(index);
/*      */ 
/*  279 */       if (node != null) {
/*  280 */         if ((node instanceof Node)) {
/*  281 */           return (Node)node;
/*      */         }
/*  283 */         return getDocumentFactory().createText(node.toString());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  288 */     return null;
/*      */   }
/*      */ 
/*      */   public int indexOf(Node node) {
/*  292 */     return contentList().indexOf(node);
/*      */   }
/*      */ 
/*      */   public int nodeCount() {
/*  296 */     return contentList().size();
/*      */   }
/*      */ 
/*      */   public Iterator nodeIterator() {
/*  300 */     return contentList().iterator();
/*      */   }
/*      */ 
/*      */   public Element element(String name)
/*      */   {
/*  306 */     List list = contentList();
/*      */ 
/*  308 */     int size = list.size();
/*      */ 
/*  310 */     for (int i = 0; i < size; i++) {
/*  311 */       Object object = list.get(i);
/*      */ 
/*  313 */       if ((object instanceof Element)) {
/*  314 */         Element element = (Element)object;
/*      */ 
/*  316 */         if (name.equals(element.getName())) {
/*  317 */           return element;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  322 */     return null;
/*      */   }
/*      */ 
/*      */   public Element element(QName qName) {
/*  326 */     List list = contentList();
/*      */ 
/*  328 */     int size = list.size();
/*      */ 
/*  330 */     for (int i = 0; i < size; i++) {
/*  331 */       Object object = list.get(i);
/*      */ 
/*  333 */       if ((object instanceof Element)) {
/*  334 */         Element element = (Element)object;
/*      */ 
/*  336 */         if (qName.equals(element.getQName())) {
/*  337 */           return element;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  342 */     return null;
/*      */   }
/*      */ 
/*      */   public Element element(String name, Namespace namespace) {
/*  346 */     return element(getDocumentFactory().createQName(name, namespace));
/*      */   }
/*      */ 
/*      */   public List elements() {
/*  350 */     List list = contentList();
/*      */ 
/*  352 */     BackedList answer = createResultList();
/*      */ 
/*  354 */     int size = list.size();
/*      */ 
/*  356 */     for (int i = 0; i < size; i++) {
/*  357 */       Object object = list.get(i);
/*      */ 
/*  359 */       if ((object instanceof Element)) {
/*  360 */         answer.addLocal(object);
/*      */       }
/*      */     }
/*      */ 
/*  364 */     return answer;
/*      */   }
/*      */ 
/*      */   public List elements(String name) {
/*  368 */     List list = contentList();
/*      */ 
/*  370 */     BackedList answer = createResultList();
/*      */ 
/*  372 */     int size = list.size();
/*      */ 
/*  374 */     for (int i = 0; i < size; i++) {
/*  375 */       Object object = list.get(i);
/*      */ 
/*  377 */       if ((object instanceof Element)) {
/*  378 */         Element element = (Element)object;
/*      */ 
/*  380 */         if (name.equals(element.getName())) {
/*  381 */           answer.addLocal(element);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  386 */     return answer;
/*      */   }
/*      */ 
/*      */   public List elements(QName qName) {
/*  390 */     List list = contentList();
/*      */ 
/*  392 */     BackedList answer = createResultList();
/*      */ 
/*  394 */     int size = list.size();
/*      */ 
/*  396 */     for (int i = 0; i < size; i++) {
/*  397 */       Object object = list.get(i);
/*      */ 
/*  399 */       if ((object instanceof Element)) {
/*  400 */         Element element = (Element)object;
/*      */ 
/*  402 */         if (qName.equals(element.getQName())) {
/*  403 */           answer.addLocal(element);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  408 */     return answer;
/*      */   }
/*      */ 
/*      */   public List elements(String name, Namespace namespace) {
/*  412 */     return elements(getDocumentFactory().createQName(name, namespace));
/*      */   }
/*      */ 
/*      */   public Iterator elementIterator() {
/*  416 */     List list = elements();
/*      */ 
/*  418 */     return list.iterator();
/*      */   }
/*      */ 
/*      */   public Iterator elementIterator(String name) {
/*  422 */     List list = elements(name);
/*      */ 
/*  424 */     return list.iterator();
/*      */   }
/*      */ 
/*      */   public Iterator elementIterator(QName qName) {
/*  428 */     List list = elements(qName);
/*      */ 
/*  430 */     return list.iterator();
/*      */   }
/*      */ 
/*      */   public Iterator elementIterator(String name, Namespace ns) {
/*  434 */     return elementIterator(getDocumentFactory().createQName(name, ns));
/*      */   }
/*      */ 
/*      */   public List attributes()
/*      */   {
/*  440 */     return new ContentListFacade(this, attributeList());
/*      */   }
/*      */ 
/*      */   public Iterator attributeIterator() {
/*  444 */     return attributeList().iterator();
/*      */   }
/*      */ 
/*      */   public Attribute attribute(int index) {
/*  448 */     return (Attribute)attributeList().get(index);
/*      */   }
/*      */ 
/*      */   public int attributeCount() {
/*  452 */     return attributeList().size();
/*      */   }
/*      */ 
/*      */   public Attribute attribute(String name) {
/*  456 */     List list = attributeList();
/*      */ 
/*  458 */     int size = list.size();
/*      */ 
/*  460 */     for (int i = 0; i < size; i++) {
/*  461 */       Attribute attribute = (Attribute)list.get(i);
/*      */ 
/*  463 */       if (name.equals(attribute.getName())) {
/*  464 */         return attribute;
/*      */       }
/*      */     }
/*      */ 
/*  468 */     return null;
/*      */   }
/*      */ 
/*      */   public Attribute attribute(QName qName) {
/*  472 */     List list = attributeList();
/*      */ 
/*  474 */     int size = list.size();
/*      */ 
/*  476 */     for (int i = 0; i < size; i++) {
/*  477 */       Attribute attribute = (Attribute)list.get(i);
/*      */ 
/*  479 */       if (qName.equals(attribute.getQName())) {
/*  480 */         return attribute;
/*      */       }
/*      */     }
/*      */ 
/*  484 */     return null;
/*      */   }
/*      */ 
/*      */   public Attribute attribute(String name, Namespace namespace) {
/*  488 */     return attribute(getDocumentFactory().createQName(name, namespace));
/*      */   }
/*      */ 
/*      */   public void setAttributes(Attributes attributes, NamespaceStack namespaceStack, boolean noNamespaceAttributes)
/*      */   {
/*  505 */     int size = attributes.getLength();
/*      */ 
/*  507 */     if (size > 0) {
/*  508 */       DocumentFactory factory = getDocumentFactory();
/*      */ 
/*  510 */       if (size == 1)
/*      */       {
/*  512 */         String name = attributes.getQName(0);
/*      */ 
/*  514 */         if ((noNamespaceAttributes) || (!name.startsWith("xmlns"))) {
/*  515 */           String attributeURI = attributes.getURI(0);
/*      */ 
/*  517 */           String attributeLocalName = attributes.getLocalName(0);
/*      */ 
/*  519 */           String attributeValue = attributes.getValue(0);
/*      */ 
/*  521 */           QName attributeQName = namespaceStack.getAttributeQName(attributeURI, attributeLocalName, name);
/*      */ 
/*  524 */           add(factory.createAttribute(this, attributeQName, attributeValue));
/*      */         }
/*      */       }
/*      */       else {
/*  528 */         List list = attributeList(size);
/*      */ 
/*  530 */         list.clear();
/*      */ 
/*  532 */         for (int i = 0; i < size; i++)
/*      */         {
/*  535 */           String attributeName = attributes.getQName(i);
/*      */ 
/*  537 */           if ((noNamespaceAttributes) || (!attributeName.startsWith("xmlns")))
/*      */           {
/*  539 */             String attributeURI = attributes.getURI(i);
/*      */ 
/*  541 */             String attributeLocalName = attributes.getLocalName(i);
/*      */ 
/*  543 */             String attributeValue = attributes.getValue(i);
/*      */ 
/*  545 */             QName attributeQName = namespaceStack.getAttributeQName(attributeURI, attributeLocalName, attributeName);
/*      */ 
/*  549 */             Attribute attribute = factory.createAttribute(this, attributeQName, attributeValue);
/*      */ 
/*  552 */             list.add(attribute);
/*      */ 
/*  554 */             childAdded(attribute);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public String attributeValue(String name) {
/*  562 */     Attribute attrib = attribute(name);
/*      */ 
/*  564 */     if (attrib == null) {
/*  565 */       return null;
/*      */     }
/*  567 */     return attrib.getValue();
/*      */   }
/*      */ 
/*      */   public String attributeValue(QName qName)
/*      */   {
/*  572 */     Attribute attrib = attribute(qName);
/*      */ 
/*  574 */     if (attrib == null) {
/*  575 */       return null;
/*      */     }
/*  577 */     return attrib.getValue();
/*      */   }
/*      */ 
/*      */   public String attributeValue(String name, String defaultValue)
/*      */   {
/*  582 */     String answer = attributeValue(name);
/*      */ 
/*  584 */     return answer != null ? answer : defaultValue;
/*      */   }
/*      */ 
/*      */   public String attributeValue(QName qName, String defaultValue) {
/*  588 */     String answer = attributeValue(qName);
/*      */ 
/*  590 */     return answer != null ? answer : defaultValue;
/*      */   }
/*      */ 
/*      */   /** @deprecated */
/*      */   public void setAttributeValue(String name, String value)
/*      */   {
/*  606 */     addAttribute(name, value);
/*      */   }
/*      */ 
/*      */   /** @deprecated */
/*      */   public void setAttributeValue(QName qName, String value)
/*      */   {
/*  622 */     addAttribute(qName, value);
/*      */   }
/*      */ 
/*      */   public void add(Attribute attribute) {
/*  626 */     if (attribute.getParent() != null) {
/*  627 */       String message = "The Attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"";
/*      */ 
/*  630 */       throw new IllegalAddException(this, attribute, message);
/*      */     }
/*      */ 
/*  633 */     if (attribute.getValue() == null)
/*      */     {
/*  637 */       Attribute oldAttribute = attribute(attribute.getQName());
/*      */ 
/*  639 */       if (oldAttribute != null)
/*  640 */         remove(oldAttribute);
/*      */     }
/*      */     else {
/*  643 */       attributeList().add(attribute);
/*      */ 
/*  645 */       childAdded(attribute);
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean remove(Attribute attribute) {
/*  650 */     List list = attributeList();
/*      */ 
/*  652 */     boolean answer = list.remove(attribute);
/*      */ 
/*  654 */     if (answer) {
/*  655 */       childRemoved(attribute);
/*      */     }
/*      */     else {
/*  658 */       Attribute copy = attribute(attribute.getQName());
/*      */ 
/*  660 */       if (copy != null) {
/*  661 */         list.remove(copy);
/*      */ 
/*  663 */         answer = true;
/*      */       }
/*      */     }
/*      */ 
/*  667 */     return answer;
/*      */   }
/*      */ 
/*      */   public List processingInstructions()
/*      */   {
/*  673 */     List list = contentList();
/*      */ 
/*  675 */     BackedList answer = createResultList();
/*      */ 
/*  677 */     int size = list.size();
/*      */ 
/*  679 */     for (int i = 0; i < size; i++) {
/*  680 */       Object object = list.get(i);
/*      */ 
/*  682 */       if ((object instanceof ProcessingInstruction)) {
/*  683 */         answer.addLocal(object);
/*      */       }
/*      */     }
/*      */ 
/*  687 */     return answer;
/*      */   }
/*      */ 
/*      */   public List processingInstructions(String target) {
/*  691 */     List list = contentList();
/*      */ 
/*  693 */     BackedList answer = createResultList();
/*      */ 
/*  695 */     int size = list.size();
/*      */ 
/*  697 */     for (int i = 0; i < size; i++) {
/*  698 */       Object object = list.get(i);
/*      */ 
/*  700 */       if ((object instanceof ProcessingInstruction)) {
/*  701 */         ProcessingInstruction pi = (ProcessingInstruction)object;
/*      */ 
/*  703 */         if (target.equals(pi.getName())) {
/*  704 */           answer.addLocal(pi);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  709 */     return answer;
/*      */   }
/*      */ 
/*      */   public ProcessingInstruction processingInstruction(String target) {
/*  713 */     List list = contentList();
/*      */ 
/*  715 */     int size = list.size();
/*      */ 
/*  717 */     for (int i = 0; i < size; i++) {
/*  718 */       Object object = list.get(i);
/*      */ 
/*  720 */       if ((object instanceof ProcessingInstruction)) {
/*  721 */         ProcessingInstruction pi = (ProcessingInstruction)object;
/*      */ 
/*  723 */         if (target.equals(pi.getName())) {
/*  724 */           return pi;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  729 */     return null;
/*      */   }
/*      */ 
/*      */   public boolean removeProcessingInstruction(String target) {
/*  733 */     List list = contentList();
/*      */ 
/*  735 */     for (Iterator iter = list.iterator(); iter.hasNext(); ) {
/*  736 */       Object object = iter.next();
/*      */ 
/*  738 */       if ((object instanceof ProcessingInstruction)) {
/*  739 */         ProcessingInstruction pi = (ProcessingInstruction)object;
/*      */ 
/*  741 */         if (target.equals(pi.getName())) {
/*  742 */           iter.remove();
/*      */ 
/*  744 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  749 */     return false;
/*      */   }
/*      */ 
/*      */   public Node getXPathResult(int index)
/*      */   {
/*  755 */     Node answer = node(index);
/*      */ 
/*  757 */     if ((answer != null) && (!answer.supportsParent())) {
/*  758 */       return answer.asXPathResult(this);
/*      */     }
/*      */ 
/*  761 */     return answer;
/*      */   }
/*      */ 
/*      */   public Element addAttribute(String name, String value)
/*      */   {
/*  766 */     Attribute attribute = attribute(name);
/*      */ 
/*  768 */     if (value != null) {
/*  769 */       if (attribute == null) {
/*  770 */         add(getDocumentFactory().createAttribute(this, name, value));
/*  771 */       } else if (attribute.isReadOnly()) {
/*  772 */         remove(attribute);
/*      */ 
/*  774 */         add(getDocumentFactory().createAttribute(this, name, value));
/*      */       } else {
/*  776 */         attribute.setValue(value);
/*      */       }
/*  778 */     } else if (attribute != null) {
/*  779 */       remove(attribute);
/*      */     }
/*      */ 
/*  782 */     return this;
/*      */   }
/*      */ 
/*      */   public Element addAttribute(QName qName, String value)
/*      */   {
/*  787 */     Attribute attribute = attribute(qName);
/*      */ 
/*  789 */     if (value != null) {
/*  790 */       if (attribute == null) {
/*  791 */         add(getDocumentFactory().createAttribute(this, qName, value));
/*  792 */       } else if (attribute.isReadOnly()) {
/*  793 */         remove(attribute);
/*      */ 
/*  795 */         add(getDocumentFactory().createAttribute(this, qName, value));
/*      */       } else {
/*  797 */         attribute.setValue(value);
/*      */       }
/*  799 */     } else if (attribute != null) {
/*  800 */       remove(attribute);
/*      */     }
/*      */ 
/*  803 */     return this;
/*      */   }
/*      */ 
/*      */   public Element addCDATA(String cdata) {
/*  807 */     CDATA node = getDocumentFactory().createCDATA(cdata);
/*      */ 
/*  809 */     addNewNode(node);
/*      */ 
/*  811 */     return this;
/*      */   }
/*      */ 
/*      */   public Element addComment(String comment) {
/*  815 */     Comment node = getDocumentFactory().createComment(comment);
/*      */ 
/*  817 */     addNewNode(node);
/*      */ 
/*  819 */     return this;
/*      */   }
/*      */ 
/*      */   public Element addElement(String name) {
/*  823 */     DocumentFactory factory = getDocumentFactory();
/*      */ 
/*  825 */     int index = name.indexOf(":");
/*      */ 
/*  827 */     String prefix = "";
/*      */ 
/*  829 */     String localName = name;
/*      */ 
/*  831 */     Namespace namespace = null;
/*      */ 
/*  833 */     if (index > 0) {
/*  834 */       prefix = name.substring(0, index);
/*      */ 
/*  836 */       localName = name.substring(index + 1);
/*      */ 
/*  838 */       namespace = getNamespaceForPrefix(prefix);
/*      */ 
/*  840 */       if (namespace == null) {
/*  841 */         throw new IllegalAddException("No such namespace prefix: " + prefix + " is in scope on: " + this + " so cannot add element: " + name);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  846 */       namespace = getNamespaceForPrefix("");
/*      */     }
/*      */     Element node;
/*      */     Element node;
/*  851 */     if (namespace != null) {
/*  852 */       QName qname = factory.createQName(localName, namespace);
/*      */ 
/*  854 */       node = factory.createElement(qname);
/*      */     } else {
/*  856 */       node = factory.createElement(name);
/*      */     }
/*      */ 
/*  859 */     addNewNode(node);
/*      */ 
/*  861 */     return node;
/*      */   }
/*      */ 
/*      */   public Element addEntity(String name, String text) {
/*  865 */     Entity node = getDocumentFactory().createEntity(name, text);
/*      */ 
/*  867 */     addNewNode(node);
/*      */ 
/*  869 */     return this;
/*      */   }
/*      */ 
/*      */   public Element addNamespace(String prefix, String uri) {
/*  873 */     Namespace node = getDocumentFactory().createNamespace(prefix, uri);
/*      */ 
/*  875 */     addNewNode(node);
/*      */ 
/*  877 */     return this;
/*      */   }
/*      */ 
/*      */   public Element addProcessingInstruction(String target, String data) {
/*  881 */     ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
/*      */ 
/*  884 */     addNewNode(node);
/*      */ 
/*  886 */     return this;
/*      */   }
/*      */ 
/*      */   public Element addProcessingInstruction(String target, Map data) {
/*  890 */     ProcessingInstruction node = getDocumentFactory().createProcessingInstruction(target, data);
/*      */ 
/*  893 */     addNewNode(node);
/*      */ 
/*  895 */     return this;
/*      */   }
/*      */ 
/*      */   public Element addText(String text) {
/*  899 */     Text node = getDocumentFactory().createText(text);
/*      */ 
/*  901 */     addNewNode(node);
/*      */ 
/*  903 */     return this;
/*      */   }
/*      */ 
/*      */   public void add(Node node)
/*      */   {
/*  908 */     switch (node.getNodeType()) {
/*      */     case 1:
/*  910 */       add((Element)node);
/*      */ 
/*  912 */       break;
/*      */     case 2:
/*  915 */       add((Attribute)node);
/*      */ 
/*  917 */       break;
/*      */     case 3:
/*  920 */       add((Text)node);
/*      */ 
/*  922 */       break;
/*      */     case 4:
/*  925 */       add((CDATA)node);
/*      */ 
/*  927 */       break;
/*      */     case 5:
/*  930 */       add((Entity)node);
/*      */ 
/*  932 */       break;
/*      */     case 7:
/*  935 */       add((ProcessingInstruction)node);
/*      */ 
/*  937 */       break;
/*      */     case 8:
/*  940 */       add((Comment)node);
/*      */ 
/*  942 */       break;
/*      */     case 13:
/*  949 */       add((Namespace)node);
/*      */ 
/*  951 */       break;
/*      */     case 6:
/*      */     case 9:
/*      */     case 10:
/*      */     case 11:
/*      */     case 12:
/*      */     default:
/*  954 */       invalidNodeTypeAddException(node);
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean remove(Node node) {
/*  959 */     switch (node.getNodeType()) {
/*      */     case 1:
/*  961 */       return remove((Element)node);
/*      */     case 2:
/*  964 */       return remove((Attribute)node);
/*      */     case 3:
/*  967 */       return remove((Text)node);
/*      */     case 4:
/*  970 */       return remove((CDATA)node);
/*      */     case 5:
/*  973 */       return remove((Entity)node);
/*      */     case 7:
/*  976 */       return remove((ProcessingInstruction)node);
/*      */     case 8:
/*  979 */       return remove((Comment)node);
/*      */     case 13:
/*  985 */       return remove((Namespace)node);
/*      */     case 6:
/*      */     case 9:
/*      */     case 10:
/*      */     case 11:
/*  988 */     case 12: } return false;
/*      */   }
/*      */ 
/*      */   public void add(CDATA cdata)
/*      */   {
/*  994 */     addNode(cdata);
/*      */   }
/*      */ 
/*      */   public void add(Comment comment) {
/*  998 */     addNode(comment);
/*      */   }
/*      */ 
/*      */   public void add(Element element) {
/* 1002 */     addNode(element);
/*      */   }
/*      */ 
/*      */   public void add(Entity entity) {
/* 1006 */     addNode(entity);
/*      */   }
/*      */ 
/*      */   public void add(Namespace namespace) {
/* 1010 */     addNode(namespace);
/*      */   }
/*      */ 
/*      */   public void add(ProcessingInstruction pi) {
/* 1014 */     addNode(pi);
/*      */   }
/*      */ 
/*      */   public void add(Text text) {
/* 1018 */     addNode(text);
/*      */   }
/*      */ 
/*      */   public boolean remove(CDATA cdata) {
/* 1022 */     return removeNode(cdata);
/*      */   }
/*      */ 
/*      */   public boolean remove(Comment comment) {
/* 1026 */     return removeNode(comment);
/*      */   }
/*      */ 
/*      */   public boolean remove(Element element) {
/* 1030 */     return removeNode(element);
/*      */   }
/*      */ 
/*      */   public boolean remove(Entity entity) {
/* 1034 */     return removeNode(entity);
/*      */   }
/*      */ 
/*      */   public boolean remove(Namespace namespace) {
/* 1038 */     return removeNode(namespace);
/*      */   }
/*      */ 
/*      */   public boolean remove(ProcessingInstruction pi) {
/* 1042 */     return removeNode(pi);
/*      */   }
/*      */ 
/*      */   public boolean remove(Text text) {
/* 1046 */     return removeNode(text);
/*      */   }
/*      */ 
/*      */   public boolean hasMixedContent()
/*      */   {
/* 1052 */     List content = contentList();
/*      */ 
/* 1054 */     if ((content == null) || (content.isEmpty()) || (content.size() < 2)) {
/* 1055 */       return false;
/*      */     }
/*      */ 
/* 1058 */     Class prevClass = null;
/*      */ 
/* 1060 */     for (Iterator iter = content.iterator(); iter.hasNext(); ) {
/* 1061 */       Object object = iter.next();
/*      */ 
/* 1063 */       Class newClass = object.getClass();
/*      */ 
/* 1065 */       if (newClass != prevClass) {
/* 1066 */         if (prevClass != null) {
/* 1067 */           return true;
/*      */         }
/*      */ 
/* 1070 */         prevClass = newClass;
/*      */       }
/*      */     }
/*      */ 
/* 1074 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean isTextOnly() {
/* 1078 */     List content = contentList();
/*      */ 
/* 1080 */     if ((content == null) || (content.isEmpty())) {
/* 1081 */       return true;
/*      */     }
/*      */ 
/* 1084 */     for (Iterator iter = content.iterator(); iter.hasNext(); ) {
/* 1085 */       Object object = iter.next();
/*      */ 
/* 1087 */       if ((!(object instanceof CharacterData)) && (!(object instanceof String)))
/*      */       {
/* 1089 */         return false;
/*      */       }
/*      */     }
/*      */ 
/* 1093 */     return true;
/*      */   }
/*      */ 
/*      */   public void setText(String text)
/*      */   {
/* 1098 */     List allContent = contentList();
/*      */ 
/* 1100 */     if (allContent != null) {
/* 1101 */       Iterator it = allContent.iterator();
/*      */ 
/* 1103 */       while (it.hasNext()) {
/* 1104 */         Node node = (Node)it.next();
/*      */ 
/* 1106 */         switch (node.getNodeType())
/*      */         {
/*      */         case 3:
/*      */         case 4:
/*      */         case 5:
/* 1112 */           it.remove();
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1120 */     addText(text);
/*      */   }
/*      */ 
/*      */   public String getStringValue() {
/* 1124 */     List list = contentList();
/*      */ 
/* 1126 */     int size = list.size();
/*      */ 
/* 1128 */     if (size > 0) {
/* 1129 */       if (size == 1)
/*      */       {
/* 1131 */         return getContentAsStringValue(list.get(0));
/*      */       }
/* 1133 */       StringBuffer buffer = new StringBuffer();
/*      */ 
/* 1135 */       for (int i = 0; i < size; i++) {
/* 1136 */         Object node = list.get(i);
/*      */ 
/* 1138 */         String string = getContentAsStringValue(node);
/*      */ 
/* 1140 */         if (string.length() > 0)
/*      */         {
/* 1147 */           buffer.append(string);
/*      */         }
/*      */       }
/*      */ 
/* 1151 */       return buffer.toString();
/*      */     }
/*      */ 
/* 1155 */     return "";
/*      */   }
/*      */ 
/*      */   public void normalize()
/*      */   {
/* 1176 */     List content = contentList();
/*      */ 
/* 1178 */     Text previousText = null;
/*      */ 
/* 1180 */     int i = 0;
/*      */ 
/* 1182 */     while (i < content.size()) {
/* 1183 */       Node node = (Node)content.get(i);
/*      */ 
/* 1185 */       if ((node instanceof Text)) {
/* 1186 */         Text text = (Text)node;
/*      */ 
/* 1188 */         if (previousText != null) {
/* 1189 */           previousText.appendText(text.getText());
/*      */ 
/* 1191 */           remove(text);
/*      */         } else {
/* 1193 */           String value = text.getText();
/*      */ 
/* 1197 */           if ((value == null) || (value.length() <= 0)) {
/* 1198 */             remove(text);
/*      */           } else {
/* 1200 */             previousText = text;
/*      */ 
/* 1202 */             i++;
/*      */           }
/*      */         }
/*      */       } else {
/* 1206 */         if ((node instanceof Element)) {
/* 1207 */           Element element = (Element)node;
/*      */ 
/* 1209 */           element.normalize();
/*      */         }
/*      */ 
/* 1212 */         previousText = null;
/*      */ 
/* 1214 */         i++;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public String elementText(String name) {
/* 1220 */     Element element = element(name);
/*      */ 
/* 1222 */     return element != null ? element.getText() : null;
/*      */   }
/*      */ 
/*      */   public String elementText(QName qName) {
/* 1226 */     Element element = element(qName);
/*      */ 
/* 1228 */     return element != null ? element.getText() : null;
/*      */   }
/*      */ 
/*      */   public String elementTextTrim(String name) {
/* 1232 */     Element element = element(name);
/*      */ 
/* 1234 */     return element != null ? element.getTextTrim() : null;
/*      */   }
/*      */ 
/*      */   public String elementTextTrim(QName qName) {
/* 1238 */     Element element = element(qName);
/*      */ 
/* 1240 */     return element != null ? element.getTextTrim() : null;
/*      */   }
/*      */ 
/*      */   public void appendAttributes(Element element)
/*      */   {
/* 1246 */     int i = 0; for (int size = element.attributeCount(); i < size; i++) {
/* 1247 */       Attribute attribute = element.attribute(i);
/*      */ 
/* 1249 */       if (attribute.supportsParent())
/* 1250 */         addAttribute(attribute.getQName(), attribute.getValue());
/*      */       else
/* 1252 */         add(attribute);
/*      */     }
/*      */   }
/*      */ 
/*      */   public Element createCopy()
/*      */   {
/* 1271 */     Element clone = createElement(getQName());
/*      */ 
/* 1273 */     clone.appendAttributes(this);
/*      */ 
/* 1275 */     clone.appendContent(this);
/*      */ 
/* 1277 */     return clone;
/*      */   }
/*      */ 
/*      */   public Element createCopy(String name) {
/* 1281 */     Element clone = createElement(name);
/*      */ 
/* 1283 */     clone.appendAttributes(this);
/*      */ 
/* 1285 */     clone.appendContent(this);
/*      */ 
/* 1287 */     return clone;
/*      */   }
/*      */ 
/*      */   public Element createCopy(QName qName) {
/* 1291 */     Element clone = createElement(qName);
/*      */ 
/* 1293 */     clone.appendAttributes(this);
/*      */ 
/* 1295 */     clone.appendContent(this);
/*      */ 
/* 1297 */     return clone;
/*      */   }
/*      */ 
/*      */   public QName getQName(String qualifiedName) {
/* 1301 */     String prefix = "";
/*      */ 
/* 1303 */     String localName = qualifiedName;
/*      */ 
/* 1305 */     int index = qualifiedName.indexOf(":");
/*      */ 
/* 1307 */     if (index > 0) {
/* 1308 */       prefix = qualifiedName.substring(0, index);
/*      */ 
/* 1310 */       localName = qualifiedName.substring(index + 1);
/*      */     }
/*      */ 
/* 1313 */     Namespace namespace = getNamespaceForPrefix(prefix);
/*      */ 
/* 1315 */     if (namespace != null) {
/* 1316 */       return getDocumentFactory().createQName(localName, namespace);
/*      */     }
/* 1318 */     return getDocumentFactory().createQName(localName);
/*      */   }
/*      */ 
/*      */   public Namespace getNamespaceForPrefix(String prefix)
/*      */   {
/* 1323 */     if (prefix == null) {
/* 1324 */       prefix = "";
/*      */     }
/*      */ 
/* 1327 */     if (prefix.equals(getNamespacePrefix()))
/* 1328 */       return getNamespace();
/* 1329 */     if (prefix.equals("xml")) {
/* 1330 */       return Namespace.XML_NAMESPACE;
/*      */     }
/* 1332 */     List list = contentList();
/*      */ 
/* 1334 */     int size = list.size();
/*      */ 
/* 1336 */     for (int i = 0; i < size; i++) {
/* 1337 */       Object object = list.get(i);
/*      */ 
/* 1339 */       if ((object instanceof Namespace)) {
/* 1340 */         Namespace namespace = (Namespace)object;
/*      */ 
/* 1342 */         if (prefix.equals(namespace.getPrefix())) {
/* 1343 */           return namespace;
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1349 */     Element parent = getParent();
/*      */ 
/* 1351 */     if (parent != null) {
/* 1352 */       Namespace answer = parent.getNamespaceForPrefix(prefix);
/*      */ 
/* 1354 */       if (answer != null) {
/* 1355 */         return answer;
/*      */       }
/*      */     }
/*      */ 
/* 1359 */     if ((prefix == null) || (prefix.length() <= 0)) {
/* 1360 */       return Namespace.NO_NAMESPACE;
/*      */     }
/*      */ 
/* 1363 */     return null;
/*      */   }
/*      */ 
/*      */   public Namespace getNamespaceForURI(String uri) {
/* 1367 */     if ((uri == null) || (uri.length() <= 0))
/* 1368 */       return Namespace.NO_NAMESPACE;
/* 1369 */     if (uri.equals(getNamespaceURI())) {
/* 1370 */       return getNamespace();
/*      */     }
/* 1372 */     List list = contentList();
/*      */ 
/* 1374 */     int size = list.size();
/*      */ 
/* 1376 */     for (int i = 0; i < size; i++) {
/* 1377 */       Object object = list.get(i);
/*      */ 
/* 1379 */       if ((object instanceof Namespace)) {
/* 1380 */         Namespace namespace = (Namespace)object;
/*      */ 
/* 1382 */         if (uri.equals(namespace.getURI())) {
/* 1383 */           return namespace;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1388 */     return null;
/*      */   }
/*      */ 
/*      */   public List getNamespacesForURI(String uri)
/*      */   {
/* 1393 */     BackedList answer = createResultList();
/*      */ 
/* 1400 */     List list = contentList();
/*      */ 
/* 1402 */     int size = list.size();
/*      */ 
/* 1404 */     for (int i = 0; i < size; i++) {
/* 1405 */       Object object = list.get(i);
/*      */ 
/* 1407 */       if (((object instanceof Namespace)) && (((Namespace)object).getURI().equals(uri)))
/*      */       {
/* 1409 */         answer.addLocal(object);
/*      */       }
/*      */     }
/*      */ 
/* 1413 */     return answer;
/*      */   }
/*      */ 
/*      */   public List declaredNamespaces() {
/* 1417 */     BackedList answer = createResultList();
/*      */ 
/* 1425 */     List list = contentList();
/*      */ 
/* 1427 */     int size = list.size();
/*      */ 
/* 1429 */     for (int i = 0; i < size; i++) {
/* 1430 */       Object object = list.get(i);
/*      */ 
/* 1432 */       if ((object instanceof Namespace)) {
/* 1433 */         answer.addLocal(object);
/*      */       }
/*      */     }
/*      */ 
/* 1437 */     return answer;
/*      */   }
/*      */ 
/*      */   public List additionalNamespaces() {
/* 1441 */     List list = contentList();
/*      */ 
/* 1443 */     int size = list.size();
/*      */ 
/* 1445 */     BackedList answer = createResultList();
/*      */ 
/* 1447 */     for (int i = 0; i < size; i++) {
/* 1448 */       Object object = list.get(i);
/*      */ 
/* 1450 */       if ((object instanceof Namespace)) {
/* 1451 */         Namespace namespace = (Namespace)object;
/*      */ 
/* 1453 */         if (!namespace.equals(getNamespace())) {
/* 1454 */           answer.addLocal(namespace);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1459 */     return answer;
/*      */   }
/*      */ 
/*      */   public List additionalNamespaces(String defaultNamespaceURI) {
/* 1463 */     List list = contentList();
/*      */ 
/* 1465 */     BackedList answer = createResultList();
/*      */ 
/* 1467 */     int size = list.size();
/*      */ 
/* 1469 */     for (int i = 0; i < size; i++) {
/* 1470 */       Object object = list.get(i);
/*      */ 
/* 1472 */       if ((object instanceof Namespace)) {
/* 1473 */         Namespace namespace = (Namespace)object;
/*      */ 
/* 1475 */         if (!defaultNamespaceURI.equals(namespace.getURI())) {
/* 1476 */           answer.addLocal(namespace);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1481 */     return answer;
/*      */   }
/*      */ 
/*      */   public void ensureAttributesCapacity(int minCapacity)
/*      */   {
/* 1494 */     if (minCapacity > 1) {
/* 1495 */       List list = attributeList();
/*      */ 
/* 1497 */       if ((list instanceof ArrayList)) {
/* 1498 */         ArrayList arrayList = (ArrayList)list;
/*      */ 
/* 1500 */         arrayList.ensureCapacity(minCapacity);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   protected Element createElement(String name)
/*      */   {
/* 1508 */     return getDocumentFactory().createElement(name);
/*      */   }
/*      */ 
/*      */   protected Element createElement(QName qName) {
/* 1512 */     return getDocumentFactory().createElement(qName);
/*      */   }
/*      */ 
/*      */   protected void addNode(Node node) {
/* 1516 */     if (node.getParent() != null)
/*      */     {
/* 1518 */       String message = "The Node already has an existing parent of \"" + node.getParent().getQualifiedName() + "\"";
/*      */ 
/* 1521 */       throw new IllegalAddException(this, node, message);
/*      */     }
/*      */ 
/* 1524 */     addNewNode(node);
/*      */   }
/*      */ 
/*      */   protected void addNode(int index, Node node) {
/* 1528 */     if (node.getParent() != null)
/*      */     {
/* 1530 */       String message = "The Node already has an existing parent of \"" + node.getParent().getQualifiedName() + "\"";
/*      */ 
/* 1533 */       throw new IllegalAddException(this, node, message);
/*      */     }
/*      */ 
/* 1536 */     addNewNode(index, node);
/*      */   }
/*      */ 
/*      */   protected void addNewNode(Node node)
/*      */   {
/* 1546 */     contentList().add(node);
/*      */ 
/* 1548 */     childAdded(node);
/*      */   }
/*      */ 
/*      */   protected void addNewNode(int index, Node node) {
/* 1552 */     contentList().add(index, node);
/*      */ 
/* 1554 */     childAdded(node);
/*      */   }
/*      */ 
/*      */   protected boolean removeNode(Node node) {
/* 1558 */     boolean answer = contentList().remove(node);
/*      */ 
/* 1560 */     if (answer) {
/* 1561 */       childRemoved(node);
/*      */     }
/*      */ 
/* 1564 */     return answer;
/*      */   }
/*      */ 
/*      */   protected void childAdded(Node node)
/*      */   {
/* 1574 */     if (node != null)
/* 1575 */       node.setParent(this);
/*      */   }
/*      */ 
/*      */   protected void childRemoved(Node node)
/*      */   {
/* 1580 */     if (node != null) {
/* 1581 */       node.setParent(null);
/*      */ 
/* 1583 */       node.setDocument(null);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected abstract List attributeList();
/*      */ 
/*      */   protected abstract List attributeList(int paramInt);
/*      */ 
/*      */   protected DocumentFactory getDocumentFactory()
/*      */   {
/* 1607 */     QName qName = getQName();
/*      */ 
/* 1610 */     if (qName != null) {
/* 1611 */       DocumentFactory factory = qName.getDocumentFactory();
/*      */ 
/* 1613 */       if (factory != null) {
/* 1614 */         return factory;
/*      */       }
/*      */     }
/*      */ 
/* 1618 */     return DOCUMENT_FACTORY;
/*      */   }
/*      */ 
/*      */   protected List createAttributeList()
/*      */   {
/* 1628 */     return createAttributeList(5);
/*      */   }
/*      */ 
/*      */   protected List createAttributeList(int size)
/*      */   {
/* 1641 */     return new ArrayList(size);
/*      */   }
/*      */ 
/*      */   protected Iterator createSingleIterator(Object result) {
/* 1645 */     return new SingleIterator(result);
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractElement
 * JD-Core Version:    0.6.2
 */