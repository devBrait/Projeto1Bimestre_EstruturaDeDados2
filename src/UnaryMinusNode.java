/*
    Guilherme Teodoro de Oliveira RA: 10425362
    Luís Henrique Ribeiro Fernandes RA: 10420079
    Vinícius Brait Lorimier RA: 10420046
*/

// Classe que representa o operador unário de negação na árvore de expressão
public class UnaryMinusNode extends OperatorNode {

    // Construtor que recebe o nó filho esquerdo (único filho) da operação unária
    public UnaryMinusNode(Node left) {
        super(left, null); // apenas o filho esquerdo é usado
    }

    // Retorna o valor negado do filho
    @Override
    public Float visit() {
        return -left.visit();
    }

    // Retorna a representação textual do operador
    @Override
    public String display() {
        return "-";
    }
}
