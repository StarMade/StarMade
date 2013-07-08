/*   1:    */import com.bulletphysics.linearmath.Transform;
/*   2:    */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*   3:    */import java.io.File;
/*   4:    */import java.util.HashMap;
/*   5:    */import org.schema.game.common.controller.SegmentController;
/*   6:    */import org.schema.game.common.data.world.Segment;
/*   7:    */import org.schema.game.common.data.world.SegmentData;
/*   8:    */import org.schema.game.server.controller.EntityAlreadyExistsException;
/*   9:    */import org.schema.schine.network.NetworkStateContainer;
/*  10:    */import org.schema.schine.network.StateInterface;
/*  11:    */import org.schema.schine.network.client.ClientStateInterface;
/*  12:    */
/*  18:    */public final class ve
/*  19:    */{
/*  20:    */  private Object[] a;
/*  21:    */  public final Class a;
/*  22:    */  public final int a;
/*  23:    */  
/*  24:    */  public static String a(String paramString)
/*  25:    */  {
/*  26: 26 */    return "ENTITY_SHIP_" + paramString;
/*  27:    */  }
/*  28:    */  
/*  29: 29 */  public static String b(String paramString) { return "ENTITY_SPACESTATION_" + paramString; }
/*  30:    */  
/*  31:    */  public static String c(String paramString) {
/*  32: 32 */    return "ENTITY_FLOATINGROCK_" + paramString;
/*  33:    */  }
/*  34:    */  
/*  35: 35 */  public static String d(String paramString) { return "ENTITY_SHOP_" + paramString; }
/*  36:    */  
/*  40:    */  public static boolean a(StateInterface paramStateInterface, String paramString)
/*  41:    */  {
/*  42: 42 */    File localFile = new File(vg.a + paramString + ".ent");
/*  43: 43 */    if (((paramStateInterface instanceof vg)) && 
/*  44: 44 */      (((vg)paramStateInterface).c().containsKey(paramString))) {
/*  45: 45 */      throw new EntityAlreadyExistsException(paramString);
/*  46:    */    }
/*  47:    */    
/*  48: 48 */    if (localFile.exists()) {
/*  49: 49 */      throw new EntityAlreadyExistsException(paramString);
/*  50:    */    }
/*  51: 51 */    return false;
/*  52:    */  }
/*  53:    */  
/*  54:    */  public static ki a(StateInterface paramStateInterface, String paramString1, int paramInt1, String paramString2, float[] paramArrayOfFloat, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
/*  55:    */  {
/*  56:    */    ki localki;
/*  57: 57 */    (localki = new ki(paramStateInterface)).setUniqueIdentifier(paramString1);
/*  58: 58 */    localki.getMinPos().b(new q(paramInt2, paramInt3, paramInt4));
/*  59: 59 */    localki.getMaxPos().b(new q(paramInt5, paramInt6, paramInt7));
/*  60: 60 */    localki.setCreatorId(kk.b.ordinal());
/*  61: 61 */    localki.setId(paramStateInterface.getNextFreeObjectId());
/*  62: 62 */    localki.setSectorId(paramInt1);
/*  63: 63 */    localki.setRealName(paramString2);
/*  64: 64 */    localki.initialize();
/*  65: 65 */    localki.getInitialTransform().setFromOpenGLMatrix(paramArrayOfFloat);
/*  66: 66 */    return localki;
/*  67:    */  }
/*  68:    */  
/*  69:    */  public static kd a(StateInterface paramStateInterface, String paramString1, int paramInt1, String paramString2, float[] paramArrayOfFloat, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, String paramString3) { kd localkd;
/*  70: 70 */    (localkd = new kd(paramStateInterface)).setUniqueIdentifier(paramString1);
/*  71: 71 */    localkd.getMinPos().b(new q(paramInt2, paramInt3, paramInt4));
/*  72: 72 */    localkd.getMaxPos().b(new q(paramInt5, paramInt6, paramInt7));
/*  73: 73 */    localkd.setId(paramStateInterface.getNextFreeObjectId());
/*  74: 74 */    localkd.setSectorId(paramInt1);
/*  75: 75 */    localkd.setRealName(paramString2);
/*  76: 76 */    localkd.initialize();
/*  77: 77 */    localkd.getInitialTransform().setFromOpenGLMatrix(paramArrayOfFloat);
/*  78:    */    
/*  80: 80 */    localkd.setSpawner(paramString3);
/*  81: 81 */    return localkd;
/*  82:    */  }
/*  83:    */  
/* 121:    */  public static boolean a(String paramString)
/* 122:    */  {
/* 123:123 */    return (paramString.length() > 0) && (paramString.matches("[a-zA-Z0-9_-]+"));
/* 124:    */  }
/* 125:    */  
/* 129:    */  public ve(int paramInt, Object[] paramArrayOfObject, Class paramClass)
/* 130:    */  {
/* 131:131 */    this.jdField_a_of_type_Int = paramInt;
/* 132:132 */    this.jdField_a_of_type_ArrayOfJavaLangObject = paramArrayOfObject;
/* 133:133 */    this.jdField_a_of_type_JavaLangClass = paramClass;
/* 134:    */  }
/* 135:    */  
/* 144:    */  public final kd a(vg paramvg)
/* 145:    */  {
/* 146:146 */    Object localObject1 = new float[16];
/* 147:147 */    int i = 0;
/* 148:148 */    for (int j = 0; j < 16; j++) {
/* 149:149 */      localObject1[j] = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).floatValue();
/* 150:    */    }
/* 151:151 */    j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 152:152 */    int k = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 153:153 */    int m = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 154:    */    
/* 155:155 */    int n = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 156:156 */    int i1 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 157:157 */    int i2 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 158:    */    
/* 159:159 */    int i3 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 160:    */    
/* 161:161 */    String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)];
/* 162:162 */    Object localObject2 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[i];
/* 163:    */    
/* 164:164 */    lE locallE = (lE)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get(i3);
/* 165:    */    
/* 166:166 */    a(paramvg, str);
/* 167:    */    
/* 169:169 */    localObject1 = a(paramvg, str, locallE.c(), (String)localObject2, (float[])localObject1, j, k, m, n, i1, i2, locallE.getUniqueIdentifier());
/* 170:    */    
/* 172:172 */    (
/* 173:173 */      localObject2 = new mw((SegmentController)localObject1)).a(new SegmentData(paramvg instanceof ClientStateInterface));
/* 174:174 */    ((mw)localObject2).a().setSegment((Segment)localObject2);
/* 175:175 */    ((mw)localObject2).a().setInfoElement((byte)8, (byte)8, (byte)8, (short)1, true);
/* 176:176 */    ((mw)localObject2).a(System.currentTimeMillis());
/* 177:177 */    ((kd)localObject1).getSegmentBuffer().a((Segment)localObject2);
/* 178:178 */    ((kd)localObject1).getSegmentBuffer().b((Segment)localObject2);
/* 179:    */    
/* 180:180 */    return localObject1;
/* 181:    */  }
/* 182:    */  
/* 183:183 */  public final kn a(vg paramvg) { Object localObject1 = new float[16];
/* 184:184 */    int i = 0;
/* 185:185 */    for (lE locallE1 = 0; locallE1 < 16; locallE1++) {
/* 186:186 */      localObject1[locallE1] = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).floatValue();
/* 187:    */    }
/* 188:188 */    locallE1 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 189:189 */    String str1 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 190:190 */    int j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 191:    */    
/* 192:192 */    lE locallE2 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 193:193 */    String str2 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 194:194 */    int k = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 195:    */    
/* 196:196 */    int m = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 197:    */    
/* 198:198 */    String str3 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)];
/* 199:199 */    Object localObject2 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[i];
/* 200:    */    
/* 202:202 */    a(paramvg, str3);
/* 203:203 */    lE locallE3 = (lE)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get(m);
/* 204:    */    
/* 205:205 */    String str4 = locallE3.getUniqueIdentifier();int n = k;str3 = str2;locallE3 = locallE2;k = j;str2 = str1;locallE2 = locallE1;Object localObject4 = localObject1;Object localObject3 = localObject2;locallE1 = locallE3.c();localObject2 = str3;localObject1 = paramvg; kn localkn; (localkn = new kn((StateInterface)localObject1)).setUniqueIdentifier((String)localObject2);localkn.getMinPos().b(new q(locallE2, str2, k));localkn.getMaxPos().b(new q(locallE3, str3, n));localkn.setId(((StateInterface)localObject1).getNextFreeObjectId());localkn.setSectorId(locallE1);localkn.setRealName(localObject3);localkn.initialize();localkn.getInitialTransform().setFromOpenGLMatrix(localObject4);localkn.setSpawner(str4);localObject1 = localkn;
/* 206:    */    
/* 208:208 */    (
/* 209:209 */      localObject2 = new mw((SegmentController)localObject1)).a(new SegmentData(paramvg instanceof ClientStateInterface));
/* 210:210 */    ((mw)localObject2).a().setSegment((Segment)localObject2);
/* 211:211 */    ((mw)localObject2).a().setInfoElement((byte)8, (byte)8, (byte)8, (short)1, true);
/* 212:212 */    ((mw)localObject2).a(System.currentTimeMillis());
/* 213:213 */    ((kn)localObject1).getSegmentBuffer().a((Segment)localObject2);
/* 214:    */    
/* 215:215 */    return localObject1;
/* 216:    */  }
/* 217:    */  
/* 218:218 */  public final ki a(vg paramvg) { Object localObject1 = new float[16];
/* 219:219 */    int i = 0;
/* 220:220 */    for (int j = 0; j < 16; j++) {
/* 221:221 */      localObject1[j] = ((Float)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).floatValue();
/* 222:    */    }
/* 223:223 */    j = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 224:224 */    int k = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 225:225 */    int m = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 226:    */    
/* 227:227 */    int n = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 228:228 */    int i1 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 229:229 */    int i2 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 230:    */    
/* 231:231 */    int i3 = ((Integer)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)]).intValue();
/* 232:    */    
/* 233:233 */    String str = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[(i++)];
/* 234:234 */    Object localObject2 = (String)this.jdField_a_of_type_ArrayOfJavaLangObject[i];
/* 235:    */    
/* 236:236 */    a(paramvg, str);
/* 237:237 */    lE locallE = (lE)paramvg.getLocalAndRemoteObjectContainer().getLocalObjects().get(i3);
/* 238:    */    
/* 239:239 */    localObject1 = a(paramvg, str, locallE.c(), (String)localObject2, (float[])localObject1, j, k, m, n, i1, i2);
/* 240:    */    
/* 242:242 */    (
/* 243:243 */      localObject2 = new mw((SegmentController)localObject1)).a(new SegmentData(paramvg instanceof ClientStateInterface));
/* 244:244 */    ((mw)localObject2).a().setSegment((Segment)localObject2);
/* 245:245 */    ((mw)localObject2).a().setInfoElement((byte)8, (byte)8, (byte)8, (short)5, true);
/* 246:246 */    ((mw)localObject2).a(System.currentTimeMillis());
/* 247:247 */    ((ki)localObject1).getSegmentBuffer().a((Segment)localObject2);
/* 248:    */    
/* 249:249 */    return localObject1;
/* 250:    */  }
/* 251:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     ve
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */