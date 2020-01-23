class Demo
{
	public static void main(String arg[])
	{
		RedBlackTree<Integer> obj=new RedBlackTree<Integer>();
		
		for(int i=0;i<10000;i++)
		{
			obj.insert(i);
		}
		//obj.display();
	}
}