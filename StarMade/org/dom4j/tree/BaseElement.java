/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.util.List;
/*   4:    */import org.dom4j.Branch;
/*   5:    */import org.dom4j.Document;
/*   6:    */import org.dom4j.DocumentFactory;
/*   7:    */import org.dom4j.Element;
/*   8:    */import org.dom4j.Namespace;
/*   9:    */import org.dom4j.QName;
/*  10:    */
/*  36:    */public class BaseElement
/*  37:    */  extends AbstractElement
/*  38:    */{
/*  39:    */  private QName qname;
/*  40:    */  private Branch parentBranch;
/*  41:    */  protected List content;
/*  42:    */  protected List attributes;
/*  43:    */  
/*  44:    */  public BaseElement(String name)
/*  45:    */  {
/*  46: 46 */    this.qname = getDocumentFactory().createQName(name);
/*  47:    */  }
/*  48:    */  
/*  49:    */  public BaseElement(QName qname) {
/*  50: 50 */    this.qname = qname;
/*  51:    */  }
/*  52:    */  
/*  53:    */  public BaseElement(String name, Namespace namespace) {
/*  54: 54 */    this.qname = getDocumentFactory().createQName(name, namespace);
/*  55:    */  }
/*  56:    */  
/*  57:    */  public Element getParent() {
/*  58: 58 */    Element result = null;
/*  59:    */    
/*  60: 60 */    if ((this.parentBranch instanceof Element)) {
/*  61: 61 */      result = (Element)this.parentBranch;
/*  62:    */    }
/*  63:    */    
/*  64: 64 */    return result;
/*  65:    */  }
/*  66:    */  
/*  67:    */  public void setParent(Element parent) {
/*  68: 68 */    if (((this.parentBranch instanceof Element)) || (parent != null)) {
/*  69: 69 */      this.parentBranch = parent;
/*  70:    */    }
/*  71:    */  }
/*  72:    */  
/*  73:    */  public Document getDocument() {
/*  74: 74 */    if ((this.parentBranch instanceof Document))
/*  75: 75 */      return (Document)this.parentBranch;
/*  76: 76 */    if ((this.parentBranch instanceof Element)) {
/*  77: 77 */      Element parent = (Element)this.parentBranch;
/*  78:    */      
/*  79: 79 */      return parent.getDocument();
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    return null;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public void setDocument(Document document) {
/*  86: 86 */    if (((this.parentBranch instanceof Document)) || (document != null)) {
/*  87: 87 */      this.parentBranch = document;
/*  88:    */    }
/*  89:    */  }
/*  90:    */  
/*  91:    */  public boolean supportsParent() {
/*  92: 92 */    return true;
/*  93:    */  }
/*  94:    */  
/*  95:    */  public QName getQName() {
/*  96: 96 */    return this.qname;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public void setQName(QName name) {
/* 100:100 */    this.qname = name;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public void clearContent() {
/* 104:104 */    contentList().clear();
/* 105:    */  }
/* 106:    */  
/* 107:    */  public void setContent(List content) {
/* 108:108 */    this.content = content;
/* 109:    */    
/* 110:110 */    if ((content instanceof ContentListFacade)) {
/* 111:111 */      this.content = ((ContentListFacade)content).getBackingList();
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 115:    */  public void setAttributes(List attributes) {
/* 116:116 */    this.attributes = attributes;
/* 117:    */    
/* 118:118 */    if ((attributes instanceof ContentListFacade)) {
/* 119:119 */      this.attributes = ((ContentListFacade)attributes).getBackingList();
/* 120:    */    }
/* 121:    */  }
/* 122:    */  
/* 124:    */  protected List contentList()
/* 125:    */  {
/* 126:126 */    if (this.content == null) {
/* 127:127 */      this.content = createContentList();
/* 128:    */    }
/* 129:    */    
/* 130:130 */    return this.content;
/* 131:    */  }
/* 132:    */  
/* 133:    */  protected List attributeList() {
/* 134:134 */    if (this.attributes == null) {
/* 135:135 */      this.attributes = createAttributeList();
/* 136:    */    }
/* 137:    */    
/* 138:138 */    return this.attributes;
/* 139:    */  }
/* 140:    */  
/* 141:    */  protected List attributeList(int size) {
/* 142:142 */    if (this.attributes == null) {
/* 143:143 */      this.attributes = createAttributeList(size);
/* 144:    */    }
/* 145:    */    
/* 146:146 */    return this.attributes;
/* 147:    */  }
/* 148:    */  
/* 149:    */  protected void setAttributeList(List attributeList) {
/* 150:150 */    this.attributes = attributeList;
/* 151:    */  }
/* 152:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.BaseElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */