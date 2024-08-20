import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "c:\\Grafos\\dados.txt";
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
}