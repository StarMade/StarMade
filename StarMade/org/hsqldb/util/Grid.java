package org.hsqldb.util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.SystemColor;
import java.util.Vector;

class Grid
  extends Panel
{
  private Dimension dMinimum;
  protected Font fFont = new Font("Dialog", 0, 12);
  private FontMetrics fMetrics;
  private Graphics gImage;
  private Image iImage;
  private int iWidth;
  private int iHeight;
  private int iRowHeight;
  private int iFirstRow;
  private int iGridWidth;
  private int iGridHeight;
  private int field_1916;
  private int field_1917;
  protected String[] sColHead = new String[0];
  protected Vector vData = new Vector();
  private int[] iColWidth;
  private int iColCount;
  protected int iRowCount;
  private Scrollbar sbHoriz;
  private Scrollbar sbVert;
  private int iSbWidth;
  private int iSbHeight;
  private boolean bDrag;
  private int iXDrag;
  private int iColDrag;
  
  public Grid()
  {
    setLayout(null);
    this.sbHoriz = new Scrollbar(0);
    add(this.sbHoriz);
    this.sbVert = new Scrollbar(1);
    add(this.sbVert);
  }
  
  String[] getHead()
  {
    return this.sColHead;
  }
  
  Vector getData()
  {
    return this.vData;
  }
  
  public void setMinimumSize(Dimension paramDimension)
  {
    this.dMinimum = paramDimension;
  }
  
  public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.setBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    this.iSbHeight = this.sbHoriz.getPreferredSize().height;
    this.iSbWidth = this.sbVert.getPreferredSize().width;
    this.iHeight = (paramInt4 - this.iSbHeight);
    this.iWidth = (paramInt3 - this.iSbWidth);
    this.sbHoriz.setBounds(0, this.iHeight, this.iWidth, this.iSbHeight);
    this.sbVert.setBounds(this.iWidth, 0, this.iSbWidth, this.iHeight);
    adjustScroll();
    this.iImage = null;
    repaint();
  }
  
  public void setHead(String[] paramArrayOfString)
  {
    this.iColCount = paramArrayOfString.length;
    this.sColHead = new String[this.iColCount];
    this.iColWidth = new int[this.iColCount];
    for (int i = 0; i < this.iColCount; i++)
    {
      this.sColHead[i] = paramArrayOfString[i];
      this.iColWidth[i] = 100;
    }
    this.iRowCount = 0;
    this.iRowHeight = 0;
    this.vData = new Vector();
  }
  
  public void addRow(String[] paramArrayOfString)
  {
    if (paramArrayOfString.length != this.iColCount) {
      return;
    }
    String[] arrayOfString = new String[this.iColCount];
    for (int i = 0; i < this.iColCount; i++)
    {
      arrayOfString[i] = paramArrayOfString[i];
      if (arrayOfString[i] == null) {
        arrayOfString[i] = "(null)";
      }
    }
    this.vData.addElement(arrayOfString);
    this.iRowCount += 1;
  }
  
  public void update()
  {
    adjustScroll();
    repaint();
  }
  
  void adjustScroll()
  {
    if (this.iRowHeight == 0) {
      return;
    }
    int i = 0;
    for (int j = 0; j < this.iColCount; j++) {
      i += this.iColWidth[j];
    }
    this.iGridWidth = i;
    this.iGridHeight = (this.iRowHeight * (this.iRowCount + 1));
    this.sbHoriz.setValues(this.field_1916, this.iWidth, 0, this.iGridWidth);
    j = this.field_1917 / this.iRowHeight;
    int k = this.iHeight / this.iRowHeight;
    this.sbVert.setValues(j, k, 0, this.iRowCount + 1);
    this.field_1916 = this.sbHoriz.getValue();
    this.field_1917 = (this.iRowHeight * this.sbVert.getValue());
  }
  
  public boolean handleEvent(Event paramEvent)
  {
    switch (paramEvent.id)
    {
    case 601: 
    case 602: 
    case 603: 
    case 604: 
    case 605: 
      this.field_1916 = this.sbHoriz.getValue();
      this.field_1917 = (this.iRowHeight * this.sbVert.getValue());
      repaint();
      return true;
    }
    return super.handleEvent(paramEvent);
  }
  
  public void paint(Graphics paramGraphics)
  {
    if (paramGraphics == null) {
      return;
    }
    if (this.sColHead.length == 0)
    {
      super.paint(paramGraphics);
      return;
    }
    if ((this.iWidth <= 0) || (this.iHeight <= 0)) {
      return;
    }
    paramGraphics.setColor(SystemColor.control);
    paramGraphics.fillRect(this.iWidth, this.iHeight, this.iSbWidth, this.iSbHeight);
    if (this.iImage == null)
    {
      this.iImage = createImage(this.iWidth, this.iHeight);
      this.gImage = this.iImage.getGraphics();
      this.gImage.setFont(this.fFont);
      if (this.fMetrics == null) {
        this.fMetrics = this.gImage.getFontMetrics();
      }
    }
    if (this.iRowHeight == 0)
    {
      this.iRowHeight = getMaxHeight(this.fMetrics);
      for (i = 0; i < this.iColCount; i++) {
        calcAutoWidth(i);
      }
      adjustScroll();
    }
    this.gImage.setColor(Color.white);
    this.gImage.fillRect(0, 0, this.iWidth, this.iHeight);
    this.gImage.setColor(Color.darkGray);
    this.gImage.drawLine(0, this.iRowHeight, this.iWidth, this.iRowHeight);
    int i = -this.field_1916;
    for (int j = 0; j < this.iColCount; j++)
    {
      k = this.iColWidth[j];
      this.gImage.setColor(SystemColor.control);
      this.gImage.fillRect(i + 1, 0, k - 2, this.iRowHeight);
      this.gImage.setColor(Color.black);
      this.gImage.drawString(this.sColHead[j], i + 2, this.iRowHeight - 5);
      this.gImage.setColor(Color.darkGray);
      this.gImage.drawLine(i + k - 1, 0, i + k - 1, this.iRowHeight - 1);
      this.gImage.setColor(Color.white);
      this.gImage.drawLine(i + k, 0, i + k, this.iRowHeight - 1);
      i += k;
    }
    this.gImage.setColor(SystemColor.control);
    this.gImage.fillRect(0, 0, 1, this.iRowHeight);
    this.gImage.fillRect(i + 1, 0, this.iWidth - i, this.iRowHeight);
    this.gImage.drawLine(0, 0, 0, this.iRowHeight - 1);
    j = this.iRowHeight + 1 - this.field_1917;
    int k = 0;
    while (j < this.iRowHeight + 1)
    {
      k++;
      j += this.iRowHeight;
    }
    this.iFirstRow = k;
    j = this.iRowHeight + 1;
    while ((j < this.iHeight) && (k < this.iRowCount))
    {
      i = -this.field_1916;
      for (int m = 0; m < this.iColCount; m++)
      {
        int n = this.iColWidth[m];
        Color localColor1 = Color.white;
        Color localColor2 = Color.black;
        this.gImage.setColor(localColor1);
        this.gImage.fillRect(i, j, n - 1, this.iRowHeight - 1);
        this.gImage.setColor(localColor2);
        this.gImage.drawString(getDisplay(m, k), i + 2, j + this.iRowHeight - 5);
        this.gImage.setColor(Color.lightGray);
        this.gImage.drawLine(i + n - 1, j, i + n - 1, j + this.iRowHeight - 1);
        this.gImage.drawLine(i, j + this.iRowHeight - 1, i + n - 1, j + this.iRowHeight - 1);
        i += n;
      }
      this.gImage.setColor(Color.white);
      this.gImage.fillRect(i, j, this.iWidth - i, this.iRowHeight - 1);
      k++;
      j += this.iRowHeight;
    }
    paramGraphics.drawImage(this.iImage, 0, 0, this);
  }
  
  public void update(Graphics paramGraphics)
  {
    paint(paramGraphics);
  }
  
  public boolean mouseMove(Event paramEvent, int paramInt1, int paramInt2)
  {
    if (paramInt2 <= this.iRowHeight)
    {
      int i = paramInt1;
      paramInt1 += this.field_1916 - this.iGridWidth;
      for (int j = this.iColCount - 1; (j >= 0) && ((paramInt1 <= -7) || (paramInt1 >= 7)); j--) {
        paramInt1 += this.iColWidth[j];
      }
      if (j >= 0)
      {
        if (!this.bDrag)
        {
          setCursor(new Cursor(11));
          this.bDrag = true;
          this.iXDrag = (i - this.iColWidth[j]);
          this.iColDrag = j;
        }
        return true;
      }
    }
    return mouseExit(paramEvent, paramInt1, paramInt2);
  }
  
  public boolean mouseDrag(Event paramEvent, int paramInt1, int paramInt2)
  {
    if ((this.bDrag) && (paramInt1 < this.iWidth))
    {
      int i = paramInt1 - this.iXDrag;
      if (i < 0) {
        i = 0;
      }
      this.iColWidth[this.iColDrag] = i;
      adjustScroll();
      repaint();
    }
    return true;
  }
  
  public boolean mouseExit(Event paramEvent, int paramInt1, int paramInt2)
  {
    if (this.bDrag)
    {
      setCursor(new Cursor(0));
      this.bDrag = false;
    }
    return true;
  }
  
  public Dimension preferredSize()
  {
    return this.dMinimum;
  }
  
  public Dimension getPreferredSize()
  {
    return this.dMinimum;
  }
  
  public Dimension getMinimumSize()
  {
    return this.dMinimum;
  }
  
  public Dimension minimumSize()
  {
    return this.dMinimum;
  }
  
  private void calcAutoWidth(int paramInt)
  {
    int i = 10;
    i = Math.max(i, this.fMetrics.stringWidth(this.sColHead[paramInt]));
    for (int j = 0; j < this.iRowCount; j++)
    {
      String[] arrayOfString = (String[])this.vData.elementAt(j);
      i = Math.max(i, this.fMetrics.stringWidth(arrayOfString[paramInt]));
    }
    this.iColWidth[paramInt] = (i + 6);
  }
  
  private String getDisplay(int paramInt1, int paramInt2)
  {
    return ((String[])(String[])this.vData.elementAt(paramInt2))[paramInt1];
  }
  
  private String get(int paramInt1, int paramInt2)
  {
    return ((String[])(String[])this.vData.elementAt(paramInt2))[paramInt1];
  }
  
  private static int getMaxHeight(FontMetrics paramFontMetrics)
  {
    return paramFontMetrics.getHeight() + 4;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.util.Grid
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */