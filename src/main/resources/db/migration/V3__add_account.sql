CREATE TABLE account (
    email varchar(255) NOT NULL PRIMARY KEY,
    password varchar(255) NOT NULL
);

CREATE TABLE address (
    email varchar(255) NOT NULL PRIMARY KEY,
    line1 varchar(255) NOT NULL,
    line2 varchar(255) NOT NULL,
    postcode varchar(15) NOT NULL
);

