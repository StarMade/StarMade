/*   1:    */package org.apache.commons.lang3;
/*   2:    */
/*  30:    */public enum JavaVersion
/*  31:    */{
/*  32: 32 */  JAVA_0_9(1.5F, "0.9"), 
/*  33:    */  
/*  37: 37 */  JAVA_1_1(1.1F, "1.1"), 
/*  38:    */  
/*  42: 42 */  JAVA_1_2(1.2F, "1.2"), 
/*  43:    */  
/*  47: 47 */  JAVA_1_3(1.3F, "1.3"), 
/*  48:    */  
/*  52: 52 */  JAVA_1_4(1.4F, "1.4"), 
/*  53:    */  
/*  57: 57 */  JAVA_1_5(1.5F, "1.5"), 
/*  58:    */  
/*  62: 62 */  JAVA_1_6(1.6F, "1.6"), 
/*  63:    */  
/*  67: 67 */  JAVA_1_7(1.7F, "1.7"), 
/*  68:    */  
/*  72: 72 */  JAVA_1_8(1.8F, "1.8");
/*  73:    */  
/*  78:    */  private float value;
/*  79:    */  
/*  83:    */  private String name;
/*  84:    */  
/*  88:    */  private JavaVersion(float value, String name)
/*  89:    */  {
/*  90: 90 */    this.value = value;
/*  91: 91 */    this.name = name;
/*  92:    */  }
/*  93:    */  
/* 103:    */  public boolean atLeast(JavaVersion requiredVersion)
/* 104:    */  {
/* 105:105 */    return this.value >= requiredVersion.value;
/* 106:    */  }
/* 107:    */  
/* 117:    */  static JavaVersion getJavaVersion(String nom)
/* 118:    */  {
/* 119:119 */    return get(nom);
/* 120:    */  }
/* 121:    */  
/* 130:    */  static JavaVersion get(String nom)
/* 131:    */  {
/* 132:132 */    if ("0.9".equals(nom))
/* 133:133 */      return JAVA_0_9;
/* 134:134 */    if ("1.1".equals(nom))
/* 135:135 */      return JAVA_1_1;
/* 136:136 */    if ("1.2".equals(nom))
/* 137:137 */      return JAVA_1_2;
/* 138:138 */    if ("1.3".equals(nom))
/* 139:139 */      return JAVA_1_3;
/* 140:140 */    if ("1.4".equals(nom))
/* 141:141 */      return JAVA_1_4;
/* 142:142 */    if ("1.5".equals(nom))
/* 143:143 */      return JAVA_1_5;
/* 144:144 */    if ("1.6".equals(nom))
/* 145:145 */      return JAVA_1_6;
/* 146:146 */    if ("1.7".equals(nom))
/* 147:147 */      return JAVA_1_7;
/* 148:148 */    if ("1.8".equals(nom)) {
/* 149:149 */      return JAVA_1_8;
/* 150:    */    }
/* 151:151 */    return null;
/* 152:    */  }
/* 153:    */  
/* 163:    */  public String toString()
/* 164:    */  {
/* 165:165 */    return this.name;
/* 166:    */  }
/* 167:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.commons.lang3.JavaVersion
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */