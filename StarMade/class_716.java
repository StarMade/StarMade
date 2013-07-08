import java.io.RandomAccessFile;

public final class class_716
{
  long jdField_field_982_of_type_Long;
  RandomAccessFile jdField_field_982_of_type_JavaIoRandomAccessFile;
  final String jdField_field_982_of_type_JavaLangString;
  
  public class_716(RandomAccessFile paramRandomAccessFile, String paramString)
  {
    this.jdField_field_982_of_type_JavaIoRandomAccessFile = paramRandomAccessFile;
    this.jdField_field_982_of_type_JavaLangString = paramString;
    this.jdField_field_982_of_type_Long = System.currentTimeMillis();
  }
  
  public final boolean equals(Object paramObject)
  {
    return ((class_716)paramObject).jdField_field_982_of_type_JavaLangString.equals(this.jdField_field_982_of_type_JavaLangString);
  }
  
  public final RandomAccessFile a()
  {
    this.jdField_field_982_of_type_Long = System.currentTimeMillis();
    return this.jdField_field_982_of_type_JavaIoRandomAccessFile;
  }
  
  public final int hashCode()
  {
    return this.jdField_field_982_of_type_JavaLangString.hashCode();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_716
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */