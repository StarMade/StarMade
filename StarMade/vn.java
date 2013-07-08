/*   1:    */import it.unimi.dsi.fastutil.shorts.Short2IntArrayMap;
/*   2:    */import java.io.DataInputStream;
/*   3:    */import java.util.ArrayList;
/*   4:    */import javax.vecmath.Vector3f;
/*   5:    */import org.schema.game.common.data.element.ControlElementMap;
/*   6:    */import org.schema.game.common.data.element.ControlElementMapper;
/*   7:    */import org.schema.game.common.data.element.ElementInformation;
/*   8:    */import org.schema.game.common.data.element.ElementKeyMap;
/*   9:    */import org.schema.game.server.data.blueprintnw.BlueprintEntry;
/*  10:    */import org.schema.schine.network.StateInterface;
/*  11:    */
/*  26:    */public class vn
/*  27:    */  implements vc
/*  28:    */{
/*  29:    */  public xO a;
/*  30:    */  public String a;
/*  31:    */  public int a;
/*  32:    */  private ControlElementMapper jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
/*  33:    */  private int b;
/*  34:    */  public Short2IntArrayMap a;
/*  35:    */  private int c;
/*  36:    */  private ArrayList jdField_a_of_type_JavaUtilArrayList;
/*  37:    */  private jx jdField_a_of_type_Jx;
/*  38:    */  
/*  39:    */  static
/*  40:    */  {
/*  41: 41 */    vn.class.desiredAssertionStatus();
/*  42:    */  }
/*  43:    */  
/*  79:    */  public static vD a(StateInterface paramStateInterface, BlueprintEntry paramBlueprintEntry, String paramString1, String paramString2, float[] paramArrayOfFloat, int paramInt, q paramq1, q paramq2, String paramString3)
/*  80:    */  {
/*  81: 81 */    return paramBlueprintEntry.a().jdField_a_of_type_Vt.a(paramStateInterface, paramBlueprintEntry, paramString1, paramString2, paramArrayOfFloat, paramInt, paramq1, paramq2, paramString3);
/*  82:    */  }
/*  83:    */  
/*  84:    */  public vn(DataInputStream paramDataInputStream)
/*  85:    */  {
/*  86: 50 */    this.jdField_a_of_type_Int = -1;
/*  87:    */    
/*  90: 54 */    vo.k.a();
/*  91:    */    
/*  92: 56 */    this.jdField_a_of_type_JavaUtilArrayList = new ArrayList();
/*  93:    */    
/* 110:    */    Object localObject;
/* 111:    */    
/* 128: 92 */    if (!(localObject = paramDataInputStream.readUTF()).contains(":")) {
/* 129: 93 */      this.jdField_a_of_type_JavaLangString = ((String)localObject);
/* 130: 94 */      this.c = 0;
/* 131:    */    } else {
/* 132: 96 */      localObject = ((String)localObject).split(":");
/* 133: 97 */      this.c = Integer.parseInt(localObject[1]);
/* 134: 98 */      this.jdField_a_of_type_JavaLangString = localObject[0];
/* 135:    */    }
/* 136:100 */    if (this.c < 3) {
/* 137:101 */      this.jdField_a_of_type_JavaUtilArrayList.add("v0.061 to v0.062");
/* 138:    */    }
/* 139:103 */    if (this.c < 4) {
/* 140:104 */      this.jdField_a_of_type_JavaUtilArrayList.add("v0.078 to v0.079");
/* 141:    */    }
/* 142:106 */    if (this.c < 6) {
/* 143:107 */      this.jdField_a_of_type_JavaUtilArrayList.add("v0.0897 to v0.0898");
/* 144:    */    }
/* 145:109 */    if (this.c > 4) {
/* 146:110 */      paramDataInputStream.readBoolean();
/* 147:111 */      paramDataInputStream.readBoolean();
/* 148:112 */      paramDataInputStream.readUTF();
/* 149:113 */      paramDataInputStream.readInt();
/* 150:114 */      this.jdField_a_of_type_Int = paramDataInputStream.readInt();
/* 151:115 */    } else { if (this.c > 1) {
/* 152:116 */        paramDataInputStream.readBoolean();
/* 153:117 */        paramDataInputStream.readBoolean();
/* 154:118 */        paramDataInputStream.readUTF();
/* 155:119 */        paramDataInputStream.readInt();
/* 156:    */      }
/* 157:121 */      else if (this.c > 0) {
/* 158:122 */        paramDataInputStream.readBoolean();
/* 159:123 */        paramDataInputStream.readBoolean(); }
/* 160:124 */      this.jdField_a_of_type_Int = vK.jdField_a_of_type_VK.ordinal();
/* 161:    */    }
/* 162:    */    
/* 170:134 */    this.jdField_a_of_type_XO = new xO(new Vector3f(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat()), new Vector3f(paramDataInputStream.readFloat(), paramDataInputStream.readFloat(), paramDataInputStream.readFloat()));
/* 171:    */    
/* 173:137 */    int i = paramDataInputStream.readInt();
/* 174:138 */    this.b = 0;
/* 175:139 */    this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap = new Short2IntArrayMap();
/* 176:    */    
/* 177:141 */    for (int j = 0; j < i; j++) {
/* 178:142 */      short s = (short)Math.abs(paramDataInputStream.readShort());
/* 179:143 */      int k = paramDataInputStream.readInt();
/* 180:144 */      if (ElementKeyMap.exists(s))
/* 181:    */      {
/* 182:146 */        if (Integer.valueOf(this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.get(s)) == null) {
/* 183:147 */          this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.put(s, 0);
/* 184:    */        }
/* 185:149 */        this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.put(s, this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap.get(s) + k);
/* 186:150 */        this.b = ((int)(this.b + ElementKeyMap.getInfo(s).getPrice() * k));
/* 187:    */      }
/* 188:    */    }
/* 189:153 */    this.jdField_a_of_type_Jx = new jx();
/* 190:154 */    this.jdField_a_of_type_Jx.a(this.jdField_a_of_type_ItUnimiDsiFastutilShortsShort2IntArrayMap);
/* 191:    */    
/* 192:156 */    Ah localAh = Ah.a(paramDataInputStream, false);
/* 193:157 */    this.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper = new ControlElementMapper();
/* 194:158 */    this.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper = ControlElementMap.mapFromTag(localAh, this.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper);
/* 195:    */  }
/* 196:    */  
/* 206:    */  public boolean equals(Object paramObject)
/* 207:    */  {
/* 208:172 */    return ((vn)paramObject).jdField_a_of_type_JavaLangString.equals(this.jdField_a_of_type_JavaLangString);
/* 209:    */  }
/* 210:    */  
/* 213:    */  public int hashCode()
/* 214:    */  {
/* 215:179 */    return this.jdField_a_of_type_JavaLangString.hashCode();
/* 216:    */  }
/* 217:    */  
/* 220:    */  public String toString()
/* 221:    */  {
/* 222:186 */    return this.jdField_a_of_type_JavaLangString;
/* 223:    */  }
/* 224:    */  
/* 293:    */  public final ControlElementMapper a()
/* 294:    */  {
/* 295:259 */    return this.jdField_a_of_type_OrgSchemaGameCommonDataElementControlElementMapper;
/* 296:    */  }
/* 297:    */  
/* 298:    */  public final int a() {
/* 299:263 */    return this.b;
/* 300:    */  }
/* 301:    */  
/* 302:    */  public final jx a()
/* 303:    */  {
/* 304:268 */    return this.jdField_a_of_type_Jx;
/* 305:    */  }
/* 306:    */  
/* 307:    */  public final String a() {
/* 308:272 */    return this.jdField_a_of_type_JavaLangString;
/* 309:    */  }
/* 310:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     vn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */