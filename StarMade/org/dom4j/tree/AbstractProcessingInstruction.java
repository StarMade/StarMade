/*   1:    */package org.dom4j.tree;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.Writer;
/*   5:    */import java.util.HashMap;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.Map;
/*   8:    */import java.util.Map.Entry;
/*   9:    */import java.util.Set;
/*  10:    */import java.util.StringTokenizer;
/*  11:    */import org.dom4j.Element;
/*  12:    */import org.dom4j.ProcessingInstruction;
/*  13:    */import org.dom4j.Visitor;
/*  14:    */
/*  30:    */public abstract class AbstractProcessingInstruction
/*  31:    */  extends AbstractNode
/*  32:    */  implements ProcessingInstruction
/*  33:    */{
/*  34:    */  public short getNodeType()
/*  35:    */  {
/*  36: 36 */    return 7;
/*  37:    */  }
/*  38:    */  
/*  39:    */  public String getPath(Element context) {
/*  40: 40 */    Element parent = getParent();
/*  41:    */    
/*  42: 42 */    return (parent != null) && (parent != context) ? parent.getPath(context) + "/processing-instruction()" : "processing-instruction()";
/*  43:    */  }
/*  44:    */  
/*  46:    */  public String getUniquePath(Element context)
/*  47:    */  {
/*  48: 48 */    Element parent = getParent();
/*  49:    */    
/*  50: 50 */    return (parent != null) && (parent != context) ? parent.getUniquePath(context) + "/processing-instruction()" : "processing-instruction()";
/*  51:    */  }
/*  52:    */  
/*  54:    */  public String toString()
/*  55:    */  {
/*  56: 56 */    return super.toString() + " [ProcessingInstruction: &" + getName() + ";]";
/*  57:    */  }
/*  58:    */  
/*  59:    */  public String asXML()
/*  60:    */  {
/*  61: 61 */    return "<?" + getName() + " " + getText() + "?>";
/*  62:    */  }
/*  63:    */  
/*  64:    */  public void write(Writer writer) throws IOException {
/*  65: 65 */    writer.write("<?");
/*  66: 66 */    writer.write(getName());
/*  67: 67 */    writer.write(" ");
/*  68: 68 */    writer.write(getText());
/*  69: 69 */    writer.write("?>");
/*  70:    */  }
/*  71:    */  
/*  72:    */  public void accept(Visitor visitor) {
/*  73: 73 */    visitor.visit(this);
/*  74:    */  }
/*  75:    */  
/*  76:    */  public void setValue(String name, String value) {
/*  77: 77 */    throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
/*  78:    */  }
/*  79:    */  
/*  80:    */  public void setValues(Map data)
/*  81:    */  {
/*  82: 82 */    throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
/*  83:    */  }
/*  84:    */  
/*  85:    */  public String getName()
/*  86:    */  {
/*  87: 87 */    return getTarget();
/*  88:    */  }
/*  89:    */  
/*  90:    */  public void setName(String name) {
/*  91: 91 */    setTarget(name);
/*  92:    */  }
/*  93:    */  
/*  94:    */  public boolean removeValue(String name) {
/*  95: 95 */    return false;
/*  96:    */  }
/*  97:    */  
/* 109:    */  protected String toString(Map values)
/* 110:    */  {
/* 111:111 */    StringBuffer buffer = new StringBuffer();
/* 112:    */    
/* 113:113 */    for (Iterator iter = values.entrySet().iterator(); iter.hasNext();) {
/* 114:114 */      Map.Entry entry = (Map.Entry)iter.next();
/* 115:115 */      String name = (String)entry.getKey();
/* 116:116 */      String value = (String)entry.getValue();
/* 117:    */      
/* 118:118 */      buffer.append(name);
/* 119:119 */      buffer.append("=\"");
/* 120:120 */      buffer.append(value);
/* 121:121 */      buffer.append("\" ");
/* 122:    */    }
/* 123:    */    
/* 125:125 */    buffer.setLength(buffer.length() - 1);
/* 126:    */    
/* 127:127 */    return buffer.toString();
/* 128:    */  }
/* 129:    */  
/* 139:    */  protected Map parseValues(String text)
/* 140:    */  {
/* 141:141 */    Map data = new HashMap();
/* 142:    */    
/* 143:143 */    StringTokenizer s = new StringTokenizer(text, " ='\"", true);
/* 144:    */    
/* 145:145 */    while (s.hasMoreTokens()) {
/* 146:146 */      String name = getName(s);
/* 147:    */      
/* 148:148 */      if (s.hasMoreTokens()) {
/* 149:149 */        String value = getValue(s);
/* 150:150 */        data.put(name, value);
/* 151:    */      }
/* 152:    */    }
/* 153:    */    
/* 154:154 */    return data;
/* 155:    */  }
/* 156:    */  
/* 157:    */  private String getName(StringTokenizer tokenizer) {
/* 158:158 */    String token = tokenizer.nextToken();
/* 159:159 */    StringBuffer name = new StringBuffer(token);
/* 160:    */    
/* 161:161 */    while (tokenizer.hasMoreTokens()) {
/* 162:162 */      token = tokenizer.nextToken();
/* 163:    */      
/* 164:164 */      if (token.equals("=")) break;
/* 165:165 */      name.append(token);
/* 166:    */    }
/* 167:    */    
/* 171:171 */    return name.toString().trim();
/* 172:    */  }
/* 173:    */  
/* 174:    */  private String getValue(StringTokenizer tokenizer) {
/* 175:175 */    String token = tokenizer.nextToken();
/* 176:176 */    StringBuffer value = new StringBuffer();
/* 177:    */    
/* 180:180 */    while ((tokenizer.hasMoreTokens()) && (!token.equals("'")) && (!token.equals("\""))) {
/* 181:181 */      token = tokenizer.nextToken();
/* 182:    */    }
/* 183:    */    
/* 184:184 */    String quote = token;
/* 185:    */    
/* 186:186 */    while (tokenizer.hasMoreTokens()) {
/* 187:187 */      token = tokenizer.nextToken();
/* 188:    */      
/* 189:189 */      if (quote.equals(token)) break;
/* 190:190 */      value.append(token);
/* 191:    */    }
/* 192:    */    
/* 196:196 */    return value.toString();
/* 197:    */  }
/* 198:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.AbstractProcessingInstruction
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */