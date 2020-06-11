import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';

const routes: Routes = [
    {
        path: '',
        redirectTo: '/main',
        pathMatch: 'full'

    },
    {
        path: 'main',
        loadChildren: './pages/main/main.module#MainPageModule'
    },
    {
        path: 'details/:id',
        loadChildren: './pages/details/details.module#DetailsPageModule'
    },
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
