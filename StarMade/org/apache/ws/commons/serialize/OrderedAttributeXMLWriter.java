/*  1:   */package org.apache.ws.commons.serialize;
/*  2:   */
/*  3:   */import java.util.Arrays;
/*  4:   */import java.util.Comparator;
/*  5:   */import org.xml.sax.Attributes;
/*  6:   */import org.xml.sax.SAXException;
/*  7:   */import org.xml.sax.helpers.AttributesImpl;
/*  8:   */
/* 29:   */public class OrderedAttributeXMLWriter
/* 30:   */  extends XMLWriterImpl
/* 31:   */{
/* 32:   */  public void startElement(String pNamespaceURI, String pLocalName, String pQName, Attributes pAttrs)
/* 33:   */    throws SAXException
/* 34:   */  {
/* 35:35 */    Integer[] attributeNumbers = new Integer[pAttrs.getLength()];
/* 36:36 */    for (int i = 0; i < attributeNumbers.length; i++) {
/* 37:37 */      attributeNumbers[i] = new Integer(i);
/* 38:   */    }
/* 39:39 */    Arrays.sort(attributeNumbers, new Comparator() { private final Attributes val$pAttrs;
/* 40:   */      
/* 41:41 */      public int compare(Object pNum1, Object pNum2) { int i1 = ((Integer)pNum1).intValue();
/* 42:42 */        int i2 = ((Integer)pNum2).intValue();
/* 43:43 */        String uri1 = this.val$pAttrs.getURI(i1);
/* 44:44 */        if (uri1 == null) {
/* 45:45 */          uri1 = "";
/* 46:   */        }
/* 47:47 */        String uri2 = this.val$pAttrs.getURI(i2);
/* 48:48 */        if (uri2 == null) {
/* 49:49 */          uri2 = "";
/* 50:   */        }
/* 51:51 */        int result = uri1.compareTo(uri2);
/* 52:52 */        if (result == 0) {
/* 53:53 */          result = this.val$pAttrs.getLocalName(i1).compareTo(this.val$pAttrs.getLocalName(i2));
/* 54:   */        }
/* 55:55 */        return result;
/* 56:   */      }
/* 57:57 */    });
/* 58:58 */    AttributesImpl orderedAttributes = new AttributesImpl();
/* 59:59 */    for (int i = 0; i < attributeNumbers.length; i++) {
/* 60:60 */      int num = attributeNumbers[i].intValue();
/* 61:61 */      orderedAttributes.addAttribute(pAttrs.getURI(num), pAttrs.getLocalName(num), pAttrs.getQName(num), pAttrs.getType(num), pAttrs.getValue(num));
/* 62:   */    }
/* 63:   */    
/* 65:65 */    super.startElement(pNamespaceURI, pLocalName, pQName, orderedAttributes);
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.serialize.OrderedAttributeXMLWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */