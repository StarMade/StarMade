package org.hsqldb;

import org.hsqldb.types.Type;

public class ColumnBase
{
  private String name;
  private String table;
  private String schema;
  private String catalog;
  private boolean isWriteable;
  private boolean isSearchable;
  protected byte parameterMode;
  protected boolean isIdentity;
  protected byte nullability = 1;
  protected Type dataType;

  ColumnBase()
  {
  }

  public ColumnBase(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.catalog = paramString1;
    this.schema = paramString2;
    this.table = paramString3;
    this.name = paramString4;
  }

  public ColumnBase(String paramString, ColumnSchema paramColumnSchema)
  {
    this.catalog = paramString;
    this.schema = paramColumnSchema.getSchemaNameString();
    this.table = paramColumnSchema.getTableNameString();
    this.name = paramColumnSchema.getNameString();
    this.nullability = paramColumnSchema.getNullability();
    this.isIdentity = paramColumnSchema.isIdentity();
    this.isSearchable = paramColumnSchema.isSearchable();
    this.isWriteable = paramColumnSchema.isWriteable();
  }

  public String getNameString()
  {
    return this.name;
  }

  public String getTableNameString()
  {
    return this.table;
  }

  public String getSchemaNameString()
  {
    return this.schema;
  }

  public String getCatalogNameString()
  {
    return this.catalog;
  }

  public void setIdentity(boolean paramBoolean)
  {
    this.isIdentity = paramBoolean;
  }

  public boolean isIdentity()
  {
    return this.isIdentity;
  }

  protected void setType(ColumnBase paramColumnBase)
  {
    this.nullability = paramColumnBase.nullability;
    this.dataType = paramColumnBase.dataType;
  }

  public void setType(Type paramType)
  {
    this.dataType = paramType;
  }

  public boolean isNullable()
  {
    return (!this.isIdentity) && (this.nullability == 1);
  }

  protected void setNullable(boolean paramBoolean)
  {
    this.nullability = (paramBoolean ? 1 : 0);
  }

  public byte getNullability()
  {
    return this.isIdentity ? 0 : this.nullability;
  }

  public void setNullability(byte paramByte)
  {
    this.nullability = paramByte;
  }

  public boolean isWriteable()
  {
    return this.isWriteable;
  }

  public void setWriteable(boolean paramBoolean)
  {
    this.isWriteable = paramBoolean;
  }

  public boolean isSearchable()
  {
    return this.isSearchable;
  }

  public void setSearchable(boolean paramBoolean)
  {
    this.isSearchable = paramBoolean;
  }

  public Type getDataType()
  {
    return this.dataType;
  }

  public byte getParameterMode()
  {
    return this.parameterMode;
  }

  public void setParameterMode(byte paramByte)
  {
    this.parameterMode = paramByte;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.ColumnBase
 * JD-Core Version:    0.6.2
 */