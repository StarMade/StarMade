package org.hsqldb.types;

import org.hsqldb.Constraint;
import org.hsqldb.Expression;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.Session;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.rights.Grantee;

public class UserTypeModifier
{
  final HsqlNameManager.HsqlName name;
  final int schemaObjectType;
  final Type dataType;
  Constraint[] constraints = Constraint.emptyArray;
  Expression defaultExpression;
  boolean isNullable = true;
  
  public UserTypeModifier(HsqlNameManager.HsqlName paramHsqlName, int paramInt, Type paramType)
  {
    this.name = paramHsqlName;
    this.schemaObjectType = paramInt;
    this.dataType = paramType;
  }
  
  public int schemaObjectType()
  {
    return this.schemaObjectType;
  }
  
  public void addConstraint(Constraint paramConstraint)
  {
    int i = this.constraints.length;
    this.constraints = ((Constraint[])ArrayUtil.resizeArray(this.constraints, i + 1));
    this.constraints[i] = paramConstraint;
    setNotNull();
  }
  
  public void removeConstraint(String paramString)
  {
    for (int i = 0; i < this.constraints.length; i++) {
      if (this.constraints[i].getName().name.equals(paramString))
      {
        this.constraints = ((Constraint[])ArrayUtil.toAdjustedArray(this.constraints, null, i, -1));
        break;
      }
    }
    setNotNull();
  }
  
  public Constraint getConstraint(String paramString)
  {
    for (int i = 0; i < this.constraints.length; i++) {
      if (this.constraints[i].getName().name.equals(paramString)) {
        return this.constraints[i];
      }
    }
    return null;
  }
  
  public Constraint[] getConstraints()
  {
    return this.constraints;
  }
  
  public boolean isNullable()
  {
    return this.isNullable;
  }
  
  public Expression getDefaultClause()
  {
    return this.defaultExpression;
  }
  
  public void setDefaultClause(Expression paramExpression)
  {
    this.defaultExpression = paramExpression;
  }
  
  public void removeDefaultClause()
  {
    this.defaultExpression = null;
  }
  
  private void setNotNull()
  {
    this.isNullable = true;
    for (int i = 0; i < this.constraints.length; i++) {
      if (this.constraints[i].isNotNull()) {
        this.isNullable = false;
      }
    }
  }
  
  public int getType()
  {
    return this.schemaObjectType;
  }
  
  public HsqlNameManager.HsqlName getName()
  {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getSchemaName()
  {
    return this.name.schema;
  }
  
  public Grantee getOwner()
  {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences()
  {
    OrderedHashSet localOrderedHashSet1 = new OrderedHashSet();
    for (int i = 0; i < this.constraints.length; i++)
    {
      OrderedHashSet localOrderedHashSet2 = this.constraints[i].getReferences();
      if (localOrderedHashSet2 != null) {
        localOrderedHashSet1.addAll(localOrderedHashSet2);
      }
    }
    return localOrderedHashSet1;
  }
  
  public final OrderedHashSet getComponents()
  {
    if (this.constraints == null) {
      return null;
    }
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    localOrderedHashSet.addAll(this.constraints);
    return localOrderedHashSet;
  }
  
  public void compile(Session paramSession)
  {
    for (int i = 0; i < this.constraints.length; i++) {
      this.constraints[i].compile(paramSession, null);
    }
  }
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (this.schemaObjectType == 12)
    {
      localStringBuffer.append("CREATE").append(' ').append("TYPE").append(' ');
      localStringBuffer.append(this.name.getSchemaQualifiedStatementName());
      localStringBuffer.append(' ').append("AS").append(' ');
      localStringBuffer.append(this.dataType.getDefinition());
      if (this.dataType.hasCollation())
      {
        localStringBuffer.append(' ').append("COLLATE").append(' ');
        localStringBuffer.append(this.dataType.getCollationDefinition());
      }
    }
    else
    {
      localStringBuffer.append("CREATE").append(' ').append("DOMAIN").append(' ');
      localStringBuffer.append(this.name.getSchemaQualifiedStatementName());
      localStringBuffer.append(' ').append("AS").append(' ');
      localStringBuffer.append(this.dataType.getDefinition());
      if (this.defaultExpression != null)
      {
        localStringBuffer.append(' ').append("DEFAULT").append(' ');
        localStringBuffer.append(this.defaultExpression.getSQL());
      }
      for (int i = 0; i < this.constraints.length; i++)
      {
        localStringBuffer.append(' ').append("CONSTRAINT").append(' ');
        localStringBuffer.append(this.constraints[i].getName().statementName).append(' ');
        localStringBuffer.append("CHECK").append('(').append(this.constraints[i].getCheckSQL()).append(')');
      }
    }
    return localStringBuffer.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.types.UserTypeModifier
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */