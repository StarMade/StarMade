/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Collections;
/*   5:    */import java.util.Iterator;
/*   6:    */import java.util.LinkedList;
/*   7:    */import java.util.List;
/*   8:    */import org.jaxen.Context;
/*   9:    */import org.jaxen.ContextSupport;
/*  10:    */import org.jaxen.JaxenException;
/*  11:    */
/*  61:    */abstract class DefaultLocationPath
/*  62:    */  extends DefaultExpr
/*  63:    */  implements LocationPath
/*  64:    */{
/*  65:    */  private List steps;
/*  66:    */  
/*  67:    */  DefaultLocationPath()
/*  68:    */  {
/*  69: 69 */    this.steps = new LinkedList();
/*  70:    */  }
/*  71:    */  
/*  72:    */  public void addStep(Step step)
/*  73:    */  {
/*  74: 74 */    getSteps().add(step);
/*  75:    */  }
/*  76:    */  
/*  77:    */  public List getSteps()
/*  78:    */  {
/*  79: 79 */    return this.steps;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public Expr simplify()
/*  83:    */  {
/*  84: 84 */    Iterator stepIter = getSteps().iterator();
/*  85: 85 */    Step eachStep = null;
/*  86: 86 */    while (stepIter.hasNext())
/*  87:    */    {
/*  88: 88 */      eachStep = (Step)stepIter.next();
/*  89: 89 */      eachStep.simplify();
/*  90:    */    }
/*  91: 91 */    return this;
/*  92:    */  }
/*  93:    */  
/*  94:    */  public String getText()
/*  95:    */  {
/*  96: 96 */    StringBuffer buf = new StringBuffer();
/*  97: 97 */    Iterator stepIter = getSteps().iterator();
/*  98: 98 */    while (stepIter.hasNext())
/*  99:    */    {
/* 100:100 */      buf.append(((Step)stepIter.next()).getText());
/* 101:101 */      if (stepIter.hasNext())
/* 102:    */      {
/* 103:103 */        buf.append("/");
/* 104:    */      }
/* 105:    */    }
/* 106:106 */    return buf.toString();
/* 107:    */  }
/* 108:    */  
/* 109:    */  public String toString()
/* 110:    */  {
/* 111:111 */    StringBuffer buf = new StringBuffer();
/* 112:112 */    Iterator stepIter = getSteps().iterator();
/* 113:113 */    while (stepIter.hasNext())
/* 114:    */    {
/* 115:115 */      buf.append(stepIter.next().toString());
/* 116:116 */      if (stepIter.hasNext())
/* 117:    */      {
/* 118:118 */        buf.append("/");
/* 119:    */      }
/* 120:    */    }
/* 121:121 */    return buf.toString();
/* 122:    */  }
/* 123:    */  
/* 124:    */  public boolean isAbsolute()
/* 125:    */  {
/* 126:126 */    return false;
/* 127:    */  }
/* 128:    */  
/* 129:    */  public Object evaluate(Context context) throws JaxenException
/* 130:    */  {
/* 131:131 */    List nodeSet = context.getNodeSet();
/* 132:132 */    List contextNodeSet = new ArrayList(nodeSet);
/* 133:133 */    ContextSupport support = context.getContextSupport();
/* 134:134 */    Context stepContext = new Context(support);
/* 135:135 */    Iterator stepIter = getSteps().iterator();
/* 136:136 */    while (stepIter.hasNext())
/* 137:    */    {
/* 138:138 */      Step eachStep = (Step)stepIter.next();
/* 139:139 */      stepContext.setNodeSet(contextNodeSet);
/* 140:140 */      contextNodeSet = eachStep.evaluate(stepContext);
/* 141:    */      
/* 142:142 */      if (isReverseAxis(eachStep)) {
/* 143:143 */        Collections.reverse(contextNodeSet);
/* 144:    */      }
/* 145:    */    }
/* 146:    */    
/* 147:147 */    if ((getSteps().size() > 1) || (nodeSet.size() > 1)) {
/* 148:148 */      Collections.sort(contextNodeSet, new NodeComparator(support.getNavigator()));
/* 149:    */    }
/* 150:    */    
/* 151:151 */    return contextNodeSet;
/* 152:    */  }
/* 153:    */  
/* 154:    */  private boolean isReverseAxis(Step step)
/* 155:    */  {
/* 156:156 */    int axis = step.getAxis();
/* 157:157 */    return (axis == 8) || (axis == 6) || (axis == 4) || (axis == 13);
/* 158:    */  }
/* 159:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultLocationPath
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */