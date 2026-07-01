#include <iostream>
// Define a Node structure
struct Node {
    int data;
    Node* next;
};

// Function to print the list
void printList(Node* head) {
    Node* current = head;
    while (current != nullptr) {
        std::cout << current->data << " → ";
        current = current->next;
    }
     std::cout << "NULL" << std::endl;
}


int main() {
    std::cout << "Hello world!!" << std::endl;
    int a = 10;
    int b = 33;
    std::cout << a * b << std::endl;
    for(int i = 1; i<= a; i++){
        for(int k = a-i; k>= 1;k--){
            std::cout<<" ";
        }
        for(int j = 1; j <= i ;j++){
          std::cout<<j<<" ";
        }
       std::cout<<std::endl;
    }std::cout<<std::endl;
    int arr [] = { 1,2,3,4,34,23,43};
     std::cout<<"The array is:";
    for(int j = 0; j < 7;j++){
    std::cout<<arr[j]<<" ";
    }
    std::cout<<std::endl;
     // Create nodes manually
    Node* head = new Node{10, nullptr};
    Node* second = new Node{20, nullptr};
    Node* third = new Node{30, nullptr};

    // Link the nodes
    head->next = second;
    second->next = third;

    // Print the list
    printList(head);

    // Free memory
    delete head;
    delete second;
    delete third;

    return 0;
}
