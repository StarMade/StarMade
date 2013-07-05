/*     */ package org.dom4j.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.tree.BackedList;
/*     */ import org.dom4j.tree.DefaultElement;
/*     */ 
/*     */ public class IndexedElement extends DefaultElement
/*     */ {
/*     */   private Map elementIndex;
/*     */   private Map attributeIndex;
/*     */ 
/*     */   public IndexedElement(String name)
/*     */   {
/*  41 */     super(name);
/*     */   }
/*     */ 
/*     */   public IndexedElement(QName qname) {
/*  45 */     super(qname);
/*     */   }
/*     */ 
/*     */   public IndexedElement(QName qname, int attributeCount) {
/*  49 */     super(qname, attributeCount);
/*     */   }
/*     */ 
/*     */   public Attribute attribute(String name) {
/*  53 */     return (Attribute)attributeIndex().get(name);
/*     */   }
/*     */ 
/*     */   public Attribute attribute(QName qName) {
/*  57 */     return (Attribute)attributeIndex().get(qName);
/*     */   }
/*     */ 
/*     */   public Element element(String name) {
/*  61 */     return asElement(elementIndex().get(name));
/*     */   }
/*     */ 
/*     */   public Element element(QName qName) {
/*  65 */     return asElement(elementIndex().get(qName));
/*     */   }
/*     */ 
/*     */   public List elements(String name) {
/*  69 */     return asElementList(elementIndex().get(name));
/*     */   }
/*     */ 
/*     */   public List elements(QName qName) {
/*  73 */     return asElementList(elementIndex().get(qName));
/*     */   }
/*     */ 
/*     */   protected Element asElement(Object object)
/*     */   {
/*  79 */     if ((object instanceof Element))
/*  80 */       return (Element)object;
/*  81 */     if (object != null) {
/*  82 */       List list = (List)object;
/*     */ 
/*  84 */       if (list.size() >= 1) {
/*  85 */         return (Element)list.get(0);
/*     */       }
/*     */     }
/*     */ 
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */   protected List asElementList(Object object) {
/*  93 */     if ((object instanceof Element))
/*  94 */       return createSingleResultList(object);
/*  95 */     if (object != null) {
/*  96 */       List list = (List)object;
/*  97 */       BackedList answer = createResultList();
/*     */ 
/*  99 */       int i = 0; for (int size = list.size(); i < size; i++) {
/* 100 */         answer.addLocal(list.get(i));
/*     */       }
/*     */ 
/* 103 */       return answer;
/*     */     }
/*     */ 
/* 106 */     return createEmptyList();
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   protected Iterator asElementIterator(Object object)
/*     */   {
/* 120 */     return asElementList(object).iterator();
/*     */   }
/*     */ 
/*     */   protected void addNode(Node node)
/*     */   {
/* 125 */     super.addNode(node);
/*     */ 
/* 127 */     if ((this.elementIndex != null) && ((node instanceof Element)))
/* 128 */       addToElementIndex((Element)node);
/* 129 */     else if ((this.attributeIndex != null) && ((node instanceof Attribute)))
/* 130 */       addToAttributeIndex((Attribute)node);
/*     */   }
/*     */ 
/*     */   protected boolean removeNode(Node node)
/*     */   {
/* 135 */     if (super.removeNode(node)) {
/* 136 */       if ((this.elementIndex != null) && ((node instanceof Element)))
/* 137 */         removeFromElementIndex((Element)node);
/* 138 */       else if ((this.attributeIndex != null) && ((node instanceof Attribute))) {
/* 139 */         removeFromAttributeIndex((Attribute)node);
/*     */       }
/*     */ 
/* 142 */       return true;
/*     */     }
/*     */ 
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */   protected Map attributeIndex()
/*     */   {
/*     */     Iterator iter;
/* 149 */     if (this.attributeIndex == null) {
/* 150 */       this.attributeIndex = createAttributeIndex();
/*     */ 
/* 152 */       for (iter = attributeIterator(); iter.hasNext(); ) {
/* 153 */         addToAttributeIndex((Attribute)iter.next());
/*     */       }
/*     */     }
/*     */ 
/* 157 */     return this.attributeIndex;
/*     */   }
/*     */ 
/*     */   protected Map elementIndex()
/*     */   {
/*     */     Iterator iter;
/* 161 */     if (this.elementIndex == null) {
/* 162 */       this.elementIndex = createElementIndex();
/*     */ 
/* 164 */       for (iter = elementIterator(); iter.hasNext(); ) {
/* 165 */         addToElementIndex((Element)iter.next());
/*     */       }
/*     */     }
/*     */ 
/* 169 */     return this.elementIndex;
/*     */   }
/*     */ 
/*     */   protected Map createAttributeIndex()
/*     */   {
/* 178 */     Map answer = createIndex();
/*     */ 
/* 180 */     return answer;
/*     */   }
/*     */ 
/*     */   protected Map createElementIndex()
/*     */   {
/* 189 */     Map answer = createIndex();
/*     */ 
/* 191 */     return answer;
/*     */   }
/*     */ 
/*     */   protected void addToElementIndex(Element element) {
/* 195 */     QName qName = element.getQName();
/* 196 */     String name = qName.getName();
/* 197 */     addToElementIndex(qName, element);
/* 198 */     addToElementIndex(name, element);
/*     */   }
/*     */ 
/*     */   protected void addToElementIndex(Object key, Element value) {
/* 202 */     Object oldValue = this.elementIndex.get(key);
/*     */ 
/* 204 */     if (oldValue == null) {
/* 205 */       this.elementIndex.put(key, value);
/*     */     }
/* 207 */     else if ((oldValue instanceof List)) {
/* 208 */       List list = (List)oldValue;
/* 209 */       list.add(value);
/*     */     } else {
/* 211 */       List list = createList();
/* 212 */       list.add(oldValue);
/* 213 */       list.add(value);
/* 214 */       this.elementIndex.put(key, list);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void removeFromElementIndex(Element element)
/*     */   {
/* 220 */     QName qName = element.getQName();
/* 221 */     String name = qName.getName();
/* 222 */     removeFromElementIndex(qName, element);
/* 223 */     removeFromElementIndex(name, element);
/*     */   }
/*     */ 
/*     */   protected void removeFromElementIndex(Object key, Element value) {
/* 227 */     Object oldValue = this.elementIndex.get(key);
/*     */ 
/* 229 */     if ((oldValue instanceof List)) {
/* 230 */       List list = (List)oldValue;
/* 231 */       list.remove(value);
/*     */     } else {
/* 233 */       this.elementIndex.remove(key);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void addToAttributeIndex(Attribute attribute) {
/* 238 */     QName qName = attribute.getQName();
/* 239 */     String name = qName.getName();
/* 240 */     addToAttributeIndex(qName, attribute);
/* 241 */     addToAttributeIndex(name, attribute);
/*     */   }
/*     */ 
/*     */   protected void addToAttributeIndex(Object key, Attribute value) {
/* 245 */     Object oldValue = this.attributeIndex.get(key);
/*     */ 
/* 247 */     if (oldValue != null)
/* 248 */       this.attributeIndex.put(key, value);
/*     */   }
/*     */ 
/*     */   protected void removeFromAttributeIndex(Attribute attribute)
/*     */   {
/* 253 */     QName qName = attribute.getQName();
/* 254 */     String name = qName.getName();
/* 255 */     removeFromAttributeIndex(qName, attribute);
/* 256 */     removeFromAttributeIndex(name, attribute);
/*     */   }
/*     */ 
/*     */   protected void removeFromAttributeIndex(Object key, Attribute value) {
/* 260 */     Object oldValue = this.attributeIndex.get(key);
/*     */ 
/* 262 */     if ((oldValue != null) && (oldValue.equals(value)))
/* 263 */       this.attributeIndex.remove(key);
/*     */   }
/*     */ 
/*     */   protected Map createIndex()
/*     */   {
/* 273 */     return new HashMap();
/*     */   }
/*     */ 
/*     */   protected List createList()
/*     */   {
/* 282 */     return new ArrayList();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.IndexedElement
 * JD-Core Version:    0.6.2
 */