class RedBlackTree<T extends Comparable<T>>
{
	//These are the flags for the color characterstic of TreeNode class
	private final boolean RED=true;
	private final boolean BLACK=false;
	
	//This is the roort of  tree
	private TreeNode rootNode;
	
	class TreeNode
	{
		T data;
		boolean color;
		
		TreeNode leftChild;
		TreeNode rightChild;
		TreeNode parent;
		
		// Parameterised constructor of TreeNode class
		public TreeNode(T data,boolean color)
		{
			this.data=data;
			this.color=color;
			this.leftChild=null;
			this.rightChild=null;
			this.parent=null;
		}
	}
	
	
	//Default constructor of RedBlackTree class
	public RedBlackTree()
	{
		this.rootNode=null;
	}
	
	
	//Insert function to insert node in tree
	public void insert(T data)
	{
		// If tree is empty
		if(this.rootNode==null)
		{
			this.rootNode=new TreeNode(data,BLACK);
			return;
		}
		
		TreeNode temp=this.rootNode;
		TreeNode newChild=new TreeNode(data,RED);
		int ret;
		
		// Insert node in tree as like Binary Search Tree
		while(true)
		{
			ret=data.compareTo(temp.data);
			if(ret>0)
			{
				if(temp.rightChild==null)
				{
					temp.rightChild=newChild;
					newChild.parent=temp;
					break;
				}
				temp=temp.rightChild;
			}
			else if(ret<0)
			{
				if(temp.leftChild==null)
				{
					temp.leftChild=newChild;
					newChild.parent=temp;
					break;
				}
				temp=temp.leftChild;
			}
			else if(ret==0)
			{
				break;
			}
		}
		
		// If parent of newly inserted node is black then return
		if(newChild.parent.color==BLACK)
		{
			return;
		}
		
		// Uncle node of newly inserted node
		TreeNode uncle;
		
		// This is the loop for fixing the tree which was unbalanced by the newly inserted node
		while((newChild.parent!=rootNode)&&(newChild.parent.color==RED))
		{
			
			// Getting uncle node
			if(newChild.parent==newChild.parent.parent.leftChild)
			{
				uncle=newChild.parent.parent.rightChild;
			}
			else
			{
				uncle=newChild.parent.parent.leftChild;
			}
			
			// if uncle node is NULL or BLACK then roation and recolouring
			if((uncle==null)||(uncle.color==BLACK))
			{
				
				if((newChild.parent==newChild.parent.parent.leftChild)&&(newChild==newChild.parent.leftChild))
				{
					if((newChild.parent.rightChild!=null))
					{
						newChild=newChild.parent;
						if(newChild.parent==this.rootNode)
						{
							newChild.parent.leftChild=newChild.rightChild;
							newChild.rightChild.parent=newChild.parent;
							newChild.rightChild=newChild.parent;
							newChild.rightChild.parent=newChild;
							newChild.parent=null;
							rootNode=newChild;
						}
						else
						{
							newChild.parent.leftChild=newChild.rightChild;
							newChild.rightChild.parent=newChild.parent;
							newChild.rightChild=newChild.parent;
							newChild.parent.parent.leftChild=newChild;
							newChild.parent=newChild.parent.parent;
							newChild.rightChild.parent=newChild;
							
						}
					}
					else
					{
						if(newChild.parent.parent!=rootNode)
						{
							newChild=newChild.parent;
						}
						newChild.parent.parent.leftChild=newChild;
						newChild.rightChild=newChild.parent;
						newChild.parent=newChild.parent.parent;
						newChild.rightChild.parent=newChild;
						newChild.rightChild.leftChild=null;
					}
					newChild.color=(newChild.color==BLACK?RED:BLACK);
					newChild.rightChild.color=(newChild.rightChild.color==RED?BLACK:RED);
				}
				else if((newChild.parent==newChild.parent.parent.leftChild)&&(newChild==newChild.parent.rightChild))
				{												
					newChild=leftRotate(newChild);
					newChild=rightRotate(newChild);
					newChild.color=(newChild.color==BLACK?RED:BLACK);
					newChild.rightChild.color=(newChild.rightChild.color==BLACK?RED:BLACK);
				}
				else if((newChild.parent==newChild.parent.parent.rightChild)&&(newChild==newChild.parent.rightChild))
				{
					if((newChild.parent.leftChild!=null))
					{
						newChild=newChild.parent;
						if(newChild.parent==this.rootNode)
						{
							newChild.parent.rightChild=newChild.leftChild;
							newChild.leftChild.parent=newChild.parent;
							newChild.leftChild=newChild.parent;
							newChild.leftChild.parent=newChild;
							newChild.parent=null;
							rootNode=newChild;
						}
						else
						{
							newChild.parent.rightChild=newChild.leftChild;
							newChild.leftChild.parent=newChild.parent;
							newChild.leftChild=newChild.parent;
							newChild.parent.parent.rightChild=newChild;
							newChild.parent=newChild.parent.parent;
							newChild.leftChild.parent=newChild;
							
						}
					}
					else
					{
						if(newChild.parent.parent!=rootNode)
						{
							newChild=newChild.parent;
						}
						newChild.parent.parent.rightChild=newChild;
						newChild.leftChild=newChild.parent;
						newChild.parent=newChild.parent.parent;
						newChild.leftChild.parent=newChild;
						newChild.leftChild.rightChild=null;
						
					}
					newChild.color=(newChild.color==BLACK?RED:BLACK);
					newChild.leftChild.color=(newChild.leftChild.color==RED?BLACK:RED);
				}
				else if((newChild.parent==newChild.parent.parent.rightChild)&&(newChild==newChild.parent.leftChild))
				{					
					newChild=rightRotate(newChild);
					newChild.parent.parent.rightChild=newChild;
					newChild.leftChild=newChild.parent;
					newChild.parent=newChild.parent.parent;
					newChild.leftChild.parent=newChild;
					newChild.leftChild.rightChild=null;
					newChild.color=(newChild.color==BLACK?RED:BLACK);
					newChild.leftChild.color=(newChild.leftChild.color==BLACK?RED:BLACK);
				}
				break;
			}
			
			// Uncle's color is RED then only recolouring
			else
			{
				if(newChild.parent.color=BLACK)
				{
					newChild.parent.color=RED;
				}
				else
				{
					newChild.parent.color=BLACK;
				}
				
				if(uncle.color=BLACK)
				{
					uncle.color=RED;
				}
				else
				{
					uncle.color=BLACK;
				}
				
				if(newChild.parent.parent!=rootNode)
				{
					if(newChild.parent.parent.color==BLACK)
					{
						newChild.parent.parent.color=RED;
					}
					else
					{
						newChild.parent.parent.color=BLACK;
					}
					newChild=newChild.parent.parent;
				}
				else
				{
					break;
				}
				
			}
			
		}
		
	}
	
	
	//Helper function for left rotation
	public TreeNode leftRotate(TreeNode newChild)
	{
		
		newChild.parent.parent.leftChild=newChild;
		newChild.leftChild=newChild.parent;
		newChild.parent=newChild.parent.parent;
		newChild.leftChild.parent=newChild;
		newChild.leftChild.rightChild=null;
		
		return newChild;
	}
	
	//Helper function for right roation
	public TreeNode rightRotate(TreeNode newChild)
	{
		newChild.parent.parent.rightChild=newChild;
		newChild.rightChild=newChild.parent;
		newChild.parent=newChild.parent.parent;
		newChild.rightChild.parent=newChild;
		newChild.rightChild.leftChild=null;
		
		return newChild;
	}
	
	// Displays nodes of tree
	public void display()
	{
		System.out.print("{");
		MyStack<TreeNode> nodes = new MyStack<TreeNode>();
		TreeNode current = this.rootNode;
		int i=0;
		while (!nodes.isEmpty() || current != null) 
		{

			if (current != null) 
			{
				nodes.push(current);
				current = current.leftChild;
				
			}
			else 
			{
				TreeNode node = nodes.pop();
				System.out.print(node.data+" \t");
				current = node.rightChild;
			}
			if(i==5)
			{
				break;
			}
		}
		System.out.print("}");
	}
}
































