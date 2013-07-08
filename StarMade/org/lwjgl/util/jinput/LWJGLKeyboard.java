/*   1:    */package org.lwjgl.util.jinput;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.lang.reflect.Field;
/*   5:    */import java.lang.reflect.Modifier;
/*   6:    */import java.util.ArrayList;
/*   7:    */import java.util.List;
/*   8:    */import net.java.games.input.AbstractComponent;
/*   9:    */import net.java.games.input.Component;
/*  10:    */import net.java.games.input.Component.Identifier.Key;
/*  11:    */import net.java.games.input.Controller;
/*  12:    */import net.java.games.input.Event;
/*  13:    */import net.java.games.input.Rumbler;
/*  14:    */
/*  48:    */final class LWJGLKeyboard
/*  49:    */  extends net.java.games.input.Keyboard
/*  50:    */{
/*  51:    */  LWJGLKeyboard()
/*  52:    */  {
/*  53: 53 */    super("LWJGLKeyboard", createComponents(), new Controller[0], new Rumbler[0]);
/*  54:    */  }
/*  55:    */  
/*  56:    */  private static Component[] createComponents() {
/*  57: 57 */    List<Key> components = new ArrayList();
/*  58: 58 */    Field[] vkey_fields = org.lwjgl.input.Keyboard.class.getFields();
/*  59: 59 */    for (Field vkey_field : vkey_fields) {
/*  60:    */      try {
/*  61: 61 */        if ((Modifier.isStatic(vkey_field.getModifiers())) && (vkey_field.getType() == Integer.TYPE) && (vkey_field.getName().startsWith("KEY_")))
/*  62:    */        {
/*  63: 63 */          int vkey_code = vkey_field.getInt(null);
/*  64: 64 */          Component.Identifier.Key key_id = KeyMap.map(vkey_code);
/*  65: 65 */          if (key_id != Component.Identifier.Key.UNKNOWN)
/*  66: 66 */            components.add(new Key(key_id, vkey_code));
/*  67:    */        }
/*  68:    */      } catch (IllegalAccessException e) {
/*  69: 69 */        throw new RuntimeException(e);
/*  70:    */      }
/*  71:    */    }
/*  72: 72 */    return (Component[])components.toArray(new Component[components.size()]);
/*  73:    */  }
/*  74:    */  
/*  75:    */  public synchronized void pollDevice() throws IOException {
/*  76: 76 */    if (!org.lwjgl.input.Keyboard.isCreated())
/*  77: 77 */      return;
/*  78: 78 */    org.lwjgl.input.Keyboard.poll();
/*  79: 79 */    for (Component component : getComponents()) {
/*  80: 80 */      Key key = (Key)component;
/*  81: 81 */      key.update();
/*  82:    */    }
/*  83:    */  }
/*  84:    */  
/*  85:    */  protected synchronized boolean getNextDeviceEvent(Event event) throws IOException {
/*  86: 86 */    if (!org.lwjgl.input.Keyboard.isCreated())
/*  87: 87 */      return false;
/*  88: 88 */    if (!org.lwjgl.input.Keyboard.next())
/*  89: 89 */      return false;
/*  90: 90 */    int lwjgl_key = org.lwjgl.input.Keyboard.getEventKey();
/*  91: 91 */    if (lwjgl_key == 0)
/*  92: 92 */      return false;
/*  93: 93 */    Component.Identifier.Key key_id = KeyMap.map(lwjgl_key);
/*  94: 94 */    if (key_id == null)
/*  95: 95 */      return false;
/*  96: 96 */    Component key = getComponent(key_id);
/*  97: 97 */    if (key == null)
/*  98: 98 */      return false;
/*  99: 99 */    float value = org.lwjgl.input.Keyboard.getEventKeyState() ? 1.0F : 0.0F;
/* 100:100 */    event.set(key, value, org.lwjgl.input.Keyboard.getEventNanoseconds());
/* 101:101 */    return true;
/* 102:    */  }
/* 103:    */  
/* 104:    */  private static final class Key extends AbstractComponent
/* 105:    */  {
/* 106:    */    private final int lwjgl_key;
/* 107:    */    private float value;
/* 108:    */    
/* 109:    */    Key(Component.Identifier.Key key_id, int lwjgl_key) {
/* 110:110 */      super(key_id);
/* 111:111 */      this.lwjgl_key = lwjgl_key;
/* 112:    */    }
/* 113:    */    
/* 114:    */    public void update() {
/* 115:115 */      this.value = (org.lwjgl.input.Keyboard.isKeyDown(this.lwjgl_key) ? 1.0F : 0.0F);
/* 116:    */    }
/* 117:    */    
/* 118:    */    protected float poll() {
/* 119:119 */      return this.value;
/* 120:    */    }
/* 121:    */    
/* 122:    */    public boolean isRelative() {
/* 123:123 */      return false;
/* 124:    */    }
/* 125:    */    
/* 126:    */    public boolean isAnalog() {
/* 127:127 */      return false;
/* 128:    */    }
/* 129:    */  }
/* 130:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.jinput.LWJGLKeyboard
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */