/*   1:    */import javax.vecmath.Vector3f;
/*   2:    */import org.newdawn.slick.Color;
/*   3:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*   4:    */import org.schema.schine.network.ChatSystem;
/*   5:    */import org.schema.schine.network.client.ClientState;
/*   6:    */
/*  12:    */public final class yu
/*  13:    */  extends yz
/*  14:    */{
/*  15:    */  private yP jdField_a_of_type_YP;
/*  16:    */  private yP jdField_b_of_type_YP;
/*  17: 17 */  private boolean jdField_a_of_type_Boolean = true;
/*  18:    */  
/*  19:    */  private boolean jdField_b_of_type_Boolean;
/*  20:    */  
/*  21:    */  private yO jdField_a_of_type_YO;
/*  22:    */  
/*  23:    */  private yt jdField_a_of_type_Yt;
/*  24: 24 */  private String jdField_b_of_type_JavaLangString = "";
/*  25:    */  
/*  26:    */  public yu(ClientState paramClientState) {
/*  27: 27 */    super(paramClientState);
/*  28:    */    
/*  29: 29 */    this.jdField_b_of_type_YP = new yP(d.n(), new Color(0.8F, 0.8F, 0.8F, 0.8F), paramClientState);
/*  30:    */    
/*  31: 31 */    this.jdField_b_of_type_YP.a(new yv(this));
/*  32:    */    
/*  42: 42 */    this.jdField_a_of_type_YP = new yP(300, 300, d.n(), paramClientState);
/*  43: 43 */    d.n();this.jdField_a_of_type_Yt = new yt(paramClientState);
/*  44: 44 */    this.jdField_a_of_type_YO = new yO(d.n(), paramClientState);
/*  45: 45 */    this.jdField_a_of_type_YO.jdField_b_of_type_JavaLangString = "[CHAT]: ";
/*  46:    */  }
/*  47:    */  
/*  48:    */  public final void a() {
/*  49: 49 */    this.jdField_a_of_type_YP.a();
/*  50:    */  }
/*  51:    */  
/*  52: 52 */  public final void a(xq paramxq) { this.jdField_a_of_type_YO.a(paramxq); }
/*  53:    */  
/*  54:    */  public final void b()
/*  55:    */  {
/*  56: 56 */    if (this.jdField_a_of_type_Boolean) {
/*  57: 57 */      c();
/*  58:    */    }
/*  59: 59 */    GlUtil.d();
/*  60: 60 */    r();
/*  61: 61 */    if (this.jdField_b_of_type_Boolean) {
/*  62: 62 */      this.jdField_a_of_type_Yt.b();
/*  63:    */      
/*  64: 64 */      this.jdField_a_of_type_YO.b();
/*  65: 65 */      if (this.jdField_b_of_type_JavaLangString.length() > 0) {
/*  66: 66 */        this.jdField_b_of_type_YP.b();
/*  67:    */      }
/*  68:    */    } else {
/*  69: 69 */      this.jdField_a_of_type_YP.b();
/*  70:    */    }
/*  71:    */    
/*  72: 72 */    GlUtil.c();
/*  73:    */  }
/*  74:    */  
/*  75:    */  public final float a()
/*  76:    */  {
/*  77: 77 */    return this.jdField_a_of_type_YO.a();
/*  78:    */  }
/*  79:    */  
/*  80:    */  public final String b()
/*  81:    */  {
/*  82: 82 */    return "chatWindow";
/*  83:    */  }
/*  84:    */  
/*  85:    */  public final float b()
/*  86:    */  {
/*  87: 87 */    return this.jdField_a_of_type_YO.b();
/*  88:    */  }
/*  89:    */  
/* 107:    */  public final void c()
/* 108:    */  {
/* 109:109 */    this.jdField_a_of_type_YP.a = 8;
/* 110:    */    
/* 111:111 */    this.jdField_a_of_type_YP.b = a().getVisibleChatLog();
/* 112:    */    
/* 113:113 */    this.jdField_b_of_type_YP.a().y -= 55.0F;
/* 114:    */    
/* 115:115 */    this.jdField_a_of_type_YO.a().y -= 30.0F;
/* 116:116 */    this.jdField_a_of_type_YO.c();
/* 117:    */    
/* 118:118 */    this.jdField_a_of_type_YP.c();
/* 119:119 */    this.jdField_a_of_type_Yt.c();
/* 120:120 */    this.jdField_b_of_type_YP.c();
/* 121:    */    
/* 122:122 */    this.jdField_a_of_type_Boolean = false;
/* 123:    */  }
/* 124:    */  
/* 125:    */  public final void a(ChatSystem paramChatSystem) {
/* 126:126 */    this.jdField_a_of_type_YO.a = paramChatSystem.getTextInput();
/* 127:    */  }
/* 128:    */  
/* 130:    */  public final void a(boolean paramBoolean)
/* 131:    */  {
/* 132:132 */    this.jdField_b_of_type_Boolean = paramBoolean;
/* 133:    */  }
/* 134:    */  
/* 142:    */  public final void a(String paramString)
/* 143:    */  {
/* 144:144 */    this.jdField_b_of_type_JavaLangString = paramString;
/* 145:    */  }
/* 146:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     yu
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */