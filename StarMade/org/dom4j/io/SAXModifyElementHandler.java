/*     */ package org.dom4j.io;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.ElementHandler;
/*     */ import org.dom4j.ElementPath;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ class SAXModifyElementHandler
/*     */   implements ElementHandler
/*     */ {
/*     */   private ElementModifier elemModifier;
/*     */   private Element modifiedElement;
/*     */ 
/*     */   public SAXModifyElementHandler(ElementModifier elemModifier)
/*     */   {
/*  34 */     this.elemModifier = elemModifier;
/*     */   }
/*     */ 
/*     */   public void onStart(ElementPath elementPath) {
/*  38 */     this.modifiedElement = elementPath.getCurrent();
/*     */   }
/*     */ 
/*     */   public void onEnd(ElementPath elementPath) {
/*     */     try {
/*  43 */       Element origElement = elementPath.getCurrent();
/*  44 */       Element currentParent = origElement.getParent();
/*     */ 
/*  46 */       if (currentParent != null)
/*     */       {
/*  48 */         Element clonedElem = (Element)origElement.clone();
/*     */ 
/*  51 */         this.modifiedElement = this.elemModifier.modifyElement(clonedElem);
/*     */ 
/*  53 */         if (this.modifiedElement != null)
/*     */         {
/*  55 */           this.modifiedElement.setParent(origElement.getParent());
/*  56 */           this.modifiedElement.setDocument(origElement.getDocument());
/*     */ 
/*  59 */           int contentIndex = currentParent.indexOf(origElement);
/*  60 */           currentParent.content().set(contentIndex, this.modifiedElement);
/*     */         }
/*     */ 
/*  64 */         origElement.detach();
/*     */       }
/*  66 */       else if (origElement.isRootElement())
/*     */       {
/*  68 */         Element clonedElem = (Element)origElement.clone();
/*     */ 
/*  71 */         this.modifiedElement = this.elemModifier.modifyElement(clonedElem);
/*     */ 
/*  73 */         if (this.modifiedElement != null)
/*     */         {
/*  75 */           this.modifiedElement.setDocument(origElement.getDocument());
/*     */ 
/*  78 */           Document doc = origElement.getDocument();
/*  79 */           doc.setRootElement(this.modifiedElement);
/*     */         }
/*     */ 
/*  83 */         origElement.detach();
/*     */       }
/*     */ 
/*  89 */       if ((elementPath instanceof ElementStack)) {
/*  90 */         ElementStack elementStack = (ElementStack)elementPath;
/*  91 */         elementStack.popElement();
/*  92 */         elementStack.pushElement(this.modifiedElement);
/*     */       }
/*     */     } catch (Exception ex) {
/*  95 */       throw new SAXModifyException(ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected Element getModifiedElement()
/*     */   {
/* 105 */     return this.modifiedElement;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.io.SAXModifyElementHandler
 * JD-Core Version:    0.6.2
 */