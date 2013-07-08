package org.newdawn.slick.util.pathfinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.newdawn.slick.util.pathfinding.heuristics.ClosestHeuristic;

public class AStarPathFinder
  implements PathFinder, PathFindingContext
{
  private ArrayList closed = new ArrayList();
  private PriorityList open = new PriorityList(null);
  private TileBasedMap map;
  private int maxSearchDistance;
  private Node[][] nodes;
  private boolean allowDiagMovement;
  private AStarHeuristic heuristic;
  private Node current;
  private Mover mover;
  private int sourceX;
  private int sourceY;
  private int distance;
  
  public AStarPathFinder(TileBasedMap map, int maxSearchDistance, boolean allowDiagMovement)
  {
    this(map, maxSearchDistance, allowDiagMovement, new ClosestHeuristic());
  }
  
  public AStarPathFinder(TileBasedMap map, int maxSearchDistance, boolean allowDiagMovement, AStarHeuristic heuristic)
  {
    this.heuristic = heuristic;
    this.map = map;
    this.maxSearchDistance = maxSearchDistance;
    this.allowDiagMovement = allowDiagMovement;
    this.nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
    for (int local_x = 0; local_x < map.getWidthInTiles(); local_x++) {
      for (int local_y = 0; local_y < map.getHeightInTiles(); local_y++) {
        this.nodes[local_x][local_y] = new Node(local_x, local_y);
      }
    }
  }
  
  public Path findPath(Mover mover, int local_sx, int local_sy, int local_tx, int local_ty)
  {
    this.current = null;
    this.mover = mover;
    this.sourceX = local_tx;
    this.sourceY = local_ty;
    this.distance = 0;
    if (this.map.blocked(this, local_tx, local_ty)) {
      return null;
    }
    for (int local_x = 0; local_x < this.map.getWidthInTiles(); local_x++) {
      for (int local_y = 0; local_y < this.map.getHeightInTiles(); local_y++) {
        this.nodes[local_x][local_y].reset();
      }
    }
    this.nodes[local_sx][local_sy].cost = 0.0F;
    this.nodes[local_sx][local_sy].depth = 0;
    this.closed.clear();
    this.open.clear();
    addToOpen(this.nodes[local_sx][local_sy]);
    this.nodes[local_tx][local_ty].parent = null;
    int local_x = 0;
    while ((local_x < this.maxSearchDistance) && (this.open.size() != 0))
    {
      int local_y = local_sx;
      int local_ly = local_sy;
      if (this.current != null)
      {
        local_y = this.current.field_1741;
        local_ly = this.current.field_1742;
      }
      this.current = getFirstInOpen();
      this.distance = this.current.depth;
      if ((this.current == this.nodes[local_tx][local_ty]) && (isValidLocation(mover, local_y, local_ly, local_tx, local_ty))) {
        break;
      }
      removeFromOpen(this.current);
      addToClosed(this.current);
      for (int local_x1 = -1; local_x1 < 2; local_x1++) {
        for (int local_y1 = -1; local_y1 < 2; local_y1++) {
          if (((local_x1 != 0) || (local_y1 != 0)) && ((this.allowDiagMovement) || (local_x1 == 0) || (local_y1 == 0)))
          {
            int local_xp = local_x1 + this.current.field_1741;
            int local_yp = local_y1 + this.current.field_1742;
            if (isValidLocation(mover, this.current.field_1741, this.current.field_1742, local_xp, local_yp))
            {
              float nextStepCost = this.current.cost + getMovementCost(mover, this.current.field_1741, this.current.field_1742, local_xp, local_yp);
              Node neighbour = this.nodes[local_xp][local_yp];
              this.map.pathFinderVisited(local_xp, local_yp);
              if (nextStepCost < neighbour.cost)
              {
                if (inOpenList(neighbour)) {
                  removeFromOpen(neighbour);
                }
                if (inClosedList(neighbour)) {
                  removeFromClosed(neighbour);
                }
              }
              if ((!inOpenList(neighbour)) && (!inClosedList(neighbour)))
              {
                neighbour.cost = nextStepCost;
                neighbour.heuristic = getHeuristicCost(mover, local_xp, local_yp, local_tx, local_ty);
                local_x = Math.max(local_x, neighbour.setParent(this.current));
                addToOpen(neighbour);
              }
            }
          }
        }
      }
    }
    if (this.nodes[local_tx][local_ty].parent == null) {
      return null;
    }
    Path local_y = new Path();
    for (Node local_ly = this.nodes[local_tx][local_ty]; local_ly != this.nodes[local_sx][local_sy]; local_ly = local_ly.parent) {
      local_y.prependStep(local_ly.field_1741, local_ly.field_1742);
    }
    local_y.prependStep(local_sx, local_sy);
    return local_y;
  }
  
  public int getCurrentX()
  {
    if (this.current == null) {
      return -1;
    }
    return this.current.field_1741;
  }
  
  public int getCurrentY()
  {
    if (this.current == null) {
      return -1;
    }
    return this.current.field_1742;
  }
  
  protected Node getFirstInOpen()
  {
    return (Node)this.open.first();
  }
  
  protected void addToOpen(Node node)
  {
    node.setOpen(true);
    this.open.add(node);
  }
  
  protected boolean inOpenList(Node node)
  {
    return node.isOpen();
  }
  
  protected void removeFromOpen(Node node)
  {
    node.setOpen(false);
    this.open.remove(node);
  }
  
  protected void addToClosed(Node node)
  {
    node.setClosed(true);
    this.closed.add(node);
  }
  
  protected boolean inClosedList(Node node)
  {
    return node.isClosed();
  }
  
  protected void removeFromClosed(Node node)
  {
    node.setClosed(false);
    this.closed.remove(node);
  }
  
  protected boolean isValidLocation(Mover mover, int local_sx, int local_sy, int local_x, int local_y)
  {
    boolean invalid = (local_x < 0) || (local_y < 0) || (local_x >= this.map.getWidthInTiles()) || (local_y >= this.map.getHeightInTiles());
    if ((!invalid) && ((local_sx != local_x) || (local_sy != local_y)))
    {
      this.mover = mover;
      this.sourceX = local_sx;
      this.sourceY = local_sy;
      invalid = this.map.blocked(this, local_x, local_y);
    }
    return !invalid;
  }
  
  public float getMovementCost(Mover mover, int local_sx, int local_sy, int local_tx, int local_ty)
  {
    this.mover = mover;
    this.sourceX = local_sx;
    this.sourceY = local_sy;
    return this.map.getCost(this, local_tx, local_ty);
  }
  
  public float getHeuristicCost(Mover mover, int local_x, int local_y, int local_tx, int local_ty)
  {
    return this.heuristic.getCost(this.map, mover, local_x, local_y, local_tx, local_ty);
  }
  
  public Mover getMover()
  {
    return this.mover;
  }
  
  public int getSearchDistance()
  {
    return this.distance;
  }
  
  public int getSourceX()
  {
    return this.sourceX;
  }
  
  public int getSourceY()
  {
    return this.sourceY;
  }
  
  private class Node
    implements Comparable
  {
    private int field_1741;
    private int field_1742;
    private float cost;
    private Node parent;
    private float heuristic;
    private int depth;
    private boolean open;
    private boolean closed;
    
    public Node(int local_x, int local_y)
    {
      this.field_1741 = local_x;
      this.field_1742 = local_y;
    }
    
    public int setParent(Node parent)
    {
      parent.depth += 1;
      this.parent = parent;
      return this.depth;
    }
    
    public int compareTo(Object other)
    {
      Node local_o = (Node)other;
      float local_f = this.heuristic + this.cost;
      float local_of = local_o.heuristic + local_o.cost;
      if (local_f < local_of) {
        return -1;
      }
      if (local_f > local_of) {
        return 1;
      }
      return 0;
    }
    
    public void setOpen(boolean open)
    {
      this.open = open;
    }
    
    public boolean isOpen()
    {
      return this.open;
    }
    
    public void setClosed(boolean closed)
    {
      this.closed = closed;
    }
    
    public boolean isClosed()
    {
      return this.closed;
    }
    
    public void reset()
    {
      this.closed = false;
      this.open = false;
      this.cost = 0.0F;
      this.depth = 0;
    }
    
    public String toString()
    {
      return "[Node " + this.field_1741 + "," + this.field_1742 + "]";
    }
  }
  
  private class PriorityList
  {
    private List list = new LinkedList();
    
    private PriorityList() {}
    
    public Object first()
    {
      return this.list.get(0);
    }
    
    public void clear()
    {
      this.list.clear();
    }
    
    public void add(Object local_o)
    {
      for (int local_i = 0; local_i < this.list.size(); local_i++) {
        if (((Comparable)this.list.get(local_i)).compareTo(local_o) > 0)
        {
          this.list.add(local_i, local_o);
          break;
        }
      }
      if (!this.list.contains(local_o)) {
        this.list.add(local_o);
      }
    }
    
    public void remove(Object local_o)
    {
      this.list.remove(local_o);
    }
    
    public int size()
    {
      return this.list.size();
    }
    
    public boolean contains(Object local_o)
    {
      return this.list.contains(local_o);
    }
    
    public String toString()
    {
      String temp = "{";
      for (int local_i = 0; local_i < size(); local_i++) {
        temp = temp + this.list.get(local_i).toString() + ",";
      }
      temp = temp + "}";
      return temp;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.pathfinding.AStarPathFinder
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */