export const environment = {
    production: true,
    appVersion: '1.0.0',
    api: {
        version: '1.0.0',
        baseUrl: 'http://34.68.95.196:8000/',
        routes: {
            author: {endpoint: 'reputation', method: 'GET'},
            trends: {endpoint: 'reputation/twelvemonths', method: 'GET'}
        }
    },

    ngxChartOptions: {
        verticalBarChart: {
            barPadding: 16,
            yAxisLabel: 'score',
        },
        chartColorScheme: {
            domain: ['#15509e', '#1862c6', '#1a81ff', '#3a84e6', '#5ca6ff', '#d2e3f9']
        }
    },

    googleClientId: '6056193565-0ieknv9tmjqdi6dsufmcvh4md3pikacm.apps.googleusercontent.com'
};
