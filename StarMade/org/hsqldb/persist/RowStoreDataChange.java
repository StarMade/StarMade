package org.hsqldb.persist;

import java.io.IOException;
import org.hsqldb.HsqlException;
import org.hsqldb.RowDiskDataChange;
import org.hsqldb.Session;
import org.hsqldb.TableBase;
import org.hsqldb.rowio.RowInputInterface;

public class RowStoreDataChange
  extends RowStoreAVLHybrid
{
  public RowStoreDataChange(Session paramSession, PersistentStoreCollection paramPersistentStoreCollection, TableBase paramTableBase)
  {
    super(paramSession, paramPersistentStoreCollection, paramTableBase, true);
    super.changeToDiskTable(paramSession);
  }
  
  public CachedObject getNewCachedObject(Session paramSession, Object paramObject, boolean paramBoolean)
  {
    RowDiskDataChange localRowDiskDataChange = new RowDiskDataChange(this.table, (Object[])paramObject, this, null);
    add(localRowDiskDataChange);
    return localRowDiskDataChange;
  }
  
  public CachedObject get(RowInputInterface paramRowInputInterface)
  {
    try
    {
      return new RowDiskDataChange(this.session, this.table, paramRowInputInterface);
    }
    catch (HsqlException localHsqlException)
    {
      return null;
    }
    catch (IOException localIOException) {}
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.persist.RowStoreDataChange
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */