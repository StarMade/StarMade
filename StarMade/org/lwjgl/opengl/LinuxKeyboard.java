package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLUtil;

final class LinuxKeyboard
{
  private static final int LockMapIndex = 1;
  private static final long NoSymbol = 0L;
  private static final long ShiftMask = 1L;
  private static final long LockMask = 2L;
  private static final int XLookupChars = 2;
  private static final int XLookupBoth = 4;
  private static final int KEYBOARD_BUFFER_SIZE = 50;
  private final long xim;
  private final long xic;
  private final int numlock_mask;
  private final int modeswitch_mask;
  private final int caps_lock_mask;
  private final int shift_lock_mask;
  private final ByteBuffer compose_status;
  private final byte[] key_down_buffer = new byte[256];
  private final EventQueue event_queue = new EventQueue(18);
  private final ByteBuffer tmp_event = ByteBuffer.allocate(18);
  private final int[] temp_translation_buffer = new int[50];
  private final ByteBuffer native_translation_buffer = BufferUtils.createByteBuffer(50);
  private final CharsetDecoder utf8_decoder = Charset.forName("UTF-8").newDecoder();
  private final CharBuffer char_buffer = CharBuffer.allocate(50);
  private boolean has_deferred_event;
  private int deferred_keycode;
  private int deferred_event_keycode;
  private long deferred_nanos;
  private byte deferred_key_state;
  
  LinuxKeyboard(long display, long window)
  {
    long modifier_map = getModifierMapping(display);
    int tmp_numlock_mask = 0;
    int tmp_modeswitch_mask = 0;
    int tmp_caps_lock_mask = 0;
    int tmp_shift_lock_mask = 0;
    if (modifier_map != 0L)
    {
      int max_keypermod = getMaxKeyPerMod(modifier_map);
      for (int local_i = 0; local_i < 8; local_i++) {
        for (int local_j = 0; local_j < max_keypermod; local_j++)
        {
          int key_code = lookupModifierMap(modifier_map, local_i * max_keypermod + local_j);
          int key_sym = (int)keycodeToKeySym(display, key_code);
          int mask = 1 << local_i;
          switch (key_sym)
          {
          case 65407: 
            tmp_numlock_mask |= mask;
            break;
          case 65406: 
            tmp_modeswitch_mask |= mask;
            break;
          case 65509: 
            if (local_i == 1)
            {
              tmp_caps_lock_mask = mask;
              tmp_shift_lock_mask = 0;
            }
            break;
          case 65510: 
            if ((local_i == 1) && (tmp_caps_lock_mask == 0)) {
              tmp_shift_lock_mask = mask;
            }
            break;
          }
        }
      }
      freeModifierMapping(modifier_map);
    }
    this.numlock_mask = tmp_numlock_mask;
    this.modeswitch_mask = tmp_modeswitch_mask;
    this.caps_lock_mask = tmp_caps_lock_mask;
    this.shift_lock_mask = tmp_shift_lock_mask;
    setDetectableKeyRepeat(display, true);
    this.xim = openIM(display);
    if (this.xim != 0L)
    {
      this.xic = createIC(this.xim, window);
      if (this.xic != 0L) {
        setupIMEventMask(display, window, this.xic);
      } else {
        destroy(display);
      }
    }
    else
    {
      this.xic = 0L;
    }
    this.compose_status = allocateComposeStatus();
  }
  
  private static native long getModifierMapping(long paramLong);
  
  private static native void freeModifierMapping(long paramLong);
  
  private static native int getMaxKeyPerMod(long paramLong);
  
  private static native int lookupModifierMap(long paramLong, int paramInt);
  
  private static native long keycodeToKeySym(long paramLong, int paramInt);
  
  private static native long openIM(long paramLong);
  
  private static native long createIC(long paramLong1, long paramLong2);
  
  private static native void setupIMEventMask(long paramLong1, long paramLong2, long paramLong3);
  
  private static native ByteBuffer allocateComposeStatus();
  
  private static void setDetectableKeyRepeat(long display, boolean enabled)
  {
    boolean success = nSetDetectableKeyRepeat(display, enabled);
    if (!success) {
      LWJGLUtil.log("Failed to set detectable key repeat to " + enabled);
    }
  }
  
  private static native boolean nSetDetectableKeyRepeat(long paramLong, boolean paramBoolean);
  
  public void destroy(long display)
  {
    if (this.xic != 0L) {
      destroyIC(this.xic);
    }
    if (this.xim != 0L) {
      closeIM(this.xim);
    }
    setDetectableKeyRepeat(display, false);
  }
  
  private static native void destroyIC(long paramLong);
  
  private static native void closeIM(long paramLong);
  
  public void read(ByteBuffer buffer)
  {
    flushDeferredEvent();
    this.event_queue.copyEvents(buffer);
  }
  
  public void poll(ByteBuffer keyDownBuffer)
  {
    flushDeferredEvent();
    int old_position = keyDownBuffer.position();
    keyDownBuffer.put(this.key_down_buffer);
    keyDownBuffer.position(old_position);
  }
  
  private void putKeyboardEvent(int keycode, byte state, int local_ch, long nanos, boolean repeat)
  {
    this.tmp_event.clear();
    this.tmp_event.putInt(keycode).put(state).putInt(local_ch).putLong(nanos).put((byte)(repeat ? 1 : 0));
    this.tmp_event.flip();
    this.event_queue.putEvent(this.tmp_event);
  }
  
  private int lookupStringISO88591(long event_ptr, int[] translation_buffer)
  {
    int num_chars = lookupString(event_ptr, this.native_translation_buffer, this.compose_status);
    for (int local_i = 0; local_i < num_chars; local_i++) {
      translation_buffer[local_i] = (this.native_translation_buffer.get(local_i) & 0xFF);
    }
    return num_chars;
  }
  
  private static native int lookupString(long paramLong, ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2);
  
  private int lookupStringUnicode(long event_ptr, int[] translation_buffer)
  {
    int status = utf8LookupString(this.xic, event_ptr, this.native_translation_buffer, this.native_translation_buffer.position(), this.native_translation_buffer.remaining());
    if ((status != 2) && (status != 4)) {
      return 0;
    }
    this.native_translation_buffer.flip();
    this.utf8_decoder.decode(this.native_translation_buffer, this.char_buffer, true);
    this.native_translation_buffer.compact();
    this.char_buffer.flip();
    int local_i = 0;
    while ((this.char_buffer.hasRemaining()) && (local_i < translation_buffer.length)) {
      translation_buffer[(local_i++)] = this.char_buffer.get();
    }
    this.char_buffer.compact();
    return local_i;
  }
  
  private static native int utf8LookupString(long paramLong1, long paramLong2, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  private int lookupString(long event_ptr, int[] translation_buffer)
  {
    if (this.xic != 0L) {
      return lookupStringUnicode(event_ptr, translation_buffer);
    }
    return lookupStringISO88591(event_ptr, translation_buffer);
  }
  
  private void translateEvent(long event_ptr, int keycode, byte key_state, long nanos, boolean repeat)
  {
    int num_chars = lookupString(event_ptr, this.temp_translation_buffer);
    if (num_chars > 0)
    {
      int local_ch = this.temp_translation_buffer[0];
      putKeyboardEvent(keycode, key_state, local_ch, nanos, repeat);
      for (int local_i = 1; local_i < num_chars; local_i++)
      {
        local_ch = this.temp_translation_buffer[local_i];
        putKeyboardEvent(0, (byte)0, local_ch, nanos, repeat);
      }
    }
    putKeyboardEvent(keycode, key_state, 0, nanos, repeat);
  }
  
  private static boolean isKeypadKeysym(long keysym)
  {
    return ((65408L <= keysym) && (keysym <= 65469L)) || ((285212672L <= keysym) && (keysym <= 285278207L));
  }
  
  private static boolean isNoSymbolOrVendorSpecific(long keysym)
  {
    return (keysym == 0L) || ((keysym & 0x10000000) != 0L);
  }
  
  private static long getKeySym(long event_ptr, int group, int index)
  {
    long keysym = lookupKeysym(event_ptr, group * 2 + index);
    if ((isNoSymbolOrVendorSpecific(keysym)) && (index == 1)) {
      keysym = lookupKeysym(event_ptr, group * 2 + 0);
    }
    if ((isNoSymbolOrVendorSpecific(keysym)) && (group == 1)) {
      keysym = getKeySym(event_ptr, 0, index);
    }
    return keysym;
  }
  
  private static native long lookupKeysym(long paramLong, int paramInt);
  
  private static native long toUpper(long paramLong);
  
  private long mapEventToKeySym(long event_ptr, int event_state)
  {
    int group;
    int group;
    if ((event_state & this.modeswitch_mask) != 0) {
      group = 1;
    } else {
      group = 0;
    }
    long keysym;
    if (((event_state & this.numlock_mask) != 0) && (isKeypadKeysym(keysym = getKeySym(event_ptr, group, 1))))
    {
      if ((event_state & (1L | this.shift_lock_mask)) != 0L) {
        return getKeySym(event_ptr, group, 0);
      }
      return keysym;
    }
    if ((event_state & 0x3) == 0L) {
      return getKeySym(event_ptr, group, 0);
    }
    if ((event_state & 1L) == 0L)
    {
      long keysym = getKeySym(event_ptr, group, 0);
      if ((event_state & this.caps_lock_mask) != 0) {
        keysym = toUpper(keysym);
      }
      return keysym;
    }
    long keysym = getKeySym(event_ptr, group, 1);
    if ((event_state & this.caps_lock_mask) != 0) {
      keysym = toUpper(keysym);
    }
    return keysym;
  }
  
  private int getKeycode(long event_ptr, int event_state)
  {
    long keysym = mapEventToKeySym(event_ptr, event_state);
    int keycode = LinuxKeycodes.mapKeySymToLWJGLKeyCode(keysym);
    if (keycode == 0)
    {
      keysym = lookupKeysym(event_ptr, 0);
      keycode = LinuxKeycodes.mapKeySymToLWJGLKeyCode(keysym);
    }
    return keycode;
  }
  
  private byte getKeyState(int event_type)
  {
    switch (event_type)
    {
    case 2: 
      return 1;
    case 3: 
      return 0;
    }
    throw new IllegalArgumentException("Unknown event_type: " + event_type);
  }
  
  private void handleKeyEvent(long event_ptr, long millis, int event_type, int event_keycode, int event_state)
  {
    int keycode = getKeycode(event_ptr, event_state);
    byte key_state = getKeyState(event_type);
    boolean repeat = key_state == this.key_down_buffer[keycode];
    this.key_down_buffer[keycode] = key_state;
    long nanos = millis * 1000000L;
    if (event_type == 2)
    {
      if (this.has_deferred_event) {
        if ((nanos == this.deferred_nanos) && (event_keycode == this.deferred_event_keycode))
        {
          this.has_deferred_event = false;
          repeat = true;
        }
        else
        {
          flushDeferredEvent();
        }
      }
      translateEvent(event_ptr, keycode, key_state, nanos, repeat);
    }
    else
    {
      flushDeferredEvent();
      this.has_deferred_event = true;
      this.deferred_keycode = keycode;
      this.deferred_event_keycode = event_keycode;
      this.deferred_nanos = nanos;
      this.deferred_key_state = key_state;
    }
  }
  
  private void flushDeferredEvent()
  {
    if (this.has_deferred_event)
    {
      putKeyboardEvent(this.deferred_keycode, this.deferred_key_state, 0, this.deferred_nanos, false);
      this.has_deferred_event = false;
    }
  }
  
  public boolean filterEvent(LinuxEvent event)
  {
    switch (event.getType())
    {
    case 2: 
    case 3: 
      handleKeyEvent(event.getKeyAddress(), event.getKeyTime(), event.getKeyType(), event.getKeyKeyCode(), event.getKeyState());
      return true;
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.LinuxKeyboard
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */