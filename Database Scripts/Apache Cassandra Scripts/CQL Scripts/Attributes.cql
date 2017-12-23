CREATE TABLE "Attributes" (
    "AttributeId" text,
    "AttributeDescription" text,
    "AttributeName" text,
    "Timestamp" timestamp,
    PRIMARY KEY ("AttributeId")
)
WITH
  bloom_filter_fp_chance=0.01
  AND compaction={ 'class' : 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy' }
  AND compression={ 'sstable_compression' : 'org.apache.cassandra.io.compress.LZ4Compressor' }
  AND caching='{"keys":"ALL", "rows_per_partition":"NONE"}';
