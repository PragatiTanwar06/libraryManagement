import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ReaderauthComponent} from './auth/readerauth/readerauth.component';
import {AdminauthComponent} from './auth/adminauth/adminauth.component';
import {ReaderwelcomeComponent} from './welcome/readerwelcome/readerwelcome.component';
import {ReaderGuard} from './guard/reader.guard';
import {AdminwelcomeComponent} from './welcome/adminwelcome/adminwelcome.component';
import {AdminGuard} from './guard/admin.guard';
import {AddDocumentComponent} from './welcome/adminwelcome/add-document/add-document.component';
import {AddreaderComponent} from './welcome/adminwelcome/addreader/addreader.component';
import {AllbranchComponent} from './welcome/adminwelcome/allbranch/allbranch.component';
import {DocumentstatusComponent} from './welcome/readerwelcome/documentstatus/documentstatus.component';
import {FinesComponent} from './welcome/readerwelcome/fines/fines.component';
import {TestCommand} from '@angular/cli/commands/test-impl';
import {TestComponent} from './test/test/test.component';
import {DocumentsearchadminComponent} from './welcome/adminwelcome/documentsearchadmin/documentsearchadmin.component';
import {FrequentBorrowersComponent} from './welcome/adminwelcome/frequent-borrowers/frequent-borrowers.component';
import {MostBorrowedComponent} from './welcome/adminwelcome/most-borrowed/most-borrowed.component';
import {MostPopularComponent} from './welcome/adminwelcome/most-popular/most-popular.component';
import {AvgFineComponent} from './welcome/adminwelcome/avg-fine/avg-fine.component';

const routes: Routes = [
  {path: '', redirectTo:'reader_login',pathMatch: 'full'},
  {path: 'reader_login',component: ReaderauthComponent},
  {path: 'admin_login',component: AdminauthComponent},
  {path: 'test',component: TestComponent},
  {path: 'welcome/reader',component: ReaderwelcomeComponent, canActivate:[ReaderGuard]},
  {path: 'document/status',component: DocumentstatusComponent, canActivate:[ReaderGuard]},
  {path: 'view/fine',component: FinesComponent, canActivate:[ReaderGuard]},
  {path: 'welcome/admin', component: AdminwelcomeComponent, canActivate:[AdminGuard]},
  {path: 'add/documet', component: AddDocumentComponent, canActivate:[AdminGuard]},
  {path: 'add/reader', component: AddreaderComponent, canActivate:[AdminGuard]},
  {path: 'branch/info', component: AllbranchComponent, canActivate:[AdminGuard]},
  {path: 'search/document', component: DocumentsearchadminComponent, canActivate:[AdminGuard]},
  {path: 'frequent/borrowers', component: FrequentBorrowersComponent, canActivate:[AdminGuard]},
  {path: 'most/borrowed/books', component: MostBorrowedComponent, canActivate:[AdminGuard]},
  {path: 'popular/books', component: MostPopularComponent, canActivate:[AdminGuard]},
  {path: 'avg/fine', component: AvgFineComponent, canActivate:[AdminGuard]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
