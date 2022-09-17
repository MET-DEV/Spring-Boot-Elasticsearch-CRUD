## Note! 
- Download: ` git clone https://github.com/MET-DEV/Spring-Boot-Elasticsearch-CRUD.git ` 
- I used Elasticsearch   ` 7.4.2 `, in other versions you can get error.
- Docker command for up your image: ` docker run  -p 9200:9200  -p 9300:9300  -e "discovery.type=single-node"  -e "xpack.security.enabled=false"  docker.elastic.co/elasticsearch/elasticsearch:{your_tag} `
- If you want basic auth, you can remove ` -e "xpack.security.enabled=false" ` from command. 
 
 ## Endpoints
For Get All Products ```GET localhost:8080/ ``` <br/>
For Add Product ```POST localhost:8080/ ```  <br/>
Example Request Body:
```json
{
    "id":3,
    "name":"Fırça",
    "description":"Kötü bir fırça",
    "price":33.5,
    "categoryName":"ev"
}
```
For Update Product ```PUT localhost:8080/update ```  <br/>
Example Request Body:
```json
{
    "id":3,
    "name":"Fırça",
    "description":"Kötü bir fırça",
    "price":33.5,
    "categoryName":"ev"
}
```
For Delete Product ```DELETE localhost:8080/{id} ```  <br/>
