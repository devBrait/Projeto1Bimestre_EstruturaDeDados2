/*
    Guilherme Teodoro de Oliveira RA: 10425362
    Luís Henrique Ribeiro Fernandes RA: 10420079
    Vinícius Brait Lorimier RA: 10420046
*/

// Classe que representa o operador de subtração na árvore de expressão
public class SubtractionNode extends OperatorNode {

    // Construtor que recebe os nós filho esquerdo e direito da operação
    public SubtractionNode(Node left, Node right) {
        super(left, right);
    }

    // Realiza a subtração entre os valores dos filhos esquerdo e direito
    @Override
    public Float visit() {
        return left.visit() - right.visit();
    }

    // Retorna a representação textual do operador
    @Override
    public String display() {
        return "-";
    }
}
