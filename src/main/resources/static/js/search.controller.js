(function () {
    angular.module('app').controller('SearchController', ['SearchService', SearchController]);

    function SearchController(SearchService) {
        var vm = this;
        vm.query = '';
        vm.searchResult = [];
        vm.search = search;
        vm.isError = isError;
        vm.errorMessage = '';

        function search() {
            SearchService.search(vm.query).then(onSearchSuccess, onSearchFailure);
        }

        function onSearchSuccess(result) {
            vm.errorMessage = '';
            vm.searchResult = result.data;
        }

        function onSearchFailure(error) {
            vm.errorMessage = 'An error occured: ' + error.status;
        }

        function isError() {
            return vm.errorMessage !== '';
        }
    }
})();