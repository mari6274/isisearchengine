(function () {
    angular.module('app').service('SearchService', ['$http', '$location', SearchService]);

    function SearchService($http, $location) {
        this.search = search;
        this.total = total;

        return this;

        function search(query, page, size) {
            return $http.get($location.protocol() + '://' + $location.host() + ':' + $location.port()
                + '/search?query=' + query + '&page=' + page + '&size=' + size);
        }

        function total() {
            return $http.get($location.protocol() + '://' + $location.host() + ':' + $location.port()
                + '/search/total');
        }
    }
})();