package org.newdawn.slick.tiled;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.util.Log;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Layer
{
  private static byte[] baseCodes = new byte[256];
  private final TiledMap map;
  public int index;
  public String name;
  public int[][][] data;
  public int width;
  public int height;
  public Properties props;
  
  public Layer(TiledMap map, Element element)
    throws SlickException
  {
    this.map = map;
    this.name = element.getAttribute("name");
    this.width = Integer.parseInt(element.getAttribute("width"));
    this.height = Integer.parseInt(element.getAttribute("height"));
    this.data = new int[this.width][this.height][3];
    Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
    if (propsElement != null)
    {
      NodeList properties = propsElement.getElementsByTagName("property");
      if (properties != null)
      {
        this.props = new Properties();
        for (int local_p = 0; local_p < properties.getLength(); local_p++)
        {
          Element propElement = (Element)properties.item(local_p);
          String name = propElement.getAttribute("name");
          String value = propElement.getAttribute("value");
          this.props.setProperty(name, value);
        }
      }
    }
    Element properties = (Element)element.getElementsByTagName("data").item(0);
    String local_p = properties.getAttribute("encoding");
    String propElement = properties.getAttribute("compression");
    if ((local_p.equals("base64")) && (propElement.equals("gzip"))) {
      try
      {
        Node name = properties.getFirstChild();
        char[] value = name.getNodeValue().trim().toCharArray();
        byte[] dec = decodeBase64(value);
        GZIPInputStream local_is = new GZIPInputStream(new ByteArrayInputStream(dec));
        for (int local_y = 0; local_y < this.height; local_y++) {
          for (int local_x = 0; local_x < this.width; local_x++)
          {
            int tileId = 0;
            tileId |= local_is.read();
            tileId |= local_is.read() << 8;
            tileId |= local_is.read() << 16;
            tileId |= local_is.read() << 24;
            if (tileId == 0)
            {
              this.data[local_x][local_y][0] = -1;
              this.data[local_x][local_y][1] = 0;
              this.data[local_x][local_y][2] = 0;
            }
            else
            {
              TileSet set = map.findTileSet(tileId);
              if (set != null)
              {
                this.data[local_x][local_y][0] = set.index;
                this.data[local_x][local_y][1] = (tileId - set.firstGID);
              }
              this.data[local_x][local_y][2] = tileId;
            }
          }
        }
      }
      catch (IOException name)
      {
        Log.error(name);
        throw new SlickException("Unable to decode base 64 block");
      }
    } else {
      throw new SlickException("Unsupport tiled map type: " + local_p + "," + propElement + " (only gzip base64 supported)");
    }
  }
  
  public int getTileID(int local_x, int local_y)
  {
    return this.data[local_x][local_y][2];
  }
  
  public void setTileID(int local_x, int local_y, int tile)
  {
    if (tile == 0)
    {
      this.data[local_x][local_y][0] = -1;
      this.data[local_x][local_y][1] = 0;
      this.data[local_x][local_y][2] = 0;
    }
    else
    {
      TileSet set = this.map.findTileSet(tile);
      this.data[local_x][local_y][0] = set.index;
      this.data[local_x][local_y][1] = (tile - set.firstGID);
      this.data[local_x][local_y][2] = tile;
    }
  }
  
  public void render(int local_x, int local_y, int local_sx, int local_sy, int width, int local_ty, boolean lineByLine, int mapTileWidth, int mapTileHeight)
  {
    for (int tileset = 0; tileset < this.map.getTileSetCount(); tileset++)
    {
      TileSet set = null;
      for (int local_tx = 0; local_tx < width; local_tx++) {
        if ((local_sx + local_tx >= 0) && (local_sy + local_ty >= 0) && (local_sx + local_tx < this.width) && (local_sy + local_ty < this.height) && (this.data[(local_sx + local_tx)][(local_sy + local_ty)][0] == tileset))
        {
          if (set == null)
          {
            set = this.map.getTileSet(tileset);
            set.tiles.startUse();
          }
          int sheetX = set.getTileX(this.data[(local_sx + local_tx)][(local_sy + local_ty)][1]);
          int sheetY = set.getTileY(this.data[(local_sx + local_tx)][(local_sy + local_ty)][1]);
          int tileOffsetY = set.tileHeight - mapTileHeight;
          set.tiles.renderInUse(local_x + local_tx * mapTileWidth, local_y + local_ty * mapTileHeight - tileOffsetY, sheetX, sheetY);
        }
      }
      if (lineByLine)
      {
        if (set != null)
        {
          set.tiles.endUse();
          set = null;
        }
        this.map.renderedLine(local_ty, local_ty + local_sy, this.index);
      }
      if (set != null) {
        set.tiles.endUse();
      }
    }
  }
  
  private byte[] decodeBase64(char[] data)
  {
    int temp = data.length;
    for (int local_ix = 0; local_ix < data.length; local_ix++) {
      if ((data[local_ix] > 'ÿ') || (baseCodes[data[local_ix]] < 0)) {
        temp--;
      }
    }
    int local_ix = temp / 4 * 3;
    if (temp % 4 == 3) {
      local_ix += 2;
    }
    if (temp % 4 == 2) {
      local_ix++;
    }
    byte[] out = new byte[local_ix];
    int shift = 0;
    int accum = 0;
    int index = 0;
    for (int local_ix1 = 0; local_ix1 < data.length; local_ix1++)
    {
      int value = data[local_ix1] > 'ÿ' ? -1 : baseCodes[data[local_ix1]];
      if (value >= 0)
      {
        accum <<= 6;
        shift += 6;
        accum |= value;
        if (shift >= 8)
        {
          shift -= 8;
          out[(index++)] = ((byte)(accum >> shift & 0xFF));
        }
      }
    }
    if (index != out.length) {
      throw new RuntimeException("Data length appears to be wrong (wrote " + index + " should be " + out.length + ")");
    }
    return out;
  }
  
  static
  {
    for (int local_i = 0; local_i < 256; local_i++) {
      baseCodes[local_i] = -1;
    }
    for (int local_i = 65; local_i <= 90; local_i++) {
      baseCodes[local_i] = ((byte)(local_i - 65));
    }
    for (int local_i = 97; local_i <= 122; local_i++) {
      baseCodes[local_i] = ((byte)(26 + local_i - 97));
    }
    for (int local_i = 48; local_i <= 57; local_i++) {
      baseCodes[local_i] = ((byte)(52 + local_i - 48));
    }
    baseCodes[43] = 62;
    baseCodes[47] = 63;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.tiled.Layer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */