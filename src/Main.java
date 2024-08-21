import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "dados.txt";
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int n = getNumberOfVertices(br);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deseja transformar em: [1] matriz [2] lista?");
        int choice = scanner.nextInt();

        if (choice == 1) {
            MatrizAdjacencia MA = new MatrizAdjacencia(n);
            MA.fillMatrix(br);
            MA.printMatrix();
            System.out.println("eh digrafo: "+ MA.isDigrafo());
            System.out.println("eh simples: "+ MA.isSimples());
            if(MA.isDigrafo()) {
                System.out.println("eh regular emissao: "+ MA.isRegularEmissao());
                System.out.println("eh regular recepcao: "+ MA.isRegularRecepcao());
            } else {
                System.out.println("eh regular: "+ MA.isRegular());
            }
            System.out.println("eh completo: "+ MA.isCompleto());
        } else if (choice == 2) {
            br.close();
            br = new BufferedReader(new FileReader(filePath));
            List list = new List();
            list.fillList(br);
            list.printList();
            System.out.println("eh digrafo: "+ list.isDigrafo());
            System.out.println("eh simples: "+ list.isSimples());
            if(list.isDigrafo()) {
                System.out.println("eh regular emissao: "+ list.isRegularEmissao());
                System.out.println("eh regular recepcao: "+ list.isRegularRecepcao());
            } else {
                System.out.println("eh regular: "+ list.isRegular());
            }
            System.out.println("eh completo: "+ list.isCompleto());
        } else {
            System.out.println("Opção inválida.");
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
        Node current = new Node(), head = current;
        Node aimNode;
        char vertice = 'A';
        for(int i=0; i<n; i++) {
            current.setValue(vertice++);
            aimNode = current.getAim();
            aimNode = new Node();
            for(int j=0; j<n; j++) {
                if(matriz[i][j]!=0) {
//                    aimNode.setAim(vertices[i].charAt(0),0,j);
                }
            }
        }
        return new List();
    }
}