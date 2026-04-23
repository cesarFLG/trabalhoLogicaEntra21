package Byteburger;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);package Byteburger;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] codigos = {1, 2, 3, 4, 5, 6, 7};
        String[] nomes = {"ByteBurger Classico", "ByteBurger Duplo",
                "Batata Frita P", "Batata Frita G",
                "Refrigerante Lata", "Suco Natural", "Agua"};
        double[] precos = {22.90, 29.90, 12.00, 18.00, 7.00, 10.00, 4.00};
        int[] categorias = {1, 1, 2, 2, 3, 3, 3};
        int totalItens = codigos.length;

        double ultimoTotalPedido = 0;

        int produtoSorteadoIndice = -1;
        double valorDesconto = 0;
        String nomeProdutoSorteado = "";
        int opcao = -1;

        do {
            System.out.println("\n========================================");
            System.out.println("  BYTEBURGER - Sistema de Pedidos");
            System.out.println("========================================");
            System.out.println("  1. Novo Pedido");
            System.out.println("  2. Consultar Cardapio por Categoria");
            System.out.println("  3. Calcular Troco");
            System.out.println("  4. Sorteio do Dia");
            System.out.println("  0. Encerrar");
            System.out.println("========================================");
            System.out.print("  Escolha uma opcao: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("\n   Entrada invalida! Digite um numero (0 a 4).");
                scanner.nextLine();
                continue;
            }

            if (opcao < 0 || opcao > 4) {
                System.out.println("\n   Opcao invalida! Digite 0, 1, 2, 3 ou 4.");
                continue;
            }

            switch (opcao) {

                case 1:
                    ArrayList<String> itensPedido = new ArrayList<>();
                    ArrayList<Double> precosPedido = new ArrayList<>();
                    double total = 0;

                    System.out.println("\n--- CARDAPIO ---");
                    for (int i = 0; i < totalItens; i++) {
                        System.out.printf("  %d. %s - R$ %.2f%n",
                                codigos[i], nomes[i], precos[i]);
                    }

                    if (produtoSorteadoIndice != -1 && valorDesconto > 0) {
                        System.out.println("\n  PROMOCAO DO DIA ATIVA! ");
                        System.out.printf("  Produto sorteado: %s%n", nomeProdutoSorteado);
                        System.out.printf("  Desconto de R$ %.2f no valor total do pedido!%n", valorDesconto);
                    }

                    System.out.println("\nNOVO PEDIDO");
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();

                    System.out.print("\nDeseja adicionar itens ao pedido? (S/N): ");
                    String resposta = scanner.nextLine();

                    if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("SIM")) {

                        boolean adicionando = true;

                        while (adicionando) {
                            System.out.println("\n--- ADICIONAR ITEM ---");
                            System.out.print("Digite o codigo do produto: ");
                            int codigo = scanner.nextInt();
                            scanner.nextLine();

                            if (codigo >= 1 && codigo <= totalItens) {
                                int indice = codigo - 1;
                                total += precos[indice];
                                itensPedido.add(nomes[indice]);
                                precosPedido.add(precos[indice]);
                                System.out.printf("   + %s adicionado! (R$ %.2f)%n",
                                        nomes[indice], precos[indice]);

                                System.out.print("\nDeseja adicionar outro item? (S/N): ");
                                String continuar = scanner.nextLine();
                                if (!continuar.equalsIgnoreCase("S") && !continuar.equalsIgnoreCase("SIM")) {
                                    adicionando = false;
                                }
                            } else {
                                System.out.println("   Codigo invalido! Tente novamente.");
                            }
                        }
                    } else {
                        System.out.println("  Nenhum item adicionado ao pedido.");
                    }

                    double descontoAplicado = 0;
                    if (produtoSorteadoIndice != -1 && valorDesconto > 0) {
                        descontoAplicado = valorDesconto;
                        System.out.printf("%n  Desconto do produto sorteado (%s) aplicado: -R$ %.2f%n",
                                nomeProdutoSorteado, descontoAplicado);
                        produtoSorteadoIndice = -1;
                        valorDesconto = 0;
                    }

                    double totalComDesconto = total - descontoAplicado;
                    if (totalComDesconto < 0) {
                        totalComDesconto = 0;
                    }

                    System.out.println("\n--- RESUMO DO PEDIDO ---");
                    System.out.println("Cliente: " + nomeCliente);
                    System.out.println("Itens:");

                    if (itensPedido.isEmpty()) {
                        System.out.println("  Nenhum item adicionado");
                    } else {
                        int i = 0;
                        for (String item : itensPedido) {
                            System.out.printf("  - %-22s R$ %.2f%n", item, precosPedido.get(i));
                            i++;
                        }
                    }

                    System.out.printf("Subtotal: R$ %.2f%n", total);
                    if (descontoAplicado > 0) {
                        System.out.printf("Desconto: -R$ %.2f%n", descontoAplicado);
                    }
                    System.out.printf("Total a pagar: R$ %.2f%n", totalComDesconto);

                    ultimoTotalPedido = totalComDesconto;
                    break;

                case 2:
                    System.out.println("\nCONSULTAR CARDAPIO POR CATEGORIA");
                    System.out.println("  1 - Lanches");
                    System.out.println("  2 - Acompanhamentos");
                    System.out.println("  3 - Bebidas");
                    System.out.println("  0 - Voltar");

                    int categoria = -1;
                    boolean entradaValida = false;

                    while (!entradaValida) {
                        System.out.print("  Escolha uma categoria: ");

                        if (scanner.hasNextInt()) {
                            categoria = scanner.nextInt();
                            scanner.nextLine();

                            if (categoria >= 0 && categoria <= 3) {
                                entradaValida = true;
                            } else {
                                System.out.println("   Opção invalida! Digite 0, 1, 2 ou 3.");
                            }
                        } else {
                            System.out.println("   Entrada invalida! Digite um numero.");
                            scanner.nextLine();
                        }
                    }

                    if (categoria == 0) {
                        break;
                    }

                    switch (categoria) {
                        case 1:
                            System.out.println("\n--- LANCHES ---");
                            break;
                        case 2:
                            System.out.println("\n--- ACOMPANHAMENTOS ---");
                            break;
                        case 3:
                            System.out.println("\n--- BEBIDAS ---");
                            break;
                    }

                    for (int i = 0; i < totalItens; i++) {
                        if (categorias[i] == categoria) {
                            System.out.printf("  %d. %s - R$ %.2f%n",
                                    codigos[i], nomes[i], precos[i]);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\nCALCULAR TROCO");

                    if (ultimoTotalPedido <= 0) {
                        System.out.println("   Nenhum pedido registrado!");
                        System.out.println("  Faca um pedido primeiro (opcao 1).");
                        break;
                    }

                    System.out.printf("   Valor do pedido: R$ %.2f%n", ultimoTotalPedido);
                    System.out.println("  Digite 0 para cancelar a operacao.\n");

                    double valorPago;
                    double troco;

                    while (true) {
                        System.out.print("   Digite o valor pago: R$ ");

                        if (scanner.hasNextDouble()) {
                            valorPago = scanner.nextDouble();
                            scanner.nextLine();

                            if (valorPago == 0) {
                                System.out.println("\n  Operacao cancelada. Voltando ao menu...");
                                break;
                            } else if (valorPago < ultimoTotalPedido) {
                                double falta = ultimoTotalPedido - valorPago;
                                System.out.printf("\n   Valor insuficiente! Faltam R$ %.2f%n", falta);
                                System.out.println("  Tente novamente!\n");
                            } else if (valorPago == ultimoTotalPedido) {
                                System.out.println("\n   Pedido pago no valor exato! Nao ha troco.");
                                break;
                            } else {
                                troco = valorPago - ultimoTotalPedido;
                                System.out.printf("\n   Troco: R$ %.2f%n", troco);
                                break;
                            }
                        } else {
                            String entrada = scanner.nextLine();
                            if (entrada.equalsIgnoreCase("sair")) {
                                System.out.println("\n  Operacao cancelada. Voltando ao menu...");
                                break;
                            } else {
                                System.out.println("   Entrada invalida! Digite um numero ou 'sair'.\n");
                            }
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nSORTEIO DO DIA");

                    produtoSorteadoIndice = (int) (Math.random() * totalItens);
                    nomeProdutoSorteado = nomes[produtoSorteadoIndice];
                    double precoSorteado = precos[produtoSorteadoIndice];
                    valorDesconto = precoSorteado * 0.20;

                    double precoComDesconto = precoSorteado - valorDesconto;

                    System.out.println("   PRODUTO SORTEADO DO DIA!");
                    System.out.printf("  Produto: %s%n", nomeProdutoSorteado);
                    System.out.printf("  Preco original:    R$ %.2f%n", precoSorteado);
                    System.out.printf("  Desconto de 20%%:  -R$ %.2f%n", valorDesconto);
                    System.out.printf("  Preco com desconto: R$ %.2f%n", precoComDesconto);
                    System.out.println("\n   Esse desconto sera aplicado no seu proximo pedido!");
                    break;

                case 0:
                    System.out.println("\nEncerrando o sistema. Volte sempre!");
                    break;

                default:
                    System.out.println("\nOpcao invalida! Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}

        int[] codigos = {1, 2, 3, 4, 5, 6, 7};
        String[] nomes = {"ByteBurger Classico", "ByteBurger Duplo",
                "Batata Frita P", "Batata Frita G",
                "Refrigerante Lata", "Suco Natural", "Agua"};
        double[] precos = {22.90, 29.90, 12.00, 18.00, 7.00, 10.00, 4.00};
        int[] categorias = {1, 1, 2, 2, 3, 3, 3};
        int totalItens = codigos.length;

        double ultimoTotalPedido = 0;

        int produtoSorteadoIndice = -1;
        double valorDesconto = 0;
        String nomeProdutoSorteado = "";
        int opcao = -1;

        do {
            System.out.println("\n========================================");
            System.out.println("  BYTEBURGER - Sistema de Pedidos");
            System.out.println("========================================");
            System.out.println("  1. Novo Pedido");
            System.out.println("  2. Consultar Cardapio por Categoria");
            System.out.println("  3. Calcular Troco");
            System.out.println("  4. Sorteio do Dia");
            System.out.println("  0. Encerrar");
            System.out.println("========================================");
            System.out.print("  Escolha uma opcao: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("\n   Entrada invalida! Digite um numero (0 a 4).");
                scanner.nextLine();
                continue;
            }

            if (opcao < 0 || opcao > 4) {
                System.out.println("\n   Opcao invalida! Digite 0, 1, 2, 3 ou 4.");
                continue;
            }

            switch (opcao) {

                case 1:
                    ArrayList<String> itensPedido = new ArrayList<>();
                    ArrayList<Double> precosPedido = new ArrayList<>();
                    double total = 0;

                    System.out.println("\n--- CARDAPIO ---");
                    for (int i = 0; i < totalItens; i++) {
                        System.out.printf("  %d. %s - R$ %.2f%n",
                                codigos[i], nomes[i], precos[i]);
                    }

                    if (produtoSorteadoIndice != -1 && valorDesconto > 0) {
                        System.out.println("\n  PROMOCAO DO DIA ATIVA! ");
                        System.out.printf("  Produto sorteado: %s%n", nomeProdutoSorteado);
                        System.out.printf("  Desconto de R$ %.2f no valor total do pedido!%n", valorDesconto);
                    }

                    System.out.println("\nNOVO PEDIDO");
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();

                    System.out.print("\nDeseja adicionar itens ao pedido? (S/N): ");
                    String resposta = scanner.nextLine();

                    if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("SIM")) {

                        boolean adicionando = true;

                        while (adicionando) {
                            System.out.println("\n--- ADICIONAR ITEM ---");
                            System.out.print("Digite o codigo do produto: ");
                            int codigo = scanner.nextInt();
                            scanner.nextLine();

                            if (codigo >= 1 && codigo <= totalItens) {
                                int indice = codigo - 1;
                                total += precos[indice];
                                itensPedido.add(nomes[indice]);
                                precosPedido.add(precos[indice]);
                                System.out.printf("   + %s adicionado! (R$ %.2f)%n",
                                        nomes[indice], precos[indice]);

                                System.out.print("\nDeseja adicionar outro item? (S/N): ");
                                String continuar = scanner.nextLine();
                                if (!continuar.equalsIgnoreCase("S") && !continuar.equalsIgnoreCase("SIM")) {
                                    adicionando = false;
                                }
                            } else {
                                System.out.println("   Codigo invalido! Tente novamente.");
                            }
                        }
                    } else {
                        System.out.println("  Nenhum item adicionado ao pedido.");
                    }

                    double descontoAplicado = 0;
                    if (produtoSorteadoIndice != -1 && valorDesconto > 0) {
                        descontoAplicado = valorDesconto;
                        System.out.printf("%n  Desconto do produto sorteado (%s) aplicado: -R$ %.2f%n",
                                nomeProdutoSorteado, descontoAplicado);
                        produtoSorteadoIndice = -1;
                        valorDesconto = 0;
                    }

                    double totalComDesconto = total - descontoAplicado;
                    if (totalComDesconto < 0) {
                        totalComDesconto = 0;
                    }

                    System.out.println("\n--- RESUMO DO PEDIDO ---");
                    System.out.println("Cliente: " + nomeCliente);
                    System.out.println("Itens:");

                    if (itensPedido.isEmpty()) {
                        System.out.println("  Nenhum item adicionado");
                    } else {
                        for (int i = 0; i < itensPedido.size(); i++) {
                            System.out.printf("  - %-22s R$ %.2f%n",
                                    itensPedido.get(i), precosPedido.get(i));
                        }
                    }

                    System.out.printf("Subtotal: R$ %.2f%n", total);
                    if (descontoAplicado > 0) {
                        System.out.printf("Desconto: -R$ %.2f%n", descontoAplicado);
                    }
                    System.out.printf("Total a pagar: R$ %.2f%n", totalComDesconto);

                    ultimoTotalPedido = totalComDesconto;
                    break;

                case 2:
                    System.out.println("\nCONSULTAR CARDAPIO POR CATEGORIA");
                    System.out.println("  1 - Lanches");
                    System.out.println("  2 - Acompanhamentos");
                    System.out.println("  3 - Bebidas");
                    System.out.println("  0 - Voltar");

                    int categoria = -1;
                    boolean entradaValida = false;

                    while (!entradaValida) {
                        System.out.print("  Escolha uma categoria: ");

                        if (scanner.hasNextInt()) {
                            categoria = scanner.nextInt();
                            scanner.nextLine();

                            if (categoria >= 0 && categoria <= 3) {
                                entradaValida = true;
                            } else {
                                System.out.println("   Opção invalida! Digite 0, 1, 2 ou 3.");
                            }
                        } else {
                            System.out.println("   Entrada invalida! Digite um numero.");
                            scanner.nextLine();
                        }
                    }

                    if (categoria == 0) {
                        break;
                    }

                    switch (categoria) {
                        case 1:
                            System.out.println("\n--- LANCHES ---");
                            break;
                        case 2:
                            System.out.println("\n--- ACOMPANHAMENTOS ---");
                            break;
                        case 3:
                            System.out.println("\n--- BEBIDAS ---");
                            break;
                    }

                    for (int i = 0; i < totalItens; i++) {
                        if (categorias[i] == categoria) {
                            System.out.printf("  %d. %s - R$ %.2f%n",
                                    codigos[i], nomes[i], precos[i]);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\nCALCULAR TROCO");

                    if (ultimoTotalPedido <= 0) {
                        System.out.println("   Nenhum pedido registrado!");
                        System.out.println("  Faca um pedido primeiro (opcao 1).");
                        break;
                    }

                    System.out.printf("   Valor do pedido: R$ %.2f%n", ultimoTotalPedido);
                    System.out.println("  Digite 0 para cancelar a operacao.\n");

                    double valorPago;
                    double troco;

                    while (true) {
                        System.out.print("   Digite o valor pago: R$ ");

                        if (scanner.hasNextDouble()) {
                            valorPago = scanner.nextDouble();
                            scanner.nextLine();

                            if (valorPago == 0) {
                                System.out.println("\n  Operacao cancelada. Voltando ao menu...");
                                break;
                            } else if (valorPago < ultimoTotalPedido) {
                                double falta = ultimoTotalPedido - valorPago;
                                System.out.printf("\n   Valor insuficiente! Faltam R$ %.2f%n", falta);
                                System.out.println("  Tente novamente!\n");
                            } else if (valorPago == ultimoTotalPedido) {
                                System.out.println("\n   Pedido pago no valor exato! Nao ha troco.");
                                break;
                            } else {
                                troco = valorPago - ultimoTotalPedido;
                                System.out.printf("\n   Troco: R$ %.2f%n", troco);
                                break;
                            }
                        } else {
                            String entrada = scanner.nextLine();
                            if (entrada.equalsIgnoreCase("sair")) {
                                System.out.println("\n  Operacao cancelada. Voltando ao menu...");
                                break;
                            } else {
                                System.out.println("   Entrada invalida! Digite um numero ou 'sair'.\n");
                            }
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nSORTEIO DO DIA");

                    produtoSorteadoIndice = (int) (Math.random() * totalItens);
                    nomeProdutoSorteado = nomes[produtoSorteadoIndice];
                    double precoSorteado = precos[produtoSorteadoIndice];
                    valorDesconto = precoSorteado * 0.20;

                    double precoComDesconto = precoSorteado - valorDesconto;

                    System.out.println("   PRODUTO SORTEADO DO DIA!");
                    System.out.printf("  Produto: %s%n", nomeProdutoSorteado);
                    System.out.printf("  Preco original:    R$ %.2f%n", precoSorteado);
                    System.out.printf("  Desconto de 20%%:  -R$ %.2f%n", valorDesconto);
                    System.out.printf("  Preco com desconto: R$ %.2f%n", precoComDesconto);
                    System.out.println("\n   Esse desconto sera aplicado no seu proximo pedido!");
                    break;

                case 0:
                    System.out.println("\nEncerrando o sistema. Volte sempre!");
                    break;

                default:
                    System.out.println("\nOpcao invalida! Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
