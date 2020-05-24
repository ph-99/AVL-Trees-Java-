import java.io.*; 
import java.util.*;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;
import java.io.*; 
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue; 
import java.util.Stack;
class Node{
	int key,height;
	Node left,right;
	Node(int d){
		key = d;
		height = 1;
	}
}

public class AVLtree{
	Node root;
	int height(Node N){
		if(N == null)
			return 0;
		return N.height;
	}
	int max(int a ,int b){
		return (a>b)? a:b;
	}
		
	//right rotation
	Node rightRotate(Node y){
		Node x = y.left;
		Node t2 = x.right;
		
		// rotation
		
		x.right = y;
		y.left = t2;
		
		//update heights
		
		y.height = max(height(y.left) , height(y.right)) +1;
		x.height = max(height(x.left) , height(x.right)) +1;
	
		return x;
		
	}
	
	//left rotation
	Node leftRotate(Node x){
		Node y = x.right;
		Node t2 = y.left;
		
		// rotation
		
		
		y.left = x;
		x.right=t2;
		
		//update heights
		x.height = max(height(x.left) , height(x.right)) +1;
		y.height = max(height(y.left) , height(y.right)) +1;
		
	
		return y;
		
	}
	int getBalance(Node N){
		if(N==null)
			return 0;
		return ( height(N.left) - height(N.right) );
	}
	Node insert(Node node , int key){
		if(node == null)
			return(new Node(key));
		if(key<node.key)
			node.left = insert(node.left , key);
		else if(key>node.key)
			node.right=insert(node.right, key);
		else
			return node;
		node.height = max(height(node.left) , height(node.right)) +1;
		int balance  = getBalance(node);
		
		if(balance >1 && key<node.left.key)
		{
			return rightRotate(node);
		}
		if(balance<-1 && key > node.right.key)
			return leftRotate(node);
		
		if(balance >1 && key>node.left.key)
		{
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		if(balance <-1 && key<node.right.key)
		{
			node.right= rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
		
	}
	Node minValue(Node node){
		Node current = node;
		while(current.left != null){
			current = current.left;
			
		}
		return current;
	}
	Node delete(Node root , int key){
		if(root==null)
			return root;
		if(key<root.key)
			root.left = delete(root.left,key);
		else if(key>root.key)
			root.right = delete(root.right,key);
		else {
			if((root.left == null) || (root.right == null)){
				Node temp = null;
				if(temp == root.left)
					temp = root.right;
				else
					temp = root.left;
				
				if(temp == null){
					temp = root;
					root = null;
				}
				else 
					root = temp;
			}
			else{
				Node temp = minValue(root.right);
				
				root.key = temp.key;
				root.right = delete(root.right , temp.key);
			}
		}
		if(root == null)
			return root;
		root.height = max(height(root.left), height(root.right))+1;
		int balance = getBalance(root);
		if(balance>1 && getBalance(root.left)>=0)
			return rightRotate(root);
		if(balance>1 && getBalance(root.left)<0){
			root.left = leftRotate(root.left);
			return rightRotate(root);
			
		}
		 if (balance < -1 && getBalance(root.right) <= 0)  
            return leftRotate(root);  
  
        // Right Left Case  
        if (balance < -1 && getBalance(root.right) > 0)  
        {  
            root.right = rightRotate(root.right);  
            return leftRotate(root);  
        }  
  
        return root;  
	}
	
	void preOrder(Node node){
		if(node!=null){
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		
		}
	}
	 public static void main(String[] args) { 
        AVLtree tree = new AVLtree();

		tree.root = tree.insert(tree.root, 9);  
        tree.root = tree.insert(tree.root, 5);  
        tree.root = tree.insert(tree.root, 10);  
        tree.root = tree.insert(tree.root, 0);  
        tree.root = tree.insert(tree.root, 6);  
        tree.root = tree.insert(tree.root, 11);  
        tree.root = tree.insert(tree.root, -1);  
        tree.root = tree.insert(tree.root, 1);  
        tree.root = tree.insert(tree.root, 2);   
  
        
        System.out.println("Preorder traversal" + " of constructed AVL tree is : "); 
        tree.preOrder(tree.root); 
		
		tree.root = tree.delete(tree.root,1 );
		System.out.println("");  
        System.out.println("Preorder traversal after "+  
                        "deletion of 1 :");  
        tree.preOrder(tree.root);  
    } 
} 
	
	
	
	
	




