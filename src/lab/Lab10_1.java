package lab;

/* 
 * 파일명: Lab10_1.java
 * 작성일: 2017.11.24
 * 작성자: 홍길동
 * 설명: 무방향 그래프를 생성하고 연산을 수행하는 프로그램 
 */

import java.util.Scanner;

public class Lab10_1 {
	public static void main(String[] args) {
		System.out.println("lab10_1 : 마재희");

		// 정점 수 n 입력
		Scanner scan = new Scanner(System.in);
		System.out.print("정점 수 입력: ");
		int n = scan.nextInt();

		// 정점 수가 n인 무방향 그래프를 생성
		MatrixGraph graph = new MatrixGraph(n);

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
			// case 3:
			// System.out.println("깊이우선탐색 순서대로 정점을 출력합니다.");
			// graph.depthFirstSearch();
			// break;
			// case 4:
			// System.out.println("너비우선탐색 순서대로 정점을 출력합니다.");
			// graph.breadthFirstSearch();
			// break;
			case 5:
				System.out.println("종료합니다.");
				break;
			default:
				System.out.println("메뉴 번호 오류 - 메뉴를 다시 선택하세요.");
			}
		} while (menu != 5);
	}
}

class MatrixGraph {

	private int max;
	private int[][] matrix;

	public MatrixGraph(int v) {
		super();
		this.max = v;
		this.matrix = new int[max][max];
	}

	public void addEdge(int v1, int v2) {
		if (v2 < 0 || v1 < 0 || v2 > max - 1 || v1 > max - 1)
			System.out.println("잘못된 정점 번호입니다.");
		else if (matrix[v1][v2] == 1 || matrix[v2][v1] == 1)
			System.out.println("이미 존재하는 간선입니다");
		else {
			matrix[v1][v2] = 1;
			matrix[v2][v1] = 1;
		}
	}

	public void printAdjacentVertices(int v) {
		if (v < 0 || v > max - 1)
			System.out.println("잘못된 정점 번호입니다.");
		else {
			StringBuffer str = new StringBuffer(v+"에 인접한 정점 =");// 스트링버퍼생성

			for (int i = 0; i < max; i++) {
				if (matrix[v][i] == 1)
					str.append(i + " ");
			}
			System.out.println(str.toString());
		}
	}

}