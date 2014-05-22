// Copyright 2014 BrightTag, Inc. All rights reserved.
package com.brighttag.kairosdb;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author codyaray
 * @since 5/22/14
 */
public class OpaqueTridentDataPointTest {

  @Test
  public void getCurrentValue() throws Exception {
    OpaqueTridentDataPoint datapoint = new OpaqueTridentDataPoint(123, "[9, 123, 117]");
    assertEquals(123, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_notArray() throws Exception {
    OpaqueTridentDataPoint datapoint = new OpaqueTridentDataPoint(123, "9");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_arrayTooShort() throws Exception {
    OpaqueTridentDataPoint datapoint = new OpaqueTridentDataPoint(123, "[9, 123]");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_arrayTooLong() throws Exception {
    OpaqueTridentDataPoint datapoint = new OpaqueTridentDataPoint(123, "[9, 123, 117, 109]");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_currentFloat() throws Exception {
    OpaqueTridentDataPoint datapoint = new OpaqueTridentDataPoint(123, "[9, 123.2, 117]");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_currentNotNumber() throws Exception {
    OpaqueTridentDataPoint datapoint = new OpaqueTridentDataPoint(123, "[9, cowbell, 117]");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_txidNotNumber() throws Exception {
    OpaqueTridentDataPoint datapoint = new OpaqueTridentDataPoint(123, "[bellhop, 123, 117]");
    assertEquals(123, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_previousNotNumber() throws Exception {
    OpaqueTridentDataPoint datapoint = new OpaqueTridentDataPoint(123, "[9, 123, dumbbell]");
    assertEquals(123, datapoint.getCurrentValue());
  }

}
