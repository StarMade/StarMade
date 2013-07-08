/*   1:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.HashSet;
/*   5:    */import java.util.Iterator;
/*   6:    */import org.lwjgl.input.Keyboard;
/*   7:    */
/*  22:    */public final class aq
/*  23:    */  extends U
/*  24:    */{
/*  25:    */  public ar a;
/*  26:    */  public an a;
/*  27:    */  public bz a;
/*  28:    */  public bm a;
/*  29:    */  public bg a;
/*  30:    */  public aa a;
/*  31:    */  public V a;
/*  32:    */  public aO a;
/*  33:    */  public aC a;
/*  34:    */  private final ObjectArrayList a;
/*  35:    */  
/*  36:    */  public aq(ct paramct)
/*  37:    */  {
/*  38: 38 */    super(paramct);this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList = new ObjectArrayList();
/*  39: 39 */    paramct = this;this.jdField_a_of_type_Ar = new ar(paramct.a());paramct.jdField_a_of_type_An = new an(paramct.a());paramct.jdField_a_of_type_Bz = new bz(paramct.a());paramct.jdField_a_of_type_Bm = new bm(paramct.a());paramct.jdField_a_of_type_Bg = new bg(paramct.a());paramct.jdField_a_of_type_Aa = new aa(paramct.a());paramct.jdField_a_of_type_V = new V(paramct.a());paramct.jdField_a_of_type_AO = new aO(paramct.a());paramct.jdField_a_of_type_AC = new aC(paramct.a());paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Ar);paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_An);paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Bz);paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Bm);paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Bg);paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_V);paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_Aa);paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_AO);paramct.jdField_a_of_type_JavaUtilHashSet.add(paramct.jdField_a_of_type_AC);paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_An);paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_Bz);paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_Bm);paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_Bg);paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_V);paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_Aa);paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_AO);paramct.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.add(paramct.jdField_a_of_type_AC);
/*  40:    */  }
/*  41:    */  
/*  42:    */  public final void a(le paramle)
/*  43:    */  {
/*  44: 44 */    if (a().a() != null) {
/*  45: 45 */      if (a().a().a().a() != null) {
/*  46: 46 */        this.jdField_a_of_type_V.a(a().a().a().a());
/*  47:    */      } else {
/*  48: 48 */        this.jdField_a_of_type_V.a(null);
/*  49:    */        
/*  50: 50 */        a().a().b("No AI context available\nEither use this in a ship\nwith an AI Module or\nactivate an AI Module externally");
/*  51:    */      }
/*  52:    */      
/*  54:    */    }
/*  55:    */    else
/*  56:    */    {
/*  57: 57 */      this.jdField_a_of_type_V.a(paramle);
/*  58:    */    }
/*  59:    */    
/*  60: 60 */    if (this.jdField_a_of_type_An.b) {
/*  61: 61 */      this.jdField_a_of_type_An.c(false);
/*  62:    */    }
/*  63: 63 */    if (this.jdField_a_of_type_AO.b) {
/*  64: 64 */      this.jdField_a_of_type_AO.c(false);
/*  65:    */    }
/*  66: 66 */    if (this.jdField_a_of_type_AC.b) {
/*  67: 67 */      this.jdField_a_of_type_AC.c(false);
/*  68:    */    }
/*  69: 69 */    if (this.jdField_a_of_type_Bz.b) {
/*  70: 70 */      this.jdField_a_of_type_Bz.c(false);
/*  71:    */    }
/*  72: 72 */    if (this.jdField_a_of_type_Bm.b) {
/*  73: 73 */      this.jdField_a_of_type_Bm.c(false);
/*  74:    */    }
/*  75: 75 */    if (this.jdField_a_of_type_Bg.b) {
/*  76: 76 */      this.jdField_a_of_type_Bg.c(false);
/*  77:    */    }
/*  78:    */    
/*  80: 80 */    paramle = !this.jdField_a_of_type_V.b ? 1 : 0;
/*  81: 81 */    this.jdField_a_of_type_V.d(paramle);
/*  82:    */  }
/*  83:    */  
/*  86:    */  public final V a()
/*  87:    */  {
/*  88: 88 */    return this.jdField_a_of_type_V;
/*  89:    */  }
/*  90:    */  
/*  93:    */  public final an a()
/*  94:    */  {
/*  95: 95 */    return this.jdField_a_of_type_An;
/*  96:    */  }
/*  97:    */  
/*  99:    */  public final bg a()
/* 100:    */  {
/* 101:101 */    return this.jdField_a_of_type_Bg;
/* 102:    */  }
/* 103:    */  
/* 105:    */  public final ar a()
/* 106:    */  {
/* 107:107 */    return this.jdField_a_of_type_Ar;
/* 108:    */  }
/* 109:    */  
/* 111:    */  public final bz a()
/* 112:    */  {
/* 113:113 */    return this.jdField_a_of_type_Bz;
/* 114:    */  }
/* 115:    */  
/* 118:    */  public final bm a()
/* 119:    */  {
/* 120:120 */    return this.jdField_a_of_type_Bm;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public final void b()
/* 124:    */  {
/* 125:125 */    for (Iterator localIterator = this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList.iterator(); localIterator.hasNext();) {
/* 126:126 */      ((U)localIterator.next()).c(false);
/* 127:    */    }
/* 128:    */    
/* 129:129 */    this.jdField_a_of_type_Ar.a(600);
/* 130:    */  }
/* 131:    */  
/* 132:    */  public final void handleKeyEvent()
/* 133:    */  {
/* 134:134 */    if (Keyboard.getEventKeyState())
/* 135:    */    {
/* 138:138 */      if (a().b().isEmpty()) {
/* 139:139 */        if ((this.jdField_a_of_type_An.b) && (Keyboard.getEventKey() == cv.w.a()))
/* 140:    */        {
/* 141:141 */          a(null);
/* 142:    */        }
/* 143:143 */        if (Keyboard.getEventKey() == cv.H.a())
/* 144:    */        {
/* 145:145 */          a(null);
/* 146:    */        }
/* 147:147 */        else if (Keyboard.getEventKey() == cv.G.a())
/* 148:    */        {
/* 150:150 */          aq localaq = this;boolean bool2 = !this.jdField_a_of_type_Bz.b; if (!localaq.a().d()) { localaq.a().a().d("ERROR: You are not near any shop"); } else { if (localaq.jdField_a_of_type_An.b) localaq.jdField_a_of_type_An.c(false); if (localaq.jdField_a_of_type_Aa.b) localaq.jdField_a_of_type_Aa.c(false); if (localaq.jdField_a_of_type_AO.b) localaq.jdField_a_of_type_AO.c(false); if (localaq.jdField_a_of_type_Bm.b) localaq.jdField_a_of_type_Bm.c(false); if (localaq.jdField_a_of_type_Bg.b) localaq.jdField_a_of_type_Bg.c(false); if (localaq.jdField_a_of_type_AC.b) localaq.jdField_a_of_type_AC.c(false); if (localaq.jdField_a_of_type_V.b) localaq.jdField_a_of_type_V.c(false); localaq.jdField_a_of_type_Bz.d(bool2);
/* 151:151 */          } } else if (Keyboard.getEventKey() == cv.I.a())
/* 152:    */        {
/* 153:153 */          if (!this.jdField_a_of_type_Ar.a().b)
/* 154:    */          {
/* 155:155 */            a().a().b("ERROR: Weapon Menu only available\ninside ship");
/* 156:    */            
/* 160:160 */            return;
/* 161:    */          }
/* 162:162 */          if (this.jdField_a_of_type_AO.b) {
/* 163:163 */            this.jdField_a_of_type_AO.c(false);
/* 164:    */          }
/* 165:165 */          if (this.jdField_a_of_type_AC.b) {
/* 166:166 */            this.jdField_a_of_type_AC.c(false);
/* 167:    */          }
/* 168:168 */          if (this.jdField_a_of_type_An.b) {
/* 169:169 */            this.jdField_a_of_type_An.c(false);
/* 170:    */          }
/* 171:171 */          if (this.jdField_a_of_type_Bz.b) {
/* 172:172 */            this.jdField_a_of_type_Bz.c(false);
/* 173:    */          }
/* 174:174 */          if (this.jdField_a_of_type_Aa.b) {
/* 175:175 */            this.jdField_a_of_type_Aa.c(false);
/* 176:    */          }
/* 177:177 */          if (this.jdField_a_of_type_Bg.b) {
/* 178:178 */            this.jdField_a_of_type_Bg.c(false);
/* 179:    */          }
/* 180:180 */          if (this.jdField_a_of_type_V.b) {
/* 181:181 */            this.jdField_a_of_type_V.c(false);
/* 182:    */          }
/* 183:183 */          bool1 = !this.jdField_a_of_type_Bm.b;
/* 184:184 */          this.jdField_a_of_type_Bm.d(bool1);
/* 185:    */        }
/* 186:186 */        else if (Keyboard.getEventKey() == cv.J.a())
/* 187:    */        {
/* 191:191 */          if (this.jdField_a_of_type_An.b) {
/* 192:192 */            this.jdField_a_of_type_An.c(false);
/* 193:    */          }
/* 194:194 */          if (this.jdField_a_of_type_Bz.b) {
/* 195:195 */            this.jdField_a_of_type_Bz.c(false);
/* 196:    */          }
/* 197:197 */          if (this.jdField_a_of_type_AO.b) {
/* 198:198 */            this.jdField_a_of_type_AO.c(false);
/* 199:    */          }
/* 200:200 */          if (this.jdField_a_of_type_AC.b) {
/* 201:201 */            this.jdField_a_of_type_AC.c(false);
/* 202:    */          }
/* 203:203 */          if (this.jdField_a_of_type_Bm.b) {
/* 204:204 */            this.jdField_a_of_type_Bm.c(false);
/* 205:    */          }
/* 206:206 */          if (this.jdField_a_of_type_Aa.b) {
/* 207:207 */            this.jdField_a_of_type_Aa.c(false);
/* 208:    */          }
/* 209:209 */          if (this.jdField_a_of_type_V.b) {
/* 210:210 */            this.jdField_a_of_type_V.c(false);
/* 211:    */          }
/* 212:212 */          bool1 = !this.jdField_a_of_type_Bg.b;
/* 213:213 */          this.jdField_a_of_type_Bg.d(bool1);
/* 214:    */        }
/* 215:215 */        else if (Keyboard.getEventKey() == cv.Z.a())
/* 216:    */        {
/* 219:219 */          if (this.jdField_a_of_type_An.b) {
/* 220:220 */            this.jdField_a_of_type_An.c(false);
/* 221:    */          }
/* 222:222 */          if (this.jdField_a_of_type_Bz.b) {
/* 223:223 */            this.jdField_a_of_type_Bz.c(false);
/* 224:    */          }
/* 225:225 */          if (this.jdField_a_of_type_AO.b) {
/* 226:226 */            this.jdField_a_of_type_AO.c(false);
/* 227:    */          }
/* 228:228 */          if (this.jdField_a_of_type_AC.b) {
/* 229:229 */            this.jdField_a_of_type_AC.c(false);
/* 230:    */          }
/* 231:231 */          if (this.jdField_a_of_type_Bm.b) {
/* 232:232 */            this.jdField_a_of_type_Bm.c(false);
/* 233:    */          }
/* 234:234 */          if (this.jdField_a_of_type_Bg.b) {
/* 235:235 */            this.jdField_a_of_type_Bg.c(false);
/* 236:    */          }
/* 237:237 */          if (this.jdField_a_of_type_V.b) {
/* 238:238 */            this.jdField_a_of_type_V.c(false);
/* 239:    */          }
/* 240:    */          
/* 241:241 */          bool1 = !this.jdField_a_of_type_Aa.b;
/* 242:242 */          System.err.println("ACTIVATE MAP " + bool1);
/* 243:243 */          this.jdField_a_of_type_Aa.d(bool1);
/* 245:    */        }
/* 246:246 */        else if (Keyboard.getEventKey() == cv.K.a())
/* 247:    */        {
/* 249:249 */          a(null);
/* 250:250 */        } else if (Keyboard.getEventKey() == cv.Y.a()) {
/* 251:251 */          if (this.jdField_a_of_type_An.b) {
/* 252:252 */            this.jdField_a_of_type_An.c(false);
/* 253:    */          }
/* 254:254 */          if (this.jdField_a_of_type_Bz.b) {
/* 255:255 */            this.jdField_a_of_type_Bz.c(false);
/* 256:    */          }
/* 257:257 */          if (this.jdField_a_of_type_Bg.b) {
/* 258:258 */            this.jdField_a_of_type_Bg.c(false);
/* 259:    */          }
/* 260:260 */          if (this.jdField_a_of_type_AC.b) {
/* 261:261 */            this.jdField_a_of_type_AC.c(false);
/* 262:    */          }
/* 263:263 */          if (this.jdField_a_of_type_Aa.b) {
/* 264:264 */            this.jdField_a_of_type_Aa.c(false);
/* 265:    */          }
/* 266:266 */          if (this.jdField_a_of_type_Bm.b) {
/* 267:267 */            this.jdField_a_of_type_Bm.c(false);
/* 268:    */          }
/* 269:269 */          if (this.jdField_a_of_type_V.b) {
/* 270:270 */            this.jdField_a_of_type_V.c(false);
/* 271:    */          }
/* 272:272 */          bool1 = !this.jdField_a_of_type_AO.b;
/* 273:273 */          this.jdField_a_of_type_AO.d(bool1);
/* 274:    */        }
/* 275:    */      }
/* 276:    */    }
/* 277:    */    
/* 278:278 */    boolean bool1 = false;
/* 279:279 */    for (U localU : this.jdField_a_of_type_ItUnimiDsiFastutilObjectsObjectArrayList) {
/* 280:280 */      bool1 = (bool1) || (localU.b);
/* 281:    */    }
/* 282:282 */    if (this.jdField_a_of_type_Ar.jdField_a_of_type_Boolean != bool1)
/* 283:    */    {
/* 284:284 */      this.jdField_a_of_type_Ar.e(bool1);
/* 285:    */    }
/* 286:286 */    if (a().b().isEmpty()) {
/* 287:287 */      super.handleKeyEvent();
/* 288:    */    }
/* 289:    */  }
/* 290:    */  
/* 322:    */  public final void a(mf parammf)
/* 323:    */  {
/* 324:324 */    if (this.jdField_a_of_type_Bz.b) {
/* 325:325 */      this.jdField_a_of_type_Bz.c(false);
/* 326:    */    }
/* 327:327 */    if (this.jdField_a_of_type_Bm.b) {
/* 328:328 */      this.jdField_a_of_type_Bm.c(false);
/* 329:    */    }
/* 330:330 */    if (this.jdField_a_of_type_V.b) {
/* 331:331 */      this.jdField_a_of_type_V.c(false);
/* 332:    */    }
/* 333:333 */    if (this.jdField_a_of_type_Bg.b) {
/* 334:334 */      this.jdField_a_of_type_Bg.c(false);
/* 335:    */    }
/* 336:336 */    if (this.jdField_a_of_type_AO.b) {
/* 337:337 */      this.jdField_a_of_type_AO.c(false);
/* 338:    */    }
/* 339:339 */    if (this.jdField_a_of_type_Aa.b) {
/* 340:340 */      this.jdField_a_of_type_Aa.c(false);
/* 341:    */    }
/* 342:342 */    if (this.jdField_a_of_type_AC.b) {
/* 343:343 */      this.jdField_a_of_type_AC.c(false);
/* 344:    */    }
/* 345:    */    boolean bool;
/* 346:346 */    if ((bool = !this.jdField_a_of_type_An.b ? 1 : 0) != 0) {
/* 347:347 */      mf localmf = parammf;parammf = this.jdField_a_of_type_An;System.err.println("SECOND INVENTORY SET TO " + localmf);parammf.a = localmf;
/* 348:    */    }
/* 349:    */    
/* 350:350 */    this.jdField_a_of_type_An.d(bool);
/* 351:    */  }
/* 352:    */  
/* 354:    */  public final void b(boolean paramBoolean)
/* 355:    */  {
/* 356:356 */    if (paramBoolean) {
/* 357:357 */      this.jdField_a_of_type_Bg.c(false);
/* 358:358 */      this.jdField_a_of_type_Bz.c(false);
/* 359:359 */      this.jdField_a_of_type_Bm.c(false);
/* 360:360 */      this.jdField_a_of_type_An.c(false);
/* 361:361 */      this.jdField_a_of_type_Aa.c(false);
/* 362:362 */      this.jdField_a_of_type_Ar.d(true);
/* 363:    */    }
/* 364:    */    else {
/* 365:365 */      this.jdField_a_of_type_An.c(false);
/* 366:366 */      this.jdField_a_of_type_Bg.c(false);
/* 367:367 */      this.jdField_a_of_type_Bz.c(false);
/* 368:368 */      this.jdField_a_of_type_Bm.c(false);
/* 369:369 */      this.jdField_a_of_type_Aa.c(false);
/* 370:    */    }
/* 371:371 */    super.b(paramBoolean);
/* 372:    */  }
/* 373:    */  
/* 413:    */  public final void a(xq paramxq)
/* 414:    */  {
/* 415:415 */    boolean bool = (this.jdField_a_of_type_An.b) || (this.jdField_a_of_type_Bz.b) || (this.jdField_a_of_type_Bm.b) || (this.jdField_a_of_type_AO.b) || (this.jdField_a_of_type_V.b) || (this.jdField_a_of_type_AC.b);
/* 416:    */    
/* 421:421 */    if (this.jdField_a_of_type_Ar.jdField_a_of_type_Boolean != bool)
/* 422:    */    {
/* 423:423 */      this.jdField_a_of_type_Ar.e(bool);
/* 424:    */    }
/* 425:    */    
/* 426:426 */    super.a(paramxq);
/* 427:    */  }
/* 428:    */  
/* 429:    */  public final aO a()
/* 430:    */  {
/* 431:431 */    return this.jdField_a_of_type_AO;
/* 432:    */  }
/* 433:    */  
/* 437:    */  public final aC a()
/* 438:    */  {
/* 439:439 */    return this.jdField_a_of_type_AC;
/* 440:    */  }
/* 441:    */  
/* 445:    */  public final aa a()
/* 446:    */  {
/* 447:447 */    return this.jdField_a_of_type_Aa;
/* 448:    */  }
/* 449:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     aq
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */