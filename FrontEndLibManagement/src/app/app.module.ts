import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, FormGroup, FormControl} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ReaderauthComponent } from './auth/readerauth/readerauth.component';
import { AdminauthComponent } from './auth/adminauth/adminauth.component';
import { HeaderComponent } from './header/header/header.component';
import { ReaderwelcomeComponent } from './welcome/readerwelcome/readerwelcome.component';
import { AdminwelcomeComponent } from './welcome/adminwelcome/adminwelcome.component';
import { AddDocumentComponent } from './welcome/adminwelcome/add-document/add-document.component';
import { AddreaderComponent } from './welcome/adminwelcome/addreader/addreader.component';
import { AllbranchComponent } from './welcome/adminwelcome/allbranch/allbranch.component';
import { DocumentstatusComponent } from './welcome/readerwelcome/documentstatus/documentstatus.component';
import { FinesComponent } from './welcome/readerwelcome/fines/fines.component';
import { TestComponent } from './test/test/test.component';
import { DocumentsearchadminComponent } from './welcome/adminwelcome/documentsearchadmin/documentsearchadmin.component';
import { FrequentBorrowersComponent } from './welcome/adminwelcome/frequent-borrowers/frequent-borrowers.component';
import { MostBorrowedComponent } from './welcome/adminwelcome/most-borrowed/most-borrowed.component';
import { MostPopularComponent } from './welcome/adminwelcome/most-popular/most-popular.component';
import { AvgFineComponent } from './welcome/adminwelcome/avg-fine/avg-fine.component';

@NgModule({
  declarations: [
    AppComponent,
    ReaderauthComponent,
    AdminauthComponent,
    HeaderComponent,
    ReaderwelcomeComponent,
    AdminwelcomeComponent,
    AddDocumentComponent,
    AddreaderComponent,
    AllbranchComponent,
    DocumentstatusComponent,
    FinesComponent,
    TestComponent,
    DocumentsearchadminComponent,
    FrequentBorrowersComponent,
    MostBorrowedComponent,
    MostPopularComponent,
    AvgFineComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
