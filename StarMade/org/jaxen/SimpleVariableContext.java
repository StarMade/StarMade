/*     */ package org.jaxen;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SimpleVariableContext
/*     */   implements VariableContext, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 961322093794516518L;
/*     */   private Map variables;
/*     */ 
/*     */   public SimpleVariableContext()
/*     */   {
/*  82 */     this.variables = new HashMap();
/*     */   }
/*     */ 
/*     */   public void setVariableValue(String namespaceURI, String localName, Object value)
/*     */   {
/* 106 */     this.variables.put(new QualifiedName(namespaceURI, localName), value);
/*     */   }
/*     */ 
/*     */   public void setVariableValue(String localName, Object value)
/*     */   {
/* 125 */     this.variables.put(new QualifiedName(null, localName), value);
/*     */   }
/*     */ 
/*     */   public Object getVariableValue(String namespaceURI, String prefix, String localName)
/*     */     throws UnresolvableException
/*     */   {
/* 133 */     QualifiedName key = new QualifiedName(namespaceURI, localName);
/*     */ 
/* 135 */     if (this.variables.containsKey(key))
/*     */     {
/* 137 */       return this.variables.get(key);
/*     */     }
/*     */ 
/* 141 */     throw new UnresolvableException("Variable " + key.getClarkForm());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.SimpleVariableContext
 * JD-Core Version:    0.6.2
 */