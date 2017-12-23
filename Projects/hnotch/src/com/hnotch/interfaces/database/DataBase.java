package com.hnotch.interfaces.database;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.hnotch.values.constants.Constants;

public interface DataBase {
	
	public static final Cluster cluster = Cluster
			.builder()
			.addContactPoint(Constants.KEYSPACE_HOST)
			.withRetryPolicy(DefaultRetryPolicy.INSTANCE)
			.withLoadBalancingPolicy(
	                         new TokenAwarePolicy(new DCAwareRoundRobinPolicy()))
			.build();
	
	public static final Session session = cluster.connect(Constants.KEYSPACE_NAME);

}
