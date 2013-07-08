/*   1:    */import java.io.BufferedReader;
/*   2:    */import java.io.File;
/*   3:    */import java.io.FileReader;
/*   4:    */import java.io.IOException;
/*   5:    */import org.schema.game.network.objects.NetworkGameState;
/*   6:    */import org.schema.schine.network.StateInterface;
/*   7:    */import org.schema.schine.network.objects.NetworkObject;
/*   8:    */import org.schema.schine.network.objects.Sendable;
/*   9:    */import org.schema.schine.network.objects.remote.RemoteFloat;
/*  10:    */import org.schema.schine.network.objects.remote.RemoteInteger;
/*  11:    */import org.schema.schine.network.objects.remote.RemoteString;
/*  12:    */import org.schema.schine.network.server.ServerStateInterface;
/*  13:    */
/*  21:    */public class lg
/*  22:    */  implements Sendable
/*  23:    */{
/*  24:    */  private final StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*  25:    */  private int jdField_a_of_type_Int;
/*  26:    */  private final boolean jdField_a_of_type_Boolean;
/*  27:    */  private boolean jdField_b_of_type_Boolean;
/*  28:    */  private boolean jdField_c_of_type_Boolean;
/*  29:    */  private NetworkGameState jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState;
/*  30:    */  private float jdField_a_of_type_Float;
/*  31:    */  private float jdField_b_of_type_Float;
/*  32:    */  private float jdField_c_of_type_Float;
/*  33:    */  private String jdField_a_of_type_JavaLangString;
/*  34:    */  private final lT jdField_a_of_type_LT;
/*  35:    */  private final lJ jdField_a_of_type_LJ;
/*  36:    */  
/*  37:    */  public final lT a()
/*  38:    */  {
/*  39: 39 */    return this.jdField_a_of_type_LT;
/*  40:    */  }
/*  41:    */  
/*  49: 49 */  private String jdField_b_of_type_JavaLangString = "(TODO description)";
/*  50: 50 */  private String jdField_c_of_type_JavaLangString = "(TODO name)";
/*  51:    */  
/*  52:    */  public lg(StateInterface paramStateInterface) {
/*  53: 53 */    this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  54: 54 */    this.jdField_a_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
/*  55: 55 */    if (this.jdField_a_of_type_Boolean) {
/*  56: 56 */      this.jdField_a_of_type_Float = ((Integer)vo.s.a()).intValue();
/*  57:    */    }
/*  58: 58 */    this.jdField_a_of_type_LT = new lT(this);
/*  59: 59 */    this.jdField_a_of_type_LJ = new lJ(this);
/*  60: 60 */    if (this.jdField_a_of_type_Boolean) {
/*  61:    */      try {
/*  62: 62 */        a();
/*  63: 63 */      } catch (IOException localIOException) { 
/*  64:    */        
/*  66: 66 */          localIOException.printStackTrace();this.jdField_a_of_type_JavaLangString = "";
/*  67:    */      }
/*  68: 68 */      this.jdField_b_of_type_Float = ((Float)vo.y.a()).floatValue();
/*  69: 69 */      this.jdField_c_of_type_Float = ((Float)vo.z.a()).floatValue();
/*  70:    */    }
/*  71:    */  }
/*  72:    */  
/*  76:    */  public void cleanUpOnEntityDelete() {}
/*  77:    */  
/*  80:    */  public int getId()
/*  81:    */  {
/*  82: 82 */    return this.jdField_a_of_type_Int;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public final NetworkGameState a()
/*  86:    */  {
/*  87: 87 */    return this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public StateInterface getState()
/*  91:    */  {
/*  92: 92 */    return this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*  93:    */  }
/*  94:    */  
/*  96:    */  public void initFromNetworkObject(NetworkObject paramNetworkObject)
/*  97:    */  {
/*  98: 98 */    setId(((Integer)paramNetworkObject.id.get()).intValue());
/*  99: 99 */    lT.c();
/* 100:100 */    lJ.c();
/* 101:101 */    if (!isOnServer()) {
/* 102:102 */      this.jdField_a_of_type_Float = ((Float)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.serverMaxSpeed.get()).floatValue();
/* 103:103 */      this.jdField_b_of_type_Float = ((Float)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.linearDamping.get()).floatValue();
/* 104:104 */      this.jdField_c_of_type_Float = ((Float)this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.rotationalDamping.get()).floatValue();
/* 105:    */    }
/* 106:    */  }
/* 107:    */  
/* 115:    */  public void initialize() {}
/* 116:    */  
/* 123:    */  public boolean isMarkedForDeleteVolatile()
/* 124:    */  {
/* 125:125 */    return this.jdField_b_of_type_Boolean;
/* 126:    */  }
/* 127:    */  
/* 128:    */  public boolean isMarkedForDeleteVolatileSent()
/* 129:    */  {
/* 130:130 */    return this.jdField_c_of_type_Boolean;
/* 131:    */  }
/* 132:    */  
/* 133:    */  public boolean isOnServer()
/* 134:    */  {
/* 135:135 */    return this.jdField_a_of_type_Boolean;
/* 136:    */  }
/* 137:    */  
/* 138:    */  public void newNetworkObject()
/* 139:    */  {
/* 140:140 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState = new NetworkGameState(getState());
/* 141:    */  }
/* 142:    */  
/* 143:    */  public final void a() {
/* 144:144 */    if (!(localObject = new File("./server-message.txt")).exists()) {
/* 145:145 */      ((File)localObject).createNewFile();
/* 146:146 */      this.jdField_a_of_type_JavaLangString = "";return;
/* 147:    */    }
/* 148:148 */    Object localObject = new BufferedReader(new FileReader((File)localObject));
/* 149:149 */    String str = null;
/* 150:150 */    StringBuffer localStringBuffer = new StringBuffer();
/* 151:151 */    while ((str = ((BufferedReader)localObject).readLine()) != null) {
/* 152:152 */      localStringBuffer.append(str + "\n");
/* 153:    */    }
/* 154:154 */    ((BufferedReader)localObject).close();
/* 155:155 */    this.jdField_a_of_type_JavaLangString = localStringBuffer.toString();
/* 156:    */  }
/* 157:    */  
/* 159:    */  public void setId(int paramInt)
/* 160:    */  {
/* 161:161 */    this.jdField_a_of_type_Int = paramInt;
/* 162:    */  }
/* 163:    */  
/* 164:    */  public void setMarkedForDeleteVolatile(boolean paramBoolean)
/* 165:    */  {
/* 166:166 */    this.jdField_b_of_type_Boolean = paramBoolean;
/* 167:    */  }
/* 168:    */  
/* 170:    */  public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
/* 171:    */  {
/* 172:172 */    this.jdField_c_of_type_Boolean = paramBoolean;
/* 173:    */  }
/* 174:    */  
/* 177:    */  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
/* 178:    */  {
/* 179:179 */    if (!this.jdField_a_of_type_Boolean) {
/* 180:180 */      vo.b((NetworkGameState)paramNetworkObject);
/* 181:    */    }
/* 182:182 */    this.jdField_a_of_type_LT.a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
/* 183:183 */    this.jdField_a_of_type_LJ.b(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
/* 184:    */  }
/* 185:    */  
/* 186:    */  public void updateLocal(xq paramxq)
/* 187:    */  {
/* 188:188 */    this.jdField_a_of_type_LT.b();
/* 189:189 */    this.jdField_a_of_type_LJ.a();
/* 190:    */  }
/* 191:    */  
/* 192:    */  public void updateToFullNetworkObject()
/* 193:    */  {
/* 194:194 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.id.set(Integer.valueOf(getId()));
/* 195:195 */    if ((!d) && (this.jdField_a_of_type_JavaLangString == null)) throw new AssertionError();
/* 196:196 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.serverMessage.set(this.jdField_a_of_type_JavaLangString);
/* 197:197 */    if (this.jdField_a_of_type_Boolean) {
/* 198:198 */      vo.a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
/* 199:199 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.saveSlotsAllowed.set((Integer)vo.o.a());
/* 200:200 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.serverMaxSpeed.set(Float.valueOf(this.jdField_a_of_type_Float));
/* 201:201 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.linearDamping.set(Float.valueOf(this.jdField_b_of_type_Float));
/* 202:202 */      this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.rotationalDamping.set(Float.valueOf(this.jdField_c_of_type_Float));
/* 203:    */    }
/* 204:204 */    this.jdField_a_of_type_LT.b(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
/* 205:205 */    this.jdField_a_of_type_LJ.a(this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState);
/* 206:    */  }
/* 207:    */  
/* 208:    */  public void updateToNetworkObject()
/* 209:    */  {
/* 210:210 */    this.jdField_a_of_type_OrgSchemaGameNetworkObjectsNetworkGameState.id.set(Integer.valueOf(getId()));
/* 211:211 */    lT.d();
/* 212:212 */    lJ.b();
/* 213:    */  }
/* 214:    */  
/* 219:    */  public final lJ a()
/* 220:    */  {
/* 221:221 */    return this.jdField_a_of_type_LJ;
/* 222:    */  }
/* 223:    */  
/* 228:    */  public void destroyPersistent() {}
/* 229:    */  
/* 234:    */  public boolean isMarkedForPermanentDelete()
/* 235:    */  {
/* 236:236 */    return false;
/* 237:    */  }
/* 238:    */  
/* 243:    */  public void markedForPermanentDelete(boolean paramBoolean) {}
/* 244:    */  
/* 249:    */  public boolean isUpdatable()
/* 250:    */  {
/* 251:251 */    return true;
/* 252:    */  }
/* 253:    */  
/* 258:    */  public final float a()
/* 259:    */  {
/* 260:260 */    return this.jdField_a_of_type_Float;
/* 261:    */  }
/* 262:    */  
/* 272:    */  public String toString()
/* 273:    */  {
/* 274:274 */    return "SenableGameState(" + this.jdField_a_of_type_Int + ")";
/* 275:    */  }
/* 276:    */  
/* 278:    */  public final String a()
/* 279:    */  {
/* 280:280 */    return this.jdField_b_of_type_JavaLangString;
/* 281:    */  }
/* 282:    */  
/* 284:    */  public final String b()
/* 285:    */  {
/* 286:286 */    return this.jdField_c_of_type_JavaLangString;
/* 287:    */  }
/* 288:    */  
/* 289:    */  public final float b()
/* 290:    */  {
/* 291:291 */    return this.jdField_b_of_type_Float;
/* 292:    */  }
/* 293:    */  
/* 294:    */  public final float c() {
/* 295:295 */    return this.jdField_c_of_type_Float;
/* 296:    */  }
/* 297:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     lg
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */