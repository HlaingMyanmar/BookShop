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



-- Create PurchaseReturn table
CREATE TABLE PurchaseReturn (
    rdate TIMESTAMP NOT NULL PRIMARY KEY,
    puid VARCHAR(15) NOT NULL,
    FOREIGN KEY (puid) REFERENCES purchase(puid)
);

-- Create PurchaseReturnDetails table
CREATE TABLE PurchaseReturnDetails (
    rdate TIMESTAMP NOT NULL,
    bcode VARCHAR(15),
    qty INT NOT NULL,
    amount INT NOT NULL,
    returnReason VARCHAR(255),
    PRIMARY KEY (rdate, bcode),
    FOREIGN KEY (bcode) REFERENCES book(bcode),
    FOREIGN KEY (rdate) REFERENCES PurchaseReturn(rdate)
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
CREATE TABLE sale_return (
    return_id VARCHAR(15) PRIMARY KEY NOT NULL,
    orid VARCHAR(15) NOT NULL,
    bcode VARCHAR(15) NOT NULL,
    return_date DATE NOT NULL,
    qty_returned INT,
    reason VARCHAR(255),
    FOREIGN KEY (orid) REFERENCES cuorder(orid),
    FOREIGN KEY (bcode) REFERENCES sale(bcode)
);
