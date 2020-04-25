SHOW databases;
CREATE DATABASE if not exists inventory;
USE inventory;
SHOW tables;
CREATE TABLE inventory.customers(
    customerID int unique NOT NULL AUTO_INCREMENT,
    firstname varchar(50),
    surname varchar(50),
    email varchar(100),
	mobile char(11) default 'N/A',
    address varchar(100) default 'Not applicable',
    cardType varchar(20) default 'Not applicable',
    cardNo char(16) default 'Not applicable',
    expiryMonth tinyint default null,
    expiryYear smallint default null, 
    cardCVC smallint default null,
    PRIMARY KEY (customerID)
);
CREATE TABLE inventory.items(
    itemID int unique NOT NULL AUTO_INCREMENT,
    itemName varchar(100),
    price float,
    stock int,
    PRIMARY KEY (itemID)
);
CREATE TABLE inventory.orders(
    orderID int unique NOT NULL AUTO_INCREMENT,
    customerID int NOT NULL,
	itemID int NOT NULL,
	orderQuant int,
    totalCost float,
    PRIMARY KEY (orderID),
    FOREIGN KEY (customerID) REFERENCES customers(customerID),
    FOREIGN KEY (itemID) REFERENCES items(itemID)
);

SHOW tables;
DESCRIBE customers;
DESCRIBE orders;
DESCRIBE items;

INSERT INTO customers(firstname,surname,email,mobile,address,cardType,cardNo,expiryMonth,expiryYear,cardCVC) VALUES('Ash','Siva','as@gmail.com','07654456564','1 abc lane, London. HA3 9SU','Visa','1234123412341234',11,2012,464);
SELECT * FROM customers;
SELECT cardNo,expiryMonth,expiryYear FROM customers;
UPDATE customers SET mobile='01234567890' WHERE customerID=1;
DELETE FROM customers WHERE customerID=1;