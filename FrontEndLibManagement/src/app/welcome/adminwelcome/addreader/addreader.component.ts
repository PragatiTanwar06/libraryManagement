import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {ReaderService} from '../../../service/reader.service';

@Component({
  selector: 'app-addreader',
  templateUrl: './addreader.component.html',
  styleUrls: ['./addreader.component.css']
})
export class AddreaderComponent implements OnInit {

  constructor(private readerService: ReaderService) { }

  addReaderMsg;
  addReaderError: boolean;

  ngOnInit() {
  }

  addReader(readerFrm: NgForm){
    console.log(readerFrm);
    this.readerService.addReader(readerFrm).subscribe(
      (resp) => {
        if(resp['message'] === 'success'){
          this.addReaderMsg = 'Reader Added Successfully';
        }
        if(resp['message'] === 'error'){
          this.addReaderMsg = 'There is something wrong, please contact IT Services';
        }
        if(resp['message'] === 'addUserError'){
          this.addReaderError = true;
        }
      }
    );
  }

}
