package org.hsqldb.dbinfo;

import java.util.Locale;
import org.hsqldb.ColumnSchema;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.Table;
import org.hsqldb.resources.BundleHandler;
import org.hsqldb.rights.Grantee;
import org.hsqldb.store.ValuePool;

final class DITableInfo
{
  int bestRowTemporary = 0;
  int bestRowTransaction = 1;
  int bestRowSession = 2;
  int bestRowUnknown = 0;
  int bestRowNotPseudo = 1;
  static final short tableIndexOther = 3;
  private static final int HALF_MAX_INT = 1073741823;
  private int hnd_column_remarks = -1;
  private int hnd_table_remarks = -1;
  private Table table;
  
  DITableInfo()
  {
    setupBundles();
  }
  
  void setupBundles()
  {
    synchronized (BundleHandler.class)
    {
      Locale localLocale = BundleHandler.getLocale();
      BundleHandler.setLocale(Locale.getDefault());
      this.hnd_column_remarks = BundleHandler.getBundleHandle("column-remarks", null);
      this.hnd_table_remarks = BundleHandler.getBundleHandle("table-remarks", null);
      BundleHandler.setLocale(localLocale);
    }
  }
  
  Integer getBRIPseudo()
  {
    return ValuePool.getInt(this.bestRowNotPseudo);
  }
  
  Integer getBRIScope()
  {
    return this.table.isWritable() ? ValuePool.getInt(this.bestRowTemporary) : ValuePool.getInt(this.bestRowSession);
  }
  
  String getColName(int paramInt)
  {
    return this.table.getColumn(paramInt).getName().name;
  }
  
  String getColRemarks(int paramInt)
  {
    if (this.table.getTableType() != 1) {
      return this.table.getColumn(paramInt).getName().comment;
    }
    String str = getName() + "_" + getColName(paramInt);
    return BundleHandler.getString(this.hnd_column_remarks, str);
  }
  
  String getHsqlType()
  {
    switch (this.table.getTableType())
    {
    case 1: 
    case 3: 
    case 4: 
      return "MEMORY";
    case 5: 
      return "CACHED";
    case 6: 
    case 7: 
      return "TEXT";
    }
    return null;
  }
  
  String getName()
  {
    return this.table.getName().name;
  }
  
  String getRemark()
  {
    return this.table.getTableType() == 1 ? BundleHandler.getString(this.hnd_table_remarks, getName()) : this.table.getName().comment;
  }
  
  String getJDBCStandardType()
  {
    switch (this.table.getTableType())
    {
    case 8: 
      return "VIEW";
    case 3: 
    case 6: 
      return "GLOBAL TEMPORARY";
    case 1: 
      return "SYSTEM TABLE";
    }
    if (this.table.getOwner().isSystem()) {
      return "SYSTEM TABLE";
    }
    return "TABLE";
  }
  
  Table getTable()
  {
    return this.table;
  }
  
  void setTable(Table paramTable)
  {
    this.table = paramTable;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.dbinfo.DITableInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */