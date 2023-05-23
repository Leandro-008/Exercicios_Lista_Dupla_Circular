//Exercicio 5 lista encadeada circular com um “nó cabeça” 

public class ListaCircularNoCabeca{
    Celula Primeira;
    Celula ultima;
    int TotaldeElementos = 0;
    Celula cabeca;

    void AdicionaNoComeco(Object elemento){
        Celula nova = new Celula(this.Primeira,elemento);
        this.Primeira = nova;
        if(this.TotaldeElementos == 0){
        this.ultima = this.Primeira;
        this.cabeca = this.ultima;
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
        this.ultima.setProxima(cabeca);
        this.cabeca.setProxima(Primeira);
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
        this.cabeca.setProxima(Primeira);
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

    int Tamanho(){
        return(this.TotaldeElementos);
    }
}