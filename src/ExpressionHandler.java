import java.util.Stack;

public class ExpressionHandler {

    // Converte uma expressão infixa para pós-fixa (notação polonesa reversa)
    public static String convertExpression(String expression) throws InvalidExpressionException {
        Stack<Character> operatorStack = new Stack<>();
        Stack<Integer> unaryStack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        // Remove todos os espaços da expressão
        expression = expression.replaceAll("\\s", "");

        // Verifica se a expressão está vazia
        if (expression.isEmpty()) {
            throw new InvalidExpressionException("Expressão vazia.");
        }

        // Verifica se o último caractere é um operador
        if (isOperator(expression.charAt(expression.length() - 1))) {
            throw new InvalidExpressionException("Expressão não pode terminar com um operador.");
        }

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Verifica se o caractere é um operador inválido
            if (c == '^' || c == '%' || c == '=') {
                throw new InvalidExpressionException("Operador inválido: '" + c + "'");
            }

            // Número ou sinal unário negativo
            if (Character.isDigit(c) || (c == '-' && (i == 0 || isOperator(expression.charAt(i - 1)) || expression.charAt(i - 1) == '('))) {
                int unaryCount = 0;

                // Conta quantos '-' unários em sequência
                while (i < expression.length() && expression.charAt(i) == '-') {
                    unaryCount++;
                    i++;
                }

                // Se houver parêntese após os unários, abre subexpressão
                if (i < expression.length() && expression.charAt(i) == '(') {
                    operatorStack.push('(');
                    unaryStack.push(unaryCount);
                    continue;
                }

                // Captura número (inteiro ou decimal)
                StringBuilder number = new StringBuilder();
                while (i < expression.length() &&
                        (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i));
                    i++;
                }
                i--; // Corrige o índice

                postfix.append(number).append(" ");

                // Adiciona "N" para cada unário negativo
                for (int j = 0; j < unaryCount; j++) {
                    postfix.append("N ");
                }

            } else if (isOperator(c)) {
                // Verificações separadas para diferentes erros de operadores
                if (i == 0) {
                    throw new InvalidExpressionException("Operador no início da expressão.");
                }
                if (isOperator(expression.charAt(i - 1))) {
                    throw new InvalidExpressionException("Dois operadores seguidos.");
                }
                if (expression.charAt(i - 1) == '(') {
                    throw new InvalidExpressionException("Operador imediatamente após parêntese de abertura.");
                }

                // Aplica regras de precedência
                while (!operatorStack.isEmpty() && precedence(c) <= precedence(operatorStack.peek())) {
                    postfix.append(operatorStack.pop()).append(" ");
                }

                operatorStack.push(c);

            } else if (c == '(') {
                // Verifica se parênteses estão vazios
                if (i + 1 < expression.length() && expression.charAt(i + 1) == ')') {
                    throw new InvalidExpressionException("Parênteses vazios não são permitidos.");
                }
                operatorStack.push(c);

            } else if (c == ')') {
                // Fecha subexpressão
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop()).append(" ");
                }

                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop();

                    // Aplica operadores unários associados ao parêntese
                    if (!unaryStack.isEmpty()) {
                        int unaryCount = unaryStack.pop();
                        for (int j = 0; j < unaryCount; j++) {
                            postfix.append("N ");
                        }
                    }
                } else {
                    throw new InvalidExpressionException("Parênteses não balanceados.");
                }
            } else {
                throw new InvalidExpressionException("Caractere inválido na expressão: '" + c + "'");
            }
        }

        // Desempilha operadores restantes
        while (!operatorStack.isEmpty()) {
            char top = operatorStack.pop();
            if (top == '(') {
                throw new InvalidExpressionException("Parênteses não balanceados.");
            }
            postfix.append(top).append(" ");
        }

        return postfix.toString().trim();
    }

    // Verifica se é um operador válido
    public static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    // Retorna a precedência dos operadores
    private static int precedence(char c) {
        switch (c) {
            case '*':
            case '/': return 2;
            case '+':
            case '-': return 1;
            default: return -1;
        }
    }
}
