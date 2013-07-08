/*   1:    */package org.apache.ws.commons.serialize;
/*   2:    */
/*   3:    */import org.w3c.dom.NamedNodeMap;
/*   4:    */import org.w3c.dom.Node;
/*   5:    */import org.xml.sax.ContentHandler;
/*   6:    */import org.xml.sax.SAXException;
/*   7:    */import org.xml.sax.ext.LexicalHandler;
/*   8:    */import org.xml.sax.helpers.AttributesImpl;
/*   9:    */
/*  28:    */public class DOMSerializer
/*  29:    */{
/*  30:    */  private boolean namespaceDeclarationAttribute;
/*  31:    */  private boolean parentsNamespaceDeclarationDisabled;
/*  32: 32 */  private boolean startingDocument = true;
/*  33:    */  
/*  38:    */  public void setNamespaceDeclarationAttribute(boolean pXmlDeclarationAttribute)
/*  39:    */  {
/*  40: 40 */    this.namespaceDeclarationAttribute = pXmlDeclarationAttribute;
/*  41:    */  }
/*  42:    */  
/*  47:    */  public boolean isNamespaceDeclarationAttribute()
/*  48:    */  {
/*  49: 49 */    return this.namespaceDeclarationAttribute;
/*  50:    */  }
/*  51:    */  
/*  59:    */  public void setParentsNamespaceDeclarationDisabled(boolean pParentsXmlDeclarationDisabled)
/*  60:    */  {
/*  61: 61 */    this.parentsNamespaceDeclarationDisabled = pParentsXmlDeclarationDisabled;
/*  62:    */  }
/*  63:    */  
/*  71:    */  public boolean isParentsNamespaceDeclarationDisabled()
/*  72:    */  {
/*  73: 73 */    return this.parentsNamespaceDeclarationDisabled;
/*  74:    */  }
/*  75:    */  
/*  82:    */  public boolean isStartingDocument()
/*  83:    */  {
/*  84: 84 */    return this.startingDocument;
/*  85:    */  }
/*  86:    */  
/*  94:    */  public void setStartingDocument(boolean pStartingDocument)
/*  95:    */  {
/*  96: 96 */    this.startingDocument = pStartingDocument;
/*  97:    */  }
/*  98:    */  
/* 103:    */  protected void doSerializeChilds(Node pNode, ContentHandler pHandler)
/* 104:    */    throws SAXException
/* 105:    */  {
/* 106:106 */    for (Node child = pNode.getFirstChild(); child != null; 
/* 107:107 */        child = child.getNextSibling()) {
/* 108:108 */      doSerialize(child, pHandler);
/* 109:    */    }
/* 110:    */  }
/* 111:    */  
/* 119:    */  private void parentsStartPrefixMappingEvents(Node pNode, ContentHandler pHandler)
/* 120:    */    throws SAXException
/* 121:    */  {
/* 122:122 */    if (pNode != null) {
/* 123:123 */      parentsStartPrefixMappingEvents(pNode.getParentNode(), pHandler);
/* 124:124 */      if (pNode.getNodeType() == 1) {
/* 125:125 */        startPrefixMappingEvents(pNode, pHandler);
/* 126:    */      }
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 137:    */  private void parentsEndPrefixMappingEvents(Node pNode, ContentHandler pHandler)
/* 138:    */    throws SAXException
/* 139:    */  {
/* 140:140 */    if (pNode != null) {
/* 141:141 */      if (pNode.getNodeType() == 1) {
/* 142:142 */        endPrefixMappingEvents(pNode, pHandler);
/* 143:    */      }
/* 144:144 */      parentsEndPrefixMappingEvents(pNode.getParentNode(), pHandler);
/* 145:    */    }
/* 146:    */  }
/* 147:    */  
/* 152:    */  private void startPrefixMappingEvents(Node pNode, ContentHandler pHandler)
/* 153:    */    throws SAXException
/* 154:    */  {
/* 155:155 */    NamedNodeMap nnm = pNode.getAttributes();
/* 156:156 */    if (nnm != null) {
/* 157:157 */      for (int i = 0; i < nnm.getLength(); i++) {
/* 158:158 */        Node attr = nnm.item(i);
/* 159:159 */        if ("http://www.w3.org/2000/xmlns/".equals(attr.getNamespaceURI())) {
/* 160:    */          String prefix;
/* 161:161 */          if ("xmlns".equals(attr.getPrefix())) {
/* 162:162 */            prefix = attr.getLocalName(); } else { String prefix;
/* 163:163 */            if ("xmlns".equals(attr.getNodeName())) {
/* 164:164 */              prefix = "";
/* 165:    */            } else
/* 166:166 */              throw new IllegalStateException("Unable to parse namespace declaration: " + attr.getNodeName()); }
/* 167:    */          String prefix;
/* 168:168 */          String uri = attr.getNodeValue();
/* 169:169 */          if (uri == null) {
/* 170:170 */            uri = "";
/* 171:    */          }
/* 172:172 */          pHandler.startPrefixMapping(prefix, uri);
/* 173:    */        }
/* 174:    */      }
/* 175:    */    }
/* 176:    */  }
/* 177:    */  
/* 182:    */  private void endPrefixMappingEvents(Node pNode, ContentHandler pHandler)
/* 183:    */    throws SAXException
/* 184:    */  {
/* 185:185 */    NamedNodeMap nnm = pNode.getAttributes();
/* 186:186 */    if (nnm != null) {
/* 187:187 */      for (int i = nnm.getLength() - 1; i >= 0; i--) {
/* 188:188 */        Node attr = nnm.item(i);
/* 189:189 */        if ("http://www.w3.org/2000/xmlns/".equals(attr.getNamespaceURI())) {
/* 190:190 */          String prefix = attr.getLocalName();
/* 191:191 */          pHandler.endPrefixMapping(prefix);
/* 192:    */        }
/* 193:    */      }
/* 194:    */    }
/* 195:    */  }
/* 196:    */  
/* 197:    */  private void characters(ContentHandler pHandler, String pValue, boolean pCdata) throws SAXException {
/* 198:    */    LexicalHandler lh;
/* 199:    */    LexicalHandler lh;
/* 200:200 */    if (pCdata) {
/* 201:201 */      lh = (pHandler instanceof LexicalHandler) ? (LexicalHandler)pHandler : null;
/* 202:    */    } else {
/* 203:203 */      lh = null;
/* 204:    */    }
/* 205:205 */    if (lh != null) {
/* 206:206 */      lh.startCDATA();
/* 207:    */    }
/* 208:208 */    pHandler.characters(pValue.toCharArray(), 0, pValue.length());
/* 209:209 */    if (lh != null) {
/* 210:210 */      lh.endCDATA();
/* 211:    */    }
/* 212:    */  }
/* 213:    */  
/* 220:    */  public void serialize(Node pNode, ContentHandler pHandler)
/* 221:    */    throws SAXException
/* 222:    */  {
/* 223:223 */    if ((!isNamespaceDeclarationAttribute()) && (!isParentsNamespaceDeclarationDisabled()))
/* 224:    */    {
/* 225:225 */      parentsStartPrefixMappingEvents(pNode.getParentNode(), pHandler);
/* 226:    */    }
/* 227:227 */    doSerialize(pNode, pHandler);
/* 228:228 */    if ((!isNamespaceDeclarationAttribute()) && (!isParentsNamespaceDeclarationDisabled()))
/* 229:    */    {
/* 230:230 */      parentsEndPrefixMappingEvents(pNode.getParentNode(), pHandler);
/* 231:    */    }
/* 232:    */  }
/* 233:    */  
/* 245:    */  protected void doSerialize(Node pNode, ContentHandler pHandler)
/* 246:    */    throws SAXException
/* 247:    */  {
/* 248:248 */    switch (pNode.getNodeType()) {
/* 249:    */    case 9: 
/* 250:250 */      boolean startDocumentEvent = isStartingDocument();
/* 251:251 */      if (startDocumentEvent) {
/* 252:252 */        pHandler.startDocument();
/* 253:    */      }
/* 254:254 */      doSerializeChilds(pNode, pHandler);
/* 255:255 */      if (startDocumentEvent)
/* 256:256 */        pHandler.endDocument(); break;
/* 257:    */    
/* 259:    */    case 11: 
/* 260:260 */      doSerializeChilds(pNode, pHandler);
/* 261:261 */      break;
/* 262:    */    case 1: 
/* 263:263 */      AttributesImpl attr = new AttributesImpl();
/* 264:264 */      boolean isNamespaceDeclarationAttribute = isNamespaceDeclarationAttribute();
/* 265:265 */      if (!isNamespaceDeclarationAttribute) {
/* 266:266 */        startPrefixMappingEvents(pNode, pHandler);
/* 267:    */      }
/* 268:268 */      NamedNodeMap nnm = pNode.getAttributes();
/* 269:269 */      if (nnm != null) {
/* 270:270 */        for (int i = 0; i < nnm.getLength(); i++) {
/* 271:271 */          Node a = nnm.item(i);
/* 272:272 */          if ((isNamespaceDeclarationAttribute) || (!"http://www.w3.org/2000/xmlns/".equals(a.getNamespaceURI())))
/* 273:    */          {
/* 274:274 */            String aUri = a.getNamespaceURI();
/* 275:275 */            String aLocalName = a.getLocalName();
/* 276:276 */            String aNodeName = a.getNodeName();
/* 277:277 */            if (aLocalName == null) {
/* 278:278 */              if ((aUri == null) || (aUri.length() == 0)) {
/* 279:279 */                aLocalName = aNodeName;
/* 280:    */              } else {
/* 281:281 */                throw new IllegalStateException("aLocalName is null");
/* 282:    */              }
/* 283:    */            }
/* 284:284 */            attr.addAttribute(aUri == null ? "" : aUri, aNodeName, aLocalName, "CDATA", a.getNodeValue());
/* 285:    */          }
/* 286:    */        }
/* 287:    */      }
/* 288:    */      
/* 289:289 */      String nUri = pNode.getNamespaceURI();
/* 290:290 */      if (nUri == null) {
/* 291:291 */        nUri = "";
/* 292:    */      }
/* 293:293 */      pHandler.startElement(nUri, pNode.getLocalName(), pNode.getNodeName(), attr);
/* 294:    */      
/* 295:295 */      doSerializeChilds(pNode, pHandler);
/* 296:296 */      pHandler.endElement(nUri, pNode.getLocalName(), pNode.getNodeName());
/* 297:    */      
/* 298:298 */      if (!isNamespaceDeclarationAttribute)
/* 299:299 */        endPrefixMappingEvents(pNode, pHandler); break;
/* 300:    */    
/* 302:    */    case 3: 
/* 303:303 */      characters(pHandler, pNode.getNodeValue(), false);
/* 304:304 */      break;
/* 305:    */    case 4: 
/* 306:306 */      characters(pHandler, pNode.getNodeValue(), true);
/* 307:307 */      break;
/* 308:    */    case 7: 
/* 309:309 */      pHandler.processingInstruction(pNode.getNodeName(), pNode.getNodeValue());
/* 310:310 */      break;
/* 311:    */    case 5: 
/* 312:312 */      pHandler.skippedEntity(pNode.getNodeName());
/* 313:313 */      break;
/* 314:    */    case 8: 
/* 315:315 */      if ((pHandler instanceof LexicalHandler)) {
/* 316:316 */        String s = pNode.getNodeValue();
/* 317:317 */        ((LexicalHandler)pHandler).comment(s.toCharArray(), 0, s.length()); }
/* 318:318 */      break;
/* 319:    */    case 2: case 6: 
/* 320:    */    case 10: default: 
/* 321:321 */      throw new IllegalStateException("Unknown node type: " + pNode.getNodeType());
/* 322:    */    }
/* 323:    */  }
/* 324:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.serialize.DOMSerializer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */