import { Component, OnInit } from '@angular/core';
import {DocumentsearchService} from '../../../service/documentsearch.service';

@Component({
  selector: 'app-most-popular',
  templateUrl: './most-popular.component.html',
  styleUrls: ['./most-popular.component.css']
})
export class MostPopularComponent implements OnInit {

  constructor(private documentService: DocumentsearchService) { }

  mostPopularArr: any = [];
  showTable: boolean;
  noDataMsg: boolean;

  ngOnInit() {
    this.findMostPopularBooks();
  }

  findMostPopularBooks(){
    this.documentService.mostPopularDocumentService().subscribe(
      (resp) => {
        if(resp['message'] === 'emptyLst'){
          this.showTable = false;
          this.noDataMsg = true;
        }else{
          this.showTable = true;
          this.noDataMsg = false;
          this.mostPopularArr = resp['responseObject'];
        }
      }
    );
  }

}
