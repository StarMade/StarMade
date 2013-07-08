package org.hsqldb;

import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.rights.Grantee;

public abstract interface SchemaObject
{
  public static final int CATALOG = 1;
  public static final int SCHEMA = 2;
  public static final int TABLE = 3;
  public static final int VIEW = 4;
  public static final int CONSTRAINT = 5;
  public static final int ASSERTION = 6;
  public static final int SEQUENCE = 7;
  public static final int TRIGGER = 8;
  public static final int COLUMN = 9;
  public static final int TRANSITION = 10;
  public static final int GRANTEE = 11;
  public static final int TYPE = 12;
  public static final int DOMAIN = 13;
  public static final int CHARSET = 14;
  public static final int COLLATION = 15;
  public static final int FUNCTION = 16;
  public static final int PROCEDURE = 17;
  public static final int ROUTINE = 18;
  public static final int CURSOR = 19;
  public static final int INDEX = 20;
  public static final int LABEL = 21;
  public static final int VARIABLE = 22;
  public static final int PARAMETER = 23;
  public static final int SPECIFIC_ROUTINE = 24;
  public static final int WRAPPER = 25;
  public static final int SERVER = 26;
  public static final int SUBQUERY = 27;
  public static final SchemaObject[] emptyArray = new SchemaObject[0];
  
  public abstract int getType();
  
  public abstract HsqlNameManager.HsqlName getName();
  
  public abstract HsqlNameManager.HsqlName getSchemaName();
  
  public abstract HsqlNameManager.HsqlName getCatalogName();
  
  public abstract Grantee getOwner();
  
  public abstract OrderedHashSet getReferences();
  
  public abstract OrderedHashSet getComponents();
  
  public abstract void compile(Session paramSession, SchemaObject paramSchemaObject);
  
  public abstract String getSQL();
  
  public abstract long getChangeTimestamp();
  
  public static abstract interface Nullability
  {
    public static final byte NO_NULLS = 0;
    public static final byte NULLABLE = 1;
    public static final byte NULLABLE_UNKNOWN = 2;
  }
  
  public static abstract interface ParameterModes
  {
    public static final byte PARAM_UNKNOWN = 0;
    public static final byte PARAM_IN = 1;
    public static final byte PARAM_OUT = 4;
    public static final byte PARAM_INOUT = 2;
  }
  
  public static abstract interface ViewCheckModes
  {
    public static final int CHECK_NONE = 0;
    public static final int CHECK_LOCAL = 1;
    public static final int CHECK_CASCADE = 2;
  }
  
  public static abstract interface Deferable
  {
    public static final int NOT_DEFERRABLE = 0;
    public static final int INIT_DEFERRED = 1;
    public static final int INIT_IMMEDIATE = 2;
  }
  
  public static abstract interface ReferentialAction
  {
    public static final int CASCADE = 0;
    public static final int RESTRICT = 1;
    public static final int SET_NULL = 2;
    public static final int NO_ACTION = 3;
    public static final int SET_DEFAULT = 4;
  }
  
  public static abstract interface ConstraintTypes
  {
    public static final int FOREIGN_KEY = 0;
    public static final int MAIN = 1;
    public static final int UNIQUE = 2;
    public static final int CHECK = 3;
    public static final int PRIMARY_KEY = 4;
    public static final int TEMP = 5;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.SchemaObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */