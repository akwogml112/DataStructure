package lab;

import java.util.LinkedList;

/* 
 * 파일명: hw10_3.java
 * 작성일: 2017.12.05
 * 작성자: 마재희
 * 설명: adjacency list로 구현한 그래프의 DFS, BFS를 이해한다
 */

import java.util.Scanner;

public class Hw10_3 {
	public static void main(String[] args) {
		System.out.println("hw10_1 : 마재희");

		// 정점 수 n 입력
		Scanner scan = new Scanner(System.in);
		System.out.print("정점 수 입력: ");
		int n = scan.nextInt();

		// 정점 수가 n인 무방향 그래프를 생성
		ListGraph2 graph = new ListGraph2(n);

		System.out.println("\n메뉴 번호를 입력하세요.");
		int menu = 0;
		int item = 0;
		int v1 = 0;
		int v2 = 0;

		// 종료를 선택할 때까지 반복하여 메뉴를 제공하고 그에 맞는 그래프 연산을 수행
		do {
			System.out.print("\n1:간선삽입 2:인접정점출력 3:DFS 4:BFS 5:종료 --->");
			menu = scan.nextInt();
			switch (menu) {
			case 1:
				System.out.print("정점 v1 입력: ");
				v1 = scan.nextInt();
				System.out.print("정점 v2 입력: ");
				v2 = scan.nextInt();
				graph.addEdge(v1, v2);
				break;
			case 2:
				System.out.print("정점 입력:");
				v1 = scan.nextInt();
				graph.printAdjacentVertices(v1);
				break;
			case 3:
				System.out.println("깊이우선탐색 순서대로 정점을 출력합니다.");
				graph.depthFirstSearch();
				break;
			case 4:
				System.out.println("너비우선탐색 순서대로 정점을 출력합니다.");
				graph.breadthFirstSearch();
				break;
			case 5:
				System.out.println("종료합니다.");
				break;
			default:
				System.out.println("메뉴 번호 오류 - 메뉴를 다시 선택하세요.");
			}
		} while (menu != 5);
	}
}

class ListGraph2 {

	private int max;
	private Node[] matrix;
	private boolean[] visited;
	private LinkedList<Integer> queue = new LinkedList<Integer>();

	public ListGraph2(int v) {
		super();
		this.max = v;
		this.matrix = new Node[max];
		this.visited = new boolean[max];
	}

	public void addEdge(int v1, int v2) {
		if (v2 < 0 || v1 < 0 || v2 > max - 1 || v1 > max - 1)
			System.out.println("잘못된 정점 번호입니다.");
		else {
			if (matrix[v1] == null)
				matrix[v1] = new Node(v2);
			else {
				Node temp = matrix[v1];
				while (temp.link != null) {
					if (temp.v == v2) {
						System.out.println("이미 존재하는 간선입니다");
						return;
					} else
						temp = temp.link;
				}
				temp.link = new Node(v2);
			}
			if (matrix[v2] == null)
				matrix[v2] = new Node(v1);
			else {
				Node temp2 = matrix[v2];
				while (temp2.link != null) {
					if (temp2.v == v1) {
						System.out.println("이미 존재하는 간선입니다");
						return;
					} else
						temp2 = temp2.link;
				}
				temp2.link = new Node(v1);
			}
		}

	}

	public void breadthFirstSearch() {

		for (int i = 0; i < visited.length; ++i) {
			visited[i] = false;
		}

		for (int j = 0; j < visited.length; ++j) {
			if (visited[j] == false)
				bfs(j);
		}
	}

	public void bfs(int v) {

		visited[v] = true;
		System.out.println(v + " ");

		queue.addLast(v);

		while (!queue.isEmpty()) {
			int w = queue.removeFirst();
			Node temp = matrix[w];
			while (temp != null) {
				if (visited[temp.v] == false) {
					visited[temp.v] = true;
					System.out.println(temp.v);
					queue.addLast(temp.v);
				}
				temp = temp.link;
			}
		}
	}

	public void depthFirstSearch() {

		for (int i = 0; i < visited.length; ++i) {
			visited[i] = false;
		}

		for (int j = 0; j < visited.length; ++j) {
			if (visited[j] == false)
				dfs(j);
		}
	}

	public void dfs(int v) {

		visited[v] = true;
		System.out.println(v + " ");

		Node temp = matrix[v];

		while (temp != null) {
			if (visited[temp.v] == false)
				dfs(temp.v);
			temp = temp.link;
		}
	}

	public void printAdjacentVertices(int v) {
		if (v < 0 || v > max - 1)
			System.out.println("잘못된 정점 번호입니다.");
		else {
			StringBuffer str = new StringBuffer(v + "에 인접한 정점 =");// 스트링버퍼생성
			Node temp = matrix[v];
			while (temp != null) {
				str.append(temp.v + " ");
				temp = temp.link;
			}
			System.out.println(str.toString());
		}
	}

	private class Node {
		int v;
		Node link;

		Node(int data) {
			this.v = data;
			this.link = null;
		}
	}
}