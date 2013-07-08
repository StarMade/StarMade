/*   1:    */package org.jaxen.pattern;
/*   2:    */
/*   3:    */import org.jaxen.Context;
/*   4:    */import org.jaxen.ContextSupport;
/*   5:    */import org.jaxen.Navigator;
/*   6:    */
/*  60:    */public class NamespaceTest
/*  61:    */  extends NodeTest
/*  62:    */{
/*  63:    */  private String prefix;
/*  64:    */  private short nodeType;
/*  65:    */  
/*  66:    */  public NamespaceTest(String prefix, short nodeType)
/*  67:    */  {
/*  68: 68 */    if (prefix == null)
/*  69:    */    {
/*  70: 70 */      prefix = "";
/*  71:    */    }
/*  72: 72 */    this.prefix = prefix;
/*  73: 73 */    this.nodeType = nodeType;
/*  74:    */  }
/*  75:    */  
/*  78:    */  public boolean matches(Object node, Context context)
/*  79:    */  {
/*  80: 80 */    Navigator navigator = context.getNavigator();
/*  81: 81 */    String uri = getURI(node, context);
/*  82:    */    
/*  83: 83 */    if (this.nodeType == 1)
/*  84:    */    {
/*  85: 85 */      return (navigator.isElement(node)) && (uri.equals(navigator.getElementNamespaceUri(node)));
/*  86:    */    }
/*  87:    */    
/*  88: 88 */    if (this.nodeType == 2)
/*  89:    */    {
/*  90: 90 */      return (navigator.isAttribute(node)) && (uri.equals(navigator.getAttributeNamespaceUri(node)));
/*  91:    */    }
/*  92:    */    
/*  93: 93 */    return false;
/*  94:    */  }
/*  95:    */  
/*  96:    */  public double getPriority()
/*  97:    */  {
/*  98: 98 */    return -0.25D;
/*  99:    */  }
/* 100:    */  
/* 102:    */  public short getMatchType()
/* 103:    */  {
/* 104:104 */    return this.nodeType;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public String getText()
/* 108:    */  {
/* 109:109 */    return this.prefix + ":";
/* 110:    */  }
/* 111:    */  
/* 112:    */  public String toString()
/* 113:    */  {
/* 114:114 */    return super.toString() + "[ prefix: " + this.prefix + " type: " + this.nodeType + " ]";
/* 115:    */  }
/* 116:    */  
/* 119:    */  protected String getURI(Object node, Context context)
/* 120:    */  {
/* 121:121 */    String uri = context.getNavigator().translateNamespacePrefixToUri(this.prefix, node);
/* 122:122 */    if (uri == null)
/* 123:    */    {
/* 124:124 */      uri = context.getContextSupport().translateNamespacePrefixToUri(this.prefix);
/* 125:    */    }
/* 126:126 */    if (uri == null)
/* 127:    */    {
/* 128:128 */      uri = "";
/* 129:    */    }
/* 130:130 */    return uri;
/* 131:    */  }
/* 132:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.NamespaceTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */