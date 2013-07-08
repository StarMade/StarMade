/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferUtils;
/*   6:    */import org.lwjgl.LWJGLException;
/*   7:    */
/*  59:    */final class MacOSXNativeMouse
/*  60:    */  extends EventQueue
/*  61:    */{
/*  62:    */  private static final int WHEEL_SCALE = 120;
/*  63:    */  private static final int NUM_BUTTONS = 3;
/*  64:    */  private ByteBuffer window_handle;
/*  65:    */  private MacOSXDisplay display;
/*  66:    */  private boolean grabbed;
/*  67:    */  private float accum_dx;
/*  68:    */  private float accum_dy;
/*  69:    */  private int accum_dz;
/*  70:    */  private float last_x;
/*  71:    */  private float last_y;
/*  72:    */  private boolean saved_control_state;
/*  73: 73 */  private final ByteBuffer event = ByteBuffer.allocate(22);
/*  74: 74 */  private IntBuffer delta_buffer = BufferUtils.createIntBuffer(2);
/*  75:    */  
/*  76:    */  private int skip_event;
/*  77: 77 */  private final byte[] buttons = new byte[3];
/*  78:    */  
/*  79:    */  MacOSXNativeMouse(MacOSXDisplay display, ByteBuffer window_handle) {
/*  80: 80 */    super(22);
/*  81: 81 */    this.display = display;
/*  82: 82 */    this.window_handle = window_handle;
/*  83:    */  }
/*  84:    */  
/*  85:    */  private native void nSetCursorPosition(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
/*  86:    */  
/*  87:    */  public static native void nGrabMouse(boolean paramBoolean);
/*  88:    */  
/*  89:    */  private native void nRegisterMouseListener(ByteBuffer paramByteBuffer);
/*  90:    */  
/*  91:    */  private native void nUnregisterMouseListener(ByteBuffer paramByteBuffer);
/*  92:    */  
/*  93:    */  private static native long nCreateCursor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, IntBuffer paramIntBuffer1, int paramInt6, IntBuffer paramIntBuffer2, int paramInt7) throws LWJGLException;
/*  94:    */  
/*  95:    */  private static native void nDestroyCursor(long paramLong);
/*  96:    */  
/*  97:    */  private static native void nSetCursor(long paramLong) throws LWJGLException;
/*  98:    */  
/*  99:    */  public synchronized void register() {
/* 100:100 */    nRegisterMouseListener(this.window_handle);
/* 101:    */  }
/* 102:    */  
/* 103:    */  public static long createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
/* 104:    */    try {
/* 105:105 */      return nCreateCursor(width, height, xHotspot, yHotspot, numImages, images, images.position(), delays, delays != null ? delays.position() : -1);
/* 106:    */    } catch (LWJGLException e) {
/* 107:107 */      throw e;
/* 108:    */    }
/* 109:    */  }
/* 110:    */  
/* 111:    */  public static void destroyCursor(long cursor_handle) {
/* 112:112 */    nDestroyCursor(cursor_handle);
/* 113:    */  }
/* 114:    */  
/* 115:    */  public static void setCursor(long cursor_handle) throws LWJGLException {
/* 116:    */    try {
/* 117:117 */      nSetCursor(cursor_handle);
/* 118:    */    } catch (LWJGLException e) {
/* 119:119 */      throw e;
/* 120:    */    }
/* 121:    */  }
/* 122:    */  
/* 123:    */  public synchronized void setCursorPosition(int x, int y) {
/* 124:124 */    nSetCursorPosition(this.window_handle, x, y);
/* 125:    */  }
/* 126:    */  
/* 127:    */  public synchronized void unregister() {
/* 128:128 */    nUnregisterMouseListener(this.window_handle);
/* 129:    */  }
/* 130:    */  
/* 131:    */  public synchronized void setGrabbed(boolean grabbed) {
/* 132:132 */    this.grabbed = grabbed;
/* 133:133 */    nGrabMouse(grabbed);
/* 134:134 */    this.skip_event = 1;
/* 135:135 */    this.accum_dx = (this.accum_dy = 0.0F);
/* 136:    */  }
/* 137:    */  
/* 138:    */  public synchronized boolean isGrabbed() {
/* 139:139 */    return this.grabbed;
/* 140:    */  }
/* 141:    */  
/* 142:    */  protected void resetCursorToCenter() {
/* 143:143 */    clearEvents();
/* 144:144 */    this.accum_dx = (this.accum_dy = 0.0F);
/* 145:145 */    if (this.display != null) {
/* 146:146 */      this.last_x = (this.display.getWidth() / 2);
/* 147:147 */      this.last_y = (this.display.getHeight() / 2);
/* 148:    */    }
/* 149:    */  }
/* 150:    */  
/* 151:    */  private void putMouseEvent(byte button, byte state, int dz, long nanos) {
/* 152:152 */    if (this.grabbed) {
/* 153:153 */      putMouseEventWithCoords(button, state, 0, 0, dz, nanos);
/* 154:    */    } else
/* 155:155 */      putMouseEventWithCoords(button, state, (int)this.last_x, (int)this.last_y, dz, nanos);
/* 156:    */  }
/* 157:    */  
/* 158:    */  protected void putMouseEventWithCoords(byte button, byte state, int coord1, int coord2, int dz, long nanos) {
/* 159:159 */    this.event.clear();
/* 160:160 */    this.event.put(button).put(state).putInt(coord1).putInt(coord2).putInt(dz).putLong(nanos);
/* 161:161 */    this.event.flip();
/* 162:162 */    putEvent(this.event);
/* 163:    */  }
/* 164:    */  
/* 165:    */  public synchronized void poll(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
/* 166:166 */    if (this.grabbed) {
/* 167:167 */      coord_buffer.put(0, (int)this.accum_dx);
/* 168:168 */      coord_buffer.put(1, (int)this.accum_dy);
/* 169:    */    } else {
/* 170:170 */      coord_buffer.put(0, (int)this.last_x);
/* 171:171 */      coord_buffer.put(1, (int)this.last_y);
/* 172:    */    }
/* 173:173 */    coord_buffer.put(2, this.accum_dz);
/* 174:174 */    this.accum_dx = (this.accum_dy = this.accum_dz = 0);
/* 175:175 */    int old_position = buttons_buffer.position();
/* 176:176 */    buttons_buffer.put(this.buttons, 0, this.buttons.length);
/* 177:177 */    buttons_buffer.position(old_position);
/* 178:    */  }
/* 179:    */  
/* 180:    */  private void setCursorPos(float x, float y, long nanos) {
/* 181:181 */    if (this.grabbed)
/* 182:182 */      return;
/* 183:183 */    float dx = x - this.last_x;
/* 184:184 */    float dy = y - this.last_y;
/* 185:185 */    addDelta(dx, dy);
/* 186:186 */    this.last_x = x;
/* 187:187 */    this.last_y = y;
/* 188:188 */    putMouseEventWithCoords((byte)-1, (byte)0, (int)x, (int)y, 0, nanos);
/* 189:    */  }
/* 190:    */  
/* 191:    */  protected void addDelta(float dx, float dy) {
/* 192:192 */    this.accum_dx += dx;
/* 193:193 */    this.accum_dy += -dy;
/* 194:    */  }
/* 195:    */  
/* 196:    */  public synchronized void setButton(int button, int state, long nanos) {
/* 197:197 */    this.buttons[button] = ((byte)state);
/* 198:198 */    putMouseEvent((byte)button, (byte)state, 0, nanos);
/* 199:    */  }
/* 200:    */  
/* 201:    */  public synchronized void mouseMoved(float x, float y, float dx, float dy, float dz, long nanos) {
/* 202:202 */    if (this.skip_event > 0) {
/* 203:203 */      this.skip_event -= 1;
/* 204:204 */      if (this.skip_event == 0) {
/* 205:205 */        this.last_x = x;
/* 206:206 */        this.last_y = y;
/* 207:    */      }
/* 208:208 */      return;
/* 209:    */    }
/* 210:210 */    if (this.grabbed) {
/* 211:211 */      if ((dx != 0.0F) || (dy != 0.0F)) {
/* 212:212 */        putMouseEventWithCoords((byte)-1, (byte)0, (int)dx, (int)-dy, 0, nanos);
/* 213:213 */        addDelta(dx, dy);
/* 214:    */      }
/* 215:    */    } else {
/* 216:216 */      setCursorPos(x, y, nanos);
/* 217:    */    }
/* 218:218 */    if (dz != 0.0F)
/* 219:    */    {
/* 220:220 */      if (dy == 0.0F) { dy = dx;
/* 221:    */      }
/* 222:222 */      int wheel_amount = (int)(dy * 120.0F);
/* 223:223 */      this.accum_dz += wheel_amount;
/* 224:224 */      putMouseEvent((byte)-1, (byte)0, wheel_amount, nanos);
/* 225:    */    }
/* 226:    */  }
/* 227:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXNativeMouse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */