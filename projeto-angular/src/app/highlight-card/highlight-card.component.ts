import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-highlight-card',
  templateUrl: './highlight-card.component.html',
  styleUrls: ['./highlight-card.component.css']
})
export class HighlightCardComponent implements OnInit {

  title = 'projeto-angular';

  constructor() { }

  ngOnInit(): void {
  }

}
