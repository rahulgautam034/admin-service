package com.flight_app.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * user table
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "user1")
public class AuthEntity {

	/**
	 * first name
	 */
	@DynamoDBAttribute
	
	private String firstName;

	/**
	 * last name
	 */
	@DynamoDBAttribute
	private String lastName;

	/**
	 * role
	 */
	@DynamoDBAttribute
	private String role;

	/**
	 * user name
	 */
	@DynamoDBHashKey
	private String userName;

	/**
	 * password
	 */
	@DynamoDBAttribute
	private String password;

}
