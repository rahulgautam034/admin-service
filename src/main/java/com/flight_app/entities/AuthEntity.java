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

	@DynamoDBAttribute
	private String firstName;

	@DynamoDBAttribute
	private String lastName;

	@DynamoDBAttribute
	private String role;

	@DynamoDBHashKey
	private String userName;

	@DynamoDBAttribute
	private String password;

}
