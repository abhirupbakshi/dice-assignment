CREATE TABLE IF NOT EXISTS user_table (
	account_non_expired bool NULL,
	account_non_locked bool NULL,
	credentials_non_expired bool NULL,
	enabled bool NULL,
	"password" varchar(255) NULL,
	username varchar(255) NOT NULL,
	CONSTRAINT user_table_pkey PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS user_authorities (
	authorities int2 NULL,
	username varchar(255) NOT NULL,
	CONSTRAINT user_authorities_authorities_check CHECK (((authorities >= 0) AND (authorities <= 0))),
	CONSTRAINT fkfdqk9fod15kfmuwu5mwah655x FOREIGN KEY (username) REFERENCES user_table(username)
);