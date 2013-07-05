package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.index.Index;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.navigator.RowIterator;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.hsqldb.persist.Logger;
import org.hsqldb.result.Result;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.GranteeManager;
import org.hsqldb.rights.Right;
import org.hsqldb.rights.User;
import org.hsqldb.rights.UserManager;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.Charset;
import org.hsqldb.types.Collation;
import org.hsqldb.types.Type;
import org.hsqldb.types.UserTypeModifier;

public class StatementSchema extends Statement
{
  int order;
  Object[] arguments = ValuePool.emptyObjectArray;
  boolean isSchemaDefinition;
  Token[] statementTokens;

  StatementSchema(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.isTransactionStatement = true;
  }

  StatementSchema(String paramString, int paramInt)
  {
    this(paramString, paramInt, null, (HsqlNameManager.HsqlName[])null, null);
  }

  StatementSchema(String paramString, int paramInt, Object[] paramArrayOfObject)
  {
    this(paramString, paramInt, paramArrayOfObject, (HsqlNameManager.HsqlName[])null, null);
  }

  StatementSchema(String paramString, int paramInt, Object[] paramArrayOfObject, HsqlNameManager.HsqlName[] paramArrayOfHsqlName1, HsqlNameManager.HsqlName[] paramArrayOfHsqlName2)
  {
    super(paramInt);
    this.isTransactionStatement = true;
    this.sql = paramString;
    if (paramArrayOfObject != null)
      this.arguments = paramArrayOfObject;
    if (paramArrayOfHsqlName1 != null)
      this.readTableNames = paramArrayOfHsqlName1;
    if (paramArrayOfHsqlName2 != null)
      this.writeTableNames = paramArrayOfHsqlName2;
    switch (paramInt)
    {
    case 1192:
      this.group = 2002;
      break;
    case 3:
    case 4:
    case 17:
    case 60:
    case 127:
    case 134:
    case 1069:
    case 1070:
      this.group = 2002;
      break;
    case 24:
    case 25:
    case 26:
    case 27:
    case 29:
    case 30:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case 78:
    case 115:
    case 116:
    case 135:
    case 1076:
    case 1077:
    case 1078:
    case 1079:
      this.group = 2002;
      break;
    case 48:
      this.group = 2002;
      this.order = 10;
      break;
    case 49:
      this.group = 2002;
      this.order = 10;
      break;
    case 59:
    case 129:
      this.group = 2002;
      break;
    case 64:
      this.group = 2001;
      break;
    case 61:
      this.group = 2001;
      this.order = 1;
      break;
    case 14:
      this.group = 2001;
      this.order = 7;
      break;
    case 133:
      this.group = 2001;
      this.order = 1;
      break;
    case 77:
      this.group = 2001;
      this.order = 2;
      break;
    case 117:
      this.group = 2001;
      this.order = 1;
      break;
    case 79:
      this.group = 2001;
      this.order = 1;
      break;
    case 80:
      this.group = 2001;
      this.order = 7;
      break;
    case 52:
      this.group = 2001;
      this.order = 2;
      break;
    case 83:
      this.group = 2001;
      this.order = 1;
      break;
    case 114:
      this.group = 2001;
      this.order = 1;
      break;
    case 84:
      this.group = 2001;
      this.order = 5;
      break;
    case 1074:
      this.group = 2001;
      this.order = 1;
      break;
    case 6:
      this.group = 2001;
      this.order = 9;
      break;
    case 8:
      this.group = 2001;
      this.order = 1;
      break;
    case 10:
      this.group = 2001;
      this.order = 1;
      break;
    case 23:
      this.group = 2001;
      this.order = 1;
      break;
    case 1072:
      this.group = 2001;
      this.order = 8;
      break;
    case 1073:
      this.group = 2002;
      this.order = 4;
      break;
    case 1071:
      this.group = 2002;
      this.order = 11;
      break;
    case 1204:
      this.group = 2002;
      this.statementTokens = ((Token[])paramArrayOfObject[0]);
      break;
    case 1199:
      this.group = 2002;
      break;
    default:
      throw Error.runtimeError(201, "StatemntSchema");
    }
  }

  public Result execute(Session paramSession)
  {
    Result localResult;
    try
    {
      localResult = getResult(paramSession);
    }
    catch (Throwable localThrowable1)
    {
      localResult = Result.newErrorResult(localThrowable1, null);
    }
    if (localResult.isError())
    {
      localResult.getException().setStatementType(this.group, this.type);
      return localResult;
    }
    paramSession.database.schemaManager.setSchemaChangeTimestamp();
    try
    {
      if (this.isLogged)
        paramSession.database.logger.writeOtherStatement(paramSession, this.sql);
    }
    catch (Throwable localThrowable2)
    {
      return Result.newErrorResult(localThrowable2, this.sql);
    }
    return localResult;
  }

  Result getResult(Session paramSession)
  {
    SchemaManager localSchemaManager = paramSession.database.schemaManager;
    if (this.isExplain)
      return Result.newSingleColumnStringResult("OPERATION", describe(paramSession));
    Object localObject1;
    Object localObject5;
    Object localObject9;
    Object localObject16;
    Object localObject13;
    Object localObject17;
    TableWorks localTableWorks;
    int i4;
    Object localObject2;
    int m;
    boolean bool2;
    boolean bool4;
    OrderedHashSet localOrderedHashSet;
    Object localObject10;
    Object localObject14;
    Object localObject20;
    int i8;
    Object localObject23;
    boolean bool7;
    Object localObject22;
    Object localObject3;
    Object localObject4;
    Object localObject6;
    Object localObject7;
    Object localObject15;
    Object localObject18;
    Object localObject11;
    Object localObject8;
    switch (this.type)
    {
    case 1192:
      localObject1 = (HsqlNameManager.HsqlName)this.arguments[0];
      localObject5 = (HsqlNameManager.HsqlName)this.arguments[1];
      if (((HsqlNameManager.HsqlName)localObject1).type == 1)
        try
        {
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          ((HsqlNameManager.HsqlName)localObject1).rename((HsqlNameManager.HsqlName)localObject5);
        }
        catch (HsqlException localHsqlException21)
        {
          return Result.newErrorResult(localHsqlException21, this.sql);
        }
      if (((HsqlNameManager.HsqlName)localObject1).type == 2)
      {
        checkSchemaUpdateAuthorisation(paramSession, (HsqlNameManager.HsqlName)localObject1);
        localSchemaManager.checkSchemaNameCanChange((HsqlNameManager.HsqlName)localObject1);
        localSchemaManager.renameSchema((HsqlNameManager.HsqlName)localObject1, (HsqlNameManager.HsqlName)localObject5);
        break label4822;
      }
      try
      {
        ((HsqlNameManager.HsqlName)localObject1).setSchemaIfNull(paramSession.getCurrentSchemaHsqlName());
        Object localObject12;
        if (((HsqlNameManager.HsqlName)localObject1).type == 9)
        {
          localObject12 = localSchemaManager.getUserTable(paramSession, ((HsqlNameManager.HsqlName)localObject1).parent);
          int i6 = ((Table)localObject12).getColumnIndex(((HsqlNameManager.HsqlName)localObject1).name);
          localObject9 = ((Table)localObject12).getColumn(i6);
        }
        else
        {
          localObject9 = localSchemaManager.getSchemaObject((HsqlNameManager.HsqlName)localObject1);
          if (localObject9 == null)
            throw Error.error(5501, ((HsqlNameManager.HsqlName)localObject1).name);
          localObject1 = ((SchemaObject)localObject9).getName();
        }
        checkSchemaUpdateAuthorisation(paramSession, ((HsqlNameManager.HsqlName)localObject1).schema);
        ((HsqlNameManager.HsqlName)localObject5).setSchemaIfNull(((HsqlNameManager.HsqlName)localObject1).schema);
        if (((HsqlNameManager.HsqlName)localObject1).schema != ((HsqlNameManager.HsqlName)localObject5).schema)
        {
          localObject12 = Error.error(5505);
          return Result.newErrorResult((Throwable)localObject12, this.sql);
        }
        ((HsqlNameManager.HsqlName)localObject5).parent = ((HsqlNameManager.HsqlName)localObject1).parent;
        switch (((SchemaObject)localObject9).getType())
        {
        case 9:
          localObject12 = ((SchemaObject)localObject9).getName().parent;
          localSchemaManager.checkColumnIsReferenced((HsqlNameManager.HsqlName)localObject12, ((SchemaObject)localObject9).getName());
          localObject16 = localSchemaManager.getUserTable(paramSession, (HsqlNameManager.HsqlName)localObject12);
          ((Table)localObject16).renameColumn((ColumnSchema)localObject9, (HsqlNameManager.HsqlName)localObject5);
          break;
        default:
          localSchemaManager.renameSchemaObject((HsqlNameManager.HsqlName)localObject1, (HsqlNameManager.HsqlName)localObject5);
        }
      }
      catch (HsqlException localHsqlException22)
      {
        return Result.newErrorResult(localHsqlException22, this.sql);
      }
    case 1069:
      localObject1 = (Table)this.arguments[0];
      localObject5 = (int[])this.arguments[1];
      localObject9 = (HsqlNameManager.HsqlName)this.arguments[2];
      try
      {
        localObject13 = (Index)paramSession.database.schemaManager.getSchemaObject((HsqlNameManager.HsqlName)localObject9);
        localObject16 = new TableWorks(paramSession, (Table)localObject1);
        ((TableWorks)localObject16).alterIndex((Index)localObject13, (int[])localObject5);
      }
      catch (HsqlException localHsqlException23)
      {
        return Result.newErrorResult(localHsqlException23, this.sql);
      }
    case 134:
      try
      {
        localObject1 = (NumberSequence)this.arguments[0];
        localObject5 = (NumberSequence)this.arguments[1];
        checkSchemaUpdateAuthorisation(paramSession, ((NumberSequence)localObject1).getSchemaName());
        ((NumberSequence)localObject1).reset((NumberSequence)localObject5);
      }
      catch (HsqlException localHsqlException1)
      {
        return Result.newErrorResult(localHsqlException1, this.sql);
      }
    case 3:
      try
      {
        int i = ((Integer)this.arguments[0]).intValue();
        localObject5 = (Type)this.arguments[1];
        switch (i)
        {
        case 1082:
          localObject9 = (Constraint)this.arguments[2];
          paramSession.database.schemaManager.checkSchemaObjectNotExists(((Constraint)localObject9).getName());
          ((Type)localObject5).userTypeModifier.addConstraint((Constraint)localObject9);
          paramSession.database.schemaManager.addSchemaObject((SchemaObject)localObject9);
          break;
        case 1083:
          localObject9 = (Expression)this.arguments[2];
          ((Type)localObject5).userTypeModifier.setDefaultClause((Expression)localObject9);
          break;
        case 1078:
          localObject9 = (HsqlNameManager.HsqlName)this.arguments[2];
          paramSession.database.schemaManager.removeSchemaObject((HsqlNameManager.HsqlName)localObject9);
          break;
        case 1080:
          ((Type)localObject5).userTypeModifier.removeDefaultClause();
        case 1079:
        case 1081:
        }
      }
      catch (HsqlException localHsqlException2)
      {
        return Result.newErrorResult(localHsqlException2, this.sql);
      }
    case 4:
      try
      {
        int j = ((Integer)this.arguments[0]).intValue();
        localObject5 = (Table)this.arguments[1];
        Object localObject19;
        Type localType;
        switch (j)
        {
        case 1082:
          localObject9 = (Constraint)this.arguments[2];
          switch (((Constraint)localObject9).getConstraintType())
          {
          case 4:
            localObject13 = new TableWorks(paramSession, (Table)localObject5);
            ((TableWorks)localObject13).addPrimaryKey((Constraint)localObject9);
            break;
          case 2:
            localObject13 = new TableWorks(paramSession, (Table)localObject5);
            ((TableWorks)localObject13).addUniqueConstraint((Constraint)localObject9);
            break;
          case 0:
            localObject13 = new TableWorks(paramSession, (Table)localObject5);
            ((TableWorks)localObject13).addForeignKey((Constraint)localObject9);
            break;
          case 3:
            localObject13 = new TableWorks(paramSession, (Table)localObject5);
            ((TableWorks)localObject13).addCheckConstraint((Constraint)localObject9);
          case 1:
          }
          break;
        case 1081:
          localObject9 = (ColumnSchema)this.arguments[2];
          int i2 = ((Integer)this.arguments[3]).intValue();
          localObject17 = (HsqlArrayList)this.arguments[4];
          localObject19 = new TableWorks(paramSession, (Table)localObject5);
          ((TableWorks)localObject19).addColumn((ColumnSchema)localObject9, i2, (HsqlArrayList)localObject17);
          break;
        case 1084:
          localObject9 = (ColumnSchema)this.arguments[2];
          localType = (Type)this.arguments[3];
          localObject17 = ((ColumnSchema)localObject9).duplicate();
          ((ColumnSchema)localObject17).setType(localType);
          localObject19 = new TableWorks(paramSession, (Table)localObject5);
          ((TableWorks)localObject19).retypeColumn((ColumnSchema)localObject9, (ColumnSchema)localObject17);
          break;
        case 1090:
          localObject9 = (ColumnSchema)this.arguments[2];
          localType = (Type)this.arguments[3];
          localObject17 = (NumberSequence)this.arguments[4];
          localObject19 = ((ColumnSchema)localObject9).duplicate();
          ((ColumnSchema)localObject19).setType(localType);
          ((ColumnSchema)localObject19).setIdentity((NumberSequence)localObject17);
          localTableWorks = new TableWorks(paramSession, (Table)localObject5);
          localTableWorks.retypeColumn((ColumnSchema)localObject9, (ColumnSchema)localObject19);
          break;
        case 1085:
          localObject9 = (ColumnSchema)this.arguments[2];
          int i3 = ((Integer)this.arguments[3]).intValue();
          localObject17 = (NumberSequence)this.arguments[4];
          if (((ColumnSchema)localObject9).isIdentity())
          {
            ((ColumnSchema)localObject9).getIdentitySequence().reset((NumberSequence)localObject17);
          }
          else
          {
            ((ColumnSchema)localObject9).setIdentity((NumberSequence)localObject17);
            ((Table)localObject5).setColumnTypeVars(i3);
          }
          break;
        case 1086:
          localObject9 = (ColumnSchema)this.arguments[2];
          boolean bool3 = ((Boolean)this.arguments[3]).booleanValue();
          localObject17 = new TableWorks(paramSession, (Table)localObject5);
          ((TableWorks)localObject17).setColNullability((ColumnSchema)localObject9, bool3);
          break;
        case 1087:
          localObject9 = (ColumnSchema)this.arguments[2];
          i4 = ((Integer)this.arguments[3]).intValue();
          localObject17 = (Expression)this.arguments[4];
          localObject19 = new TableWorks(paramSession, (Table)localObject5);
          ((TableWorks)localObject19).setColDefaultExpression(i4, (Expression)localObject17);
          break;
        case 1088:
          localObject9 = (ColumnSchema)this.arguments[2];
          i4 = ((Integer)this.arguments[3]).intValue();
          localObject17 = new TableWorks(paramSession, (Table)localObject5);
          ((TableWorks)localObject17).setColDefaultExpression(i4, null);
          ((Table)localObject5).setColumnTypeVars(i4);
          break;
        case 1089:
          localObject9 = (ColumnSchema)this.arguments[2];
          i4 = ((Integer)this.arguments[3]).intValue();
          ((ColumnSchema)localObject9).setIdentity(null);
          ((Table)localObject5).setColumnTypeVars(i4);
        case 1083:
        }
      }
      catch (HsqlException localHsqlException3)
      {
        return Result.newErrorResult(localHsqlException3, this.sql);
      }
    case 17:
      localObject2 = (Routine)this.arguments[0];
      try
      {
        ((Routine)localObject2).resolveReferences(paramSession);
        localObject5 = (Routine)localSchemaManager.getSchemaObject(((Routine)localObject2).getSpecificName());
        localSchemaManager.replaceReferences((SchemaObject)localObject5, (SchemaObject)localObject2);
        ((Routine)localObject5).setAsAlteredRoutine((Routine)localObject2);
      }
      catch (HsqlException localHsqlException9)
      {
        return Result.newErrorResult(localHsqlException9, this.sql);
      }
    case 60:
    case 127:
      throw Error.runtimeError(201, "StatementSchema");
    case 1070:
      localObject2 = (View)this.arguments[0];
      try
      {
        checkSchemaUpdateAuthorisation(paramSession, ((View)localObject2).getSchemaName());
        View localView = (View)localSchemaManager.getSchemaObject(((View)localObject2).getName());
        if (localView == null)
          throw Error.error(5501, ((View)localObject2).getName().name);
        ((View)localObject2).setName(localView.getName());
        ((View)localObject2).compile(paramSession, null);
        localObject9 = localSchemaManager.getReferencesTo(localView.getName());
        if (((OrderedHashSet)localObject9).getCommonElementCount(((View)localObject2).getReferences()) > 0)
          throw Error.error(5502);
        i4 = localSchemaManager.getTableIndex(localView);
        localSchemaManager.setTable(i4, (Table)localObject2);
        localObject17 = new OrderedHashSet();
        ((OrderedHashSet)localObject17).add(localObject2);
        try
        {
          localSchemaManager.recompileDependentObjects((OrderedHashSet)localObject17);
        }
        catch (HsqlException localHsqlException25)
        {
          localSchemaManager.setTable(i4, localView);
          localSchemaManager.recompileDependentObjects((OrderedHashSet)localObject17);
        }
      }
      catch (HsqlException localHsqlException10)
      {
        return Result.newErrorResult(localHsqlException10, this.sql);
      }
    case 1076:
      try
      {
        localObject2 = (HsqlNameManager.HsqlName)this.arguments[0];
        m = ((Integer)this.arguments[1]).intValue();
        bool2 = ((Boolean)this.arguments[2]).booleanValue();
        bool4 = ((Boolean)this.arguments[3]).booleanValue();
        localObject17 = localSchemaManager.getUserTable(paramSession, ((HsqlNameManager.HsqlName)localObject2).parent);
        int i7 = ((Table)localObject17).getColumnIndex(((HsqlNameManager.HsqlName)localObject2).name);
        if (((Table)localObject17).getColumnCount() == 1)
          throw Error.error(5591);
        checkSchemaUpdateAuthorisation(paramSession, ((Table)localObject17).getSchemaName());
        localTableWorks = new TableWorks(paramSession, (Table)localObject17);
        localTableWorks.dropColumn(i7, bool2);
      }
      catch (HsqlException localHsqlException4)
      {
        return Result.newErrorResult(localHsqlException4, this.sql);
      }
    case 24:
    case 25:
    case 26:
    case 27:
    case 29:
    case 30:
    case 31:
    case 32:
    case 33:
    case 34:
    case 35:
    case 36:
    case 78:
    case 115:
    case 116:
    case 135:
    case 1077:
    case 1078:
    case 1079:
      try
      {
        HsqlNameManager.HsqlName localHsqlName = (HsqlNameManager.HsqlName)this.arguments[0];
        m = ((Integer)this.arguments[1]).intValue();
        bool2 = ((Boolean)this.arguments[2]).booleanValue();
        bool4 = ((Boolean)this.arguments[3]).booleanValue();
        switch (this.type)
        {
        case 29:
        case 1079:
          paramSession.checkAdmin();
          paramSession.checkDDLWrite();
          break;
        case 31:
          checkSchemaUpdateAuthorisation(paramSession, localHsqlName);
          if ((!localSchemaManager.schemaExists(localHsqlName.name)) && (bool4))
            return Result.updateZeroResult;
          break;
        default:
          if (localHsqlName.schema == null)
            localHsqlName.schema = paramSession.getCurrentSchemaHsqlName();
          else if ((!localSchemaManager.schemaExists(localHsqlName.schema.name)) && (bool4))
            return Result.updateZeroResult;
          localHsqlName.schema = localSchemaManager.getUserSchemaHsqlName(localHsqlName.schema.name);
          checkSchemaUpdateAuthorisation(paramSession, localHsqlName.schema);
          localObject17 = localSchemaManager.getSchemaObject(localHsqlName);
          if (localObject17 == null)
          {
            if (bool4)
              return Result.updateZeroResult;
            throw Error.error(5501, localHsqlName.name);
          }
          if (localHsqlName.type == 24)
            localHsqlName = ((Routine)localObject17).getSpecificName();
          else
            localHsqlName = ((SchemaObject)localObject17).getName();
          break;
        }
        if (!bool2)
          localSchemaManager.checkObjectIsReferenced(localHsqlName);
        switch (this.type)
        {
        case 29:
          dropRole(paramSession, localHsqlName, bool2);
          break;
        case 1079:
          dropUser(paramSession, localHsqlName, bool2);
          break;
        case 31:
          dropSchema(paramSession, localHsqlName, bool2);
          break;
        case 24:
          break;
        case 25:
        case 26:
        case 34:
        case 135:
          dropObject(paramSession, localHsqlName, bool2);
          break;
        case 35:
          dropType(paramSession, localHsqlName, bool2);
          break;
        case 27:
          dropDomain(paramSession, localHsqlName, bool2);
          break;
        case 30:
          dropRoutine(paramSession, localHsqlName, bool2);
          break;
        case 32:
        case 36:
          dropTable(paramSession, localHsqlName, bool2);
          break;
        case 33:
        case 78:
        case 115:
        case 116:
          break;
        case 1077:
          checkSchemaUpdateAuthorisation(paramSession, localHsqlName.schema);
          localSchemaManager.dropIndex(paramSession, localHsqlName);
          break;
        case 1078:
          checkSchemaUpdateAuthorisation(paramSession, localHsqlName.schema);
          localSchemaManager.dropConstraint(paramSession, localHsqlName, bool2);
        }
      }
      catch (HsqlException localHsqlException5)
      {
        return Result.newErrorResult(localHsqlException5, this.sql);
      }
    case 48:
    case 59:
      try
      {
        int k = this.type == 48 ? 1 : 0;
        localOrderedHashSet = (OrderedHashSet)this.arguments[0];
        localObject10 = (HsqlNameManager.HsqlName)this.arguments[1];
        setSchemaName(paramSession, null, (HsqlNameManager.HsqlName)localObject10);
        localObject10 = localSchemaManager.getSchemaObjectName(((HsqlNameManager.HsqlName)localObject10).schema, ((HsqlNameManager.HsqlName)localObject10).name, ((HsqlNameManager.HsqlName)localObject10).type, true);
        localObject14 = localSchemaManager.getSchemaObject((HsqlNameManager.HsqlName)localObject10);
        localObject17 = (Right)this.arguments[2];
        localObject20 = (Grantee)this.arguments[3];
        i8 = ((Boolean)this.arguments[4]).booleanValue();
        boolean bool8 = ((Boolean)this.arguments[5]).booleanValue();
        if (localObject20 == null)
          localObject20 = this.isSchemaDefinition ? this.schemaName.owner : paramSession.getGrantee();
        GranteeManager localGranteeManager = paramSession.database.granteeManager;
        switch (((SchemaObject)localObject14).getType())
        {
        case 14:
          break;
        case 3:
        case 4:
          localObject23 = (Table)localObject14;
          ((Right)localObject17).setColumns((Table)localObject23);
          if ((((Table)localObject23).getTableType() == 3) && (!((Right)localObject17).isFull()))
            return Result.newErrorResult(Error.error(5595), this.sql);
          break;
        }
        if (k != 0)
          localGranteeManager.grant(localOrderedHashSet, (SchemaObject)localObject14, (Right)localObject17, (Grantee)localObject20, bool8);
        else
          localGranteeManager.revoke(localOrderedHashSet, (SchemaObject)localObject14, (Right)localObject17, (Grantee)localObject20, bool8, i8);
      }
      catch (HsqlException localHsqlException6)
      {
        return Result.newErrorResult(localHsqlException6, this.sql);
      }
    case 49:
    case 129:
      try
      {
        boolean bool1 = this.type == 49;
        localOrderedHashSet = (OrderedHashSet)this.arguments[0];
        localObject10 = (OrderedHashSet)this.arguments[1];
        localObject14 = (Grantee)this.arguments[2];
        bool7 = ((Boolean)this.arguments[3]).booleanValue();
        localObject20 = paramSession.database.granteeManager;
        ((GranteeManager)localObject20).checkGranteeList(localOrderedHashSet);
        for (i8 = 0; i8 < localOrderedHashSet.size(); i8++)
        {
          localObject22 = (String)localOrderedHashSet.get(i8);
          ((GranteeManager)localObject20).checkRoleList((String)localObject22, (OrderedHashSet)localObject10, (Grantee)localObject14, bool1);
        }
        int i9;
        int i10;
        if (bool1)
          for (i9 = 0; i9 < localOrderedHashSet.size(); i9++)
          {
            localObject22 = (String)localOrderedHashSet.get(i9);
            for (i10 = 0; i10 < ((OrderedHashSet)localObject10).size(); i10++)
            {
              localObject23 = (String)((OrderedHashSet)localObject10).get(i10);
              ((GranteeManager)localObject20).grant((String)localObject22, (String)localObject23, (Grantee)localObject14);
            }
          }
        else
          for (i9 = 0; i9 < localOrderedHashSet.size(); i9++)
          {
            localObject22 = (String)localOrderedHashSet.get(i9);
            for (i10 = 0; i10 < ((OrderedHashSet)localObject10).size(); i10++)
              ((GranteeManager)localObject20).revoke((String)localObject22, (String)((OrderedHashSet)localObject10).get(i10), (Grantee)localObject14);
          }
      }
      catch (HsqlException localHsqlException7)
      {
        return Result.newErrorResult(localHsqlException7, this.sql);
      }
    case 6:
      return Result.updateZeroResult;
    case 8:
      localObject3 = (Charset)this.arguments[0];
      try
      {
        setOrCheckObjectName(paramSession, null, ((Charset)localObject3).getName(), true);
        localSchemaManager.addSchemaObject((SchemaObject)localObject3);
      }
      catch (HsqlException localHsqlException11)
      {
        return Result.newErrorResult(localHsqlException11, this.sql);
      }
    case 10:
      localObject3 = (Collation)this.arguments[0];
      try
      {
        setOrCheckObjectName(paramSession, null, ((Collation)localObject3).getName(), true);
        localSchemaManager.addSchemaObject((SchemaObject)localObject3);
      }
      catch (HsqlException localHsqlException12)
      {
        return Result.newErrorResult(localHsqlException12, this.sql);
      }
    case 61:
      try
      {
        paramSession.checkAdmin();
        paramSession.checkDDLWrite();
        localObject3 = (HsqlNameManager.HsqlName)this.arguments[0];
        paramSession.database.getGranteeManager().addRole((HsqlNameManager.HsqlName)localObject3);
      }
      catch (HsqlException localHsqlException8)
      {
        return Result.newErrorResult(localHsqlException8, this.sql);
      }
    case 1074:
      localObject4 = (HsqlNameManager.HsqlName)this.arguments[0];
      localObject6 = (String)this.arguments[1];
      localObject10 = (Grantee)this.arguments[2];
      boolean bool5 = ((Boolean)this.arguments[3]).booleanValue();
      bool7 = ((Boolean)this.arguments[4]).booleanValue();
      try
      {
        paramSession.checkAdmin();
        paramSession.checkDDLWrite();
        paramSession.database.getUserManager().createUser((HsqlNameManager.HsqlName)localObject4, (String)localObject6, bool7);
        if (bool5)
          paramSession.database.getGranteeManager().grant(((HsqlNameManager.HsqlName)localObject4).name, "DBA", (Grantee)localObject10);
      }
      catch (HsqlException localHsqlException26)
      {
        return Result.newErrorResult(localHsqlException26, this.sql);
      }
    case 64:
      localObject4 = (HsqlNameManager.HsqlName)this.arguments[0];
      localObject6 = (Grantee)this.arguments[1];
      try
      {
        paramSession.checkDDLWrite();
        if (localSchemaManager.schemaExists(((HsqlNameManager.HsqlName)localObject4).name))
        {
          if ((!paramSession.isProcessingScript) || (!"PUBLIC".equals(((HsqlNameManager.HsqlName)localObject4).name)))
            throw Error.error(5504, ((HsqlNameManager.HsqlName)localObject4).name);
        }
        else
        {
          localSchemaManager.createSchema((HsqlNameManager.HsqlName)localObject4, (Grantee)localObject6);
          localObject10 = localSchemaManager.findSchema(((HsqlNameManager.HsqlName)localObject4).name);
          this.sql = ((Schema)localObject10).getSQL();
          if ((paramSession.isProcessingScript()) && (paramSession.database.getProperties().isVersion18()))
            paramSession.setCurrentSchemaHsqlName(((Schema)localObject10).getName());
        }
      }
      catch (HsqlException localHsqlException17)
      {
        return Result.newErrorResult(localHsqlException17, this.sql);
      }
    case 14:
      localObject4 = (Routine)this.arguments[0];
      try
      {
        ((Routine)localObject4).resolve(paramSession);
        setOrCheckObjectName(paramSession, null, ((Routine)localObject4).getName(), false);
        localSchemaManager.addSchemaObject((SchemaObject)localObject4);
      }
      catch (HsqlException localHsqlException13)
      {
        return Result.newErrorResult(localHsqlException13, this.sql);
      }
    case 1072:
      localObject4 = (HsqlNameManager.HsqlName)this.arguments[0];
      Routine[] arrayOfRoutine = (Routine[])this.arguments[1];
      try
      {
        paramSession.checkAdmin();
        paramSession.checkDDLWrite();
        if (localObject4 != null)
          for (int n = 0; n < arrayOfRoutine.length; n++)
          {
            arrayOfRoutine[n].setName((HsqlNameManager.HsqlName)localObject4);
            localSchemaManager.addSchemaObject(arrayOfRoutine[n]);
          }
      }
      catch (HsqlException localHsqlException18)
      {
        return Result.newErrorResult(localHsqlException18, this.sql);
      }
    case 133:
      localObject4 = (NumberSequence)this.arguments[0];
      try
      {
        setOrCheckObjectName(paramSession, null, ((NumberSequence)localObject4).getName(), true);
        localSchemaManager.addSchemaObject((SchemaObject)localObject4);
      }
      catch (HsqlException localHsqlException14)
      {
        return Result.newErrorResult(localHsqlException14, this.sql);
      }
    case 23:
      localObject4 = (Type)this.arguments[0];
      localObject7 = ((Type)localObject4).userTypeModifier.getConstraints();
      try
      {
        setOrCheckObjectName(paramSession, null, ((Type)localObject4).getName(), true);
        for (int i1 = 0; i1 < localObject7.length; i1++)
        {
          localObject15 = localObject7[i1];
          setOrCheckObjectName(paramSession, ((Type)localObject4).getName(), ((Constraint)localObject15).getName(), true);
          localSchemaManager.addSchemaObject((SchemaObject)localObject15);
        }
        localSchemaManager.addSchemaObject((SchemaObject)localObject4);
      }
      catch (HsqlException localHsqlException19)
      {
        return Result.newErrorResult(localHsqlException19, this.sql);
      }
    case 77:
      localObject4 = (Table)this.arguments[0];
      localObject7 = (HsqlArrayList)this.arguments[1];
      StatementDMQL localStatementDMQL = (StatementDMQL)this.arguments[2];
      localObject15 = (Boolean)this.arguments[3];
      localObject18 = null;
      try
      {
        setOrCheckObjectName(paramSession, null, ((Table)localObject4).getName(), true);
      }
      catch (HsqlException localHsqlException27)
      {
        if ((localObject15 != null) && (((Boolean)localObject15).booleanValue()))
          return Result.updateZeroResult;
        return Result.newErrorResult(localHsqlException27, this.sql);
      }
      try
      {
        if (this.isSchemaDefinition)
          localObject18 = new HsqlArrayList();
        if (((HsqlArrayList)localObject7).size() != 0)
        {
          localObject4 = ParserDDL.addTableConstraintDefinitions(paramSession, (Table)localObject4, (HsqlArrayList)localObject7, (HsqlArrayList)localObject18, true);
          this.arguments[1] = localObject18;
        }
        ((Table)localObject4).compile(paramSession, null);
        localSchemaManager.addSchemaObject((SchemaObject)localObject4);
        Object localObject21;
        if (localStatementDMQL != null)
        {
          localObject21 = localStatementDMQL.execute(paramSession);
          ((Table)localObject4).insertIntoTable(paramSession, (Result)localObject21);
        }
        if (((Table)localObject4).hasLobColumn)
        {
          localObject21 = ((Table)localObject4).rowIterator(paramSession);
          while (((RowIterator)localObject21).hasNext())
          {
            Row localRow = ((RowIterator)localObject21).getNextRow();
            localObject22 = localRow.getData();
            paramSession.sessionData.adjustLobUsageCount((TableBase)localObject4, (Object[])localObject22, 1);
          }
        }
        return Result.updateZeroResult;
      }
      catch (HsqlException localHsqlException28)
      {
        localSchemaManager.removeExportedKeys((Table)localObject4);
        localSchemaManager.removeDependentObjects(((Table)localObject4).getName());
        return Result.newErrorResult(localHsqlException28, this.sql);
      }
    case 117:
      return Result.updateZeroResult;
    case 79:
      return Result.updateZeroResult;
    case 80:
      localObject4 = (TriggerDef)this.arguments[0];
      localObject7 = (HsqlNameManager.HsqlName)this.arguments[1];
      try
      {
        checkSchemaUpdateAuthorisation(paramSession, ((TriggerDef)localObject4).getSchemaName());
        localSchemaManager.checkSchemaObjectNotExists(((TriggerDef)localObject4).getName());
        if ((localObject7 != null) && (localSchemaManager.getSchemaObject((HsqlNameManager.HsqlName)localObject7) == null))
          throw Error.error(5501, ((HsqlNameManager.HsqlName)localObject7).name);
        ((TriggerDef)localObject4).table.addTrigger((TriggerDef)localObject4, (HsqlNameManager.HsqlName)localObject7);
        localSchemaManager.addSchemaObject((SchemaObject)localObject4);
      }
      catch (HsqlException localHsqlException20)
      {
        return Result.newErrorResult(localHsqlException20, this.sql);
      }
    case 52:
      return Result.updateZeroResult;
    case 83:
      localObject4 = (Type)this.arguments[0];
      try
      {
        setOrCheckObjectName(paramSession, null, ((Type)localObject4).getName(), true);
        localSchemaManager.addSchemaObject((SchemaObject)localObject4);
      }
      catch (HsqlException localHsqlException15)
      {
        return Result.newErrorResult(localHsqlException15, this.sql);
      }
    case 114:
      return Result.updateZeroResult;
    case 84:
      localObject4 = (View)this.arguments[0];
      try
      {
        checkSchemaUpdateAuthorisation(paramSession, ((View)localObject4).getSchemaName());
        localSchemaManager.checkSchemaObjectNotExists(((View)localObject4).getName());
        ((View)localObject4).compile(paramSession, null);
        localSchemaManager.addSchemaObject((SchemaObject)localObject4);
      }
      catch (HsqlException localHsqlException16)
      {
        return Result.newErrorResult(localHsqlException16, this.sql);
      }
    case 1073:
      localObject4 = (Table)this.arguments[0];
      localObject11 = (int[])this.arguments[1];
      localObject8 = (HsqlNameManager.HsqlName)this.arguments[2];
      boolean bool6 = ((Boolean)this.arguments[3]).booleanValue();
      try
      {
        setOrCheckObjectName(paramSession, ((Table)localObject4).getName(), (HsqlNameManager.HsqlName)localObject8, true);
        localObject18 = new TableWorks(paramSession, (Table)localObject4);
        ((TableWorks)localObject18).addIndex((int[])localObject11, (HsqlNameManager.HsqlName)localObject8, bool6);
      }
      catch (HsqlException localHsqlException24)
      {
        return Result.newErrorResult(localHsqlException24, this.sql);
      }
    case 1071:
      localObject4 = (HsqlNameManager.HsqlName)this.arguments[0];
      localObject8 = (String)this.arguments[1];
      switch (((HsqlNameManager.HsqlName)localObject4).type)
      {
      case 9:
        localObject11 = (Table)localSchemaManager.getSchemaObject(((HsqlNameManager.HsqlName)localObject4).parent.name, ((HsqlNameManager.HsqlName)localObject4).parent.schema.name, 3);
        if (!paramSession.getGrantee().isFullyAccessibleByRole(((Table)localObject11).getName()))
          throw Error.error(5501);
        int i5 = ((Table)localObject11).getColumnIndex(((HsqlNameManager.HsqlName)localObject4).name);
        if (i5 < 0)
          throw Error.error(5501);
        ColumnSchema localColumnSchema = ((Table)localObject11).getColumn(i5);
        localColumnSchema.getName().comment = ((String)localObject8);
        break;
      case 18:
        localObject11 = (RoutineSchema)localSchemaManager.getSchemaObject(((HsqlNameManager.HsqlName)localObject4).name, ((HsqlNameManager.HsqlName)localObject4).schema.name, 18);
        if (!paramSession.getGrantee().isFullyAccessibleByRole(((RoutineSchema)localObject11).getName()))
          throw Error.error(5501);
        ((RoutineSchema)localObject11).getName().comment = ((String)localObject8);
        break;
      case 3:
        localObject11 = (Table)localSchemaManager.getSchemaObject(((HsqlNameManager.HsqlName)localObject4).name, ((HsqlNameManager.HsqlName)localObject4).schema.name, 3);
        if (!paramSession.getGrantee().isFullyAccessibleByRole(((Table)localObject11).getName()))
          throw Error.error(5501);
        ((Table)localObject11).getName().comment = ((String)localObject8);
      }
      break;
    case 1199:
      break;
    }
    throw Error.runtimeError(201, "StatementSchema");
    label4822: return Result.updateZeroResult;
  }

  private void dropType(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean)
  {
    checkSchemaUpdateAuthorisation(paramSession, paramHsqlName.schema);
    Type localType = (Type)paramSession.database.schemaManager.getSchemaObject(paramHsqlName);
    paramSession.database.schemaManager.removeSchemaObject(paramHsqlName, paramBoolean);
    localType.userTypeModifier = null;
  }

  private static void dropDomain(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean)
  {
    Type localType = (Type)paramSession.database.schemaManager.getSchemaObject(paramHsqlName);
    OrderedHashSet localOrderedHashSet = paramSession.database.schemaManager.getReferencesTo(localType.getName());
    if ((!paramBoolean) && (localOrderedHashSet.size() > 0))
    {
      localObject = (HsqlNameManager.HsqlName)localOrderedHashSet.get(0);
      throw Error.error(5502, ((HsqlNameManager.HsqlName)localObject).getSchemaQualifiedStatementName());
    }
    Object localObject = localType.userTypeModifier.getConstraints();
    localOrderedHashSet = new OrderedHashSet();
    for (int i = 0; i < localObject.length; i++)
      localOrderedHashSet.add(localObject[i].getName());
    paramSession.database.schemaManager.removeSchemaObjects(localOrderedHashSet);
    paramSession.database.schemaManager.removeSchemaObject(localType.getName(), paramBoolean);
    localType.userTypeModifier = null;
  }

  private static void dropRole(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean)
  {
    Grantee localGrantee = paramSession.database.getGranteeManager().getRole(paramHsqlName.name);
    if ((!paramBoolean) && (paramSession.database.schemaManager.hasSchemas(localGrantee)))
    {
      HsqlArrayList localHsqlArrayList = paramSession.database.schemaManager.getSchemas(localGrantee);
      Schema localSchema = (Schema)localHsqlArrayList.get(0);
      throw Error.error(5502, localSchema.getName().statementName);
    }
    paramSession.database.schemaManager.dropSchemas(paramSession, localGrantee, paramBoolean);
    paramSession.database.getGranteeManager().dropRole(paramHsqlName.name);
  }

  private static void dropUser(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean)
  {
    User localUser = paramSession.database.getUserManager().get(paramHsqlName.name);
    if (paramSession.database.getSessionManager().isUserActive(paramHsqlName.name))
      throw Error.error(5539);
    if ((!paramBoolean) && (paramSession.database.schemaManager.hasSchemas(localUser)))
    {
      HsqlArrayList localHsqlArrayList = paramSession.database.schemaManager.getSchemas(localUser);
      Schema localSchema = (Schema)localHsqlArrayList.get(0);
      throw Error.error(5502, localSchema.getName().statementName);
    }
    paramSession.database.schemaManager.dropSchemas(paramSession, localUser, paramBoolean);
    paramSession.database.getUserManager().dropUser(paramHsqlName.name);
  }

  private void dropSchema(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean)
  {
    HsqlNameManager.HsqlName localHsqlName = paramSession.database.schemaManager.getUserSchemaHsqlName(paramHsqlName.name);
    checkSchemaUpdateAuthorisation(paramSession, localHsqlName);
    paramSession.database.schemaManager.dropSchema(paramSession, paramHsqlName.name, paramBoolean);
  }

  private void dropRoutine(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean)
  {
    checkSchemaUpdateAuthorisation(paramSession, paramHsqlName.schema);
    paramSession.database.schemaManager.removeSchemaObject(paramHsqlName, paramBoolean);
  }

  private void dropObject(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean)
  {
    paramHsqlName = paramSession.database.schemaManager.getSchemaObjectName(paramHsqlName.schema, paramHsqlName.name, paramHsqlName.type, true);
    paramSession.database.schemaManager.removeSchemaObject(paramHsqlName, paramBoolean);
  }

  private void dropTable(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, boolean paramBoolean)
  {
    Table localTable = paramSession.database.schemaManager.findUserTable(paramSession, paramHsqlName.name, paramHsqlName.schema.name);
    paramSession.database.schemaManager.dropTableOrView(paramSession, localTable, paramBoolean);
  }

  static void checkSchemaUpdateAuthorisation(Session paramSession, HsqlNameManager.HsqlName paramHsqlName)
  {
    if (paramSession.isProcessingLog)
      return;
    if (SqlInvariants.isSystemSchemaName(paramHsqlName.name))
      throw Error.error(5503);
    if (paramSession.parser.isSchemaDefinition)
    {
      if (paramHsqlName == paramSession.getCurrentSchemaHsqlName())
        return;
      Error.error(5505, paramHsqlName.name);
    }
    paramSession.getGrantee().checkSchemaUpdateOrGrantRights(paramHsqlName.name);
    paramSession.checkDDLWrite();
  }

  void setOrCheckObjectName(Session paramSession, HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2, boolean paramBoolean)
  {
    if (paramHsqlName2.schema == null)
    {
      paramHsqlName2.schema = (this.schemaName == null ? paramSession.getCurrentSchemaHsqlName() : this.schemaName);
    }
    else
    {
      paramHsqlName2.schema = paramSession.getSchemaHsqlName(paramHsqlName2.schema.name);
      if (paramHsqlName2.schema == null)
        throw Error.error(5505);
      if ((this.isSchemaDefinition) && (this.schemaName != paramHsqlName2.schema))
        throw Error.error(5505);
    }
    paramHsqlName2.parent = paramHsqlName1;
    if (!this.isSchemaDefinition)
      checkSchemaUpdateAuthorisation(paramSession, paramHsqlName2.schema);
    if (paramBoolean)
      paramSession.database.schemaManager.checkSchemaObjectNotExists(paramHsqlName2);
  }

  void setSchemaName(Session paramSession, HsqlNameManager.HsqlName paramHsqlName1, HsqlNameManager.HsqlName paramHsqlName2)
  {
    if (paramHsqlName2.schema == null)
    {
      paramHsqlName2.schema = (this.schemaName == null ? paramSession.getCurrentSchemaHsqlName() : this.schemaName);
    }
    else
    {
      paramHsqlName2.schema = paramSession.getSchemaHsqlName(paramHsqlName2.schema.name);
      if (paramHsqlName2.schema == null)
        throw Error.error(5505);
      if ((this.isSchemaDefinition) && (this.schemaName != paramHsqlName2.schema))
        throw Error.error(5505);
    }
  }

  public boolean isAutoCommitStatement()
  {
    return true;
  }

  public String describe(Session paramSession)
  {
    return this.sql;
  }

  public Object[] getArguments()
  {
    return this.arguments;
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.StatementSchema
 * JD-Core Version:    0.6.2
 */