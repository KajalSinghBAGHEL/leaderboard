import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthorModel} from '../models/author.model';
import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class EmployeeActivityService {
    private url = environment.api.baseUrl + environment.api.routes.author.endpoint;
    private  monthlyApiUrl = environment.api.routes.monthlyReputation.endpoint;
    private scoreStreakApiUrl =  environment.api.routes.streakReputation.endpoint;
    constructor(private httpClient: HttpClient) {
    }

    getData(): Observable<AuthorModel[]> {
        return this.httpClient.get<AuthorModel[]>(this.url);
    }

    getMonthlyData(): Observable<AuthorModel[]> {
        return this.httpClient.get<AuthorModel[]>(this.monthlyApiUrl);
    }

    getStreakData(): Observable<AuthorModel[]> {
        return this.httpClient.get<AuthorModel[]>(this.scoreStreakApiUrl);
    }
}
