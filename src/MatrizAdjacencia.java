import java.io.BufferedReader;
import java.io.IOException;

public class MatrizAdjacencia {
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
        while ((line = br.readLine()) != null) {
            String[] numbers = line.split(",");
            for (int col = 0; col < numbers.length; col++) {
                matriz[row][col] = Integer.parseInt(numbers[col]);
            }
            row++;
        }
    }

    public void printMatrix() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isCompleto() {
        for (int i = 1; i < matriz.length; i++) {
            int grau = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] != 0) {
                    grau++;
                }
            }
            if (grau != matriz.length-1) {
                return false;
            }
        }
        return true;
    }

    public boolean isRegularRecepcao() {
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
                return false;
            }
        }
        return true;
    }

    public boolean isRegularEmissao() {
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
                return false;
            }
        }
        return true;
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

    public boolean isRegular() {
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
                return false;
            }
        }
        return true;
    }
}
