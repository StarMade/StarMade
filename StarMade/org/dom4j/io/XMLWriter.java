package org.dom4j.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Text;
import org.dom4j.tree.NamespaceStack;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.XMLFilterImpl;

public class XMLWriter
  extends XMLFilterImpl
  implements LexicalHandler
{
  private static final String PAD_TEXT = " ";
  protected static final String[] LEXICAL_HANDLER_NAMES = { "http://xml.org/sax/properties/lexical-handler", "http://xml.org/sax/handlers/LexicalHandler" };
  protected static final OutputFormat DEFAULT_FORMAT = new OutputFormat();
  private boolean resolveEntityRefs = true;
  protected int lastOutputNodeType;
  private boolean lastElementClosed = false;
  protected boolean preserve = false;
  protected Writer writer;
  private NamespaceStack namespaceStack = new NamespaceStack();
  private OutputFormat format;
  private boolean escapeText = true;
  private int indentLevel = 0;
  private StringBuffer buffer = new StringBuffer();
  private boolean charsAdded = false;
  private char lastChar;
  private boolean autoFlush;
  private LexicalHandler lexicalHandler;
  private boolean showCommentsInDTDs;
  private boolean inDTD;
  private Map namespacesMap;
  private int maximumAllowedCharacter;
  
  public XMLWriter(Writer writer)
  {
    this(writer, DEFAULT_FORMAT);
  }
  
  public XMLWriter(Writer writer, OutputFormat format)
  {
    this.writer = writer;
    this.format = format;
    this.namespaceStack.push(Namespace.NO_NAMESPACE);
  }
  
  public XMLWriter()
  {
    this.format = DEFAULT_FORMAT;
    this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
    this.autoFlush = true;
    this.namespaceStack.push(Namespace.NO_NAMESPACE);
  }
  
  public XMLWriter(OutputStream out)
    throws UnsupportedEncodingException
  {
    this.format = DEFAULT_FORMAT;
    this.writer = createWriter(out, this.format.getEncoding());
    this.autoFlush = true;
    this.namespaceStack.push(Namespace.NO_NAMESPACE);
  }
  
  public XMLWriter(OutputStream out, OutputFormat format)
    throws UnsupportedEncodingException
  {
    this.format = format;
    this.writer = createWriter(out, format.getEncoding());
    this.autoFlush = true;
    this.namespaceStack.push(Namespace.NO_NAMESPACE);
  }
  
  public XMLWriter(OutputFormat format)
    throws UnsupportedEncodingException
  {
    this.format = format;
    this.writer = createWriter(System.out, format.getEncoding());
    this.autoFlush = true;
    this.namespaceStack.push(Namespace.NO_NAMESPACE);
  }
  
  public void setWriter(Writer writer)
  {
    this.writer = writer;
    this.autoFlush = false;
  }
  
  public void setOutputStream(OutputStream out)
    throws UnsupportedEncodingException
  {
    this.writer = createWriter(out, this.format.getEncoding());
    this.autoFlush = true;
  }
  
  public boolean isEscapeText()
  {
    return this.escapeText;
  }
  
  public void setEscapeText(boolean escapeText)
  {
    this.escapeText = escapeText;
  }
  
  public void setIndentLevel(int indentLevel)
  {
    this.indentLevel = indentLevel;
  }
  
  public int getMaximumAllowedCharacter()
  {
    if (this.maximumAllowedCharacter == 0) {
      this.maximumAllowedCharacter = defaultMaximumAllowedCharacter();
    }
    return this.maximumAllowedCharacter;
  }
  
  public void setMaximumAllowedCharacter(int maximumAllowedCharacter)
  {
    this.maximumAllowedCharacter = maximumAllowedCharacter;
  }
  
  public void flush()
    throws IOException
  {
    this.writer.flush();
  }
  
  public void close()
    throws IOException
  {
    this.writer.close();
  }
  
  public void println()
    throws IOException
  {
    this.writer.write(this.format.getLineSeparator());
  }
  
  public void write(Attribute attribute)
    throws IOException
  {
    writeAttribute(attribute);
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(Document doc)
    throws IOException
  {
    writeDeclaration();
    if (doc.getDocType() != null)
    {
      indent();
      writeDocType(doc.getDocType());
    }
    int local_i = 0;
    int size = doc.nodeCount();
    while (local_i < size)
    {
      Node node = doc.node(local_i);
      writeNode(node);
      local_i++;
    }
    writePrintln();
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(Element element)
    throws IOException
  {
    writeElement(element);
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(CDATA cdata)
    throws IOException
  {
    writeCDATA(cdata.getText());
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(Comment comment)
    throws IOException
  {
    writeComment(comment.getText());
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(DocumentType docType)
    throws IOException
  {
    writeDocType(docType);
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(Entity entity)
    throws IOException
  {
    writeEntity(entity);
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(Namespace namespace)
    throws IOException
  {
    writeNamespace(namespace);
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(ProcessingInstruction processingInstruction)
    throws IOException
  {
    writeProcessingInstruction(processingInstruction);
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(String text)
    throws IOException
  {
    writeString(text);
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(Text text)
    throws IOException
  {
    writeString(text.getText());
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(Node node)
    throws IOException
  {
    writeNode(node);
    if (this.autoFlush) {
      flush();
    }
  }
  
  public void write(Object object)
    throws IOException
  {
    if ((object instanceof Node))
    {
      write((Node)object);
    }
    else if ((object instanceof String))
    {
      write((String)object);
    }
    else if ((object instanceof List))
    {
      List list = (List)object;
      int local_i = 0;
      int size = list.size();
      while (local_i < size)
      {
        write(list.get(local_i));
        local_i++;
      }
    }
    else if (object != null)
    {
      throw new IOException("Invalid object: " + object);
    }
  }
  
  public void writeOpen(Element element)
    throws IOException
  {
    this.writer.write("<");
    this.writer.write(element.getQualifiedName());
    writeAttributes(element);
    this.writer.write(">");
  }
  
  public void writeClose(Element element)
    throws IOException
  {
    writeClose(element.getQualifiedName());
  }
  
  public void parse(InputSource source)
    throws IOException, SAXException
  {
    installLexicalHandler();
    super.parse(source);
  }
  
  public void setProperty(String name, Object value)
    throws SAXNotRecognizedException, SAXNotSupportedException
  {
    for (int local_i = 0; local_i < LEXICAL_HANDLER_NAMES.length; local_i++) {
      if (LEXICAL_HANDLER_NAMES[local_i].equals(name))
      {
        setLexicalHandler((LexicalHandler)value);
        return;
      }
    }
    super.setProperty(name, value);
  }
  
  public Object getProperty(String name)
    throws SAXNotRecognizedException, SAXNotSupportedException
  {
    for (int local_i = 0; local_i < LEXICAL_HANDLER_NAMES.length; local_i++) {
      if (LEXICAL_HANDLER_NAMES[local_i].equals(name)) {
        return getLexicalHandler();
      }
    }
    return super.getProperty(name);
  }
  
  public void setLexicalHandler(LexicalHandler handler)
  {
    if (handler == null) {
      throw new NullPointerException("Null lexical handler");
    }
    this.lexicalHandler = handler;
  }
  
  public LexicalHandler getLexicalHandler()
  {
    return this.lexicalHandler;
  }
  
  public void setDocumentLocator(Locator locator)
  {
    super.setDocumentLocator(locator);
  }
  
  public void startDocument()
    throws SAXException
  {
    try
    {
      writeDeclaration();
      super.startDocument();
    }
    catch (IOException local_e)
    {
      handleException(local_e);
    }
  }
  
  public void endDocument()
    throws SAXException
  {
    super.endDocument();
    if (this.autoFlush) {
      try
      {
        flush();
      }
      catch (IOException local_e) {}
    }
  }
  
  public void startPrefixMapping(String prefix, String uri)
    throws SAXException
  {
    if (this.namespacesMap == null) {
      this.namespacesMap = new HashMap();
    }
    this.namespacesMap.put(prefix, uri);
    super.startPrefixMapping(prefix, uri);
  }
  
  public void endPrefixMapping(String prefix)
    throws SAXException
  {
    super.endPrefixMapping(prefix);
  }
  
  public void startElement(String namespaceURI, String localName, String qName, Attributes attributes)
    throws SAXException
  {
    try
    {
      this.charsAdded = false;
      writePrintln();
      indent();
      this.writer.write("<");
      this.writer.write(qName);
      writeNamespaces();
      writeAttributes(attributes);
      this.writer.write(">");
      this.indentLevel += 1;
      this.lastOutputNodeType = 1;
      this.lastElementClosed = false;
      super.startElement(namespaceURI, localName, qName, attributes);
    }
    catch (IOException local_e)
    {
      handleException(local_e);
    }
  }
  
  public void endElement(String namespaceURI, String localName, String qName)
    throws SAXException
  {
    try
    {
      this.charsAdded = false;
      this.indentLevel -= 1;
      if (this.lastElementClosed)
      {
        writePrintln();
        indent();
      }
      boolean hadContent = true;
      if (hadContent) {
        writeClose(qName);
      } else {
        writeEmptyElementClose(qName);
      }
      this.lastOutputNodeType = 1;
      this.lastElementClosed = true;
      super.endElement(namespaceURI, localName, qName);
    }
    catch (IOException hadContent)
    {
      handleException(hadContent);
    }
  }
  
  public void characters(char[] local_ch, int start, int length)
    throws SAXException
  {
    if ((local_ch == null) || (local_ch.length == 0) || (length <= 0)) {
      return;
    }
    try
    {
      String string = String.valueOf(local_ch, start, length);
      if (this.escapeText) {
        string = escapeElementEntities(string);
      }
      if (this.format.isTrimText())
      {
        if ((this.lastOutputNodeType == 3) && (!this.charsAdded)) {
          this.writer.write(32);
        } else if ((this.charsAdded) && (Character.isWhitespace(this.lastChar))) {
          this.writer.write(32);
        } else if ((this.lastOutputNodeType == 1) && (this.format.isPadText()) && (this.lastElementClosed) && (Character.isWhitespace(local_ch[0]))) {
          this.writer.write(" ");
        }
        String delim = "";
        StringTokenizer tokens = new StringTokenizer(string);
        while (tokens.hasMoreTokens())
        {
          this.writer.write(delim);
          this.writer.write(tokens.nextToken());
          delim = " ";
        }
      }
      else
      {
        this.writer.write(string);
      }
      this.charsAdded = true;
      this.lastChar = local_ch[(start + length - 1)];
      this.lastOutputNodeType = 3;
      super.characters(local_ch, start, length);
    }
    catch (IOException string)
    {
      handleException(string);
    }
  }
  
  public void ignorableWhitespace(char[] local_ch, int start, int length)
    throws SAXException
  {
    super.ignorableWhitespace(local_ch, start, length);
  }
  
  public void processingInstruction(String target, String data)
    throws SAXException
  {
    try
    {
      indent();
      this.writer.write("<?");
      this.writer.write(target);
      this.writer.write(" ");
      this.writer.write(data);
      this.writer.write("?>");
      writePrintln();
      this.lastOutputNodeType = 7;
      super.processingInstruction(target, data);
    }
    catch (IOException local_e)
    {
      handleException(local_e);
    }
  }
  
  public void notationDecl(String name, String publicID, String systemID)
    throws SAXException
  {
    super.notationDecl(name, publicID, systemID);
  }
  
  public void unparsedEntityDecl(String name, String publicID, String systemID, String notationName)
    throws SAXException
  {
    super.unparsedEntityDecl(name, publicID, systemID, notationName);
  }
  
  public void startDTD(String name, String publicID, String systemID)
    throws SAXException
  {
    this.inDTD = true;
    try
    {
      writeDocType(name, publicID, systemID);
    }
    catch (IOException local_e)
    {
      handleException(local_e);
    }
    if (this.lexicalHandler != null) {
      this.lexicalHandler.startDTD(name, publicID, systemID);
    }
  }
  
  public void endDTD()
    throws SAXException
  {
    this.inDTD = false;
    if (this.lexicalHandler != null) {
      this.lexicalHandler.endDTD();
    }
  }
  
  public void startCDATA()
    throws SAXException
  {
    try
    {
      this.writer.write("<![CDATA[");
    }
    catch (IOException local_e)
    {
      handleException(local_e);
    }
    if (this.lexicalHandler != null) {
      this.lexicalHandler.startCDATA();
    }
  }
  
  public void endCDATA()
    throws SAXException
  {
    try
    {
      this.writer.write("]]>");
    }
    catch (IOException local_e)
    {
      handleException(local_e);
    }
    if (this.lexicalHandler != null) {
      this.lexicalHandler.endCDATA();
    }
  }
  
  public void startEntity(String name)
    throws SAXException
  {
    try
    {
      writeEntityRef(name);
    }
    catch (IOException local_e)
    {
      handleException(local_e);
    }
    if (this.lexicalHandler != null) {
      this.lexicalHandler.startEntity(name);
    }
  }
  
  public void endEntity(String name)
    throws SAXException
  {
    if (this.lexicalHandler != null) {
      this.lexicalHandler.endEntity(name);
    }
  }
  
  public void comment(char[] local_ch, int start, int length)
    throws SAXException
  {
    if ((this.showCommentsInDTDs) || (!this.inDTD)) {
      try
      {
        this.charsAdded = false;
        writeComment(new String(local_ch, start, length));
      }
      catch (IOException local_e)
      {
        handleException(local_e);
      }
    }
    if (this.lexicalHandler != null) {
      this.lexicalHandler.comment(local_ch, start, length);
    }
  }
  
  protected void writeElement(Element element)
    throws IOException
  {
    int size = element.nodeCount();
    String qualifiedName = element.getQualifiedName();
    writePrintln();
    indent();
    this.writer.write("<");
    this.writer.write(qualifiedName);
    int previouslyDeclaredNamespaces = this.namespaceStack.size();
    Namespace local_ns = element.getNamespace();
    if (isNamespaceDeclaration(local_ns))
    {
      this.namespaceStack.push(local_ns);
      writeNamespace(local_ns);
    }
    boolean textOnly = true;
    for (int local_i = 0; local_i < size; local_i++)
    {
      Node node = element.node(local_i);
      if ((node instanceof Namespace))
      {
        Namespace additional = (Namespace)node;
        if (isNamespaceDeclaration(additional))
        {
          this.namespaceStack.push(additional);
          writeNamespace(additional);
        }
      }
      else if ((node instanceof Element))
      {
        textOnly = false;
      }
      else if ((node instanceof Comment))
      {
        textOnly = false;
      }
    }
    writeAttributes(element);
    this.lastOutputNodeType = 1;
    if (size <= 0)
    {
      writeEmptyElementClose(qualifiedName);
    }
    else
    {
      this.writer.write(">");
      if (textOnly)
      {
        writeElementContent(element);
      }
      else
      {
        this.indentLevel += 1;
        writeElementContent(element);
        this.indentLevel -= 1;
        writePrintln();
        indent();
      }
      this.writer.write("</");
      this.writer.write(qualifiedName);
      this.writer.write(">");
    }
    while (this.namespaceStack.size() > previouslyDeclaredNamespaces) {
      this.namespaceStack.pop();
    }
    this.lastOutputNodeType = 1;
  }
  
  protected final boolean isElementSpacePreserved(Element element)
  {
    Attribute attr = element.attribute("space");
    boolean preserveFound = this.preserve;
    if (attr != null) {
      if (("xml".equals(attr.getNamespacePrefix())) && ("preserve".equals(attr.getText()))) {
        preserveFound = true;
      } else {
        preserveFound = false;
      }
    }
    return preserveFound;
  }
  
  protected void writeElementContent(Element element)
    throws IOException
  {
    boolean trim = this.format.isTrimText();
    boolean oldPreserve = this.preserve;
    if (trim)
    {
      this.preserve = isElementSpacePreserved(element);
      trim = !this.preserve;
    }
    if (trim)
    {
      Text lastTextNode = null;
      StringBuffer buff = null;
      boolean textOnly = true;
      int local_i = 0;
      int size = element.nodeCount();
      while (local_i < size)
      {
        Node node = element.node(local_i);
        if ((node instanceof Text))
        {
          if (lastTextNode == null)
          {
            lastTextNode = (Text)node;
          }
          else
          {
            if (buff == null) {
              buff = new StringBuffer(lastTextNode.getText());
            }
            buff.append(((Text)node).getText());
          }
        }
        else
        {
          if ((!textOnly) && (this.format.isPadText()))
          {
            char firstChar = 'a';
            if (buff != null) {
              firstChar = buff.charAt(0);
            } else if (lastTextNode != null) {
              firstChar = lastTextNode.getText().charAt(0);
            }
            if (Character.isWhitespace(firstChar)) {
              this.writer.write(" ");
            }
          }
          if (lastTextNode != null)
          {
            if (buff != null)
            {
              writeString(buff.toString());
              buff = null;
            }
            else
            {
              writeString(lastTextNode.getText());
            }
            if (this.format.isPadText())
            {
              char firstChar = 'a';
              if (buff != null)
              {
                firstChar = buff.charAt(buff.length() - 1);
              }
              else if (lastTextNode != null)
              {
                String txt = lastTextNode.getText();
                firstChar = txt.charAt(txt.length() - 1);
              }
              if (Character.isWhitespace(firstChar)) {
                this.writer.write(" ");
              }
            }
            lastTextNode = null;
          }
          textOnly = false;
          writeNode(node);
        }
        local_i++;
      }
      if (lastTextNode != null)
      {
        if ((!textOnly) && (this.format.isPadText()))
        {
          char local_i = 'a';
          if (buff != null) {
            local_i = buff.charAt(0);
          } else {
            local_i = lastTextNode.getText().charAt(0);
          }
          if (Character.isWhitespace(local_i)) {
            this.writer.write(" ");
          }
        }
        if (buff != null)
        {
          writeString(buff.toString());
          buff = null;
        }
        else
        {
          writeString(lastTextNode.getText());
        }
        lastTextNode = null;
      }
    }
    else
    {
      Node lastTextNode = null;
      int buff = 0;
      int textOnly = element.nodeCount();
      while (buff < textOnly)
      {
        Node local_i = element.node(buff);
        if ((local_i instanceof Text))
        {
          writeNode(local_i);
          lastTextNode = local_i;
        }
        else
        {
          if ((lastTextNode != null) && (this.format.isPadText()))
          {
            String size = lastTextNode.getText();
            char node = size.charAt(size.length() - 1);
            if (Character.isWhitespace(node)) {
              this.writer.write(" ");
            }
          }
          writeNode(local_i);
          lastTextNode = null;
        }
        buff++;
      }
    }
    this.preserve = oldPreserve;
  }
  
  protected void writeCDATA(String text)
    throws IOException
  {
    this.writer.write("<![CDATA[");
    if (text != null) {
      this.writer.write(text);
    }
    this.writer.write("]]>");
    this.lastOutputNodeType = 4;
  }
  
  protected void writeDocType(DocumentType docType)
    throws IOException
  {
    if (docType != null)
    {
      docType.write(this.writer);
      writePrintln();
    }
  }
  
  protected void writeNamespace(Namespace namespace)
    throws IOException
  {
    if (namespace != null) {
      writeNamespace(namespace.getPrefix(), namespace.getURI());
    }
  }
  
  protected void writeNamespaces()
    throws IOException
  {
    if (this.namespacesMap != null)
    {
      Iterator iter = this.namespacesMap.entrySet().iterator();
      while (iter.hasNext())
      {
        Map.Entry entry = (Map.Entry)iter.next();
        String prefix = (String)entry.getKey();
        String uri = (String)entry.getValue();
        writeNamespace(prefix, uri);
      }
      this.namespacesMap = null;
    }
  }
  
  protected void writeNamespace(String prefix, String uri)
    throws IOException
  {
    if ((prefix != null) && (prefix.length() > 0))
    {
      this.writer.write(" xmlns:");
      this.writer.write(prefix);
      this.writer.write("=\"");
    }
    else
    {
      this.writer.write(" xmlns=\"");
    }
    this.writer.write(uri);
    this.writer.write("\"");
  }
  
  protected void writeProcessingInstruction(ProcessingInstruction local_pi)
    throws IOException
  {
    this.writer.write("<?");
    this.writer.write(local_pi.getName());
    this.writer.write(" ");
    this.writer.write(local_pi.getText());
    this.writer.write("?>");
    writePrintln();
    this.lastOutputNodeType = 7;
  }
  
  protected void writeString(String text)
    throws IOException
  {
    if ((text != null) && (text.length() > 0))
    {
      if (this.escapeText) {
        text = escapeElementEntities(text);
      }
      if (this.format.isTrimText())
      {
        boolean first = true;
        StringTokenizer tokenizer = new StringTokenizer(text);
        while (tokenizer.hasMoreTokens())
        {
          String token = tokenizer.nextToken();
          if (first)
          {
            first = false;
            if (this.lastOutputNodeType == 3) {
              this.writer.write(" ");
            }
          }
          else
          {
            this.writer.write(" ");
          }
          this.writer.write(token);
          this.lastOutputNodeType = 3;
          this.lastChar = token.charAt(token.length() - 1);
        }
      }
      else
      {
        this.lastOutputNodeType = 3;
        this.writer.write(text);
        this.lastChar = text.charAt(text.length() - 1);
      }
    }
  }
  
  protected void writeNodeText(Node node)
    throws IOException
  {
    String text = node.getText();
    if ((text != null) && (text.length() > 0))
    {
      if (this.escapeText) {
        text = escapeElementEntities(text);
      }
      this.lastOutputNodeType = 3;
      this.writer.write(text);
      this.lastChar = text.charAt(text.length() - 1);
    }
  }
  
  protected void writeNode(Node node)
    throws IOException
  {
    int nodeType = node.getNodeType();
    switch (nodeType)
    {
    case 1: 
      writeElement((Element)node);
      break;
    case 2: 
      writeAttribute((Attribute)node);
      break;
    case 3: 
      writeNodeText(node);
      break;
    case 4: 
      writeCDATA(node.getText());
      break;
    case 5: 
      writeEntity((Entity)node);
      break;
    case 7: 
      writeProcessingInstruction((ProcessingInstruction)node);
      break;
    case 8: 
      writeComment(node.getText());
      break;
    case 9: 
      write((Document)node);
      break;
    case 10: 
      writeDocType((DocumentType)node);
      break;
    case 13: 
      break;
    case 6: 
    case 11: 
    case 12: 
    default: 
      throw new IOException("Invalid node type: " + node);
    }
  }
  
  protected void installLexicalHandler()
  {
    XMLReader parent = getParent();
    if (parent == null) {
      throw new NullPointerException("No parent for filter");
    }
    for (int local_i = 0; local_i < LEXICAL_HANDLER_NAMES.length; local_i++) {
      try
      {
        parent.setProperty(LEXICAL_HANDLER_NAMES[local_i], this);
      }
      catch (SAXNotRecognizedException local_ex) {}catch (SAXNotSupportedException local_ex) {}
    }
  }
  
  protected void writeDocType(String name, String publicID, String systemID)
    throws IOException
  {
    boolean hasPublic = false;
    this.writer.write("<!DOCTYPE ");
    this.writer.write(name);
    if ((publicID != null) && (!publicID.equals("")))
    {
      this.writer.write(" PUBLIC \"");
      this.writer.write(publicID);
      this.writer.write("\"");
      hasPublic = true;
    }
    if ((systemID != null) && (!systemID.equals("")))
    {
      if (!hasPublic) {
        this.writer.write(" SYSTEM");
      }
      this.writer.write(" \"");
      this.writer.write(systemID);
      this.writer.write("\"");
    }
    this.writer.write(">");
    writePrintln();
  }
  
  protected void writeEntity(Entity entity)
    throws IOException
  {
    if (!resolveEntityRefs()) {
      writeEntityRef(entity.getName());
    } else {
      this.writer.write(entity.getText());
    }
  }
  
  protected void writeEntityRef(String name)
    throws IOException
  {
    this.writer.write("&");
    this.writer.write(name);
    this.writer.write(";");
    this.lastOutputNodeType = 5;
  }
  
  protected void writeComment(String text)
    throws IOException
  {
    if (this.format.isNewlines())
    {
      println();
      indent();
    }
    this.writer.write("<!--");
    this.writer.write(text);
    this.writer.write("-->");
    this.lastOutputNodeType = 8;
  }
  
  protected void writeAttributes(Element element)
    throws IOException
  {
    int local_i = 0;
    int size = element.attributeCount();
    while (local_i < size)
    {
      Attribute attribute = element.attribute(local_i);
      Namespace local_ns = attribute.getNamespace();
      if ((local_ns != null) && (local_ns != Namespace.NO_NAMESPACE) && (local_ns != Namespace.XML_NAMESPACE))
      {
        String prefix = local_ns.getPrefix();
        String uri = this.namespaceStack.getURI(prefix);
        if (!local_ns.getURI().equals(uri))
        {
          writeNamespace(local_ns);
          this.namespaceStack.push(local_ns);
        }
      }
      String prefix = attribute.getName();
      if (prefix.startsWith("xmlns:"))
      {
        String uri = prefix.substring(6);
        if (this.namespaceStack.getNamespaceForPrefix(uri) == null)
        {
          String uri = attribute.getValue();
          this.namespaceStack.push(uri, uri);
          writeNamespace(uri, uri);
        }
      }
      else if (prefix.equals("xmlns"))
      {
        if (this.namespaceStack.getDefaultNamespace() == null)
        {
          String uri = attribute.getValue();
          this.namespaceStack.push(null, uri);
          writeNamespace(null, uri);
        }
      }
      else
      {
        char uri = this.format.getAttributeQuoteCharacter();
        this.writer.write(" ");
        this.writer.write(attribute.getQualifiedName());
        this.writer.write("=");
        this.writer.write(uri);
        writeEscapeAttributeEntities(attribute.getValue());
        this.writer.write(uri);
      }
      local_i++;
    }
  }
  
  protected void writeAttribute(Attribute attribute)
    throws IOException
  {
    this.writer.write(" ");
    this.writer.write(attribute.getQualifiedName());
    this.writer.write("=");
    char quote = this.format.getAttributeQuoteCharacter();
    this.writer.write(quote);
    writeEscapeAttributeEntities(attribute.getValue());
    this.writer.write(quote);
    this.lastOutputNodeType = 2;
  }
  
  protected void writeAttributes(Attributes attributes)
    throws IOException
  {
    int local_i = 0;
    int size = attributes.getLength();
    while (local_i < size)
    {
      writeAttribute(attributes, local_i);
      local_i++;
    }
  }
  
  protected void writeAttribute(Attributes attributes, int index)
    throws IOException
  {
    char quote = this.format.getAttributeQuoteCharacter();
    this.writer.write(" ");
    this.writer.write(attributes.getQName(index));
    this.writer.write("=");
    this.writer.write(quote);
    writeEscapeAttributeEntities(attributes.getValue(index));
    this.writer.write(quote);
  }
  
  protected void indent()
    throws IOException
  {
    String indent = this.format.getIndent();
    if ((indent != null) && (indent.length() > 0)) {
      for (int local_i = 0; local_i < this.indentLevel; local_i++) {
        this.writer.write(indent);
      }
    }
  }
  
  protected void writePrintln()
    throws IOException
  {
    if (this.format.isNewlines())
    {
      String seperator = this.format.getLineSeparator();
      if (this.lastChar != seperator.charAt(seperator.length() - 1)) {
        this.writer.write(this.format.getLineSeparator());
      }
    }
  }
  
  protected Writer createWriter(OutputStream outStream, String encoding)
    throws UnsupportedEncodingException
  {
    return new BufferedWriter(new OutputStreamWriter(outStream, encoding));
  }
  
  protected void writeDeclaration()
    throws IOException
  {
    String encoding = this.format.getEncoding();
    if (!this.format.isSuppressDeclaration())
    {
      if (encoding.equals("UTF8"))
      {
        this.writer.write("<?xml version=\"1.0\"");
        if (!this.format.isOmitEncoding()) {
          this.writer.write(" encoding=\"UTF-8\"");
        }
        this.writer.write("?>");
      }
      else
      {
        this.writer.write("<?xml version=\"1.0\"");
        if (!this.format.isOmitEncoding()) {
          this.writer.write(" encoding=\"" + encoding + "\"");
        }
        this.writer.write("?>");
      }
      if (this.format.isNewLineAfterDeclaration()) {
        println();
      }
    }
  }
  
  protected void writeClose(String qualifiedName)
    throws IOException
  {
    this.writer.write("</");
    this.writer.write(qualifiedName);
    this.writer.write(">");
  }
  
  protected void writeEmptyElementClose(String qualifiedName)
    throws IOException
  {
    if (!this.format.isExpandEmptyElements())
    {
      this.writer.write("/>");
    }
    else
    {
      this.writer.write("></");
      this.writer.write(qualifiedName);
      this.writer.write(">");
    }
  }
  
  protected boolean isExpandEmptyElements()
  {
    return this.format.isExpandEmptyElements();
  }
  
  protected String escapeElementEntities(String text)
  {
    char[] block = null;
    int last = 0;
    int size = text.length();
    for (int local_i = 0; local_i < size; local_i++)
    {
      String entity = null;
      char local_c = text.charAt(local_i);
      switch (local_c)
      {
      case '<': 
        entity = "&lt;";
        break;
      case '>': 
        entity = "&gt;";
        break;
      case '&': 
        entity = "&amp;";
        break;
      case '\t': 
      case '\n': 
      case '\r': 
        if (this.preserve) {
          entity = String.valueOf(local_c);
        }
        break;
      default: 
        if ((local_c < ' ') || (shouldEncodeChar(local_c))) {
          entity = "&#" + local_c + ";";
        }
        break;
      }
      if (entity != null)
      {
        if (block == null) {
          block = text.toCharArray();
        }
        this.buffer.append(block, last, local_i - last);
        this.buffer.append(entity);
        last = local_i + 1;
      }
    }
    if (last == 0) {
      return text;
    }
    if (last < size)
    {
      if (block == null) {
        block = text.toCharArray();
      }
      this.buffer.append(block, last, local_i - last);
    }
    String entity = this.buffer.toString();
    this.buffer.setLength(0);
    return entity;
  }
  
  protected void writeEscapeAttributeEntities(String txt)
    throws IOException
  {
    if (txt != null)
    {
      String escapedText = escapeAttributeEntities(txt);
      this.writer.write(escapedText);
    }
  }
  
  protected String escapeAttributeEntities(String text)
  {
    char quote = this.format.getAttributeQuoteCharacter();
    char[] block = null;
    int last = 0;
    int size = text.length();
    for (int local_i = 0; local_i < size; local_i++)
    {
      String entity = null;
      char local_c = text.charAt(local_i);
      switch (local_c)
      {
      case '<': 
        entity = "&lt;";
        break;
      case '>': 
        entity = "&gt;";
        break;
      case '\'': 
        if (quote == '\'') {
          entity = "&apos;";
        }
        break;
      case '"': 
        if (quote == '"') {
          entity = "&quot;";
        }
        break;
      case '&': 
        entity = "&amp;";
        break;
      case '\t': 
      case '\n': 
      case '\r': 
        break;
      default: 
        if ((local_c < ' ') || (shouldEncodeChar(local_c))) {
          entity = "&#" + local_c + ";";
        }
        break;
      }
      if (entity != null)
      {
        if (block == null) {
          block = text.toCharArray();
        }
        this.buffer.append(block, last, local_i - last);
        this.buffer.append(entity);
        last = local_i + 1;
      }
    }
    if (last == 0) {
      return text;
    }
    if (last < size)
    {
      if (block == null) {
        block = text.toCharArray();
      }
      this.buffer.append(block, last, local_i - last);
    }
    String entity = this.buffer.toString();
    this.buffer.setLength(0);
    return entity;
  }
  
  protected boolean shouldEncodeChar(char local_c)
  {
    int max = getMaximumAllowedCharacter();
    return (max > 0) && (local_c > max);
  }
  
  protected int defaultMaximumAllowedCharacter()
  {
    String encoding = this.format.getEncoding();
    if ((encoding != null) && (encoding.equals("US-ASCII"))) {
      return 127;
    }
    return -1;
  }
  
  protected boolean isNamespaceDeclaration(Namespace local_ns)
  {
    if ((local_ns != null) && (local_ns != Namespace.XML_NAMESPACE))
    {
      String uri = local_ns.getURI();
      if ((uri != null) && (!this.namespaceStack.contains(local_ns))) {
        return true;
      }
    }
    return false;
  }
  
  protected void handleException(IOException local_e)
    throws SAXException
  {
    throw new SAXException(local_e);
  }
  
  protected OutputFormat getOutputFormat()
  {
    return this.format;
  }
  
  public boolean resolveEntityRefs()
  {
    return this.resolveEntityRefs;
  }
  
  public void setResolveEntityRefs(boolean resolve)
  {
    this.resolveEntityRefs = resolve;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.io.XMLWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */