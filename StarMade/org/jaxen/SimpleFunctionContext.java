/*     */ package org.jaxen;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class SimpleFunctionContext
/*     */   implements FunctionContext
/*     */ {
/*     */   private HashMap functions;
/*     */ 
/*     */   public SimpleFunctionContext()
/*     */   {
/*  77 */     this.functions = new HashMap();
/*     */   }
/*     */ 
/*     */   public void registerFunction(String namespaceURI, String localName, Function function)
/*     */   {
/* 112 */     this.functions.put(new QualifiedName(namespaceURI, localName), function);
/*     */   }
/*     */ 
/*     */   public Function getFunction(String namespaceURI, String prefix, String localName)
/*     */     throws UnresolvableException
/*     */   {
/* 121 */     QualifiedName key = new QualifiedName(namespaceURI, localName);
/*     */ 
/* 123 */     if (this.functions.containsKey(key)) {
/* 124 */       return (Function)this.functions.get(key);
/*     */     }
/*     */ 
/* 127 */     throw new UnresolvableException("No Such Function " + key.getClarkForm());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.SimpleFunctionContext
 * JD-Core Version:    0.6.2
 */