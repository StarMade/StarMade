/*   1:    */package org.newdawn.slick.util.xml;
/*   2:    */
/*   3:    */import org.w3c.dom.Element;
/*   4:    */import org.w3c.dom.NamedNodeMap;
/*   5:    */import org.w3c.dom.Node;
/*   6:    */import org.w3c.dom.NodeList;
/*   7:    */import org.w3c.dom.Text;
/*   8:    */
/*  21:    */public class XMLElement
/*  22:    */{
/*  23:    */  private Element dom;
/*  24:    */  private XMLElementList children;
/*  25:    */  private String name;
/*  26:    */  
/*  27:    */  XMLElement(Element xmlElement)
/*  28:    */  {
/*  29: 29 */    this.dom = xmlElement;
/*  30: 30 */    this.name = this.dom.getTagName();
/*  31:    */  }
/*  32:    */  
/*  37:    */  public String[] getAttributeNames()
/*  38:    */  {
/*  39: 39 */    NamedNodeMap map = this.dom.getAttributes();
/*  40: 40 */    String[] names = new String[map.getLength()];
/*  41:    */    
/*  42: 42 */    for (int i = 0; i < names.length; i++) {
/*  43: 43 */      names[i] = map.item(i).getNodeName();
/*  44:    */    }
/*  45:    */    
/*  46: 46 */    return names;
/*  47:    */  }
/*  48:    */  
/*  53:    */  public String getName()
/*  54:    */  {
/*  55: 55 */    return this.name;
/*  56:    */  }
/*  57:    */  
/*  63:    */  public String getAttribute(String name)
/*  64:    */  {
/*  65: 65 */    return this.dom.getAttribute(name);
/*  66:    */  }
/*  67:    */  
/*  74:    */  public String getAttribute(String name, String def)
/*  75:    */  {
/*  76: 76 */    String value = this.dom.getAttribute(name);
/*  77: 77 */    if ((value == null) || (value.length() == 0)) {
/*  78: 78 */      return def;
/*  79:    */    }
/*  80:    */    
/*  81: 81 */    return value;
/*  82:    */  }
/*  83:    */  
/*  88:    */  public int getIntAttribute(String name)
/*  89:    */    throws SlickXMLException
/*  90:    */  {
/*  91:    */    try
/*  92:    */    {
/*  93: 93 */      return Integer.parseInt(getAttribute(name));
/*  94:    */    } catch (NumberFormatException e) {
/*  95: 95 */      throw new SlickXMLException("Value read: '" + getAttribute(name) + "' is not an integer", e);
/*  96:    */    }
/*  97:    */  }
/*  98:    */  
/* 104:    */  public int getIntAttribute(String name, int def)
/* 105:    */    throws SlickXMLException
/* 106:    */  {
/* 107:    */    try
/* 108:    */    {
/* 109:109 */      return Integer.parseInt(getAttribute(name, "" + def));
/* 110:    */    } catch (NumberFormatException e) {
/* 111:111 */      throw new SlickXMLException("Value read: '" + getAttribute(name, new StringBuilder().append("").append(def).toString()) + "' is not an integer", e);
/* 112:    */    }
/* 113:    */  }
/* 114:    */  
/* 119:    */  public double getDoubleAttribute(String name)
/* 120:    */    throws SlickXMLException
/* 121:    */  {
/* 122:    */    try
/* 123:    */    {
/* 124:124 */      return Double.parseDouble(getAttribute(name));
/* 125:    */    } catch (NumberFormatException e) {
/* 126:126 */      throw new SlickXMLException("Value read: '" + getAttribute(name) + "' is not a double", e);
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 135:    */  public double getDoubleAttribute(String name, double def)
/* 136:    */    throws SlickXMLException
/* 137:    */  {
/* 138:    */    try
/* 139:    */    {
/* 140:140 */      return Double.parseDouble(getAttribute(name, "" + def));
/* 141:    */    } catch (NumberFormatException e) {
/* 142:142 */      throw new SlickXMLException("Value read: '" + getAttribute(name, new StringBuilder().append("").append(def).toString()) + "' is not a double", e);
/* 143:    */    }
/* 144:    */  }
/* 145:    */  
/* 151:    */  public boolean getBooleanAttribute(String name)
/* 152:    */    throws SlickXMLException
/* 153:    */  {
/* 154:154 */    String value = getAttribute(name);
/* 155:155 */    if (value.equalsIgnoreCase("true")) {
/* 156:156 */      return true;
/* 157:    */    }
/* 158:158 */    if (value.equalsIgnoreCase("false")) {
/* 159:159 */      return false;
/* 160:    */    }
/* 161:    */    
/* 162:162 */    throw new SlickXMLException("Value read: '" + getAttribute(name) + "' is not a boolean");
/* 163:    */  }
/* 164:    */  
/* 172:    */  public boolean getBooleanAttribute(String name, boolean def)
/* 173:    */    throws SlickXMLException
/* 174:    */  {
/* 175:175 */    String value = getAttribute(name, "" + def);
/* 176:176 */    if (value.equalsIgnoreCase("true")) {
/* 177:177 */      return true;
/* 178:    */    }
/* 179:179 */    if (value.equalsIgnoreCase("false")) {
/* 180:180 */      return false;
/* 181:    */    }
/* 182:    */    
/* 183:183 */    throw new SlickXMLException("Value read: '" + getAttribute(name, new StringBuilder().append("").append(def).toString()) + "' is not a boolean");
/* 184:    */  }
/* 185:    */  
/* 190:    */  public String getContent()
/* 191:    */  {
/* 192:192 */    String content = "";
/* 193:    */    
/* 194:194 */    NodeList list = this.dom.getChildNodes();
/* 195:195 */    for (int i = 0; i < list.getLength(); i++) {
/* 196:196 */      if ((list.item(i) instanceof Text)) {
/* 197:197 */        content = content + list.item(i).getNodeValue();
/* 198:    */      }
/* 199:    */    }
/* 200:    */    
/* 201:201 */    return content;
/* 202:    */  }
/* 203:    */  
/* 208:    */  public XMLElementList getChildren()
/* 209:    */  {
/* 210:210 */    if (this.children != null) {
/* 211:211 */      return this.children;
/* 212:    */    }
/* 213:    */    
/* 214:214 */    NodeList list = this.dom.getChildNodes();
/* 215:215 */    this.children = new XMLElementList();
/* 216:    */    
/* 217:217 */    for (int i = 0; i < list.getLength(); i++) {
/* 218:218 */      if ((list.item(i) instanceof Element)) {
/* 219:219 */        this.children.add(new XMLElement((Element)list.item(i)));
/* 220:    */      }
/* 221:    */    }
/* 222:    */    
/* 223:223 */    return this.children;
/* 224:    */  }
/* 225:    */  
/* 231:    */  public XMLElementList getChildrenByName(String name)
/* 232:    */  {
/* 233:233 */    XMLElementList selected = new XMLElementList();
/* 234:234 */    XMLElementList children = getChildren();
/* 235:    */    
/* 236:236 */    for (int i = 0; i < children.size(); i++) {
/* 237:237 */      if (children.get(i).getName().equals(name)) {
/* 238:238 */        selected.add(children.get(i));
/* 239:    */      }
/* 240:    */    }
/* 241:    */    
/* 242:242 */    return selected;
/* 243:    */  }
/* 244:    */  
/* 247:    */  public String toString()
/* 248:    */  {
/* 249:249 */    String value = "[XML " + getName();
/* 250:250 */    String[] attrs = getAttributeNames();
/* 251:    */    
/* 252:252 */    for (int i = 0; i < attrs.length; i++) {
/* 253:253 */      value = value + " " + attrs[i] + "=" + getAttribute(attrs[i]);
/* 254:    */    }
/* 255:    */    
/* 256:256 */    value = value + "]";
/* 257:    */    
/* 258:258 */    return value;
/* 259:    */  }
/* 260:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.xml.XMLElement
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */