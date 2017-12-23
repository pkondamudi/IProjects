CREATE TABLE "ApplicationAttributes" (
    "ApplicationAttributesId" text,
    "ApplicationId" text,
    "AttributeId" text,
    "AttributeValue" text,
    "Timestamp" timestamp,
    PRIMARY KEY ("ApplicationAttributesId")
)
WITH
  bloom_filter_fp_chance=0.01
  AND compaction={ 'class' : 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy' }
  AND compression={ 'sstable_compression' : 'org.apache.cassandra.io.compress.LZ4Compressor' }
  AND caching='{"keys":"ALL", "rows_per_partition":"NONE"}';
