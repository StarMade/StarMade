/*     */ package org.dom4j.util;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.dom4j.Attribute;
/*     */ import org.dom4j.Branch;
/*     */ import org.dom4j.CDATA;
/*     */ import org.dom4j.CharacterData;
/*     */ import org.dom4j.Comment;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentType;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Entity;
/*     */ import org.dom4j.Namespace;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.ProcessingInstruction;
/*     */ import org.dom4j.QName;
/*     */ import org.dom4j.Text;
/*     */ 
/*     */ public class NodeComparator
/*     */   implements Comparator
/*     */ {
/*     */   public int compare(Object o1, Object o2)
/*     */   {
/*  79 */     if (o1 == o2)
/*  80 */       return 0;
/*  81 */     if (o1 == null)
/*     */     {
/*  83 */       return -1;
/*  84 */     }if (o2 == null) {
/*  85 */       return 1;
/*     */     }
/*     */ 
/*  88 */     if ((o1 instanceof Node)) {
/*  89 */       if ((o2 instanceof Node)) {
/*  90 */         return compare((Node)o1, (Node)o2);
/*     */       }
/*     */ 
/*  93 */       return 1;
/*     */     }
/*     */ 
/*  96 */     if ((o2 instanceof Node))
/*     */     {
/*  98 */       return -1;
/*     */     }
/* 100 */     if ((o1 instanceof Comparable)) {
/* 101 */       Comparable c1 = (Comparable)o1;
/*     */ 
/* 103 */       return c1.compareTo(o2);
/*     */     }
/* 105 */     String name1 = o1.getClass().getName();
/* 106 */     String name2 = o2.getClass().getName();
/*     */ 
/* 108 */     return name1.compareTo(name2);
/*     */   }
/*     */ 
/*     */   public int compare(Node n1, Node n2)
/*     */   {
/* 115 */     int nodeType1 = n1.getNodeType();
/* 116 */     int nodeType2 = n2.getNodeType();
/* 117 */     int answer = nodeType1 - nodeType2;
/*     */ 
/* 119 */     if (answer != 0) {
/* 120 */       return answer;
/*     */     }
/* 122 */     switch (nodeType1) {
/*     */     case 1:
/* 124 */       return compare((Element)n1, (Element)n2);
/*     */     case 9:
/* 127 */       return compare((Document)n1, (Document)n2);
/*     */     case 2:
/* 130 */       return compare((Attribute)n1, (Attribute)n2);
/*     */     case 3:
/* 133 */       return compare((Text)n1, (Text)n2);
/*     */     case 4:
/* 136 */       return compare((CDATA)n1, (CDATA)n2);
/*     */     case 5:
/* 139 */       return compare((Entity)n1, (Entity)n2);
/*     */     case 7:
/* 142 */       return compare((ProcessingInstruction)n1, (ProcessingInstruction)n2);
/*     */     case 8:
/* 146 */       return compare((Comment)n1, (Comment)n2);
/*     */     case 10:
/* 149 */       return compare((DocumentType)n1, (DocumentType)n2);
/*     */     case 13:
/* 152 */       return compare((Namespace)n1, (Namespace)n2);
/*     */     case 6:
/*     */     case 11:
/* 155 */     case 12: } throw new RuntimeException("Invalid node types. node1: " + n1 + " and node2: " + n2);
/*     */   }
/*     */ 
/*     */   public int compare(Document n1, Document n2)
/*     */   {
/* 162 */     int answer = compare(n1.getDocType(), n2.getDocType());
/*     */ 
/* 164 */     if (answer == 0) {
/* 165 */       answer = compareContent(n1, n2);
/*     */     }
/*     */ 
/* 168 */     return answer;
/*     */   }
/*     */ 
/*     */   public int compare(Element n1, Element n2) {
/* 172 */     int answer = compare(n1.getQName(), n2.getQName());
/*     */ 
/* 174 */     if (answer == 0)
/*     */     {
/* 176 */       int c1 = n1.attributeCount();
/* 177 */       int c2 = n2.attributeCount();
/* 178 */       answer = c1 - c2;
/*     */ 
/* 180 */       if (answer == 0) {
/* 181 */         for (int i = 0; i < c1; i++) {
/* 182 */           Attribute a1 = n1.attribute(i);
/* 183 */           Attribute a2 = n2.attribute(a1.getQName());
/* 184 */           answer = compare(a1, a2);
/*     */ 
/* 186 */           if (answer != 0) {
/* 187 */             return answer;
/*     */           }
/*     */         }
/*     */ 
/* 191 */         answer = compareContent(n1, n2);
/*     */       }
/*     */     }
/*     */ 
/* 195 */     return answer;
/*     */   }
/*     */ 
/*     */   public int compare(Attribute n1, Attribute n2) {
/* 199 */     int answer = compare(n1.getQName(), n2.getQName());
/*     */ 
/* 201 */     if (answer == 0) {
/* 202 */       answer = compare(n1.getValue(), n2.getValue());
/*     */     }
/*     */ 
/* 205 */     return answer;
/*     */   }
/*     */ 
/*     */   public int compare(QName n1, QName n2) {
/* 209 */     int answer = compare(n1.getNamespaceURI(), n2.getNamespaceURI());
/*     */ 
/* 211 */     if (answer == 0) {
/* 212 */       answer = compare(n1.getQualifiedName(), n2.getQualifiedName());
/*     */     }
/*     */ 
/* 215 */     return answer;
/*     */   }
/*     */ 
/*     */   public int compare(Namespace n1, Namespace n2) {
/* 219 */     int answer = compare(n1.getURI(), n2.getURI());
/*     */ 
/* 221 */     if (answer == 0) {
/* 222 */       answer = compare(n1.getPrefix(), n2.getPrefix());
/*     */     }
/*     */ 
/* 225 */     return answer;
/*     */   }
/*     */ 
/*     */   public int compare(CharacterData t1, CharacterData t2) {
/* 229 */     return compare(t1.getText(), t2.getText());
/*     */   }
/*     */ 
/*     */   public int compare(DocumentType o1, DocumentType o2) {
/* 233 */     if (o1 == o2)
/* 234 */       return 0;
/* 235 */     if (o1 == null)
/*     */     {
/* 237 */       return -1;
/* 238 */     }if (o2 == null) {
/* 239 */       return 1;
/*     */     }
/*     */ 
/* 242 */     int answer = compare(o1.getPublicID(), o2.getPublicID());
/*     */ 
/* 244 */     if (answer == 0) {
/* 245 */       answer = compare(o1.getSystemID(), o2.getSystemID());
/*     */ 
/* 247 */       if (answer == 0) {
/* 248 */         answer = compare(o1.getName(), o2.getName());
/*     */       }
/*     */     }
/*     */ 
/* 252 */     return answer;
/*     */   }
/*     */ 
/*     */   public int compare(Entity n1, Entity n2) {
/* 256 */     int answer = compare(n1.getName(), n2.getName());
/*     */ 
/* 258 */     if (answer == 0) {
/* 259 */       answer = compare(n1.getText(), n2.getText());
/*     */     }
/*     */ 
/* 262 */     return answer;
/*     */   }
/*     */ 
/*     */   public int compare(ProcessingInstruction n1, ProcessingInstruction n2) {
/* 266 */     int answer = compare(n1.getTarget(), n2.getTarget());
/*     */ 
/* 268 */     if (answer == 0) {
/* 269 */       answer = compare(n1.getText(), n2.getText());
/*     */     }
/*     */ 
/* 272 */     return answer;
/*     */   }
/*     */ 
/*     */   public int compareContent(Branch b1, Branch b2) {
/* 276 */     int c1 = b1.nodeCount();
/* 277 */     int c2 = b2.nodeCount();
/* 278 */     int answer = c1 - c2;
/*     */ 
/* 280 */     if (answer == 0) {
/* 281 */       for (int i = 0; i < c1; i++) {
/* 282 */         Node n1 = b1.node(i);
/* 283 */         Node n2 = b2.node(i);
/* 284 */         answer = compare(n1, n2);
/*     */ 
/* 286 */         if (answer != 0)
/*     */         {
/*     */           break;
/*     */         }
/*     */       }
/*     */     }
/* 292 */     return answer;
/*     */   }
/*     */ 
/*     */   public int compare(String o1, String o2) {
/* 296 */     if (o1 == o2)
/* 297 */       return 0;
/* 298 */     if (o1 == null)
/*     */     {
/* 300 */       return -1;
/* 301 */     }if (o2 == null) {
/* 302 */       return 1;
/*     */     }
/*     */ 
/* 305 */     return o1.compareTo(o2);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.NodeComparator
 * JD-Core Version:    0.6.2
 */