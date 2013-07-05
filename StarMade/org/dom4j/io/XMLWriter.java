/*      */ package org.dom4j.io;
/*      */ 
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.io.Writer;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import org.dom4j.Attribute;
/*      */ import org.dom4j.CDATA;
/*      */ import org.dom4j.Comment;
/*      */ import org.dom4j.Document;
/*      */ import org.dom4j.DocumentType;
/*      */ import org.dom4j.Element;
/*      */ import org.dom4j.Entity;
/*      */ import org.dom4j.Namespace;
/*      */ import org.dom4j.Node;
/*      */ import org.dom4j.ProcessingInstruction;
/*      */ import org.dom4j.Text;
/*      */ import org.dom4j.tree.NamespaceStack;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.InputSource;
/*      */ import org.xml.sax.Locator;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXNotRecognizedException;
/*      */ import org.xml.sax.SAXNotSupportedException;
/*      */ import org.xml.sax.XMLReader;
/*      */ import org.xml.sax.ext.LexicalHandler;
/*      */ import org.xml.sax.helpers.XMLFilterImpl;
/*      */ 
/*      */ public class XMLWriter extends XMLFilterImpl
/*      */   implements LexicalHandler
/*      */ {
/*      */   private static final String PAD_TEXT = " ";
/*   74 */   protected static final String[] LEXICAL_HANDLER_NAMES = { "http://xml.org/sax/properties/lexical-handler", "http://xml.org/sax/handlers/LexicalHandler" };
/*      */ 
/*   78 */   protected static final OutputFormat DEFAULT_FORMAT = new OutputFormat();
/*      */ 
/*   81 */   private boolean resolveEntityRefs = true;
/*      */   protected int lastOutputNodeType;
/*   93 */   private boolean lastElementClosed = false;
/*      */ 
/*   96 */   protected boolean preserve = false;
/*      */   protected Writer writer;
/*  102 */   private NamespaceStack namespaceStack = new NamespaceStack();
/*      */   private OutputFormat format;
/*  108 */   private boolean escapeText = true;
/*      */ 
/*  114 */   private int indentLevel = 0;
/*      */ 
/*  117 */   private StringBuffer buffer = new StringBuffer();
/*      */ 
/*  122 */   private boolean charsAdded = false;
/*      */   private char lastChar;
/*      */   private boolean autoFlush;
/*      */   private LexicalHandler lexicalHandler;
/*      */   private boolean showCommentsInDTDs;
/*      */   private boolean inDTD;
/*      */   private Map namespacesMap;
/*      */   private int maximumAllowedCharacter;
/*      */ 
/*      */   public XMLWriter(Writer writer)
/*      */   {
/*  152 */     this(writer, DEFAULT_FORMAT);
/*      */   }
/*      */ 
/*      */   public XMLWriter(Writer writer, OutputFormat format) {
/*  156 */     this.writer = writer;
/*  157 */     this.format = format;
/*  158 */     this.namespaceStack.push(Namespace.NO_NAMESPACE);
/*      */   }
/*      */ 
/*      */   public XMLWriter() {
/*  162 */     this.format = DEFAULT_FORMAT;
/*  163 */     this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
/*  164 */     this.autoFlush = true;
/*  165 */     this.namespaceStack.push(Namespace.NO_NAMESPACE);
/*      */   }
/*      */ 
/*      */   public XMLWriter(OutputStream out) throws UnsupportedEncodingException {
/*  169 */     this.format = DEFAULT_FORMAT;
/*  170 */     this.writer = createWriter(out, this.format.getEncoding());
/*  171 */     this.autoFlush = true;
/*  172 */     this.namespaceStack.push(Namespace.NO_NAMESPACE);
/*      */   }
/*      */ 
/*      */   public XMLWriter(OutputStream out, OutputFormat format) throws UnsupportedEncodingException
/*      */   {
/*  177 */     this.format = format;
/*  178 */     this.writer = createWriter(out, format.getEncoding());
/*  179 */     this.autoFlush = true;
/*  180 */     this.namespaceStack.push(Namespace.NO_NAMESPACE);
/*      */   }
/*      */ 
/*      */   public XMLWriter(OutputFormat format) throws UnsupportedEncodingException {
/*  184 */     this.format = format;
/*  185 */     this.writer = createWriter(System.out, format.getEncoding());
/*  186 */     this.autoFlush = true;
/*  187 */     this.namespaceStack.push(Namespace.NO_NAMESPACE);
/*      */   }
/*      */ 
/*      */   public void setWriter(Writer writer) {
/*  191 */     this.writer = writer;
/*  192 */     this.autoFlush = false;
/*      */   }
/*      */ 
/*      */   public void setOutputStream(OutputStream out) throws UnsupportedEncodingException
/*      */   {
/*  197 */     this.writer = createWriter(out, this.format.getEncoding());
/*  198 */     this.autoFlush = true;
/*      */   }
/*      */ 
/*      */   public boolean isEscapeText()
/*      */   {
/*  209 */     return this.escapeText;
/*      */   }
/*      */ 
/*      */   public void setEscapeText(boolean escapeText)
/*      */   {
/*  221 */     this.escapeText = escapeText;
/*      */   }
/*      */ 
/*      */   public void setIndentLevel(int indentLevel)
/*      */   {
/*  233 */     this.indentLevel = indentLevel;
/*      */   }
/*      */ 
/*      */   public int getMaximumAllowedCharacter()
/*      */   {
/*  244 */     if (this.maximumAllowedCharacter == 0) {
/*  245 */       this.maximumAllowedCharacter = defaultMaximumAllowedCharacter();
/*      */     }
/*      */ 
/*  248 */     return this.maximumAllowedCharacter;
/*      */   }
/*      */ 
/*      */   public void setMaximumAllowedCharacter(int maximumAllowedCharacter)
/*      */   {
/*  262 */     this.maximumAllowedCharacter = maximumAllowedCharacter;
/*      */   }
/*      */ 
/*      */   public void flush()
/*      */     throws IOException
/*      */   {
/*  272 */     this.writer.flush();
/*      */   }
/*      */ 
/*      */   public void close()
/*      */     throws IOException
/*      */   {
/*  282 */     this.writer.close();
/*      */   }
/*      */ 
/*      */   public void println()
/*      */     throws IOException
/*      */   {
/*  292 */     this.writer.write(this.format.getLineSeparator());
/*      */   }
/*      */ 
/*      */   public void write(Attribute attribute)
/*      */     throws IOException
/*      */   {
/*  305 */     writeAttribute(attribute);
/*      */ 
/*  307 */     if (this.autoFlush)
/*  308 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(Document doc)
/*      */     throws IOException
/*      */   {
/*  335 */     writeDeclaration();
/*      */ 
/*  337 */     if (doc.getDocType() != null) {
/*  338 */       indent();
/*  339 */       writeDocType(doc.getDocType());
/*      */     }
/*      */ 
/*  342 */     int i = 0; for (int size = doc.nodeCount(); i < size; i++) {
/*  343 */       Node node = doc.node(i);
/*  344 */       writeNode(node);
/*      */     }
/*      */ 
/*  347 */     writePrintln();
/*      */ 
/*  349 */     if (this.autoFlush)
/*  350 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(Element element)
/*      */     throws IOException
/*      */   {
/*  369 */     writeElement(element);
/*      */ 
/*  371 */     if (this.autoFlush)
/*  372 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(CDATA cdata)
/*      */     throws IOException
/*      */   {
/*  386 */     writeCDATA(cdata.getText());
/*      */ 
/*  388 */     if (this.autoFlush)
/*  389 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(Comment comment)
/*      */     throws IOException
/*      */   {
/*  403 */     writeComment(comment.getText());
/*      */ 
/*  405 */     if (this.autoFlush)
/*  406 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(DocumentType docType)
/*      */     throws IOException
/*      */   {
/*  420 */     writeDocType(docType);
/*      */ 
/*  422 */     if (this.autoFlush)
/*  423 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(Entity entity)
/*      */     throws IOException
/*      */   {
/*  437 */     writeEntity(entity);
/*      */ 
/*  439 */     if (this.autoFlush)
/*  440 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(Namespace namespace)
/*      */     throws IOException
/*      */   {
/*  454 */     writeNamespace(namespace);
/*      */ 
/*  456 */     if (this.autoFlush)
/*  457 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(ProcessingInstruction processingInstruction)
/*      */     throws IOException
/*      */   {
/*  472 */     writeProcessingInstruction(processingInstruction);
/*      */ 
/*  474 */     if (this.autoFlush)
/*  475 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(String text)
/*      */     throws IOException
/*      */   {
/*  492 */     writeString(text);
/*      */ 
/*  494 */     if (this.autoFlush)
/*  495 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(Text text)
/*      */     throws IOException
/*      */   {
/*  509 */     writeString(text.getText());
/*      */ 
/*  511 */     if (this.autoFlush)
/*  512 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(Node node)
/*      */     throws IOException
/*      */   {
/*  526 */     writeNode(node);
/*      */ 
/*  528 */     if (this.autoFlush)
/*  529 */       flush();
/*      */   }
/*      */ 
/*      */   public void write(Object object)
/*      */     throws IOException
/*      */   {
/*  544 */     if ((object instanceof Node)) {
/*  545 */       write((Node)object);
/*  546 */     } else if ((object instanceof String)) {
/*  547 */       write((String)object);
/*  548 */     } else if ((object instanceof List)) {
/*  549 */       List list = (List)object;
/*      */ 
/*  551 */       int i = 0; for (int size = list.size(); i < size; i++)
/*  552 */         write(list.get(i));
/*      */     }
/*  554 */     else if (object != null) {
/*  555 */       throw new IOException("Invalid object: " + object);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void writeOpen(Element element)
/*      */     throws IOException
/*      */   {
/*  572 */     this.writer.write("<");
/*  573 */     this.writer.write(element.getQualifiedName());
/*  574 */     writeAttributes(element);
/*  575 */     this.writer.write(">");
/*      */   }
/*      */ 
/*      */   public void writeClose(Element element)
/*      */     throws IOException
/*      */   {
/*  590 */     writeClose(element.getQualifiedName());
/*      */   }
/*      */ 
/*      */   public void parse(InputSource source)
/*      */     throws IOException, SAXException
/*      */   {
/*  596 */     installLexicalHandler();
/*  597 */     super.parse(source);
/*      */   }
/*      */ 
/*      */   public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException
/*      */   {
/*  602 */     for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
/*  603 */       if (LEXICAL_HANDLER_NAMES[i].equals(name)) {
/*  604 */         setLexicalHandler((LexicalHandler)value);
/*      */ 
/*  606 */         return;
/*      */       }
/*      */     }
/*      */ 
/*  610 */     super.setProperty(name, value);
/*      */   }
/*      */ 
/*      */   public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException
/*      */   {
/*  615 */     for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++) {
/*  616 */       if (LEXICAL_HANDLER_NAMES[i].equals(name)) {
/*  617 */         return getLexicalHandler();
/*      */       }
/*      */     }
/*      */ 
/*  621 */     return super.getProperty(name);
/*      */   }
/*      */ 
/*      */   public void setLexicalHandler(LexicalHandler handler) {
/*  625 */     if (handler == null) {
/*  626 */       throw new NullPointerException("Null lexical handler");
/*      */     }
/*  628 */     this.lexicalHandler = handler;
/*      */   }
/*      */ 
/*      */   public LexicalHandler getLexicalHandler()
/*      */   {
/*  633 */     return this.lexicalHandler;
/*      */   }
/*      */ 
/*      */   public void setDocumentLocator(Locator locator)
/*      */   {
/*  639 */     super.setDocumentLocator(locator);
/*      */   }
/*      */ 
/*      */   public void startDocument() throws SAXException {
/*      */     try {
/*  644 */       writeDeclaration();
/*  645 */       super.startDocument();
/*      */     } catch (IOException e) {
/*  647 */       handleException(e);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void endDocument() throws SAXException {
/*  652 */     super.endDocument();
/*      */ 
/*  654 */     if (this.autoFlush)
/*      */       try {
/*  656 */         flush();
/*      */       }
/*      */       catch (IOException e)
/*      */       {
/*      */       }
/*      */   }
/*      */ 
/*      */   public void startPrefixMapping(String prefix, String uri) throws SAXException {
/*  664 */     if (this.namespacesMap == null) {
/*  665 */       this.namespacesMap = new HashMap();
/*      */     }
/*      */ 
/*  668 */     this.namespacesMap.put(prefix, uri);
/*  669 */     super.startPrefixMapping(prefix, uri);
/*      */   }
/*      */ 
/*      */   public void endPrefixMapping(String prefix) throws SAXException {
/*  673 */     super.endPrefixMapping(prefix);
/*      */   }
/*      */ 
/*      */   public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) throws SAXException
/*      */   {
/*      */     try {
/*  679 */       this.charsAdded = false;
/*      */ 
/*  681 */       writePrintln();
/*  682 */       indent();
/*  683 */       this.writer.write("<");
/*  684 */       this.writer.write(qName);
/*  685 */       writeNamespaces();
/*  686 */       writeAttributes(attributes);
/*  687 */       this.writer.write(">");
/*  688 */       this.indentLevel += 1;
/*  689 */       this.lastOutputNodeType = 1;
/*  690 */       this.lastElementClosed = false;
/*      */ 
/*  692 */       super.startElement(namespaceURI, localName, qName, attributes);
/*      */     } catch (IOException e) {
/*  694 */       handleException(e);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void endElement(String namespaceURI, String localName, String qName) throws SAXException
/*      */   {
/*      */     try {
/*  701 */       this.charsAdded = false;
/*  702 */       this.indentLevel -= 1;
/*      */ 
/*  704 */       if (this.lastElementClosed) {
/*  705 */         writePrintln();
/*  706 */         indent();
/*      */       }
/*      */ 
/*  711 */       boolean hadContent = true;
/*      */ 
/*  713 */       if (hadContent)
/*  714 */         writeClose(qName);
/*      */       else {
/*  716 */         writeEmptyElementClose(qName);
/*      */       }
/*      */ 
/*  719 */       this.lastOutputNodeType = 1;
/*  720 */       this.lastElementClosed = true;
/*      */ 
/*  722 */       super.endElement(namespaceURI, localName, qName);
/*      */     } catch (IOException e) {
/*  724 */       handleException(e);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void characters(char[] ch, int start, int length) throws SAXException
/*      */   {
/*  730 */     if ((ch == null) || (ch.length == 0) || (length <= 0)) {
/*  731 */       return;
/*      */     }
/*      */ 
/*      */     try
/*      */     {
/*  741 */       String string = String.valueOf(ch, start, length);
/*      */ 
/*  743 */       if (this.escapeText) {
/*  744 */         string = escapeElementEntities(string);
/*      */       }
/*      */ 
/*  747 */       if (this.format.isTrimText()) {
/*  748 */         if ((this.lastOutputNodeType == 3) && (!this.charsAdded))
/*  749 */           this.writer.write(32);
/*  750 */         else if ((this.charsAdded) && (Character.isWhitespace(this.lastChar)))
/*  751 */           this.writer.write(32);
/*  752 */         else if ((this.lastOutputNodeType == 1) && (this.format.isPadText()) && (this.lastElementClosed) && (Character.isWhitespace(ch[0])))
/*      */         {
/*  755 */           this.writer.write(" ");
/*      */         }
/*      */ 
/*  758 */         String delim = "";
/*  759 */         StringTokenizer tokens = new StringTokenizer(string);
/*      */ 
/*  761 */         while (tokens.hasMoreTokens()) {
/*  762 */           this.writer.write(delim);
/*  763 */           this.writer.write(tokens.nextToken());
/*  764 */           delim = " ";
/*      */         }
/*      */       } else {
/*  767 */         this.writer.write(string);
/*      */       }
/*      */ 
/*  770 */       this.charsAdded = true;
/*  771 */       this.lastChar = ch[(start + length - 1)];
/*  772 */       this.lastOutputNodeType = 3;
/*      */ 
/*  774 */       super.characters(ch, start, length);
/*      */     } catch (IOException e) {
/*  776 */       handleException(e);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException
/*      */   {
/*  782 */     super.ignorableWhitespace(ch, start, length);
/*      */   }
/*      */ 
/*      */   public void processingInstruction(String target, String data) throws SAXException
/*      */   {
/*      */     try {
/*  788 */       indent();
/*  789 */       this.writer.write("<?");
/*  790 */       this.writer.write(target);
/*  791 */       this.writer.write(" ");
/*  792 */       this.writer.write(data);
/*  793 */       this.writer.write("?>");
/*  794 */       writePrintln();
/*  795 */       this.lastOutputNodeType = 7;
/*      */ 
/*  797 */       super.processingInstruction(target, data);
/*      */     } catch (IOException e) {
/*  799 */       handleException(e);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void notationDecl(String name, String publicID, String systemID)
/*      */     throws SAXException
/*      */   {
/*  807 */     super.notationDecl(name, publicID, systemID);
/*      */   }
/*      */ 
/*      */   public void unparsedEntityDecl(String name, String publicID, String systemID, String notationName) throws SAXException
/*      */   {
/*  812 */     super.unparsedEntityDecl(name, publicID, systemID, notationName);
/*      */   }
/*      */ 
/*      */   public void startDTD(String name, String publicID, String systemID)
/*      */     throws SAXException
/*      */   {
/*  819 */     this.inDTD = true;
/*      */     try
/*      */     {
/*  822 */       writeDocType(name, publicID, systemID);
/*      */     } catch (IOException e) {
/*  824 */       handleException(e);
/*      */     }
/*      */ 
/*  827 */     if (this.lexicalHandler != null)
/*  828 */       this.lexicalHandler.startDTD(name, publicID, systemID);
/*      */   }
/*      */ 
/*      */   public void endDTD() throws SAXException
/*      */   {
/*  833 */     this.inDTD = false;
/*      */ 
/*  835 */     if (this.lexicalHandler != null)
/*  836 */       this.lexicalHandler.endDTD();
/*      */   }
/*      */ 
/*      */   public void startCDATA() throws SAXException
/*      */   {
/*      */     try {
/*  842 */       this.writer.write("<![CDATA[");
/*      */     } catch (IOException e) {
/*  844 */       handleException(e);
/*      */     }
/*      */ 
/*  847 */     if (this.lexicalHandler != null)
/*  848 */       this.lexicalHandler.startCDATA();
/*      */   }
/*      */ 
/*      */   public void endCDATA() throws SAXException
/*      */   {
/*      */     try {
/*  854 */       this.writer.write("]]>");
/*      */     } catch (IOException e) {
/*  856 */       handleException(e);
/*      */     }
/*      */ 
/*  859 */     if (this.lexicalHandler != null)
/*  860 */       this.lexicalHandler.endCDATA();
/*      */   }
/*      */ 
/*      */   public void startEntity(String name) throws SAXException
/*      */   {
/*      */     try {
/*  866 */       writeEntityRef(name);
/*      */     } catch (IOException e) {
/*  868 */       handleException(e);
/*      */     }
/*      */ 
/*  871 */     if (this.lexicalHandler != null)
/*  872 */       this.lexicalHandler.startEntity(name);
/*      */   }
/*      */ 
/*      */   public void endEntity(String name) throws SAXException
/*      */   {
/*  877 */     if (this.lexicalHandler != null)
/*  878 */       this.lexicalHandler.endEntity(name);
/*      */   }
/*      */ 
/*      */   public void comment(char[] ch, int start, int length) throws SAXException
/*      */   {
/*  883 */     if ((this.showCommentsInDTDs) || (!this.inDTD)) {
/*      */       try {
/*  885 */         this.charsAdded = false;
/*  886 */         writeComment(new String(ch, start, length));
/*      */       } catch (IOException e) {
/*  888 */         handleException(e);
/*      */       }
/*      */     }
/*      */ 
/*  892 */     if (this.lexicalHandler != null)
/*  893 */       this.lexicalHandler.comment(ch, start, length);
/*      */   }
/*      */ 
/*      */   protected void writeElement(Element element)
/*      */     throws IOException
/*      */   {
/*  900 */     int size = element.nodeCount();
/*  901 */     String qualifiedName = element.getQualifiedName();
/*      */ 
/*  903 */     writePrintln();
/*  904 */     indent();
/*      */ 
/*  906 */     this.writer.write("<");
/*  907 */     this.writer.write(qualifiedName);
/*      */ 
/*  909 */     int previouslyDeclaredNamespaces = this.namespaceStack.size();
/*  910 */     Namespace ns = element.getNamespace();
/*      */ 
/*  912 */     if (isNamespaceDeclaration(ns)) {
/*  913 */       this.namespaceStack.push(ns);
/*  914 */       writeNamespace(ns);
/*      */     }
/*      */ 
/*  918 */     boolean textOnly = true;
/*      */ 
/*  920 */     for (int i = 0; i < size; i++) {
/*  921 */       Node node = element.node(i);
/*      */ 
/*  923 */       if ((node instanceof Namespace)) {
/*  924 */         Namespace additional = (Namespace)node;
/*      */ 
/*  926 */         if (isNamespaceDeclaration(additional)) {
/*  927 */           this.namespaceStack.push(additional);
/*  928 */           writeNamespace(additional);
/*      */         }
/*  930 */       } else if ((node instanceof Element)) {
/*  931 */         textOnly = false;
/*  932 */       } else if ((node instanceof Comment)) {
/*  933 */         textOnly = false;
/*      */       }
/*      */     }
/*      */ 
/*  937 */     writeAttributes(element);
/*      */ 
/*  939 */     this.lastOutputNodeType = 1;
/*      */ 
/*  941 */     if (size <= 0) {
/*  942 */       writeEmptyElementClose(qualifiedName);
/*      */     } else {
/*  944 */       this.writer.write(">");
/*      */ 
/*  946 */       if (textOnly)
/*      */       {
/*  949 */         writeElementContent(element);
/*      */       }
/*      */       else {
/*  952 */         this.indentLevel += 1;
/*      */ 
/*  954 */         writeElementContent(element);
/*      */ 
/*  956 */         this.indentLevel -= 1;
/*      */ 
/*  958 */         writePrintln();
/*  959 */         indent();
/*      */       }
/*      */ 
/*  962 */       this.writer.write("</");
/*  963 */       this.writer.write(qualifiedName);
/*  964 */       this.writer.write(">");
/*      */     }
/*      */ 
/*  968 */     while (this.namespaceStack.size() > previouslyDeclaredNamespaces) {
/*  969 */       this.namespaceStack.pop();
/*      */     }
/*      */ 
/*  972 */     this.lastOutputNodeType = 1;
/*      */   }
/*      */ 
/*      */   protected final boolean isElementSpacePreserved(Element element)
/*      */   {
/*  985 */     Attribute attr = element.attribute("space");
/*  986 */     boolean preserveFound = this.preserve;
/*      */ 
/*  988 */     if (attr != null) {
/*  989 */       if (("xml".equals(attr.getNamespacePrefix())) && ("preserve".equals(attr.getText())))
/*      */       {
/*  991 */         preserveFound = true;
/*      */       }
/*  993 */       else preserveFound = false;
/*      */ 
/*      */     }
/*      */ 
/*  997 */     return preserveFound;
/*      */   }
/*      */ 
/*      */   protected void writeElementContent(Element element)
/*      */     throws IOException
/*      */   {
/* 1014 */     boolean trim = this.format.isTrimText();
/* 1015 */     boolean oldPreserve = this.preserve;
/*      */ 
/* 1017 */     if (trim) {
/* 1018 */       this.preserve = isElementSpacePreserved(element);
/* 1019 */       trim = !this.preserve;
/*      */     }
/*      */ 
/* 1022 */     if (trim)
/*      */     {
/* 1025 */       Text lastTextNode = null;
/* 1026 */       StringBuffer buff = null;
/* 1027 */       boolean textOnly = true;
/*      */ 
/* 1029 */       int i = 0; for (int size = element.nodeCount(); i < size; i++) {
/* 1030 */         Node node = element.node(i);
/*      */ 
/* 1032 */         if ((node instanceof Text)) {
/* 1033 */           if (lastTextNode == null) {
/* 1034 */             lastTextNode = (Text)node;
/*      */           } else {
/* 1036 */             if (buff == null) {
/* 1037 */               buff = new StringBuffer(lastTextNode.getText());
/*      */             }
/*      */ 
/* 1040 */             buff.append(((Text)node).getText());
/*      */           }
/*      */         } else {
/* 1043 */           if ((!textOnly) && (this.format.isPadText()))
/*      */           {
/* 1046 */             char firstChar = 'a';
/* 1047 */             if (buff != null)
/* 1048 */               firstChar = buff.charAt(0);
/* 1049 */             else if (lastTextNode != null) {
/* 1050 */               firstChar = lastTextNode.getText().charAt(0);
/*      */             }
/*      */ 
/* 1053 */             if (Character.isWhitespace(firstChar)) {
/* 1054 */               this.writer.write(" ");
/*      */             }
/*      */           }
/*      */ 
/* 1058 */           if (lastTextNode != null) {
/* 1059 */             if (buff != null) {
/* 1060 */               writeString(buff.toString());
/* 1061 */               buff = null;
/*      */             } else {
/* 1063 */               writeString(lastTextNode.getText());
/*      */             }
/*      */ 
/* 1066 */             if (this.format.isPadText())
/*      */             {
/* 1069 */               char lastTextChar = 'a';
/* 1070 */               if (buff != null) {
/* 1071 */                 lastTextChar = buff.charAt(buff.length() - 1);
/* 1072 */               } else if (lastTextNode != null) {
/* 1073 */                 String txt = lastTextNode.getText();
/* 1074 */                 lastTextChar = txt.charAt(txt.length() - 1);
/*      */               }
/*      */ 
/* 1077 */               if (Character.isWhitespace(lastTextChar)) {
/* 1078 */                 this.writer.write(" ");
/*      */               }
/*      */             }
/*      */ 
/* 1082 */             lastTextNode = null;
/*      */           }
/*      */ 
/* 1085 */           textOnly = false;
/* 1086 */           writeNode(node);
/*      */         }
/*      */       }
/*      */ 
/* 1090 */       if (lastTextNode != null) {
/* 1091 */         if ((!textOnly) && (this.format.isPadText()))
/*      */         {
/* 1094 */           char firstChar = 'a';
/* 1095 */           if (buff != null)
/* 1096 */             firstChar = buff.charAt(0);
/*      */           else {
/* 1098 */             firstChar = lastTextNode.getText().charAt(0);
/*      */           }
/*      */ 
/* 1101 */           if (Character.isWhitespace(firstChar)) {
/* 1102 */             this.writer.write(" ");
/*      */           }
/*      */         }
/*      */ 
/* 1106 */         if (buff != null) {
/* 1107 */           writeString(buff.toString());
/* 1108 */           buff = null;
/*      */         } else {
/* 1110 */           writeString(lastTextNode.getText());
/*      */         }
/*      */ 
/* 1113 */         lastTextNode = null;
/*      */       }
/*      */     } else {
/* 1116 */       Node lastTextNode = null;
/*      */ 
/* 1118 */       int i = 0; for (int size = element.nodeCount(); i < size; i++) {
/* 1119 */         Node node = element.node(i);
/*      */ 
/* 1121 */         if ((node instanceof Text)) {
/* 1122 */           writeNode(node);
/* 1123 */           lastTextNode = node;
/*      */         } else {
/* 1125 */           if ((lastTextNode != null) && (this.format.isPadText()))
/*      */           {
/* 1128 */             String txt = lastTextNode.getText();
/* 1129 */             char lastTextChar = txt.charAt(txt.length() - 1);
/*      */ 
/* 1131 */             if (Character.isWhitespace(lastTextChar)) {
/* 1132 */               this.writer.write(" ");
/*      */             }
/*      */           }
/*      */ 
/* 1136 */           writeNode(node);
/*      */ 
/* 1142 */           lastTextNode = null;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1147 */     this.preserve = oldPreserve;
/*      */   }
/*      */ 
/*      */   protected void writeCDATA(String text) throws IOException {
/* 1151 */     this.writer.write("<![CDATA[");
/*      */ 
/* 1153 */     if (text != null) {
/* 1154 */       this.writer.write(text);
/*      */     }
/*      */ 
/* 1157 */     this.writer.write("]]>");
/*      */ 
/* 1159 */     this.lastOutputNodeType = 4;
/*      */   }
/*      */ 
/*      */   protected void writeDocType(DocumentType docType) throws IOException {
/* 1163 */     if (docType != null) {
/* 1164 */       docType.write(this.writer);
/* 1165 */       writePrintln();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void writeNamespace(Namespace namespace) throws IOException {
/* 1170 */     if (namespace != null)
/* 1171 */       writeNamespace(namespace.getPrefix(), namespace.getURI());
/*      */   }
/*      */ 
/*      */   protected void writeNamespaces()
/*      */     throws IOException
/*      */   {
/* 1182 */     if (this.namespacesMap != null) {
/* 1183 */       Iterator iter = this.namespacesMap.entrySet().iterator();
/* 1184 */       while (iter.hasNext()) {
/* 1185 */         Map.Entry entry = (Map.Entry)iter.next();
/* 1186 */         String prefix = (String)entry.getKey();
/* 1187 */         String uri = (String)entry.getValue();
/* 1188 */         writeNamespace(prefix, uri);
/*      */       }
/*      */ 
/* 1191 */       this.namespacesMap = null;
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void writeNamespace(String prefix, String uri)
/*      */     throws IOException
/*      */   {
/* 1207 */     if ((prefix != null) && (prefix.length() > 0)) {
/* 1208 */       this.writer.write(" xmlns:");
/* 1209 */       this.writer.write(prefix);
/* 1210 */       this.writer.write("=\"");
/*      */     } else {
/* 1212 */       this.writer.write(" xmlns=\"");
/*      */     }
/*      */ 
/* 1215 */     this.writer.write(uri);
/* 1216 */     this.writer.write("\"");
/*      */   }
/*      */ 
/*      */   protected void writeProcessingInstruction(ProcessingInstruction pi)
/*      */     throws IOException
/*      */   {
/* 1222 */     this.writer.write("<?");
/* 1223 */     this.writer.write(pi.getName());
/* 1224 */     this.writer.write(" ");
/* 1225 */     this.writer.write(pi.getText());
/* 1226 */     this.writer.write("?>");
/* 1227 */     writePrintln();
/*      */ 
/* 1229 */     this.lastOutputNodeType = 7;
/*      */   }
/*      */ 
/*      */   protected void writeString(String text) throws IOException {
/* 1233 */     if ((text != null) && (text.length() > 0)) {
/* 1234 */       if (this.escapeText) {
/* 1235 */         text = escapeElementEntities(text);
/*      */       }
/*      */ 
/* 1243 */       if (this.format.isTrimText()) {
/* 1244 */         boolean first = true;
/* 1245 */         StringTokenizer tokenizer = new StringTokenizer(text);
/*      */ 
/* 1247 */         while (tokenizer.hasMoreTokens()) {
/* 1248 */           String token = tokenizer.nextToken();
/*      */ 
/* 1250 */           if (first) {
/* 1251 */             first = false;
/*      */ 
/* 1253 */             if (this.lastOutputNodeType == 3)
/* 1254 */               this.writer.write(" ");
/*      */           }
/*      */           else {
/* 1257 */             this.writer.write(" ");
/*      */           }
/*      */ 
/* 1260 */           this.writer.write(token);
/* 1261 */           this.lastOutputNodeType = 3;
/* 1262 */           this.lastChar = token.charAt(token.length() - 1);
/*      */         }
/*      */       } else {
/* 1265 */         this.lastOutputNodeType = 3;
/* 1266 */         this.writer.write(text);
/* 1267 */         this.lastChar = text.charAt(text.length() - 1);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void writeNodeText(Node node)
/*      */     throws IOException
/*      */   {
/* 1283 */     String text = node.getText();
/*      */ 
/* 1285 */     if ((text != null) && (text.length() > 0)) {
/* 1286 */       if (this.escapeText) {
/* 1287 */         text = escapeElementEntities(text);
/*      */       }
/*      */ 
/* 1290 */       this.lastOutputNodeType = 3;
/* 1291 */       this.writer.write(text);
/* 1292 */       this.lastChar = text.charAt(text.length() - 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void writeNode(Node node) throws IOException {
/* 1297 */     int nodeType = node.getNodeType();
/*      */ 
/* 1299 */     switch (nodeType) {
/*      */     case 1:
/* 1301 */       writeElement((Element)node);
/*      */ 
/* 1303 */       break;
/*      */     case 2:
/* 1306 */       writeAttribute((Attribute)node);
/*      */ 
/* 1308 */       break;
/*      */     case 3:
/* 1311 */       writeNodeText(node);
/*      */ 
/* 1314 */       break;
/*      */     case 4:
/* 1317 */       writeCDATA(node.getText());
/*      */ 
/* 1319 */       break;
/*      */     case 5:
/* 1322 */       writeEntity((Entity)node);
/*      */ 
/* 1324 */       break;
/*      */     case 7:
/* 1327 */       writeProcessingInstruction((ProcessingInstruction)node);
/*      */ 
/* 1329 */       break;
/*      */     case 8:
/* 1332 */       writeComment(node.getText());
/*      */ 
/* 1334 */       break;
/*      */     case 9:
/* 1337 */       write((Document)node);
/*      */ 
/* 1339 */       break;
/*      */     case 10:
/* 1342 */       writeDocType((DocumentType)node);
/*      */ 
/* 1344 */       break;
/*      */     case 13:
/* 1350 */       break;
/*      */     case 6:
/*      */     case 11:
/*      */     case 12:
/*      */     default:
/* 1353 */       throw new IOException("Invalid node type: " + node);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void installLexicalHandler() {
/* 1358 */     XMLReader parent = getParent();
/*      */ 
/* 1360 */     if (parent == null) {
/* 1361 */       throw new NullPointerException("No parent for filter");
/*      */     }
/*      */ 
/* 1365 */     for (int i = 0; i < LEXICAL_HANDLER_NAMES.length; i++)
/*      */       try {
/* 1367 */         parent.setProperty(LEXICAL_HANDLER_NAMES[i], this);
/*      */       }
/*      */       catch (SAXNotRecognizedException ex)
/*      */       {
/*      */       }
/*      */       catch (SAXNotSupportedException ex)
/*      */       {
/*      */       }
/*      */   }
/*      */ 
/*      */   protected void writeDocType(String name, String publicID, String systemID)
/*      */     throws IOException
/*      */   {
/* 1380 */     boolean hasPublic = false;
/*      */ 
/* 1382 */     this.writer.write("<!DOCTYPE ");
/* 1383 */     this.writer.write(name);
/*      */ 
/* 1385 */     if ((publicID != null) && (!publicID.equals(""))) {
/* 1386 */       this.writer.write(" PUBLIC \"");
/* 1387 */       this.writer.write(publicID);
/* 1388 */       this.writer.write("\"");
/* 1389 */       hasPublic = true;
/*      */     }
/*      */ 
/* 1392 */     if ((systemID != null) && (!systemID.equals(""))) {
/* 1393 */       if (!hasPublic) {
/* 1394 */         this.writer.write(" SYSTEM");
/*      */       }
/*      */ 
/* 1397 */       this.writer.write(" \"");
/* 1398 */       this.writer.write(systemID);
/* 1399 */       this.writer.write("\"");
/*      */     }
/*      */ 
/* 1402 */     this.writer.write(">");
/* 1403 */     writePrintln();
/*      */   }
/*      */ 
/*      */   protected void writeEntity(Entity entity) throws IOException {
/* 1407 */     if (!resolveEntityRefs())
/* 1408 */       writeEntityRef(entity.getName());
/*      */     else
/* 1410 */       this.writer.write(entity.getText());
/*      */   }
/*      */ 
/*      */   protected void writeEntityRef(String name) throws IOException
/*      */   {
/* 1415 */     this.writer.write("&");
/* 1416 */     this.writer.write(name);
/* 1417 */     this.writer.write(";");
/*      */ 
/* 1419 */     this.lastOutputNodeType = 5;
/*      */   }
/*      */ 
/*      */   protected void writeComment(String text) throws IOException {
/* 1423 */     if (this.format.isNewlines()) {
/* 1424 */       println();
/* 1425 */       indent();
/*      */     }
/*      */ 
/* 1428 */     this.writer.write("<!--");
/* 1429 */     this.writer.write(text);
/* 1430 */     this.writer.write("-->");
/*      */ 
/* 1432 */     this.lastOutputNodeType = 8;
/*      */   }
/*      */ 
/*      */   protected void writeAttributes(Element element)
/*      */     throws IOException
/*      */   {
/* 1449 */     int i = 0; for (int size = element.attributeCount(); i < size; i++) {
/* 1450 */       Attribute attribute = element.attribute(i);
/* 1451 */       Namespace ns = attribute.getNamespace();
/*      */ 
/* 1453 */       if ((ns != null) && (ns != Namespace.NO_NAMESPACE) && (ns != Namespace.XML_NAMESPACE))
/*      */       {
/* 1455 */         String prefix = ns.getPrefix();
/* 1456 */         String uri = this.namespaceStack.getURI(prefix);
/*      */ 
/* 1458 */         if (!ns.getURI().equals(uri)) {
/* 1459 */           writeNamespace(ns);
/* 1460 */           this.namespaceStack.push(ns);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1467 */       String attName = attribute.getName();
/*      */ 
/* 1469 */       if (attName.startsWith("xmlns:")) {
/* 1470 */         String prefix = attName.substring(6);
/*      */ 
/* 1472 */         if (this.namespaceStack.getNamespaceForPrefix(prefix) == null) {
/* 1473 */           String uri = attribute.getValue();
/* 1474 */           this.namespaceStack.push(prefix, uri);
/* 1475 */           writeNamespace(prefix, uri);
/*      */         }
/* 1477 */       } else if (attName.equals("xmlns")) {
/* 1478 */         if (this.namespaceStack.getDefaultNamespace() == null) {
/* 1479 */           String uri = attribute.getValue();
/* 1480 */           this.namespaceStack.push(null, uri);
/* 1481 */           writeNamespace(null, uri);
/*      */         }
/*      */       } else {
/* 1484 */         char quote = this.format.getAttributeQuoteCharacter();
/* 1485 */         this.writer.write(" ");
/* 1486 */         this.writer.write(attribute.getQualifiedName());
/* 1487 */         this.writer.write("=");
/* 1488 */         this.writer.write(quote);
/* 1489 */         writeEscapeAttributeEntities(attribute.getValue());
/* 1490 */         this.writer.write(quote);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void writeAttribute(Attribute attribute) throws IOException {
/* 1496 */     this.writer.write(" ");
/* 1497 */     this.writer.write(attribute.getQualifiedName());
/* 1498 */     this.writer.write("=");
/*      */ 
/* 1500 */     char quote = this.format.getAttributeQuoteCharacter();
/* 1501 */     this.writer.write(quote);
/*      */ 
/* 1503 */     writeEscapeAttributeEntities(attribute.getValue());
/*      */ 
/* 1505 */     this.writer.write(quote);
/* 1506 */     this.lastOutputNodeType = 2;
/*      */   }
/*      */ 
/*      */   protected void writeAttributes(Attributes attributes) throws IOException {
/* 1510 */     int i = 0; for (int size = attributes.getLength(); i < size; i++)
/* 1511 */       writeAttribute(attributes, i);
/*      */   }
/*      */ 
/*      */   protected void writeAttribute(Attributes attributes, int index)
/*      */     throws IOException
/*      */   {
/* 1517 */     char quote = this.format.getAttributeQuoteCharacter();
/* 1518 */     this.writer.write(" ");
/* 1519 */     this.writer.write(attributes.getQName(index));
/* 1520 */     this.writer.write("=");
/* 1521 */     this.writer.write(quote);
/* 1522 */     writeEscapeAttributeEntities(attributes.getValue(index));
/* 1523 */     this.writer.write(quote);
/*      */   }
/*      */ 
/*      */   protected void indent() throws IOException {
/* 1527 */     String indent = this.format.getIndent();
/*      */ 
/* 1529 */     if ((indent != null) && (indent.length() > 0))
/* 1530 */       for (int i = 0; i < this.indentLevel; i++)
/* 1531 */         this.writer.write(indent);
/*      */   }
/*      */ 
/*      */   protected void writePrintln()
/*      */     throws IOException
/*      */   {
/* 1545 */     if (this.format.isNewlines()) {
/* 1546 */       String seperator = this.format.getLineSeparator();
/* 1547 */       if (this.lastChar != seperator.charAt(seperator.length() - 1))
/* 1548 */         this.writer.write(this.format.getLineSeparator());
/*      */     }
/*      */   }
/*      */ 
/*      */   protected Writer createWriter(OutputStream outStream, String encoding)
/*      */     throws UnsupportedEncodingException
/*      */   {
/* 1568 */     return new BufferedWriter(new OutputStreamWriter(outStream, encoding));
/*      */   }
/*      */ 
/*      */   protected void writeDeclaration()
/*      */     throws IOException
/*      */   {
/* 1581 */     String encoding = this.format.getEncoding();
/*      */ 
/* 1584 */     if (!this.format.isSuppressDeclaration())
/*      */     {
/* 1586 */       if (encoding.equals("UTF8")) {
/* 1587 */         this.writer.write("<?xml version=\"1.0\"");
/*      */ 
/* 1589 */         if (!this.format.isOmitEncoding()) {
/* 1590 */           this.writer.write(" encoding=\"UTF-8\"");
/*      */         }
/*      */ 
/* 1593 */         this.writer.write("?>");
/*      */       } else {
/* 1595 */         this.writer.write("<?xml version=\"1.0\"");
/*      */ 
/* 1597 */         if (!this.format.isOmitEncoding()) {
/* 1598 */           this.writer.write(" encoding=\"" + encoding + "\"");
/*      */         }
/*      */ 
/* 1601 */         this.writer.write("?>");
/*      */       }
/*      */ 
/* 1604 */       if (this.format.isNewLineAfterDeclaration())
/* 1605 */         println();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void writeClose(String qualifiedName) throws IOException
/*      */   {
/* 1611 */     this.writer.write("</");
/* 1612 */     this.writer.write(qualifiedName);
/* 1613 */     this.writer.write(">");
/*      */   }
/*      */ 
/*      */   protected void writeEmptyElementClose(String qualifiedName)
/*      */     throws IOException
/*      */   {
/* 1619 */     if (!this.format.isExpandEmptyElements()) {
/* 1620 */       this.writer.write("/>");
/*      */     } else {
/* 1622 */       this.writer.write("></");
/* 1623 */       this.writer.write(qualifiedName);
/* 1624 */       this.writer.write(">");
/*      */     }
/*      */   }
/*      */ 
/*      */   protected boolean isExpandEmptyElements() {
/* 1629 */     return this.format.isExpandEmptyElements();
/*      */   }
/*      */ 
/*      */   protected String escapeElementEntities(String text)
/*      */   {
/* 1643 */     char[] block = null;
/*      */ 
/* 1645 */     int last = 0;
/* 1646 */     int size = text.length();
/*      */ 
/* 1648 */     for (int i = 0; i < size; i++) {
/* 1649 */       String entity = null;
/* 1650 */       char c = text.charAt(i);
/*      */ 
/* 1652 */       switch (c) {
/*      */       case '<':
/* 1654 */         entity = "&lt;";
/*      */ 
/* 1656 */         break;
/*      */       case '>':
/* 1659 */         entity = "&gt;";
/*      */ 
/* 1661 */         break;
/*      */       case '&':
/* 1664 */         entity = "&amp;";
/*      */ 
/* 1666 */         break;
/*      */       case '\t':
/*      */       case '\n':
/*      */       case '\r':
/* 1673 */         if (this.preserve)
/* 1674 */           entity = String.valueOf(c); break;
/*      */       default:
/* 1681 */         if ((c < ' ') || (shouldEncodeChar(c))) {
/* 1682 */           entity = "&#" + c + ";";
/*      */         }
/*      */ 
/*      */         break;
/*      */       }
/*      */ 
/* 1688 */       if (entity != null) {
/* 1689 */         if (block == null) {
/* 1690 */           block = text.toCharArray();
/*      */         }
/*      */ 
/* 1693 */         this.buffer.append(block, last, i - last);
/* 1694 */         this.buffer.append(entity);
/* 1695 */         last = i + 1;
/*      */       }
/*      */     }
/*      */ 
/* 1699 */     if (last == 0) {
/* 1700 */       return text;
/*      */     }
/*      */ 
/* 1703 */     if (last < size) {
/* 1704 */       if (block == null) {
/* 1705 */         block = text.toCharArray();
/*      */       }
/*      */ 
/* 1708 */       this.buffer.append(block, last, i - last);
/*      */     }
/*      */ 
/* 1711 */     String answer = this.buffer.toString();
/* 1712 */     this.buffer.setLength(0);
/*      */ 
/* 1714 */     return answer;
/*      */   }
/*      */ 
/*      */   protected void writeEscapeAttributeEntities(String txt) throws IOException {
/* 1718 */     if (txt != null) {
/* 1719 */       String escapedText = escapeAttributeEntities(txt);
/* 1720 */       this.writer.write(escapedText);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected String escapeAttributeEntities(String text)
/*      */   {
/* 1735 */     char quote = this.format.getAttributeQuoteCharacter();
/*      */ 
/* 1737 */     char[] block = null;
/*      */ 
/* 1739 */     int last = 0;
/* 1740 */     int size = text.length();
/*      */ 
/* 1742 */     for (int i = 0; i < size; i++) {
/* 1743 */       String entity = null;
/* 1744 */       char c = text.charAt(i);
/*      */ 
/* 1746 */       switch (c) {
/*      */       case '<':
/* 1748 */         entity = "&lt;";
/*      */ 
/* 1750 */         break;
/*      */       case '>':
/* 1753 */         entity = "&gt;";
/*      */ 
/* 1755 */         break;
/*      */       case '\'':
/* 1759 */         if (quote == '\'')
/* 1760 */           entity = "&apos;"; break;
/*      */       case '"':
/* 1767 */         if (quote == '"')
/* 1768 */           entity = "&quot;"; break;
/*      */       case '&':
/* 1774 */         entity = "&amp;";
/*      */ 
/* 1776 */         break;
/*      */       case '\t':
/*      */       case '\n':
/*      */       case '\r':
/* 1783 */         break;
/*      */       default:
/* 1787 */         if ((c < ' ') || (shouldEncodeChar(c))) {
/* 1788 */           entity = "&#" + c + ";";
/*      */         }
/*      */ 
/*      */         break;
/*      */       }
/*      */ 
/* 1794 */       if (entity != null) {
/* 1795 */         if (block == null) {
/* 1796 */           block = text.toCharArray();
/*      */         }
/*      */ 
/* 1799 */         this.buffer.append(block, last, i - last);
/* 1800 */         this.buffer.append(entity);
/* 1801 */         last = i + 1;
/*      */       }
/*      */     }
/*      */ 
/* 1805 */     if (last == 0) {
/* 1806 */       return text;
/*      */     }
/*      */ 
/* 1809 */     if (last < size) {
/* 1810 */       if (block == null) {
/* 1811 */         block = text.toCharArray();
/*      */       }
/*      */ 
/* 1814 */       this.buffer.append(block, last, i - last);
/*      */     }
/*      */ 
/* 1817 */     String answer = this.buffer.toString();
/* 1818 */     this.buffer.setLength(0);
/*      */ 
/* 1820 */     return answer;
/*      */   }
/*      */ 
/*      */   protected boolean shouldEncodeChar(char c)
/*      */   {
/* 1833 */     int max = getMaximumAllowedCharacter();
/*      */ 
/* 1835 */     return (max > 0) && (c > max);
/*      */   }
/*      */ 
/*      */   protected int defaultMaximumAllowedCharacter()
/*      */   {
/* 1846 */     String encoding = this.format.getEncoding();
/*      */ 
/* 1848 */     if ((encoding != null) && 
/* 1849 */       (encoding.equals("US-ASCII"))) {
/* 1850 */       return 127;
/*      */     }
/*      */ 
/* 1855 */     return -1;
/*      */   }
/*      */ 
/*      */   protected boolean isNamespaceDeclaration(Namespace ns) {
/* 1859 */     if ((ns != null) && (ns != Namespace.XML_NAMESPACE)) {
/* 1860 */       String uri = ns.getURI();
/*      */ 
/* 1862 */       if ((uri != null) && 
/* 1863 */         (!this.namespaceStack.contains(ns))) {
/* 1864 */         return true;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1869 */     return false;
/*      */   }
/*      */ 
/*      */   protected void handleException(IOException e) throws SAXException {
/* 1873 */     throw new SAXException(e);
/*      */   }
/*      */ 
/*      */   protected OutputFormat getOutputFormat()
/*      */   {
/* 1887 */     return this.format;
/*      */   }
/*      */ 
/*      */   public boolean resolveEntityRefs() {
/* 1891 */     return this.resolveEntityRefs;
/*      */   }
/*      */ 
/*      */   public void setResolveEntityRefs(boolean resolve) {
/* 1895 */     this.resolveEntityRefs = resolve;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.XMLWriter
 * JD-Core Version:    0.6.2
 */