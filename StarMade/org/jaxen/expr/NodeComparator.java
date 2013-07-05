/*     */ package org.jaxen.expr;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import org.jaxen.Navigator;
/*     */ import org.jaxen.UnsupportedAxisException;
/*     */ 
/*     */ class NodeComparator
/*     */   implements Comparator
/*     */ {
/*     */   private Navigator navigator;
/*     */ 
/*     */   NodeComparator(Navigator navigator)
/*     */   {
/*  62 */     this.navigator = navigator;
/*     */   }
/*     */ 
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/*  67 */     if (o1 == o2) return 0;
/*     */ 
/*  70 */     if (this.navigator == null) return 0;
/*     */ 
/*  72 */     if ((isNonChild(o1)) && (isNonChild(o2))) {
/*     */       try
/*     */       {
/*  75 */         Object p1 = this.navigator.getParentNode(o1);
/*  76 */         Object p2 = this.navigator.getParentNode(o2);
/*     */ 
/*  78 */         if (p1 == p2) {
/*  79 */           if ((this.navigator.isNamespace(o1)) && (this.navigator.isAttribute(o2))) {
/*  80 */             return -1;
/*     */           }
/*  82 */           if ((this.navigator.isNamespace(o2)) && (this.navigator.isAttribute(o1))) {
/*  83 */             return 1;
/*     */           }
/*  85 */           if (this.navigator.isNamespace(o1)) {
/*  86 */             String prefix1 = this.navigator.getNamespacePrefix(o1);
/*  87 */             String prefix2 = this.navigator.getNamespacePrefix(o2);
/*  88 */             return prefix1.compareTo(prefix2);
/*     */           }
/*  90 */           if (this.navigator.isAttribute(o1)) {
/*  91 */             String name1 = this.navigator.getAttributeQName(o1);
/*  92 */             String name2 = this.navigator.getAttributeQName(o2);
/*  93 */             return name1.compareTo(name2);
/*     */           }
/*     */         }
/*     */ 
/*  97 */         return compare(p1, p2);
/*     */       }
/*     */       catch (UnsupportedAxisException ex) {
/* 100 */         return 0;
/*     */       }
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 106 */       int depth1 = getDepth(o1);
/* 107 */       int depth2 = getDepth(o2);
/*     */ 
/* 109 */       Object a1 = o1;
/* 110 */       Object a2 = o2;
/*     */ 
/* 112 */       while (depth1 > depth2) {
/* 113 */         a1 = this.navigator.getParentNode(a1);
/* 114 */         depth1--;
/*     */       }
/* 116 */       if (a1 == o2) return 1;
/*     */ 
/* 118 */       while (depth2 > depth1) {
/* 119 */         a2 = this.navigator.getParentNode(a2);
/* 120 */         depth2--;
/*     */       }
/* 122 */       if (a2 == o1) return -1;
/*     */ 
/*     */       while (true)
/*     */       {
/* 126 */         Object p1 = this.navigator.getParentNode(a1);
/* 127 */         Object p2 = this.navigator.getParentNode(a2);
/* 128 */         if (p1 == p2) {
/* 129 */           return compareSiblings(a1, a2);
/*     */         }
/* 131 */         a1 = p1;
/* 132 */         a2 = p2;
/*     */       }
/*     */     }
/*     */     catch (UnsupportedAxisException ex) {
/*     */     }
/* 137 */     return 0;
/*     */   }
/*     */ 
/*     */   private boolean isNonChild(Object o)
/*     */   {
/* 143 */     return (this.navigator.isAttribute(o)) || (this.navigator.isNamespace(o));
/*     */   }
/*     */ 
/*     */   private int compareSiblings(Object sib1, Object sib2)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 150 */     if (isNonChild(sib1))
/* 151 */       return 1;
/* 152 */     if (isNonChild(sib2)) {
/* 153 */       return -1;
/*     */     }
/*     */ 
/* 156 */     Iterator following = this.navigator.getFollowingSiblingAxisIterator(sib1);
/* 157 */     while (following.hasNext()) {
/* 158 */       Object next = following.next();
/* 159 */       if (next.equals(sib2)) return -1;
/*     */     }
/* 161 */     return 1;
/*     */   }
/*     */ 
/*     */   private int getDepth(Object o)
/*     */     throws UnsupportedAxisException
/*     */   {
/* 167 */     int depth = 0;
/* 168 */     Object parent = o;
/*     */ 
/* 170 */     while ((parent = this.navigator.getParentNode(parent)) != null) {
/* 171 */       depth++;
/*     */     }
/* 173 */     return depth;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.NodeComparator
 * JD-Core Version:    0.6.2
 */