package org.hsqldb.rights;

import org.hsqldb.Database;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.Routine;
import org.hsqldb.RoutineSchema;
import org.hsqldb.SchemaObject;
import org.hsqldb.SqlInvariants;
import org.hsqldb.error.Error;
import org.hsqldb.lib.Collection;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.lib.Iterator;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.lib.Set;

public class GranteeManager
{
  static User systemAuthorisation;
  private HashMappedList map = new HashMappedList();
  private HashMappedList roleMap = new HashMappedList();
  Database database;
  Grantee publicRole;
  Grantee dbaRole;
  Grantee schemaRole;
  Grantee changeAuthRole;
  static final IntValueHashMap rightsStringLookup;
  
  public GranteeManager(Database paramDatabase)
  {
    this.database = paramDatabase;
    addRole(this.database.nameManager.newHsqlName("PUBLIC", false, 11));
    this.publicRole = getRole("PUBLIC");
    this.publicRole.isPublic = true;
    addRole(this.database.nameManager.newHsqlName("DBA", false, 11));
    this.dbaRole = getRole("DBA");
    this.dbaRole.setAdminDirect();
    addRole(this.database.nameManager.newHsqlName("CREATE_SCHEMA", false, 11));
    this.schemaRole = getRole("CREATE_SCHEMA");
    addRole(this.database.nameManager.newHsqlName("CHANGE_AUTHORIZATION", false, 11));
    this.changeAuthRole = getRole("CHANGE_AUTHORIZATION");
  }
  
  public Grantee getDBARole()
  {
    return this.dbaRole;
  }
  
  public static Grantee getSystemRole()
  {
    return systemAuthorisation;
  }
  
  public void grant(OrderedHashSet paramOrderedHashSet, SchemaObject paramSchemaObject, Right paramRight, Grantee paramGrantee, boolean paramBoolean)
  {
    if ((paramSchemaObject instanceof RoutineSchema))
    {
      localObject = ((RoutineSchema)paramSchemaObject).getSpecificRoutines();
      grant(paramOrderedHashSet, (SchemaObject[])localObject, paramRight, paramGrantee, paramBoolean);
      return;
    }
    Object localObject = paramSchemaObject.getName();
    if ((paramSchemaObject instanceof Routine)) {
      localObject = ((Routine)paramSchemaObject).getSpecificName();
    }
    if (!paramGrantee.isGrantable(paramSchemaObject, paramRight)) {
      throw Error.error(2000, paramGrantee.getName().getNameString());
    }
    if (paramGrantee.isAdmin()) {
      paramGrantee = paramSchemaObject.getOwner();
    }
    checkGranteeList(paramOrderedHashSet);
    for (int i = 0; i < paramOrderedHashSet.size(); i++)
    {
      Grantee localGrantee = get((String)paramOrderedHashSet.get(i));
      localGrantee.grant((HsqlNameManager.HsqlName)localObject, paramRight, paramGrantee, paramBoolean);
      if (localGrantee.isRole) {
        updateAllRights(localGrantee);
      }
    }
  }
  
  public void grant(OrderedHashSet paramOrderedHashSet, SchemaObject[] paramArrayOfSchemaObject, Right paramRight, Grantee paramGrantee, boolean paramBoolean)
  {
    int i = 0;
    for (int j = 0; j < paramArrayOfSchemaObject.length; j++) {
      if (paramGrantee.isGrantable(paramArrayOfSchemaObject[j], paramRight))
      {
        grant(paramOrderedHashSet, paramArrayOfSchemaObject[j], paramRight, paramGrantee, paramBoolean);
        i = 1;
      }
    }
    if (i == 0) {
      throw Error.error(2000, paramGrantee.getName().getNameString());
    }
  }
  
  public void checkGranteeList(OrderedHashSet paramOrderedHashSet)
  {
    for (int i = 0; i < paramOrderedHashSet.size(); i++)
    {
      String str = (String)paramOrderedHashSet.get(i);
      Grantee localGrantee = get(str);
      if (localGrantee == null) {
        throw Error.error(4001, str);
      }
      if (isImmutable(str)) {
        throw Error.error(4002, str);
      }
      if (((localGrantee instanceof User)) && (((User)localGrantee).isExternalOnly)) {
        throw Error.error(4000, str);
      }
    }
  }
  
  public void grant(String paramString1, String paramString2, Grantee paramGrantee)
  {
    Grantee localGrantee1 = get(paramString1);
    if (localGrantee1 == null) {
      throw Error.error(4001, paramString1);
    }
    if (isImmutable(paramString1)) {
      throw Error.error(4002, paramString1);
    }
    Grantee localGrantee2 = getRole(paramString2);
    if (localGrantee2 == null) {
      throw Error.error(2200, paramString2);
    }
    if (localGrantee2 == localGrantee1) {
      throw Error.error(2251, paramString1);
    }
    if (localGrantee2.hasRole(localGrantee1)) {
      throw Error.error(2251, paramString2);
    }
    if (!paramGrantee.isGrantable(localGrantee2)) {
      throw Error.error(2000, paramGrantee.getName().getNameString());
    }
    localGrantee1.grant(localGrantee2);
    localGrantee1.updateAllRights();
    if (localGrantee1.isRole) {
      updateAllRights(localGrantee1);
    }
  }
  
  public void checkRoleList(String paramString, OrderedHashSet paramOrderedHashSet, Grantee paramGrantee, boolean paramBoolean)
  {
    Grantee localGrantee1 = get(paramString);
    for (int i = 0; i < paramOrderedHashSet.size(); i++)
    {
      String str = (String)paramOrderedHashSet.get(i);
      Grantee localGrantee2 = getRole(str);
      if (localGrantee2 == null) {
        throw Error.error(2200, str);
      }
      if ((str.equals("_SYSTEM")) || (str.equals("PUBLIC"))) {
        throw Error.error(4002, str);
      }
      if (paramBoolean)
      {
        if (localGrantee1.getDirectRoles().contains(localGrantee2)) {
          throw Error.error(2200, paramString);
        }
      }
      else if (!localGrantee1.getDirectRoles().contains(localGrantee2)) {
        throw Error.error(2200, str);
      }
      if (!paramGrantee.isAdmin()) {
        throw Error.error(2000, paramGrantee.getName().getNameString());
      }
    }
  }
  
  public void grantSystemToPublic(SchemaObject paramSchemaObject, Right paramRight)
  {
    this.publicRole.grant(paramSchemaObject.getName(), paramRight, systemAuthorisation, true);
  }
  
  public void revoke(String paramString1, String paramString2, Grantee paramGrantee)
  {
    if (!paramGrantee.isAdmin()) {
      throw Error.error(5507);
    }
    Grantee localGrantee1 = get(paramString1);
    if (localGrantee1 == null) {
      throw Error.error(4000, paramString1);
    }
    Grantee localGrantee2 = (Grantee)this.roleMap.get(paramString2);
    localGrantee1.revoke(localGrantee2);
    localGrantee1.updateAllRights();
    if (localGrantee1.isRole) {
      updateAllRights(localGrantee1);
    }
  }
  
  public void revoke(OrderedHashSet paramOrderedHashSet, SchemaObject paramSchemaObject, Right paramRight, Grantee paramGrantee, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramSchemaObject instanceof RoutineSchema))
    {
      localObject = ((RoutineSchema)paramSchemaObject).getSpecificRoutines();
      revoke(paramOrderedHashSet, (SchemaObject[])localObject, paramRight, paramGrantee, paramBoolean1, paramBoolean2);
      return;
    }
    Object localObject = paramSchemaObject.getName();
    if ((paramSchemaObject instanceof Routine)) {
      localObject = ((Routine)paramSchemaObject).getSpecificName();
    }
    if (!paramGrantee.isFullyAccessibleByRole((HsqlNameManager.HsqlName)localObject)) {
      throw Error.error(5501, paramSchemaObject.getName().name);
    }
    if (paramGrantee.isAdmin()) {
      paramGrantee = paramSchemaObject.getOwner();
    }
    String str;
    Grantee localGrantee;
    for (int i = 0; i < paramOrderedHashSet.size(); i++)
    {
      str = (String)paramOrderedHashSet.get(i);
      localGrantee = get(str);
      if (localGrantee == null) {
        throw Error.error(4001, str);
      }
      if (isImmutable(str)) {
        throw Error.error(4002, str);
      }
    }
    for (i = 0; i < paramOrderedHashSet.size(); i++)
    {
      str = (String)paramOrderedHashSet.get(i);
      localGrantee = get(str);
      localGrantee.revoke(paramSchemaObject, paramRight, paramGrantee, paramBoolean1);
      localGrantee.updateAllRights();
      if (localGrantee.isRole) {
        updateAllRights(localGrantee);
      }
    }
  }
  
  public void revoke(OrderedHashSet paramOrderedHashSet, SchemaObject[] paramArrayOfSchemaObject, Right paramRight, Grantee paramGrantee, boolean paramBoolean1, boolean paramBoolean2)
  {
    for (int i = 0; i < paramArrayOfSchemaObject.length; i++) {
      revoke(paramOrderedHashSet, paramArrayOfSchemaObject[i], paramRight, paramGrantee, paramBoolean1, paramBoolean2);
    }
  }
  
  void removeEmptyRole(Grantee paramGrantee)
  {
    for (int i = 0; i < this.map.size(); i++)
    {
      Grantee localGrantee = (Grantee)this.map.get(i);
      localGrantee.roles.remove(paramGrantee);
    }
  }
  
  public void removeDbObject(HsqlNameManager.HsqlName paramHsqlName)
  {
    for (int i = 0; i < this.map.size(); i++)
    {
      Grantee localGrantee = (Grantee)this.map.get(i);
      localGrantee.revokeDbObject(paramHsqlName);
    }
  }
  
  public void removeDbObjects(OrderedHashSet paramOrderedHashSet)
  {
    Iterator localIterator = paramOrderedHashSet.iterator();
    while (localIterator.hasNext())
    {
      HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)localIterator.next();
      for (int i = 0; i < this.map.size(); i++)
      {
        Grantee localGrantee = (Grantee)this.map.get(i);
        localGrantee.revokeDbObject(localHsqlName);
      }
    }
  }
  
  void updateAllRights(Grantee paramGrantee)
  {
    Grantee localGrantee;
    for (int i = 0; i < this.map.size(); i++)
    {
      localGrantee = (Grantee)this.map.get(i);
      if (localGrantee.isRole) {
        localGrantee.updateNestedRoles(paramGrantee);
      }
    }
    for (i = 0; i < this.map.size(); i++)
    {
      localGrantee = (Grantee)this.map.get(i);
      if (!localGrantee.isRole) {
        localGrantee.updateAllRights();
      }
    }
  }
  
  public boolean removeGrantee(String paramString)
  {
    if (isReserved(paramString)) {
      return false;
    }
    Grantee localGrantee = (Grantee)this.map.remove(paramString);
    if (localGrantee == null) {
      return false;
    }
    localGrantee.clearPrivileges();
    updateAllRights(localGrantee);
    if (localGrantee.isRole)
    {
      this.roleMap.remove(paramString);
      removeEmptyRole(localGrantee);
    }
    return true;
  }
  
  public Grantee addRole(HsqlNameManager.HsqlName paramHsqlName)
  {
    if (this.map.containsKey(paramHsqlName.name)) {
      throw Error.error(4003, paramHsqlName.name);
    }
    if ((SqlInvariants.isLobsSchemaName(paramHsqlName.name)) || (SqlInvariants.isSystemSchemaName(paramHsqlName.name))) {
      throw Error.error(4002, paramHsqlName.name);
    }
    Grantee localGrantee = new Grantee(paramHsqlName, this);
    localGrantee.isRole = true;
    this.map.put(paramHsqlName.name, localGrantee);
    this.roleMap.add(paramHsqlName.name, localGrantee);
    return localGrantee;
  }
  
  public User addUser(HsqlNameManager.HsqlName paramHsqlName)
  {
    if (this.map.containsKey(paramHsqlName.name)) {
      throw Error.error(4003, paramHsqlName.name);
    }
    if ((SqlInvariants.isLobsSchemaName(paramHsqlName.name)) || (SqlInvariants.isSystemSchemaName(paramHsqlName.name))) {
      throw Error.error(4002, paramHsqlName.name);
    }
    User localUser = new User(paramHsqlName, this);
    this.map.put(paramHsqlName.name, localUser);
    return localUser;
  }
  
  boolean isGrantee(String paramString)
  {
    return this.map.containsKey(paramString);
  }
  
  public static int getCheckSingleRight(String paramString)
  {
    int i = getRight(paramString);
    if (i != 0) {
      return i;
    }
    throw Error.error(5581, paramString);
  }
  
  public static int getRight(String paramString)
  {
    return rightsStringLookup.get(paramString, 0);
  }
  
  public Grantee get(String paramString)
  {
    return (Grantee)this.map.get(paramString);
  }
  
  public Collection getGrantees()
  {
    return this.map.values();
  }
  
  public static boolean validRightString(String paramString)
  {
    return getRight(paramString) != 0;
  }
  
  public static boolean isImmutable(String paramString)
  {
    return (paramString.equals("_SYSTEM")) || (paramString.equals("DBA")) || (paramString.equals("CREATE_SCHEMA")) || (paramString.equals("CHANGE_AUTHORIZATION"));
  }
  
  public static boolean isReserved(String paramString)
  {
    return (paramString.equals("_SYSTEM")) || (paramString.equals("DBA")) || (paramString.equals("CREATE_SCHEMA")) || (paramString.equals("CHANGE_AUTHORIZATION")) || (paramString.equals("PUBLIC"));
  }
  
  public void dropRole(String paramString)
  {
    if (!isRole(paramString)) {
      throw Error.error(2200, paramString);
    }
    if (isReserved(paramString)) {
      throw Error.error(5507);
    }
    removeGrantee(paramString);
  }
  
  public Set getRoleNames()
  {
    return this.roleMap.keySet();
  }
  
  public Collection getRoles()
  {
    return this.roleMap.values();
  }
  
  public Grantee getRole(String paramString)
  {
    Grantee localGrantee = (Grantee)this.roleMap.get(paramString);
    if (localGrantee == null) {
      throw Error.error(2200, paramString);
    }
    return localGrantee;
  }
  
  public boolean isRole(String paramString)
  {
    return this.roleMap.containsKey(paramString);
  }
  
  public String[] getSQL()
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    Iterator localIterator = getRoles().iterator();
    while (localIterator.hasNext())
    {
      localObject = (Grantee)localIterator.next();
      if (!isReserved(((Grantee)localObject).getName().getNameString())) {
        localHsqlArrayList.add(((Grantee)localObject).getSQL());
      }
    }
    localIterator = getGrantees().iterator();
    while (localIterator.hasNext())
    {
      localObject = (Grantee)localIterator.next();
      if ((localObject instanceof User)) {
        if (!((User)localObject).isExternalOnly)
        {
          localHsqlArrayList.add(((Grantee)localObject).getSQL());
          if (((User)localObject).isLocalOnly) {
            localHsqlArrayList.add(((User)localObject).getLocalUserSQL());
          }
        }
      }
    }
    Object localObject = new String[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(localObject);
    return localObject;
  }
  
  public String[] getRightstSQL()
  {
    HsqlArrayList localHsqlArrayList1 = new HsqlArrayList();
    Iterator localIterator = getGrantees().iterator();
    while (localIterator.hasNext())
    {
      localObject = (Grantee)localIterator.next();
      String str = ((Grantee)localObject).getName().getNameString();
      if ((!isImmutable(str)) && ((!(localObject instanceof User)) || (!((User)localObject).isExternalOnly)))
      {
        HsqlArrayList localHsqlArrayList2 = ((Grantee)localObject).getRightsSQL();
        localHsqlArrayList1.addAll(localHsqlArrayList2);
      }
    }
    Object localObject = new String[localHsqlArrayList1.size()];
    localHsqlArrayList1.toArray(localObject);
    return localObject;
  }
  
  static
  {
    HsqlNameManager.HsqlName localHsqlName = HsqlNameManager.newSystemObjectName("_SYSTEM", 11);
    systemAuthorisation = new User(localHsqlName, null);
    systemAuthorisation.isSystem = true;
    systemAuthorisation.setAdminDirect();
    systemAuthorisation.setInitialSchema(SqlInvariants.SYSTEM_SCHEMA_HSQLNAME);
    SqlInvariants.INFORMATION_SCHEMA_HSQLNAME.owner = systemAuthorisation;
    SqlInvariants.SYSTEM_SCHEMA_HSQLNAME.owner = systemAuthorisation;
    SqlInvariants.LOBS_SCHEMA_HSQLNAME.owner = systemAuthorisation;
    SqlInvariants.SQLJ_SCHEMA_HSQLNAME.owner = systemAuthorisation;
    rightsStringLookup = new IntValueHashMap(7);
    rightsStringLookup.put("ALL", 63);
    rightsStringLookup.put("SELECT", 1);
    rightsStringLookup.put("UPDATE", 8);
    rightsStringLookup.put("DELETE", 2);
    rightsStringLookup.put("INSERT", 4);
    rightsStringLookup.put("EXECUTE", 32);
    rightsStringLookup.put("USAGE", 16);
    rightsStringLookup.put("REFERENCES", 64);
    rightsStringLookup.put("TRIGGER", 128);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.rights.GranteeManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */