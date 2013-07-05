/*    */ package org.schema.game.common.data.element;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class ElementCategory
/*    */ {
/*    */   private ElementCategory parent;
/*    */   private final Class category;
/*  8 */   private final ArrayList children = new ArrayList();
/*  9 */   private final ArrayList infoElements = new ArrayList();
/*    */ 
/*    */   public ElementCategory(Class paramClass, ElementCategory paramElementCategory) {
/* 12 */     this.category = paramClass;
/* 13 */     this.parent = paramElementCategory;
/*    */   }
/*    */ 
/*    */   public Class getCategory()
/*    */   {
/* 23 */     return this.category;
/*    */   }
/*    */ 
/*    */   public ArrayList getChildren()
/*    */   {
/* 29 */     return this.children;
/*    */   }
/*    */ 
/*    */   public ArrayList getInfoElements()
/*    */   {
/* 36 */     return this.infoElements;
/*    */   }
/*    */ 
/*    */   public boolean hasChildren() {
/* 40 */     return !getChildren().isEmpty();
/*    */   }
/*    */ 
/*    */   public boolean isRoot() {
/* 44 */     return this.parent == null;
/*    */   }
/*    */ 
/*    */   public void print() {
/* 48 */     printRec(1);
/*    */   }
/*    */   private void printItems(int paramInt) {
/* 51 */     StringBuilder localStringBuilder = new StringBuilder();
/* 52 */     for (int i = 0; i < paramInt; i++) {
/* 53 */       localStringBuilder.append("-");
/*    */     }
/* 55 */     for (i = 0; i < getInfoElements().size(); i++) {
/* 56 */       paramInt = (ElementInformation)getInfoElements().get(i);
/* 57 */       System.err.println(localStringBuilder.toString() + " " + paramInt.getName());
/*    */     }
/*    */   }
/*    */ 
/* 61 */   private void printRec(int paramInt) { StringBuilder localStringBuilder = new StringBuilder();
/* 62 */     for (int i = 0; i < paramInt; i++) {
/* 63 */       localStringBuilder.append("#");
/*    */     }
/* 65 */     System.err.println(localStringBuilder.toString() + " " + this.category.getSimpleName());
/* 66 */     printItems(paramInt);
/* 67 */     for (i = 0; i < this.children.size(); i++)
/* 68 */       ((ElementCategory)this.children.get(i)).printRec(paramInt + 1);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 73 */     return this.category.getSimpleName();
/*    */   }
/*    */ 
/*    */   public void clear() {
/* 77 */     this.children.clear();
/* 78 */     this.infoElements.clear();
/*    */   }
/*    */ 
/*    */   public void insertRecusrive(ElementInformation paramElementInformation)
/*    */   {
/* 83 */     if (this.category == paramElementInformation.getType()) {
/* 84 */       this.infoElements.add(paramElementInformation); return;
/*    */     }
/* 86 */     for (Iterator localIterator = getChildren().iterator(); localIterator.hasNext(); ) ((ElementCategory)localIterator.next())
/* 87 */         .insertRecusrive(paramElementInformation);
/*    */   }
/*    */ 
/*    */   public void removeRecursive(ElementInformation paramElementInformation)
/*    */   {
/* 93 */     if (this.infoElements.contains(paramElementInformation)) {
/* 94 */       this.infoElements.remove(paramElementInformation);
/* 95 */       System.err.println("REMOVED FROM CATEGORY: " + paramElementInformation.getName()); return;
/*    */     }
/* 97 */     for (Iterator localIterator = getChildren().iterator(); localIterator.hasNext(); ) ((ElementCategory)localIterator.next())
/* 98 */         .removeRecursive(paramElementInformation);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementCategory
 * JD-Core Version:    0.6.2
 */