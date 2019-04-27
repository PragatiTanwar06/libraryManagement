import { Component, OnInit } from '@angular/core';
import {BranchService} from '../../../service/branch.service';
import {NgForm} from '@angular/forms';
import {ReaderService} from '../../../service/reader.service';

@Component({
  selector: 'app-frequent-borrowers',
  templateUrl: './frequent-borrowers.component.html',
  styleUrls: ['./frequent-borrowers.component.css']
})
export class FrequentBorrowersComponent implements OnInit {

  constructor(private branchService: BranchService, private readerService: ReaderService) { }

  allBranchInfoArr: any = [];
  frequentBorrowerArr: any = [];
  showTable: boolean;
  noDataMsg: boolean;

  ngOnInit() {
    this.findAllBranchInfo();
  }

  findAllBranchInfo(){
    this.branchService.getBranchIdAndLocation().subscribe(
      (resp) =>{
        this.allBranchInfoArr = resp;
      }
    );
  }

  searchFrequentBorrowers(searchFrm: NgForm){
    this.readerService.frequentBorrowedService(searchFrm).subscribe(
      (resp) => {
        if(resp['message'] === 'emptyLst'){
          this.showTable = false;
          this.noDataMsg = true;
        }else {
          this.showTable = true;
          this.noDataMsg = false;
          this.frequentBorrowerArr = resp['responseObject'];
        }
      }
    );
  }

}
