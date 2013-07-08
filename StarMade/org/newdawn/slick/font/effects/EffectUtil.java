/*   1:    */package org.newdawn.slick.font.effects;
/*   2:    */
/*   3:    */import java.awt.AlphaComposite;
/*   4:    */import java.awt.Color;
/*   5:    */import java.awt.Container;
/*   6:    */import java.awt.Dimension;
/*   7:    */import java.awt.EventQueue;
/*   8:    */import java.awt.Graphics2D;
/*   9:    */import java.awt.GridBagConstraints;
/*  10:    */import java.awt.GridBagLayout;
/*  11:    */import java.awt.Insets;
/*  12:    */import java.awt.event.ActionEvent;
/*  13:    */import java.awt.event.ActionListener;
/*  14:    */import java.awt.image.BufferedImage;
/*  15:    */import javax.swing.BorderFactory;
/*  16:    */import javax.swing.DefaultComboBoxModel;
/*  17:    */import javax.swing.JButton;
/*  18:    */import javax.swing.JCheckBox;
/*  19:    */import javax.swing.JColorChooser;
/*  20:    */import javax.swing.JComboBox;
/*  21:    */import javax.swing.JComponent;
/*  22:    */import javax.swing.JDialog;
/*  23:    */import javax.swing.JFormattedTextField;
/*  24:    */import javax.swing.JLabel;
/*  25:    */import javax.swing.JPanel;
/*  26:    */import javax.swing.JSpinner;
/*  27:    */import javax.swing.JSpinner.DefaultEditor;
/*  28:    */import javax.swing.JTextArea;
/*  29:    */import javax.swing.SpinnerNumberModel;
/*  30:    */
/*  38:    */public class EffectUtil
/*  39:    */{
/*  40: 40 */  private static BufferedImage scratchImage = new BufferedImage(256, 256, 2);
/*  41:    */  
/*  47:    */  public static BufferedImage getScratchImage()
/*  48:    */  {
/*  49: 49 */    Graphics2D g = (Graphics2D)scratchImage.getGraphics();
/*  50: 50 */    g.setComposite(AlphaComposite.Clear);
/*  51: 51 */    g.fillRect(0, 0, 256, 256);
/*  52: 52 */    g.setComposite(AlphaComposite.SrcOver);
/*  53: 53 */    g.setColor(Color.white);
/*  54: 54 */    return scratchImage;
/*  55:    */  }
/*  56:    */  
/*  63:    */  public static ConfigurableEffect.Value colorValue(String name, Color currentValue)
/*  64:    */  {
/*  65: 65 */    new DefaultValue(name, toString(currentValue)) {
/*  66:    */      public void showDialog() {
/*  67: 67 */        Color newColor = JColorChooser.showDialog(null, "Choose a color", EffectUtil.fromString(this.value));
/*  68: 68 */        if (newColor != null) this.value = EffectUtil.toString(newColor);
/*  69:    */      }
/*  70:    */      
/*  71:    */      public Object getObject() {
/*  72: 72 */        return EffectUtil.fromString(this.value);
/*  73:    */      }
/*  74:    */    };
/*  75:    */  }
/*  76:    */  
/*  84:    */  public static ConfigurableEffect.Value intValue(String name, final int currentValue, final String description)
/*  85:    */  {
/*  86: 86 */    new DefaultValue(name, String.valueOf(currentValue)) {
/*  87:    */      public void showDialog() {
/*  88: 88 */        JSpinner spinner = new JSpinner(new SpinnerNumberModel(currentValue, -32768, 32767, 1));
/*  89: 89 */        if (showValueDialog(spinner, description)) this.value = String.valueOf(spinner.getValue());
/*  90:    */      }
/*  91:    */      
/*  92:    */      public Object getObject() {
/*  93: 93 */        return Integer.valueOf(this.value);
/*  94:    */      }
/*  95:    */    };
/*  96:    */  }
/*  97:    */  
/* 108:    */  public static ConfigurableEffect.Value floatValue(String name, final float currentValue, final float min, final float max, final String description)
/* 109:    */  {
/* 110:110 */    new DefaultValue(name, String.valueOf(currentValue)) {
/* 111:    */      public void showDialog() {
/* 112:112 */        JSpinner spinner = new JSpinner(new SpinnerNumberModel(currentValue, min, max, 0.1000000014901161D));
/* 113:113 */        if (showValueDialog(spinner, description)) this.value = String.valueOf(((Double)spinner.getValue()).floatValue());
/* 114:    */      }
/* 115:    */      
/* 116:    */      public Object getObject() {
/* 117:117 */        return Float.valueOf(this.value);
/* 118:    */      }
/* 119:    */    };
/* 120:    */  }
/* 121:    */  
/* 129:    */  public static ConfigurableEffect.Value booleanValue(String name, final boolean currentValue, final String description)
/* 130:    */  {
/* 131:131 */    new DefaultValue(name, String.valueOf(currentValue)) {
/* 132:    */      public void showDialog() {
/* 133:133 */        JCheckBox checkBox = new JCheckBox();
/* 134:134 */        checkBox.setSelected(currentValue);
/* 135:135 */        if (showValueDialog(checkBox, description)) this.value = String.valueOf(checkBox.isSelected());
/* 136:    */      }
/* 137:    */      
/* 138:    */      public Object getObject() {
/* 139:139 */        return Boolean.valueOf(this.value);
/* 140:    */      }
/* 141:    */    };
/* 142:    */  }
/* 143:    */  
/* 156:    */  public static ConfigurableEffect.Value optionValue(String name, final String currentValue, final String[][] options, final String description)
/* 157:    */  {
/* 158:158 */    new DefaultValue(name, currentValue.toString()) {
/* 159:    */      public void showDialog() {
/* 160:160 */        int selectedIndex = -1;
/* 161:161 */        DefaultComboBoxModel model = new DefaultComboBoxModel();
/* 162:162 */        for (int i = 0; i < options.length; i++) {
/* 163:163 */          model.addElement(options[i][0]);
/* 164:164 */          if (getValue(i).equals(currentValue)) selectedIndex = i;
/* 165:    */        }
/* 166:166 */        JComboBox comboBox = new JComboBox(model);
/* 167:167 */        comboBox.setSelectedIndex(selectedIndex);
/* 168:168 */        if (showValueDialog(comboBox, description)) this.value = getValue(comboBox.getSelectedIndex());
/* 169:    */      }
/* 170:    */      
/* 171:    */      private String getValue(int i) {
/* 172:172 */        if (options[i].length == 1) return options[i][0];
/* 173:173 */        return options[i][1];
/* 174:    */      }
/* 175:    */      
/* 176:    */      public String toString() {
/* 177:177 */        for (int i = 0; i < options.length; i++)
/* 178:178 */          if (getValue(i).equals(this.value)) return options[i][0].toString();
/* 179:179 */        return "";
/* 180:    */      }
/* 181:    */      
/* 182:    */      public Object getObject() {
/* 183:183 */        return this.value;
/* 184:    */      }
/* 185:    */    };
/* 186:    */  }
/* 187:    */  
/* 193:    */  public static String toString(Color color)
/* 194:    */  {
/* 195:195 */    if (color == null) throw new IllegalArgumentException("color cannot be null.");
/* 196:196 */    String r = Integer.toHexString(color.getRed());
/* 197:197 */    if (r.length() == 1) r = "0" + r;
/* 198:198 */    String g = Integer.toHexString(color.getGreen());
/* 199:199 */    if (g.length() == 1) g = "0" + g;
/* 200:200 */    String b = Integer.toHexString(color.getBlue());
/* 201:201 */    if (b.length() == 1) b = "0" + b;
/* 202:202 */    return r + g + b;
/* 203:    */  }
/* 204:    */  
/* 210:    */  public static Color fromString(String rgb)
/* 211:    */  {
/* 212:212 */    if ((rgb == null) || (rgb.length() != 6)) return Color.white;
/* 213:213 */    return new Color(Integer.parseInt(rgb.substring(0, 2), 16), Integer.parseInt(rgb.substring(2, 4), 16), Integer.parseInt(rgb.substring(4, 6), 16));
/* 214:    */  }
/* 215:    */  
/* 219:    */  private static abstract class DefaultValue
/* 220:    */    implements ConfigurableEffect.Value
/* 221:    */  {
/* 222:    */    String value;
/* 223:    */    
/* 226:    */    String name;
/* 227:    */    
/* 231:    */    public DefaultValue(String name, String value)
/* 232:    */    {
/* 233:233 */      this.value = value;
/* 234:234 */      this.name = name;
/* 235:    */    }
/* 236:    */    
/* 239:    */    public void setString(String value)
/* 240:    */    {
/* 241:241 */      this.value = value;
/* 242:    */    }
/* 243:    */    
/* 246:    */    public String getString()
/* 247:    */    {
/* 248:248 */      return this.value;
/* 249:    */    }
/* 250:    */    
/* 253:    */    public String getName()
/* 254:    */    {
/* 255:255 */      return this.name;
/* 256:    */    }
/* 257:    */    
/* 260:    */    public String toString()
/* 261:    */    {
/* 262:262 */      if (this.value == null) {
/* 263:263 */        return "";
/* 264:    */      }
/* 265:265 */      return this.value.toString();
/* 266:    */    }
/* 267:    */    
/* 274:    */    public boolean showValueDialog(final JComponent component, String description)
/* 275:    */    {
/* 276:276 */      EffectUtil.ValueDialog dialog = new EffectUtil.ValueDialog(component, this.name, description);
/* 277:277 */      dialog.setTitle(this.name);
/* 278:278 */      dialog.setLocationRelativeTo(null);
/* 279:279 */      EventQueue.invokeLater(new Runnable() {
/* 280:    */        public void run() {
/* 281:281 */          JComponent focusComponent = component;
/* 282:282 */          if ((focusComponent instanceof JSpinner))
/* 283:283 */            focusComponent = ((JSpinner.DefaultEditor)((JSpinner)component).getEditor()).getTextField();
/* 284:284 */          focusComponent.requestFocusInWindow();
/* 285:    */        }
/* 286:286 */      });
/* 287:287 */      dialog.setVisible(true);
/* 288:288 */      return dialog.okPressed;
/* 289:    */    }
/* 290:    */  }
/* 291:    */  
/* 294:    */  private static class ValueDialog
/* 295:    */    extends JDialog
/* 296:    */  {
/* 297:297 */    public boolean okPressed = false;
/* 298:    */    
/* 305:    */    public ValueDialog(JComponent component, String name, String description)
/* 306:    */    {
/* 307:307 */      setDefaultCloseOperation(2);
/* 308:308 */      setLayout(new GridBagLayout());
/* 309:309 */      setModal(true);
/* 310:    */      
/* 311:311 */      if ((component instanceof JSpinner)) {
/* 312:312 */        ((JSpinner.DefaultEditor)((JSpinner)component).getEditor()).getTextField().setColumns(4);
/* 313:    */      }
/* 314:314 */      JPanel descriptionPanel = new JPanel();
/* 315:315 */      descriptionPanel.setLayout(new GridBagLayout());
/* 316:316 */      getContentPane().add(descriptionPanel, new GridBagConstraints(0, 0, 2, 1, 1.0D, 0.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
/* 317:    */      
/* 320:320 */      descriptionPanel.setBackground(Color.white);
/* 321:321 */      descriptionPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
/* 322:    */      
/* 323:323 */      JTextArea descriptionText = new JTextArea(description);
/* 324:324 */      descriptionPanel.add(descriptionText, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
/* 325:    */      
/* 326:326 */      descriptionText.setWrapStyleWord(true);
/* 327:327 */      descriptionText.setLineWrap(true);
/* 328:328 */      descriptionText.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 329:329 */      descriptionText.setEditable(false);
/* 330:    */      
/* 332:332 */      JPanel panel = new JPanel();
/* 333:333 */      getContentPane().add(panel, new GridBagConstraints(0, 1, 1, 1, 1.0D, 1.0D, 10, 0, new Insets(5, 5, 0, 5), 0, 0));
/* 334:    */      
/* 337:337 */      panel.add(new JLabel(name + ":"));
/* 338:338 */      panel.add(component);
/* 339:    */      
/* 340:340 */      JPanel buttonPanel = new JPanel();
/* 341:341 */      getContentPane().add(buttonPanel, new GridBagConstraints(0, 2, 2, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
/* 342:    */      
/* 346:346 */      JButton okButton = new JButton("OK");
/* 347:347 */      buttonPanel.add(okButton);
/* 348:348 */      okButton.addActionListener(new ActionListener() {
/* 349:    */        public void actionPerformed(ActionEvent evt) {
/* 350:350 */          EffectUtil.ValueDialog.this.okPressed = true;
/* 351:351 */          EffectUtil.ValueDialog.this.setVisible(false);
/* 352:    */        }
/* 353:    */        
/* 355:355 */      });
/* 356:356 */      JButton cancelButton = new JButton("Cancel");
/* 357:357 */      buttonPanel.add(cancelButton);
/* 358:358 */      cancelButton.addActionListener(new ActionListener() {
/* 359:    */        public void actionPerformed(ActionEvent evt) {
/* 360:360 */          EffectUtil.ValueDialog.this.setVisible(false);
/* 361:    */        }
/* 362:    */        
/* 364:364 */      });
/* 365:365 */      setSize(new Dimension(320, 175));
/* 366:    */    }
/* 367:    */  }
/* 368:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.font.effects.EffectUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */