import java.io.IOException;
import java.nio.ByteBuffer;

public final class class_1396
{
  public int field_1593;
  public int field_1594;
  public int field_1595;
  public int field_1596;
  public int field_1597;
  public int field_1598;
  public String field_1593;
  public int field_1599;
  public int field_1600;
  public int field_1601;
  public int field_1602;
  public int field_1603;
  
  public class_1396(ByteBuffer paramByteBuffer)
  {
    byte[] arrayOfByte = new byte[4];
    paramByteBuffer.get(arrayOfByte);
    String str;
    if (!(str = new String(arrayOfByte, "US-ASCII")).equals("DDS ")) {
      throw new IOException("Bad magic: " + str);
    }
    int i;
    if ((i = paramByteBuffer.getInt()) != 124) {
      throw new IOException("Wrong header size: " + i);
    }
    this.jdField_field_1593_of_type_Int = paramByteBuffer.getInt();
    this.field_1594 = paramByteBuffer.getInt();
    this.field_1595 = paramByteBuffer.getInt();
    paramByteBuffer.getInt();
    this.field_1596 = paramByteBuffer.getInt();
    this.field_1597 = paramByteBuffer.getInt();
    paramByteBuffer.position(paramByteBuffer.position() + 44);
    if ((i = paramByteBuffer.getInt()) != 32) {
      throw new IOException("Wrong pixel format size: " + i);
    }
    this.field_1598 = paramByteBuffer.getInt();
    paramByteBuffer.get(arrayOfByte);
    this.jdField_field_1593_of_type_JavaLangString = ((this.field_1598 & 0x4) == 0 ? null : new String(arrayOfByte, "US-ASCII"));
    paramByteBuffer.getInt();
    this.field_1599 = paramByteBuffer.getInt();
    this.field_1600 = paramByteBuffer.getInt();
    this.field_1601 = paramByteBuffer.getInt();
    this.field_1602 = paramByteBuffer.getInt();
    paramByteBuffer.getInt();
    this.field_1603 = paramByteBuffer.getInt();
    paramByteBuffer.position(paramByteBuffer.position() + 8);
    paramByteBuffer.position(paramByteBuffer.position() + 4);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1396
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */