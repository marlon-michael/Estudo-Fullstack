import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Hero } from 'src/app/heroes/heroes-screen/heroes-screen.component';

@Component({
  selector: 'app-heroes-form',
  templateUrl: './heroes-form.component.html',
  styleUrls: ['./heroes-form.component.css']
})
export class HeroesFormComponent implements OnChanges {
  
  @Input() editingHero? : Hero | null | undefined;
  @Output() heroSave = new EventEmitter<Hero>();

  heroForm = this.formBuilder.group({
    id: [null],
    heroName: ['', Validators.required],
    secretIdentity: [''],
    alive: [true],
    universe: ['', Validators.required]
  });

  onSubmit(){
    this.heroSave.emit(this.heroForm.value as unknown as Hero);
    this.heroForm.reset();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.heroForm.reset();
    this.heroForm.patchValue(changes["editingHero"].currentValue);
  }

  constructor(private formBuilder: FormBuilder){}

}
