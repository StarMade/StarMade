package org.newdawn.slick.tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Bootstrap;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;
import org.newdawn.slick.util.pathfinding.navmesh.Link;
import org.newdawn.slick.util.pathfinding.navmesh.NavMesh;
import org.newdawn.slick.util.pathfinding.navmesh.NavMeshBuilder;
import org.newdawn.slick.util.pathfinding.navmesh.NavPath;
import org.newdawn.slick.util.pathfinding.navmesh.Space;

public class NavMeshTest
  extends BasicGame
  implements PathFindingContext
{
  private NavMesh navMesh;
  private NavMeshBuilder builder;
  private boolean showSpaces = true;
  private boolean showLinks = true;
  private NavPath path;
  private float field_353;
  private float field_354;
  private float field_355;
  private float field_356;
  private DataMap dataMap;
  
  public NavMeshTest()
  {
    super("Nav-mesh Test");
  }
  
  public void init(GameContainer container)
    throws SlickException
  {
    container.setShowFPS(false);
    try
    {
      this.dataMap = new DataMap("testdata/map.dat");
    }
    catch (IOException local_e)
    {
      throw new SlickException("Failed to load map data", local_e);
    }
    this.builder = new NavMeshBuilder();
    this.navMesh = this.builder.build(this.dataMap);
    System.out.println("Navmesh shapes: " + this.navMesh.getSpaceCount());
  }
  
  public void update(GameContainer container, int delta)
    throws SlickException
  {
    if (container.getInput().isKeyPressed(2)) {
      this.showLinks = (!this.showLinks);
    }
    if (container.getInput().isKeyPressed(3)) {
      this.showSpaces = (!this.showSpaces);
    }
  }
  
  public void render(GameContainer container, Graphics local_g)
    throws SlickException
  {
    local_g.translate(50.0F, 50.0F);
    for (int local_x = 0; local_x < 50; local_x++) {
      for (int local_y = 0; local_y < 50; local_y++) {
        if (this.dataMap.blocked(this, local_x, local_y))
        {
          local_g.setColor(Color.gray);
          local_g.fillRect(local_x * 10 + 1, local_y * 10 + 1, 8.0F, 8.0F);
        }
      }
    }
    if (this.showSpaces) {
      for (int local_x = 0; local_x < this.navMesh.getSpaceCount(); local_x++)
      {
        Space local_y = this.navMesh.getSpace(local_x);
        if (this.builder.clear(this.dataMap, local_y))
        {
          local_g.setColor(new Color(1.0F, 1.0F, 0.0F, 0.5F));
          local_g.fillRect(local_y.getX() * 10.0F, local_y.getY() * 10.0F, local_y.getWidth() * 10.0F, local_y.getHeight() * 10.0F);
        }
        local_g.setColor(Color.yellow);
        local_g.drawRect(local_y.getX() * 10.0F, local_y.getY() * 10.0F, local_y.getWidth() * 10.0F, local_y.getHeight() * 10.0F);
        if (this.showLinks)
        {
          int links = local_y.getLinkCount();
          for (int local_j = 0; local_j < links; local_j++)
          {
            Link link = local_y.getLink(local_j);
            local_g.setColor(Color.red);
            local_g.fillRect(link.getX() * 10.0F - 2.0F, link.getY() * 10.0F - 2.0F, 5.0F, 5.0F);
          }
        }
      }
    }
    if (this.path != null)
    {
      local_g.setColor(Color.white);
      for (int local_x = 0; local_x < this.path.length() - 1; local_x++) {
        local_g.drawLine(this.path.getX(local_x) * 10.0F, this.path.getY(local_x) * 10.0F, this.path.getX(local_x + 1) * 10.0F, this.path.getY(local_x + 1) * 10.0F);
      }
    }
  }
  
  public Mover getMover()
  {
    return null;
  }
  
  public int getSearchDistance()
  {
    return 0;
  }
  
  public int getSourceX()
  {
    return 0;
  }
  
  public int getSourceY()
  {
    return 0;
  }
  
  public void mousePressed(int button, int local_x, int local_y)
  {
    float local_mx = (local_x - 50) / 10.0F;
    float local_my = (local_y - 50) / 10.0F;
    if (button == 0)
    {
      this.field_353 = local_mx;
      this.field_354 = local_my;
    }
    else
    {
      this.field_355 = local_mx;
      this.field_356 = local_my;
    }
    this.path = this.navMesh.findPath(this.field_353, this.field_354, this.field_355, this.field_356, true);
  }
  
  public static void main(String[] argv)
  {
    Bootstrap.runAsApplication(new NavMeshTest(), 600, 600, false);
  }
  
  private class DataMap
    implements TileBasedMap
  {
    private byte[] map = new byte[2500];
    
    public DataMap(String ref)
      throws IOException
    {
      ResourceLoader.getResourceAsStream(ref).read(this.map);
    }
    
    public boolean blocked(PathFindingContext context, int local_tx, int local_ty)
    {
      if ((local_tx < 0) || (local_ty < 0) || (local_tx >= 50) || (local_ty >= 50)) {
        return false;
      }
      return this.map[(local_tx + local_ty * 50)] != 0;
    }
    
    public float getCost(PathFindingContext context, int local_tx, int local_ty)
    {
      return 1.0F;
    }
    
    public int getHeightInTiles()
    {
      return 50;
    }
    
    public int getWidthInTiles()
    {
      return 50;
    }
    
    public void pathFinderVisited(int local_x, int local_y) {}
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tests.NavMeshTest
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */