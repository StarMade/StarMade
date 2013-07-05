/*     */ package paulscode.sound;
/*     */ 
/*     */ public class CommandObject
/*     */ {
/*     */   public static final int INITIALIZE = 1;
/*     */   public static final int LOAD_SOUND = 2;
/*     */   public static final int LOAD_DATA = 3;
/*     */   public static final int UNLOAD_SOUND = 4;
/*     */   public static final int QUEUE_SOUND = 5;
/*     */   public static final int DEQUEUE_SOUND = 6;
/*     */   public static final int FADE_OUT = 7;
/*     */   public static final int FADE_OUT_IN = 8;
/*     */   public static final int CHECK_FADE_VOLUMES = 9;
/*     */   public static final int NEW_SOURCE = 10;
/*     */   public static final int RAW_DATA_STREAM = 11;
/*     */   public static final int QUICK_PLAY = 12;
/*     */   public static final int SET_POSITION = 13;
/*     */   public static final int SET_VOLUME = 14;
/*     */   public static final int SET_PITCH = 15;
/*     */   public static final int SET_PRIORITY = 16;
/*     */   public static final int SET_LOOPING = 17;
/*     */   public static final int SET_ATTENUATION = 18;
/*     */   public static final int SET_DIST_OR_ROLL = 19;
/*     */   public static final int CHANGE_DOPPLER_FACTOR = 20;
/*     */   public static final int CHANGE_DOPPLER_VELOCITY = 21;
/*     */   public static final int SET_VELOCITY = 22;
/*     */   public static final int SET_LISTENER_VELOCITY = 23;
/*     */   public static final int PLAY = 24;
/*     */   public static final int FEED_RAW_AUDIO_DATA = 25;
/*     */   public static final int PAUSE = 26;
/*     */   public static final int STOP = 27;
/*     */   public static final int REWIND = 28;
/*     */   public static final int FLUSH = 29;
/*     */   public static final int CULL = 30;
/*     */   public static final int ACTIVATE = 31;
/*     */   public static final int SET_TEMPORARY = 32;
/*     */   public static final int REMOVE_SOURCE = 33;
/*     */   public static final int MOVE_LISTENER = 34;
/*     */   public static final int SET_LISTENER_POSITION = 35;
/*     */   public static final int TURN_LISTENER = 36;
/*     */   public static final int SET_LISTENER_ANGLE = 37;
/*     */   public static final int SET_LISTENER_ORIENTATION = 38;
/*     */   public static final int SET_MASTER_VOLUME = 39;
/*     */   public static final int NEW_LIBRARY = 40;
/*     */   public byte[] buffer;
/*     */   public int[] intArgs;
/*     */   public float[] floatArgs;
/*     */   public long[] longArgs;
/*     */   public boolean[] boolArgs;
/*     */   public String[] stringArgs;
/*     */   public Class[] classArgs;
/*     */   public Object[] objectArgs;
/*     */   public int Command;
/*     */ 
/*     */   public CommandObject(int cmd)
/*     */   {
/* 252 */     this.Command = cmd;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, int i)
/*     */   {
/* 261 */     this.Command = cmd;
/* 262 */     this.intArgs = new int[1];
/* 263 */     this.intArgs[0] = i;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, Class c)
/*     */   {
/* 273 */     this.Command = cmd;
/* 274 */     this.classArgs = new Class[1];
/* 275 */     this.classArgs[0] = c;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, float f)
/*     */   {
/* 284 */     this.Command = cmd;
/* 285 */     this.floatArgs = new float[1];
/* 286 */     this.floatArgs[0] = f;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, String s)
/*     */   {
/* 295 */     this.Command = cmd;
/* 296 */     this.stringArgs = new String[1];
/* 297 */     this.stringArgs[0] = s;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, Object o)
/*     */   {
/* 306 */     this.Command = cmd;
/* 307 */     this.objectArgs = new Object[1];
/* 308 */     this.objectArgs[0] = o;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, String s, Object o)
/*     */   {
/* 319 */     this.Command = cmd;
/* 320 */     this.stringArgs = new String[1];
/* 321 */     this.stringArgs[0] = s;
/* 322 */     this.objectArgs = new Object[1];
/* 323 */     this.objectArgs[0] = o;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, String s, byte[] buff)
/*     */   {
/* 334 */     this.Command = cmd;
/* 335 */     this.stringArgs = new String[1];
/* 336 */     this.stringArgs[0] = s;
/* 337 */     this.buffer = buff;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, String s, Object o, long l)
/*     */   {
/* 349 */     this.Command = cmd;
/* 350 */     this.stringArgs = new String[1];
/* 351 */     this.stringArgs[0] = s;
/* 352 */     this.objectArgs = new Object[1];
/* 353 */     this.objectArgs[0] = o;
/* 354 */     this.longArgs = new long[1];
/* 355 */     this.longArgs[0] = l;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, String s, Object o, long l1, long l2)
/*     */   {
/* 368 */     this.Command = cmd;
/* 369 */     this.stringArgs = new String[1];
/* 370 */     this.stringArgs[0] = s;
/* 371 */     this.objectArgs = new Object[1];
/* 372 */     this.objectArgs[0] = o;
/* 373 */     this.longArgs = new long[2];
/* 374 */     this.longArgs[0] = l1;
/* 375 */     this.longArgs[1] = l2;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, String s1, String s2)
/*     */   {
/* 385 */     this.Command = cmd;
/* 386 */     this.stringArgs = new String[2];
/* 387 */     this.stringArgs[0] = s1;
/* 388 */     this.stringArgs[1] = s2;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, String s, int i)
/*     */   {
/* 399 */     this.Command = cmd;
/* 400 */     this.intArgs = new int[1];
/* 401 */     this.stringArgs = new String[1];
/* 402 */     this.intArgs[0] = i;
/* 403 */     this.stringArgs[0] = s;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, String s, float f)
/*     */   {
/* 414 */     this.Command = cmd;
/* 415 */     this.floatArgs = new float[1];
/* 416 */     this.stringArgs = new String[1];
/* 417 */     this.floatArgs[0] = f;
/* 418 */     this.stringArgs[0] = s;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, String s, boolean b)
/*     */   {
/* 429 */     this.Command = cmd;
/* 430 */     this.boolArgs = new boolean[1];
/* 431 */     this.stringArgs = new String[1];
/* 432 */     this.boolArgs[0] = b;
/* 433 */     this.stringArgs[0] = s;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, float f1, float f2, float f3)
/*     */   {
/* 444 */     this.Command = cmd;
/* 445 */     this.floatArgs = new float[3];
/* 446 */     this.floatArgs[0] = f1;
/* 447 */     this.floatArgs[1] = f2;
/* 448 */     this.floatArgs[2] = f3;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, String s, float f1, float f2, float f3)
/*     */   {
/* 461 */     this.Command = cmd;
/* 462 */     this.floatArgs = new float[3];
/* 463 */     this.stringArgs = new String[1];
/* 464 */     this.floatArgs[0] = f1;
/* 465 */     this.floatArgs[1] = f2;
/* 466 */     this.floatArgs[2] = f3;
/* 467 */     this.stringArgs[0] = s;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, float f1, float f2, float f3, float f4, float f5, float f6)
/*     */   {
/* 482 */     this.Command = cmd;
/* 483 */     this.floatArgs = new float[6];
/* 484 */     this.floatArgs[0] = f1;
/* 485 */     this.floatArgs[1] = f2;
/* 486 */     this.floatArgs[2] = f3;
/* 487 */     this.floatArgs[3] = f4;
/* 488 */     this.floatArgs[4] = f5;
/* 489 */     this.floatArgs[5] = f6;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, boolean b1, boolean b2, boolean b3, String s, Object o, float f1, float f2, float f3, int i, float f4)
/*     */   {
/* 511 */     this.Command = cmd;
/* 512 */     this.intArgs = new int[1];
/* 513 */     this.floatArgs = new float[4];
/* 514 */     this.boolArgs = new boolean[3];
/* 515 */     this.stringArgs = new String[1];
/* 516 */     this.objectArgs = new Object[1];
/* 517 */     this.intArgs[0] = i;
/* 518 */     this.floatArgs[0] = f1;
/* 519 */     this.floatArgs[1] = f2;
/* 520 */     this.floatArgs[2] = f3;
/* 521 */     this.floatArgs[3] = f4;
/* 522 */     this.boolArgs[0] = b1;
/* 523 */     this.boolArgs[1] = b2;
/* 524 */     this.boolArgs[2] = b3;
/* 525 */     this.stringArgs[0] = s;
/* 526 */     this.objectArgs[0] = o;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, boolean b1, boolean b2, boolean b3, String s, Object o, float f1, float f2, float f3, int i, float f4, boolean b4)
/*     */   {
/* 550 */     this.Command = cmd;
/* 551 */     this.intArgs = new int[1];
/* 552 */     this.floatArgs = new float[4];
/* 553 */     this.boolArgs = new boolean[4];
/* 554 */     this.stringArgs = new String[1];
/* 555 */     this.objectArgs = new Object[1];
/* 556 */     this.intArgs[0] = i;
/* 557 */     this.floatArgs[0] = f1;
/* 558 */     this.floatArgs[1] = f2;
/* 559 */     this.floatArgs[2] = f3;
/* 560 */     this.floatArgs[3] = f4;
/* 561 */     this.boolArgs[0] = b1;
/* 562 */     this.boolArgs[1] = b2;
/* 563 */     this.boolArgs[2] = b3;
/* 564 */     this.boolArgs[3] = b4;
/* 565 */     this.stringArgs[0] = s;
/* 566 */     this.objectArgs[0] = o;
/*     */   }
/*     */ 
/*     */   public CommandObject(int cmd, Object o, boolean b, String s, float f1, float f2, float f3, int i, float f4)
/*     */   {
/* 588 */     this.Command = cmd;
/* 589 */     this.intArgs = new int[1];
/* 590 */     this.floatArgs = new float[4];
/* 591 */     this.boolArgs = new boolean[1];
/* 592 */     this.stringArgs = new String[1];
/* 593 */     this.objectArgs = new Object[1];
/* 594 */     this.intArgs[0] = i;
/* 595 */     this.floatArgs[0] = f1;
/* 596 */     this.floatArgs[1] = f2;
/* 597 */     this.floatArgs[2] = f3;
/* 598 */     this.floatArgs[3] = f4;
/* 599 */     this.boolArgs[0] = b;
/* 600 */     this.stringArgs[0] = s;
/* 601 */     this.objectArgs[0] = o;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     paulscode.sound.CommandObject
 * JD-Core Version:    0.6.2
 */