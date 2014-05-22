// Copyright 2014 BrightTag, Inc. All rights reserved.
package com.brighttag.kairosdb;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author codyaray
 * @since 5/22/14
 */
public class TridentDataPointTest {

  @Test
  public void parseJsonArray() throws Exception {
    TridentDataPoint datapoint = new TestTridentDataPoint(123, "[9, 123, 117]");
    String[] parts = datapoint.parseJsonArray();
    assertEquals("9",   parts[0]);
    assertEquals("123", parts[1]);
    assertEquals("117", parts[2]);
  }

  @Test
  public void parseJsonArray_notArray() throws Exception {
    TridentDataPoint datapoint = new TestTridentDataPoint(123, "9");
    assertNull(datapoint.parseJsonArray());
  }

  private class TestTridentDataPoint extends TridentDataPoint {
    public TestTridentDataPoint(long timestamp, String value) {
      super(timestamp, value);
    }

    @Override
    protected long getCurrentValue() {
      return 0;
    }

    @Override
    public String getApiDataType() {
      return null;
    }

    @Override
    public String getDataStoreDataType() {
      return null;
    }
  }
}
