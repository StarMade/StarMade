package org.hsqldb.types;

import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.TypeInvariants;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.rights.Grantee;

public class Charset
  implements SchemaObject
{
  public static final int[][] uppercaseLetters = { { 65, 90 } };
  public static final int[][] unquotedIdentifier = { { 48, 57 }, { 65, 90 }, { 95, 95 } };
  public static final int[][] basicIdentifier = { { 48, 57 }, { 65, 90 }, { 95, 95 }, { 97, 122 } };
  HsqlNameManager.HsqlName name;
  public HsqlNameManager.HsqlName base;
  int[][] ranges;
  
  public Charset(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.name = paramHsqlName;
  }
  
  public int getType()
  {
    return 14;
  }
  
  public HsqlNameManager.HsqlName getName()
  {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getCatalogName()
  {
    return this.name.schema.schema;
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
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    localOrderedHashSet.add(this.base);
    return localOrderedHashSet;
  }
  
  public OrderedHashSet getComponents()
  {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("CREATE").append(' ').append("CHARACTER").append(' ').append("SET").append(' ');
    localStringBuffer.append(this.name.getSchemaQualifiedStatementName());
    localStringBuffer.append(' ').append("AS").append(' ').append("GET");
    localStringBuffer.append(' ').append(this.base.getSchemaQualifiedStatementName());
    return localStringBuffer.toString();
  }
  
  public long getChangeTimestamp()
  {
    return 0L;
  }
  
  public static boolean isInSet(String paramString, int[][] paramArrayOfInt)
  {
    int i = paramString.length();
    label64:
    for (int j = 0; j < i; j++)
    {
      int k = paramString.charAt(j);
      for (int m = 0; m < paramArrayOfInt.length; m++) {
        if (k <= paramArrayOfInt[m][1])
        {
          if (k >= paramArrayOfInt[m][0]) {
            break label64;
          }
          return false;
        }
      }
      return false;
    }
    return true;
  }
  
  public static boolean startsWith(String paramString, int[][] paramArrayOfInt)
  {
    int i = paramString.charAt(0);
    for (int j = 0; j < paramArrayOfInt.length; j++) {
      if (i <= paramArrayOfInt[j][1]) {
        return i >= paramArrayOfInt[j][0];
      }
    }
    return false;
  }
  
  public static Charset getDefaultInstance()
  {
    return TypeInvariants.SQL_TEXT;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.types.Charset
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */