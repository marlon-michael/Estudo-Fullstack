import { Component, OnInit } from '@angular/core';
import { HeroesFormComponent } from '../heroes-form/heroes-form.component';

export type Hero = {
  id: number,
  heroName: string,
  secretIdentity?: string
  universe: "MARVEL" | "DC"
  alive: boolean
}

@Component({
  selector: 'app-heroes-screen',
  templateUrl: './heroes-screen.component.html',
  styleUrls: ['./heroes-screen.component.css']
})
export class HeroesScreenComponent implements OnInit {

  editingHero: Hero | null | undefined = null;
  create: boolean = false;

  heroes: Hero[] = [{
    id: 1,
    heroName: "Iron man",
    universe: "MARVEL",
    secretIdentity: "Tony Stark",
    alive: false
  },
  {
    id: 2,
    heroName: "Hulk",
    universe: "MARVEL",
    secretIdentity: "Bruce Benner",
    alive: true
  },
  {
    id: 3,
    heroName: "Capitão America",
    universe: "MARVEL",
    secretIdentity: "Steven Roggers",
    alive: true
  },
  {
    id: 4,
    heroName: "Batman",
    universe: "DC",
    secretIdentity: "Bruce Wayne",
    alive: true
  }
];

  clear(){
    this.editingHero = null;
  }

  save(hero: Hero){
    if (hero.id == null){
      hero.id = (
        this.heroes.length > 0? this.heroes.map((h:Hero)=> h.id).sort()[this.heroes.length-1]:0
      )+1
      this.heroes.push(hero);
    }
    else{
      let pos = this.heroes.findIndex((h:Hero)=>h.id!==hero.id!)
      this.heroes[pos] = hero;
    }
  }

  remove(hero: Hero){
    this.heroes = this.heroes.filter((h:Hero)=>h.id!==hero.id!)
  }

  edit(heroID: number){
    this.editingHero = this.heroes.find((h: Hero) => h.id! == heroID!);
    console.log(this.editingHero?.id)
    this.create = true;
  }

  constructor() { }

  ngOnInit(): void {
  }

}
