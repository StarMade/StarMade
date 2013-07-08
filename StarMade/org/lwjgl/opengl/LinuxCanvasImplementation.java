/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.awt.Canvas;
/*   4:    */import java.awt.GraphicsConfiguration;
/*   5:    */import java.awt.GraphicsDevice;
/*   6:    */import java.lang.reflect.Method;
/*   7:    */import java.security.AccessController;
/*   8:    */import java.security.PrivilegedExceptionAction;
/*   9:    */import org.lwjgl.LWJGLException;
/*  10:    */import org.lwjgl.LWJGLUtil;
/*  11:    */
/*  45:    */final class LinuxCanvasImplementation
/*  46:    */  implements AWTCanvasImplementation
/*  47:    */{
/*  48:    */  static int getScreenFromDevice(GraphicsDevice device)
/*  49:    */    throws LWJGLException
/*  50:    */  {
/*  51:    */    try
/*  52:    */    {
/*  53: 53 */      Method getScreen_method = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*  54:    */        public Method run() throws Exception {
/*  55: 55 */          return this.val$device.getClass().getMethod("getScreen", new Class[0]);
/*  56:    */        }
/*  57: 57 */      });
/*  58: 58 */      Integer screen = (Integer)getScreen_method.invoke(device, new Object[0]);
/*  59: 59 */      return screen.intValue();
/*  60:    */    } catch (Exception e) {
/*  61: 61 */      throw new LWJGLException(e);
/*  62:    */    }
/*  63:    */  }
/*  64:    */  
/*  65:    */  private static int getVisualIDFromConfiguration(GraphicsConfiguration configuration) throws LWJGLException {
/*  66:    */    try {
/*  67: 67 */      Method getVisual_method = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
/*  68:    */        public Method run() throws Exception {
/*  69: 69 */          return this.val$configuration.getClass().getMethod("getVisual", new Class[0]);
/*  70:    */        }
/*  71: 71 */      });
/*  72: 72 */      Integer visual = (Integer)getVisual_method.invoke(configuration, new Object[0]);
/*  73: 73 */      return visual.intValue();
/*  74:    */    } catch (Exception e) {
/*  75: 75 */      throw new LWJGLException(e);
/*  76:    */    }
/*  77:    */  }
/*  78:    */  
/*  79:    */  public PeerInfo createPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
/*  80: 80 */    return new LinuxAWTGLCanvasPeerInfo(component);
/*  81:    */  }
/*  82:    */  
/*  85:    */  public GraphicsConfiguration findConfiguration(GraphicsDevice device, PixelFormat pixel_format)
/*  86:    */    throws LWJGLException
/*  87:    */  {
/*  88:    */    try
/*  89:    */    {
/*  90: 90 */      int screen = getScreenFromDevice(device);
/*  91: 91 */      int visual_id_matching_format = findVisualIDFromFormat(screen, pixel_format);
/*  92: 92 */      GraphicsConfiguration[] configurations = device.getConfigurations();
/*  93: 93 */      for (GraphicsConfiguration configuration : configurations) {
/*  94: 94 */        int visual_id = getVisualIDFromConfiguration(configuration);
/*  95: 95 */        if (visual_id == visual_id_matching_format)
/*  96: 96 */          return configuration;
/*  97:    */      }
/*  98:    */    } catch (LWJGLException e) {
/*  99: 99 */      LWJGLUtil.log("Got exception while trying to determine configuration: " + e);
/* 100:    */    }
/* 101:101 */    return null;
/* 102:    */  }
/* 103:    */  
/* 104:    */  /* Error */
/* 105:    */  private static int findVisualIDFromFormat(int screen, PixelFormat pixel_format)
/* 106:    */    throws LWJGLException
/* 107:    */  {
/* 108:    */    // Byte code:
/* 109:    */    //   0: invokestatic 28	org/lwjgl/opengl/LinuxDisplay:lockAWT	()V
/* 110:    */    //   3: invokestatic 29	org/lwjgl/opengl/GLContext:loadOpenGLLibrary	()V
/* 111:    */    //   6: invokestatic 30	org/lwjgl/opengl/LinuxDisplay:incDisplay	()V
/* 112:    */    //   9: invokestatic 31	org/lwjgl/opengl/LinuxDisplay:getDisplay	()J
/* 113:    */    //   12: iload_0
/* 114:    */    //   13: aload_1
/* 115:    */    //   14: invokestatic 32	org/lwjgl/opengl/LinuxCanvasImplementation:nFindVisualIDFromFormat	(JILorg/lwjgl/opengl/PixelFormat;)I
/* 116:    */    //   17: istore_2
/* 117:    */    //   18: invokestatic 33	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 118:    */    //   21: invokestatic 34	org/lwjgl/opengl/GLContext:unloadOpenGLLibrary	()V
/* 119:    */    //   24: invokestatic 35	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 120:    */    //   27: iload_2
/* 121:    */    //   28: ireturn
/* 122:    */    //   29: astore_3
/* 123:    */    //   30: invokestatic 33	org/lwjgl/opengl/LinuxDisplay:decDisplay	()V
/* 124:    */    //   33: aload_3
/* 125:    */    //   34: athrow
/* 126:    */    //   35: astore 4
/* 127:    */    //   37: invokestatic 34	org/lwjgl/opengl/GLContext:unloadOpenGLLibrary	()V
/* 128:    */    //   40: aload 4
/* 129:    */    //   42: athrow
/* 130:    */    //   43: astore 5
/* 131:    */    //   45: invokestatic 35	org/lwjgl/opengl/LinuxDisplay:unlockAWT	()V
/* 132:    */    //   48: aload 5
/* 133:    */    //   50: athrow
/* 134:    */    // Line number table:
/* 135:    */    //   Java source line #106	-> byte code offset #0
/* 136:    */    //   Java source line #108	-> byte code offset #3
/* 137:    */    //   Java source line #110	-> byte code offset #6
/* 138:    */    //   Java source line #111	-> byte code offset #9
/* 139:    */    //   Java source line #113	-> byte code offset #18
/* 140:    */    //   Java source line #116	-> byte code offset #21
/* 141:    */    //   Java source line #119	-> byte code offset #24
/* 142:    */    //   Java source line #113	-> byte code offset #29
/* 143:    */    //   Java source line #116	-> byte code offset #35
/* 144:    */    //   Java source line #119	-> byte code offset #43
/* 145:    */    // Local variable table:
/* 146:    */    //   start	length	slot	name	signature
/* 147:    */    //   0	51	0	screen	int
/* 148:    */    //   0	51	1	pixel_format	PixelFormat
/* 149:    */    //   17	11	2	i	int
/* 150:    */    //   29	5	3	localObject1	Object
/* 151:    */    //   35	6	4	localObject2	Object
/* 152:    */    //   43	6	5	localObject3	Object
/* 153:    */    // Exception table:
/* 154:    */    //   from	to	target	type
/* 155:    */    //   6	18	29	finally
/* 156:    */    //   29	30	29	finally
/* 157:    */    //   3	21	35	finally
/* 158:    */    //   29	37	35	finally
/* 159:    */    //   0	24	43	finally
/* 160:    */    //   29	45	43	finally
/* 161:    */  }
/* 162:    */  
/* 163:    */  private static native int nFindVisualIDFromFormat(long paramLong, int paramInt, PixelFormat paramPixelFormat)
/* 164:    */    throws LWJGLException;
/* 165:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxCanvasImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */