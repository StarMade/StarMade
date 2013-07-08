/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */
/*  44:    */final class LinuxEvent
/*  45:    */{
/*  46:    */  public static final int FocusIn = 9;
/*  47:    */  public static final int FocusOut = 10;
/*  48:    */  public static final int KeyPress = 2;
/*  49:    */  public static final int KeyRelease = 3;
/*  50:    */  public static final int ButtonPress = 4;
/*  51:    */  public static final int ButtonRelease = 5;
/*  52:    */  public static final int MotionNotify = 6;
/*  53:    */  public static final int EnterNotify = 7;
/*  54:    */  public static final int LeaveNotify = 8;
/*  55:    */  public static final int UnmapNotify = 18;
/*  56:    */  public static final int MapNotify = 19;
/*  57:    */  public static final int Expose = 12;
/*  58:    */  public static final int ConfigureNotify = 22;
/*  59:    */  public static final int ClientMessage = 33;
/*  60:    */  private final ByteBuffer event_buffer;
/*  61:    */  
/*  62: 62 */  LinuxEvent() { this.event_buffer = createEventBuffer(); }
/*  63:    */  
/*  64:    */  private static native ByteBuffer createEventBuffer();
/*  65:    */  
/*  66:    */  public void copyFrom(LinuxEvent event) {
/*  67: 67 */    int pos = this.event_buffer.position();
/*  68: 68 */    int event_pos = event.event_buffer.position();
/*  69: 69 */    this.event_buffer.put(event.event_buffer);
/*  70: 70 */    this.event_buffer.position(pos);
/*  71: 71 */    event.event_buffer.position(event_pos);
/*  72:    */  }
/*  73:    */  
/*  74:    */  public static native int getPending(long paramLong);
/*  75:    */  
/*  76:    */  public void sendEvent(long display, long window, boolean propagate, long event_mask) {
/*  77: 77 */    nSendEvent(this.event_buffer, display, window, propagate, event_mask);
/*  78:    */  }
/*  79:    */  
/*  80:    */  private static native void nSendEvent(ByteBuffer paramByteBuffer, long paramLong1, long paramLong2, boolean paramBoolean, long paramLong3);
/*  81:    */  
/*  82: 82 */  public boolean filterEvent(long window) { return nFilterEvent(this.event_buffer, window); }
/*  83:    */  
/*  84:    */  private static native boolean nFilterEvent(ByteBuffer paramByteBuffer, long paramLong);
/*  85:    */  
/*  86:    */  public void nextEvent(long display) {
/*  87: 87 */    nNextEvent(display, this.event_buffer);
/*  88:    */  }
/*  89:    */  
/*  90:    */  private static native void nNextEvent(long paramLong, ByteBuffer paramByteBuffer);
/*  91:    */  
/*  92: 92 */  public int getType() { return nGetType(this.event_buffer); }
/*  93:    */  
/*  94:    */  private static native int nGetType(ByteBuffer paramByteBuffer);
/*  95:    */  
/*  96:    */  public long getWindow() {
/*  97: 97 */    return nGetWindow(this.event_buffer);
/*  98:    */  }
/*  99:    */  
/* 100:    */  private static native long nGetWindow(ByteBuffer paramByteBuffer);
/* 101:    */  
/* 102:102 */  public void setWindow(long window) { nSetWindow(this.event_buffer, window); }
/* 103:    */  
/* 105:    */  private static native void nSetWindow(ByteBuffer paramByteBuffer, long paramLong);
/* 106:    */  
/* 107:    */  public int getFocusMode()
/* 108:    */  {
/* 109:109 */    return nGetFocusMode(this.event_buffer);
/* 110:    */  }
/* 111:    */  
/* 112:    */  private static native int nGetFocusMode(ByteBuffer paramByteBuffer);
/* 113:    */  
/* 114:114 */  public int getFocusDetail() { return nGetFocusDetail(this.event_buffer); }
/* 115:    */  
/* 117:    */  private static native int nGetFocusDetail(ByteBuffer paramByteBuffer);
/* 118:    */  
/* 119:    */  public long getClientMessageType()
/* 120:    */  {
/* 121:121 */    return nGetClientMessageType(this.event_buffer);
/* 122:    */  }
/* 123:    */  
/* 124:    */  private static native long nGetClientMessageType(ByteBuffer paramByteBuffer);
/* 125:    */  
/* 126:126 */  public int getClientData(int index) { return nGetClientData(this.event_buffer, index); }
/* 127:    */  
/* 128:    */  private static native int nGetClientData(ByteBuffer paramByteBuffer, int paramInt);
/* 129:    */  
/* 130:    */  public int getClientFormat() {
/* 131:131 */    return nGetClientFormat(this.event_buffer);
/* 132:    */  }
/* 133:    */  
/* 134:    */  private static native int nGetClientFormat(ByteBuffer paramByteBuffer);
/* 135:    */  
/* 136:    */  public long getButtonTime()
/* 137:    */  {
/* 138:138 */    return nGetButtonTime(this.event_buffer);
/* 139:    */  }
/* 140:    */  
/* 141:    */  private static native long nGetButtonTime(ByteBuffer paramByteBuffer);
/* 142:    */  
/* 143:143 */  public int getButtonState() { return nGetButtonState(this.event_buffer); }
/* 144:    */  
/* 145:    */  private static native int nGetButtonState(ByteBuffer paramByteBuffer);
/* 146:    */  
/* 147:    */  public int getButtonType() {
/* 148:148 */    return nGetButtonType(this.event_buffer);
/* 149:    */  }
/* 150:    */  
/* 151:    */  private static native int nGetButtonType(ByteBuffer paramByteBuffer);
/* 152:    */  
/* 153:153 */  public int getButtonButton() { return nGetButtonButton(this.event_buffer); }
/* 154:    */  
/* 155:    */  private static native int nGetButtonButton(ByteBuffer paramByteBuffer);
/* 156:    */  
/* 157:    */  public long getButtonRoot() {
/* 158:158 */    return nGetButtonRoot(this.event_buffer);
/* 159:    */  }
/* 160:    */  
/* 161:    */  private static native long nGetButtonRoot(ByteBuffer paramByteBuffer);
/* 162:    */  
/* 163:163 */  public int getButtonXRoot() { return nGetButtonXRoot(this.event_buffer); }
/* 164:    */  
/* 165:    */  private static native int nGetButtonXRoot(ByteBuffer paramByteBuffer);
/* 166:    */  
/* 167:    */  public int getButtonYRoot() {
/* 168:168 */    return nGetButtonYRoot(this.event_buffer);
/* 169:    */  }
/* 170:    */  
/* 171:    */  private static native int nGetButtonYRoot(ByteBuffer paramByteBuffer);
/* 172:    */  
/* 173:173 */  public int getButtonX() { return nGetButtonX(this.event_buffer); }
/* 174:    */  
/* 175:    */  private static native int nGetButtonX(ByteBuffer paramByteBuffer);
/* 176:    */  
/* 177:    */  public int getButtonY() {
/* 178:178 */    return nGetButtonY(this.event_buffer);
/* 179:    */  }
/* 180:    */  
/* 181:    */  private static native int nGetButtonY(ByteBuffer paramByteBuffer);
/* 182:    */  
/* 183:    */  public long getKeyAddress()
/* 184:    */  {
/* 185:185 */    return nGetKeyAddress(this.event_buffer);
/* 186:    */  }
/* 187:    */  
/* 188:    */  private static native long nGetKeyAddress(ByteBuffer paramByteBuffer);
/* 189:    */  
/* 190:190 */  public long getKeyTime() { return nGetKeyTime(this.event_buffer); }
/* 191:    */  
/* 192:    */  private static native int nGetKeyTime(ByteBuffer paramByteBuffer);
/* 193:    */  
/* 194:    */  public int getKeyType() {
/* 195:195 */    return nGetKeyType(this.event_buffer);
/* 196:    */  }
/* 197:    */  
/* 198:    */  private static native int nGetKeyType(ByteBuffer paramByteBuffer);
/* 199:    */  
/* 200:200 */  public int getKeyKeyCode() { return nGetKeyKeyCode(this.event_buffer); }
/* 201:    */  
/* 202:    */  private static native int nGetKeyKeyCode(ByteBuffer paramByteBuffer);
/* 203:    */  
/* 204:    */  public int getKeyState() {
/* 205:205 */    return nGetKeyState(this.event_buffer);
/* 206:    */  }
/* 207:    */  
/* 208:    */  private static native int nGetKeyState(ByteBuffer paramByteBuffer);
/* 209:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxEvent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */