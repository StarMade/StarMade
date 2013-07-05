/*     */ package org.dom4j.dom;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import org.dom4j.Branch;
/*     */ import org.dom4j.CharacterData;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.Text;
/*     */ 
/*     */ public class DOMNodeHelper
/*     */ {
/*  33 */   public static final NodeList EMPTY_NODE_LIST = new EmptyNodeList();
/*     */ 
/*     */   public static boolean supports(org.dom4j.Node node, String feature, String version)
/*     */   {
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getNamespaceURI(org.dom4j.Node node) {
/*  45 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getPrefix(org.dom4j.Node node) {
/*  49 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getLocalName(org.dom4j.Node node) {
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */   public static void setPrefix(org.dom4j.Node node, String prefix) throws DOMException {
/*  57 */     notSupported();
/*     */   }
/*     */ 
/*     */   public static String getNodeValue(org.dom4j.Node node) throws DOMException {
/*  61 */     return node.getText();
/*     */   }
/*     */ 
/*     */   public static void setNodeValue(org.dom4j.Node node, String nodeValue) throws DOMException
/*     */   {
/*  66 */     node.setText(nodeValue);
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Node getParentNode(org.dom4j.Node node) {
/*  70 */     return asDOMNode(node.getParent());
/*     */   }
/*     */ 
/*     */   public static NodeList getChildNodes(org.dom4j.Node node) {
/*  74 */     return EMPTY_NODE_LIST;
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Node getFirstChild(org.dom4j.Node node) {
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Node getLastChild(org.dom4j.Node node) {
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Node getPreviousSibling(org.dom4j.Node node) {
/*  86 */     org.dom4j.Element parent = node.getParent();
/*     */ 
/*  88 */     if (parent != null) {
/*  89 */       int index = parent.indexOf(node);
/*     */ 
/*  91 */       if (index > 0) {
/*  92 */         org.dom4j.Node previous = parent.node(index - 1);
/*     */ 
/*  94 */         return asDOMNode(previous);
/*     */       }
/*     */     }
/*     */ 
/*  98 */     return null;
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Node getNextSibling(org.dom4j.Node node) {
/* 102 */     org.dom4j.Element parent = node.getParent();
/*     */ 
/* 104 */     if (parent != null) {
/* 105 */       int index = parent.indexOf(node);
/*     */ 
/* 107 */       if (index >= 0) {
/* 108 */         index++; if (index < parent.nodeCount()) {
/* 109 */           org.dom4j.Node next = parent.node(index);
/*     */ 
/* 111 */           return asDOMNode(next);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */   public static NamedNodeMap getAttributes(org.dom4j.Node node) {
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Document getOwnerDocument(org.dom4j.Node node) {
/* 124 */     return asDOMDocument(node.getDocument());
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Node insertBefore(org.dom4j.Node node, org.w3c.dom.Node newChild, org.w3c.dom.Node refChild)
/*     */     throws DOMException
/*     */   {
/* 130 */     if ((node instanceof Branch)) {
/* 131 */       Branch branch = (Branch)node;
/* 132 */       List list = branch.content();
/* 133 */       int index = list.indexOf(refChild);
/*     */ 
/* 135 */       if (index < 0)
/* 136 */         branch.add((org.dom4j.Node)newChild);
/*     */       else {
/* 138 */         list.add(index, newChild);
/*     */       }
/*     */ 
/* 141 */       return newChild;
/*     */     }
/* 143 */     throw new DOMException((short)3, "Children not allowed for this node: " + node);
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Node replaceChild(org.dom4j.Node node, org.w3c.dom.Node newChild, org.w3c.dom.Node oldChild)
/*     */     throws DOMException
/*     */   {
/* 151 */     if ((node instanceof Branch)) {
/* 152 */       Branch branch = (Branch)node;
/* 153 */       List list = branch.content();
/* 154 */       int index = list.indexOf(oldChild);
/*     */ 
/* 156 */       if (index < 0) {
/* 157 */         throw new DOMException((short)8, "Tried to replace a non existing child for node: " + node);
/*     */       }
/*     */ 
/* 162 */       list.set(index, newChild);
/*     */ 
/* 164 */       return oldChild;
/*     */     }
/* 166 */     throw new DOMException((short)3, "Children not allowed for this node: " + node);
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Node removeChild(org.dom4j.Node node, org.w3c.dom.Node oldChild)
/*     */     throws DOMException
/*     */   {
/* 173 */     if ((node instanceof Branch)) {
/* 174 */       Branch branch = (Branch)node;
/* 175 */       branch.remove((org.dom4j.Node)oldChild);
/*     */ 
/* 177 */       return oldChild;
/*     */     }
/*     */ 
/* 180 */     throw new DOMException((short)3, "Children not allowed for this node: " + node);
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Node appendChild(org.dom4j.Node node, org.w3c.dom.Node newChild)
/*     */     throws DOMException
/*     */   {
/* 186 */     if ((node instanceof Branch)) {
/* 187 */       Branch branch = (Branch)node;
/* 188 */       org.w3c.dom.Node previousParent = newChild.getParentNode();
/*     */ 
/* 190 */       if (previousParent != null) {
/* 191 */         previousParent.removeChild(newChild);
/*     */       }
/*     */ 
/* 194 */       branch.add((org.dom4j.Node)newChild);
/*     */ 
/* 196 */       return newChild;
/*     */     }
/*     */ 
/* 199 */     throw new DOMException((short)3, "Children not allowed for this node: " + node);
/*     */   }
/*     */ 
/*     */   public static boolean hasChildNodes(org.dom4j.Node node)
/*     */   {
/* 204 */     return false;
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Node cloneNode(org.dom4j.Node node, boolean deep) {
/* 208 */     return asDOMNode((org.dom4j.Node)node.clone());
/*     */   }
/*     */ 
/*     */   public static void normalize(org.dom4j.Node node) {
/* 212 */     notSupported();
/*     */   }
/*     */ 
/*     */   public static boolean isSupported(org.dom4j.Node n, String feature, String version) {
/* 216 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean hasAttributes(org.dom4j.Node node) {
/* 220 */     if ((node != null) && ((node instanceof org.dom4j.Element))) {
/* 221 */       return ((org.dom4j.Element)node).attributeCount() > 0;
/*     */     }
/* 223 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getData(CharacterData charData)
/*     */     throws DOMException
/*     */   {
/* 230 */     return charData.getText();
/*     */   }
/*     */ 
/*     */   public static void setData(CharacterData charData, String data) throws DOMException
/*     */   {
/* 235 */     charData.setText(data);
/*     */   }
/*     */ 
/*     */   public static int getLength(CharacterData charData) {
/* 239 */     String text = charData.getText();
/*     */ 
/* 241 */     return text != null ? text.length() : 0;
/*     */   }
/*     */ 
/*     */   public static String substringData(CharacterData charData, int offset, int count) throws DOMException
/*     */   {
/* 246 */     if (count < 0) {
/* 247 */       throw new DOMException((short)1, "Illegal value for count: " + count);
/*     */     }
/*     */ 
/* 251 */     String text = charData.getText();
/* 252 */     int length = text != null ? text.length() : 0;
/*     */ 
/* 254 */     if ((offset < 0) || (offset >= length)) {
/* 255 */       throw new DOMException((short)1, "No text at offset: " + offset);
/*     */     }
/*     */ 
/* 259 */     if (offset + count > length) {
/* 260 */       return text.substring(offset);
/*     */     }
/*     */ 
/* 263 */     return text.substring(offset, offset + count);
/*     */   }
/*     */ 
/*     */   public static void appendData(CharacterData charData, String arg) throws DOMException
/*     */   {
/* 268 */     if (charData.isReadOnly()) {
/* 269 */       throw new DOMException((short)7, "CharacterData node is read only: " + charData);
/*     */     }
/*     */ 
/* 272 */     String text = charData.getText();
/*     */ 
/* 274 */     if (text == null)
/* 275 */       charData.setText(text);
/*     */     else
/* 277 */       charData.setText(text + arg);
/*     */   }
/*     */ 
/*     */   public static void insertData(CharacterData data, int offset, String arg)
/*     */     throws DOMException
/*     */   {
/* 284 */     if (data.isReadOnly()) {
/* 285 */       throw new DOMException((short)7, "CharacterData node is read only: " + data);
/*     */     }
/*     */ 
/* 288 */     String text = data.getText();
/*     */ 
/* 290 */     if (text == null) {
/* 291 */       data.setText(arg);
/*     */     } else {
/* 293 */       int length = text.length();
/*     */ 
/* 295 */       if ((offset < 0) || (offset > length)) {
/* 296 */         throw new DOMException((short)1, "No text at offset: " + offset);
/*     */       }
/*     */ 
/* 299 */       StringBuffer buffer = new StringBuffer(text);
/* 300 */       buffer.insert(offset, arg);
/* 301 */       data.setText(buffer.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void deleteData(CharacterData charData, int offset, int count)
/*     */     throws DOMException
/*     */   {
/* 309 */     if (charData.isReadOnly()) {
/* 310 */       throw new DOMException((short)7, "CharacterData node is read only: " + charData);
/*     */     }
/*     */ 
/* 313 */     if (count < 0) {
/* 314 */       throw new DOMException((short)1, "Illegal value for count: " + count);
/*     */     }
/*     */ 
/* 318 */     String text = charData.getText();
/*     */ 
/* 320 */     if (text != null) {
/* 321 */       int length = text.length();
/*     */ 
/* 323 */       if ((offset < 0) || (offset >= length)) {
/* 324 */         throw new DOMException((short)1, "No text at offset: " + offset);
/*     */       }
/*     */ 
/* 327 */       StringBuffer buffer = new StringBuffer(text);
/* 328 */       buffer.delete(offset, offset + count);
/* 329 */       charData.setText(buffer.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void replaceData(CharacterData charData, int offset, int count, String arg)
/*     */     throws DOMException
/*     */   {
/* 337 */     if (charData.isReadOnly()) {
/* 338 */       throw new DOMException((short)7, "CharacterData node is read only: " + charData);
/*     */     }
/*     */ 
/* 341 */     if (count < 0) {
/* 342 */       throw new DOMException((short)1, "Illegal value for count: " + count);
/*     */     }
/*     */ 
/* 346 */     String text = charData.getText();
/*     */ 
/* 348 */     if (text != null) {
/* 349 */       int length = text.length();
/*     */ 
/* 351 */       if ((offset < 0) || (offset >= length)) {
/* 352 */         throw new DOMException((short)1, "No text at offset: " + offset);
/*     */       }
/*     */ 
/* 355 */       StringBuffer buffer = new StringBuffer(text);
/* 356 */       buffer.replace(offset, offset + count, arg);
/* 357 */       charData.setText(buffer.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void appendElementsByTagName(List list, Branch parent, String name)
/*     */   {
/* 367 */     boolean isStar = "*".equals(name);
/*     */ 
/* 369 */     int i = 0; for (int size = parent.nodeCount(); i < size; i++) {
/* 370 */       org.dom4j.Node node = parent.node(i);
/*     */ 
/* 372 */       if ((node instanceof org.dom4j.Element)) {
/* 373 */         org.dom4j.Element element = (org.dom4j.Element)node;
/*     */ 
/* 375 */         if ((isStar) || (name.equals(element.getName()))) {
/* 376 */           list.add(element);
/*     */         }
/*     */ 
/* 379 */         appendElementsByTagName(list, element, name);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void appendElementsByTagNameNS(List list, Branch parent, String namespace, String localName)
/*     */   {
/* 386 */     boolean isStarNS = "*".equals(namespace);
/* 387 */     boolean isStar = "*".equals(localName);
/*     */ 
/* 389 */     int i = 0; for (int size = parent.nodeCount(); i < size; i++) {
/* 390 */       org.dom4j.Node node = parent.node(i);
/*     */ 
/* 392 */       if ((node instanceof org.dom4j.Element)) {
/* 393 */         org.dom4j.Element element = (org.dom4j.Element)node;
/*     */ 
/* 395 */         if (((isStarNS) || (((namespace != null) && (namespace.length() != 0)) || ((element.getNamespaceURI() == null) || (element.getNamespaceURI().length() == 0) || ((namespace != null) && (namespace.equals(element.getNamespaceURI())))))) && ((isStar) || (localName.equals(element.getName()))))
/*     */         {
/* 403 */           list.add(element);
/*     */         }
/*     */ 
/* 406 */         appendElementsByTagNameNS(list, element, namespace, localName);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static NodeList createNodeList(List list)
/*     */   {
/* 414 */     return new NodeList() { private final List val$list;
/*     */ 
/* 416 */       public org.w3c.dom.Node item(int index) { if (index >= getLength())
/*     */         {
/* 422 */           return null;
/*     */         }
/* 424 */         return DOMNodeHelper.asDOMNode((org.dom4j.Node)this.val$list.get(index));
/*     */       }
/*     */ 
/*     */       public int getLength()
/*     */       {
/* 429 */         return this.val$list.size();
/*     */       } } ;
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Node asDOMNode(org.dom4j.Node node)
/*     */   {
/* 435 */     if (node == null) {
/* 436 */       return null;
/*     */     }
/*     */ 
/* 439 */     if ((node instanceof org.w3c.dom.Node)) {
/* 440 */       return (org.w3c.dom.Node)node;
/*     */     }
/*     */ 
/* 443 */     System.out.println("Cannot convert: " + node + " into a W3C DOM Node");
/*     */ 
/* 445 */     notSupported();
/*     */ 
/* 447 */     return null;
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Document asDOMDocument(org.dom4j.Document document)
/*     */   {
/* 452 */     if (document == null) {
/* 453 */       return null;
/*     */     }
/*     */ 
/* 456 */     if ((document instanceof org.w3c.dom.Document)) {
/* 457 */       return (org.w3c.dom.Document)document;
/*     */     }
/*     */ 
/* 460 */     notSupported();
/*     */ 
/* 462 */     return null;
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.DocumentType asDOMDocumentType(org.dom4j.DocumentType dt)
/*     */   {
/* 467 */     if (dt == null) {
/* 468 */       return null;
/*     */     }
/*     */ 
/* 471 */     if ((dt instanceof org.w3c.dom.DocumentType)) {
/* 472 */       return (org.w3c.dom.DocumentType)dt;
/*     */     }
/*     */ 
/* 475 */     notSupported();
/*     */ 
/* 477 */     return null;
/*     */   }
/*     */ 
/*     */   public static Text asDOMText(CharacterData text)
/*     */   {
/* 482 */     if (text == null) {
/* 483 */       return null;
/*     */     }
/*     */ 
/* 486 */     if ((text instanceof Text)) {
/* 487 */       return (Text)text;
/*     */     }
/*     */ 
/* 490 */     notSupported();
/*     */ 
/* 492 */     return null;
/*     */   }
/*     */ 
/*     */   public static org.w3c.dom.Element asDOMElement(org.dom4j.Node element)
/*     */   {
/* 497 */     if (element == null) {
/* 498 */       return null;
/*     */     }
/*     */ 
/* 501 */     if ((element instanceof org.w3c.dom.Element)) {
/* 502 */       return (org.w3c.dom.Element)element;
/*     */     }
/*     */ 
/* 505 */     notSupported();
/*     */ 
/* 507 */     return null;
/*     */   }
/*     */ 
/*     */   public static Attr asDOMAttr(org.dom4j.Node attribute)
/*     */   {
/* 512 */     if (attribute == null) {
/* 513 */       return null;
/*     */     }
/*     */ 
/* 516 */     if ((attribute instanceof Attr)) {
/* 517 */       return (Attr)attribute;
/*     */     }
/*     */ 
/* 520 */     notSupported();
/*     */ 
/* 522 */     return null;
/*     */   }
/*     */ 
/*     */   public static void notSupported()
/*     */   {
/* 533 */     throw new DOMException((short)9, "Not supported yet");
/*     */   }
/*     */ 
/*     */   public static class EmptyNodeList implements NodeList
/*     */   {
/*     */     public org.w3c.dom.Node item(int index) {
/* 539 */       return null;
/*     */     }
/*     */ 
/*     */     public int getLength() {
/* 543 */       return 0;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.dom.DOMNodeHelper
 * JD-Core Version:    0.6.2
 */