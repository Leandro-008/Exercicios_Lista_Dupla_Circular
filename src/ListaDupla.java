//Exercicio 1 implementação de metodos para listas duplamente encadeadas
public class ListaDupla {
    Celula Primeira;
    Celula Ultima;
    int TotaldeElementos = 0;

    boolean PosicaoOcupada(int pos){
        return((pos >= 0)&&(pos < this.TotaldeElementos));
        }
    
    Celula PegaCelula(int pos){
        if(!this.PosicaoOcupada(pos)){
            throw new IllegalArgumentException("Posicao nao existe");    
        }else{
            Celula atual = this.Primeira;
            for(int i=0; i < pos; i++){
            atual = atual.getProxima();
            }
            return(atual);
            } 
        }

    Object Pega(int pos){
        return(this.PegaCelula(pos).getElemento());
        }        

    void AdicionaNoComeco(Object elemento){
        if(this.TotaldeElementos == 0){
            Celula nova = new Celula(elemento);
            this.Primeira = nova;
            this.Ultima = nova;
        }else{
            Celula nova = new Celula(this.Primeira, elemento);
            this.Primeira.setAnterior(nova);
            this.Primeira = nova;
        }
        this.TotaldeElementos++; }

    void Adiciona(Object elemento){
        if(this.TotaldeElementos == 0){
            this.AdicionaNoComeco(elemento);
        }else{
            Celula nova = new Celula(elemento);
            this.Ultima.setProxima(nova);
            nova.setAnterior(this.Ultima);
            this.Ultima = nova;
            this.TotaldeElementos++;
            } 
        }

    void Adiciona(int pos, Object elemento){
        if(pos == 0){
            this.AdicionaNoComeco(elemento);
        }else if(pos == this.TotaldeElementos){
            this.Adiciona(elemento);
        }else {
            Celula anterior = this.PegaCelula(pos-1);
            Celula proxima = anterior.getProxima();
            Celula nova = new Celula(anterior.getProxima(),elemento);
            nova.setAnterior(anterior);
            anterior.setProxima(nova);
            proxima.setAnterior(nova);
            this.TotaldeElementos++;
            } 
        }

    void RemovedoComeco(){
        if(!this.PosicaoOcupada(0)){
            throw new IllegalArgumentException("Posicao nao Existe");
        }else{
            this.Primeira = this.Primeira.getProxima();
            this.TotaldeElementos--;
            }
        if(this.TotaldeElementos == 0){
            this.Ultima = null;
            } 
        }

    void RemovedoFim(){
        if(!this.PosicaoOcupada(TotaldeElementos-1)){
            throw new IllegalArgumentException("Posicao nao existe");
        }else{
            if(this.TotaldeElementos == 1){
            this.RemovedoComeco();
        }else{
                Celula penultima = this.Ultima.getAnterior();
                penultima.setProxima(null);
                this.Ultima = penultima;
                this.TotaldeElementos--;
            } 
        } 
    }

    void Remove(int pos){
        if(!this.PosicaoOcupada(pos)){
            throw new IllegalArgumentException("Posicao nao Existe");
        }else{
            if (pos == 0){
                this.RemovedoComeco();
            }else if(pos == this.TotaldeElementos -1){
                this.RemovedoFim();
            }else {
                Celula anterior = this.PegaCelula(pos -1);
                Celula atual = anterior.getProxima();
                Celula proxima = atual.getProxima();
                anterior.setProxima(proxima);
                proxima.setAnterior(anterior);
                this.TotaldeElementos--;
                } 
            }
        }

    boolean Contem(Object elemento){
        Celula atual = this.Primeira;
        while(atual != null){
            if(atual.getElemento().equals(elemento)){
            return(true);
            }
        atual = atual.getProxima();
        }
        return(false);
        }

    int tamanho(){
        return(this.TotaldeElementos);
        }

    void EsvaziaLista(){
        this.Primeira = null;
        this.Ultima = null;
        this.TotaldeElementos = 0;
        }

    String Imprimir(){
        if(this.TotaldeElementos == 0){
            return("[]");
        }else{
            StringBuilder builder = new StringBuilder("[");
            Celula atual = this.Primeira;
        for(int i=0; i < this.TotaldeElementos -1; i++){
            builder.append(atual.getElemento());
            builder.append(", ");
            atual = atual.getProxima();
            }
            builder.append(atual.getElemento());
            builder.append("]");
            return(builder.toString());
            }
    }

    //a)
    void concatenarLista(ListaDupla lista1, ListaDupla lista2){
        for(int i = 0; i < lista2.tamanho() ;i++ ){
            lista1.Adiciona(lista2.Pega(i));
        }
    }

    void imprimirListaDePessoas(){
        for(int i = 0; i< this.tamanho(); i++){
            Pessoa pessoa;
            pessoa = (Pessoa)this.Pega(i);
            System.out.println(pessoa.nome);
        }
    }

    //b)
    void dividirLista(){
        ListaDupla novalista = new ListaDupla();
        int novotamanho = this.tamanho()/2;

        System.out.println(this.tamanho());
        System.out.println(novotamanho);

        if(novotamanho == 1){
        for(int i = novotamanho-1 ; i < this.tamanho() ; i++){
            novalista.Adiciona(this.Pega(novotamanho ));
            this.Remove(novotamanho );
        }            
        }else{
        for(int i = novotamanho-1 ; i < this.tamanho() +1 ; i++){
            novalista.Adiciona(this.Pega(novotamanho ));
            this.Remove(novotamanho );
        }            
        }

        System.out.println("Lista dividida: ");   
        this.imprimirListaDePessoas();
        
        System.out.println("Nova lista: ");
        novalista.imprimirListaDePessoas();
    }

    //c)
    void intercalarListas(ListaDupla lista2) {
        ListaDupla novaLista = new ListaDupla();
    
        int tamanho1 = this.tamanho();
        int tamanho2 = lista2.tamanho();
    
        int i = 0;
        int j = 0;
    
        while (i < tamanho1 && j < tamanho2) {
            Object elemento1 = this.Pega(i);
            Object elemento2 = lista2.Pega(j);
    
            if (elemento1 instanceof Float && elemento2 instanceof Float) {
                if ((Float) elemento1 < (Float) elemento2) {
                    novaLista.Adiciona(elemento1);
                    i++;
                } else {
                    novaLista.Adiciona(elemento2);
                    j++;
                }
            } 
        }

        while (i < tamanho1) {
            novaLista.Adiciona(this.Pega(i));
            i++;
        }

        while (j < tamanho2) {
            novaLista.Adiciona(lista2.Pega(j));
            j++;
        }
    
        for(int x = 0; x < novaLista.tamanho(); x++){
            System.out.println(novaLista.Pega(x));
        }
    }


}