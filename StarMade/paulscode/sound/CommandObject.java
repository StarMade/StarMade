/*   1:    */package paulscode.sound;
/*   2:    */
/*   7:    */public class CommandObject
/*   8:    */{
/*   9:    */  public static final int INITIALIZE = 1;
/*  10:    */  
/*  14:    */  public static final int LOAD_SOUND = 2;
/*  15:    */  
/*  19:    */  public static final int LOAD_DATA = 3;
/*  20:    */  
/*  24:    */  public static final int UNLOAD_SOUND = 4;
/*  25:    */  
/*  29:    */  public static final int QUEUE_SOUND = 5;
/*  30:    */  
/*  34:    */  public static final int DEQUEUE_SOUND = 6;
/*  35:    */  
/*  39:    */  public static final int FADE_OUT = 7;
/*  40:    */  
/*  44:    */  public static final int FADE_OUT_IN = 8;
/*  45:    */  
/*  49:    */  public static final int CHECK_FADE_VOLUMES = 9;
/*  50:    */  
/*  54:    */  public static final int NEW_SOURCE = 10;
/*  55:    */  
/*  59:    */  public static final int RAW_DATA_STREAM = 11;
/*  60:    */  
/*  64:    */  public static final int QUICK_PLAY = 12;
/*  65:    */  
/*  69:    */  public static final int SET_POSITION = 13;
/*  70:    */  
/*  74:    */  public static final int SET_VOLUME = 14;
/*  75:    */  
/*  79:    */  public static final int SET_PITCH = 15;
/*  80:    */  
/*  84:    */  public static final int SET_PRIORITY = 16;
/*  85:    */  
/*  89:    */  public static final int SET_LOOPING = 17;
/*  90:    */  
/*  94:    */  public static final int SET_ATTENUATION = 18;
/*  95:    */  
/*  99:    */  public static final int SET_DIST_OR_ROLL = 19;
/* 100:    */  
/* 104:    */  public static final int CHANGE_DOPPLER_FACTOR = 20;
/* 105:    */  
/* 109:    */  public static final int CHANGE_DOPPLER_VELOCITY = 21;
/* 110:    */  
/* 114:    */  public static final int SET_VELOCITY = 22;
/* 115:    */  
/* 119:    */  public static final int SET_LISTENER_VELOCITY = 23;
/* 120:    */  
/* 124:    */  public static final int PLAY = 24;
/* 125:    */  
/* 129:    */  public static final int FEED_RAW_AUDIO_DATA = 25;
/* 130:    */  
/* 134:    */  public static final int PAUSE = 26;
/* 135:    */  
/* 139:    */  public static final int STOP = 27;
/* 140:    */  
/* 144:    */  public static final int REWIND = 28;
/* 145:    */  
/* 149:    */  public static final int FLUSH = 29;
/* 150:    */  
/* 154:    */  public static final int CULL = 30;
/* 155:    */  
/* 159:    */  public static final int ACTIVATE = 31;
/* 160:    */  
/* 164:    */  public static final int SET_TEMPORARY = 32;
/* 165:    */  
/* 169:    */  public static final int REMOVE_SOURCE = 33;
/* 170:    */  
/* 174:    */  public static final int MOVE_LISTENER = 34;
/* 175:    */  
/* 179:    */  public static final int SET_LISTENER_POSITION = 35;
/* 180:    */  
/* 184:    */  public static final int TURN_LISTENER = 36;
/* 185:    */  
/* 189:    */  public static final int SET_LISTENER_ANGLE = 37;
/* 190:    */  
/* 194:    */  public static final int SET_LISTENER_ORIENTATION = 38;
/* 195:    */  
/* 199:    */  public static final int SET_MASTER_VOLUME = 39;
/* 200:    */  
/* 204:    */  public static final int NEW_LIBRARY = 40;
/* 205:    */  
/* 209:    */  public byte[] buffer;
/* 210:    */  
/* 214:    */  public int[] intArgs;
/* 215:    */  
/* 219:    */  public float[] floatArgs;
/* 220:    */  
/* 224:    */  public long[] longArgs;
/* 225:    */  
/* 229:    */  public boolean[] boolArgs;
/* 230:    */  
/* 233:    */  public String[] stringArgs;
/* 234:    */  
/* 237:    */  public Class[] classArgs;
/* 238:    */  
/* 241:    */  public Object[] objectArgs;
/* 242:    */  
/* 245:    */  public int Command;
/* 246:    */  
/* 250:    */  public CommandObject(int cmd)
/* 251:    */  {
/* 252:252 */    this.Command = cmd;
/* 253:    */  }
/* 254:    */  
/* 259:    */  public CommandObject(int cmd, int i)
/* 260:    */  {
/* 261:261 */    this.Command = cmd;
/* 262:262 */    this.intArgs = new int[1];
/* 263:263 */    this.intArgs[0] = i;
/* 264:    */  }
/* 265:    */  
/* 271:    */  public CommandObject(int cmd, Class c)
/* 272:    */  {
/* 273:273 */    this.Command = cmd;
/* 274:274 */    this.classArgs = new Class[1];
/* 275:275 */    this.classArgs[0] = c;
/* 276:    */  }
/* 277:    */  
/* 282:    */  public CommandObject(int cmd, float f)
/* 283:    */  {
/* 284:284 */    this.Command = cmd;
/* 285:285 */    this.floatArgs = new float[1];
/* 286:286 */    this.floatArgs[0] = f;
/* 287:    */  }
/* 288:    */  
/* 293:    */  public CommandObject(int cmd, String s)
/* 294:    */  {
/* 295:295 */    this.Command = cmd;
/* 296:296 */    this.stringArgs = new String[1];
/* 297:297 */    this.stringArgs[0] = s;
/* 298:    */  }
/* 299:    */  
/* 304:    */  public CommandObject(int cmd, Object o)
/* 305:    */  {
/* 306:306 */    this.Command = cmd;
/* 307:307 */    this.objectArgs = new Object[1];
/* 308:308 */    this.objectArgs[0] = o;
/* 309:    */  }
/* 310:    */  
/* 317:    */  public CommandObject(int cmd, String s, Object o)
/* 318:    */  {
/* 319:319 */    this.Command = cmd;
/* 320:320 */    this.stringArgs = new String[1];
/* 321:321 */    this.stringArgs[0] = s;
/* 322:322 */    this.objectArgs = new Object[1];
/* 323:323 */    this.objectArgs[0] = o;
/* 324:    */  }
/* 325:    */  
/* 332:    */  public CommandObject(int cmd, String s, byte[] buff)
/* 333:    */  {
/* 334:334 */    this.Command = cmd;
/* 335:335 */    this.stringArgs = new String[1];
/* 336:336 */    this.stringArgs[0] = s;
/* 337:337 */    this.buffer = buff;
/* 338:    */  }
/* 339:    */  
/* 347:    */  public CommandObject(int cmd, String s, Object o, long l)
/* 348:    */  {
/* 349:349 */    this.Command = cmd;
/* 350:350 */    this.stringArgs = new String[1];
/* 351:351 */    this.stringArgs[0] = s;
/* 352:352 */    this.objectArgs = new Object[1];
/* 353:353 */    this.objectArgs[0] = o;
/* 354:354 */    this.longArgs = new long[1];
/* 355:355 */    this.longArgs[0] = l;
/* 356:    */  }
/* 357:    */  
/* 366:    */  public CommandObject(int cmd, String s, Object o, long l1, long l2)
/* 367:    */  {
/* 368:368 */    this.Command = cmd;
/* 369:369 */    this.stringArgs = new String[1];
/* 370:370 */    this.stringArgs[0] = s;
/* 371:371 */    this.objectArgs = new Object[1];
/* 372:372 */    this.objectArgs[0] = o;
/* 373:373 */    this.longArgs = new long[2];
/* 374:374 */    this.longArgs[0] = l1;
/* 375:375 */    this.longArgs[1] = l2;
/* 376:    */  }
/* 377:    */  
/* 383:    */  public CommandObject(int cmd, String s1, String s2)
/* 384:    */  {
/* 385:385 */    this.Command = cmd;
/* 386:386 */    this.stringArgs = new String[2];
/* 387:387 */    this.stringArgs[0] = s1;
/* 388:388 */    this.stringArgs[1] = s2;
/* 389:    */  }
/* 390:    */  
/* 397:    */  public CommandObject(int cmd, String s, int i)
/* 398:    */  {
/* 399:399 */    this.Command = cmd;
/* 400:400 */    this.intArgs = new int[1];
/* 401:401 */    this.stringArgs = new String[1];
/* 402:402 */    this.intArgs[0] = i;
/* 403:403 */    this.stringArgs[0] = s;
/* 404:    */  }
/* 405:    */  
/* 412:    */  public CommandObject(int cmd, String s, float f)
/* 413:    */  {
/* 414:414 */    this.Command = cmd;
/* 415:415 */    this.floatArgs = new float[1];
/* 416:416 */    this.stringArgs = new String[1];
/* 417:417 */    this.floatArgs[0] = f;
/* 418:418 */    this.stringArgs[0] = s;
/* 419:    */  }
/* 420:    */  
/* 427:    */  public CommandObject(int cmd, String s, boolean b)
/* 428:    */  {
/* 429:429 */    this.Command = cmd;
/* 430:430 */    this.boolArgs = new boolean[1];
/* 431:431 */    this.stringArgs = new String[1];
/* 432:432 */    this.boolArgs[0] = b;
/* 433:433 */    this.stringArgs[0] = s;
/* 434:    */  }
/* 435:    */  
/* 442:    */  public CommandObject(int cmd, float f1, float f2, float f3)
/* 443:    */  {
/* 444:444 */    this.Command = cmd;
/* 445:445 */    this.floatArgs = new float[3];
/* 446:446 */    this.floatArgs[0] = f1;
/* 447:447 */    this.floatArgs[1] = f2;
/* 448:448 */    this.floatArgs[2] = f3;
/* 449:    */  }
/* 450:    */  
/* 459:    */  public CommandObject(int cmd, String s, float f1, float f2, float f3)
/* 460:    */  {
/* 461:461 */    this.Command = cmd;
/* 462:462 */    this.floatArgs = new float[3];
/* 463:463 */    this.stringArgs = new String[1];
/* 464:464 */    this.floatArgs[0] = f1;
/* 465:465 */    this.floatArgs[1] = f2;
/* 466:466 */    this.floatArgs[2] = f3;
/* 467:467 */    this.stringArgs[0] = s;
/* 468:    */  }
/* 469:    */  
/* 480:    */  public CommandObject(int cmd, float f1, float f2, float f3, float f4, float f5, float f6)
/* 481:    */  {
/* 482:482 */    this.Command = cmd;
/* 483:483 */    this.floatArgs = new float[6];
/* 484:484 */    this.floatArgs[0] = f1;
/* 485:485 */    this.floatArgs[1] = f2;
/* 486:486 */    this.floatArgs[2] = f3;
/* 487:487 */    this.floatArgs[3] = f4;
/* 488:488 */    this.floatArgs[4] = f5;
/* 489:489 */    this.floatArgs[5] = f6;
/* 490:    */  }
/* 491:    */  
/* 509:    */  public CommandObject(int cmd, boolean b1, boolean b2, boolean b3, String s, Object o, float f1, float f2, float f3, int i, float f4)
/* 510:    */  {
/* 511:511 */    this.Command = cmd;
/* 512:512 */    this.intArgs = new int[1];
/* 513:513 */    this.floatArgs = new float[4];
/* 514:514 */    this.boolArgs = new boolean[3];
/* 515:515 */    this.stringArgs = new String[1];
/* 516:516 */    this.objectArgs = new Object[1];
/* 517:517 */    this.intArgs[0] = i;
/* 518:518 */    this.floatArgs[0] = f1;
/* 519:519 */    this.floatArgs[1] = f2;
/* 520:520 */    this.floatArgs[2] = f3;
/* 521:521 */    this.floatArgs[3] = f4;
/* 522:522 */    this.boolArgs[0] = b1;
/* 523:523 */    this.boolArgs[1] = b2;
/* 524:524 */    this.boolArgs[2] = b3;
/* 525:525 */    this.stringArgs[0] = s;
/* 526:526 */    this.objectArgs[0] = o;
/* 527:    */  }
/* 528:    */  
/* 548:    */  public CommandObject(int cmd, boolean b1, boolean b2, boolean b3, String s, Object o, float f1, float f2, float f3, int i, float f4, boolean b4)
/* 549:    */  {
/* 550:550 */    this.Command = cmd;
/* 551:551 */    this.intArgs = new int[1];
/* 552:552 */    this.floatArgs = new float[4];
/* 553:553 */    this.boolArgs = new boolean[4];
/* 554:554 */    this.stringArgs = new String[1];
/* 555:555 */    this.objectArgs = new Object[1];
/* 556:556 */    this.intArgs[0] = i;
/* 557:557 */    this.floatArgs[0] = f1;
/* 558:558 */    this.floatArgs[1] = f2;
/* 559:559 */    this.floatArgs[2] = f3;
/* 560:560 */    this.floatArgs[3] = f4;
/* 561:561 */    this.boolArgs[0] = b1;
/* 562:562 */    this.boolArgs[1] = b2;
/* 563:563 */    this.boolArgs[2] = b3;
/* 564:564 */    this.boolArgs[3] = b4;
/* 565:565 */    this.stringArgs[0] = s;
/* 566:566 */    this.objectArgs[0] = o;
/* 567:    */  }
/* 568:    */  
/* 586:    */  public CommandObject(int cmd, Object o, boolean b, String s, float f1, float f2, float f3, int i, float f4)
/* 587:    */  {
/* 588:588 */    this.Command = cmd;
/* 589:589 */    this.intArgs = new int[1];
/* 590:590 */    this.floatArgs = new float[4];
/* 591:591 */    this.boolArgs = new boolean[1];
/* 592:592 */    this.stringArgs = new String[1];
/* 593:593 */    this.objectArgs = new Object[1];
/* 594:594 */    this.intArgs[0] = i;
/* 595:595 */    this.floatArgs[0] = f1;
/* 596:596 */    this.floatArgs[1] = f2;
/* 597:597 */    this.floatArgs[2] = f3;
/* 598:598 */    this.floatArgs[3] = f4;
/* 599:599 */    this.boolArgs[0] = b;
/* 600:600 */    this.stringArgs[0] = s;
/* 601:601 */    this.objectArgs[0] = o;
/* 602:    */  }
/* 603:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.CommandObject
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */