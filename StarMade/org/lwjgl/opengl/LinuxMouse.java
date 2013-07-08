/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferUtils;
/*   6:    */import org.lwjgl.LWJGLException;
/*   7:    */
/*  51:    */final class LinuxMouse
/*  52:    */{
/*  53:    */  private static final int POINTER_WARP_BORDER = 10;
/*  54:    */  private static final int WHEEL_SCALE = 120;
/*  55:    */  private int button_count;
/*  56:    */  private static final int Button1 = 1;
/*  57:    */  private static final int Button2 = 2;
/*  58:    */  private static final int Button3 = 3;
/*  59:    */  private static final int Button4 = 4;
/*  60:    */  private static final int Button5 = 5;
/*  61:    */  private static final int Button6 = 6;
/*  62:    */  private static final int Button7 = 7;
/*  63:    */  private static final int Button8 = 8;
/*  64:    */  private static final int Button9 = 9;
/*  65:    */  private static final int ButtonPress = 4;
/*  66:    */  private static final int ButtonRelease = 5;
/*  67:    */  private final long display;
/*  68:    */  private final long window;
/*  69:    */  private final long input_window;
/*  70:    */  private final long warp_atom;
/*  71: 71 */  private final IntBuffer query_pointer_buffer = BufferUtils.createIntBuffer(4);
/*  72: 72 */  private final ByteBuffer event_buffer = ByteBuffer.allocate(22);
/*  73:    */  private int last_x;
/*  74:    */  private int last_y;
/*  75:    */  private int accum_dx;
/*  76:    */  private int accum_dy;
/*  77:    */  private int accum_dz;
/*  78:    */  private byte[] buttons;
/*  79:    */  private EventQueue event_queue;
/*  80:    */  private long last_event_nanos;
/*  81:    */  
/*  82:    */  LinuxMouse(long display, long window, long input_window) throws LWJGLException
/*  83:    */  {
/*  84: 84 */    this.display = display;
/*  85: 85 */    this.window = window;
/*  86: 86 */    this.input_window = input_window;
/*  87: 87 */    this.warp_atom = LinuxDisplay.nInternAtom(display, "_LWJGL", false);
/*  88: 88 */    this.button_count = nGetButtonCount(display);
/*  89: 89 */    this.buttons = new byte[this.button_count];
/*  90: 90 */    reset(false, false);
/*  91:    */  }
/*  92:    */  
/*  93:    */  private void reset(boolean grab, boolean warp_pointer) {
/*  94: 94 */    this.event_queue = new EventQueue(this.event_buffer.capacity());
/*  95: 95 */    this.accum_dx = (this.accum_dy = 0);
/*  96: 96 */    long root_window = nQueryPointer(this.display, this.window, this.query_pointer_buffer);
/*  97:    */    
/*  98: 98 */    int root_x = this.query_pointer_buffer.get(0);
/*  99: 99 */    int root_y = this.query_pointer_buffer.get(1);
/* 100:100 */    int win_x = this.query_pointer_buffer.get(2);
/* 101:101 */    int win_y = this.query_pointer_buffer.get(3);
/* 102:    */    
/* 103:103 */    this.last_x = win_x;
/* 104:104 */    this.last_y = transformY(win_y);
/* 105:105 */    doHandlePointerMotion(grab, warp_pointer, root_window, root_x, root_y, win_x, win_y, this.last_event_nanos);
/* 106:    */  }
/* 107:    */  
/* 108:    */  public void read(ByteBuffer buffer) {
/* 109:109 */    this.event_queue.copyEvents(buffer);
/* 110:    */  }
/* 111:    */  
/* 112:    */  public void poll(boolean grab, IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
/* 113:113 */    if (grab) {
/* 114:114 */      coord_buffer.put(0, this.accum_dx);
/* 115:115 */      coord_buffer.put(1, this.accum_dy);
/* 116:    */    } else {
/* 117:117 */      coord_buffer.put(0, this.last_x);
/* 118:118 */      coord_buffer.put(1, this.last_y);
/* 119:    */    }
/* 120:120 */    coord_buffer.put(2, this.accum_dz);
/* 121:121 */    this.accum_dx = (this.accum_dy = this.accum_dz = 0);
/* 122:122 */    for (int i = 0; i < this.buttons.length; i++)
/* 123:123 */      buttons_buffer.put(i, this.buttons[i]);
/* 124:    */  }
/* 125:    */  
/* 126:    */  private void putMouseEventWithCoords(byte button, byte state, int coord1, int coord2, int dz, long nanos) {
/* 127:127 */    this.event_buffer.clear();
/* 128:128 */    this.event_buffer.put(button).put(state).putInt(coord1).putInt(coord2).putInt(dz).putLong(nanos);
/* 129:129 */    this.event_buffer.flip();
/* 130:130 */    this.event_queue.putEvent(this.event_buffer);
/* 131:131 */    this.last_event_nanos = nanos;
/* 132:    */  }
/* 133:    */  
/* 134:    */  private void setCursorPos(boolean grab, int x, int y, long nanos) {
/* 135:135 */    y = transformY(y);
/* 136:136 */    int dx = x - this.last_x;
/* 137:137 */    int dy = y - this.last_y;
/* 138:138 */    if ((dx != 0) || (dy != 0)) {
/* 139:139 */      this.accum_dx += dx;
/* 140:140 */      this.accum_dy += dy;
/* 141:141 */      this.last_x = x;
/* 142:142 */      this.last_y = y;
/* 143:143 */      if (grab) {
/* 144:144 */        putMouseEventWithCoords((byte)-1, (byte)0, dx, dy, 0, nanos);
/* 145:    */      } else {
/* 146:146 */        putMouseEventWithCoords((byte)-1, (byte)0, x, y, 0, nanos);
/* 147:    */      }
/* 148:    */    }
/* 149:    */  }
/* 150:    */  
/* 151:    */  private void doWarpPointer(int center_x, int center_y) {
/* 152:152 */    nSendWarpEvent(this.display, this.input_window, this.warp_atom, center_x, center_y);
/* 153:153 */    nWarpCursor(this.display, this.window, center_x, center_y);
/* 154:    */  }
/* 155:    */  
/* 156:    */  private static native void nSendWarpEvent(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2);
/* 157:    */  
/* 158:158 */  private void doHandlePointerMotion(boolean grab, boolean warp_pointer, long root_window, int root_x, int root_y, int win_x, int win_y, long nanos) { setCursorPos(grab, win_x, win_y, nanos);
/* 159:159 */    if (!warp_pointer)
/* 160:160 */      return;
/* 161:161 */    int root_window_height = nGetWindowHeight(this.display, root_window);
/* 162:162 */    int root_window_width = nGetWindowWidth(this.display, root_window);
/* 163:163 */    int window_height = nGetWindowHeight(this.display, this.window);
/* 164:164 */    int window_width = nGetWindowWidth(this.display, this.window);
/* 165:    */    
/* 167:167 */    int win_left = root_x - win_x;
/* 168:168 */    int win_top = root_y - win_y;
/* 169:169 */    int win_right = win_left + window_width;
/* 170:170 */    int win_bottom = win_top + window_height;
/* 171:    */    
/* 172:172 */    int border_left = Math.max(0, win_left);
/* 173:173 */    int border_top = Math.max(0, win_top);
/* 174:174 */    int border_right = Math.min(root_window_width, win_right);
/* 175:175 */    int border_bottom = Math.min(root_window_height, win_bottom);
/* 176:    */    
/* 177:177 */    boolean outside_limits = (root_x < border_left + 10) || (root_y < border_top + 10) || (root_x > border_right - 10) || (root_y > border_bottom - 10);
/* 178:    */    
/* 179:179 */    if (outside_limits)
/* 180:    */    {
/* 181:181 */      int center_x = (border_right - border_left) / 2;
/* 182:182 */      int center_y = (border_bottom - border_top) / 2;
/* 183:183 */      doWarpPointer(center_x, center_y);
/* 184:    */    }
/* 185:    */  }
/* 186:    */  
/* 187:    */  public void changeGrabbed(boolean grab, boolean warp_pointer) {
/* 188:188 */    reset(grab, warp_pointer);
/* 189:    */  }
/* 190:    */  
/* 191:    */  public int getButtonCount() {
/* 192:192 */    return this.buttons.length;
/* 193:    */  }
/* 194:    */  
/* 196:196 */  private int transformY(int y) { return nGetWindowHeight(this.display, this.window) - 1 - y; }
/* 197:    */  
/* 198:    */  private static native int nGetWindowHeight(long paramLong1, long paramLong2);
/* 199:    */  
/* 200:    */  private static native int nGetWindowWidth(long paramLong1, long paramLong2);
/* 201:    */  
/* 202:    */  private static native int nGetButtonCount(long paramLong);
/* 203:    */  
/* 204:    */  private static native long nQueryPointer(long paramLong1, long paramLong2, IntBuffer paramIntBuffer);
/* 205:    */  
/* 206:206 */  public void setCursorPosition(int x, int y) { nWarpCursor(this.display, this.window, x, transformY(y)); }
/* 207:    */  
/* 208:    */  private static native void nWarpCursor(long paramLong1, long paramLong2, int paramInt1, int paramInt2);
/* 209:    */  
/* 210:    */  private void handlePointerMotion(boolean grab, boolean warp_pointer, long millis, long root_window, int x_root, int y_root, int x, int y) {
/* 211:211 */    doHandlePointerMotion(grab, warp_pointer, root_window, x_root, y_root, x, y, millis * 1000000L);
/* 212:    */  }
/* 213:    */  
/* 214:    */  private void handleButton(boolean grab, int button, byte state, long nanos) {
/* 215:    */    byte button_num;
/* 216:216 */    switch (button) {
/* 217:    */    case 1: 
/* 218:218 */      button_num = 0;
/* 219:219 */      break;
/* 220:    */    case 2: 
/* 221:221 */      button_num = 2;
/* 222:222 */      break;
/* 223:    */    case 3: 
/* 224:224 */      button_num = 1;
/* 225:225 */      break;
/* 226:    */    case 6: 
/* 227:227 */      button_num = 5;
/* 228:228 */      break;
/* 229:    */    case 7: 
/* 230:230 */      button_num = 6;
/* 231:231 */      break;
/* 232:    */    case 8: 
/* 233:233 */      button_num = 3;
/* 234:234 */      break;
/* 235:    */    case 9: 
/* 236:236 */      button_num = 4;
/* 237:237 */      break;
/* 238:    */    case 4: case 5: default: 
/* 239:239 */      if ((button > 9) && (button <= this.button_count))
/* 240:240 */        button_num = (byte)(button - 1); else
/* 241:    */        return;
/* 242:    */      break;
/* 243:    */    }
/* 244:    */    byte button_num;
/* 245:245 */    this.buttons[button_num] = state;
/* 246:246 */    putMouseEvent(grab, button_num, state, 0, nanos);
/* 247:    */  }
/* 248:    */  
/* 249:    */  private void putMouseEvent(boolean grab, byte button, byte state, int dz, long nanos) {
/* 250:250 */    if (grab) {
/* 251:251 */      putMouseEventWithCoords(button, state, 0, 0, dz, nanos);
/* 252:    */    } else
/* 253:253 */      putMouseEventWithCoords(button, state, this.last_x, this.last_y, dz, nanos);
/* 254:    */  }
/* 255:    */  
/* 256:    */  private void handleButtonPress(boolean grab, byte button, long nanos) {
/* 257:257 */    int delta = 0;
/* 258:258 */    switch (button) {
/* 259:    */    case 4: 
/* 260:260 */      delta = 120;
/* 261:261 */      putMouseEvent(grab, (byte)-1, (byte)0, delta, nanos);
/* 262:262 */      this.accum_dz += delta;
/* 263:263 */      break;
/* 264:    */    case 5: 
/* 265:265 */      delta = -120;
/* 266:266 */      putMouseEvent(grab, (byte)-1, (byte)0, delta, nanos);
/* 267:267 */      this.accum_dz += delta;
/* 268:268 */      break;
/* 269:    */    default: 
/* 270:270 */      handleButton(grab, button, (byte)1, nanos);
/* 271:    */    }
/* 272:    */  }
/* 273:    */  
/* 274:    */  private void handleButtonEvent(boolean grab, long millis, int type, byte button)
/* 275:    */  {
/* 276:276 */    long nanos = millis * 1000000L;
/* 277:277 */    switch (type) {
/* 278:    */    case 5: 
/* 279:279 */      handleButton(grab, button, (byte)0, nanos);
/* 280:280 */      break;
/* 281:    */    case 4: 
/* 282:282 */      handleButtonPress(grab, button, nanos);
/* 283:283 */      break;
/* 284:    */    }
/* 285:    */    
/* 286:    */  }
/* 287:    */  
/* 288:    */  private void resetCursor(int x, int y)
/* 289:    */  {
/* 290:290 */    this.last_x = x;
/* 291:291 */    this.last_y = transformY(y);
/* 292:    */  }
/* 293:    */  
/* 294:    */  private void handleWarpEvent(int x, int y) {
/* 295:295 */    resetCursor(x, y);
/* 296:    */  }
/* 297:    */  
/* 298:    */  public boolean filterEvent(boolean grab, boolean warp_pointer, LinuxEvent event) {
/* 299:299 */    switch (event.getType()) {
/* 300:    */    case 33: 
/* 301:301 */      if (event.getClientMessageType() == this.warp_atom) {
/* 302:302 */        handleWarpEvent(event.getClientData(0), event.getClientData(1));
/* 303:303 */        return true;
/* 304:    */      }
/* 305:    */      break;
/* 306:    */    case 4: 
/* 307:    */    case 5: 
/* 308:308 */      handleButtonEvent(grab, event.getButtonTime(), event.getButtonType(), (byte)event.getButtonButton());
/* 309:309 */      return true;
/* 310:    */    case 6: 
/* 311:311 */      handlePointerMotion(grab, warp_pointer, event.getButtonTime(), event.getButtonRoot(), event.getButtonXRoot(), event.getButtonYRoot(), event.getButtonX(), event.getButtonY());
/* 312:312 */      return true;
/* 313:    */    }
/* 314:    */    
/* 315:    */    
/* 316:316 */    return false;
/* 317:    */  }
/* 318:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxMouse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */