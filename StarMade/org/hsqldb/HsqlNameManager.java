package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.rights.Grantee;

public final class HsqlNameManager
{
  public static final String DEFAULT_CATALOG_NAME = "PUBLIC";
  private static final HsqlNameManager staticManager = new HsqlNameManager();
  private static final HsqlName[] autoColumnNames;
  private static final String[] autoNoNameColumnNames;
  private int serialNumber = 1;
  private int sysNumber = 10000;
  private HsqlName catalogName;
  private boolean sqlRegularNames;
  HsqlName subqueryTableName;
  
  public HsqlNameManager()
  {
    this.sqlRegularNames = true;
  }
  
  public HsqlNameManager(Database paramDatabase)
  {
    this.catalogName = new HsqlName(this, "PUBLIC", 1, false, null);
    this.sqlRegularNames = paramDatabase.sqlRegularNames;
    this.subqueryTableName = new HsqlName(this, "SYSTEM_SUBQUERY", false, 3, null);
    this.subqueryTableName.schema = SqlInvariants.SYSTEM_SCHEMA_HSQLNAME;
  }
  
  public HsqlName getCatalogName()
  {
    return this.catalogName;
  }
  
  public void setSqlRegularNames(boolean paramBoolean)
  {
    this.sqlRegularNames = paramBoolean;
  }
  
  public static HsqlName newSystemObjectName(String paramString, int paramInt)
  {
    return new HsqlName(staticManager, paramString, paramInt, false, null);
  }
  
  public static HsqlName newInfoSchemaColumnName(String paramString, HsqlName paramHsqlName)
  {
    HsqlName localHsqlName = new HsqlName(staticManager, paramString, false, 9, null);
    localHsqlName.schema = SqlInvariants.INFORMATION_SCHEMA_HSQLNAME;
    localHsqlName.parent = paramHsqlName;
    return localHsqlName;
  }
  
  public static HsqlName newInfoSchemaTableName(String paramString)
  {
    HsqlName localHsqlName = new HsqlName(staticManager, paramString, 3, false, null);
    localHsqlName.schema = SqlInvariants.INFORMATION_SCHEMA_HSQLNAME;
    return localHsqlName;
  }
  
  public static HsqlName newInfoSchemaObjectName(String paramString, boolean paramBoolean, int paramInt)
  {
    HsqlName localHsqlName = new HsqlName(staticManager, paramString, paramInt, paramBoolean, null);
    localHsqlName.schema = SqlInvariants.INFORMATION_SCHEMA_HSQLNAME;
    return localHsqlName;
  }
  
  public HsqlName newHsqlName(HsqlName paramHsqlName, String paramString, int paramInt)
  {
    HsqlName localHsqlName = new HsqlName(this, paramString, paramInt, false, null);
    localHsqlName.schema = paramHsqlName;
    return localHsqlName;
  }
  
  public HsqlName newHsqlName(String paramString, boolean paramBoolean, int paramInt)
  {
    return new HsqlName(this, paramString, paramBoolean, paramInt, null);
  }
  
  public HsqlName newHsqlName(HsqlName paramHsqlName, String paramString, boolean paramBoolean, int paramInt)
  {
    HsqlName localHsqlName = new HsqlName(this, paramString, paramBoolean, paramInt, null);
    localHsqlName.schema = paramHsqlName;
    return localHsqlName;
  }
  
  public HsqlName newHsqlName(HsqlName paramHsqlName1, String paramString, boolean paramBoolean, int paramInt, HsqlName paramHsqlName2)
  {
    HsqlName localHsqlName = new HsqlName(this, paramString, paramBoolean, paramInt, null);
    localHsqlName.schema = paramHsqlName1;
    localHsqlName.parent = paramHsqlName2;
    return localHsqlName;
  }
  
  public HsqlName newColumnSchemaHsqlName(HsqlName paramHsqlName, SimpleName paramSimpleName)
  {
    return newColumnHsqlName(paramHsqlName, paramSimpleName.name, paramSimpleName.isNameQuoted);
  }
  
  public HsqlName newColumnHsqlName(HsqlName paramHsqlName, String paramString, boolean paramBoolean)
  {
    HsqlName localHsqlName = new HsqlName(this, paramString, paramBoolean, 9, null);
    localHsqlName.schema = paramHsqlName.schema;
    localHsqlName.parent = paramHsqlName;
    return localHsqlName;
  }
  
  public HsqlName getSubqueryTableName()
  {
    return this.subqueryTableName;
  }
  
  public HsqlName newAutoName(String paramString, HsqlName paramHsqlName1, HsqlName paramHsqlName2, int paramInt)
  {
    HsqlName localHsqlName = newAutoName(paramString, (String)null, paramHsqlName1, paramHsqlName2, paramInt);
    return localHsqlName;
  }
  
  public HsqlName newSpecificRoutineName(HsqlName paramHsqlName)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramHsqlName.name).append('_').append(++this.sysNumber);
    HsqlName localHsqlName = new HsqlName(this, localStringBuffer.toString(), 24, paramHsqlName.isNameQuoted, null);
    localHsqlName.parent = paramHsqlName;
    localHsqlName.schema = paramHsqlName.schema;
    return localHsqlName;
  }
  
  public static HsqlName getAutoColumnName(int paramInt)
  {
    if (paramInt < autoColumnNames.length) {
      return autoColumnNames[paramInt];
    }
    return new HsqlName(staticManager, "C_" + (paramInt + 1), 0, false, null);
  }
  
  public static String getAutoColumnNameString(int paramInt)
  {
    if (paramInt < autoColumnNames.length) {
      return autoColumnNames[paramInt].name;
    }
    return "C" + (paramInt + 1);
  }
  
  public static String getAutoNoNameColumnString(int paramInt)
  {
    if (paramInt < autoColumnNames.length) {
      return autoNoNameColumnNames[paramInt];
    }
    return String.valueOf(paramInt);
  }
  
  public static String getAutoSavepointNameString(long paramLong, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer("S");
    localStringBuffer.append(paramLong).append('_').append(paramInt);
    return localStringBuffer.toString();
  }
  
  public HsqlName newAutoName(String paramString1, String paramString2, HsqlName paramHsqlName1, HsqlName paramHsqlName2, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramString1 != null)
    {
      if (paramString1.length() != 0)
      {
        localStringBuffer.append("SYS_");
        localStringBuffer.append(paramString1);
        localStringBuffer.append('_');
        if (paramString2 != null)
        {
          localStringBuffer.append(paramString2);
          localStringBuffer.append('_');
        }
        localStringBuffer.append(++this.sysNumber);
      }
    }
    else {
      localStringBuffer.append(paramString2);
    }
    HsqlName localHsqlName = new HsqlName(this, localStringBuffer.toString(), paramInt, false, null);
    localHsqlName.schema = paramHsqlName1;
    localHsqlName.parent = paramHsqlName2;
    return localHsqlName;
  }
  
  void resetNumbering()
  {
    this.sysNumber = 0;
    this.serialNumber = 0;
  }
  
  public static SimpleName getSimpleName(String paramString, boolean paramBoolean)
  {
    return new SimpleName(paramString, paramBoolean, null);
  }
  
  static
  {
    staticManager.serialNumber = -2147483648;
    autoColumnNames = new HsqlName[32];
    autoNoNameColumnNames = new String[32];
    for (int i = 0; i < autoColumnNames.length; i++)
    {
      autoColumnNames[i] = new HsqlName(staticManager, "C" + (i + 1), 0, false, null);
      autoNoNameColumnNames[i] = String.valueOf(i);
    }
  }
  
  public static final class HsqlName
    extends HsqlNameManager.SimpleName
  {
    static HsqlName[] emptyArray = new HsqlName[0];
    HsqlNameManager manager;
    public String statementName;
    public String comment;
    public HsqlName schema;
    public HsqlName parent;
    public Grantee owner;
    public final int type;
    private final int hashCode;
    static final String[] sysPrefixes = { "SYS_IDX_", "SYS_PK_", "SYS_REF_", "SYS_CT_", "SYS_FK_" };
    
    private HsqlName(HsqlNameManager paramHsqlNameManager, int paramInt)
    {
      super();
      this.manager = paramHsqlNameManager;
      this.type = paramInt;
      this.hashCode = HsqlNameManager.access$408(this.manager);
    }
    
    private HsqlName(HsqlNameManager paramHsqlNameManager, String paramString, boolean paramBoolean, int paramInt)
    {
      this(paramHsqlNameManager, paramInt);
      rename(paramString, paramBoolean);
    }
    
    private HsqlName(HsqlNameManager paramHsqlNameManager, String paramString, int paramInt, boolean paramBoolean)
    {
      this(paramHsqlNameManager, paramInt);
      this.name = paramString;
      this.statementName = paramString;
      this.isNameQuoted = paramBoolean;
      if (this.isNameQuoted) {
        this.statementName = StringConverter.toQuotedString(paramString, '"', true);
      }
    }
    
    public String getStatementName()
    {
      return this.statementName;
    }
    
    public String getSchemaQualifiedStatementName()
    {
      switch (this.type)
      {
      case 22: 
      case 23: 
        return this.statementName;
      case 9: 
        if ((this.parent == null) || ("SYSTEM_SUBQUERY".equals(this.parent.name))) {
          return this.statementName;
        }
        localStringBuffer = new StringBuffer();
        if (this.schema != null)
        {
          localStringBuffer.append(this.schema.getStatementName());
          localStringBuffer.append('.');
        }
        localStringBuffer.append(this.parent.getStatementName());
        localStringBuffer.append('.');
        localStringBuffer.append(this.statementName);
        return localStringBuffer.toString();
      }
      if (this.schema == null) {
        return this.statementName;
      }
      StringBuffer localStringBuffer = new StringBuffer();
      if (this.schema != null)
      {
        localStringBuffer.append(this.schema.getStatementName());
        localStringBuffer.append('.');
      }
      localStringBuffer.append(this.statementName);
      return localStringBuffer.toString();
    }
    
    public void rename(HsqlName paramHsqlName)
    {
      rename(paramHsqlName.name, paramHsqlName.isNameQuoted);
    }
    
    public void rename(String paramString, boolean paramBoolean)
    {
      if ((this.manager.sqlRegularNames) && (paramString.length() > 128)) {
        throw Error.error(5501, paramString);
      }
      this.name = new String(paramString);
      this.statementName = this.name;
      this.isNameQuoted = paramBoolean;
      if (this.isNameQuoted) {
        this.statementName = StringConverter.toQuotedString(paramString, '"', true);
      }
      if (paramString.startsWith("SYS_"))
      {
        int i = paramString.lastIndexOf(95) + 1;
        try
        {
          int j = Integer.parseInt(paramString.substring(i));
          if (j > this.manager.sysNumber) {
            this.manager.sysNumber = j;
          }
        }
        catch (NumberFormatException localNumberFormatException) {}
      }
    }
    
    void rename(String paramString1, String paramString2, boolean paramBoolean)
    {
      StringBuffer localStringBuffer = new StringBuffer(paramString1);
      localStringBuffer.append('_');
      localStringBuffer.append(paramString2);
      rename(localStringBuffer.toString(), paramBoolean);
    }
    
    public void setSchemaIfNull(HsqlName paramHsqlName)
    {
      if (this.schema == null) {
        this.schema = paramHsqlName;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof HsqlName)) {
        return this.hashCode == ((HsqlName)paramObject).hashCode;
      }
      return false;
    }
    
    public int hashCode()
    {
      return this.hashCode;
    }
    
    static int sysPrefixLength(String paramString)
    {
      for (int i = 0; i < sysPrefixes.length; i++) {
        if (paramString.startsWith(sysPrefixes[i])) {
          return sysPrefixes[i].length();
        }
      }
      return 0;
    }
    
    static boolean isReservedName(String paramString)
    {
      return sysPrefixLength(paramString) > 0;
    }
    
    boolean isReservedName()
    {
      return isReservedName(this.name);
    }
    
    public String toString()
    {
      return getClass().getName() + super.hashCode() + "[this.hashCode()=" + this.hashCode + ", name=" + this.name + ", name.hashCode()=" + this.name.hashCode() + ", isNameQuoted=" + this.isNameQuoted + "]";
    }
    
    public int compareTo(Object paramObject)
    {
      return this.hashCode - paramObject.hashCode();
    }
    
    static boolean isRegularIdentifier(String paramString)
    {
      int i = 0;
      int j = paramString.length();
      while (i < j)
      {
        int k = paramString.charAt(i);
        if (((k < 65) || (k > 90)) && ((k != 95) || (i <= 0)) && ((k < 48) || (k > 57))) {
          return false;
        }
        i++;
      }
      return !Tokens.isKeyword(paramString);
    }
  }
  
  public static class SimpleName
  {
    public String name;
    public boolean isNameQuoted;
    
    private SimpleName() {}
    
    private SimpleName(String paramString, boolean paramBoolean)
    {
      this.name = new String(paramString);
      this.isNameQuoted = paramBoolean;
    }
    
    public int hashCode()
    {
      return this.name.hashCode();
    }
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof SimpleName)) {
        return (((SimpleName)paramObject).isNameQuoted == this.isNameQuoted) && (((SimpleName)paramObject).name.equals(this.name));
      }
      return false;
    }
    
    public String getStatementName()
    {
      return this.isNameQuoted ? StringConverter.toQuotedString(this.name, '"', true) : this.name;
    }
    
    public String getNameString()
    {
      return this.name;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.HsqlNameManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */