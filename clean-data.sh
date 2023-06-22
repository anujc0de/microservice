RED='\033[0;31m'
NC='\033[0m'

# Replace "container_name" with the actual name of your PostgreSQL container
PGPASSWORD=root psql -h 127.0.0.1 -U root -d cart_service -c 'delete from cart_items'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d order_service -c 'delete from order_items; delete from orders;'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d payment_service -c 'delete from payments'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d inventory_service -c 'update inventories set quantity = 100'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d inventory_service -c 'delete from block_inventories'
