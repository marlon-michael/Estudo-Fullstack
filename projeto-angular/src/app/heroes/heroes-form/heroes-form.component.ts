import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-heroes-form',
  templateUrl: './heroes-form.component.html',
  styleUrls: ['./heroes-form.component.css']
})
export class HeroesFormComponent{

  heroForm = new FormGroup({
    name: new FormControl('', Validators.required),
    secretIdentity: new FormControl('')
  });

  //PEGAR NO GIT - CORRIGIR
  // constructor(private formBuilder: FormBuilder);

}
