package org.hsqldb;

import java.io.IOException;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputBinary;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.Type;

public class RowDiskDataChange extends RowAVLDisk
{
  public static final int COL_POS_ROW_NUM = 0;
  public static final int COL_POS_ROW_ID = 1;
  public static final int COL_POS_TABLE_ID = 2;
  public static final int COL_POS_SCHEMA_NAME = 3;
  public static final int COL_POS_TABLE_NAME = 4;
  public static final int COL_POS_IS_UPDATE = 5;
  static final Type[] arrayType = { new ArrayType(Type.SQL_INTEGER, 2147483647) };
  Table targetTable;
  Object[] updateData;
  int[] updateColMap;

  public RowDiskDataChange(TableBase paramTableBase, Object[] paramArrayOfObject, PersistentStore paramPersistentStore, Table paramTable)
  {
    super(paramTableBase, paramArrayOfObject, paramPersistentStore);
    this.targetTable = paramTable;
  }

  public RowDiskDataChange(Session paramSession, TableBase paramTableBase, RowInputInterface paramRowInputInterface)
    throws IOException
  {
    super(paramTableBase, paramRowInputInterface);
    this.targetTable = paramTableBase.database.schemaManager.getTable(paramSession, (String)this.rowData[4], (String)this.rowData[3]);
    if (((Boolean)this.rowData[5]).booleanValue())
    {
      this.updateData = paramRowInputInterface.readData(this.targetTable.colTypes);
      RowInputBinary localRowInputBinary = (RowInputBinary)paramRowInputInterface;
      if (localRowInputBinary.readNull())
        this.updateColMap = null;
      else
        this.updateColMap = localRowInputBinary.readIntArray();
    }
    else
    {
      this.updateData = null;
      this.updateColMap = null;
    }
  }

  public void write(RowOutputInterface paramRowOutputInterface)
  {
    writeNodes(paramRowOutputInterface);
    if (this.hasDataChanged)
    {
      paramRowOutputInterface.writeData(this, this.table.colTypes);
      if (this.updateData != null)
      {
        Type[] arrayOfType = this.targetTable.colTypes;
        paramRowOutputInterface.writeData(arrayOfType.length, arrayOfType, this.updateData, null, null);
        RowOutputBinary localRowOutputBinary = (RowOutputBinary)paramRowOutputInterface;
        if (this.updateColMap == null)
          localRowOutputBinary.writeNull(Type.SQL_ARRAY_ALL_TYPES);
        else
          localRowOutputBinary.writeArray(this.updateColMap);
      }
      paramRowOutputInterface.writeEnd();
      this.hasDataChanged = false;
    }
  }

  public Object[] getUpdateData()
  {
    return this.updateData;
  }

  public int[] getUpdateColumnMap()
  {
    return this.updateColMap;
  }

  public void setTargetTable(Table paramTable)
  {
    this.targetTable = paramTable;
  }

  public void setUpdateData(Object[] paramArrayOfObject)
  {
    this.updateData = paramArrayOfObject;
  }

  public void setUpdateColumnMap(int[] paramArrayOfInt)
  {
    this.updateColMap = paramArrayOfInt;
  }

  public int getRealSize(RowOutputInterface paramRowOutputInterface)
  {
    RowOutputBinary localRowOutputBinary = (RowOutputBinary)paramRowOutputInterface;
    int i = paramRowOutputInterface.getSize(this);
    if (this.updateData != null)
    {
      i += localRowOutputBinary.getSize(this.updateData, this.targetTable.getColumnCount(), this.targetTable.getColumnTypes());
      if (this.updateColMap != null)
        i += localRowOutputBinary.getSize(this.updateColMap);
    }
    return i;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.RowDiskDataChange
 * JD-Core Version:    0.6.2
 */