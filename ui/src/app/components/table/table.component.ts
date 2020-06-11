import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthorModel} from '../../models/author.model';
import {Router} from '@angular/router';

@Component({
    selector: 'app-table',
    templateUrl: './table.component.html',
    styleUrls: ['./table.component.scss'],
    encapsulation: ViewEncapsulation.None,
})

export class TableComponent implements OnInit {
    @Input() tableRows: AuthorModel[];
    @Input() dataKeys: string[];
    id: number;
    columns = [{name: 'Name', prop: 'knolderName', headerClass: 'table-header-style', cellClass: 'table-cell-style'},
        {name: 'Overall Score', prop: 'allTimeScore', headerClass: 'table-header-style', cellClass: 'table-cell-style'},
        {name: 'Overall Rank', prop: 'allTimeRank', headerClass: 'table-header-style', cellClass: 'table-cell-style'},
        {name: 'Monthly Score', prop: 'monthlyScore', headerClass: 'table-header-style', cellClass: 'table-cell-style'},
        {name: 'Monthly Rank', prop: 'monthlyRank', headerClass: 'table-header-style', cellClass: 'table-cell-style'},
        {
            name: '3 Month Streak',
            prop: 'quarterlyStreak',
            sortable: false,
            headerClass: 'table-header-style',
            cellClass: 'table-cell-style'
        }];

    constructor(public router: Router) {
    }

    ngOnInit() {
    }

    onActivate(event) {
        if (event.type === 'click') {
            this.id = event.row.knolderId;
        }
        this.router.navigate(['/details', this.id]);
    }
}
