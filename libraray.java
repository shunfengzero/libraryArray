package lib;

import java.util.Arrays;
import java.util.Scanner;

/*
 * ͼ������Сϵͳ
 * 		������Ҫһ��ͼ���࣬
 * 				ͼ�����Ϣ������ �۸�  ����
 * 			  һ������ͼ����
 * 				����ͼ��Ĺ���
 * 					��������ͼ�飺ͼ�����͵�����
 * 					���ܣ���ɾ�Ĳ�
 *
 */
//ͼ����
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

// ����ͼ����
class BookManagement {
	private String name;
	MyBook[] mbs;
	

	private static BookManagement bm = null;
	
	public static int count = 0;
	
	public static int getCount() {
		return count;
	}

	private BookManagement(String name) {
		this.name = name;// ͼ��ݵ�����
		mbs = new MyBook[2];// ���������
	}

	public static BookManagement getInstance(String name) {
		if (bm == null) {
			bm = new BookManagement(name);
		}
		return bm;
	}

	// ��
	public void myAddBook(MyBook addBooktoo) {
		// �����Ϸ����ж�
		if (addBooktoo == null) {
			System.out.println("��");
			return;
		}
		if ( this.mbs[this.mbs.length-1] != null) {
			this.mbs = myGrow();
		}
	
		for (int i = 0; i < this.mbs.length; i++) {
			if (this.mbs[i] == null) {
				this.mbs[i] = addBooktoo;
				count++;
				break;//���� ��������
			}
		}

	}
	
	//�Զ�����
	public MyBook[] myGrow() {
		
		int newLength = this.mbs.length + (this.mbs.length >> 1);
		MyBook[] newBook = new MyBook[newLength];
		
		for (int i = 0; i < this.mbs.length; i++) {
			newBook[i] = this.mbs[i];
 		}
		
		return newBook;
	}

	// ɾ
	public void myDeleteBook(int id) {
		// �����Ϸ����ж�
		if (id <= 0) {
			System.out.println("��ɾ�������id������");
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

	// ��
	public void myAlterBook(int id) {
		// �����Ϸ����ж�
		if (id <= 0) {
			System.out.println("��ĵ����id������");
			return;
		}
		Scanner sc = new Scanner(System.in);
		int index = myFindBook(id);
		
		int flag = 1;
		if (index != -1) {
			while (true) {
				System.out.println("�Ȿ�����Ϣ���£�");
				System.out.println("������" + this.mbs[index].getBookName());
				System.out.println("���ߣ�" + this.mbs[index].getAuthor());
				System.out.println("�۸�" + this.mbs[index].getPrice());
				System.out.print("����������Ҫ�޸ĵ���Ϣ  :1.����; 2.����; 3.�۸�; 4. �˳��޸�");
				int choose = sc.nextInt();
				sc.nextLine();
				switch (choose) {
				case 1:
					System.out.println("����������Ҫ��������֣�");
					String name = sc.nextLine();
					this.mbs[index].setBookName(name);
					break;
				case 2:
					System.out.println("�������޸ĵ������������");
					String authorName = sc.nextLine();
					this.mbs[index].setAuthor(authorName);
					break;
				case 3:
					System.out.println("�������޸ĵ���ļ�Ǯ");
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

	// ��
	/*
	 * ͨ���������Id ��������
	 */
	public int myFindBook(int id) {
		// �����Ϸ����ж�
		if (id <= 0) {
			System.out.println("����ҵ����id������");
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

	// ��ʾͼ��
	public void show() {
		for (int i = 0; i < this.mbs.length; i++) {
			if (this.mbs[i] != null) {
				System.out.println("������ " + this.mbs[i].getBookName() + "���ߣ�" + this.mbs[i].getAuthor() + "�۸� "
						+ this.mbs[i].getPrice());
			}
		}
	}
	
	//select���� ����
	public void selectSort() {
		//������ĳ���
		int newLength = this.mbs.length;
		//����������
		MyBook[] newArray = new MyBook[newLength];
		//�Ѿ������е�Ԫ�ظ���������
		newArray = Arrays.copyOf(this.mbs, newLength);
		
		//ѡ������
		for (int i = 0; i < count - 1; i++) {
			int index = i;
			for (int j = i  + 1; j < count; j++) {
				if (newArray[index].getPrice() > newArray[j].getPrice()) {
					index = j;//��ȡ��Сֵ��С��
				}
					
			}
			if (index != i) {
				MyBook temp = newArray[index];
				newArray[index] = newArray[i];
				newArray[i] = temp;
			}//if
			
		}//for
		
		//�������
		for (int i = 0; i < newArray.length; i++) {
			if (this.mbs[i] != null) {
				System.out.println("������ " + newArray[i].getBookName() + "���ߣ�" + newArray[i].getAuthor() + "�۸� "
						+ newArray[i].getPrice());
			}
		}
		
	}
	
	//ð������ ������
		public void bubbleSort() {
			//������ĳ���
			int newLength = this.mbs.length;
			//����������
			MyBook[] newArray = new MyBook[newLength];
			//�Ѿ������е�Ԫ�ظ���������
			newArray = Arrays.copyOf(this.mbs, newLength);
			int flag = 0;
			//ð������ ����
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
			//�������
			for (int i = 0; i < newArray.length; i++) {
				if (this.mbs[i] != null) {
					System.out.println("������ " + newArray[i].getBookName() + "���ߣ�" + newArray[i].getAuthor() + "�۸� "
							+ newArray[i].getPrice());
				}
			}
			
		}

}

public class libraray {
	public static void main(String[] args) {
		BookManagement b = BookManagement.getInstance("�Ӳӵ�ͼ���");
		MyBook b1 = new MyBook("������", "β����һ��", 100);
		MyBook b2 = new MyBook("��Ӱ����", "������ʷ", 20);
		MyBook b3 = new MyBook("��ͥ��ʦ", "��Ұ��", 65);
		MyBook b4 = new MyBook("������", "��ɽ��", 22);
		MyBook b5 = new MyBook("������β��", "�浺��", 43);
		MyBook b6 = new MyBook("�����³³��", "�ȿ�����", 89);
		MyBook b7 = new MyBook("High DXD School", "ʯ̤һ��", 34);
		MyBook b8 = new MyBook("��������Ļ���", "�´�ֱ˾ ", 54);
		MyBook b9 = new MyBook("����", "��֪Ӣ��", 12);
		MyBook b10 = new MyBook("���ΰ���", "�����岩", 34);
		MyBook b11 = new MyBook("����", "�ñ�����", 32);
		MyBook b12 = new MyBook("����ʯ֮��", "�Բ���̫��", 45);

		
		
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
