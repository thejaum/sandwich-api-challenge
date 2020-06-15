/*DELETE FROM composition;
DELETE FROM products;
DELETE FROM addons;

INSERT INTO addons (addon_id,active,name,price)
VALUES
(uuid(),1,"Alface",0.4),
(uuid(),1,"Bacon",2),
(uuid(),1,"Hambúrguer",3),
(uuid(),1,"Ovo",0.8),
(uuid(),1,"Queijo",1.5);

SELECT addon_id from addons where name = "Alface" into @alface;
SELECT addon_id from addons where name = "Bacon" into @bacon;
SELECT addon_id from addons where name = "Hambúrguer" into @burguer;
SELECT addon_id from addons where name = "Ovo" into @ovo;
SELECT addon_id from addons where name = "Queijo" into @queijo;

INSERT INTO products (product_id,active,base_price,description,name )
VALUES (uuid(),1,0,"Para os amant3s de Bacon","X-Bacon");
SELECT product_id from products where name="X-Bacon" into @product_key;
INSERT INTO composition(product_id ,addon_id ) 
VALUES (@product_key,@bacon),(@product_key,@burguer),(@product_key,@queijo);

INSERT INTO products (product_id,active,base_price,description,name )
VALUES (uuid(),1,0,"O tradicionalíssimo","X-Burger");
SELECT product_id from products where name="X-Burger" into @product_key;
INSERT INTO composition(product_id ,addon_id )
VALUES (@product_key,@burguer),(@product_key,@queijo);

INSERT INTO products (product_id,active,base_price,description,name )
VALUES (uuid(),1,0,"Ovos verdes","X-Egg");
SELECT product_id from products where name="X-Egg" into @product_key;
INSERT INTO composition(product_id ,addon_id ) 
VALUES (@product_key,@burguer),(@product_key,@ovo),(@product_key,@queijo);

INSERT INTO products (product_id,active,base_price,description,name )
VALUES (uuid(),1,0,"Café da manhã de americano","X-Egg Bacon");
SELECT product_id from products where name="X-Egg" into @product_key;
INSERT INTO composition(product_id ,addon_id ) 
VALUES (@product_key,@bacon),(@product_key,@burguer),(@product_key,@ovo),(@product_key,@queijo);


DELETE FROM offer_rules;
DELETE FROM offers;

INSERT INTO offers (offer_id,active,identifier_name,description,discount,accumulative,whole_order)
VALUES (uuid(),1,"LIGHT","Lanche levinho!",10,0,1);
SELECT offer_id from offers where identifier_name="LIGHT" into @offer_key;
INSERT INTO offer_rules (offer_rule_id,offer_id,addon_id,amount,presence)
VALUES (uuid(),@offer_key,@alface,1,1),(uuid(),@offer_key,@bacon,0,0);

INSERT INTO offers (offer_id,active,identifier_name,description,discount,accumulative,whole_order)
VALUES (uuid(),1,"MUITA_CARNE","Velório do boi!",33,1,0);
SELECT offer_id from offers where identifier_name="MUITA_CARNE" into @offer_key;
INSERT INTO offer_rules (offer_rule_id,offer_id,addon_id,amount,presence)
VALUES (uuid(),@offer_key,@burguer,3,1);

INSERT INTO offers (offer_id,active,identifier_name,description,discount,accumulative,whole_order)
VALUES (uuid(),1,"MUITO_QUEIJO","Mais queijo por favor!",33,1,0);
SELECT offer_id from offers where identifier_name="MUITO_QUEIJO" into @offer_key;
INSERT INTO offer_rules (offer_rule_id,offer_id,addon_id,amount,presence)
VALUES (uuid(),@offer_key,@queijo,3,1);*/