/*
    Guilherme Teodoro de Oliveira RA: 10425362
    Luís Henrique Ribeiro Fernandes RA: 10420079
    Vinícius Brait Lorimier RA: 10420046
*/

import java.util.Stack;

public class Tree {
    private Node root; // Raiz da árvore de expressão
    
    // Constrói a árvore a partir de uma expressão em notação pós-fixa (RPN)
    public void insertExpression(String expression) {
        Stack<Node> stack = new Stack<>();
        String[] tokens = expression.split("\\s+");
        
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            
            switch (token) {
                case "+": {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Expressão mal formada: operandos insuficientes para operador '+'");
                    }
                    Node right = stack.pop();
                    Node left = stack.pop();
                    stack.push(new AdditionNode(left, right));
                    break;
                }
                case "-": {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Expressão mal formada: operandos insuficientes para operador '-'");
                    }
                    Node right = stack.pop();
                    Node left = stack.pop();
                    stack.push(new SubtractionNode(left, right));
                    break;
                }
                case "*": {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Expressão mal formada: operandos insuficientes para operador '*'");
                    }
                    Node right = stack.pop();
                    Node left = stack.pop();
                    stack.push(new MultiplicationNode(left, right));
                    break;
                }
                case "/": {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Expressão mal formada: operandos insuficientes para operador '/'");
                    }
                    Node right = stack.pop();
                    Node left = stack.pop();
                    stack.push(new DivisionNode(left, right));
                    break;
                }
                case "N": {
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Expressão mal formada: operando ausente para operador unário.");
                    }
                    Node child = stack.pop();
                    stack.push(new UnaryMinusNode(child));
                    break;
                }
                default: {
                    try {
                        Float value = Float.parseFloat(token);
                        stack.push(new OperandNode(value));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Erro ao converter número: " + token);
                    }
                }
            }
        }
    
        if (stack.size() != 1) {
            throw new IllegalStateException("Expressão inválida: número incorreto de operandos e operadores.");
        }
    
        root = stack.pop();
    }    

    // Avalia o valor da expressão representada pela árvore
    public Float processExpression() {
        if (root == null) throw new IllegalStateException("Nenhuma expressão foi inserida.");
        return root.visit();
    }

    // Exibe a estrutura hierárquica da árvore
    public void printTree() {
        printTree(root, "", false);
    }

    private void printTree(Node node, String indent, boolean last) {
        if (node == null) return;

        System.out.println(indent + (last ? "└── " : "├── ") + node.display());
        indent += last ? "    " : "|   ";

        if (node instanceof OperatorNode) {
            printTree(((OperatorNode) node).getLeft(), indent, false);
            printTree(((OperatorNode) node).getRight(), indent, true);
        } else if (node instanceof UnaryMinusNode) {
            printTree(((UnaryMinusNode) node).getLeft(), indent, true);
        }
    }

    // Imprime a árvore em pré-ordem (nó → esquerda → direita)
    public void printPreOrder() {
        System.out.print("Pré-Ordem: ");
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(Node node) {
        if (node == null) return;

        System.out.print(node.display() + " ");
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
    }

    // Imprime a árvore em ordem infixa com parênteses para manter a precedência
    public void printInOrder() {
        System.out.print("Em ordem: ");
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node node) {
        if (node == null) return;

        if (node instanceof UnaryMinusNode) {
            System.out.print("-(");
            printInOrder(((UnaryMinusNode) node).getLeft());
            System.out.print(")");
        } else {
            if (node instanceof OperatorNode) System.out.print("( ");
            printInOrder(node.getLeft());
            System.out.print(node.display() + " ");
            printInOrder(node.getRight());
            if (node instanceof OperatorNode) System.out.print(") ");
        }
    }

    // Imprime a árvore em pós-ordem (esquerda → direita → nó)
    public void printPostOrder() {
        System.out.print("Pós-ordem: ");
        printPostOrder(root);
        System.out.println();
    }

    private void printPostOrder(Node node) {
        if (node == null) return;
        printPostOrder(node.getLeft());
        printPostOrder(node.getRight());
        System.out.print(node.display() + " ");
    }

    // Reseta a árvore para estado vazio
    public void resetTree() {
        root = null;
    }
}
