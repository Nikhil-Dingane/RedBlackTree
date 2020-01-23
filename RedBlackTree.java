class RedBlackTree<T extends Comparable<T>>
{
	private final boolean RED=true;
	private final boolean BLACK=false;
	
	private TreeNode rootNode;
	
	class TreeNode
	{
		T data;
		boolean color;
		
		TreeNode leftChild;
		TreeNode rightChild;
		TreeNode parent;
		
		public TreeNode(T data,boolean color)
		{
			this.data=data;
			this.color=color;
			this.leftChild=null;
			this.rightChild=null;
			this.parent=null;
		}
	}
	
	public RedBlackTree()
	{
		this.rootNode=null;
	}
	
	public void insert(T data)
	{
		if(this.rootNode==null)
		{
			this.rootNode=new TreeNode(data,BLACK);
			return;
		}
		
		TreeNode temp=this.rootNode;
		TreeNode newChild=new TreeNode(data,RED);
		int ret;
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
		
		if(newChild.parent.color==BLACK)
		{
			return;
		}
		
		TreeNode uncle;
		
		while((newChild.parent!=rootNode)&&(newChild.parent.color==RED))
		{
			if(newChild.parent==newChild.parent.parent.leftChild)
			{
				uncle=newChild.parent.parent.rightChild;
			}
			else
			{
				uncle=newChild.parent.parent.leftChild;
			}
			
			if((uncle==null)||(uncle.color==BLACK))
			{
				//System.out.println("uncle is black or null of="+newChild.data);
				//System.out.println("parent="+newChild.parent.parent.rightChild.data+" grandparent"+newChild.parent.parent.data);
				if((newChild.parent==newChild.parent.parent.leftChild)&&(newChild==newChild.parent.leftChild))
				{
					//System.out.println("LL");
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
					//System.out.println("LR");
												
					newChild=leftRotate(newChild);
					newChild=rightRotate(newChild);
					newChild.color=(newChild.color==BLACK?RED:BLACK);
					newChild.rightChild.color=(newChild.rightChild.color==BLACK?RED:BLACK);
				}
				else if((newChild.parent==newChild.parent.parent.rightChild)&&(newChild==newChild.parent.rightChild))
				{
					//System.out.println("RR");
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
					
					//System.out.println("RL");
					
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
			else
			{
				//System.out.println("uncle is red of data="+newChild.data);
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
					//System.out.println("recoloring parent's parent");
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
					//System.out.println("breaking");
					break;
				}
				
			}
			
		}
		
	}
	
	public TreeNode leftRotate(TreeNode newChild)
	{
		
		newChild.parent.parent.leftChild=newChild;
		newChild.leftChild=newChild.parent;
		newChild.parent=newChild.parent.parent;
		newChild.leftChild.parent=newChild;
		newChild.leftChild.rightChild=null;
		
		return newChild;
	}
	
	public TreeNode rightRotate(TreeNode newChild)
	{
		newChild.parent.parent.rightChild=newChild;
		newChild.rightChild=newChild.parent;
		newChild.parent=newChild.parent.parent;
		newChild.rightChild.parent=newChild;
		newChild.rightChild.leftChild=null;
		
		return newChild;
	}
	
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
				//System.out.println("display if");
				nodes.push(current);
				current = current.leftChild;
				
			}
			else 
			{
				//System.out.println("display else");
				TreeNode node = nodes.pop();
				//System.out.println("data="+node.data+" color="+node.color+" parent="+(node.parent==null?"null":node.parent.data)+" \t");
				System.out.print(node.data+" \t");
				current = node.rightChild;
			}
			//System.out.print(current);
			if(i==5)
			{
				break;
			}
		}
		System.out.print("}");
	}
}
































