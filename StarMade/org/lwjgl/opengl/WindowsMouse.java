/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import org.lwjgl.BufferUtils;
/*   6:    */import org.lwjgl.LWJGLException;
/*   7:    */import org.lwjgl.LWJGLUtil;
/*   8:    */
/*  48:    */final class WindowsMouse
/*  49:    */{
/*  50:    */  private final long hwnd;
/*  51:    */  private final int mouse_button_count;
/*  52:    */  private final boolean has_wheel;
/*  53: 53 */  private final EventQueue event_queue = new EventQueue(22);
/*  54:    */  
/*  55: 55 */  private final ByteBuffer mouse_event = ByteBuffer.allocate(22);
/*  56:    */  private final Object blank_cursor;
/*  57:    */  private boolean mouse_grabbed;
/*  58:    */  private byte[] button_states;
/*  59:    */  private int accum_dx;
/*  60:    */  private int accum_dy;
/*  61:    */  private int accum_dwheel;
/*  62:    */  private int last_x;
/*  63:    */  private int last_y;
/*  64:    */  
/*  65:    */  WindowsMouse(long hwnd) throws LWJGLException
/*  66:    */  {
/*  67: 67 */    this.hwnd = hwnd;
/*  68: 68 */    this.mouse_button_count = Math.min(5, WindowsDisplay.getSystemMetrics(43));
/*  69: 69 */    this.has_wheel = (WindowsDisplay.getSystemMetrics(75) != 0);
/*  70: 70 */    this.blank_cursor = createBlankCursor();
/*  71: 71 */    this.button_states = new byte[this.mouse_button_count];
/*  72:    */  }
/*  73:    */  
/*  74:    */  private Object createBlankCursor() throws LWJGLException {
/*  75: 75 */    int width = WindowsDisplay.getSystemMetrics(13);
/*  76: 76 */    int height = WindowsDisplay.getSystemMetrics(14);
/*  77: 77 */    IntBuffer pixels = BufferUtils.createIntBuffer(width * height);
/*  78: 78 */    return WindowsDisplay.doCreateCursor(width, height, 0, 0, 1, pixels, null);
/*  79:    */  }
/*  80:    */  
/*  81:    */  public boolean isGrabbed() {
/*  82: 82 */    return this.mouse_grabbed;
/*  83:    */  }
/*  84:    */  
/*  85:    */  public boolean hasWheel() {
/*  86: 86 */    return this.has_wheel;
/*  87:    */  }
/*  88:    */  
/*  89:    */  public int getButtonCount() {
/*  90: 90 */    return this.mouse_button_count;
/*  91:    */  }
/*  92:    */  
/*  93:    */  public void poll(IntBuffer coord_buffer, ByteBuffer buttons) {
/*  94: 94 */    for (int i = 0; i < coord_buffer.remaining(); i++)
/*  95: 95 */      coord_buffer.put(coord_buffer.position() + i, 0);
/*  96: 96 */    int num_buttons = this.mouse_button_count;
/*  97: 97 */    coord_buffer.put(coord_buffer.position() + 2, this.accum_dwheel);
/*  98: 98 */    if (num_buttons > this.button_states.length)
/*  99: 99 */      num_buttons = this.button_states.length;
/* 100:100 */    for (int j = 0; j < num_buttons; j++) {
/* 101:101 */      buttons.put(buttons.position() + j, this.button_states[j]);
/* 102:    */    }
/* 103:103 */    if (isGrabbed()) {
/* 104:104 */      coord_buffer.put(coord_buffer.position() + 0, this.accum_dx);
/* 105:105 */      coord_buffer.put(coord_buffer.position() + 1, this.accum_dy);
/* 106:    */    } else {
/* 107:107 */      coord_buffer.put(coord_buffer.position() + 0, this.last_x);
/* 108:108 */      coord_buffer.put(coord_buffer.position() + 1, this.last_y);
/* 109:    */    }
/* 110:110 */    this.accum_dx = (this.accum_dy = this.accum_dwheel = 0);
/* 111:    */  }
/* 112:    */  
/* 113:    */  private void putMouseEventWithCoords(byte button, byte state, int coord1, int coord2, int dz, long nanos) {
/* 114:114 */    this.mouse_event.clear();
/* 115:115 */    this.mouse_event.put(button).put(state).putInt(coord1).putInt(coord2).putInt(dz).putLong(nanos);
/* 116:116 */    this.mouse_event.flip();
/* 117:117 */    this.event_queue.putEvent(this.mouse_event);
/* 118:    */  }
/* 119:    */  
/* 120:    */  private void putMouseEvent(byte button, byte state, int dz, long nanos) {
/* 121:121 */    if (this.mouse_grabbed) {
/* 122:122 */      putMouseEventWithCoords(button, state, 0, 0, dz, nanos);
/* 123:    */    } else
/* 124:124 */      putMouseEventWithCoords(button, state, this.last_x, this.last_y, dz, nanos);
/* 125:    */  }
/* 126:    */  
/* 127:    */  public void read(ByteBuffer buffer) {
/* 128:128 */    this.event_queue.copyEvents(buffer);
/* 129:    */  }
/* 130:    */  
/* 131:    */  public Object getBlankCursor() {
/* 132:132 */    return this.blank_cursor;
/* 133:    */  }
/* 134:    */  
/* 135:    */  public void grab(boolean grab, boolean should_center) {
/* 136:136 */    if (grab) {
/* 137:137 */      if (!this.mouse_grabbed) {
/* 138:138 */        this.mouse_grabbed = true;
/* 139:139 */        if (should_center) {
/* 140:    */          try {
/* 141:141 */            WindowsDisplay.setupCursorClipping(this.hwnd);
/* 142:    */          } catch (LWJGLException e) {
/* 143:143 */            LWJGLUtil.log("Failed to setup cursor clipping: " + e);
/* 144:    */          }
/* 145:145 */          centerCursor();
/* 146:    */        }
/* 147:    */      }
/* 148:    */    }
/* 149:149 */    else if (this.mouse_grabbed) {
/* 150:150 */      this.mouse_grabbed = false;
/* 151:151 */      WindowsDisplay.resetCursorClipping();
/* 152:    */    }
/* 153:    */    
/* 154:154 */    this.event_queue.clearEvents();
/* 155:    */  }
/* 156:    */  
/* 157:    */  public void handleMouseScrolled(int event_dwheel, long millis) {
/* 158:158 */    this.accum_dwheel += event_dwheel;
/* 159:159 */    putMouseEvent((byte)-1, (byte)0, event_dwheel, millis * 1000000L);
/* 160:    */  }
/* 161:    */  
/* 162:    */  private void centerCursor() {
/* 163:163 */    WindowsDisplay.centerCursor(this.hwnd);
/* 164:    */  }
/* 165:    */  
/* 166:    */  public void setPosition(int x, int y) {
/* 167:167 */    this.last_x = x;
/* 168:168 */    this.last_y = y;
/* 169:    */  }
/* 170:    */  
/* 171:    */  public void destroy() {
/* 172:172 */    WindowsDisplay.doDestroyCursor(this.blank_cursor);
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void handleMouseMoved(int x, int y, long millis, boolean should_center) {
/* 176:176 */    int dx = x - this.last_x;
/* 177:177 */    int dy = y - this.last_y;
/* 178:178 */    if ((dx != 0) || (dy != 0)) {
/* 179:179 */      this.accum_dx += dx;
/* 180:180 */      this.accum_dy += dy;
/* 181:181 */      this.last_x = x;
/* 182:182 */      this.last_y = y;
/* 183:183 */      long nanos = millis * 1000000L;
/* 184:184 */      if (this.mouse_grabbed) {
/* 185:185 */        putMouseEventWithCoords((byte)-1, (byte)0, dx, dy, 0, nanos);
/* 186:186 */        if (should_center)
/* 187:187 */          centerCursor();
/* 188:    */      } else {
/* 189:189 */        putMouseEventWithCoords((byte)-1, (byte)0, x, y, 0, nanos);
/* 190:    */      }
/* 191:    */    }
/* 192:    */  }
/* 193:    */  
/* 194:    */  public void handleMouseButton(byte button, byte state, long millis) {
/* 195:195 */    putMouseEvent(button, state, 0, millis * 1000000L);
/* 196:196 */    if (button < this.button_states.length) {
/* 197:197 */      this.button_states[button] = (state != 0 ? 1 : 0);
/* 198:    */    }
/* 199:    */  }
/* 200:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsMouse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */