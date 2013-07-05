/*     */ package it.unimi.dsi.fastutil.chars;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.AbstractObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectCollection;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public abstract class AbstractChar2ObjectSortedMap<V> extends AbstractChar2ObjectMap<V>
/*     */   implements Char2ObjectSortedMap<V>
/*     */ {
/*     */   public static final long serialVersionUID = -1773560792952436569L;
/*     */ 
/*     */   public Char2ObjectSortedMap<V> headMap(Character to)
/*     */   {
/*  58 */     return headMap(to.charValue());
/*     */   }
/*     */ 
/*     */   public Char2ObjectSortedMap<V> tailMap(Character from) {
/*  62 */     return tailMap(from.charValue());
/*     */   }
/*     */ 
/*     */   public Char2ObjectSortedMap<V> subMap(Character from, Character to) {
/*  66 */     return subMap(from.charValue(), to.charValue());
/*     */   }
/*     */ 
/*     */   public Character firstKey() {
/*  70 */     return Character.valueOf(firstCharKey());
/*     */   }
/*     */ 
/*     */   public Character lastKey() {
/*  74 */     return Character.valueOf(lastCharKey());
/*     */   }
/*     */ 
/*     */   public CharSortedSet keySet()
/*     */   {
/*  88 */     return new KeySet();
/*     */   }
/*     */ 
/*     */   public ObjectCollection<V> values()
/*     */   {
/* 143 */     return new ValuesCollection();
/*     */   }
/*     */ 
/*     */   public ObjectSortedSet<Map.Entry<Character, V>> entrySet()
/*     */   {
/* 174 */     return char2ObjectEntrySet();
/*     */   }
/*     */ 
/*     */   protected static class ValuesIterator<V> extends AbstractObjectIterator<V>
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Character, V>> i;
/*     */ 
/*     */     public ValuesIterator(ObjectBidirectionalIterator<Map.Entry<Character, V>> i)
/*     */     {
/* 165 */       this.i = i;
/*     */     }
/*     */     public V next() {
/* 168 */       return ((Map.Entry)this.i.next()).getValue(); } 
/* 169 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class ValuesCollection extends AbstractObjectCollection<V>
/*     */   {
/*     */     protected ValuesCollection()
/*     */     {
/*     */     }
/*     */ 
/*     */     public ObjectIterator<V> iterator()
/*     */     {
/* 148 */       return new AbstractChar2ObjectSortedMap.ValuesIterator(AbstractChar2ObjectSortedMap.this.entrySet().iterator()); } 
/* 149 */     public boolean contains(Object k) { return AbstractChar2ObjectSortedMap.this.containsValue(k); } 
/* 150 */     public int size() { return AbstractChar2ObjectSortedMap.this.size(); } 
/* 151 */     public void clear() { AbstractChar2ObjectSortedMap.this.clear(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected static class KeySetIterator<V> extends AbstractCharBidirectionalIterator
/*     */   {
/*     */     protected final ObjectBidirectionalIterator<Map.Entry<Character, V>> i;
/*     */ 
/*     */     public KeySetIterator(ObjectBidirectionalIterator<Map.Entry<Character, V>> i)
/*     */     {
/* 118 */       this.i = i;
/*     */     }
/*     */     public char nextChar() {
/* 121 */       return ((Character)((Map.Entry)this.i.next()).getKey()).charValue(); } 
/* 122 */     public char previousChar() { return ((Character)((Map.Entry)this.i.previous()).getKey()).charValue(); } 
/*     */     public boolean hasNext() {
/* 124 */       return this.i.hasNext(); } 
/* 125 */     public boolean hasPrevious() { return this.i.hasPrevious(); }
/*     */ 
/*     */   }
/*     */ 
/*     */   protected class KeySet extends AbstractCharSortedSet
/*     */   {
/*     */     protected KeySet()
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean contains(char k)
/*     */     {
/*  92 */       return AbstractChar2ObjectSortedMap.this.containsKey(k); } 
/*  93 */     public int size() { return AbstractChar2ObjectSortedMap.this.size(); } 
/*  94 */     public void clear() { AbstractChar2ObjectSortedMap.this.clear(); } 
/*  95 */     public CharComparator comparator() { return AbstractChar2ObjectSortedMap.this.comparator(); } 
/*  96 */     public char firstChar() { return AbstractChar2ObjectSortedMap.this.firstCharKey(); } 
/*  97 */     public char lastChar() { return AbstractChar2ObjectSortedMap.this.lastCharKey(); } 
/*     */     public CharSortedSet headSet(char to) {
/*  99 */       return AbstractChar2ObjectSortedMap.this.headMap(to).keySet(); } 
/* 100 */     public CharSortedSet tailSet(char from) { return AbstractChar2ObjectSortedMap.this.tailMap(from).keySet(); } 
/* 101 */     public CharSortedSet subSet(char from, char to) { return AbstractChar2ObjectSortedMap.this.subMap(from, to).keySet(); } 
/*     */     public CharBidirectionalIterator iterator(char from) {
/* 103 */       return new AbstractChar2ObjectSortedMap.KeySetIterator(AbstractChar2ObjectSortedMap.this.entrySet().iterator(new AbstractChar2ObjectMap.BasicEntry(from, null))); } 
/* 104 */     public CharBidirectionalIterator iterator() { return new AbstractChar2ObjectSortedMap.KeySetIterator(AbstractChar2ObjectSortedMap.this.entrySet().iterator()); }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.chars.AbstractChar2ObjectSortedMap
 * JD-Core Version:    0.6.2
 */