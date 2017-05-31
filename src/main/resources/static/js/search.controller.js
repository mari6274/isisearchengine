(function () {
    angular.module('app').controller('SearchController', ['SearchService', SearchController]);

    function SearchController(SearchService) {
        var vm = this;
        vm.query = '';
        vm.searchResult = [];
        vm.search = search;

        function search() {
            SearchService.search(vm.query).then(
                function (result) {
                    vm.searchResult = result.data;
                }
            );
        }
    }
})();