CREATE TABLE address (
	id varchar(36) PRIMARY KEY,
	street varchar(1024) NOT NULL,
	city varchar(1024) NOT NULL,
	state varchar(1024),
	postal_code varchar(1024),
	country varchar(1024) NOT NULL	
);

CREATE TABLE person (
	id varchar(36) PRIMARY KEY,
	name varchar(1024) NOT NULL,
	phone varchar(1024),
	email varchar(1024),
	address_id varchar(36) REFERENCES address(id)
)