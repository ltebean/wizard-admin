var groupOperation = function ($http) {
    //load a group
    var load = function (groupName, cb) {
        $http.get("/api/group/" + groupName)
            .success(function (data) {
                cb(data);
            }).error(function (data) {
                alert("server error");
            });
    }
    //save the group
    var save = function (group, cb) {
        $http.post("/api/group/", group)
            .success(function (data) {
                cb(data);
            }).error(function (data) {
                alert("server error");
            });
    }

    var loadAll = function (cb) {
        $http.get("/api/group")
            .success(function (data) {
                cb(data);
            }).error(function (data) {
                alert("server error");
            });
    }


    return {
        load: load,
        save: save,
        loadAll: loadAll
    };
}