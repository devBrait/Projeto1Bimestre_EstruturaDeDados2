/*
    Guilherme Teodoro de Oliveira RA: 10425362
    Luís Henrique Ribeiro Fernandes RA: 10420079
    Vinícius Brait Lorimier RA: 10420046
*/

// Classe que representa o operador de multiplicação na árvore de expressão
public class MultiplicationNode extends OperatorNode {

    // Construtor que recebe os nós filho esquerdo e direito da operação
    public MultiplicationNode(Node left, Node right) {
        super(left, right);
    }

    // Realiza a multiplicação entre os valores dos filhos esquerdo e direito
    @Override
    public Float visit() {
        return left.visit() * right.visit();
    }

    // Retorna a representação textual do operador
    @Override
    public String display() {
        return "*";
    }
}
