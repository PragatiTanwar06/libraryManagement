import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {DocumentsearchService} from '../../../service/documentsearch.service';

@Component({
  selector: 'app-documentsearchadmin',
  templateUrl: './documentsearchadmin.component.html',
  styleUrls: ['./documentsearchadmin.component.css']
})
export class DocumentsearchadminComponent implements OnInit {

  constructor(private docSearchService: DocumentsearchService) { }

  docSearchArr: any = [];
  docSearchMsg: boolean;
  showTable: boolean;

  ngOnInit() {
  }

  searchDocument(searchFrm: NgForm){
    this.docSearchService.searchDocumentForAdmin(searchFrm).subscribe(
      (resp) => {
        if(resp['message'] === 'emptyLst'){
          this.docSearchMsg = true;
          this.showTable = false;
        }else {
          this.showTable = true;
          this.docSearchMsg = false;
          this.docSearchArr = resp['responseObject'];
        }
      }
    );
  }

}
