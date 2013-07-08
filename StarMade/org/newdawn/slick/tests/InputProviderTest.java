/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import org.newdawn.slick.AppGameContainer;
/*  4:   */import org.newdawn.slick.BasicGame;
/*  5:   */import org.newdawn.slick.GameContainer;
/*  6:   */import org.newdawn.slick.Graphics;
/*  7:   */import org.newdawn.slick.SlickException;
/*  8:   */import org.newdawn.slick.command.BasicCommand;
/*  9:   */import org.newdawn.slick.command.Command;
/* 10:   */import org.newdawn.slick.command.ControllerButtonControl;
/* 11:   */import org.newdawn.slick.command.ControllerDirectionControl;
/* 12:   */import org.newdawn.slick.command.InputProvider;
/* 13:   */import org.newdawn.slick.command.InputProviderListener;
/* 14:   */import org.newdawn.slick.command.KeyControl;
/* 15:   */import org.newdawn.slick.command.MouseButtonControl;
/* 16:   */
/* 21:   */public class InputProviderTest
/* 22:   */  extends BasicGame
/* 23:   */  implements InputProviderListener
/* 24:   */{
/* 25:25 */  private Command attack = new BasicCommand("attack");
/* 26:   */  
/* 27:27 */  private Command jump = new BasicCommand("jump");
/* 28:   */  
/* 29:29 */  private Command run = new BasicCommand("run");
/* 30:   */  
/* 31:   */  private InputProvider provider;
/* 32:   */  
/* 33:33 */  private String message = "";
/* 34:   */  
/* 37:   */  public InputProviderTest()
/* 38:   */  {
/* 39:39 */    super("InputProvider Test");
/* 40:   */  }
/* 41:   */  
/* 43:   */  public void init(GameContainer container)
/* 44:   */    throws SlickException
/* 45:   */  {
/* 46:46 */    this.provider = new InputProvider(container.getInput());
/* 47:47 */    this.provider.addListener(this);
/* 48:   */    
/* 49:49 */    this.provider.bindCommand(new KeyControl(203), this.run);
/* 50:50 */    this.provider.bindCommand(new KeyControl(30), this.run);
/* 51:51 */    this.provider.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.LEFT), this.run);
/* 52:52 */    this.provider.bindCommand(new KeyControl(200), this.jump);
/* 53:53 */    this.provider.bindCommand(new KeyControl(17), this.jump);
/* 54:54 */    this.provider.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.UP), this.jump);
/* 55:55 */    this.provider.bindCommand(new KeyControl(57), this.attack);
/* 56:56 */    this.provider.bindCommand(new MouseButtonControl(0), this.attack);
/* 57:57 */    this.provider.bindCommand(new ControllerButtonControl(0, 1), this.attack);
/* 58:   */  }
/* 59:   */  
/* 62:   */  public void render(GameContainer container, Graphics g)
/* 63:   */  {
/* 64:64 */    g.drawString("Press A, W, Left, Up, space, mouse button 1,and gamepad controls", 10.0F, 50.0F);
/* 65:65 */    g.drawString(this.message, 100.0F, 150.0F);
/* 66:   */  }
/* 67:   */  
/* 71:   */  public void update(GameContainer container, int delta) {}
/* 72:   */  
/* 76:   */  public void controlPressed(Command command)
/* 77:   */  {
/* 78:78 */    this.message = ("Pressed: " + command);
/* 79:   */  }
/* 80:   */  
/* 83:   */  public void controlReleased(Command command)
/* 84:   */  {
/* 85:85 */    this.message = ("Released: " + command);
/* 86:   */  }
/* 87:   */  
/* 91:   */  public static void main(String[] argv)
/* 92:   */  {
/* 93:   */    try
/* 94:   */    {
/* 95:95 */      AppGameContainer container = new AppGameContainer(new InputProviderTest());
/* 96:96 */      container.setDisplayMode(800, 600, false);
/* 97:97 */      container.start();
/* 98:   */    } catch (SlickException e) {
/* 99:99 */      e.printStackTrace();
/* 100:   */    }
/* 101:   */  }
/* 102:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.InputProviderTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */