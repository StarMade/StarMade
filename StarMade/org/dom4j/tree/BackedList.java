/*     */ package org.dom4j.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.dom4j.IllegalAddException;
/*     */ import org.dom4j.Node;
/*     */ 
/*     */ public class BackedList extends ArrayList
/*     */ {
/*     */   private List branchContent;
/*     */   private AbstractBranch branch;
/*     */ 
/*     */   public BackedList(AbstractBranch branch, List branchContent)
/*     */   {
/*  36 */     this(branch, branchContent, branchContent.size());
/*     */   }
/*     */ 
/*     */   public BackedList(AbstractBranch branch, List branchContent, int capacity) {
/*  40 */     super(capacity);
/*  41 */     this.branch = branch;
/*  42 */     this.branchContent = branchContent;
/*     */   }
/*     */ 
/*     */   public BackedList(AbstractBranch branch, List branchContent, List initialContent)
/*     */   {
/*  47 */     super(initialContent);
/*  48 */     this.branch = branch;
/*  49 */     this.branchContent = branchContent;
/*     */   }
/*     */ 
/*     */   public boolean add(Object object) {
/*  53 */     this.branch.addNode(asNode(object));
/*     */ 
/*  55 */     return super.add(object);
/*     */   }
/*     */ 
/*     */   public void add(int index, Object object) {
/*  59 */     int size = size();
/*     */ 
/*  61 */     if (index < 0) {
/*  62 */       throw new IndexOutOfBoundsException("Index value: " + index + " is less than zero");
/*     */     }
/*  64 */     if (index > size)
/*  65 */       throw new IndexOutOfBoundsException("Index value: " + index + " cannot be greater than " + "the size: " + size);
/*     */     int realIndex;
/*     */     int realIndex;
/*  71 */     if (size == 0) {
/*  72 */       realIndex = this.branchContent.size();
/*     */     }
/*     */     else
/*     */     {
/*     */       int realIndex;
/*  73 */       if (index < size)
/*  74 */         realIndex = this.branchContent.indexOf(get(index));
/*     */       else {
/*  76 */         realIndex = this.branchContent.indexOf(get(size - 1)) + 1;
/*     */       }
/*     */     }
/*  79 */     this.branch.addNode(realIndex, asNode(object));
/*  80 */     super.add(index, object);
/*     */   }
/*     */ 
/*     */   public Object set(int index, Object object) {
/*  84 */     int realIndex = this.branchContent.indexOf(get(index));
/*     */ 
/*  86 */     if (realIndex < 0) {
/*  87 */       realIndex = index == 0 ? 0 : 2147483647;
/*     */     }
/*     */ 
/*  90 */     if (realIndex < this.branchContent.size()) {
/*  91 */       this.branch.removeNode(asNode(get(index)));
/*  92 */       this.branch.addNode(realIndex, asNode(object));
/*     */     } else {
/*  94 */       this.branch.removeNode(asNode(get(index)));
/*  95 */       this.branch.addNode(asNode(object));
/*     */     }
/*     */ 
/*  98 */     this.branch.childAdded(asNode(object));
/*     */ 
/* 100 */     return super.set(index, object);
/*     */   }
/*     */ 
/*     */   public boolean remove(Object object) {
/* 104 */     this.branch.removeNode(asNode(object));
/*     */ 
/* 106 */     return super.remove(object);
/*     */   }
/*     */ 
/*     */   public Object remove(int index) {
/* 110 */     Object object = super.remove(index);
/*     */ 
/* 112 */     if (object != null) {
/* 113 */       this.branch.removeNode(asNode(object));
/*     */     }
/*     */ 
/* 116 */     return object;
/*     */   }
/*     */ 
/*     */   public boolean addAll(Collection collection) {
/* 120 */     ensureCapacity(size() + collection.size());
/*     */ 
/* 122 */     int count = size();
/*     */ 
/* 124 */     for (Iterator iter = collection.iterator(); iter.hasNext(); count--) {
/* 125 */       add(iter.next());
/*     */     }
/*     */ 
/* 128 */     return count != 0;
/*     */   }
/*     */ 
/*     */   public boolean addAll(int index, Collection collection) {
/* 132 */     ensureCapacity(size() + collection.size());
/*     */ 
/* 134 */     int count = size();
/*     */ 
/* 136 */     for (Iterator iter = collection.iterator(); iter.hasNext(); count--) {
/* 137 */       add(index++, iter.next());
/*     */     }
/*     */ 
/* 140 */     return count != 0;
/*     */   }
/*     */ 
/*     */   public void clear() {
/* 144 */     for (Iterator iter = iterator(); iter.hasNext(); ) {
/* 145 */       Object object = iter.next();
/* 146 */       this.branchContent.remove(object);
/* 147 */       this.branch.childRemoved(asNode(object));
/*     */     }
/*     */ 
/* 150 */     super.clear();
/*     */   }
/*     */ 
/*     */   public void addLocal(Object object)
/*     */   {
/* 161 */     super.add(object);
/*     */   }
/*     */ 
/*     */   protected Node asNode(Object object) {
/* 165 */     if ((object instanceof Node)) {
/* 166 */       return (Node)object;
/*     */     }
/* 168 */     throw new IllegalAddException("This list must contain instances of Node. Invalid type: " + object);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.tree.BackedList
 * JD-Core Version:    0.6.2
 */