package org.hsqldb.result;

import java.io.IOException;
import org.hsqldb.ColumnBase;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.CharacterType;
import org.hsqldb.types.Type;

public final class ResultMetaData
{
  public static final int RESULT_METADATA = 1;
  public static final int SIMPLE_RESULT_METADATA = 2;
  public static final int UPDATE_RESULT_METADATA = 3;
  public static final int PARAM_METADATA = 4;
  public static final int GENERATED_INDEX_METADATA = 5;
  public static final int GENERATED_NAME_METADATA = 6;
  private int type;
  public String[] columnLabels;
  public Type[] columnTypes;
  private int columnCount;
  private int extendedColumnCount;
  public static final ResultMetaData emptyResultMetaData = newResultMetaData(0);
  public static final ResultMetaData emptyParamMetaData = newParameterMetaData(0);
  public int[] colIndexes;
  public ColumnBase[] columns;
  public byte[] paramModes;
  public byte[] paramNullable;
  
  private ResultMetaData(int paramInt)
  {
    this.type = paramInt;
  }
  
  public static ResultMetaData newUpdateResultMetaData(Type[] paramArrayOfType)
  {
    ResultMetaData localResultMetaData = new ResultMetaData(3);
    localResultMetaData.columnTypes = new Type[paramArrayOfType.length];
    localResultMetaData.columnCount = paramArrayOfType.length;
    localResultMetaData.extendedColumnCount = paramArrayOfType.length;
    ArrayUtil.copyArray(paramArrayOfType, localResultMetaData.columnTypes, paramArrayOfType.length);
    return localResultMetaData;
  }
  
  public static ResultMetaData newSimpleResultMetaData(Type[] paramArrayOfType)
  {
    ResultMetaData localResultMetaData = new ResultMetaData(2);
    localResultMetaData.columnTypes = paramArrayOfType;
    localResultMetaData.columnCount = paramArrayOfType.length;
    localResultMetaData.extendedColumnCount = paramArrayOfType.length;
    return localResultMetaData;
  }
  
  public static ResultMetaData newResultMetaData(int paramInt)
  {
    Type[] arrayOfType = new Type[paramInt];
    return newResultMetaData(arrayOfType, null, paramInt, paramInt);
  }
  
  public static ResultMetaData newSingleColumnMetaData(String paramString)
  {
    ResultMetaData localResultMetaData = newResultMetaData(1);
    localResultMetaData.columns[0] = new ColumnBase(null, null, null, paramString);
    localResultMetaData.columns[0].setType(Type.SQL_VARCHAR_DEFAULT);
    localResultMetaData.prepareData();
    return localResultMetaData;
  }
  
  public static ResultMetaData newResultMetaData(Type[] paramArrayOfType, int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    ResultMetaData localResultMetaData = new ResultMetaData(1);
    localResultMetaData.columnLabels = new String[paramInt1];
    localResultMetaData.columns = new ColumnBase[paramInt1];
    localResultMetaData.columnTypes = paramArrayOfType;
    localResultMetaData.colIndexes = paramArrayOfInt;
    localResultMetaData.columnCount = paramInt1;
    localResultMetaData.extendedColumnCount = paramInt2;
    return localResultMetaData;
  }
  
  public static ResultMetaData newParameterMetaData(int paramInt)
  {
    ResultMetaData localResultMetaData = new ResultMetaData(4);
    localResultMetaData.columnTypes = new Type[paramInt];
    localResultMetaData.columnLabels = new String[paramInt];
    localResultMetaData.paramModes = new byte[paramInt];
    localResultMetaData.paramNullable = new byte[paramInt];
    localResultMetaData.columnCount = paramInt;
    localResultMetaData.extendedColumnCount = paramInt;
    return localResultMetaData;
  }
  
  public static ResultMetaData newGeneratedColumnsMetaData(int[] paramArrayOfInt, String[] paramArrayOfString)
  {
    ResultMetaData localResultMetaData;
    if (paramArrayOfInt != null)
    {
      localResultMetaData = new ResultMetaData(5);
      localResultMetaData.columnCount = paramArrayOfInt.length;
      localResultMetaData.extendedColumnCount = paramArrayOfInt.length;
      localResultMetaData.colIndexes = new int[paramArrayOfInt.length];
      for (int i = 0; i < paramArrayOfInt.length; i++) {
        localResultMetaData.colIndexes[i] = (paramArrayOfInt[i] - 1);
      }
      return localResultMetaData;
    }
    if (paramArrayOfString != null)
    {
      localResultMetaData = new ResultMetaData(6);
      localResultMetaData.columnLabels = new String[paramArrayOfString.length];
      localResultMetaData.columnCount = paramArrayOfString.length;
      localResultMetaData.extendedColumnCount = paramArrayOfString.length;
      localResultMetaData.columnLabels = paramArrayOfString;
      return localResultMetaData;
    }
    return null;
  }
  
  public void prepareData()
  {
    if (this.columns != null) {
      for (int i = 0; i < this.columnCount; i++) {
        if (this.columnTypes[i] == null) {
          this.columnTypes[i] = this.columns[i].getDataType();
        }
      }
    }
  }
  
  public int getColumnCount()
  {
    return this.columnCount;
  }
  
  public int getExtendedColumnCount()
  {
    return this.extendedColumnCount;
  }
  
  public void resetExtendedColumnCount()
  {
    this.extendedColumnCount = this.columnCount;
  }
  
  public Type[] getParameterTypes()
  {
    return this.columnTypes;
  }
  
  public String[] getGeneratedColumnNames()
  {
    return this.columnLabels;
  }
  
  public int[] getGeneratedColumnIndexes()
  {
    return this.colIndexes;
  }
  
  public boolean isTableColumn(int paramInt)
  {
    String str1 = this.columns[paramInt].getNameString();
    String str2 = this.columns[paramInt].getTableNameString();
    return (str2 != null) && (str2.length() > 0) && (str1 != null) && (str1.length() > 0);
  }
  
  private static void decodeTableColumnAttrs(int paramInt, ColumnBase paramColumnBase)
  {
    paramColumnBase.setNullability((byte)(paramInt & 0x3));
    paramColumnBase.setIdentity((paramInt & 0x4) != 0);
    paramColumnBase.setWriteable((paramInt & 0x8) != 0);
    paramColumnBase.setSearchable((paramInt & 0x10) != 0);
  }
  
  private static int encodeTableColumnAttrs(ColumnBase paramColumnBase)
  {
    int i = paramColumnBase.getNullability();
    if (paramColumnBase.isIdentity()) {
      i |= 4;
    }
    if (paramColumnBase.isWriteable()) {
      i |= 8;
    }
    if (paramColumnBase.isSearchable()) {
      i |= 16;
    }
    return i;
  }
  
  private void decodeParamColumnAttrs(int paramInt1, int paramInt2)
  {
    this.paramNullable[paramInt2] = ((byte)(paramInt1 & 0x3));
    this.paramModes[paramInt2] = ((byte)(paramInt1 >> 4 & 0xF));
  }
  
  private int encodeParamColumnAttrs(int paramInt)
  {
    int i = this.paramModes[paramInt] << 4;
    i |= this.paramNullable[paramInt];
    return i;
  }
  
  ResultMetaData(RowInputBinary paramRowInputBinary)
    throws IOException
  {
    this.type = paramRowInputBinary.readInt();
    this.columnCount = paramRowInputBinary.readInt();
    int i;
    switch (this.type)
    {
    case 2: 
    case 3: 
      this.columnTypes = new Type[this.columnCount];
      for (i = 0; i < this.columnCount; i++) {
        this.columnTypes[i] = readDataTypeSimple(paramRowInputBinary);
      }
      return;
    case 5: 
      this.colIndexes = new int[this.columnCount];
      for (i = 0; i < this.columnCount; i++) {
        this.colIndexes[i] = paramRowInputBinary.readInt();
      }
      return;
    case 6: 
      this.columnLabels = new String[this.columnCount];
      for (i = 0; i < this.columnCount; i++) {
        this.columnLabels[i] = paramRowInputBinary.readString();
      }
      return;
    case 4: 
      this.columnTypes = new Type[this.columnCount];
      this.columnLabels = new String[this.columnCount];
      this.paramModes = new byte[this.columnCount];
      this.paramNullable = new byte[this.columnCount];
      for (i = 0; i < this.columnCount; i++)
      {
        this.columnTypes[i] = readDataType(paramRowInputBinary);
        this.columnLabels[i] = paramRowInputBinary.readString();
        decodeParamColumnAttrs(paramRowInputBinary.readByte(), i);
      }
      return;
    case 1: 
      this.extendedColumnCount = paramRowInputBinary.readInt();
      this.columnTypes = new Type[this.extendedColumnCount];
      this.columnLabels = new String[this.columnCount];
      this.columns = new ColumnBase[this.columnCount];
      if (this.columnCount != this.extendedColumnCount) {
        this.colIndexes = new int[this.columnCount];
      }
      Object localObject;
      for (i = 0; i < this.extendedColumnCount; i++)
      {
        localObject = readDataType(paramRowInputBinary);
        this.columnTypes[i] = localObject;
      }
      for (i = 0; i < this.columnCount; i++)
      {
        this.columnLabels[i] = paramRowInputBinary.readString();
        localObject = paramRowInputBinary.readString();
        String str1 = paramRowInputBinary.readString();
        String str2 = paramRowInputBinary.readString();
        String str3 = paramRowInputBinary.readString();
        ColumnBase localColumnBase = new ColumnBase((String)localObject, str1, str2, str3);
        localColumnBase.setType(this.columnTypes[i]);
        decodeTableColumnAttrs(paramRowInputBinary.readByte(), localColumnBase);
        this.columns[i] = localColumnBase;
      }
      if (this.columnCount != this.extendedColumnCount) {
        for (i = 0; i < this.columnCount; i++) {
          this.colIndexes[i] = paramRowInputBinary.readInt();
        }
      }
      return;
    }
    throw Error.runtimeError(201, "ResultMetaData");
  }
  
  Type readDataTypeSimple(RowInputBinary paramRowInputBinary)
    throws IOException
  {
    int i = paramRowInputBinary.readType();
    int j = i == 50 ? 1 : 0;
    if (j != 0)
    {
      i = paramRowInputBinary.readType();
      return Type.getDefaultArrayType(i);
    }
    return Type.getDefaultType(i);
  }
  
  Type readDataType(RowInputBinary paramRowInputBinary)
    throws IOException
  {
    int i = paramRowInputBinary.readType();
    int j = i == 50 ? 1 : 0;
    if (j != 0) {
      i = paramRowInputBinary.readType();
    }
    long l = paramRowInputBinary.readLong();
    int k = paramRowInputBinary.readInt();
    Object localObject = Type.getType(i, Type.SQL_VARCHAR.getCharacterSet(), Type.SQL_VARCHAR.getCollation(), l, k);
    if (j != 0) {
      localObject = new ArrayType((Type)localObject, 1024);
    }
    return localObject;
  }
  
  void writeDataType(RowOutputInterface paramRowOutputInterface, Type paramType)
  {
    paramRowOutputInterface.writeType(paramType.typeCode);
    if (paramType.isArrayType()) {
      paramRowOutputInterface.writeType(paramType.collectionBaseType().typeCode);
    }
    paramRowOutputInterface.writeLong(paramType.precision);
    paramRowOutputInterface.writeInt(paramType.scale);
  }
  
  void writeDataTypeCodes(RowOutputInterface paramRowOutputInterface, Type paramType)
  {
    paramRowOutputInterface.writeType(paramType.typeCode);
    if (paramType.isArrayType()) {
      paramRowOutputInterface.writeType(paramType.collectionBaseType().typeCode);
    }
  }
  
  void write(RowOutputInterface paramRowOutputInterface)
    throws IOException
  {
    paramRowOutputInterface.writeInt(this.type);
    paramRowOutputInterface.writeInt(this.columnCount);
    int i;
    switch (this.type)
    {
    case 2: 
    case 3: 
      for (i = 0; i < this.columnCount; i++) {
        writeDataTypeCodes(paramRowOutputInterface, this.columnTypes[i]);
      }
      return;
    case 5: 
      for (i = 0; i < this.columnCount; i++) {
        paramRowOutputInterface.writeInt(this.colIndexes[i]);
      }
      return;
    case 6: 
      for (i = 0; i < this.columnCount; i++) {
        paramRowOutputInterface.writeString(this.columnLabels[i]);
      }
      return;
    case 4: 
      for (i = 0; i < this.columnCount; i++)
      {
        writeDataType(paramRowOutputInterface, this.columnTypes[i]);
        paramRowOutputInterface.writeString(this.columnLabels[i]);
        paramRowOutputInterface.writeByte(encodeParamColumnAttrs(i));
      }
      return;
    case 1: 
      paramRowOutputInterface.writeInt(this.extendedColumnCount);
      ColumnBase localColumnBase;
      for (i = 0; i < this.extendedColumnCount; i++)
      {
        if (this.columnTypes[i] == null)
        {
          localColumnBase = this.columns[i];
          this.columnTypes[i] = localColumnBase.getDataType();
        }
        writeDataType(paramRowOutputInterface, this.columnTypes[i]);
      }
      for (i = 0; i < this.columnCount; i++)
      {
        localColumnBase = this.columns[i];
        paramRowOutputInterface.writeString(this.columnLabels[i]);
        paramRowOutputInterface.writeString(localColumnBase.getCatalogNameString());
        paramRowOutputInterface.writeString(localColumnBase.getSchemaNameString());
        paramRowOutputInterface.writeString(localColumnBase.getTableNameString());
        paramRowOutputInterface.writeString(localColumnBase.getNameString());
        paramRowOutputInterface.writeByte(encodeTableColumnAttrs(localColumnBase));
      }
      if (this.columnCount != this.extendedColumnCount) {
        for (i = 0; i < this.colIndexes.length; i++) {
          paramRowOutputInterface.writeInt(this.colIndexes[i]);
        }
      }
      return;
    }
    throw Error.runtimeError(201, "ResultMetaData");
  }
  
  public ResultMetaData getNewMetaData(int[] paramArrayOfInt)
  {
    ResultMetaData localResultMetaData = newResultMetaData(paramArrayOfInt.length);
    ArrayUtil.projectRow(this.columnLabels, paramArrayOfInt, localResultMetaData.columnLabels);
    ArrayUtil.projectRow(this.columnTypes, paramArrayOfInt, localResultMetaData.columnTypes);
    ArrayUtil.projectRow(this.columns, paramArrayOfInt, localResultMetaData.columns);
    return localResultMetaData;
  }
  
  public boolean areTypesCompatible(ResultMetaData paramResultMetaData)
  {
    if (this.columnCount != paramResultMetaData.columnCount) {
      return false;
    }
    for (int i = 0; i < this.columnCount; i++) {
      if (!this.columnTypes[i].canConvertFrom(paramResultMetaData.columnTypes[i])) {
        return false;
      }
    }
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.result.ResultMetaData
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */