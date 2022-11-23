echo "Stoping ..."
docker stop user-acc-db
docker stop discovery-server
docker stop user-service
docker stop auth-service
docker stop rate-service

echo ""
echo "Deleting containers ..."
docker rm user-acc-db
docker rm discovery-server
docker rm gateway
docker rm user-service
docker rm auth-service
docker rm rate-service

echo ""
echo "Deleting images ..."
docker rmi scb-wind2022/discovery-service:1.0
docker rmi scb-wind2022/gateway:1.0
docker rmi scb-wind2022/user-service:1.0
docker rmi scb-wind2022/auth-service:1.0
docker rmi scb-wind2022/rate-service:1.0

echo ""
read -p "Нажмите <Enter> для завершения"