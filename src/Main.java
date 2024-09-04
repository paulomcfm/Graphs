import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Tree tree = new Tree();
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
                    System.out.println("É dígrafo: " + MA.isDigrafo());
                    System.out.println("É simples: " + MA.isSimples());
                    if(MA.isSimples()){
                        if (MA.isDigrafo()) {
                            if(MA.isRegularRecepcao()!=0)
                                System.out.println("Dígrafo regular de recepção grau: " + MA.isRegularRecepcao());
                            else
                                System.out.println("Não é regular de recepção.");
                            if(MA.isRegularEmissao()!=0)
                                System.out.println("Dígrafo regular de emissão grau: " + MA.isRegularEmissao());
                            else
                                System.out.println("Não é regular de emissão.");
                        } else {
                            if(MA.isRegular()!=0)
                                System.out.println("Grafo " + MA.isRegular()+"-regular.");
                            else
                                System.out.println("Não é regular.");
                        }
                    }
                    if(MA.isCompleto()!=0)
                        System.out.println("O grafo é k"+MA.isCompleto()+".");
                    else
                        System.out.println("Não é completo.");
                    tree.generateTree(MA);
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
                    System.out.println("É dígrafo: " + list.isDigrafo());
                    System.out.println("É simples: " + list.isSimples());
                    if(list.isSimples()){
                        if (list.isDigrafo()) {
                            if(list.isRegularRecepcao()!=0)
                                System.out.println("Dígrafo regular de recepção grau: " + list.isRegularRecepcao());
                            else
                                System.out.println("Não é regular de recepção.");
                            if(list.isRegularEmissao()!=0)
                                System.out.println("Dígrafo regular de emissão grau: " + list.isRegularEmissao());
                            else
                                System.out.println("Não é regular de emissão.");
                        } else {
                            if(list.isRegular()!=0)
                                System.out.println("Grafo " + list.isRegular()+"-regular.");
                            else
                                System.out.println("Não é regular.");
                        }
                    }
                    System.out.println("É completo: " + list.isCompleto());
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
                matriz[index][column] = aimNode.getDistance();
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
                    list.addAim((char)(j+65), matriz[i][j],i);
                }
            }
        }
        return list;
    }
}