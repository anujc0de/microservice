#!/bin/zsh

# List of directories
directories=("cart_service" "inventory_service" "order_service" "payment_service" "product_service")

# Create a new tmux session
tmux new-session -d -s mysession

# Function to handle Ctrl+C
cleanup() {
  # Exit the current tab without closing the entire window
  tmux send-keys -t mysession: Escape
  tmux send-keys -t mysession: q
}

# Loop through each directory
for directory in "${directories[@]}"
do
  # Create a new window (tab) in the tmux session and run the command
  tmux new-window -t mysession: -n "$directory" "cd $directory && trap cleanup SIGINT && ./mvnw clean spring-boot:run; read"

  # Rename the window (tab) to the directory name
  tmux rename-window -t mysession: "$directory"
done

# Attach to the tmux session to view the windows (tabs)
tmux attach-session -t mysession
