/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.CharBuffer;
/*   5:    */import org.lwjgl.LWJGLException;
/*   6:    */
/*  46:    */final class WindowsKeyboard
/*  47:    */{
/*  48:    */  private static final int MAPVK_VK_TO_VSC = 0;
/*  49:    */  private static final int BUFFER_SIZE = 50;
/*  50:    */  private final long hwnd;
/*  51: 51 */  private final byte[] key_down_buffer = new byte[256];
/*  52: 52 */  private final byte[] virt_key_down_buffer = new byte[256];
/*  53: 53 */  private final EventQueue event_queue = new EventQueue(18);
/*  54: 54 */  private final ByteBuffer tmp_event = ByteBuffer.allocate(18);
/*  55:    */  private boolean grabbed;
/*  56:    */  private boolean has_retained_event;
/*  57:    */  private int retained_key_code;
/*  58:    */  private byte retained_state;
/*  59:    */  private int retained_char;
/*  60:    */  private long retained_millis;
/*  61:    */  private boolean retained_repeat;
/*  62:    */  
/*  63:    */  WindowsKeyboard(long hwnd)
/*  64:    */    throws LWJGLException
/*  65:    */  {
/*  66: 66 */    this.hwnd = hwnd;
/*  67:    */  }
/*  68:    */  
/*  69:    */  private static native boolean isWindowsNT();
/*  70:    */  
/*  71:    */  public void destroy() {}
/*  72:    */  
/*  73:    */  boolean isKeyDown(int lwjgl_keycode) {
/*  74: 74 */    return this.key_down_buffer[lwjgl_keycode] == 1;
/*  75:    */  }
/*  76:    */  
/*  77:    */  public void grab(boolean grab) {
/*  78: 78 */    if (grab) {
/*  79: 79 */      if (!this.grabbed) {
/*  80: 80 */        this.grabbed = true;
/*  81:    */      }
/*  82:    */    }
/*  83: 83 */    else if (this.grabbed) {
/*  84: 84 */      this.grabbed = false;
/*  85:    */    }
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void poll(ByteBuffer keyDownBuffer)
/*  89:    */  {
/*  90: 90 */    int old_position = keyDownBuffer.position();
/*  91: 91 */    keyDownBuffer.put(this.key_down_buffer);
/*  92: 92 */    keyDownBuffer.position(old_position); }
/*  93:    */  
/*  94:    */  private static native int MapVirtualKey(int paramInt1, int paramInt2);
/*  95:    */  
/*  96:    */  private static native int ToUnicode(int paramInt1, int paramInt2, ByteBuffer paramByteBuffer, CharBuffer paramCharBuffer, int paramInt3, int paramInt4);
/*  97:    */  
/*  98:    */  private static native int ToAscii(int paramInt1, int paramInt2, ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2, int paramInt3);
/*  99:    */  
/* 100:    */  private static native int GetKeyboardState(ByteBuffer paramByteBuffer);
/* 101:    */  private static native short GetKeyState(int paramInt);
/* 102:    */  private static native short GetAsyncKeyState(int paramInt);
/* 103:103 */  private void putEvent(int keycode, byte state, int ch, long millis, boolean repeat) { this.tmp_event.clear();
/* 104:104 */    this.tmp_event.putInt(keycode).put(state).putInt(ch).putLong(millis * 1000000L).put((byte)(repeat ? 1 : 0));
/* 105:105 */    this.tmp_event.flip();
/* 106:106 */    this.event_queue.putEvent(this.tmp_event);
/* 107:    */  }
/* 108:    */  
/* 109:    */  private boolean checkShiftKey(int virt_key, byte state) {
/* 110:110 */    int key_state = GetKeyState(virt_key) >>> 15 & 0x1;
/* 111:111 */    int lwjgl_code = WindowsKeycodes.mapVirtualKeyToLWJGLCode(virt_key);
/* 112:112 */    return (this.key_down_buffer[lwjgl_code] == 1 - state) && (key_state == state);
/* 113:    */  }
/* 114:    */  
/* 115:    */  private int translateShift(int scan_code, byte state) {
/* 116:116 */    if (checkShiftKey(160, state))
/* 117:117 */      return 160;
/* 118:118 */    if (checkShiftKey(161, state)) {
/* 119:119 */      return 161;
/* 120:    */    }
/* 121:121 */    if (scan_code == 42) {
/* 122:122 */      return 160;
/* 123:    */    }
/* 124:124 */    if (scan_code == 54) {
/* 125:125 */      return 161;
/* 126:    */    }
/* 127:127 */    return 160;
/* 128:    */  }
/* 129:    */  
/* 131:    */  private int translateExtended(int virt_key, int scan_code, byte state, boolean extended)
/* 132:    */  {
/* 133:133 */    switch (virt_key) {
/* 134:    */    case 16: 
/* 135:135 */      return translateShift(scan_code, state);
/* 136:    */    case 17: 
/* 137:137 */      return extended ? 163 : 162;
/* 138:    */    case 18: 
/* 139:139 */      return extended ? 165 : 164;
/* 140:    */    }
/* 141:141 */    return virt_key;
/* 142:    */  }
/* 143:    */  
/* 144:    */  private void flushRetained()
/* 145:    */  {
/* 146:146 */    if (this.has_retained_event) {
/* 147:147 */      this.has_retained_event = false;
/* 148:148 */      putEvent(this.retained_key_code, this.retained_state, this.retained_char, this.retained_millis, this.retained_repeat);
/* 149:    */    }
/* 150:    */  }
/* 151:    */  
/* 152:    */  private static boolean isKeyPressed(int state) {
/* 153:153 */    return (state & 0x1) == 1;
/* 154:    */  }
/* 155:    */  
/* 156:    */  public void handleKey(int virt_key, int scan_code, boolean extended, byte event_state, long millis, boolean repeat) {
/* 157:157 */    virt_key = translateExtended(virt_key, scan_code, event_state, extended);
/* 158:158 */    if ((!repeat) && (isKeyPressed(event_state) == isKeyPressed(this.virt_key_down_buffer[virt_key]))) {
/* 159:159 */      return;
/* 160:    */    }
/* 161:161 */    flushRetained();
/* 162:162 */    this.has_retained_event = true;
/* 163:163 */    int keycode = WindowsKeycodes.mapVirtualKeyToLWJGLCode(virt_key);
/* 164:164 */    if (keycode < this.key_down_buffer.length) {
/* 165:165 */      this.key_down_buffer[keycode] = event_state;
/* 166:166 */      this.virt_key_down_buffer[virt_key] = event_state;
/* 167:    */    }
/* 168:168 */    this.retained_key_code = keycode;
/* 169:169 */    this.retained_state = event_state;
/* 170:170 */    this.retained_millis = millis;
/* 171:171 */    this.retained_char = 0;
/* 172:172 */    this.retained_repeat = repeat;
/* 173:    */  }
/* 174:    */  
/* 175:    */  public void fireLostKeyEvents()
/* 176:    */  {
/* 177:177 */    for (int i = 0; i < this.virt_key_down_buffer.length; i++) {
/* 178:178 */      if ((isKeyPressed(this.virt_key_down_buffer[i])) && ((GetAsyncKeyState(i) & 0x8000) == 0))
/* 179:179 */        handleKey(i, 0, false, (byte)0, System.currentTimeMillis(), false);
/* 180:    */    }
/* 181:    */  }
/* 182:    */  
/* 183:    */  public void handleChar(int event_char, long millis, boolean repeat) {
/* 184:184 */    if ((this.has_retained_event) && (this.retained_char != 0))
/* 185:185 */      flushRetained();
/* 186:186 */    if (!this.has_retained_event) {
/* 187:187 */      putEvent(0, (byte)0, event_char, millis, repeat);
/* 188:    */    } else
/* 189:189 */      this.retained_char = event_char;
/* 190:    */  }
/* 191:    */  
/* 192:    */  public void read(ByteBuffer buffer) {
/* 193:193 */    flushRetained();
/* 194:194 */    this.event_queue.copyEvents(buffer);
/* 195:    */  }
/* 196:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsKeyboard
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */