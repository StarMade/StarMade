/*   1:    */package org.newdawn.slick.font.effects;
/*   2:    */
/*   3:    */import java.awt.BasicStroke;
/*   4:    */import java.awt.Color;
/*   5:    */import java.awt.Shape;
/*   6:    */import java.awt.Stroke;
/*   7:    */import java.awt.geom.FlatteningPathIterator;
/*   8:    */import java.awt.geom.GeneralPath;
/*   9:    */import java.awt.geom.PathIterator;
/*  10:    */import java.util.Iterator;
/*  11:    */import java.util.List;
/*  12:    */
/*  32:    */public class OutlineWobbleEffect
/*  33:    */  extends OutlineEffect
/*  34:    */{
/*  35: 35 */  private float detail = 1.0F;
/*  36:    */  
/*  37: 37 */  private float amplitude = 1.0F;
/*  38:    */  
/*  41:    */  public OutlineWobbleEffect()
/*  42:    */  {
/*  43: 43 */    setStroke(new WobbleStroke(null));
/*  44:    */  }
/*  45:    */  
/*  50:    */  public float getDetail()
/*  51:    */  {
/*  52: 52 */    return this.detail;
/*  53:    */  }
/*  54:    */  
/*  59:    */  public void setDetail(float detail)
/*  60:    */  {
/*  61: 61 */    this.detail = detail;
/*  62:    */  }
/*  63:    */  
/*  68:    */  public float getAmplitude()
/*  69:    */  {
/*  70: 70 */    return this.amplitude;
/*  71:    */  }
/*  72:    */  
/*  77:    */  public void setAmplitude(float amplitude)
/*  78:    */  {
/*  79: 79 */    this.amplitude = amplitude;
/*  80:    */  }
/*  81:    */  
/*  87:    */  public OutlineWobbleEffect(int width, Color color)
/*  88:    */  {
/*  89: 89 */    super(width, color);
/*  90:    */  }
/*  91:    */  
/*  94:    */  public String toString()
/*  95:    */  {
/*  96: 96 */    return "Outline (Wobble)";
/*  97:    */  }
/*  98:    */  
/* 101:    */  public List getValues()
/* 102:    */  {
/* 103:103 */    List values = super.getValues();
/* 104:104 */    values.remove(2);
/* 105:105 */    values.add(EffectUtil.floatValue("Detail", this.detail, 1.0F, 50.0F, "This setting controls how detailed the outline will be. Smaller numbers cause the outline to have more detail."));
/* 106:    */    
/* 107:107 */    values.add(EffectUtil.floatValue("Amplitude", this.amplitude, 0.5F, 50.0F, "This setting controls the amplitude of the outline."));
/* 108:108 */    return values;
/* 109:    */  }
/* 110:    */  
/* 113:    */  public void setValues(List values)
/* 114:    */  {
/* 115:115 */    super.setValues(values);
/* 116:116 */    for (Iterator iter = values.iterator(); iter.hasNext();) {
/* 117:117 */      ConfigurableEffect.Value value = (ConfigurableEffect.Value)iter.next();
/* 118:118 */      if (value.getName().equals("Detail")) {
/* 119:119 */        this.detail = ((Float)value.getObject()).floatValue();
/* 120:120 */      } else if (value.getName().equals("Amplitude")) {
/* 121:121 */        this.amplitude = ((Float)value.getObject()).floatValue();
/* 122:    */      }
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 128:    */  private class WobbleStroke
/* 129:    */    implements Stroke
/* 130:    */  {
/* 131:    */    private static final float FLATNESS = 1.0F;
/* 132:    */    
/* 135:    */    private WobbleStroke() {}
/* 136:    */    
/* 138:    */    public Shape createStrokedShape(Shape shape)
/* 139:    */    {
/* 140:140 */      GeneralPath result = new GeneralPath();
/* 141:141 */      shape = new BasicStroke(OutlineWobbleEffect.this.getWidth(), 2, OutlineWobbleEffect.this.getJoin()).createStrokedShape(shape);
/* 142:142 */      PathIterator it = new FlatteningPathIterator(shape.getPathIterator(null), 1.0D);
/* 143:143 */      float[] points = new float[6];
/* 144:144 */      float moveX = 0.0F;float moveY = 0.0F;
/* 145:145 */      float lastX = 0.0F;float lastY = 0.0F;
/* 146:146 */      float thisX = 0.0F;float thisY = 0.0F;
/* 147:147 */      int type = 0;
/* 148:148 */      float next = 0.0F;
/* 149:149 */      while (!it.isDone()) {
/* 150:150 */        type = it.currentSegment(points);
/* 151:151 */        switch (type) {
/* 152:    */        case 0: 
/* 153:153 */          moveX = lastX = randomize(points[0]);
/* 154:154 */          moveY = lastY = randomize(points[1]);
/* 155:155 */          result.moveTo(moveX, moveY);
/* 156:156 */          next = 0.0F;
/* 157:157 */          break;
/* 158:    */        
/* 159:    */        case 4: 
/* 160:160 */          points[0] = moveX;
/* 161:161 */          points[1] = moveY;
/* 162:    */        
/* 164:    */        case 1: 
/* 165:165 */          thisX = randomize(points[0]);
/* 166:166 */          thisY = randomize(points[1]);
/* 167:167 */          float dx = thisX - lastX;
/* 168:168 */          float dy = thisY - lastY;
/* 169:169 */          float distance = (float)Math.sqrt(dx * dx + dy * dy);
/* 170:170 */          if (distance >= next) {
/* 171:171 */            float r = 1.0F / distance;
/* 172:172 */            while (distance >= next) {
/* 173:173 */              float x = lastX + next * dx * r;
/* 174:174 */              float y = lastY + next * dy * r;
/* 175:175 */              result.lineTo(randomize(x), randomize(y));
/* 176:176 */              next += OutlineWobbleEffect.this.detail;
/* 177:    */            }
/* 178:    */          }
/* 179:179 */          next -= distance;
/* 180:180 */          lastX = thisX;
/* 181:181 */          lastY = thisY;
/* 182:    */        }
/* 183:    */        
/* 184:184 */        it.next();
/* 185:    */      }
/* 186:    */      
/* 187:187 */      return result;
/* 188:    */    }
/* 189:    */    
/* 195:    */    private float randomize(float x)
/* 196:    */    {
/* 197:197 */      return x + (float)Math.random() * OutlineWobbleEffect.this.amplitude * 2.0F - 1.0F;
/* 198:    */    }
/* 199:    */  }
/* 200:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.effects.OutlineWobbleEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */