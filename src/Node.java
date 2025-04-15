/*
    Guilherme Teodoro de Oliveira RA: 10425362
    Luís Henrique Ribeiro Fernandes RA: 10420079
    Vinícius Brait Lorimier RA: 10420046
*/

// Classe abstrata que representa um nó genérico da árvore de expressão
public abstract class Node {

    // Referências para os nós filhos (esquerdo e direito)
    protected Node left, right;

    // Construtor que define os filhos do nó
    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    // Método que será sobrescrito pelas subclasses para calcular o valor da expressão
    // Por padrão, retorna NaN
    public Float visit() {
        return Float.NaN;
    }

    // Método abstrato que será implementado pelas subclasses para retornar a representação textual do nó
    abstract public String display();

    // Retorna o filho à esquerda
    public Node getLeft() {
        return left;
    }

    // Retorna o filho à direita
    public Node getRight() {
        return right;
    }

    // Define o filho à esquerda
    public void setLeft(Node node) {
        this.left = node;
    }

    // Define o filho à direita
    public void setRight(Node node) {
        this.right = node;
    }
}
