# Como desenvolver uma aplicação Angular 18 + Typescript

### indices
 - [Como iniciar o projeto](/3.front-end/angular-typescript/CREATE-RUN.md)
 - [Sintaxe typescript](/3.front-end/angular-typescript/CODE.md)
 - [Componentes](#components)
 - [Serviços](#services)
 - [Diretivas](#diretctives)
 - [Pipes](#pipes)
 - [Decoradores](#decorators)
 - [Definição de variaveis](#definição-de-variaveis)
 - [Loops e Condicionais](#loops-e-condifionais-no-template)
 - [Rotas](#rotas)
 - [Eventos](#eventos)
    - [Eventos de teclado](#eventos-de-teclado)
    - [Eventos para componente pai](#emitindo-eventos-para-componente-pai)
    - [Eventos por serviço](#emitindo-eventos-para-componentes-através-de-serviços)
 - [Requisições Http](#requisições-http)

---

- ### Pipes
    - pipes são estruturas que recebem um dado de outro elemento e o transforma de forma facilitada

    ### gerando um pipe
    ```console
    ng generate pipe {nome-do-pipe}
    # ou no formato abreviado
    ng g p {nome-do-pipe}
    ```

    ---
    ```javascript
    import { Pipe, PipeTransform } from '@angular/core';

    @Pipe({
        name: 'addPeriod'  
    })
    export class AddPeriodPipe implements PipeTransform {
    transform(value: string, args?: string): string{
        var newValue = ""
        let options = <any>{
            "exclamation": "!",
            "default": "."
        }
        newValue = value.concat(options[args??"default"])
        return newValue;
    }
    }
    ```

    - #### utilizando pipes
    ```javascript
    @Component({
        imports: [addPeriod],
        template: '<p>{{MyString() | addPeriod:"exclamation"}}</p>'
    })
    ```


## Estrutura do projeto Angular

- ### Components
    - Componentes são trechos de objetos reutilizaveis para outras partes da mesma pagina e de outras, que juntos formam as paginas que utilizamos, como botões, textos, barras de pesquisa e navegação, entre outros objetos maiores.

    ### gerando componentes
    ```console
    ng generate component {nome-do-componente}
    # ou no formato abreviado
    ng g c {nome-do-componente}
    ```

    ---
    ```javascript
    import {Component} from '@angular/core';
    // Decorador @Component define algumas propriedades necessárias do componente
    @Component({
        selector: 'tag-do-componente', // por qual tag o componente será chamado <tag-do-componente>
        standalone: true, // componente pode ser utilizado fora de um modulo
        imports:[ComponenteFilho], // define componentes utilizados dentro deste componente
        template: '<h1>estrutura</h1>', // define a estrutura html
        templateUrl: './estrutura-componente.html', // define o arquivo contendo a estrutura html
        styles: ['h1 {color: #ddd}'], // define estilização
        styleUrl: './estilo-componente.css' // define estilização
    })
    // exporta componente e define a implementação do metodo de inicialização
    export class MeuComponente implements OnInit {
        ngOnInit(): void{
            // inicialização do componente
        }
        // {{logica do componente}}
    }
    
    ```

- ### Services
    - Services são classes utilizadas para fazer chamadas externas ao componente ou aplicação

    ### gerando serviços
    ```console
    ng generate service {nome-da-service}
    # ou no formato abreviado
    ng g s {nome-da-service}
    ```
    
    ```javascript
    import {Injectable} from '@angular/core';

    @Injectable({
        providedIn: 'root' // informa que o serviço será disponibilizado apartir da raiz para todo o projeto
    })
    export class NomeService {
        getResponse(){[...]}
    }
    ```

    - #### utilizando a service
    ```javascript
    import {inject} from '@angular/core'

    export class NomeComponent implements OnInit{
        service = inject(NomeService)
        ngOnInit(): void{
            this.service.getResponse()
        }
    }
    ```

- ### Diretctives
    - Diretivas são elementos que alteram algo na aplicação a depender de algum outro item externo

    ### gerando serviços
    ```console
    ng generate directive {nome-da-directive}
    # ou no formato abreviado
    ng g d {nome-da-directive}
    ```

    ---
    ```javascript
    @Directive({
        selector: '[changeColorNegativeValue]', // 
        standalone: true
    })
    export class changeColorNegativeValueDirective{
        isNegative = input(false)
        ref = inject(ElementRef)
        styleEffect = effect(()=>{
            if (this.isNegative()){
                this.ref.nativeElement.style.color = '#811'
            }else{
                this.ref.nativeElement.style.color = '#eee'
            }
        })
    }
    ```

    - component
    ```javascript
    @Component({
        imports: [changeColorNegativeValueDirective]
    })
    export class Values{
        item = input.required() // depende da passagem de item pela tag para que funcione
    }
    ```

    - template
    ```html
    <!-- Mostra o valor de item vermelho caso o atributo valor seja negativo -->
    <li changeColorNegativeValue [isNegative]="item().value">
        <p>item().value</p>
    </li>
    ```

- ### Decorators
    - Decoradores são itens que englobam outra estrutura como classes, metodos ou variaveis. Podendo manipular seus valores e seus comportamentos
    
    - Capturando os argumentos do construtor da classe
    ```javascript
    // Criando um decorator para interceptação de argumentos
    function log(target:any, name:any, descriptor: any):any{
        console.log(target, name, descriptor.value);
        descriptor.value = function(...args:any){
            console.log(args);
        }
        return descriptor
    }

    class Math{
        @log // utilização do Decorador desenvolvido
        static sum(n1: number, n2:number){
            return n1 + n2;
        }
    }

    Math.sum(1,2)
    ```

## Sintaxe Angular

- ### Definição de variaveis
    ```javascript
    import {signal} from '@angular/core';

    // @Component({[...]})
    export class variableComponent{
        // valores variaveis que reinderizam na tela quando alterados
        texto = signal('Meu titulo')
        numero = signal(0)
        ligado = signal(false)

        // operações com signal
        this.numero.set(0)
        this.numero.update((current)=> current + 1)

        // valores recebidos de fora do componente através da tag
        tag_props = input('recebe props da tag')
        valueEmitter = output() // define função para emissão de eventos
        valueEmitter.emit("valor") // emite valores/eventos
    }
    ```
    - utilização de variaveis
    ```html
    <h1>{{texto()}}</h1>
    <h2 [tag_props]="'props desta tag'" >{{tag_props()}}</h2>
    ```

- ### Eventos

    ### Eventos de teclado
    ---
    ```javascript
    //@Component({[...]})
    export class keyEventComponent{
        eventHandler(event: keyboardEvent){
            console.log(`key ${event.key} was pressed`)
        }
    }
    ```

    - utilização das tags
    ```html
    <input type="text" (keyup)="keyEventComponent($event)"/>
    ```

    ### Emitindo Eventos para componente pai
    ---
    - componente filho de item
    ```javascript
    export class ItemComponent{
        item = signal<string>(""); // valor em observação
        itemChanged = output<string>(); // onde será notificado a atualização do item

        // metodo que emite o item atualizado
        itemChangedEmitter(){
            this.itemChanged.emit(this.item());
        }

        // metodo que atualiza o valor do input e aciona o metodo de emissão
        update(event: any){
            this.item.set(event.target.value);
            this.itemChangedEmitter();
        }
    }
    ```
    - template de item
    ```html
    <!-- define o tipo do imput como texto; define que o valor será atualizado pelo signal de item; define que sera emitida atualização a cada mudança; define que a cada caracter midificado será executado o metodo update-->
    <input 
        type="text"
        [value]="item()"
        (change)="itemChangedEmitter()"
        (input)="this.update($event)"
    />
    ```
    - componente pai de total
    ```javascript
    export class totalComponent{
        total = signal("");

        // atualiza o valor
        updateTotal(item: any){
            this.total.set(item);
        }
    }
    ```
    - template de total
    ```html
    <p>{{vtotalalor()}}</p>
    <!-- quando itemChanged for emitido executa o metodo updateTotal passando o evento emitido por ele -->
    <app-item (itemChanged)="updateTotal($event)">
    ```
    ###  Emitindo eventos para componentes através de Serviços
    ---
    - Service
    ```javascript
    import { EventEmitter, Injectable } from '@angular/core';

    @Injectable({
        providedIn: 'root'
    })
    export class eventService {
        // variavel de evento
        static eventEmitter = new EventEmitter<string>();
        // função de emissão de eventos
        static emitter(value: string){
            ValueService.eventEmitter.emit(value);
        }
    }
    ```
    - Componente Emissor do Evento
    ```javascript
    //[...]
    ValueService.emitter(this.item()); // emite o evento
    //[...]
    ```
    - Componente recebedor do Evento
    ```javascript
    //[...]
    ngOnInit(): void {
        // registra função no recebimento do evento
        ValueService.eventEmitter.subscribe((value: string) => {
            this.total.set(value);
        });
    }
    //[...]
    ```

- ### Rotas
    - $$[...]/app.routes.ts$$

    ```javascript
    import {Routes} from '@angular/router';

    export const routes: Routes = [
        {
            path: '',
            pathMatch: 'full',
            loadComponent:()=>{return import('./home/home.component')
            .then((module)=>module.HomeComponent)}
        },
        {
            path: 'about',
            loadComponent: ()=>{return import('./about/about.component')
            .then((module)=>module.AboutComponent)}
        }
    ]
    ```

    - importando o componente de navegação no componente principal
    $$ [...]/app.component.ts $$
    ```javascript
    import {RouterOutlet} from '@angular/router';

    // importando o componente de rotas
    @Component({
        imports: [RouterOutlet]
    })
    ```

    - chamando o componente de navegação no componente principal
    $$ [...]/app.component.html $$
    ```html
    <main>
        <router-outlet/>
    </main>

    ```
    - importando o gerenciador de rotas no componente de navegação
    $$ [...]/header.component.ts $$
    ```javascript
    import {RouterLink} from '@angular/router';

    // importando o componente de rotas
    @Component({
        imports: [RouterLink]
    })
    ```

    - utilizando o gerenciador de rotas no componente de navegação
    $$ [...]/header.component.html $$
    ```html
    <nav>
        <ul>
            <li routerlink="/">inicio</li>
            <li routerlink="/about">sobre nós</li>
        </ul>
    <nav>
    ```

- ### Loops e Condifionais no template
    - loops
        - a opção "track" serve para mapear cada item do array para o html individualmente
        - "track $index" pode ser substituido por "track item.id" caso haja este atributo no objeto
    ```javascript
    @for(item of signalArray(); track $index){
        <p>item.id</p>
        <p>item.nome</p>
    }
    ```

    - condicionais
    ```javascript
    @if(signalArray().length){
        <p>O array está vazio</p>
    }
    ```

- ### Requisições Http
    - configuração do provedor do cliente Http
    - $$ [...]/app.config.ts $$
    ```javascript
    import {providerHttpClient} from '@angular/commom/http'

    export const appConfig: ApplicationConfig = {
        providers: [
            providerHttpClient(),
            // [...]
        ]
    }
    ```

    - utilizando o cliente http
    ```javascript
    import {inject} from '@angular/core'
    import {HttpClient} from '@angular/commom/http'

    export class NomeService {
        http = inject(HttpClient)
        getArrayFromAPI(){
            url = "http://site.com"
            return this.http.get<Array<TipoItem>>(url)
        }
    }
    ```

    - lidando com a resultado
    ```javascript
    export class NomeComponente implements OnInit{
        service = inject(NomeService)
        itens = signal<Array<TipoItem>>([])
        ngOnInit(): void{
            service.getArrayFromAPI()
            .pipe(catchError((error)=> console.log(error)))
            .subscribe((itens)=>{
                this.itens.set(itens)
            })
        }
    }
    ```
