/*
    Guilherme Teodoro de Oliveira RA: 10425362
    Luís Henrique Ribeiro Fernandes RA: 10420079
    Vinícius Brait Lorimier RA: 10420046
*/

// Classe que representa o operador de adição na árvore de expressão
public class AdditionNode extends OperatorNode {

    // Construtor que define os filhos esquerdo e direito do nó
    public AdditionNode(Node left, Node right) {
        super(left, right);
    }

    // Realiza a operação de adição entre os valores dos nós filhos
    @Override
    public Float visit() {
        return left.visit() + right.visit();
    }

    // Retorna a representação textual do operador
    @Override
    public String display() {
        return "+";
    }
}
