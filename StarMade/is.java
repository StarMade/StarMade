/*   1:    */import java.util.ArrayList;
/*   2:    */import java.util.HashMap;
/*   3:    */import java.util.List;
/*   4:    */import java.util.Observable;
/*   5:    */import java.util.Observer;
/*   6:    */import javax.vecmath.Vector4f;
/*   7:    */import org.schema.game.common.data.element.BlockFactory;
/*   8:    */import org.schema.game.common.data.element.ElementCategory;
/*   9:    */import org.schema.game.common.data.element.ElementInformation;
/*  10:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  11:    */import org.schema.game.common.data.element.ElementParser;
/*  12:    */import org.schema.game.common.data.element.FactoryResource;
/*  13:    */import org.schema.schine.network.client.ClientState;
/*  14:    */
/*  26:    */public class is
/*  27:    */  extends yr
/*  28:    */  implements Observer
/*  29:    */{
/*  30:    */  private final yA jdField_a_of_type_YA;
/*  31:    */  private HashMap jdField_a_of_type_JavaUtilHashMap;
/*  32:    */  private boolean jdField_a_of_type_Boolean;
/*  33:    */  
/*  34:    */  public is(ClientState paramClientState, HashMap paramHashMap)
/*  35:    */  {
/*  36: 36 */    super(paramClientState);
/*  37: 37 */    this.jdField_a_of_type_JavaUtilHashMap = paramHashMap;
/*  38: 38 */    this.jdField_a_of_type_YA = new yA(paramClientState);
/*  39:    */  }
/*  40:    */  
/*  43:    */  private void a(ElementCategory paramElementCategory, yA paramyA, int paramInt)
/*  44:    */  {
/*  45: 45 */    paramyA.a(a());
/*  46:    */    
/*  47:    */    Object localObject;
/*  48: 48 */    ElementInformation localElementInformation = null;(localObject = new yP(176, 30, d.h(), a())).b = new ArrayList();
/*  49: 49 */    ((yP)localObject).b.add("+ " + ElementParser.getStringFromType(paramElementCategory.getCategory()));
/*  50:    */    
/*  51:    */    yP localyP;
/*  52: 52 */    (localyP = new yP(176, 30, d.h(), a())).b = new ArrayList();
/*  53: 53 */    localyP.b.add("- " + ElementParser.getStringFromType(paramElementCategory.getCategory()));
/*  54:    */    
/*  56: 56 */    (localObject = new yC(a(), (yz)localObject, localyP)).a().x = (paramInt * 5);
/*  57: 57 */    int i = 1;
/*  58:    */    
/*  60: 60 */    if (paramElementCategory.hasChildren()) {
/*  61: 61 */      for (j = 0; j < paramElementCategory.getChildren().size(); j++) {
/*  62: 62 */        a((ElementCategory)paramElementCategory.getChildren().get(j), ((yC)localObject).a(), paramInt + 1);
/*  63: 63 */        i = 0;
/*  64:    */      }
/*  65:    */    }
/*  66:    */    
/*  68: 68 */    ((yC)localObject).a().a(a());
/*  69: 69 */    int j = 0;
/*  70: 70 */    for (paramInt = 0; paramInt < paramElementCategory.getInfoElements().size(); paramInt++)
/*  71:    */    {
/*  72: 72 */      if ((localElementInformation = (ElementInformation)paramElementCategory.getInfoElements().get(paramInt)).isShoppable())
/*  73:    */      {
/*  74: 74 */        a(((yC)localObject).a(), localElementInformation, j);
/*  75: 75 */        j++;
/*  76: 76 */        i = 0;
/*  77:    */      }
/*  78:    */    }
/*  79:    */    
/*  82: 82 */    if (i == 0) {
/*  83: 83 */      ((yC)localObject).addObserver(this);
/*  84: 84 */      ((yz)localObject).a = "CATEGORY";
/*  85: 85 */      ((yC)localObject).c();
/*  86: 86 */      ((yz)localObject).g = true;
/*  87:    */      
/*  89: 89 */      paramInt = new yD((yz)localObject, (yz)localObject, a());
/*  90: 90 */      ((yC)localObject).b(this);
/*  91: 91 */      paramyA.a(paramInt);
/*  92:    */    }
/*  93:    */  }
/*  94:    */  
/*  96:    */  private void a(yA paramyA, ElementInformation paramElementInformation, int paramInt)
/*  97:    */  {
/*  98: 98 */    (paramInt = new ip(a(), paramElementInformation, paramInt)).c();
/*  99:    */    
/* 100:100 */    yy localyy = new yy(paramInt.a(), paramInt.b(), paramInt.a(), new Vector4f(1.0F, 1.0F, 1.0F, 0.2F), paramInt);paramyA = null;paramyA.a(new yD(paramInt, localyy, paramInt.a()));
/* 101:    */    
/* 103:103 */    if (paramElementInformation.getId() >= 0)
/* 104:    */    {
/* 105:105 */      (paramyA = new iu(paramElementInformation, a(), d.n())).a = new ArrayList();
/* 106:    */      
/* 107:107 */      if ((!b) && (paramElementInformation.getDescription() == null)) { throw new AssertionError(paramElementInformation.getId() + "; " + paramElementInformation.getName());
/* 108:    */      }
/* 109:    */      
/* 110:110 */      paramInt = paramElementInformation.getDescription().split("\\n");
/* 111:111 */      for (int i = 0; i < paramInt.length; i++) {
/* 112:112 */        paramInt[i] = paramInt[i].replace("$ACTIVATE", cv.w.b());
/* 113:    */        
/* 114:114 */        if (paramInt[i].contains("$RESOURCES")) {
/* 115:115 */          paramInt[i] = paramInt[i].replace("$RESOURCES", a(paramElementInformation));
/* 116:    */        }
/* 117:117 */        if (paramInt[i].contains("$ARMOUR")) {
/* 118:118 */          paramInt[i] = paramInt[i].replace("$ARMOUR", String.valueOf(paramElementInformation.getAmour()));
/* 119:    */        }
/* 120:120 */        if (paramInt[i].contains("$HP")) {
/* 121:121 */          paramInt[i] = paramInt[i].replace("$HP", String.valueOf(paramElementInformation.getMaxHitPoints()));
/* 122:    */        }
/* 123:123 */        paramyA.a.add(paramInt[i]);
/* 124:    */      }
/* 125:    */      
/* 126:126 */      this.jdField_a_of_type_JavaUtilHashMap.put(Short.valueOf(paramElementInformation.getId()), paramyA);
/* 127:    */    }
/* 128:    */  }
/* 129:    */  
/* 131:    */  private static CharSequence a(ElementInformation paramElementInformation)
/* 132:    */  {
/* 133:133 */    if (paramElementInformation.getFactory() == null) {
/* 134:134 */      return "CANNOT DISPLAY RESOURCES: NOT A FACTORY";
/* 135:    */    }
/* 136:    */    StringBuffer localStringBuffer;
/* 137:137 */    (localStringBuffer = new StringBuffer()).append("----------Factory Production--------------\n\n");
/* 138:    */    
/* 139:139 */    for (int i = 0; i < paramElementInformation.getFactory().input.length; i++) {
/* 140:140 */      localStringBuffer.append("----------Product-<" + (i + 1) + ">--------------\n");
/* 141:141 */      localStringBuffer.append("--- Required Resources:\n");
/* 142:    */      FactoryResource localFactoryResource;
/* 143:143 */      for (localFactoryResource : paramElementInformation.getFactory().input[i]) {
/* 144:144 */        localStringBuffer.append(localFactoryResource.count + "x " + ElementKeyMap.getInfo(localFactoryResource.type).getName() + "\n");
/* 145:    */      }
/* 146:146 */      localStringBuffer.append("\n\n--- Produces Resources:\n");
/* 147:147 */      for (localFactoryResource : paramElementInformation.getFactory().output[i]) {
/* 148:148 */        localStringBuffer.append(localFactoryResource.count + "x " + ElementKeyMap.getInfo(localFactoryResource.type).getName() + "\n");
/* 149:    */      }
/* 150:150 */      localStringBuffer.append("\n");
/* 151:    */    }
/* 152:152 */    localStringBuffer.append("\n---------------------------------------------\n\n");
/* 153:153 */    return localStringBuffer.toString();
/* 154:    */  }
/* 155:    */  
/* 158:    */  public final void a() {}
/* 159:    */  
/* 161:    */  public final void b()
/* 162:    */  {
/* 163:163 */    if (!this.jdField_a_of_type_Boolean) {
/* 164:164 */      c();
/* 165:    */    }
/* 166:166 */    super.b();
/* 167:    */  }
/* 168:    */  
/* 169:    */  public final float a()
/* 170:    */  {
/* 171:171 */    return this.jdField_a_of_type_YA.a();
/* 172:    */  }
/* 173:    */  
/* 174:    */  private bz a() {
/* 175:175 */    return ((ct)a()).a().a.a.a;
/* 176:    */  }
/* 177:    */  
/* 178:    */  public final float b()
/* 179:    */  {
/* 180:180 */    return this.jdField_a_of_type_YA.b();
/* 181:    */  }
/* 182:    */  
/* 183:    */  public final boolean a_()
/* 184:    */  {
/* 185:185 */    if (a() != null) {
/* 186:186 */      return (((yz)a()).a_()) && (super.a_());
/* 187:    */    }
/* 188:188 */    return super.a_();
/* 189:    */  }
/* 190:    */  
/* 197:    */  public final void c()
/* 198:    */  {
/* 199:199 */    super.c();
/* 200:    */    
/* 202:    */    ElementCategory localElementCategory;
/* 203:    */    
/* 205:205 */    if ((localElementCategory = ElementKeyMap.getCategoryHirarchy()).hasChildren())
/* 206:    */    {
/* 210:210 */      for (i = 0; i < localElementCategory.getChildren().size(); i++) {
/* 211:211 */        a((ElementCategory)localElementCategory.getChildren().get(i), this.jdField_a_of_type_YA, 0);
/* 212:    */      }
/* 213:    */    }
/* 214:214 */    int i = 0;
/* 215:215 */    for (int j = 0; j < localElementCategory.getInfoElements().size(); j++) {
/* 216:    */      ElementInformation localElementInformation;
/* 217:217 */      if ((localElementInformation = (ElementInformation)localElementCategory.getInfoElements().get(j)).isShoppable()) {
/* 218:218 */        a(this.jdField_a_of_type_YA, localElementInformation, i);
/* 219:219 */        i++;
/* 220:    */      }
/* 221:    */    }
/* 222:    */    
/* 223:223 */    a(this.jdField_a_of_type_YA);
/* 224:224 */    this.jdField_a_of_type_YA.c();
/* 225:225 */    this.jdField_a_of_type_YA.g = true;
/* 226:226 */    this.g = true;
/* 227:    */    
/* 228:228 */    this.jdField_a_of_type_Boolean = true;
/* 229:    */  }
/* 230:    */  
/* 232:    */  public void update(Observable paramObservable, Object paramObject)
/* 233:    */  {
/* 234:234 */    this.jdField_a_of_type_YA.f();
/* 235:    */  }
/* 236:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     is
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */