import { Component, OnInit } from '@angular/core';
import {BranchService} from '../../../service/branch.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-most-borrowed',
  templateUrl: './most-borrowed.component.html',
  styleUrls: ['./most-borrowed.component.css']
})
export class MostBorrowedComponent implements OnInit {

  constructor(private branchService: BranchService) { }

  allBranchInfoArr: any = [];
  mostBorrowedArr: any = [];
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
    this.branchService.mostBorrowedService(searchFrm).subscribe(
      (resp) => {
        if(resp['message'] === 'emptyLst'){
          this.showTable = false;
          this.noDataMsg = true;
        }else {
          this.showTable = true;
          this.noDataMsg = false;
          this.mostBorrowedArr = resp['responseObject'];
        }
      }
    );
  }

}
