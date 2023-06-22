RED='\033[0;31m'
NC='\033[0m'

# Replace "container_name" with the actual name of your PostgreSQL container
PGPASSWORD=root psql -h 127.0.0.1 -U root -d postgres -c 'drop database inventory_service'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d postgres -c 'drop database cart_service'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d postgres -c 'drop database payment_service'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d postgres -c 'drop database order_service'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d postgres -c 'drop database product_service'
