import { Component, OnInit } from '@angular/core';

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

  constructor() { }

  ngOnInit(): void {
  }

}
