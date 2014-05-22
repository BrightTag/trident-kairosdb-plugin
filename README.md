# Trident KairosDB Plugin

A [KairosDB plugin](https://code.google.com/p/kairosdb/wiki/Plugins) providing support
for storing Storm Trident transactional metadata in the value.

This plugin is designed to be used with one of the Kairos States from the
[trident-kairosdb](https://github.com/brighttag/trident-kairosdb) project.

The motivation for storing this metadata in the value instead of tags is because
(as of the time of this writing) KairosDB is poorly equipped to handle high-cardinality
tags. This is because each combination of tags results in a new column in the `row_key_index`
column family. When the number of columns in this index approaches Cassandra's limit (2 billion),
performances degrades drastically. Obviously, a monotonically-increasing `transactionId` has very
high cardinality and thus approaches this limit relatively quickly.

## Install

Unfortunately, until KairosDB is in Maven Central or another repository, there are a few
pre-build steps required for linking with KairosDB.

1. Clone KairosDB **in parent directory**: `git clone https://github.com/proofpoint/kairosdb.git`
2. Checkout branch or tag with custom_data support: `cd kairosdb && git checkout feature/custom_data`
3. Build the jar: `CLASSPATH=tools/tablesaw-1.2.0.jar java make`
4. Symlink the jar to a known name: `cd build/jar/ && ln -s kairosdb-0.9.4-SNAPSHOT.jar kairosdb.jar`.
   Make sure to **substitute the built version** in the `ln` command above.

If you have problems building the KairosDB jar, checkout the
[build instructions](https://github.com/proofpoint/kairosdb/blob/master/how_to_build.txt).

Now you can proceed to the regular install.

1. Build the code with Maven: `mvn clean install`
2. Copy `target/trident-kairosdb-plugin-0.1.0.jar` into `$KAIROSDB_HOME/lib`
3. Copy `src/main/resources/trident-kairosdb-plugin.properties` into `$KAIROSDB_HOME/conf`

## Usage

This plugin provides three [KairosDB custom_data types](https://groups.google.com/forum/#!topic/kairosdb-group/7cDxqmMMyzE):

* `trident_nontransactional` expects a `long` value
* `trident_transactional` expects a stringified JSON array of the format `"[txid, curr_value]"`
* `trident_opaque` expects a stringified JSON array of the format `"[txid, curr_value, prev_value]"`

The [Trident KairosState](https://github.com/brighttag/trident-kairosdb) will use the correct
type depending on the requested state type.

## Troubleshooting

If your aggregations always return 0, be sure that the JSON has the correct format
as described above.
