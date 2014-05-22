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

import java.io.DataOutput;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONWriter;
import org.kairosdb.core.datapoints.DataPointHelper;

/**
 * @author codyaray
 * @since 5/21/14
 */
public abstract class TridentDataPoint extends DataPointHelper {
  private final Gson gson = new Gson();

  protected final String value;

  public TridentDataPoint(long timestamp, String value) {
    super(timestamp);
    this.value = value;
  }

  @Override
  public void writeValueToBuffer(DataOutput buffer) throws IOException {
    buffer.writeUTF(value);
  }

  @Override
  public void writeValueToJson(JSONWriter writer) throws JSONException {
    writer.value(value);
  }

  @Override
  public boolean isLong() {
    return true;
  }

  @Override
  public long getLongValue() {
    return getCurrentValue();
  }

  @Override
  public boolean isDouble() {
    return true;
  }

  @Override
  public double getDoubleValue() {
    return (double) getCurrentValue();
  }

  protected abstract long getCurrentValue();

  protected @Nullable String[] parseJsonArray() {
    Type listType = new TypeToken<List<String>>() {}.getType();
    try {
      List<String> parts = gson.fromJson(value, listType);
      return Iterables.toArray(parts, String.class);
    } catch (JsonSyntaxException e) {
      return null;
    }
  }
}
