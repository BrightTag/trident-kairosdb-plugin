/*
 * Copyright 2014 BrightTag, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.brighttag.kairosdb;

import org.kairosdb.core.DataPoint;

/**
 * @author codyaray
 * @since 3/9/2013
 */
public class OpaqueTridentDataPointFactory extends TridentDataPointFactory {

  public static final String DST_TRIDENT_OPAQUE = "kairos_trident_opaque";

  public OpaqueTridentDataPointFactory() {
    super(DST_TRIDENT_OPAQUE);
  }

  @Override
  protected DataPoint factory(long timestamp, String value) {
    return new OpaqueTridentDataPoint(timestamp, value);
  }
}
