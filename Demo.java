class Demo
{
	public static void main(String arg[])
	{
		RedBlackTree<Integer> obj=new RedBlackTree<Integer>();
		
		for(int i=100;i>1;i--)
		{
			obj.insert(i);
		}
		obj.display();
	}
}