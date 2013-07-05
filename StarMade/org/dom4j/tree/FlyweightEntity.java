/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class FlyweightEntity extends AbstractEntity
/*     */ {
/*     */   protected String name;
/*     */   protected String text;
/*     */ 
/*     */   protected FlyweightEntity()
/*     */   {
/*     */   }
/*     */ 
/*     */   public FlyweightEntity(String name)
/*     */   {
/*  53 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public FlyweightEntity(String name, String text)
/*     */   {
/*  65 */     this.name = name;
/*  66 */     this.text = text;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  75 */     return this.name;
/*     */   }
/*     */ 
/*     */   public String getText()
/*     */   {
/*  84 */     return this.text;
/*     */   }
/*     */ 
/*     */   public void setText(String text)
/*     */   {
/*  99 */     if (this.text != null)
/* 100 */       this.text = text;
/*     */     else
/* 102 */       throw new UnsupportedOperationException("This Entity is read-only. It cannot be modified");
/*     */   }
/*     */ 
/*     */   protected Node createXPathResult(Element parent)
/*     */   {
/* 108 */     return new DefaultEntity(parent, getName(), getText());
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.FlyweightEntity
 * JD-Core Version:    0.6.2
 */