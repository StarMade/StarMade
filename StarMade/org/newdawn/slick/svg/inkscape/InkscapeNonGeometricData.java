/*  1:   */package org.newdawn.slick.svg.inkscape;
/*  2:   */
/*  3:   */import org.newdawn.slick.svg.NonGeometricData;
/*  4:   */import org.w3c.dom.Element;
/*  5:   */
/* 16:   */public class InkscapeNonGeometricData
/* 17:   */  extends NonGeometricData
/* 18:   */{
/* 19:   */  private Element element;
/* 20:   */  
/* 21:   */  public InkscapeNonGeometricData(String metaData, Element element)
/* 22:   */  {
/* 23:23 */    super(metaData);
/* 24:   */    
/* 25:25 */    this.element = element;
/* 26:   */  }
/* 27:   */  
/* 30:   */  public String getAttribute(String attribute)
/* 31:   */  {
/* 32:32 */    String result = super.getAttribute(attribute);
/* 33:33 */    if (result == null) {
/* 34:34 */      result = this.element.getAttribute(attribute);
/* 35:   */    }
/* 36:   */    
/* 37:37 */    return result;
/* 38:   */  }
/* 39:   */  
/* 44:   */  public Element getElement()
/* 45:   */  {
/* 46:46 */    return this.element;
/* 47:   */  }
/* 48:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.inkscape.InkscapeNonGeometricData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */