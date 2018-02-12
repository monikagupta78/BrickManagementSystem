# BrickManagementSystem
This project is about creating a Brick Management System

Pre-requisites
--------------
    JDK 1.8 above
    Maven

Git Clone
---------
https://github.com/Sajeshtk/BrickManagementSystem.git


Instructions
------------
mvn clean package

java -jar target/brick-management-web-0.1.0.jar

Access the URL as specified below,

Create New Brick Order
----------------------
[POST]
    Request - http://localhost:8080/order/<brickType>/<quantity>
    Response Code - 201 Created
    Response Data - {
        orderReferenceId: 1
    }

Retrieve an Existing Brick Order
--------------------------------
[GET] - Success Scenario
    Request - http://localhost:8080/order/<orderReferenceId>
    Response Code - 200 OK
    Response Data - {
        orderReferenceId: 1,
        brickType: UN_FRIED,
        quantity: 10
    }

[GET] - Invalid Order Reference Id
    Request - http://localhost:8080/order/<orderReferenceIdWrong>
    Response Code - 400 Bad Request
    Response error - {
        code: '100',
        message: 'Invalid Order Reference Id [111111]'
    }

Retrieve All Existing Brick Orders
--------------------------------
[GET] - Success Scenario
    Request - http://localhost:8080/orders
    Response Code - 200 OK
    Response Data - {
        [
            orderReferenceId: 1,
            brickType: UN_FRIED,
            quantity: 10
        ],
        [
            orderReferenceId: 2,
            brickType: FRIED,
            quantity: 20
        ],
        [
            orderReferenceId: 3,
            brickType: CHEMICALLY_SET,
            quantity: 10
        ],
    }

[GET] - No Orders
    Request - http://localhost:8080/orders
    Response Code - 200 OK
    Response Data - {
        <<EMPTY>>
    }

Update an Existing Brick Order
------------------------------
[PUT] - Success Scenario
    Request - http://localhost:8080/order/<orderReferenceId>/<quantity>
    Response Code - 200 OK
    Response Data - {
        orderReferenceId: 1 << This will be new reference Id. Old Order moves to Cancelled
    }

[PUT] - Invalid Order Reference Id
    Request - http://localhost:8080/order/<orderReferenceIdWrong>/<quantity>
    Response Code - 400 Bad Request
    Response error - {
        code: '100',
        message: 'Invalid Order Reference Id [111111]'
    }

Fulfill an Existing Brick Order
------------------------------
[PUT] - Success Scenario
    Request - http://localhost:8080/order/<orderReferenceId>/dispatch
    Response Code - 200 OK
    Response Data - {
        updated: true << TBD
    }

[PUT] - Invalid Order Reference Id
    Request - http://localhost:8080/order/<orderReferenceIdWrong>/dispatch
    Response Code - 400 Bad Request
    Response error - {
        code: '100',
        message: 'Invalid Order Reference Id [111111]'
    }

Error Fulfilling an Brick Order Already Dispatched
--------------------------------------------------
[PUT] - Invalid Order Reference Id
    Request - http://localhost:8080/order/<orderReferenceId>/dispatch
    Response Code - 400 Bad Request
    Response error - {
        code: '101',
        description: 'Order is fulfilled already'
    }