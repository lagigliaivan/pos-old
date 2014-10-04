CREATE TABLE product (
  id_product varchar(20) NOT NULL,
  description varchar(250) NOT NULL,
  stock int  NULL,
  price double DEFAULT '0',
  PRIMARY KEY (id_product)
) ;

INSERT INTO product VALUES (10,'goma',2,0.5),(5262,'tigera',2,2.1),(12123,'lapicera',2,2);

CREATE TABLE sale (
  id_sale bigint  NOT NULL IDENTITY,
  date datetime DEFAULT NULL,
  description varchar(200) DEFAULT NULL,
 );

CREATE TABLE sale_detail(
  id_sale bigint  NOT NULL,
  id_product varchar(20) NOT NULL,
  product_amount int NOT NULL,
  PRIMARY KEY (id_sale,id_product)
);
