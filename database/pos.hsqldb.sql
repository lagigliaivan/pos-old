CREATE TABLE products (
  idproduct varchar(20) NOT NULL,
  description varchar(250) NOT NULL,
  stock int  NULL,
  price double DEFAULT '0',
  PRIMARY KEY (idproduct)
) 


INSERT INTO products VALUES (10,'goma',2,0.5),(5262,'tigera',2,2.1),(12123,'lapicera',2,2);


CREATE TABLE product_sale (
  idproduct varchar(20) NOT NULL,
  idsale bigint NOT NULL,
  PRIMARY KEY (idproduct,idsale)
) 

CREATE TABLE sales (
  idsale bigint  NOT NULL IDENTITY,
  date datetime DEFAULT NULL,
  description varchar(200) DEFAULT NULL,
)

