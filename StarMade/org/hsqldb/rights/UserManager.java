package org.hsqldb.rights;

import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.HsqlNameManager.HsqlName;
import org.hsqldb.Routine;
import org.hsqldb.Schema;
import org.hsqldb.SchemaManager;
import org.hsqldb.Session;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.result.Result;

public final class UserManager
{
  private HashMappedList userList;
  private GranteeManager granteeManager;
  Routine pwCheckFunction;
  Routine extAuthenticationFunction;
  
  public UserManager(Database paramDatabase)
  {
    this.granteeManager = paramDatabase.getGranteeManager();
    this.userList = new HashMappedList();
  }
  
  public User createUser(HsqlNameManager.HsqlName paramHsqlName, String paramString, boolean paramBoolean)
  {
    User localUser = this.granteeManager.addUser(paramHsqlName);
    localUser.setPassword(paramString, paramBoolean);
    boolean bool = this.userList.add(paramHsqlName.name, localUser);
    if (!bool) {
      throw Error.error(4003, paramHsqlName.statementName);
    }
    return localUser;
  }
  
  public void setPassword(Session paramSession, User paramUser, String paramString, boolean paramBoolean)
  {
    if ((!paramBoolean) && (!checkComplexity(paramSession, paramString))) {
      throw Error.error(391);
    }
    paramUser.setPassword(paramString, paramBoolean);
  }
  
  public boolean checkComplexity(Session paramSession, String paramString)
  {
    if ((paramSession == null) || (this.pwCheckFunction == null)) {
      return true;
    }
    Result localResult = this.pwCheckFunction.invoke(paramSession, new Object[] { paramString }, null, false);
    Boolean localBoolean = (Boolean)localResult.getValueObject();
    return (localBoolean != null) && (localBoolean.booleanValue());
  }
  
  public void dropUser(String paramString)
  {
    boolean bool1 = GranteeManager.isReserved(paramString);
    if (bool1) {
      throw Error.error(4002, paramString);
    }
    boolean bool2 = this.granteeManager.removeGrantee(paramString);
    if (!bool2) {
      throw Error.error(4001, paramString);
    }
    User localUser = (User)this.userList.remove(paramString);
    if (localUser == null) {
      throw Error.error(4001, paramString);
    }
  }
  
  public void createFirstUser(String paramString1, String paramString2)
  {
    boolean bool = true;
    if (paramString1.equalsIgnoreCase("SA"))
    {
      paramString1 = "SA";
      bool = false;
    }
    HsqlNameManager.HsqlName localHsqlName = this.granteeManager.database.nameManager.newHsqlName(paramString1, bool, 11);
    User localUser = createUser(localHsqlName, paramString2, false);
    localUser.isLocalOnly = true;
    this.granteeManager.grant(localHsqlName.name, "DBA", this.granteeManager.getDBARole());
  }
  
  public User getUser(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      paramString1 = "";
    }
    if (paramString2 == null) {
      paramString2 = "";
    }
    User localUser = (User)this.userList.get(paramString1);
    int i = (localUser != null) && (localUser.isLocalOnly) ? 1 : 0;
    if ((this.extAuthenticationFunction == null) || (i != 0))
    {
      localUser = get(paramString1);
      localUser.checkPassword(paramString2);
      return localUser;
    }
    Result localResult = this.extAuthenticationFunction.invokeJavaMethodDirect(new String[] { this.granteeManager.database.getUniqueName(), paramString1, paramString2 });
    if (localResult.isError()) {
      throw Error.error(4001, localResult.getMainString());
    }
    Object[] arrayOfObject = (Object[])localResult.getValueObject();
    if (localUser == null)
    {
      HsqlNameManager.HsqlName localHsqlName = this.granteeManager.database.nameManager.newHsqlName(paramString1, true, 11);
      localUser = createUser(localHsqlName, "", false);
      localUser.isExternalOnly = true;
    }
    if (arrayOfObject == null)
    {
      localUser.updateAllRights();
      return localUser;
    }
    localUser.clearPrivileges();
    for (int j = 0; j < arrayOfObject.length; j++) {
      try
      {
        Grantee localGrantee = this.granteeManager.getRole((String)arrayOfObject[j]);
        localUser.grant(localGrantee);
      }
      catch (HsqlException localHsqlException) {}
    }
    localUser.updateAllRights();
    for (j = 0; j < arrayOfObject.length; j++)
    {
      Schema localSchema = this.granteeManager.database.schemaManager.findSchema((String)arrayOfObject[j]);
      if (localSchema != null)
      {
        localUser.setInitialSchema(localSchema.getName());
        break;
      }
    }
    return localUser;
  }
  
  public HashMappedList getUsers()
  {
    return this.userList;
  }
  
  public boolean exists(String paramString)
  {
    return this.userList.get(paramString) != null;
  }
  
  public User get(String paramString)
  {
    User localUser = (User)this.userList.get(paramString);
    if (localUser == null) {
      throw Error.error(4001, paramString);
    }
    return localUser;
  }
  
  public HsqlArrayList listVisibleUsers(Session paramSession)
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    boolean bool = paramSession.isAdmin();
    String str1 = paramSession.getUsername();
    if ((this.userList == null) || (this.userList.size() == 0)) {
      return localHsqlArrayList;
    }
    for (int i = 0; i < this.userList.size(); i++)
    {
      User localUser = (User)this.userList.get(i);
      if (localUser != null)
      {
        String str2 = localUser.getName().getNameString();
        if (bool) {
          localHsqlArrayList.add(localUser);
        } else if (str1.equals(str2)) {
          localHsqlArrayList.add(localUser);
        }
      }
    }
    return localHsqlArrayList;
  }
  
  public User getSysUser()
  {
    return GranteeManager.systemAuthorisation;
  }
  
  public synchronized void removeSchemaReference(String paramString)
  {
    for (int i = 0; i < this.userList.size(); i++)
    {
      User localUser = (User)this.userList.get(i);
      HsqlNameManager.HsqlName localHsqlName = localUser.getInitialSchema();
      if ((localHsqlName != null) && (paramString.equals(localHsqlName.name))) {
        localUser.setInitialSchema(null);
      }
    }
  }
  
  public void setPasswordCheckFunction(Routine paramRoutine)
  {
    this.pwCheckFunction = paramRoutine;
  }
  
  public void setExtAuthenticationFunction(Routine paramRoutine)
  {
    this.extAuthenticationFunction = paramRoutine;
  }
  
  public String[] getInitialSchemaSQL()
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList(this.userList.size());
    for (int i = 0; i < this.userList.size(); i++)
    {
      User localUser = (User)this.userList.get(i);
      if (!localUser.isSystem)
      {
        HsqlNameManager.HsqlName localHsqlName = localUser.getInitialSchema();
        if (localHsqlName != null) {
          localHsqlArrayList.add(localUser.getInitialSchemaSQL());
        }
      }
    }
    String[] arrayOfString = new String[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public String[] getAuthenticationSQL()
  {
    HsqlArrayList localHsqlArrayList = new HsqlArrayList();
    StringBuffer localStringBuffer;
    if (this.pwCheckFunction != null)
    {
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("SET").append(' ').append("DATABASE");
      localStringBuffer.append(' ').append("PASSWORD").append(' ');
      localStringBuffer.append("CHECK").append(' ').append("FUNCTION");
      localStringBuffer.append(' ');
      localStringBuffer.append(this.pwCheckFunction.getSQLBodyDefinition());
      localHsqlArrayList.add(localStringBuffer.toString());
    }
    if (this.extAuthenticationFunction != null)
    {
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("SET").append(' ').append("DATABASE");
      localStringBuffer.append(' ').append("AUTHENTICATION").append(' ');
      localStringBuffer.append("FUNCTION").append(' ');
      localStringBuffer.append(this.extAuthenticationFunction.getSQLBodyDefinition());
      localHsqlArrayList.add(localStringBuffer.toString());
    }
    String[] arrayOfString = new String[localHsqlArrayList.size()];
    localHsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.rights.UserManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */