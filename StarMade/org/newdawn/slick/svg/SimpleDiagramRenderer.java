/*   1:    */package org.newdawn.slick.svg;
/*   2:    */
/*   3:    */import java.io.PrintStream;
/*   4:    */import org.newdawn.slick.Color;
/*   5:    */import org.newdawn.slick.Graphics;
/*   6:    */import org.newdawn.slick.geom.Shape;
/*   7:    */import org.newdawn.slick.geom.ShapeRenderer;
/*   8:    */import org.newdawn.slick.geom.TexCoordGenerator;
/*   9:    */import org.newdawn.slick.opengl.TextureImpl;
/*  10:    */import org.newdawn.slick.opengl.renderer.Renderer;
/*  11:    */import org.newdawn.slick.opengl.renderer.SGL;
/*  12:    */
/*  18:    */public class SimpleDiagramRenderer
/*  19:    */{
/*  20: 20 */  protected static SGL GL = ;
/*  21:    */  
/*  23:    */  public Diagram diagram;
/*  24:    */  
/*  25: 25 */  public int list = -1;
/*  26:    */  
/*  31:    */  public SimpleDiagramRenderer(Diagram diagram)
/*  32:    */  {
/*  33: 33 */    this.diagram = diagram;
/*  34:    */  }
/*  35:    */  
/*  41:    */  public void render(Graphics g)
/*  42:    */  {
/*  43: 43 */    if (this.list == -1) {
/*  44: 44 */      this.list = GL.glGenLists(1);
/*  45: 45 */      GL.glNewList(this.list, 4864);
/*  46: 46 */      render(g, this.diagram);
/*  47: 47 */      GL.glEndList();
/*  48:    */    }
/*  49:    */    
/*  50: 50 */    GL.glCallList(this.list);
/*  51:    */    
/*  52: 52 */    TextureImpl.bindNone();
/*  53:    */  }
/*  54:    */  
/*  60:    */  public static void render(Graphics g, Diagram diagram)
/*  61:    */  {
/*  62: 62 */    for (int i = 0; i < diagram.getFigureCount(); i++) {
/*  63: 63 */      Figure figure = diagram.getFigure(i);
/*  64:    */      
/*  65: 65 */      if (figure.getData().isFilled()) {
/*  66: 66 */        if (figure.getData().isColor("fill")) {
/*  67: 67 */          g.setColor(figure.getData().getAsColor("fill"));
/*  68: 68 */          g.fill(diagram.getFigure(i).getShape());
/*  69: 69 */          g.setAntiAlias(true);
/*  70: 70 */          g.draw(diagram.getFigure(i).getShape());
/*  71: 71 */          g.setAntiAlias(false);
/*  72:    */        }
/*  73:    */        
/*  74: 74 */        String fill = figure.getData().getAsReference("fill");
/*  75: 75 */        if (diagram.getPatternDef(fill) != null) {
/*  76: 76 */          System.out.println("PATTERN");
/*  77:    */        }
/*  78: 78 */        if (diagram.getGradient(fill) != null) {
/*  79: 79 */          Gradient gradient = diagram.getGradient(fill);
/*  80: 80 */          Shape shape = diagram.getFigure(i).getShape();
/*  81: 81 */          TexCoordGenerator fg = null;
/*  82: 82 */          if (gradient.isRadial()) {
/*  83: 83 */            fg = new RadialGradientFill(shape, diagram.getFigure(i).getTransform(), gradient);
/*  84:    */          } else {
/*  85: 85 */            fg = new LinearGradientFill(shape, diagram.getFigure(i).getTransform(), gradient);
/*  86:    */          }
/*  87:    */          
/*  88: 88 */          Color.white.bind();
/*  89: 89 */          ShapeRenderer.texture(shape, gradient.getImage(), fg);
/*  90:    */        }
/*  91:    */      }
/*  92:    */      
/*  93: 93 */      if ((figure.getData().isStroked()) && 
/*  94: 94 */        (figure.getData().isColor("stroke"))) {
/*  95: 95 */        g.setColor(figure.getData().getAsColor("stroke"));
/*  96: 96 */        g.setLineWidth(figure.getData().getAsFloat("stroke-width"));
/*  97: 97 */        g.setAntiAlias(true);
/*  98: 98 */        g.draw(diagram.getFigure(i).getShape());
/*  99: 99 */        g.setAntiAlias(false);
/* 100:100 */        g.resetLineWidth();
/* 101:    */      }
/* 102:    */    }
/* 103:    */  }
/* 104:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.svg.SimpleDiagramRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */