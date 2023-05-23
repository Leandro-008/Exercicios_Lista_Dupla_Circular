public class Main {
    public static void main(String[] args) {
        ListaCircular lista1 = new ListaCircular();
        ListaCircular lista2 = new ListaCircular();
        
        lista1.adiciona(1f);
        lista1.adiciona(3f);
        lista1.adiciona(5f);
        lista2.adiciona(2f);
        lista2.adiciona(4f);
        lista2.adiciona(6f);
        lista1.adiciona(8f);
        lista1.adiciona(10f);

        lista1.intercalarListas(lista2);

    }
}
