/*  1:   */import it.unimi.dsi.fastutil.shorts.Short2IntArrayMap;
/*  2:   */import it.unimi.dsi.fastutil.shorts.Short2IntMap.Entry;
/*  3:   */import java.io.DataInputStream;
/*  4:   */import java.io.DataOutputStream;
/*  5:   */import java.util.Arrays;
/*  6:   */import java.util.HashSet;
/*  7:   */import java.util.Iterator;
/*  8:   */import org.schema.game.common.data.element.ElementInformation;
/*  9:   */import org.schema.game.common.data.element.ElementKeyMap;
/* 10:   */
/* 13:   */public class jx
/* 14:   */{
/* 15:   */  private int[] jdField_a_of_type_ArrayOfInt;
/* 16:   */  private int jdField_a_of_type_Int;
/* 17:   */  
/* 18:   */  public jx()
/* 19:   */  {
/* 20:20 */    this.jdField_a_of_type_ArrayOfInt = new int[ElementKeyMap.highestType + 1];
/* 21:   */  }
/* 22:   */  
/* 29:   */  public final void a(short paramShort)
/* 30:   */  {
/* 31:31 */    this.jdField_a_of_type_ArrayOfInt[paramShort] -= 1;
/* 32:32 */    this.jdField_a_of_type_Int = ((int)(this.jdField_a_of_type_Int - ElementKeyMap.getInfo(paramShort).getPrice()));
/* 33:   */  }
/* 34:   */  
/* 35:35 */  public final int a(short paramShort) { return this.jdField_a_of_type_ArrayOfInt[paramShort]; }
/* 36:   */  
/* 37:   */  public final void b(short paramShort) {
/* 38:38 */    if ((!jdField_a_of_type_Boolean) && (paramShort >= this.jdField_a_of_type_ArrayOfInt.length)) throw new AssertionError("ERROR: " + paramShort + "/" + this.jdField_a_of_type_ArrayOfInt.length + "  (" + ElementKeyMap.highestType + ")");
/* 39:39 */    this.jdField_a_of_type_ArrayOfInt[paramShort] += 1;
/* 40:40 */    this.jdField_a_of_type_Int = ((int)(this.jdField_a_of_type_Int + ElementKeyMap.getInfo(paramShort).getPrice()));
/* 41:   */  }
/* 42:   */  
/* 46:   */  public final void a()
/* 47:   */  {
/* 48:48 */    Arrays.fill(this.jdField_a_of_type_ArrayOfInt, 0);
/* 49:49 */    this.jdField_a_of_type_Int = 0;
/* 50:   */  }
/* 51:   */  
/* 53:   */  public final int a()
/* 54:   */  {
/* 55:55 */    long l = 0L;
/* 56:56 */    for (Iterator localIterator = ElementKeyMap.keySet.iterator(); localIterator.hasNext();) { short s = ((Short)localIterator.next()).shortValue();
/* 57:57 */      l += ElementKeyMap.getInfo(s).getPrice() * this.jdField_a_of_type_ArrayOfInt[s];
/* 58:   */    }
/* 59:59 */    return (int)Math.min(2147483647L, l);
/* 60:   */  }
/* 61:   */  
/* 62:62 */  public final void a(DataOutputStream paramDataOutputStream) { int i = 0;
/* 63:63 */    for (int j = 0; j < this.jdField_a_of_type_ArrayOfInt.length; j++) {
/* 64:64 */      if (this.jdField_a_of_type_ArrayOfInt[j] > 0) {
/* 65:65 */        i++;
/* 66:   */      }
/* 67:   */    }
/* 68:68 */    paramDataOutputStream.writeInt(i);
/* 69:69 */    for (j = 0; j < this.jdField_a_of_type_ArrayOfInt.length; j++)
/* 70:70 */      if (this.jdField_a_of_type_ArrayOfInt[j] > 0) {
/* 71:71 */        paramDataOutputStream.writeShort(j);
/* 72:72 */        paramDataOutputStream.writeInt(this.jdField_a_of_type_ArrayOfInt[j]);
/* 73:   */      }
/* 74:   */  }
/* 75:   */  
/* 76:   */  public final void a(DataInputStream paramDataInputStream) {
/* 77:77 */    int i = paramDataInputStream.readInt();
/* 78:78 */    for (int j = 0; j < i; j++) {
/* 79:79 */      short s = paramDataInputStream.readShort();
/* 80:80 */      int k = paramDataInputStream.readInt();
/* 81:81 */      if (ElementKeyMap.exists(s)) {
/* 82:82 */        this.jdField_a_of_type_ArrayOfInt[s] = k;
/* 83:   */      }
/* 84:   */    }
/* 85:85 */    this.jdField_a_of_type_Int = a();
/* 86:   */  }
/* 87:   */  
/* 88:88 */  public final void a(Short2IntArrayMap paramShort2IntArrayMap) { for (Short2IntMap.Entry localEntry : paramShort2IntArrayMap.short2IntEntrySet()) {
/* 89:89 */      this.jdField_a_of_type_ArrayOfInt[localEntry.getShortKey()] = localEntry.getIntValue();
/* 90:   */    }
/* 91:91 */    this.jdField_a_of_type_Int = a();
/* 92:   */  }
/* 93:   */  
/* 95:   */  public final int b()
/* 96:   */  {
/* 97:97 */    return this.jdField_a_of_type_Int;
/* 98:   */  }
/* 99:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     jx
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */