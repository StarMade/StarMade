package org.newdawn.slick.tests;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.GeomUtil;
import org.newdawn.slick.geom.GeomUtilListener;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class GeomUtilTileTest
  extends BasicGame
  implements GeomUtilListener
{
  private Shape source;
  private Shape cut;
  private Shape[] result;
  private GeomUtil util = new GeomUtil();
  private ArrayList original = new ArrayList();
  private ArrayList combined = new ArrayList();
  private ArrayList intersections = new ArrayList();
  private ArrayList used = new ArrayList();
  private ArrayList[][] quadSpace;
  private Shape[][] quadSpaceShapes;
  
  public GeomUtilTileTest()
  {
    super("GeomUtilTileTest");
  }
  
  private void generateSpace(ArrayList shapes, float minx, float miny, float maxx, float maxy, int segments)
  {
    this.quadSpace = new ArrayList[segments][segments];
    this.quadSpaceShapes = new Shape[segments][segments];
    float local_dx = (maxx - minx) / segments;
    float local_dy = (maxy - miny) / segments;
    for (int local_x = 0; local_x < segments; local_x++) {
      for (int local_y = 0; local_y < segments; local_y++)
      {
        this.quadSpace[local_x][local_y] = new ArrayList();
        Polygon segmentPolygon = new Polygon();
        segmentPolygon.addPoint(minx + local_dx * local_x, miny + local_dy * local_y);
        segmentPolygon.addPoint(minx + local_dx * local_x + local_dx, miny + local_dy * local_y);
        segmentPolygon.addPoint(minx + local_dx * local_x + local_dx, miny + local_dy * local_y + local_dy);
        segmentPolygon.addPoint(minx + local_dx * local_x, miny + local_dy * local_y + local_dy);
        for (int local_i = 0; local_i < shapes.size(); local_i++)
        {
          Shape shape = (Shape)shapes.get(local_i);
          if (collides(shape, segmentPolygon)) {
            this.quadSpace[local_x][local_y].add(shape);
          }
        }
        this.quadSpaceShapes[local_x][local_y] = segmentPolygon;
      }
    }
  }
  
  private void removeFromQuadSpace(Shape shape)
  {
    int segments = this.quadSpace.length;
    for (int local_x = 0; local_x < segments; local_x++) {
      for (int local_y = 0; local_y < segments; local_y++) {
        this.quadSpace[local_x][local_y].remove(shape);
      }
    }
  }
  
  private void addToQuadSpace(Shape shape)
  {
    int segments = this.quadSpace.length;
    for (int local_x = 0; local_x < segments; local_x++) {
      for (int local_y = 0; local_y < segments; local_y++) {
        if (collides(shape, this.quadSpaceShapes[local_x][local_y])) {
          this.quadSpace[local_x][local_y].add(shape);
        }
      }
    }
  }
  
  public void init()
  {
    int size = 10;
    int[][] map = { { 0, 0, 0, 0, 0, 0, 0, 3, 0, 0 }, { 0, 1, 1, 1, 0, 0, 1, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 5, 1, 6, 0 }, { 0, 1, 2, 0, 0, 0, 4, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 3, 0, 1, 1, 0, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0, 1, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
    for (int local_x = 0; local_x < map[0].length; local_x++) {
      for (int local_y = 0; local_y < map.length; local_y++) {
        if (map[local_y][local_x] != 0) {
          switch (map[local_y][local_x])
          {
          case 1: 
            Polygon local_p2 = new Polygon();
            local_p2.addPoint(local_x * 32, local_y * 32);
            local_p2.addPoint(local_x * 32 + 32, local_y * 32);
            local_p2.addPoint(local_x * 32 + 32, local_y * 32 + 32);
            local_p2.addPoint(local_x * 32, local_y * 32 + 32);
            this.original.add(local_p2);
            break;
          case 2: 
            Polygon poly = new Polygon();
            poly.addPoint(local_x * 32, local_y * 32);
            poly.addPoint(local_x * 32 + 32, local_y * 32);
            poly.addPoint(local_x * 32, local_y * 32 + 32);
            this.original.add(poly);
            break;
          case 3: 
            Circle ellipse = new Circle(local_x * 32 + 16, local_y * 32 + 32, 16.0F, 16);
            this.original.add(ellipse);
            break;
          case 4: 
            Polygon local_p = new Polygon();
            local_p.addPoint(local_x * 32 + 32, local_y * 32);
            local_p.addPoint(local_x * 32 + 32, local_y * 32 + 32);
            local_p.addPoint(local_x * 32, local_y * 32 + 32);
            this.original.add(local_p);
            break;
          case 5: 
            Polygon local_p3 = new Polygon();
            local_p3.addPoint(local_x * 32, local_y * 32);
            local_p3.addPoint(local_x * 32 + 32, local_y * 32);
            local_p3.addPoint(local_x * 32 + 32, local_y * 32 + 32);
            this.original.add(local_p3);
            break;
          case 6: 
            Polygon local_p4 = new Polygon();
            local_p4.addPoint(local_x * 32, local_y * 32);
            local_p4.addPoint(local_x * 32 + 32, local_y * 32);
            local_p4.addPoint(local_x * 32, local_y * 32 + 32);
            this.original.add(local_p4);
          }
        }
      }
    }
    long local_x = System.currentTimeMillis();
    generateSpace(this.original, 0.0F, 0.0F, (size + 1) * 32, (size + 1) * 32, 8);
    this.combined = combineQuadSpace();
    long local_p2 = System.currentTimeMillis();
    System.out.println("Combine took: " + (local_p2 - local_x));
    System.out.println("Combine result: " + this.combined.size());
  }
  
  private ArrayList combineQuadSpace()
  {
    boolean updated = true;
    while (updated)
    {
      updated = false;
      for (int local_x = 0; local_x < this.quadSpace.length; local_x++) {
        for (int local_y = 0; local_y < this.quadSpace.length; local_y++)
        {
          ArrayList shapes = this.quadSpace[local_x][local_y];
          int before = shapes.size();
          combine(shapes);
          int after = shapes.size();
          updated |= before != after;
        }
      }
    }
    HashSet local_x = new HashSet();
    for (int local_y = 0; local_y < this.quadSpace.length; local_y++) {
      for (int shapes = 0; shapes < this.quadSpace.length; shapes++) {
        local_x.addAll(this.quadSpace[local_y][shapes]);
      }
    }
    return new ArrayList(local_x);
  }
  
  private ArrayList combine(ArrayList shapes)
  {
    ArrayList last = shapes;
    ArrayList current = shapes;
    boolean first = true;
    while ((current.size() != last.size()) || (first))
    {
      first = false;
      last = current;
      current = combineImpl(current);
    }
    ArrayList pruned = new ArrayList();
    for (int local_i = 0; local_i < current.size(); local_i++) {
      pruned.add(((Shape)current.get(local_i)).prune());
    }
    return pruned;
  }
  
  private ArrayList combineImpl(ArrayList shapes)
  {
    ArrayList result = new ArrayList(shapes);
    if (this.quadSpace != null) {
      result = shapes;
    }
    for (int local_i = 0; local_i < shapes.size(); local_i++)
    {
      Shape first = (Shape)shapes.get(local_i);
      for (int local_j = local_i + 1; local_j < shapes.size(); local_j++)
      {
        Shape second = (Shape)shapes.get(local_j);
        if (first.intersects(second))
        {
          Shape[] joined = this.util.union(first, second);
          if (joined.length == 1)
          {
            if (this.quadSpace != null)
            {
              removeFromQuadSpace(first);
              removeFromQuadSpace(second);
              addToQuadSpace(joined[0]);
            }
            else
            {
              result.remove(first);
              result.remove(second);
              result.add(joined[0]);
            }
            return result;
          }
        }
      }
    }
    return result;
  }
  
  public boolean collides(Shape shape1, Shape shape2)
  {
    if (shape1.intersects(shape2)) {
      return true;
    }
    for (int local_i = 0; local_i < shape1.getPointCount(); local_i++)
    {
      float[] local_pt = shape1.getPoint(local_i);
      if (shape2.contains(local_pt[0], local_pt[1])) {
        return true;
      }
    }
    for (int local_i = 0; local_i < shape2.getPointCount(); local_i++)
    {
      float[] local_pt = shape2.getPoint(local_i);
      if (shape1.contains(local_pt[0], local_pt[1])) {
        return true;
      }
    }
    return false;
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    this.util.setListener(this);
    init();
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {}
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.setColor(Color.green);
    for (int local_i = 0; local_i < this.original.size(); local_i++)
    {
      Shape shape = (Shape)this.original.get(local_i);
      local_g.draw(shape);
    }
    local_g.setColor(Color.white);
    if (this.quadSpaceShapes != null) {
      local_g.draw(this.quadSpaceShapes[0][0]);
    }
    local_g.translate(0.0F, 320.0F);
    for (int local_i = 0; local_i < this.combined.size(); local_i++)
    {
      local_g.setColor(Color.white);
      Shape shape = (Shape)this.combined.get(local_i);
      local_g.draw(shape);
      for (int local_j = 0; local_j < shape.getPointCount(); local_j++)
      {
        local_g.setColor(Color.yellow);
        float[] local_pt = shape.getPoint(local_j);
        local_g.fillOval(local_pt[0] - 1.0F, local_pt[1] - 1.0F, 3.0F, 3.0F);
      }
    }
  }
  
  public static void main(String[] argv)
  {
    try
    {
      AppGameContainer container = new AppGameContainer(new GeomUtilTileTest());
      container.setDisplayMode(800, 600, false);
      container.start();
    }
    catch (SlickException container)
    {
      container.printStackTrace();
    }
  }
  
  public void pointExcluded(float local_x, float local_y) {}
  
  public void pointIntersected(float local_x, float local_y)
  {
    this.intersections.add(new Vector2f(local_x, local_y));
  }
  
  public void pointUsed(float local_x, float local_y)
  {
    this.used.add(new Vector2f(local_x, local_y));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.GeomUtilTileTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */