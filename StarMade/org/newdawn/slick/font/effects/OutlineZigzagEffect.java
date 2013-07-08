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
/*  32:    */public class OutlineZigzagEffect
/*  33:    */  extends OutlineEffect
/*  34:    */{
/*  35: 35 */  private float amplitude = 1.0F;
/*  36:    */  
/*  37: 37 */  private float wavelength = 3.0F;
/*  38:    */  
/*  41:    */  public OutlineZigzagEffect()
/*  42:    */  {
/*  43: 43 */    setStroke(new ZigzagStroke(null));
/*  44:    */  }
/*  45:    */  
/*  50:    */  public float getWavelength()
/*  51:    */  {
/*  52: 52 */    return this.wavelength;
/*  53:    */  }
/*  54:    */  
/*  59:    */  public void setWavelength(float wavelength)
/*  60:    */  {
/*  61: 61 */    this.wavelength = wavelength;
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
/*  87:    */  public OutlineZigzagEffect(int width, Color color)
/*  88:    */  {
/*  89: 89 */    super(width, color);
/*  90:    */  }
/*  91:    */  
/*  94:    */  public String toString()
/*  95:    */  {
/*  96: 96 */    return "Outline (Zigzag)";
/*  97:    */  }
/*  98:    */  
/* 101:    */  public List getValues()
/* 102:    */  {
/* 103:103 */    List values = super.getValues();
/* 104:104 */    values.add(EffectUtil.floatValue("Wavelength", this.wavelength, 1.0F, 100.0F, "This setting controls the wavelength of the outline. The smaller the value, the more segments will be used to draw the outline."));
/* 105:    */    
/* 106:106 */    values.add(EffectUtil.floatValue("Amplitude", this.amplitude, 0.5F, 50.0F, "This setting controls the amplitude of the outline. The bigger the value, the more the zigzags will vary."));
/* 107:    */    
/* 108:108 */    return values;
/* 109:    */  }
/* 110:    */  
/* 113:    */  public void setValues(List values)
/* 114:    */  {
/* 115:115 */    super.setValues(values);
/* 116:116 */    for (Iterator iter = values.iterator(); iter.hasNext();) {
/* 117:117 */      ConfigurableEffect.Value value = (ConfigurableEffect.Value)iter.next();
/* 118:118 */      if (value.getName().equals("Wavelength")) {
/* 119:119 */        this.wavelength = ((Float)value.getObject()).floatValue();
/* 120:120 */      } else if (value.getName().equals("Amplitude")) {
/* 121:121 */        this.amplitude = ((Float)value.getObject()).floatValue();
/* 122:    */      }
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 128:    */  private class ZigzagStroke
/* 129:    */    implements Stroke
/* 130:    */  {
/* 131:    */    private static final float FLATNESS = 1.0F;
/* 132:    */    
/* 135:    */    private ZigzagStroke() {}
/* 136:    */    
/* 138:    */    public Shape createStrokedShape(Shape shape)
/* 139:    */    {
/* 140:140 */      GeneralPath result = new GeneralPath();
/* 141:141 */      PathIterator it = new FlatteningPathIterator(shape.getPathIterator(null), 1.0D);
/* 142:142 */      float[] points = new float[6];
/* 143:143 */      float moveX = 0.0F;float moveY = 0.0F;
/* 144:144 */      float lastX = 0.0F;float lastY = 0.0F;
/* 145:145 */      float thisX = 0.0F;float thisY = 0.0F;
/* 146:146 */      int type = 0;
/* 147:147 */      float next = 0.0F;
/* 148:148 */      int phase = 0;
/* 149:149 */      while (!it.isDone()) {
/* 150:150 */        type = it.currentSegment(points);
/* 151:151 */        switch (type) {
/* 152:    */        case 0: 
/* 153:153 */          moveX = lastX = points[0];
/* 154:154 */          moveY = lastY = points[1];
/* 155:155 */          result.moveTo(moveX, moveY);
/* 156:156 */          next = OutlineZigzagEffect.this.wavelength / 2.0F;
/* 157:157 */          break;
/* 158:    */        
/* 159:    */        case 4: 
/* 160:160 */          points[0] = moveX;
/* 161:161 */          points[1] = moveY;
/* 162:    */        
/* 164:    */        case 1: 
/* 165:165 */          thisX = points[0];
/* 166:166 */          thisY = points[1];
/* 167:167 */          float dx = thisX - lastX;
/* 168:168 */          float dy = thisY - lastY;
/* 169:169 */          float distance = (float)Math.sqrt(dx * dx + dy * dy);
/* 170:170 */          if (distance >= next) {
/* 171:171 */            float r = 1.0F / distance;
/* 172:172 */            while (distance >= next) {
/* 173:173 */              float x = lastX + next * dx * r;
/* 174:174 */              float y = lastY + next * dy * r;
/* 175:175 */              if ((phase & 0x1) == 0) {
/* 176:176 */                result.lineTo(x + OutlineZigzagEffect.this.amplitude * dy * r, y - OutlineZigzagEffect.this.amplitude * dx * r);
/* 177:    */              } else
/* 178:178 */                result.lineTo(x - OutlineZigzagEffect.this.amplitude * dy * r, y + OutlineZigzagEffect.this.amplitude * dx * r);
/* 179:179 */              next += OutlineZigzagEffect.this.wavelength;
/* 180:180 */              phase++;
/* 181:    */            }
/* 182:    */          }
/* 183:183 */          next -= distance;
/* 184:184 */          lastX = thisX;
/* 185:185 */          lastY = thisY;
/* 186:186 */          if (type == 4) result.closePath();
/* 187:    */          break;
/* 188:    */        }
/* 189:189 */        it.next();
/* 190:    */      }
/* 191:191 */      return new BasicStroke(OutlineZigzagEffect.this.getWidth(), 2, OutlineZigzagEffect.this.getJoin()).createStrokedShape(result);
/* 192:    */    }
/* 193:    */  }
/* 194:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.effects.OutlineZigzagEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */