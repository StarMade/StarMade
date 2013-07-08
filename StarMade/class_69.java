import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PushbackInputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import javax.vecmath.Vector3f;

public class class_69
{
  private final class_79 jdField_field_534_of_type_Class_79;
  private class_79 field_535 = null;
  private final String jdField_field_534_of_type_JavaLangString;
  private Object jdField_field_534_of_type_JavaLangObject;
  
  private static Object a(DataInputStream paramDataInputStream, byte paramByte)
  {
    class_69[] arrayOfclass_69;
    int k;
    switch (paramByte)
    {
    case 0: 
      return null;
    case 1: 
      return Byte.valueOf(paramDataInputStream.readByte());
    case 2: 
      return Short.valueOf(paramDataInputStream.readShort());
    case 3: 
      return Integer.valueOf(paramDataInputStream.readInt());
    case 4: 
      return Long.valueOf(paramDataInputStream.readLong());
    case 5: 
      return Float.valueOf(paramDataInputStream.readFloat());
    case 6: 
      return Double.valueOf(paramDataInputStream.readDouble());
    case 7: 
      paramByte = new byte[paramDataInputStream.readInt()];
      paramDataInputStream.readFully(paramByte);
      return paramByte;
    case 8: 
      return paramDataInputStream.readUTF();
    case 9: 
      return new Vector3f(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat());
    case 10: 
      return new class_48(paramDataInputStream.readInt(), paramDataInputStream.readInt(), paramDataInputStream.readInt());
    case 11: 
      return new class_35(paramDataInputStream.read(), paramDataInputStream.read(), paramDataInputStream.read());
    case 12: 
      paramByte = paramDataInputStream.readByte();
      int i;
      arrayOfclass_69 = new class_69[i = paramDataInputStream.readInt()];
      for (k = 0; k < i; k++) {
        arrayOfclass_69[k] = new class_69(class_79.values()[paramByte], null, a(paramDataInputStream, paramByte));
      }
      if (arrayOfclass_69.length == 0) {
        return class_79.values()[paramByte];
      }
      return arrayOfclass_69;
    case 13: 
      paramByte = new class_69[0];
      do
      {
        k = paramDataInputStream.readByte();
        String str = null;
        if (k != 0) {
          str = paramDataInputStream.readUTF();
        }
        arrayOfclass_69 = new class_69[paramByte.length + 1];
        System.arraycopy(paramByte, 0, arrayOfclass_69, 0, paramByte.length);
        arrayOfclass_69[paramByte.length] = new class_69(class_79.values()[k], str, a(paramDataInputStream, k));
        paramByte = arrayOfclass_69;
      } while (k != 0);
      return paramByte;
    case 14: 
      int j = paramDataInputStream.readByte();
      return class_29.field_19[j].create(paramDataInputStream);
    }
    return null;
  }
  
  public class_69(String paramString, class_79 paramclass_79)
  {
    this(class_79.field_560, paramString, paramclass_79);
  }
  
  public class_69(class_79 paramclass_79, String paramString, Object paramObject)
  {
    switch (class_78.field_547[paramclass_79.ordinal()])
    {
    case 1: 
      if (paramObject != null) {
        throw new IllegalArgumentException();
      }
      break;
    case 2: 
      if (!(paramObject instanceof Byte)) {
        throw new IllegalArgumentException();
      }
      break;
    case 3: 
      if (!(paramObject instanceof Short)) {
        throw new IllegalArgumentException();
      }
      break;
    case 4: 
      if (!(paramObject instanceof Integer)) {
        throw new IllegalArgumentException();
      }
      break;
    case 5: 
      if (!(paramObject instanceof Long)) {
        throw new IllegalArgumentException();
      }
      break;
    case 6: 
      if (!(paramObject instanceof Float)) {
        throw new IllegalArgumentException();
      }
      break;
    case 7: 
      if (!(paramObject instanceof Double)) {
        throw new IllegalArgumentException();
      }
      break;
    case 8: 
      if (!(paramObject instanceof byte[])) {
        throw new IllegalArgumentException();
      }
      break;
    case 9: 
      if (!(paramObject instanceof String)) {
        throw new IllegalArgumentException();
      }
      break;
    case 10: 
      if (!(paramObject instanceof Vector3f)) {
        throw new IllegalArgumentException();
      }
      break;
    case 11: 
      if (!(paramObject instanceof class_48)) {
        throw new IllegalArgumentException();
      }
      break;
    case 12: 
      if (!(paramObject instanceof class_35)) {
        throw new IllegalArgumentException();
      }
      break;
    case 13: 
      if ((paramObject instanceof class_79))
      {
        this.field_535 = ((class_79)paramObject);
        paramObject = new class_69[0];
      }
      else
      {
        if (!(paramObject instanceof class_69[])) {
          throw new IllegalArgumentException();
        }
        this.field_535 = ((class_69[])paramObject)[0].jdField_field_534_of_type_Class_79;
      }
      break;
    case 14: 
      if (!(paramObject instanceof class_69[])) {
        throw new IllegalArgumentException();
      }
      break;
    case 15: 
      if (!(paramObject instanceof class_67)) {
        throw new IllegalArgumentException();
      }
      break;
    default: 
      throw new IllegalArgumentException();
    }
    this.jdField_field_534_of_type_Class_79 = paramclass_79;
    this.jdField_field_534_of_type_JavaLangString = paramString;
    this.jdField_field_534_of_type_JavaLangObject = paramObject;
  }
  
  public class_69(class_79 paramclass_79, String paramString, class_69[] paramArrayOfclass_69)
  {
    this(paramclass_79, paramString, paramArrayOfclass_69);
    if ((!jdField_field_534_of_type_Boolean) && (paramclass_79 != class_79.field_548) && (paramArrayOfclass_69[(paramArrayOfclass_69.length - 1)].jdField_field_534_of_type_Class_79 != class_79.field_548)) {
      throw new AssertionError();
    }
  }
  
  public final void a1(class_69 paramclass_69)
  {
    if ((this.jdField_field_534_of_type_Class_79 != class_79.field_560) && (this.jdField_field_534_of_type_Class_79 != class_79.field_561)) {
      throw new RuntimeException();
    }
    int i = ((class_69[])this.jdField_field_534_of_type_JavaLangObject).length;
    if (this.jdField_field_534_of_type_Class_79 == class_79.field_561) {
      i--;
    }
    int j = i;
    class_69 localclass_69 = paramclass_69;
    paramclass_69 = this;
    if ((this.jdField_field_534_of_type_Class_79 != class_79.field_560) && (paramclass_69.jdField_field_534_of_type_Class_79 != class_79.field_561)) {
      throw new RuntimeException();
    }
    class_69[] arrayOfclass_691;
    if (((arrayOfclass_691 = (class_69[])paramclass_69.jdField_field_534_of_type_JavaLangObject).length > 0) && (paramclass_69.jdField_field_534_of_type_Class_79 == class_79.field_560) && (localclass_69.jdField_field_534_of_type_Class_79 != paramclass_69.field_535)) {
      throw new IllegalArgumentException();
    }
    if (j > arrayOfclass_691.length) {
      throw new IndexOutOfBoundsException();
    }
    class_69[] arrayOfclass_692 = new class_69[arrayOfclass_691.length + 1];
    System.arraycopy(arrayOfclass_691, 0, arrayOfclass_692, 0, j);
    arrayOfclass_692[j] = localclass_69;
    System.arraycopy(arrayOfclass_691, j, arrayOfclass_692, j + 1, arrayOfclass_691.length - j);
    paramclass_69.jdField_field_534_of_type_JavaLangObject = arrayOfclass_692;
  }
  
  public final String a2()
  {
    return this.jdField_field_534_of_type_JavaLangString;
  }
  
  public final class_79 a3()
  {
    return this.jdField_field_534_of_type_Class_79;
  }
  
  public final Object a4()
  {
    return this.jdField_field_534_of_type_JavaLangObject;
  }
  
  public final void a5(Object paramObject)
  {
    switch (class_78.field_547[this.jdField_field_534_of_type_Class_79.ordinal()])
    {
    case 1: 
      if (paramObject != null) {
        throw new IllegalArgumentException();
      }
      break;
    case 2: 
      if (!(paramObject instanceof Byte)) {
        throw new IllegalArgumentException();
      }
      break;
    case 3: 
      if (!(paramObject instanceof Short)) {
        throw new IllegalArgumentException();
      }
      break;
    case 4: 
      if (!(paramObject instanceof Integer)) {
        throw new IllegalArgumentException();
      }
      break;
    case 5: 
      if (!(paramObject instanceof Long)) {
        throw new IllegalArgumentException();
      }
      break;
    case 6: 
      if (!(paramObject instanceof Float)) {
        throw new IllegalArgumentException();
      }
      break;
    case 7: 
      if (!(paramObject instanceof Double)) {
        throw new IllegalArgumentException();
      }
      break;
    case 8: 
      if (!(paramObject instanceof byte[])) {
        throw new IllegalArgumentException();
      }
      break;
    case 9: 
      if (!(paramObject instanceof String)) {
        throw new IllegalArgumentException();
      }
      break;
    case 10: 
      if (!(paramObject instanceof Vector3f)) {
        throw new IllegalArgumentException();
      }
      break;
    case 11: 
      if (!(paramObject instanceof class_48)) {
        throw new IllegalArgumentException();
      }
      break;
    case 12: 
      if (!(paramObject instanceof class_35)) {
        throw new IllegalArgumentException();
      }
      break;
    case 13: 
      if ((paramObject instanceof class_79))
      {
        this.field_535 = ((class_79)paramObject);
        paramObject = new class_69[0];
      }
      else
      {
        if (!(paramObject instanceof class_69[])) {
          throw new IllegalArgumentException();
        }
        this.field_535 = ((class_69[])paramObject)[0].jdField_field_534_of_type_Class_79;
      }
      break;
    case 14: 
      if (!(paramObject instanceof class_69[])) {
        throw new IllegalArgumentException();
      }
      break;
    case 15: 
      if (!(paramObject instanceof class_67)) {
        throw new IllegalArgumentException();
      }
      break;
    default: 
      throw new IllegalArgumentException();
    }
    this.jdField_field_534_of_type_JavaLangObject = paramObject;
  }
  
  public static class_69 a6(Collection paramCollection, String paramString)
  {
    class_69[] arrayOfclass_69;
    (arrayOfclass_69 = new class_69[paramCollection.size() + 1])[paramCollection.size()] = new class_69(class_79.field_548, null, null);
    int i = 0;
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      class_80 localclass_80 = (class_80)paramCollection.next();
      arrayOfclass_69[i] = localclass_80.toTagStructure();
      i++;
    }
    return new class_69(class_79.field_561, paramString, arrayOfclass_69);
  }
  
  public static void a7(Collection paramCollection, class_69[] paramArrayOfclass_69)
  {
    if ((!jdField_field_534_of_type_Boolean) && (paramArrayOfclass_69[(paramArrayOfclass_69.length - 1)].jdField_field_534_of_type_Class_79 != class_79.field_548)) {
      throw new AssertionError();
    }
    for (int i = 0; i < paramArrayOfclass_69.length - 1; i++) {
      paramCollection.add(paramArrayOfclass_69[i].jdField_field_534_of_type_JavaLangObject);
    }
  }
  
  public static class_69 a8(Collection paramCollection, class_79 paramclass_79, String paramString)
  {
    class_69[] arrayOfclass_69;
    (arrayOfclass_69 = new class_69[paramCollection.size() + 1])[paramCollection.size()] = new class_69(class_79.field_548, null, null);
    int i = 0;
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Object localObject = paramCollection.next();
      arrayOfclass_69[i] = new class_69(paramclass_79, null, localObject);
      i++;
    }
    return new class_69(class_79.field_561, paramString, arrayOfclass_69);
  }
  
  public String toString()
  {
    return this.jdField_field_534_of_type_JavaLangString + "(" + this.jdField_field_534_of_type_JavaLangObject + ")";
  }
  
  private void a9(DataOutputStream paramDataOutputStream)
  {
    Object localObject;
    int j;
    switch (class_78.field_547[this.jdField_field_534_of_type_Class_79.ordinal()])
    {
    case 1: 
    case 2: 
      paramDataOutputStream.writeByte(((Byte)this.jdField_field_534_of_type_JavaLangObject).byteValue());
      return;
    case 3: 
      paramDataOutputStream.writeShort(((Short)this.jdField_field_534_of_type_JavaLangObject).shortValue());
      return;
    case 4: 
      paramDataOutputStream.writeInt(((Integer)this.jdField_field_534_of_type_JavaLangObject).intValue());
      return;
    case 5: 
      paramDataOutputStream.writeLong(((Long)this.jdField_field_534_of_type_JavaLangObject).longValue());
      return;
    case 6: 
      paramDataOutputStream.writeFloat(((Float)this.jdField_field_534_of_type_JavaLangObject).floatValue());
      return;
    case 7: 
      paramDataOutputStream.writeDouble(((Double)this.jdField_field_534_of_type_JavaLangObject).doubleValue());
      return;
    case 8: 
      localObject = (byte[])this.jdField_field_534_of_type_JavaLangObject;
      paramDataOutputStream.writeInt(localObject.length);
      paramDataOutputStream.write((byte[])localObject);
      return;
    case 9: 
      paramDataOutputStream.writeUTF((String)this.jdField_field_534_of_type_JavaLangObject);
      return;
    case 10: 
      paramDataOutputStream.writeFloat(((Vector3f)this.jdField_field_534_of_type_JavaLangObject).field_615);
      paramDataOutputStream.writeFloat(((Vector3f)this.jdField_field_534_of_type_JavaLangObject).field_616);
      paramDataOutputStream.writeFloat(((Vector3f)this.jdField_field_534_of_type_JavaLangObject).field_617);
      return;
    case 11: 
      paramDataOutputStream.writeInt(((class_48)this.jdField_field_534_of_type_JavaLangObject).field_475);
      paramDataOutputStream.writeInt(((class_48)this.jdField_field_534_of_type_JavaLangObject).field_476);
      paramDataOutputStream.writeInt(((class_48)this.jdField_field_534_of_type_JavaLangObject).field_477);
      return;
    case 12: 
      paramDataOutputStream.write(((class_35)this.jdField_field_534_of_type_JavaLangObject).field_453);
      paramDataOutputStream.write(((class_35)this.jdField_field_534_of_type_JavaLangObject).field_454);
      paramDataOutputStream.write(((class_35)this.jdField_field_534_of_type_JavaLangObject).field_455);
      return;
    case 15: 
      paramDataOutputStream.writeByte(((class_67)this.jdField_field_534_of_type_JavaLangObject).getFactoryId());
      ((class_67)this.jdField_field_534_of_type_JavaLangObject).writeToTag(paramDataOutputStream);
      return;
    case 13: 
      localObject = (class_69[])this.jdField_field_534_of_type_JavaLangObject;
      paramDataOutputStream.writeByte(this.field_535.ordinal());
      paramDataOutputStream.writeInt(localObject.length);
      int i = (localObject = localObject).length;
      for (j = 0; j < i; j++) {
        localObject[j].a9(paramDataOutputStream);
      }
      return;
    case 14: 
      localObject = null;
      class_69[] arrayOfclass_69;
      j = (arrayOfclass_69 = (class_69[])this.jdField_field_534_of_type_JavaLangObject).length;
      for (int k = 0; k < j; k++)
      {
        class_79 localclass_79 = (localObject = arrayOfclass_69[k]).jdField_field_534_of_type_Class_79;
        paramDataOutputStream.writeByte(localclass_79.ordinal());
        if (localclass_79 != class_79.field_548)
        {
          paramDataOutputStream.writeUTF(((class_69)localObject).jdField_field_534_of_type_JavaLangString != null ? ((class_69)localObject).jdField_field_534_of_type_JavaLangString : "null");
          ((class_69)localObject).a9(paramDataOutputStream);
        }
      }
    }
  }
  
  public static class_69 a10(InputStream paramInputStream, boolean paramBoolean)
  {
    paramInputStream = new PushbackInputStream(paramInputStream, 2);
    byte[] arrayOfByte = new byte[2];
    paramInputStream.read(arrayOfByte);
    paramInputStream.unread(arrayOfByte);
    if ((arrayOfByte[0] == 31) && (arrayOfByte[1] == -117)) {
      paramInputStream = new DataInputStream(new GZIPInputStream(paramInputStream, 4096));
    } else {
      (paramInputStream = new DataInputStream(new BufferedInputStream(paramInputStream, 4096))).readShort();
    }
    int i;
    class_69 localclass_69;
    if ((i = paramInputStream.readByte()) == 0)
    {
      localclass_69 = new class_69(class_79.field_548, null, null);
    }
    else
    {
      String str = paramInputStream.readUTF();
      try
      {
        localclass_69 = new class_69(class_79.values()[localclass_69], str, a(paramInputStream, localclass_69));
      }
      catch (IOException paramInputStream)
      {
        System.err.println("EXCEPTION WHILE READING TAG " + str);
        throw paramInputStream;
      }
    }
    if (paramBoolean) {
      paramInputStream.close();
    }
    return localclass_69;
  }
  
  public final void a11(OutputStream paramOutputStream, boolean paramBoolean)
  {
    DataOutputStream localDataOutputStream;
    if ((paramOutputStream instanceof DataOutputStream)) {
      localDataOutputStream = (DataOutputStream)paramOutputStream;
    } else {
      localDataOutputStream = new DataOutputStream(paramOutputStream);
    }
    localDataOutputStream.writeShort(0);
    localDataOutputStream.writeByte(this.jdField_field_534_of_type_Class_79.ordinal());
    if (this.jdField_field_534_of_type_Class_79 != class_79.field_548)
    {
      localDataOutputStream.writeUTF(this.jdField_field_534_of_type_JavaLangString);
      a9(localDataOutputStream);
    }
    if (paramBoolean) {
      paramOutputStream.close();
    }
  }
  
  static
  {
    new class_81();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_69
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */