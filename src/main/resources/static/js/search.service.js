(function () {
    angular.module('app').service('SearchService', ['$http', SearchService]);

    function SearchService($http) {
        this.search = search;

        return this;

        function search(query) {
            return $http.get('http://localhost:8080/search?query=' + query);
        }
    }
})();