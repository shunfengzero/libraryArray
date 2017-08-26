package lib;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 图书管理的小系统
 * 		这里需要一个图书类，
 * 				图书的信息：书名 价格  作者
 * 			  一个管理图书类
 * 				对于图书的管理：
 * 					保存所有图书：图书类型的数组
 * 					功能：增删改查
 *
 */
//图书类
class MyBook {

	private int id;
	private String bookName;
	private int price;
	private String author;
	private static int count = 1;

	{
		id = count;
		count++;

	}

	public MyBook(String bookName, String author, int price) {
		setBookName(bookName);
		setPrice(price);
		setAuthor(author);
	}

	public int getId() {
		return id;
	}

	/*
	 * public void setId(int id) { if (id <= 0) { this.id = 8; } else { this.id =
	 * id; } }
	 */
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}

// 管理图书类
class BookManagement {
	private String name;
	MyBook[] mbs;
	

	private static BookManagement bm = null;
	
	public static int count = 0;
	
	public static int getCount() {
		return count;
	}

	private BookManagement(String name) {
		this.name = name;// 图书馆的名字
		mbs = new MyBook[2];// 创建好书架
	}

	public static BookManagement getInstance(String name) {
		if (bm == null) {
			bm = new BookManagement(name);
		}
		return bm;
	}

	// 增
	public void myAddBook(MyBook addBooktoo) {
		// 参数合法性判断
		if (addBooktoo == null) {
			System.out.println("滚");
			return;
		}
		if ( this.mbs[this.mbs.length-1] != null) {
			this.mbs = myGrow();
		}
	
		for (int i = 0; i < this.mbs.length; i++) {
			if (this.mbs[i] == null) {
				this.mbs[i] = addBooktoo;
				count++;
				break;//满了 让它增长
			}
		}

	}
	
	//自动增长
	public MyBook[] myGrow() {
		
		int newLength = this.mbs.length + (this.mbs.length >> 1);
		MyBook[] newBook = new MyBook[newLength];
		
		for (int i = 0; i < this.mbs.length; i++) {
			newBook[i] = this.mbs[i];
 		}
		
		return newBook;
	}

	// 删
	public void myDeleteBook(int id) {
		// 参数合法性判断
		if (id <= 0) {
			System.out.println("你删除的书的id不存在");
			return;
		}

		int index = myFindBook(id);

		if (index != -1) {
			for (int i = index; i < this.mbs.length - 1; i++) {
				this.mbs[i] = this.mbs[i + 1];
			}

			this.mbs[this.mbs.length - 1] = null;

		}

	}

	// 改
	public void myAlterBook(int id) {
		// 参数合法性判断
		if (id <= 0) {
			System.out.println("你改的书的id不存在");
			return;
		}
		Scanner sc = new Scanner(System.in);
		int index = myFindBook(id);
		
		int flag = 1;
		if (index != -1) {
			while (true) {
				System.out.println("这本书的信息如下：");
				System.out.println("书名：" + this.mbs[index].getBookName());
				System.out.println("作者：" + this.mbs[index].getAuthor());
				System.out.println("价格：" + this.mbs[index].getPrice());
				System.out.print("请输入你想要修改的信息  :1.书名; 2.作者; 3.价格; 4. 退出修改");
				int choose = sc.nextInt();
				sc.nextLine();
				switch (choose) {
				case 1:
					System.out.println("请输入你想要的书的名字：");
					String name = sc.nextLine();
					this.mbs[index].setBookName(name);
					break;
				case 2:
					System.out.println("请输入修改的书的作者名字");
					String authorName = sc.nextLine();
					this.mbs[index].setAuthor(authorName);
					break;
				case 3:
					System.out.println("请输入修改的书的价钱");
					int price = sc.nextInt();
					this.mbs[index].setPrice(price);
					break;
				case 4:
					flag = 0;
					break;

				}
				if (flag == 0) {
					break;
				}

			}
		}
	}

	// 查
	/*
	 * 通过查找书的Id 来查找书
	 */
	public int myFindBook(int id) {
		// 参数合法性判断
		if (id <= 0) {
			System.out.println("你查找的书的id不存在");
			return -1;
		}
		int index = -1;
		for (int i = 0; i < this.mbs.length; i++) {
			if (this.mbs[i].getId() == id) {
				index = i;
				break;
			}
		}
		return index;

	}

	// 显示图书
	public void show() {
		for (int i = 0; i < this.mbs.length; i++) {
			if (this.mbs[i] != null) {
				System.out.println("书名： " + this.mbs[i].getBookName() + "作者：" + this.mbs[i].getAuthor() + "价格： "
						+ this.mbs[i].getPrice());
			}
		}
	}
	
	//select排序 升序
	public void selectSort() {
		//新书组的长度
		int newLength = this.mbs.length;
		//创建新书组
		MyBook[] newArray = new MyBook[newLength];
		//把旧数组中的元素赋给新书组
		newArray = Arrays.copyOf(this.mbs, newLength);
		
		//选择排序
		for (int i = 0; i < count - 1; i++) {
			int index = i;
			for (int j = i  + 1; j < count; j++) {
				if (newArray[index].getPrice() > newArray[j].getPrice()) {
					index = j;//获取最小值的小标
				}
					
			}
			if (index != i) {
				MyBook temp = newArray[index];
				newArray[index] = newArray[i];
				newArray[i] = temp;
			}//if
			
		}//for
		
		//遍历输出
		for (int i = 0; i < newArray.length; i++) {
			if (this.mbs[i] != null) {
				System.out.println("书名： " + newArray[i].getBookName() + "作者：" + newArray[i].getAuthor() + "价格： "
						+ newArray[i].getPrice());
			}
		}
		
	}
	
	//冒泡排序 降序序
		public void bubbleSort() {
			//新书组的长度
			int newLength = this.mbs.length;
			//创建新书组
			MyBook[] newArray = new MyBook[newLength];
			//把旧数组中的元素赋给新书组
			newArray = Arrays.copyOf(this.mbs, newLength);
			int flag = 0;
			//冒泡排序 降序
			for (int i = 0; i < count - 1; i++) {
				for (int j = 0; j < count - 1 - i; j++) {
				    if (newArray[j].getPrice() < newArray[j + 1].getPrice()) {
				    	MyBook temp = newArray[j];
						newArray[j] = newArray[j + 1];
						newArray[j + 1] = temp;
						
						flag = 1;
				    }
				    
				 
			}//for
				   if (flag == 0) {
				    	break;
				    }
				    flag = 0;
				
			}
			//遍历输出
			for (int i = 0; i < newArray.length; i++) {
				if (this.mbs[i] != null) {
					System.out.println("书名： " + newArray[i].getBookName() + "作者：" + newArray[i].getAuthor() + "价格： "
							+ newArray[i].getPrice());
				}
			}
			
		}

}

public class libraray {
	public static void main(String[] args) {
		BookManagement b = BookManagement.getInstance("灿灿的图书馆");
		MyBook b1 = new MyBook("海贼王", "尾田荣一郎", 100);
		MyBook b2 = new MyBook("火影忍者", "岸本齐史", 20);
		MyBook b3 = new MyBook("家庭教师", "天野明", 65);
		MyBook b4 = new MyBook("七龙珠", "鸟山明", 22);
		MyBook b5 = new MyBook("妖精的尾巴", "真岛浩", 43);
		MyBook b6 = new MyBook("叛逆的鲁鲁修", "谷口悟朗", 89);
		MyBook b7 = new MyBook("High DXD School", "石踏一荣", 34);
		MyBook b8 = new MyBook("四月是你的谎言", "新川直司 ", 54);
		MyBook b9 = new MyBook("银魂", "空知英秋", 12);
		MyBook b10 = new MyBook("幽游白书", "富坚义博", 34);
		MyBook b11 = new MyBook("死神", "久保带人", 32);
		MyBook b12 = new MyBook("命运石之门", "冈部伦太郎", 45);

		
		
		b.myAddBook(b1);
		b.myAddBook(b2);
		b.show();
		b.myAddBook(b3);
		b.myAddBook(b4);
		b.myAddBook(b5);
		b.myAddBook(b6);
		b.myAddBook(b7);
		b.myAddBook(b8);
		b.myAddBook(b9);
		b.myAddBook(b10);
		b.myAddBook(b11);
		b.myAddBook(b12);
		
		
		System.out.println("------------");
	/*	b.show();
		// System.out.println(MyBook.getId());

		System.out.println(b.myFindBook(2));
		b.myDeleteBook(2);
		b.myAddBook(b2);
		b.myAlterBook(2);
*/
		b.selectSort();
		System.out.println("---------");
		b.bubbleSort();
       
		
		
		
		
	}

}
