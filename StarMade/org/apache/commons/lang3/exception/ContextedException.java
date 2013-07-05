/*     */ package org.apache.commons.lang3.exception;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ public class ContextedException extends Exception
/*     */   implements ExceptionContext
/*     */ {
/*     */   private static final long serialVersionUID = 20110706L;
/*     */   private final ExceptionContext exceptionContext;
/*     */ 
/*     */   public ContextedException()
/*     */   {
/*  98 */     this.exceptionContext = new DefaultExceptionContext();
/*     */   }
/*     */ 
/*     */   public ContextedException(String message)
/*     */   {
/* 109 */     super(message);
/* 110 */     this.exceptionContext = new DefaultExceptionContext();
/*     */   }
/*     */ 
/*     */   public ContextedException(Throwable cause)
/*     */   {
/* 121 */     super(cause);
/* 122 */     this.exceptionContext = new DefaultExceptionContext();
/*     */   }
/*     */ 
/*     */   public ContextedException(String message, Throwable cause)
/*     */   {
/* 134 */     super(message, cause);
/* 135 */     this.exceptionContext = new DefaultExceptionContext();
/*     */   }
/*     */ 
/*     */   public ContextedException(String message, Throwable cause, ExceptionContext context)
/*     */   {
/* 146 */     super(message, cause);
/* 147 */     if (context == null) {
/* 148 */       context = new DefaultExceptionContext();
/*     */     }
/* 150 */     this.exceptionContext = context;
/*     */   }
/*     */ 
/*     */   public ContextedException addContextValue(String label, Object value)
/*     */   {
/* 168 */     this.exceptionContext.addContextValue(label, value);
/* 169 */     return this;
/*     */   }
/*     */ 
/*     */   public ContextedException setContextValue(String label, Object value)
/*     */   {
/* 186 */     this.exceptionContext.setContextValue(label, value);
/* 187 */     return this;
/*     */   }
/*     */ 
/*     */   public List<Object> getContextValues(String label)
/*     */   {
/* 194 */     return this.exceptionContext.getContextValues(label);
/*     */   }
/*     */ 
/*     */   public Object getFirstContextValue(String label)
/*     */   {
/* 201 */     return this.exceptionContext.getFirstContextValue(label);
/*     */   }
/*     */ 
/*     */   public List<Pair<String, Object>> getContextEntries()
/*     */   {
/* 208 */     return this.exceptionContext.getContextEntries();
/*     */   }
/*     */ 
/*     */   public Set<String> getContextLabels()
/*     */   {
/* 215 */     return this.exceptionContext.getContextLabels();
/*     */   }
/*     */ 
/*     */   public String getMessage()
/*     */   {
/* 226 */     return getFormattedExceptionMessage(super.getMessage());
/*     */   }
/*     */ 
/*     */   public String getRawMessage()
/*     */   {
/* 237 */     return super.getMessage();
/*     */   }
/*     */ 
/*     */   public String getFormattedExceptionMessage(String baseMessage)
/*     */   {
/* 244 */     return this.exceptionContext.getFormattedExceptionMessage(baseMessage);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.exception.ContextedException
 * JD-Core Version:    0.6.2
 */