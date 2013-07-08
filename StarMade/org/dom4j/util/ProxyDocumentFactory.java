/*   1:    */package org.dom4j.util;
/*   2:    */
/*   3:    */import java.util.Map;
/*   4:    */import org.dom4j.Attribute;
/*   5:    */import org.dom4j.CDATA;
/*   6:    */import org.dom4j.Comment;
/*   7:    */import org.dom4j.Document;
/*   8:    */import org.dom4j.DocumentFactory;
/*   9:    */import org.dom4j.DocumentType;
/*  10:    */import org.dom4j.Element;
/*  11:    */import org.dom4j.Entity;
/*  12:    */import org.dom4j.Namespace;
/*  13:    */import org.dom4j.NodeFilter;
/*  14:    */import org.dom4j.ProcessingInstruction;
/*  15:    */import org.dom4j.QName;
/*  16:    */import org.dom4j.Text;
/*  17:    */import org.dom4j.XPath;
/*  18:    */import org.dom4j.rule.Pattern;
/*  19:    */import org.jaxen.VariableContext;
/*  20:    */
/*  41:    */public abstract class ProxyDocumentFactory
/*  42:    */{
/*  43:    */  private DocumentFactory proxy;
/*  44:    */  
/*  45:    */  public ProxyDocumentFactory()
/*  46:    */  {
/*  47: 47 */    this.proxy = DocumentFactory.getInstance();
/*  48:    */  }
/*  49:    */  
/*  50:    */  public ProxyDocumentFactory(DocumentFactory proxy) {
/*  51: 51 */    this.proxy = proxy;
/*  52:    */  }
/*  53:    */  
/*  55:    */  public Document createDocument()
/*  56:    */  {
/*  57: 57 */    return this.proxy.createDocument();
/*  58:    */  }
/*  59:    */  
/*  60:    */  public Document createDocument(Element rootElement) {
/*  61: 61 */    return this.proxy.createDocument(rootElement);
/*  62:    */  }
/*  63:    */  
/*  64:    */  public DocumentType createDocType(String name, String publicId, String systemId)
/*  65:    */  {
/*  66: 66 */    return this.proxy.createDocType(name, publicId, systemId);
/*  67:    */  }
/*  68:    */  
/*  69:    */  public Element createElement(QName qname) {
/*  70: 70 */    return this.proxy.createElement(qname);
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Element createElement(String name) {
/*  74: 74 */    return this.proxy.createElement(name);
/*  75:    */  }
/*  76:    */  
/*  77:    */  public Attribute createAttribute(Element owner, QName qname, String value) {
/*  78: 78 */    return this.proxy.createAttribute(owner, qname, value);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public Attribute createAttribute(Element owner, String name, String value) {
/*  82: 82 */    return this.proxy.createAttribute(owner, name, value);
/*  83:    */  }
/*  84:    */  
/*  85:    */  public CDATA createCDATA(String text) {
/*  86: 86 */    return this.proxy.createCDATA(text);
/*  87:    */  }
/*  88:    */  
/*  89:    */  public Comment createComment(String text) {
/*  90: 90 */    return this.proxy.createComment(text);
/*  91:    */  }
/*  92:    */  
/*  93:    */  public Text createText(String text) {
/*  94: 94 */    return this.proxy.createText(text);
/*  95:    */  }
/*  96:    */  
/*  97:    */  public Entity createEntity(String name, String text) {
/*  98: 98 */    return this.proxy.createEntity(name, text);
/*  99:    */  }
/* 100:    */  
/* 101:    */  public Namespace createNamespace(String prefix, String uri) {
/* 102:102 */    return this.proxy.createNamespace(prefix, uri);
/* 103:    */  }
/* 104:    */  
/* 105:    */  public ProcessingInstruction createProcessingInstruction(String target, String data)
/* 106:    */  {
/* 107:107 */    return this.proxy.createProcessingInstruction(target, data);
/* 108:    */  }
/* 109:    */  
/* 110:    */  public ProcessingInstruction createProcessingInstruction(String target, Map data)
/* 111:    */  {
/* 112:112 */    return this.proxy.createProcessingInstruction(target, data);
/* 113:    */  }
/* 114:    */  
/* 115:    */  public QName createQName(String localName, Namespace namespace) {
/* 116:116 */    return this.proxy.createQName(localName, namespace);
/* 117:    */  }
/* 118:    */  
/* 119:    */  public QName createQName(String localName) {
/* 120:120 */    return this.proxy.createQName(localName);
/* 121:    */  }
/* 122:    */  
/* 123:    */  public QName createQName(String name, String prefix, String uri) {
/* 124:124 */    return this.proxy.createQName(name, prefix, uri);
/* 125:    */  }
/* 126:    */  
/* 127:    */  public QName createQName(String qualifiedName, String uri) {
/* 128:128 */    return this.proxy.createQName(qualifiedName, uri);
/* 129:    */  }
/* 130:    */  
/* 131:    */  public XPath createXPath(String xpathExpression) {
/* 132:132 */    return this.proxy.createXPath(xpathExpression);
/* 133:    */  }
/* 134:    */  
/* 135:    */  public XPath createXPath(String xpathExpression, VariableContext variableContext)
/* 136:    */  {
/* 137:137 */    return this.proxy.createXPath(xpathExpression, variableContext);
/* 138:    */  }
/* 139:    */  
/* 140:    */  public NodeFilter createXPathFilter(String xpathFilterExpression, VariableContext variableContext)
/* 141:    */  {
/* 142:142 */    return this.proxy.createXPathFilter(xpathFilterExpression, variableContext);
/* 143:    */  }
/* 144:    */  
/* 145:    */  public NodeFilter createXPathFilter(String xpathFilterExpression) {
/* 146:146 */    return this.proxy.createXPathFilter(xpathFilterExpression);
/* 147:    */  }
/* 148:    */  
/* 149:    */  public Pattern createPattern(String xpathPattern) {
/* 150:150 */    return this.proxy.createPattern(xpathPattern);
/* 151:    */  }
/* 152:    */  
/* 154:    */  protected DocumentFactory getProxy()
/* 155:    */  {
/* 156:156 */    return this.proxy;
/* 157:    */  }
/* 158:    */  
/* 159:    */  protected void setProxy(DocumentFactory proxy) {
/* 160:160 */    if (proxy == null)
/* 161:    */    {
/* 162:162 */      proxy = DocumentFactory.getInstance();
/* 163:    */    }
/* 164:    */    
/* 165:165 */    this.proxy = proxy;
/* 166:    */  }
/* 167:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.ProxyDocumentFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */