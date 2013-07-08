/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.Writer;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.List;
/*   7:    */import org.dom4j.DocumentType;
/*   8:    */import org.dom4j.Element;
/*   9:    */import org.dom4j.Visitor;
/*  10:    */
/*  28:    */public abstract class AbstractDocumentType
/*  29:    */  extends AbstractNode
/*  30:    */  implements DocumentType
/*  31:    */{
/*  32:    */  public short getNodeType()
/*  33:    */  {
/*  34: 34 */    return 10;
/*  35:    */  }
/*  36:    */  
/*  37:    */  public String getName() {
/*  38: 38 */    return getElementName();
/*  39:    */  }
/*  40:    */  
/*  41:    */  public void setName(String name) {
/*  42: 42 */    setElementName(name);
/*  43:    */  }
/*  44:    */  
/*  45:    */  public String getPath(Element context)
/*  46:    */  {
/*  47: 47 */    return "";
/*  48:    */  }
/*  49:    */  
/*  50:    */  public String getUniquePath(Element context)
/*  51:    */  {
/*  52: 52 */    return "";
/*  53:    */  }
/*  54:    */  
/*  60:    */  public String getText()
/*  61:    */  {
/*  62: 62 */    List list = getInternalDeclarations();
/*  63:    */    
/*  64: 64 */    if ((list != null) && (list.size() > 0)) {
/*  65: 65 */      StringBuffer buffer = new StringBuffer();
/*  66: 66 */      Iterator iter = list.iterator();
/*  67:    */      
/*  68: 68 */      if (iter.hasNext()) {
/*  69: 69 */        Object decl = iter.next();
/*  70: 70 */        buffer.append(decl.toString());
/*  71:    */        
/*  72: 72 */        while (iter.hasNext()) {
/*  73: 73 */          decl = iter.next();
/*  74: 74 */          buffer.append("\n");
/*  75: 75 */          buffer.append(decl.toString());
/*  76:    */        }
/*  77:    */      }
/*  78:    */      
/*  79: 79 */      return buffer.toString();
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    return "";
/*  83:    */  }
/*  84:    */  
/*  85:    */  public String toString() {
/*  86: 86 */    return super.toString() + " [DocumentType: " + asXML() + "]";
/*  87:    */  }
/*  88:    */  
/*  89:    */  public String asXML() {
/*  90: 90 */    StringBuffer buffer = new StringBuffer("<!DOCTYPE ");
/*  91: 91 */    buffer.append(getElementName());
/*  92:    */    
/*  93: 93 */    boolean hasPublicID = false;
/*  94: 94 */    String publicID = getPublicID();
/*  95:    */    
/*  96: 96 */    if ((publicID != null) && (publicID.length() > 0)) {
/*  97: 97 */      buffer.append(" PUBLIC \"");
/*  98: 98 */      buffer.append(publicID);
/*  99: 99 */      buffer.append("\"");
/* 100:100 */      hasPublicID = true;
/* 101:    */    }
/* 102:    */    
/* 103:103 */    String systemID = getSystemID();
/* 104:    */    
/* 105:105 */    if ((systemID != null) && (systemID.length() > 0)) {
/* 106:106 */      if (!hasPublicID) {
/* 107:107 */        buffer.append(" SYSTEM");
/* 108:    */      }
/* 109:    */      
/* 110:110 */      buffer.append(" \"");
/* 111:111 */      buffer.append(systemID);
/* 112:112 */      buffer.append("\"");
/* 113:    */    }
/* 114:    */    
/* 115:115 */    buffer.append(">");
/* 116:    */    
/* 117:117 */    return buffer.toString();
/* 118:    */  }
/* 119:    */  
/* 120:    */  public void write(Writer writer) throws IOException {
/* 121:121 */    writer.write("<!DOCTYPE ");
/* 122:122 */    writer.write(getElementName());
/* 123:    */    
/* 124:124 */    boolean hasPublicID = false;
/* 125:125 */    String publicID = getPublicID();
/* 126:    */    
/* 127:127 */    if ((publicID != null) && (publicID.length() > 0)) {
/* 128:128 */      writer.write(" PUBLIC \"");
/* 129:129 */      writer.write(publicID);
/* 130:130 */      writer.write("\"");
/* 131:131 */      hasPublicID = true;
/* 132:    */    }
/* 133:    */    
/* 134:134 */    String systemID = getSystemID();
/* 135:    */    
/* 136:136 */    if ((systemID != null) && (systemID.length() > 0)) {
/* 137:137 */      if (!hasPublicID) {
/* 138:138 */        writer.write(" SYSTEM");
/* 139:    */      }
/* 140:    */      
/* 141:141 */      writer.write(" \"");
/* 142:142 */      writer.write(systemID);
/* 143:143 */      writer.write("\"");
/* 144:    */    }
/* 145:    */    
/* 146:146 */    List list = getInternalDeclarations();
/* 147:    */    
/* 148:148 */    if ((list != null) && (list.size() > 0)) {
/* 149:149 */      writer.write(" [");
/* 150:    */      
/* 151:151 */      for (Iterator iter = list.iterator(); iter.hasNext();) {
/* 152:152 */        Object decl = iter.next();
/* 153:153 */        writer.write("\n  ");
/* 154:154 */        writer.write(decl.toString());
/* 155:    */      }
/* 156:    */      
/* 157:157 */      writer.write("\n]");
/* 158:    */    }
/* 159:    */    
/* 160:160 */    writer.write(">");
/* 161:    */  }
/* 162:    */  
/* 163:    */  public void accept(Visitor visitor) {
/* 164:164 */    visitor.visit(this);
/* 165:    */  }
/* 166:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractDocumentType
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */