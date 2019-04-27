import { Component, OnInit } from '@angular/core';
import {DocumentsearchService} from '../../../service/documentsearch.service';
import {BranchService} from '../../../service/branch.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-add-document',
  templateUrl: './add-document.component.html',
  styleUrls: ['./add-document.component.css']
})
export class AddDocumentComponent implements OnInit {

  constructor(private documemtService: DocumentsearchService, private branchService: BranchService) { }

  allDocInfoArr:any = [] ;
  allBranchInfoArr : any = [];
  docCopyAddedMsg: string;

  ngOnInit() {
    this.findAllDocInfo();
    this.findAllBranchInfo();
  }

  findAllDocInfo(){
    this.documemtService.findAllDocTitleAndIdService().subscribe(
      (resp) => {
        this.allDocInfoArr = resp;
      }
    );
  }

  findAllBranchInfo(){
    this.branchService.getBranchIdAndLocation().subscribe(
      (resp) =>{
       this.allBranchInfoArr = resp;
      }
    );
  }

  addDocCopy(docCpFrm: NgForm){
    this.documemtService.addDocumentCopy(docCpFrm).subscribe(
      (resp) =>{
        this.docCopyAddedMsg = resp['message'];
        console.log(this.docCopyAddedMsg)
      }
    );
  }

}
