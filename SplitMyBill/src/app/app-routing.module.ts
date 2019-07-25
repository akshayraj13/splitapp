import { NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AddcardComponent } from './addcard/addcard.component';
import { ProfileComponent } from './profile/profile.component';
import { ViewcardComponent } from './viewcard/viewcard.component';
import { FriendComponent } from './friend/friend.component';
import { AddfriendComponent } from './addfriend/addfriend.component';
import { SettlementComponent } from './settlement/settlement.component';
import { ExpenseComponent } from './expense/expense.component';
import { TransactionhistoryComponent } from './transactionhistory/transactionhistory.component';
import { AuthGaurdService } from './service/auth-gaurd.service';
import { StepploginComponent } from './stepplogin/stepplogin.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'addcard', component: AddcardComponent,canActivate:[AuthGaurdService]},
  { path: 'profile', component: ProfileComponent ,canActivate:[AuthGaurdService]},
  { path: 'getcard', component: ViewcardComponent ,canActivate:[AuthGaurdService]},
  { path: 'friend', component: FriendComponent,canActivate:[AuthGaurdService]},
  { path: 'addfriend', component: AddfriendComponent,canActivate:[AuthGaurdService]},
  { path: 'settlement', component: SettlementComponent,canActivate:[AuthGaurdService]},
  { path: 'expense', component: ExpenseComponent ,canActivate:[AuthGaurdService]},
  { path: 'transactionhistory', component: TransactionhistoryComponent,canActivate:[AuthGaurdService]},
  { path: '',  component:HomeComponent, pathMatch: 'full' },
  { path: 'stepplogin', component: StepploginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
