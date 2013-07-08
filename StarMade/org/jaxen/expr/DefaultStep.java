/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Iterator;
/*   5:    */import java.util.List;
/*   6:    */import org.jaxen.Context;
/*   7:    */import org.jaxen.ContextSupport;
/*   8:    */import org.jaxen.JaxenException;
/*   9:    */import org.jaxen.UnsupportedAxisException;
/*  10:    */import org.jaxen.expr.iter.IterableAxis;
/*  11:    */import org.jaxen.saxpath.Axis;
/*  12:    */
/*  60:    *//**
/*  61:    */ * @deprecated
/*  62:    */ */
/*  63:    */public abstract class DefaultStep
/*  64:    */  implements Step
/*  65:    */{
/*  66:    */  private IterableAxis axis;
/*  67:    */  private PredicateSet predicates;
/*  68:    */  
/*  69:    */  public DefaultStep(IterableAxis axis, PredicateSet predicates)
/*  70:    */  {
/*  71: 71 */    this.axis = axis;
/*  72: 72 */    this.predicates = predicates;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void addPredicate(Predicate predicate)
/*  76:    */  {
/*  77: 77 */    this.predicates.addPredicate(predicate);
/*  78:    */  }
/*  79:    */  
/*  80:    */  public List getPredicates()
/*  81:    */  {
/*  82: 82 */    return this.predicates.getPredicates();
/*  83:    */  }
/*  84:    */  
/*  85:    */  public PredicateSet getPredicateSet()
/*  86:    */  {
/*  87: 87 */    return this.predicates;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public int getAxis()
/*  91:    */  {
/*  92: 92 */    return this.axis.value();
/*  93:    */  }
/*  94:    */  
/*  95:    */  public IterableAxis getIterableAxis()
/*  96:    */  {
/*  97: 97 */    return this.axis;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public String getAxisName()
/* 101:    */  {
/* 102:102 */    return Axis.lookup(getAxis());
/* 103:    */  }
/* 104:    */  
/* 105:    */  public String getText()
/* 106:    */  {
/* 107:107 */    return this.predicates.getText();
/* 108:    */  }
/* 109:    */  
/* 110:    */  public String toString()
/* 111:    */  {
/* 112:112 */    return getIterableAxis() + " " + super.toString();
/* 113:    */  }
/* 114:    */  
/* 115:    */  public void simplify()
/* 116:    */  {
/* 117:117 */    this.predicates.simplify();
/* 118:    */  }
/* 119:    */  
/* 120:    */  public Iterator axisIterator(Object contextNode, ContextSupport support)
/* 121:    */    throws UnsupportedAxisException
/* 122:    */  {
/* 123:123 */    return getIterableAxis().iterator(contextNode, support);
/* 124:    */  }
/* 125:    */  
/* 126:    */  public List evaluate(Context context) throws JaxenException
/* 127:    */  {
/* 128:128 */    List contextNodeSet = context.getNodeSet();
/* 129:129 */    IdentitySet unique = new IdentitySet();
/* 130:130 */    int contextSize = contextNodeSet.size();
/* 131:    */    
/* 134:134 */    ArrayList interimSet = new ArrayList();
/* 135:135 */    ArrayList newNodeSet = new ArrayList();
/* 136:136 */    ContextSupport support = context.getContextSupport();
/* 137:    */    
/* 139:139 */    for (int i = 0; i < contextSize; i++)
/* 140:    */    {
/* 141:141 */      Object eachContextNode = contextNodeSet.get(i);
/* 142:    */      
/* 151:151 */      Iterator axisNodeIter = this.axis.iterator(eachContextNode, support);
/* 152:152 */      while (axisNodeIter.hasNext())
/* 153:    */      {
/* 154:154 */        Object eachAxisNode = axisNodeIter.next();
/* 155:155 */        if (!unique.contains(eachAxisNode))
/* 156:    */        {
/* 157:157 */          if (matches(eachAxisNode, support))
/* 158:    */          {
/* 159:159 */            unique.add(eachAxisNode);
/* 160:160 */            interimSet.add(eachAxisNode);
/* 161:    */          }
/* 162:    */        }
/* 163:    */      }
/* 164:164 */      newNodeSet.addAll(getPredicateSet().evaluatePredicates(interimSet, support));
/* 165:    */      
/* 166:166 */      interimSet.clear();
/* 167:    */    }
/* 168:168 */    return newNodeSet;
/* 169:    */  }
/* 170:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultStep
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */