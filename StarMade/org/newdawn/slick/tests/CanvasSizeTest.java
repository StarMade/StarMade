/*  1:   */package org.newdawn.slick.tests;
/*  2:   */
/*  3:   */import java.awt.Frame;
/*  4:   */import java.awt.GridLayout;
/*  5:   */import java.awt.event.WindowAdapter;
/*  6:   */import java.awt.event.WindowEvent;
/*  7:   */import java.io.PrintStream;
/*  8:   */import org.newdawn.slick.BasicGame;
/*  9:   */import org.newdawn.slick.CanvasGameContainer;
/* 10:   */import org.newdawn.slick.GameContainer;
/* 11:   */import org.newdawn.slick.Graphics;
/* 12:   */import org.newdawn.slick.SlickException;
/* 13:   */import org.newdawn.slick.util.Log;
/* 14:   */
/* 21:   */public class CanvasSizeTest
/* 22:   */  extends BasicGame
/* 23:   */{
/* 24:   */  public CanvasSizeTest()
/* 25:   */  {
/* 26:26 */    super("Test");
/* 27:   */  }
/* 28:   */  
/* 30:   */  public void init(GameContainer container)
/* 31:   */    throws SlickException
/* 32:   */  {
/* 33:33 */    System.out.println(container.getWidth() + ", " + container.getHeight());
/* 34:   */  }
/* 35:   */  
/* 39:   */  public void render(GameContainer container, Graphics g)
/* 40:   */    throws SlickException
/* 41:   */  {}
/* 42:   */  
/* 46:   */  public void update(GameContainer container, int delta)
/* 47:   */    throws SlickException
/* 48:   */  {}
/* 49:   */  
/* 53:   */  public static void main(String[] args)
/* 54:   */  {
/* 55:   */    try
/* 56:   */    {
/* 57:57 */      CanvasGameContainer container = new CanvasGameContainer(new CanvasSizeTest());
/* 58:   */      
/* 59:59 */      container.setSize(640, 480);
/* 60:60 */      Frame frame = new Frame("Test");
/* 61:61 */      frame.setLayout(new GridLayout(1, 2));
/* 62:62 */      frame.add(container);
/* 63:63 */      frame.pack();
/* 64:64 */      frame.addWindowListener(new WindowAdapter() {
/* 65:   */        public void windowClosing(WindowEvent e) {
/* 66:66 */          System.exit(0);
/* 67:   */        }
/* 68:68 */      });
/* 69:69 */      frame.setVisible(true);
/* 70:   */      
/* 71:71 */      container.start();
/* 72:   */    } catch (Exception e) {
/* 73:73 */      Log.error(e);
/* 74:   */    }
/* 75:   */  }
/* 76:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.tests.CanvasSizeTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */