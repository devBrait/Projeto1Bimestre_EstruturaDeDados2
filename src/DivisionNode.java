/*
    Guilherme Teodoro de Oliveira RA: 10425362
    Luís Henrique Ribeiro Fernandes RA: 10420079
    Vinícius Brait Lorimier RA: 10420046
*/

// Classe que representa o operador de divisão na árvore de expressão
public class DivisionNode extends OperatorNode {

    // Construtor que inicializa os nós filhos esquerdo e direito
    public DivisionNode(Node left, Node right) {
        super(left, right);
    }

    // Realiza a operação de divisão entre os valores dos nós filhos
    @Override
    public Float visit() {
        // Lança exceção se houver tentativa de divisão por zero
        if (right.visit() == 0) throw new ArithmeticException("Divisão por zero.");
        return left.visit() / right.visit();
    }

    // Retorna a representação textual do operador
    @Override
    public String display() {
        return "/";
    }
}
