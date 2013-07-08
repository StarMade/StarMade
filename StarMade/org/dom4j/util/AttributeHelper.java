/*  1:   */package org.dom4j.util;
/*  2:   */
/*  3:   */import org.dom4j.Attribute;
/*  4:   */import org.dom4j.Element;
/*  5:   */import org.dom4j.QName;
/*  6:   */
/* 24:   */public class AttributeHelper
/* 25:   */{
/* 26:   */  public static boolean booleanValue(Element element, String attributeName)
/* 27:   */  {
/* 28:28 */    return booleanValue(element.attribute(attributeName));
/* 29:   */  }
/* 30:   */  
/* 31:   */  public static boolean booleanValue(Element element, QName attributeQName) {
/* 32:32 */    return booleanValue(element.attribute(attributeQName));
/* 33:   */  }
/* 34:   */  
/* 35:   */  protected static boolean booleanValue(Attribute attribute) {
/* 36:36 */    if (attribute == null) {
/* 37:37 */      return false;
/* 38:   */    }
/* 39:   */    
/* 40:40 */    Object value = attribute.getData();
/* 41:   */    
/* 42:42 */    if (value == null)
/* 43:43 */      return false;
/* 44:44 */    if ((value instanceof Boolean)) {
/* 45:45 */      Boolean b = (Boolean)value;
/* 46:   */      
/* 47:47 */      return b.booleanValue();
/* 48:   */    }
/* 49:49 */    return "true".equalsIgnoreCase(value.toString());
/* 50:   */  }
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.dom4j.util.AttributeHelper
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */