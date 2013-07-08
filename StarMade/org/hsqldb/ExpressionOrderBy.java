package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.types.Collation;
import org.hsqldb.types.Type;

public class ExpressionOrderBy
  extends Expression
{
  private boolean isDescending;
  private boolean isNullsLast;
  
  ExpressionOrderBy(Expression paramExpression)
  {
    super(94);
    this.nodes = new Expression[1];
    this.nodes[0] = paramExpression;
    this.collation = paramExpression.collation;
    paramExpression.collation = null;
  }
  
  void setDescending()
  {
    this.isDescending = true;
  }
  
  boolean isDescending()
  {
    return this.isDescending;
  }
  
  void setNullsLast(boolean paramBoolean)
  {
    this.isNullsLast = paramBoolean;
  }
  
  boolean isNullsLast()
  {
    return this.isNullsLast;
  }
  
  public Object getValue(Session paramSession)
  {
    return this.nodes[0].getValue(paramSession);
  }
  
  public void resolveTypes(Session paramSession, Expression paramExpression)
  {
    this.nodes[0].resolveTypes(paramSession, paramExpression);
    if (this.nodes[0].isUnresolvedParam()) {
      throw Error.error(5567);
    }
    this.dataType = this.nodes[0].dataType;
    if ((this.collation != null) && (!this.dataType.isCharacterType())) {
      throw Error.error(4650, this.collation.getName().statementName);
    }
  }
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("ORDER").append(' ').append("BY").append(' ');
    if (this.nodes[0].alias != null) {
      localStringBuffer.append(this.nodes[0].alias.name);
    } else {
      localStringBuffer.append(this.nodes[0].getSQL());
    }
    if (this.collation != null) {
      localStringBuffer.append(' ').append(this.collation.getName().getSchemaQualifiedStatementName());
    }
    if (this.isDescending) {
      localStringBuffer.append(' ').append("DESC");
    }
    return localStringBuffer.toString();
  }
  
  protected String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(getLeftNode().describe(paramSession, paramInt));
    if (this.isDescending)
    {
      for (int i = 0; i < paramInt; i++) {
        localStringBuffer.append(' ');
      }
      localStringBuffer.append("DESC").append('\n');
    }
    return localStringBuffer.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ExpressionOrderBy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */