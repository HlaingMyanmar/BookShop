-- Create the database
CREATE DATABASE IF NOT EXISTS bookshop_db;
USE bookshop_db;

-- Table: category
CREATE TABLE category (
    cid VARCHAR(15) PRIMARY KEY NOT NULL,
    cname VARCHAR(20) NOT NULL
);

-- Table: author
CREATE TABLE author (
    aid VARCHAR(15) PRIMARY KEY NOT NULL,
    aname VARCHAR(20) NOT NULL
);

-- Table: book
CREATE TABLE book (
    bcode VARCHAR(15) PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    qty INT,
    price INT NOT NULL,
    cid VARCHAR(15) NOT NULL,
    aid VARCHAR(15) NOT NULL,
    FOREIGN KEY (cid) REFERENCES category(cid),
    FOREIGN KEY (aid) REFERENCES author(aid)
);

-- Table: supplier
CREATE TABLE supplier (
    sid VARCHAR(15) PRIMARY KEY NOT NULL,
    suname VARCHAR(45),
    suphone VARCHAR(20),
    suaddress VARCHAR(30)
);

-- Table: purchase
CREATE TABLE purchase (
    puid VARCHAR(15) NOT NULL,
    pudate DATE NOT NULL,
    bcode VARCHAR(15),
    sid VARCHAR(15),
    qty INT,
    price INT,
    PRIMARY KEY (puid, bcode, sid),
    FOREIGN KEY (bcode) REFERENCES book(bcode),
    FOREIGN KEY (sid) REFERENCES supplier(sid)
);

-- Table: PurchaseReturn
CREATE TABLE PurchaseReturn (
    rid INT PRIMARY KEY AUTO_INCREMENT,
    puid VARCHAR(15) NOT NULL,
    rdate DATE NOT NULL,
    FOREIGN KEY (puid) REFERENCES purchase(puid)
);

-- Table: PurchaseReturnDetails
CREATE TABLE PurchaseReturnDetails (
    rdid VARCHAR(15) NOT NULL,
    rid INT NOT NULL,
    bcode VARCHAR(15),
    qty INT NOT NULL,
    amount INT NOT NULL,
    returnReason VARCHAR(255),
    PRIMARY KEY (rdid, rid, bcode),
    FOREIGN KEY (bcode) REFERENCES book(bcode),
    FOREIGN KEY (rid) REFERENCES PurchaseReturn(rid)
);

-- Table: order
CREATE TABLE orders (
    orid VARCHAR(15) PRIMARY KEY NOT NULL,
    ordate DATE NOT NULL,
    cuname VARCHAR(20),
    cuphone VARCHAR(20)
);

-- Table: sale
CREATE TABLE sale (
    orid VARCHAR(15) NOT NULL,
    bcode VARCHAR(15) NOT NULL,
    cid VARCHAR(15) NOT NULL,
    aid VARCHAR(15) NOT NULL,
    qty INT,
    price INT,
    PRIMARY KEY (orid, bcode, cid, aid),
    FOREIGN KEY (bcode) REFERENCES book(bcode),
    FOREIGN KEY (orid) REFERENCES orders(orid),
    FOREIGN KEY (cid) REFERENCES category(cid),
    FOREIGN KEY (aid) REFERENCES author(aid)
);
