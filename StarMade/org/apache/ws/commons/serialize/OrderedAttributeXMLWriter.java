/*    */ package org.apache.ws.commons.serialize;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Comparator;
/*    */ import org.xml.sax.Attributes;
/*    */ import org.xml.sax.SAXException;
/*    */ import org.xml.sax.helpers.AttributesImpl;
/*    */ 
/*    */ public class OrderedAttributeXMLWriter extends XMLWriterImpl
/*    */ {
/*    */   public void startElement(String pNamespaceURI, String pLocalName, String pQName, Attributes pAttrs)
/*    */     throws SAXException
/*    */   {
/* 35 */     Integer[] attributeNumbers = new Integer[pAttrs.getLength()];
/* 36 */     for (int i = 0; i < attributeNumbers.length; i++) {
/* 37 */       attributeNumbers[i] = new Integer(i);
/*    */     }
/* 39 */     Arrays.sort(attributeNumbers, new Comparator() { private final Attributes val$pAttrs;
/*    */ 
/* 41 */       public int compare(Object pNum1, Object pNum2) { int i1 = ((Integer)pNum1).intValue();
/* 42 */         int i2 = ((Integer)pNum2).intValue();
/* 43 */         String uri1 = this.val$pAttrs.getURI(i1);
/* 44 */         if (uri1 == null) {
/* 45 */           uri1 = "";
/*    */         }
/* 47 */         String uri2 = this.val$pAttrs.getURI(i2);
/* 48 */         if (uri2 == null) {
/* 49 */           uri2 = "";
/*    */         }
/* 51 */         int result = uri1.compareTo(uri2);
/* 52 */         if (result == 0) {
/* 53 */           result = this.val$pAttrs.getLocalName(i1).compareTo(this.val$pAttrs.getLocalName(i2));
/*    */         }
/* 55 */         return result;
/*    */       }
/*    */     });
/* 58 */     AttributesImpl orderedAttributes = new AttributesImpl();
/* 59 */     for (int i = 0; i < attributeNumbers.length; i++) {
/* 60 */       int num = attributeNumbers[i].intValue();
/* 61 */       orderedAttributes.addAttribute(pAttrs.getURI(num), pAttrs.getLocalName(num), pAttrs.getQName(num), pAttrs.getType(num), pAttrs.getValue(num));
/*    */     }
/*    */ 
/* 65 */     super.startElement(pNamespaceURI, pLocalName, pQName, orderedAttributes);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.serialize.OrderedAttributeXMLWriter
 * JD-Core Version:    0.6.2
 */