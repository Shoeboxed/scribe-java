package org.scribe.utils;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

public class URLUtilsTest
{
  @Test
  public void shouldPercentEncodeMap()
  {
    Map<String, String> params = new LinkedHashMap<String, String>();
    params.put("key", "value");
    params.put("key with spaces", "value with spaces");
    params.put("&symbols!", "#!");

    String expected = "key=value&key%20with%20spaces=value%20with%20spaces&%26symbols%21=%23%21";
    assertEquals(expected, URLUtils.formURLEncodeMap(params));
  }

  @Test
  public void shouldReturnEmptyStringForEmptyMap()
  {
    Map<String, String> params = new LinkedHashMap<String, String>();
    String expected = "";
    assertEquals(expected, URLUtils.formURLEncodeMap(params));
  }

  @Test
  public void shouldPercentEncodeString()
  {
    String toEncode = "this is a test &^";
    String expected = "this%20is%20a%20test%20%26%5E";
    assertEquals(expected, URLUtils.percentEncode(toEncode));
  }

  @Test
  public void shouldPercentDecodeString()
  {
    String toDecode = "this+is+a+test+%26%5E";
    String expected = "this is a test &^";
    assertEquals(expected, URLUtils.percentDecode(toDecode));
  }

  @Test
  public void shouldEncodeAllSpecialCharacters()
  {
    String plain = "!*'();:@&=+$,/?#[]";
    String encoded = "%21%2A%27%28%29%3B%3A%40%26%3D%2B%24%2C%2F%3F%23%5B%5D";
    assertEquals(encoded, URLUtils.percentEncode(plain));
    assertEquals(plain, URLUtils.percentDecode(encoded));
  }

  @Test
  public void shouldNotEncodeReservedCharacters()
  {
    String plain = "abcde123456-._~";
    String encoded = plain;
    assertEquals(encoded, URLUtils.percentEncode(plain));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfMapIsNull()
  {
    Map<String, String> nullMap = null;
    URLUtils.formURLEncodeMap(nullMap);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfStringToEncodeIsNull()
  {
    String toEncode = null;
    URLUtils.percentEncode(toEncode);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionIfStringToDecodeIsNull()
  {
    String toDecode = null;
    URLUtils.percentDecode(toDecode);
  }

}
