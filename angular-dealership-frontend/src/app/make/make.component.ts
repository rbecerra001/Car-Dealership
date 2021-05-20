import { MakeService } from 'src/app/services/make/make.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-make',
  templateUrl: './make.component.html',
  styleUrls: ['./make.component.css']
})
export class MakeComponent implements OnInit {
  makeId: string;
  make: any;
  modelName = '';

  constructor(private route: ActivatedRoute, private makeService: MakeService) { }

  createModel(): any {
    console.log('component: ', this.make, this.modelName);
    const newModel = {name: this.modelName};
    this.makeService.createModel(this.make, newModel).subscribe(response => {
      console.log(response);
    });
  }

  ngOnInit(): void {
    this.route.paramMap
      .subscribe( params => {
        this.makeId = params.get('id');
        this.makeService.getModel(this.modelId).subscribe(response => {
          this.make = response;
          console.log(this.make);
        });
      });
  }
}
