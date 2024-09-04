import java.io.BufferedReader;
import java.io.IOException;

public class MatrizAdjacencia {
    private String[] vertices;
    private int[][] matriz;

    public MatrizAdjacencia(int vertices) {
        matriz = new int[vertices][vertices];
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void fillMatrix(BufferedReader br) throws IOException {
        String line;
        int row = 0;
        line =  br.readLine();
        vertices = line.split(",");
        while ((line = br.readLine()) != null) {
            String[] numbers = line.split(",");
            for (int col = 0; col < numbers.length; col++) {
                matriz[row][col] = Integer.parseInt(numbers[col]);
            }
            row++;
        }
    }

    public void printMatrix() {
        for (int i = 0; i < vertices.length; i++) {
            System.out.print(vertices[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int isCompleto() {
        for (int i = 1; i < matriz.length; i++) {
            int grau = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] != 0) {
                    grau++;
                }
            }
            if (grau != matriz.length-1) {
                return 0;
            }
        }
        return matriz.length;
    }

    public int isRegularRecepcao() {
        int grau = 0;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0] != 0) {
                grau++;
            }
        }
        for (int i = 1; i < matriz.length; i++) {
            int grauVertice = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[j][i] != 0) {
                    grauVertice++;
                }
            }
            if (grauVertice != grau) {
                return 0;
            }
        }
        return grau;
    }

    public int isRegularEmissao() {
        int grau = 0;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[0][i] != 0) {
                grau++;
            }
        }
        for (int i = 1; i < matriz.length; i++) {
            int grauVertice = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] != 0) {
                    grauVertice++;
                }
            }
            if (grauVertice != grau) {
                return 0;
            }
        }
        return grau;
    }

    public boolean isDigrafo() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSimples() {
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[i][i] != 0) {
                return false;
            }
        }
        return true;
    }

    public int isRegular() {
        int grau = 0;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[0][i] != 0) {
                grau++;
            }
        }
        for (int i = 1; i < matriz.length; i++) {
            int grauVertice = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] != 0) {
                    grauVertice++;
                }
            }
            if (grauVertice != grau) {
                return 0;
            }
        }
        return grau;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public String[] getVertices() {
        return vertices;
    }
}
