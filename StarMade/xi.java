/*   1:    */import java.io.PrintStream;
/*   2:    */import java.nio.IntBuffer;
/*   3:    */import org.lwjgl.BufferUtils;
/*   4:    */import org.lwjgl.opengl.EXTFramebufferMultisample;
/*   5:    */import org.lwjgl.opengl.GL11;
/*   6:    */import org.lwjgl.opengl.GL13;
/*   7:    */import org.lwjgl.opengl.GL30;
/*   8:    */import org.schema.schine.graphicsengine.core.GLException;
/*   9:    */import org.schema.schine.graphicsengine.core.GlUtil;
/*  10:    */
/* 123:    */public final class xi
/* 124:    */{
/* 125:    */  private IntBuffer jdField_a_of_type_JavaNioIntBuffer;
/* 126:    */  public int a;
/* 127:    */  private int jdField_d_of_type_Int;
/* 128:    */  public boolean a;
/* 129:    */  public boolean b;
/* 130:    */  private boolean c;
/* 131:    */  private boolean jdField_d_of_type_Boolean;
/* 132:    */  public int b;
/* 133:    */  public int c;
/* 134:    */  
/* 135:    */  private static int b()
/* 136:    */  {
/* 137:137 */    return ((Integer)xu.T.a()).intValue();
/* 138:    */  }
/* 139:    */  
/* 167:167 */  private boolean jdField_e_of_type_Boolean = true;
/* 168:    */  
/* 170:    */  private xi jdField_a_of_type_Xi;
/* 171:    */  
/* 172:172 */  private boolean jdField_f_of_type_Boolean = true;
/* 173:    */  
/* 174:    */  private int jdField_e_of_type_Int;
/* 175:    */  
/* 176:    */  private int jdField_f_of_type_Int;
/* 177:    */  
/* 178:    */  public xi(int paramInt1, int paramInt2)
/* 179:    */  {
/* 180:180 */    this.jdField_b_of_type_Int = paramInt1;
/* 181:    */    
/* 189:189 */    this.jdField_c_of_type_Int = paramInt2;
/* 190:    */  }
/* 191:    */  
/* 339:    */  public final void a()
/* 340:    */  {
/* 341:341 */    System.out.println("[FBO] cleaning up FBO ");
/* 342:342 */    xi localxi = this; if (this.jdField_a_of_type_JavaNioIntBuffer != null) { System.out.println("[FBO] deleting Frame buffers ");GL30.glDeleteFramebuffers(localxi.jdField_a_of_type_JavaNioIntBuffer); } else { System.out.println("[FBO] no Frame buffers to clean up "); }
/* 343:343 */    localxi = this; IntBuffer localIntBuffer; (localIntBuffer = BufferUtils.createIntBuffer(1)).put(0, localxi.jdField_e_of_type_Int);localIntBuffer.rewind();GL30.glDeleteRenderbuffers(localIntBuffer);localIntBuffer.put(0, localxi.jdField_f_of_type_Int);localIntBuffer.rewind();GL30.glDeleteRenderbuffers(localIntBuffer);
/* 344:344 */    System.out.println("[FBO] deleting  fbo textures " + this.jdField_a_of_type_Int);
/* 345:345 */    GL11.glDeleteTextures(this.jdField_a_of_type_Int);
/* 346:346 */    if (this.jdField_a_of_type_Xi != null) {
/* 347:347 */      System.out.println("[FBO] deleting BLIN fbo textures " + this.jdField_a_of_type_Xi.jdField_a_of_type_Int);
/* 348:348 */      GL11.glDeleteTextures(this.jdField_a_of_type_Xi.jdField_a_of_type_Int);
/* 349:    */    }
/* 350:350 */    if (this.jdField_d_of_type_Int != 0) {
/* 351:351 */      System.out.println("[FBO] deleting depth fbo texture " + this.jdField_d_of_type_Int);
/* 352:352 */      GL11.glDeleteTextures(this.jdField_d_of_type_Int);
/* 353:    */    }
/* 354:    */  }
/* 355:    */  
/* 415:    */  private static int c()
/* 416:    */  {
/* 417:    */    IntBuffer localIntBuffer;
/* 418:    */    
/* 476:476 */    GL30.glGenRenderbuffers(localIntBuffer = BufferUtils.createIntBuffer(1));
/* 477:477 */    return localIntBuffer.get(0);
/* 478:    */  }
/* 479:    */  
/* 489:    */  private void a(int paramInt1, int paramInt2, int paramInt3)
/* 490:    */  {
/* 491:491 */    IntBuffer localIntBuffer = BufferUtils.createIntBuffer(16);
/* 492:492 */    GL11.glGetInteger(34024, localIntBuffer);
/* 493:493 */    localIntBuffer.rewind();
/* 494:494 */    int i = localIntBuffer.get(0);
/* 495:    */    
/* 497:497 */    if ((paramInt1 > i) || (paramInt2 > i)) {
/* 498:498 */      throw new GLException("height or width of renderbuffer store exceeds max");
/* 499:    */    }
/* 500:    */    
/* 501:501 */    if ((b() == 0) || (!this.jdField_f_of_type_Boolean)) {
/* 502:502 */      GL30.glRenderbufferStorage(36161, paramInt3, paramInt2, paramInt1);return;
/* 503:    */    }
/* 504:504 */    System.err.println("CREATING MULTISAMPLED RENDERBUFFER. SAMPLES: " + b());
/* 505:505 */    EXTFramebufferMultisample.glRenderbufferStorageMultisampleEXT(36161, b(), paramInt3, paramInt2, paramInt1);
/* 506:    */  }
/* 507:    */  
/* 611:    */  public final void b()
/* 612:    */  {
/* 613:613 */    if ((this.jdField_a_of_type_Boolean) && (b() > 0)) {
/* 614:614 */      xi localxi = this;GL30.glBindFramebuffer(36008, localxi.jdField_a_of_type_JavaNioIntBuffer.get(0));GL30.glBindFramebuffer(36009, localxi.jdField_a_of_type_Xi.jdField_a_of_type_JavaNioIntBuffer.get(0));GL30.glBlitFramebuffer(0, 0, localxi.jdField_b_of_type_Int, localxi.jdField_c_of_type_Int, 0, 0, localxi.jdField_b_of_type_Int, localxi.jdField_c_of_type_Int, 16384, 9728);GL30.glBindFramebuffer(36008, 0);GL30.glBindFramebuffer(36009, 0);
/* 615:    */    }
/* 616:    */    
/* 617:617 */    GL30.glBindRenderbuffer(36161, 0);
/* 618:618 */    GL30.glBindFramebuffer(36160, 0);
/* 619:    */    
/* 620:620 */    GL11.glViewport(0, 0, xm.b(), xm.a());
/* 621:621 */    this.jdField_a_of_type_Boolean = false;
/* 622:    */  }
/* 623:    */  
/* 624:    */  private static void f() {
/* 625:625 */    GL11.glBegin(7);
/* 626:    */    
/* 627:627 */    GL11.glTexCoord2f(0.0F, 0.0F);GL11.glVertex2f(0.0F, 0.0F);
/* 628:    */    
/* 629:629 */    GL11.glTexCoord2f(0.0F, 1.0F);GL11.glVertex2f(0.0F, xm.a());
/* 630:    */    
/* 631:631 */    GL11.glTexCoord2f(1.0F, 1.0F);GL11.glVertex2f(xm.b(), xm.a());
/* 632:    */    
/* 633:633 */    GL11.glTexCoord2f(1.0F, 0.0F);GL11.glVertex2f(xm.b(), 0.0F);
/* 634:    */    
/* 635:635 */    GL11.glEnd();
/* 636:    */  }
/* 637:    */  
/* 644:    */  public final void c()
/* 645:    */  {
/* 646:646 */    h();
/* 647:647 */    f();
/* 648:648 */    g();
/* 649:    */  }
/* 650:    */  
/* 658:    */  public final void a(zj paramzj)
/* 659:    */  {
/* 660:660 */    h();
/* 661:661 */    paramzj.b();
/* 662:662 */    f();
/* 663:663 */    paramzj.d();
/* 664:664 */    g();
/* 665:    */  }
/* 666:    */  
/* 760:    */  public final void d()
/* 761:    */  {
/* 762:762 */    GL11.glViewport(0, 0, this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/* 763:    */    
/* 765:765 */    int i = this.jdField_a_of_type_JavaNioIntBuffer.get(0);GL30.glBindFramebuffer(36160, i);
/* 766:766 */    if (!this.jdField_b_of_type_Boolean) {
/* 767:767 */      i = this.jdField_f_of_type_Int;GL30.glBindRenderbuffer(36161, i);
/* 768:    */    }
/* 769:769 */    if (!this.jdField_e_of_type_Boolean) {
/* 770:770 */      i = this.jdField_e_of_type_Int;GL30.glBindRenderbuffer(36161, i);
/* 771:    */    }
/* 772:    */    
/* 773:773 */    this.jdField_a_of_type_Boolean = true;
/* 774:    */  }
/* 775:    */  
/* 776:    */  private static void g() {
/* 777:777 */    GL11.glEnable(2884);
/* 778:    */    
/* 780:780 */    GL11.glBindTexture(3553, 0);
/* 781:781 */    GL11.glDisable(3553);
/* 782:782 */    GL11.glEnable(2896);
/* 783:783 */    GL11.glDisable(3042);
/* 784:784 */    GL11.glEnable(2929);
/* 785:785 */    GlUtil.a(5889);
/* 786:786 */    GlUtil.c();
/* 787:787 */    GlUtil.a(5888);
/* 788:788 */    GlUtil.c();
/* 789:    */  }
/* 790:    */  
/* 849:    */  public final int a()
/* 850:    */  {
/* 851:851 */    if (b() > 0) {
/* 852:852 */      return this.jdField_a_of_type_Xi.jdField_a_of_type_Int;
/* 853:    */    }
/* 854:854 */    return this.jdField_a_of_type_Int;
/* 855:    */  }
/* 856:    */  
/* 879:    */  public final void e()
/* 880:    */  {
/* 881:881 */    if (this.jdField_c_of_type_Boolean) {
/* 882:882 */      return;
/* 883:    */    }
/* 884:884 */    this.jdField_c_of_type_Boolean = true;
/* 885:885 */    if (this.jdField_d_of_type_Boolean)
/* 886:    */    {
/* 887:887 */      a();
/* 888:    */    }
/* 889:    */    
/* 892:892 */    System.out.println("[FrameBuffer] creating frame buffer  " + this.jdField_b_of_type_Int + " / " + this.jdField_c_of_type_Int);
/* 893:    */    
/* 894:894 */    if ((b() > 0) && (this.jdField_f_of_type_Boolean)) {
/* 895:895 */      this.jdField_a_of_type_Xi = new xi(this.jdField_b_of_type_Int, this.jdField_c_of_type_Int);
/* 896:896 */      this.jdField_a_of_type_Xi.jdField_e_of_type_Boolean = this.jdField_e_of_type_Boolean;
/* 897:897 */      this.jdField_a_of_type_Xi.jdField_b_of_type_Boolean = this.jdField_b_of_type_Boolean;
/* 898:898 */      this.jdField_a_of_type_Xi.jdField_f_of_type_Boolean = false;
/* 899:899 */      this.jdField_e_of_type_Boolean = false;
/* 900:900 */      this.jdField_b_of_type_Boolean = false;
/* 901:901 */      this.jdField_a_of_type_Xi.e();
/* 902:    */    }
/* 903:    */    
/* 904:904 */    xi localxi1 = this;this.jdField_a_of_type_JavaNioIntBuffer = BufferUtils.createIntBuffer(1);GL30.glGenFramebuffers(localxi1.jdField_a_of_type_JavaNioIntBuffer);
/* 905:905 */    int j = this.jdField_a_of_type_JavaNioIntBuffer.get(0);GL30.glBindFramebuffer(36160, j);
/* 906:    */    
/* 908:908 */    GlUtil.f();
/* 909:    */    int m;
/* 910:    */    int k;
/* 911:911 */    if (this.jdField_e_of_type_Boolean) {
/* 912:912 */      localxi1 = this;System.out.println("[FrameBuffer] initializing frame buffer textures ");GL11.glGenTextures(GlUtil.a());localxi1.jdField_a_of_type_Int = GlUtil.a().get(0);GL11.glBindTexture(3553, localxi1.jdField_a_of_type_Int);GlUtil.f();GL11.glTexParameteri(3553, 10241, 9729);GL11.glTexParameteri(3553, 10240, 9729);GlUtil.f();GL11.glTexParameteri(3553, 10242, 10496);GL11.glTexParameteri(3553, 10243, 10496);GlUtil.f();GL11.glTexImage2D(3553, 0, 6408, localxi1.jdField_b_of_type_Int, localxi1.jdField_c_of_type_Int, 0, 6408, 5121, null);GlUtil.f();
/* 913:913 */      j = this.jdField_a_of_type_Int;GL30.glFramebufferTexture2D(36160, 36064, 3553, j, 0);
/* 914:914 */      GlUtil.f();
/* 915:    */    } else {
/* 916:916 */      localxi1 = this;this.jdField_e_of_type_Int = c();j = localxi1.jdField_e_of_type_Int;GL30.glBindRenderbuffer(36161, j);j = localxi1.jdField_c_of_type_Int;m = localxi1.jdField_b_of_type_Int;localxi1.a(j, m, 6408);k = localxi1.jdField_e_of_type_Int;GL30.glFramebufferRenderbuffer(36160, 36064, 36161, k);GL30.glBindRenderbuffer(36161, 0);
/* 917:    */    }
/* 918:918 */    GlUtil.f();
/* 919:919 */    if (this.jdField_b_of_type_Boolean) {
/* 920:920 */      localxi1 = this;System.err.println("creating depth texture");GL11.glGenTextures(GlUtil.a());k = GlUtil.a().get(0);GL11.glBindTexture(3553, k);GL11.glTexParameteri(3553, 10242, 10497);GL11.glTexParameteri(3553, 10243, 10497);GL11.glTexParameteri(3553, 33085, 3);GlUtil.f();GL11.glTexParameteri(3553, 10241, 9987);GlUtil.f();GL11.glTexParameteri(3553, 10240, 9729);GlUtil.f();GL11.glTexParameteri(3553, 33169, 1);GL11.glTexParameteri(3553, 34891, 32841);GL11.glTexImage2D(3553, 0, 6408, localxi1.jdField_b_of_type_Int, localxi1.jdField_c_of_type_Int, 0, 6408, 5121, null);GlUtil.f();this.jdField_d_of_type_Int = k;
/* 921:921 */      int i = this.jdField_d_of_type_Int;GL30.glFramebufferTexture2D(36160, 36096, 3553, i, 0);
/* 922:922 */      GlUtil.f();
/* 923:    */    }
/* 924:    */    else {
/* 925:925 */      xi localxi2 = this;this.jdField_f_of_type_Int = c();j = localxi2.jdField_f_of_type_Int;GL30.glBindRenderbuffer(36161, j);m = localxi2.jdField_c_of_type_Int;k = localxi2.jdField_b_of_type_Int;localxi2.a(m, k, 6402);j = localxi2.jdField_f_of_type_Int;GL30.glFramebufferRenderbuffer(36160, 36096, 36161, j);GL30.glBindRenderbuffer(36161, 0);
/* 926:    */    }
/* 927:    */    
/* 928:928 */    GlUtil.f();
/* 929:929 */    if ((j = GL30.glCheckFramebufferStatus(36160)) != 36053) { String str = "unknown"; switch (j) {case 36061:  str = "GL_FRAMEBUFFER_UNSUPPORTED_EXn";break; case 36054:  str = "INCOMPLETE_ATTACHMENT";break; case 36055:  str = "FRAMEBUFFER_MISSING_ATTACHMENT";break; case 36182:  str = "GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE";break; case 36059:  str = "INCOMPLETE_DRAW_BUFFER";break; case 36060:  str = "INCOMPLETE_READ_BUFFER";break; case 36006:  str = "BINDING"; } throw new GLException("FrameBuffer Exception: " + str + ": (" + j + ")"); } GlUtil.f();
/* 930:930 */    GL30.glBindFramebuffer(36160, 0);
/* 931:    */    
/* 932:932 */    GL11.glBindTexture(3553, 0);
/* 933:933 */    this.jdField_d_of_type_Boolean = true;
/* 934:934 */    this.jdField_c_of_type_Boolean = false;
/* 935:    */    
/* 936:936 */    GlUtil.f();
/* 937:    */  }
/* 938:    */  
/* 965:    */  private void h()
/* 966:    */  {
/* 967:967 */    GlUtil.a(5889);
/* 968:968 */    GlUtil.d();
/* 969:969 */    GlUtil.b();
/* 970:    */    
/* 973:973 */    GlUtil.a(xm.b(), 0.0F, xm.a());
/* 974:974 */    GlUtil.a(5888);
/* 975:975 */    GlUtil.d();
/* 976:976 */    GlUtil.b();
/* 977:    */    
/* 979:979 */    GL11.glDisable(2896);
/* 980:980 */    GL11.glDisable(2929);
/* 981:    */    
/* 987:987 */    GL11.glEnable(3553);
/* 988:    */    
/* 989:989 */    GL13.glActiveTexture(33984);
/* 990:    */    
/* 991:991 */    GL11.glBindTexture(3553, a());
/* 992:    */    
/* 996:996 */    GL11.glDisable(2884);
/* 997:    */  }
/* 998:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     xi
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */