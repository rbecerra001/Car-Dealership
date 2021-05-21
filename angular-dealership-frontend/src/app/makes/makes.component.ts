import { MakeService } from 'src/app/services/make/make.service';
import { Component, OnInit } from '@angular/core';
declare const M;

@Component({
  selector: 'app-makes',
  templateUrl: './makes.component.html',
  styleUrls: ['./makes.component.css']
})

export class MakesComponent implements OnInit {
  public makes: any[];
  public makeName: string;
  public modelName: string;

  constructor(private makeService: MakeService) { }

  getMakes(): any {
    this.makeService.getMakes().subscribe(response => {
      this.makes = response;
    }, err => console.log(err));
  }

  ngOnInit(): void {
    this.getMakes();

    if (!localStorage.getItem('currentUser')) {
      const toastHTML = '<span>You must login to see your makes</span>';
      M.toast({html: toastHTML});
    }
  }

  createMake(): any {
    const newMake = {
      name: this.makeName,
    };
    this.makeService.createMake(newMake).subscribe(response => {
      this.makes = [...this.makes, response];
    }, err => console.log(err));
  }
}
