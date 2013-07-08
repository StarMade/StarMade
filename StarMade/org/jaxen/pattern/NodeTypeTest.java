/*   1:    */package org.jaxen.pattern;
/*   2:    */
/*   3:    */import org.jaxen.Context;
/*   4:    */import org.jaxen.Navigator;
/*   5:    */
/*  57:    */public class NodeTypeTest
/*  58:    */  extends NodeTest
/*  59:    */{
/*  60: 60 */  public static final NodeTypeTest DOCUMENT_TEST = new NodeTypeTest((short)9);
/*  61:    */  
/*  63: 63 */  public static final NodeTypeTest ELEMENT_TEST = new NodeTypeTest((short)1);
/*  64:    */  
/*  66: 66 */  public static final NodeTypeTest ATTRIBUTE_TEST = new NodeTypeTest((short)2);
/*  67:    */  
/*  69: 69 */  public static final NodeTypeTest COMMENT_TEST = new NodeTypeTest((short)8);
/*  70:    */  
/*  72: 72 */  public static final NodeTypeTest TEXT_TEST = new NodeTypeTest((short)3);
/*  73:    */  
/*  75: 75 */  public static final NodeTypeTest PROCESSING_INSTRUCTION_TEST = new NodeTypeTest((short)7);
/*  76:    */  
/*  78: 78 */  public static final NodeTypeTest NAMESPACE_TEST = new NodeTypeTest((short)13);
/*  79:    */  
/*  81:    */  private short nodeType;
/*  82:    */  
/*  84:    */  public NodeTypeTest(short nodeType)
/*  85:    */  {
/*  86: 86 */    this.nodeType = nodeType;
/*  87:    */  }
/*  88:    */  
/*  91:    */  public boolean matches(Object node, Context context)
/*  92:    */  {
/*  93: 93 */    return this.nodeType == context.getNavigator().getNodeType(node);
/*  94:    */  }
/*  95:    */  
/*  96:    */  public double getPriority()
/*  97:    */  {
/*  98: 98 */    return -0.5D;
/*  99:    */  }
/* 100:    */  
/* 102:    */  public short getMatchType()
/* 103:    */  {
/* 104:104 */    return this.nodeType;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public String getText()
/* 108:    */  {
/* 109:109 */    switch (this.nodeType)
/* 110:    */    {
/* 111:    */    case 1: 
/* 112:112 */      return "child()";
/* 113:    */    case 2: 
/* 114:114 */      return "@*";
/* 115:    */    case 13: 
/* 116:116 */      return "namespace()";
/* 117:    */    case 9: 
/* 118:118 */      return "/";
/* 119:    */    case 8: 
/* 120:120 */      return "comment()";
/* 121:    */    case 3: 
/* 122:122 */      return "text()";
/* 123:    */    case 7: 
/* 124:124 */      return "processing-instruction()";
/* 125:    */    }
/* 126:126 */    return "";
/* 127:    */  }
/* 128:    */  
/* 129:    */  public String toString()
/* 130:    */  {
/* 131:131 */    return super.toString() + "[ type: " + this.nodeType + " ]";
/* 132:    */  }
/* 133:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.pattern.NodeTypeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */