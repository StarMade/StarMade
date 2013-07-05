/*     */ package org.apache.ws.commons.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ 
/*     */ public class NamespaceContextImpl
/*     */   implements NamespaceContext
/*     */ {
/*     */   private List prefixList;
/*     */   private String cachedPrefix;
/*     */   private String cachedURI;
/*     */ 
/*     */   public void reset()
/*     */   {
/*  45 */     this.cachedURI = (this.cachedPrefix = null);
/*  46 */     if (this.prefixList != null)
/*  47 */       this.prefixList.clear();
/*     */   }
/*     */ 
/*     */   public void startPrefixMapping(String pPrefix, String pURI)
/*     */   {
/*  56 */     if (pPrefix == null) {
/*  57 */       throw new IllegalArgumentException("The namespace prefix must not be null.");
/*     */     }
/*  59 */     if (pURI == null) {
/*  60 */       throw new IllegalArgumentException("The namespace prefix must not be null.");
/*     */     }
/*  62 */     if (this.cachedURI != null) {
/*  63 */       if (this.prefixList == null) this.prefixList = new ArrayList();
/*  64 */       this.prefixList.add(this.cachedPrefix);
/*  65 */       this.prefixList.add(this.cachedURI);
/*     */     }
/*  67 */     this.cachedURI = pURI;
/*  68 */     this.cachedPrefix = pPrefix;
/*     */   }
/*     */ 
/*     */   public void endPrefixMapping(String pPrefix)
/*     */   {
/*  81 */     if (pPrefix == null) {
/*  82 */       throw new IllegalArgumentException("The namespace prefix must not be null.");
/*     */     }
/*  84 */     if (pPrefix.equals(this.cachedPrefix)) {
/*  85 */       if ((this.prefixList != null) && (this.prefixList.size() > 0)) {
/*  86 */         this.cachedURI = this.prefixList.remove(this.prefixList.size() - 1).toString();
/*  87 */         this.cachedPrefix = this.prefixList.remove(this.prefixList.size() - 1).toString();
/*     */       } else {
/*  89 */         this.cachedPrefix = (this.cachedURI = null);
/*     */       }
/*     */     }
/*  92 */     else throw new IllegalStateException("The prefix " + pPrefix + " isn't the prefix, which has been defined last.");
/*     */   }
/*     */ 
/*     */   public String getNamespaceURI(String pPrefix)
/*     */   {
/* 104 */     if (pPrefix == null) {
/* 105 */       throw new IllegalArgumentException("The namespace prefix must not be null.");
/*     */     }
/* 107 */     if (this.cachedURI != null) {
/* 108 */       if (this.cachedPrefix.equals(pPrefix)) return this.cachedURI;
/* 109 */       if (this.prefixList != null) {
/* 110 */         for (int i = this.prefixList.size(); i > 0; i -= 2) {
/* 111 */           if (pPrefix.equals(this.prefixList.get(i - 2))) {
/* 112 */             return (String)this.prefixList.get(i - 1);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 117 */     if ("xml".equals(pPrefix))
/* 118 */       return "http://www.w3.org/XML/1998/namespace";
/* 119 */     if ("xmlns".equals(pPrefix)) {
/* 120 */       return "http://www.w3.org/2000/xmlns/";
/*     */     }
/* 122 */     return null;
/*     */   }
/*     */ 
/*     */   public String getPrefix(String pURI)
/*     */   {
/* 135 */     if (pURI == null) {
/* 136 */       throw new IllegalArgumentException("The namespace URI must not be null.");
/*     */     }
/* 138 */     if (this.cachedURI != null) {
/* 139 */       if (this.cachedURI.equals(pURI)) return this.cachedPrefix;
/* 140 */       if (this.prefixList != null) {
/* 141 */         for (int i = this.prefixList.size(); i > 0; i -= 2) {
/* 142 */           if (pURI.equals(this.prefixList.get(i - 1))) {
/* 143 */             return (String)this.prefixList.get(i - 2);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 148 */     if ("http://www.w3.org/XML/1998/namespace".equals(pURI))
/* 149 */       return "xml";
/* 150 */     if ("http://www.w3.org/2000/xmlns/".equals(pURI)) {
/* 151 */       return "xmlns";
/*     */     }
/* 153 */     return null;
/*     */   }
/*     */ 
/*     */   public String getAttributePrefix(String pURI)
/*     */   {
/* 164 */     if (pURI == null) {
/* 165 */       throw new IllegalArgumentException("The namespace URI must not be null.");
/*     */     }
/* 167 */     if (pURI.length() == 0) {
/* 168 */       return "";
/*     */     }
/* 170 */     if (this.cachedURI != null) {
/* 171 */       if ((this.cachedURI.equals(pURI)) && (this.cachedPrefix.length() > 0)) {
/* 172 */         return this.cachedPrefix;
/*     */       }
/* 174 */       if (this.prefixList != null) {
/* 175 */         for (int i = this.prefixList.size(); i > 0; i -= 2) {
/* 176 */           if (pURI.equals(this.prefixList.get(i - 1))) {
/* 177 */             String prefix = (String)this.prefixList.get(i - 2);
/* 178 */             if (prefix.length() > 0) {
/* 179 */               return prefix;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 185 */     if ("http://www.w3.org/XML/1998/namespace".equals(pURI))
/* 186 */       return "xml";
/* 187 */     if ("http://www.w3.org/2000/xmlns/".equals(pURI)) {
/* 188 */       return "xmlns";
/*     */     }
/* 190 */     return null;
/*     */   }
/*     */ 
/*     */   public Iterator getPrefixes(String pURI)
/*     */   {
/* 200 */     if (pURI == null) {
/* 201 */       throw new IllegalArgumentException("The namespace URI must not be null.");
/*     */     }
/* 203 */     List list = new ArrayList();
/* 204 */     if (this.cachedURI != null) {
/* 205 */       if (this.cachedURI.equals(pURI)) list.add(this.cachedPrefix);
/* 206 */       if (this.prefixList != null) {
/* 207 */         for (int i = this.prefixList.size(); i > 0; i -= 2) {
/* 208 */           if (pURI.equals(this.prefixList.get(i - 1))) {
/* 209 */             list.add(this.prefixList.get(i - 2));
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 214 */     if (pURI.equals("http://www.w3.org/2000/xmlns/"))
/* 215 */       list.add("xmlns");
/* 216 */     else if (pURI.equals("http://www.w3.org/XML/1998/namespace")) {
/* 217 */       list.add("xml");
/*     */     }
/* 219 */     return list.iterator();
/*     */   }
/*     */ 
/*     */   public boolean isPrefixDeclared(String pPrefix)
/*     */   {
/* 225 */     if (this.cachedURI != null) {
/* 226 */       if ((this.cachedPrefix != null) && (this.cachedPrefix.equals(pPrefix))) return true;
/* 227 */       if (this.prefixList != null) {
/* 228 */         for (int i = this.prefixList.size(); i > 0; i -= 2) {
/* 229 */           if (this.prefixList.get(i - 2).equals(pPrefix)) {
/* 230 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 235 */     return "xml".equals(pPrefix);
/*     */   }
/*     */ 
/*     */   public int getContext()
/*     */   {
/* 250 */     return (this.prefixList == null ? 0 : this.prefixList.size()) + (this.cachedURI == null ? 0 : 2);
/*     */   }
/*     */ 
/*     */   public String checkContext(int i)
/*     */   {
/* 277 */     if (getContext() == i) {
/* 278 */       return null;
/*     */     }
/* 280 */     String result = this.cachedPrefix;
/* 281 */     if ((this.prefixList != null) && (this.prefixList.size() > 0)) {
/* 282 */       this.cachedURI = this.prefixList.remove(this.prefixList.size() - 1).toString();
/* 283 */       this.cachedPrefix = this.prefixList.remove(this.prefixList.size() - 1).toString();
/*     */     } else {
/* 285 */       this.cachedURI = null;
/* 286 */       this.cachedPrefix = null;
/*     */     }
/* 288 */     return result;
/*     */   }
/*     */ 
/*     */   public List getPrefixes()
/*     */   {
/* 297 */     if (this.cachedPrefix == null)
/* 298 */       return Collections.EMPTY_LIST;
/* 299 */     if (this.prefixList == null) {
/* 300 */       return Collections.singletonList(this.cachedPrefix);
/*     */     }
/* 302 */     List result = new ArrayList(this.prefixList.size() + 1);
/* 303 */     for (int i = 0; i < this.prefixList.size(); i += 2) {
/* 304 */       result.add(this.prefixList.get(i));
/*     */     }
/* 306 */     result.add(this.cachedPrefix);
/* 307 */     return result;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.util.NamespaceContextImpl
 * JD-Core Version:    0.6.2
 */