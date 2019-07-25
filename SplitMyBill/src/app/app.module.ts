import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SocialLoginModule } from 'angularx-social-login';
import { AuthServiceConfig, GoogleLoginProvider } from 'angularx-social-login';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module';
import { HeaderComponent } from './header/header.component';
import { AddcardComponent } from './addcard/addcard.component';
import { ProfileComponent } from './profile/profile.component';
import { HttpClientModule } from '@angular/common/http';
import { ViewcardComponent } from './viewcard/viewcard.component';
import { FriendComponent } from './friend/friend.component';
import { AddfriendComponent } from './addfriend/addfriend.component';
import { SettlementComponent } from './settlement/settlement.component';
import { ExpenseComponent } from './expense/expense.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { MatSelectModule } from '@angular/material/select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TransactionhistoryComponent } from './transactionhistory/transactionhistory.component';
import { UserService } from './service/user.service';
import { StepploginComponent } from './stepplogin/stepplogin.component';


const config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider('1044084550891-j9uc8leg0tlp40n9geec23je75ludseh.apps.googleusercontent.com')
  },
]);

export function provideConfig() {
  return config;
}

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    HeaderComponent,
    AddcardComponent,
    ProfileComponent,
    ViewcardComponent,
    FriendComponent,
    AddfriendComponent,
    SettlementComponent,
    ExpenseComponent,
    TransactionhistoryComponent,
    StepploginComponent
  ],

  imports: [
    BrowserModule,
    FormsModule,
    SocialLoginModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    ModalModule.forRoot(),
    MatSelectModule,
    BrowserAnimationsModule
  ],
  providers: [
    {
      provide: AuthServiceConfig,
      useFactory: provideConfig
    },
    UserService
  ],
  bootstrap: [AppComponent],

})
export class AppModule { }