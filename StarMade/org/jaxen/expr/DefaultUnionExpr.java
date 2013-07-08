/*   1:    */package org.jaxen.expr;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Collections;
/*   5:    */import java.util.HashSet;
/*   6:    */import java.util.Iterator;
/*   7:    */import java.util.List;
/*   8:    */import java.util.Set;
/*   9:    */import org.jaxen.Context;
/*  10:    */import org.jaxen.JaxenException;
/*  11:    */import org.jaxen.XPathSyntaxException;
/*  12:    */
/*  65:    *//**
/*  66:    */ * @deprecated
/*  67:    */ */
/*  68:    */public class DefaultUnionExpr
/*  69:    */  extends DefaultBinaryExpr
/*  70:    */  implements UnionExpr
/*  71:    */{
/*  72:    */  private static final long serialVersionUID = 7629142718276852707L;
/*  73:    */  
/*  74:    */  public DefaultUnionExpr(Expr lhs, Expr rhs)
/*  75:    */  {
/*  76: 76 */    super(lhs, rhs);
/*  77:    */  }
/*  78:    */  
/*  80:    */  public String getOperator()
/*  81:    */  {
/*  82: 82 */    return "|";
/*  83:    */  }
/*  84:    */  
/*  85:    */  public String toString()
/*  86:    */  {
/*  87: 87 */    return "[(DefaultUnionExpr): " + getLHS() + ", " + getRHS() + "]";
/*  88:    */  }
/*  89:    */  
/*  90:    */  public Object evaluate(Context context) throws JaxenException
/*  91:    */  {
/*  92: 92 */    List results = new ArrayList();
/*  93:    */    try
/*  94:    */    {
/*  95: 95 */      List lhsResults = (List)getLHS().evaluate(context);
/*  96: 96 */      List rhsResults = (List)getRHS().evaluate(context);
/*  97:    */      
/*  98: 98 */      Set unique = new HashSet();
/*  99:    */      
/* 100:100 */      results.addAll(lhsResults);
/* 101:101 */      unique.addAll(lhsResults);
/* 102:    */      
/* 103:103 */      Iterator rhsIter = rhsResults.iterator();
/* 104:    */      
/* 105:105 */      while (rhsIter.hasNext())
/* 106:    */      {
/* 107:107 */        Object each = rhsIter.next();
/* 108:    */        
/* 109:109 */        if (!unique.contains(each))
/* 110:    */        {
/* 111:111 */          results.add(each);
/* 112:112 */          unique.add(each);
/* 113:    */        }
/* 114:    */      }
/* 115:    */      
/* 116:116 */      Collections.sort(results, new NodeComparator(context.getNavigator()));
/* 117:    */      
/* 118:118 */      return results;
/* 119:    */    }
/* 120:    */    catch (ClassCastException e) {
/* 121:121 */      throw new XPathSyntaxException(getText(), context.getPosition(), "Unions are only allowed over node-sets");
/* 122:    */    }
/* 123:    */  }
/* 124:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.jaxen.expr.DefaultUnionExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */