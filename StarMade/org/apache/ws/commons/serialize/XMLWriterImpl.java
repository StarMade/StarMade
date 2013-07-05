/*     */ package org.apache.ws.commons.serialize;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.Locator;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class XMLWriterImpl
/*     */   implements XMLWriter
/*     */ {
/*     */   private static final int STATE_OUTSIDE = 0;
/*     */   private static final int STATE_IN_START_ELEMENT = 1;
/*     */   private static final int STATE_IN_ELEMENT = 2;
/*     */   private String encoding;
/*     */   private String indentString;
/*     */   private String lineFeed;
/*     */   private Writer w;
/*     */   private Locator l;
/*     */   private Map delayedPrefixes;
/*  40 */   int curIndent = 0;
/*     */   private int state;
/*     */   private boolean declarating;
/*     */   private boolean indenting;
/*     */   private boolean flushing;
/*     */ 
/*     */   public void setEncoding(String pEncoding)
/*     */   {
/*  45 */     this.encoding = pEncoding; } 
/*  46 */   public String getEncoding() { return this.encoding; } 
/*  47 */   public void setDeclarating(boolean pDeclarating) { this.declarating = pDeclarating; } 
/*  48 */   public boolean isDeclarating() { return this.declarating; } 
/*  49 */   public void setIndenting(boolean pIndenting) { this.indenting = pIndenting; } 
/*  50 */   public boolean isIndenting() { return this.indenting; } 
/*  51 */   public void setIndentString(String pIndentString) { this.indentString = pIndentString; } 
/*  52 */   public String getIndentString() { return this.indentString; } 
/*  53 */   public void setLineFeed(String pLineFeed) { this.lineFeed = pLineFeed; } 
/*  54 */   public String getLineFeed() { return this.lineFeed; } 
/*  55 */   public void setFlushing(boolean pFlushing) { this.flushing = pFlushing; } 
/*  56 */   public boolean isFlushing() { return this.flushing; }
/*     */ 
/*     */ 
/*     */   public void setWriter(Writer pWriter)
/*     */   {
/*  61 */     this.w = pWriter;
/*     */   }
/*     */ 
/*     */   public Writer getWriter()
/*     */   {
/*  67 */     return this.w;
/*     */   }
/*     */ 
/*     */   public void setDocumentLocator(Locator pLocator)
/*     */   {
/*  75 */     this.l = pLocator;
/*     */   }
/*     */ 
/*     */   public Locator getDocumentLocator()
/*     */   {
/*  81 */     return this.l;
/*     */   }
/*     */ 
/*     */   public void startPrefixMapping(String prefix, String namespaceURI)
/*     */     throws SAXException
/*     */   {
/*  92 */     if (this.delayedPrefixes == null) {
/*  93 */       this.delayedPrefixes = new HashMap();
/*     */     }
/*  95 */     if ("".equals(prefix)) {
/*  96 */       if (namespaceURI.equals(prefix)) {
/*  97 */         return;
/*     */       }
/*  99 */       prefix = "xmlns";
/*     */     } else {
/* 101 */       prefix = "xmlns:" + prefix;
/*     */     }
/* 103 */     this.delayedPrefixes.put(prefix, namespaceURI);
/*     */   }
/*     */ 
/*     */   public void endPrefixMapping(String prefix)
/*     */     throws SAXException
/*     */   {
/* 112 */     if (this.delayedPrefixes != null) {
/* 113 */       if ("".equals(prefix))
/* 114 */         prefix = "xmlns";
/*     */       else {
/* 116 */         prefix = "xmlns:" + prefix;
/*     */       }
/* 118 */       this.delayedPrefixes.remove(prefix);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startDocument()
/*     */     throws SAXException
/*     */   {
/* 126 */     if (this.delayedPrefixes != null) {
/* 127 */       this.delayedPrefixes.clear();
/*     */     }
/* 129 */     this.state = 0;
/* 130 */     this.curIndent = 0;
/* 131 */     if ((isDeclarating()) && (this.w != null))
/*     */       try {
/* 133 */         this.w.write("<?xml version=\"1.0\"");
/* 134 */         String enc = getEncoding();
/* 135 */         if (enc != null) {
/* 136 */           this.w.write(" encoding=\"");
/* 137 */           this.w.write(enc);
/* 138 */           this.w.write("\"");
/*     */         }
/* 140 */         this.w.write("?>");
/* 141 */         if (isIndenting()) {
/* 142 */           String lf = getLineFeed();
/* 143 */           if (lf != null)
/* 144 */             this.w.write(lf);
/*     */         }
/*     */       }
/*     */       catch (IOException e) {
/* 148 */         throw new SAXException("Failed to write XML declaration: " + e.getMessage(), e);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void endDocument()
/*     */     throws SAXException
/*     */   {
/* 160 */     if ((isFlushing()) && (this.w != null))
/*     */       try {
/* 162 */         this.w.flush();
/*     */       } catch (IOException e) {
/* 164 */         throw new SAXException("Failed to flush target writer: " + e.getMessage(), e);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void ignorableWhitespace(char[] ch, int start, int length)
/*     */     throws SAXException
/*     */   {
/* 177 */     characters(ch, start, length);
/*     */   }
/*     */ 
/*     */   private void stopTerminator() throws IOException {
/* 181 */     if (this.state == 1) {
/* 182 */       if (this.w != null) {
/* 183 */         this.w.write(62);
/*     */       }
/* 185 */       this.state = 2;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void characters(char[] ch, int start, int length)
/*     */     throws SAXException
/*     */   {
/*     */     try
/*     */     {
/* 197 */       stopTerminator();
/* 198 */       if (this.w == null) return;
/* 199 */       int end = start + length;
/* 200 */       for (int i = start; i < end; i++) {
/* 201 */         char c = ch[i];
/* 202 */         switch (c) { case '&':
/* 203 */           this.w.write("&amp;"); break;
/*     */         case '<':
/* 204 */           this.w.write("&lt;"); break;
/*     */         case '>':
/* 205 */           this.w.write("&gt;"); break;
/*     */         case '\t':
/*     */         case '\n':
/*     */         case '\r':
/* 209 */           this.w.write(c); break;
/*     */         default:
/* 211 */           if (canEncode(c)) {
/* 212 */             this.w.write(c);
/*     */           } else {
/* 214 */             this.w.write("&#");
/* 215 */             this.w.write(Integer.toString(c));
/* 216 */             this.w.write(";");
/*     */           }
/*     */           break; }
/*     */       }
/*     */     }
/*     */     catch (IOException e) {
/* 222 */       throw new SAXException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean canEncode(char c) {
/* 227 */     return (c == '\n') || ((c >= ' ') && (c < ''));
/*     */   }
/*     */ 
/*     */   public void endElement(String namespaceURI, String localName, String qName)
/*     */     throws SAXException
/*     */   {
/* 240 */     if (isIndenting()) {
/* 241 */       this.curIndent -= 1;
/*     */     }
/* 243 */     if (this.w != null)
/*     */       try {
/* 245 */         if (this.state == 1) {
/* 246 */           this.w.write("/>");
/* 247 */           this.state = 0;
/*     */         } else {
/* 249 */           if (this.state == 0) {
/* 250 */             indentMe();
/*     */           }
/* 252 */           this.w.write("</");
/* 253 */           this.w.write(qName);
/* 254 */           this.w.write(62);
/*     */         }
/* 256 */         this.state = 0;
/*     */       } catch (IOException e) {
/* 258 */         throw new SAXException(e);
/*     */       }
/*     */   }
/*     */ 
/*     */   private void indentMe() throws IOException
/*     */   {
/* 264 */     if ((this.w != null) && 
/* 265 */       (isIndenting())) {
/* 266 */       String s = getLineFeed();
/* 267 */       if (s != null) {
/* 268 */         this.w.write(s);
/*     */       }
/* 270 */       s = getIndentString();
/* 271 */       if (s != null)
/* 272 */         for (int i = 0; i < this.curIndent; i++)
/* 273 */           this.w.write(s);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void writeCData(String v)
/*     */     throws IOException
/*     */   {
/* 281 */     int len = v.length();
/* 282 */     for (int j = 0; j < len; j++) {
/* 283 */       char c = v.charAt(j);
/* 284 */       switch (c) { case '&':
/* 285 */         this.w.write("&amp;"); break;
/*     */       case '<':
/* 286 */         this.w.write("&lt;"); break;
/*     */       case '>':
/* 287 */         this.w.write("&gt;"); break;
/*     */       case '\'':
/* 288 */         this.w.write("&apos;"); break;
/*     */       case '"':
/* 289 */         this.w.write("&quot;"); break;
/*     */       default:
/* 291 */         if (canEncode(c)) {
/* 292 */           this.w.write(c);
/*     */         } else {
/* 294 */           this.w.write("&#");
/* 295 */           this.w.write(Integer.toString(c));
/* 296 */           this.w.write(59);
/*     */         }
/*     */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void startElement(String namespaceURI, String localName, String qName, Attributes attr)
/*     */     throws SAXException
/*     */   {
/*     */     try
/*     */     {
/* 314 */       stopTerminator();
/* 315 */       if (isIndenting()) {
/* 316 */         if (this.curIndent > 0) {
/* 317 */           indentMe();
/*     */         }
/* 319 */         this.curIndent += 1;
/*     */       }
/*     */ 
/* 322 */       if (this.w != null) {
/* 323 */         this.w.write(60);
/* 324 */         this.w.write(qName);
/*     */         int i;
/* 325 */         if (attr != null) {
/* 326 */           for (i = attr.getLength(); i > 0; ) {
/* 327 */             this.w.write(32);
/* 328 */             String name = attr.getQName(--i);
/* 329 */             this.w.write(name);
/* 330 */             if (this.delayedPrefixes != null) {
/* 331 */               this.delayedPrefixes.remove(name);
/*     */             }
/* 333 */             this.w.write("=\"");
/* 334 */             writeCData(attr.getValue(i));
/* 335 */             this.w.write(34);
/*     */           }
/*     */         }
/* 338 */         if ((this.delayedPrefixes != null) && (this.delayedPrefixes.size() > 0)) {
/* 339 */           Iterator iter = this.delayedPrefixes.entrySet().iterator();
/* 340 */           while (iter.hasNext()) {
/* 341 */             Map.Entry entry = (Map.Entry)iter.next();
/* 342 */             this.w.write(32);
/* 343 */             this.w.write((String)entry.getKey());
/* 344 */             this.w.write("=\"");
/* 345 */             this.w.write((String)entry.getValue());
/* 346 */             this.w.write(34);
/*     */           }
/* 348 */           this.delayedPrefixes.clear();
/*     */         }
/*     */       }
/* 351 */       this.state = 1;
/*     */     } catch (IOException e) {
/* 353 */       throw new SAXException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void skippedEntity(String ent)
/*     */     throws SAXException
/*     */   {
/* 363 */     throw new SAXException("Don't know how to skip entities");
/*     */   }
/*     */ 
/*     */   public void processingInstruction(String target, String data)
/*     */     throws SAXException
/*     */   {
/*     */     try
/*     */     {
/* 375 */       stopTerminator();
/* 376 */       if (this.w != null) {
/* 377 */         this.w.write("<?");
/* 378 */         this.w.write(target);
/* 379 */         this.w.write(32);
/* 380 */         this.w.write(data);
/* 381 */         this.w.write("?>");
/*     */       }
/*     */     } catch (IOException e) {
/* 384 */       throw new SAXException(e);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.serialize.XMLWriterImpl
 * JD-Core Version:    0.6.2
 */