class StackNode<T>
{
	T data;
	StackNode<T> next;
	
	public StackNode(T data)
	{
		this.data=data;
		next=null;
	}
}

public class MyStack<T>
{
	private StackNode<T> head;
	
	public MyStack()
	{
		this.head=null;
	}
	
	public void push(T data)
	{
		StackNode<T> newNode=new StackNode<T>(data);
		if(this.head==null)
		{
			
			this.head=newNode;
		}
		else
		{
			newNode.next=head;
			head=newNode;
		}
	}
	public T pop()
	{
		if(head==null)
		{
			return null;
		}
		else
		{
			StackNode<T> temp=this.head;
			this.head=this.head.next;
			return temp.data;
		}
	}
	
	public boolean isEmpty()
	{
		if(this.head==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
