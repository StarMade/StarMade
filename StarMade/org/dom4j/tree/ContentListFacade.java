/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.util.AbstractList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.dom4j.IllegalAddException;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class ContentListFacade extends AbstractList
/*     */ {
/*     */   private List branchContent;
/*     */   private AbstractBranch branch;
/*     */ 
/*     */   public ContentListFacade(AbstractBranch branch, List branchContent)
/*     */   {
/*  39 */     this.branch = branch;
/*  40 */     this.branchContent = branchContent;
/*     */   }
/*     */ 
/*     */   public boolean add(Object object) {
/*  44 */     this.branch.childAdded(asNode(object));
/*     */ 
/*  46 */     return this.branchContent.add(object);
/*     */   }
/*     */ 
/*     */   public void add(int index, Object object) {
/*  50 */     this.branch.childAdded(asNode(object));
/*  51 */     this.branchContent.add(index, object);
/*     */   }
/*     */ 
/*     */   public Object set(int index, Object object) {
/*  55 */     this.branch.childAdded(asNode(object));
/*     */ 
/*  57 */     return this.branchContent.set(index, object);
/*     */   }
/*     */ 
/*     */   public boolean remove(Object object) {
/*  61 */     this.branch.childRemoved(asNode(object));
/*     */ 
/*  63 */     return this.branchContent.remove(object);
/*     */   }
/*     */ 
/*     */   public Object remove(int index) {
/*  67 */     Object object = this.branchContent.remove(index);
/*     */ 
/*  69 */     if (object != null) {
/*  70 */       this.branch.childRemoved(asNode(object));
/*     */     }
/*     */ 
/*  73 */     return object;
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection collection) {
/*  77 */     int count = this.branchContent.size();
/*     */ 
/*  79 */     for (Iterator iter = collection.iterator(); iter.hasNext(); count++) {
/*  80 */       add(iter.next());
/*     */     }
/*     */ 
/*  83 */     return count == this.branchContent.size();
/*     */   }
/*     */ 
/*     */   public boolean addAll(int index, Collection collection) {
/*  87 */     int count = this.branchContent.size();
/*     */ 
/*  89 */     for (Iterator iter = collection.iterator(); iter.hasNext(); count--) {
/*  90 */       add(index++, iter.next());
/*     */     }
/*     */ 
/*  93 */     return count == this.branchContent.size();
/*     */   }
/*     */ 
/*     */   public void clear() {
/*  97 */     for (Iterator iter = iterator(); iter.hasNext(); ) {
/*  98 */       Object object = iter.next();
/*  99 */       this.branch.childRemoved(asNode(object));
/*     */     }
/*     */ 
/* 102 */     this.branchContent.clear();
/*     */   }
/*     */ 
/*     */   public boolean removeAll(Collection c) {
/* 106 */     for (Iterator iter = c.iterator(); iter.hasNext(); ) {
/* 107 */       Object object = iter.next();
/* 108 */       this.branch.childRemoved(asNode(object));
/*     */     }
/*     */ 
/* 111 */     return this.branchContent.removeAll(c);
/*     */   }
/*     */ 
/*     */   public int size() {
/* 115 */     return this.branchContent.size();
/*     */   }
/*     */ 
/*     */   public boolean isEmpty() {
/* 119 */     return this.branchContent.isEmpty();
/*     */   }
/*     */ 
/*     */   public boolean contains(Object o) {
/* 123 */     return this.branchContent.contains(o);
/*     */   }
/*     */ 
/*     */   public Object[] toArray() {
/* 127 */     return this.branchContent.toArray();
/*     */   }
/*     */ 
/*     */   public Object[] toArray(Object[] a) {
/* 131 */     return this.branchContent.toArray(a);
/*     */   }
/*     */ 
/*     */   public boolean containsAll(Collection c) {
/* 135 */     return this.branchContent.containsAll(c);
/*     */   }
/*     */ 
/*     */   public Object get(int index) {
/* 139 */     return this.branchContent.get(index);
/*     */   }
/*     */ 
/*     */   public int indexOf(Object o) {
/* 143 */     return this.branchContent.indexOf(o);
/*     */   }
/*     */ 
/*     */   public int lastIndexOf(Object o) {
/* 147 */     return this.branchContent.lastIndexOf(o);
/*     */   }
/*     */ 
/*     */   protected Node asNode(Object object) {
/* 151 */     if ((object instanceof Node)) {
/* 152 */       return (Node)object;
/*     */     }
/* 154 */     throw new IllegalAddException("This list must contain instances of Node. Invalid type: " + object);
/*     */   }
/*     */ 
/*     */   protected List getBackingList()
/*     */   {
/* 161 */     return this.branchContent;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.ContentListFacade
 * JD-Core Version:    0.6.2
 */