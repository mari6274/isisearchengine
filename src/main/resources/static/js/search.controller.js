(function () {
    angular.module('app').controller('SearchController', ['SearchService', '$window', 'usSpinnerService', SearchController]);

    function SearchController(SearchService, $window, usSpinnerService) {
        var vm = this;
        const PAGE_SIZE = 20;
        const MAX_PAGES = 100;
        vm.query = '';
        vm.searchResult = null;
        vm.search = search;
        vm.isError = isError;
        vm.errorMessage = '';
        vm.paginatorConfig = {
            currentPage: 1,
            maxSize: 5
        };
        vm.pageChanged = pageChanged;

        SearchService.total().then(function (response) {
            vm.total = response.data ;
        });

        function pageChanged() {
            SearchService.search(vm.query, vm.paginatorConfig.currentPage - 1, PAGE_SIZE)
                .then(onSearchSuccess, onSearchFailure)
                .finally(scrollTop);
        }

        function search() {
            usSpinnerService.spin('spinner');
            SearchService.search(vm.query, 0, PAGE_SIZE)
                .then(function (response) {
                    onSearchSuccess(response);
                    vm.paginatorConfig.currentPage = 1;
                }, onSearchFailure)
                .finally(function() {
                    scrollTop();
                    usSpinnerService.stop('spinner');
                });
        }

        function onSearchSuccess(response) {
            vm.errorMessage = '';
            vm.searchResult = response.data;
            vm.searchResult.totalElements = Math.min(vm.searchResult.totalElements, PAGE_SIZE * MAX_PAGES)
        }

        function onSearchFailure(error) {
            vm.errorMessage = 'An error occured: ' + error.status;
            vm.searchResult = null;
        }

        function isError() {
            return vm.errorMessage !== '';
        }

        function scrollTop() {
            $window.scrollTo(0, 0);
        }
    }
})();