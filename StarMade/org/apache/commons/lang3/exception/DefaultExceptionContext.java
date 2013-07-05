/*     */ package org.apache.commons.lang3.exception;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ public class DefaultExceptionContext
/*     */   implements ExceptionContext, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 20110706L;
/*  47 */   private final List<Pair<String, Object>> contextValues = new ArrayList();
/*     */ 
/*     */   public DefaultExceptionContext addContextValue(String label, Object value)
/*     */   {
/*  53 */     this.contextValues.add(new ImmutablePair(label, value));
/*  54 */     return this;
/*     */   }
/*     */ 
/*     */   public DefaultExceptionContext setContextValue(String label, Object value)
/*     */   {
/*  61 */     for (Iterator iter = this.contextValues.iterator(); iter.hasNext(); ) {
/*  62 */       Pair p = (Pair)iter.next();
/*  63 */       if (StringUtils.equals(label, (CharSequence)p.getKey())) {
/*  64 */         iter.remove();
/*     */       }
/*     */     }
/*  67 */     addContextValue(label, value);
/*  68 */     return this;
/*     */   }
/*     */ 
/*     */   public List<Object> getContextValues(String label)
/*     */   {
/*  75 */     List values = new ArrayList();
/*  76 */     for (Pair pair : this.contextValues) {
/*  77 */       if (StringUtils.equals(label, (CharSequence)pair.getKey())) {
/*  78 */         values.add(pair.getValue());
/*     */       }
/*     */     }
/*  81 */     return values;
/*     */   }
/*     */ 
/*     */   public Object getFirstContextValue(String label)
/*     */   {
/*  88 */     for (Pair pair : this.contextValues) {
/*  89 */       if (StringUtils.equals(label, (CharSequence)pair.getKey())) {
/*  90 */         return pair.getValue();
/*     */       }
/*     */     }
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */   public Set<String> getContextLabels()
/*     */   {
/* 100 */     Set labels = new HashSet();
/* 101 */     for (Pair pair : this.contextValues) {
/* 102 */       labels.add(pair.getKey());
/*     */     }
/* 104 */     return labels;
/*     */   }
/*     */ 
/*     */   public List<Pair<String, Object>> getContextEntries()
/*     */   {
/* 111 */     return this.contextValues;
/*     */   }
/*     */ 
/*     */   public String getFormattedExceptionMessage(String baseMessage)
/*     */   {
/* 121 */     StringBuilder buffer = new StringBuilder(256);
/* 122 */     if (baseMessage != null) {
/* 123 */       buffer.append(baseMessage);
/*     */     }
/*     */ 
/* 126 */     if (this.contextValues.size() > 0) {
/* 127 */       if (buffer.length() > 0) {
/* 128 */         buffer.append('\n');
/*     */       }
/* 130 */       buffer.append("Exception Context:\n");
/*     */ 
/* 132 */       int i = 0;
/* 133 */       for (Pair pair : this.contextValues) {
/* 134 */         buffer.append("\t[");
/* 135 */         buffer.append(++i);
/* 136 */         buffer.append(':');
/* 137 */         buffer.append((String)pair.getKey());
/* 138 */         buffer.append("=");
/* 139 */         Object value = pair.getValue();
/* 140 */         if (value == null) {
/* 141 */           buffer.append("null");
/*     */         } else {
/*     */           String valueStr;
/*     */           try {
/* 145 */             valueStr = value.toString();
/*     */           } catch (Exception e) {
/* 147 */             valueStr = "Exception thrown on toString(): " + ExceptionUtils.getStackTrace(e);
/*     */           }
/* 149 */           buffer.append(valueStr);
/*     */         }
/* 151 */         buffer.append("]\n");
/*     */       }
/* 153 */       buffer.append("---------------------------------");
/*     */     }
/* 155 */     return buffer.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.exception.DefaultExceptionContext
 * JD-Core Version:    0.6.2
 */