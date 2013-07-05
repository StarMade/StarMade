package org.hsqldb;

public abstract interface RangeGroup
{
  public static final RangeGroup emptyGroup = new RangeGroupEmpty();
  public static final RangeGroup[] emptyArray = { emptyGroup };

  public abstract RangeVariable[] getRangeVariables();

  public abstract void setCorrelated();

  public static class RangeGroupEmpty
    implements RangeGroup
  {
    public RangeVariable[] getRangeVariables()
    {
      return RangeVariable.emptyArray;
    }

    public void setCorrelated()
    {
    }
  }

  public static class RangeGroupSimple
    implements RangeGroup
  {
    RangeVariable[] ranges;
    RangeGroup baseGroup;
    TableDerived table;

    public RangeGroupSimple(TableDerived paramTableDerived)
    {
      this.ranges = RangeVariable.emptyArray;
      this.table = paramTableDerived;
    }

    public RangeGroupSimple(RangeVariable[] paramArrayOfRangeVariable, RangeGroup paramRangeGroup)
    {
      this.ranges = paramArrayOfRangeVariable;
      this.baseGroup = paramRangeGroup;
    }

    public RangeGroupSimple(RangeVariable[] paramArrayOfRangeVariable)
    {
      this.ranges = paramArrayOfRangeVariable;
    }

    public RangeVariable[] getRangeVariables()
    {
      return this.ranges;
    }

    public void setCorrelated()
    {
      if (this.baseGroup != null)
        this.baseGroup.setCorrelated();
      if (this.table != null)
        this.table.setCorrelated();
    }
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.RangeGroup
 * JD-Core Version:    0.6.2
 */