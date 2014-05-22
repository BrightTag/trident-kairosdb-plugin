// Copyright 2014 BrightTag, Inc. All rights reserved.
package com.brighttag.kairosdb;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author codyaray
 * @since 5/22/14
 */
public class TransactionalTridentDataPointTest {

  @Test
  public void getCurrentValue() throws Exception {
    TransactionalTridentDataPoint datapoint = new TransactionalTridentDataPoint(123, "[9, 123]");
    assertEquals(123, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_notArray() throws Exception {
    TransactionalTridentDataPoint datapoint = new TransactionalTridentDataPoint(123, "9");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_arrayTooShort() throws Exception {
    TransactionalTridentDataPoint datapoint = new TransactionalTridentDataPoint(123, "[9]");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_arrayTooLong() throws Exception {
    TransactionalTridentDataPoint datapoint = new TransactionalTridentDataPoint(123, "[9, 123, 117]");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_currentFloat() throws Exception {
    TransactionalTridentDataPoint datapoint = new TransactionalTridentDataPoint(123, "[9, 123.2]");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_currentNotNumber() throws Exception {
    TransactionalTridentDataPoint datapoint = new TransactionalTridentDataPoint(123, "[9, cowbell]");
    assertEquals(0, datapoint.getCurrentValue());
  }

  @Test
  public void getCurrentValue_txidNotNumber() throws Exception {
    TransactionalTridentDataPoint datapoint = new TransactionalTridentDataPoint(123, "[bellhop, 123]");
    assertEquals(123, datapoint.getCurrentValue());
  }

}
