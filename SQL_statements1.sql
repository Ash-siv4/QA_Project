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
    PRIMARY KEY (orderID),
    FOREIGN KEY (customerID) REFERENCES customers(customerID)
);

CREATE TABLE inventory.orderline(
    orderLineID int unique NOT NULL AUTO_INCREMENT,
    orderID int NOT NULL,
	itemID int NOT NULL,
	orderQuant int,
    totalCost float,
    PRIMARY KEY (orderLineID),
    FOREIGN KEY (orderID) REFERENCES orders(orderID),
    FOREIGN KEY (itemID) REFERENCES items(itemID)
);


SHOW tables;
DESCRIBE customers;
DESCRIBE orders;
DESCRIBE orderline;
DESCRIBE items;

INSERT INTO customers(firstname,surname,email,mobile,address,cardType,cardNo,expiryMonth,expiryYear,cardCVC) VALUES('Ash','Siva','as@gmail.com','07654456564','1 abc lane, London, HA3 9SU','Visa','5234123412341234',11,2012,464);
INSERT INTO customers(firstname,surname,email,mobile,address,cardType,cardNo,expiryMonth,expiryYear,cardCVC) VALUES('Jack','Sparrow','captain@cmail.com','07942305372','4 abc lane, London, HA3 9ST','Debit','4444333311112222',8,2040,423);
INSERT INTO customers(firstname,surname,email,mobile,address,cardType,cardNo,expiryMonth,expiryYear,cardCVC) VALUES('Katniss','Everdeen','katEv@mockingjay.com','07534876543','9 abc lane, London, HA3 9SU','Mastercard','6453453534575478',6,2043,346);
SELECT * FROM customers;
UPDATE customers SET mobile='01234567890' WHERE customerID=1;
DELETE FROM customers WHERE customerID=0;

INSERT INTO items(itemName,price,stock) VALUES('Iron Man 1 DVD',5.99,3000);
INSERT INTO items(itemName,price,stock) VALUES('Avengers DVD',12.99,1066);
INSERT INTO items(itemName,price,stock) VALUES('Deadpool DVD',8.99,900);
SELECT * FROM items;
UPDATE items SET stock=934 WHERE itemID=1;
DELETE FROM items WHERE itemID=0;


SELECT * FROM orders;
SELECT * FROM orderline;


INSERT INTO orders(customerID) VALUES(4);
SELECT orderID FROM orders WHERE customerID=4;
INSERT INTO orderline(orderID,itemID,orderQuant,totalCost) VALUES(1,2,3,24.54);

SELECT ol.orderID, o.customerID, SUM(ol.totalCost) AS totalCost FROM orderline ol 
INNER JOIN orders o ON ol.orderID=o.orderID GROUP BY orderID;

SELECT o.orderID, o.customerID, c.firstname, ol.itemID, i.itemName, ol.orderQuant, ol.totalCost 
FROM orders o INNER JOIN orderline ol ON ol.orderID=o.orderID 
INNER JOIN items i ON ol.itemID=i.itemID 
INNER JOIN customers c ON o.customerID=c.customerID;

UPDATE orderline SET orderQuant=10 WHERE orderID=2 AND itemID=1;

SELECT price FROM items WHERE itemID=0;
SELECT orderQuant FROM orderline WHERE orderID=2 AND itemID=3;

UPDATE orderline SET totalCost=15.99 WHERE orderID=4 AND itemID=2;

DELETE FROM orderline WHERE itemID=10 AND orderID=5;

DELETE FROM orderline WHERE orderID=0;
DELETE FROM orders WHERE orderID=0;