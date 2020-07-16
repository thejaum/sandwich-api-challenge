### Esta é uma API Rest desenvolvida com a finalidade de gerenciar a criação e lançamento de pedidos de uma startup de vendas online. O arquivo PDF do desafio pode ser encontrado em `/docs/Avaliação Técnica  - Java.pdf`

### Modelagem do banco conceitual 
![Modelagem Conceitual](/docs/modelagem.png?raw=true "Modelagem Conceitual")

### Ideias por trás da modelagem
#### Venda de produtos
Para solucionar a necessidade de vender lanches e adicionais sem descartar a possibilidade da venda de outros produtos, criei a tabela "products" que representa os registros que podem ser vendidos, ou seja, que podem ser adicionados a um pedido.
#### Composição do Produto
A tabela "addons" representa tudo que pode compor um produto, portanto quando é adicionado o produto ao pedido, o preço inicial dele é a soma dos addons que estão relacionados com ele mais o preço base do mesmo.
#### Pedidos e itens
Separei o Pedido em duas tabelas, primeiro a "orders" que representa a base do pedido e então a "order_itens" que através do relacionamento armazena todos os itens lançados no pedido.
#### Promoções
Procurei criar uma forma dinamica de implementar as promoções sem fixar no codigo fonte as mesmas, o resultado são as tabelas "offers", a promoção em si e "offer_rules" que são as regras que compoem a promoção
#### Regras
A Regra da promoção funciona assim, 
- O campo addon_id diz qual adicional se trata a regra.
- O campo presence condiciona se é preciso ter ou não o item.
- O campo amount diz qual a quantidade que precisa (ter ou não)
Ja os campos da tabela offer
- O campo whole_order diz se o desconto é aplicado ao item todo ou no adicional,
- O campo accumulative diz se a promoção é cumulativa para o mesmo item do pedido.
- O Campo discount_percentage diz qual a % de desconto;
- O Campo discount_amount diz quantos itens de desconto;


### Registros
Durante o processo de criação documentei nos seguintes arquivos alguns fluxos de negocio e endpoints que foram idealizados para a API
```
/docs/api-prototype.json
```
```
/docs/fluxo-das-logicas.txt
```
```
/docs/novo_fluxo_promocoes.txt
```
### Quais implementações consideraria importantes em uma evolução
#### ->Tratativa dos erros
Criar handlers para interceptar e unificar a resposta para possiveis exceções nas chamadas a API;
#### ->Camada de Log
Cobrir a aplicação com uma saida eficiente de logs;-
#### ->Finalizar Fluxos
Após entender quais seriam as necessidades do Front, implementar as demais chamadas, paginações e cache.
#### ->Docker DevOps
Preparar containers de servidor/mysql e criar um docker-compose para subir o projeto apenas com o .war


## Instruções do consumo dos endpoints
Antes de iniciar o projeto alterar o application.properties com os dados de conexão do mysql

1. Assim que subir o projeto acesse o swagger (Considerando localhost e porta definida no application.prop);
```
GET - localhost:9091/swagger-ui.html
```
2. Adquira um token de acesso no endpoint Security / login
```
POST - localhost:9091/login
Body
{
  "password": "sandwich",
  "username": "sandwich"
}
Ou
{
  "password": "bacon",
  "username": "bacon"
}
Estes dois usuarios ja são criados no startup.
```
3. Recupere os possiveis produtos a serem pedidos
```
GET - localhost:9091/v1/protected/products
```
4. Recupere os possiveis adicionais que podem ser solicitados junto ao produto
```
GET - localhost:9091/v1/protected/addons
```
5. Crie um pedido
```
POST - localhost:9091/v1/protected/orders
```
6. Agora com o ID do pedido, id do produto e dos adicionais, adicione o item ao pedido
```
POST - localhost:9091/v1/protected/orders/{order_id}/itens
```
7. Após lançar os itens finalize o pedido
```
PUT - localhost:9091/v1/protected/orders/{order_id}/perform
```
