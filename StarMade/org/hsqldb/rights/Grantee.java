package org.hsqldb.rights;

import org.hsqldb.Database;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.NumberSequence;
import org.hsqldb.Routine;
import org.hsqldb.SchemaManager;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.Table;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMap;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.MultiValueHashMap;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;
import org.hsqldb.types.Type;

public class Grantee
  implements SchemaObject
{
  boolean isRole;
  private boolean isAdminDirect = false;
  private boolean isAdmin = false;
  boolean isSchemaCreator = false;
  boolean isPublic = false;
  boolean isSystem = false;
  protected HsqlNameManager.HsqlName granteeName;
  private MultiValueHashMap directRightsMap = new MultiValueHashMap();
  HashMap fullRightsMap = new HashMap();
  OrderedHashSet roles;
  private MultiValueHashMap grantedRightsMap = new MultiValueHashMap();
  protected GranteeManager granteeManager;
  protected Right ownerRights;

  Grantee(HsqlNameManager.HsqlName paramHsqlName, GranteeManager paramGranteeManager)
  {
    this.granteeName = paramHsqlName;
    this.granteeManager = paramGranteeManager;
    this.roles = new OrderedHashSet();
    this.ownerRights = new Right();
    this.ownerRights.isFull = true;
    this.ownerRights.grantor = GranteeManager.systemAuthorisation;
    this.ownerRights.grantee = this;
  }

  public int getType()
  {
    return 11;
  }

  public HsqlNameManager.HsqlName getName()
  {
    return this.granteeName;
  }

  public HsqlNameManager.HsqlName getSchemaName()
  {
    return null;
  }

  public HsqlNameManager.HsqlName getCatalogName()
  {
    return null;
  }

  public Grantee getOwner()
  {
    return null;
  }

  public OrderedHashSet getReferences()
  {
    return new OrderedHashSet();
  }

  public OrderedHashSet getComponents()
  {
    return null;
  }

  public void compile(Session paramSession, SchemaObject paramSchemaObject)
  {
  }

  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("CREATE").append(' ').append("ROLE");
    localStringBuffer.append(' ').append(this.granteeName.statementName);
    return localStringBuffer.toString();
  }

  public long getChangeTimestamp()
  {
    return 0L;
  }

  public boolean isRole()
  {
    return this.isRole;
  }

  public boolean isSystem()
  {
    return this.isSystem;
  }

  public OrderedHashSet getDirectRoles()
  {
    return this.roles;
  }

  public OrderedHashSet getAllRoles()
  {
    OrderedHashSet localOrderedHashSet = getGranteeAndAllRoles();
    localOrderedHashSet.remove(this);
    return localOrderedHashSet;
  }

  public OrderedHashSet getGranteeAndAllRoles()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    addGranteeAndRoles(localOrderedHashSet);
    return localOrderedHashSet;
  }

  public OrderedHashSet getGranteeAndAllRolesWithPublic()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    addGranteeAndRoles(localOrderedHashSet);
    localOrderedHashSet.add(this.granteeManager.publicRole);
    return localOrderedHashSet;
  }

  public boolean isAccessible(HsqlNameManager.HsqlName paramHsqlName, int paramInt)
  {
    if (isFullyAccessibleByRole(paramHsqlName))
      return true;
    Right localRight = (Right)this.fullRightsMap.get(paramHsqlName);
    if (localRight == null)
      return false;
    return localRight.canAccess(paramInt);
  }

  public boolean isAccessible(SchemaObject paramSchemaObject)
  {
    return isAccessible(paramSchemaObject.getName());
  }

  public boolean isAccessible(HsqlNameManager.HsqlName paramHsqlName)
  {
    if (isFullyAccessibleByRole(paramHsqlName))
      return true;
    Right localRight = (Right)this.fullRightsMap.get(paramHsqlName);
    if ((localRight != null) && (!localRight.isEmpty()))
      return true;
    if (!this.isPublic)
      return this.granteeManager.publicRole.isAccessible(paramHsqlName);
    return false;
  }

  private OrderedHashSet addGranteeAndRoles(OrderedHashSet paramOrderedHashSet)
  {
    paramOrderedHashSet.add(this);
    for (int i = 0; i < this.roles.size(); i++)
    {
      Grantee localGrantee = (Grantee)this.roles.get(i);
      if (!paramOrderedHashSet.contains(localGrantee))
        localGrantee.addGranteeAndRoles(paramOrderedHashSet);
    }
    return paramOrderedHashSet;
  }

  private boolean hasRoleDirect(Grantee paramGrantee)
  {
    return this.roles.contains(paramGrantee);
  }

  public boolean hasRole(Grantee paramGrantee)
  {
    return getAllRoles().contains(paramGrantee);
  }

  void grant(HsqlNameManager.HsqlName paramHsqlName, Right paramRight, Grantee paramGrantee, boolean paramBoolean)
  {
    Right localRight1 = paramGrantee.getAllGrantableRights(paramHsqlName);
    Object localObject = null;
    if (paramRight == Right.fullRights)
    {
      if (localRight1.isEmpty())
        return;
      paramRight = localRight1;
    }
    else if (!localRight1.contains(paramRight))
    {
      throw Error.error(2000);
    }
    Iterator localIterator = this.directRightsMap.get(paramHsqlName);
    while (localIterator.hasNext())
    {
      Right localRight2 = (Right)localIterator.next();
      if (localRight2.grantor == paramGrantee)
      {
        localObject = localRight2;
        ((Right)localObject).add(paramRight);
        break;
      }
    }
    if (localObject == null)
    {
      localObject = paramRight.duplicate();
      ((Right)localObject).grantor = paramGrantee;
      ((Right)localObject).grantee = this;
      this.directRightsMap.put(paramHsqlName, localObject);
    }
    if (paramBoolean)
      if (((Right)localObject).grantableRights == null)
        ((Right)localObject).grantableRights = paramRight.duplicate();
      else
        ((Right)localObject).grantableRights.add(paramRight);
    if (!paramGrantee.isSystem())
      paramGrantee.grantedRightsMap.put(paramHsqlName, localObject);
    updateAllRights();
  }

  void revoke(SchemaObject paramSchemaObject, Right paramRight, Grantee paramGrantee, boolean paramBoolean)
  {
    HsqlNameManager.HsqlName localHsqlName = paramSchemaObject.getName();
    if ((paramSchemaObject instanceof Routine))
      localHsqlName = ((Routine)paramSchemaObject).getSpecificName();
    Iterator localIterator = this.directRightsMap.get(localHsqlName);
    Right localRight = null;
    while (localIterator.hasNext())
    {
      localRight = (Right)localIterator.next();
      if (localRight.grantor == paramGrantee)
        break;
    }
    if (localRight == null)
      return;
    if (localRight.grantableRights != null)
      localRight.grantableRights.remove(paramSchemaObject, paramRight);
    if (paramBoolean)
      return;
    if (paramRight.isFull)
    {
      this.directRightsMap.remove(localHsqlName, localRight);
      paramGrantee.grantedRightsMap.remove(localHsqlName, localRight);
      updateAllRights();
      return;
    }
    localRight.remove(paramSchemaObject, paramRight);
    if (localRight.isEmpty())
    {
      this.directRightsMap.remove(localHsqlName, localRight);
      paramGrantee.grantedRightsMap.remove(localHsqlName, localRight);
    }
    updateAllRights();
  }

  void revokeDbObject(HsqlNameManager.HsqlName paramHsqlName)
  {
    this.directRightsMap.remove(paramHsqlName);
    this.grantedRightsMap.remove(paramHsqlName);
    this.fullRightsMap.remove(paramHsqlName);
  }

  void clearPrivileges()
  {
    this.roles.clear();
    this.directRightsMap.clear();
    this.grantedRightsMap.clear();
    this.fullRightsMap.clear();
    this.isAdmin = false;
  }

  public OrderedHashSet getColumnsForAllPrivileges(SchemaObject paramSchemaObject)
  {
    if ((paramSchemaObject instanceof Table))
    {
      Table localTable = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(localTable.getName()))
        return localTable.getColumnNameSet();
      Right localRight = (Right)this.fullRightsMap.get(localTable.getName());
      return localRight == null ? Right.emptySet : localRight.getColumnsForAllRights(localTable);
    }
    return Right.emptySet;
  }

  public OrderedHashSet getAllDirectPrivileges(SchemaObject paramSchemaObject)
  {
    if (paramSchemaObject.getOwner() == this)
    {
      localObject = new OrderedHashSet();
      ((OrderedHashSet)localObject).add(this.ownerRights);
      return localObject;
    }
    Object localObject = paramSchemaObject.getName();
    if ((paramSchemaObject instanceof Routine))
      localObject = ((Routine)paramSchemaObject).getSpecificName();
    Iterator localIterator = this.directRightsMap.get(localObject);
    if (localIterator.hasNext())
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      while (localIterator.hasNext())
        localOrderedHashSet.add(localIterator.next());
      return localOrderedHashSet;
    }
    return Right.emptySet;
  }

  public OrderedHashSet getAllGrantedPrivileges(SchemaObject paramSchemaObject)
  {
    HsqlNameManager.HsqlName localHsqlName = paramSchemaObject.getName();
    if ((paramSchemaObject instanceof Routine))
      localHsqlName = ((Routine)paramSchemaObject).getSpecificName();
    Iterator localIterator = this.grantedRightsMap.get(localHsqlName);
    if (localIterator.hasNext())
    {
      OrderedHashSet localOrderedHashSet = new OrderedHashSet();
      while (localIterator.hasNext())
        localOrderedHashSet.add(localIterator.next());
      return localOrderedHashSet;
    }
    return Right.emptySet;
  }

  public void checkSelect(SchemaObject paramSchemaObject, boolean[] paramArrayOfBoolean)
  {
    if ((paramSchemaObject instanceof Table))
    {
      Table localTable = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(localTable.getName()))
        return;
      Right localRight = (Right)this.fullRightsMap.get(localTable.getName());
      if ((localRight != null) && (localRight.canSelect(localTable, paramArrayOfBoolean)))
        return;
    }
    throw Error.error(5501, paramSchemaObject.getName().name);
  }

  public void checkInsert(SchemaObject paramSchemaObject, boolean[] paramArrayOfBoolean)
  {
    if ((paramSchemaObject instanceof Table))
    {
      Table localTable = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(localTable.getName()))
        return;
      Right localRight = (Right)this.fullRightsMap.get(localTable.getName());
      if ((localRight != null) && (localRight.canInsert(localTable, paramArrayOfBoolean)))
        return;
    }
    throw Error.error(5501, paramSchemaObject.getName().name);
  }

  public void checkUpdate(SchemaObject paramSchemaObject, boolean[] paramArrayOfBoolean)
  {
    if ((paramSchemaObject instanceof Table))
    {
      Table localTable = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(localTable.getName()))
        return;
      Right localRight = (Right)this.fullRightsMap.get(localTable.getName());
      if ((localRight != null) && (localRight.canUpdate(localTable, paramArrayOfBoolean)))
        return;
    }
    throw Error.error(5501, paramSchemaObject.getName().name);
  }

  public void checkReferences(SchemaObject paramSchemaObject, boolean[] paramArrayOfBoolean)
  {
    if ((paramSchemaObject instanceof Table))
    {
      Table localTable = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(localTable.getName()))
        return;
      Right localRight = (Right)this.fullRightsMap.get(localTable.getName());
      if ((localRight != null) && (localRight.canReference(localTable, paramArrayOfBoolean)))
        return;
    }
    throw Error.error(5501, paramSchemaObject.getName().name);
  }

  public void checkTrigger(SchemaObject paramSchemaObject, boolean[] paramArrayOfBoolean)
  {
    if ((paramSchemaObject instanceof Table))
    {
      Table localTable = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(localTable.getName()))
        return;
      Right localRight = (Right)this.fullRightsMap.get(localTable.getName());
      if ((localRight != null) && (localRight.canReference(localTable, paramArrayOfBoolean)))
        return;
    }
    throw Error.error(5501, paramSchemaObject.getName().name);
  }

  public void checkDelete(SchemaObject paramSchemaObject)
  {
    if ((paramSchemaObject instanceof Table))
    {
      Table localTable = (Table)paramSchemaObject;
      if (isFullyAccessibleByRole(localTable.getName()))
        return;
      Right localRight = (Right)this.fullRightsMap.get(localTable.getName());
      if ((localRight != null) && (localRight.canDelete()))
        return;
    }
    throw Error.error(5501, paramSchemaObject.getName().name);
  }

  public void checkAccess(SchemaObject paramSchemaObject)
  {
    if (isFullyAccessibleByRole(paramSchemaObject.getName()))
      return;
    HsqlNameManager.HsqlName localHsqlName = paramSchemaObject.getName();
    if ((paramSchemaObject instanceof Routine))
      localHsqlName = ((Routine)paramSchemaObject).getSpecificName();
    Right localRight = (Right)this.fullRightsMap.get(localHsqlName);
    if ((localRight != null) && (!localRight.isEmpty()))
      return;
    throw Error.error(5501, paramSchemaObject.getName().name);
  }

  public void checkSchemaUpdateOrGrantRights(String paramString)
  {
    if (!hasSchemaUpdateOrGrantRights(paramString))
      throw Error.error(5501, paramString);
  }

  public boolean hasSchemaUpdateOrGrantRights(String paramString)
  {
    if (isAdmin())
      return true;
    Grantee localGrantee = this.granteeManager.database.schemaManager.toSchemaOwner(paramString);
    if (localGrantee == this)
      return true;
    return hasRole(localGrantee);
  }

  public boolean isGrantable(SchemaObject paramSchemaObject, Right paramRight)
  {
    if (isFullyAccessibleByRole(paramSchemaObject.getName()))
      return true;
    Right localRight = getAllGrantableRights(paramSchemaObject.getName());
    return localRight.contains(paramRight);
  }

  public boolean isGrantable(Grantee paramGrantee)
  {
    return this.isAdmin;
  }

  public boolean isFullyAccessibleByRole(HsqlNameManager.HsqlName paramHsqlName)
  {
    if (this.isAdmin)
      return true;
    Grantee localGrantee;
    if (paramHsqlName.type == 2)
    {
      localGrantee = paramHsqlName.owner;
    }
    else
    {
      if (paramHsqlName.schema == null)
        return false;
      localGrantee = paramHsqlName.schema.owner;
    }
    if (localGrantee == this)
      return true;
    return hasRole(localGrantee);
  }

  public void checkAdmin()
  {
    if (!isAdmin())
      throw Error.error(5507);
  }

  public boolean isAdmin()
  {
    return this.isAdmin;
  }

  public boolean isSchemaCreator()
  {
    return (this.isAdmin) || (hasRole(this.granteeManager.schemaRole));
  }

  public boolean canChangeAuthorisation()
  {
    return (this.isAdmin) || (hasRole(this.granteeManager.changeAuthRole));
  }

  public boolean isPublic()
  {
    return this.isPublic;
  }

  public OrderedHashSet visibleGrantees()
  {
    OrderedHashSet localOrderedHashSet = new OrderedHashSet();
    GranteeManager localGranteeManager = this.granteeManager;
    if (isAdmin())
    {
      localOrderedHashSet.addAll(localGranteeManager.getGrantees());
    }
    else
    {
      localOrderedHashSet.add(this);
      Iterator localIterator = getAllRoles().iterator();
      while (localIterator.hasNext())
        localOrderedHashSet.add(localIterator.next());
    }
    return localOrderedHashSet;
  }

  public boolean hasNonSelectTableRight(SchemaObject paramSchemaObject)
  {
    if (isFullyAccessibleByRole(paramSchemaObject.getName()))
      return true;
    Right localRight = (Right)this.fullRightsMap.get(paramSchemaObject.getName());
    if (localRight == null)
      return false;
    return localRight.canAcesssNonSelect();
  }

  public boolean hasColumnRights(SchemaObject paramSchemaObject, int[] paramArrayOfInt)
  {
    if (isFullyAccessibleByRole(paramSchemaObject.getName()))
      return true;
    Right localRight = (Right)this.fullRightsMap.get(paramSchemaObject.getName());
    if (localRight == null)
      return false;
    return localRight.canAccess((Table)paramSchemaObject, paramArrayOfInt);
  }

  void setAdminDirect()
  {
    this.isAdmin = (this.isAdminDirect = 1);
  }

  boolean updateNestedRoles(Grantee paramGrantee)
  {
    boolean bool = false;
    if (paramGrantee != this)
      for (int i = 0; i < this.roles.size(); i++)
      {
        Grantee localGrantee = (Grantee)this.roles.get(i);
        bool |= localGrantee.updateNestedRoles(paramGrantee);
      }
    if (bool)
      updateAllRights();
    return (bool) || (paramGrantee == this);
  }

  void updateAllRights()
  {
    this.fullRightsMap.clear();
    this.isAdmin = this.isAdminDirect;
    for (int i = 0; i < this.roles.size(); i++)
    {
      Grantee localGrantee = (Grantee)this.roles.get(i);
      addToFullRights(localGrantee.fullRightsMap);
      this.isAdmin |= localGrantee.isAdmin();
    }
    addToFullRights(this.directRightsMap);
    if ((!this.isRole) && (!this.isPublic) && (!this.isSystem))
      addToFullRights(this.granteeManager.publicRole.fullRightsMap);
  }

  void addToFullRights(HashMap paramHashMap)
  {
    Iterator localIterator = paramHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      Right localRight1 = (Right)paramHashMap.get(localObject);
      Right localRight2 = (Right)this.fullRightsMap.get(localObject);
      if (localRight2 == null)
      {
        localRight2 = localRight1.duplicate();
        this.fullRightsMap.put(localObject, localRight2);
      }
      else
      {
        localRight2.add(localRight1);
      }
      if (localRight1.grantableRights != null)
        if (localRight2.grantableRights == null)
          localRight2.grantableRights = localRight1.grantableRights.duplicate();
        else
          localRight2.grantableRights.add(localRight1.grantableRights);
    }
  }

  private void addToFullRights(MultiValueHashMap paramMultiValueHashMap)
  {
    Iterator localIterator1 = paramMultiValueHashMap.keySet().iterator();
    while (localIterator1.hasNext())
    {
      Object localObject = localIterator1.next();
      Iterator localIterator2 = paramMultiValueHashMap.get(localObject);
      Right localRight1 = (Right)this.fullRightsMap.get(localObject);
      while (localIterator2.hasNext())
      {
        Right localRight2 = (Right)localIterator2.next();
        if (localRight1 == null)
        {
          localRight1 = localRight2.duplicate();
          this.fullRightsMap.put(localObject, localRight1);
        }
        else
        {
          localRight1.add(localRight2);
        }
        if (localRight2.grantableRights != null)
          if (localRight1.grantableRights == null)
            localRight1.grantableRights = localRight2.grantableRights.duplicate();
          else
            localRight1.grantableRights.add(localRight2.grantableRights);
      }
    }
  }

  Right getAllGrantableRights(HsqlNameManager.HsqlName paramHsqlName)
  {
    if (this.isAdmin)
      return paramHsqlName.schema.owner.ownerRights;
    if (paramHsqlName.schema.owner == this)
      return this.ownerRights;
    if (this.roles.contains(paramHsqlName.schema.owner))
      return paramHsqlName.schema.owner.ownerRights;
    OrderedHashSet localOrderedHashSet = getAllRoles();
    for (int i = 0; i < localOrderedHashSet.size(); i++)
    {
      Grantee localGrantee = (Grantee)localOrderedHashSet.get(i);
      if (paramHsqlName.schema.owner == localGrantee)
        return localGrantee.ownerRights;
    }
    Right localRight = (Right)this.fullRightsMap.get(paramHsqlName);
    return (localRight == null) || (localRight.grantableRights == null) ? Right.noRights : localRight.grantableRights;
  }

  private MultiValueHashMap getRights()
  {
    return this.directRightsMap;
  }

  void grant(Grantee paramGrantee)
  {
    this.roles.add(paramGrantee);
  }

  void revoke(Grantee paramGrantee)
  {
    if (!hasRoleDirect(paramGrantee))
      throw Error.error(2253, paramGrantee.getName().getNameString());
    this.roles.remove(paramGrantee);
  }

  private String roleMapToString(OrderedHashSet paramOrderedHashSet)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramOrderedHashSet.size(); i++)
    {
      if (localStringBuffer.length() > 0)
        localStringBuffer.append(',');
      Grantee localGrantee = (Grantee)paramOrderedHashSet.get(i);
      localStringBuffer.append(localGrantee.getName().getStatementName());
    }
    return localStringBuffer.toString();
  }

  HsqlArrayList getRightsSQL()
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    String str = roleMapToString(this.roles);
    if (str.length() != 0)
    {
      localObject1 = new StringBuffer(128);
      ((StringBuffer)localObject1).append("GRANT").append(' ').append(str);
      ((StringBuffer)localObject1).append(' ').append("TO").append(' ');
      ((StringBuffer)localObject1).append(getName().getStatementName());
      localHsqlArrayList.add(((StringBuffer)localObject1).toString());
    }
    Object localObject1 = getRights();
    Iterator localIterator1 = ((MultiValueHashMap)localObject1).keySet().iterator();
    while (localIterator1.hasNext())
    {
      Object localObject2 = localIterator1.next();
      Iterator localIterator2 = ((MultiValueHashMap)localObject1).get(localObject2);
      while (localIterator2.hasNext())
      {
        Right localRight = (Right)localIterator2.next();
        StringBuffer localStringBuffer = new StringBuffer(128);
        HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localObject2;
        switch (localHsqlName.type)
        {
        case 3:
        case 4:
          Table localTable = this.granteeManager.database.schemaManager.findUserTable(null, localHsqlName.name, localHsqlName.schema.name);
          if (localTable != null)
          {
            localStringBuffer.append("GRANT").append(' ');
            localStringBuffer.append(localRight.getTableRightsSQL(localTable));
            localStringBuffer.append(' ').append("ON").append(' ');
            localStringBuffer.append("TABLE").append(' ');
            localStringBuffer.append(localHsqlName.getSchemaQualifiedStatementName());
          }
          break;
        case 7:
          NumberSequence localNumberSequence = (NumberSequence)this.granteeManager.database.schemaManager.findSchemaObject(localHsqlName.name, localHsqlName.schema.name, 7);
          if (localNumberSequence != null)
          {
            localStringBuffer.append("GRANT").append(' ');
            localStringBuffer.append("USAGE");
            localStringBuffer.append(' ').append("ON").append(' ');
            localStringBuffer.append("SEQUENCE").append(' ');
            localStringBuffer.append(localHsqlName.getSchemaQualifiedStatementName());
          }
          break;
        case 13:
          Type localType1 = (Type)this.granteeManager.database.schemaManager.findSchemaObject(localHsqlName.name, localHsqlName.schema.name, 13);
          if (localType1 != null)
          {
            localStringBuffer.append("GRANT").append(' ');
            localStringBuffer.append("USAGE");
            localStringBuffer.append(' ').append("ON").append(' ');
            localStringBuffer.append("DOMAIN").append(' ');
            localStringBuffer.append(localHsqlName.getSchemaQualifiedStatementName());
          }
          break;
        case 12:
          Type localType2 = (Type)this.granteeManager.database.schemaManager.findSchemaObject(localHsqlName.name, localHsqlName.schema.name, 13);
          if (localType2 != null)
          {
            localStringBuffer.append("GRANT").append(' ');
            localStringBuffer.append("USAGE");
            localStringBuffer.append(' ').append("ON").append(' ');
            localStringBuffer.append("TYPE").append(' ');
            localStringBuffer.append(localHsqlName.getSchemaQualifiedStatementName());
          }
          break;
        case 16:
        case 17:
        case 24:
          SchemaObject localSchemaObject = this.granteeManager.database.schemaManager.findSchemaObject(localHsqlName.name, localHsqlName.schema.name, localHsqlName.type);
          if (localSchemaObject != null)
          {
            localStringBuffer.append("GRANT").append(' ');
            localStringBuffer.append("EXECUTE").append(' ');
            localStringBuffer.append("ON").append(' ');
            localStringBuffer.append("SPECIFIC").append(' ');
            if (localSchemaObject.getType() == 17)
              localStringBuffer.append("PROCEDURE");
            else
              localStringBuffer.append("FUNCTION");
            localStringBuffer.append(' ');
            localStringBuffer.append(localHsqlName.getSchemaQualifiedStatementName());
          }
          break;
        case 5:
        case 6:
        case 8:
        case 9:
        case 10:
        case 11:
        case 14:
        case 15:
        case 18:
        case 19:
        case 20:
        case 21:
        case 22:
        case 23:
        }
        if (localStringBuffer.length() != 0)
        {
          localStringBuffer.append(' ').append("TO").append(' ');
          localStringBuffer.append(getName().getStatementName());
          localHsqlArrayList.add(localStringBuffer.toString());
        }
      }
    }
    return localHsqlArrayList;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.rights.Grantee
 * JD-Core Version:    0.6.2
 */