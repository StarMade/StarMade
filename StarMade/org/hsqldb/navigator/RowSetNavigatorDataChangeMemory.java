package org.hsqldb.navigator;

import java.io.IOException;
import org.hsqldb.Database;
import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.OrderedLongKeyHashMap;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.types.Type;

public class RowSetNavigatorDataChangeMemory
  implements RowSetNavigatorDataChange
{
  public static RowSetNavigatorDataChangeMemory emptyRowSet = new RowSetNavigatorDataChangeMemory(null);
  int size;
  int currentPos = -1;
  OrderedLongKeyHashMap list;
  Session session;
  
  public RowSetNavigatorDataChangeMemory(Session paramSession)
  {
    this.session = paramSession;
    this.list = new OrderedLongKeyHashMap(64, true);
  }
  
  public void release()
  {
    beforeFirst();
    this.list.clear();
    this.size = 0;
  }
  
  public int getSize()
  {
    return this.size;
  }
  
  public int getRowPosition()
  {
    return this.currentPos;
  }
  
  public boolean next()
  {
    if (this.currentPos < this.size - 1)
    {
      this.currentPos += 1;
      return true;
    }
    this.currentPos = (this.size - 1);
    return false;
  }
  
  public boolean beforeFirst()
  {
    this.currentPos = -1;
    return true;
  }
  
  public Row getCurrentRow()
  {
    return (Row)this.list.getValueByIndex(this.currentPos);
  }
  
  public Object[] getCurrentChangedData()
  {
    return (Object[])this.list.getSecondValueByIndex(this.currentPos);
  }
  
  public int[] getCurrentChangedColumns()
  {
    return (int[])this.list.getThirdValueByIndex(this.currentPos);
  }
  
  public void write(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData)
    throws IOException
  {}
  
  public void read(RowInputInterface paramRowInputInterface, ResultMetaData paramResultMetaData)
    throws IOException
  {}
  
  public void endMainDataSet() {}
  
  public boolean addRow(Row paramRow)
  {
    int i = this.list.getLookup(paramRow.getId());
    if (i == -1)
    {
      this.list.put(paramRow.getId(), paramRow, null);
      this.size += 1;
      return true;
    }
    if (this.list.getSecondValueByIndex(i) != null)
    {
      if (this.session.database.sqlEnforceTDCD) {
        throw Error.error(3900);
      }
      this.list.setSecondValueByIndex(i, null);
      this.list.setThirdValueByIndex(i, null);
      return true;
    }
    return false;
  }
  
  public Object[] addRow(Session paramSession, Row paramRow, Object[] paramArrayOfObject, Type[] paramArrayOfType, int[] paramArrayOfInt)
  {
    long l = paramRow.getId();
    int i = this.list.getLookup(l);
    if (i == -1)
    {
      this.list.put(l, paramRow, paramArrayOfObject);
      this.list.setThirdValueByIndex(this.size, paramArrayOfInt);
      this.size += 1;
      return paramArrayOfObject;
    }
    Object[] arrayOfObject1 = ((Row)this.list.getFirstByLookup(i)).getData();
    Object[] arrayOfObject2 = (Object[])this.list.getSecondValueByIndex(i);
    if (arrayOfObject2 == null)
    {
      if (paramSession.database.sqlEnforceTDCD) {
        throw Error.error(3900);
      }
      return null;
    }
    for (int j = 0; j < paramArrayOfInt.length; j++)
    {
      int k = paramArrayOfInt[j];
      if (paramArrayOfType[k].compare(paramSession, paramArrayOfObject[k], arrayOfObject2[k]) != 0) {
        if (paramArrayOfType[k].compare(paramSession, arrayOfObject1[k], arrayOfObject2[k]) != 0)
        {
          if (paramSession.database.sqlEnforceTDCU) {
            throw Error.error(3900);
          }
        }
        else {
          arrayOfObject2[k] = paramArrayOfObject[k];
        }
      }
    }
    int[] arrayOfInt = (int[])this.list.getThirdValueByIndex(i);
    arrayOfInt = ArrayUtil.union(arrayOfInt, paramArrayOfInt);
    this.list.setThirdValueByIndex(i, arrayOfInt);
    return arrayOfObject2;
  }
  
  public boolean containsDeletedRow(Row paramRow)
  {
    int i = this.list.getLookup(paramRow.getId());
    if (i == -1) {
      return false;
    }
    Object[] arrayOfObject = (Object[])this.list.getSecondValueByIndex(i);
    return arrayOfObject == null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.navigator.RowSetNavigatorDataChangeMemory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */