/*   1:    */import java.awt.Component;
/*   2:    */import java.awt.Font;
/*   3:    */import java.awt.GridBagConstraints;
/*   4:    */import java.awt.GridBagLayout;
/*   5:    */import java.awt.Insets;
/*   6:    */import java.awt.LayoutManager;
/*   7:    */import javax.swing.JLabel;
/*   8:    */import javax.swing.JPanel;
/*   9:    */
/*  19:    */public final class po
/*  20:    */  extends JPanel
/*  21:    */{
/*  22:    */  private static final long serialVersionUID = 5674993253545810749L;
/*  23:    */  
/*  24:    */  public po()
/*  25:    */  {
/*  26: 26 */    (localObject1 = new GridBagLayout()).columnWidths = new int[] { 0, 0, 0 };
/*  27: 27 */    ((GridBagLayout)localObject1).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*  28: 28 */    ((GridBagLayout)localObject1).columnWeights = new double[] { 0.0D, 1.0D, 4.9E-324D };
/*  29: 29 */    ((GridBagLayout)localObject1).rowWeights = new double[] { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 4.9E-324D };
/*  30: 30 */    setLayout((LayoutManager)localObject1);
/*  31:    */    
/*  32: 32 */    Object localObject1 = new JLabel(" Tutorial:");
/*  33:    */    
/*  34: 34 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/*  35: 35 */    ((GridBagConstraints)localObject2).insets = new Insets(0, 5, 5, 5);
/*  36: 36 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  37: 37 */    ((GridBagConstraints)localObject2).gridy = 0;
/*  38: 38 */    add((Component)localObject1, localObject2);
/*  39:    */    
/*  40: 40 */    localObject1 = new pH(xu.O);
/*  41:    */    
/*  42: 42 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  43: 43 */    ((GridBagConstraints)localObject2).fill = 2;
/*  44: 44 */    ((GridBagConstraints)localObject2).gridx = 1;
/*  45: 45 */    ((GridBagConstraints)localObject2).gridy = 0;
/*  46: 46 */    add((Component)localObject1, localObject2);
/*  47:    */    
/*  48: 48 */    (
/*  49: 49 */      localObject1 = new JLabel("Graphics Settings")).setFont(new Font("Arial", 0, 13));
/*  50:    */    
/*  51: 51 */    (localObject2 = new GridBagConstraints()).anchor = 17;
/*  52: 52 */    ((GridBagConstraints)localObject2).gridwidth = 2;
/*  53: 53 */    ((GridBagConstraints)localObject2).insets = new Insets(5, 0, 5, 0);
/*  54: 54 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  55: 55 */    ((GridBagConstraints)localObject2).gridy = 1;
/*  56: 56 */    add((Component)localObject1, localObject2);
/*  57:    */    
/*  59: 59 */    localObject1 = new JLabel(" Resolution: ");
/*  60:    */    
/*  61: 61 */    (localObject2 = new GridBagConstraints()).insets = new Insets(0, 5, 5, 5);
/*  62: 62 */    ((GridBagConstraints)localObject2).anchor = 17;
/*  63: 63 */    ((GridBagConstraints)localObject2).gridx = 0;
/*  64: 64 */    ((GridBagConstraints)localObject2).gridy = 2;
/*  65: 65 */    add((Component)localObject1, localObject2);
/*  66:    */    
/*  69: 69 */    Object localObject2 = new pH(xu.b);
/*  70:    */    
/*  71: 71 */    (localObject3 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  72: 72 */    ((GridBagConstraints)localObject3).fill = 2;
/*  73: 73 */    ((GridBagConstraints)localObject3).gridx = 1;
/*  74: 74 */    ((GridBagConstraints)localObject3).gridy = 2;
/*  75: 75 */    add((Component)localObject2, localObject3);
/*  76:    */    
/*  77: 77 */    Object localObject3 = new JLabel(" Fullscreen: ");
/*  78:    */    
/*  79: 79 */    (localObject4 = new GridBagConstraints()).anchor = 17;
/*  80: 80 */    ((GridBagConstraints)localObject4).insets = new Insets(0, 5, 5, 5);
/*  81: 81 */    ((GridBagConstraints)localObject4).gridx = 0;
/*  82: 82 */    ((GridBagConstraints)localObject4).gridy = 3;
/*  83: 83 */    add((Component)localObject3, localObject4);
/*  84:    */    
/*  87: 87 */    Object localObject4 = new pH(xu.c);
/*  88:    */    
/*  89: 89 */    (localObject5 = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/*  90: 90 */    ((GridBagConstraints)localObject5).fill = 2;
/*  91: 91 */    ((GridBagConstraints)localObject5).gridx = 1;
/*  92: 92 */    ((GridBagConstraints)localObject5).gridy = 3;
/*  93: 93 */    add((Component)localObject4, localObject5);
/*  94:    */    
/*  95: 95 */    Object localObject5 = new JLabel(" Field of View (FOV): ");
/*  96:    */    GridBagConstraints localGridBagConstraints;
/*  97: 97 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
/*  98: 98 */    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/*  99: 99 */    localGridBagConstraints.gridx = 0;
/* 100:100 */    localGridBagConstraints.gridy = 4;
/* 101:101 */    add((Component)localObject5, localGridBagConstraints);
/* 102:    */    
/* 103:103 */    localObject5 = new pH(xu.f);
/* 104:    */    
/* 105:105 */    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 106:106 */    localGridBagConstraints.fill = 2;
/* 107:107 */    localGridBagConstraints.gridx = 1;
/* 108:108 */    localGridBagConstraints.gridy = 4;
/* 109:109 */    add((Component)localObject5, localGridBagConstraints);
/* 110:    */    
/* 111:111 */    (
/* 112:112 */      localObject5 = new JLabel("Sound Settings")).setFont(new Font("Arial", 0, 13));
/* 113:    */    
/* 114:114 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
/* 115:115 */    localGridBagConstraints.gridwidth = 2;
/* 116:116 */    localGridBagConstraints.insets = new Insets(10, 0, 5, 0);
/* 117:117 */    localGridBagConstraints.gridx = 0;
/* 118:118 */    localGridBagConstraints.gridy = 5;
/* 119:119 */    add((Component)localObject5, localGridBagConstraints);
/* 120:    */    
/* 121:121 */    localObject5 = new JLabel(" Enable Sound: ");
/* 122:    */    
/* 123:123 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
/* 124:124 */    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/* 125:125 */    localGridBagConstraints.gridx = 0;
/* 126:126 */    localGridBagConstraints.gridy = 6;
/* 127:127 */    add((Component)localObject5, localGridBagConstraints);
/* 128:    */    
/* 129:129 */    localObject5 = new pH(xu.g);
/* 130:    */    
/* 131:131 */    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 132:132 */    localGridBagConstraints.fill = 2;
/* 133:133 */    localGridBagConstraints.gridx = 1;
/* 134:134 */    localGridBagConstraints.gridy = 6;
/* 135:135 */    add((Component)localObject5, localGridBagConstraints);
/* 136:    */    
/* 137:137 */    localObject5 = new JLabel(" Sound Volume: ");
/* 138:    */    
/* 139:139 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
/* 140:140 */    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/* 141:141 */    localGridBagConstraints.gridx = 0;
/* 142:142 */    localGridBagConstraints.gridy = 7;
/* 143:143 */    add((Component)localObject5, localGridBagConstraints);
/* 144:    */    
/* 145:145 */    localObject5 = new pH(xu.i);
/* 146:    */    
/* 147:147 */    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 148:148 */    localGridBagConstraints.fill = 2;
/* 149:149 */    localGridBagConstraints.gridx = 1;
/* 150:150 */    localGridBagConstraints.gridy = 7;
/* 151:151 */    add((Component)localObject5, localGridBagConstraints);
/* 152:    */    
/* 153:153 */    (
/* 154:154 */      localObject5 = new JLabel("Control Settings")).setFont(new Font("Arial", 0, 13));
/* 155:    */    
/* 156:156 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 16;
/* 157:157 */    localGridBagConstraints.gridwidth = 2;
/* 158:158 */    localGridBagConstraints.insets = new Insets(10, 0, 5, 0);
/* 159:159 */    localGridBagConstraints.gridx = 0;
/* 160:160 */    localGridBagConstraints.gridy = 8;
/* 161:161 */    add((Component)localObject5, localGridBagConstraints);
/* 162:    */    
/* 163:163 */    localObject5 = new JLabel(" Invert Mouse (All):");
/* 164:    */    
/* 165:165 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
/* 166:166 */    localGridBagConstraints.insets = new Insets(0, 5, 5, 5);
/* 167:167 */    localGridBagConstraints.gridx = 0;
/* 168:168 */    localGridBagConstraints.gridy = 9;
/* 169:169 */    add((Component)localObject5, localGridBagConstraints);
/* 170:    */    
/* 171:171 */    localObject5 = new pH(xu.Z);
/* 172:    */    
/* 173:173 */    (localGridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 5, 0);
/* 174:174 */    localGridBagConstraints.fill = 2;
/* 175:175 */    localGridBagConstraints.gridx = 1;
/* 176:176 */    localGridBagConstraints.gridy = 9;
/* 177:177 */    add((Component)localObject5, localGridBagConstraints);
/* 178:    */    
/* 179:179 */    localObject5 = new JLabel(" Invert Ship Mouse:");
/* 180:    */    
/* 181:181 */    (localGridBagConstraints = new GridBagConstraints()).anchor = 17;
/* 182:182 */    localGridBagConstraints.insets = new Insets(0, 5, 0, 5);
/* 183:183 */    localGridBagConstraints.gridx = 0;
/* 184:184 */    localGridBagConstraints.gridy = 10;
/* 185:185 */    add((Component)localObject5, localGridBagConstraints);
/* 186:    */    
/* 187:187 */    localObject5 = new pH(xu.Y);
/* 188:    */    
/* 189:189 */    (localGridBagConstraints = new GridBagConstraints()).fill = 2;
/* 190:190 */    localGridBagConstraints.gridx = 1;
/* 191:191 */    localGridBagConstraints.gridy = 10;
/* 192:192 */    add((Component)localObject5, localGridBagConstraints);
/* 193:    */    
/* 195:195 */    ((JLabel)localObject3).setLabelFor((Component)localObject4);
/* 196:196 */    ((JLabel)localObject1).setLabelFor((Component)localObject2);
/* 197:    */  }
/* 198:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     po
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */