/*   1:    */package org.newdawn.slick.tests;
/*   2:    */
/*   3:    */import org.newdawn.slick.AppGameContainer;
/*   4:    */import org.newdawn.slick.BasicGame;
/*   5:    */import org.newdawn.slick.Font;
/*   6:    */import org.newdawn.slick.GameContainer;
/*   7:    */import org.newdawn.slick.Graphics;
/*   8:    */import org.newdawn.slick.SavedState;
/*   9:    */import org.newdawn.slick.SlickException;
/*  10:    */import org.newdawn.slick.gui.AbstractComponent;
/*  11:    */import org.newdawn.slick.gui.ComponentListener;
/*  12:    */import org.newdawn.slick.gui.TextField;
/*  13:    */
/*  19:    */public class SavedStateTest
/*  20:    */  extends BasicGame
/*  21:    */  implements ComponentListener
/*  22:    */{
/*  23:    */  private TextField name;
/*  24:    */  private TextField age;
/*  25: 25 */  private String nameValue = "none";
/*  26:    */  
/*  27: 27 */  private int ageValue = 0;
/*  28:    */  
/*  29:    */  private SavedState state;
/*  30:    */  
/*  31: 31 */  private String message = "Enter a name and age to store";
/*  32:    */  
/*  33:    */  private static AppGameContainer container;
/*  34:    */  
/*  35:    */  public SavedStateTest()
/*  36:    */  {
/*  37: 37 */    super("Saved State Test");
/*  38:    */  }
/*  39:    */  
/*  41:    */  public void init(GameContainer container)
/*  42:    */    throws SlickException
/*  43:    */  {
/*  44: 44 */    this.state = new SavedState("testdata");
/*  45: 45 */    this.nameValue = this.state.getString("name", "DefaultName");
/*  46: 46 */    this.ageValue = ((int)this.state.getNumber("age", 64.0D));
/*  47:    */    
/*  48: 48 */    this.name = new TextField(container, container.getDefaultFont(), 100, 100, 300, 20, this);
/*  49: 49 */    this.age = new TextField(container, container.getDefaultFont(), 100, 150, 201, 20, this);
/*  50:    */  }
/*  51:    */  
/*  54:    */  public void render(GameContainer container, Graphics g)
/*  55:    */  {
/*  56: 56 */    this.name.render(container, g);
/*  57: 57 */    this.age.render(container, g);
/*  58:    */    
/*  59: 59 */    container.getDefaultFont().drawString(100.0F, 300.0F, "Stored Name: " + this.nameValue);
/*  60: 60 */    container.getDefaultFont().drawString(100.0F, 350.0F, "Stored Age: " + this.ageValue);
/*  61: 61 */    container.getDefaultFont().drawString(200.0F, 500.0F, this.message);
/*  62:    */  }
/*  63:    */  
/*  66:    */  public void update(GameContainer container, int delta)
/*  67:    */    throws SlickException
/*  68:    */  {}
/*  69:    */  
/*  72:    */  public void keyPressed(int key, char c)
/*  73:    */  {
/*  74: 74 */    if (key == 1) {
/*  75: 75 */      System.exit(0);
/*  76:    */    }
/*  77:    */  }
/*  78:    */  
/*  85:    */  public static void main(String[] argv)
/*  86:    */  {
/*  87:    */    try
/*  88:    */    {
/*  89: 89 */      container = new AppGameContainer(new SavedStateTest());
/*  90: 90 */      container.setDisplayMode(800, 600, false);
/*  91: 91 */      container.start();
/*  92:    */    } catch (SlickException e) {
/*  93: 93 */      e.printStackTrace();
/*  94:    */    }
/*  95:    */  }
/*  96:    */  
/*  99:    */  public void componentActivated(AbstractComponent source)
/* 100:    */  {
/* 101:101 */    if (source == this.name) {
/* 102:102 */      this.nameValue = this.name.getText();
/* 103:103 */      this.state.setString("name", this.nameValue);
/* 104:    */    }
/* 105:105 */    if (source == this.age) {
/* 106:    */      try {
/* 107:107 */        this.ageValue = Integer.parseInt(this.age.getText());
/* 108:108 */        this.state.setNumber("age", this.ageValue);
/* 109:    */      }
/* 110:    */      catch (NumberFormatException e) {}
/* 111:    */    }
/* 112:    */    
/* 113:    */    try
/* 114:    */    {
/* 115:115 */      this.state.save();
/* 116:    */    } catch (Exception e) {
/* 117:117 */      this.message = (System.currentTimeMillis() + " : Failed to save state");
/* 118:    */    }
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.SavedStateTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */