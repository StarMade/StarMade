/*   1:    */package org.newdawn.slick.svg;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import java.util.HashMap;
/*   5:    */import java.util.Set;
/*   6:    */import org.newdawn.slick.geom.Shape;
/*   7:    */
/*  11:    */public class Diagram
/*  12:    */{
/*  13: 13 */  private ArrayList figures = new ArrayList();
/*  14:    */  
/*  15: 15 */  private HashMap patterns = new HashMap();
/*  16:    */  
/*  17: 17 */  private HashMap gradients = new HashMap();
/*  18:    */  
/*  19: 19 */  private HashMap figureMap = new HashMap();
/*  20:    */  
/*  23:    */  private float width;
/*  24:    */  
/*  27:    */  private float height;
/*  28:    */  
/*  31:    */  public Diagram(float width, float height)
/*  32:    */  {
/*  33: 33 */    this.width = width;
/*  34: 34 */    this.height = height;
/*  35:    */  }
/*  36:    */  
/*  41:    */  public float getWidth()
/*  42:    */  {
/*  43: 43 */    return this.width;
/*  44:    */  }
/*  45:    */  
/*  50:    */  public float getHeight()
/*  51:    */  {
/*  52: 52 */    return this.height;
/*  53:    */  }
/*  54:    */  
/*  60:    */  public void addPatternDef(String name, String href)
/*  61:    */  {
/*  62: 62 */    this.patterns.put(name, href);
/*  63:    */  }
/*  64:    */  
/*  70:    */  public void addGradient(String name, Gradient gradient)
/*  71:    */  {
/*  72: 72 */    this.gradients.put(name, gradient);
/*  73:    */  }
/*  74:    */  
/*  80:    */  public String getPatternDef(String name)
/*  81:    */  {
/*  82: 82 */    return (String)this.patterns.get(name);
/*  83:    */  }
/*  84:    */  
/*  90:    */  public Gradient getGradient(String name)
/*  91:    */  {
/*  92: 92 */    return (Gradient)this.gradients.get(name);
/*  93:    */  }
/*  94:    */  
/*  99:    */  public String[] getPatternDefNames()
/* 100:    */  {
/* 101:101 */    return (String[])this.patterns.keySet().toArray(new String[0]);
/* 102:    */  }
/* 103:    */  
/* 109:    */  public Figure getFigureByID(String id)
/* 110:    */  {
/* 111:111 */    return (Figure)this.figureMap.get(id);
/* 112:    */  }
/* 113:    */  
/* 118:    */  public void addFigure(Figure figure)
/* 119:    */  {
/* 120:120 */    this.figures.add(figure);
/* 121:121 */    this.figureMap.put(figure.getData().getAttribute("id"), figure);
/* 122:    */    
/* 123:123 */    String fillRef = figure.getData().getAsReference("fill");
/* 124:124 */    Gradient gradient = getGradient(fillRef);
/* 125:125 */    if ((gradient != null) && 
/* 126:126 */      (gradient.isRadial())) {
/* 127:127 */      for (int i = 0; i < InkscapeLoader.RADIAL_TRIANGULATION_LEVEL; i++) {
/* 128:128 */        figure.getShape().increaseTriangulation();
/* 129:    */      }
/* 130:    */    }
/* 131:    */  }
/* 132:    */  
/* 138:    */  public int getFigureCount()
/* 139:    */  {
/* 140:140 */    return this.figures.size();
/* 141:    */  }
/* 142:    */  
/* 148:    */  public Figure getFigure(int index)
/* 149:    */  {
/* 150:150 */    return (Figure)this.figures.get(index);
/* 151:    */  }
/* 152:    */  
/* 157:    */  public void removeFigure(Figure figure)
/* 158:    */  {
/* 159:159 */    this.figures.remove(figure);
/* 160:160 */    this.figureMap.remove(figure.getData().getAttribute("id"));
/* 161:    */  }
/* 162:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.Diagram
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */