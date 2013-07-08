/*   1:    */package org.newdawn.slick.util.pathfinding;
/*   2:    */
/*   3:    */import java.io.Serializable;
/*   4:    */import java.util.ArrayList;
/*   5:    */
/*  14:    */public class Path
/*  15:    */  implements Serializable
/*  16:    */{
/*  17:    */  private static final long serialVersionUID = 1L;
/*  18: 18 */  private ArrayList steps = new ArrayList();
/*  19:    */  
/*  31:    */  public int getLength()
/*  32:    */  {
/*  33: 33 */    return this.steps.size();
/*  34:    */  }
/*  35:    */  
/*  42:    */  public Step getStep(int index)
/*  43:    */  {
/*  44: 44 */    return (Step)this.steps.get(index);
/*  45:    */  }
/*  46:    */  
/*  52:    */  public int getX(int index)
/*  53:    */  {
/*  54: 54 */    return getStep(index).x;
/*  55:    */  }
/*  56:    */  
/*  62:    */  public int getY(int index)
/*  63:    */  {
/*  64: 64 */    return getStep(index).y;
/*  65:    */  }
/*  66:    */  
/*  72:    */  public void appendStep(int x, int y)
/*  73:    */  {
/*  74: 74 */    this.steps.add(new Step(x, y));
/*  75:    */  }
/*  76:    */  
/*  82:    */  public void prependStep(int x, int y)
/*  83:    */  {
/*  84: 84 */    this.steps.add(0, new Step(x, y));
/*  85:    */  }
/*  86:    */  
/*  93:    */  public boolean contains(int x, int y)
/*  94:    */  {
/*  95: 95 */    return this.steps.contains(new Step(x, y));
/*  96:    */  }
/*  97:    */  
/* 102:    */  public class Step
/* 103:    */    implements Serializable
/* 104:    */  {
/* 105:    */    private int x;
/* 106:    */    
/* 109:    */    private int y;
/* 110:    */    
/* 114:    */    public Step(int x, int y)
/* 115:    */    {
/* 116:116 */      this.x = x;
/* 117:117 */      this.y = y;
/* 118:    */    }
/* 119:    */    
/* 124:    */    public int getX()
/* 125:    */    {
/* 126:126 */      return this.x;
/* 127:    */    }
/* 128:    */    
/* 133:    */    public int getY()
/* 134:    */    {
/* 135:135 */      return this.y;
/* 136:    */    }
/* 137:    */    
/* 140:    */    public int hashCode()
/* 141:    */    {
/* 142:142 */      return this.x * this.y;
/* 143:    */    }
/* 144:    */    
/* 147:    */    public boolean equals(Object other)
/* 148:    */    {
/* 149:149 */      if ((other instanceof Step)) {
/* 150:150 */        Step o = (Step)other;
/* 151:    */        
/* 152:152 */        return (o.x == this.x) && (o.y == this.y);
/* 153:    */      }
/* 154:    */      
/* 155:155 */      return false;
/* 156:    */    }
/* 157:    */  }
/* 158:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.pathfinding.Path
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */