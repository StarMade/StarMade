/*   1:    */import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*   2:    */import java.io.PrintStream;
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.Arrays;
/*   5:    */import org.schema.game.common.controller.SegmentController;
/*   6:    */import org.schema.game.common.data.element.ElementInformation;
/*   7:    */import org.schema.game.common.data.element.ElementKeyMap;
/*   8:    */import org.schema.game.server.controller.GameServerController;
/*   9:    */import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
/*  10:    */import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*  11:    */import org.schema.schine.network.StateInterface;
/*  12:    */import org.schema.schine.network.objects.NetworkObject;
/*  13:    */import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
/*  14:    */import org.schema.schine.network.objects.remote.RemoteField;
/*  15:    */import org.schema.schine.network.objects.remote.RemoteString;
/*  16:    */import org.schema.schine.network.objects.remote.RemoteStringArray;
/*  17:    */
/*  42:    */public abstract class kp
/*  43:    */  implements Ak, wm
/*  44:    */{
/*  45:    */  private final ko[] jdField_a_of_type_ArrayOfKo;
/*  46:    */  final SegmentController jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController;
/*  47:    */  private StateInterface jdField_a_of_type_OrgSchemaSchineNetworkStateInterface;
/*  48:    */  private wt jdField_a_of_type_Wt;
/*  49:    */  wo jdField_a_of_type_Wo;
/*  50:    */  private le jdField_a_of_type_Le;
/*  51:    */  private long jdField_a_of_type_Long;
/*  52:    */  
/*  53:    */  public final boolean a()
/*  54:    */  {
/*  55: 55 */    return this.jdField_a_of_type_Wo.a();
/*  56:    */  }
/*  57:    */  
/*  78: 78 */  public final wo a() { return this.jdField_a_of_type_Wo; }
/*  79:    */  
/*  80:    */  protected abstract wo b();
/*  81:    */  
/*  82: 82 */  public kp(StateInterface paramStateInterface, SegmentController paramSegmentController) { this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
/*  83: 83 */    this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController = paramSegmentController;
/*  84:    */    
/*  85: 85 */    this.jdField_a_of_type_Wo = b();
/*  86:    */    
/*  87: 87 */    this.jdField_a_of_type_ArrayOfKo = new ko[kq.values().length];
/*  88:    */    
/*  89: 89 */    this.jdField_a_of_type_ArrayOfKo[kq.a.ordinal()] = new ko(kq.a, "Any", new xB(new String[] { "Any", "Selected Target" }), this);
/*  90:    */    
/*  91: 91 */    this.jdField_a_of_type_ArrayOfKo[kq.b.ordinal()] = new ko(kq.b, "Ship", new xB(new String[] { "Turret", "Ship" }), this);
/*  92:    */    
/*  95: 95 */    this.jdField_a_of_type_ArrayOfKo[kq.c.ordinal()] = new ko(kq.c, Boolean.valueOf(false), new xB(new Boolean[] { Boolean.valueOf(false), Boolean.valueOf(true) }), this);
/*  96:    */  }
/*  97:    */  
/*  98: 98 */  public final void a() { for (int i = 0; i < this.jdField_a_of_type_ArrayOfKo.length; i++) {
/*  99: 99 */      b(this.jdField_a_of_type_ArrayOfKo[i]);
/* 100:    */    }
/* 101:    */  }
/* 102:    */  
/* 114:    */  public final void a(ko paramko, boolean paramBoolean)
/* 115:    */  {
/* 116:116 */    if (paramBoolean) {
/* 117:117 */      c(paramko);
/* 118:    */    }
/* 119:    */  }
/* 120:    */  
/* 121:    */  public final ko a(kq paramkq)
/* 122:    */  {
/* 123:123 */    return this.jdField_a_of_type_ArrayOfKo[paramkq.ordinal()];
/* 124:    */  }
/* 125:    */  
/* 128:    */  public final le a()
/* 129:    */  {
/* 130:130 */    return this.jdField_a_of_type_Le;
/* 131:    */  }
/* 132:    */  
/* 135:    */  public final ko[] a()
/* 136:    */  {
/* 137:137 */    return this.jdField_a_of_type_ArrayOfKo;
/* 138:    */  }
/* 139:    */  
/* 167:    */  protected void a(ko paramko)
/* 168:    */  {
/* 169:169 */    if ((paramko.a() == kq.c) && (!paramko.a())) {
/* 170:170 */      System.err.println("[AI] SENTINEL SET TO FALSE ON " + this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface);
/* 171:171 */      this.jdField_a_of_type_Wo = b();
/* 172:    */    }
/* 173:    */  }
/* 174:    */  
/* 177:    */  public void a(lb paramlb)
/* 178:    */  {
/* 179:179 */    if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/* 180:180 */      this.jdField_a_of_type_ArrayOfKo[kq.c.ordinal()].a(Boolean.valueOf(false), this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer());
/* 181:181 */      a();
/* 182:    */    }
/* 183:    */  }
/* 184:    */  
/* 186:    */  public final void b(lb paramlb)
/* 187:    */  {
/* 188:188 */    if ((this.jdField_a_of_type_Wo.a()) && ((this.jdField_a_of_type_Wo instanceof kv))) {
/* 189:189 */      ((kv)this.jdField_a_of_type_Wo).a(paramlb);
/* 190:    */    }
/* 191:    */  }
/* 192:    */  
/* 199:    */  public void a(SegmentController paramSegmentController) {}
/* 200:    */  
/* 206:    */  protected void b(ko paramko)
/* 207:    */  {
/* 208:208 */    if (paramko.a() == kq.c) {
/* 209:209 */      if (paramko.a())
/* 210:    */      {
/* 211:211 */        b();
/* 212:    */      } else {
/* 213:213 */        ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().a(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/* 214:    */      }
/* 215:    */      
/* 216:216 */      if ((!jdField_a_of_type_Boolean) && (this.jdField_a_of_type_Wo == null)) throw new AssertionError();
/* 217:217 */      if (this.jdField_a_of_type_Wo.a != null) {
/* 218:218 */        this.jdField_a_of_type_Wo.a.b(!paramko.a());
/* 219:    */      }
/* 220:    */    }
/* 221:    */  }
/* 222:    */  
/* 223:    */  protected abstract void b();
/* 224:    */  
/* 225:    */  private void c(ko paramko)
/* 226:    */  {
/* 227:227 */    if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject() == null) {
/* 228:    */      return;
/* 229:    */    }
/* 230:    */    RemoteStringArray localRemoteStringArray;
/* 231:231 */    (localRemoteStringArray = new RemoteStringArray(2, this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject())).set(0, paramko.b());
/* 232:232 */    localRemoteStringArray.set(1, paramko.a().toString());
/* 233:    */    
/* 234:234 */    ((kr)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject()).getAiSettingsModification().add(localRemoteStringArray);
/* 235:    */  }
/* 236:    */  
/* 244:    */  public final void a(le paramle)
/* 245:    */  {
/* 246:246 */    this.jdField_a_of_type_Le = paramle;
/* 247:    */  }
/* 248:    */  
/* 262:    */  public final void c()
/* 263:    */  {
/* 264:264 */    ko[] arrayOfko = null; if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/* 265:    */    {
/* 266:266 */      for (ko localko : this.jdField_a_of_type_ArrayOfKo) {
/* 267:267 */        c(localko);
/* 268:    */      }
/* 269:    */    }
/* 270:    */  }
/* 271:    */  
/* 272:    */  public void fromTagStructure(Ah paramAh) {
/* 273:    */    Ah[] arrayOfAh;
/* 274:    */    int j;
/* 275:275 */    if (paramAh.a().equals("AIConfig0")) {
/* 276:276 */      paramAh = (Ah[])paramAh.a();
/* 277:277 */      for (i = 0; (i < paramAh.length) && (paramAh[i].a() != Aj.a); i++) {
/* 278:278 */        arrayOfAh = (Ah[])paramAh[i].a();
/* 279:279 */        for (j = 0; j < this.jdField_a_of_type_ArrayOfKo.length; j++)
/* 280:    */        {
/* 281:281 */          if ((this.jdField_a_of_type_ArrayOfKo[j] != null) && (((String)arrayOfAh[0].a()).equals(this.jdField_a_of_type_ArrayOfKo[j].a().name()))) {
/* 282:    */            try
/* 283:    */            {
/* 284:284 */              this.jdField_a_of_type_ArrayOfKo[j].a((String)arrayOfAh[1].a(), true);
/* 285:285 */              b(this.jdField_a_of_type_ArrayOfKo[j]);
/* 286:286 */            } catch (StateParameterNotFoundException localStateParameterNotFoundException1) { 
/* 287:    */              
/* 288:288 */                localStateParameterNotFoundException1;
/* 289:    */            }
/* 290:    */          }
/* 291:    */        }
/* 292:    */      }
/* 293:    */      
/* 294:292 */      return; }
/* 295:293 */    paramAh = (Ah[])paramAh.a();
/* 296:294 */    for (int i = 0; (i < paramAh.length) && (paramAh[i].a() != Aj.a); i++) {
/* 297:295 */      arrayOfAh = (Ah[])paramAh[i].a();
/* 298:296 */      for (j = 0; j < this.jdField_a_of_type_ArrayOfKo.length; j++) {
/* 299:297 */        if ((this.jdField_a_of_type_ArrayOfKo[j] != null) && (((String)arrayOfAh[0].a()).equals(this.jdField_a_of_type_ArrayOfKo[j].a().name()))) {
/* 300:    */          try
/* 301:    */          {
/* 302:300 */            this.jdField_a_of_type_ArrayOfKo[j].a((String)arrayOfAh[1].a(), true);
/* 303:301 */            b(this.jdField_a_of_type_ArrayOfKo[j]);
/* 304:302 */          } catch (StateParameterNotFoundException localStateParameterNotFoundException2) { 
/* 305:    */            
/* 306:304 */              localStateParameterNotFoundException2;
/* 307:    */          }
/* 308:    */        }
/* 309:    */      }
/* 310:    */    }
/* 311:    */  }
/* 312:    */  
/* 316:    */  public Ah toTagStructure()
/* 317:    */  {
/* 318:314 */    new ArrayList();
/* 319:315 */    Ah[] arrayOfAh = new Ah[this.jdField_a_of_type_ArrayOfKo.length + 1];
/* 320:316 */    for (int i = 0; i < this.jdField_a_of_type_ArrayOfKo.length; i++)
/* 321:    */    {
/* 322:318 */      ko localko = this.jdField_a_of_type_ArrayOfKo[i];
/* 323:319 */      if ((!jdField_a_of_type_Boolean) && (localko == null)) throw new AssertionError(i + " / " + Arrays.toString(this.jdField_a_of_type_ArrayOfKo));
/* 324:320 */      arrayOfAh[i] = localko.toTagStructure();
/* 325:    */    }
/* 326:    */    
/* 328:324 */    arrayOfAh[this.jdField_a_of_type_ArrayOfKo.length] = new Ah(Aj.a, null, null);
/* 329:325 */    return new Ah(Aj.n, "AIConfig0", arrayOfAh);
/* 330:    */  }
/* 331:    */  
/* 333:    */  public final void a(xq paramxq)
/* 334:    */  {
/* 335:331 */    if (this.jdField_a_of_type_Wo.a()) {
/* 336:332 */      if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer())
/* 337:    */        try {
/* 338:334 */          if ((!this.jdField_a_of_type_Wo.a()) && (!(this.jdField_a_of_type_Wt instanceof sN))) {
/* 339:335 */            this.jdField_a_of_type_Wo.a().a(new tx());
/* 340:    */          }
/* 341:337 */          this.jdField_a_of_type_Wo.a.a(paramxq); return;
/* 342:    */        } catch (Exception localException1) {
/* 343:339 */          (paramxq = 
/* 344:    */          
/* 351:347 */            localException1).printStackTrace();System.err.println("Exception: Error occured updating AI " + paramxq.getMessage() + ": Current Program: " + this.jdField_a_of_type_Wo.a + "; state: " + this.jdField_a_of_type_Wo.a() + "; in " + this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController);
/* 352:    */          
/* 353:342 */          if (System.currentTimeMillis() - this.jdField_a_of_type_Long > 1000L) {
/* 354:343 */            ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().broadcastMessage("AI Error occured on Server!\nPlease send in server error report.\n" + paramxq.getClass().getSimpleName(), 3);
/* 355:344 */            this.jdField_a_of_type_Long = System.currentTimeMillis();
/* 356:    */          }
/* 357:    */          
/* 358:347 */          return;
/* 359:    */        }
/* 360:    */      try {
/* 361:350 */        this.jdField_a_of_type_Wo.a(paramxq);
/* 362:    */      } catch (Exception localException2) {
/* 363:352 */        (paramxq = 
/* 364:    */        
/* 365:354 */          localException2).printStackTrace();throw new ErrorDialogException(paramxq.getMessage());
/* 366:    */      }
/* 367:355 */      if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isClientOwnObject()) {
/* 368:    */        try {
/* 369:357 */          a(kq.c).a();
/* 370:    */          
/* 371:359 */          ((ct)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a().b("WARNING\nThis vessel was AI controlled\n... switched off AI\nre-enable AI with " + ElementKeyMap.getInfo((short)121).getName()); return;
/* 373:    */        }
/* 374:    */        catch (StateParameterNotFoundException localStateParameterNotFoundException)
/* 375:    */        {
/* 376:364 */          (paramxq = 
/* 377:    */          
/* 378:366 */            localStateParameterNotFoundException).printStackTrace();throw new ErrorDialogException(paramxq.toString());
/* 379:    */        }
/* 380:    */      }
/* 381:    */    }
/* 382:    */  }
/* 383:    */  
/* 387:    */  public final void d()
/* 388:    */  {
/* 389:376 */    ObjectArrayList localObjectArrayList = ((kr)this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.getNetworkObject()).getAiSettingsModification().getReceiveBuffer();
/* 390:377 */    for (int i = 0; i < localObjectArrayList.size(); i++)
/* 391:    */    {
/* 392:379 */      RemoteStringArray localRemoteStringArray = (RemoteStringArray)localObjectArrayList.get(i);
/* 393:380 */      ko[] arrayOfko; int j = (arrayOfko = this.jdField_a_of_type_ArrayOfKo).length; for (int k = 0; k < j; k++) { ko localko;
/* 394:    */        boolean bool;
/* 395:382 */        if (((bool = (localko = arrayOfko[k]).b().equals(localRemoteStringArray.get(0).get()))) && (!localko.a().toString().equals(localRemoteStringArray.get(1).get()))) {
/* 396:    */          try
/* 397:    */          {
/* 398:385 */            localko.a((String)localRemoteStringArray.get(1).get(), this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer());
/* 399:386 */            if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/* 400:387 */              b(localko);
/* 401:    */            } else
/* 402:389 */              a(localko);
/* 403:    */          } catch (StateParameterNotFoundException localStateParameterNotFoundException) {
/* 404:391 */            
/* 405:    */            
/* 406:393 */              localStateParameterNotFoundException;
/* 407:    */          }
/* 408:    */          
/* 409:394 */        } else if ((!this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) && (bool))
/* 410:    */        {
/* 413:398 */          a(localko);
/* 414:    */        }
/* 415:    */      }
/* 416:    */    }
/* 417:    */  }
/* 418:    */  
/* 419:    */  public final void a(NetworkObject paramNetworkObject)
/* 420:    */  {
/* 421:406 */    paramNetworkObject = (kr)paramNetworkObject;
/* 422:    */    
/* 423:408 */    if (this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController.isOnServer()) {
/* 424:409 */      if (vo.m.a())
/* 425:    */      {
/* 427:412 */        String str = "";
/* 428:413 */        if (this.jdField_a_of_type_Wo.a()) {
/* 429:414 */          str = "\nTar:" + ((sL)this.jdField_a_of_type_Wo.a).a();
/* 430:    */          
/* 431:416 */          this.jdField_a_of_type_Wt = this.jdField_a_of_type_Wo.a();
/* 432:417 */          ((sL)this.jdField_a_of_type_Wo.a).a();
/* 433:    */        }
/* 434:    */        
/* 435:420 */        vR localvR = ((vg)this.jdField_a_of_type_OrgSchemaSchineNetworkStateInterface).a();
/* 436:421 */        paramNetworkObject.getDebugState().set("(" + this.jdField_a_of_type_Wo.toString() + "[" + (this.jdField_a_of_type_Wo.a() ? "ON" : "OFF") + "])" + str + ";\n" + localvR.a(this.jdField_a_of_type_OrgSchemaGameCommonControllerSegmentController));
/* 437:    */        
/* 449:434 */        return; }
/* 450:435 */      if (((String)paramNetworkObject.getDebugState().get()).length() > 0) {
/* 451:436 */        paramNetworkObject.getDebugState().set("");
/* 452:    */      }
/* 453:    */    }
/* 454:    */  }
/* 455:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     kp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */