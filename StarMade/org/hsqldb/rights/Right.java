package org.hsqldb.rights;

import org.hsqldb.ColumnSchema;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.SchemaObject;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashSet;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;

public final class Right
{
  boolean isFull = false;
  boolean isFullSelect;
  boolean isFullInsert;
  boolean isFullUpdate;
  boolean isFullReferences;
  boolean isFullTrigger;
  boolean isFullDelete;
  OrderedHashSet selectColumnSet;
  OrderedHashSet insertColumnSet;
  OrderedHashSet updateColumnSet;
  OrderedHashSet referencesColumnSet;
  OrderedHashSet triggerColumnSet;
  Right grantableRights;
  Grantee grantor;
  Grantee grantee;
  public static final OrderedHashSet emptySet = new OrderedHashSet();
  public static final Right fullRights = new Right();
  public static final Right noRights = new Right();
  static final OrderedHashSet fullRightsSet = new OrderedHashSet();
  public static final String[] privilegeNames = { "SELECT", "INSERT", "UPDATE", "DELETE", "REFERENCES", "TRIGGER" };
  public static final int[] privilegeTypes = { 1, 4, 8, 2, 64, 128 };

  public Right()
  {
  }

  Right(Table paramTable)
  {
    this.isFullDelete = true;
    this.selectColumnSet = paramTable.getColumnNameSet();
    this.insertColumnSet = paramTable.getColumnNameSet();
    this.updateColumnSet = paramTable.getColumnNameSet();
    this.referencesColumnSet = paramTable.getColumnNameSet();
    this.triggerColumnSet = paramTable.getColumnNameSet();
  }

  public boolean isFull()
  {
    return this.isFull;
  }

  public Grantee getGrantor()
  {
    return this.grantor;
  }

  public Grantee getGrantee()
  {
    return this.grantee;
  }

  public Right getGrantableRights()
  {
    return this.grantableRights == null ? noRights : this.grantableRights;
  }

  public Right duplicate()
  {
    Right localRight = new Right();
    localRight.add(this);
    return localRight;
  }

  public void add(Right paramRight)
  {
    if (this.isFull)
      return;
    if (paramRight.isFull)
    {
      clear();
      this.isFull = true;
      return;
    }
    this.isFullSelect |= paramRight.isFullSelect;
    this.isFullInsert |= paramRight.isFullInsert;
    this.isFullUpdate |= paramRight.isFullUpdate;
    this.isFullReferences |= paramRight.isFullReferences;
    this.isFullDelete |= paramRight.isFullDelete;
    if (this.isFullSelect)
    {
      this.selectColumnSet = null;
    }
    else if (paramRight.selectColumnSet != null)
    {
      if (this.selectColumnSet == null)
        this.selectColumnSet = new OrderedHashSet();
      this.selectColumnSet.addAll(paramRight.selectColumnSet);
    }
    if (this.isFullInsert)
    {
      this.insertColumnSet = null;
    }
    else if (paramRight.insertColumnSet != null)
    {
      if (this.insertColumnSet == null)
        this.insertColumnSet = new OrderedHashSet();
      this.insertColumnSet.addAll(paramRight.insertColumnSet);
    }
    if (this.isFullUpdate)
    {
      this.updateColumnSet = null;
    }
    else if (paramRight.updateColumnSet != null)
    {
      if (this.updateColumnSet == null)
        this.updateColumnSet = new OrderedHashSet();
      this.updateColumnSet.addAll(paramRight.updateColumnSet);
    }
    if (this.isFullReferences)
    {
      this.referencesColumnSet = null;
    }
    else if (paramRight.referencesColumnSet != null)
    {
      if (this.referencesColumnSet == null)
        this.referencesColumnSet = new OrderedHashSet();
      this.referencesColumnSet.addAll(paramRight.referencesColumnSet);
    }
    if (this.isFullTrigger)
    {
      this.triggerColumnSet = null;
    }
    else if (paramRight.triggerColumnSet != null)
    {
      if (this.triggerColumnSet == null)
        this.triggerColumnSet = new OrderedHashSet();
      this.triggerColumnSet.addAll(paramRight.triggerColumnSet);
    }
  }

  public void remove(SchemaObject paramSchemaObject, Right paramRight)
  {
    if (paramRight.isFull)
    {
      clear();
      return;
    }
    if (this.isFull)
    {
      this.isFull = false;
      this.isFullSelect = (this.isFullInsert = this.isFullUpdate = this.isFullReferences = this.isFullDelete = 1);
    }
    if (paramRight.isFullDelete)
      this.isFullDelete = false;
    if ((this.isFullSelect) || (this.selectColumnSet != null))
      if (paramRight.isFullSelect)
      {
        this.isFullSelect = false;
        this.selectColumnSet = null;
      }
      else if (paramRight.selectColumnSet != null)
      {
        if (this.isFullSelect)
        {
          this.isFullSelect = false;
          this.selectColumnSet = ((Table)paramSchemaObject).getColumnNameSet();
        }
        this.selectColumnSet.removeAll(paramRight.selectColumnSet);
        if (this.selectColumnSet.isEmpty())
          this.selectColumnSet = null;
      }
    if ((this.isFullInsert) || (this.insertColumnSet != null))
      if (paramRight.isFullInsert)
      {
        this.isFullInsert = false;
        this.insertColumnSet = null;
      }
      else if (paramRight.insertColumnSet != null)
      {
        if (this.isFullInsert)
        {
          this.isFullInsert = false;
          this.insertColumnSet = ((Table)paramSchemaObject).getColumnNameSet();
        }
        this.insertColumnSet.removeAll(paramRight.insertColumnSet);
        if (this.insertColumnSet.isEmpty())
          this.insertColumnSet = null;
      }
    if ((this.isFullUpdate) || (this.updateColumnSet != null))
      if (paramRight.isFullUpdate)
      {
        this.isFullUpdate = false;
        this.updateColumnSet = null;
      }
      else if (paramRight.updateColumnSet != null)
      {
        if (this.isFullUpdate)
        {
          this.isFullUpdate = false;
          this.updateColumnSet = ((Table)paramSchemaObject).getColumnNameSet();
        }
        this.updateColumnSet.removeAll(paramRight.updateColumnSet);
        if (this.updateColumnSet.isEmpty())
          this.updateColumnSet = null;
      }
    if ((this.isFullReferences) || (this.referencesColumnSet != null))
      if (paramRight.isFullReferences)
      {
        this.isFullReferences = false;
        this.referencesColumnSet = null;
      }
      else if (paramRight.referencesColumnSet != null)
      {
        if (this.isFullReferences)
        {
          this.isFullReferences = false;
          this.referencesColumnSet = ((Table)paramSchemaObject).getColumnNameSet();
        }
        this.referencesColumnSet.removeAll(paramRight.referencesColumnSet);
        if (this.referencesColumnSet.isEmpty())
          this.referencesColumnSet = null;
      }
    if ((this.isFullTrigger) || (this.triggerColumnSet != null))
      if (paramRight.isFullTrigger)
      {
        this.isFullTrigger = false;
        this.triggerColumnSet = null;
      }
      else if (paramRight.triggerColumnSet != null)
      {
        if (this.isFullTrigger)
        {
          this.isFullTrigger = false;
          this.triggerColumnSet = ((Table)paramSchemaObject).getColumnNameSet();
        }
        this.triggerColumnSet.removeAll(paramRight.triggerColumnSet);
        if (this.triggerColumnSet.isEmpty())
          this.triggerColumnSet = null;
      }
  }

  void clear()
  {
    this.isFull = (this.isFullSelect = this.isFullInsert = this.isFullUpdate = this.isFullReferences = this.isFullDelete = 0);
    this.selectColumnSet = (this.insertColumnSet = this.updateColumnSet = this.referencesColumnSet = this.triggerColumnSet = null);
  }

  public boolean isEmpty()
  {
    if ((this.isFull) || (this.isFullSelect) || (this.isFullInsert) || (this.isFullUpdate) || (this.isFullReferences) || (this.isFullDelete))
      return false;
    if ((this.selectColumnSet != null) && (!this.selectColumnSet.isEmpty()))
      return false;
    if ((this.insertColumnSet != null) && (!this.insertColumnSet.isEmpty()))
      return false;
    if ((this.updateColumnSet != null) && (!this.updateColumnSet.isEmpty()))
      return false;
    if ((this.referencesColumnSet != null) && (!this.referencesColumnSet.isEmpty()))
      return false;
    return (this.triggerColumnSet == null) || (this.triggerColumnSet.isEmpty());
  }

  OrderedHashSet getColumnsForAllRights(Table paramTable)
  {
    if (this.isFull)
      return paramTable.getColumnNameSet();
    if ((this.isFullSelect) || (this.isFullInsert) || (this.isFullUpdate) || (this.isFullReferences))
      return paramTable.getColumnNameSet();
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    if (this.selectColumnSet != null)
      localOrderedHashSet.addAll(this.selectColumnSet);
    if (this.insertColumnSet != null)
      localOrderedHashSet.addAll(this.insertColumnSet);
    if (this.updateColumnSet != null)
      localOrderedHashSet.addAll(this.updateColumnSet);
    if (this.referencesColumnSet != null)
      localOrderedHashSet.addAll(this.referencesColumnSet);
    return localOrderedHashSet;
  }

  public boolean contains(Right paramRight)
  {
    if (this.isFull)
      return true;
    if (paramRight.isFull)
      return false;
    if (!containsRights(this.isFullSelect, this.selectColumnSet, paramRight.selectColumnSet, paramRight.isFullSelect))
      return false;
    if (!containsRights(this.isFullInsert, this.insertColumnSet, paramRight.insertColumnSet, paramRight.isFullInsert))
      return false;
    if (!containsRights(this.isFullUpdate, this.updateColumnSet, paramRight.updateColumnSet, paramRight.isFullUpdate))
      return false;
    if (!containsRights(this.isFullReferences, this.referencesColumnSet, paramRight.referencesColumnSet, paramRight.isFullReferences))
      return false;
    if (!containsRights(this.isFullTrigger, this.triggerColumnSet, paramRight.triggerColumnSet, paramRight.isFullTrigger))
      return false;
    return (this.isFullDelete) || (!paramRight.isFullDelete);
  }

