import java.util.InputMismatchException;
import java.util.Scanner;

class CadastroReserva {
    int numero_aviao;
    String nome_passageiro;
}

public class SweetFlight {

    public static void registrarAvioes(int[] avioes, Scanner escolha) {
        for (int i = 0; i < avioes.length; i++) {
            System.out.print("Digite o número do avião " + (i + 1) + ": ");
            avioes[i] = escolha.nextInt();
        }
        System.out.println("Aviões registrados com sucesso!");
    }

    public static void registrarAssentos(int[] assentos, int[] avioes, Scanner escolha) {
        for (int i = 0; i < assentos.length; i++) {
            System.out.print("Digite a quantidade de assentos disponíveis no avião " + avioes[i] + ": ");
            assentos[i] = escolha.nextInt();
        }
        System.out.println("Assentos registrados com sucesso!");
    }

    public static int reservarPassagem(int totalReservas, int[] avioes, int[] assentos, CadastroReserva[] reservas, Scanner escolha) {
        if (totalReservas >= 20) {
            System.out.println("Limite de reservas atingido!");
            return totalReservas;
        }

        System.out.print("Digite o número do avião desejado: ");
        int aviaoBusca;
        boolean encontrado = false;

        try {
            aviaoBusca = escolha.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite um número de avião válido.");
            escolha.nextLine();
            return totalReservas;
        }

        for (int i = 0; i < avioes.length; i++) {
            if (avioes[i] == aviaoBusca) {
                encontrado = true;
                if (assentos[i] > 0) {
                    System.out.print("Digite o nome do passageiro: ");
                    String nomePassageiro = escolha.next();

                    CadastroReserva novaReserva = new CadastroReserva();
                    novaReserva.numero_aviao = aviaoBusca;
                    novaReserva.nome_passageiro = nomePassageiro;

                    reservas[totalReservas] = novaReserva;
                    assentos[i]--;
                    totalReservas++;

                    System.out.println("Reserva realizada com sucesso!");
                } else {
                    System.out.println("Não há assentos disponíveis para este avião!");
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Este avião não existe!");
        }
        return totalReservas;
    }

    public static void consultarAviao(int[] avioes, Scanner escolha, int totalReservas, CadastroReserva[] reservas) {
        System.out.print("Digite o número do avião: ");
        int aviaoBusca = escolha.nextInt();
        boolean encontrado = false;

        for (int i = 0; i < avioes.length; i++) {
            if (avioes[i] == aviaoBusca) {
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Avião não encontrado!");
            return;
        }

        boolean reservaEncontrada = false;
        for (int i = 0; i < totalReservas; i++) {
            if (reservas[i].numero_aviao == aviaoBusca) {
                System.out.println("Passageiro: " + reservas[i].nome_passageiro);
                reservaEncontrada = true;
            }
        }
        if (!reservaEncontrada) {
            System.out.println("Não há reservas neste avião!");
        }
    }

    public static void consultarPassageiro(CadastroReserva[] reservas, int totalReservas, Scanner escolha) {
        System.out.print("Digite o nome do passageiro: ");
        String nomeBusca = escolha.next();
        boolean encontrado = false;

        for (int i = 0; i < totalReservas; i++) {
            if (reservas[i].nome_passageiro.equalsIgnoreCase(nomeBusca)) {
                System.out.println("Passageiro: " + reservas[i].nome_passageiro + " | Avião: " + reservas[i].numero_aviao);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Não há reservas para este passageiro!");
        }
    }

    public static void main(String[] args) {
        int[] avioes = new int[4];
        int[] assentos = new int[4];
        CadastroReserva[] reservas = new CadastroReserva[20];
        int totalReservas = 0;
        int i, j, opcao, aviaoBusca;
        String nomeBusca;
        boolean encontrado;
        Scanner escolha = new Scanner(System.in);

        opcao = 0;

        while (opcao != 6) {
            System.out.println("---- Menu SweetFlight ----");
            System.out.println("1. Registrar número dos aviões");
            System.out.println("2. Registrar quantidade de assentos disponíveis");
            System.out.println("3. Reservar passagem área");
            System.out.println("4. Consultar por avião");
            System.out.println("5. Consultar por passageiro");
            System.out.println("6. Sair do sistema");

            opcao = escolha.nextInt();

            switch (opcao) {

                case 1:
                    registrarAvioes(avioes, escolha);
                    break;
                case 2:
                    registrarAssentos(assentos, avioes, escolha);
                    break;
                case 3:
                    totalReservas = reservarPassagem(totalReservas, avioes, assentos, reservas, escolha);
                    break;
                case 4:
                    consultarAviao(avioes, escolha, totalReservas, reservas);
                    break;
                case 5:
                    consultarPassageiro(reservas, totalReservas, escolha);
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");


            }
        }
    }
}