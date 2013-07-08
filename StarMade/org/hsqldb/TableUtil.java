package org.hsqldb;

import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.types.Type;

public class TableUtil
{
  static Table newSingleColumnTable(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName1, int paramInt, HsqlNameManager.HsqlName paramHsqlName2, Type paramType)
  {
    TableDerived localTableDerived = new TableDerived(paramDatabase, paramHsqlName1, paramInt);
    ColumnSchema localColumnSchema = new ColumnSchema(paramHsqlName2, paramType, false, true, null);
    localTableDerived.addColumn(localColumnSchema);
    localTableDerived.createPrimaryKeyConstraint(localTableDerived.getName(), new int[] { 0 }, true);
    return localTableDerived;
  }
  
  static void setTableIndexesForSubquery(Table paramTable, boolean paramBoolean1, boolean paramBoolean2)
  {
    int[] arrayOfInt = null;
    if (paramBoolean1)
    {
      arrayOfInt = new int[paramTable.getColumnCount()];
      ArrayUtil.fillSequence(arrayOfInt);
    }
    paramTable.createPrimaryKey(null, paramBoolean2 ? arrayOfInt : null, false);
    if (paramBoolean2) {
      paramTable.fullIndex = paramTable.getPrimaryIndex();
    } else if (paramBoolean1) {
      paramTable.fullIndex = paramTable.createIndexForColumns(null, arrayOfInt);
    }
  }
  
  public static void addAutoColumns(Table paramTable, Type[] paramArrayOfType)
  {
    for (int i = 0; i < paramArrayOfType.length; i++)
    {
      ColumnSchema localColumnSchema = new ColumnSchema(HsqlNameManager.getAutoColumnName(i), paramArrayOfType[i], true, false, null);
      paramTable.addColumnNoCheck(localColumnSchema);
    }
  }
  
  public static void setColumnsInSchemaTable(Table paramTable, HsqlNameManager.HsqlName[] paramArrayOfHsqlName, Type[] paramArrayOfType)
  {
    for (int i = 0; i < paramArrayOfHsqlName.length; i++)
    {
      HsqlNameManager.HsqlName localHsqlName = paramArrayOfHsqlName[i];
      localHsqlName = paramTable.database.nameManager.newColumnSchemaHsqlName(paramTable.getName(), localHsqlName);
      ColumnSchema localColumnSchema = new ColumnSchema(localHsqlName, paramArrayOfType[i], true, false, null);
      paramTable.addColumn(localColumnSchema);
    }
    paramTable.setColumnStructures();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.TableUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */