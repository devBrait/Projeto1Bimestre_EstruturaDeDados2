/*
    Guilherme Teodoro de Oliveira RA: 10425362
    Luís Henrique Ribeiro Fernandes RA: 10420079
    Vinícius Brait Lorimier RA: 10420046
*/

// Classe abstrata que representa um nó operador (como +, -, *, /) em uma árvore de expressão
abstract public class OperatorNode extends Node {

    // Construtor que define os nós filhos esquerdo e direito (operadores precisam de dois operandos)
    public OperatorNode(Node left, Node right) {
        super(left, right);
    }

    // Método abstrato que deve ser implementado por subclasses para exibir o operador (ex: "+", "*")
    abstract public String display();
}
