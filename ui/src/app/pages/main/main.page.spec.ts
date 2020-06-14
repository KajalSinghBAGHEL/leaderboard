import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {IonicModule} from '@ionic/angular';

import {MainPage} from './main.page';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {EmployeeActivityService} from '../../services/employee-activity.service';
import {AuthorModel} from '../../models/author.model';
import {HeadersComponent} from '../../components/headers/headers.component';
import {of} from 'rxjs';
import {TableComponent} from '../../components/table/table.component';
import {EmployeeFilterPipe} from '../../pipe/employee-filter.pipe';
import {ReactiveFormsModule} from '@angular/forms';
import {By} from '@angular/platform-browser';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';

describe('MainPage', () => {
    let component: MainPage;
    let fixture: ComponentFixture<MainPage>;
    let mockEmployeeService: EmployeeActivityService;
    const dummyAuthorData: AuthorModel[] = [
        {
            knolderId: 1,
            knolderName: 'mark',
            allTimeScore: 10,
            allTimeRank: 2,
            quarterlyStreak: '5-6-7',
            monthlyScore: 7,
            monthlyRank: 1
        }, {
            knolderId: 2,
            knolderName: 'sam',
            allTimeScore: 15,
            allTimeRank: 1,
            quarterlyStreak: '5-6-8',
            monthlyScore: 5,
            monthlyRank: 2
        }
    ];

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [MainPage, HeadersComponent, TableComponent, EmployeeFilterPipe],
            imports: [HttpClientTestingModule, IonicModule.forRoot(), RouterTestingModule, ReactiveFormsModule, NgxDatatableModule],
            providers: [EmployeeFilterPipe]
        }).compileComponents();

        fixture = TestBed.createComponent(MainPage);
        component = fixture.componentInstance;
        mockEmployeeService = TestBed.get(EmployeeActivityService);
        fixture.detectChanges();
    }));

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should return the authorData as per api call', () => {
        spyOn(mockEmployeeService, 'getData').and.returnValue(of(dummyAuthorData));
        component.ngOnInit();
        expect(component.employeeData).toEqual(dummyAuthorData);
    });

    it('should call the filterEmp on keyup', () => {
        spyOn(component, 'filterEmp');
        fixture.detectChanges();
        const input = fixture.debugElement.query(By.css('input'));
        const inputElement = input.nativeElement;
        inputElement.dispatchEvent(new Event('keyup'));
        expect(component.filterEmp).toHaveBeenCalled();
    });

    it('should filter Employee', () => {
        component.empFilterPipe = new EmployeeFilterPipe();
        component.employeeData = dummyAuthorData;
        component.searchBar.setValue('mark');
        component.filterEmp();
        expect(component.filteredEmpData).toEqual([dummyAuthorData[0]]);
    });
});
