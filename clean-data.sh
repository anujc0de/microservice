RED='\033[0;31m'
NC='\033[0m'

# Check if the number of customers is provided as an argument
if [ $# -ne 1 ]; then
  echo "Usage: $0 <number_of_customers>"
  exit 1
fi

# Assign the argument value to a variable
num_customers=$1

PGPASSWORD=root psql -h 127.0.0.1 -U root -d cart_service -c 'delete from cart_items; delete from carts; delete from customers;'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d cart_service -v num_customers="$num_customers" -f create-customers-and-carts.sql
PGPASSWORD=root psql -h 127.0.0.1 -U root -d order_service -c 'delete from order_items; delete from orders;'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d payment_service -c 'delete from payments'
PGPASSWORD=root psql -h 127.0.0.1 -U root -d inventory_service -c "update inventories set quantity = 100"
PGPASSWORD=root psql -h 127.0.0.1 -U root -d inventory_service -c 'delete from block_inventories'
