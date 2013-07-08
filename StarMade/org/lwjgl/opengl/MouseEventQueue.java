/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.awt.Component;
/*   4:    */import java.awt.Point;
/*   5:    */import java.awt.event.MouseEvent;
/*   6:    */import java.awt.event.MouseListener;
/*   7:    */import java.awt.event.MouseMotionListener;
/*   8:    */import java.awt.event.MouseWheelEvent;
/*   9:    */import java.awt.event.MouseWheelListener;
/*  10:    */import java.nio.ByteBuffer;
/*  11:    */import java.nio.IntBuffer;
/*  12:    */
/*  58:    */class MouseEventQueue
/*  59:    */  extends EventQueue
/*  60:    */  implements MouseListener, MouseMotionListener, MouseWheelListener
/*  61:    */{
/*  62:    */  private static final int WHEEL_SCALE = 120;
/*  63:    */  public static final int NUM_BUTTONS = 3;
/*  64:    */  private final Component component;
/*  65:    */  private boolean grabbed;
/*  66:    */  private int accum_dx;
/*  67:    */  private int accum_dy;
/*  68:    */  private int accum_dz;
/*  69:    */  private int last_x;
/*  70:    */  private int last_y;
/*  71:    */  private boolean saved_control_state;
/*  72: 72 */  private final ByteBuffer event = ByteBuffer.allocate(22);
/*  73:    */  
/*  75: 75 */  private final byte[] buttons = new byte[3];
/*  76:    */  
/*  77:    */  MouseEventQueue(Component component) {
/*  78: 78 */    super(22);
/*  79: 79 */    this.component = component;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public synchronized void register() {
/*  83: 83 */    resetCursorToCenter();
/*  84: 84 */    if (this.component != null) {
/*  85: 85 */      this.component.addMouseListener(this);
/*  86: 86 */      this.component.addMouseMotionListener(this);
/*  87: 87 */      this.component.addMouseWheelListener(this);
/*  88:    */    }
/*  89:    */  }
/*  90:    */  
/*  91:    */  public synchronized void unregister() {
/*  92: 92 */    if (this.component != null) {
/*  93: 93 */      this.component.removeMouseListener(this);
/*  94: 94 */      this.component.removeMouseMotionListener(this);
/*  95: 95 */      this.component.removeMouseWheelListener(this);
/*  96:    */    }
/*  97:    */  }
/*  98:    */  
/*  99:    */  protected Component getComponent() {
/* 100:100 */    return this.component;
/* 101:    */  }
/* 102:    */  
/* 103:    */  public synchronized void setGrabbed(boolean grabbed) {
/* 104:104 */    this.grabbed = grabbed;
/* 105:105 */    resetCursorToCenter();
/* 106:    */  }
/* 107:    */  
/* 108:    */  public synchronized boolean isGrabbed() {
/* 109:109 */    return this.grabbed;
/* 110:    */  }
/* 111:    */  
/* 112:    */  protected int transformY(int y) {
/* 113:113 */    if (this.component != null) {
/* 114:114 */      return this.component.getHeight() - 1 - y;
/* 115:    */    }
/* 116:116 */    return y;
/* 117:    */  }
/* 118:    */  
/* 119:    */  protected void resetCursorToCenter() {
/* 120:120 */    clearEvents();
/* 121:121 */    this.accum_dx = (this.accum_dy = 0);
/* 122:122 */    if (this.component != null) {
/* 123:123 */      Point cursor_location = AWTUtil.getCursorPosition(this.component);
/* 124:124 */      if (cursor_location != null) {
/* 125:125 */        this.last_x = cursor_location.x;
/* 126:126 */        this.last_y = cursor_location.y;
/* 127:    */      }
/* 128:    */    }
/* 129:    */  }
/* 130:    */  
/* 131:    */  private void putMouseEvent(byte button, byte state, int dz, long nanos) {
/* 132:132 */    if (this.grabbed) {
/* 133:133 */      putMouseEventWithCoords(button, state, 0, 0, dz, nanos);
/* 134:    */    } else
/* 135:135 */      putMouseEventWithCoords(button, state, this.last_x, this.last_y, dz, nanos);
/* 136:    */  }
/* 137:    */  
/* 138:    */  protected void putMouseEventWithCoords(byte button, byte state, int coord1, int coord2, int dz, long nanos) {
/* 139:139 */    this.event.clear();
/* 140:140 */    this.event.put(button).put(state).putInt(coord1).putInt(coord2).putInt(dz).putLong(nanos);
/* 141:141 */    this.event.flip();
/* 142:142 */    putEvent(this.event);
/* 143:    */  }
/* 144:    */  
/* 145:    */  public synchronized void poll(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
/* 146:146 */    if (this.grabbed) {
/* 147:147 */      coord_buffer.put(0, this.accum_dx);
/* 148:148 */      coord_buffer.put(1, this.accum_dy);
/* 149:    */    } else {
/* 150:150 */      coord_buffer.put(0, this.last_x);
/* 151:151 */      coord_buffer.put(1, this.last_y);
/* 152:    */    }
/* 153:153 */    coord_buffer.put(2, this.accum_dz);
/* 154:154 */    this.accum_dx = (this.accum_dy = this.accum_dz = 0);
/* 155:155 */    int old_position = buttons_buffer.position();
/* 156:156 */    buttons_buffer.put(this.buttons, 0, this.buttons.length);
/* 157:157 */    buttons_buffer.position(old_position);
/* 158:    */  }
/* 159:    */  
/* 160:    */  private void setCursorPos(int x, int y, long nanos) {
/* 161:161 */    y = transformY(y);
/* 162:162 */    if (this.grabbed)
/* 163:163 */      return;
/* 164:164 */    int dx = x - this.last_x;
/* 165:165 */    int dy = y - this.last_y;
/* 166:166 */    addDelta(dx, dy);
/* 167:167 */    this.last_x = x;
/* 168:168 */    this.last_y = y;
/* 169:169 */    putMouseEventWithCoords((byte)-1, (byte)0, x, y, 0, nanos);
/* 170:    */  }
/* 171:    */  
/* 172:    */  protected void addDelta(int dx, int dy) {
/* 173:173 */    this.accum_dx += dx;
/* 174:174 */    this.accum_dy += dy;
/* 175:    */  }
/* 176:    */  
/* 178:    */  public void mouseClicked(MouseEvent e) {}
/* 179:    */  
/* 181:    */  public void mouseEntered(MouseEvent e) {}
/* 182:    */  
/* 183:    */  public void mouseExited(MouseEvent e) {}
/* 184:    */  
/* 185:    */  private void handleButton(MouseEvent e)
/* 186:    */  {
/* 187:    */    byte state;
/* 188:188 */    switch (e.getID()) {
/* 189:    */    case 501: 
/* 190:190 */      state = 1;
/* 191:191 */      break;
/* 192:    */    case 502: 
/* 193:193 */      state = 0;
/* 194:194 */      break;
/* 195:    */    default: 
/* 196:196 */      throw new IllegalArgumentException("Not a valid event ID: " + e.getID());
/* 197:    */    }
/* 198:    */    byte button;
/* 199:199 */    switch (e.getButton())
/* 200:    */    {
/* 201:    */    case 0: 
/* 202:202 */      return;
/* 203:    */    
/* 204:    */    case 1: 
/* 205:205 */      if (state == 1)
/* 206:206 */        this.saved_control_state = e.isControlDown();
/* 207:207 */      byte button; if (this.saved_control_state) {
/* 208:208 */        if (this.buttons[1] == state)
/* 209:209 */          return;
/* 210:210 */        button = 1;
/* 211:    */      } else {
/* 212:212 */        button = 0;
/* 213:    */      }
/* 214:214 */      break;
/* 215:    */    case 2: 
/* 216:216 */      button = 2;
/* 217:217 */      break;
/* 218:    */    case 3: 
/* 219:219 */      if (this.buttons[1] == state)
/* 220:220 */        return;
/* 221:221 */      button = 1;
/* 222:222 */      break;
/* 223:    */    default: 
/* 224:224 */      throw new IllegalArgumentException("Not a valid button: " + e.getButton());
/* 225:    */    }
/* 226:226 */    setButton(button, state, e.getWhen() * 1000000L);
/* 227:    */  }
/* 228:    */  
/* 229:    */  public synchronized void mousePressed(MouseEvent e) {
/* 230:230 */    handleButton(e);
/* 231:    */  }
/* 232:    */  
/* 233:    */  private void setButton(byte button, byte state, long nanos) {
/* 234:234 */    this.buttons[button] = state;
/* 235:235 */    putMouseEvent(button, state, 0, nanos);
/* 236:    */  }
/* 237:    */  
/* 238:    */  public synchronized void mouseReleased(MouseEvent e) {
/* 239:239 */    handleButton(e);
/* 240:    */  }
/* 241:    */  
/* 242:    */  private void handleMotion(MouseEvent e) {
/* 243:243 */    if (this.grabbed) {
/* 244:244 */      updateDeltas(e.getWhen() * 1000000L);
/* 245:    */    } else {
/* 246:246 */      setCursorPos(e.getX(), e.getY(), e.getWhen() * 1000000L);
/* 247:    */    }
/* 248:    */  }
/* 249:    */  
/* 250:    */  public synchronized void mouseDragged(MouseEvent e) {
/* 251:251 */    handleMotion(e);
/* 252:    */  }
/* 253:    */  
/* 254:    */  public synchronized void mouseMoved(MouseEvent e) {
/* 255:255 */    handleMotion(e);
/* 256:    */  }
/* 257:    */  
/* 258:    */  private void handleWheel(int amount, long nanos) {
/* 259:259 */    this.accum_dz += amount;
/* 260:260 */    putMouseEvent((byte)-1, (byte)0, amount, nanos);
/* 261:    */  }
/* 262:    */  
/* 263:    */  protected void updateDeltas(long nanos) {}
/* 264:    */  
/* 265:    */  public synchronized void mouseWheelMoved(MouseWheelEvent e)
/* 266:    */  {
/* 267:267 */    int wheel_amount = -e.getWheelRotation() * 120;
/* 268:268 */    handleWheel(wheel_amount, e.getWhen() * 1000000L);
/* 269:    */  }
/* 270:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MouseEventQueue
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */