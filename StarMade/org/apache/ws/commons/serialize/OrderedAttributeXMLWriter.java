package org.apache.ws.commons.serialize;

import java.util.Arrays;
import java.util.Comparator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class OrderedAttributeXMLWriter
  extends XMLWriterImpl
{
  public void startElement(String pNamespaceURI, String pLocalName, String pQName, Attributes pAttrs)
    throws SAXException
  {
    Integer[] attributeNumbers = new Integer[pAttrs.getLength()];
    for (int local_i = 0; local_i < attributeNumbers.length; local_i++) {
      attributeNumbers[local_i] = new Integer(local_i);
    }
    Arrays.sort(attributeNumbers, new Comparator()
    {
      private final Attributes val$pAttrs;
      
      public int compare(Object pNum1, Object pNum2)
      {
        int local_i1 = ((Integer)pNum1).intValue();
        int local_i2 = ((Integer)pNum2).intValue();
        String uri1 = this.val$pAttrs.getURI(local_i1);
        if (uri1 == null) {
          uri1 = "";
        }
        String uri2 = this.val$pAttrs.getURI(local_i2);
        if (uri2 == null) {
          uri2 = "";
        }
        int result = uri1.compareTo(uri2);
        if (result == 0) {
          result = this.val$pAttrs.getLocalName(local_i1).compareTo(this.val$pAttrs.getLocalName(local_i2));
        }
        return result;
      }
    });
    AttributesImpl local_i = new AttributesImpl();
    for (int local_i1 = 0; local_i1 < attributeNumbers.length; local_i1++)
    {
      int num = attributeNumbers[local_i1].intValue();
      local_i.addAttribute(pAttrs.getURI(num), pAttrs.getLocalName(num), pAttrs.getQName(num), pAttrs.getType(num), pAttrs.getValue(num));
    }
    super.startElement(pNamespaceURI, pLocalName, pQName, local_i);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.ws.commons.serialize.OrderedAttributeXMLWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */