RED='\033[0;31m'
NC='\033[0m'

# Replace "container_name" with the actual name of your PostgreSQL container
PGPASSWORD=root psql -h 127.0.0.1 -U root -d cart_service -c 'select count(*) as cart_items_count from cart_items'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d order_service -c 'select count(*) as order_items_count from order_items;'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d inventory_service -c 'select count(*) as block_inventories_count from block_inventories'

PGPASSWORD=root psql -h 127.0.0.1 -U root -d order_service -c 'select order_status, count(*) from orders group by order_status'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d payment_service -c 'select payment_status, count(*) from payments group by payment_status'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d inventory_service -c 'select * from inventories'
