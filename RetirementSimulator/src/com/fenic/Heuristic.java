package com.fenic;

public class Heuristic {

	int[] map;
	int buckets;
	int bucketsize;
	
	public Heuristic(int buckets, int bucketsize) {
		map = new int[buckets];
		this.buckets = buckets;
		this.bucketsize = bucketsize;
		for (int i=0; i<buckets; i++) {
			map[i] = 0;
		}
	}
	
	public void addValue(int value) {
		int bucket = value / bucketsize;
		if (bucket<0)
				bucket = 0;
		if (bucket<buckets)
			map[bucket]++; else
				map[buckets-1]++;
	}
	
	public int getBucketValue(int bucket) {
	  if (bucket < buckets)
		return map[bucket]; else
			throw new RuntimeException("bucket exceeds bucket size");
	}

	public int getBuckets() {
		return buckets;
	}

	public int getBucketsize() {
		return bucketsize;
	}
}
