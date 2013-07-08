import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.game.network.objects.remote.RemoteProximitySector;

public class class_653
{
  private int jdField_field_924_of_type_Int;
  private class_48 jdField_field_924_of_type_Class_48 = new class_48();
  private byte[] jdField_field_924_of_type_ArrayOfByte = new byte[5488];
  private class_748 jdField_field_924_of_type_Class_748;
  
  public class_653(class_748 paramclass_748)
  {
    this.jdField_field_924_of_type_Class_748 = paramclass_748;
  }
  
  public final class_48 a(int paramInt, class_48 paramclass_48)
  {
    paramclass_48.b(this.jdField_field_924_of_type_Class_48.field_475 - 7, this.jdField_field_924_of_type_Class_48.field_476 - 7, this.jdField_field_924_of_type_Class_48.field_477 - 7);
    int i = paramInt / 196;
    int j = (paramInt - i * 196) / 14;
    paramInt -= i * 196 + j * 14;
    paramclass_48.a(paramInt, j, i);
    return paramclass_48;
  }
  
  public final byte a1(int paramInt)
  {
    return this.jdField_field_924_of_type_ArrayOfByte[(paramInt + 2744)];
  }
  
  public final byte b(int paramInt)
  {
    return this.jdField_field_924_of_type_ArrayOfByte[paramInt];
  }
  
  public final int a2()
  {
    return this.jdField_field_924_of_type_Int;
  }
  
  public final void a3(DataInputStream paramDataInputStream)
  {
    long l1 = System.currentTimeMillis();
    this.jdField_field_924_of_type_Int = paramDataInputStream.readInt();
    int i = paramDataInputStream.readInt();
    int j = paramDataInputStream.readInt();
    int k = paramDataInputStream.readInt();
    this.jdField_field_924_of_type_Class_48.b(i, j, k);
    byte[] arrayOfByte;
    Inflater localInflater;
    if (this.jdField_field_924_of_type_Class_748.isOnServer())
    {
      arrayOfByte = class_1041.jdField_field_144_of_type_ArrayOfByte;
      localInflater = class_1041.jdField_field_144_of_type_JavaUtilZipInflater;
    }
    else
    {
      arrayOfByte = class_371.jdField_field_144_of_type_ArrayOfByte;
      localInflater = class_371.jdField_field_144_of_type_JavaUtilZipInflater;
    }
    k = paramDataInputStream.readInt();
    synchronized (arrayOfByte)
    {
      int m = paramDataInputStream.read(arrayOfByte, 0, k);
      if ((!jdField_field_924_of_type_Boolean) && (m != k)) {
        throw new AssertionError();
      }
      localInflater.reset();
      localInflater.setInput(arrayOfByte, 0, k);
      try
      {
        if ((paramDataInputStream = localInflater.inflate(this.jdField_field_924_of_type_ArrayOfByte)) == 0) {
          System.err.println("WARNING: INFLATED BYTES 0: " + localInflater.needsInput() + " " + localInflater.needsDictionary());
        }
        if (paramDataInputStream != this.jdField_field_924_of_type_ArrayOfByte.length) {
          System.err.println("[INFLATER] Exception: " + this.jdField_field_924_of_type_ArrayOfByte.length + " size received: " + k + ": " + paramDataInputStream + "/" + this.jdField_field_924_of_type_ArrayOfByte.length + " for " + this.jdField_field_924_of_type_Class_748 + " pos " + this.jdField_field_924_of_type_Class_48);
        }
      }
      catch (DataFormatException localDataFormatException)
      {
        localDataFormatException;
      }
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 5L) {
      System.err.println("[CLIENT] WARNING: deserialized PROXIMITY " + this.jdField_field_924_of_type_Class_48 + " took " + l2 + "ms");
    }
  }
  
  public final void a4(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(this.jdField_field_924_of_type_Int);
    paramDataOutputStream.writeInt(this.jdField_field_924_of_type_Class_48.field_475);
    paramDataOutputStream.writeInt(this.jdField_field_924_of_type_Class_48.field_476);
    paramDataOutputStream.writeInt(this.jdField_field_924_of_type_Class_48.field_477);
    byte[] arrayOfByte;
    Deflater localDeflater;
    if (this.jdField_field_924_of_type_Class_748.isOnServer())
    {
      arrayOfByte = class_1041.jdField_field_144_of_type_ArrayOfByte;
      localDeflater = class_1041.jdField_field_144_of_type_JavaUtilZipDeflater;
    }
    else
    {
      arrayOfByte = class_371.jdField_field_144_of_type_ArrayOfByte;
      localDeflater = class_371.jdField_field_144_of_type_JavaUtilZipDeflater;
    }
    synchronized (arrayOfByte)
    {
      localDeflater.reset();
      localDeflater.setInput(this.jdField_field_924_of_type_ArrayOfByte);
      localDeflater.finish();
      int i = localDeflater.deflate(arrayOfByte);
      paramDataOutputStream.writeInt(i);
      paramDataOutputStream.write(arrayOfByte, 0, i);
    }
    System.err.println("[SERVER] SERIALIZED PROXIMITY " + this.jdField_field_924_of_type_Class_48);
  }
  
  public final void a5()
  {
    long l1 = System.currentTimeMillis();
    class_1041 localclass_1041 = (class_1041)this.jdField_field_924_of_type_Class_748.getState();
    class_48 localclass_481 = new class_48(this.jdField_field_924_of_type_Class_748.a44());
    this.jdField_field_924_of_type_Class_48.b1(localclass_481);
    this.jdField_field_924_of_type_Int = this.jdField_field_924_of_type_Class_748.c2();
    localclass_1041.a62().updateProximitySectorInformation(localclass_481);
    int i = 0;
    class_48 localclass_482 = new class_48();
    for (int j = localclass_481.field_477 - 7; j < localclass_481.field_477 + 7; j++) {
      for (int k = localclass_481.field_476 - 7; k < localclass_481.field_476 + 7; k++) {
        for (int m = localclass_481.field_475 - 7; m < localclass_481.field_475 + 7; m++)
        {
          localclass_482.b(m, k, j);
          class_791 localclass_791 = localclass_1041.a62().getStellarSystemFromSecPos(localclass_482);
          this.jdField_field_924_of_type_ArrayOfByte[i] = ((byte)localclass_791.a196(localclass_482).ordinal());
          this.jdField_field_924_of_type_ArrayOfByte[(i + 2744)] = ((byte)localclass_791.a197(localclass_482).ordinal());
          i++;
        }
      }
    }
    if ((!jdField_field_924_of_type_Boolean) && (i != 2744)) {
      throw new AssertionError(i + "/2744");
    }
    this.jdField_field_924_of_type_Class_748.a127().proximitySector.setChanged(true);
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[SERVER] WARNING: UPDATING SERVER SECTORPROXMITY TOOK " + l2);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_653
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */