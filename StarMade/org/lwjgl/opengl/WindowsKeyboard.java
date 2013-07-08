package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import org.lwjgl.LWJGLException;

final class WindowsKeyboard
{
  private static final int MAPVK_VK_TO_VSC = 0;
  private static final int BUFFER_SIZE = 50;
  private final long hwnd;
  private final byte[] key_down_buffer = new byte[256];
  private final byte[] virt_key_down_buffer = new byte[256];
  private final EventQueue event_queue = new EventQueue(18);
  private final ByteBuffer tmp_event = ByteBuffer.allocate(18);
  private boolean grabbed;
  private boolean has_retained_event;
  private int retained_key_code;
  private byte retained_state;
  private int retained_char;
  private long retained_millis;
  private boolean retained_repeat;
  
  WindowsKeyboard(long hwnd)
    throws LWJGLException
  {
    this.hwnd = hwnd;
  }
  
  private static native boolean isWindowsNT();
  
  public void destroy() {}
  
  boolean isKeyDown(int lwjgl_keycode)
  {
    return this.key_down_buffer[lwjgl_keycode] == 1;
  }
  
  public void grab(boolean grab)
  {
    if (grab)
    {
      if (!this.grabbed) {
        this.grabbed = true;
      }
    }
    else if (this.grabbed) {
      this.grabbed = false;
    }
  }
  
  public void poll(ByteBuffer keyDownBuffer)
  {
    int old_position = keyDownBuffer.position();
    keyDownBuffer.put(this.key_down_buffer);
    keyDownBuffer.position(old_position);
  }
  
  private static native int MapVirtualKey(int paramInt1, int paramInt2);
  
  private static native int ToUnicode(int paramInt1, int paramInt2, ByteBuffer paramByteBuffer, CharBuffer paramCharBuffer, int paramInt3, int paramInt4);
  
  private static native int ToAscii(int paramInt1, int paramInt2, ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2, int paramInt3);
  
  private static native int GetKeyboardState(ByteBuffer paramByteBuffer);
  
  private static native short GetKeyState(int paramInt);
  
  private static native short GetAsyncKeyState(int paramInt);
  
  private void putEvent(int keycode, byte state, int local_ch, long millis, boolean repeat)
  {
    this.tmp_event.clear();
    this.tmp_event.putInt(keycode).put(state).putInt(local_ch).putLong(millis * 1000000L).put((byte)(repeat ? 1 : 0));
    this.tmp_event.flip();
    this.event_queue.putEvent(this.tmp_event);
  }
  
  private boolean checkShiftKey(int virt_key, byte state)
  {
    int key_state = GetKeyState(virt_key) >>> 15 & 0x1;
    int lwjgl_code = WindowsKeycodes.mapVirtualKeyToLWJGLCode(virt_key);
    return (this.key_down_buffer[lwjgl_code] == 1 - state) && (key_state == state);
  }
  
  private int translateShift(int scan_code, byte state)
  {
    if (checkShiftKey(160, state)) {
      return 160;
    }
    if (checkShiftKey(161, state)) {
      return 161;
    }
    if (scan_code == 42) {
      return 160;
    }
    if (scan_code == 54) {
      return 161;
    }
    return 160;
  }
  
  private int translateExtended(int virt_key, int scan_code, byte state, boolean extended)
  {
    switch (virt_key)
    {
    case 16: 
      return translateShift(scan_code, state);
    case 17: 
      return extended ? 163 : 162;
    case 18: 
      return extended ? 165 : 164;
    }
    return virt_key;
  }
  
  private void flushRetained()
  {
    if (this.has_retained_event)
    {
      this.has_retained_event = false;
      putEvent(this.retained_key_code, this.retained_state, this.retained_char, this.retained_millis, this.retained_repeat);
    }
  }
  
  private static boolean isKeyPressed(int state)
  {
    return (state & 0x1) == 1;
  }
  
  public void handleKey(int virt_key, int scan_code, boolean extended, byte event_state, long millis, boolean repeat)
  {
    virt_key = translateExtended(virt_key, scan_code, event_state, extended);
    if ((!repeat) && (isKeyPressed(event_state) == isKeyPressed(this.virt_key_down_buffer[virt_key]))) {
      return;
    }
    flushRetained();
    this.has_retained_event = true;
    int keycode = WindowsKeycodes.mapVirtualKeyToLWJGLCode(virt_key);
    if (keycode < this.key_down_buffer.length)
    {
      this.key_down_buffer[keycode] = event_state;
      this.virt_key_down_buffer[virt_key] = event_state;
    }
    this.retained_key_code = keycode;
    this.retained_state = event_state;
    this.retained_millis = millis;
    this.retained_char = 0;
    this.retained_repeat = repeat;
  }
  
  public void fireLostKeyEvents()
  {
    for (int local_i = 0; local_i < this.virt_key_down_buffer.length; local_i++) {
      if ((isKeyPressed(this.virt_key_down_buffer[local_i])) && ((GetAsyncKeyState(local_i) & 0x8000) == 0)) {
        handleKey(local_i, 0, false, (byte)0, System.currentTimeMillis(), false);
      }
    }
  }
  
  public void handleChar(int event_char, long millis, boolean repeat)
  {
    if ((this.has_retained_event) && (this.retained_char != 0)) {
      flushRetained();
    }
    if (!this.has_retained_event) {
      putEvent(0, (byte)0, event_char, millis, repeat);
    } else {
      this.retained_char = event_char;
    }
  }
  
  public void read(ByteBuffer buffer)
  {
    flushRetained();
    this.event_queue.copyEvents(buffer);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.WindowsKeyboard
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */