import it.unimi.dsi.fastutil.shorts.Short2IntArrayMap;
import it.unimi.dsi.fastutil.shorts.Short2IntMap.Entry;
import it.unimi.dsi.fastutil.shorts.Short2IntMap.FastEntrySet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

public class class_703
{
  private int[] jdField_field_971_of_type_ArrayOfInt = new int[ElementKeyMap.highestType + 1];
  private int jdField_field_971_of_type_Int;
  
  public final void a(short paramShort)
  {
    this.jdField_field_971_of_type_ArrayOfInt[paramShort] -= 1;
    this.jdField_field_971_of_type_Int = ((int)(this.jdField_field_971_of_type_Int - ElementKeyMap.getInfo(paramShort).getPrice()));
  }
  
  public final int a1(short paramShort)
  {
    return this.jdField_field_971_of_type_ArrayOfInt[paramShort];
  }
  
  public final void b(short paramShort)
  {
    if ((!jdField_field_971_of_type_Boolean) && (paramShort >= this.jdField_field_971_of_type_ArrayOfInt.length)) {
      throw new AssertionError("ERROR: " + paramShort + "/" + this.jdField_field_971_of_type_ArrayOfInt.length + "  (" + ElementKeyMap.highestType + ")");
    }
    this.jdField_field_971_of_type_ArrayOfInt[paramShort] += 1;
    this.jdField_field_971_of_type_Int = ((int)(this.jdField_field_971_of_type_Int + ElementKeyMap.getInfo(paramShort).getPrice()));
  }
  
  public final void a2()
  {
    Arrays.fill(this.jdField_field_971_of_type_ArrayOfInt, 0);
    this.jdField_field_971_of_type_Int = 0;
  }
  
  public final int a3()
  {
    long l = 0L;
    Iterator localIterator = ElementKeyMap.keySet.iterator();
    while (localIterator.hasNext())
    {
      short s = ((Short)localIterator.next()).shortValue();
      l += ElementKeyMap.getInfo(s).getPrice() * this.jdField_field_971_of_type_ArrayOfInt[s];
    }
    return (int)Math.min(2147483647L, l);
  }
  
  public final void a4(DataOutputStream paramDataOutputStream)
  {
    int i = 0;
    for (int j = 0; j < this.jdField_field_971_of_type_ArrayOfInt.length; j++) {
      if (this.jdField_field_971_of_type_ArrayOfInt[j] > 0) {
        i++;
      }
    }
    paramDataOutputStream.writeInt(i);
    for (j = 0; j < this.jdField_field_971_of_type_ArrayOfInt.length; j++) {
      if (this.jdField_field_971_of_type_ArrayOfInt[j] > 0)
      {
        paramDataOutputStream.writeShort(j);
        paramDataOutputStream.writeInt(this.jdField_field_971_of_type_ArrayOfInt[j]);
      }
    }
  }
  
  public final void a5(DataInputStream paramDataInputStream)
  {
    int i = paramDataInputStream.readInt();
    for (int j = 0; j < i; j++)
    {
      short s = paramDataInputStream.readShort();
      int k = paramDataInputStream.readInt();
      if (ElementKeyMap.exists(s)) {
        this.jdField_field_971_of_type_ArrayOfInt[s] = k;
      }
    }
    this.jdField_field_971_of_type_Int = a3();
  }
  
  public final void a6(Short2IntArrayMap paramShort2IntArrayMap)
  {
    paramShort2IntArrayMap = paramShort2IntArrayMap.short2IntEntrySet().iterator();
    while (paramShort2IntArrayMap.hasNext())
    {
      Short2IntMap.Entry localEntry = (Short2IntMap.Entry)paramShort2IntArrayMap.next();
      this.jdField_field_971_of_type_ArrayOfInt[localEntry.getShortKey()] = localEntry.getIntValue();
    }
    this.jdField_field_971_of_type_Int = a3();
  }
  
  public final int b1()
  {
    return this.jdField_field_971_of_type_Int;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_703
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */