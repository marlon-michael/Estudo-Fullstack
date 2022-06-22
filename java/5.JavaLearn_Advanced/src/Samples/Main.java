package Samples;

import Exercise.Carro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {

        //add integer numbers into arraylist until read num be zero, then print array
        //ArrayList array = new ArrayList(); // valor generico não primitivos
        //ArrayList<Integer> array = new ArrayList<Integer>();
        //array.add(item)
        //array.size() :int size
        //array.remove(int position)
        //array.clear()

        //HashMap<String, String> marca = new HashMap<>();
        //hm.put("key", "value")
        //hm.get("key")
        //hm.remove("key")
        //hm.size()
        //hm.keySet()  chaves do hashmap
        //hm.values()  valores do hashmap

        System.out.print("Digite o indice do programa: ");
        int exe = read.nextInt();

        if (exe == 1){
            ArrayList<Integer> array = new ArrayList<>();
            while(true) {
                System.out.print("Digite um número inteiro ou [ 0 ] para finalizar: ");
                int num = read.nextInt();
                System.out.println();
                if (num == 0) break;
                array.add(num);
            }
            System.out.println("Números digitados:");
            for (int i = 0; i < array.size()-1; i++) {
                System.out.print(array.get(i) + " | ");
            }System.out.println(array.get(array.size()-1));
        }

        else if (exe == 2) {
            HashMap<String, Double> produtos =  new HashMap<>();
            while(true){
                System.out.println("1 - cadastras produto");
                System.out.println("2 - buscar produto");
                System.out.println("3 - excluir produto");
                System.out.println("4 - visualizar produtos");
                System.out.println("0 - sair");
                System.out.print("Escolha: ");
                int option = read.nextInt();
                System.out.println();

                if (option == 1){
                    System.out.print("Digite o produto: ");
                    read.nextLine();
                    String nome = read.nextLine().toLowerCase();
                    if (produtos.containsKey(nome)) {
                        System.out.println("Produto já existe!");
                        System.out.println();
                        continue;
                    }
                    System.out.print("Digite o valor: ");
                    produtos.put(nome, read.nextDouble());
                    System.out.println();
                }
                else if (option == 2){
                    System.out.print("Perquisar: ");
                    read.nextLine();
                    String pesquisa = read.nextLine().toLowerCase();
                    if (produtos.containsKey(pesquisa)) System.out.println(pesquisa +" custa: R$ "+ produtos.get(pesquisa));
                    else System.out.println(pesquisa+" não existe em estoque!");
                }
                else if (option == 3){
                    System.out.print("Excluir item: ");
                    read.nextLine();
                    String excluir = read.nextLine().toLowerCase();
                    if (produtos.containsKey(excluir)) System.out.println("O seguinte item será excluido: "+ excluir);
                    else System.out.println(excluir+" não existe em estoque!");
                    produtos.remove(excluir);
                }
                else if (option == 4){
                    System.out.println("Lista de produtos:");
                    for (String produto: produtos.keySet()) {
                        System.out.println(produto +" valor: R$"+ produtos.get(produto));
                    }
                }
                else break;
            }
            System.out.println();
            System.out.println(produtos);
        }
        else if (exe == 3){
            HashMap<String, ArrayList<Carro>> carros = new HashMap<>();
            while(true){
                System.out.println("1 - Ver carros disponíveis");
                System.out.println("2 - Adicionar carro");
                System.out.println("3 - Vender carro");
                System.out.println("0 - sair");
                System.out.print("Escolha: ");
                int escolha = read.nextInt();
                System.out.println();

                if (escolha == 1){
                    if (carros.size()<1){
                        System.out.println("Inventário de veículos vázio");
                        continue;
                    }
                    for (String marca: carros.keySet()){
                        System.out.println("Marca: "+marca);
                        System.out.println("\nModelos disponíveis");
                        for (Carro carro: carros.get(marca)){
                            System.out.println(carro.getModelo());
                        }
                        System.out.println();
                    }
                    System.out.println(carros);
                }
                else if (escolha == 2){
                    System.out.print("Digite a marca do veiculo: ");
                    read.nextLine();
                    String marca = read.nextLine().toLowerCase();
                    System.out.print("Digite o modelo do veiculo: ");
                    read.nextLine();
                    String modelo = read.nextLine().toLowerCase();
                    System.out.print("Digite o valor do veiculo: ");
                    double valor = read.nextDouble();

                    Carro carro = new Carro();
                    carro.setModelo(modelo);
                    carro.setValor(valor);

                    if (carros.containsKey(marca)){
                        carros.get(marca).add(carro);
                    }
                    else{
                        ArrayList<Carro> modelos = new ArrayList<>(15);
                        modelos.add(carro);
                        carros.put(marca , modelos);
                    }
                }
                else if (escolha == 3){
                    System.out.print("Digite a marca do veiculo: ");
                    read.nextLine();
                    String marca = read.nextLine().toLowerCase();
                    System.out.print("Digite o modelo do veiculo: ");
                    String modelo = read.nextLine().toLowerCase();

                    if (carros.containsKey(marca)){
                        boolean has = false;
                        for (int i = 0; i < carros.get(marca).size(); i++) {
                            if (carros.get(marca).get(i).getModelo().equals(modelo)){
                                carros.get(marca).remove(i);
                                has = true;
                            }
                        }
                        if (!has) System.out.println("Modelo não existe no inventário");
                    }
                    else {
                        System.out.println("Marca não existe no inventário");
                    }
                }

                else break;
            }
        }
    }
}
