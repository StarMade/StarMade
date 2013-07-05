/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class FlyweightProcessingInstruction extends AbstractProcessingInstruction
/*     */ {
/*     */   protected String target;
/*     */   protected String text;
/*     */   protected Map values;
/*     */ 
/*     */   public FlyweightProcessingInstruction()
/*     */   {
/*     */   }
/*     */ 
/*     */   public FlyweightProcessingInstruction(String target, Map values)
/*     */   {
/*  58 */     this.target = target;
/*  59 */     this.values = values;
/*  60 */     this.text = toString(values);
/*     */   }
/*     */ 
/*     */   public FlyweightProcessingInstruction(String target, String text)
/*     */   {
/*  74 */     this.target = target;
/*  75 */     this.text = text;
/*  76 */     this.values = parseValues(text);
/*     */   }
/*     */ 
/*     */   public String getTarget() {
/*  80 */     return this.target;
/*     */   }
/*     */ 
/*     */   public void setTarget(String target) {
/*  84 */     throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  89 */     return this.text;
/*     */   }
/*     */ 
/*     */   public String getValue(String name) {
/*  93 */     String answer = (String)this.values.get(name);
/*     */ 
/*  95 */     if (answer == null) {
/*  96 */       return "";
/*     */     }
/*     */ 
/*  99 */     return answer;
/*     */   }
/*     */ 
/*     */   public Map getValues() {
/* 103 */     return Collections.unmodifiableMap(this.values);
/*     */   }
/*     */ 
/*     */   protected Node createXPathResult(Element parent) {
/* 107 */     return new DefaultProcessingInstruction(parent, getTarget(), getText());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FlyweightProcessingInstruction
 * JD-Core Version:    0.6.2
 */