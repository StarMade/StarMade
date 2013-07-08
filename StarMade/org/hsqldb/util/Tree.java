package org.hsqldb.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.Vector;

class Tree
  extends Panel
{
  private static Font fFont = new Font("Dialog", 0, 12);
  private static FontMetrics fMetrics = Toolkit.getDefaultToolkit().getFontMetrics(fFont);
  private static int iRowHeight = getMaxHeight(fMetrics);
  private static int iIndentWidth = 12;
  private int iMaxTextLength;
  private Dimension dMinimum;
  private Graphics gImage;
  private Image iImage;
  private int iWidth;
  private int iHeight;
  private int iFirstRow;
  private int iTreeWidth;
  private int iTreeHeight;
  private int iX;
  private int iY;
  private Vector vData = new Vector();
  private int iRowCount;
  private Scrollbar sbHoriz;
  private Scrollbar sbVert;
  private int iSbWidth;
  private int iSbHeight;
  
  Tree()
  {
    setLayout(null);
    this.sbHoriz = new Scrollbar(0);
    add(this.sbHoriz);
    this.sbVert = new Scrollbar(1);
    add(this.sbVert);
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
  
  public void removeAll()
  {
    this.vData = new Vector();
    this.iRowCount = 0;
    adjustScroll();
    this.iMaxTextLength = 10;
    repaint();
  }
  
  public void addRow(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    String[] arrayOfString = new String[4];
    if (paramString2 == null) {
      paramString2 = "";
    }
    arrayOfString[0] = paramString1;
    arrayOfString[1] = paramString2;
    arrayOfString[2] = paramString3;
    arrayOfString[3] = String.valueOf(paramInt);
    this.vData.addElement(arrayOfString);
    int i = fMetrics.stringWidth(paramString2);
    if (i > this.iMaxTextLength) {
      this.iMaxTextLength = i;
    }
    this.iRowCount += 1;
  }
  
  public void addRow(String paramString1, String paramString2)
  {
    addRow(paramString1, paramString2, null, 0);
  }
  
  public void update()
  {
    adjustScroll();
    repaint();
  }
  
  void adjustScroll()
  {
    this.iTreeHeight = (iRowHeight * (this.iRowCount + 1));
    this.iTreeWidth = (this.iMaxTextLength * 2);
    this.sbHoriz.setValues(this.iX, this.iWidth, 0, this.iTreeWidth);
    int i = this.iY / iRowHeight;
    int j = this.iHeight / iRowHeight;
    this.sbVert.setValues(i, j, 0, this.iRowCount + 1);
    this.iX = this.sbHoriz.getValue();
    this.iY = (iRowHeight * this.sbVert.getValue());
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
      this.iX = this.sbHoriz.getValue();
      this.iY = (iRowHeight * this.sbVert.getValue());
      repaint();
      return true;
    }
    return super.handleEvent(paramEvent);
  }
  
  public void paint(Graphics paramGraphics)
  {
    if ((paramGraphics == null) || (this.iWidth <= 0) || (this.iHeight <= 0)) {
      return;
    }
    paramGraphics.setColor(SystemColor.control);
    paramGraphics.fillRect(this.iWidth, this.iHeight, this.iSbWidth, this.iSbHeight);
    if (this.iImage == null)
    {
      this.iImage = createImage(this.iWidth, this.iHeight);
      this.gImage = this.iImage.getGraphics();
      this.gImage.setFont(fFont);
    }
    this.gImage.setColor(Color.white);
    this.gImage.fillRect(0, 0, this.iWidth, this.iHeight);
    int[] arrayOfInt = new int[100];
    String[] arrayOfString1 = new String[100];
    arrayOfString1[0] = "";
    int i = 0;
    int j = iRowHeight;
    j -= this.iY;
    int k = 0;
    for (int m = 0; m < this.iRowCount; m++)
    {
      String[] arrayOfString2 = (String[])this.vData.elementAt(m);
      String str1 = arrayOfString2[0];
      String str2 = arrayOfString2[1];
      String str3 = arrayOfString2[2];
      for (int n = i; (n > 0) && (!str1.startsWith(arrayOfString1[n])); n--) {}
      if (arrayOfString1[n].length() < str1.length()) {
        n++;
      }
      if ((k == 0) || (n <= i))
      {
        k = (str3 != null) && (str3.equals("+")) ? 1 : 0;
        arrayOfString1[n] = str1;
        int i1 = iIndentWidth * n - this.iX;
        this.gImage.setColor(Color.lightGray);
        this.gImage.drawLine(i1, j, i1 + iIndentWidth, j);
        this.gImage.drawLine(i1, j, i1, arrayOfInt[n]);
        arrayOfInt[(n + 1)] = j;
        int i2 = j + iRowHeight / 3;
        int i3 = i1 + iIndentWidth * 2;
        if (str3 != null)
        {
          arrayOfInt[(n + 1)] += 4;
          int i4 = Integer.parseInt(arrayOfString2[3]);
          this.gImage.setColor(i4 == 0 ? Color.white : new Color(i4));
          this.gImage.fillRect(i1 + iIndentWidth - 3, j - 3, 7, 7);
          this.gImage.setColor(Color.black);
          this.gImage.drawRect(i1 + iIndentWidth - 4, j - 4, 8, 8);
          this.gImage.drawLine(i1 + iIndentWidth - 2, j, i1 + iIndentWidth + 2, j);
          if (str3.equals("+")) {
            this.gImage.drawLine(i1 + iIndentWidth, j - 2, i1 + iIndentWidth, j + 2);
          }
        }
        else
        {
          i3 -= iIndentWidth;
        }
        this.gImage.setColor(Color.black);
        this.gImage.drawString(str2, i3, i2);
        i = n;
        j += iRowHeight;
      }
    }
    paramGraphics.drawImage(this.iImage, 0, 0, this);
  }
  
  public void update(Graphics paramGraphics)
  {
    paint(paramGraphics);
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
  
  public boolean mouseDown(Event paramEvent, int paramInt1, int paramInt2)
  {
    if ((iRowHeight == 0) || (paramInt1 > this.iWidth) || (paramInt2 > this.iHeight)) {
      return true;
    }
    paramInt2 += iRowHeight / 2;
    String[] arrayOfString1 = new String[100];
    arrayOfString1[0] = "";
    int i = 0;
    int j = iRowHeight;
    int k = 0;
    int m = 0;
    paramInt2 += this.iY;
    String[] arrayOfString2;
    String str1;
    while (m < this.iRowCount)
    {
      arrayOfString2 = (String[])this.vData.elementAt(m);
      str1 = arrayOfString2[0];
      String str2 = arrayOfString2[2];
      for (int n = i; (n > 0) && (!str1.startsWith(arrayOfString1[n])); n--) {}
      if (arrayOfString1[n].length() < str1.length()) {
        n++;
      }
      if ((k == 0) || (n <= i))
      {
        if ((j <= paramInt2) && (j + iRowHeight > paramInt2)) {
          break;
        }
        arrayOfString1[n] = str1;
        k = (str2 != null) && (str2.equals("+")) ? 1 : 0;
        i = n;
        j += iRowHeight;
      }
      m++;
    }
    if ((m >= 0) && (m < this.iRowCount))
    {
      arrayOfString2 = (String[])this.vData.elementAt(m);
      str1 = arrayOfString2[2];
      if ((str1 != null) && (str1.equals("+"))) {
        str1 = "-";
      } else if ((str1 != null) && (str1.equals("-"))) {
        str1 = "+";
      }
      arrayOfString2[2] = str1;
      this.vData.setElementAt(arrayOfString2, m);
      repaint();
    }
    return true;
  }
  
  private static int getMaxHeight(FontMetrics paramFontMetrics)
  {
    return paramFontMetrics.getHeight() + 2;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.util.Tree
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */