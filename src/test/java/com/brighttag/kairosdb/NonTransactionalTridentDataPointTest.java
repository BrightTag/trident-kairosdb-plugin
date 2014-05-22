// Copyright 2014 BrightTag, Inc. All rights reserved.
package com.brighttag.kairosdb;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author codyaray
 * @since 5/22/14
 */
public class NonTransactionalTridentDataPointTest {

  @Test
  public void getCurrentValue() throws Exception {
    NonTransactionalTridentDataPoint datapoint = new NonTransactionalTridentDataPoint(123, "123");
    assertEquals(123, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_array() throws Exception {
    NonTransactionalTridentDataPoint datapoint = new NonTransactionalTridentDataPoint(123, "[9]");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_currentFloat() throws Exception {
    NonTransactionalTridentDataPoint datapoint = new NonTransactionalTridentDataPoint(123, "123.2");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_currentNotNumber() throws Exception {
    NonTransactionalTridentDataPoint datapoint = new NonTransactionalTridentDataPoint(123, "cowbell");
    assertEquals(0, datapoint.getCurrentValue());
  }

}
