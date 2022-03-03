curl --request POST \
  --url http://localhost:8080/message/ \
  --header 'Content-Type: application/json' \
  --header 'message-name: FooCommand' \
  --data '{
  "commandType": "Update",
  "foo": {
    "id": "foo#1",
    "age": "30"
  }
}'
