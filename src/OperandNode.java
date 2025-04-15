/*
    Guilherme Teodoro de Oliveira RA: 10425362
    Luís Henrique Ribeiro Fernandes RA: 10420079
    Vinícius Brait Lorimier RA: 10420046
*/

// Classe que representa um nó operando (valor numérico) em uma árvore de expressão
public class OperandNode extends Node {
    
    // Valor numérico armazenado no nó
    private Float value;

    // Construtor: um operando não possui filhos, então os ponteiros são nulos
    public OperandNode(Float value) {
        super(null, null); // chama o construtor da superclasse (Node) com left e right nulos
        this.value = value;
    }

    // Método que retorna o valor numérico do operando
    @Override
    public Float visit() {
        return value;
    }

    // Retorna a representação em string do valor (para exibição)
    @Override
    public String display() {
        return value.toString();
    }
}
