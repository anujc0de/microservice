#!/bin/bash

# Check if the number of customers is provided as an argument
if [ $# -ne 1 ]; then
  echo "Usage: $0 <number_of_customers>"
  exit 1
fi

# Assign the argument value to a variable
num_customers=$1

# Execute the SQL script using psql
PGPASSWORD=root psql -U your_username -d your_database -v num_customers="$num_customers" -f create-customers-and-carts.sql
