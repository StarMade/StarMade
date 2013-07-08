package org.dom4j.util;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.QName;

public class AttributeHelper
{
  public static boolean booleanValue(Element element, String attributeName)
  {
    return booleanValue(element.attribute(attributeName));
  }
  
  public static boolean booleanValue(Element element, QName attributeQName)
  {
    return booleanValue(element.attribute(attributeQName));
  }
  
  protected static boolean booleanValue(Attribute attribute)
  {
    if (attribute == null) {
      return false;
    }
    Object value = attribute.getData();
    if (value == null) {
      return false;
    }
    if ((value instanceof Boolean))
    {
      Boolean local_b = (Boolean)value;
      return local_b.booleanValue();
    }
    return "true".equalsIgnoreCase(value.toString());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.dom4j.util.AttributeHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */