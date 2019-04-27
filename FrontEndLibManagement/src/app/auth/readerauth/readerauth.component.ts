import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-readerauth',
  templateUrl: './readerauth.component.html',
  styleUrls: ['./readerauth.component.css']
})
export class ReaderauthComponent implements OnInit {

  errorStatus: boolean;

  constructor(public authservice: AuthService, private route: Router,private router: Router) { }

  ngOnInit() {
    if (this.authservice.getReaderType() !== null){
      this.router.navigate(['welcome/reader']);
    }
  }

  login(readerLogFrm: NgForm){
    this.authservice.loginReaderService(readerLogFrm).subscribe(
      (resp) => {
        this.errorStatus = resp['status'];
        if(this.errorStatus) {
          const readerId = resp['responseObject'].readerId;
          const readerType = resp['responseObject'].readerType;
          sessionStorage.setItem('readerId', readerId);
          sessionStorage.setItem('rtype', readerType);
          this.route.navigate(['welcome/reader']);
        }
      }
    );
  }



}
