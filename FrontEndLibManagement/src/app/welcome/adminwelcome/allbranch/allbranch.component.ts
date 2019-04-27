import { Component, OnInit } from '@angular/core';
import {BranchService} from '../../../service/branch.service';

@Component({
  selector: 'app-allbranch',
  templateUrl: './allbranch.component.html',
  styleUrls: ['./allbranch.component.css']
})
export class AllbranchComponent implements OnInit {

  constructor(private branchService: BranchService) { }

  allBranchArr: any = [];

  ngOnInit() {
    this.showAllBranch();
  }

  showAllBranch(){
    this.branchService.getBranchIdAndLocation().subscribe(
      (resp) => {
        this.allBranchArr = resp;
      }
    );
  }

}
