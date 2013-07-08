/*   1:    */package org.newdawn.slick.svg;
/*   2:    */
/*   3:    */import java.util.ArrayList;
/*   4:    */import org.newdawn.slick.geom.MorphShape;
/*   5:    */
/*  11:    */public class SVGMorph
/*  12:    */  extends Diagram
/*  13:    */{
/*  14: 14 */  private ArrayList figures = new ArrayList();
/*  15:    */  
/*  20:    */  public SVGMorph(Diagram diagram)
/*  21:    */  {
/*  22: 22 */    super(diagram.getWidth(), diagram.getHeight());
/*  23:    */    
/*  24: 24 */    for (int i = 0; i < diagram.getFigureCount(); i++) {
/*  25: 25 */      Figure figure = diagram.getFigure(i);
/*  26: 26 */      Figure copy = new Figure(figure.getType(), new MorphShape(figure.getShape()), figure.getData(), figure.getTransform());
/*  27:    */      
/*  28: 28 */      this.figures.add(copy);
/*  29:    */    }
/*  30:    */  }
/*  31:    */  
/*  36:    */  public void addStep(Diagram diagram)
/*  37:    */  {
/*  38: 38 */    if (diagram.getFigureCount() != this.figures.size()) {
/*  39: 39 */      throw new RuntimeException("Mismatched diagrams, missing ids");
/*  40:    */    }
/*  41: 41 */    for (int i = 0; i < diagram.getFigureCount(); i++) {
/*  42: 42 */      Figure figure = diagram.getFigure(i);
/*  43: 43 */      String id = figure.getData().getMetaData();
/*  44:    */      
/*  45: 45 */      for (int j = 0; j < this.figures.size(); j++) {
/*  46: 46 */        Figure existing = (Figure)this.figures.get(j);
/*  47: 47 */        if (existing.getData().getMetaData().equals(id)) {
/*  48: 48 */          MorphShape morph = (MorphShape)existing.getShape();
/*  49: 49 */          morph.addShape(figure.getShape());
/*  50: 50 */          break;
/*  51:    */        }
/*  52:    */      }
/*  53:    */    }
/*  54:    */  }
/*  55:    */  
/*  62:    */  public void setExternalDiagram(Diagram diagram)
/*  63:    */  {
/*  64: 64 */    for (int i = 0; i < this.figures.size(); i++) {
/*  65: 65 */      Figure figure = (Figure)this.figures.get(i);
/*  66:    */      
/*  67: 67 */      for (int j = 0; j < diagram.getFigureCount(); j++) {
/*  68: 68 */        Figure newBase = diagram.getFigure(j);
/*  69: 69 */        if (newBase.getData().getMetaData().equals(figure.getData().getMetaData())) {
/*  70: 70 */          MorphShape shape = (MorphShape)figure.getShape();
/*  71: 71 */          shape.setExternalFrame(newBase.getShape());
/*  72: 72 */          break;
/*  73:    */        }
/*  74:    */      }
/*  75:    */    }
/*  76:    */  }
/*  77:    */  
/*  82:    */  public void updateMorphTime(float delta)
/*  83:    */  {
/*  84: 84 */    for (int i = 0; i < this.figures.size(); i++) {
/*  85: 85 */      Figure figure = (Figure)this.figures.get(i);
/*  86: 86 */      MorphShape shape = (MorphShape)figure.getShape();
/*  87: 87 */      shape.updateMorphTime(delta);
/*  88:    */    }
/*  89:    */  }
/*  90:    */  
/*  96:    */  public void setMorphTime(float time)
/*  97:    */  {
/*  98: 98 */    for (int i = 0; i < this.figures.size(); i++) {
/*  99: 99 */      Figure figure = (Figure)this.figures.get(i);
/* 100:100 */      MorphShape shape = (MorphShape)figure.getShape();
/* 101:101 */      shape.setMorphTime(time);
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 107:    */  public int getFigureCount()
/* 108:    */  {
/* 109:109 */    return this.figures.size();
/* 110:    */  }
/* 111:    */  
/* 114:    */  public Figure getFigure(int index)
/* 115:    */  {
/* 116:116 */    return (Figure)this.figures.get(index);
/* 117:    */  }
/* 118:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.SVGMorph
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */