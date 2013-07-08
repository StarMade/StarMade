import it.unimi.dsi.fastutil.shorts.Short2IntArrayMap;
import java.io.DataInputStream;
import java.util.ArrayList;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ControlElementMapper;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.server.data.blueprintnw.BlueprintEntry;
import org.schema.schine.network.StateInterface;

public class class_1055
  implements class_1074
{
  public class_988 field_235;
  public String field_235;
  public int field_235;
  private ControlElementMapper jdField_field_235_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
  private int field_236;
  public Short2IntArrayMap field_235;
  private int field_237;
  private ArrayList jdField_field_235_of_type_JavaUtilArrayList;
  private class_703 jdField_field_235_of_type_Class_703;
  
  public static class_1133 a(StateInterface paramStateInterface, BlueprintEntry paramBlueprintEntry, String paramString1, String paramString2, float[] paramArrayOfFloat, int paramInt, class_48 paramclass_481, class_48 paramclass_482, String paramString3)
  {
    return paramBlueprintEntry.a17().jdField_field_1360_of_type_Class_1043.a(paramStateInterface, paramBlueprintEntry, paramString1, paramString2, paramArrayOfFloat, paramInt, paramclass_481, paramclass_482, paramString3);
  }
  
  public class_1055(DataInputStream paramDataInputStream)
  {
    this.jdField_field_235_of_type_Int = -1;
    class_1057.field_1328.a4();
    this.jdField_field_235_of_type_JavaUtilArrayList = new ArrayList();
    Object localObject;
    if (!(localObject = paramDataInputStream.readUTF()).contains(":"))
    {
      this.jdField_field_235_of_type_JavaLangString = ((String)localObject);
      this.field_237 = 0;
    }
    else
    {
      localObject = ((String)localObject).split(":");
      this.field_237 = Integer.parseInt(localObject[1]);
      this.jdField_field_235_of_type_JavaLangString = localObject[0];
    }
    if (this.field_237 < 3) {
      this.jdField_field_235_of_type_JavaUtilArrayList.add("v0.061 to v0.062");
    }
    if (this.field_237 < 4) {
      this.jdField_field_235_of_type_JavaUtilArrayList.add("v0.078 to v0.079");
    }
    if (this.field_237 < 6) {
      this.jdField_field_235_of_type_JavaUtilArrayList.add("v0.0897 to v0.0898");
    }
    if (this.field_237 > 4)
    {
      paramDataInputStream.readBoolean();
      paramDataInputStream.readBoolean();
      paramDataInputStream.readUTF();
      paramDataInputStream.readInt();
      this.jdField_field_235_of_type_Int = paramDataInputStream.readInt();
    }
    else
    {
      if (this.field_237 > 1)
      {
        paramDataInputStream.readBoolean();
        paramDataInputStream.readBoolean();
        paramDataInputStream.readUTF();
        paramDataInputStream.readInt();
      }
      else if (this.field_237 > 0)
      {
        paramDataInputStream.readBoolean();
        paramDataInputStream.readBoolean();
      }
      this.jdField_field_235_of_type_Int = class_1084.jdField_field_1360_of_type_Class_1084.ordinal();
    }
    this.jdField_field_235_of_type_Class_988 = new class_988(new Vector3f(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat()), new Vector3f(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat()));
    int i = paramDataInputStream.readInt();
    this.field_236 = 0;
    this.jdField_field_235_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap = new Short2IntArrayMap();
    for (int j = 0; j < i; j++)
    {
      short s = (short)Math.abs(paramDataInputStream.readShort());
      int k = paramDataInputStream.readInt();
      if (ElementKeyMap.exists(s))
      {
        if (Integer.valueOf(this.jdField_field_235_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.get(s)) == null) {
          this.jdField_field_235_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.put(s, 0);
        }
        this.jdField_field_235_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.put(s, this.jdField_field_235_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.get(s) + k);
        this.field_236 = ((int)(this.field_236 + ElementKeyMap.getInfo(s).getPrice() * k));
      }
    }
    this.jdField_field_235_of_type_Class_703 = new class_703();
    this.jdField_field_235_of_type_Class_703.a6(this.jdField_field_235_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap);
    class_69 localclass_69 = class_69.a10(paramDataInputStream, false);
    this.jdField_field_235_of_type_OrgSchemaGameCommonDataElementControlElementMapper = new ControlElementMapper();
    this.jdField_field_235_of_type_OrgSchemaGameCommonDataElementControlElementMapper = ControlElementMap.mapFromTag(localclass_69, this.jdField_field_235_of_type_OrgSchemaGameCommonDataElementControlElementMapper);
  }
  
  public boolean equals(Object paramObject)
  {
    return ((class_1055)paramObject).jdField_field_235_of_type_JavaLangString.equals(this.jdField_field_235_of_type_JavaLangString);
  }
  
  public int hashCode()
  {
    return this.jdField_field_235_of_type_JavaLangString.hashCode();
  }
  
  public String toString()
  {
    return this.jdField_field_235_of_type_JavaLangString;
  }
  
  public final ControlElementMapper a1()
  {
    return this.jdField_field_235_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
  }
  
  public final int a2()
  {
    return this.field_236;
  }
  
  public final class_703 a3()
  {
    return this.jdField_field_235_of_type_Class_703;
  }
  
  public final String a4()
  {
    return this.jdField_field_235_of_type_JavaLangString;
  }
  
  static
  {
    vn.class.desiredAssertionStatus();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1055
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */