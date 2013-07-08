package org.hsqldb.navigator;

import java.io.IOException;
import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.types.Type;

public abstract interface RowSetNavigatorDataChange
{
  public abstract void release();
  
  public abstract int getSize();
  
  public abstract int getRowPosition();
  
  public abstract boolean next();
  
  public abstract boolean beforeFirst();
  
  public abstract Row getCurrentRow();
  
  public abstract Object[] getCurrentChangedData();
  
  public abstract int[] getCurrentChangedColumns();
  
  public abstract void write(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData)
    throws IOException;
  
  public abstract void read(RowInputInterface paramRowInputInterface, ResultMetaData paramResultMetaData)
    throws IOException;
  
  public abstract void endMainDataSet();
  
  public abstract boolean addRow(Row paramRow);
  
  public abstract Object[] addRow(Session paramSession, Row paramRow, Object[] paramArrayOfObject, Type[] paramArrayOfType, int[] paramArrayOfInt);
  
  public abstract boolean containsDeletedRow(Row paramRow);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.navigator.RowSetNavigatorDataChange
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */