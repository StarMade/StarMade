/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.dom4j.Element;
/*     */ 
/*     */ public class DefaultProcessingInstruction extends FlyweightProcessingInstruction
/*     */ {
/*     */   private Element parent;
/*     */ 
/*     */   public DefaultProcessingInstruction(String target, Map values)
/*     */   {
/*  40 */     super(target, values);
/*     */   }
/*     */ 
/*     */   public DefaultProcessingInstruction(String target, String values)
/*     */   {
/*  54 */     super(target, values);
/*     */   }
/*     */ 
/*     */   public DefaultProcessingInstruction(Element parent, String target, String values)
/*     */   {
/*  71 */     super(target, values);
/*  72 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   public void setTarget(String target) {
/*  76 */     this.target = target;
/*     */   }
/*     */ 
/*     */   public void setText(String text) {
/*  80 */     this.text = text;
/*  81 */     this.values = parseValues(text);
/*     */   }
/*     */ 
/*     */   public void setValues(Map values) {
/*  85 */     this.values = values;
/*  86 */     this.text = toString(values);
/*     */   }
/*     */ 
/*     */   public void setValue(String name, String value) {
/*  90 */     this.values.put(name, value);
/*     */   }
/*     */ 
/*     */   public Element getParent() {
/*  94 */     return this.parent;
/*     */   }
/*     */ 
/*     */   public void setParent(Element parent) {
/*  98 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */   public boolean supportsParent() {
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isReadOnly() {
/* 106 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.DefaultProcessingInstruction
 * JD-Core Version:    0.6.2
 */