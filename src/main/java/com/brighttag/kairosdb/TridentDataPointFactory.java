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

import java.io.DataInput;
import java.io.IOException;

import com.google.gson.JsonElement;

import org.kairosdb.core.DataPoint;
import org.kairosdb.core.datapoints.DataPointFactory;

import static org.kairosdb.core.DataPoint.GROUP_NUMBER;

/**
 * @author codyaray
 * @since 5/22/14
 */
public abstract class TridentDataPointFactory implements DataPointFactory {
  private final String dataStoreType;

  public TridentDataPointFactory(String dataStoreType) {
    this.dataStoreType = dataStoreType;
  }

  @Override
  public String getDataStoreType() {
    return dataStoreType;
  }

  @Override
  public String getGroupType() {
    return GROUP_NUMBER;
  }

  @Override
  public DataPoint getDataPoint(long timestamp, JsonElement json) throws IOException {
    return factory(timestamp, json.getAsString());
  }

  @Override
  public DataPoint getDataPoint(long timestamp, DataInput buffer) throws IOException {
    return factory(timestamp, buffer.readUTF());
  }

  protected abstract DataPoint factory(long timestamp, String value);

}
