
DROP DATABASE IF EXISTS Book_Swapper;

CREATE DATABASE Book_Swapper;

USE Book_Swapper;

CREATE TABLE users (
    UserId VARCHAR(50),
	FirstName VARCHAR(50),
	LastName VARCHAR(50),
	EmailAddress VARCHAR(50),
	AddressField1 VARCHAR(50),
	AddressFiled2 VARCHAR(50),
	City VARCHAR(50),
	StateCode VARCHAR(50),
	PostalCode VARCHAR(50),
	Country VARCHAR(50),
	PRIMARY KEY(UserId)
);


CREATE TABLE items(
    Name VARCHAR(50),
	ItemCode VARCHAR(50),
	HeadLine VARCHAR(500),
	Author VARCHAR(50),
	Rating VARCHAR(50),
	Category VARCHAR(50),
    ImageUrl VARCHAR(50),
	Description VARCHAR(1000),
	PRIMARY KEY(ItemCode)
	
);

CREATE TABLE useritems(
 	
	ItemCode VARCHAR(50),
	UserId VARCHAR(50),
	ItemStatus VARCHAR(50),
	UserRating VARCHAR (50),
	PRIMARY KEY(ItemCode, UserId),
	FOREIGN KEY (ItemCode) REFERENCES items(ItemCode),
	FOREIGN KEY (UserId) REFERENCES users(UserId)
);
 
CREATE TABLE offers(
	  ID int NOT NULL AUTO_INCREMENT,
	  ItemCode VARCHAR(50),
      ItemName VARCHAR(50),
	  RequestedItemcode VARCHAR(50),
      RequestedItemName VARCHAR(50),
	  RequestedFrom VARCHAR(50),
	  RequestedTo VARCHAR(50),
	  PRIMARY KEY (ID),                                       
	  FOREIGN KEY (ItemCode) REFERENCES items(ItemCode),
      FOREIGN KEY (RequestedItemcode) REFERENCES items(ItemCode),
	  FOREIGN KEY (RequestedFrom) REFERENCES users(UserId),
	  FOREIGN KEY (RequestedTo) REFERENCES users(UserId)
 );

CREATE TABLE swaps(
	  ID int NOT NULL AUTO_INCREMENT,
	  ItemCode VARCHAR(50),
      ItemName VARCHAR(50),
	  RequestedItemcode VARCHAR(50),
      RequestedItemName VARCHAR(50),
	  RequestedFrom VARCHAR(50),
	  RequestedTo VARCHAR(50),
	  PRIMARY KEY (ID),                                       
	  FOREIGN KEY (ItemCode) REFERENCES items(ItemCode),
      FOREIGN KEY (RequestedItemcode) REFERENCES items(ItemCode),
	  FOREIGN KEY (RequestedFrom) REFERENCES users(UserId),
	  FOREIGN KEY (RequestedTo) REFERENCES users(UserId)
 );
 
 CREATE TABLE UserFeedback (
 
  offerid int,
  fromUser VARCHAR(50),
  toUser VARCHAR(50),
  rating VARCHAR(50),
  PRIMARY KEY (offerid,fromUser),
  FOREIGN KEY (offerid) REFERENCES swaps(ID)
 );
 
 CREATE TABLE itemFeedBack (
 
    userID VARCHAR(50),
	itemCode VARCHAR(50),
	rating VARCHAR(50),
	PRIMARY KEY (userID,itemCode),
    FOREIGN KEY (userID) REFERENCES users(UserId),
	FOREIGN KEY (itemCode) REFERENCES items(ItemCode)
 
 );
 
 CREATE TABLE credentials (
 
 userID VARCHAR(50),
 password VARCHAR(2000),
 PRIMARY KEY (userID)
 
 );

INSERT INTO credentials VALUES('saikrishna6', '$2a$12$u1PAbjeIV9n4VuCEw1N0QupnwKpT/poF6dWEQRzyE.LF04Z1W1yRS');
INSERT INTO credentials VALUES('guest', '$2a$12$iDgRxUfMLCpHQ2/zZBtFgO.CsC5rWRRgAsMQPshezn6UmrISLpbo.');

insert into users VALUES ('guest', 'Mark', 'Antony', 'mark.antony@uncc.edu','512 Barton Creek', 'APT M', 'Charlotte', 'NC', '28262','United States'); 
insert into users VALUES ('saikrishna6', 'Sai Krishna', 'Telukuntla', 'stelukun@uncc.edu','508 Barton Creek', 'APT L', 'Charlotte', 'NC', '28262','United States'); 
insert into items VALUES ('Brave New World','BOK02','Brave New World is a dystopian novel','Mark Gates','N/A','Science Fiction','img7.jpg','he Modern Library ranked Brave New World fifth on its list of the 100 best English-language novels of the 20th century.In 2003, Robert McCrum writing for The Observer included Brave New World chronologically at number 53 in the top 100 greatest novels of all time,and the novel was listed at number 87 on the BBCs survey The Big Read.');

insert into items VALUES ('The Forever War','BOK04','The Forever War (1974) is a military science fiction novel by American author Joe Haldeman','Joe Haldeman','N/A', 'Science Fiction', 'img4.jpg','The Modern Library ranked Brave New World fifth on its list of the 100 best English-language novels of the 20th century. In 2003, Robert McCrum writing for The Observer included Brave New World chronologically at number 53 in the top 100 greatest novels of all time, and the novel was listed at number 87 on the BBCs survey The Big Read.');

insert into items VALUES ('The Soul of A New Machine', 'BOK05', 'Pulitzer Prize winner Tracy Kidder memorably records the drama, comedy, and excitement of one companys efforts to bring a new microcomputer to market.', 'Joe Haldeman', 'N/A', 'Technology','img5.jpg','Computers have changed since 1981, when The Soul of a New Machine first examined the culture of the computer revolution. What has not changed is the feverish pace of the high-tech industry, the go-for-broke approach to business that has caused so many computer companies to win big (or go belly up)');

insert into items VALUES ('What Technology Wants', 'BOK06','From the author of the New York Times bestseller The Inevitable a sweeping vision of technology as a living force that can expand our individual potential.','Kevin Kelly','N/A','Technology','img6.jpg', 'In this provocative book, one of todays most respected thinkers turns the conversation about technology on its head by viewing technology as a natural system, an extension of biological evolution. By mapping the behavior of life, we paradoxically get a glimpse at where technology is headed-or what it wants. Kevin Kelly offers a dozen trajectories in the coming decades for this near-living system.');

insert into items VALUES ('Block Chain','BOK01','Blockchain: Ultimate guide to understanding blockchain, bitcoin, cryptocurrencies, smart contracts and the future of money.','Mark Gates','N/A','Technology','img2.jpg','Blockchain technology has been called the greatest innovation since the internet. Is blockchain technology one of the greatest technological revolutions in history or is it just hype? Will blockchain technology cause governments and banking systems to change the way they process information or will it be business as usual? In this book, well look at the answers to these questions along with addressing the different sides of the arguments, for and against, blockchain technology.');

insert into items VALUES ('The City Outside the World','BOK03','How to Be a Good Creature: A Memoir in Thirteen Animals by Sy Montgomery','Lin Carter','N/A','Science Fiction','img3.jpg','This is a beautiful book essential reading for anyone who loves animals and knows how much they can teach us about being human.Gwen Cooper, author of Homer Odyssey A Fearless Feline Tale,or How I Learned About Love and Life with a Blind Wonder Cat.');
 

insert into useritems VALUES ('BOK01', 'saikrishna6', 'pending','4/5');
insert into useritems VALUES ('BOK06', 'guest', 'pending','4/5');
insert into useritems VALUES ('BOK03', 'saikrishna6','pending','3.5/5');
insert into useritems VALUES ('BOK02','guest','this item is available for swap','4.5/5');
insert into useritems VALUES ('BOK05','guest', 'pending','4/5');
insert into useritems VALUES ('BOK04', 'guest', 'this item is available for swap', '4/5');
 INSERT INTO offers (ItemCode,ItemName, RequestedItemcode, RequestedItemName,RequestedFrom, RequestedTo)
VALUES ('BOK03','The City Outside the World' ,'BOK05','The Soul of A New Machine','saikrishna6', 'guest');
INSERT INTO offers(ItemCode,ItemName, RequestedItemcode, RequestedItemName,RequestedFrom, RequestedTo) 
VALUES ('BOK06', 'What Technology wants','BOK01','Block Chain','guest','saikrishna6');


 
 
 