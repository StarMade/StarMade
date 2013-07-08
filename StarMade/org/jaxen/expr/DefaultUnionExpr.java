package org.jaxen.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.XPathSyntaxException;

/**
 * @deprecated
 */
public class DefaultUnionExpr
  extends DefaultBinaryExpr
  implements UnionExpr
{
  private static final long serialVersionUID = 7629142718276852707L;
  
  public DefaultUnionExpr(Expr lhs, Expr rhs)
  {
    super(lhs, rhs);
  }
  
  public String getOperator()
  {
    return "|";
  }
  
  public String toString()
  {
    return "[(DefaultUnionExpr): " + getLHS() + ", " + getRHS() + "]";
  }
  
  public Object evaluate(Context context)
    throws JaxenException
  {
    List results = new ArrayList();
    try
    {
      List lhsResults = (List)getLHS().evaluate(context);
      List rhsResults = (List)getRHS().evaluate(context);
      Set unique = new HashSet();
      results.addAll(lhsResults);
      unique.addAll(lhsResults);
      Iterator rhsIter = rhsResults.iterator();
      while (rhsIter.hasNext())
      {
        Object each = rhsIter.next();
        if (!unique.contains(each))
        {
          results.add(each);
          unique.add(each);
        }
      }
      Collections.sort(results, new NodeComparator(context.getNavigator()));
      return results;
    }
    catch (ClassCastException lhsResults)
    {
      throw new XPathSyntaxException(getText(), context.getPosition(), "Unions are only allowed over node-sets");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jaxen.expr.DefaultUnionExpr
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */