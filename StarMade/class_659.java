import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.game.network.objects.remote.RemoteProximitySystem;

public class class_659
{
  private class_48 jdField_field_926_of_type_Class_48 = new class_48();
  private long[] jdField_field_926_of_type_ArrayOfLong = new long[27];
  private byte[] jdField_field_926_of_type_ArrayOfByte = new byte[27];
  private class_748 jdField_field_926_of_type_Class_748;
  
  public class_659(class_748 paramclass_748)
  {
    this.jdField_field_926_of_type_Class_748 = paramclass_748;
  }
  
  public final class_48 a(int paramInt, class_48 paramclass_48)
  {
    paramclass_48.b(this.jdField_field_926_of_type_Class_48.field_475 - 1, this.jdField_field_926_of_type_Class_48.field_476 - 1, this.jdField_field_926_of_type_Class_48.field_477 - 1);
    int i = paramInt / 9;
    int j = (paramInt - i * 9) / 3;
    paramInt -= i * 9 + j * 3;
    paramclass_48.a(paramInt, j, i);
    return paramclass_48;
  }
  
  public final class_48 a1()
  {
    return this.jdField_field_926_of_type_Class_48;
  }
  
  public final void a2(DataInputStream paramDataInputStream)
  {
    int i = paramDataInputStream.readInt();
    int j = paramDataInputStream.readInt();
    int k = paramDataInputStream.readInt();
    this.jdField_field_926_of_type_Class_48.b(i, j, k);
    for (i = 0; i < 27; i++)
    {
      this.jdField_field_926_of_type_ArrayOfByte[i] = paramDataInputStream.readByte();
      this.jdField_field_926_of_type_ArrayOfLong[i] = paramDataInputStream.readLong();
    }
  }
  
  public final void a3(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeInt(this.jdField_field_926_of_type_Class_48.field_475);
    paramDataOutputStream.writeInt(this.jdField_field_926_of_type_Class_48.field_476);
    paramDataOutputStream.writeInt(this.jdField_field_926_of_type_Class_48.field_477);
    for (int i = 0; i < 27; i++)
    {
      paramDataOutputStream.writeByte(this.jdField_field_926_of_type_ArrayOfByte[i]);
      paramDataOutputStream.writeLong(this.jdField_field_926_of_type_ArrayOfLong[i]);
    }
  }
  
  public final void a4()
  {
    class_1041 localclass_1041 = (class_1041)this.jdField_field_926_of_type_Class_748.getState();
    class_48 localclass_48 = new class_48(this.jdField_field_926_of_type_Class_748.a44());
    this.jdField_field_926_of_type_Class_48.b1(localclass_48);
    localclass_1041.a62().updateProximitySectorInformation(localclass_48);
    int i = 0;
    localclass_48 = new class_48(localclass_48);
    class_791 localclass_7911 = localclass_1041.a62().getStellarSystemFromSecPos(localclass_48);
    this.jdField_field_926_of_type_Class_48.b1(localclass_7911.jdField_field_136_of_type_Class_48);
    for (int j = -1; j <= 1; j++) {
      for (int k = -1; k <= 1; k++) {
        for (int m = -1; m <= 1; m++)
        {
          localclass_48.b(this.jdField_field_926_of_type_Class_48.field_475 + m, this.jdField_field_926_of_type_Class_48.field_476 + k, this.jdField_field_926_of_type_Class_48.field_477 + j);
          class_791 localclass_7912 = localclass_1041.a62().getStellarSystemFromStellarPos(localclass_48);
          this.jdField_field_926_of_type_ArrayOfByte[i] = ((byte)localclass_7912.jdField_field_136_of_type_Class_808.ordinal());
          this.jdField_field_926_of_type_ArrayOfLong[i] = localclass_7912.jdField_field_136_of_type_Long;
          i++;
        }
      }
    }
    if ((!jdField_field_926_of_type_Boolean) && (i != 27)) {
      throw new AssertionError(i + "/27");
    }
    synchronized (this.jdField_field_926_of_type_Class_748.a127())
    {
      this.jdField_field_926_of_type_Class_748.a127().proximitySystem.setChanged(true);
      return;
    }
  }
  
  public final int a5(class_48 paramclass_48)
  {
    class_48 localclass_48 = new class_48(this.jdField_field_926_of_type_Class_48.field_475 - paramclass_48.field_475 + 1, this.jdField_field_926_of_type_Class_48.field_476 - paramclass_48.field_476 + 1, this.jdField_field_926_of_type_Class_48.field_477 - paramclass_48.field_477 + 1);
    if ((!jdField_field_926_of_type_Boolean) && ((localclass_48.field_475 < 0) || (localclass_48.field_476 < 0) || (localclass_48.field_477 < 0))) {
      throw new AssertionError(localclass_48 + ": " + paramclass_48 + " / " + this.jdField_field_926_of_type_Class_48);
    }
    int i = localclass_48.field_477 * 9 + localclass_48.field_476 * 3 + localclass_48.field_475;
    if ((!jdField_field_926_of_type_Boolean) && (!a(i, new class_48()).equals(paramclass_48))) {
      throw new AssertionError(a(i, new class_48()) + " / " + paramclass_48);
    }
    if ((i < 0) || (i >= 27)) {
      return -1;
    }
    return i;
  }
  
  public final byte a6(int paramInt)
  {
    return this.jdField_field_926_of_type_ArrayOfByte[paramInt];
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_659
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */