//Exercicio 3 Lista Encadeada Circular
public class ListaCircularDupla {
    Celula Primeira;
    Celula ultima;
    int TotaldeElementos = 0;

    //b)
    void AdicionaNoComeco(Object elemento){
        if(this.TotaldeElementos == 0){
            Celula nova = new Celula(elemento);
            this.Primeira = nova;
            this.ultima = nova;
        }else{
            Celula nova = new Celula(this.Primeira, elemento);
            this.Primeira.setAnterior(nova);
            this.Primeira = nova;
        }
        this.TotaldeElementos++; 
        }

    void adiciona(Object elemento){
        if(this.TotaldeElementos == 0){
        this.AdicionaNoComeco(elemento);
        }else{
        Celula nova = new Celula(elemento);
        this.ultima.setProxima(nova);
        this.ultima = nova;
        this.ultima.setProxima(Primeira);
        this.TotaldeElementos++;
        }
    }

    boolean PosicaoOcupada(int pos){
        return((pos >= 0)&&(pos < this.TotaldeElementos));
        }


    Celula PegaCelula(int pos){
        if(!this.PosicaoOcupada(pos)){
        throw new IllegalArgumentException("Posicao nao Existe");
        }else{
        Celula atual = this.Primeira;
        for(int i=0; i < pos; i++){
        atual = atual.getProxima();
        }
        return(atual);
        } 
    }

    void adiciona(int pos, Object elemento){
        if(pos == 0){
        this.AdicionaNoComeco(elemento);
        }else if(pos == this.TotaldeElementos){
        this.adiciona(elemento);
        }else {
        Celula anterior = this.PegaCelula(pos-1);
        Celula Nova = new Celula(anterior.getProxima(),elemento);
        anterior.setProxima(Nova);
        this.TotaldeElementos++;
        } 
    }

    Object pega(int posicao){
        return(this.PegaCelula(posicao).getElemento());
        }

    void removeDoComeco(){
        if(!this.PosicaoOcupada(0)){
        throw new IllegalArgumentException("Posição não Existe");
        }else{
        this.Primeira = this.Primeira.getProxima();
        this.TotaldeElementos--;
        if(this.TotaldeElementos == 0){
        this.ultima = null;
            }
        } 
    }

    boolean Contem(Object elemento){
        Celula atual = this.Primeira;
        while(atual!=null){
        if(atual.getElemento().equals(elemento)){
        return(true);
        }
        atual = atual.getProxima();
        }
        return(false); 
    }

    //a)
    int Tamanho(){
        return(this.TotaldeElementos);
    }

    //c)
    void concatenarLista(ListaCircular lista2){
        for(int i = 0; i < lista2.Tamanho() ;i++ ){
            this.adiciona(lista2.pega(i));
        }
    }

    //d)
    void intercalarListas(ListaCircular lista2) {
        ListaCircular novaLista = new ListaCircular();
        int tamanho1 = this.Tamanho();
        int tamanho2 = lista2.Tamanho();
    
        int i = 0;
        int j = 0;
    
        while (i < tamanho1 && j < tamanho2) {
            Object elemento1 = this.pega(i);
            Object elemento2 = lista2.pega(j);
    
            if (elemento1 instanceof Float && elemento2 instanceof Float) {
                if ((Float) elemento1 < (Float) elemento2) {
                    novaLista.adiciona(elemento1);
                    i++;
                } else {
                    novaLista.adiciona(elemento2);
                    j++;
                }
            } 
        }

        while (i < tamanho1) {
            novaLista.adiciona(this.pega(i));
            i++;
        }

        while (j < tamanho2) {
            novaLista.adiciona(lista2.pega(j));
            j++;
        }
    
        for(int x = 0; x < novaLista.Tamanho(); x++){
            System.out.println(novaLista.pega(x));
        }
    }

    //e)
    ListaCircular fazerCopia(){
        ListaCircular novaLista = new ListaCircular();

        for(int i = 0; i < this.Tamanho(); i++){
            novaLista.adiciona(this.pega(i));
        }

        return novaLista;
    }
}
