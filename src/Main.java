/*
    Guilherme Teodoro de Oliveira RA: 10425362
    Luís Henrique Ribeiro Fernandes RA: 10420079
    Vinícius Brait Lorimier RA: 10420046

    Referências:
    https://www.geeksforgeeks.org/convert-infix-expression-to-postfix-expression/
    https://www.baeldung.com/java-convert-infix-to-postfix-expressions
    https://www.baeldung.com/java-print-binary-tree-diagram
    https://codingtechroom.com/tutorial/java-java-print-binary-tree-diagram
    https://www.geeksforgeeks.org/expression-tree/
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree(); // Instancia a árvore que será usada para representar e processar a expressão
        Scanner sc = new Scanner(System.in); // Scanner para ler entradas do usuário
        boolean endProgram = false, op1Ready = false, op2Ready = false; // Flags de controle para o fluxo do programa
        String input = null; // Armazena a expressão fornecida pelo usuário

        do {
            try {
                // Tenta limpar o console de forma compatível com o sistema operacional
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                }
            } catch (Exception e) {
                System.out.println("Erro ao limpar o console.");
            }

            // Mostra o cabeçalho e as opções do menu
            Menu.start();
            Menu.showMenu();

            System.out.print("Opção: ");
            String option = sc.nextLine().trim(); // Lê a opção do usuário e remove espaços extras

            switch(option) {
                case "1":
                    // Opção 1: Leitura da expressão infixa
                    System.out.print("Digite a expressão a ser executada: ");
                    input = sc.nextLine();
                    op1Ready = true; // Marca que a expressão foi recebida
                    System.out.print("Expressão recebida. Pressione ENTER para continuar.");
                    sc.nextLine(); // Aguarda o usuário pressionar ENTER
                    break;

                case "2":
                    // Opção 2: Conversão da expressão e criação da árvore
                    if (op1Ready) {
                        try {
                            String postFix = ExpressionHandler.convertExpression(input); // Converte para pós-fixa
                            if (postFix != null) {
                                try {
                                    tree.insertExpression(postFix); // Tenta criar a árvore
                                    op2Ready = true;
                                    System.out.print("Árvore criada! Pressione ENTER para continuar: ");
                                } catch (IllegalStateException | IllegalArgumentException e) {
                                    System.out.println("Erro ao criar a árvore → " + e.getMessage());
                                    System.out.print("Pressione ENTER para continuar: ");
                                }
                            }
                        } catch (InvalidExpressionException e) {
                            System.out.println("Erro ao converter a expressão → " + e.getMessage());
                            System.out.print("Pressione ENTER para continuar: ");
                        }
                    } else {
                        System.out.println("Erro: Expressão não inserida. Utilize a opção 1.");
                        System.out.print("Pressione ENTER para continuar: ");
                    }
                    sc.nextLine();
                    break;
                    
                case "3":
                    // Opção 3: Exibição da árvore e suas travessias
                    if (op2Ready) {
                        tree.printTree(); // Exibe a estrutura da árvore
                        System.out.println();
                        tree.printPreOrder(); // Travessia pré-ordem
                        System.out.println();
                        tree.printInOrder(); // Travessia in-ordem
                        System.out.println();
                        tree.printPostOrder(); // Travessia pós-ordem
                    } else {
                        System.out.println("Erro: Árvore não foi criada. Utilize a opção 2.");
                    }
                    System.out.print("\nPressione ENTER para continuar.");
                    sc.nextLine();
                    break;

                case "4":
                    // Opção 4: Avaliação da expressão representada na árvore
                    if (op2Ready) {
                        try {
                            Float value = tree.processExpression(); // Calcula o valor da expressão
                            System.out.println("Resultado = " + value);
                        } catch (ArithmeticException e) {
                            System.out.println("Erro ao processar a expressão → " + e.getMessage());
                        }
                    } else {
                        System.out.println("Erro: Árvore não foi criada. Utilize a opção 2.");
                    }
                    System.out.print("Pressione ENTER para continuar.");
                    sc.nextLine();
                    break;

                case "5":
                    // Opção 5: Encerrar o programa
                    System.out.print("Programa encerrado! Pressione ENTER para continuar.");
                    sc.nextLine();
                    endProgram = true;
                    break;

                default:
                    // Tratamento para opções inválidas
                    System.out.println("Erro: Opção inválida! Escolha um número entre 1 e 5.");
                    System.out.print("Pressione ENTER para continuar.");
                    sc.nextLine();
                    break;
            }
        } while (!endProgram); // Loop continua até o usuário escolher encerrar

        sc.close(); // Fecha o scanner ao final da execução
    }
}