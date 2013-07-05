/*    */ package org.newdawn.slick.util.xml;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ 
/*    */ public class XMLElementList
/*    */ {
/* 13 */   private ArrayList list = new ArrayList();
/*    */ 
/*    */   public void add(XMLElement element)
/*    */   {
/* 28 */     this.list.add(element);
/*    */   }
/*    */ 
/*    */   public int size()
/*    */   {
/* 37 */     return this.list.size();
/*    */   }
/*    */ 
/*    */   public XMLElement get(int i)
/*    */   {
/* 47 */     return (XMLElement)this.list.get(i);
/*    */   }
/*    */ 
/*    */   public boolean contains(XMLElement element)
/*    */   {
/* 57 */     return this.list.contains(element);
/*    */   }
/*    */ 
/*    */   public void addAllTo(Collection collection)
/*    */   {
/* 66 */     collection.addAll(this.list);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.xml.XMLElementList
 * JD-Core Version:    0.6.2
 */