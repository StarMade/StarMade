import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PushbackInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.DeserializationException;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;

public class class_672
  extends Segment
{
  private long jdField_field_34_of_type_Long;
  private boolean jdField_field_34_of_type_Boolean;
  public Object field_35;
  private long jdField_field_35_of_type_Long = -1L;
  private boolean jdField_field_35_of_type_Boolean;
  private boolean field_187;
  
  public class_672(SegmentController paramSegmentController)
  {
    super(paramSegmentController);
    this.jdField_field_35_of_type_JavaLangObject = new Object();
  }
  
  public void a11(boolean paramBoolean)
  {
    this.jdField_field_34_of_type_Boolean = paramBoolean;
    System.currentTimeMillis();
  }
  
  public final void a43(DataInputStream arg1, int paramInt, boolean paramBoolean)
  {
    ??? = new PushbackInputStream(???, 2);
    byte[] arrayOfByte = new byte[2];
    ???.read(arrayOfByte);
    ???.unread(arrayOfByte);
    ??? = new DataInputStream(???);
    if ((arrayOfByte[0] == 31) && (arrayOfByte[1] == -117))
    {
      boolean bool = paramBoolean;
      paramBoolean = paramInt;
      paramInt = ???;
      ??? = this;
      if (paramBoolean) {
        paramInt = new DataInputStream(new GZIPInputStream(paramInt));
      } else {
        paramInt = new DataInputStream(new GZIPInputStream(paramInt, paramBoolean));
      }
      long l2 = paramInt.readLong();
      localDataInputStream = paramInt.readInt();
      m = paramInt.readInt();
      int n = paramInt.readInt();
      if (bool) {
        ???.a25(localDataInputStream, m, n);
      }
      if ((!field_188) && (!bool) && ((???.jdField_field_34_of_type_Class_48.field_475 != localDataInputStream) || (???.jdField_field_34_of_type_Class_48.field_476 != m) || (???.jdField_field_34_of_type_Class_48.field_477 != n))) {
        throw new AssertionError(" deserialized " + localDataInputStream + ", " + m + ", " + n + "; toSerialize " + ???.jdField_field_34_of_type_Class_48);
      }
      int i;
      if ((i = paramInt.readByte()) == 1)
      {
        if (???.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData == null) {
          ???.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a25().assignData(???);
        }
        ???.field_187 = true;
        synchronized (???.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData)
        {
          ???.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.deSerialize(paramInt);
        }
      }
      if ((!field_188) && (??? != 2)) {
        throw new AssertionError();
      }
      ???.jdField_field_34_of_type_Long = l2;
      ???.field_187 = false;
      try
      {
        int j;
        if ((j = paramInt.read()) != -1) {
          throw new DeserializationException("EoF not reached: " + j + " - size given: " + paramBoolean);
        }
        return;
      }
      catch (IOException localIOException2)
      {
        IOException localIOException1;
        (localIOException1 = localIOException2).printStackTrace();
        throw new DeserializationException("[WARNING][DESERIALIZE] " + ???.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + ": " + ???.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController + ": " + ???.jdField_field_34_of_type_Class_48 + ": " + localIOException1.getMessage());
      }
    }
    long l1 = ???.readLong();
    int k = ???.readInt();
    DataInputStream localDataInputStream = ???.readInt();
    int m = ???.readInt();
    if (paramBoolean) {
      a25(k, localDataInputStream, m);
    }
    if ((!field_188) && (!paramBoolean) && ((this.jdField_field_34_of_type_Class_48.field_475 != k) || (this.jdField_field_34_of_type_Class_48.field_476 != localDataInputStream) || (this.jdField_field_34_of_type_Class_48.field_477 != m))) {
      throw new AssertionError(" deserialized " + k + ", " + localDataInputStream + ", " + m + "; toSerialize " + this.jdField_field_34_of_type_Class_48 + " on " + this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController);
    }
    if ((paramBoolean = ???.readByte()) == 1)
    {
      if ((this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController == null) || (this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()))
      {
        paramBoolean = class_1041.jdField_field_144_of_type_ArrayOfByte;
        paramInt = class_1041.jdField_field_144_of_type_JavaUtilZipInflater;
      }
      else
      {
        paramBoolean = class_371.jdField_field_144_of_type_ArrayOfByte;
        paramInt = class_371.jdField_field_144_of_type_JavaUtilZipInflater;
      }
      k = 0;
      if (this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData == null) {
        this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getSegmentProvider().a25().assignData(this);
      } else {
        k = 1;
      }
      this.field_187 = true;
      localDataInputStream = ???.readInt();
      if ((!field_188) && (localDataInputStream > paramBoolean.length)) {
        throw new AssertionError(localDataInputStream + "/" + paramBoolean.length);
      }
      synchronized (paramBoolean)
      {
        ??? = ???.read(paramBoolean, 0, localDataInputStream);
        if ((!field_188) && (??? != localDataInputStream)) {
          throw new AssertionError();
        }
        paramInt.reset();
        paramInt.setInput(paramBoolean, 0, localDataInputStream);
        synchronized (this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData)
        {
          try
          {
            if (k != 0) {
              this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.reset();
            }
            paramBoolean = paramInt.inflate(this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer());
            this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.setNeedsRevalidate(true);
            System.currentTimeMillis();
            if (!paramBoolean) {
              System.err.println("WARNING: INFLATED BYTES 0: " + paramInt.needsInput() + " " + paramInt.needsDictionary());
            }
            if (paramBoolean != this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer().length) {
              System.err.println("[INFLATER] Exception: " + this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.getState() + " size received: " + localDataInputStream + ": " + paramBoolean + "/" + this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer().length + " for " + this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController + " pos " + this.jdField_field_34_of_type_Class_48);
            }
          }
          catch (DataFormatException localDataFormatException)
          {
            localDataFormatException;
          }
        }
      }
    }
    if ((!field_188) && (paramBoolean != true)) {
      throw new AssertionError(paramBoolean + "/2: " + k + ", " + localDataInputStream + ", " + ??? + "; byte size: " + paramInt);
    }
    this.jdField_field_34_of_type_Long = l1;
    this.field_187 = false;
  }
  
  public final long a44()
  {
    return this.jdField_field_34_of_type_Long;
  }
  
  public final long b8()
  {
    return this.jdField_field_35_of_type_Long;
  }
  
  public final void a18(class_796 paramclass_796)
  {
    super.a18(paramclass_796);
    if (this.jdField_field_34_of_type_Boolean) {
      this.jdField_field_34_of_type_Long = System.currentTimeMillis();
    }
  }
  
  public final boolean e1()
  {
    return this.field_187;
  }
  
  public final boolean f()
  {
    return this.jdField_field_35_of_type_Boolean;
  }
  
  public final int a45(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream = new DataOutputStream(paramDataOutputStream);
    if ((!field_188) && (paramDataOutputStream.size() != 0)) {
      throw new AssertionError();
    }
    int i = 0;
    paramDataOutputStream.writeLong(this.jdField_field_34_of_type_Long);
    paramDataOutputStream.writeInt(this.jdField_field_34_of_type_Class_48.field_475);
    paramDataOutputStream.writeInt(this.jdField_field_34_of_type_Class_48.field_476);
    paramDataOutputStream.writeInt(this.jdField_field_34_of_type_Class_48.field_477);
    i += 20;
    if ((!field_188) && (paramDataOutputStream.size() != 20)) {
      throw new AssertionError(paramDataOutputStream.size() + "/20");
    }
    int k = 0;
    int j;
    if ((this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData != null) && (!g()))
    {
      paramDataOutputStream.writeByte(1);
      byte[] arrayOfByte;
      Deflater localDeflater;
      if ((this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController == null) || (this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()))
      {
        arrayOfByte = class_1041.jdField_field_144_of_type_ArrayOfByte;
        localDeflater = class_1041.jdField_field_144_of_type_JavaUtilZipDeflater;
      }
      else
      {
        arrayOfByte = class_371.jdField_field_144_of_type_ArrayOfByte;
        localDeflater = class_371.jdField_field_144_of_type_JavaUtilZipDeflater;
      }
      if ((!field_188) && (paramDataOutputStream.size() != 21)) {
        throw new AssertionError(paramDataOutputStream.size() + "/21");
      }
      int m;
      synchronized (arrayOfByte)
      {
        synchronized (this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData)
        {
          localDeflater.reset();
          localDeflater.setInput(this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.getAsBuffer());
          localDeflater.finish();
          m = localDeflater.deflate(arrayOfByte);
        }
        if ((!field_188) && (m <= 0)) {
          throw new AssertionError("[DEFLATER] DELFLATED SIZE: " + m + " for: " + this.jdField_field_34_of_type_Class_48 + " " + this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController + ": SData Size: " + this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSize());
        }
        paramDataOutputStream.writeInt(m);
        if ((!field_188) && (paramDataOutputStream.size() != 25)) {
          throw new AssertionError(paramDataOutputStream.size() + "/25");
        }
        paramDataOutputStream.write(arrayOfByte, 0, m);
      }
      j = m + 25;
      if ((!field_188) && (paramDataOutputStream.size() != j)) {
        throw new AssertionError(paramDataOutputStream.size() + "/" + j);
      }
    }
    else
    {
      if ((!field_188) && (!g())) {
        throw new AssertionError();
      }
      j++;
      paramDataOutputStream.writeByte(2);
    }
    if ((!field_188) && (paramDataOutputStream.size() != j)) {
      throw new AssertionError(paramDataOutputStream.size() + "/" + j);
    }
    return paramDataOutputStream.size();
  }
  
  public final void a46(long paramLong)
  {
    this.jdField_field_34_of_type_Long = paramLong;
  }
  
  public final void b9(long paramLong)
  {
    this.jdField_field_35_of_type_Long = paramLong;
  }
  
  public final void f1(boolean paramBoolean)
  {
    this.jdField_field_35_of_type_Boolean = paramBoolean;
  }
  
  public String toString()
  {
    return this.jdField_field_34_of_type_OrgSchemaGameCommonControllerSegmentController + "(" + this.jdField_field_34_of_type_Class_48 + ")[ID" + getId() + "; " + (this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData != null ? Integer.valueOf(this.jdField_field_34_of_type_OrgSchemaGameCommonDataWorldSegmentData.getSize()) : "e") + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_672
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */