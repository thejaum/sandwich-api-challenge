DELETE FROM composition;

DELETE FROM addons;
INSERT INTO addons (active,name,price)
VALUES(1,"Alface",0.4),
(1,"Bacon",2),
(1,"Hambúrguer",3),
(1,"Ovo",0.8),
(1,"Queijo",1.5);


DELETE FROM products;
INSERT INTO products (active,base_price,description,name )
VALUES (1,0,"Para os amant3s de Bacon","X-Bacon");
INSERT INTO composition(product_id ,addon_id ) 
VALUES (1,2),(1,3),(1,5);

INSERT INTO products (active,base_price,description,name )
VALUES (1,0,"O tradicionalíssimo","X-Burger");
INSERT INTO composition(product_id ,addon_id ) 
VALUES (2,3),(2,5);

INSERT INTO products (active,base_price,description,name )
VALUES (1,0,"Ovos verdes","X-Egg");
INSERT INTO composition(product_id ,addon_id ) 
VALUES (3,3),(3,4),(3,5);

INSERT INTO products (active,base_price,description,name )
VALUES (1,0,"Café da manhã de americano","X-Egg Bacon");
INSERT INTO composition(product_id ,addon_id ) 
VALUES (4,2),(4,3),(4,4),(4,5);

DELETE FROM offer_rules;
DELETE FROM offers;
INSERT INTO offers (active,identifier_name,description,discount,accumulative,whole_order)
VALUES (1,"LIGHT","Lanche levinho!",10,0,1);
INSERT INTO offer_rules (offer_id,addon_id,amount,presence)
VALUES (1,1,1,1),(1,2,0,0);

INSERT INTO offers (active,identifier_name,description,discount,accumulative,whole_order)
VALUES (1,"MUITA_CARNE","Velório do boi!",33,1,0);
INSERT INTO offer_rules (offer_id,addon_id,amount,presence)
VALUES (2,3,3,1);

INSERT INTO offers (active,identifier_name,description,discount,accumulative,whole_order)
VALUES (1,"MUITO_QUEIJO","Mais queijo por favor!",33,1,0);
INSERT INTO offer_rules (offer_id,addon_id,amount,presence)
VALUES (3,5,3,1);