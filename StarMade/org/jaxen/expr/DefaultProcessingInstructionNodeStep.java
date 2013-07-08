/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import org.jaxen.ContextSupport;
/*   4:    */import org.jaxen.Navigator;
/*   5:    */import org.jaxen.expr.iter.IterableAxis;
/*   6:    */
/*  61:    *//**
/*  62:    */ * @deprecated
/*  63:    */ */
/*  64:    */public class DefaultProcessingInstructionNodeStep
/*  65:    */  extends DefaultStep
/*  66:    */  implements ProcessingInstructionNodeStep
/*  67:    */{
/*  68:    */  private static final long serialVersionUID = -4825000697808126927L;
/*  69:    */  private String name;
/*  70:    */  
/*  71:    */  public DefaultProcessingInstructionNodeStep(IterableAxis axis, String name, PredicateSet predicateSet)
/*  72:    */  {
/*  73: 73 */    super(axis, predicateSet);
/*  74:    */    
/*  75: 75 */    this.name = name;
/*  76:    */  }
/*  77:    */  
/*  78:    */  public String getName()
/*  79:    */  {
/*  80: 80 */    return this.name;
/*  81:    */  }
/*  82:    */  
/*  83:    */  public String getText()
/*  84:    */  {
/*  85: 85 */    StringBuffer buf = new StringBuffer();
/*  86: 86 */    buf.append(getAxisName());
/*  87: 87 */    buf.append("::processing-instruction(");
/*  88: 88 */    String name = getName();
/*  89: 89 */    if ((name != null) && (name.length() != 0))
/*  90:    */    {
/*  91: 91 */      buf.append("'");
/*  92: 92 */      buf.append(name);
/*  93: 93 */      buf.append("'");
/*  94:    */    }
/*  95: 95 */    buf.append(")");
/*  96: 96 */    buf.append(super.getText());
/*  97: 97 */    return buf.toString();
/*  98:    */  }
/*  99:    */  
/* 102:    */  public boolean matches(Object node, ContextSupport support)
/* 103:    */  {
/* 104:104 */    Navigator nav = support.getNavigator();
/* 105:105 */    if (nav.isProcessingInstruction(node))
/* 106:    */    {
/* 107:107 */      String name = getName();
/* 108:108 */      if ((name == null) || (name.length() == 0))
/* 109:    */      {
/* 110:110 */        return true;
/* 111:    */      }
/* 112:    */      
/* 114:114 */      return name.equals(nav.getProcessingInstructionTarget(node));
/* 115:    */    }
/* 116:    */    
/* 118:118 */    return false;
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultProcessingInstructionNodeStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */