# online_food_delivery
I've crafted a sleek Online Food Delivery System using Spring Boot. Users can effortlessly explore a variety of restaurants, peruse enticing menus, place orders seamlessly, and stay in the loop with real-time delivery status updates. It's a tasteful solution for those craving convenience and efficiency in their dining experience.

for Order Class :
# POST request to save an order
1.curl --location 'http://localhost:8090/api/controller/saveorder' \
--header 'Content-Type: application/json' \
--data '{
    "user": {
      "id": 4,
      "username": "emma_green",
      "password": "strongpassword10",
      "role": "customer"
    },
    "totalAmount": 75.50,
    "orderDate": "2024-01-21T18:00:00"
}'

# PUT request to update an order
2. curl --location --request PUT 'localhost:8090/api/contoller/update/1' \
--header 'Content-Type: application/json' \
--data '{
    "id": 7,
    "user": {
        "id": 4,
        "username": "Aasif",
        "password": "strongpassword10",
        "role": "Admin"
    },
    "totalAmount": 75.5,
    "orderDate": "2024-01-21T18:00:00.000+00:00"
}'
# to get Order Based on id 
3.curl --location 'localhost:8090/api/contoller/Fetching/1'

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
for Delivery Class :
# POST request to save delivery 
1. curl --location --request POST 'http://localhost:8090/api/delivery/controller/savedelivery' \
--header 'Content-Type: application/json' \
--data '{
    "order": {
        "id": 1,
        "user": {
            "id": 4,
            "username": "Aasif",
            "password": "strongpassword10",
            "role": "customer"
        },
        "totalAmount": 7866.4,
        "orderDate": "2024-01-21T12:30:00"
    },
    "status": "shipped",
    "deliveryDate": "2024-01-21T15:45:00"
}'

# GET request to retrieve delivery information
2. curl --location 'http://localhost:8090/api/delivery/controller/getting/1'

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

for Menu Item Class :
# POST request to save menu 
1. curl --location --request POST 'http://localhost:8090/api/controller/save' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Vegetarian Pad Thai",
  "price": 10.99,
  "restaurant": {
    "id": 3,
    "name": "Thai Spice House",
    "location": "321 Spice Lane",
    "rating": 4.6
  }
}'

# GET request to retrieve data
2. curl --location 'http://localhost:8090/api/controller/gettingData/1'
   
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
#  my branch name is : online_food_delivery, please visit

















