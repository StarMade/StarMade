/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.QName;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.DTDHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.DeclHandler;
/*     */ import org.xml.sax.ext.LexicalHandler;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class SAXEventRecorder extends DefaultHandler
/*     */   implements LexicalHandler, DeclHandler, DTDHandler, Externalizable
/*     */ {
/*     */   public static final long serialVersionUID = 1L;
/*     */   private static final byte STRING = 0;
/*     */   private static final byte OBJECT = 1;
/*     */   private static final byte NULL = 2;
/*  70 */   private List events = new ArrayList();
/*     */ 
/*  72 */   private Map prefixMappings = new HashMap();
/*     */   private static final String XMLNS = "xmlns";
/*     */   private static final String EMPTY_STRING = "";
/*     */ 
/*     */   public void replay(ContentHandler handler)
/*     */     throws SAXException
/*     */   {
/*  83 */     Iterator itr = this.events.iterator();
/*     */ 
/*  85 */     while (itr.hasNext()) {
/*  86 */       SAXEvent saxEvent = (SAXEvent)itr.next();
/*     */ 
/*  88 */       switch (saxEvent.event)
/*     */       {
/*     */       case 1:
/*  91 */         handler.processingInstruction((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
/*     */ 
/*  94 */         break;
/*     */       case 2:
/*  97 */         handler.startPrefixMapping((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
/*     */ 
/* 100 */         break;
/*     */       case 3:
/* 103 */         handler.endPrefixMapping((String)saxEvent.getParm(0));
/*     */ 
/* 105 */         break;
/*     */       case 4:
/* 108 */         handler.startDocument();
/*     */ 
/* 110 */         break;
/*     */       case 5:
/* 113 */         handler.endDocument();
/*     */ 
/* 115 */         break;
/*     */       case 6:
/* 119 */         AttributesImpl attributes = new AttributesImpl();
/* 120 */         List attParmList = (List)saxEvent.getParm(3);
/*     */ 
/* 122 */         if (attParmList != null) {
/* 123 */           Iterator attsItr = attParmList.iterator();
/*     */ 
/* 125 */           while (attsItr.hasNext()) {
/* 126 */             String[] attParms = (String[])attsItr.next();
/* 127 */             attributes.addAttribute(attParms[0], attParms[1], attParms[2], attParms[3], attParms[4]);
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 132 */         handler.startElement((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2), attributes);
/*     */ 
/* 136 */         break;
/*     */       case 7:
/* 139 */         handler.endElement((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
/*     */ 
/* 143 */         break;
/*     */       case 8:
/* 147 */         char[] chars = (char[])saxEvent.getParm(0);
/* 148 */         int start = ((Integer)saxEvent.getParm(1)).intValue();
/* 149 */         int end = ((Integer)saxEvent.getParm(2)).intValue();
/* 150 */         handler.characters(chars, start, end);
/*     */ 
/* 152 */         break;
/*     */       case 9:
/* 156 */         ((Externalizable)handler).startDTD((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
/*     */ 
/* 160 */         break;
/*     */       case 10:
/* 163 */         ((Externalizable)handler).endDTD();
/*     */ 
/* 165 */         break;
/*     */       case 11:
/* 168 */         ((Externalizable)handler).startEntity((String)saxEvent.getParm(0));
/*     */ 
/* 171 */         break;
/*     */       case 12:
/* 174 */         ((Externalizable)handler).endEntity((String)saxEvent.getParm(0));
/*     */ 
/* 177 */         break;
/*     */       case 13:
/* 180 */         ((Externalizable)handler).startCDATA();
/*     */ 
/* 182 */         break;
/*     */       case 14:
/* 185 */         ((Externalizable)handler).endCDATA();
/*     */ 
/* 187 */         break;
/*     */       case 15:
/* 191 */         char[] cchars = (char[])saxEvent.getParm(0);
/* 192 */         int cstart = ((Integer)saxEvent.getParm(1)).intValue();
/* 193 */         int cend = ((Integer)saxEvent.getParm(2)).intValue();
/* 194 */         ((Externalizable)handler).comment(cchars, cstart, cend);
/*     */ 
/* 196 */         break;
/*     */       case 16:
/* 200 */         ((DeclHandler)handler).elementDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
/*     */ 
/* 203 */         break;
/*     */       case 17:
/* 206 */         ((DeclHandler)handler).attributeDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2), (String)saxEvent.getParm(3), (String)saxEvent.getParm(4));
/*     */ 
/* 211 */         break;
/*     */       case 18:
/* 214 */         ((DeclHandler)handler).internalEntityDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1));
/*     */ 
/* 218 */         break;
/*     */       case 19:
/* 221 */         ((DeclHandler)handler).externalEntityDecl((String)saxEvent.getParm(0), (String)saxEvent.getParm(1), (String)saxEvent.getParm(2));
/*     */ 
/* 225 */         break;
/*     */       default:
/* 228 */         throw new SAXException("Unrecognized event: " + saxEvent.event);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void processingInstruction(String target, String data)
/*     */     throws SAXException
/*     */   {
/* 238 */     SAXEvent saxEvent = new SAXEvent((byte)1);
/* 239 */     saxEvent.addParm(target);
/* 240 */     saxEvent.addParm(data);
/* 241 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void startPrefixMapping(String prefix, String uri) throws SAXException
/*     */   {
/* 246 */     SAXEvent saxEvent = new SAXEvent((byte)2);
/* 247 */     saxEvent.addParm(prefix);
/* 248 */     saxEvent.addParm(uri);
/* 249 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void endPrefixMapping(String prefix) throws SAXException {
/* 253 */     SAXEvent saxEvent = new SAXEvent((byte)3);
/* 254 */     saxEvent.addParm(prefix);
/* 255 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void startDocument() throws SAXException {
/* 259 */     SAXEvent saxEvent = new SAXEvent((byte)4);
/* 260 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void endDocument() throws SAXException {
/* 264 */     SAXEvent saxEvent = new SAXEvent((byte)5);
/* 265 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException
/*     */   {
/* 270 */     SAXEvent saxEvent = new SAXEvent((byte)6);
/* 271 */     saxEvent.addParm(namespaceURI);
/* 272 */     saxEvent.addParm(localName);
/* 273 */     saxEvent.addParm(qualifiedName);
/*     */ 
/* 275 */     QName qName = null;
/* 276 */     if (namespaceURI != null)
/* 277 */       qName = new QName(localName, Namespace.get(namespaceURI));
/*     */     else {
/* 279 */       qName = new QName(localName);
/*     */     }
/*     */ 
/* 282 */     if ((attributes != null) && (attributes.getLength() > 0)) {
/* 283 */       List attParmList = new ArrayList(attributes.getLength());
/* 284 */       String[] attParms = null;
/*     */ 
/* 286 */       for (int i = 0; i < attributes.getLength(); i++)
/*     */       {
/* 288 */         String attLocalName = attributes.getLocalName(i);
/*     */ 
/* 290 */         if (attLocalName.startsWith("xmlns"))
/*     */         {
/* 295 */           String prefix = null;
/* 296 */           if (attLocalName.length() > 5)
/* 297 */             prefix = attLocalName.substring(6);
/*     */           else {
/* 299 */             prefix = "";
/*     */           }
/*     */ 
/* 302 */           SAXEvent prefixEvent = new SAXEvent((byte)2);
/*     */ 
/* 304 */           prefixEvent.addParm(prefix);
/* 305 */           prefixEvent.addParm(attributes.getValue(i));
/* 306 */           this.events.add(prefixEvent);
/*     */ 
/* 310 */           List prefixes = (List)this.prefixMappings.get(qName);
/* 311 */           if (prefixes == null) {
/* 312 */             prefixes = new ArrayList();
/* 313 */             this.prefixMappings.put(qName, prefixes);
/*     */           }
/* 315 */           prefixes.add(prefix);
/*     */         }
/*     */         else
/*     */         {
/* 319 */           attParms = new String[5];
/* 320 */           attParms[0] = attributes.getURI(i);
/* 321 */           attParms[1] = attLocalName;
/* 322 */           attParms[2] = attributes.getQName(i);
/* 323 */           attParms[3] = attributes.getType(i);
/* 324 */           attParms[4] = attributes.getValue(i);
/* 325 */           attParmList.add(attParms);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 331 */       saxEvent.addParm(attParmList);
/*     */     }
/*     */ 
/* 334 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void endElement(String namespaceURI, String localName, String qName)
/*     */     throws SAXException
/*     */   {
/* 340 */     SAXEvent saxEvent = new SAXEvent((byte)7);
/* 341 */     saxEvent.addParm(namespaceURI);
/* 342 */     saxEvent.addParm(localName);
/* 343 */     saxEvent.addParm(qName);
/* 344 */     this.events.add(saxEvent);
/*     */ 
/* 349 */     QName elementName = null;
/* 350 */     if (namespaceURI != null)
/* 351 */       elementName = new QName(localName, Namespace.get(namespaceURI));
/*     */     else {
/* 353 */       elementName = new QName(localName);
/*     */     }
/*     */ 
/* 356 */     List prefixes = (List)this.prefixMappings.get(elementName);
/* 357 */     if (prefixes != null) {
/* 358 */       Iterator itr = prefixes.iterator();
/* 359 */       while (itr.hasNext()) {
/* 360 */         SAXEvent prefixEvent = new SAXEvent((byte)3);
/*     */ 
/* 362 */         prefixEvent.addParm(itr.next());
/* 363 */         this.events.add(prefixEvent);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void characters(char[] ch, int start, int end) throws SAXException
/*     */   {
/* 370 */     SAXEvent saxEvent = new SAXEvent((byte)8);
/* 371 */     saxEvent.addParm(ch);
/* 372 */     saxEvent.addParm(new Integer(start));
/* 373 */     saxEvent.addParm(new Integer(end));
/* 374 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void startDTD(String name, String publicId, String systemId)
/*     */     throws SAXException
/*     */   {
/* 381 */     SAXEvent saxEvent = new SAXEvent((byte)9);
/* 382 */     saxEvent.addParm(name);
/* 383 */     saxEvent.addParm(publicId);
/* 384 */     saxEvent.addParm(systemId);
/* 385 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void endDTD() throws SAXException {
/* 389 */     SAXEvent saxEvent = new SAXEvent((byte)10);
/* 390 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void startEntity(String name) throws SAXException {
/* 394 */     SAXEvent saxEvent = new SAXEvent((byte)11);
/* 395 */     saxEvent.addParm(name);
/* 396 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void endEntity(String name) throws SAXException {
/* 400 */     SAXEvent saxEvent = new SAXEvent((byte)12);
/* 401 */     saxEvent.addParm(name);
/* 402 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void startCDATA() throws SAXException {
/* 406 */     SAXEvent saxEvent = new SAXEvent((byte)13);
/* 407 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void endCDATA() throws SAXException {
/* 411 */     SAXEvent saxEvent = new SAXEvent((byte)14);
/* 412 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void comment(char[] ch, int start, int end) throws SAXException {
/* 416 */     SAXEvent saxEvent = new SAXEvent((byte)15);
/* 417 */     saxEvent.addParm(ch);
/* 418 */     saxEvent.addParm(new Integer(start));
/* 419 */     saxEvent.addParm(new Integer(end));
/* 420 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void elementDecl(String name, String model)
/*     */     throws SAXException
/*     */   {
/* 426 */     SAXEvent saxEvent = new SAXEvent((byte)16);
/* 427 */     saxEvent.addParm(name);
/* 428 */     saxEvent.addParm(model);
/* 429 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) throws SAXException
/*     */   {
/* 434 */     SAXEvent saxEvent = new SAXEvent((byte)17);
/* 435 */     saxEvent.addParm(eName);
/* 436 */     saxEvent.addParm(aName);
/* 437 */     saxEvent.addParm(type);
/* 438 */     saxEvent.addParm(valueDefault);
/* 439 */     saxEvent.addParm(value);
/* 440 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void internalEntityDecl(String name, String value) throws SAXException
/*     */   {
/* 445 */     SAXEvent saxEvent = new SAXEvent((byte)18);
/* 446 */     saxEvent.addParm(name);
/* 447 */     saxEvent.addParm(value);
/* 448 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void externalEntityDecl(String name, String publicId, String sysId) throws SAXException
/*     */   {
/* 453 */     SAXEvent saxEvent = new SAXEvent((byte)19);
/* 454 */     saxEvent.addParm(name);
/* 455 */     saxEvent.addParm(publicId);
/* 456 */     saxEvent.addParm(sysId);
/* 457 */     this.events.add(saxEvent);
/*     */   }
/*     */ 
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 461 */     if (this.events == null) {
/* 462 */       out.writeByte(2);
/*     */     } else {
/* 464 */       out.writeByte(1);
/* 465 */       out.writeObject(this.events);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException
/*     */   {
/* 471 */     if (in.readByte() != 2)
/* 472 */       this.events = ((List)in.readObject());
/*     */   }
/*     */ 
/*     */   static class SAXEvent implements Externalizable
/*     */   {
/*     */     public static final long serialVersionUID = 1L;
/*     */     static final byte PROCESSING_INSTRUCTION = 1;
/*     */     static final byte START_PREFIX_MAPPING = 2;
/*     */     static final byte END_PREFIX_MAPPING = 3;
/*     */     static final byte START_DOCUMENT = 4;
/*     */     static final byte END_DOCUMENT = 5;
/*     */     static final byte START_ELEMENT = 6;
/*     */     static final byte END_ELEMENT = 7;
/*     */     static final byte CHARACTERS = 8;
/*     */     static final byte START_DTD = 9;
/*     */     static final byte END_DTD = 10;
/*     */     static final byte START_ENTITY = 11;
/*     */     static final byte END_ENTITY = 12;
/*     */     static final byte START_CDATA = 13;
/*     */     static final byte END_CDATA = 14;
/*     */     static final byte COMMENT = 15;
/*     */     static final byte ELEMENT_DECL = 16;
/*     */     static final byte ATTRIBUTE_DECL = 17;
/*     */     static final byte INTERNAL_ENTITY_DECL = 18;
/*     */     static final byte EXTERNAL_ENTITY_DECL = 19;
/*     */     protected byte event;
/*     */     protected List parms;
/*     */ 
/*     */     public SAXEvent()
/*     */     {
/*     */     }
/*     */ 
/*     */     SAXEvent(byte event)
/*     */     {
/* 527 */       this.event = event;
/*     */     }
/*     */ 
/*     */     void addParm(Object parm) {
/* 531 */       if (this.parms == null) {
/* 532 */         this.parms = new ArrayList(3);
/*     */       }
/*     */ 
/* 535 */       this.parms.add(parm);
/*     */     }
/*     */ 
/*     */     Object getParm(int index) {
/* 539 */       if ((this.parms != null) && (index < this.parms.size())) {
/* 540 */         return this.parms.get(index);
/*     */       }
/* 542 */       return null;
/*     */     }
/*     */ 
/*     */     public void writeExternal(ObjectOutput out) throws IOException
/*     */     {
/* 547 */       out.writeByte(this.event);
/*     */ 
/* 549 */       if (this.parms == null) {
/* 550 */         out.writeByte(2);
/*     */       } else {
/* 552 */         out.writeByte(1);
/* 553 */         out.writeObject(this.parms);
/*     */       }
/*     */     }
/*     */ 
/*     */     public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException
/*     */     {
/* 559 */       this.event = in.readByte();
/*     */ 
/* 561 */       if (in.readByte() != 2)
/* 562 */         this.parms = ((List)in.readObject());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXEventRecorder
 * JD-Core Version:    0.6.2
 */