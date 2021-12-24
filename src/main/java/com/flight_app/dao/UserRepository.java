package com.flight_app.dao;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.flight_app.entities.AuthEntity;

/**
 * dynamoDB repository for logged in user operations
 *
 */

@Repository

public class UserRepository {

	private final DynamoDBMapper dynamoDBMapper;

	public UserRepository(DynamoDBMapper dynamoDBMapper) {
		this.dynamoDBMapper = dynamoDBMapper;
	}

	/**
	 * get user for db using user name
	 * 
	 * @param userName
	 *
	 */
	public AuthEntity findByUserName(String userName) {
		return dynamoDBMapper.load(AuthEntity.class, userName,
				new DynamoDBMapperConfig(DynamoDBMapperConfig.ConsistentReads.CONSISTENT));
	}

}
