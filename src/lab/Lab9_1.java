/* 
 * 파일명: Lab9_1.java
 * 작성일: 2017.11.18
 * 작성자: 마재희
 * 설명: int형 키값을 저장하는 이진탐색트리를 생성하고 연산을 수행하는 프로그램 
 */
package lab;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Lab9_1 {
	public static void main(String[] args) {
		System.out.println("lab9_1 : 마재희");

		// 공백 이진탐색트리 tree를 생성
		MyBinarySearchTree tree = new MyBinarySearchTree();

		Scanner scan = new Scanner(System.in);
		System.out.println("메뉴 번호를 입력하세요.");
		int menu = 0;
		int item = 0;

		// 종료를 선택할 때까지 반복하여 메뉴를 제공하고 그에 맞는 트리 연산을 수행
		do {
			System.out.print("1:전체조회 2:재귀삽입 3:후위순회 4:검색 5:최대값조회 6:삽입 7:삭제 9:종료 --->");
			menu = scan.nextInt();
			switch (menu) {
			case 1:
				System.out.println("전체조회를 수행합니다.");
				System.out.println(tree.toString()); // (1)
				break;
			case 2:
				System.out.println("재귀 알고리즘을 이용한 삽입을 수행합니다.");
				System.out.print("삽입할 정수 입력:"); // (2)
				item = scan.nextInt();
				tree.insert(item);
				break;
			case 3:
				System.out.println("후위순회를 수행합니다.");
				tree.printPostorder(); // (3)
				break;
			// case 4:
			// System.out.println("검색을 수행합니다.");
			// System.out.print("검색할 정수 입력:");
			// item = scan.nextInt();
			// if(tree.contains(item)) // (4)
			// System.out.println("검색 성공");
			// else
			// System.out.println("검색 실패");
			// break;
			// case 5:
			// System.out.println("최대값 조회를 수행합니다.");
			// System.out.println(tree.max()); // (5)
			// break;
			// case 6:
			// System.out.println("반복 알고리즘을 이용한 삽입을 수행합니다.");
			// System.out.print("삽입할 정수 입력:");
			// item = scan.nextInt();
			// if(tree.add(item)) // (6)
			// System.out.println("삽입 성공");
			// else
			// System.out.println("삽입 실패");
			// break;
			// case 7:
			// System.out.println("삭제를 수행합니다.");
			// System.out.print("삭제할 정수 입력:");
			// item = scan.nextInt();
			// if(tree.remove(item)) // (7)
			// System.out.println("삭제 성공");
			// else
			// System.out.println("삭제 실패");
			//
			// break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
				System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
			}
		} while (menu != 9);
	}
}

class MyBinarySearchTree {
	// 트리의 루트 노드를 가리킬 인스턴스 변수 root 선언하고 null로 초기화
	// (가) ...
	private Node root = null;

	// 트리의 노드를 표현하는 클래스 - 필드(int형 key, leftChild, rightChild), 생성자(key값을 매개변수하여
	// 초기화)
	private class Node {
		// (나) ...
		int key;
		Node leftChild;
		Node rightChild;

		public Node(int key) {
			super();
			this.key = key;
			this.leftChild = null;
			this.rightChild = null;
		}

	}

	// (1) 전체조회를 위한 toString() 오버라이드
	@Override
	public String toString() {
		String result = inorder(root);
		return result;
	}

	// p를 루트로 하는 트리를 중위 순회하며 키값을 문자열로 리턴하는 메소드(재귀 알고리즘)
	private String inorder(Node p) {
		String result = "";
		if (p != null) {
			result += inorder(p.leftChild);
			result += p.key + " ";
			result += inorder(p.rightChild);
		}
		return result;
	}

	// (2) 재귀 삽입
	public void insert(int key) {
		root = insertKey(root, key);
	}

	// p를 루트로 하는 트리에 key를 삽입하는 메소드 (재귀 알고리즘)
	private Node insertKey(Node p, int key) {
		if (p == null) {
			Node newNode = new Node(key);
			return newNode;
		} else if (key < p.key) {
			p.leftChild = insertKey(p.leftChild, key);
			return p; // 루트 불변
		} else if (key > p.key) {
			p.rightChild = insertKey(p.rightChild, key);
			return p; // 루트 불변
		} else { // key = p.key 인 경우 삽입 실패
			System.out.println("Insertion fail! key duplication : " + key);
			return p; // 루트 불변
		}
	}

	// (3) 후위 순회
	public void printPostorder() {
		postorder(root);
		System.out.println();
	}

	// p를 루트로 하는 트리를 후위 순회하며 키값을 출력하는 메소드
	private void postorder(Node p) {

		// (다) ...
		if (p != null) {
			postorder(p.leftChild);
			postorder(p.rightChild);
			System.out.print(p.key + " ");
		}
	}
}
