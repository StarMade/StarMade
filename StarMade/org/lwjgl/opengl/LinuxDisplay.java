/*    1:     */package org.lwjgl.opengl;
/*    2:     */
/*    3:     */import java.awt.Canvas;
/*    4:     */import java.awt.event.FocusEvent;
/*    5:     */import java.awt.event.FocusListener;
/*    6:     */import java.io.BufferedReader;
/*    7:     */import java.io.IOException;
/*    8:     */import java.io.InputStreamReader;
/*    9:     */import java.nio.ByteBuffer;
/*   10:     */import java.nio.FloatBuffer;
/*   11:     */import java.nio.IntBuffer;
/*   12:     */import java.security.AccessController;
/*   13:     */import java.security.PrivilegedAction;
/*   14:     */import java.util.ArrayList;
/*   15:     */import java.util.List;
/*   16:     */import org.lwjgl.BufferUtils;
/*   17:     */import org.lwjgl.LWJGLException;
/*   18:     */import org.lwjgl.LWJGLUtil;
/*   19:     */import org.lwjgl.MemoryUtil;
/*   20:     */
/*   72:     */final class LinuxDisplay
/*   73:     */  implements DisplayImplementation
/*   74:     */{
/*   75:     */  public static final int CurrentTime = 0;
/*   76:     */  public static final int GrabSuccess = 0;
/*   77:     */  public static final int AutoRepeatModeOff = 0;
/*   78:     */  public static final int AutoRepeatModeOn = 1;
/*   79:     */  public static final int AutoRepeatModeDefault = 2;
/*   80:     */  public static final int None = 0;
/*   81:     */  private static final int KeyPressMask = 1;
/*   82:     */  private static final int KeyReleaseMask = 2;
/*   83:     */  private static final int ButtonPressMask = 4;
/*   84:     */  private static final int ButtonReleaseMask = 8;
/*   85:     */  private static final int NotifyAncestor = 0;
/*   86:     */  private static final int NotifyNonlinear = 3;
/*   87:     */  private static final int NotifyPointer = 5;
/*   88:     */  private static final int NotifyPointerRoot = 6;
/*   89:     */  private static final int NotifyDetailNone = 7;
/*   90:     */  private static final int SetModeInsert = 0;
/*   91:     */  private static final int SaveSetRoot = 1;
/*   92:     */  private static final int SaveSetUnmap = 1;
/*   93:     */  private static final int X_SetInputFocus = 42;
/*   94:     */  private static final int FULLSCREEN_LEGACY = 1;
/*   95:     */  private static final int FULLSCREEN_NETWM = 2;
/*   96:     */  private static final int WINDOWED = 3;
/*   97:  97 */  private static int current_window_mode = 3;
/*   98:     */  private static final int XRANDR = 10;
/*   99:     */  private static final int XF86VIDMODE = 11;
/*  100:     */  private static final int NONE = 12;
/*  101:     */  private static long display;
/*  102:     */  private static long current_window;
/*  103:     */  private static long saved_error_handler;
/*  104:     */  private static int display_connection_usage_count;
/*  105:     */  private final LinuxEvent event_buffer;
/*  106:     */  private final LinuxEvent tmp_event_buffer;
/*  107:     */  private int current_displaymode_extension;
/*  108:     */  private long delete_atom;
/*  109:     */  
/*  110:     */  LinuxDisplay()
/*  111:     */  {
/*  112: 112 */    this.event_buffer = new LinuxEvent();
/*  113: 113 */    this.tmp_event_buffer = new LinuxEvent();
/*  114:     */    
/*  116: 116 */    this.current_displaymode_extension = 12;
/*  117:     */    
/*  143: 143 */    this.mouseInside = true;
/*  144:     */    
/*  158: 158 */    this.last_window_focus = 0L;
/*  159:     */    
/*  163: 163 */    this.focus_listener = new FocusListener() {
/*  164:     */      public void focusGained(FocusEvent e) {
/*  165: 165 */        synchronized (GlobalLock.lock) {
/*  166: 166 */          LinuxDisplay.this.parent_focused = true;
/*  167: 167 */          LinuxDisplay.this.parent_focus_changed = true;
/*  168:     */        }
/*  169:     */      }
/*  170:     */      
/*  171: 171 */      public void focusLost(FocusEvent e) { synchronized (GlobalLock.lock) {
/*  172: 172 */          LinuxDisplay.this.parent_focused = false;
/*  173: 173 */          LinuxDisplay.this.parent_focus_changed = true;
/*  174:     */        }
/*  175:     */      }
/*  176:     */    };
/*  177:     */  }
/*  178:     */  
/*  179:     */  /* Error */
/*  180:     */  private static ByteBuffer getCurrentGammaRamp()
/*  181:     */    throws LWJGLException
/*  182:     */  {
/*  183:     */    // Byte code:
/*  184:     */    //   0: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/*  185:     */    //   3: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/*  186:     */    //   6: invokestatic 17	org/lwjgl/opengl/LinuxDisplay:isXF86VidModeSupported	()Z
/*  187:     */    //   9: ifeq +21 -> 30
/*  188:     */    //   12: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/*  189:     */    //   15: invokestatic 19	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
/*  190:     */    //   18: invokestatic 20	org/lwjgl/opengl/LinuxDisplay:nGetCurrentGammaRamp	(JI)Ljava/nio/ByteBuffer;
/*  191:     */    //   21: astore_0
/*  192:     */    //   22: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  193:     */    //   25: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  194:     */    //   28: aload_0
/*  195:     */    //   29: areturn
/*  196:     */    //   30: aconst_null
/*  197:     */    //   31: astore_0
/*  198:     */    //   32: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  199:     */    //   35: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  200:     */    //   38: aload_0
/*  201:     */    //   39: areturn
/*  202:     */    //   40: astore_1
/*  203:     */    //   41: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  204:     */    //   44: aload_1
/*  205:     */    //   45: athrow
/*  206:     */    //   46: astore_2
/*  207:     */    //   47: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  208:     */    //   50: aload_2
/*  209:     */    //   51: athrow
/*  210:     */    // Line number table:
/*  211:     */    //   Java source line #179	-> byte code offset #0
/*  212:     */    //   Java source line #181	-> byte code offset #3
/*  213:     */    //   Java source line #183	-> byte code offset #6
/*  214:     */    //   Java source line #184	-> byte code offset #12
/*  215:     */    //   Java source line #188	-> byte code offset #22
/*  216:     */    //   Java source line #191	-> byte code offset #25
/*  217:     */    //   Java source line #186	-> byte code offset #30
/*  218:     */    //   Java source line #188	-> byte code offset #32
/*  219:     */    //   Java source line #191	-> byte code offset #35
/*  220:     */    //   Java source line #188	-> byte code offset #40
/*  221:     */    //   Java source line #191	-> byte code offset #46
/*  222:     */    // Local variable table:
/*  223:     */    //   start	length	slot	name	signature
/*  224:     */    //   21	18	0	localByteBuffer	ByteBuffer
/*  225:     */    //   40	5	1	localObject1	Object
/*  226:     */    //   46	5	2	localObject2	Object
/*  227:     */    // Exception table:
/*  228:     */    //   from	to	target	type
/*  229:     */    //   6	22	40	finally
/*  230:     */    //   30	32	40	finally
/*  231:     */    //   40	41	40	finally
/*  232:     */    //   3	25	46	finally
/*  233:     */    //   30	35	46	finally
/*  234:     */    //   40	47	46	finally
/*  235:     */  }
/*  236:     */  
/*  237:     */  private static native ByteBuffer nGetCurrentGammaRamp(long paramLong, int paramInt)
/*  238:     */    throws LWJGLException;
/*  239:     */  
/*  240:     */  private static int getBestDisplayModeExtension()
/*  241:     */  {
/*  242:     */    int result;
/*  243:     */    int result;
/*  244: 198 */    if (isXrandrSupported()) {
/*  245: 199 */      LWJGLUtil.log("Using Xrandr for display mode switching");
/*  246: 200 */      result = 10; } else { int result;
/*  247: 201 */      if (isXF86VidModeSupported()) {
/*  248: 202 */        LWJGLUtil.log("Using XF86VidMode for display mode switching");
/*  249: 203 */        result = 11;
/*  250:     */      } else {
/*  251: 205 */        LWJGLUtil.log("No display mode extensions available");
/*  252: 206 */        result = 12;
/*  253:     */      } }
/*  254: 208 */    return result;
/*  255:     */  }
/*  256:     */  
/*  257:     */  /* Error */
/*  258:     */  private static boolean isXrandrSupported()
/*  259:     */  {
/*  260:     */    // Byte code:
/*  261:     */    //   0: ldc 28
/*  262:     */    //   2: invokestatic 29	org/lwjgl/opengl/Display:getPrivilegedBoolean	(Ljava/lang/String;)Z
/*  263:     */    //   5: ifeq +5 -> 10
/*  264:     */    //   8: iconst_0
/*  265:     */    //   9: ireturn
/*  266:     */    //   10: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/*  267:     */    //   13: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/*  268:     */    //   16: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/*  269:     */    //   19: invokestatic 30	org/lwjgl/opengl/LinuxDisplay:nIsXrandrSupported	(J)Z
/*  270:     */    //   22: istore_0
/*  271:     */    //   23: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  272:     */    //   26: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  273:     */    //   29: iload_0
/*  274:     */    //   30: ireturn
/*  275:     */    //   31: astore_1
/*  276:     */    //   32: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  277:     */    //   35: aload_1
/*  278:     */    //   36: athrow
/*  279:     */    //   37: astore_0
/*  280:     */    //   38: new 32	java/lang/StringBuilder
/*  281:     */    //   41: dup
/*  282:     */    //   42: invokespecial 33	java/lang/StringBuilder:<init>	()V
/*  283:     */    //   45: ldc 34
/*  284:     */    //   47: invokevirtual 35	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*  285:     */    //   50: aload_0
/*  286:     */    //   51: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*  287:     */    //   54: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*  288:     */    //   57: invokestatic 25	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
/*  289:     */    //   60: iconst_0
/*  290:     */    //   61: istore_1
/*  291:     */    //   62: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  292:     */    //   65: iload_1
/*  293:     */    //   66: ireturn
/*  294:     */    //   67: astore_2
/*  295:     */    //   68: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  296:     */    //   71: aload_2
/*  297:     */    //   72: athrow
/*  298:     */    // Line number table:
/*  299:     */    //   Java source line #212	-> byte code offset #0
/*  300:     */    //   Java source line #213	-> byte code offset #8
/*  301:     */    //   Java source line #214	-> byte code offset #10
/*  302:     */    //   Java source line #216	-> byte code offset #13
/*  303:     */    //   Java source line #218	-> byte code offset #16
/*  304:     */    //   Java source line #220	-> byte code offset #23
/*  305:     */    //   Java source line #226	-> byte code offset #26
/*  306:     */    //   Java source line #220	-> byte code offset #31
/*  307:     */    //   Java source line #222	-> byte code offset #37
/*  308:     */    //   Java source line #223	-> byte code offset #38
/*  309:     */    //   Java source line #224	-> byte code offset #60
/*  310:     */    //   Java source line #226	-> byte code offset #62
/*  311:     */    // Local variable table:
/*  312:     */    //   start	length	slot	name	signature
/*  313:     */    //   22	8	0	bool1	boolean
/*  314:     */    //   37	14	0	e	LWJGLException
/*  315:     */    //   31	5	1	localObject1	Object
/*  316:     */    //   61	5	1	bool2	boolean
/*  317:     */    //   67	5	2	localObject2	Object
/*  318:     */    // Exception table:
/*  319:     */    //   from	to	target	type
/*  320:     */    //   16	23	31	finally
/*  321:     */    //   31	32	31	finally
/*  322:     */    //   13	26	37	org/lwjgl/LWJGLException
/*  323:     */    //   31	37	37	org/lwjgl/LWJGLException
/*  324:     */    //   13	26	67	finally
/*  325:     */    //   31	62	67	finally
/*  326:     */    //   67	68	67	finally
/*  327:     */  }
/*  328:     */  
/*  329:     */  private static native boolean nIsXrandrSupported(long paramLong)
/*  330:     */    throws LWJGLException;
/*  331:     */  
/*  332:     */  /* Error */
/*  333:     */  private static boolean isXF86VidModeSupported()
/*  334:     */  {
/*  335:     */    // Byte code:
/*  336:     */    //   0: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/*  337:     */    //   3: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/*  338:     */    //   6: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/*  339:     */    //   9: invokestatic 38	org/lwjgl/opengl/LinuxDisplay:nIsXF86VidModeSupported	(J)Z
/*  340:     */    //   12: istore_0
/*  341:     */    //   13: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  342:     */    //   16: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  343:     */    //   19: iload_0
/*  344:     */    //   20: ireturn
/*  345:     */    //   21: astore_1
/*  346:     */    //   22: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  347:     */    //   25: aload_1
/*  348:     */    //   26: athrow
/*  349:     */    //   27: astore_0
/*  350:     */    //   28: new 32	java/lang/StringBuilder
/*  351:     */    //   31: dup
/*  352:     */    //   32: invokespecial 33	java/lang/StringBuilder:<init>	()V
/*  353:     */    //   35: ldc 39
/*  354:     */    //   37: invokevirtual 35	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*  355:     */    //   40: aload_0
/*  356:     */    //   41: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*  357:     */    //   44: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*  358:     */    //   47: invokestatic 25	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
/*  359:     */    //   50: iconst_0
/*  360:     */    //   51: istore_1
/*  361:     */    //   52: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  362:     */    //   55: iload_1
/*  363:     */    //   56: ireturn
/*  364:     */    //   57: astore_2
/*  365:     */    //   58: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  366:     */    //   61: aload_2
/*  367:     */    //   62: athrow
/*  368:     */    // Line number table:
/*  369:     */    //   Java source line #232	-> byte code offset #0
/*  370:     */    //   Java source line #234	-> byte code offset #3
/*  371:     */    //   Java source line #236	-> byte code offset #6
/*  372:     */    //   Java source line #238	-> byte code offset #13
/*  373:     */    //   Java source line #244	-> byte code offset #16
/*  374:     */    //   Java source line #238	-> byte code offset #21
/*  375:     */    //   Java source line #240	-> byte code offset #27
/*  376:     */    //   Java source line #241	-> byte code offset #28
/*  377:     */    //   Java source line #242	-> byte code offset #50
/*  378:     */    //   Java source line #244	-> byte code offset #52
/*  379:     */    // Local variable table:
/*  380:     */    //   start	length	slot	name	signature
/*  381:     */    //   12	8	0	bool1	boolean
/*  382:     */    //   27	14	0	e	LWJGLException
/*  383:     */    //   21	5	1	localObject1	Object
/*  384:     */    //   51	5	1	bool2	boolean
/*  385:     */    //   57	5	2	localObject2	Object
/*  386:     */    // Exception table:
/*  387:     */    //   from	to	target	type
/*  388:     */    //   6	13	21	finally
/*  389:     */    //   21	22	21	finally
/*  390:     */    //   3	16	27	org/lwjgl/LWJGLException
/*  391:     */    //   21	27	27	org/lwjgl/LWJGLException
/*  392:     */    //   3	16	57	finally
/*  393:     */    //   21	52	57	finally
/*  394:     */    //   57	58	57	finally
/*  395:     */  }
/*  396:     */  
/*  397:     */  private static native boolean nIsXF86VidModeSupported(long paramLong)
/*  398:     */    throws LWJGLException;
/*  399:     */  
/*  400:     */  /* Error */
/*  401:     */  private static boolean isNetWMFullscreenSupported()
/*  402:     */    throws LWJGLException
/*  403:     */  {
/*  404:     */    // Byte code:
/*  405:     */    //   0: ldc 40
/*  406:     */    //   2: invokestatic 29	org/lwjgl/opengl/Display:getPrivilegedBoolean	(Ljava/lang/String;)Z
/*  407:     */    //   5: ifeq +5 -> 10
/*  408:     */    //   8: iconst_0
/*  409:     */    //   9: ireturn
/*  410:     */    //   10: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/*  411:     */    //   13: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/*  412:     */    //   16: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/*  413:     */    //   19: invokestatic 19	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
/*  414:     */    //   22: invokestatic 41	org/lwjgl/opengl/LinuxDisplay:nIsNetWMFullscreenSupported	(JI)Z
/*  415:     */    //   25: istore_0
/*  416:     */    //   26: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  417:     */    //   29: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  418:     */    //   32: iload_0
/*  419:     */    //   33: ireturn
/*  420:     */    //   34: astore_1
/*  421:     */    //   35: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  422:     */    //   38: aload_1
/*  423:     */    //   39: athrow
/*  424:     */    //   40: astore_0
/*  425:     */    //   41: new 32	java/lang/StringBuilder
/*  426:     */    //   44: dup
/*  427:     */    //   45: invokespecial 33	java/lang/StringBuilder:<init>	()V
/*  428:     */    //   48: ldc 42
/*  429:     */    //   50: invokevirtual 35	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*  430:     */    //   53: aload_0
/*  431:     */    //   54: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*  432:     */    //   57: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*  433:     */    //   60: invokestatic 25	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
/*  434:     */    //   63: iconst_0
/*  435:     */    //   64: istore_1
/*  436:     */    //   65: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  437:     */    //   68: iload_1
/*  438:     */    //   69: ireturn
/*  439:     */    //   70: astore_2
/*  440:     */    //   71: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  441:     */    //   74: aload_2
/*  442:     */    //   75: athrow
/*  443:     */    // Line number table:
/*  444:     */    //   Java source line #250	-> byte code offset #0
/*  445:     */    //   Java source line #251	-> byte code offset #8
/*  446:     */    //   Java source line #252	-> byte code offset #10
/*  447:     */    //   Java source line #254	-> byte code offset #13
/*  448:     */    //   Java source line #256	-> byte code offset #16
/*  449:     */    //   Java source line #258	-> byte code offset #26
/*  450:     */    //   Java source line #264	-> byte code offset #29
/*  451:     */    //   Java source line #258	-> byte code offset #34
/*  452:     */    //   Java source line #260	-> byte code offset #40
/*  453:     */    //   Java source line #261	-> byte code offset #41
/*  454:     */    //   Java source line #262	-> byte code offset #63
/*  455:     */    //   Java source line #264	-> byte code offset #65
/*  456:     */    // Local variable table:
/*  457:     */    //   start	length	slot	name	signature
/*  458:     */    //   25	8	0	bool1	boolean
/*  459:     */    //   40	14	0	e	LWJGLException
/*  460:     */    //   34	5	1	localObject1	Object
/*  461:     */    //   64	5	1	bool2	boolean
/*  462:     */    //   70	5	2	localObject2	Object
/*  463:     */    // Exception table:
/*  464:     */    //   from	to	target	type
/*  465:     */    //   16	26	34	finally
/*  466:     */    //   34	35	34	finally
/*  467:     */    //   13	29	40	org/lwjgl/LWJGLException
/*  468:     */    //   34	40	40	org/lwjgl/LWJGLException
/*  469:     */    //   13	29	70	finally
/*  470:     */    //   34	65	70	finally
/*  471:     */    //   70	71	70	finally
/*  472:     */  }
/*  473:     */  
/*  474:     */  private static native boolean nIsNetWMFullscreenSupported(long paramLong, int paramInt)
/*  475:     */    throws LWJGLException;
/*  476:     */  
/*  477:     */  static void lockAWT()
/*  478:     */  {
/*  479:     */    try
/*  480:     */    {
/*  481:     */      
/*  482:     */    }
/*  483:     */    catch (LWJGLException e)
/*  484:     */    {
/*  485: 277 */      LWJGLUtil.log("Caught exception while locking AWT: " + e);
/*  486:     */    }
/*  487:     */  }
/*  488:     */  
/*  489:     */  private static native void nLockAWT() throws LWJGLException;
/*  490:     */  
/*  491:     */  static void unlockAWT() {
/*  492:     */    try {
/*  493:     */      
/*  494: 286 */    } catch (LWJGLException e) { LWJGLUtil.log("Caught exception while unlocking AWT: " + e);
/*  495:     */    }
/*  496:     */  }
/*  497:     */  
/*  498:     */  private static native void nUnlockAWT()
/*  499:     */    throws LWJGLException;
/*  500:     */  
/*  501:     */  static void incDisplay() throws LWJGLException
/*  502:     */  {
/*  503: 295 */    if (display_connection_usage_count == 0)
/*  504:     */    {
/*  505:     */      try {
/*  506: 298 */        GLContext.loadOpenGLLibrary();
/*  507: 299 */        org.lwjgl.opengles.GLContext.loadOpenGLLibrary();
/*  508:     */      }
/*  509:     */      catch (Throwable t) {}
/*  510: 302 */      saved_error_handler = setErrorHandler();
/*  511: 303 */      display = openDisplay();
/*  512:     */    }
/*  513:     */    
/*  514: 306 */    display_connection_usage_count += 1; }
/*  515:     */  
/*  516:     */  private static native int callErrorHandler(long paramLong1, long paramLong2, long paramLong3);
/*  517:     */  
/*  518:     */  private static native long setErrorHandler();
/*  519:     */  
/*  520:     */  private static native long resetErrorHandler(long paramLong);
/*  521:     */  private static native void synchronize(long paramLong, boolean paramBoolean);
/*  522: 314 */  private static int globalErrorHandler(long display, long event_ptr, long error_display, long serial, long error_code, long request_code, long minor_code) throws LWJGLException { if ((xembedded) && (request_code == 42L)) { return 0;
/*  523:     */    }
/*  524: 316 */    if (display == getDisplay()) {
/*  525: 317 */      String error_msg = getErrorText(display, error_code);
/*  526: 318 */      throw new LWJGLException("X Error - disp: 0x" + Long.toHexString(error_display) + " serial: " + serial + " error: " + error_msg + " request_code: " + request_code + " minor_code: " + minor_code); }
/*  527: 319 */    if (saved_error_handler != 0L)
/*  528: 320 */      return callErrorHandler(saved_error_handler, display, event_ptr);
/*  529: 321 */    return 0;
/*  530:     */  }
/*  531:     */  
/*  534:     */  private static native String getErrorText(long paramLong1, long paramLong2);
/*  535:     */  
/*  538:     */  static void decDisplay() {}
/*  539:     */  
/*  542:     */  static native long openDisplay()
/*  543:     */    throws LWJGLException;
/*  544:     */  
/*  547:     */  static native void closeDisplay(long paramLong);
/*  548:     */  
/*  551:     */  private int getWindowMode(boolean fullscreen)
/*  552:     */    throws LWJGLException
/*  553:     */  {
/*  554: 346 */    if (fullscreen) {
/*  555: 347 */      if ((this.current_displaymode_extension == 10) && (isNetWMFullscreenSupported())) {
/*  556: 348 */        LWJGLUtil.log("Using NetWM for fullscreen window");
/*  557: 349 */        return 2;
/*  558:     */      }
/*  559: 351 */      LWJGLUtil.log("Using legacy mode for fullscreen window");
/*  560: 352 */      return 1;
/*  561:     */    }
/*  562:     */    
/*  563: 355 */    return 3;
/*  564:     */  }
/*  565:     */  
/*  566:     */  static long getDisplay() {
/*  567: 359 */    if (display_connection_usage_count <= 0)
/*  568: 360 */      throw new InternalError("display_connection_usage_count = " + display_connection_usage_count);
/*  569: 361 */    return display;
/*  570:     */  }
/*  571:     */  
/*  572:     */  static int getDefaultScreen() {
/*  573: 365 */    return nGetDefaultScreen(getDisplay());
/*  574:     */  }
/*  575:     */  
/*  576:     */  static native int nGetDefaultScreen(long paramLong);
/*  577:     */  
/*  578: 370 */  static long getWindow() { return current_window; }
/*  579:     */  
/*  580:     */  private void ungrabKeyboard()
/*  581:     */  {
/*  582: 374 */    if (this.keyboard_grabbed) {
/*  583: 375 */      nUngrabKeyboard(getDisplay());
/*  584: 376 */      this.keyboard_grabbed = false;
/*  585:     */    }
/*  586:     */  }
/*  587:     */  
/*  588:     */  static native int nUngrabKeyboard(long paramLong);
/*  589:     */  
/*  590: 382 */  private void grabKeyboard() { if (!this.keyboard_grabbed) {
/*  591: 383 */      int res = nGrabKeyboard(getDisplay(), getWindow());
/*  592: 384 */      if (res == 0)
/*  593: 385 */        this.keyboard_grabbed = true;
/*  594:     */    }
/*  595:     */  }
/*  596:     */  
/*  597:     */  static native int nGrabKeyboard(long paramLong1, long paramLong2);
/*  598:     */  
/*  599: 391 */  private void grabPointer() { if (!this.pointer_grabbed) {
/*  600: 392 */      int result = nGrabPointer(getDisplay(), getWindow(), 0L);
/*  601: 393 */      if (result == 0) {
/*  602: 394 */        this.pointer_grabbed = true;
/*  603:     */        
/*  604: 396 */        if (isLegacyFullscreen())
/*  605: 397 */          nSetViewPort(getDisplay(), getWindow(), getDefaultScreen());
/*  606:     */      }
/*  607:     */    }
/*  608:     */  }
/*  609:     */  
/*  610:     */  static native int nGrabPointer(long paramLong1, long paramLong2, long paramLong3);
/*  611:     */  
/*  612:     */  private static native void nSetViewPort(long paramLong1, long paramLong2, int paramInt);
/*  613:     */  
/*  614: 406 */  private void ungrabPointer() { if (this.pointer_grabbed) {
/*  615: 407 */      this.pointer_grabbed = false;
/*  616: 408 */      nUngrabPointer(getDisplay());
/*  617:     */    }
/*  618:     */  }
/*  619:     */  
/*  620:     */  static native int nUngrabPointer(long paramLong);
/*  621:     */  
/*  622: 414 */  private static boolean isFullscreen() { return (current_window_mode == 1) || (current_window_mode == 2); }
/*  623:     */  
/*  624:     */  private boolean shouldGrab()
/*  625:     */  {
/*  626: 418 */    return (!this.input_released) && (this.grab) && (this.mouse != null);
/*  627:     */  }
/*  628:     */  
/*  629:     */  private void updatePointerGrab() {
/*  630: 422 */    if ((isFullscreen()) || (shouldGrab())) {
/*  631: 423 */      grabPointer();
/*  632:     */    } else {
/*  633: 425 */      ungrabPointer();
/*  634:     */    }
/*  635: 427 */    updateCursor();
/*  636:     */  }
/*  637:     */  
/*  638:     */  private void updateCursor() { long cursor;
/*  639:     */    long cursor;
/*  640: 432 */    if (shouldGrab()) {
/*  641: 433 */      cursor = this.blank_cursor;
/*  642:     */    } else {
/*  643: 435 */      cursor = this.current_cursor;
/*  644:     */    }
/*  645: 437 */    nDefineCursor(getDisplay(), getWindow(), cursor);
/*  646:     */  }
/*  647:     */  
/*  648:     */  private static native void nDefineCursor(long paramLong1, long paramLong2, long paramLong3);
/*  649:     */  
/*  650: 442 */  private static boolean isLegacyFullscreen() { return current_window_mode == 1; }
/*  651:     */  
/*  652:     */  private void updateKeyboardGrab()
/*  653:     */  {
/*  654: 446 */    if (isLegacyFullscreen()) {
/*  655: 447 */      grabKeyboard();
/*  656:     */    } else
/*  657: 449 */      ungrabKeyboard();
/*  658:     */  }
/*  659:     */  
/*  660:     */  public void createWindow(DrawableLWJGL drawable, DisplayMode mode, Canvas parent, int x, int y) throws LWJGLException {
/*  661:     */    
/*  662:     */    try {
/*  663: 455 */      incDisplay();
/*  664:     */      try {
/*  665: 457 */        if ((drawable instanceof DrawableGLES)) {
/*  666: 458 */          this.peer_info = new LinuxDisplayPeerInfo();
/*  667:     */        }
/*  668: 460 */        ByteBuffer handle = this.peer_info.lockAndGetHandle();
/*  669:     */        try {
/*  670: 462 */          current_window_mode = getWindowMode(Display.isFullscreen());
/*  671:     */          
/*  673: 465 */          if (current_window_mode != 3) {
/*  674: 466 */            Compiz.setLegacyFullscreenSupport(true);
/*  675:     */          }
/*  676:     */          
/*  679: 471 */          boolean undecorated = (Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated")) || ((current_window_mode != 3) && (Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated_fs")));
/*  680: 472 */          this.parent = parent;
/*  681: 473 */          this.parent_window = (parent != null ? getHandle(parent) : getRootWindow(getDisplay(), getDefaultScreen()));
/*  682: 474 */          this.resizable = Display.isResizable();
/*  683: 475 */          this.resized = false;
/*  684: 476 */          this.window_x = x;
/*  685: 477 */          this.window_y = y;
/*  686: 478 */          this.window_width = mode.getWidth();
/*  687: 479 */          this.window_height = mode.getHeight();
/*  688: 480 */          current_window = nCreateWindow(getDisplay(), getDefaultScreen(), handle, mode, current_window_mode, x, y, undecorated, this.parent_window, this.resizable);
/*  689: 481 */          mapRaised(getDisplay(), current_window);
/*  690: 482 */          xembedded = (parent != null) && (isAncestorXEmbedded(this.parent_window));
/*  691: 483 */          this.blank_cursor = createBlankCursor();
/*  692: 484 */          this.current_cursor = 0L;
/*  693: 485 */          this.focused = false;
/*  694: 486 */          this.input_released = false;
/*  695: 487 */          this.pointer_grabbed = false;
/*  696: 488 */          this.keyboard_grabbed = false;
/*  697: 489 */          this.close_requested = false;
/*  698: 490 */          this.grab = false;
/*  699: 491 */          this.minimized = false;
/*  700: 492 */          this.dirty = true;
/*  701:     */          
/*  702: 494 */          if ((drawable instanceof DrawableGLES)) {
/*  703: 495 */            ((DrawableGLES)drawable).initialize(current_window, getDisplay(), 4, (org.lwjgl.opengles.PixelFormat)drawable.getPixelFormat());
/*  704:     */          }
/*  705: 497 */          if (parent != null) {
/*  706: 498 */            parent.addFocusListener(this.focus_listener);
/*  707: 499 */            this.parent_focused = parent.isFocusOwner();
/*  708: 500 */            this.parent_focus_changed = true;
/*  709:     */          }
/*  710:     */        } finally {
/*  711: 503 */          this.peer_info.unlock();
/*  712:     */        }
/*  713:     */      }
/*  714:     */      catch (LWJGLException e) {
/*  715: 507 */        throw e;
/*  716:     */      }
/*  717:     */    } finally {
/*  718: 510 */      unlockAWT(); } }
/*  719:     */  
/*  720:     */  private static native long nCreateWindow(long paramLong1, int paramInt1, ByteBuffer paramByteBuffer, DisplayMode paramDisplayMode, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, long paramLong2, boolean paramBoolean2) throws LWJGLException;
/*  721:     */  
/*  722:     */  private static native long getRootWindow(long paramLong, int paramInt);
/*  723:     */  
/*  724:     */  private static native boolean hasProperty(long paramLong1, long paramLong2, long paramLong3);
/*  725:     */  
/*  726:     */  private static native long getParentWindow(long paramLong1, long paramLong2) throws LWJGLException;
/*  727:     */  private static native int getChildCount(long paramLong1, long paramLong2) throws LWJGLException;
/*  728:     */  private static native void mapRaised(long paramLong1, long paramLong2);
/*  729:     */  private static native void reparentWindow(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2);
/*  730:     */  private static native long nGetInputFocus(long paramLong) throws LWJGLException;
/*  731:     */  private static native void nSetInputFocus(long paramLong1, long paramLong2, long paramLong3);
/*  732:     */  private static native void nSetWindowSize(long paramLong1, long paramLong2, int paramInt1, int paramInt2, boolean paramBoolean);
/*  733:     */  private static native int nGetX(long paramLong1, long paramLong2);
/*  734:     */  private static native int nGetY(long paramLong1, long paramLong2);
/*  735:     */  private static native int nGetWidth(long paramLong1, long paramLong2);
/*  736:     */  private static native int nGetHeight(long paramLong1, long paramLong2);
/*  737: 529 */  private static boolean isAncestorXEmbedded(long window) throws LWJGLException { long xembed_atom = internAtom("_XEMBED_INFO", true);
/*  738: 530 */    if (xembed_atom != 0L) {
/*  739: 531 */      long w = window;
/*  740: 532 */      while (w != 0L) {
/*  741: 533 */        if (hasProperty(getDisplay(), w, xembed_atom))
/*  742: 534 */          return true;
/*  743: 535 */        w = getParentWindow(getDisplay(), w);
/*  744:     */      }
/*  745:     */    }
/*  746: 538 */    return false;
/*  747:     */  }
/*  748:     */  
/*  749:     */  private static long getHandle(Canvas parent) throws LWJGLException {
/*  750: 542 */    AWTCanvasImplementation awt_impl = AWTGLCanvas.createImplementation();
/*  751: 543 */    LinuxPeerInfo parent_peer_info = (LinuxPeerInfo)awt_impl.createPeerInfo(parent, null, null);
/*  752: 544 */    ByteBuffer parent_peer_info_handle = parent_peer_info.lockAndGetHandle();
/*  753:     */    try {
/*  754: 546 */      return parent_peer_info.getDrawable();
/*  755:     */    } finally {
/*  756: 548 */      parent_peer_info.unlock();
/*  757:     */    }
/*  758:     */  }
/*  759:     */  
/*  760:     */  private void updateInputGrab() {
/*  761: 553 */    updatePointerGrab();
/*  762: 554 */    updateKeyboardGrab();
/*  763:     */  }
/*  764:     */  
/*  765:     */  public void destroyWindow() {
/*  766:     */    
/*  767:     */    try {
/*  768: 560 */      if (this.parent != null) {
/*  769: 561 */        this.parent.removeFocusListener(this.focus_listener);
/*  770:     */      }
/*  771:     */      try {
/*  772: 564 */        setNativeCursor(null);
/*  773:     */      } catch (LWJGLException e) {
/*  774: 566 */        LWJGLUtil.log("Failed to reset cursor: " + e.getMessage());
/*  775:     */      }
/*  776: 568 */      nDestroyCursor(getDisplay(), this.blank_cursor);
/*  777: 569 */      this.blank_cursor = 0L;
/*  778: 570 */      ungrabKeyboard();
/*  779: 571 */      nDestroyWindow(getDisplay(), getWindow());
/*  780: 572 */      decDisplay();
/*  781:     */      
/*  782: 574 */      if (current_window_mode != 3)
/*  783: 575 */        Compiz.setLegacyFullscreenSupport(false);
/*  784:     */    } finally {
/*  785: 577 */      unlockAWT();
/*  786:     */    }
/*  787:     */  }
/*  788:     */  
/*  789:     */  static native void nDestroyWindow(long paramLong1, long paramLong2);
/*  790:     */  
/*  791:     */  public void switchDisplayMode(DisplayMode mode) throws LWJGLException {
/*  792:     */    
/*  793: 585 */    try { switchDisplayModeOnTmpDisplay(mode);
/*  794: 586 */      this.current_mode = mode;
/*  795:     */    } finally {
/*  796: 588 */      unlockAWT();
/*  797:     */    }
/*  798:     */  }
/*  799:     */  
/*  800:     */  private void switchDisplayModeOnTmpDisplay(DisplayMode mode) throws LWJGLException {
/*  801:     */    
/*  802:     */    try {
/*  803: 595 */      nSwitchDisplayMode(getDisplay(), getDefaultScreen(), this.current_displaymode_extension, mode);
/*  804:     */    } finally {
/*  805: 597 */      decDisplay();
/*  806:     */    }
/*  807:     */  }
/*  808:     */  
/*  809:     */  private static native void nSwitchDisplayMode(long paramLong, int paramInt1, int paramInt2, DisplayMode paramDisplayMode) throws LWJGLException;
/*  810:     */  
/*  811:     */  private static long internAtom(String atom_name, boolean only_if_exists) throws LWJGLException {
/*  812:     */    
/*  813: 605 */    try { return nInternAtom(getDisplay(), atom_name, only_if_exists);
/*  814:     */    } finally {
/*  815: 607 */      decDisplay();
/*  816:     */    }
/*  817:     */  }
/*  818:     */  
/*  819:     */  static native long nInternAtom(long paramLong, String paramString, boolean paramBoolean);
/*  820:     */  
/*  821:     */  public void resetDisplayMode() {
/*  822:     */    
/*  823: 615 */    try { if ((this.current_displaymode_extension == 10) && (this.savedXrandrConfig.length > 0))
/*  824:     */      {
/*  825: 617 */        AccessController.doPrivileged(new PrivilegedAction() {
/*  826:     */          public Object run() {
/*  827: 619 */            XRandR.setConfiguration(LinuxDisplay.this.savedXrandrConfig);
/*  828: 620 */            return null;
/*  829:     */          }
/*  830:     */          
/*  832:     */        });
/*  833:     */      } else {
/*  834: 626 */        switchDisplayMode(this.saved_mode);
/*  835:     */      }
/*  836: 628 */      if (isXF86VidModeSupported()) {
/*  837: 629 */        doSetGamma(this.saved_gamma);
/*  838:     */      }
/*  839: 631 */      Compiz.setLegacyFullscreenSupport(false);
/*  840:     */    } catch (LWJGLException e) {
/*  841: 633 */      LWJGLUtil.log("Caught exception while resetting mode: " + e);
/*  842:     */    } finally {
/*  843: 635 */      unlockAWT();
/*  844:     */    }
/*  845:     */  }
/*  846:     */  
/*  847:     */  /* Error */
/*  848:     */  public int getGammaRampLength()
/*  849:     */  {
/*  850:     */    // Byte code:
/*  851:     */    //   0: invokestatic 17	org/lwjgl/opengl/LinuxDisplay:isXF86VidModeSupported	()Z
/*  852:     */    //   3: ifne +5 -> 8
/*  853:     */    //   6: iconst_0
/*  854:     */    //   7: ireturn
/*  855:     */    //   8: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/*  856:     */    //   11: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/*  857:     */    //   14: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/*  858:     */    //   17: invokestatic 19	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
/*  859:     */    //   20: invokestatic 167	org/lwjgl/opengl/LinuxDisplay:nGetGammaRampLength	(JI)I
/*  860:     */    //   23: istore_1
/*  861:     */    //   24: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  862:     */    //   27: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  863:     */    //   30: iload_1
/*  864:     */    //   31: ireturn
/*  865:     */    //   32: astore_1
/*  866:     */    //   33: new 32	java/lang/StringBuilder
/*  867:     */    //   36: dup
/*  868:     */    //   37: invokespecial 33	java/lang/StringBuilder:<init>	()V
/*  869:     */    //   40: ldc 168
/*  870:     */    //   42: invokevirtual 35	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*  871:     */    //   45: aload_1
/*  872:     */    //   46: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*  873:     */    //   49: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*  874:     */    //   52: invokestatic 25	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
/*  875:     */    //   55: iconst_0
/*  876:     */    //   56: istore_2
/*  877:     */    //   57: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  878:     */    //   60: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  879:     */    //   63: iload_2
/*  880:     */    //   64: ireturn
/*  881:     */    //   65: astore_3
/*  882:     */    //   66: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/*  883:     */    //   69: aload_3
/*  884:     */    //   70: athrow
/*  885:     */    //   71: astore_1
/*  886:     */    //   72: new 32	java/lang/StringBuilder
/*  887:     */    //   75: dup
/*  888:     */    //   76: invokespecial 33	java/lang/StringBuilder:<init>	()V
/*  889:     */    //   79: ldc 169
/*  890:     */    //   81: invokevirtual 35	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*  891:     */    //   84: aload_1
/*  892:     */    //   85: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*  893:     */    //   88: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*  894:     */    //   91: invokestatic 25	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
/*  895:     */    //   94: iconst_0
/*  896:     */    //   95: istore_2
/*  897:     */    //   96: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  898:     */    //   99: iload_2
/*  899:     */    //   100: ireturn
/*  900:     */    //   101: astore 4
/*  901:     */    //   103: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/*  902:     */    //   106: aload 4
/*  903:     */    //   108: athrow
/*  904:     */    // Line number table:
/*  905:     */    //   Java source line #640	-> byte code offset #0
/*  906:     */    //   Java source line #641	-> byte code offset #6
/*  907:     */    //   Java source line #642	-> byte code offset #8
/*  908:     */    //   Java source line #645	-> byte code offset #11
/*  909:     */    //   Java source line #647	-> byte code offset #14
/*  910:     */    //   Java source line #652	-> byte code offset #24
/*  911:     */    //   Java source line #659	-> byte code offset #27
/*  912:     */    //   Java source line #648	-> byte code offset #32
/*  913:     */    //   Java source line #649	-> byte code offset #33
/*  914:     */    //   Java source line #650	-> byte code offset #55
/*  915:     */    //   Java source line #652	-> byte code offset #57
/*  916:     */    //   Java source line #659	-> byte code offset #60
/*  917:     */    //   Java source line #652	-> byte code offset #65
/*  918:     */    //   Java source line #654	-> byte code offset #71
/*  919:     */    //   Java source line #655	-> byte code offset #72
/*  920:     */    //   Java source line #656	-> byte code offset #94
/*  921:     */    //   Java source line #659	-> byte code offset #96
/*  922:     */    // Local variable table:
/*  923:     */    //   start	length	slot	name	signature
/*  924:     */    //   0	109	0	this	LinuxDisplay
/*  925:     */    //   23	8	1	i	int
/*  926:     */    //   32	14	1	e	LWJGLException
/*  927:     */    //   71	14	1	e	LWJGLException
/*  928:     */    //   56	44	2	j	int
/*  929:     */    //   65	5	3	localObject1	Object
/*  930:     */    //   101	6	4	localObject2	Object
/*  931:     */    // Exception table:
/*  932:     */    //   from	to	target	type
/*  933:     */    //   14	24	32	org/lwjgl/LWJGLException
/*  934:     */    //   14	24	65	finally
/*  935:     */    //   32	57	65	finally
/*  936:     */    //   65	66	65	finally
/*  937:     */    //   11	27	71	org/lwjgl/LWJGLException
/*  938:     */    //   32	60	71	org/lwjgl/LWJGLException
/*  939:     */    //   65	71	71	org/lwjgl/LWJGLException
/*  940:     */    //   11	27	101	finally
/*  941:     */    //   32	60	101	finally
/*  942:     */    //   65	96	101	finally
/*  943:     */    //   101	103	101	finally
/*  944:     */  }
/*  945:     */  
/*  946:     */  private static native int nGetGammaRampLength(long paramLong, int paramInt)
/*  947:     */    throws LWJGLException;
/*  948:     */  
/*  949:     */  public void setGammaRamp(FloatBuffer gammaRamp)
/*  950:     */    throws LWJGLException
/*  951:     */  {
/*  952: 665 */    if (!isXF86VidModeSupported())
/*  953: 666 */      throw new LWJGLException("No gamma ramp support (Missing XF86VM extension)");
/*  954: 667 */    doSetGamma(convertToNativeRamp(gammaRamp));
/*  955:     */  }
/*  956:     */  
/*  957:     */  private void doSetGamma(ByteBuffer native_gamma) throws LWJGLException {
/*  958:     */    
/*  959:     */    try {
/*  960: 673 */      setGammaRampOnTmpDisplay(native_gamma);
/*  961: 674 */      this.current_gamma = native_gamma;
/*  962:     */    } finally {
/*  963: 676 */      unlockAWT();
/*  964:     */    }
/*  965:     */  }
/*  966:     */  
/*  967:     */  private static void setGammaRampOnTmpDisplay(ByteBuffer native_gamma) throws LWJGLException {
/*  968:     */    
/*  969:     */    try {
/*  970: 683 */      nSetGammaRamp(getDisplay(), getDefaultScreen(), native_gamma);
/*  971:     */    } finally {
/*  972: 685 */      decDisplay();
/*  973:     */    }
/*  974:     */  }
/*  975:     */  
/*  976:     */  private static native void nSetGammaRamp(long paramLong, int paramInt, ByteBuffer paramByteBuffer) throws LWJGLException;
/*  977:     */  
/*  978: 691 */  private static ByteBuffer convertToNativeRamp(FloatBuffer ramp) throws LWJGLException { return nConvertToNativeRamp(ramp, ramp.position(), ramp.remaining()); }
/*  979:     */  
/*  980:     */  private static native ByteBuffer nConvertToNativeRamp(FloatBuffer paramFloatBuffer, int paramInt1, int paramInt2) throws LWJGLException;
/*  981:     */  
/*  982:     */  public String getAdapter() {
/*  983: 696 */    return null;
/*  984:     */  }
/*  985:     */  
/*  986:     */  public String getVersion() {
/*  987: 700 */    return null;
/*  988:     */  }
/*  989:     */  
/*  990:     */  public DisplayMode init() throws LWJGLException {
/*  991:     */    
/*  992:     */    try {
/*  993: 706 */      Compiz.init();
/*  994:     */      
/*  995: 708 */      this.delete_atom = internAtom("WM_DELETE_WINDOW", false);
/*  996: 709 */      this.current_displaymode_extension = getBestDisplayModeExtension();
/*  997: 710 */      if (this.current_displaymode_extension == 12)
/*  998: 711 */        throw new LWJGLException("No display mode extension is available");
/*  999: 712 */      DisplayMode[] modes = getAvailableDisplayModes();
/* 1000: 713 */      if ((modes == null) || (modes.length == 0))
/* 1001: 714 */        throw new LWJGLException("No modes available");
/* 1002: 715 */      switch (this.current_displaymode_extension) {
/* 1003:     */      case 10: 
/* 1004: 717 */        this.savedXrandrConfig = ((XRandR.Screen[])AccessController.doPrivileged(new PrivilegedAction()) {
/* 1005:     */          public XRandR.Screen[] run() {
/* 1006: 719 */            return XRandR.getConfiguration();
/* 1007:     */          }
/* 1008: 721 */        }());
/* 1009: 722 */        this.saved_mode = getCurrentXRandrMode();
/* 1010: 723 */        break;
/* 1011:     */      case 11: 
/* 1012: 725 */        this.saved_mode = modes[0];
/* 1013: 726 */        break;
/* 1014:     */      default: 
/* 1015: 728 */        throw new LWJGLException("Unknown display mode extension: " + this.current_displaymode_extension);
/* 1016:     */      }
/* 1017: 730 */      this.current_mode = this.saved_mode;
/* 1018: 731 */      this.saved_gamma = getCurrentGammaRamp();
/* 1019: 732 */      this.current_gamma = this.saved_gamma;
/* 1020: 733 */      return this.saved_mode;
/* 1021:     */    } finally {
/* 1022: 735 */      unlockAWT();
/* 1023:     */    }
/* 1024:     */  }
/* 1025:     */  
/* 1026:     */  /* Error */
/* 1027:     */  private static DisplayMode getCurrentXRandrMode()
/* 1028:     */    throws LWJGLException
/* 1029:     */  {
/* 1030:     */    // Byte code:
/* 1031:     */    //   0: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/* 1032:     */    //   3: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/* 1033:     */    //   6: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/* 1034:     */    //   9: invokestatic 19	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
/* 1035:     */    //   12: invokestatic 191	org/lwjgl/opengl/LinuxDisplay:nGetCurrentXRandrMode	(JI)Lorg/lwjgl/opengl/DisplayMode;
/* 1036:     */    //   15: astore_0
/* 1037:     */    //   16: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1038:     */    //   19: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1039:     */    //   22: aload_0
/* 1040:     */    //   23: areturn
/* 1041:     */    //   24: astore_1
/* 1042:     */    //   25: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1043:     */    //   28: aload_1
/* 1044:     */    //   29: athrow
/* 1045:     */    //   30: astore_2
/* 1046:     */    //   31: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1047:     */    //   34: aload_2
/* 1048:     */    //   35: athrow
/* 1049:     */    // Line number table:
/* 1050:     */    //   Java source line #740	-> byte code offset #0
/* 1051:     */    //   Java source line #742	-> byte code offset #3
/* 1052:     */    //   Java source line #744	-> byte code offset #6
/* 1053:     */    //   Java source line #746	-> byte code offset #16
/* 1054:     */    //   Java source line #749	-> byte code offset #19
/* 1055:     */    //   Java source line #746	-> byte code offset #24
/* 1056:     */    //   Java source line #749	-> byte code offset #30
/* 1057:     */    // Local variable table:
/* 1058:     */    //   start	length	slot	name	signature
/* 1059:     */    //   15	8	0	localDisplayMode	DisplayMode
/* 1060:     */    //   24	5	1	localObject1	Object
/* 1061:     */    //   30	5	2	localObject2	Object
/* 1062:     */    // Exception table:
/* 1063:     */    //   from	to	target	type
/* 1064:     */    //   6	16	24	finally
/* 1065:     */    //   24	25	24	finally
/* 1066:     */    //   3	19	30	finally
/* 1067:     */    //   24	31	30	finally
/* 1068:     */  }
/* 1069:     */  
/* 1070:     */  private static native DisplayMode nGetCurrentXRandrMode(long paramLong, int paramInt)
/* 1071:     */    throws LWJGLException;
/* 1072:     */  
/* 1073:     */  public void setTitle(String title)
/* 1074:     */  {
/* 1075:     */    
/* 1076:     */    try
/* 1077:     */    {
/* 1078: 759 */      ByteBuffer titleText = MemoryUtil.encodeUTF8(title);
/* 1079: 760 */      nSetTitle(getDisplay(), getWindow(), MemoryUtil.getAddress(titleText), titleText.remaining() - 1);
/* 1080:     */    } finally {
/* 1081: 762 */      unlockAWT();
/* 1082:     */    }
/* 1083:     */  }
/* 1084:     */  
/* 1085:     */  private static native void nSetTitle(long paramLong1, long paramLong2, long paramLong3, int paramInt);
/* 1086:     */  
/* 1087: 768 */  public boolean isCloseRequested() { boolean result = this.close_requested;
/* 1088: 769 */    this.close_requested = false;
/* 1089: 770 */    return result;
/* 1090:     */  }
/* 1091:     */  
/* 1092:     */  public boolean isVisible() {
/* 1093: 774 */    return !this.minimized;
/* 1094:     */  }
/* 1095:     */  
/* 1096:     */  public boolean isActive() {
/* 1097: 778 */    return (this.focused) || (isLegacyFullscreen());
/* 1098:     */  }
/* 1099:     */  
/* 1100:     */  public boolean isDirty() {
/* 1101: 782 */    boolean result = this.dirty;
/* 1102: 783 */    this.dirty = false;
/* 1103: 784 */    return result;
/* 1104:     */  }
/* 1105:     */  
/* 1106:     */  public PeerInfo createPeerInfo(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
/* 1107: 788 */    this.peer_info = new LinuxDisplayPeerInfo(pixel_format);
/* 1108: 789 */    return this.peer_info;
/* 1109:     */  }
/* 1110:     */  
/* 1111:     */  private void relayEventToParent(LinuxEvent event_buffer, int event_mask) {
/* 1112: 793 */    this.tmp_event_buffer.copyFrom(event_buffer);
/* 1113: 794 */    this.tmp_event_buffer.setWindow(this.parent_window);
/* 1114: 795 */    this.tmp_event_buffer.sendEvent(getDisplay(), this.parent_window, true, event_mask);
/* 1115:     */  }
/* 1116:     */  
/* 1117:     */  private void relayEventToParent(LinuxEvent event_buffer) {
/* 1118: 799 */    if (this.parent == null)
/* 1119: 800 */      return;
/* 1120: 801 */    switch (event_buffer.getType()) {
/* 1121:     */    case 2: 
/* 1122: 803 */      relayEventToParent(event_buffer, 1);
/* 1123: 804 */      break;
/* 1124:     */    case 3: 
/* 1125: 806 */      relayEventToParent(event_buffer, 1);
/* 1126: 807 */      break;
/* 1127:     */    case 4: 
/* 1128: 809 */      if ((xembedded) || (!this.focused)) relayEventToParent(event_buffer, 1); break;
/* 1129:     */    
/* 1130:     */    case 5: 
/* 1131: 812 */      if ((xembedded) || (!this.focused)) relayEventToParent(event_buffer, 1); break;
/* 1132:     */    }
/* 1133:     */    
/* 1134:     */  }
/* 1135:     */  
/* 1137:     */  private void processEvents()
/* 1138:     */  {
/* 1139: 820 */    while (LinuxEvent.getPending(getDisplay()) > 0) {
/* 1140: 821 */      this.event_buffer.nextEvent(getDisplay());
/* 1141: 822 */      long event_window = this.event_buffer.getWindow();
/* 1142: 823 */      relayEventToParent(this.event_buffer);
/* 1143: 824 */      if ((event_window == getWindow()) && (!this.event_buffer.filterEvent(event_window)) && ((this.mouse == null) || (!this.mouse.filterEvent(this.grab, shouldWarpPointer(), this.event_buffer))) && ((this.keyboard == null) || (!this.keyboard.filterEvent(this.event_buffer))))
/* 1144:     */      {
/* 1147: 828 */        switch (this.event_buffer.getType()) {
/* 1148:     */        case 9: 
/* 1149: 830 */          setFocused(true, this.event_buffer.getFocusDetail());
/* 1150: 831 */          break;
/* 1151:     */        case 10: 
/* 1152: 833 */          setFocused(false, this.event_buffer.getFocusDetail());
/* 1153: 834 */          break;
/* 1154:     */        case 33: 
/* 1155: 836 */          if ((this.event_buffer.getClientFormat() == 32) && (this.event_buffer.getClientData(0) == this.delete_atom))
/* 1156: 837 */            this.close_requested = true; break;
/* 1157:     */        
/* 1158:     */        case 19: 
/* 1159: 840 */          this.dirty = true;
/* 1160: 841 */          this.minimized = false;
/* 1161: 842 */          break;
/* 1162:     */        case 18: 
/* 1163: 844 */          this.dirty = true;
/* 1164: 845 */          this.minimized = true;
/* 1165: 846 */          break;
/* 1166:     */        case 12: 
/* 1167: 848 */          this.dirty = true;
/* 1168: 849 */          break;
/* 1169:     */        case 22: 
/* 1170: 851 */          int x = nGetX(getDisplay(), getWindow());
/* 1171: 852 */          int y = nGetY(getDisplay(), getWindow());
/* 1172:     */          
/* 1173: 854 */          int width = nGetWidth(getDisplay(), getWindow());
/* 1174: 855 */          int height = nGetHeight(getDisplay(), getWindow());
/* 1175:     */          
/* 1176: 857 */          this.window_x = x;
/* 1177: 858 */          this.window_y = y;
/* 1178:     */          
/* 1179: 860 */          if ((this.window_width != width) || (this.window_height != height)) {
/* 1180: 861 */            this.resized = true;
/* 1181: 862 */            this.window_width = width;
/* 1182: 863 */            this.window_height = height; } break;
/* 1183:     */        
/* 1186:     */        case 7: 
/* 1187: 868 */          this.mouseInside = true;
/* 1188: 869 */          break;
/* 1189:     */        case 8: 
/* 1190: 871 */          this.mouseInside = false;
/* 1191:     */        }
/* 1192:     */      }
/* 1193:     */    }
/* 1194:     */  }
/* 1195:     */  
/* 1196:     */  public void update()
/* 1197:     */  {
/* 1198:     */    
/* 1199:     */    try
/* 1200:     */    {
/* 1201: 882 */      processEvents();
/* 1202: 883 */      checkInput();
/* 1203:     */    } finally {
/* 1204: 885 */      unlockAWT();
/* 1205:     */    }
/* 1206:     */  }
/* 1207:     */  
/* 1208:     */  public void reshape(int x, int y, int width, int height) {
/* 1209:     */    
/* 1210:     */    try {
/* 1211: 892 */      nReshape(getDisplay(), getWindow(), x, y, width, height);
/* 1212:     */    } finally {
/* 1213: 894 */      unlockAWT();
/* 1214:     */    }
/* 1215:     */  }
/* 1216:     */  
/* 1217:     */  private static native void nReshape(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/* 1218:     */  
/* 1219:     */  /* Error */
/* 1220:     */  public DisplayMode[] getAvailableDisplayModes()
/* 1221:     */    throws LWJGLException
/* 1222:     */  {
/* 1223:     */    // Byte code:
/* 1224:     */    //   0: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/* 1225:     */    //   3: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/* 1226:     */    //   6: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/* 1227:     */    //   9: invokestatic 19	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
/* 1228:     */    //   12: aload_0
/* 1229:     */    //   13: getfield 9	org/lwjgl/opengl/LinuxDisplay:current_displaymode_extension	I
/* 1230:     */    //   16: invokestatic 222	org/lwjgl/opengl/LinuxDisplay:nGetAvailableDisplayModes	(JII)[Lorg/lwjgl/opengl/DisplayMode;
/* 1231:     */    //   19: astore_1
/* 1232:     */    //   20: aload_1
/* 1233:     */    //   21: astore_2
/* 1234:     */    //   22: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1235:     */    //   25: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1236:     */    //   28: aload_2
/* 1237:     */    //   29: areturn
/* 1238:     */    //   30: astore_3
/* 1239:     */    //   31: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1240:     */    //   34: aload_3
/* 1241:     */    //   35: athrow
/* 1242:     */    //   36: astore 4
/* 1243:     */    //   38: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1244:     */    //   41: aload 4
/* 1245:     */    //   43: athrow
/* 1246:     */    // Line number table:
/* 1247:     */    //   Java source line #900	-> byte code offset #0
/* 1248:     */    //   Java source line #902	-> byte code offset #3
/* 1249:     */    //   Java source line #904	-> byte code offset #6
/* 1250:     */    //   Java source line #905	-> byte code offset #20
/* 1251:     */    //   Java source line #907	-> byte code offset #22
/* 1252:     */    //   Java source line #910	-> byte code offset #25
/* 1253:     */    //   Java source line #907	-> byte code offset #30
/* 1254:     */    //   Java source line #910	-> byte code offset #36
/* 1255:     */    // Local variable table:
/* 1256:     */    //   start	length	slot	name	signature
/* 1257:     */    //   0	44	0	this	LinuxDisplay
/* 1258:     */    //   19	2	1	modes	DisplayMode[]
/* 1259:     */    //   21	8	2	arrayOfDisplayMode1	DisplayMode[]
/* 1260:     */    //   30	5	3	localObject1	Object
/* 1261:     */    //   36	6	4	localObject2	Object
/* 1262:     */    // Exception table:
/* 1263:     */    //   from	to	target	type
/* 1264:     */    //   6	22	30	finally
/* 1265:     */    //   30	31	30	finally
/* 1266:     */    //   3	25	36	finally
/* 1267:     */    //   30	38	36	finally
/* 1268:     */  }
/* 1269:     */  
/* 1270:     */  private static native DisplayMode[] nGetAvailableDisplayModes(long paramLong, int paramInt1, int paramInt2)
/* 1271:     */    throws LWJGLException;
/* 1272:     */  
/* 1273:     */  public boolean hasWheel()
/* 1274:     */  {
/* 1275: 917 */    return true;
/* 1276:     */  }
/* 1277:     */  
/* 1278:     */  public int getButtonCount() {
/* 1279: 921 */    return this.mouse.getButtonCount();
/* 1280:     */  }
/* 1281:     */  
/* 1282:     */  public void createMouse() throws LWJGLException {
/* 1283:     */    
/* 1284:     */    try {
/* 1285: 927 */      this.mouse = new LinuxMouse(getDisplay(), getWindow(), getWindow());
/* 1286:     */    } finally {
/* 1287: 929 */      unlockAWT();
/* 1288:     */    }
/* 1289:     */  }
/* 1290:     */  
/* 1291:     */  public void destroyMouse() {
/* 1292: 934 */    this.mouse = null;
/* 1293: 935 */    updateInputGrab();
/* 1294:     */  }
/* 1295:     */  
/* 1296:     */  public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons) {
/* 1297:     */    
/* 1298:     */    try {
/* 1299: 941 */      this.mouse.poll(this.grab, coord_buffer, buttons);
/* 1300:     */    } finally {
/* 1301: 943 */      unlockAWT();
/* 1302:     */    }
/* 1303:     */  }
/* 1304:     */  
/* 1305:     */  public void readMouse(ByteBuffer buffer) {
/* 1306:     */    
/* 1307:     */    try {
/* 1308: 950 */      this.mouse.read(buffer);
/* 1309:     */    } finally {
/* 1310: 952 */      unlockAWT();
/* 1311:     */    }
/* 1312:     */  }
/* 1313:     */  
/* 1314:     */  public void setCursorPosition(int x, int y) {
/* 1315:     */    
/* 1316:     */    try {
/* 1317: 959 */      this.mouse.setCursorPosition(x, y);
/* 1318:     */    } finally {
/* 1319: 961 */      unlockAWT();
/* 1320:     */    }
/* 1321:     */  }
/* 1322:     */  
/* 1323:     */  private void checkInput() {
/* 1324: 966 */    if (this.parent == null) { return;
/* 1325:     */    }
/* 1326: 968 */    if (xembedded) {
/* 1327: 969 */      long current_focus_window = 0L;
/* 1328:     */      
/* 1329: 971 */      if ((this.last_window_focus != current_focus_window) || (this.parent_focused != this.focused)) {
/* 1330: 972 */        if (isParentWindowActive(current_focus_window)) {
/* 1331: 973 */          if (this.parent_focused) {
/* 1332: 974 */            nSetInputFocus(getDisplay(), current_window, 0L);
/* 1333: 975 */            this.last_window_focus = current_window;
/* 1334: 976 */            this.focused = true;
/* 1335:     */          }
/* 1336:     */          else
/* 1337:     */          {
/* 1338: 980 */            nSetInputFocus(getDisplay(), this.parent_proxy_focus_window, 0L);
/* 1339: 981 */            this.last_window_focus = this.parent_proxy_focus_window;
/* 1340: 982 */            this.focused = false;
/* 1341:     */          }
/* 1342:     */        }
/* 1343:     */        else {
/* 1344: 986 */          this.last_window_focus = current_focus_window;
/* 1345: 987 */          this.focused = false;
/* 1346:     */        }
/* 1347:     */        
/* 1348:     */      }
/* 1349:     */    }
/* 1350: 992 */    else if ((this.parent_focus_changed) && (this.parent_focused)) {
/* 1351: 993 */      setInputFocusUnsafe(getWindow());
/* 1352: 994 */      this.parent_focus_changed = false;
/* 1353:     */    }
/* 1354:     */  }
/* 1355:     */  
/* 1356:     */  private void setInputFocusUnsafe(long window)
/* 1357:     */  {
/* 1358:     */    try {
/* 1359:1001 */      nSetInputFocus(getDisplay(), window, 0L);
/* 1360:1002 */      nSync(getDisplay(), false);
/* 1361:     */    }
/* 1362:     */    catch (LWJGLException e) {
/* 1363:1005 */      LWJGLUtil.log("Got exception while trying to focus: " + e);
/* 1364:     */    }
/* 1365:     */  }
/* 1366:     */  
/* 1367:     */  private PeerInfo peer_info;
/* 1368:     */  private ByteBuffer saved_gamma;
/* 1369:     */  private ByteBuffer current_gamma;
/* 1370:     */  private DisplayMode saved_mode;
/* 1371:     */  private DisplayMode current_mode;
/* 1372:     */  private XRandR.Screen[] savedXrandrConfig;
/* 1373:     */  private boolean keyboard_grabbed;
/* 1374:     */  private boolean pointer_grabbed;
/* 1375:     */  private boolean input_released;
/* 1376:     */  private boolean grab;
/* 1377:     */  private boolean focused;
/* 1378:     */  private static native void nSync(long paramLong, boolean paramBoolean) throws LWJGLException;
/* 1379:     */  
/* 1380:     */  private boolean isParentWindowActive(long window) {
/* 1381:     */    try {
/* 1382:1024 */      if (window == current_window) { return true;
/* 1383:     */      }
/* 1384:     */      
/* 1385:1027 */      if (getChildCount(getDisplay(), window) != 0) { return false;
/* 1386:     */      }
/* 1387:     */      
/* 1388:1030 */      long parent_window = getParentWindow(getDisplay(), window);
/* 1389:     */      
/* 1391:1033 */      if (parent_window == 0L) { return false;
/* 1392:     */      }
/* 1393:     */      
/* 1394:1036 */      long w = current_window;
/* 1395:     */      
/* 1396:1038 */      while (w != 0L) {
/* 1397:1039 */        w = getParentWindow(getDisplay(), w);
/* 1398:1040 */        if (w == parent_window) {
/* 1399:1041 */          this.parent_proxy_focus_window = window;
/* 1400:1042 */          return true;
/* 1401:     */        }
/* 1402:     */      }
/* 1403:     */    } catch (LWJGLException e) {
/* 1404:1046 */      LWJGLUtil.log("Failed to detect if parent window is active: " + e.getMessage());
/* 1405:1047 */      return true;
/* 1406:     */    }
/* 1407:     */    
/* 1408:1050 */    return false;
/* 1409:     */  }
/* 1410:     */  
/* 1411:     */  private void setFocused(boolean got_focus, int focus_detail) {
/* 1412:1054 */    if ((this.focused == got_focus) || (focus_detail == 7) || (focus_detail == 5) || (focus_detail == 6) || (xembedded))
/* 1413:1055 */      return;
/* 1414:1056 */    this.focused = got_focus;
/* 1415:     */    
/* 1416:1058 */    if (this.focused) {
/* 1417:1059 */      acquireInput();
/* 1418:     */    }
/* 1419:     */    else {
/* 1420:1062 */      releaseInput();
/* 1421:     */    }
/* 1422:     */  }
/* 1423:     */  
/* 1424:     */  private void releaseInput() {
/* 1425:1067 */    if ((isLegacyFullscreen()) || (this.input_released))
/* 1426:1068 */      return;
/* 1427:1069 */    this.input_released = true;
/* 1428:1070 */    updateInputGrab();
/* 1429:1071 */    if (current_window_mode == 2) {
/* 1430:1072 */      nIconifyWindow(getDisplay(), getWindow(), getDefaultScreen());
/* 1431:     */      try {
/* 1432:1074 */        if ((this.current_displaymode_extension == 10) && (this.savedXrandrConfig.length > 0))
/* 1433:     */        {
/* 1434:1076 */          AccessController.doPrivileged(new PrivilegedAction() {
/* 1435:     */            public Object run() {
/* 1436:1078 */              XRandR.setConfiguration(LinuxDisplay.this.savedXrandrConfig);
/* 1437:1079 */              return null;
/* 1438:     */            }
/* 1439:     */            
/* 1441:     */          });
/* 1442:     */        } else {
/* 1443:1085 */          switchDisplayModeOnTmpDisplay(this.saved_mode);
/* 1444:     */        }
/* 1445:1087 */        setGammaRampOnTmpDisplay(this.saved_gamma);
/* 1446:     */      } catch (LWJGLException e) {
/* 1447:1089 */        LWJGLUtil.log("Failed to restore saved mode: " + e.getMessage());
/* 1448:     */      }
/* 1449:     */    }
/* 1450:     */  }
/* 1451:     */  
/* 1452:     */  private static native void nIconifyWindow(long paramLong1, long paramLong2, int paramInt);
/* 1453:     */  
/* 1454:1096 */  private void acquireInput() { if ((isLegacyFullscreen()) || (!this.input_released))
/* 1455:1097 */      return;
/* 1456:1098 */    this.input_released = false;
/* 1457:1099 */    updateInputGrab();
/* 1458:1100 */    if (current_window_mode == 2) {
/* 1459:     */      try {
/* 1460:1102 */        switchDisplayModeOnTmpDisplay(this.current_mode);
/* 1461:1103 */        setGammaRampOnTmpDisplay(this.current_gamma);
/* 1462:     */      } catch (LWJGLException e) {
/* 1463:1105 */        LWJGLUtil.log("Failed to restore mode: " + e.getMessage());
/* 1464:     */      }
/* 1465:     */    }
/* 1466:     */  }
/* 1467:     */  
/* 1468:     */  public void grabMouse(boolean new_grab) {
/* 1469:     */    
/* 1470:     */    try {
/* 1471:1113 */      if (new_grab != this.grab) {
/* 1472:1114 */        this.grab = new_grab;
/* 1473:1115 */        updateInputGrab();
/* 1474:1116 */        this.mouse.changeGrabbed(this.grab, shouldWarpPointer());
/* 1475:     */      }
/* 1476:     */    } finally {
/* 1477:1119 */      unlockAWT();
/* 1478:     */    }
/* 1479:     */  }
/* 1480:     */  
/* 1481:     */  private boolean shouldWarpPointer() {
/* 1482:1124 */    return (this.pointer_grabbed) && (shouldGrab());
/* 1483:     */  }
/* 1484:     */  
/* 1485:     */  /* Error */
/* 1486:     */  public int getNativeCursorCapabilities()
/* 1487:     */  {
/* 1488:     */    // Byte code:
/* 1489:     */    //   0: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/* 1490:     */    //   3: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/* 1491:     */    //   6: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/* 1492:     */    //   9: invokestatic 246	org/lwjgl/opengl/LinuxDisplay:nGetNativeCursorCapabilities	(J)I
/* 1493:     */    //   12: istore_1
/* 1494:     */    //   13: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1495:     */    //   16: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1496:     */    //   19: iload_1
/* 1497:     */    //   20: ireturn
/* 1498:     */    //   21: astore_2
/* 1499:     */    //   22: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1500:     */    //   25: aload_2
/* 1501:     */    //   26: athrow
/* 1502:     */    //   27: astore_1
/* 1503:     */    //   28: new 247	java/lang/RuntimeException
/* 1504:     */    //   31: dup
/* 1505:     */    //   32: aload_1
/* 1506:     */    //   33: invokespecial 248	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
/* 1507:     */    //   36: athrow
/* 1508:     */    //   37: astore_3
/* 1509:     */    //   38: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1510:     */    //   41: aload_3
/* 1511:     */    //   42: athrow
/* 1512:     */    // Line number table:
/* 1513:     */    //   Java source line #1128	-> byte code offset #0
/* 1514:     */    //   Java source line #1130	-> byte code offset #3
/* 1515:     */    //   Java source line #1132	-> byte code offset #6
/* 1516:     */    //   Java source line #1134	-> byte code offset #13
/* 1517:     */    //   Java source line #1139	-> byte code offset #16
/* 1518:     */    //   Java source line #1134	-> byte code offset #21
/* 1519:     */    //   Java source line #1136	-> byte code offset #27
/* 1520:     */    //   Java source line #1137	-> byte code offset #28
/* 1521:     */    //   Java source line #1139	-> byte code offset #37
/* 1522:     */    // Local variable table:
/* 1523:     */    //   start	length	slot	name	signature
/* 1524:     */    //   0	43	0	this	LinuxDisplay
/* 1525:     */    //   12	8	1	i	int
/* 1526:     */    //   27	6	1	e	LWJGLException
/* 1527:     */    //   21	5	2	localObject1	Object
/* 1528:     */    //   37	5	3	localObject2	Object
/* 1529:     */    // Exception table:
/* 1530:     */    //   from	to	target	type
/* 1531:     */    //   6	13	21	finally
/* 1532:     */    //   21	22	21	finally
/* 1533:     */    //   3	16	27	org/lwjgl/LWJGLException
/* 1534:     */    //   21	27	27	org/lwjgl/LWJGLException
/* 1535:     */    //   3	16	37	finally
/* 1536:     */    //   21	38	37	finally
/* 1537:     */  }
/* 1538:     */  
/* 1539:     */  private static native int nGetNativeCursorCapabilities(long paramLong)
/* 1540:     */    throws LWJGLException;
/* 1541:     */  
/* 1542:     */  public void setNativeCursor(Object handle)
/* 1543:     */    throws LWJGLException
/* 1544:     */  {
/* 1545:1145 */    this.current_cursor = getCursorHandle(handle);
/* 1546:1146 */    lockAWT();
/* 1547:     */    try {
/* 1548:1148 */      updateCursor();
/* 1549:     */    } finally {
/* 1550:1150 */      unlockAWT();
/* 1551:     */    }
/* 1552:     */  }
/* 1553:     */  
/* 1554:     */  /* Error */
/* 1555:     */  public int getMinCursorSize()
/* 1556:     */  {
/* 1557:     */    // Byte code:
/* 1558:     */    //   0: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/* 1559:     */    //   3: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/* 1560:     */    //   6: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/* 1561:     */    //   9: invokestatic 79	org/lwjgl/opengl/LinuxDisplay:getWindow	()J
/* 1562:     */    //   12: invokestatic 250	org/lwjgl/opengl/LinuxDisplay:nGetMinCursorSize	(JJ)I
/* 1563:     */    //   15: istore_1
/* 1564:     */    //   16: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1565:     */    //   19: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1566:     */    //   22: iload_1
/* 1567:     */    //   23: ireturn
/* 1568:     */    //   24: astore_2
/* 1569:     */    //   25: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1570:     */    //   28: aload_2
/* 1571:     */    //   29: athrow
/* 1572:     */    //   30: astore_1
/* 1573:     */    //   31: new 32	java/lang/StringBuilder
/* 1574:     */    //   34: dup
/* 1575:     */    //   35: invokespecial 33	java/lang/StringBuilder:<init>	()V
/* 1576:     */    //   38: ldc 251
/* 1577:     */    //   40: invokevirtual 35	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* 1578:     */    //   43: aload_1
/* 1579:     */    //   44: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/* 1580:     */    //   47: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
/* 1581:     */    //   50: invokestatic 25	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
/* 1582:     */    //   53: iconst_0
/* 1583:     */    //   54: istore_2
/* 1584:     */    //   55: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1585:     */    //   58: iload_2
/* 1586:     */    //   59: ireturn
/* 1587:     */    //   60: astore_3
/* 1588:     */    //   61: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1589:     */    //   64: aload_3
/* 1590:     */    //   65: athrow
/* 1591:     */    // Line number table:
/* 1592:     */    //   Java source line #1155	-> byte code offset #0
/* 1593:     */    //   Java source line #1157	-> byte code offset #3
/* 1594:     */    //   Java source line #1159	-> byte code offset #6
/* 1595:     */    //   Java source line #1161	-> byte code offset #16
/* 1596:     */    //   Java source line #1167	-> byte code offset #19
/* 1597:     */    //   Java source line #1161	-> byte code offset #24
/* 1598:     */    //   Java source line #1163	-> byte code offset #30
/* 1599:     */    //   Java source line #1164	-> byte code offset #31
/* 1600:     */    //   Java source line #1165	-> byte code offset #53
/* 1601:     */    //   Java source line #1167	-> byte code offset #55
/* 1602:     */    // Local variable table:
/* 1603:     */    //   start	length	slot	name	signature
/* 1604:     */    //   0	66	0	this	LinuxDisplay
/* 1605:     */    //   15	8	1	i	int
/* 1606:     */    //   30	14	1	e	LWJGLException
/* 1607:     */    //   24	5	2	localObject1	Object
/* 1608:     */    //   54	5	2	j	int
/* 1609:     */    //   60	5	3	localObject2	Object
/* 1610:     */    // Exception table:
/* 1611:     */    //   from	to	target	type
/* 1612:     */    //   6	16	24	finally
/* 1613:     */    //   24	25	24	finally
/* 1614:     */    //   3	19	30	org/lwjgl/LWJGLException
/* 1615:     */    //   24	30	30	org/lwjgl/LWJGLException
/* 1616:     */    //   3	19	60	finally
/* 1617:     */    //   24	55	60	finally
/* 1618:     */    //   60	61	60	finally
/* 1619:     */  }
/* 1620:     */  
/* 1621:     */  private static native int nGetMinCursorSize(long paramLong1, long paramLong2);
/* 1622:     */  
/* 1623:     */  /* Error */
/* 1624:     */  public int getMaxCursorSize()
/* 1625:     */  {
/* 1626:     */    // Byte code:
/* 1627:     */    //   0: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/* 1628:     */    //   3: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/* 1629:     */    //   6: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/* 1630:     */    //   9: invokestatic 79	org/lwjgl/opengl/LinuxDisplay:getWindow	()J
/* 1631:     */    //   12: invokestatic 252	org/lwjgl/opengl/LinuxDisplay:nGetMaxCursorSize	(JJ)I
/* 1632:     */    //   15: istore_1
/* 1633:     */    //   16: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1634:     */    //   19: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1635:     */    //   22: iload_1
/* 1636:     */    //   23: ireturn
/* 1637:     */    //   24: astore_2
/* 1638:     */    //   25: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1639:     */    //   28: aload_2
/* 1640:     */    //   29: athrow
/* 1641:     */    //   30: astore_1
/* 1642:     */    //   31: new 32	java/lang/StringBuilder
/* 1643:     */    //   34: dup
/* 1644:     */    //   35: invokespecial 33	java/lang/StringBuilder:<init>	()V
/* 1645:     */    //   38: ldc 253
/* 1646:     */    //   40: invokevirtual 35	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* 1647:     */    //   43: aload_1
/* 1648:     */    //   44: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/* 1649:     */    //   47: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
/* 1650:     */    //   50: invokestatic 25	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
/* 1651:     */    //   53: iconst_0
/* 1652:     */    //   54: istore_2
/* 1653:     */    //   55: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1654:     */    //   58: iload_2
/* 1655:     */    //   59: ireturn
/* 1656:     */    //   60: astore_3
/* 1657:     */    //   61: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1658:     */    //   64: aload_3
/* 1659:     */    //   65: athrow
/* 1660:     */    // Line number table:
/* 1661:     */    //   Java source line #1173	-> byte code offset #0
/* 1662:     */    //   Java source line #1175	-> byte code offset #3
/* 1663:     */    //   Java source line #1177	-> byte code offset #6
/* 1664:     */    //   Java source line #1179	-> byte code offset #16
/* 1665:     */    //   Java source line #1185	-> byte code offset #19
/* 1666:     */    //   Java source line #1179	-> byte code offset #24
/* 1667:     */    //   Java source line #1181	-> byte code offset #30
/* 1668:     */    //   Java source line #1182	-> byte code offset #31
/* 1669:     */    //   Java source line #1183	-> byte code offset #53
/* 1670:     */    //   Java source line #1185	-> byte code offset #55
/* 1671:     */    // Local variable table:
/* 1672:     */    //   start	length	slot	name	signature
/* 1673:     */    //   0	66	0	this	LinuxDisplay
/* 1674:     */    //   15	8	1	i	int
/* 1675:     */    //   30	14	1	e	LWJGLException
/* 1676:     */    //   24	5	2	localObject1	Object
/* 1677:     */    //   54	5	2	j	int
/* 1678:     */    //   60	5	3	localObject2	Object
/* 1679:     */    // Exception table:
/* 1680:     */    //   from	to	target	type
/* 1681:     */    //   6	16	24	finally
/* 1682:     */    //   24	25	24	finally
/* 1683:     */    //   3	19	30	org/lwjgl/LWJGLException
/* 1684:     */    //   24	30	30	org/lwjgl/LWJGLException
/* 1685:     */    //   3	19	60	finally
/* 1686:     */    //   24	55	60	finally
/* 1687:     */    //   60	61	60	finally
/* 1688:     */  }
/* 1689:     */  
/* 1690:     */  private static native int nGetMaxCursorSize(long paramLong1, long paramLong2);
/* 1691:     */  
/* 1692:     */  public void createKeyboard()
/* 1693:     */    throws LWJGLException
/* 1694:     */  {
/* 1695:     */    
/* 1696:     */    try
/* 1697:     */    {
/* 1698:1194 */      this.keyboard = new LinuxKeyboard(getDisplay(), getWindow());
/* 1699:     */    } finally {
/* 1700:1196 */      unlockAWT();
/* 1701:     */    }
/* 1702:     */  }
/* 1703:     */  
/* 1704:     */  public void destroyKeyboard() {
/* 1705:     */    
/* 1706:     */    try {
/* 1707:1203 */      this.keyboard.destroy(getDisplay());
/* 1708:1204 */      this.keyboard = null;
/* 1709:     */    } finally {
/* 1710:1206 */      unlockAWT();
/* 1711:     */    }
/* 1712:     */  }
/* 1713:     */  
/* 1714:     */  public void pollKeyboard(ByteBuffer keyDownBuffer) {
/* 1715:     */    
/* 1716:     */    try {
/* 1717:1213 */      this.keyboard.poll(keyDownBuffer);
/* 1718:     */    } finally {
/* 1719:1215 */      unlockAWT();
/* 1720:     */    }
/* 1721:     */  }
/* 1722:     */  
/* 1723:     */  public void readKeyboard(ByteBuffer buffer) {
/* 1724:     */    
/* 1725:     */    try {
/* 1726:1222 */      this.keyboard.read(buffer);
/* 1727:     */    } finally {
/* 1728:1224 */      unlockAWT();
/* 1729:     */    }
/* 1730:     */  }
/* 1731:     */  
/* 1732:     */  private static native long nCreateCursor(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, IntBuffer paramIntBuffer1, int paramInt6, IntBuffer paramIntBuffer2, int paramInt7) throws LWJGLException;
/* 1733:     */  
/* 1734:     */  private static long createBlankCursor() {
/* 1735:1231 */    return nCreateBlankCursor(getDisplay(), getWindow());
/* 1736:     */  }
/* 1737:     */  
/* 1738:     */  static native long nCreateBlankCursor(long paramLong1, long paramLong2);
/* 1739:     */  
/* 1740:     */  /* Error */
/* 1741:     */  public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays)
/* 1742:     */    throws LWJGLException
/* 1743:     */  {
/* 1744:     */    // Byte code:
/* 1745:     */    //   0: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/* 1746:     */    //   3: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/* 1747:     */    //   6: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/* 1748:     */    //   9: iload_1
/* 1749:     */    //   10: iload_2
/* 1750:     */    //   11: iload_3
/* 1751:     */    //   12: iload 4
/* 1752:     */    //   14: iload 5
/* 1753:     */    //   16: aload 6
/* 1754:     */    //   18: aload 6
/* 1755:     */    //   20: invokevirtual 260	java/nio/IntBuffer:position	()I
/* 1756:     */    //   23: aload 7
/* 1757:     */    //   25: aload 7
/* 1758:     */    //   27: ifnull +11 -> 38
/* 1759:     */    //   30: aload 7
/* 1760:     */    //   32: invokevirtual 260	java/nio/IntBuffer:position	()I
/* 1761:     */    //   35: goto +4 -> 39
/* 1762:     */    //   38: iconst_m1
/* 1763:     */    //   39: invokestatic 261	org/lwjgl/opengl/LinuxDisplay:nCreateCursor	(JIIIIILjava/nio/IntBuffer;ILjava/nio/IntBuffer;I)J
/* 1764:     */    //   42: lstore 8
/* 1765:     */    //   44: lload 8
/* 1766:     */    //   46: invokestatic 262	java/lang/Long:valueOf	(J)Ljava/lang/Long;
/* 1767:     */    //   49: astore 10
/* 1768:     */    //   51: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1769:     */    //   54: aload 10
/* 1770:     */    //   56: areturn
/* 1771:     */    //   57: astore 8
/* 1772:     */    //   59: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1773:     */    //   62: aload 8
/* 1774:     */    //   64: athrow
/* 1775:     */    //   65: astore 11
/* 1776:     */    //   67: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1777:     */    //   70: aload 11
/* 1778:     */    //   72: athrow
/* 1779:     */    // Line number table:
/* 1780:     */    //   Java source line #1236	-> byte code offset #0
/* 1781:     */    //   Java source line #1238	-> byte code offset #3
/* 1782:     */    //   Java source line #1240	-> byte code offset #6
/* 1783:     */    //   Java source line #1241	-> byte code offset #44
/* 1784:     */    //   Java source line #1247	-> byte code offset #51
/* 1785:     */    //   Java source line #1242	-> byte code offset #57
/* 1786:     */    //   Java source line #1243	-> byte code offset #59
/* 1787:     */    //   Java source line #1244	-> byte code offset #62
/* 1788:     */    //   Java source line #1247	-> byte code offset #65
/* 1789:     */    // Local variable table:
/* 1790:     */    //   start	length	slot	name	signature
/* 1791:     */    //   0	73	0	this	LinuxDisplay
/* 1792:     */    //   0	73	1	width	int
/* 1793:     */    //   0	73	2	height	int
/* 1794:     */    //   0	73	3	xHotspot	int
/* 1795:     */    //   0	73	4	yHotspot	int
/* 1796:     */    //   0	73	5	numImages	int
/* 1797:     */    //   0	73	6	images	IntBuffer
/* 1798:     */    //   0	73	7	delays	IntBuffer
/* 1799:     */    //   42	3	8	cursor	long
/* 1800:     */    //   57	6	8	e	LWJGLException
/* 1801:     */    //   49	6	10	localLong	Long
/* 1802:     */    //   65	6	11	localObject	Object
/* 1803:     */    // Exception table:
/* 1804:     */    //   from	to	target	type
/* 1805:     */    //   6	51	57	org/lwjgl/LWJGLException
/* 1806:     */    //   3	51	65	finally
/* 1807:     */    //   57	67	65	finally
/* 1808:     */  }
/* 1809:     */  
/* 1810:     */  private static long getCursorHandle(Object cursor_handle)
/* 1811:     */  {
/* 1812:1252 */    return cursor_handle != null ? ((Long)cursor_handle).longValue() : 0L;
/* 1813:     */  }
/* 1814:     */  
/* 1815:     */  public void destroyCursor(Object cursorHandle) {
/* 1816:     */    
/* 1817:     */    try {
/* 1818:1258 */      nDestroyCursor(getDisplay(), getCursorHandle(cursorHandle));
/* 1819:1259 */      decDisplay();
/* 1820:     */    } finally {
/* 1821:1261 */      unlockAWT();
/* 1822:     */    }
/* 1823:     */  }
/* 1824:     */  
/* 1825:     */  static native void nDestroyCursor(long paramLong1, long paramLong2);
/* 1826:     */  
/* 1827:     */  /* Error */
/* 1828:     */  public int getPbufferCapabilities()
/* 1829:     */  {
/* 1830:     */    // Byte code:
/* 1831:     */    //   0: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/* 1832:     */    //   3: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/* 1833:     */    //   6: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/* 1834:     */    //   9: invokestatic 19	org/lwjgl/opengl/LinuxDisplay:getDefaultScreen	()I
/* 1835:     */    //   12: invokestatic 265	org/lwjgl/opengl/LinuxDisplay:nGetPbufferCapabilities	(JI)I
/* 1836:     */    //   15: istore_1
/* 1837:     */    //   16: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1838:     */    //   19: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1839:     */    //   22: iload_1
/* 1840:     */    //   23: ireturn
/* 1841:     */    //   24: astore_2
/* 1842:     */    //   25: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 1843:     */    //   28: aload_2
/* 1844:     */    //   29: athrow
/* 1845:     */    //   30: astore_1
/* 1846:     */    //   31: new 32	java/lang/StringBuilder
/* 1847:     */    //   34: dup
/* 1848:     */    //   35: invokespecial 33	java/lang/StringBuilder:<init>	()V
/* 1849:     */    //   38: ldc_w 266
/* 1850:     */    //   41: invokevirtual 35	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* 1851:     */    //   44: aload_1
/* 1852:     */    //   45: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/* 1853:     */    //   48: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
/* 1854:     */    //   51: invokestatic 25	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
/* 1855:     */    //   54: iconst_0
/* 1856:     */    //   55: istore_2
/* 1857:     */    //   56: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1858:     */    //   59: iload_2
/* 1859:     */    //   60: ireturn
/* 1860:     */    //   61: astore_3
/* 1861:     */    //   62: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 1862:     */    //   65: aload_3
/* 1863:     */    //   66: athrow
/* 1864:     */    // Line number table:
/* 1865:     */    //   Java source line #1267	-> byte code offset #0
/* 1866:     */    //   Java source line #1269	-> byte code offset #3
/* 1867:     */    //   Java source line #1271	-> byte code offset #6
/* 1868:     */    //   Java source line #1273	-> byte code offset #16
/* 1869:     */    //   Java source line #1279	-> byte code offset #19
/* 1870:     */    //   Java source line #1273	-> byte code offset #24
/* 1871:     */    //   Java source line #1275	-> byte code offset #30
/* 1872:     */    //   Java source line #1276	-> byte code offset #31
/* 1873:     */    //   Java source line #1277	-> byte code offset #54
/* 1874:     */    //   Java source line #1279	-> byte code offset #56
/* 1875:     */    // Local variable table:
/* 1876:     */    //   start	length	slot	name	signature
/* 1877:     */    //   0	67	0	this	LinuxDisplay
/* 1878:     */    //   15	8	1	i	int
/* 1879:     */    //   30	15	1	e	LWJGLException
/* 1880:     */    //   24	5	2	localObject1	Object
/* 1881:     */    //   55	5	2	j	int
/* 1882:     */    //   61	5	3	localObject2	Object
/* 1883:     */    // Exception table:
/* 1884:     */    //   from	to	target	type
/* 1885:     */    //   6	16	24	finally
/* 1886:     */    //   24	25	24	finally
/* 1887:     */    //   3	19	30	org/lwjgl/LWJGLException
/* 1888:     */    //   24	30	30	org/lwjgl/LWJGLException
/* 1889:     */    //   3	19	61	finally
/* 1890:     */    //   24	56	61	finally
/* 1891:     */    //   61	62	61	finally
/* 1892:     */  }
/* 1893:     */  
/* 1894:     */  private static native int nGetPbufferCapabilities(long paramLong, int paramInt);
/* 1895:     */  
/* 1896:     */  public boolean isBufferLost(PeerInfo handle)
/* 1897:     */  {
/* 1898:1285 */    return false;
/* 1899:     */  }
/* 1900:     */  
/* 1901:     */  public PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs, IntBuffer pixelFormatCaps, IntBuffer pBufferAttribs)
/* 1902:     */    throws LWJGLException
/* 1903:     */  {
/* 1904:1291 */    return new LinuxPbufferPeerInfo(width, height, pixel_format);
/* 1905:     */  }
/* 1906:     */  
/* 1907:     */  public void setPbufferAttrib(PeerInfo handle, int attrib, int value) {
/* 1908:1295 */    throw new UnsupportedOperationException();
/* 1909:     */  }
/* 1910:     */  
/* 1911:     */  public void bindTexImageToPbuffer(PeerInfo handle, int buffer) {
/* 1912:1299 */    throw new UnsupportedOperationException();
/* 1913:     */  }
/* 1914:     */  
/* 1915:     */  public void releaseTexImageFromPbuffer(PeerInfo handle, int buffer) {
/* 1916:1303 */    throw new UnsupportedOperationException();
/* 1917:     */  }
/* 1918:     */  
/* 1919:     */  private static ByteBuffer convertIcon(ByteBuffer icon, int width, int height) {
/* 1920:1307 */    ByteBuffer icon_rgb = BufferUtils.createByteBuffer(icon.capacity());
/* 1921:     */    
/* 1925:1312 */    int depth = 4;
/* 1926:     */    
/* 1927:1314 */    for (int y = 0; y < height; y++) {
/* 1928:1315 */      for (int x = 0; x < width; x++) {
/* 1929:1316 */        byte r = icon.get(x * 4 + y * width * 4);
/* 1930:1317 */        byte g = icon.get(x * 4 + y * width * 4 + 1);
/* 1931:1318 */        byte b = icon.get(x * 4 + y * width * 4 + 2);
/* 1932:     */        
/* 1933:1320 */        icon_rgb.put(x * depth + y * width * depth, b);
/* 1934:1321 */        icon_rgb.put(x * depth + y * width * depth + 1, g);
/* 1935:1322 */        icon_rgb.put(x * depth + y * width * depth + 2, r);
/* 1936:     */      }
/* 1937:     */    }
/* 1938:1325 */    return icon_rgb;
/* 1939:     */  }
/* 1940:     */  
/* 1941:     */  private static ByteBuffer convertIconMask(ByteBuffer icon, int width, int height) {
/* 1942:1329 */    ByteBuffer icon_mask = BufferUtils.createByteBuffer(icon.capacity() / 4 / 8);
/* 1943:     */    
/* 1947:1334 */    int depth = 4;
/* 1948:     */    
/* 1949:1336 */    for (int y = 0; y < height; y++) {
/* 1950:1337 */      for (int x = 0; x < width; x++) {
/* 1951:1338 */        byte a = icon.get(x * 4 + y * width * 4 + 3);
/* 1952:     */        
/* 1953:1340 */        int mask_index = x + y * width;
/* 1954:1341 */        int mask_byte_index = mask_index / 8;
/* 1955:1342 */        int mask_bit_index = mask_index % 8;
/* 1956:1343 */        byte bit = (a & 0xFF) >= 127 ? 1 : 0;
/* 1957:1344 */        byte new_byte = (byte)((icon_mask.get(mask_byte_index) | bit << mask_bit_index) & 0xFF);
/* 1958:1345 */        icon_mask.put(mask_byte_index, new_byte);
/* 1959:     */      }
/* 1960:     */    }
/* 1961:1348 */    return icon_mask;
/* 1962:     */  }
/* 1963:     */  
/* 1964:     */  /* Error */
/* 1965:     */  public int setIcon(ByteBuffer[] icons)
/* 1966:     */  {
/* 1967:     */    // Byte code:
/* 1968:     */    //   0: invokestatic 15	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/* 1969:     */    //   3: invokestatic 16	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/* 1970:     */    //   6: aload_1
/* 1971:     */    //   7: astore_2
/* 1972:     */    //   8: aload_2
/* 1973:     */    //   9: arraylength
/* 1974:     */    //   10: istore_3
/* 1975:     */    //   11: iconst_0
/* 1976:     */    //   12: istore 4
/* 1977:     */    //   14: iload 4
/* 1978:     */    //   16: iload_3
/* 1979:     */    //   17: if_icmpge +99 -> 116
/* 1980:     */    //   20: aload_2
/* 1981:     */    //   21: iload 4
/* 1982:     */    //   23: aaload
/* 1983:     */    //   24: astore 5
/* 1984:     */    //   26: aload 5
/* 1985:     */    //   28: invokevirtual 275	java/nio/ByteBuffer:limit	()I
/* 1986:     */    //   31: iconst_4
/* 1987:     */    //   32: idiv
/* 1988:     */    //   33: istore 6
/* 1989:     */    //   35: iload 6
/* 1990:     */    //   37: i2d
/* 1991:     */    //   38: invokestatic 276	java/lang/Math:sqrt	(D)D
/* 1992:     */    //   41: d2i
/* 1993:     */    //   42: istore 7
/* 1994:     */    //   44: iload 7
/* 1995:     */    //   46: ifle +64 -> 110
/* 1996:     */    //   49: aload 5
/* 1997:     */    //   51: iload 7
/* 1998:     */    //   53: iload 7
/* 1999:     */    //   55: invokestatic 277	org/lwjgl/opengl/LinuxDisplay:convertIcon	(Ljava/nio/ByteBuffer;II)Ljava/nio/ByteBuffer;
/* 2000:     */    //   58: astore 8
/* 2001:     */    //   60: aload 5
/* 2002:     */    //   62: iload 7
/* 2003:     */    //   64: iload 7
/* 2004:     */    //   66: invokestatic 278	org/lwjgl/opengl/LinuxDisplay:convertIconMask	(Ljava/nio/ByteBuffer;II)Ljava/nio/ByteBuffer;
/* 2005:     */    //   69: astore 9
/* 2006:     */    //   71: invokestatic 18	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/* 2007:     */    //   74: invokestatic 79	org/lwjgl/opengl/LinuxDisplay:getWindow	()J
/* 2008:     */    //   77: aload 8
/* 2009:     */    //   79: aload 8
/* 2010:     */    //   81: invokevirtual 271	java/nio/ByteBuffer:capacity	()I
/* 2011:     */    //   84: aload 9
/* 2012:     */    //   86: aload 9
/* 2013:     */    //   88: invokevirtual 271	java/nio/ByteBuffer:capacity	()I
/* 2014:     */    //   91: iload 7
/* 2015:     */    //   93: iload 7
/* 2016:     */    //   95: invokestatic 279	org/lwjgl/opengl/LinuxDisplay:nSetWindowIcon	(JJLjava/nio/ByteBuffer;ILjava/nio/ByteBuffer;III)V
/* 2017:     */    //   98: iconst_1
/* 2018:     */    //   99: istore 10
/* 2019:     */    //   101: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 2020:     */    //   104: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 2021:     */    //   107: iload 10
/* 2022:     */    //   109: ireturn
/* 2023:     */    //   110: iinc 4 1
/* 2024:     */    //   113: goto -99 -> 14
/* 2025:     */    //   116: iconst_0
/* 2026:     */    //   117: istore_2
/* 2027:     */    //   118: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 2028:     */    //   121: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 2029:     */    //   124: iload_2
/* 2030:     */    //   125: ireturn
/* 2031:     */    //   126: astore 11
/* 2032:     */    //   128: invokestatic 21	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 2033:     */    //   131: aload 11
/* 2034:     */    //   133: athrow
/* 2035:     */    //   134: astore_2
/* 2036:     */    //   135: new 32	java/lang/StringBuilder
/* 2037:     */    //   138: dup
/* 2038:     */    //   139: invokespecial 33	java/lang/StringBuilder:<init>	()V
/* 2039:     */    //   142: ldc_w 280
/* 2040:     */    //   145: invokevirtual 35	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/* 2041:     */    //   148: aload_2
/* 2042:     */    //   149: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
/* 2043:     */    //   152: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
/* 2044:     */    //   155: invokestatic 25	org/lwjgl/LWJGLUtil:log	(Ljava/lang/CharSequence;)V
/* 2045:     */    //   158: iconst_0
/* 2046:     */    //   159: istore_3
/* 2047:     */    //   160: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 2048:     */    //   163: iload_3
/* 2049:     */    //   164: ireturn
/* 2050:     */    //   165: astore 12
/* 2051:     */    //   167: invokestatic 22	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 2052:     */    //   170: aload 12
/* 2053:     */    //   172: athrow
/* 2054:     */    // Line number table:
/* 2055:     */    //   Java source line #1364	-> byte code offset #0
/* 2056:     */    //   Java source line #1366	-> byte code offset #3
/* 2057:     */    //   Java source line #1368	-> byte code offset #6
/* 2058:     */    //   Java source line #1369	-> byte code offset #26
/* 2059:     */    //   Java source line #1370	-> byte code offset #35
/* 2060:     */    //   Java source line #1371	-> byte code offset #44
/* 2061:     */    //   Java source line #1372	-> byte code offset #49
/* 2062:     */    //   Java source line #1373	-> byte code offset #60
/* 2063:     */    //   Java source line #1374	-> byte code offset #71
/* 2064:     */    //   Java source line #1375	-> byte code offset #98
/* 2065:     */    //   Java source line #1380	-> byte code offset #101
/* 2066:     */    //   Java source line #1386	-> byte code offset #104
/* 2067:     */    //   Java source line #1368	-> byte code offset #110
/* 2068:     */    //   Java source line #1378	-> byte code offset #116
/* 2069:     */    //   Java source line #1380	-> byte code offset #118
/* 2070:     */    //   Java source line #1386	-> byte code offset #121
/* 2071:     */    //   Java source line #1380	-> byte code offset #126
/* 2072:     */    //   Java source line #1382	-> byte code offset #134
/* 2073:     */    //   Java source line #1383	-> byte code offset #135
/* 2074:     */    //   Java source line #1384	-> byte code offset #158
/* 2075:     */    //   Java source line #1386	-> byte code offset #160
/* 2076:     */    // Local variable table:
/* 2077:     */    //   start	length	slot	name	signature
/* 2078:     */    //   0	173	0	this	LinuxDisplay
/* 2079:     */    //   0	173	1	icons	ByteBuffer[]
/* 2080:     */    //   7	118	2	arr$	ByteBuffer[]
/* 2081:     */    //   134	15	2	e	LWJGLException
/* 2082:     */    //   10	154	3	len$	int
/* 2083:     */    //   12	99	4	i$	int
/* 2084:     */    //   24	37	5	icon	ByteBuffer
/* 2085:     */    //   33	3	6	size	int
/* 2086:     */    //   42	52	7	dimension	int
/* 2087:     */    //   58	22	8	icon_rgb	ByteBuffer
/* 2088:     */    //   69	18	9	icon_mask	ByteBuffer
/* 2089:     */    //   99	9	10	i	int
/* 2090:     */    //   126	6	11	localObject1	Object
/* 2091:     */    //   165	6	12	localObject2	Object
/* 2092:     */    // Exception table:
/* 2093:     */    //   from	to	target	type
/* 2094:     */    //   6	101	126	finally
/* 2095:     */    //   110	118	126	finally
/* 2096:     */    //   126	128	126	finally
/* 2097:     */    //   3	104	134	org/lwjgl/LWJGLException
/* 2098:     */    //   110	121	134	org/lwjgl/LWJGLException
/* 2099:     */    //   126	134	134	org/lwjgl/LWJGLException
/* 2100:     */    //   3	104	165	finally
/* 2101:     */    //   110	121	165	finally
/* 2102:     */    //   126	160	165	finally
/* 2103:     */    //   165	167	165	finally
/* 2104:     */  }
/* 2105:     */  
/* 2106:     */  private static native void nSetWindowIcon(long paramLong1, long paramLong2, ByteBuffer paramByteBuffer1, int paramInt1, ByteBuffer paramByteBuffer2, int paramInt2, int paramInt3, int paramInt4);
/* 2107:     */  
/* 2108:     */  public int getX()
/* 2109:     */  {
/* 2110:1393 */    return this.window_x;
/* 2111:     */  }
/* 2112:     */  
/* 2113:     */  public int getY() {
/* 2114:1397 */    return this.window_y;
/* 2115:     */  }
/* 2116:     */  
/* 2117:     */  public int getWidth() {
/* 2118:1401 */    return this.window_width;
/* 2119:     */  }
/* 2120:     */  
/* 2121:     */  public int getHeight() {
/* 2122:1405 */    return this.window_height;
/* 2123:     */  }
/* 2124:     */  
/* 2125:     */  public boolean isInsideWindow() {
/* 2126:1409 */    return this.mouseInside;
/* 2127:     */  }
/* 2128:     */  
/* 2129:     */  public void setResizable(boolean resizable) {
/* 2130:1413 */    if (this.resizable == resizable) {
/* 2131:1414 */      return;
/* 2132:     */    }
/* 2133:     */    
/* 2134:1417 */    this.resizable = resizable;
/* 2135:1418 */    nSetWindowSize(getDisplay(), getWindow(), this.window_width, this.window_height, resizable);
/* 2136:     */  }
/* 2137:     */  
/* 2138:     */  public boolean wasResized() {
/* 2139:1422 */    if (this.resized) {
/* 2140:1423 */      this.resized = false;
/* 2141:1424 */      return true;
/* 2142:     */    }
/* 2143:     */    
/* 2144:1427 */    return false;
/* 2145:     */  }
/* 2146:     */  
/* 2148:     */  private boolean minimized;
/* 2149:     */  
/* 2150:     */  private boolean dirty;
/* 2151:     */  private boolean close_requested;
/* 2152:     */  private long current_cursor;
/* 2153:     */  private long blank_cursor;
/* 2154:     */  private boolean mouseInside;
/* 2155:     */  private boolean resizable;
/* 2156:     */  private boolean resized;
/* 2157:     */  private int window_x;
/* 2158:     */  private int window_y;
/* 2159:     */  private int window_width;
/* 2160:     */  
/* 2161:     */  private static final class Compiz
/* 2162:     */  {
/* 2163:     */    static void init()
/* 2164:     */    {
/* 2165:1448 */      if (Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.nocompiz_lfs")) {
/* 2166:1449 */        return;
/* 2167:     */      }
/* 2168:1451 */      AccessController.doPrivileged(new PrivilegedAction()
/* 2169:     */      {
/* 2170:     */        public Object run() {
/* 2171:     */          try {
/* 2172:1455 */            if (!LinuxDisplay.Compiz.isProcessActive("compiz")) {
/* 2173:1456 */              Object localObject1 = null;
/* 2174:     */              
/* 2263:1546 */              return null;
/* 2264:     */            }
/* 2265:1458 */            LinuxDisplay.Compiz.access$402(null);
/* 2266:     */            
/* 2267:1460 */            String providerName = null;
/* 2268:     */            
/* 2270:1463 */            if (LinuxDisplay.Compiz.isProcessActive("dbus-daemon")) {
/* 2271:1464 */              providerName = "Dbus";
/* 2272:1465 */              LinuxDisplay.Compiz.access$402(new LinuxDisplay.Compiz.Provider()
/* 2273:     */              {
/* 2274:     */                private static final String KEY = "/org/freedesktop/compiz/workarounds/allscreens/legacy_fullscreen";
/* 2275:     */                
/* 2276:     */                public boolean hasLegacyFullscreenSupport() throws LWJGLException {
/* 2277:1470 */                  List output = LinuxDisplay.Compiz.run(new String[] { "dbus-send", "--print-reply", "--type=method_call", "--dest=org.freedesktop.compiz", "/org/freedesktop/compiz/workarounds/allscreens/legacy_fullscreen", "org.freedesktop.compiz.get" });
/* 2278:     */                  
/* 2281:1474 */                  if ((output == null) || (output.size() < 2)) {
/* 2282:1475 */                    throw new LWJGLException("Invalid Dbus reply.");
/* 2283:     */                  }
/* 2284:1477 */                  String line = (String)output.get(0);
/* 2285:     */                  
/* 2286:1479 */                  if (!line.startsWith("method return")) {
/* 2287:1480 */                    throw new LWJGLException("Invalid Dbus reply.");
/* 2288:     */                  }
/* 2289:1482 */                  line = ((String)output.get(1)).trim();
/* 2290:1483 */                  if ((!line.startsWith("boolean")) || (line.length() < 12)) {
/* 2291:1484 */                    throw new LWJGLException("Invalid Dbus reply.");
/* 2292:     */                  }
/* 2293:1486 */                  return "true".equalsIgnoreCase(line.substring("boolean".length() + 1));
/* 2294:     */                }
/* 2295:     */                
/* 2296:     */                public void setLegacyFullscreenSupport(boolean state) throws LWJGLException {
/* 2297:1490 */                  if (LinuxDisplay.Compiz.run(new String[] { "dbus-send", "--type=method_call", "--dest=org.freedesktop.compiz", "/org/freedesktop/compiz/workarounds/allscreens/legacy_fullscreen", "org.freedesktop.compiz.set", "boolean:" + Boolean.toString(state) }) == null)
/* 2298:     */                  {
/* 2300:1493 */                    throw new LWJGLException("Failed to apply Compiz LFS workaround.");
/* 2301:     */                  }
/* 2302:     */                }
/* 2303:     */              });
/* 2304:     */            } else {
/* 2305:     */              try {
/* 2306:1499 */                Runtime.getRuntime().exec("gconftool");
/* 2307:     */                
/* 2308:1501 */                providerName = "gconftool";
/* 2309:1502 */                LinuxDisplay.Compiz.access$402(new LinuxDisplay.Compiz.Provider()
/* 2310:     */                {
/* 2311:     */                  private static final String KEY = "/apps/compiz/plugins/workarounds/allscreens/options/legacy_fullscreen";
/* 2312:     */                  
/* 2313:     */                  public boolean hasLegacyFullscreenSupport() throws LWJGLException {
/* 2314:1507 */                    List output = LinuxDisplay.Compiz.run(new String[] { "gconftool", "-g", "/apps/compiz/plugins/workarounds/allscreens/options/legacy_fullscreen" });
/* 2315:     */                    
/* 2318:1511 */                    if ((output == null) || (output.size() == 0)) {
/* 2319:1512 */                      throw new LWJGLException("Invalid gconftool reply.");
/* 2320:     */                    }
/* 2321:1514 */                    return Boolean.parseBoolean(((String)output.get(0)).trim());
/* 2322:     */                  }
/* 2323:     */                  
/* 2324:     */                  public void setLegacyFullscreenSupport(boolean state) throws LWJGLException {
/* 2325:1518 */                    if (LinuxDisplay.Compiz.run(new String[] { "gconftool", "-s", "/apps/compiz/plugins/workarounds/allscreens/options/legacy_fullscreen", "-s", Boolean.toString(state), "-t", "bool" }) == null)
/* 2326:     */                    {
/* 2328:1521 */                      throw new LWJGLException("Failed to apply Compiz LFS workaround.");
/* 2329:     */                    }
/* 2330:1523 */                    if (state)
/* 2331:     */                    {
/* 2332:     */                      try
/* 2333:     */                      {
/* 2334:1527 */                        Thread.sleep(200L);
/* 2335:     */                      } catch (InterruptedException e) {
/* 2336:1529 */                        e.printStackTrace();
/* 2337:     */                      }
/* 2338:     */                    }
/* 2339:     */                  }
/* 2340:     */                });
/* 2341:     */              }
/* 2342:     */              catch (IOException e) {}
/* 2343:     */            }
/* 2344:     */            
/* 2346:1539 */            if ((LinuxDisplay.Compiz.provider != null) && (!LinuxDisplay.Compiz.provider.hasLegacyFullscreenSupport())) {
/* 2347:1540 */              LinuxDisplay.Compiz.access$602(true);
/* 2348:1541 */              LWJGLUtil.log("Using " + providerName + " to apply Compiz LFS workaround.");
/* 2349:     */            }
/* 2350:     */            
/* 2353:1546 */            return null;
/* 2354:     */          }
/* 2355:     */          catch (LWJGLException e)
/* 2356:     */          {
/* 2357:1543 */            e = 
/* 2358:     */            
/* 2360:1546 */              e;return null; } finally {} return null;
/* 2361:     */        }
/* 2362:     */      });
/* 2363:     */    }
/* 2364:     */    
/* 2365:     */    private static boolean applyFix;
/* 2366:     */    private static Provider provider;
/* 2367:1553 */    static void setLegacyFullscreenSupport(boolean enabled) { if (!applyFix) {
/* 2368:1554 */        return;
/* 2369:     */      }
/* 2370:1556 */      AccessController.doPrivileged(new PrivilegedAction() {
/* 2371:     */        public Object run() {
/* 2372:     */          try {
/* 2373:1559 */            LinuxDisplay.Compiz.provider.setLegacyFullscreenSupport(this.val$enabled);
/* 2374:     */          } catch (LWJGLException e) {
/* 2375:1561 */            LWJGLUtil.log("Failed to change Compiz Legacy Fullscreen Support. Reason: " + e.getMessage());
/* 2376:     */          }
/* 2377:1563 */          return null;
/* 2378:     */        }
/* 2379:     */      });
/* 2380:     */    }
/* 2381:     */    
/* 2382:     */    private static List<String> run(String... command) throws LWJGLException {
/* 2383:1569 */      List<String> output = new ArrayList();
/* 2384:     */      try
/* 2385:     */      {
/* 2386:1572 */        Process p = Runtime.getRuntime().exec(command);
/* 2387:     */        try {
/* 2388:1574 */          int exitValue = p.waitFor();
/* 2389:1575 */          if (exitValue != 0)
/* 2390:1576 */            return null;
/* 2391:     */        } catch (InterruptedException e) {
/* 2392:1578 */          throw new LWJGLException("Process interrupted.", e);
/* 2393:     */        }
/* 2394:     */        
/* 2395:1581 */        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
/* 2396:     */        
/* 2397:     */        String line;
/* 2398:1584 */        while ((line = br.readLine()) != null) {
/* 2399:1585 */          output.add(line);
/* 2400:     */        }
/* 2401:1587 */        br.close();
/* 2402:     */      } catch (IOException e) {
/* 2403:1589 */        throw new LWJGLException("Process failed.", e);
/* 2404:     */      }
/* 2405:     */      
/* 2406:1592 */      return output;
/* 2407:     */    }
/* 2408:     */    
/* 2409:     */    private static boolean isProcessActive(String processName) throws LWJGLException {
/* 2410:1596 */      List<String> output = run(new String[] { "ps", "-C", processName });
/* 2411:1597 */      if (output == null) {
/* 2412:1598 */        return false;
/* 2413:     */      }
/* 2414:1600 */      for (String line : output) {
/* 2415:1601 */        if (line.contains(processName)) {
/* 2416:1602 */          return true;
/* 2417:     */        }
/* 2418:     */      }
/* 2419:1605 */      return false;
/* 2420:     */    }
/* 2421:     */    
/* 2422:     */    private static abstract interface Provider
/* 2423:     */    {
/* 2424:     */      public abstract boolean hasLegacyFullscreenSupport()
/* 2425:     */        throws LWJGLException;
/* 2426:     */      
/* 2427:     */      public abstract void setLegacyFullscreenSupport(boolean paramBoolean)
/* 2428:     */        throws LWJGLException;
/* 2429:     */    }
/* 2430:     */  }
/* 2431:     */  
/* 2432:     */  private int window_height;
/* 2433:     */  private Canvas parent;
/* 2434:     */  private long parent_window;
/* 2435:     */  private static boolean xembedded;
/* 2436:     */  private long parent_proxy_focus_window;
/* 2437:     */  private boolean parent_focused;
/* 2438:     */  private boolean parent_focus_changed;
/* 2439:     */  private long last_window_focus;
/* 2440:     */  private LinuxKeyboard keyboard;
/* 2441:     */  private LinuxMouse mouse;
/* 2442:     */  private final FocusListener focus_listener;
/* 2443:     */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxDisplay
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */