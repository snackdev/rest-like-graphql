curl --request POST \
  --url http://localhost:8080/message/ \
  --header 'Content-Type: application/json' \
  --header 'message-name: FooQuery' \
  --data '{
  "age": "20"
}'
