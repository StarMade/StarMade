/*   1:    */package org.lwjgl.util.jinput;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import net.java.games.input.AbstractComponent;
/*   5:    */import net.java.games.input.Component;
/*   6:    */import net.java.games.input.Component.Identifier.Axis;
/*   7:    */import net.java.games.input.Component.Identifier.Button;
/*   8:    */import net.java.games.input.Controller;
/*   9:    */import net.java.games.input.Event;
/*  10:    */import net.java.games.input.Rumbler;
/*  11:    */
/*  45:    */final class LWJGLMouse
/*  46:    */  extends net.java.games.input.Mouse
/*  47:    */{
/*  48:    */  private static final int EVENT_X = 1;
/*  49:    */  private static final int EVENT_Y = 2;
/*  50:    */  private static final int EVENT_WHEEL = 3;
/*  51:    */  private static final int EVENT_BUTTON = 4;
/*  52:    */  private static final int EVENT_DONE = 5;
/*  53: 53 */  private int event_state = 5;
/*  54:    */  
/*  55:    */  LWJGLMouse() {
/*  56: 56 */    super("LWJGLMouse", createComponents(), new Controller[0], new Rumbler[0]);
/*  57:    */  }
/*  58:    */  
/*  59:    */  private static Component[] createComponents() {
/*  60: 60 */    return new Component[] { new Axis(Component.Identifier.Axis.X), new Axis(Component.Identifier.Axis.Y), new Axis(Component.Identifier.Axis.Z), new Button(Component.Identifier.Button.LEFT), new Button(Component.Identifier.Button.MIDDLE), new Button(Component.Identifier.Button.RIGHT) };
/*  61:    */  }
/*  62:    */  
/*  66:    */  public synchronized void pollDevice()
/*  67:    */    throws IOException
/*  68:    */  {
/*  69: 69 */    if (!org.lwjgl.input.Mouse.isCreated())
/*  70: 70 */      return;
/*  71: 71 */    org.lwjgl.input.Mouse.poll();
/*  72: 72 */    for (int i = 0; i < 3; i++)
/*  73: 73 */      setButtonState(i);
/*  74:    */  }
/*  75:    */  
/*  76:    */  private Button map(int lwjgl_button) {
/*  77: 77 */    switch (lwjgl_button) {
/*  78:    */    case 0: 
/*  79: 79 */      return (Button)getLeft();
/*  80:    */    case 1: 
/*  81: 81 */      return (Button)getRight();
/*  82:    */    case 2: 
/*  83: 83 */      return (Button)getMiddle();
/*  84:    */    }
/*  85: 85 */    return null;
/*  86:    */  }
/*  87:    */  
/*  88:    */  private void setButtonState(int lwjgl_button)
/*  89:    */  {
/*  90: 90 */    Button button = map(lwjgl_button);
/*  91: 91 */    if (button != null)
/*  92: 92 */      button.setValue(org.lwjgl.input.Mouse.isButtonDown(lwjgl_button) ? 1.0F : 0.0F);
/*  93:    */  }
/*  94:    */  
/*  95:    */  protected synchronized boolean getNextDeviceEvent(Event event) throws IOException {
/*  96: 96 */    if (!org.lwjgl.input.Mouse.isCreated())
/*  97: 97 */      return false;
/*  98:    */    for (;;) {
/*  99: 99 */      long nanos = org.lwjgl.input.Mouse.getEventNanoseconds();
/* 100:100 */      switch (this.event_state) {
/* 101:    */      case 1: 
/* 102:102 */        this.event_state = 2;
/* 103:103 */        int dx = org.lwjgl.input.Mouse.getEventDX();
/* 104:104 */        if (dx != 0) {
/* 105:105 */          event.set(getX(), dx, nanos);
/* 106:106 */          return true;
/* 107:    */        }
/* 108:    */        break;
/* 109:    */      case 2: 
/* 110:110 */        this.event_state = 3;
/* 111:    */        
/* 114:114 */        int dy = -org.lwjgl.input.Mouse.getEventDY();
/* 115:115 */        if (dy != 0) {
/* 116:116 */          event.set(getY(), dy, nanos);
/* 117:117 */          return true;
/* 118:    */        }
/* 119:    */        break;
/* 120:    */      case 3: 
/* 121:121 */        this.event_state = 4;
/* 122:122 */        int dwheel = org.lwjgl.input.Mouse.getEventDWheel();
/* 123:123 */        if (dwheel != 0) {
/* 124:124 */          event.set(getWheel(), dwheel, nanos);
/* 125:125 */          return true;
/* 126:    */        }
/* 127:    */        break;
/* 128:    */      case 4: 
/* 129:129 */        this.event_state = 5;
/* 130:130 */        int lwjgl_button = org.lwjgl.input.Mouse.getEventButton();
/* 131:131 */        if (lwjgl_button != -1) {
/* 132:132 */          Button button = map(lwjgl_button);
/* 133:133 */          if (button != null) {
/* 134:134 */            event.set(button, org.lwjgl.input.Mouse.getEventButtonState() ? 1.0F : 0.0F, nanos);
/* 135:135 */            return true;
/* 136:    */          } }
/* 137:137 */        break;
/* 138:    */      
/* 139:    */      case 5: 
/* 140:140 */        if (!org.lwjgl.input.Mouse.next())
/* 141:141 */          return false;
/* 142:142 */        this.event_state = 1;
/* 143:    */      }
/* 144:    */    }
/* 145:    */  }
/* 146:    */  
/* 147:    */  static final class Axis
/* 148:    */    extends AbstractComponent
/* 149:    */  {
/* 150:    */    Axis(Component.Identifier.Axis axis_id)
/* 151:    */    {
/* 152:152 */      super(axis_id);
/* 153:    */    }
/* 154:    */    
/* 155:    */    public boolean isRelative() {
/* 156:156 */      return true;
/* 157:    */    }
/* 158:    */    
/* 159:    */    protected float poll() throws IOException {
/* 160:160 */      return 0.0F;
/* 161:    */    }
/* 162:    */    
/* 163:    */    public boolean isAnalog() {
/* 164:164 */      return true;
/* 165:    */    }
/* 166:    */  }
/* 167:    */  
/* 168:    */  static final class Button extends AbstractComponent {
/* 169:    */    private float value;
/* 170:    */    
/* 171:    */    Button(Component.Identifier.Button button_id) {
/* 172:172 */      super(button_id);
/* 173:    */    }
/* 174:    */    
/* 175:    */    void setValue(float value) {
/* 176:176 */      this.value = value;
/* 177:    */    }
/* 178:    */    
/* 179:    */    protected float poll() throws IOException {
/* 180:180 */      return this.value;
/* 181:    */    }
/* 182:    */    
/* 183:    */    public boolean isRelative() {
/* 184:184 */      return false;
/* 185:    */    }
/* 186:    */    
/* 187:    */    public boolean isAnalog() {
/* 188:188 */      return false;
/* 189:    */    }
/* 190:    */  }
/* 191:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.jinput.LWJGLMouse
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */