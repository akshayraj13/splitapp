import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class UserService {


    public dispalyNavbar: boolean;
    public dispalyEventEmitter: EventEmitter<any> = new EventEmitter();

    public setDisplayNavbar(value: boolean) {
        this.dispalyNavbar = value;
        this.dispalyEventEmitter.emit(value);
    }

    public getDisplayNavbar() : boolean {
        return this.dispalyNavbar;
    }
}


