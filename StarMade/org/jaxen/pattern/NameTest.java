/*   1:    */package org.jaxen.pattern;
/*   2:    */
/*   3:    */import org.jaxen.Context;
/*   4:    */import org.jaxen.Navigator;
/*   5:    */
/*  60:    */public class NameTest
/*  61:    */  extends NodeTest
/*  62:    */{
/*  63:    */  private String name;
/*  64:    */  private short nodeType;
/*  65:    */  
/*  66:    */  public NameTest(String name, short nodeType)
/*  67:    */  {
/*  68: 68 */    this.name = name;
/*  69: 69 */    this.nodeType = nodeType;
/*  70:    */  }
/*  71:    */  
/*  74:    */  public boolean matches(Object node, Context context)
/*  75:    */  {
/*  76: 76 */    Navigator navigator = context.getNavigator();
/*  77: 77 */    if (this.nodeType == 1)
/*  78:    */    {
/*  79: 79 */      return (navigator.isElement(node)) && (this.name.equals(navigator.getElementName(node)));
/*  80:    */    }
/*  81:    */    
/*  82: 82 */    if (this.nodeType == 2)
/*  83:    */    {
/*  84: 84 */      return (navigator.isAttribute(node)) && (this.name.equals(navigator.getAttributeName(node)));
/*  85:    */    }
/*  86:    */    
/*  89: 89 */    if (navigator.isElement(node))
/*  90:    */    {
/*  91: 91 */      return this.name.equals(navigator.getElementName(node));
/*  92:    */    }
/*  93:    */    
/*  94: 94 */    if (navigator.isAttribute(node))
/*  95:    */    {
/*  96: 96 */      return this.name.equals(navigator.getAttributeName(node));
/*  97:    */    }
/*  98:    */    
/*  99: 99 */    return false;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public double getPriority()
/* 103:    */  {
/* 104:104 */    return 0.0D;
/* 105:    */  }
/* 106:    */  
/* 108:    */  public short getMatchType()
/* 109:    */  {
/* 110:110 */    return this.nodeType;
/* 111:    */  }
/* 112:    */  
/* 113:    */  public String getText()
/* 114:    */  {
/* 115:115 */    if (this.nodeType == 2)
/* 116:    */    {
/* 117:117 */      return "@" + this.name;
/* 118:    */    }
/* 119:    */    
/* 121:121 */    return this.name;
/* 122:    */  }
/* 123:    */  
/* 125:    */  public String toString()
/* 126:    */  {
/* 127:127 */    return super.toString() + "[ name: " + this.name + " type: " + this.nodeType + " ]";
/* 128:    */  }
/* 129:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.NameTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */