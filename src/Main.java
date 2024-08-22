import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "dados.txt";
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int n = getNumberOfVertices(br);
        br.close();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deseja transformar em: [1] matriz [2] lista?");
        int choice = scanner.nextInt();
        while(choice==2 || choice==1) {
            if (choice == 1) {
                br = new BufferedReader(new FileReader(filePath));
                MatrizAdjacencia MA = new MatrizAdjacencia(n);
                MA.fillMatrix(br);
                MA.printMatrix();
                System.out.println("Deseja transformar em lista [1] ou analisar a matriz [2]?");
                choice = scanner.nextInt();
                if (choice == 2) {
                    System.out.println("eh digrafo: " + MA.isDigrafo());
                    System.out.println("eh simples: " + MA.isSimples());
                    if (MA.isDigrafo()) {
                        System.out.println("eh regular emissao: " + MA.isRegularEmissao());
                        System.out.println("eh regular recepcao: " + MA.isRegularRecepcao());
                    } else {
                        System.out.println("eh regular: " + MA.isRegular());
                    }
                    System.out.println("eh completo: " + MA.isCompleto());
                } else if (choice == 1) {
                    List list = matrizToList(MA, n);
                    list.printList();
                } else {
                    System.out.println("Opção inválida.");
                }
            } else if (choice == 2) {
                br = new BufferedReader(new FileReader(filePath));
                List list = new List();
                list.fillList(br);
                list.printList();
                System.out.println("Deseja transformar em matriz [1] ou analisar a lista [2]?");
                choice = scanner.nextInt();
                if (choice == 2) {
                    System.out.println("eh digrafo: " + list.isDigrafo());
                    System.out.println("eh simples: " + list.isSimples());
                    if (list.isDigrafo()) {
                        System.out.println("eh regular emissao: " + list.isRegularEmissao());
                        System.out.println("eh regular recepcao: " + list.isRegularRecepcao());
                    } else {
                        System.out.println("eh regular: " + list.isRegular());
                    }
                    System.out.println("eh completo: " + list.isCompleto());
                } else if (choice == 1) {
                    MatrizAdjacencia MA = listToMatriz(list, n);
                    MA.printMatrix();
                } else {
                    System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Opção inválida.");
            }
            System.out.println("Deseja transformar em: [1] matriz [2] lista?");
            choice = scanner.nextInt();
        }
    }


    public static int getNumberOfVertices(BufferedReader br) throws IOException {
        int count = 0;
        String line = br.readLine();
        if (line != null) {
            String[] vertices = line.split(",");
            count = vertices.length;
        }
        return count;
    }

    public static MatrizAdjacencia listToMatriz(List list, int n) {
        MatrizAdjacencia MA = new MatrizAdjacencia(n);
        int[][] matriz = new int[n][n];
        Node current = list.getHead();
        Node aimNode;
        int index = 0, column;
        while (current != null) {
            aimNode = current.getAim();
            while (aimNode != null) {
                column = (int)aimNode.getValue()-65;
                matriz[index][column] = 1; //aimNode.getDistance();
                aimNode = aimNode.getAim();
            }
            index++;
            current = current.getNext();
        }
        MA.setMatriz(matriz);
        return MA;
    }

    public static List matrizToList(MatrizAdjacencia MA, int n) {
        int[][] matriz = MA.getMatriz();
        List list = new List();
        for (char vertice = 0; vertice < n; vertice++) {
            list.addNext((char)(vertice+65),0);
        }
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if (matriz[i][j]!=0) {
                    list.addAim((char)(j+65), 0,i);
                }
            }
        }
        return list;
    }
}