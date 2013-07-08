package org.newdawn.slick.tests;

import java.util.ArrayList;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.GeomUtil;
import org.newdawn.slick.geom.GeomUtilListener;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class GeomUtilTest
  extends BasicGame
  implements GeomUtilListener
{
  private Shape source;
  private Shape cut;
  private Shape[] result;
  private ArrayList points = new ArrayList();
  private ArrayList marks = new ArrayList();
  private ArrayList exclude = new ArrayList();
  private boolean dynamic;
  private GeomUtil util = new GeomUtil();
  private int field_361;
  private int field_362;
  private Circle circle;
  private Shape rect;
  private Polygon star;
  private boolean union;
  
  public GeomUtilTest()
  {
    super("GeomUtilTest");
  }
  
  public void init()
  {
    Polygon source = new Polygon();
    source.addPoint(100.0F, 100.0F);
    source.addPoint(150.0F, 80.0F);
    source.addPoint(210.0F, 120.0F);
    source.addPoint(340.0F, 150.0F);
    source.addPoint(150.0F, 200.0F);
    source.addPoint(120.0F, 250.0F);
    this.source = source;
    this.circle = new Circle(0.0F, 0.0F, 50.0F);
    this.rect = new Rectangle(-100.0F, -40.0F, 200.0F, 80.0F);
    this.star = new Polygon();
    float dis = 40.0F;
    for (int local_i = 0; local_i < 360; local_i += 30)
    {
      dis = dis == 40.0F ? 60.0F : 40.0F;
      double local_x = Math.cos(Math.toRadians(local_i)) * dis;
      double local_y = Math.sin(Math.toRadians(local_i)) * dis;
      this.star.addPoint((float)local_x, (float)local_y);
    }
    this.cut = this.circle;
    this.cut.setLocation(203.0F, 78.0F);
    this.field_361 = ((int)this.cut.getCenterX());
    this.field_362 = ((int)this.cut.getCenterY());
    makeBoolean();
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.util.setListener(this);
    init();
    container.setVSync(true);
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    if (container.getInput().isKeyPressed(57)) {
      this.dynamic = (!this.dynamic);
    }
    if (container.getInput().isKeyPressed(28))
    {
      this.union = (!this.union);
      makeBoolean();
    }
    if (container.getInput().isKeyPressed(2))
    {
      this.cut = this.circle;
      this.circle.setCenterX(this.field_361);
      this.circle.setCenterY(this.field_362);
      makeBoolean();
    }
    if (container.getInput().isKeyPressed(3))
    {
      this.cut = this.rect;
      this.rect.setCenterX(this.field_361);
      this.rect.setCenterY(this.field_362);
      makeBoolean();
    }
    if (container.getInput().isKeyPressed(4))
    {
      this.cut = this.star;
      this.star.setCenterX(this.field_361);
      this.star.setCenterY(this.field_362);
      makeBoolean();
    }
    if (this.dynamic)
    {
      this.field_361 = container.getInput().getMouseX();
      this.field_362 = container.getInput().getMouseY();
      makeBoolean();
    }
  }
  
  private void makeBoolean()
  {
    this.marks.clear();
    this.points.clear();
    this.exclude.clear();
    this.cut.setCenterX(this.field_361);
    this.cut.setCenterY(this.field_362);
    if (this.union) {
      this.result = this.util.union(this.source, this.cut);
    } else {
      this.result = this.util.subtract(this.source, this.cut);
    }
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.drawString("Space - toggle movement of cutting shape", 530.0F, 10.0F);
    local_g.drawString("1,2,3 - select cutting shape", 530.0F, 30.0F);
    local_g.drawString("Mouse wheel - rotate shape", 530.0F, 50.0F);
    local_g.drawString("Enter - toggle union/subtract", 530.0F, 70.0F);
    local_g.drawString("MODE: " + (this.union ? "Union" : "Cut"), 530.0F, 200.0F);
    local_g.setColor(Color.green);
    local_g.draw(this.source);
    local_g.setColor(Color.red);
    local_g.draw(this.cut);
    local_g.setColor(Color.white);
    for (int local_i = 0; local_i < this.exclude.size(); local_i++)
    {
      Vector2f local_pt = (Vector2f)this.exclude.get(local_i);
      local_g.drawOval(local_pt.field_1680 - 3.0F, local_pt.field_1681 - 3.0F, 7.0F, 7.0F);
    }
    local_g.setColor(Color.yellow);
    for (int local_i = 0; local_i < this.points.size(); local_i++)
    {
      Vector2f local_pt = (Vector2f)this.points.get(local_i);
      local_g.fillOval(local_pt.field_1680 - 1.0F, local_pt.field_1681 - 1.0F, 3.0F, 3.0F);
    }
    local_g.setColor(Color.white);
    for (int local_i = 0; local_i < this.marks.size(); local_i++)
    {
      Vector2f local_pt = (Vector2f)this.marks.get(local_i);
      local_g.fillOval(local_pt.field_1680 - 1.0F, local_pt.field_1681 - 1.0F, 3.0F, 3.0F);
    }
    local_g.translate(0.0F, 300.0F);
    local_g.setColor(Color.white);
    if (this.result != null)
    {
      for (int local_i = 0; local_i < this.result.length; local_i++) {
        local_g.draw(this.result[local_i]);
      }
      local_g.drawString("Polys:" + this.result.length, 10.0F, 100.0F);
      local_g.drawString("X:" + this.field_361, 10.0F, 120.0F);
      local_g.drawString("Y:" + this.field_362, 10.0F, 130.0F);
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new GeomUtilTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
  
  public void pointExcluded(float local_x, float local_y)
  {
    this.exclude.add(new Vector2f(local_x, local_y));
  }
  
  public void pointIntersected(float local_x, float local_y)
  {
    this.marks.add(new Vector2f(local_x, local_y));
  }
  
  public void pointUsed(float local_x, float local_y)
  {
    this.points.add(new Vector2f(local_x, local_y));
  }
  
  public void mouseWheelMoved(int change)
  {
    if (this.dynamic) {
      if (change < 0) {
        this.cut = this.cut.transform(Transform.createRotateTransform((float)Math.toRadians(10.0D), this.cut.getCenterX(), this.cut.getCenterY()));
      } else {
        this.cut = this.cut.transform(Transform.createRotateTransform((float)Math.toRadians(-10.0D), this.cut.getCenterX(), this.cut.getCenterY()));
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.GeomUtilTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */