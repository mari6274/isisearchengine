(function () {
    angular.module('app').service('SearchService', ['$http', '$location', SearchService]);

    function SearchService($http, $location) {
        this.search = search;

        return this;

        function search(query) {
            return $http.get($location.protocol() + '://' + $location.host() + ':' + $location.port() + '/search?query=' + query);
        }
    }
})();