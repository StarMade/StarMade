package org.jasypt.contrib.org.apache.commons.codec_1_3.binary;

import org.jasypt.contrib.org.apache.commons.codec_1_3.BinaryDecoder;
import org.jasypt.contrib.org.apache.commons.codec_1_3.BinaryEncoder;
import org.jasypt.contrib.org.apache.commons.codec_1_3.DecoderException;
import org.jasypt.contrib.org.apache.commons.codec_1_3.EncoderException;

public class Base64
  implements BinaryEncoder, BinaryDecoder
{
  static final int CHUNK_SIZE = 76;
  static final byte[] CHUNK_SEPARATOR = "\r\n".getBytes();
  static final int BASELENGTH = 255;
  static final int LOOKUPLENGTH = 64;
  static final int EIGHTBIT = 8;
  static final int SIXTEENBIT = 16;
  static final int TWENTYFOURBITGROUP = 24;
  static final int FOURBYTE = 4;
  static final int SIGN = -128;
  static final byte PAD = 61;
  private static byte[] base64Alphabet = new byte['ÿ'];
  private static byte[] lookUpBase64Alphabet = new byte[64];
  
  private static boolean isBase64(byte octect)
  {
    if (octect == 61) {
      return true;
    }
    return base64Alphabet[octect] != -1;
  }
  
  public static boolean isArrayByteBase64(byte[] arrayOctect)
  {
    arrayOctect = discardWhitespace(arrayOctect);
    int length = arrayOctect.length;
    if (length == 0) {
      return true;
    }
    for (int local_i = 0; local_i < length; local_i++) {
      if (!isBase64(arrayOctect[local_i])) {
        return false;
      }
    }
    return true;
  }
  
  public static byte[] encodeBase64(byte[] binaryData)
  {
    return encodeBase64(binaryData, false);
  }
  
  public static byte[] encodeBase64Chunked(byte[] binaryData)
  {
    return encodeBase64(binaryData, true);
  }
  
  public Object decode(Object pObject)
    throws DecoderException
  {
    if (!(pObject instanceof byte[])) {
      throw new DecoderException("Parameter supplied to Base64 decode is not a byte[]");
    }
    return decode((byte[])pObject);
  }
  
  public byte[] decode(byte[] pArray)
  {
    return decodeBase64(pArray);
  }
  
  public static byte[] encodeBase64(byte[] binaryData, boolean isChunked)
  {
    int lengthDataBits = binaryData.length * 8;
    int fewerThan24bits = lengthDataBits % 24;
    int numberTriplets = lengthDataBits / 24;
    byte[] encodedData = null;
    int encodedDataLength = 0;
    int nbrChunks = 0;
    if (fewerThan24bits != 0) {
      encodedDataLength = (numberTriplets + 1) * 4;
    } else {
      encodedDataLength = numberTriplets * 4;
    }
    if (isChunked)
    {
      nbrChunks = CHUNK_SEPARATOR.length == 0 ? 0 : (int)Math.ceil(encodedDataLength / 76.0F);
      encodedDataLength += nbrChunks * CHUNK_SEPARATOR.length;
    }
    encodedData = new byte[encodedDataLength];
    byte local_k = 0;
    byte local_l = 0;
    byte local_b1 = 0;
    byte local_b2 = 0;
    byte local_b3 = 0;
    int encodedIndex = 0;
    int dataIndex = 0;
    int local_i = 0;
    int nextSeparatorIndex = 76;
    int chunksSoFar = 0;
    for (local_i = 0; local_i < numberTriplets; local_i++)
    {
      dataIndex = local_i * 3;
      local_b1 = binaryData[dataIndex];
      local_b2 = binaryData[(dataIndex + 1)];
      local_b3 = binaryData[(dataIndex + 2)];
      local_l = (byte)(local_b2 & 0xF);
      local_k = (byte)(local_b1 & 0x3);
      byte val1 = (local_b1 & 0xFFFFFF80) == 0 ? (byte)(local_b1 >> 2) : (byte)(local_b1 >> 2 ^ 0xC0);
      byte val2 = (local_b2 & 0xFFFFFF80) == 0 ? (byte)(local_b2 >> 4) : (byte)(local_b2 >> 4 ^ 0xF0);
      byte val3 = (local_b3 & 0xFFFFFF80) == 0 ? (byte)(local_b3 >> 6) : (byte)(local_b3 >> 6 ^ 0xFC);
      encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
      encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(val2 | local_k << 4)];
      encodedData[(encodedIndex + 2)] = lookUpBase64Alphabet[(local_l << 2 | val3)];
      encodedData[(encodedIndex + 3)] = lookUpBase64Alphabet[(local_b3 & 0x3F)];
      encodedIndex += 4;
      if ((isChunked) && (encodedIndex == nextSeparatorIndex))
      {
        System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedIndex, CHUNK_SEPARATOR.length);
        chunksSoFar++;
        nextSeparatorIndex = 76 * (chunksSoFar + 1) + chunksSoFar * CHUNK_SEPARATOR.length;
        encodedIndex += CHUNK_SEPARATOR.length;
      }
    }
    dataIndex = local_i * 3;
    if (fewerThan24bits == 8)
    {
      local_b1 = binaryData[dataIndex];
      local_k = (byte)(local_b1 & 0x3);
      byte val1 = (local_b1 & 0xFFFFFF80) == 0 ? (byte)(local_b1 >> 2) : (byte)(local_b1 >> 2 ^ 0xC0);
      encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
      encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(local_k << 4)];
      encodedData[(encodedIndex + 2)] = 61;
      encodedData[(encodedIndex + 3)] = 61;
    }
    else if (fewerThan24bits == 16)
    {
      local_b1 = binaryData[dataIndex];
      local_b2 = binaryData[(dataIndex + 1)];
      local_l = (byte)(local_b2 & 0xF);
      local_k = (byte)(local_b1 & 0x3);
      byte val1 = (local_b1 & 0xFFFFFF80) == 0 ? (byte)(local_b1 >> 2) : (byte)(local_b1 >> 2 ^ 0xC0);
      byte val2 = (local_b2 & 0xFFFFFF80) == 0 ? (byte)(local_b2 >> 4) : (byte)(local_b2 >> 4 ^ 0xF0);
      encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
      encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(val2 | local_k << 4)];
      encodedData[(encodedIndex + 2)] = lookUpBase64Alphabet[(local_l << 2)];
      encodedData[(encodedIndex + 3)] = 61;
    }
    if ((isChunked) && (chunksSoFar < nbrChunks)) {
      System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedDataLength - CHUNK_SEPARATOR.length, CHUNK_SEPARATOR.length);
    }
    return encodedData;
  }
  
  public static byte[] decodeBase64(byte[] base64Data)
  {
    base64Data = discardNonBase64(base64Data);
    if (base64Data.length == 0) {
      return new byte[0];
    }
    int numberQuadruple = base64Data.length / 4;
    byte[] decodedData = null;
    byte local_b1 = 0;
    byte local_b2 = 0;
    byte local_b3 = 0;
    byte local_b4 = 0;
    byte marker0 = 0;
    byte marker1 = 0;
    int encodedIndex = 0;
    int dataIndex = 0;
    int lastData = base64Data.length;
    while (base64Data[(lastData - 1)] == 61)
    {
      lastData--;
      if (lastData == 0) {
        return new byte[0];
      }
    }
    decodedData = new byte[lastData - numberQuadruple];
    for (int lastData = 0; lastData < numberQuadruple; lastData++)
    {
      dataIndex = lastData * 4;
      marker0 = base64Data[(dataIndex + 2)];
      marker1 = base64Data[(dataIndex + 3)];
      local_b1 = base64Alphabet[base64Data[dataIndex]];
      local_b2 = base64Alphabet[base64Data[(dataIndex + 1)]];
      if ((marker0 != 61) && (marker1 != 61))
      {
        local_b3 = base64Alphabet[marker0];
        local_b4 = base64Alphabet[marker1];
        decodedData[encodedIndex] = ((byte)(local_b1 << 2 | local_b2 >> 4));
        decodedData[(encodedIndex + 1)] = ((byte)((local_b2 & 0xF) << 4 | local_b3 >> 2 & 0xF));
        decodedData[(encodedIndex + 2)] = ((byte)(local_b3 << 6 | local_b4));
      }
      else if (marker0 == 61)
      {
        decodedData[encodedIndex] = ((byte)(local_b1 << 2 | local_b2 >> 4));
      }
      else if (marker1 == 61)
      {
        local_b3 = base64Alphabet[marker0];
        decodedData[encodedIndex] = ((byte)(local_b1 << 2 | local_b2 >> 4));
        decodedData[(encodedIndex + 1)] = ((byte)((local_b2 & 0xF) << 4 | local_b3 >> 2 & 0xF));
      }
      encodedIndex += 3;
    }
    return decodedData;
  }
  
  static byte[] discardWhitespace(byte[] data)
  {
    byte[] groomedData = new byte[data.length];
    int bytesCopied = 0;
    for (int local_i = 0; local_i < data.length; local_i++) {
      switch (data[local_i])
      {
      case 9: 
      case 10: 
      case 13: 
      case 32: 
        break;
      default: 
        groomedData[(bytesCopied++)] = data[local_i];
      }
    }
    byte[] local_i = new byte[bytesCopied];
    System.arraycopy(groomedData, 0, local_i, 0, bytesCopied);
    return local_i;
  }
  
  static byte[] discardNonBase64(byte[] data)
  {
    byte[] groomedData = new byte[data.length];
    int bytesCopied = 0;
    for (int local_i = 0; local_i < data.length; local_i++) {
      if (isBase64(data[local_i])) {
        groomedData[(bytesCopied++)] = data[local_i];
      }
    }
    byte[] local_i = new byte[bytesCopied];
    System.arraycopy(groomedData, 0, local_i, 0, bytesCopied);
    return local_i;
  }
  
  public Object encode(Object pObject)
    throws EncoderException
  {
    if (!(pObject instanceof byte[])) {
      throw new EncoderException("Parameter supplied to Base64 encode is not a byte[]");
    }
    return encode((byte[])pObject);
  }
  
  public byte[] encode(byte[] pArray)
  {
    return encodeBase64(pArray, false);
  }
  
  static
  {
    for (int local_i = 0; local_i < 255; local_i++) {
      base64Alphabet[local_i] = -1;
    }
    for (int local_i = 90; local_i >= 65; local_i--) {
      base64Alphabet[local_i] = ((byte)(local_i - 65));
    }
    for (int local_i = 122; local_i >= 97; local_i--) {
      base64Alphabet[local_i] = ((byte)(local_i - 97 + 26));
    }
    for (int local_i = 57; local_i >= 48; local_i--) {
      base64Alphabet[local_i] = ((byte)(local_i - 48 + 52));
    }
    base64Alphabet[43] = 62;
    base64Alphabet[47] = 63;
    for (int local_i = 0; local_i <= 25; local_i++) {
      lookUpBase64Alphabet[local_i] = ((byte)(65 + local_i));
    }
    int local_i = 26;
    for (int local_j = 0; local_i <= 51; local_j++)
    {
      lookUpBase64Alphabet[local_i] = ((byte)(97 + local_j));
      local_i++;
    }
    int local_i = 52;
    for (int local_j = 0; local_i <= 61; local_j++)
    {
      lookUpBase64Alphabet[local_i] = ((byte)(48 + local_j));
      local_i++;
    }
    lookUpBase64Alphabet[62] = 43;
    lookUpBase64Alphabet[63] = 47;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */