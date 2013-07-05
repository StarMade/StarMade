package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.IntValueHashMap;
import org.hsqldb.lib.OrderedIntHashSet;
import org.hsqldb.rights.Grantee;
import org.hsqldb.rights.User;
import org.hsqldb.store.ValuePool;
import org.hsqldb.types.BinaryData;
import org.hsqldb.types.BinaryType;
import org.hsqldb.types.BlobData;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.DTIType;
import org.hsqldb.types.DateTimeType;
import org.hsqldb.types.IntervalType;
import org.hsqldb.types.NumberType;
import org.hsqldb.types.Type;

public class FunctionSQL extends Expression
{
  protected static final int FUNC_POSITION_CHAR = 1;
  private static final int FUNC_POSITION_BINARY = 2;
  private static final int FUNC_OCCURENCES_REGEX = 3;
  private static final int FUNC_POSITION_REGEX = 4;
  protected static final int FUNC_EXTRACT = 5;
  protected static final int FUNC_BIT_LENGTH = 6;
  protected static final int FUNC_CHAR_LENGTH = 7;
  protected static final int FUNC_OCTET_LENGTH = 8;
  private static final int FUNC_CARDINALITY = 9;
  private static final int FUNC_MAX_CARDINALITY = 10;
  private static final int FUNC_TRIM_ARRAY = 11;
  private static final int FUNC_ABS = 12;
  private static final int FUNC_MOD = 13;
  protected static final int FUNC_LN = 14;
  private static final int FUNC_EXP = 15;
  private static final int FUNC_POWER = 16;
  private static final int FUNC_SQRT = 17;
  private static final int FUNC_FLOOR = 20;
  private static final int FUNC_CEILING = 21;
  private static final int FUNC_WIDTH_BUCKET = 22;
  protected static final int FUNC_SUBSTRING_CHAR = 23;
  private static final int FUNC_SUBSTRING_REG_EXPR = 24;
  private static final int FUNC_SUBSTRING_REGEX = 25;
  protected static final int FUNC_FOLD_LOWER = 26;
  protected static final int FUNC_FOLD_UPPER = 27;
  private static final int FUNC_TRANSCODING = 28;
  private static final int FUNC_TRANSLITERATION = 29;
  private static final int FUNC_REGEX_TRANSLITERATION = 30;
  protected static final int FUNC_TRIM_CHAR = 31;
  static final int FUNC_OVERLAY_CHAR = 32;
  private static final int FUNC_CHAR_NORMALIZE = 33;
  private static final int FUNC_SUBSTRING_BINARY = 40;
  private static final int FUNC_TRIM_BINARY = 41;
  private static final int FUNC_OVERLAY_BINARY = 42;
  protected static final int FUNC_CURRENT_DATE = 43;
  protected static final int FUNC_CURRENT_TIME = 44;
  protected static final int FUNC_CURRENT_TIMESTAMP = 50;
  protected static final int FUNC_LOCALTIME = 51;
  protected static final int FUNC_LOCALTIMESTAMP = 52;
  private static final int FUNC_CURRENT_CATALOG = 53;
  private static final int FUNC_CURRENT_DEFAULT_TRANSFORM_GROUP = 54;
  private static final int FUNC_CURRENT_PATH = 55;
  private static final int FUNC_CURRENT_ROLE = 56;
  private static final int FUNC_CURRENT_SCHEMA = 57;
  private static final int FUNC_CURRENT_TRANSFORM_GROUP_FOR_TYPE = 58;
  private static final int FUNC_CURRENT_USER = 59;
  private static final int FUNC_SESSION_USER = 60;
  private static final int FUNC_SYSTEM_USER = 61;
  protected static final int FUNC_USER = 62;
  private static final int FUNC_VALUE = 63;
  static final short[] noParamList = new short[0];
  static final short[] emptyParamList = { 786, 772 };
  static final short[] optionalNoParamList = { 842, 2, 786, 772 };
  static final short[] optionalSingleParamList = { 786, 842, 1, 788, 772 };
  static final short[] singleParamList = { 786, 788, 772 };
  static final short[] optionalIntegerParamList = { 842, 3, 786, 844, 772 };
  static final short[] doubleParamList = { 786, 788, 774, 788, 772 };
  static final short[] tripleParamList = { 786, 788, 774, 788, 774, 788, 772 };
  static final short[] quadParamList = { 786, 788, 774, 788, 774, 788, 774, 788, 772 };
  static IntValueHashMap valueFuncMap = new IntValueHashMap();
  static IntValueHashMap regularFuncMap = new IntValueHashMap();
  static OrderedIntHashSet nonDeterministicFuncSet = new OrderedIntHashSet();
  int funcType;
  boolean isDeterministic;
  String name;
  short[] parseList;
  short[] parseListAlt;
  boolean isSQLValueFunction;

  public static FunctionSQL newSQLFunction(String paramString, ParserDQL.CompileContext paramCompileContext)
  {
    int i = regularFuncMap.get(paramString, -1);
    boolean bool = false;
    if (i == -1)
    {
      i = valueFuncMap.get(paramString, -1);
      bool = true;
    }
    if (i == -1)
      return null;
    FunctionSQL localFunctionSQL = new FunctionSQL(i);
    if (i == 63)
    {
      if (paramCompileContext.currentDomain == null)
        return null;
      localFunctionSQL.dataType = paramCompileContext.currentDomain;
    }
    else
    {
      localFunctionSQL.isSQLValueFunction = bool;
    }
    return localFunctionSQL;
  }

  protected FunctionSQL()
  {
    super(28);
    this.nodes = Expression.emptyArray;
  }

  protected FunctionSQL(int paramInt)
  {
    this();
    this.funcType = paramInt;
    this.isDeterministic = (!nonDeterministicFuncSet.contains(paramInt));
    switch (paramInt)
    {
    case 1:
    case 2:
      this.name = "POSITION";
      this.parseList = new short[] { 786, 788, 130, 788, 842, 5, 306, 841, 2, 355, 454, 772 };
      break;
    case 3:
    case 4:
      break;
    case 5:
      this.name = "EXTRACT";
      this.parseList = new short[] { 786, 841, 17, 323, 173, 73, 127, 169, 250, 671, 765, 711, 672, 670, 765, 669, 702, 724, 283, 284, 115, 788, 772 };
      break;
    case 7:
      this.name = "CHAR_LENGTH";
      this.parseList = new short[] { 786, 788, 842, 5, 306, 841, 2, 355, 454, 772 };
      break;
    case 6:
      this.name = "BIT_LENGTH";
      this.parseList = singleParamList;
      break;
    case 8:
      this.name = "OCTET_LENGTH";
      this.parseList = singleParamList;
      break;
    case 9:
      this.name = "CARDINALITY";
      this.parseList = singleParamList;
      break;
    case 10:
      this.name = "MAX_CARDINALITY";
      this.parseList = singleParamList;
      break;
    case 11:
      this.name = "TRIM_ARRAY";
      this.parseList = doubleParamList;
      break;
    case 12:
      this.name = "ABS";
      this.parseList = singleParamList;
      break;
    case 13:
      this.name = "MOD";
      this.parseList = doubleParamList;
      break;
    case 14:
      this.name = "LN";
      this.parseList = singleParamList;
      break;
    case 15:
      this.name = "EXP";
      this.parseList = singleParamList;
      break;
    case 16:
      this.name = "POWER";
      this.parseList = doubleParamList;
      break;
    case 17:
      this.name = "SQRT";
      this.parseList = singleParamList;
      break;
    case 20:
      this.name = "FLOOR";
      this.parseList = singleParamList;
      break;
    case 21:
      this.name = "CEILING";
      this.parseList = singleParamList;
      break;
    case 22:
      this.name = "WIDTH_BUCKET";
      this.parseList = quadParamList;
      break;
    case 23:
    case 40:
      this.name = "SUBSTRING";
      this.parseList = new short[] { 786, 788, 115, 788, 842, 2, 112, 788, 842, 5, 306, 841, 2, 355, 454, 772 };
      this.parseListAlt = new short[] { 786, 788, 774, 788, 842, 2, 774, 788, 772 };
      break;
    case 26:
      this.name = "LOWER";
      this.parseList = singleParamList;
      break;
    case 27:
      this.name = "UPPER";
      this.parseList = singleParamList;
      break;
    case 31:
    case 41:
      this.name = "TRIM";
      this.parseList = new short[] { 786, 842, 11, 842, 5, 841, 3, 151, 286, 23, 842, 1, 788, 115, 788, 772 };
      break;
    case 32:
    case 42:
      this.name = "OVERLAY";
      this.parseList = new short[] { 786, 788, 473, 788, 115, 788, 842, 2, 112, 788, 842, 2, 306, 355, 772 };
      break;
    case 53:
      this.name = "CURRENT_CATALOG";
      this.parseList = noParamList;
      break;
    case 56:
      this.name = "CURRENT_ROLE";
      this.parseList = noParamList;
      break;
    case 57:
      this.name = "CURRENT_SCHEMA";
      this.parseList = noParamList;
      break;
    case 59:
      this.name = "CURRENT_USER";
      this.parseList = noParamList;
      break;
    case 60:
      this.name = "SESSION_USER";
      this.parseList = noParamList;
      break;
    case 61:
      this.name = "SYSTEM_USER";
      this.parseList = noParamList;
      break;
    case 62:
      this.name = "USER";
      this.parseList = optionalNoParamList;
      break;
    case 63:
      this.name = "VALUE";
      this.parseList = noParamList;
      break;
    case 43:
      this.name = "CURRENT_DATE";
      this.parseList = noParamList;
      break;
    case 44:
      this.name = "CURRENT_TIME";
      this.parseList = optionalIntegerParamList;
      break;
    case 50:
      this.name = "CURRENT_TIMESTAMP";
      this.parseList = optionalIntegerParamList;
      break;
    case 51:
      this.name = "LOCALTIME";
      this.parseList = optionalIntegerParamList;
      break;
    case 52:
      this.name = "LOCALTIMESTAMP";
      this.parseList = optionalIntegerParamList;
      break;
    case 18:
    case 19:
    case 24:
    case 25:
    case 28:
    case 29:
    case 30:
    case 33:
    case 34:
    case 35:
    case 36:
    case 37:
    case 38:
    case 39:
    case 45:
    case 46:
    case 47:
    case 48:
    case 49:
    case 54:
    case 55:
    case 58:
    default:
      throw Error.runtimeError(201, "FunctionSQL");
    }
  }

  public void setArguments(Expression[] paramArrayOfExpression)
  {
    this.nodes = paramArrayOfExpression;
  }

  public Expression getFunctionExpression()
  {
    return this;
  }

  public Object getValue(Session paramSession)
  {
    Object[] arrayOfObject = new Object[this.nodes.length];
    for (int i = 0; i < this.nodes.length; i++)
    {
      Expression localExpression = this.nodes[i];
      if (localExpression != null)
        arrayOfObject[i] = localExpression.getValue(paramSession, localExpression.dataType);
    }
    return getValue(paramSession, arrayOfObject);
  }

  Object getValue(Session paramSession, Object[] paramArrayOfObject)
  {
    long l1;
    int m;
    long l2;
    int j;
    Object localObject1;
    double d1;
    Object localObject6;
    Object localObject8;
    Object localObject3;
    long l4;
    long l8;
    switch (this.funcType)
    {
    case 1:
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null))
        return null;
      l1 = 0L;
      if ((this.nodes.length > 3) && (this.nodes[3] != null))
      {
        Object localObject5 = this.nodes[3].getValue(paramSession);
        l1 = ((Number)localObject5).longValue() - 1L;
        if (l1 < 0L)
          l1 = 0L;
      }
      long l6 = ((CharacterType)this.nodes[1].dataType).position(paramSession, paramArrayOfObject[1], paramArrayOfObject[0], this.nodes[0].dataType, l1) + 1L;
      if ((this.nodes[2] != null) && (((Number)this.nodes[2].valueData).intValue() == 454))
        l6 *= 2L;
      return ValuePool.getLong(l6);
    case 2:
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null))
        return null;
      l1 = ((BinaryType)this.nodes[1].dataType).position(paramSession, (BlobData)paramArrayOfObject[1], (BlobData)paramArrayOfObject[0], this.nodes[0].dataType, 0L) + 1L;
      if ((this.nodes[2] != null) && (((Number)this.nodes[2].valueData).intValue() == 454))
        l1 *= 2L;
      return ValuePool.getLong(l1);
    case 5:
      if (paramArrayOfObject[1] == null)
        return null;
      int i = ((Number)this.nodes[0].valueData).intValue();
      i = DTIType.getFieldNameTypeForToken(i);
      switch (i)
      {
      case 106:
        return ((DTIType)this.nodes[1].dataType).getSecondPart(paramArrayOfObject[1]);
      case 264:
      case 265:
        return ((DateTimeType)this.nodes[1].dataType).getPartString(paramSession, paramArrayOfObject[1], i);
      }
      m = ((DTIType)this.nodes[1].dataType).getPart(paramSession, paramArrayOfObject[1], i);
      return ValuePool.getInt(m);
    case 7:
      if (paramArrayOfObject[0] == null)
        return null;
      l2 = ((CharacterType)this.nodes[0].dataType).size(paramSession, paramArrayOfObject[0]);
      return ValuePool.getLong(l2);
    case 6:
      if (paramArrayOfObject[0] == null)
        return null;
      if (this.nodes[0].dataType.isBinaryType())
        l2 = ((BlobData)paramArrayOfObject[0]).bitLength(paramSession);
      else
        l2 = 16L * ((CharacterType)this.nodes[0].dataType).size(paramSession, paramArrayOfObject[0]);
      return ValuePool.getLong(l2);
    case 8:
      if (paramArrayOfObject[0] == null)
        return null;
      if (this.nodes[0].dataType.isBinaryType())
        l2 = ((BlobData)paramArrayOfObject[0]).length(paramSession);
      else
        l2 = 2L * ((CharacterType)this.nodes[0].dataType).size(paramSession, paramArrayOfObject[0]);
      return ValuePool.getLong(l2);
    case 9:
      if (paramArrayOfObject[0] == null)
        return null;
      j = this.nodes[0].dataType.cardinality(paramSession, paramArrayOfObject[0]);
      return ValuePool.getInt(j);
    case 10:
      if (paramArrayOfObject[0] == null)
        return null;
      j = this.nodes[0].dataType.arrayLimitCardinality();
      return ValuePool.getInt(j);
    case 11:
      if (paramArrayOfObject[0] == null)
        return null;
      if (paramArrayOfObject[1] == null)
        return null;
      localObject1 = (Object[])paramArrayOfObject[0];
      m = ((Number)paramArrayOfObject[1]).intValue();
      if ((m < 0) || (m > localObject1.length))
        throw Error.error(3490);
      Object[] arrayOfObject = new Object[localObject1.length - m];
      System.arraycopy(localObject1, 0, arrayOfObject, 0, arrayOfObject.length);
      return arrayOfObject;
    case 12:
      if (paramArrayOfObject[0] == null)
        return null;
      return this.dataType.absolute(paramArrayOfObject[0]);
    case 13:
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null))
        return null;
      localObject1 = ((NumberType)this.nodes[0].dataType).modulo(paramArrayOfObject[0], paramArrayOfObject[1], this.nodes[0].dataType);
      return this.dataType.convertToType(paramSession, localObject1, this.nodes[0].dataType);
    case 14:
      if (paramArrayOfObject[0] == null)
        return null;
      d1 = ((Number)paramArrayOfObject[0]).doubleValue();
      if (d1 <= 0.0D)
        throw Error.error(3444);
      d1 = Math.log(d1);
      return ValuePool.getDouble(Double.doubleToLongBits(d1));
    case 15:
      if (paramArrayOfObject[0] == null)
        return null;
      d1 = Math.exp(((Number)paramArrayOfObject[0]).doubleValue());
      return ValuePool.getDouble(Double.doubleToLongBits(d1));
    case 16:
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null))
        return null;
      d1 = ((Number)paramArrayOfObject[0]).doubleValue();
      double d2 = ((Number)paramArrayOfObject[1]).doubleValue();
      double d3;
      if (d1 == 0.0D)
      {
        if (d2 < 0.0D)
          throw Error.error(3445);
        if (d2 == 0.0D)
          d3 = 1.0D;
        else
          d3 = 0.0D;
      }
      else
      {
        d3 = Math.pow(d1, d2);
      }
      return ValuePool.getDouble(Double.doubleToLongBits(d3));
    case 17:
      if (paramArrayOfObject[0] == null)
        return null;
      d1 = Math.sqrt(((Number)paramArrayOfObject[0]).doubleValue());
      return ValuePool.getDouble(Double.doubleToLongBits(d1));
    case 20:
      if (paramArrayOfObject[0] == null)
        return null;
      return ((NumberType)this.dataType).floor(paramArrayOfObject[0]);
    case 21:
      if (paramArrayOfObject[0] == null)
        return null;
      return ((NumberType)this.dataType).ceiling(paramArrayOfObject[0]);
    case 22:
      for (int k = 0; k < paramArrayOfObject.length; k++)
        if (paramArrayOfObject[k] == null)
          return null;
      if (((NumberType)this.nodes[3].dataType).isNegative(paramArrayOfObject[3]))
        throw Error.error(3446);
      k = this.nodes[1].dataType.compare(paramSession, paramArrayOfObject[1], paramArrayOfObject[2]);
      Type localType;
      if (this.nodes[0].dataType.isNumberType())
        localType = this.nodes[0].dataType;
      else
        localType = this.nodes[0].dataType.getCombinedType(paramSession, this.nodes[0].dataType, 33);
      Object localObject7;
      switch (k)
      {
      case 0:
        throw Error.error(3446);
      case -1:
        if (this.nodes[0].dataType.compare(paramSession, paramArrayOfObject[0], paramArrayOfObject[1]) < 0)
          return ValuePool.INTEGER_0;
        if (this.nodes[0].dataType.compare(paramSession, paramArrayOfObject[0], paramArrayOfObject[2]) >= 0)
          return this.dataType.add(paramArrayOfObject[3], ValuePool.INTEGER_1, Type.SQL_INTEGER);
        localObject6 = localType.subtract(paramArrayOfObject[0], paramArrayOfObject[1], this.nodes[0].dataType);
        localObject7 = localType.subtract(paramArrayOfObject[2], paramArrayOfObject[1], this.nodes[0].dataType);
        break;
      case 1:
        if (this.nodes[0].dataType.compare(paramSession, paramArrayOfObject[0], paramArrayOfObject[1]) > 0)
          return ValuePool.INTEGER_0;
        if (this.nodes[0].dataType.compare(paramSession, paramArrayOfObject[0], paramArrayOfObject[2]) <= 0)
          return this.dataType.add(paramArrayOfObject[3], ValuePool.INTEGER_1, Type.SQL_INTEGER);
        localObject6 = localType.subtract(paramArrayOfObject[1], paramArrayOfObject[0], this.nodes[0].dataType);
        localObject7 = localType.subtract(paramArrayOfObject[1], paramArrayOfObject[2], this.nodes[0].dataType);
        break;
      default:
        throw Error.runtimeError(201, "");
      }
      if (localType.typeCode == 8)
      {
        localObject8 = localType;
      }
      else
      {
        localObject8 = IntervalType.factorType;
        localObject6 = ((Type)localObject8).convertToType(paramSession, localObject6, localType);
        localObject7 = ((Type)localObject8).convertToType(paramSession, localObject7, localType);
      }
      localObject6 = ((Type)localObject8).multiply(localObject6, paramArrayOfObject[3]);
      localObject6 = ((Type)localObject8).divide(paramSession, localObject6, localObject7);
      localObject6 = this.dataType.convertToDefaultType(paramSession, localObject6);
      return this.dataType.add(localObject6, ValuePool.INTEGER_1, Type.SQL_INTEGER);
    case 23:
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null))
        return null;
      Object localObject2 = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].dataType);
      long l3 = ((Number)localObject2).longValue() - 1L;
      long l7 = 0L;
      if (this.nodes[2] != null)
      {
        if (paramArrayOfObject[2] == null)
          return null;
        localObject2 = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[2], this.nodes[2].dataType);
        l7 = ((Number)localObject2).longValue();
      }
      return ((CharacterType)this.dataType).substring(paramSession, paramArrayOfObject[0], l3, l7, ((this.nodes.length <= 3) || (this.nodes[3] == null) || (((Number)this.nodes[2].valueData).intValue() != 454)) || (this.nodes[2] != null), false);
    case 26:
      if (paramArrayOfObject[0] == null)
        return null;
      return ((CharacterType)this.dataType).lower(paramSession, paramArrayOfObject[0]);
    case 27:
      if (paramArrayOfObject[0] == null)
        return null;
      return ((CharacterType)this.dataType).upper(paramSession, paramArrayOfObject[0]);
    case 31:
      if ((paramArrayOfObject[1] == null) || (paramArrayOfObject[2] == null))
        return null;
      boolean bool1 = false;
      boolean bool3 = false;
      switch (((Number)this.nodes[0].valueData).intValue())
      {
      case 23:
        bool1 = bool3 = 1;
        break;
      case 151:
        bool1 = true;
        break;
      case 286:
        bool3 = true;
        break;
      default:
        throw Error.runtimeError(201, "FunctionSQL");
      }
      localObject6 = (String)paramArrayOfObject[1];
      if (((String)localObject6).length() != 1)
        throw Error.error(3460);
      int i1 = ((String)localObject6).charAt(0);
      return ((CharacterType)this.dataType).trim(paramSession, paramArrayOfObject[2], i1, bool1, bool3);
    case 32:
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null) || (paramArrayOfObject[2] == null))
        return null;
      localObject3 = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[2], this.nodes[2].dataType);
      l4 = ((Number)localObject3).longValue() - 1L;
      l8 = 0L;
      if (this.nodes[3] != null)
      {
        if (paramArrayOfObject[3] == null)
          return null;
        localObject3 = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[3], this.nodes[3].dataType);
        l8 = ((Number)localObject3).longValue();
      }
      return ((CharacterType)this.dataType).overlay(null, paramArrayOfObject[0], paramArrayOfObject[1], l4, l8, this.nodes[3] != null);
    case 40:
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null))
        return null;
      localObject3 = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[1], this.nodes[1].dataType);
      l4 = ((Number)localObject3).longValue() - 1L;
      l8 = 0L;
      if (this.nodes[2] != null)
      {
        if (paramArrayOfObject[2] == null)
          return null;
        localObject3 = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[2], this.nodes[2].dataType);
        l8 = ((Number)localObject3).intValue();
      }
      return ((BinaryType)this.dataType).substring(paramSession, (BlobData)paramArrayOfObject[0], l4, l8, this.nodes[2] != null);
    case 41:
      if ((paramArrayOfObject[1] == null) || (paramArrayOfObject[2] == null))
        return null;
      boolean bool2 = false;
      boolean bool4 = false;
      int n = ((Number)this.nodes[0].valueData).intValue();
      switch (n)
      {
      case 23:
        bool2 = bool4 = 1;
        break;
      case 151:
        bool2 = true;
        break;
      case 286:
        bool4 = true;
        break;
      default:
        throw Error.runtimeError(201, "FunctionSQL");
      }
      BlobData localBlobData = (BlobData)paramArrayOfObject[1];
      if (localBlobData.length(paramSession) != 1L)
        throw Error.error(3460);
      localObject8 = localBlobData.getBytes();
      return ((BinaryType)this.dataType).trim(paramSession, (BlobData)paramArrayOfObject[2], localObject8[0], bool2, bool4);
    case 42:
      if ((paramArrayOfObject[0] == null) || (paramArrayOfObject[1] == null) || (paramArrayOfObject[2] == null))
        return null;
      Object localObject4 = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[2], this.nodes[2].dataType);
      long l5 = ((Number)localObject4).longValue() - 1L;
      long l9 = 0L;
      if (this.nodes[3] != null)
      {
        if (paramArrayOfObject[3] == null)
          return null;
        localObject4 = Type.SQL_BIGINT.convertToType(paramSession, paramArrayOfObject[3], this.nodes[3].dataType);
        l9 = ((Number)localObject4).longValue();
      }
      return ((BinaryType)this.dataType).overlay(paramSession, (BlobData)paramArrayOfObject[0], (BlobData)paramArrayOfObject[1], l5, l9, this.nodes[3] != null);
    case 53:
      return paramSession.database.getCatalogName().name;
    case 56:
      return paramSession.getRole() == null ? null : paramSession.getRole().getName().getNameString();
    case 57:
      return paramSession.getCurrentSchemaHsqlName().name;
    case 59:
      return paramSession.getUser().getName().getNameString();
    case 60:
      return paramSession.getUser().getName().getNameString();
    case 61:
      return paramSession.getUser().getName().getNameString();
    case 62:
      return paramSession.getUser().getName().getNameString();
    case 63:
      return paramSession.sessionData.currentValue;
    case 43:
      return paramSession.getCurrentDate();
    case 44:
      return this.dataType.convertToTypeLimits(paramSession, paramSession.getCurrentTime(true));
    case 50:
      return this.dataType.convertToTypeLimits(paramSession, paramSession.getCurrentTimestamp(true));
    case 51:
      return this.dataType.convertToTypeLimits(paramSession, paramSession.getCurrentTime(false));
    case 52:
      return this.dataType.convertToTypeLimits(paramSession, paramSession.getCurrentTimestamp(false));
    case 3:
    case 4:
    case 18:
    case 19:
    case 24:
    case 25:
    case 28:
    case 29:
    case 30:
    case 33:
    case 34:
    case 35:
    case 36:
    case 37:
    case 38:
    case 39:
    case 45:
    case 46:
    case 47:
    case 48:
    case 49:
    case 54:
    case 55:
    case 58:
    }
    throw Error.runtimeError(201, "FunctionSQL");
  }

  public void resolveTypes(Session paramSession, Expression paramExpression)
  {
    for (int i = 0; i < this.nodes.length; i++)
      if (this.nodes[i] != null)
        this.nodes[i].resolveTypes(paramSession, this);
    switch (this.funcType)
    {
    case 1:
    case 2:
      if (this.nodes[0].dataType == null)
      {
        if (this.nodes[1].dataType == null)
          throw Error.error(5567);
        if ((this.nodes[1].dataType.typeCode == 40) || (this.nodes[1].dataType.isBinaryType()))
          this.nodes[0].dataType = this.nodes[1].dataType;
        else
          this.nodes[0].dataType = Type.SQL_VARCHAR;
      }
      if (this.nodes[1].dataType == null)
        if ((this.nodes[0].dataType.typeCode == 40) || (this.nodes[0].dataType.isBinaryType()))
          this.nodes[1].dataType = this.nodes[0].dataType;
        else
          this.nodes[1].dataType = Type.SQL_VARCHAR;
      if ((this.nodes[0].dataType.isCharacterType()) && (this.nodes[1].dataType.isCharacterType()))
      {
        this.funcType = 1;
      }
      else if ((this.nodes[0].dataType.isBinaryType()) && (this.nodes[1].dataType.isBinaryType()))
      {
        if ((this.nodes[0].dataType.isBitType()) || (this.nodes[1].dataType.isBitType()))
          throw Error.error(5563);
        this.funcType = 2;
      }
      else
      {
        throw Error.error(5563);
      }
      if ((this.nodes.length > 3) && (this.nodes[3] != null))
      {
        if (this.nodes[3].isDynamicParam())
          this.nodes[3].dataType = Type.SQL_BIGINT;
        if (!this.nodes[3].dataType.isNumberType())
          throw Error.error(5563);
      }
      this.dataType = Type.SQL_BIGINT;
      break;
    case 5:
      if (this.nodes[1].dataType == null)
        throw Error.error(5567);
      if ((!this.nodes[1].dataType.isDateTimeType()) && (!this.nodes[1].dataType.isIntervalType()))
        throw Error.error(5563);
      i = ((Number)this.nodes[0].valueData).intValue();
      DTIType localDTIType = (DTIType)this.nodes[1].dataType;
      i = DTIType.getFieldNameTypeForToken(i);
      this.dataType = localDTIType.getExtractType(i);
      break;
    case 6:
      if (this.nodes[0].dataType == null)
        this.nodes[0].dataType = Type.SQL_BIT_VARYING_MAX_LENGTH;
      if ((!this.nodes[0].dataType.isCharacterType()) && (!this.nodes[0].dataType.isBinaryType()))
        throw Error.error(5563);
      this.dataType = Type.SQL_BIGINT;
      break;
    case 7:
      if (this.nodes[0].dataType == null)
        this.nodes[0].dataType = Type.SQL_VARCHAR;
      if (!this.nodes[0].dataType.isCharacterType())
        throw Error.error(5563);
    case 8:
      if (this.nodes[0].dataType == null)
        this.nodes[0].dataType = Type.SQL_VARCHAR;
      if ((!this.nodes[0].dataType.isCharacterType()) && (!this.nodes[0].dataType.isBinaryType()))
        throw Error.error(5563);
      this.dataType = Type.SQL_BIGINT;
      break;
    case 9:
      if (this.nodes[0].dataType == null)
        throw Error.error(5567);
      if (!this.nodes[0].dataType.isArrayType())
        throw Error.error(5563);
      this.dataType = Type.SQL_INTEGER;
      break;
    case 10:
      if (this.nodes[0].dataType == null)
        throw Error.error(5567);
      if (!this.nodes[0].dataType.isArrayType())
        throw Error.error(5563);
      this.dataType = Type.SQL_INTEGER;
      break;
    case 11:
      if (this.nodes[0].dataType == null)
        throw Error.error(5567);
      if (!this.nodes[0].dataType.isArrayType())
        throw Error.error(5563);
      if (this.nodes[1].dataType == null)
        this.nodes[1].dataType = Type.SQL_INTEGER;
      if (!this.nodes[1].dataType.isIntegralType())
        throw Error.error(5563);
      this.dataType = this.nodes[0].dataType;
      break;
    case 13:
      if (this.nodes[0].dataType == null)
        this.nodes[0].dataType = this.nodes[1].dataType;
      if (this.nodes[1].dataType == null)
        this.nodes[1].dataType = this.nodes[0].dataType;
      if (this.nodes[0].dataType == null)
        throw Error.error(5567);
      if ((!this.nodes[0].dataType.isNumberType()) || (!this.nodes[1].dataType.isNumberType()))
        throw Error.error(5563);
      this.nodes[0].dataType = ((NumberType)this.nodes[0].dataType).getIntegralType();
      this.nodes[1].dataType = ((NumberType)this.nodes[1].dataType).getIntegralType();
      this.dataType = this.nodes[1].dataType;
      break;
    case 16:
      if (this.nodes[0].dataType == null)
        this.nodes[0].dataType = this.nodes[1].dataType;
      if (this.nodes[1].dataType == null)
        this.nodes[1].dataType = this.nodes[0].dataType;
      if (this.nodes[0].dataType == null)
        throw Error.error(5567);
      if ((!this.nodes[0].dataType.isNumberType()) || (!this.nodes[1].dataType.isNumberType()))
        throw Error.error(5563);
      this.nodes[0].dataType = Type.SQL_DOUBLE;
      this.nodes[1].dataType = Type.SQL_DOUBLE;
      this.dataType = Type.SQL_DOUBLE;
      break;
    case 14:
    case 15:
    case 17:
      if (this.nodes[0].dataType == null)
        this.nodes[0].dataType = Type.SQL_DOUBLE;
      if (!this.nodes[0].dataType.isNumberType())
        throw Error.error(5563);
      this.nodes[0].dataType = Type.SQL_DOUBLE;
      this.dataType = Type.SQL_DOUBLE;
      break;
    case 12:
      if ((this.nodes[0].dataType != null) && (this.nodes[0].dataType.isIntervalType()))
        this.dataType = this.nodes[0].dataType;
      break;
    case 20:
    case 21:
      if (this.nodes[0].dataType == null)
        this.nodes[0].dataType = Type.SQL_DOUBLE;
      if (!this.nodes[0].dataType.isNumberType())
        throw Error.error(5563);
      this.dataType = this.nodes[0].dataType;
      if (((this.dataType.typeCode != 3) && (this.dataType.typeCode != 2)) || (this.dataType.scale <= 0))
        return;
      this.dataType = NumberType.getNumberType(this.dataType.typeCode, this.dataType.precision + 1L, 0);
      break;
    case 22:
      this.nodes[0].dataType = Type.getAggregateType(this.nodes[0].dataType, this.nodes[1].dataType);
      this.nodes[0].dataType = Type.getAggregateType(this.nodes[0].dataType, this.nodes[2].dataType);
      if (this.nodes[0].dataType == null)
        throw Error.error(5567);
      if ((!this.nodes[0].dataType.isNumberType()) && (!this.nodes[0].dataType.isDateTimeType()))
        throw Error.error(5563);
      this.nodes[1].dataType = this.nodes[0].dataType;
      this.nodes[2].dataType = this.nodes[0].dataType;
      if (this.nodes[3].dataType == null)
        this.nodes[3].dataType = Type.SQL_INTEGER;
      if (!this.nodes[3].dataType.isIntegralType())
        throw Error.error(5563);
      this.dataType = this.nodes[3].dataType;
      break;
    case 23:
    case 40:
      if (this.nodes[0].dataType == null)
        this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
      if (this.nodes[1].dataType == null)
        this.nodes[1].dataType = Type.SQL_NUMERIC;
      if (!this.nodes[1].dataType.isNumberType())
        throw Error.error(5563);
      if (this.nodes[2] != null)
      {
        if (this.nodes[2].dataType == null)
          this.nodes[2].dataType = Type.SQL_NUMERIC;
        if (!this.nodes[2].dataType.isNumberType())
          throw Error.error(5563);
        this.nodes[2].dataType = ((NumberType)this.nodes[2].dataType).getIntegralType();
      }
      this.dataType = this.nodes[0].dataType;
      if (this.dataType.isCharacterType())
      {
        this.funcType = 23;
        if (this.dataType.typeCode == 1)
          this.dataType = CharacterType.getCharacterType(12, this.dataType.precision, this.dataType.getCollation());
      }
      else if (this.dataType.isBinaryType())
      {
        this.funcType = 40;
      }
      else
      {
        throw Error.error(5563);
      }
      if ((this.nodes.length <= 3) || (this.nodes[3] == null))
        return;
      break;
    case 26:
    case 27:
      if (this.nodes[0].dataType == null)
        this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
      this.dataType = this.nodes[0].dataType;
      if (this.dataType.isCharacterType())
        return;
      throw Error.error(5563);
    case 31:
    case 41:
      if (this.nodes[0] == null)
        this.nodes[0] = new ExpressionValue(ValuePool.getInt(23), Type.SQL_INTEGER);
      if (this.nodes[2].dataType == null)
        this.nodes[2].dataType = Type.SQL_VARCHAR_DEFAULT;
      this.dataType = this.nodes[2].dataType;
      if (this.dataType.isCharacterType())
      {
        this.funcType = 31;
        if (this.dataType.typeCode == 1)
          this.dataType = CharacterType.getCharacterType(12, this.dataType.precision, this.dataType.getCollation());
        if (this.nodes[1] != null)
          return;
        this.nodes[1] = new ExpressionValue(" ", Type.SQL_CHAR);
        return;
      }
      if (this.dataType.isBinaryType())
      {
        this.funcType = 41;
        if (this.nodes[1] != null)
          return;
        this.nodes[1] = new ExpressionValue(new BinaryData(new byte[] { 0 }, false), Type.SQL_BINARY);
        return;
      }
      throw Error.error(5563);
    case 32:
    case 42:
      if (this.nodes[0].dataType == null)
      {
        if (this.nodes[1].dataType == null)
          this.nodes[0].dataType = (this.nodes[1].dataType = Type.SQL_VARCHAR_DEFAULT);
        if ((this.nodes[1].dataType.typeCode == 40) || (this.nodes[1].dataType.isBinaryType()))
          this.nodes[0].dataType = this.nodes[1].dataType;
        else
          this.nodes[0].dataType = Type.SQL_VARCHAR_DEFAULT;
      }
      if (this.nodes[1].dataType == null)
        if ((this.nodes[0].dataType.typeCode == 40) || (this.nodes[0].dataType.isBinaryType()))
          this.nodes[1].dataType = this.nodes[0].dataType;
        else
          this.nodes[1].dataType = Type.SQL_VARCHAR_DEFAULT;
      if ((this.nodes[0].dataType.isCharacterType()) && (this.nodes[1].dataType.isCharacterType()))
      {
        this.funcType = 32;
        if ((this.nodes[0].dataType.typeCode == 40) || (this.nodes[1].dataType.typeCode == 40))
          this.dataType = CharacterType.getCharacterType(40, this.nodes[0].dataType.precision + this.nodes[1].dataType.precision, this.nodes[0].dataType.getCollation());
        else
          this.dataType = CharacterType.getCharacterType(12, this.nodes[0].dataType.precision + this.nodes[1].dataType.precision, this.nodes[0].dataType.getCollation());
      }
      else if ((this.nodes[0].dataType.isBinaryType()) && (this.nodes[1].dataType.isBinaryType()))
      {
        this.funcType = 42;
        if ((this.nodes[0].dataType.typeCode == 30) || (this.nodes[1].dataType.typeCode == 30))
          this.dataType = BinaryType.getBinaryType(30, this.nodes[0].dataType.precision + this.nodes[1].dataType.precision);
        else
          this.dataType = BinaryType.getBinaryType(61, this.nodes[0].dataType.precision + this.nodes[1].dataType.precision);
      }
      else
      {
        throw Error.error(5563);
      }
      if (this.nodes[2].dataType == null)
        this.nodes[2].dataType = Type.SQL_NUMERIC;
      if (!this.nodes[2].dataType.isNumberType())
        throw Error.error(5563);
      this.nodes[2].dataType = ((NumberType)this.nodes[2].dataType).getIntegralType();
      if (this.nodes[3] == null)
        return;
      if (this.nodes[3].dataType == null)
        this.nodes[3].dataType = Type.SQL_NUMERIC;
      if (!this.nodes[3].dataType.isNumberType())
        throw Error.error(5563);
      this.nodes[3].dataType = ((NumberType)this.nodes[3].dataType).getIntegralType();
      break;
    case 53:
    case 54:
    case 55:
    case 56:
    case 57:
    case 58:
    case 59:
    case 60:
    case 61:
    case 62:
      this.dataType = TypeInvariants.SQL_IDENTIFIER;
      break;
    case 63:
      break;
    case 43:
      this.dataType = CharacterType.SQL_DATE;
      break;
    case 44:
      i = 0;
      if (this.nodes[0] != null)
        i = ((Integer)this.nodes[0].valueData).intValue();
      this.dataType = DateTimeType.getDateTimeType(94, i);
      break;
    case 50:
      i = 6;
      if ((this.nodes.length > 0) && (this.nodes[0] != null))
        i = ((Integer)this.nodes[0].valueData).intValue();
      this.dataType = DateTimeType.getDateTimeType(95, i);
      break;
    case 51:
      i = 0;
      if ((this.nodes.length > 0) && (this.nodes[0] != null))
        i = ((Integer)this.nodes[0].valueData).intValue();
      this.dataType = DateTimeType.getDateTimeType(92, i);
      break;
    case 52:
      i = 6;
      if ((this.nodes.length > 0) && (this.nodes[0] != null))
        i = ((Integer)this.nodes[0].valueData).intValue();
      this.dataType = DateTimeType.getDateTimeType(93, i);
      break;
    case 3:
    case 4:
    case 18:
    case 19:
    case 24:
    case 25:
    case 28:
    case 29:
    case 30:
    case 33:
    case 34:
    case 35:
    case 36:
    case 37:
    case 38:
    case 39:
    case 45:
    case 46:
    case 47:
    case 48:
    case 49:
    }
    throw Error.runtimeError(201, "FunctionSQL");
  }

  public String getSQL()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int j;
    switch (this.funcType)
    {
    case 1:
    case 2:
      localStringBuffer.append("POSITION").append('(').append(this.nodes[0].getSQL()).append(' ').append("IN").append(' ').append(this.nodes[1].getSQL());
      if ((this.nodes[2] != null) && (Boolean.TRUE.equals(this.nodes[2].valueData)))
        localStringBuffer.append(' ').append("USING").append(' ').append("OCTETS");
      localStringBuffer.append(')');
      break;
    case 3:
      break;
    case 4:
      break;
    case 5:
      int i = ((Integer)this.nodes[0].valueData).intValue();
      i = DTIType.getFieldNameTypeForToken(i);
      String str2 = DTIType.getFieldNameTokenForType(i);
      localStringBuffer.append("EXTRACT").append('(').append(str2).append(' ').append("FROM").append(' ').append(this.nodes[1].getSQL()).append(')');
      break;
    case 7:
      localStringBuffer.append("CHAR_LENGTH").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 6:
      localStringBuffer.append("BIT_LENGTH").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 8:
      localStringBuffer.append("OCTET_LENGTH").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 9:
      localStringBuffer.append("CARDINALITY").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 10:
      localStringBuffer.append("MAX_CARDINALITY").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 11:
      localStringBuffer.append("TRIM_ARRAY").append('(').append(this.nodes[0].getSQL()).append(',').append(this.nodes[1].getSQL()).append(')');
      break;
    case 12:
      localStringBuffer.append("ABS").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 13:
      localStringBuffer.append("MOD").append('(').append(this.nodes[0].getSQL()).append(',').append(this.nodes[1].getSQL()).append(')');
      break;
    case 14:
      localStringBuffer.append("LN").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 15:
      localStringBuffer.append("EXP").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 16:
      localStringBuffer.append("POWER").append('(').append(this.nodes[0].getSQL()).append(',').append(this.nodes[1].getSQL()).append(')');
      break;
    case 17:
      localStringBuffer.append("SQRT").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 20:
      localStringBuffer.append("FLOOR").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 21:
      localStringBuffer.append("CEILING").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 22:
      localStringBuffer.append("WIDTH_BUCKET").append('(').append(this.nodes[0].getSQL()).append(',').append(this.nodes[1].getSQL()).append(',').append(this.nodes[2].getSQL()).append(',').append(this.nodes[3].getSQL()).append(')');
      break;
    case 23:
    case 40:
      localStringBuffer.append("SUBSTRING").append('(').append(this.nodes[0].getSQL()).append(' ').append("FROM").append(' ').append(this.nodes[1].getSQL());
      if (this.nodes[2] != null)
        localStringBuffer.append(' ').append("FOR").append(' ').append(this.nodes[2].getSQL());
      if ((this.nodes.length > 3) && (this.nodes[3] != null) && (Boolean.TRUE.equals(this.nodes[3].valueData)))
        localStringBuffer.append(' ').append("USING").append(' ').append("OCTETS");
      localStringBuffer.append(')');
      break;
    case 26:
      localStringBuffer.append("LOWER").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 27:
      localStringBuffer.append("UPPER").append('(').append(this.nodes[0].getSQL()).append(')');
      break;
    case 32:
    case 42:
      localStringBuffer.append("OVERLAY").append('(').append(this.nodes[0].getSQL()).append(' ').append("PLACING").append(' ').append(this.nodes[1].getSQL()).append(' ').append("FROM").append(' ').append(this.nodes[2].getSQL());
      if (this.nodes[3] != null)
        localStringBuffer.append(' ').append("FOR").append(' ').append(this.nodes[3].getSQL());
      if ((this.nodes[4] != null) && (Boolean.TRUE.equals(this.nodes[4].valueData)))
        localStringBuffer.append(' ').append("USING").append(' ').append("OCTETS");
      localStringBuffer.append(')');
      break;
    case 31:
    case 41:
      String str1 = null;
      switch (((Number)this.nodes[0].valueData).intValue())
      {
      case 23:
        str1 = "BOTH";
        break;
      case 151:
        str1 = "LEADING";
        break;
      case 286:
        str1 = "TRAILING";
      }
      localStringBuffer.append("TRIM").append('(').append(str1).append(' ').append(this.nodes[1].getSQL()).append(' ').append("FROM").append(' ').append(this.nodes[2].getSQL()).append(')');
      break;
    case 43:
    case 53:
    case 54:
    case 55:
    case 56:
    case 57:
    case 58:
    case 59:
    case 60:
    case 61:
    case 62:
    case 63:
      return this.name;
    case 44:
    case 51:
      j = 0;
      if ((this.nodes.length > 0) && (this.nodes[0] != null))
        j = ((Number)this.nodes[0].valueData).intValue();
      if (j == 0)
        return this.name;
      localStringBuffer.append(this.name).append("(").append(j);
      localStringBuffer.append(")");
      return localStringBuffer.toString();
    case 50:
    case 52:
      j = 6;
      if ((this.nodes.length > 0) && (this.nodes[0] != null))
        j = ((Number)this.nodes[0].valueData).intValue();
      if (j == 6)
        return this.name;
      localStringBuffer.append(this.name).append("(").append(j);
      localStringBuffer.append(")");
      return localStringBuffer.toString();
    case 18:
    case 19:
    case 24:
    case 25:
    case 28:
    case 29:
    case 30:
    case 33:
    case 34:
    case 35:
    case 36:
    case 37:
    case 38:
    case 39:
    case 45:
    case 46:
    case 47:
    case 48:
    case 49:
    default:
      throw Error.runtimeError(201, "FunctionSQL");
    }
    return localStringBuffer.toString();
  }

  public boolean equals(Expression paramExpression)
  {
    if (((paramExpression instanceof FunctionSQL)) && (this.funcType == ((FunctionSQL)paramExpression).funcType))
      return super.equals(paramExpression);
    return false;
  }

  public int hashCode()
  {
    return this.opType + this.funcType;
  }

  public String describe(Session paramSession, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append('\n');
    for (int i = 0; i < paramInt; i++)
      localStringBuffer.append(' ');
    localStringBuffer.append("FUNCTION ").append("=[\n");
    localStringBuffer.append(this.name).append("(");
    for (i = 0; i < this.nodes.length; i++)
      if (this.nodes[i] != null)
        localStringBuffer.append("[").append(this.nodes[i].describe(paramSession, paramInt)).append("]");
    localStringBuffer.append(") returns ").append(this.dataType.getNameString());
    localStringBuffer.append("]\n");
    return localStringBuffer.toString();
  }

  public boolean isDeterministic()
  {
    return this.isDeterministic;
  }

  public boolean isValueFunction()
  {
    return this.isSQLValueFunction;
  }

  static
  {
    regularFuncMap.put("POSITION", 1);
    regularFuncMap.put("POSITION_REGEX", 4);
    regularFuncMap.put("EXTRACT", 5);
    regularFuncMap.put("BIT_LENGTH", 6);
    regularFuncMap.put("CHAR_LENGTH", 7);
    regularFuncMap.put("CHARACTER_LENGTH", 7);
    regularFuncMap.put("OCTET_LENGTH", 8);
    regularFuncMap.put("CARDINALITY", 9);
    regularFuncMap.put("MAX_CARDINALITY", 10);
    regularFuncMap.put("TRIM_ARRAY", 11);
    regularFuncMap.put("ABS", 12);
    regularFuncMap.put("MOD", 13);
    regularFuncMap.put("LN", 14);
    regularFuncMap.put("EXP", 15);
    regularFuncMap.put("POWER", 16);
    regularFuncMap.put("SQRT", 17);
    regularFuncMap.put("FLOOR", 20);
    regularFuncMap.put("CEILING", 21);
    regularFuncMap.put("CEIL", 21);
    regularFuncMap.put("WIDTH_BUCKET", 22);
    regularFuncMap.put("SUBSTRING", 23);
    regularFuncMap.put("SUBSTRING_REGEX", 25);
    regularFuncMap.put("LOWER", 26);
    regularFuncMap.put("UPPER", 27);
    regularFuncMap.put("TRIM", 31);
    regularFuncMap.put("OVERLAY", 32);
    regularFuncMap.put("TRIM", 41);
    valueFuncMap.put("CURRENT_DATE", 43);
    valueFuncMap.put("CURRENT_TIME", 44);
    valueFuncMap.put("CURRENT_TIMESTAMP", 50);
    valueFuncMap.put("LOCALTIME", 51);
    valueFuncMap.put("LOCALTIMESTAMP", 52);
    valueFuncMap.put("CURRENT_CATALOG", 53);
    valueFuncMap.put("CURRENT_PATH", 55);
    valueFuncMap.put("CURRENT_ROLE", 56);
    valueFuncMap.put("CURRENT_SCHEMA", 57);
    valueFuncMap.put("CURRENT_USER", 59);
    valueFuncMap.put("SESSION_USER", 60);
    valueFuncMap.put("SYSTEM_USER", 61);
    valueFuncMap.put("USER", 62);
    valueFuncMap.put("VALUE", 63);
    nonDeterministicFuncSet.addAll(valueFuncMap.values());
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.FunctionSQL
 * JD-Core Version:    0.6.2
 */