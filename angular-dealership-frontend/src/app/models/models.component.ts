import { MakeService } from 'src/app/services/make/make.service';
import { Component, OnInit } from '@angular/core';
declare const M;

@Component({
  selector: 'app-models',
  templateUrl: './models.component.html',
  styleUrls: ['./models.component.css']
})

export class ModelsComponent implements OnInit {
  public models: any[];
  public makeName: string;
  public modelName: string;
  public miles: number;
  public price: number;

  constructor(private modelService: MakeService) { }

  getModels(): any {
    this.modelService.getModels().subscribe(response => {
      this.models = response;
    }, err => console.log(err));
  }

  ngOnInit(): void {
    this.getModels();
    if (!localStorage.getItem('currentUser')) {
      const toastHTML = '<span>You must login to see your models</span>';
      M.toast({html: toastHTML});
    }
  }

  createModel(make): any {
    console.log('component: ', make, this.modelName);
    const newModel = {
      name: this.modelName,
      miles: this.miles,
      price: this.price
    };
    this.modelService.createModel(make, newModel).subscribe(response => {
      console.log(response);
    });
  }


}
