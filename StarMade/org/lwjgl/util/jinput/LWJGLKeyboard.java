/*     */ package org.lwjgl.util.jinput;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.java.games.input.AbstractComponent;
/*     */ import net.java.games.input.Component;
/*     */ import net.java.games.input.Component.Identifier.Key;
/*     */ import net.java.games.input.Controller;
/*     */ import net.java.games.input.Event;
/*     */ import net.java.games.input.Rumbler;
/*     */ 
/*     */ final class LWJGLKeyboard extends net.java.games.input.Keyboard
/*     */ {
/*     */   LWJGLKeyboard()
/*     */   {
/*  53 */     super("LWJGLKeyboard", createComponents(), new Controller[0], new Rumbler[0]);
/*     */   }
/*     */ 
/*     */   private static Component[] createComponents() {
/*  57 */     List components = new ArrayList();
/*  58 */     Field[] vkey_fields = org.lwjgl.input.Keyboard.class.getFields();
/*  59 */     for (Field vkey_field : vkey_fields) {
/*     */       try {
/*  61 */         if ((Modifier.isStatic(vkey_field.getModifiers())) && (vkey_field.getType() == Integer.TYPE) && (vkey_field.getName().startsWith("KEY_")))
/*     */         {
/*  63 */           int vkey_code = vkey_field.getInt(null);
/*  64 */           Component.Identifier.Key key_id = KeyMap.map(vkey_code);
/*  65 */           if (key_id != Component.Identifier.Key.UNKNOWN)
/*  66 */             components.add(new Key(key_id, vkey_code));
/*     */         }
/*     */       } catch (IllegalAccessException e) {
/*  69 */         throw new RuntimeException(e);
/*     */       }
/*     */     }
/*  72 */     return (Component[])components.toArray(new Component[components.size()]);
/*     */   }
/*     */ 
/*     */   public synchronized void pollDevice() throws IOException {
/*  76 */     if (!org.lwjgl.input.Keyboard.isCreated())
/*  77 */       return;
/*  78 */     org.lwjgl.input.Keyboard.poll();
/*  79 */     for (Component component : getComponents()) {
/*  80 */       Key key = (Key)component;
/*  81 */       key.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected synchronized boolean getNextDeviceEvent(Event event) throws IOException {
/*  86 */     if (!org.lwjgl.input.Keyboard.isCreated())
/*  87 */       return false;
/*  88 */     if (!org.lwjgl.input.Keyboard.next())
/*  89 */       return false;
/*  90 */     int lwjgl_key = org.lwjgl.input.Keyboard.getEventKey();
/*  91 */     if (lwjgl_key == 0)
/*  92 */       return false;
/*  93 */     Component.Identifier.Key key_id = KeyMap.map(lwjgl_key);
/*  94 */     if (key_id == null)
/*  95 */       return false;
/*  96 */     Component key = getComponent(key_id);
/*  97 */     if (key == null)
/*  98 */       return false;
/*  99 */     float value = org.lwjgl.input.Keyboard.getEventKeyState() ? 1.0F : 0.0F;
/* 100 */     event.set(key, value, org.lwjgl.input.Keyboard.getEventNanoseconds());
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   private static final class Key extends AbstractComponent {
/*     */     private final int lwjgl_key;
/*     */     private float value;
/*     */ 
/*     */     Key(Component.Identifier.Key key_id, int lwjgl_key) {
/* 110 */       super(key_id);
/* 111 */       this.lwjgl_key = lwjgl_key;
/*     */     }
/*     */ 
/*     */     public void update() {
/* 115 */       this.value = (org.lwjgl.input.Keyboard.isKeyDown(this.lwjgl_key) ? 1.0F : 0.0F);
/*     */     }
/*     */ 
/*     */     protected float poll() {
/* 119 */       return this.value;
/*     */     }
/*     */ 
/*     */     public boolean isRelative() {
/* 123 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean isAnalog() {
/* 127 */       return false;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.jinput.LWJGLKeyboard
 * JD-Core Version:    0.6.2
 */