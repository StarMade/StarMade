/*     */ package org.dom4j.xpath;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.dom4j.InvalidXPathException;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.XPathException;
/*     */ import org.jaxen.Context;
/*     */ import org.jaxen.ContextSupport;
/*     */ import org.jaxen.JaxenException;
/*     */ import org.jaxen.SimpleNamespaceContext;
/*     */ import org.jaxen.SimpleVariableContext;
/*     */ import org.jaxen.VariableContext;
/*     */ import org.jaxen.XPathFunctionContext;
/*     */ import org.jaxen.dom4j.DocumentNavigator;
/*     */ import org.jaxen.pattern.PatternParser;
/*     */ import org.jaxen.saxpath.SAXPathException;
/*     */ 
/*     */ public class XPathPattern
/*     */   implements org.dom4j.rule.Pattern
/*     */ {
/*     */   private String text;
/*     */   private org.jaxen.pattern.Pattern pattern;
/*     */   private Context context;
/*     */ 
/*     */   public XPathPattern(org.jaxen.pattern.Pattern pattern)
/*     */   {
/*  45 */     this.pattern = pattern;
/*  46 */     this.text = pattern.getText();
/*  47 */     this.context = new Context(getContextSupport());
/*     */   }
/*     */ 
/*     */   public XPathPattern(String text) {
/*  51 */     this.text = text;
/*  52 */     this.context = new Context(getContextSupport());
/*     */     try
/*     */     {
/*  55 */       this.pattern = PatternParser.parse(text);
/*     */     } catch (SAXPathException e) {
/*  57 */       throw new InvalidXPathException(text, e.getMessage());
/*     */     } catch (Throwable t) {
/*  59 */       throw new InvalidXPathException(text, t);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean matches(Node node) {
/*     */     try {
/*  65 */       ArrayList list = new ArrayList(1);
/*  66 */       list.add(node);
/*  67 */       this.context.setNodeSet(list);
/*     */ 
/*  69 */       return this.pattern.matches(node, this.context);
/*     */     } catch (JaxenException e) {
/*  71 */       handleJaxenException(e);
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  78 */     return this.text;
/*     */   }
/*     */ 
/*     */   public double getPriority() {
/*  82 */     return this.pattern.getPriority();
/*     */   }
/*     */ 
/*     */   public org.dom4j.rule.Pattern[] getUnionPatterns() {
/*  86 */     org.jaxen.pattern.Pattern[] patterns = this.pattern.getUnionPatterns();
/*     */ 
/*  88 */     if (patterns != null) {
/*  89 */       int size = patterns.length;
/*  90 */       XPathPattern[] answer = new XPathPattern[size];
/*     */ 
/*  92 */       for (int i = 0; i < size; i++) {
/*  93 */         answer[i] = new XPathPattern(patterns[i]);
/*     */       }
/*     */ 
/*  96 */       return answer;
/*     */     }
/*     */ 
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */   public short getMatchType() {
/* 103 */     return this.pattern.getMatchType();
/*     */   }
/*     */ 
/*     */   public String getMatchesNodeName() {
/* 107 */     return this.pattern.getMatchesNodeName();
/*     */   }
/*     */ 
/*     */   public void setVariableContext(VariableContext variableContext) {
/* 111 */     this.context.getContextSupport().setVariableContext(variableContext);
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 115 */     return "[XPathPattern: text: " + this.text + " Pattern: " + this.pattern + "]";
/*     */   }
/*     */ 
/*     */   protected ContextSupport getContextSupport() {
/* 119 */     return new ContextSupport(new SimpleNamespaceContext(), XPathFunctionContext.getInstance(), new SimpleVariableContext(), DocumentNavigator.getInstance());
/*     */   }
/*     */ 
/*     */   protected void handleJaxenException(JaxenException exception)
/*     */     throws XPathException
/*     */   {
/* 126 */     throw new XPathException(this.text, exception);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.xpath.XPathPattern
 * JD-Core Version:    0.6.2
 */