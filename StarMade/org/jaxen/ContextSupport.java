/*     */ package org.jaxen;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class ContextSupport
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4494082174713652559L;
/*     */   private transient FunctionContext functionContext;
/*     */   private NamespaceContext namespaceContext;
/*     */   private VariableContext variableContext;
/*     */   private Navigator navigator;
/*     */ 
/*     */   public ContextSupport()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ContextSupport(NamespaceContext namespaceContext, FunctionContext functionContext, VariableContext variableContext, Navigator navigator)
/*     */   {
/*  98 */     setNamespaceContext(namespaceContext);
/*  99 */     setFunctionContext(functionContext);
/* 100 */     setVariableContext(variableContext);
/*     */ 
/* 102 */     this.navigator = navigator;
/*     */   }
/*     */ 
/*     */   public void setNamespaceContext(NamespaceContext namespaceContext)
/*     */   {
/* 115 */     this.namespaceContext = namespaceContext;
/*     */   }
/*     */ 
/*     */   public NamespaceContext getNamespaceContext()
/*     */   {
/* 124 */     return this.namespaceContext;
/*     */   }
/*     */ 
/*     */   public void setFunctionContext(FunctionContext functionContext)
/*     */   {
/* 133 */     this.functionContext = functionContext;
/*     */   }
/*     */ 
/*     */   public FunctionContext getFunctionContext()
/*     */   {
/* 142 */     return this.functionContext;
/*     */   }
/*     */ 
/*     */   public void setVariableContext(VariableContext variableContext)
/*     */   {
/* 151 */     this.variableContext = variableContext;
/*     */   }
/*     */ 
/*     */   public VariableContext getVariableContext()
/*     */   {
/* 160 */     return this.variableContext;
/*     */   }
/*     */ 
/*     */   public Navigator getNavigator()
/*     */   {
/* 169 */     return this.navigator;
/*     */   }
/*     */ 
/*     */   public String translateNamespacePrefixToUri(String prefix)
/*     */   {
/* 183 */     if ("xml".equals(prefix)) {
/* 184 */       return "http://www.w3.org/XML/1998/namespace";
/*     */     }
/* 186 */     NamespaceContext context = getNamespaceContext();
/*     */ 
/* 188 */     if (context != null)
/*     */     {
/* 190 */       return context.translateNamespacePrefixToUri(prefix);
/*     */     }
/*     */ 
/* 193 */     return null;
/*     */   }
/*     */ 
/*     */   public Object getVariableValue(String namespaceURI, String prefix, String localName)
/*     */     throws UnresolvableException
/*     */   {
/* 211 */     VariableContext context = getVariableContext();
/*     */ 
/* 213 */     if (context != null)
/*     */     {
/* 215 */       return context.getVariableValue(namespaceURI, prefix, localName);
/*     */     }
/*     */ 
/* 219 */     throw new UnresolvableException("No variable context installed");
/*     */   }
/*     */ 
/*     */   public Function getFunction(String namespaceURI, String prefix, String localName)
/*     */     throws UnresolvableException
/*     */   {
/* 238 */     FunctionContext context = getFunctionContext();
/*     */ 
/* 240 */     if (context != null)
/*     */     {
/* 242 */       return context.getFunction(namespaceURI, prefix, localName);
/*     */     }
/*     */ 
/* 246 */     throw new UnresolvableException("No function context installed");
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.ContextSupport
 * JD-Core Version:    0.6.2
 */