  void removeDroppedColumns(OrderedHashSet paramOrderedHashSet, Table paramTable)
  {
    for (int i = 0; i < paramOrderedHashSet.size(); i++)
    {
      HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)paramOrderedHashSet.get(i);
      if (paramTable.findColumn(localHsqlName.name) >= 0)
      {
        paramOrderedHashSet.remove(i);
        i--;
      }
    }
  }

  public OrderedHashSet getColumnsForPrivilege(Table paramTable, int paramInt)
  {
    if (this.isFull)
      return paramTable.getColumnNameSet();
    switch (paramInt)
    {
    case 1:
      return this.selectColumnSet == null ? emptySet : this.isFullSelect ? paramTable.getColumnNameSet() : this.selectColumnSet;
    case 4:
      return this.insertColumnSet == null ? emptySet : this.isFullInsert ? paramTable.getColumnNameSet() : this.insertColumnSet;
    case 8:
      return this.updateColumnSet == null ? emptySet : this.isFullUpdate ? paramTable.getColumnNameSet() : this.updateColumnSet;
    case 64:
      return this.referencesColumnSet == null ? emptySet : this.isFullReferences ? paramTable.getColumnNameSet() : this.referencesColumnSet;
    case 128:
      return this.triggerColumnSet == null ? emptySet : this.isFullTrigger ? paramTable.getColumnNameSet() : this.triggerColumnSet;
    }
    return emptySet;
  }

  static boolean containsAllColumns(OrderedHashSet paramOrderedHashSet, Table paramTable, boolean[] paramArrayOfBoolean)
  {
    for (int i = 0; i < paramArrayOfBoolean.length; i++)
      if (paramArrayOfBoolean[i] != 0)
      {
        if (paramOrderedHashSet == null)
          return false;
        if (!paramOrderedHashSet.contains(paramTable.getColumn(i).getName()))
          return false;
      }
    return true;
  }

  static boolean containsRights(boolean paramBoolean1, OrderedHashSet paramOrderedHashSet1, OrderedHashSet paramOrderedHashSet2, boolean paramBoolean2)
  {
    if (paramBoolean1)
      return true;
    if (paramBoolean2)
      return false;
    return (paramOrderedHashSet2 == null) || ((paramOrderedHashSet1 != null) && (paramOrderedHashSet1.containsAll(paramOrderedHashSet2)));
  }

  boolean canSelect(Table paramTable, boolean[] paramArrayOfBoolean)
  {
    if ((this.isFull) || (this.isFullSelect))
      return true;
    return containsAllColumns(this.selectColumnSet, paramTable, paramArrayOfBoolean);
  }

  boolean canInsert(Table paramTable, boolean[] paramArrayOfBoolean)
  {
    if ((this.isFull) || (this.isFullInsert))
      return true;
    return containsAllColumns(this.insertColumnSet, paramTable, paramArrayOfBoolean);
  }

  boolean canUpdate(Table paramTable, boolean[] paramArrayOfBoolean)
  {
    if ((this.isFull) || (this.isFullUpdate))
      return true;
    return containsAllColumns(this.updateColumnSet, paramTable, paramArrayOfBoolean);
  }

  boolean canReference(Table paramTable, boolean[] paramArrayOfBoolean)
  {
    if ((this.isFull) || (this.isFullReferences))
      return true;
    return containsAllColumns(this.referencesColumnSet, paramTable, paramArrayOfBoolean);
  }

  boolean canTrigger(Table paramTable, boolean[] paramArrayOfBoolean)
  {
    if ((this.isFull) || (this.isFullTrigger))
      return true;
    return containsAllColumns(this.triggerColumnSet, paramTable, paramArrayOfBoolean);
  }

  boolean canDelete()
  {
    return (this.isFull) || (this.isFullDelete);
  }

  public boolean canAccessFully(int paramInt)
  {
    if (this.isFull)
      return true;
    switch (paramInt)
    {
    case 2:
      return this.isFullDelete;
    case 1:
      return this.isFullSelect;
    case 4:
      return this.isFullInsert;
    case 8:
      return this.isFullUpdate;
    case 64:
      return this.isFullReferences;
    case 128:
      return this.isFullTrigger;
    case 32:
      return this.isFull;
    }
    throw Error.runtimeError(201, "Right");
  }

  public boolean canAcesssNonSelect()
  {
    if (this.isFull)
      return true;
    if ((this.isFullInsert) || (this.isFullUpdate) || (this.isFullDelete) || (this.isFullReferences) || (this.isFullTrigger))
      return true;
    boolean bool = false;
    bool |= ((this.insertColumnSet != null) && (!this.insertColumnSet.isEmpty()));
    bool |= ((this.updateColumnSet != null) && (!this.updateColumnSet.isEmpty()));
    bool |= ((this.referencesColumnSet != null) && (!this.referencesColumnSet.isEmpty()));
    bool |= ((this.triggerColumnSet != null) && (!this.triggerColumnSet.isEmpty()));
    return bool;
  }

  public boolean canAccess(int paramInt)
  {
    if (this.isFull)
      return true;
    switch (paramInt)
    {
    case 2:
      return this.isFullDelete;
    case 1:
      if (this.isFullSelect)
        return true;
      return (this.selectColumnSet != null) && (!this.selectColumnSet.isEmpty());
    case 4:
      if (this.isFullInsert)
        return true;
      return (this.insertColumnSet != null) && (!this.insertColumnSet.isEmpty());
    case 8:
      if (this.isFullUpdate)
        return true;
      return (this.updateColumnSet != null) && (!this.updateColumnSet.isEmpty());
    case 64:
      if (this.isFullReferences)
        return true;
      return (this.referencesColumnSet != null) && (!this.referencesColumnSet.isEmpty());
    case 128:
      if (this.isFullTrigger)
        return true;
      return (this.triggerColumnSet != null) && (!this.triggerColumnSet.isEmpty());
    case 32:
      return this.isFull;
    }
    throw Error.runtimeError(201, "Right");
  }

  public boolean canAccess(Table paramTable, int[] paramArrayOfInt)
  {
    if (this.isFull)
      return true;
    if ((this.isFullSelect) || (this.isFullInsert) || (this.isFullUpdate) || (this.isFullDelete) || (this.isFullReferences) || (this.isFullTrigger))
      return true;
    boolean bool = false;
    bool |= ((this.selectColumnSet != null) && (this.insertColumnSet.isEmpty()));
    bool |= ((this.insertColumnSet != null) && (this.insertColumnSet.isEmpty()));
    bool |= ((this.updateColumnSet != null) && (!this.updateColumnSet.isEmpty()));
    bool |= ((this.referencesColumnSet != null) && (!this.referencesColumnSet.isEmpty()));
    bool |= ((this.triggerColumnSet != null) && (!this.triggerColumnSet.isEmpty()));
    if (!bool)
      return false;
    HashSet localHashSet = new HashSet();
    localHashSet.addAll(this.selectColumnSet);
    localHashSet.addAll(this.insertColumnSet);
    localHashSet.addAll(this.updateColumnSet);
    localHashSet.addAll(this.referencesColumnSet);
    localHashSet.addAll(this.triggerColumnSet);
    for (int i = 0; i < paramArrayOfInt.length; i++)
      if (!localHashSet.contains(paramTable.getColumn(i).getName()))
        return false;
    return bool;
  }

  String getTableRightsSQL(Table paramTable)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (this.isFull)
      return "ALL";
    if (this.isFullSelect)
    {
      localStringBuffer.append("SELECT");
      localStringBuffer.append(',');
    }
    else if (this.selectColumnSet != null)
    {
      localStringBuffer.append("SELECT");
      getColumnList(paramTable, this.selectColumnSet, localStringBuffer);
      localStringBuffer.append(',');
    }
    if (this.isFullInsert)
    {
      localStringBuffer.append("INSERT");
      localStringBuffer.append(',');
    }
    else if (this.insertColumnSet != null)
    {
      localStringBuffer.append("INSERT");
      getColumnList(paramTable, this.insertColumnSet, localStringBuffer);
      localStringBuffer.append(',');
    }
    if (this.isFullUpdate)
    {
      localStringBuffer.append("UPDATE");
      localStringBuffer.append(',');
    }
    else if (this.updateColumnSet != null)
    {
      localStringBuffer.append("UPDATE");
      getColumnList(paramTable, this.updateColumnSet, localStringBuffer);
      localStringBuffer.append(',');
    }
    if (this.isFullDelete)
    {
      localStringBuffer.append("DELETE");
      localStringBuffer.append(',');
    }
    if (this.isFullReferences)
    {
      localStringBuffer.append("REFERENCES");
      localStringBuffer.append(',');
    }
    else if (this.referencesColumnSet != null)
    {
      localStringBuffer.append("REFERENCES");
      localStringBuffer.append(',');
    }
    if (this.isFullTrigger)
    {
      localStringBuffer.append("TRIGGER");
      localStringBuffer.append(',');
    }
    else if (this.triggerColumnSet != null)
    {
      localStringBuffer.append("TRIGGER");
      localStringBuffer.append(',');
    }
    return localStringBuffer.toString().substring(0, localStringBuffer.length() - 1);
  }

  private static void getColumnList(Table paramTable, OrderedHashSet paramOrderedHashSet, StringBuffer paramStringBuffer)
  {
    int i = 0;
    boolean[] arrayOfBoolean = paramTable.getNewColumnCheckList();
    for (int j = 0; j < paramOrderedHashSet.size(); j++)
    {
      HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)paramOrderedHashSet.get(j);
      int m = paramTable.findColumn(localHsqlName.name);
      if (m != -1)
      {
        arrayOfBoolean[m] = true;
        i++;
      }
    }
    if (i == 0)
      throw Error.runtimeError(201, "Right");
    paramStringBuffer.append('(');
    j = 0;
    int k = 0;
    while (j < arrayOfBoolean.length)
    {
      if (arrayOfBoolean[j] != 0)
      {
        k++;
        paramStringBuffer.append(paramTable.getColumn(j).getName().statementName);
        if (k < i)
          paramStringBuffer.append(',');
      }
      j++;
    }
    paramStringBuffer.append(')');
  }

  public void setColumns(Table paramTable)
  {
    if (this.selectColumnSet != null)
      setColumns(paramTable, this.selectColumnSet);
    if (this.insertColumnSet != null)
      setColumns(paramTable, this.insertColumnSet);
    if (this.updateColumnSet != null)
      setColumns(paramTable, this.updateColumnSet);
    if (this.referencesColumnSet != null)
      setColumns(paramTable, this.referencesColumnSet);
    if (this.triggerColumnSet != null)
      setColumns(paramTable, this.triggerColumnSet);
  }

  private static void setColumns(Table paramTable, OrderedHashSet paramOrderedHashSet)
  {
    int i = 0;
    boolean[] arrayOfBoolean = paramTable.getNewColumnCheckList();
    for (int j = 0; j < paramOrderedHashSet.size(); j++)
    {
      String str = (String)paramOrderedHashSet.get(j);
      int k = paramTable.findColumn(str);
      if (k == -1)
        throw Error.error(5501, str);
      arrayOfBoolean[k] = true;
      i++;
    }
    if (i == 0)
      throw Error.error(5501);
    paramOrderedHashSet.clear();
    for (j = 0; j < arrayOfBoolean.length; j++)
      if (arrayOfBoolean[j] != 0)
        paramOrderedHashSet.add(paramTable.getColumn(j).getName());
  }

  public void set(int paramInt, OrderedHashSet paramOrderedHashSet)
  {
    switch (paramInt)
    {
    case 1:
      if (paramOrderedHashSet == null)
        this.isFullSelect = true;
      this.selectColumnSet = paramOrderedHashSet;
      break;
    case 2:
      if (paramOrderedHashSet == null)
        this.isFullDelete = true;
      break;
    case 4:
      if (paramOrderedHashSet == null)
        this.isFullInsert = true;
      this.insertColumnSet = paramOrderedHashSet;
      break;
    case 8:
      if (paramOrderedHashSet == null)
        this.isFullUpdate = true;
      this.updateColumnSet = paramOrderedHashSet;
      break;
    case 64:
      if (paramOrderedHashSet == null)
        this.isFullReferences = true;
      this.referencesColumnSet = paramOrderedHashSet;
      break;
    case 128:
      if (paramOrderedHashSet == null)
        this.isFullTrigger = true;
      this.triggerColumnSet = paramOrderedHashSet;
    }
  }

  String[] getTableRightsArray()
  {
    if (this.isFull)
      return new String[] { "SELECT", "INSERT", "UPDATE", "DELETE", "REFERENCES" };
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    String[] arrayOfString = new String[localHsqlArrayList.size()];
    if (this.isFullSelect)
      localHsqlArrayList.add("SELECT");
    if (this.isFullInsert)
      localHsqlArrayList.add("INSERT");
    if (this.isFullUpdate)
      localHsqlArrayList.add("UPDATE");
    if (this.isFullDelete)
      localHsqlArrayList.add("DELETE");
    if (this.isFullReferences)
      localHsqlArrayList.add("REFERENCES");
    if (this.isFullTrigger)
      localHsqlArrayList.add("TRIGGER");
    localHsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }

  static
  {
    fullRights.grantor = GranteeManager.systemAuthorisation;
    fullRights.isFull = true;
    fullRightsSet.add(fullRights);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.rights.Right
 * JD-Core Version:    0.6.2
 */