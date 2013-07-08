/*   1:    */package paulscode.sound.utils;
/*   2:    */
/*   3:    */import java.net.URL;
/*   4:    */import paulscode.sound.CommandObject;
/*   5:    */import paulscode.sound.FilenameURL;
/*   6:    */import paulscode.sound.SoundSystem;
/*   7:    */import paulscode.sound.SoundSystemConfig;
/*   8:    */import paulscode.sound.SoundSystemException;
/*   9:    */import paulscode.sound.SoundSystemLogger;
/*  10:    */
/*  53:    */public class SoundSystemLoader
/*  54:    */{
/*  55:    */  private static SoundSystemLogger logger;
/*  56: 56 */  private static boolean verbose = false;
/*  57:    */  
/* 111:    */  public static SoundSystem loadXML(URL xmlFile, SoundSystem s)
/* 112:    */  {
/* 113:113 */    if (xmlFile == null)
/* 114:    */    {
/* 115:115 */      errorMessage("Parameter 'xmlFile' null in method 'loadXML'");
/* 116:116 */      return null;
/* 117:    */    }
/* 118:    */    
/* 120:120 */    XMLNode commands = XMLParser.parseXML(xmlFile);
/* 121:    */    
/* 123:123 */    if (commands == null)
/* 124:    */    {
/* 125:125 */      warningMessage("No commands found in XML file");
/* 126:126 */      return null;
/* 127:    */    }
/* 128:    */    
/* 130:130 */    Class c = null;
/* 131:    */    
/* 133:133 */    while (commands != null)
/* 134:    */    {
/* 136:136 */      String command = commands.name().toUpperCase();
/* 137:    */      
/* 139:139 */      if ((command != null) && (!command.substring(0, 1).equals("/")))
/* 140:    */      {
/* 142:142 */        if (command.equals("ADDLIBRARY"))
/* 143:    */        {
/* 145:145 */          if (verbose)
/* 146:    */          {
/* 147:147 */            message("SoundSystemLoader.loadXML:  addLibrary", 0);
/* 148:148 */            message("Class name:  " + commands.get("CLASSNAME"), 1);
/* 149:    */          }
/* 150:    */          
/* 152:    */          try
/* 153:    */          {
/* 154:154 */            c = Class.forName(commands.get("CLASSNAME"));
/* 155:155 */            if (verbose) {
/* 156:156 */              message("Command:  SoundSystemConfig.addLibrary( " + commands.get("CLASSNAME") + ".class );", 1);
/* 157:    */            }
/* 158:    */            
/* 160:160 */            SoundSystemConfig.addLibrary(c);
/* 161:    */          }
/* 162:    */          catch (ClassNotFoundException cnfe)
/* 163:    */          {
/* 164:164 */            errorMessage("Unable to add library plug-in in method 'loadXML':  Class name '" + commands.get("CLASSNAME") + "' not found.");
/* 167:    */          }
/* 168:    */          catch (SoundSystemException sse)
/* 169:    */          {
/* 171:171 */            printStackTrace(sse);
/* 172:    */          }
/* 173:    */        }
/* 174:174 */        else if (command.equals("SETCODEC"))
/* 175:    */        {
/* 177:177 */          if (verbose)
/* 178:    */          {
/* 179:179 */            message("SoundSystemLoader.loadXML:  setCodec", 0);
/* 180:180 */            message("Extension: " + commands.get("EXTENSION"), 1);
/* 181:    */            
/* 182:182 */            message("Class name: " + commands.get("CLASSNAME"), 1);
/* 183:    */          }
/* 184:    */          
/* 186:    */          try
/* 187:    */          {
/* 188:188 */            c = Class.forName(commands.get("CLASSNAME"));
/* 189:189 */            if (verbose) {
/* 190:190 */              message("Command:  SoundSystemConfig.setCodec( \"" + commands.get("EXTENSION") + "\", " + commands.get("CLASSNAME") + ".class );", 1);
/* 191:    */            }
/* 192:    */            
/* 195:195 */            SoundSystemConfig.setCodec(commands.get("EXTENSION"), c);
/* 197:    */          }
/* 198:    */          catch (ClassNotFoundException cnfe)
/* 199:    */          {
/* 200:200 */            errorMessage("Unable to set codec plug-in for extension '" + commands.get("EXTENSION") + "' in method 'loadXML':  Class name '" + commands.get("CLASSNAME") + "' not found.");
/* 204:    */          }
/* 205:    */          catch (SoundSystemException sse)
/* 206:    */          {
/* 209:209 */            printStackTrace(sse);
/* 210:    */          }
/* 211:    */        }
/* 212:212 */        else if (command.equals("CREATE"))
/* 213:    */        {
/* 215:215 */          if (verbose)
/* 216:216 */            message("SoundSystemLoader.loadXML:  create", 0);
/* 217:217 */          if (s != null)
/* 218:218 */            s.cleanup();
/* 219:219 */          s = null;
/* 220:220 */          String parameter = commands.get("CLASSNAME");
/* 221:221 */          if ((parameter != null) && (!parameter.equals("")))
/* 222:    */          {
/* 223:    */            try
/* 224:    */            {
/* 225:225 */              c = Class.forName(parameter);
/* 226:226 */              if (verbose)
/* 227:    */              {
/* 228:228 */                message("Command:  s = (SoundSystem) new " + parameter + "();", 1);
/* 229:    */                
/* 230:230 */                s = (SoundSystem)c.newInstance();
/* 231:    */              }
/* 232:    */            }
/* 233:    */            catch (ClassNotFoundException cnfe)
/* 234:    */            {
/* 235:235 */              printStackTrace(cnfe);
/* 236:    */            }
/* 237:    */            catch (InstantiationException ie)
/* 238:    */            {
/* 239:239 */              printStackTrace(ie);
/* 240:    */            }
/* 241:    */            catch (IllegalAccessException iae)
/* 242:    */            {
/* 243:243 */              printStackTrace(iae);
/* 244:    */            }
/* 245:245 */            if (verbose)
/* 246:    */            {
/* 247:247 */              message("Unable to instantiate the Sound System in method 'loadXML'  Returning null.", 1);
/* 248:    */              
/* 249:249 */              if (s != null)
/* 250:250 */                s.cleanup();
/* 251:251 */              s = null;
/* 252:252 */              return null;
/* 253:    */            }
/* 254:    */          }
/* 255:    */          else
/* 256:    */          {
/* 257:257 */            if (verbose)
/* 258:258 */              message("Command:  s = new SoundSystem();", 1);
/* 259:259 */            s = new SoundSystem();
/* 260:    */          }
/* 261:    */        }
/* 262:262 */        else if (command.equals("LOADSOUND"))
/* 263:    */        {
/* 265:265 */          if (verbose)
/* 266:    */          {
/* 267:267 */            message("SoundSystemLoader.loadXML:  loadSound", 0);
/* 268:268 */            message("Filename: " + commands.get("FILENAME"), 1);
/* 269:    */          }
/* 270:    */          
/* 271:271 */          if (s == null)
/* 272:    */          {
/* 273:273 */            errorMessage("Encountered 'loadSound' command before SoundSystem was instantiated in method 'loadXML'.  returning null.");
/* 274:    */            
/* 276:276 */            return null;
/* 277:    */          }
/* 278:278 */          if (verbose) {
/* 279:279 */            message("Command:  s.loadSound( \"" + commands.get("FILENAME") + "\" );", 1);
/* 280:    */          }
/* 281:    */          
/* 282:282 */          s.loadSound(commands.get("FILENAME"));
/* 283:    */        }
/* 284:284 */        else if (command.equals("NEWSOURCE"))
/* 285:    */        {
/* 287:287 */          if (verbose) {
/* 288:288 */            message("SoundSystemLoader.loadXML:  newSource", 0);
/* 289:    */          }
/* 290:    */          
/* 291:291 */          if (s == null)
/* 292:    */          {
/* 293:293 */            errorMessage("Encountered 'newSource' command before SoundSystem was instantiated in method 'loadXML'.  returning null.");
/* 294:    */            
/* 296:296 */            return null;
/* 297:    */          }
/* 298:298 */          boolean priority = false;
/* 299:299 */          boolean toStream = false;
/* 300:300 */          boolean toLoop = false;
/* 301:301 */          String sourcename = "";
/* 302:302 */          String filename = "";
/* 303:303 */          float x = 0.0F;
/* 304:304 */          float y = 0.0F;
/* 305:305 */          float z = 0.0F;
/* 306:306 */          int attModel = SoundSystemConfig.getDefaultAttenuation();
/* 307:307 */          float distOrRoll = 0.0F;
/* 308:308 */          String parameter = commands.get("PRIORITY");
/* 309:309 */          if ((parameter != null) && (!parameter.equals("")))
/* 310:    */          {
/* 311:311 */            if (verbose)
/* 312:312 */              message("PRIORITY: " + parameter, 1);
/* 313:313 */            if (parameter.toUpperCase().equals("TRUE"))
/* 314:314 */              priority = true;
/* 315:    */          }
/* 316:316 */          parameter = commands.get("TOSTREAM");
/* 317:317 */          if ((parameter != null) && (!parameter.equals("")))
/* 318:    */          {
/* 319:319 */            if (verbose)
/* 320:320 */              message("TOSTREAM: " + parameter, 1);
/* 321:321 */            if (parameter.toUpperCase().equals("TRUE"))
/* 322:322 */              toStream = true;
/* 323:    */          }
/* 324:324 */          parameter = commands.get("TOLOOP");
/* 325:325 */          if ((parameter != null) && (!parameter.equals("")))
/* 326:    */          {
/* 327:327 */            if (verbose)
/* 328:328 */              message("TOLOOP: " + parameter, 1);
/* 329:329 */            if (parameter.toUpperCase().equals("TRUE"))
/* 330:330 */              toLoop = true;
/* 331:    */          }
/* 332:332 */          parameter = commands.get("SOURCENAME");
/* 333:333 */          if ((parameter != null) && (!parameter.equals("")))
/* 334:    */          {
/* 335:335 */            if (verbose)
/* 336:336 */              message("SOURCENAME: " + parameter, 1);
/* 337:337 */            sourcename = parameter;
/* 338:    */          }
/* 339:339 */          parameter = commands.get("FILENAME");
/* 340:340 */          if ((parameter != null) && (!parameter.equals("")))
/* 341:    */          {
/* 342:342 */            if (verbose)
/* 343:343 */              message("FILENAME: " + parameter, 1);
/* 344:344 */            filename = parameter;
/* 345:    */          }
/* 346:346 */          parameter = commands.get("X");
/* 347:347 */          if ((parameter != null) && (!parameter.equals("")))
/* 348:    */          {
/* 349:349 */            if (verbose) {
/* 350:350 */              message("X: " + parameter, 1);
/* 351:    */            }
/* 352:    */            try {
/* 353:353 */              x = Float.parseFloat(parameter);
/* 354:    */            }
/* 355:    */            catch (NumberFormatException nfe)
/* 356:    */            {
/* 357:357 */              errorMessage("Error parsing float 'X' from String '" + parameter + "' in " + "method 'loadXML'.  Using x=0");
/* 358:    */              
/* 360:360 */              x = 0.0F;
/* 361:    */            }
/* 362:    */          }
/* 363:363 */          parameter = commands.get("Y");
/* 364:364 */          if ((parameter != null) && (!parameter.equals("")))
/* 365:    */          {
/* 366:366 */            if (verbose) {
/* 367:367 */              message("Y: " + parameter, 1);
/* 368:    */            }
/* 369:    */            try {
/* 370:370 */              y = Float.parseFloat(parameter);
/* 371:    */            }
/* 372:    */            catch (NumberFormatException nfe)
/* 373:    */            {
/* 374:374 */              errorMessage("Error parsing float 'Y' from String '" + parameter + "' in " + "method 'loadXML'.  Using y=0");
/* 375:    */              
/* 377:377 */              y = 0.0F;
/* 378:    */            }
/* 379:    */          }
/* 380:380 */          parameter = commands.get("Z");
/* 381:381 */          if ((parameter != null) && (!parameter.equals("")))
/* 382:    */          {
/* 383:383 */            if (verbose) {
/* 384:384 */              message("Z: " + parameter, 1);
/* 385:    */            }
/* 386:    */            try {
/* 387:387 */              x = Float.parseFloat(parameter);
/* 388:    */            }
/* 389:    */            catch (NumberFormatException nfe)
/* 390:    */            {
/* 391:391 */              errorMessage("Error parsing float 'Z' from String '" + parameter + "' in " + "method 'loadXML'.  Using z=0");
/* 392:    */              
/* 394:394 */              z = 0.0F;
/* 395:    */            }
/* 396:    */          }
/* 397:397 */          parameter = commands.get("ATTMODEL");
/* 398:398 */          if ((parameter != null) && (!parameter.equals("")))
/* 399:    */          {
/* 400:400 */            if (verbose)
/* 401:401 */              message("ATTMODEL: " + parameter, 1);
/* 402:402 */            if (parameter.toUpperCase().contains("NONE")) {
/* 403:403 */              attModel = 0;
/* 404:404 */            } else if (parameter.toUpperCase().contains("LINEAR")) {
/* 405:405 */              attModel = 2;
/* 406:406 */            } else if (parameter.toUpperCase().contains("ROLLOFF"))
/* 407:407 */              attModel = 1;
/* 408:    */          }
/* 409:409 */          parameter = commands.get("DISTORROLL");
/* 410:410 */          if ((parameter != null) && (!parameter.equals("")))
/* 411:    */          {
/* 412:412 */            if (verbose) {
/* 413:413 */              message("DISTORROLL: " + parameter, 1);
/* 414:    */            }
/* 415:    */            try {
/* 416:416 */              distOrRoll = Float.parseFloat(parameter);
/* 417:    */            }
/* 418:    */            catch (NumberFormatException nfe)
/* 419:    */            {
/* 420:420 */              errorMessage("Error parsing float 'DISTORROLL' from String '" + parameter + "' in method " + "'loadXML'.  Using default value.");
/* 421:    */              
/* 424:424 */              distOrRoll = 0.0F;
/* 425:425 */              if (attModel == 2)
/* 426:    */              {
/* 427:427 */                distOrRoll = SoundSystemConfig.getDefaultFadeDistance();
/* 428:    */              }
/* 429:429 */              else if (attModel == 1)
/* 430:    */              {
/* 431:431 */                distOrRoll = SoundSystemConfig.getDefaultAttenuation();
/* 432:    */              }
/* 433:    */              
/* 434:    */            }
/* 435:    */            
/* 436:    */          }
/* 437:437 */          else if (attModel == 2) {
/* 438:438 */            distOrRoll = SoundSystemConfig.getDefaultFadeDistance();
/* 439:    */          }
/* 440:440 */          else if (attModel == 1)
/* 441:    */          {
/* 442:442 */            distOrRoll = SoundSystemConfig.getDefaultAttenuation();
/* 443:    */          }
/* 444:    */          
/* 445:445 */          if (sourcename.equals(""))
/* 446:    */          {
/* 447:447 */            errorMessage("Parameter 'SOURCENAME' not specified for 'NEWSOURCE' tag in method 'loadXML.  Unable to create new source.");
/* 451:    */          }
/* 452:452 */          else if (filename.equals(""))
/* 453:    */          {
/* 454:454 */            errorMessage("Parameter 'FILENAME' not specified for 'NEWSOURCE' tag in method 'loadXML.  Unable to create new source.");
/* 457:    */          }
/* 458:    */          else
/* 459:    */          {
/* 461:461 */            if (verbose) {
/* 462:462 */              message("Command:  s.CommandQueue( new paulscode.sound.CommandObject( paulscode.sound.CommandObject.NEW_SOURCE, " + priority + ", " + toStream + ", " + toLoop + ", \"" + sourcename + "\", " + "new paulscode.sound.FilenameURL( \"" + filename + "\" ), " + x + ", " + y + ", " + z + ", " + attModel + ", " + distOrRoll + " ) );", 1);
/* 463:    */            }
/* 464:    */            
/* 472:472 */            s.CommandQueue(new CommandObject(10, priority, toStream, toLoop, sourcename, new FilenameURL(filename), x, y, z, attModel, distOrRoll));
/* 473:    */            
/* 477:477 */            if (verbose) {
/* 478:478 */              message("Command:  s.interruptCommandThread();", 1);
/* 479:    */            }
/* 480:480 */            s.interruptCommandThread();
/* 481:    */          }
/* 482:    */        }
/* 483:483 */        else if ((command.length() >= 3) && (command.substring(0, 3).equals("!--")))
/* 484:    */        {
/* 487:487 */          if (verbose)
/* 488:    */          {
/* 489:489 */            message("SoundSystemLoader.loadXML:  comment", 0);
/* 490:490 */            if (commands.contents().length() > 6) {
/* 491:491 */              message(commands.contents().substring(3, commands.contents().length() - 2), 1);
/* 493:    */            }
/* 494:    */            
/* 495:    */          }
/* 496:    */          
/* 498:    */        }
/* 499:499 */        else if (verbose)
/* 500:    */        {
/* 501:501 */          message("SoundSystemLoader.loadXML:  " + command, 0);
/* 502:502 */          message("Unrecognized tag.", 1);
/* 503:    */        }
/* 504:    */        else
/* 505:    */        {
/* 506:506 */          warningMessage("Command '" + command + "' not " + "recognized in method 'loadXML'");
/* 507:    */        }
/* 508:    */      }
/* 509:    */      
/* 512:512 */      commands = commands.next();
/* 513:    */    }
/* 514:    */    
/* 516:516 */    return s;
/* 517:    */  }
/* 518:    */  
/* 523:    */  public static void setVerbose(boolean val)
/* 524:    */  {
/* 525:525 */    verbose = val;
/* 526:    */  }
/* 527:    */  
/* 533:    */  protected static void message(String message, int indent)
/* 534:    */  {
/* 535:535 */    if (logger == null) {
/* 536:536 */      logger = SoundSystemConfig.getLogger();
/* 537:    */    }
/* 538:538 */    if (logger == null)
/* 539:    */    {
/* 540:540 */      logger = new SoundSystemLogger();
/* 541:541 */      SoundSystemConfig.setLogger(logger);
/* 542:    */    }
/* 543:543 */    logger.message(message, indent);
/* 544:    */  }
/* 545:    */  
/* 550:    */  protected static void errorMessage(String message)
/* 551:    */  {
/* 552:552 */    if (logger == null) {
/* 553:553 */      logger = SoundSystemConfig.getLogger();
/* 554:    */    }
/* 555:555 */    if (logger == null)
/* 556:    */    {
/* 557:557 */      logger = new SoundSystemLogger();
/* 558:558 */      SoundSystemConfig.setLogger(logger);
/* 559:    */    }
/* 560:560 */    logger.errorMessage("SoundSystemLoader", message, 0);
/* 561:    */  }
/* 562:    */  
/* 568:    */  protected static void warningMessage(String message)
/* 569:    */  {
/* 570:570 */    if (logger == null) {
/* 571:571 */      logger = SoundSystemConfig.getLogger();
/* 572:    */    }
/* 573:573 */    if (logger == null)
/* 574:    */    {
/* 575:575 */      logger = new SoundSystemLogger();
/* 576:576 */      SoundSystemConfig.setLogger(logger);
/* 577:    */    }
/* 578:578 */    logger.importantMessage("Warning in class 'SoundSystemLoader': " + message, 0);
/* 579:    */  }
/* 580:    */  
/* 586:    */  protected static void printStackTrace(Exception e)
/* 587:    */  {
/* 588:588 */    if (logger == null) {
/* 589:589 */      logger = SoundSystemConfig.getLogger();
/* 590:    */    }
/* 591:591 */    if (logger == null)
/* 592:    */    {
/* 593:593 */      logger = new SoundSystemLogger();
/* 594:594 */      SoundSystemConfig.setLogger(logger);
/* 595:    */    }
/* 596:596 */    logger.printStackTrace(e, 1);
/* 597:    */  }
/* 598:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.utils.SoundSystemLoader
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */