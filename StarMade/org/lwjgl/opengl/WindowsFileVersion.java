package org.lwjgl.opengl;

final class WindowsFileVersion
{
  private final int product_version_ms;
  private final int product_version_ls;
  
  WindowsFileVersion(int product_version_ms, int product_version_ls)
  {
    this.product_version_ms = product_version_ms;
    this.product_version_ls = product_version_ls;
  }
  
  public String toString()
  {
    int local_f1 = this.product_version_ms >> 16 & 0xFFFF;
    int local_f2 = this.product_version_ms & 0xFFFF;
    int local_f3 = this.product_version_ls >> 16 & 0xFFFF;
    int local_f4 = this.product_version_ls & 0xFFFF;
    return local_f1 + "." + local_f2 + "." + local_f3 + "." + local_f4;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.WindowsFileVersion
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */